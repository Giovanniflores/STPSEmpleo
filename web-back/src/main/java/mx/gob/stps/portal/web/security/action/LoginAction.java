package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.CANDIDATO_HEAD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.EMPRESA_HEAD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_CANCEL;
import static mx.gob.stps.portal.web.infra.utils.Constantes.MSG_ERROR_SESSION;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.candidate.vo.AccesoOLAVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.GENERO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Constantes.SISTEMAS_PORTAL;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.core.seguridad.RegistroUsuarioAplicacion;
import mx.gob.stps.portal.core.seguridad.vo.AccionVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.exception.EstatusException;
import mx.gob.stps.portal.exception.LoginException;
import mx.gob.stps.portal.exception.PasswordException;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.filter.CookieManager;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Cuenta con el manejo de autenticacion del usuario
 * 
 * @author oscar.manzo
 *
 */
public class LoginAction extends GenericAction {

	private static final Logger logger = Logger.getLogger(LoginAction.class);

	private static final String FORWARD_ACTION_CANDIDATO_ESPACIO = "ACTION_CANDIDATO";
	private static final String FORWARD_ACTION_EMPRESA_ESPACIO = "ACTION_EMPRESA";
	private static final String FORWARD_ACTION_EMPRESA_ESPACIO_RESP = "ACTION_EMPRESA_RESP";
	private static final String FORWARD_ACTION_CANDIDATO_PERFIL = "ACTION_CANDIDATO_PERFIL";
	private static final String FORWARD_ACTION_EMPRESA_PERFIL = "ACTION_EMPRESA_PERFIL";

	private static final String FORWARD_ACTION_CANDIDATO_OFERTAS = "ACTION_CANDIDATO_OFERTAS";
	private static final String FORWARD_ACTION_EMPRESA_OFERTAS = "ACTION_EMPRESA_OFERTAS";

	private static final String FORWARD_ACTION_ADMIN_PARAMETROS = "ACTION_PARAMETROS";
	private static final String FORWARD_ACTION_RECUPERA_CONTRASENA = "ACTION_RECUPERA_CONTRASENA";

	private static final String SECCION_PARAMETRO = "seccion";
	private static final String SECCION_MI_ESPACIO = "espacio";
	private static final String SECCION_MI_PERFIL = "perfil";
	private static final String SECCION_MIS_OFERTAS = "ofertas";

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String fromAbriendoEspacios = request.getParameter("fromAbriendoEspacios");

		if (null != fromAbriendoEspacios && fromAbriendoEspacios.equalsIgnoreCase("true")) {
			request.getSession().setAttribute("bFromAbriendoEspacios", true);
		}

		return getForwardHome(mapping, request);
	}

	public ActionForward userlogged(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		String seccion = request.getParameter(SECCION_PARAMETRO);
		if (seccion == null)
			seccion = "";

		UsuarioWebVO usuario = getUsuario(request.getSession());

		if (usuario == null) {
			forward = getForwardHome(mapping, request);
			return forward;
		}

		long idPerfil = usuario.getIdPerfil();
		boolean empresa = idPerfil == PERFIL.EMPRESA.getIdOpcion();
		boolean candidato = isPerfilCandidato(idPerfil);

		if (SECCION_MI_ESPACIO.equals(seccion)) {
			if (empresa) {
				forward = mapping.findForward(FORWARD_ACTION_EMPRESA_ESPACIO);
			} else if (candidato) {
				forward = mapping.findForward(FORWARD_ACTION_CANDIDATO_ESPACIO);
			} else {
				forward = mapping.findForward(FORWARD_ACTION_ADMIN_PARAMETROS);
			}

		} else if (SECCION_MI_PERFIL.equals(seccion)) {
			if (empresa) {
				forward = mapping.findForward(FORWARD_ACTION_EMPRESA_PERFIL);
			} else if (candidato) {
				forward = mapping.findForward(FORWARD_ACTION_CANDIDATO_PERFIL);
			} else {
				forward = mapping.findForward(FORWARD_ACTION_ADMIN_PARAMETROS);
			}
		} else if (SECCION_MIS_OFERTAS.equals(seccion)) {
			if (empresa) {
				forward = mapping.findForward(FORWARD_ACTION_EMPRESA_OFERTAS);
			} else if (candidato) {
				forward = mapping.findForward(FORWARD_ACTION_CANDIDATO_OFERTAS);
			} else {
				forward = mapping.findForward(FORWARD_ACTION_ADMIN_PARAMETROS);
			}
		} else {
			if (empresa) {
				forward = mapping.findForward(FORWARD_ACTION_EMPRESA_ESPACIO);
			} else if (candidato) {
				forward = mapping.findForward(FORWARD_ACTION_CANDIDATO_ESPACIO);
			} else {
				forward = mapping.findForward(FORWARD_ACTION_ADMIN_PARAMETROS);
			}
		}

		if (forward == null) {
			if (empresa) {
				forward = mapping.findForward(FORWARD_ACTION_EMPRESA_ESPACIO);
			} else if (candidato) {
				forward = mapping.findForward(FORWARD_ACTION_CANDIDATO_ESPACIO);
			} else {
				forward = mapping.findForward(FORWARD_ACTION_ADMIN_PARAMETROS);
			}
		}

		return forward;
	}

	/**
	 * Verifica la firma del usuario para permitirlo ingresar al sitio
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward = getForwardHome(mapping, request);

		String username = request.getParameter("username"); // Parametro del
															// login
		String password = request.getParameter("password"); // Parametro de la
															// contraseña
		String loginPage = request.getParameter("login_page"); // Pagina origen
		String code = request.getParameter("code"); // Codigo de acceso de un
													// CIL
		String LOGIN_PAGE_CIL = "cil";
		// String LOGIN_PAGE_HOME = "home";

		String msgerr = null; // Mensaje a mostrar en caso de error
		boolean error = false;

		// bandera de sesion para postular a ofertas reciente
		request.getSession().setAttribute("POST_OFFER_REC", "activo");

		UsuarioVO usuario = null;
		SISTEMAS_PORTAL app = SISTEMAS_PORTAL.PORTAL;

		if (LOGIN_PAGE_CIL.equals(loginPage)) {
			app = SISTEMAS_PORTAL.CIL;
		}

		try {
			username = username != null ? username.trim() : "";
			password = password != null ? password.trim() : "";
			code = code != null ? code.trim() : "";

			// Se consulta el usuario a traves de su login, se confia que se
			// haya verificado la NO DUPLICIDAD del login
			usuario = consultaUsuarioValido(username, password);

			if (app == SISTEMAS_PORTAL.CIL) {
				long idCil = 0;

				idCil = SecutityDelegateImpl.getInstance().isValidCode(code);

				if (idCil < 0) {
					msgerr = getMensaje(request, "seguridad.login.msg.code.no.valid") + " [" + code + "]";
					throw new EstatusException(msgerr);
				}

				request.getSession().setAttribute("idCil", (Long) idCil);
			}

			// Verifica la existencia del usuario
			validaEstatus(request, usuario, username);

			/** USUARIO VALIDO **/

			if (esPerfilCandidatoAbriendoEspacios(usuario.getIdPerfil())) {
				usuario.setIdPerfil(PERFIL.CANDIDATO.getIdOpcion());
			}

			firmaUsuarioPortal(request, response, usuario);
			// isSesionActiva = usuario.getSesionActiva() == SESION_ACTIVA;
			forward = getPerfilForward(request, response, mapping, usuario.getIdPerfil());
			if (null == forward) {
				error = true;
				msgerr = "No cuenta con los permisos suficientes para ingresar al administrador";
			}
		} catch (LoginException e) {
			logger.error("seguridad.login.msg.usu.error", e);
			msgerr = getMensaje(request, "seguridad.login.msg.usu.no.localizado");
			error = true;
		} catch (PasswordException e) {
			logger.error("seguridad.login.msg.usu.error", e);
			msgerr = getMensaje(request, "seguridad.login.msg.usu.error");
			error = true;
		} catch (EstatusException e) {
			logger.error(e.getMessage()); // El mensaje se establece en donde se
											// lanza la exception
			msgerr = e.getMessage();
			error = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			msgerr = getMensaje(request, "seguridad.login.msg.datos.error");
			error = true;
		}
		if (error) { // En caso de error se verifica a donde se redirecciona
			request.getSession().setAttribute(MSG_ERROR_SESSION,
					msgerr); /** REGISTRO DE MENSAJE DE ERROR **/
			if (usuario != null && isPerfilCandidato(usuario.getIdPerfil())
					&& usuario.getEstatus() == ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion()
					&& app == SISTEMAS_PORTAL.PORTAL) {
				forward = mapping.findForward(FORWARD_ACTION_RECUPERA_CONTRASENA);
			} else if (usuario != null && isPerfilCandidato(usuario.getIdPerfil())
					&& usuario.getEstatus() == ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion()
					&& app == SISTEMAS_PORTAL.PORTAL) {
				forward = mapping.findForward(FORWARD_ACTION_RECUPERA_CONTRASENA);
			} else if (usuario != null && usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()
					&& usuario.getEstatus() == ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion()
					&& app == SISTEMAS_PORTAL.PORTAL) {
				forward = mapping.findForward(FORWARD_ACTION_RECUPERA_CONTRASENA);
			} else if (usuario != null && usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()) {
				forward = getForwardHome(mapping, request);
			} else if (app == SISTEMAS_PORTAL.CIL) {
				forward = getForwardCil(mapping, request);
			} else {
				forward = getForwardHome(mapping, request);
			}
		}
		return forward;
	}

	public ActionForward getPerfilForward(HttpServletRequest request, HttpServletResponse response,
			ActionMapping mapping, long idPerfil) {
		ActionForward forward = null;

		if (idPerfil == PERFIL.EMPRESA.getIdOpcion()) {
			forward = mapping.findForward(FORWARD_ACTION_EMPRESA_ESPACIO);
		} else if (isPerfilCandidato(idPerfil)) {
			forward = mapping.findForward(FORWARD_ACTION_CANDIDATO_ESPACIO);
		} else if (idPerfil == PERFIL.PUBLICADOR.getIdOpcion()) {
			forward = mapping.findForward("ACTION_PUBLICADOR");
		} else if (idPerfil == PERFIL.SUPERVISOR_SNETEL.getIdOpcion()) {
			forward = mapping.findForward("ACTION_ADMIN");
		} else if (idPerfil == PERFIL.ADMINISTRADOR_SNETEL.getIdOpcion()) {
			forward = mapping.findForward("ACTION_ADMIN");
		} else if (idPerfil == PERFIL.ADMINISTRADOR.getIdOpcion()
				|| idPerfil == PERFIL.SUPERADMINISTRADOR.getIdOpcion()) {
			forward = mapping.findForward("ACTION_ADMIN");
		} else if (idPerfil == PERFIL.ADMIN_TIPO_A.getIdOpcion()) {
			forward = mapping.findForward(FORWARD_ACTION_ADMIN_PARAMETROS);
		} else if (idPerfil == PERFIL.MONITOR_PE.getIdOpcion()) {
			forward = mapping.findForward("ACTION_MONITOR");
		}
		// cookie red it
		CookieManager.getInstance().removeCookieRediIt(request, response, "r3d1T024");
		return forward;
	}

	private void validaEstatus(HttpServletRequest request, UsuarioVO usuario, String username)
			throws EstatusException, Exception {
		String msgerr = null;

		if (usuario.getEstatus() == ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion()) {
			request.getSession().setAttribute("idInactivoAPeticionDelUsuario",
					ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion());
			request.getSession().setAttribute("usuarioInactivoAPeticionDelUsuario", username);
			msgerr = getMensaje(request, "seguridad.login.msg.usu.reactivate", username);
			throw new EstatusException(msgerr);

		} else if (usuario.getEstatus() == ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion()) {
			request.getSession().setAttribute("idInactivoPorVigencia", ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion());
			request.getSession().setAttribute("usuarioInactivoPorVigencia", username);
			msgerr = getMensaje(request, "seguridad.login.msg.usu.inactivo.vig", username);
			throw new EstatusException(msgerr);

		} else if (usuario.getEstatus() == ESTATUS.INACTIVO_POR_BAJA_ADMINISTRADOR.getIdOpcion()) {
			request.getSession().setAttribute("idInactivoPorAdmor",
					ESTATUS.INACTIVO_POR_BAJA_ADMINISTRADOR.getIdOpcion());
			request.getSession().setAttribute("usuarioInactivoPorAdmor", username);
			msgerr = getMensaje(request, "seguridad.login.msg.usu.inactivo.admor");
			throw new EstatusException(msgerr);

		} else if (usuario.getEstatus() == ESTATUS.ELIMINADA_ADMIN.getIdOpcion()) {
			msgerr = getMensaje(request, "seguridad.login.msg.empresa.desactivada", username);
			throw new EstatusException(msgerr);

		} else if (usuario.getEstatus() != ESTATUS.ACTIVO.getIdOpcion() || !isPropietarioActivo(usuario)) {
			msgerr = getMensaje(request, "seguridad.login.msg.usu.no.activo") + " [" + username + "]";
			throw new EstatusException(msgerr);
		}
	}

	private UsuarioVO consultaUsuarioValido(String username, String password)
			throws LoginException, PasswordException, Exception {

		if (username == null || username.trim().isEmpty())
			throw new LoginException("Usuario requerido.");
		if (password == null || password.trim().isEmpty())
			throw new PasswordException("Contraseña requerida.");

		// Se verifica si existe el mapa de usuarios con sesion y en el tiempo
		// que se logueo
		RegistroUsuarioAplicacion.registrarUsuarioLogeado(username);

		SecutityDelegate services = SecutityDelegateImpl.getInstance();
		UsuarioVO usuario = services.consultaUsuarioPorLogin(username);

		if (usuario == null)
			throw new LoginException("Usuario no localizado.");

		boolean isPswValid = validatePassword(usuario.getContrasena(), password);

		if (!isPswValid)
			throw new PasswordException("Contraseña incorrecta");

		return usuario;
	}

	private boolean esPerfilCandidatoAbriendoEspacios(long idPerfil) {
		boolean esPerfilCandidatoAbriendoEspacios = false;

		if (idPerfil == PERFIL.CANDIDATO_ADULTO_MAYOR.getIdOpcion()
				|| idPerfil == PERFIL.CANDIDATO_DISCAPACIDAD.getIdOpcion()) {
			esPerfilCandidatoAbriendoEspacios = true;
		}

		return esPerfilCandidatoAbriendoEspacios;
	}

	public void firmaUsuarioPortal(HttpServletRequest request, HttpServletResponse response, UsuarioVO usuario)
			throws SQLException, ServiceLocatorException {

		// Se consulta el usuario a traves de su login, se confia que se haya
		// verificado la NO DUPLICIDAD del login
		SecutityDelegate services = SecutityDelegateImpl.getInstance();

		/** SE AGREGA EL USUARIO A SESION ***/
		UsuarioWebVO usuarioWeb = createUsuarioWeb(usuario);

		long idPropietario = services.consultaPropietario(usuario.getIdUsuario());

		usuarioWeb.setIdPropietario(idPropietario);

		List<AccionVO> acciones = services.consultaAccionesPorPerfil(usuario.getIdPerfil());
		usuarioWeb.setAcciones(getVinculos(acciones));

		request.getSession().setAttribute(USUARIO_APP, usuarioWeb);

		/**
		 * La Sesion del usuario se Activa una vez que se esta seguro que el
		 * usuario puede entrar a su modulo (Empresa, Candidato), por lo tanto
		 * esta bandera de sesion_activa se establece en el Espacio de Empresa y
		 * Espacio de Candidato
		 **/
		// services.actualizaSesionActiva(usuario.getIdUsuario(),
		// SESION_ACTIVA);

		if (isPerfilCandidato(usuario.getIdPerfil())) {
			registraCandidatoHeader(request, idPropietario);
		} else if (usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()) {
			registraEmpresaHeader(request, idPropietario);
		}

		CookieManager.getInstance().createUserCookie(response, usuario.getUsuario());

		consultaOLA(usuarioWeb);

		SecutityDelegateImpl.getInstance().iniciaSesion(usuario.getIdUsuario(), usuario.getIdPerfil());

		// Registrar el ultimoacceso del candidato
		if (isPerfilCandidato(usuario.getIdPerfil())) {
			SecutityDelegateImpl.getInstance().registraUltimoAccesoCandidato(idPropietario);
		}
	}

	public ActionForward redirectSWB(long idPerfil, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, String username) {
		return redirectSWB(idPerfil, mapping, request, response, username, SISTEMAS_PORTAL.PORTAL);
	}

	public ActionForward redirectSWB(long idPerfil, ActionMapping mapping, HttpServletRequest request,
			HttpServletResponse response, String username, SISTEMAS_PORTAL app) {
		String urlRedirectSWB = getUrlRedirectSWB(idPerfil, username, app);
		estableceURL(request, urlRedirectSWB);
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}

	/**
	 * Arma la ruta hacia un recurso del SWB para el LOGIN en SWB con el
	 * parametro convenido
	 * 
	 * @param idPerfil
	 *            Perfil de usuario
	 * @param username
	 * @return
	 */
	protected String getUrlRedirectSWB(long idPerfil, String username) {
		return getUrlRedirectSWB(idPerfil, username, SISTEMAS_PORTAL.PORTAL);
	}

	protected String getUrlRedirectSWB(long idPerfil, String username, SISTEMAS_PORTAL app) {
		String key = null;

		if (idPerfil == PERFIL.EMPRESA.getIdOpcion()) {
			key = "app.login.redirect.portal.empresa";
		} else if (isPerfilCandidato(idPerfil)) {
			if (app != null && app == SISTEMAS_PORTAL.CIL)
				key = "app.login.redirect.portal.cil";
			else
				key = "app.login.redirect.portal.candidato";
		} else if (idPerfil == PERFIL.PUBLICADOR.getIdOpcion()) {
			key = "app.login.redirect.portal.publicador";
		} else if (idPerfil == PERFIL.ADMINISTRADOR.getIdOpcion()
				|| idPerfil == PERFIL.SUPERADMINISTRADOR.getIdOpcion()) {
			key = "app.login.redirect.portal.admin";
		} else if (idPerfil == PERFIL.SUPERVISOR.getIdOpcion()) {
			key = "app.login.redirect.portal.admin";
		} else if (idPerfil == PERFIL.ADMIN_TIPO_A.getIdOpcion()) {
			key = "app.login.redirect.portal.admin";
		} else if (idPerfil == PERFIL.SUPERVISOR_SNETEL.getIdOpcion()) {
			key = "app.login.redirect.portal.admin";
		} else if (idPerfil == PERFIL.ADMINISTRADOR_SNETEL.getIdOpcion()) {
			key = "app.login.redirect.portal.admin";
		} else if (idPerfil == PERFIL.MONITOR_PE.getIdOpcion()) {
			key = "app.login.redirect.portal.admin";
		} else {
			key = "app.login.redirect.portal.home";
		}

		PropertiesLoader properties = PropertiesLoader.getInstance();

		String SEND_REDIRECT_SWB = properties.getProperty(key);// +
																// Utils.codifica(username);
		return SEND_REDIRECT_SWB;
	}

	private ActionForward getForwardHome(ActionMapping mapping, HttpServletRequest request) {
		return getForward("app.swb.redirect.home", mapping, request);
	}

	private ActionForward getForwardCil(ActionMapping mapping, HttpServletRequest request) {
		// request.getSession().setAttribute(BODY_JSP,
		// mapping.findForward(JSP_CANCEL).getPath());
		return mapping.findForward(JSP_CANCEL);// mapping.findForward(FORWARD_TEMPLATE_FORM);
	}

	private ActionForward getForward(String key, ActionMapping mapping, HttpServletRequest request) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		String msg = (String) request.getSession().getAttribute(MSG_ERROR_SESSION);
		String urlredirect = properties.getProperty(key) + (msg != null ? "?msg=" + Utils.codifica(msg) : "");
		estableceURL(request, urlredirect);
		ActionForward forward = mapping.findForward(FORWARD_REDIRECT_SWB);
		return forward;
	}

	private void estableceURL(HttpServletRequest request, String urlredirect) {
		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
	}

	/**
	 * Valida con correspondencia de la contraseña
	 * 
	 * @param storedPassword
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean validatePassword(String storedPassword, String password) throws Exception {

		if (storedPassword == null || storedPassword.isEmpty())
			return false;
		if (password == null || password.isEmpty())
			return false;

		String codificadoPassword = Password.codificaPassword(password);

		if (!storedPassword.equals(codificadoPassword))
			return false;

		return true;
	}

	/**
	 * Crea una instancia para el Usuario Web con los datos del usuario firmado
	 * 
	 * @param usuario
	 * @return
	 */
	private UsuarioWebVO createUsuarioWeb(UsuarioVO usuario) {
		UsuarioWebVO vo = UsuarioWebVO.getInstance(usuario.getUsuario(), usuario.getIdUsuario(),
				usuario.getCorreoElectronico(), usuario.getIdTipoUsuario(), usuario.getIdRegistro(),
				usuario.getEstatus(), usuario.getIdEntidad(), usuario.getIdPerfil(), usuario.getFechaAlta(),
				usuario.getContrasena());
		return vo;
	}

	private List<String> getVinculos(List<AccionVO> acciones) {

		List<String> accionesPath = new ArrayList<String>();
		if (acciones != null && !acciones.isEmpty()) {
			for (AccionVO accion : acciones)
				accionesPath.add(accion.getVinculo());
		}

		return accionesPath;
	}

	private void consultaOLA(UsuarioWebVO usuarioWeb) {
		try {
			CandidatosRegistroBusDelegate services = CandidatosRegistroBusDelegateImpl.getInstance();
			AccesoOLAVO accesos = services.consultaAccesosOLA(usuarioWeb.getIdPropietario(), usuarioWeb.getIdPerfil());

			usuarioWeb.setUrlCarrera(accesos.getUrlCarrera());
			usuarioWeb.setTituloCarrera(accesos.getTituloCarrera());

			usuarioWeb.setUrlOcupacion(accesos.getUrlOcupacion());
			usuarioWeb.setTituloOcupacion(accesos.getTituloOcupacion());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	private boolean isPropietarioActivo(UsuarioVO usuario) throws Exception {
		boolean isActivo = true; // SE INICIALIZA EN TRUE PARA QUE PERMITA PASAR
									// A USUARIOS QUE NO CORRESPONDEN A UNA
									// EMPRESA O UN CANDIDATO

		SecutityDelegate services = SecutityDelegateImpl.getInstance();
		long idPropietario = services.consultaPropietario(usuario.getIdUsuario());

		if (usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()) {

			EmpresaVO empresa = services.consultaEmpresa(idPropietario);
			isActivo = empresa.getEstatus() == ESTATUS.ACTIVO.getIdOpcion();

		} else if (isPerfilCandidato(usuario.getIdPerfil())) {

			CandidatoVo candidato = services.consultaCandidato(idPropietario);
			isActivo = candidato.getEstatus() == ESTATUS.ACTIVO.getIdOpcion();
		}

		return isActivo;
	}

	private void registraCandidatoHeader(HttpServletRequest request, long idCandidato) {
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			CandidatoVo candidato = services.buscarDatosHeaderTemplateCandidato(idCandidato);

			if (candidato != null) {

				Date fechaNacimiento = candidato.getFechaNacimiento();

				int edad = candidato.getEdad();
				if (fechaNacimiento != null) {
					edad = Utils.calculaEdad(fechaNacimiento);
				}

				String nombre = candidato.getNombre() + " " + candidato.getApellido1();

				if (candidato.getApellido2() != null) {
					nombre += " " + candidato.getApellido2();
				}

				Calendar fechaAlta = candidato.getFechaAlta();
				String fecAlta = Utils.formatDDMMYYYY(fechaAlta.getTime());

				ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(candidato.getIdEntidadNacimiento());
				String entidadNac = "";
				if (entidad != null)
					entidadNac = entidad.getDescripcion();

				String sexo = "";

				if (GENERO.MASCULINO.getIdOpcion() == candidato.getGenero()) {
					sexo = "Hombre";
				} else if (GENERO.FEMENINO.getIdOpcion() == candidato.getGenero()) {
					sexo = "Mujer";
				}

				String ppcEstatusIdOpcion = String.valueOf(candidato.getPpcEstatus());

				logger.info(nombre + "|Validando nombre");
				CandidatoAjaxVO datos = new CandidatoAjaxVO();
				datos.setNombre(nombre);
				datos.setCurp(candidato.getCurp());
				datos.setSexo(sexo);
				datos.setEdad("" + edad);
				datos.setEntidad(entidadNac);
				datos.setFechaalta(fecAlta);
				datos.setPpcEstatusIdOpcion(ppcEstatusIdOpcion);
				datos.setCorreoElectronico(candidato.getCorreoElectronico());
				datos.setTrabajaActualmente(String.valueOf(candidato.getIdEstatusLaboral()));
				datos.setIdCandidato(candidato.getIdCandidato());
				datos.setIdUsuario(candidato.getIdUsuario());
				request.getSession().setAttribute(CANDIDATO_HEAD, datos);

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	private void registraEmpresaHeader(HttpServletRequest request, long idEmpresa) {
		try {
			RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
			EmpresaVO empresa = service.findEmpresaById(idEmpresa);

			String tipoPersona = TIPO_PERSONA.getTipoPersona((int) empresa.getIdTipoPersona());
			empresa.setTipoPersona(tipoPersona);

			Date fechaEmp = null;

			if (empresa.getIdTipoPersona() == TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()) {
				fechaEmp = empresa.getFechaNacimiento();
			} else if (empresa.getIdTipoPersona() == TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()) {
				fechaEmp = empresa.getFechaActa();
			}

			String fechaEmpresa = Utils.formatDDMMYYYY(fechaEmp);

			request.getSession().setAttribute("empresafechaheader", fechaEmpresa);
			request.getSession().setAttribute(EMPRESA_HEAD, empresa);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	// Función que evalúa si el perfil es de candidato portao o de abriendo
	// espacios
	private boolean isPerfilCandidato(long idPerfil) {

		if (idPerfil == mx.gob.stps.portal.utils.Utils.toLong(PERFIL.CANDIDATO.getIdOpcion()))
			return true;

		if (idPerfil == mx.gob.stps.portal.utils.Utils.toLong(PERFIL.CANDIDATO_ADULTO_MAYOR.getIdOpcion()))
			return true;

		if (idPerfil == mx.gob.stps.portal.utils.Utils.toLong(PERFIL.CANDIDATO_DISCAPACIDAD.getIdOpcion()))
			return true;

		return false;
	}

	public ActionForward loginPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String type = "";
		String message = "";
		String token = "";

		UsuarioVO usuario = null;

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		username = username != null ? username.trim() : "";
		password = password != null ? password.trim() : "";

		try {
			usuario = consultaUsuarioValido(username, password);

			validaEstatus(request, usuario, username);

			firmaUsuarioPortal(request, response, usuario);

			type = ResultVO.TYPE_SUCCESS;
			message = "Usuario valido.";
			token = Utils.codifica(username);

		} catch (LoginException e) {
			type = ResultVO.TYPE_ERROR;
			message = getMensaje(request, "seguridad.login.msg.usu.error");
			logger.error(message);
		} catch (PasswordException e) {
			type = ResultVO.TYPE_ERROR;
			message = getMensaje(request, "seguridad.login.msg.usu.error");
			logger.error(message);
		} catch (EstatusException e) {
			type = ResultVO.TYPE_ERROR;
			message = e.getMessage();
			logger.error(message);
		} catch (Exception e) {
			type = ResultVO.TYPE_ERROR;
			e.printStackTrace();
			message = getMensaje(request, "seguridad.login.msg.datos.error");
			logger.error(message);
		}

		ResultVO resultado = new ResultVO(message, type);
		resultado.setValue(token);

		String json = toJson(resultado);
		redirectJsonResponse(response, json);
		return null;
	}

	/**
	 * Verifica la firma del usuario para permitirlo ingresar al sitio
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward loginresp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward = getForwardHome(mapping, request);

		String username = request.getParameter("username"); // Parametro del
															// login
		String password = request.getParameter("password"); // Parametro de la
															// contraseña
		String loginPage = request.getParameter("login_page"); // Pagina origen
		String code = request.getParameter("code"); // Codigo de acceso de un
													// CIL

		String LOGIN_PAGE_CIL = "cil";
		// String LOGIN_PAGE_HOME = "home";

		String msgerr = null; // Mensaje a mostrar en caso de error
		boolean error = false;

		UsuarioVO usuario = null;
		SISTEMAS_PORTAL app = SISTEMAS_PORTAL.PORTAL;

		if (LOGIN_PAGE_CIL.equals(loginPage)) {
			app = SISTEMAS_PORTAL.CIL;
		}

		try {
			username = username != null ? username.trim() : "";
			password = password != null ? password.trim() : "";
			code = code != null ? code.trim() : "";

			// Se consulta el usuario a traves de su login, se confia que se
			// haya verificado la NO DUPLICIDAD del login
			usuario = consultaUsuarioValido(username, password);

			if (app == SISTEMAS_PORTAL.CIL) {
				long idCil = 0;

				idCil = SecutityDelegateImpl.getInstance().isValidCode(code);

				if (idCil < 0) {
					msgerr = getMensaje(request, "seguridad.login.msg.code.no.valid") + " [" + code + "]";
					throw new EstatusException(msgerr);
				}

				request.getSession().setAttribute("idCil", (Long) idCil);
			}

			// Verifica la existencia del usuario
			validaEstatus(request, usuario, username);

			/** USUARIO VALIDO **/

			if (esPerfilCandidatoAbriendoEspacios(usuario.getIdPerfil())) {
				usuario.setIdPerfil(PERFIL.CANDIDATO.getIdOpcion());
			}

			firmaUsuarioPortal(request, response, usuario);

			// isSesionActiva = usuario.getSesionActiva() == SESION_ACTIVA;
			forward = getPerfilForwardResp(mapping, usuario.getIdPerfil());

			/** REDIRECCIONA HACIA UN RECURSO DE SWB **/ // SINO SE
															// REDIRECCIONA, SE
															// ENVIA A LA PAGINA
															// CORRESPONDIENTE
															// SIN SWB
			// forward = redirectSWB(usuario.getIdPerfil(), mapping, request,
			// response, username, app);

		} catch (LoginException e) {
			logger.error("seguridad.login.msg.usu.error");
			msgerr = getMensaje(request, "seguridad.login.msg.usu.error");
			error = true;

		} catch (PasswordException e) {
			logger.error("seguridad.login.msg.usu.error");
			msgerr = getMensaje(request, "seguridad.login.msg.usu.error");
			error = true;

		} catch (EstatusException e) {
			logger.error(e.getMessage()); // El mensaje se establece en donde se
											// lanza la exception
			msgerr = e.getMessage();
			error = true;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			msgerr = getMensaje(request, "seguridad.login.msg.datos.error");
			error = true;
		}
		if (error) { // En caso de error se verifica a donde se redirecciona
			request.getSession().setAttribute(MSG_ERROR_SESSION,
					msgerr); /** REGISTRO DE MENSAJE DE ERROR **/
			if (usuario != null && isPerfilCandidato(usuario.getIdPerfil())
					&& usuario.getEstatus() == ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion()
					&& app == SISTEMAS_PORTAL.PORTAL) {
				forward = mapping.findForward(FORWARD_ACTION_RECUPERA_CONTRASENA);
			} else if (usuario != null && isPerfilCandidato(usuario.getIdPerfil())
					&& usuario.getEstatus() == ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion()
					&& app == SISTEMAS_PORTAL.PORTAL) {
				forward = mapping.findForward(FORWARD_ACTION_RECUPERA_CONTRASENA);
			} else if (usuario != null && usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()
					&& usuario.getEstatus() == ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion()
					&& app == SISTEMAS_PORTAL.PORTAL) {
				forward = mapping.findForward(FORWARD_ACTION_RECUPERA_CONTRASENA);
			} else if (usuario != null && usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion()) {
				forward = getForwardHome(mapping, request);
			} else if (app == SISTEMAS_PORTAL.CIL) {
				forward = getForwardCil(mapping, request);
			} else {
				forward = getForwardHome(mapping, request);
			}
		}
		return forward;
	}

	public ActionForward getPerfilForwardResp(ActionMapping mapping, long idPerfil) {
		ActionForward forward = null;
		if (idPerfil == PERFIL.EMPRESA.getIdOpcion()) {
			forward = mapping.findForward(FORWARD_ACTION_EMPRESA_ESPACIO_RESP);
		} else if (isPerfilCandidato(idPerfil)) {
			forward = mapping.findForward(FORWARD_ACTION_CANDIDATO_ESPACIO);
		} else if (idPerfil == PERFIL.PUBLICADOR.getIdOpcion()) {
			forward = mapping.findForward("ACTION_PUBLICADOR");
		} else if (idPerfil == PERFIL.SUPERVISOR_SNETEL.getIdOpcion()) {
			forward = mapping.findForward("ACTION_ADMIN");
		} else if (idPerfil == PERFIL.ADMINISTRADOR_SNETEL.getIdOpcion()) {
			forward = mapping.findForward("ACTION_ADMIN");
		} else if (idPerfil == PERFIL.ADMINISTRADOR.getIdOpcion()
				|| idPerfil == PERFIL.SUPERADMINISTRADOR.getIdOpcion()) {
			forward = mapping.findForward("ACTION_ADMIN");
		} else if (idPerfil == PERFIL.ADMIN_TIPO_A.getIdOpcion()) {
			forward = mapping.findForward(FORWARD_ACTION_ADMIN_PARAMETROS);
		} else if (idPerfil == PERFIL.MONITOR_PE.getIdOpcion()) {
			forward = mapping.findForward("ACTION_MONITOR");
		}
		return forward;
	}
	
	//RBM1 TK989 TK993
	public ActionForward forwardHomeFromRU(ActionMapping mapping, HttpServletRequest request , HttpServletResponse response){
		//return getForward("app.swb.redirect.home", mapping, request);25 mayo la  actualize
    	return redirectSWB(0, mapping, request, response, null, SISTEMAS_PORTAL.PORTAL);
    }
}