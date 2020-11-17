package mx.gob.stps.portal.movil.app.candidato.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.core.seguridad.vo.ConfirmacionVO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.model.base.AuthenticationPostDTO;
import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;
import mx.gob.stps.portal.movil.app.model.base.PaginadorDTO;
import mx.gob.stps.portal.movil.app.model.base.PostulatedDTO;
import mx.gob.stps.portal.movil.app.model.rest.BusquedaOfertaRestDTO;
import mx.gob.stps.portal.movil.app.model.rest.RecuperacionContrasenaDTO;
import mx.gob.stps.portal.movil.app.service.util.OfertaCandidatoComparadorFecha;
import mx.gob.stps.portal.movil.app.service.util.OfertaUtil;
import mx.gob.stps.portal.movil.app.service.util.OfertasPostuladoComparador;
import mx.gob.stps.portal.movil.app.service.util.OfertasRecomendadasComparador;
import mx.gob.stps.portal.movil.web.candidato.delegate.CandidatoDelegateImpl;
import mx.gob.stps.portal.movil.web.candidato.util.UtilPostulate;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

@Stateless
@Path("/cvEnviado")
public class EmpleosCVEnviado {
	private CandidatoDelegateImpl services = CandidatoDelegateImpl
			.getInstance();
	private Gson gson = new Gson();

	private BusquedaOfertaRestDTO ofertaRecomendada = new BusquedaOfertaRestDTO();
	private OfertaDelegateImpl ofertasServices = OfertaDelegateImpl
			.getInstance();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getEmpleosCVEnviado(String paginadorJson,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		PaginadorDTO paginador = new PaginadorDTO();
		try {
			paginador = gson.fromJson(paginadorJson, PaginadorDTO.class);
		} catch (JsonSyntaxException ex) {
			ofertaRecomendada.setResult("Sintaxis Json invalido");
			return gson.toJson(ofertaRecomendada);
		}

		Long idCandidato = 0L;
		ofertaRecomendada.setResult("OK");
		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			ofertaRecomendada.setResult("No se encuentra el Token.");
			return gson.toJson(ofertaRecomendada);
		}
		MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
		idCandidato = movilSession.getIdCandidato();
		misOfertasPostulado(idCandidato, paginador);

		return gson.toJson(ofertaRecomendada);

	}

	private void misOfertasPostulado(Long idCandidato, PaginadorDTO paginador) {
		List<OfertaEmpleoJB> misOfertas = new ArrayList<OfertaEmpleoJB>();
		try {
			int diasDifferencia = 7;
			misOfertas = ofertasServices.misOfertasEmpleo(idCandidato,
					OfertaUtil.getEstatusPostulados(), diasDifferencia);
			
			misOfertas = ordenarMisOfertasPostulados(misOfertas,idCandidato);
			
			ofertaRecomendada.setTamano(misOfertas.size());
				if (paginador.getDetalle()) {
				ofertaRecomendada.setOfertas(limitarBusquedaOferta(
						paginador.getPagina(),
						paginador.getResultadoPorPagina(), misOfertas,
						idCandidato));
			} else {
				BusquedaOfertaRestDTO ofertaRecomendadaSinDetail = new BusquedaOfertaRestDTO();
				for (int i = 0; i < misOfertas.size(); i++) {
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
				ofertaRecomendada = ofertaRecomendadaSinDetail;
				ofertaRecomendada.setTamano(misOfertas.size());
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<OfertaEmpleoJB> ordenarMisOfertasPostulados(List<OfertaEmpleoJB> misOfertas, Long idCandidato) {
		
		List<PostulatedDTO> ofertas = new ArrayList<PostulatedDTO>();
		for(OfertaEmpleoJB miOferta : misOfertas){
			PostulatedDTO postulated = new PostulatedDTO(miOferta,idCandidato,OfertaUtil.calculaCompatibilidad(Long.valueOf(miOferta.getIdOfertaEmpleo()),idCandidato));
			ofertas.add(postulated);
		}
		
		Collections.sort(ofertas, new OfertasPostuladoComparador());
		List<OfertaEmpleoJB> ordenOferta = new ArrayList<OfertaEmpleoJB>();
		for(PostulatedDTO postulated : ofertas){
			ordenOferta.add(postulated.getOferta());
		}
		return ordenOferta;
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
				
				oferta.getOferta().setFechaFin(OfertaUtil.changeDate(oferta.getOferta().getFechaFin(), "E MMM dd HH:mm:ss z yyyy", "dd 'de' MMMM 'de' yyyy"));
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
