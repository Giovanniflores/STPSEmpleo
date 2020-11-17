package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.SESION_INACTIVA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.filter.CookieManager;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Clase para finalizar la session del usuario dentro de la Aplicacion del Portal del Empleo
 * 
 * @author oscar.manzo
 *
 */
public final class LogoutAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//PropertiesLoader properties = PropertiesLoader.getInstance();
		//COMENTAR CIL EN PRODUCCION	
		  
		if (null != session.getAttribute("idCil") && null == session.getAttribute("LOGOUT_PERMITTED")) {			
			session.setAttribute("LOGOUT_CIL", request.getContextPath() + "/servicioscil.do?method=init");
			request.setAttribute(URL_REDIRECT_SWB, request.getContextPath() + "/servicioscil.do?method=init");
			return mapping.findForward(FORWARD_REDIRECT_SWB);
		}
		
		if (null == session.getAttribute("LOGOUT_PERMITTED"))
			request.setAttribute(URL_REDIRECT_SWB, request.getContextPath() +  "/");
		else 
			request.setAttribute(URL_REDIRECT_SWB,  request.getContextPath() +  "/logincil.do?method=init");
		/**/
		//TERMINA COMENTAR CIL EN PRODUCCION
		//DESCOMENTAR EN PRODUCCION
		//request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.logout.home"));
		//TERMINA DECOMENTAR EN PRODUCCION
		// Inactiva la sesion, esto tambien se invoca en el session listener
		// Se invoca para asegurar la actualizacion del usuario
		UsuarioWebVO usuario = (UsuarioWebVO)session.getAttribute(USUARIO_APP);
		if (usuario!=null) inactivaSesionUsuario(usuario);

		session.removeAttribute("POST_OFFER_REC");
		session.invalidate();

		// Se busca y elimina la cookie de sesion de usuario
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeUsuarioCookie(request, response);
		
		//request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.logout.home"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}

	private void inactivaSesionUsuario(UsuarioWebVO usuario) {
		try {
			// Desactiva la sesión del usuario (SESION_ACTIVA)
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			services.actualizaSesionActiva(usuario.getIdUsuario(), SESION_INACTIVA);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}