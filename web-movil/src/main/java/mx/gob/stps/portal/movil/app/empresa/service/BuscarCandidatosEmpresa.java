package mx.gob.stps.portal.movil.app.empresa.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

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

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.movil.app.empresa.model.base.BusquedaCandidatoDTO;
import mx.gob.stps.portal.movil.app.empresa.model.rest.BusquedaCandidatoRestDTO;
import mx.gob.stps.portal.movil.app.empresa.model.rest.VincularCandidatoRestDTO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.movil.app.model.rest.ParametrosBuscadorDTO;
import mx.gob.stps.portal.movil.app.service.util.BuscarCandidatoComparador;
import mx.gob.stps.portal.movil.app.service.util.CandidatoComparador;
import mx.gob.stps.portal.movil.web.candidato.delegate.CandidatoDelegateImpl;
import mx.gob.stps.portal.movil.web.candidato.form.CandidatoForm;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.TIPOACTION;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;
import mx.gob.stps.portal.utils.Catalogos.TIPO_PROPIETARIO;
import mx.gob.stps.portal.utils.ConstantesGenerales;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

@Stateless
@Path("/buscarCandidatos")
public class BuscarCandidatosEmpresa {

	private Gson gson = new Gson();
	private static Logger logger = Logger.getLogger(BuscarCandidatosEmpresa.class);
	private EmpresaEspacioDelegate services = EmpresaEspacioDelegateImpl.getInstance();
	BusquedaCandidatoRestDTO busquedaCandidato = new BusquedaCandidatoRestDTO();
	private TIPOACTION tipoAction;
	BaseRestDTO datos = new BaseRestDTO();
	


	@POST
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar")
	//@Path("{tipoConsulta}/{pagina}/{resultadoPorPagina}/{palabraClave}/{idOferta}/{idCandidato}")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String buscarCandidatos(String parametrosBuscar,			
			@Context HttpServletRequest request, @Context HttpServletResponse response) {
		ParametrosBuscadorDTO parametros = gson.fromJson(parametrosBuscar, ParametrosBuscadorDTO.class);
		String idOfertaStr = parametros.getIdOferta();
		String palabraClave = parametros.getPalabraClave();
		String tipoConsulta = parametros.getTipoConsulta();
		String idCandidatoStr = parametros.getIdCandidato();
		String paginaStr = parametros.getPagina();
		String idEntidad = parametros.getIdEntidad();
		String resultadoPorPaginaStr = parametros.getResultadoPorPagina();
		
		// then you can access the request/response/session etc in your methods
		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			busquedaCandidato.setResult("No se encuentra el Token");
			return gson.toJson(busquedaCandidato);
		}
		Long idOferta = 0L;
		if (Long.valueOf(idOfertaStr) > 0){
			idOferta = Long.valueOf(idOfertaStr);
		}
		
		List<Long> indices = new ArrayList<Long>();

		String query = null;
		if (!palabraClave.equals("0")) {
			query = palabraClave;
		}

		if (tipoConsulta.equals("0")) {
			busquedaCandidato.setResult("Tipo de consulta incorrecto");
			return gson.toJson(busquedaCandidato);
		}

		int tipo = Integer.parseInt(tipoConsulta);

		List<CandidatoVo> candidatos = new ArrayList<CandidatoVo>();
		busquedaCandidato.setResult("OK");
		switch (tipo) {
		case 6:
			tipoAction = TIPOACTION.LISTACANDIDATOS;
			
			try {

				indices = services.buscarCandidatosEmpleo(query, Integer.valueOf(idEntidad));
				candidatos = services.buscadorCandidatosQuery(indices);
				//Collections.sort(candidatos,new CandidatoComparador());
				busquedaCandidato.setTamano(candidatos.size());
				

			} catch (Exception se) {
				se.printStackTrace();
				logger.error(se);
				busquedaCandidato.setResult(se.getMessage());
				return gson.toJson(busquedaCandidato);				
			}

			busquedaCandidato.setCandidatosRec(limitarCandidatos(candidatos,Integer.valueOf(paginaStr),Integer.valueOf(resultadoPorPaginaStr)));
			return gson.toJson(busquedaCandidato);

		case 7:
			tipoAction = TIPOACTION.DETALLECANDIDATO;
			BusquedaCandidatoRestDTO candidatoDetail = new BusquedaCandidatoRestDTO();
			BusquedaCandidatoDTO busquedaCandidatoDto = detalleCandidato(idOferta, Long.valueOf(idCandidatoStr));
			
			candidatoDetail.setCandidato(busquedaCandidatoDto);

			return gson.toJson(candidatoDetail);
			
		default: 
			busquedaCandidato.setResult("Revisa las instrucciones de la api");

			break;
		}

		return gson.toJson(busquedaCandidato);

	}
	
	
	
	
	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{tipoConsulta}/{pagina}/{resultadoPorPagina}/{palabraClave}/{idOferta}/{idCandidato}")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String buscarCandidatos(@DefaultValue("0") @PathParam("tipoConsulta") final String tipoConsulta,
			@DefaultValue("0") @PathParam("pagina") final String paginaStr,
			@DefaultValue("10") @PathParam("resultadoPorPagina") final String resultadoPorPaginaStr,
			@DefaultValue("0") @PathParam("palabraClave") final String palabraClave,
			@DefaultValue("0") @PathParam("idOferta") final String idOfertaStr,
			@DefaultValue("0") @PathParam("idCandidato") final String idCandidatoStr,			
			@Context HttpServletRequest request, @Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			busquedaCandidato.setResult("No se encuentra el Token");
			return gson.toJson(busquedaCandidato);
		}
		Long idOferta = 0L;
		if (Long.valueOf(idOfertaStr) > 0){
			idOferta = Long.valueOf(idOfertaStr);
		}
		
		List<Long> indices = new ArrayList<Long>();

		String query = null;
		if (!palabraClave.equals("0")) {
			query = palabraClave;
		}

		if (tipoConsulta.equals("0")) {
			busquedaCandidato.setResult("Tipo de consulta incorrecto");
			return gson.toJson(busquedaCandidato);
		}

		int tipo = Integer.parseInt(tipoConsulta);

		List<CandidatoVo> candidatos = new ArrayList<CandidatoVo>();
		busquedaCandidato.setResult("OK");
		switch (tipo) {
		case 6:
			tipoAction = TIPOACTION.LISTACANDIDATOS;
			
			try {

				indices = services.buscarCandidatosEmpleo(query);
				candidatos = services.buscadorCandidatos(indices);
				Collections.sort(candidatos,new CandidatoComparador());
				busquedaCandidato.setTamano(candidatos.size());
				

			} catch (Exception se) {
				se.printStackTrace();
				logger.error(se);
				busquedaCandidato.setResult(se.getMessage());
				return gson.toJson(busquedaCandidato);				
			}

			busquedaCandidato.setCandidatosRec(limitarCandidatos(candidatos,Integer.valueOf(paginaStr),Integer.valueOf(resultadoPorPaginaStr)));
			return gson.toJson(busquedaCandidato);

		case 7:
			tipoAction = TIPOACTION.DETALLECANDIDATO;
			BusquedaCandidatoRestDTO candidatoDetail = new BusquedaCandidatoRestDTO();
			BusquedaCandidatoDTO busquedaCandidatoDto = detalleCandidato(idOferta, Long.valueOf(idCandidatoStr));
			
			candidatoDetail.setCandidato(busquedaCandidatoDto);

			return gson.toJson(candidatoDetail);
			
		default: 
			busquedaCandidato.setResult("Revisa las instrucciones de la api");

			break;
		}

		return gson.toJson(busquedaCandidato);

	}

	private List<CandidatoVo> limitarCandidatos(List<CandidatoVo> candidatos,
			Integer pagina, Integer porPagina) {
		//ordenar la lista
		Collections.sort(candidatos, new BuscarCandidatoComparador());
		List<CandidatoVo> candidatosPaginador = new ArrayList<CandidatoVo>();
		if (candidatos != null && candidatos.size() > 0) {

			if (candidatos.size() < ((pagina + 1) * porPagina)) {
				candidatosPaginador = candidatos.subList(
						pagina * porPagina, candidatos.size());
			} else {
				candidatosPaginador = candidatos.subList(
						pagina * porPagina, ((pagina + 1) * porPagina));
			}
		}

		return candidatosPaginador;
	}

	
	@POST
	// Actualizar el estatus de oferta_candidato
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String vincularOfertaCandidato(String seguimientoJson, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		BaseRestDTO datos = new BaseRestDTO();
		VincularCandidatoRestDTO vincularCandidato = gson.fromJson(seguimientoJson, VincularCandidatoRestDTO.class);

		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			datos.setResult("No se encuentra el Token");
			return gson.toJson(datos);
		}
		Long idCandidato = Long.valueOf(vincularCandidato.getIdCandidato());
		Long idOfertaEmpleo = Long.valueOf(vincularCandidato.getIdOferta());
    	int status = vincularCandidato.getEstatus();
		Long idUsuario = 0L;
		boolean guardado = false;
		String mensaje = "OK";

		try {
			MovilSessionVO movilSession = new MovilSessionVO();

			// validar el cookie para obtener el candidato
			if (!cookie.equals("0")) {
				movilSession = CookieSession.decodeValue(cookie);
				idUsuario = movilSession.getIdUsuario();
			}
			
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			List<OfertaCandidatoVO> ofertasCandidato = services.findByOfferCandidate(idOfertaEmpleo, idCandidato);
			
		//SE AGREGA A LOS CANDIDATOS SELECCIONADOS, NO EXISTE RELACION, SE CREA LA RELACION OFERTA-CANDIDATO 
		//estatus => Seleccionado
		if (status == ESTATUS.SELECCIONADO.getIdOpcion() && (ofertasCandidato == null || ofertasCandidato.isEmpty())){
			OfertaCandidatoVO oferta = new OfertaCandidatoVO();
			oferta.setIdCandidato(idCandidato);
			oferta.setIdOfertaEmpleo(idOfertaEmpleo);
			oferta.setFechaAlta(Calendar.getInstance().getTime());
			oferta.setIdUsuario(idUsuario);
			oferta.setEstatus(ESTATUS.SELECCIONADO.getIdOpcion());
			//poner officina fija por el portal de empleo porque aqui no tenemos la oficina de la empresa
			oferta.setIdOficina(ConstantesGenerales.PORTAL_ID_OFICINA);
			oferta.setPostulacionCartera(0);
			oferta.setIdFuente(1L);
			
			/** se calcula la compatibilidad */
			int compatibility = (int) calculaCompatibilidad(idOfertaEmpleo, idCandidato);
			oferta.setCompatibilidad(compatibility);
			
			//para la bitacora
			if(TIPO_USUARIO.EMPRESA.getIdTipoUsuario() == movilSession.getPerfil()){
				oferta.setTipoPropietario(TIPO_PROPIETARIO.EMPRESA);
			}
			
			int result = (int) services.postulaOferta(oferta);
	    	if (result == 1) {
				guardado = true;
	    		}
			}
		
		//no se puede relacionar al candidato con la misma oferta más de una vez
		else if(status == ESTATUS.SELECCIONADO.getIdOpcion() && ofertasCandidato != null) {
			datos.setResult("El candidato ya se encuentra relacionado con la oferta seleccionada");
			return gson.toJson(datos);
		}
		
		//SE CONTACTA AL CANDIDATO, NO EXISTE RELACION, SE CREA LA RELACION OFERTA-CANDIDATO 
		//estatus => Vinculado
		else if (status == ESTATUS.VINCULADO.getIdOpcion() && (ofertasCandidato == null || ofertasCandidato.isEmpty())){
			
			OfertaCandidatoVO oferta = new OfertaCandidatoVO();
			oferta.setIdCandidato(idCandidato);
			oferta.setIdOfertaEmpleo(idOfertaEmpleo);
			oferta.setFechaAlta(Calendar.getInstance().getTime());
			oferta.setIdUsuario(idUsuario);
			oferta.setEstatus(ESTATUS.VINCULADO.getIdOpcion());
			//poner officina fija por el portal de empleo porque aqui no tenemos la oficina de la empresa
			oferta.setIdOficina(ConstantesGenerales.PORTAL_ID_OFICINA);
			oferta.setPostulacionCartera(0);
			oferta.setIdFuente(1L);
			
			/** se calcula la compatibilidad */
			int compatibility = (int) calculaCompatibilidad(idOfertaEmpleo, idCandidato);
			oferta.setCompatibilidad(compatibility);
			
			//para la bitacora
			if(TIPO_USUARIO.EMPRESA.getIdTipoUsuario() == movilSession.getPerfil()){
				oferta.setTipoPropietario(TIPO_PROPIETARIO.EMPRESA);
			}
			
			int result = (int) services.postulaOferta(oferta);
	    	if (result == 1) {
				guardado = true;
	    	}
			
	    	/* Se manda la notificacion de Vinculacion**/
			enviarCorreo(vincularCandidato);
			
	    }
		
		//SE CONTACTA AL CANDIDATO, YA EXISTE UNA RELACION OFERTA-CANDIDATO, SE ACTUALIZA EL ESTATUS
		//estatus de Seleccionado --> vinculado
		else if(status == ESTATUS.VINCULADO.getIdOpcion() && ofertasCandidato != null) {
			
			for (OfertaCandidatoVO oferta : ofertasCandidato) {
				if (oferta.getEstatus() == ESTATUS.SELECCIONADO.getIdOpcion() || oferta.getEstatus() == ESTATUS.SELECCIONADA.getIdOpcion()) {
					oferta.setEstatus(ESTATUS.VINCULADO.getIdOpcion());
					services.actualizaPostulacionOferta(oferta);
					guardado = true;
					
			    	/* Se manda la notificacion de Vinculacion**/
					enviarCorreo(vincularCandidato);
				}
			}
		}
		
		//SE INICIA EL PROCESO DE ACEPTACION
		//estatus de Postulado/Vinculado --> En_Proceso
		else if(status == ESTATUS.EN_PROCESO.getIdOpcion() && ofertasCandidato != null) {
			
			for (OfertaCandidatoVO oferta : ofertasCandidato) {
				if (oferta.getEstatus() == ESTATUS.POSTULADO.getIdOpcion() || 
						oferta.getEstatus() == ESTATUS.VINCULADO.getIdOpcion()) {
					oferta.setEstatus(ESTATUS.EN_PROCESO.getIdOpcion());
					services.actualizaPostulacionOferta(oferta);
					guardado = true;
				}
			}
		}
	    else{
			datos.setResult("Datos inválidos");
			return gson.toJson(datos);
	    	}
		
//		if(!guardado){
//			mensaje = "Error en la actualización";
//			}
//		datos.setError(mensaje);
		
		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			datos.setResult(se.getMessage());
			return gson.toJson(datos);		
		}

	return gson.toJson(datos);

	}
	
	
	private void enviarCorreo(VincularCandidatoRestDTO vincularCandidato) {
		CandidatoDelegateImpl services = CandidatoDelegateImpl.getInstance();
		String email = vincularCandidato.getCorreoElectronico();
		String nombre = vincularCandidato.getNombreEmpresa();
		String tituloOferta = vincularCandidato.getTituloOferta();
		String tipoPersona = vincularCandidato.getTipoPersona();

		try {
			services.notificarCandidatoVinculado(email, nombre, tituloOferta, tipoPersona);
		} catch (MailException e) {
			e.printStackTrace();
			logger.error(e);			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);			
		}

	}
	

	public BusquedaCandidatoDTO detalleCandidato(long idOferta, long idCandidato) {

		BusquedaCandidatoDTO candidatoDTO = new BusquedaCandidatoDTO();
		OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();

		InformacionGeneralVO infoVO = null;
		CandidatoForm candidatoForm = new CandidatoForm();
		List<OfertaCandidatoVO> ofertasCandidato = new ArrayList<OfertaCandidatoVO>(); 

		EmpresaEspacioDelegate candidateServices = EmpresaEspacioDelegateImpl.getInstance();
		CandidatoDelegateImpl candidatoServices = CandidatoDelegateImpl.getInstance();

		try {
			if (idOferta>0){
				ofertasCandidato = services.findByOfferCandidate(idOferta, idCandidato);				

			if (ofertasCandidato.size() == 0) {
				int compatibility = (int) calculaCompatibilidad(idOferta, idCandidato);
				candidatoDTO.setCompatibilidad(compatibility);
				}
			else{
				candidatoDTO.setCompatibilidad(ofertasCandidato.get(0).getCompatibilidad());
			}
			
			if(candidatoDTO.getCompatibilidad()<50){
				int compatibility = (int) calculaCompatibilidad(idOferta, idCandidato);
				candidatoDTO.setCompatibilidad(compatibility);
				}
			}
			
			infoVO = candidateServices.showCandidateDetail(idCandidato);
			candidatoForm.setInformacionGeneralVO(infoVO);

			ConocimientoComputacionVO conocimientosComputacion = candidatoServices
					.findConocimientosComputacion(idCandidato);
			candidatoForm.setConocimientoComputacion(conocimientosComputacion);
			candidatoDTO.setCandidato(candidatoForm);
			candidatoDTO.setIdOferta(String.valueOf(idOferta));

		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return candidatoDTO;

	}


	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private long calculaCompatibilidad(long idOfertaEmpleo, long idCandidato) {
		long compatibility = 0;

		try {
			EmpresaEspacioDelegate services = EmpresaEspacioDelegateImpl.getInstance();
			compatibility = services.match(idOfertaEmpleo, idCandidato);
		} catch (Exception e) {
			logger.error(e);
		}

		return compatibility;
	}

}
