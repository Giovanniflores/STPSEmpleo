package mx.gob.stps.portal.movil.app.service;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
@Stateless
@Path("/cambioEstatusNotificacion")


public class CambioStatusNotificacion {

	private Gson gson = new Gson();

	private static Logger logger = Logger.getLogger(NotificacionCandidato.class);
	
	
	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{status}")
	public String postulateOfertaCandidato(
			@DefaultValue("-1") @PathParam("status") final long status,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) throws BusinessException {

		// then you can access the request/response/session etc in your methods
		
		Long idCandidato = 0L;
		boolean guardado = false;
		Long statusNotificacion = status;
		
		
		String cookie = request.getHeader(CookieSession.NAME);
		try{
			MovilSessionVO movilSession = new MovilSessionVO();
			if (!cookie.equals("0")) {
				movilSession = CookieSession.decodeValue(cookie);
				idCandidato = movilSession.getIdCandidato();
				
			}
			} catch (Exception se) {
				se.printStackTrace();
				logger.error(se);
			
				
			}
			
			
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			long find =0l;
			mx.gob.stps.portal.persistencia.entity.NotificacionCandidato candidato= new mx.gob.stps.portal.persistencia.entity.NotificacionCandidato();
			
			candidato.setIdCandidato(idCandidato);
			candidato.setEstatusNotificacion(statusNotificacion);
			
			try {
				//System.out.println("Entro al try ");
				services.updateEstatusNotificacion(candidato);
				
			} catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return gson.toJson(idCandidato);

	}
	
}