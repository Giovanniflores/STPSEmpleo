package mx.gob.stps.portal.movil.app.service;

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

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.glasses.utilities.SessionUtility;
import mx.gob.stps.portal.movil.app.glasses.utilities.SystemParameterUtility;
import mx.gob.stps.portal.movil.app.model.Session;
import mx.gob.stps.portal.movil.app.model.base.AuthenticationPostDTO;
import mx.gob.stps.portal.movil.app.model.rest.UsuarioRestDTO;
import mx.gob.stps.portal.movil.app.session.SessionService;
import mx.gob.stps.portal.movil.util.UtilsUsuario;
import mx.gob.stps.portal.movil.web.candidato.delegate.CandidatoDelegateImpl;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.utils.MessageLoader;
import mx.gob.stps.portal.movil.web.seguridad.delegate.SeguridadMovilDelegateImpl;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Stateless
@Path("/loginUsuario")
public class LoginUsuario {

	private SystemParameterUtility systemParameterUtility = new SystemParameterUtility();

	private Gson gson = new Gson();

	private SessionService sessionService = new SessionService();

	private static Logger logger = Logger.getLogger(LoginUsuario.class);

	private SeguridadMovilDelegateImpl services = SeguridadMovilDelegateImpl.getInstance();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String authenticatepost(String str, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		UsuarioRestDTO usuarioRest = new UsuarioRestDTO();
		String msgerr = "OK";
		AuthenticationPostDTO authentication = new AuthenticationPostDTO();
		try {
			authentication = gson.fromJson(str, AuthenticationPostDTO.class);
		} catch (JsonSyntaxException ex) {
			usuarioRest.setResult("Sintaxis Json invalido");
			return gson.toJson(usuarioRest);
		}

		if (authentication.getDeviceId() == null || authentication.getPassword() == null
				|| authentication.getUserNameOrEmail() == null) {
			usuarioRest.setResult("Sintaxis Json invalido");
			return gson.toJson(usuarioRest);
		}
		// then you can access the request/response/session etc in your methods
		MessageLoader messages = MessageLoader.getInstance();

		String userNameOrEmail = authentication.getUserNameOrEmail();
		String password = authentication.getPassword();
		String deviceId = authentication.getDeviceId();
		String tipoUsuarioEsperado = authentication.getTipo();

		boolean error = false;

		try {
			// userNameOrEmail =
			// userNameOrEmail!=null?userNameOrEmail.trim():"";
			// password = password!=null?password.trim():"";

			// Se consulta el usuario a traves de su login, se confia que se
			// haya verificado la NO DUPLICIDAD del login
			UsuarioVO usuario = services.consultaUsuarioPorLogin(userNameOrEmail);

			// Verifica la existencia del usuario
			if (usuario == null) {
				// msgerr =
				// messages.getMessage("seguridad.login.msg.usu.no.localizado");
				usuarioRest.setResult(messages.getMessage("seguridad.login.msg.usu.no.localizado.movil"));
				logger.error(msgerr);
				error = true;

			} else if (usuario.getEstatus() != ESTATUS.ACTIVO.getIdOpcion()) {
				// msgerr =
				// messages.getMessage("seguridad.login.msg.usu.no.activo")
				// +" ["+ userNameOrEmail +"]";
				usuarioRest.setResult(messages.getMessage("seguridad.login.msg.usu.no.activo") + " [" + userNameOrEmail
						+ "]");
				logger.error(msgerr);
				error = true;

			} else {
				
				
				//Revisar si el usuario es del role correcto
				long idTipoUsuarioEsperado = TIPO_USUARIO.getIdTipoUsuario(tipoUsuarioEsperado);
				if( idTipoUsuarioEsperado != usuario.getIdTipoUsuario()){
					msgerr = messages.getMessage("seguridad.login.msg.usu.role.incorrecto") +" por usuario de " + TIPO_USUARIO.forIdTipoUsuario(idTipoUsuarioEsperado).getTipoUsuario(); 
				//	usuarioRest.setError(messages.getMessage("seguridad.login.msg.usu.role.incorrecto" +" por usuario de " + TIPO_USUARIO.forIdTipoUsuario(idTipoUsuarioEsperado).getTipoUsuario()));
					usuarioRest.setResult(messages.getMessage("seguridad.login.msg.usu.role.incorrecto") +".");
					logger.error(msgerr);
					return gson.toJson(usuarioRest);
				}
				
				//revisar si la clave es correcto
				if (UtilsUsuario.validatePassword(usuario.getContrasena(), password)) {
					CandidatoDelegateImpl candidatoServices = CandidatoDelegateImpl.getInstance();
					
					// Establece el identificador del Candidato o Empresa segun
					// el perfil
					long idPropietario = services.consultaPropietario(usuario.getIdUsuario());
					services.consultaUsuario(usuario.getIdUsuario());
					UsuarioFirmadoVO usuarioFirmado = UsuarioFirmadoVO.getInstance(usuario.getIdUsuario(),
							usuario.getUsuario(), usuario.getCorreoElectronico(), usuario.getIdPerfil(), idPropietario);

					long idCan_Emp = usuarioFirmado.getIdPropietario();
					// en caso de que el perfil sea Empresa, obtenemos la
					// instancia al objeto EmpresaVO
					// de lo contrario la instancia sera al objeto CandidatoVO
					String tipoUsuario ="";
					if (usuario.getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
						EmpresaVO empresa = services.consultaEmpresaPorIdUsuario(usuario.getIdUsuario());
						//persona moral
						usuarioRest.setRazonSocial(empresa.getRazonSocial());
						//para persona fisica
						usuarioRest.setNombre(empresa.getNombre());
						usuarioRest.setApellido1(empresa.getApellido1());
						usuarioRest.setApellido2(empresa.getApellido2());
						tipoUsuario = TIPO_USUARIO.EMPRESA.getTipoUsuario();

					} else {
						CandidatoVo candidato = candidatoServices.buscarDatosHeaderTemplateCandidato(idCan_Emp);

						usuarioRest.setNombre(candidato.getNombre());
						usuarioRest.setApellido1(candidato.getApellido1());
						usuarioRest.setApellido2(candidato.getApellido2());
						tipoUsuario = TIPO_USUARIO.CANDIDATO.getTipoUsuario();
					}

					try {
						MovilSessionVO movilSession = new MovilSessionVO();
						// 3. Create Session obj with j_username and role
						String secretKey = systemParameterUtility.getSecretKey();
						String salt = SessionUtility.makeSalt(secretKey, userNameOrEmail);
						String token = SessionUtility.makeToken(salt, userNameOrEmail, deviceId);

						Session session = new Session(userNameOrEmail, deviceId, salt, token);
						sessionService.save(session);
						// 4. Create cookieSession

						String cookie = CookieSession.write(request, response, String.valueOf(usuario.getIdUsuario()),
								token, deviceId, String.valueOf(usuario.getIdTipoUsuario()), userNameOrEmail,
								String.valueOf(usuarioFirmado.getIdPropietario()),tipoUsuario);
						// usuarioRest.setCookie(cookie );

						movilSession = new MovilSessionVO(usuario.getIdUsuario(),usuario.getIdTipoUsuario(), deviceId, salt, token);
						MovilSessionVO movilExiste = services.existeMovilSessionVO(movilSession);

						if (movilExiste == null) {

							movilSession.setPrimerLogin(new Date());
							services.guardarMovilSessionVO(movilSession);
						} else {
							movilSession.setPrimerLogin(movilExiste.getPrimerLogin());
							movilSession.setUltimoLogin(new Date());

							if (!cookie.equals(CookieSession.createCookie(String.valueOf(usuario.getIdUsuario()),
									movilExiste.getToken(), deviceId, String.valueOf(usuario.getIdTipoUsuario()),
									userNameOrEmail, String.valueOf(usuarioFirmado.getIdPropietario())))) {
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
						usuarioRest.setResult("Error en Servicio Web Contacta al administrador.");
						return gson.toJson(usuarioRest);
					}

				} else {
					msgerr = messages.getMessage("seguridad.login.msg.pwd.no");
					usuarioRest.setResult(messages.getMessage("seguridad.login.msg.pwd.no"));
					logger.error(msgerr);
					error = true;
				}
			}

		} catch (Exception e) {
			usuarioRest.setResult(e.getMessage());
			logger.error(e);
		}

		return gson.toJson(usuarioRest);

	}

	public String expiredSession() {
		return "expiredSession";
	}

	/**
	 * Dispatches an "logout" HTTP GET request uri
	 * 
	 * @return - a String containing the name of the view
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String logout(@Context final HttpServletRequest request, @Context final HttpServletResponse response) {

		// FilterFlowHelper.injectFlowProcess(request, FilterFlowHelper.LOGOUT);
		UsuarioRestDTO usuarioRest = new UsuarioRestDTO();

		// obtener cookieHeader
		String cookieHeader = request.getHeader(CookieSession.NAME);
		if (cookieHeader == null) {
			return jsonreturn("No se encuentra el token");
		}

		try {

			MovilSessionVO movilSession = new MovilSessionVO();

			movilSession = CookieSession.decodeValue(cookieHeader);

			// check if cookie exist in DB

			try {
				MovilSessionVO movilSession2 = services.existeMovilSessionVO(movilSession);
				if (movilSession2 != null) {

					// movilSession.clearCookie();
					services.deleteMovilSessionVO(movilSession2);

				}
			} catch (ServiceLocatorException e1) {

				return jsonreturn("No se encuentra el token");
			}

			try {
				Session sessionB = sessionService.findByUsername(movilSession.getUsername());
				sessionService.delete(sessionB);

			} catch (ServiceLocatorException e) {
				return jsonreturn(e.getMessage());
			} catch (Exception e) {
				return jsonreturn(e.getMessage());
			}

		} catch (Exception e) {
			usuarioRest.setResult(e.getMessage());
			logger.error(e);
		}

		return jsonreturn("OK");
	}

	private String jsonreturn(String error) {
		UsuarioRestDTO usuarioRest = new UsuarioRestDTO();
		usuarioRest.setResult(error);
		return gson.toJson(usuarioRest);
	}

}
