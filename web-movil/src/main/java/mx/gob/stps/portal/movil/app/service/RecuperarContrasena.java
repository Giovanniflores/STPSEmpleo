package mx.gob.stps.portal.movil.app.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.seguridad.vo.ConfirmacionVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.exception.BusinessException;
import mx.gob.stps.portal.movil.app.model.rest.RecuperacionContrasenaDTO;
import mx.gob.stps.portal.movil.app.service.util.CaptchaPreguntasWSService;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.seguridad.delegate.SeguridadMovilDelegateImpl;
import mx.gob.stps.portal.persistencia.vo.CaptchaPreguntasVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Utils;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

@Stateless
@Path("/recuperarContraseno")
public class RecuperarContrasena {

	private Gson gson = new Gson();

	private static Logger logger = Logger.getLogger(RecuperarContrasena.class);

	private SeguridadMovilDelegateImpl services = SeguridadMovilDelegateImpl
			.getInstance();
	private EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCaptcha(@Context HttpServletRequest request,
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

		RecuperacionContrasenaDTO captcha = (RecuperacionContrasenaDTO) gson
				.fromJson(captchaJson, RecuperacionContrasenaDTO.class);
		String respuesta;

		try {
			respuesta = Password.codificaPassword(Utils
					.limpiaRespuestaCaptcha(captcha.getRespuesta()));
		} catch (EncodingException e) {

			captcha = new RecuperacionContrasenaDTO();
			captcha.setResult(e.getMessage());
			return gson.toJson(captcha);
		}
		for (String encoded : captcha.getRespuestas()) {
			if (respuesta.equals(encoded)) {

				// podemos hacer el cambio de la clave private
				try {
					ConfirmacionVO confirmacion = null;
					String usuario = captcha.getUsuario();
					UsuarioVO usuarioVO = services.consultaUsuarioPorLogin(usuario);
					if (usuarioVO==null){
						captcha = new RecuperacionContrasenaDTO();
    					captcha.setResult("Usuario no localizado mediante el nombre "+ usuario);
    					return gson.toJson(captcha);
					}
					int estatusUsuario = usuarioVO.getEstatus();
					long idUsuario = usuarioVO.getIdUsuario();
					
					if (Role.CANDIDATE.getValue().equals(captcha.getTipo())) {
						
						if ((estatusUsuario == ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion()) ||
								(estatusUsuario == ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion())) {
							confirmacion = services.confirmacionReactivacionCandidato(captcha.getUsuario(), captcha.getCurp());
						}else{
							confirmacion = services.confirmacionDirectaCandidato(captcha.getUsuario(), captcha.getCurp());
						}
						
						captcha = new RecuperacionContrasenaDTO();
						captcha.setClave(confirmacion.getContrasena());
						captcha.setNombre(confirmacion.getNombre());
						captcha.setId(String.valueOf(confirmacion.getIdCandidato()));
						captcha.setCorreo(confirmacion.getCorreoElectronico());
						captcha.setResult("OK");
					}
					else if(Role.COMPANY.getValue().equals(captcha.getTipo())){
										
						Long idTipoEmpresa = captcha.getTipoEmpresa()!=null ? Long.parseLong(captcha.getTipoEmpresa()) : null;
	
						EmpresaVO empresa = service.findByIdUsuario(Long.valueOf(idUsuario));
						if (empresa==null){
							captcha = new RecuperacionContrasenaDTO();
        					captcha.setResult("Empresa no localizada mediante el nombre de usuario "+ usuario);
        					return gson.toJson(captcha);
						}
						if (empresa.getEstatus() != ESTATUS.ACTIVO.getIdOpcion()){
							captcha = new RecuperacionContrasenaDTO();
        					captcha.setResult("Empresa no activa mediante el nombre de usuario "+ usuario);
        					return gson.toJson(captcha);
						}
						if(null==idTipoEmpresa || (empresa.getIdTipoEmpresa() != idTipoEmpresa)){
							captcha = new RecuperacionContrasenaDTO();
        					captcha.setResult("El tipo de Empresa no corresponde a la empresa con nombre de usuario "+ usuario);
        					return gson.toJson(captcha);
						}
						
						if(idTipoEmpresa.equals(Catalogos.TIPO_EMPRESA.ONG.getTipoEmpresa()) 
								|| idTipoEmpresa.equals(Catalogos.TIPO_EMPRESA.PUBLICA.getTipoEmpresa())){
							
							String razonSocial = captcha.getNombreEmpresa();
                                if(null==razonSocial){
                                	captcha = new RecuperacionContrasenaDTO();
                					captcha.setResult("El nombre de empresa proporcionado no es válido.");
                					return gson.toJson(captcha);
							} 
                                else{
                                	confirmacion = services.confirmacionDirectaEmpresa(captcha.getUsuario(), razonSocial);
                                }
						} else{
							DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
							DateFormat formatter2 = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
							Date date = (Date) formatter.parse(captcha.getDate());
    						Date fecha = Utils.dateEscritoToDate(formatter2.format(date));
							confirmacion = services.confirmacionDirectaEmpresa(captcha.getUsuario(), fecha);
						}	
								
						captcha = new RecuperacionContrasenaDTO();
						captcha.setClave(confirmacion.getContrasena());
						captcha.setNombre(confirmacion.getNombre());
						captcha.setCorreo(confirmacion.getCorreoElectronico());
						captcha.setResult("OK");
						captcha.setId(String.valueOf(confirmacion.getIdEmpresa()));
					}
				} catch (mx.gob.stps.portal.core.infra.exception.BusinessException e) {
					captcha = new RecuperacionContrasenaDTO();
					captcha.setResult(e.getMessage());
					return gson.toJson(captcha);
				} catch (TechnicalException e) {
					captcha = new RecuperacionContrasenaDTO();
					captcha.setResult(e.getMessage());
					return gson.toJson(captcha);
				} catch (ServiceLocatorException e) {
					captcha = new RecuperacionContrasenaDTO();
					captcha.setResult(e.getMessage());
					return gson.toJson(captcha);
				} catch (ParseException e) {
					captcha = new RecuperacionContrasenaDTO();
					captcha.setResult(e.getMessage());
					return gson.toJson(captcha);
				}

				return gson.toJson(captcha);
			}
		}

		return "respuesta incorrecta";
	}

	public enum Role {
		ANONYMOUS("ANONYMOUS"), CANDIDATE("Candidato"), // Value in spanish (and
														// case sensitive) due
														// to that's the way the
														// server side handles
														// it
		COMPANY("Empresa"); // Value in spanish (and case sensitive) due to
							// that's the way the server side handles it

		private String value = "ANONYMOUS"; // Default

		private Role(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public static Role fromValue(String roleStr)
				throws IllegalArgumentException {
			for (Role role : Role.values()) {
				if (roleStr.equals(role.value)) {
					return role;
				}
			}
			throw new IllegalArgumentException("Invalid Role");
		}
	}

}
