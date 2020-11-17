package mx.gob.stps.portal.movil.app.service;

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

import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.movil.app.model.rest.RecuperacionContrasenaDTO;
import mx.gob.stps.portal.movil.app.service.RecuperarContrasena.Role;
import mx.gob.stps.portal.movil.web.candidato.delegate.CandidatoDelegateImpl;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;



@Stateless
@Path("/sendMail")
public class EnviarNotificacionClave {
	private	CandidatoDelegateImpl services = CandidatoDelegateImpl.getInstance();
	private EmpresaEspacioDelegate servicesEmp = EmpresaEspacioDelegateImpl.getInstance();
	private Gson gson = new Gson();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String enviarNotificacionPorCorreo(String captchaJson,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		
		RecuperacionContrasenaDTO captcha = (RecuperacionContrasenaDTO) gson.fromJson(captchaJson,RecuperacionContrasenaDTO.class);
		String respuesta;
		try {
			respuesta =Password.codificaPassword(captcha.getRespuesta());
		} catch (EncodingException e) {
			
			captcha = new RecuperacionContrasenaDTO();
			captcha.setResult(e.getMessage());
			return gson.toJson(captcha);
		} 
		for(String encoded : captcha.getRespuestas()){
			if(respuesta.equals(encoded)){
				
				//podemos hacer el cambio de la clave private	
				
					try {
						if (Role.CANDIDATE.getValue().equals(captcha.getTipo())) {
							services.notificacionRecuperacionPswMovilCandidato(captcha.getCorreo(), Long.valueOf(captcha.getId()), captcha.getClave());
						}
						else
						if(Role.COMPANY.getValue().equals(captcha.getTipo())){
							servicesEmp.notificacionRecuperacionPswMovilEmpresa(Long.valueOf(captcha.getId()),captcha.getNombre(), captcha.getCorreo(), captcha.getClave(), captcha.getUsuario() );
						}
						
					} catch (MailException e) {
						
						e.printStackTrace();
					return gson.toJson(new RecuperacionContrasenaDTO(e.getMessage()));
					} catch (ServiceLocatorException e) {
						
						e.printStackTrace();
						gson.toJson(new RecuperacionContrasenaDTO(e.getMessage()));
					}
					
								
				
				
				
				return gson.toJson(captcha);
			}
		}
		
		return gson.toJson(new RecuperacionContrasenaDTO("respuesta Incorrecta"));
	}

}
