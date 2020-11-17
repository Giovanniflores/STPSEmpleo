package mx.gob.stps.portal.movil.app.candidato.service;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.ws.rs.core.Context;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieUtils;
import mx.gob.stps.portal.movil.app.glasses.utilities.SessionUtility;
import mx.gob.stps.portal.movil.app.glasses.utilities.SystemParameterUtility;
import mx.gob.stps.portal.movil.app.model.Session;


import mx.gob.stps.portal.movil.app.model.base.PaginadorDTO;
import mx.gob.stps.portal.movil.app.model.rest.ConfidencialidadDatosRestDTO;

import mx.gob.stps.portal.movil.app.model.rest.UsuarioRestDTO;
import mx.gob.stps.portal.movil.app.service.util.OfertaUtil;
import mx.gob.stps.portal.movil.app.session.SessionService;
import mx.gob.stps.portal.movil.web.candidato.delegate.CandidatoDelegateImpl;
import mx.gob.stps.portal.movil.web.candidato.util.UtilPostulate;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.utils.MessageLoader;
import mx.gob.stps.portal.movil.web.oferta.action.PagerOffersAction;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.movil.web.oferta.form.OfertaJB;
import mx.gob.stps.portal.persistencia.vo.CandidatoVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;
import mx.gob.stps.portal.utils.Utils;

@Stateless
@Path("/confidentialPersonalDates")
public class ConfidentialidadDatos {

	private Gson gson = new Gson();

	private static Logger logger = Logger.getLogger(ConfidentialidadDatos.class);
	private CandidatoDelegateImpl services = CandidatoDelegateImpl.getInstance();

	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	public String postulateOfertaCandidato(
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		Long idCandidato = 0L;
		Integer location = null;
		ConfidencialidadDatosRestDTO datos = new ConfidencialidadDatosRestDTO();
		String query = null;
		String cookie = request.getHeader(CookieSession.NAME);
		if(cookie == null){
			datos.setResult("No tiene datos del Candidato. Inicio Session.");
			return gson.toJson(datos);
		}
		if (null == query)
			query = "";
		logger.info(query);
		logger.info("place " + location);
		
		try {
			// validar el cookie para obtener el candidato
			if (!cookie.equals("0")) {
				MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
				idCandidato = movilSession.getIdCandidato();
			}

			if(idCandidato != 0){
				CandidatoVo candidatoVo = services.findCandidatoVO(idCandidato);
				datos.setDatosprivado(Utils.getBooleanConfidencialidad(candidatoVo.getConfidencialidadDatos()));
			}
			else
				{
					datos.setResult("Usuario incorrecto");
				}
			
			
		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			datos.setResult(se.getMessage());
			
		}
		
		// Todo agrega los detalles por los ofertas en el rango de la pagina

		return gson.toJson(datos);

	}
	
	
	@POST
	// Actualizar el estatus de oferta_candidato
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String cambioOfertaCandidato(
			String confidencialidadJson,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		MessageLoader messages = MessageLoader.getInstance();
		UsuarioRestDTO usuarioRest = new UsuarioRestDTO();
		Long idCandidato = 0L;
		
		ConfidencialidadDatosRestDTO datos = new ConfidencialidadDatosRestDTO();
		
		String mensaje = "OK";
		Integer location = null;
		String entity = "";
		
		String query = null;
		String cookie = request.getHeader(CookieSession.NAME);
		if(cookie == null){
			datos.setResult("No tiene datos del Candidato. Inicio Session.");
			return gson.toJson(datos);
		}
		if (null == query)
			query = "";
		logger.info(query);
		logger.info("place " + location);
		try {
			datos = gson.fromJson(confidencialidadJson, ConfidencialidadDatosRestDTO.class);
			
		} catch (JsonSyntaxException ex) {
			datos.setResult("Sintaxis Json invalido");
			return gson.toJson(datos);
		}
		
		CandidatoVo candidatoVo;
		try {
			if (!cookie.equals("0")) {
				MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
				idCandidato = movilSession.getIdCandidato();
			}
			candidatoVo = services.findCandidatoVO(idCandidato);
			candidatoVo.setConfidencialidadDatos(Utils.getNumeroConfidencialidad(datos.isDatosprivado()));
			
			services.actualizarRegistroCandidato(candidatoVo);
		} catch (ServiceLocatorException e) {
			datos.setResult("Servicio no encontrado");
			return gson.toJson(datos);
		}
		
		
		// Todo agrega los detalles por los ofertas en el rango de la pagina

		return gson.toJson(datos);

	}

}
