package mx.gob.stps.portal.movil.app.service;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.exception.BusinessException;
import mx.gob.stps.portal.movil.app.model.rest.RecuperacionContrasenaDTO;
import mx.gob.stps.portal.movil.app.service.util.CaptchaPreguntasWSService;
import mx.gob.stps.portal.persistencia.vo.CaptchaPreguntasVO;
import mx.gob.stps.portal.utils.Utils;



@Stateless
@Path("/validateCaptcha")
public class ValidateCaptcha {

	private Gson gson = new Gson();


	private static Logger logger = Logger.getLogger(ValidateCaptcha.class);


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCaptcha(
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		
		RecuperacionContrasenaDTO captchaRest = new RecuperacionContrasenaDTO();
		CaptchaPreguntasWSService captcha = new CaptchaPreguntasWSService();
		try {
			
			CaptchaPreguntasVO captchapregunta = captcha
					.obtieneDesafioCaptcha();
			
			captchaRest = new RecuperacionContrasenaDTO(captchapregunta);
			
			return gson.toJson(captchaRest);

			// return gson.toJson(captchapregunta);
		} catch (BusinessException e) {
			logger.error(e);
			captchaRest.setResult("no hay servicio de captcha");
			return gson.toJson(captchaRest);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String revisarRespuesta(String captchaJson,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		
		RecuperacionContrasenaDTO captcha = (RecuperacionContrasenaDTO) gson.fromJson(captchaJson,RecuperacionContrasenaDTO.class);
		String respuesta;
		try {
			
			respuesta =Password.codificaPassword(Utils.limpiaRespuestaCaptcha(captcha.getRespuesta()));
		} catch (EncodingException e) {
			
			captcha = new RecuperacionContrasenaDTO();
			captcha.setResult(e.getMessage());
			return gson.toJson(captcha);
		} 
		for(String encoded : captcha.getRespuestas()){
			if(respuesta.equals(encoded)){
				captcha = new RecuperacionContrasenaDTO();
				captcha.setResult("respuesta correcta");
				return gson.toJson(captcha);
			}
		}
		
		captcha = new RecuperacionContrasenaDTO();
		captcha.setResult("respuesta incorrecta");
		return gson.toJson(captcha);

	}
	

}
