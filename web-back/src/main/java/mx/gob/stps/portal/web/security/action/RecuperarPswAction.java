package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.form.RecuperarPswForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RecuperarPswAction extends GenericAction {
	private static final Logger logger = Logger.getLogger(RecuperarPswAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RecuperarPswForm pswForm = (RecuperarPswForm)form;
		agregandoTipo(pswForm);
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Recuperar Contrase&ntilde;a");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}

	public ActionForward recuperaPswCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RecuperarPswForm pswForm = (RecuperarPswForm)form;
		
		String msg 	= "";
		String type = ResultVO.TYPE_ERROR;;
		String json	= "";
		
		try {
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			String correoFinal = services.recuperaContrasenaCandidato(pswForm.getUsuario(), pswForm.getCurp(), pswForm.getCorreoActual(), pswForm.getCorreoNuevo());

			logger.info("Se cambio CONTRASEÑA Candidato:" + pswForm.getCurp() + " correo :" + correoFinal);										

			msg = getMensaje(request, "seguridad.passw.recupera.notificacion", correoFinal);
			type = ResultVO.TYPE_SUCCESS;

		}  catch (LoginRepetidoException e) {			
			e.printStackTrace(); logger.error(e);
			msg  = "La cuenta de correo "+ pswForm.getCorreoNuevo() +" ya se encuentra registrada, favor de indicar una diferente.";
		} catch (ServiceLocatorException e) {				
			e.printStackTrace(); logger.error(e);
			msg  = "No se pudo establecer la comunicación con los servicios del Portal, favor de intentar mas tarde.";
		}catch (TechnicalException e) {
			e.printStackTrace(); logger.error(e);
			msg  = "Se ha detectado un error en la aplicación, favor de notificar al administrador.";
		}catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			msg  = e.getMessage();
		}catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);				
			msg  = "Se han detectado error en los datos de la cuenta, favor de notificar al administrador.";
		}catch (MailException e) {
			e.printStackTrace(); logger.error(e);
			msg  = "Por el momento no se pudo enviar el correo, favor de intentar mas tarde.";
		}catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			msg  = "Se ha detectado un error en la aplicación, favor de notificar al administrador.";
		}
	
		try {
			json = toJson(new ResultVO(msg, type));				
			redirectJsonResponse(response, json);			
			return null;
		} catch (IOException e) {
			logger.error(e);
		}
			
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Recuperar Contrase&ntilde;a de Candidato");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}

	public ActionForward recuperaPswEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RecuperarPswForm pswForm = (RecuperarPswForm)form;
		
		String msg 	= "";
		String type = ResultVO.TYPE_ERROR;
		String json	= "";
	
		try {
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			String correoFinal = services.recuperaContrasenaEmpresa(pswForm.getUsuario(), pswForm.getIdPortalEmpleo(), pswForm.getCorreoActual(), pswForm.getCorreoNuevo());

			logger.info("Se cambio CONTRASEÑA Empresa:" + pswForm.getIdPortalEmpleo() + " correo :" + correoFinal);
			
			msg = getMensaje(request, "seguridad.passw.recupera.notificacion", correoFinal);
			type = ResultVO.TYPE_SUCCESS;

		}catch (LoginRepetidoException e) {			
			e.printStackTrace(); logger.error(e);
			msg  = "La cuenta de correo "+ pswForm.getCorreoNuevo() +" ya se encuentra registrada, favor de indicar una diferente.";
		}catch (ServiceLocatorException e) {				
			e.printStackTrace(); logger.error(e);
			msg  = "No se pudo establecer la comunicación con los servicios del Portal, favor de intentar mas tarde.";
		}catch (TechnicalException e) {
			e.printStackTrace(); logger.error(e);
			msg  = "Se ha detectado un error en la aplicación, favor de notificar al administrador.";
		}catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			msg  = e.getMessage();
		}catch (SQLException e) {
			e.printStackTrace(); logger.error(e);
			msg  = "Se ha detectado un error en la aplicación, favor de notificar al administrador.";
		}catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);				
			msg  = "Se han detectado error en los datos de la cuenta, favor de notificar al administrador.";
		}catch (MailException e) {
			e.printStackTrace(); logger.error(e);
			msg  = "Por el momento no se pudo enviar el correo, favor de intentar mas tarde.";
		}catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			msg  = "Se ha detectado un error en la aplicación, favor de notificar al administrador.";
		}
	
		try {
			json = toJson(new ResultVO(msg, type));			
			redirectJsonResponse(response, json);			
			return null;
		} catch (IOException e) {
				logger.error(e);
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Recuperar Contrase&ntilde;a de Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
		
	/** Encazo de con recibir algun valor correcto 
	 * ponemos candidato por default
	 * @param pswForm
	 */
	private void agregandoTipo(RecuperarPswForm pswForm){
		if(pswForm.getTipo() == null){
			pswForm.setTipo("SinTipo");
		
		} else if(!("Empresa".equals(pswForm.getTipo()) || "Candidato".equals(pswForm.getTipo()) )) {
			pswForm.setTipo("SinTipo");
		}
		
	}

}
