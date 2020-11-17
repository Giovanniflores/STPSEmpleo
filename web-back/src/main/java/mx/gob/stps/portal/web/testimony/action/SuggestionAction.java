package mx.gob.stps.portal.web.testimony.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAPTCHA_OBJ;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAPTCHA_WORD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM_EMPTY_RESP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.form.ActivacionForm;
import mx.gob.stps.portal.web.testimony.delegate.TestimonioBusDelegate;
import mx.gob.stps.portal.web.testimony.delegate.TestimonioBusDelegateImpl;
import mx.gob.stps.portal.ws.captcha.CaptchaLogicoObject;

public class SuggestionAction extends GenericAction {
	private static final Logger logger = Logger.getLogger(SuggestionAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		// instanciamos el objeto que nos sirve las preguntas del captcha: al instanciarlo genera una pregunta
		try {
			CaptchaLogicoObject captcha = getCaptcha(request);

			 ActivacionForm activacionForm = (ActivacionForm) form;

			 activacionForm.setPregunta(captcha.getPregunta());

		} catch (Exception e) {
			e.printStackTrace();
		}

        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Sugerencias");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM_EMPTY_RESP);
	}
	//Código del nuevo captcha (Abriendo Espacios)
		public void cambiaPregunta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {	
			String pregunta = null;
			try {
				CaptchaLogicoObject captcha = getCaptcha(request);
				
				ActivacionForm activacionForm = (ActivacionForm) form;
				captcha.obtieneDesafioCaptcha();
				activacionForm.setPregunta(captcha.getPregunta());
				pregunta = captcha.getPregunta();
				redirectJsonResponse(response, pregunta);	
			} catch (mx.gob.stps.portal.exception.BusinessException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				//e.printStackTrace();
			}catch(Exception e){
				//e.printStackTrace();
			}	
		}
		
		public boolean validateRespuestaUsuario(String respuestaUsuario, HttpServletRequest request) throws TechnicalException{
			boolean esCorrecto = true;
			try{
				CaptchaLogicoObject captcha = getCaptcha(request);
				
				if (respuestaUsuario == null)
					esCorrecto = false;
				else if (!captcha.isRespuestaCorrecta(respuestaUsuario) )
					esCorrecto = false;
			
			}catch (Exception e){
				logger.error("obtienePregunta: ha ocurrido un error");
				e.printStackTrace();
				throw new TechnicalException("Ha ocurrido un error al validar la respuesta");
			}
			
			return esCorrecto;
		}
		//Fin nuevo captcha (Abriendo Espacios)
	
	/**
	 * Método que guarda quejas y sugerencias
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward saveSuggestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//ActionForward forward = null;
		long idCategoria = Utils.parseLong(request.getParameter("idCategory"));
		String asunto = (null != request.getParameter("matter") ? request.getParameter("matter") : "");
		String mensaje = (null != request.getParameter("message") ? request.getParameter("message") : "");
		String email = (null != request.getParameter("email") ? request.getParameter("email") : "");
		String nombre = (null != request.getParameter("name") ? request.getParameter("name") : "");
		String apellido1 = (null != request.getParameter("lastname") ? request.getParameter("lastname") : "");
		String apellido2 = (null != request.getParameter("middlename") ? request.getParameter("middlename") : "");
		String telefono = (null != request.getParameter("phone") ? request.getParameter("phone") : "");
		int tipoTelefono = Utils.parseInt(request.getParameter("phoneType"));
		String lada = (null != request.getParameter("lada") ? request.getParameter("lada") : "");
		String ext = (null != request.getParameter("ext") ? request.getParameter("ext") : "");
		TestimonioBusDelegate services = TestimonioBusDelegateImpl.getInstance();
		try {
			ActivacionForm activacionForm = (ActivacionForm)form;
			if(!validateRespuestaUsuario(activacionForm.getRespuestaUsuario(), request)){
				registraError(request, "captcha.respuesta.incorrecta");
				//request.setAttribute(ERROR_MSG, getMensaje(request, "captcha.respuesta.incorrecta"));
			}else{
				@SuppressWarnings("unused")
				long result = services.saveSuggestion(idCategoria, asunto, mensaje, email, nombre, apellido1, apellido2, (lada + " " + telefono + " " + ext).trim(), tipoTelefono);
			}
		} catch (ServiceLocatorException e) { 
			e.printStackTrace(); 
		}  catch (TechnicalException e) {
			e.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace(); 
		}
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		//forward = getForward("app.swb.redirect.home", mapping, request);
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Sugerencias.");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM_EMPTY_RESP);
	}
	
	/** 
	 * Verificar captcha
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public ActionForward captcha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			ResultVO result = new ResultVO(ResultVO.TYPE_SUCCESS, ResultVO.TYPE_ERROR);
			if (!isValidCaptcha(request)) result.setMessage("ERROR_CAPTCHA");
			else result.setMessage("CONTINUE");
			String json = toJson(result);
			redirectJsonResponse(response, json);		
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Sugerencias");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
			return mapping.findForward(FORWARD_TEMPLATE_FORM_EMPTY_RESP);
		}
	}
	
	public boolean isValidCaptcha(HttpServletRequest request) {
		boolean captcha = true;
		HttpSession session = request.getSession();
		String word = (String)session.getAttribute(CAPTCHA_WORD);
		String code = request.getParameter("code"); // texto capturado
		if (word==null || word.trim().isEmpty() || code==null || code.trim().isEmpty() || !word.trim().equalsIgnoreCase(code.trim()))
			captcha = false;
		return captcha;
	}
	
	public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		//request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		forward = getForward("app.swb.redirect.home", mapping, request);
		return forward;
	}
	
	private ActionForward getForward(String key, ActionMapping mapping, HttpServletRequest request) {
    	PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlredirect = properties.getProperty(key); //+ (msg!=null?"?msg="+ Utils.codifica(msg):"");
		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		ActionForward forward = mapping.findForward(FORWARD_REDIRECT_SWB);
		return forward;
    }
	
	private CaptchaLogicoObject getCaptcha(HttpServletRequest request){
		CaptchaLogicoObject captcha = null;
		
		try{
			captcha = (CaptchaLogicoObject)request.getSession().getAttribute(CAPTCHA_OBJ);

			if (captcha==null){
				captcha = new CaptchaLogicoObject();
				request.getSession().setAttribute(CAPTCHA_OBJ, captcha);
			}		
		} catch (Exception e){
			e.printStackTrace();;
		}
		

		return captcha;
	}

}