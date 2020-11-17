package mx.gob.stps.portal.web.company.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.web.company.delegate.CompanyBusDelegate;
import mx.gob.stps.portal.web.company.delegate.CompanyBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.ComRegCompanyForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

public class CapacitacionMixtaAction extends GenericAction{

	private static Logger logger = Logger.getLogger(CapacitacionMixtaAction.class);
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Capacitaci&oacute;n Mixta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Capacitaci&oacute;n Mixta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	
	public ActionForward salvarTexto(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		Long idCapMixta = null;	
		String nextAction = null;
		try{
			UsuarioWebVO us = this.getUsuario(request.getSession());
			ComRegCompanyForm capMixtaForm = (ComRegCompanyForm) form;
			if (capMixtaForm.getNombre()==null || capMixtaForm.getNombre().trim().length()<1){
				nextAction = "ERROR";
			}else{ 
				CompanyBusDelegate services = CompanyBusDelegateImpl.getInstance();
				idCapMixta = services.salvarCapacitacionMixta(capMixtaForm.getNombre(), us.getIdPropietario());
				if(idCapMixta != 0)
					nextAction = "SUCCESS";
				else
					nextAction = "ERROR";
			}
			
			redirectJsonResponse(response, nextAction);
			
		}catch (BusinessException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (ServiceLocatorException e) {
			logger.error(e); // TODO Notificar error al usuario
		} catch (IOException e) {
			logger.error(e); // TODO Notificar error al usuario
		}
		
		return null;
	}
}
