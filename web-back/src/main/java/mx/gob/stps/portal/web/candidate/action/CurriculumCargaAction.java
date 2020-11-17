package mx.gob.stps.portal.web.candidate.action;


import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LABEL_BOTON;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LABEL_CANCEL;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LABEL_REGRESAR;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import javax.ejb.EJBTransactionRolledbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.candidate.vo.CurriculumVo;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.CurriculumCargaForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.MessageResources;

/**
 * 
 * @author jose.jimenez
 */

public class CurriculumCargaAction extends GenericAction {

	private static Logger logger = Logger.getLogger(CurriculumCargaAction.class);
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CurriculumCargaForm currForm = (CurriculumCargaForm) form;
		currForm.reset();
		UsuarioWebVO usuario = getUsuario(request.getSession());
		long idCandidato = Utils.toLong(usuario.getIdPropietario());
	    logger.info("JGLC sube a sesion idCanEstiloCv en video CV :" + idCandidato);
	    request.getSession().setAttribute("idCanEstiloCv", idCandidato);
		request.setAttribute(LABEL_BOTON, LABEL_CANCEL);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Subir video curr&iacute;culum");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}

	/**
	 * Obtiene los datos capturados e invoca el registro de los mismos
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		CurriculumCargaForm currForm = (CurriculumCargaForm) form;
		String nextAction = null;
		
		ActionErrors errors = currForm.validate(mapping, request);
		MessageResources messageResources = getResources(request);
		
		if (errors == null || (errors != null && errors.isEmpty())) { // Sin errores de datos
			try {
				UsuarioWebVO usuario = this.getUsuario(request.getSession());
				CurriculumVo vo = new CurriculumVo();
					
				try {
					PropertyUtils.copyProperties(vo, currForm);
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
				services.actualizarCurriculum(usuario, vo);
				
				ActionMessages messages = new ActionMessages();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("can.videoCV.guardado.msg"));
				saveMessages(request, messages);
				
				String msg = getMensaje(request, "can.videoCV.guardado.msg");
				request.setAttribute("msg_registro", msg);
				
				nextAction = FORWARD_JSP;
				request.setAttribute(LABEL_BOTON, LABEL_REGRESAR);
			
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
				
				if (errors == null) {
					errors = new ActionErrors();
				}
				
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "app.exp.locator.err"));
				logger.error(messageResources.getMessage("app.exp.locator.err"), e);
			} catch (BusinessException e) {
				e.printStackTrace();
				
				if (errors == null) {
					errors = new ActionErrors();
				}
				
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(e.getMessage()));
				logger.error(messageResources.getMessage(e.getMessage()), e);
			
			}catch (EJBTransactionRolledbackException ex){
				ex.printStackTrace();
				
				if (errors == null) {
					errors = new ActionErrors();
				}
			
				if(ex.getMessage().contains("No existe registro")){
					errors.add("archivoFoto", new ActionMessage("can.foto.sinPerfil.err"));
				}
			}
/*		} else {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("app.general.msg"));*/
		}
		if (errors != null && !errors.isEmpty()) {
			request.setAttribute(Globals.ERROR_KEY, errors);
			request.setAttribute(LABEL_BOTON, LABEL_CANCEL);
			String msg = getMensaje(request, "can.videoCV.videoURL.err");
			request.setAttribute("msg_registro", msg);
		}
		if (nextAction == null) {
			nextAction = FORWARD_JSP;
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(nextAction).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Cargar foto");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Cargar foto, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);

	}
	

	/**
	 * Redirecciona al usuario a la p&aacute;gina configurada en caso de seleccionar
	 * la opci&oacute;n de cancelar la carga de la fotograf&iaacute;
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cancelar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		PropertiesLoader properties = PropertiesLoader.getInstance();

		UsuarioWebVO usuario = super.getUsuario(request.getSession());
		
		if(usuario.getIdPerfil() == PERFIL.CANDIDATO.getIdOpcion())
			request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.miespacio.candidato"));
		
		if(usuario.getIdPerfil() == PERFIL.EMPRESA.getIdOpcion())
			request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.miespacio.empresa"));
		
	 	return mapping.findForward(FORWARD_REDIRECT_SWB);
	}
	
}
