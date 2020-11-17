package mx.gob.stps.portal.movil.app.service;


import java.sql.SQLException;



import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import javax.ws.rs.core.Context;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.model.rest.BusquedaCVDTO;
import mx.gob.stps.portal.movil.web.candidato.delegate.CandidatoDelegateImpl;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.oferta.form.PerfilJB;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;
import mx.gob.stps.portal.utils.Utils;

//import mx.gob.stps.portal.core.search.ResultInfoBO;
//import java.util.ArrayList;
//import java.util.List;
//import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;
//import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
//import mx.gob.stps.portal.movil.web.oferta.form.OfertaJB;
//import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
//import org.apache.commons.beanutils.BeanUtils;
//import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;

@Stateless
@Path("/busquedaCV")
public class BusquedaCV {

	private Gson gson = new Gson();

	private static Logger logger = Logger.getLogger(BusquedaCV.class);


	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	
	public String busquedaOferta(
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		BusquedaCVDTO busquedaCV = new BusquedaCVDTO();
		Long idCandidato = 0L;
		// Get data from cookie 
		String cookie = request.getHeader(CookieSession.NAME);
		if(cookie == null){
			busquedaCV.setResult("No tiene datos del Candidato. Inicio Session.");
			return gson.toJson(busquedaCV);
		}
		
		MovilSessionVO vo = CookieSession.decodeValue(cookie);
		
		
		idCandidato = vo.getIdCandidato();
		// then you can access the request/response/session etc in your methods
		CandidatoDelegateImpl candidatoServices = CandidatoDelegateImpl.getInstance();
		
		PerfilJB perfilLaboral = new PerfilJB();
		try {
			perfilLaboral = candidatoServices.loadPerfil( vo.getIdCandidato());
			if (perfilLaboral!=null) {
				perfilLaboral.setAniosExperiencia(Constantes.EXPERIENCIA.getDescripcion((int) perfilLaboral.getPerfilLaboral().getIdExperienciaTotal()));
				
			}
			InformacionGeneralVO info = candidatoServices.telefonoCandidato(idCandidato);
			busquedaCV.setInfo(info);
			
			ConocimientoComputacionVO conocimientosComputacion = candidatoServices.findConocimientosComputacion(idCandidato);
			busquedaCV.setConocimientoComputacion(conocimientosComputacion);
		
		} catch (NumberFormatException e) {
			logger.error(e);
			busquedaCV.setResult(e.getMessage());
			return gson.toJson(busquedaCV);
		} catch (BusinessException e) {
			logger.error(e);
			busquedaCV.setResult(e.getMessage());
			return gson.toJson(busquedaCV);
		} catch (ServiceLocatorException e) {
			logger.error(e);
			busquedaCV.setResult(e.getMessage());
			return gson.toJson(busquedaCV);
		} catch (SQLException e) {
			logger.error(e);
			busquedaCV.setResult(e.getMessage());
			return gson.toJson(busquedaCV);
		}
		busquedaCV.setSalario(Utils.formatMoney(perfilLaboral.getExpectativaPrincipal().getSalarioPretendido()));
		busquedaCV.setPerfillaboral(perfilLaboral);
		
		
		return gson.toJson(busquedaCV);

	}
}
