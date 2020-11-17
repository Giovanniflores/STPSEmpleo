package mx.gob.stps.portal.web.cil.security.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.REGRESO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.MSG_ERROR_SESSION;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.MessageLoader;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.action.LoginAction;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;

public class LoginCil extends GenericAction {
	
	private static final Logger logger = Logger.getLogger(LoginAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("FROM_CIL", true);
		request.getSession().setAttribute("TITULO", "Centros de Intermediación Laboral");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Centros de Intermediación Laboral");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
	
	public ActionForward register(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("FROM_CIL", true);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Centros de Intermediación Laboral");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
	
	public ActionForward access(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idCil = 0;
		String msgerr = null;
		ActionForward forward = null;
		String code = request.getParameter("code");
		code 	    = code != null ? code.trim() : "";
		MessageLoader messages = MessageLoader.getInstance();
		SecutityDelegate services = SecutityDelegateImpl.getInstance();
		try {
			idCil = services.isValidCode(code);
			if (idCil < 0) {
            	msgerr = messages.getMessage("seguridad.login.msg.code.no.valid") +" ["+ code +"]";
            	logger.error(msgerr);
            	request.getSession().setAttribute(MSG_ERROR_SESSION, msgerr);
            	forward = mapping.findForward(REGRESO);
			}else {
				request.getSession().setAttribute("idCil", (Long)idCil);
				forward = mapping.findForward(JSP_SUCCESS);
			}
		} catch (ServiceLocatorException e) { logger.error(e); }
		return forward;
	}
	
	/**private ActionForward getForward(String key, ActionMapping mapping, HttpServletRequest request){
    	PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlredirect = properties.getProperty(key);
		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		ActionForward forward = mapping.findForward(FORWARD_REDIRECT_SWB);
		return forward;
    }**/
}