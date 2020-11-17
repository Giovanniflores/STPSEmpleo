package mx.gob.stps.portal.web.testimony.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM_EMPTY;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.testimony.delegate.TestimonioBusDelegate;
import mx.gob.stps.portal.web.testimony.delegate.TestimonioBusDelegateImpl;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PollCommentAction extends GenericAction {
	
	private static Logger logger = Logger.getLogger(PollCommentAction.class);

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Comentarios");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
			return mapping.findForward(FORWARD_TEMPLATE_FORM_EMPTY);
	}
	
	public ActionForward savePollComment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		TestimonioBusDelegate services = TestimonioBusDelegateImpl.getInstance();
		try {
			@SuppressWarnings("unused")
	   		int item1 = (null != request.getParameter("Tema1") ? Integer.parseInt(request.getParameter("Tema1")) : 0);
			int item2 = (null != request.getParameter("Tema2") ? Integer.parseInt(request.getParameter("Tema2")) : 0);
			int item3 = (null != request.getParameter("Tema3") ? Integer.parseInt(request.getParameter("Tema3")) : 0);
			int item4 = (null != request.getParameter("Tema4") ? Integer.parseInt(request.getParameter("Tema4")) : 0);
			int item5 = (null != request.getParameter("Tema5") ? Integer.parseInt(request.getParameter("Tema5")) : 0);
			int item6 = (null != request.getParameter("Tema6") ? Integer.parseInt(request.getParameter("Tema6")) : 0);
			int item7 = (null != request.getParameter("Tema7") ? Integer.parseInt(request.getParameter("Tema7")) : 0);
			int item8 = (null != request.getParameter("Tema8") ? Integer.parseInt(request.getParameter("Tema8")) : 0);
			int item9 = (null != request.getParameter("Tema9") ? Integer.parseInt(request.getParameter("Tema9")) : 0);
	   		String description1 = (null != request.getParameter("description1") ? request.getParameter("description1") : "");
	   		String description2 = (null != request.getParameter("description2") ? request.getParameter("description2") : "");
	   		String description3 = (null != request.getParameter("description3") ? request.getParameter("description3") : "");
	   		String description4 = (null != request.getParameter("description4") ? request.getParameter("description4") : "");
	   		String description5 = (null != request.getParameter("description5") ? request.getParameter("description5") : "");
	   		//logger.info("-------item1:" + item1 + " item2:" + item2 + " item3:" + item3 + " item4:" + item4);
			long result = services.savePollComment(item1, item2, item3, item4, item5, item6, item7, item8, item9, 
					description1, description2, description3, description4, description5);
			//logger.info("-------result:" + result);
		} catch (ServiceLocatorException e) { 
			e.printStackTrace(); 
		} catch (Exception ex){
			ex.printStackTrace(); 
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_NEXT").getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Comentarios");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM_EMPTY);
		//ActionForward forward = getForward("app.swb.redirect.home", mapping, request);
		//return forward;		
	}	

	private ActionForward getForward(String key, ActionMapping mapping, HttpServletRequest request) {
    	PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlredirect = properties.getProperty(key); //+ (msg!=null?"?msg="+ Utils.codifica(msg):"");
		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		ActionForward forward = mapping.findForward(FORWARD_REDIRECT_SWB);
		return forward;
    }	
	
}
