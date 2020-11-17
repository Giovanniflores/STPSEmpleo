package mx.gob.stps.portal.movil.web.seguridad.action;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.USERLOGGED;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.movil.util.UtilsUsuario;
import mx.gob.stps.portal.movil.web.infra.utils.MessageLoader;
import mx.gob.stps.portal.movil.web.seguridad.delegate.SeguridadMovilDelegateImpl;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Cuenta con el manejo de autenticacion del usuario
 * 
 * @author oscar.manzo
 *
 */
public final class LoginAction extends Action {

	private static final Logger logger = Logger.getLogger(LoginAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		ActionForward forward = mapping.findForward("HOME");

		String username = request.getParameter("username"); // Parametro del login
		String password = request.getParameter("password"); // Parametro de la contrase�a
		int idPerfilSeleccionado = Integer.parseInt(request.getParameter("idTipoUsuario"));

		MessageLoader messages = MessageLoader.getInstance();

		String msgerr = null;
		boolean error = false;

		try {
			username = username!=null?username.trim():"";
			password = password!=null?password.trim():"";

			// Se consulta el usuario a traves de su login, se confia que se haya verificado la NO DUPLICIDAD del login
			SeguridadMovilDelegateImpl services = SeguridadMovilDelegateImpl.getInstance();
			UsuarioVO usuario = services.consultaUsuarioPorLogin(username);

			// Verifica la existencia del usuario
			if (usuario == null) {
				msgerr = messages.getMessage("seguridad.login.msg.usu.no.localizado");
				logger.error(msgerr);
				error = true;

			} else if (usuario.getEstatus() != ESTATUS.ACTIVO.getIdOpcion()) {
				msgerr = messages.getMessage("seguridad.login.msg.usu.no.activo") +" ["+ username +"]";
				logger.error(msgerr);
				error = true;

			} else {
				if (UtilsUsuario.validatePassword(usuario.getContrasena(), password)){
					long idPropietario = services.consultaPropietario(usuario.getIdUsuario()); // Establece el identificador del Candidato o Empresa segun el perfil

					UsuarioFirmadoVO usuarioFirmado = UsuarioFirmadoVO.getInstance(usuario.getIdUsuario(), usuario.getUsuario(), 
							usuario.getCorreoElectronico(),
							usuario.getIdPerfil(),
							idPropietario);


					if(idPerfilSeleccionado==usuario.getIdPerfil()){
						request.getSession().setAttribute(USERLOGGED, usuarioFirmado);
						logger.debug("Usuario autenticado ["+ usuario.getUsuario() +"]");
						if (usuario.getIdPerfil()==PERFIL.EMPRESA.getIdOpcion()) {
							forward = mapping.findForward("FORDWARD_ESPACIO_EMPRESA");
						} else if (usuario.getIdPerfil()==PERFIL.CANDIDATO.getIdOpcion()) {
							forward = mapping.findForward("FORDWARD_ESPACIO_CANDIDATO");
						}}

					else{
						msgerr = messages.getMessage("seguridad.login.msg.datos.error.perfil");
						logger.error(msgerr);
						error = true;
					}


				} else {
					msgerr = messages.getMessage("seguridad.login.msg.pwd.no");
					logger.error(msgerr);
					error = true;
				}
			}

		} catch (Exception e) {
			logger.error(e);
			msgerr = messages.getMessage("seguridad.login.msg.datos.error");
			error = true;
		}

		if (error){
			logger.error(msgerr);
			request.setAttribute("errormsg", msgerr);
		}    		

		return forward;
	}


}