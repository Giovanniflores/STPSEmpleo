package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static mx.gob.stps.portal.core.infra.utils.Constantes.SESION_INACTIVA;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdminUsuarioAction extends GenericAction {
	private static Logger logger = Logger.getLogger(AdminUsuarioAction.class);
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		UsuarioVO usuario = new UsuarioVO();
		request.setAttribute("usuariodetalle", usuario);

		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
        return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}

	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		String username = request.getParameter("usuario");
		
		if (username!=null && !username.isEmpty()){
			try {
				SecutityDelegate services = SecutityDelegateImpl.getInstance();
				UsuarioVO usuario = services.consultaUsuarioPorLogin(username);

				request.setAttribute("usuariodetalle", usuario);

			} catch (Exception e) {logger.error(e);}	
		}else{
			UsuarioVO usuario = new UsuarioVO();
			request.setAttribute("usuariodetalle", usuario);
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}	

	public ActionForward desbloquear(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	
		long idUsuario = Utils.parseLong(request.getParameter("idUsuario"));
		String username = request.getParameter("usuario");

		if (idUsuario > 0 && username!=null && !username.isEmpty()){
			logger.info("Se desbloquea al usuario ["+ idUsuario +", "+ username +"]");
			
			try {
				SecutityDelegate services = SecutityDelegateImpl.getInstance();
				services.actualizaSesionActiva(idUsuario, SESION_INACTIVA);

				UsuarioVO usuario = services.consultaUsuarioPorLogin(username);
				request.setAttribute("usuariodetalle", usuario);

			} catch (Exception e) {logger.error(e);}	
		}else{
			UsuarioVO usuario = new UsuarioVO();
			request.setAttribute("usuariodetalle", usuario);
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}	

}
