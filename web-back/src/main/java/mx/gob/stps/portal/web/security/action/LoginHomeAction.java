package mx.gob.stps.portal.web.security.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.web.infra.utils.Constantes;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public final class LoginHomeAction extends LoginAction {

	private static final Logger logger = Logger.getLogger(LoginHomeAction.class);
	
	@Override
	public ActionForward redirectSWB(long idPerfil, ActionMapping mapping, HttpServletRequest request, HttpServletResponse response, String username) {
		ActionForward actionForward = null;
		
		/** La diferencia con la forma en que redirecciona LoginAction es que esta clase utiliza un sendRedirect ya que se invoca desde SWB
		 * mientras LoginAction redirecciona con una pagina auxiliar ya que es invocada desde el iFrame **/
		
    	try {    		
            String SEND_REDIRECT_SWB = getUrlRedirectSWB(idPerfil, username);

    		logger.info("Redireccionando a  : "+ SEND_REDIRECT_SWB);
    		response.sendRedirect(SEND_REDIRECT_SWB);
    		response.flushBuffer();
    		return null;
    	} catch (IOException e) {
    		logger.error(e);
    		actionForward = mapping.findForward(Constantes.FORWARD_INICIO);
    	}

    	return actionForward;
	}
	

}