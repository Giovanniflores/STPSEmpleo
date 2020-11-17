package mx.gob.stps.portal.web.offer.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MIS_OFERTAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;

public final class SeguimientoPostulacionExternaAction extends PagerAction {

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();		
        session.setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
        ocultaBarraArticulos(request);		
		
        session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Seguimiento Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Seguimiento Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	
	public ActionForward enviarASeguimiento(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();		
        session.setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
        ocultaBarraArticulos(request);		
		
        session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Seguimiento Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Seguimiento Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}	
	
}