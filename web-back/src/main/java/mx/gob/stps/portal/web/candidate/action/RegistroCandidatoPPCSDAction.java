package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.*;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAPTCHA_OBJ;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_REGISTRO_CANDIDATO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.SHOW_BUSCADOR_OFERTAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CANDIDATO_HEAD;
import static mx.gob.stps.portal.core.infra.utils.Constantes.RESPUESTA_IMMS_CONSULTA_NSS;

import java.io.IOException;
import java.util.Date;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.seguridad.service.SeguimientoAtencionAppServiceLocal;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.web.candidate.form.RegistroCandidatoPPCSDForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.ws.captcha.CaptchaLogicoObject;

import mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO;

import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegateImpl;

import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.ACEPTACION_TERMINOS;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS;

public class RegistroCandidatoPPCSDAction extends GenericAction {
	
	private static Logger logger = Logger.getLogger(RegistroCandidatoPPCSDAction.class);
	
	private static final String FORDWARD_CONTENIDO_PPCSD = "JSP_CONTENIDO";	
	private static final String FORDWARD_TERMGEN_PPCSD = "JSP_TERMGEN";	
	private static final String FORDWARD_CURPNSS_CANDREG = "JSP_CURP_CANDREG";
	private static final String FORDWARD_TERMCOND_PPCSD = "JSP_TERMCOND";
	private static final String FORDWARD_MIPERFIL = "JSP_MIPERFIL";
	private static final String FORDWARD_ERRORPAGE = "ERROR_PAGE";
	private static final String CAPTCHA_IN_SESSION = "CAPTCHA_IN_SESSION";
	
	public ActionForward showContenido(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().removeAttribute(CAPTCHA_IN_SESSION);
		request.getSession().removeAttribute("registroCandidatoForm");
		
		showBuscadorOfertas(request);
		return getForward(mapping, request, FORDWARD_CONTENIDO_PPCSD);
	}

	public ActionForward showTerminosGenerales(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		
		request.getSession().removeAttribute(CAPTCHA_IN_SESSION);
		
		ActionForward forward = getForward(mapping, request, FORDWARD_TERMGEN_PPCSD);
		showBuscadorOfertas(request);
		return forward;
	}		
	
	public ActionForward showTerminosCondiciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {				

		try{			
			request.getSession().removeAttribute(CAPTCHA_IN_SESSION);

			if (((RegistroCandidatoPPCSDForm)form).getCurp() == null || ((RegistroCandidatoPPCSDForm)form).getCurp().trim().isEmpty()){
				logger.info("No se ha podido recuperar la curp del candidato en sesión");
				response.sendRedirect(request.getContextPath()+"/registroCandidatoPPCSD.do?method=init");
				return null;				
			}
			
			if (((RegistroCandidatoPPCSDForm)form).getNss() == null || ((RegistroCandidatoPPCSDForm)form).getNss().trim().isEmpty()){
				logger.info("No se ha podido recuperar el nss del candidato en sesión");			
				response.sendRedirect(request.getContextPath()+"/registroCandidatoPPCSD.do?method=init");
				return null;				
			}
	
			if (((RegistroCandidatoPPCSDForm)form).getIdCandidato() <= 0L){
				logger.info("No se ha podido recuperar idCandidato del candidato en sesión");			
				response.sendRedirect(request.getContextPath()+"/registroCandidatoPPCSD.do?method=init");
				return null;				
			}
			
			request.setAttribute("curp", ((RegistroCandidatoPPCSDForm)form).getCurp());
			request.setAttribute("nss", ((RegistroCandidatoPPCSDForm)form).getNss());
			request.setAttribute("idCandidato", String.valueOf(((RegistroCandidatoPPCSDForm)form).getIdCandidato()));
			
			request.setAttribute("aceptaTerminosSi", Catalogos.ACEPTACION_TERMINOS.SI);
			request.setAttribute("aceptaTerminosNo", Catalogos.ACEPTACION_TERMINOS.NO);		
			
			return getForward(mapping, request, FORDWARD_TERMCOND_PPCSD);
		} catch (Exception e){
			logger.error("Ha ocurrido un error en showTerminosCondiciones");			
			e.printStackTrace();
			return getForward(mapping, request, FORDWARD_ERRORPAGE);
		}
	}
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try{
			request.getSession().removeAttribute(CAPTCHA_IN_SESSION);
			
			RegistroCandidatoPPCSDForm registroCandidatoForm = (RegistroCandidatoPPCSDForm) form;
			
			request.getSession().removeAttribute(CAPTCHA_IN_SESSION);
			
			// Si tenemos sesión iniciada como candidato, servimos la página con los datos de la CURP precargados
			if (isLogged(request.getSession())){
	
				UsuarioWebVO usuarioFirmado = getUsuario(request.getSession());
				
				if (Utils.toLong(usuarioFirmado.getIdTipoUsuario()) == Constantes.TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()){
	
			        SecutityDelegate service = SecutityDelegateImpl.getInstance();
	
			        CandidatoVo candidato = service.consultaCandidato(usuarioFirmado.getIdPropietario());		        

			        registroCandidatoForm.setIdCandidato(candidato.getIdCandidato());			        
			        registroCandidatoForm.setCurp(candidato.getCurp());
			        registroCandidatoForm.setEditCurp(false);
	
				}  else {
					// si el usuario loggeado no es candidato redireccionamos al home porque no se puede servir esta página
			        logger.info("El usuario no ha iniciado sesión");
					return getForwardHome(mapping, request);
				}
			}
			else {
				// si el usuario loggeado no es candidato redireccionamos al home porque no se puede servir esta página				
		        logger.info("El usuario no ha iniciado sesión");
				return getForwardHome(mapping, request);
			}
			
			CaptchaLogicoObject captcha = getCaptcha(request);

			captcha.obtieneDesafioCaptcha();
			
			request.getSession().setAttribute(CAPTCHA_IN_SESSION, captcha);
			
			registroCandidatoForm.setPregunta(captcha.getPregunta());
			
			registroCandidatoForm.setRespuestaUsuario(new String());

		} catch (Exception e){

			// si el usuario loggeado no es candidato redireccionamos al home porque no se puede servir esta página
			return getForwardHome(mapping, request);			
		}

		ActionForward forward = getForward(mapping, request, FORDWARD_CURPNSS_CANDREG);
		
		return forward;
	}
	
	private ActionForward getForward(ActionMapping mapping, HttpServletRequest request, String forwardName) {
		HttpSession session = request.getSession();
		session.setAttribute(BODY_JSP, mapping.findForward(forwardName).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro para candidatos en PPC");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Registro para candidatos en PPC, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ request.getRequestURI().toString());
        return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO);
	}
	
	private ActionForward getForwardHome(ActionMapping mapping, HttpServletRequest request) {		
    	PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlredirect = properties.getProperty("app.swb.redirect.home");
		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		ActionForward forward = mapping.findForward(FORWARD_REDIRECT_SWB);
		return forward;
	}

	public void cambiaPregunta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {	
		String pregunta = null;
		try {
			CaptchaLogicoObject captcha = getCaptcha(request);
			captcha.obtieneDesafioCaptcha();
			
			RegistroCandidatoPPCSDForm registroCandidatoForm = (RegistroCandidatoPPCSDForm) form;

			registroCandidatoForm.setPregunta(captcha.getPregunta());
			registroCandidatoForm.setRespuestaUsuario(new String());
			
			pregunta = captcha.getPregunta();
			redirectJsonResponse(response, pregunta);
		} catch (mx.gob.stps.portal.exception.BusinessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}	
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
	
	private void showBuscadorOfertas(HttpServletRequest request){
		request.setAttribute(SHOW_BUSCADOR_OFERTAS, "true");
	}
	
	public ActionForward verificaCurpNss(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try{
			
			String result = new String();
			final String resultErroneo = new String("{\"result\":\"-1\"}");

			try{
			
				CandidatoBusDelegate delegate = CandidatoBusDelegateImpl.getInstance();
				RESPUESTA_IMMS_CONSULTA_NSS respuestaIMMS = delegate.consultaNssIMMS(((RegistroCandidatoPPCSDForm)form).getCurp(), ((RegistroCandidatoPPCSDForm)form).getNss());
				
				if (RESPUESTA_IMMS_CONSULTA_NSS.SERVICIO_NO_DISPONIBLE.equals(respuestaIMMS)){
					result = "4";
					
				} else if (RESPUESTA_IMMS_CONSULTA_NSS.NSS_NO_REGISTRADO.equals(respuestaIMMS)){
					result = "5";
					
				} else if (RESPUESTA_IMMS_CONSULTA_NSS.NSS_REGISTRADO.equals(respuestaIMMS)){
					
					// Consultamos el estatus del registro de PPC en la base de datos y el idCandidato si existe								
					CandidatosRegistroBusDelegate delegateCandidato = CandidatosRegistroBusDelegateImpl.getInstance();					
					CandidatoVo candidatoVo = delegateCandidato.consultaCandidatoPorCURP(((RegistroCandidatoPPCSDForm)form).getCurp());
					
					ESTATUS estatusPPCRegistrado = null;
					
					if (candidatoVo != null && candidatoVo.getIdCandidato() > 0L){
						estatusPPCRegistrado = ESTATUS.getEstatus(candidatoVo.getPpcEstatus()); 
					}
					
					if (estatusPPCRegistrado == null){
						result = "1";
						
					} else if (ESTATUS.NO_INSCRITO_PPC.equals(estatusPPCRegistrado)){
						result = "1";
						
					} else if (ESTATUS.ACTIVO_PPC.equals(estatusPPCRegistrado)){
						result = "2";
		
					} else if (ESTATUS.INACTIVO_PPC.equals(estatusPPCRegistrado)){								
						result = "2";
		
					} else if (ESTATUS.FUERA_PPC.equals(estatusPPCRegistrado)){								
						result = "3";
						
					} else {
						logger.error("Respuesta de isRegistradoPPC "+(estatusPPCRegistrado != null ? estatusPPCRegistrado.getIdOpcion() : "null"));						
						result = resultErroneo;
					}
				} else {
					logger.error("Respuesta de consultaNssIMMS "+(respuestaIMMS != null ? respuestaIMMS.getIdOpcion() : "null"));
					result = resultErroneo;
				}

			} catch (Exception e){
				logger.error("Ha ocurrido un error en RegistroCandidatoPPCSDAction.verificaCurpNss");				
				e.printStackTrace();
				result = resultErroneo;
			}

			String json = toJson(new String("{\"result\" : \""+result+"\", \"idCandidato\" : \""+((RegistroCandidatoPPCSDForm)form).getIdCandidato()+"\"}"));

			redirectJsonResponse(response, json);
		
		} catch (Exception e){
			logger.error("Ha ocurrido un error en RegistroCandidatoPPCSDAction.verificaCurpNss");
			e.printStackTrace();
		}
		
		return null;
	}

	public ActionForward sendTerminosCondiciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try{
			
			long idCandidato = ((RegistroCandidatoPPCSDForm)form).getIdCandidato();
			int ppcAceptacionTerminos = Integer.valueOf(((RegistroCandidatoPPCSDForm)form).getAceptacionTerminos()).intValue();		
			Date ppcFechaInscripcion = new Date();
			String nss = ((RegistroCandidatoPPCSDForm)form).getNss();
			
			//srojas
			Integer ppcEstatusIMSS = null;
			Date ppcFechaBajaIMSS = null; 
			String ppcTipoContratoIMSS = null;		
			//srojas
	
			CandidatoBusDelegate delegate = CandidatoBusDelegateImpl.getInstance();
			
			int ppcEstatus = delegate.actualizaRegistroPPC(idCandidato, ppcFechaInscripcion, ppcAceptacionTerminos, ppcEstatusIMSS, ppcFechaBajaIMSS, ppcTipoContratoIMSS, nss);
			
			// Actualizamos en sesión el objeto en sesión que indica que está inscrito al PPC
			try {
				CandidatoAjaxVO candidatoAjaxVO = (CandidatoAjaxVO)request.getSession().getAttribute(CANDIDATO_HEAD);
				candidatoAjaxVO.setPpcEstatusIdOpcion(String.valueOf(ppcEstatus));
			} catch (Exception e){
				logger.error("Error al actualizar el ppcEstatus en sesión");
				e.printStackTrace();
			}

			String goToAction = null;
			
			if (ACEPTACION_TERMINOS.SI.getIdOpcion() == ppcAceptacionTerminos){
				// Enviamos el correo de que se ha inscrito al PPC
				try{
					delegate.enviaNotificacionInscripcionPPC(idCandidato);
					
				} catch (Exception e){
					logger.error("Error en RegistroCandidatoPPCSDAction.sendTerminosCondiciones al enviar la notificación de inscripción al PPC");
					e.printStackTrace();					
				}
				
				goToAction = "/perfil.do?method=init";
			} else{
				goToAction = "/miEspacioCandidato.do";
				
			}
			
			response.sendRedirect(request.getContextPath()+goToAction);
			return null;
			
		} catch(Exception e){
			e.printStackTrace();
			logger.error("Ha ocurrido un error en sendTerminosCondiciones");
			return mapping.findForward(FORDWARD_ERRORPAGE);
		}
	}

	public ActionForward checkRespuesta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		try{
			CaptchaLogicoObject captcha = (CaptchaLogicoObject)request.getSession().getAttribute(CAPTCHA_IN_SESSION);
			
			String result = null;			

			if (captcha != null && captcha.isRespuestaCorrecta(((RegistroCandidatoPPCSDForm)form).getRespuestaUsuario())){
				result = new String("{\"result\":\"true\"}");
			} else {
				result = new String("{\"result\":\"false\"}");
			}

			String json = toJson(result);
	
			redirectJsonResponse(response, json);
			return null;
			
		} catch (Exception e){
			logger.error("Ha ocurrido un error en RegistroCandidatoPPCSDAction.verificaCurpNss");
			e.printStackTrace();
			return null;
		}
	}
	
}
