package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LABEL_BOTON;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LABEL_CANCEL;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LABEL_REGRESAR;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.EligeEstiloCurriculumForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class EligeEstiloCurriculumAction extends GenericAction {

	private static Logger logger = Logger.getLogger(EligeEstiloCurriculumAction.class);
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		try{
			logger.info("-----------------ENTRANDO A INIT MODIFICAR ESTILO DE CV");
			EligeEstiloCurriculumForm eligeForm = (EligeEstiloCurriculumForm) form;
			UsuarioWebVO usrWebVo = getUsuario(request.getSession());
			
			if (usrWebVo!=null){
				long idCandidato = usrWebVo.getIdPropietario();
				CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
				int estiloActual = service.getEstiloCV(idCandidato);		
				request.setAttribute("idCandidato", idCandidato);
				request.getSession().setAttribute("idCanEstiloCv", idCandidato);
				logger.info("-sube idCandidato a sesion en el estilo del CV- " + idCandidato);
				eligeForm.setEstiloCV(estiloActual);
			}
	    } catch (ServiceLocatorException e) {
	        logger.error(e);
	        registraError(request, "app.exp.locator.err");
		} catch (Exception e) {
			logger.error(e);
        }    	    	
		request.setAttribute(LABEL_BOTON, LABEL_CANCEL);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		//return mapping.findForward(FORWARD_TEMPLATE_FORM);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Elige estilo del curr&iacute;culum");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	/**
	 * Obtiene los datos capturados por el usuario en el formulario y actualiza el registro correspondiente
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */   
    public ActionForward selectCvStyle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	logger.info("-----------------ENTRANDO A MODIFICAR ESTILO DE CV");
    	EligeEstiloCurriculumForm eligeForm = (EligeEstiloCurriculumForm) form;
    	ActionErrors errors = null;
    	eligeForm.validate(mapping, request);           
    	    	    	
	    try{
	    	CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
	    	
	    	if (errors == null) {
	    		int estiloCv = eligeForm.getEstiloCV();
	    		//logger.info("------estiloCv:" + estiloCv);
	    
	    		UsuarioWebVO usrWebVo = getUsuario(request.getSession());
	    		if (usrWebVo!=null){
		    		long idCandidato = usrWebVo.getIdPropietario();
		    		service.setEstiloCV(idCandidato, estiloCv);	    			
	    		}
				
				request.getSession().setAttribute("modal", getMessage("El estilo del CV se ha sido modificado exitosamente."));
				request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());				
	    		
	            request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	            request.setAttribute(LABEL_BOTON, LABEL_REGRESAR);

	    	/*	
	    	} else {	    		
	    		int estiloActual = service.getEstiloCV(idCandidato);		
	    		eligeForm.setEstiloCV(estiloActual);    
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Favor de revisar los datos proporcionados."));		
				request.setAttribute(Globals.ERROR_KEY, errors);
			*/	 		
	    	}	    	
	    	
	    } catch (ServiceLocatorException e) {
	        logger.error(e);
	        registraError(request, "app.exp.locator.err");
		} catch (Exception e) {
			logger.error(e);
        }
    	
        request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Estilo del curriculum");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Estilo del curriculum, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);            					        
    }	
    
	private String getMessage(String message) {
		StringBuilder builder = new StringBuilder();
		if (null != message && !message.isEmpty()) {
			builder.append("<script>\n");
			builder.append("	message('" + message + "');\n");
			builder.append("</script>");
		}
		return builder.toString();
	}
	
}
