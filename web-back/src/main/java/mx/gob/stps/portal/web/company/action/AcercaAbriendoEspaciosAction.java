package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.ACTION_ALTA_OFERTA_RU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MI_ESPACIO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AcercaAbriendoEspaciosAction extends GenericAction{

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlAbriendoEspacios = properties.getProperty("app.domain.abriendoespacios");
		request.setAttribute("URL_ABRIENDO_ESPACIOS", urlAbriendoEspacios);
		
		request.getSession().setAttribute(TAB_MENU, TAB_MI_ESPACIO);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());		
		request.getSession().setAttribute(TITULO_PAGINA, "Acerca del portal Abriendo Espacios");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	public ActionForward registrarOferta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	
		//RBM1 TK990 TK995 Se redirecciona a Registro de Ofertas en RU
		/*
		request.getSession().setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());	
		request.getSession().setAttribute("ofae", 1);
		return mapping.findForward(FORWARD_NEXT);*/
		return mapping.findForward(ACTION_ALTA_OFERTA_RU);
		
	}	

}
