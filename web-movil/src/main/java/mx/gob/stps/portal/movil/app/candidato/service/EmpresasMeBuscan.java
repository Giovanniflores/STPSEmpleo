package mx.gob.stps.portal.movil.app.candidato.service;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
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

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.model.base.AuthenticationPostDTO;
import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;
import mx.gob.stps.portal.movil.app.model.base.PaginadorDTO;
import mx.gob.stps.portal.movil.app.model.rest.BusquedaOfertaRestDTO;
import mx.gob.stps.portal.movil.app.model.rest.PostulateRestDTO;
import mx.gob.stps.portal.movil.app.model.rest.PostulatedRestDTO;
import mx.gob.stps.portal.movil.app.model.rest.UsuarioRestDTO;
import mx.gob.stps.portal.movil.app.service.util.OfertaUtil;
import mx.gob.stps.portal.movil.web.candidato.util.UtilPostulate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.utils.MessageLoader;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.movil.web.oferta.form.OfertaJB;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Stateless
@Path("/empresasMeBuscan")
public class EmpresasMeBuscan {

	private Gson gson = new Gson();
	private static Logger logger = Logger.getLogger(EmpresasMeBuscan.class);
	private BusquedaOfertaRestDTO empresasMeBuscan = new BusquedaOfertaRestDTO();
	private OfertaDelegateImpl ofertasServices = OfertaDelegateImpl
			.getInstance();

	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{pagina}/{resultadoPorPagina}")
	public String empresasMeBuscan(@DefaultValue("0") @PathParam("pagina") final String paginaStr,
			@DefaultValue("10") @PathParam("resultadoPorPagina") final String resultadoPorPaginaStr,
			@Context HttpServletRequest request, @Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		Long idCandidato = 0L;
		empresasMeBuscan.setResult("OK");
		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			empresasMeBuscan.setResult("No se encuentra el Token.");
			return gson.toJson(empresasMeBuscan);
		}
		List<OfertaEmpleoJB> empresasOfertas = new ArrayList<OfertaEmpleoJB>();
		List<BusquedaOfertaDTO> empresasDetaile = new ArrayList<BusquedaOfertaDTO>();

		try {
			// validar el cookie para obtener el candidato
			MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
			idCandidato = movilSession.getIdCandidato();

			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			empresasOfertas = services.empresasMeBuscanOfertas(idCandidato);

			// limitar los resultados
			quitarLosNoActivos(empresasOfertas);

			empresasMeBuscan.setTamano(empresasOfertas.size());
			empresasDetaile = limitarOferta(paginaStr, resultadoPorPaginaStr, empresasOfertas, idCandidato);

		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			empresasMeBuscan.setResult(se.getMessage());
		}

		empresasMeBuscan.setOfertas(empresasDetaile);

		return gson.toJson(empresasMeBuscan);
	}

	private void quitarLosNoActivos(List<OfertaEmpleoJB> empresasOfertas) {

		for (int i = empresasOfertas.size() - 1; i >= 0; i--) {
			System.out.println("test oferta cual oferta = " + empresasOfertas.get(i).getIdOfertaEmpleo());
			BusquedaOfertaDTO test = busquedaDetalle(Long.valueOf(empresasOfertas.get(i).getIdOfertaEmpleo()));
			if (test.getOferta().getOferta().getEstatus() != Constantes.ESTATUS.ACTIVO.getIdOpcion()) {
				empresasOfertas.remove(i);
			}

		}

	}

	private void quitarOfertasConMasDeSieteDias(List<OfertaEmpleoJB> empresasOfertas) {

		for (int i = empresasOfertas.size() - 1; i >= 0; i--) {
			BusquedaOfertaDTO test = busquedaDetalle(Long.valueOf(empresasOfertas.get(i).getIdOfertaEmpleo()));
			
			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
			// Mon May 07 00:00:00 CDT 2012

			Date fechaOferta = new Date();
			try {
				fechaOferta = (Date) formatter.parse(test.getOferta().getFechaAlta());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			formatter2.format(fechaOferta);
			
			Calendar c1 = Calendar.getInstance();

			final long milisegundos_por_dia = 86400000; // milisegundos por dia
			long d = (fechaOferta.getTime() - c1.getTimeInMillis()) / milisegundos_por_dia + 1;

			if (d > 7) {
				empresasOfertas.remove(i);
			}
		}

	}

	private List<BusquedaOfertaDTO> limitarOferta(String paginaStr, String resultadoPorPaginaStr,
			List<OfertaEmpleoJB> empresasOfertas, Long idCandidato) {
		List<BusquedaOfertaDTO> lista = new ArrayList<BusquedaOfertaDTO>();
		// obtener cuanto resultados debemos regresar
		int resultados = Integer.valueOf(resultadoPorPaginaStr);
		// desde cual resultado regresar
		int offset = Integer.valueOf(paginaStr) * resultados;

		for (int i = 0; i < resultados && (i + offset) < empresasOfertas.size(); i++) {
			Long idOferta = Long.valueOf(empresasOfertas.get(i + offset).getIdOfertaEmpleo());
			Long idOfertaCandidato = Long.valueOf(empresasOfertas.get(i + offset).getIdOfertaCandidato());
			BusquedaOfertaDTO test = busquedaDetalle(idOferta);
			test.getOferta().setTelefonos(new ArrayList<TelefonoVO>());

			int compatibility = (int) OfertaUtil.calculaCompatibilidad(idOferta, idCandidato);
			test.setCompatibilidad(compatibility);
			test.setIdPostulated(String.valueOf(idOfertaCandidato));

			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
			// Mon May 07 00:00:00 CDT 2012

			Date date = new Date();
			try {
				date = (Date) formatter.parse(test.getOferta().getFechaAlta());
				test.getOferta().setFechaFin(OfertaUtil.changeDate(test.getOferta().getFechaFin(), "E MMM dd HH:mm:ss z yyyy", "dd 'de' MMMM 'de' yyyy"));
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getEmpresasMeBuscan(String paginadorJson,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		PaginadorDTO paginador = new PaginadorDTO();
		try {
			paginador = gson.fromJson(paginadorJson, PaginadorDTO.class);
		} catch (JsonSyntaxException ex) {
			empresasMeBuscan.setResult("Sintaxis Json invalido");
			return gson.toJson(empresasMeBuscan);
		}

		Long idCandidato = 0L;
		empresasMeBuscan.setResult("OK");
		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			empresasMeBuscan.setResult("No se encuentra el Token.");
			return gson.toJson(empresasMeBuscan);
		}
		MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
		idCandidato = movilSession.getIdCandidato();
		misOfertasEmpresas(idCandidato, paginador);

		return gson.toJson(empresasMeBuscan);

	}

	private void misOfertasEmpresas(Long idCandidato, PaginadorDTO paginador) {
		List<OfertaEmpleoJB> misOfertas = new ArrayList<OfertaEmpleoJB>();
		try {
			int diasDifferencia = 7;
			misOfertas = ofertasServices.empresasMeBuscanOfertas(idCandidato,
					OfertaUtil.getEstatusEmpresasMeBuscan(), diasDifferencia);
			empresasMeBuscan.setTamano(misOfertas.size());
			if (paginador.getDetalle()) {
				empresasMeBuscan.setOfertas(limitarBusquedaOferta(
						paginador.getPagina(),
						paginador.getResultadoPorPagina(), misOfertas,
						idCandidato));
			} else {
				BusquedaOfertaRestDTO ofertaRecomendadaSinDetail = new BusquedaOfertaRestDTO();
				for (int i = 0 +(paginador.getPagina()*paginador.getResultadoPorPagina());i <((paginador.getPagina()+1) * paginador.getResultadoPorPagina()) && i < misOfertas.size(); i++) {
					boolean posibleNotificacion = false;
					BusquedaOfertaDTO oferta = new BusquedaOfertaDTO();
					Long idOferta = Long.valueOf(misOfertas.get(i)
							.getIdOfertaEmpleo());
					Long idPostulated = Long.valueOf(misOfertas.get(i)
							.getIdOfertaCandidato());
					if (misOfertas.get(i).getDifferenciaFecha() == 1) {
						posibleNotificacion = true;
					}
					String estatusPostulated = misOfertas.get(i)
							.getEstatusOfertaCandidato();
					oferta.setIdPostulated(String.valueOf(idPostulated));
					oferta.getOferta()
							.setTelefonos(new ArrayList<TelefonoVO>());
					oferta.setEnTiempo(posibleNotificacion);

					DateFormat formatter = new SimpleDateFormat(
							"E MMM dd HH:mm:ss z yyyy", Locale.US);
					DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
					DateFormat formatter3 = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
					// Mon May 07 00:00:00 CDT 2012

					Date dateModificacion = new Date();
					try {

						String fecha = misOfertas
								.get(i).getFechaModificacion();
						if(fecha != null && !fecha.isEmpty()){
							dateModificacion = (Date) formatter3.parse(fecha);
						}
						oferta.setFechaModificacion(formatter2
								.format(dateModificacion));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}

					ofertaRecomendadaSinDetail.getOfertas().add(oferta);

				}
				empresasMeBuscan = ofertaRecomendadaSinDetail;
				empresasMeBuscan.setTamano(misOfertas.size());
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<BusquedaOfertaDTO> limitarBusquedaOferta(int paginaStr,
			int resultadoPorPaginaStr, List<OfertaEmpleoJB> ofertas,
			Long idCandidato) {
		List<BusquedaOfertaDTO> lista = new ArrayList<BusquedaOfertaDTO>();
		// obtener cuanto resultados debemos regresar
		int resultados = resultadoPorPaginaStr;
		// desde cual resultado regresar
		int offset = paginaStr * resultados;

		for (int i = 0; i < resultados && (i + offset) < ofertas.size(); i++) {
			Long idOferta = Long.valueOf(ofertas.get(i + offset)
					.getIdOfertaEmpleo());
			Long idPostulated = Long.valueOf(ofertas.get(i + offset)
					.getIdOfertaCandidato());
			boolean posibleNotificacion = false;
			if (ofertas.get(i + offset).getDifferenciaFecha() == 1) {
				posibleNotificacion = true;
			}
			String estatusPostulated = ofertas.get(i + offset)
					.getEstatusOfertaCandidato();
			BusquedaOfertaDTO oferta = OfertaUtil.busquedaDetalle(idOferta);
			oferta.getOferta().setTelefonos(new ArrayList<TelefonoVO>());
			if (idCandidato > 0L) {
				int compatibility = (int) OfertaUtil.calculaCompatibilidad(
						idOferta, idCandidato);
				oferta.setCompatibilidad(compatibility);
				oferta.setPostulated(String.valueOf(ESTATUS
						.getInt(estatusPostulated)));
				oferta.setListPostulated(UtilPostulate.getPostulated(idOferta,
						idCandidato));
				oferta.setIdPostulated(String.valueOf(idPostulated));
				oferta.setEnTiempo(posibleNotificacion);

			}

			DateFormat formatter = new SimpleDateFormat(
					"E MMM dd HH:mm:ss z yyyy", Locale.US);
			DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat formatter3 = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
			// Mon May 07 00:00:00 CDT 2012

			Date date = new Date();
			Date dateModificacion = new Date();
			try {
				date = (Date) formatter
						.parse(oferta.getOferta().getFechaAlta());
				String fecha = ofertas.get(i + offset).getFechaModificacion();
				if (fecha != null && !fecha.isEmpty()) {
					dateModificacion = (Date) formatter3.parse(fecha);
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


}
