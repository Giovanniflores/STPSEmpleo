package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.ACTION_ALTA_OFERTA_RU;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DondePublicarAction extends GenericAction{
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Crear oferta de empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		request.getSession().removeAttribute("ofae");
		request.getSession().removeAttribute("ofbec");		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		return forward;
	}
	
	//RBM1 TK990 TK995 metode que envia a edición de oferta abriendo espacios
	public ActionForward registrarOfertaAbriendoEspacios(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {		
	
		//request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());	
		//request.getSession().removeAttribute("ofbec");		
		//request.getSession().setAttribute("ofae", 1);
		return mapping.findForward(ACTION_ALTA_OFERTA_RU);
	}		
	
//	public ActionForward registrarOfertaAbriendoEspacios(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {		
//	
//		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());	
//		request.getSession().removeAttribute("ofbec");		
//		request.getSession().setAttribute("ofae", 1);
//		return mapping.findForward(FORWARD_NEXT);
//	}		
	
	public ActionForward registrarOfertaPortal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());	
		request.getSession().removeAttribute("ofae");
		request.getSession().removeAttribute("ofbec");				
		return mapping.findForward(FORWARD_NEXT);
	}		
	
	public ActionForward registrarOfertaBecate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());	
		request.getSession().removeAttribute("ofae");
		request.getSession().setAttribute("ofbec", 1);
		return mapping.findForward(FORWARD_NEXT);
	}		
}
