package mx.gob.stps.portal.movil.app.empresa.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;

import mx.gob.stps.portal.movil.app.empresa.model.rest.EmpresaRestDTO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.glasses.utilities.SessionUtility;
import mx.gob.stps.portal.movil.app.glasses.utilities.SystemParameterUtility;
import mx.gob.stps.portal.movil.app.model.Session;
import mx.gob.stps.portal.movil.app.model.base.AuthenticationPostDTO;

import mx.gob.stps.portal.movil.app.model.rest.UsuarioRestDTO;

import mx.gob.stps.portal.movil.app.session.SessionService;
import mx.gob.stps.portal.movil.util.UtilsUsuario;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.utils.MessageLoader;
import mx.gob.stps.portal.movil.web.seguridad.delegate.SeguridadMovilDelegateImpl;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;
import mx.gob.stps.portal.utils.Catalogos.TIPO_USUARIO;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Stateless
@Path("/loginEmpresa")
public class LoginEmpresa {

	private SystemParameterUtility systemParameterUtility = new SystemParameterUtility();

	private Gson gson = new Gson();

	private SessionService sessionService = new SessionService();

	private static Logger logger = Logger.getLogger(LoginEmpresa.class);

	private SeguridadMovilDelegateImpl services = SeguridadMovilDelegateImpl.getInstance();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String authenticatePost(String str, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		EmpresaRestDTO empresaRest = new EmpresaRestDTO();
		String msgerr = "OK";
		AuthenticationPostDTO authentication = new AuthenticationPostDTO();

		try {
			authentication = gson.fromJson(str, AuthenticationPostDTO.class);
		} catch (JsonSyntaxException ex) {
			empresaRest.setResult("Sintaxis Json invalido");
			return gson.toJson(empresaRest);
		}

		if (authentication.getDeviceId() == null || authentication.getPassword() == null
				|| authentication.getUserNameOrEmail() == null) {
			empresaRest.setResult("Sintaxis Json invalido");
			return gson.toJson(empresaRest);
		}
		// then you can access the request/response/session etc in your methods
		MessageLoader messages = MessageLoader.getInstance();

		String userNameOrEmail = authentication.getUserNameOrEmail();
		String password = authentication.getPassword();
		String deviceId = authentication.getDeviceId();

		boolean error = false;

		try {

			// Se consulta la empresa a traves de su login, se confia que se
			// haya verificado la NO DUPLICIDAD del login
			 UsuarioVO usuario = services.consultaUsuarioPorLogin(userNameOrEmail);
			 long idUsuario = usuario.getIdUsuario();
			
			// /1098151  idUsuario para el usuario:   vin_valles@hotmail.com
//			UsuarioVO usuario = services.consultaUsuario(1098151);
			EmpresaVO empresa = services.consultaEmpresaPorIdUsuario(idUsuario);

			// Verifica la existencia del usuario
			if (empresa == null) {
				empresaRest.setResult(messages.getMessage("seguridad.login.msg.usu.no.localizado"));
				logger.error(msgerr);
				error = true;
			} else if (empresa.getEstatus() != ESTATUS.ACTIVO.getIdOpcion()) {

				empresaRest.setResult(messages.getMessage("seguridad.login.msg.usu.no.activo") + " [" + userNameOrEmail
						+ "]");
				logger.error(msgerr);
				error = true;

			} else {
				if (UtilsUsuario.validatePassword(usuario.getContrasena(), password)) {
					long idPropietario = services.consultaPropietario(usuario
							.getIdUsuario()); // Establece el identificador del
												// Candidato o Empresa segun el
												// perfil
					UsuarioFirmadoVO usuarioFirmado = UsuarioFirmadoVO
							.getInstance(idUsuario,
									usuario.getUsuario(),
									usuario.getCorreoElectronico(),
									usuario.getIdPerfil(), idPropietario);
					long idEmpresa = usuarioFirmado.getIdPropietario();
					empresaRest.setRazonSocial(empresa.getRazonSocial());

					try {
						MovilSessionVO movilSession = new MovilSessionVO();
						// 3. Create Session obj with j_username and role
						String secretKey = systemParameterUtility.getSecretKey();
						String salt = SessionUtility.makeSalt(secretKey, userNameOrEmail);
						String token = SessionUtility.makeToken(salt, userNameOrEmail, deviceId);

						Session session = new Session(userNameOrEmail, deviceId, salt, token);
						sessionService.save(session);
						// 4. Create cookieSession

						String cookie = CookieSession.write(request, response, String.valueOf(idUsuario),

								token, deviceId, String.valueOf(movilSession.getPerfil()),movilSession.getUsername(),"",TIPO_USUARIO.EMPRESA.getTipoUsuario());
						// usuarioRest.setCookie(cookie );

						movilSession = new MovilSessionVO(idUsuario, deviceId, salt, token);
						MovilSessionVO movilExiste = services.existeMovilSessionVO(movilSession);

						if (movilExiste == null) {

							movilSession.setPrimerLogin(new Date());
							services.guardarMovilSessionVO(movilSession);
						} else {
							movilSession.setPrimerLogin(movilExiste.getPrimerLogin());
							movilSession.setUltimoLogin(new Date());

							if (!cookie.equals(CookieSession.createCookie(String.valueOf(idUsuario),
									movilExiste.getToken(),deviceId, String.valueOf(empresa.getIdEmpresa()),userNameOrEmail,""))) {
								movilSession.setUltimoCambio(new Date());
								movilSession.setToken(token);

							} else {
								movilSession.setToken(movilExiste.getToken());
								movilSession.setUltimoCambio(movilExiste.getUltimoLogin());
							}
							services.actualizarMovilSessionVO(movilSession);
						}

					} catch (Exception e) {
						logger.error(e.getMessage());
						empresaRest.setResult("Error en Servicio Web Contacta al administrador.");
						return gson.toJson(empresaRest);
					}

				} else {
					msgerr = messages.getMessage("seguridad.login.msg.pwd.no");
					empresaRest.setResult(messages.getMessage("seguridad.login.msg.pwd.no"));
					logger.error(msgerr);
					error = true;
				}
			}

		} catch (Exception e) {
			empresaRest.setResult(e.getMessage());
			logger.error(e);
		}

		return gson.toJson(empresaRest);

	}
	
	
	/**
	 * Dispatches an "logout" HTTP GET request uri
	 * 
	 * @return - a String containing the name of the view
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String logout(@Context final HttpServletRequest request, @Context final HttpServletResponse response) {

		// obtener idUsuario
		String cookieHeader = request.getHeader(CookieSession.NAME);
		if (cookieHeader == null) {
			return jsonreturn("No se encuentra el token");
		}

		MovilSessionVO movilSession = CookieSession.decodeValue(cookieHeader);

		// check if cookie exist in DB

		try {
			movilSession = services.existeMovilSessionVO(movilSession);
			if (movilSession != null) {

				// movilSession.clearCookie();
				services.deleteMovilSessionVO(movilSession);

			}
		} catch (ServiceLocatorException e1) {

			return jsonreturn("No se encuentra el token");
		}

		UsuarioVO usuario;
		try {
			usuario = services.consultaUsuario(movilSession.getIdUsuario());
			Session sessionB = sessionService.findByUsername(usuario.getCorreoElectronico());
			sessionService.delete(new Session());

		} catch (ServiceLocatorException e) {
			return jsonreturn(e.getMessage());
		} catch (Exception e) {
			return jsonreturn(e.getMessage());
		}

		return jsonreturn("OK");
	}

	private String jsonreturn(String error) {
		UsuarioRestDTO usuarioRest = new UsuarioRestDTO();
		usuarioRest.setResult(error);
		return gson.toJson(usuarioRest);
	}
	
	

}
