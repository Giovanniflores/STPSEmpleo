package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAPTCHA_OBJ;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAPTCHA_WORD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.RegistroCandidatoForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.form.ActivacionForm;
import mx.gob.stps.portal.web.security.form.RecomendarForm;
import mx.gob.stps.portal.ws.captcha.CaptchaLogicoObject;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class RecomendarAction extends GenericAction{
	private static Logger logger = Logger.getLogger(RecomendarAction.class);
	private static final String ERROR_MSG = "ERROR_MSG";
	
//	@Override
//	public ActionForward init(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	//@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("redes", true);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		
//		logger.info(" -- " + registroForm.getTxtFromName());
//		logger.info(" -- " + registroForm.getTxtFromEmail());
//		logger.info(" -- " + registroForm.getTxtToName());
//		logger.info(" -- " + registroForm.getTxtToEmail());
//		logger.info(" -- " + registroForm.getTarMsg());
		
		// instanciamos el objeto que nos sirve las preguntas del captcha: al instanciarlo genera una pregunta
					try {
						RecomendarForm registroForm = (RecomendarForm)form;
						
						CaptchaLogicoObject captcha = getCaptcha(request);
						
						registroForm.setPregunta(captcha.getPregunta());				
						
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
			        
		String json;
		logger.info("-- INICIO -- init(...");
		try{
			if (validaCaptchaGoogle(request)){
				logger.info("-- TRUE -- validaCaptchaGoogle(request) ");
				notificaRegistro(mapping, form, request, response);
				json = "false";
			}else{
	//			String msgerr = getMensaje(request, "can.curp.captcha.err");
	//			candidato.setMessage(msgerr);
				logger.info("-- FALSE -- validaCaptchaGoogle(request) ");			
				json = getMensaje(request, "can.curp.captcha.err");;
			}

//			String json = toJson(candidato);
			redirectJsonResponse(response, json);		
		}catch(Exception e){
			e.printStackTrace(); logger.error(e); 
		}
		

		logger.info("-- FIN -- init(...");
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		//return null;
	
		
		
			
		
	}
		
		//Código del nuevo captcha (Abriendo Espacios)
		public void cambiaPregunta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
			String pregunta = null;
			try {
				CaptchaLogicoObject captcha = getCaptcha(request);
				
				RecomendarForm activationForm = (RecomendarForm) form;
				captcha.obtieneDesafioCaptcha();
				activationForm.setPregunta(captcha.getPregunta());
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
		

	
	private CaptchaLogicoObject getCaptcha(HttpServletRequest request){
		CaptchaLogicoObject captcha = null;
		
		try{
			captcha = (CaptchaLogicoObject)request.getSession().getAttribute(CAPTCHA_OBJ);

			if (captcha==null){
				captcha = new CaptchaLogicoObject();
				request.getSession().setAttribute(CAPTCHA_OBJ, captcha);
			}		
		} catch (Exception e){
			e.printStackTrace();
		}
		

		return captcha;
	}
	
	public ActionForward captcha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	ResultVO result = null;
    	
    	if (!validaCaptcha(request)){
    		String msgerr = getMensaje(request, "can.curp.captcha.err");
    		result = new ResultVO(msgerr, ResultVO.TYPE_ERROR);
    	} else{
    		result = new ResultVO("cadena de validacion correcta", ResultVO.TYPE_SUCCESS);
    	}

		try{
			String json = toJson(result);
			redirectJsonResponse(response, json);		
		}catch(Exception e){
			e.printStackTrace(); logger.error(e); 
		}

		return null;
    }
	
	private boolean validaCaptcha(HttpServletRequest request) {
		boolean captchavalido = false;
		HttpSession session = request.getSession();

		String word = (String)session.getAttribute(CAPTCHA_WORD); // clave secreta
		String code = request.getParameter("code"); // texto capturado
				
		if (word==null || word.trim().isEmpty() || code==null || code.trim().isEmpty()){
			captchavalido = false;
			logger.error("validaCaptcha -> word : "+ word +" code : "+ code);
		}else if (word.trim().equalsIgnoreCase(code.trim())) {
			captchavalido = true;	
		}else{
			captchavalido = false;
			logger.error("validaCaptcha -> word : "+ word +" code : "+ code);
		}

		return captchavalido;
	}
	
	
	private boolean validaCaptchaGoogle(HttpServletRequest request) {
		boolean captchavalido = false;		
		logger.info("-- INICIO -- validaCaptchaGoogle(...");
		
		String remoteAddr = request.getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6Le1ZeMSAAAAAAXSYw_mo65yIrRUBpQU4PjKNbIW");

        String challenge = request.getParameter("recaptcha_challenge_field");
        String uresponse = request.getParameter("recaptcha_response_field");

        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

        if (reCaptchaResponse.isValid()) {
          //System.out.println("Answer was entered correctly!");
          captchavalido = true;
        } else {
          //System.out.println("Answer is wrong");
          captchavalido = false;
        }
		logger.info("-- FIN -- verificarDatosRecomendar(...");
		return captchavalido;
		
	}	
	
	  public ActionForward veriCaptchaGoogle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	    	
	    	ResultVO result = null;
	    	
	    	if (!validaCaptchaGoogle(request)){
	    		String msgerr = getMensaje(request, "can.curp.captcha.err");
	    		result = new ResultVO(msgerr, ResultVO.TYPE_ERROR);
	    	} else{
	    		result = new ResultVO("cadena de validacion correcta", ResultVO.TYPE_SUCCESS);
	    	}

			try{
				String json = toJson(result);
				redirectJsonResponse(response, json);		
			}catch(Exception e){
				e.printStackTrace(); logger.error(e); 
			}

			
	    	
	    	/*
	    	HttpClient client = new DefaultHttpClient();
	    	HttpGet request = new HttpGet("http://www.vogella.com");
	    	HttpResponse response = client.execute(request);

	    	// Get the response
	    	BufferedReader rd = new BufferedReader
	    	  (new InputStreamReader(response.getEntity().getContent()));
	    	    
	    	String line = "";
	    	while ((line = rd.readLine()) != null) {
	    	  te*/
	    	
	    	
	        return null;
	    	
	    	
	    }

	public ActionForward notificaRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RecomendarForm registroForm = (RecomendarForm)form;
		logger.info("-- INICIO -- notificaRegistro(...");
		String message = "";
		String type = "";
		
		
		String remitente = registroForm.getTxtFromName();
		String correoRemitente = registroForm.getTxtFromEmail();
		String destinatario = registroForm.getTxtToName();
		String correoDestinatario = registroForm.getTxtToEmail();
		String respuestaUsuario = registroForm.getRespuestaUsuario();
		String mensajeAdicional = registroForm.getTarMsg();
		
//		logger.info(" -- " + registroForm.getTxtFromName());
//		logger.info(" -- " + registroForm.getTxtFromEmail());
//		logger.info(" -- " + registroForm.getTxtToName());
//		logger.info(" -- " + registroForm.getTxtToEmail());
//		logger.info(" -- " + registroForm.getTarMsg());
		
		if (correoRemitente!=null && !correoRemitente.isEmpty() && Utils.validaMail(correoRemitente) &&
				remitente!=null && !remitente.isEmpty() && destinatario!=null && !destinatario.isEmpty() &&
					correoDestinatario!=null && !correoDestinatario.isEmpty() && Utils.validaMail(correoDestinatario)
					&& respuestaUsuario!=null && !respuestaUsuario.isEmpty()){
			
			
				
			try {
				
				SecutityDelegate delegate = SecutityDelegateImpl.getInstance();
				delegate.notificaRecomendacion(remitente, destinatario, correoRemitente, correoDestinatario, mensajeAdicional);
				
				type = "exito"; 
				message = "Correo enviado a la direccion "+ correoDestinatario;
			} catch (ServiceLocatorException e) {
				e.printStackTrace(); logger.error(e);
				type = "error"; 
				message = "Error al envia correo.";
			} catch (MailException e) {
				e.printStackTrace(); logger.error(e);
				type = "error"; 
				message = "Error al envia correo.";
			} catch (Exception e) {
				e.printStackTrace(); logger.error(e);
				type = "error";
				message = "Error al envia correo.";
			}
		} else {
			type = "errormaildate"; 
			message = "Campos requeridos para el envio de la notificacion por correo.";
		}
				
		try {
			ResultVO resultado = new ResultVO(message, type);
			
			String json = toJson(resultado);
			
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("-- FIN -- notificaRegistro(...");
		return null;
	}
			
	
}


