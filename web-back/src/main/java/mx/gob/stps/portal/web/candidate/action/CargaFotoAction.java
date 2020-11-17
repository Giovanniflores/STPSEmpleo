/**
 * 
 */
package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LABEL_BOTON;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LABEL_CANCEL;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LABEL_REGRESAR;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.EJBTransactionRolledbackException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.CargaFotoForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * 
 * @author jose.jimenez
 */
public class CargaFotoAction extends GenericAction {
	
	private static Logger logger = Logger.getLogger(CargaFotoAction.class);
	
	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.infra.action.GenericAction#init(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CargaFotoForm fotoForm = (CargaFotoForm) form;
		fotoForm.reset();
		request.setAttribute(LABEL_BOTON, LABEL_CANCEL);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Cargar foto");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Cargar foto, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
	    return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}

	/**
	 * Obtiene los datos capturados e invoca el registro de los mismos. Redireccionando
	 * el flujo a la p&aacute;gina configurada para confirmar el almacenamiento de
	 * la fotograf&iacute; o para mostrar los errores encontrados en los datos recibidos.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return un objeto {@code ActionForward} que indica el flujo de informaci&oacute;n a seguir
	 */
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CargaFotoForm fotoForm = (CargaFotoForm) form;		
		String msg = null;
		byte[] foto = null;
		String nextAction = null;		
		ActionErrors errors = fotoForm.validate(mapping, request);
		String method = request.getParameter("method");
		if (errors == null || (errors != null && errors.isEmpty())) { // Sin errores de datos
			try {
				UsuarioWebVO usuario = this.getUsuario(request.getSession());

				InputStream stream = fotoForm.getArchivoFoto().getInputStream();

				int fileLength = stream.available();
				foto = new byte[fileLength];
				int bytesRead = stream.read(foto);
				
				if (bytesRead > 0) {
					CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
					services.guardarFoto(usuario, foto);
					ActionMessages messages = new ActionMessages();
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("can.foto.cargada.msg"));
					saveMessages(request, messages);
					nextAction = JSP_SUCCESS;
					fotoForm.setMessage("La fotografía se almacenó exitosamente.");
					request.setAttribute(LABEL_BOTON, LABEL_REGRESAR);
				}
			} catch (ServiceLocatorException e) {
				logger.error(e);
				if (errors == null) {errors = new ActionErrors();}
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "app.exp.tecnica.err"));
				fotoForm.setMessage("No se pudo leer el contenido del archivo. Por favor intente más tarde");
			} catch (BusinessException e) {
				logger.error(e);				
				if (errors == null) {errors = new ActionErrors();}
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", e.getMessage()));
				fotoForm.setMessage("No se pudo leer el contenido del archivo. Por favor intente más tarde");
			} catch (IOException ioe) {
				logger.error(msg, ioe);
				if (errors == null) {errors = new ActionErrors();}
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", "can.foto.IOError.err"));
				fotoForm.setMessage("No se pudo leer el contenido del archivo. Por favor intente más tarde");
			}catch (EJBTransactionRolledbackException ex){
				if (errors == null) {errors = new ActionErrors();}
				if(ex.getMessage().contains("No existe registro")){
					errors.add("archivoFoto", new ActionMessage("can.foto.sinPerfil.err"));		
					fotoForm.setMessage("Favor de capturar primero la información del perfil laboral.");
				}
			}
		}
		
		if (errors != null && !errors.isEmpty()) {
			request.setAttribute(Globals.ERROR_KEY, errors);
			request.setAttribute(LABEL_BOTON, LABEL_CANCEL);
		}
		
		if (nextAction == null) {
			nextAction = FORWARD_JSP;
		}
		if (null != method && "registrar".equalsIgnoreCase(method))
			request.getSession().setAttribute("modal", getMessage(fotoForm.getMessage()));
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Cargar foto");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Cargar foto, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
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