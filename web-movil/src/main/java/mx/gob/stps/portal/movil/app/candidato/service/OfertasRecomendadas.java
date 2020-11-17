package mx.gob.stps.portal.movil.app.candidato.service;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;
import mx.gob.stps.portal.movil.app.model.base.PaginadorDTO;
import mx.gob.stps.portal.movil.app.model.rest.BusquedaOfertaRestDTO;
import mx.gob.stps.portal.movil.app.service.util.LongComparadorCompatibilidad;
import mx.gob.stps.portal.movil.app.service.util.OfertaUtil;
import mx.gob.stps.portal.movil.app.service.util.OfertasRecomendadasComparador;
import mx.gob.stps.portal.movil.web.candidato.util.UtilPostulate;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.movil.web.oferta.form.OfertaJB;
import mx.gob.stps.portal.movil.web.seguridad.delegate.SeguridadMovilDelegateImpl;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;
import mx.gob.stps.portal.utils.Catalogos;

@Stateless
@Path("/ofertasRecomendadas")
public class OfertasRecomendadas {

	private Gson gson = new Gson();
	private static Logger logger = Logger.getLogger(OfertasRecomendadas.class);
	private BusquedaOfertaRestDTO ofertaRecomendada = new BusquedaOfertaRestDTO();
	private SeguridadMovilDelegateImpl services = SeguridadMovilDelegateImpl.getInstance();
	private int compatibilityLimit = Constantes.COMPATIBILITY_LIMIT;

	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{pagina}/{resultadoPorPagina}")
	public String ofertaRecomendada(
			@DefaultValue("0") @PathParam("pagina") final String paginaStr,
			@DefaultValue("10") @PathParam("resultadoPorPagina") final String resultadoPorPaginaStr,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		Long idCandidato = 0L;
		String cookie = request.getHeader(CookieSession.NAME);

		if (cookie == null) {
			ofertaRecomendada.setResult("No se encuentra el Token.");
			return gson.toJson(ofertaRecomendada);
		}
		List<OfertaPorPerfilVO> ofertas = new ArrayList<OfertaPorPerfilVO>();
		List<BusquedaOfertaDTO> ofertasDetaile = new ArrayList<BusquedaOfertaDTO>();

		try {
			// validar el cookie para obtener el candidato
			MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
			
			
			//validar si existe el cookie o es un forma externo de generar el cookie
			MovilSessionVO movilExiste = services.existeMovilSessionVO(movilSession);
			if(movilExiste.getToken().equals(movilSession.getToken())){
				ofertaRecomendada.setResult("Cookie no valido porfavor reinicia sessión.");
				return gson.toJson(ofertaRecomendada);
			}
			
			//String token = SessionUtility.makeToken(salt, userNameOrEmail, deviceId);
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			//Regresa el id de la oferta y la compatibilidad
			List<Long[]> idOffers = services.buscarOfertasRecomendadas(idCandidato, compatibilityLimit);
			//Buscar los ofertas y solo dejar los activos
			ofertas = getOfertasPerfilFiltradas(idOffers);
			//Poner el tamaño
			ofertaRecomendada.setTamano(ofertas.size());
			//Poner el limite de los ofertas
			ofertasDetaile = limitarOfertaRecomendada(paginaStr,resultadoPorPaginaStr, ofertas, idCandidato);

		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			ofertaRecomendada.setResult(se.getMessage());
		}

		ofertaRecomendada.setOfertas(ofertasDetaile);

		return gson.toJson(ofertaRecomendada);
	}

	private List<BusquedaOfertaDTO> limitarOfertaRecomendada(String paginaStr,
			String resultadoPorPaginaStr, List<OfertaPorPerfilVO> ofertas,
			Long idCandidato) {
		List<BusquedaOfertaDTO> lista = new ArrayList<BusquedaOfertaDTO>();
		// obtener cuanto resultados debemos regresar
		int resultados = Integer.valueOf(resultadoPorPaginaStr);
		// desde cual resultado regresar
		int offset = Integer.valueOf(paginaStr) * resultados;

		for (int i = 0; i < resultados && (i + offset) < ofertas.size(); i++) {
			Long idOferta = ofertas.get(i + offset).getIdOfertaEmpleo();
			BusquedaOfertaDTO test = busquedaDetalle(idOferta);
			test.getOferta().setTelefonos(new ArrayList<TelefonoVO>());
			//calculo de la compatibilidad
			if (idCandidato > 0L) {
				int compatibility = (int) OfertaUtil.calculaCompatibilidad(idOferta, idCandidato);
				test.setCompatibilidad(compatibility);
			}

			DateFormat formatter = new SimpleDateFormat(
					"E MMM dd HH:mm:ss z yyyy", Locale.US);
			// Mon May 07 00:00:00 CDT 2012

			Date date = new Date();
			try {
				date = (Date) formatter.parse(test.getOferta().getFechaAlta());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			test.setFechaAlta(formatter2.format(date));

			lista.add(test);
		}
		return lista;
	}

	private BusquedaOfertaDTO busquedaDetalle(long idOferta) {
		BusquedaOfertaDTO ofertaJB = new BusquedaOfertaDTO(getoffer(idOferta));
		/** Se consulta el detalle de la oferta de empleo **/
		return ofertaJB;
	}

	private OfertaJB getoffer(long offerID) {
		OfertaJB offer = new OfertaJB();

		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			OfertaEmpleoJB jb = services.buscarOfertaEmpleo(offerID);

			BeanUtils.copyProperties(offer, jb);

		} catch (BusinessException be) {
			logger.error(be);
		} catch (ServiceLocatorException le) {
			logger.error(le);
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}

		return offer;
	}

	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String getOfertaRecomendada(String paginadorJson,
			@DefaultValue("0") @PathParam("id") final Long idCandidatoPrueba,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		//System.out.println("------  rrrrrr "+paginaStr);
		System.out.println("*******---------------------------------------------------- paginadorJson "+idCandidatoPrueba);
		ofertaRecomendada = new BusquedaOfertaRestDTO();
		PaginadorDTO paginador = new PaginadorDTO();
		try {
			paginador = gson.fromJson(paginadorJson, PaginadorDTO.class);
		} catch (JsonSyntaxException ex) {
			ofertaRecomendada.setResult("Sintaxis Json invalido");
			return gson.toJson(ofertaRecomendada);
		}
		
		Long idCandidato = 0L;
		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			ofertaRecomendada.setResult("No se encuentra el Token.");
			return gson.toJson(ofertaRecomendada);
		}
		MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
		idCandidato = movilSession.getIdCandidato();
		List<OfertaPorPerfilVO> misOfertas = new ArrayList<OfertaPorPerfilVO>();
		OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();

		List<Long[]> idOffers;
		//try {
			
						Long[] l_ID = {idCandidatoPrueba};
						List<Long[]> lista = new ArrayList<Long[]>();
						lista.add(l_ID);
						idOffers = lista;
						
						
						
			//idOffers = services.buscarOfertasRecomendadas(idCandidato, compatibilityLimit);
			//Se ordena la lista por compatibilidad
			Collections.sort(idOffers,new LongComparadorCompatibilidad());
			// se agrega los detalles de la oferta
 			misOfertas = getOfertasPerfilFiltradas(idOffers);			
		//} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}

		/* se ordena la lista deacuerdo a la compatibilidad */
		//Collections.sort(misOfertas, new OfertasRecomendadasComparador());
		
		misOfertasRecomendadas(idCandidato, paginador, misOfertas);

		return gson.toJson(ofertaRecomendada);

	}
	
	

	private List<OfertaPorPerfilVO> dummyService() throws ServiceLocatorException {
		List<OfertaPorPerfilVO> ofertas = new ArrayList<OfertaPorPerfilVO>();
		for (int i = 0; i < 30; i++) {
			OfertaPorPerfilVO oferta = new OfertaPorPerfilVO();
			oferta.setIdOfertaEmpleo(i);
			oferta.setCompatibilidad(i);
			ofertas.add(oferta);
		}
		return ofertas;
	}

	private void misOfertasRecomendadas(Long idCandidato,
			PaginadorDTO paginador, List<OfertaPorPerfilVO> misOfertas) {

		ofertaRecomendada.setTamano(misOfertas.size());
		if (paginador.getDetalle()) {
			ofertaRecomendada.setOfertas(limitarBusquedaOferta(
					paginador.getPagina(), paginador.getResultadoPorPagina(),
					misOfertas, idCandidato));
		} else {
			BusquedaOfertaRestDTO ofertaRecomendadaSinDetail = new BusquedaOfertaRestDTO();
			//for (int i = 0 + (paginador.getPagina() * paginador
			//		.getResultadoPorPagina()); i < ((paginador.getPagina() + 1) * paginador
			//		.getResultadoPorPagina()) && i < misOfertas.size(); i++) {
			for (int i = 0; i < misOfertas.size(); i++){
				BusquedaOfertaDTO oferta = new BusquedaOfertaDTO();
				Long idOferta = Long.valueOf(misOfertas.get(i).getIdOfertaEmpleo());
				boolean posibleNotificacion = false;
				//
				BusquedaOfertaDTO busqOferta = OfertaUtil.busquedaDetalle(idOferta);
				if(busqOferta.getOferta().getEstatusOffer() == ESTATUS.ACTIVO.getIdOpcion()){
					posibleNotificacion = OfertaUtil.comparaFecha(busqOferta.getOferta().getFechaInicio());
					oferta.setEnTiempo(posibleNotificacion);
	
					oferta.setIdOferta(String.valueOf(idOferta));
					// oferta.getOferta().setTelefonos(new ArrayList<TelefonoVO>());
					oferta.setOferta(null);
	
					DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
					DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
					DateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
					DateFormat formatter4 = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
	
					// Mon May 07 00:00:00 CDT 2012
	
					Date dateModificacion = new Date();
					try {
	
						String fecha = busqOferta.getOferta().getFechaInicio();
						if (fecha != null && !fecha.isEmpty()) {
							dateModificacion = (Date) formatter4.parse(fecha);
						}
						oferta.setFechaModificacion(formatter2.format(dateModificacion));
						// oferta.getOferta().setFechaFin(
						// OfertaUtil.changeDate(oferta.getOferta().getFechaFin(),
						// "E MMM dd HH:mm:ss z yyyy",
						// OfertaUtil.FECHAFORMATESCRITO));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

				}

				ofertaRecomendadaSinDetail.getOfertas().add(oferta);
				}
			}
			ofertaRecomendada = ofertaRecomendadaSinDetail;
			ofertaRecomendada.setTamano(misOfertas.size());
		}
		// } catch (ServiceLocatorException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	private List<BusquedaOfertaDTO> limitarBusquedaOferta(int paginaStr,
			int resultadoPorPaginaStr, List<OfertaPorPerfilVO> ofertas,
			Long idCandidato) {
		List<BusquedaOfertaDTO> lista = new ArrayList<BusquedaOfertaDTO>();
		// obtener cuanto resultados debemos regresar
		int resultados = resultadoPorPaginaStr;
		// desde cual resultado regresar
		int offset = paginaStr * resultados;

		for (int i = 0; i < resultados && (i + offset) < ofertas.size(); i++) {
			Long idOferta = Long.valueOf(ofertas.get(i + offset).getIdOfertaEmpleo());
			int compatibility = (ofertas.get(i+offset).getCompatibilidad());
			boolean posibleNotificacion = false;

			BusquedaOfertaDTO oferta = OfertaUtil.busquedaDetalle(idOferta);//dummyDetalle(idOferta); //OfertaUtil.busquedaDetalle(idOferta);
			oferta.getOferta().setTelefonos(new ArrayList<TelefonoVO>());
			if (idCandidato > 0L) {
				if(compatibility == 0){
					compatibility = (int) OfertaUtil.calculaCompatibilidad(idOferta, idCandidato);
				}
				oferta.setCompatibilidad(compatibility);
				
				oferta.setListPostulated(UtilPostulate.getPostulated(idOferta,idCandidato));

				oferta.setIdPostulated(" ");
				oferta.setIdOferta(String.valueOf(idOferta));
				posibleNotificacion = OfertaUtil.comparaFecha(oferta.getOferta().getFechaInicio());
				oferta.setEnTiempo(posibleNotificacion);

			}

			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
			DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
			DateFormat formatter4 = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");

			// Mon May 07 00:00:00 CDT 2012

			Date date = new Date();
			Date dateModificacion = new Date();
			try {
				date = (Date) formatter.parse(oferta.getOferta().getFechaAlta());
				String fecha = oferta.getOferta().getFechaInicio();
				if (fecha != null && !fecha.isEmpty()) {
					dateModificacion = (Date) formatter4.parse(fecha);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			oferta.setFechaAlta(formatter2.format(date));
			oferta.setFechaModificacion(formatter2.format(dateModificacion));
			lista.add(oferta);
		}
		return lista;
	}

	private BusquedaOfertaDTO dummyDetalle(Long idOferta) {
		
		DateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");

		
		BusquedaOfertaDTO oferta = new BusquedaOfertaDTO();
		oferta.setCompatibilidad(Integer.valueOf(String.valueOf(idOferta)));
		String f = formatter.format(new Date());
		oferta.getOferta().setIdOfertaEmpleo(String.valueOf(idOferta));
		oferta.getOferta().setFechaInicio(f);
		oferta.getOferta().setFechaAlta(f);
		oferta.getOferta().setFechaInicio(f);
		oferta.getOferta().setFechaModificacion(f);
		return oferta;
	}
	
	public List<OfertaPorPerfilVO> getOfertasPerfilFiltradas(List<Long[]> indices){
		List<OfertaPorPerfilVO> ofertasPerfilFiltradas = new ArrayList<OfertaPorPerfilVO>();
		
		if (indices!=null && !indices.isEmpty()){
		for(Long[] id:indices){
				OfertaPorPerfilVO ofertaPorPerfilVO = new OfertaPorPerfilVO();
				BusquedaOfertaDTO oferta = OfertaUtil.busquedaDetalleActiva(id[0]);  //id[0]  idOferta	
				
				//solo agregamos los activos
				if(oferta != null && oferta.getOferta() != null){
							
					ofertaPorPerfilVO.setIdEmpresa(Long.valueOf(oferta.getOferta().getIdEmpresa()));
					ofertaPorPerfilVO.setIdOfertaEmpleo(Long.valueOf(oferta.getOferta().getIdOfertaEmpleo()));
					ofertaPorPerfilVO.setTituloOferta(oferta.getOferta().getTituloOferta());
					ofertaPorPerfilVO.setUbicacion(oferta.getOferta().getUbicacion());
					ofertaPorPerfilVO.setEmpresa(oferta.getOferta().getEmpresaNombre());
					ofertaPorPerfilVO.setSalario(Double.valueOf(oferta.getOferta().getSalario()));
					ofertaPorPerfilVO.setFuente(oferta.getOferta().getFuenteId());
					ofertaPorPerfilVO.setFunciones(oferta.getOferta().getFunciones());
					ofertaPorPerfilVO.setNumeroPlazas(Integer.valueOf(oferta.getOferta().getNumeroPlazas()));
					ofertaPorPerfilVO.setMedioContacto(oferta.getOferta().getMedioContacto());
					ofertaPorPerfilVO.setGradoEstudio(oferta.getOferta().getGradoEstudios());
					ofertaPorPerfilVO.setCarrera(oferta.getOferta().getEspecialidades());
					ofertaPorPerfilVO.setOcupacion(oferta.getOferta().getOcupacion());		
					ofertaPorPerfilVO.setCompatibilidad(76);  //id[1]  compatibilidad
					ofertaPorPerfilVO.setIdiomas(oferta.getOferta().getIdiomas());
					ofertaPorPerfilVO.setIdiomasCert(oferta.getOferta().getIdiomasCert());
					ofertaPorPerfilVO.setHabilidades(getHabilidades(oferta.getOferta().getHabilidades()));	
					ofertaPorPerfilVO.setHabilidadGeneral(oferta.getOferta().getHabilidadGeneral());
					ofertaPorPerfilVO.setEstatus(oferta.getOferta().getEstatusOffer());			
					ofertaPorPerfilVO.setContactoCorreo(oferta.getOferta().getContactoCorreo());
					ofertaPorPerfilVO.setContactoTel(oferta.getOferta().getContactoTel());	
					ofertaPorPerfilVO.setCorreoElectronicoContacto(oferta.getOferta().getCorreoElectronicoContacto());
					ofertaPorPerfilVO.setTelefonoOferta(oferta.getOferta().getTelefonoOferta());
					ofertasPerfilFiltradas.add(ofertaPorPerfilVO);	
				}
			}
	 	/*se eliminan las ofertas NO activas**/
		/*for (OfertaPorPerfilVO oferta: ofertasPerfilFiltradas) {
			if (oferta.getEstatus() != Catalogos.ESTATUS.ACTIVO.getIdOpcion()) {
				ofertasPerfilFiltradas.remove(oferta);
			}
	   	  }*/				
	    }
	
		return ofertasPerfilFiltradas;
	}
	
	private String getHabilidades(List<CatalogoOpcionVO> habilidadesCandidato) {
		String habilidades ="";
		if(habilidadesCandidato.size()>0){
			for(CatalogoOpcionVO vo: habilidadesCandidato){
				if(vo!=null){
					if(habilidades.equals(""))
						habilidades=vo.getOpcion();
					else 
						habilidades = habilidades+", "+vo.getOpcion();
				}
			}
		}
		return habilidades;
	}
    

}
