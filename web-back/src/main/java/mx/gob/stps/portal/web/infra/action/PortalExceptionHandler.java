package mx.gob.stps.portal.web.infra.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

public class PortalExceptionHandler extends ExceptionHandler {
	private static Logger logger = Logger.getLogger(PortalExceptionHandler.class);

	public ActionForward execute(Exception ex, ExceptionConfig ae, ActionMapping mapping, ActionForm formInstance,
			                     HttpServletRequest request, HttpServletResponse response) throws ServletException {
		//storeException(request, ae.getKey(), new ActionMessage("error.global.msg", ex.toString()),mapping.findForward(Constantes.FORWARD_TEMPLATE), ae.getScope());
		ex.printStackTrace();
		logger.error(ex);
		return mapping.findForward("ERROR_PAGE");
	}
}
