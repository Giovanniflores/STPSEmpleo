package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.web.company.delegate.CompanyBusDelegate;
import mx.gob.stps.portal.web.company.delegate.CompanyBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.UploadLogoForm;
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

public class UploadLogoAction extends GenericAction {
	private static Logger logger = Logger.getLogger(UploadLogoAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UploadLogoForm logoForm = (UploadLogoForm)form;
		logoForm.reset();
		request.setAttribute(LABEL_BOTON, LABEL_CANCEL);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Cambiar logotipo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}

	/**
	 * Obtiene los datos capturados e invoca el registro de los mismos. Redireccionando
	 * el flujo a la p&aacute;gina configurada para confirmar el almacenamiento del
	 * logotipo o para mostrar los errores encontrados en los datos recibidos.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return un objeto {@code ActionForward} que indica el flujo de informaci&oacute;n a seguir
	 */
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UploadLogoForm logoForm = (UploadLogoForm) form;

		byte[] logo = null;
		String nextAction = FORWARD_JSP;
		String method = request.getParameter("method");
		ActionErrors errors = logoForm.validate(mapping, request);

		if (errors == null || errors.isEmpty()) { // Sin errores de datos
			
			try {
				UsuarioWebVO usuario = getUsuario(request.getSession());
	
				InputStream stream = logoForm.getLogoFile().getInputStream();
				int fileLength = stream.available();
	
				logo = new byte[fileLength];
				int bytesRead = stream.read(logo);
				
				if (bytesRead > 0) {
					CompanyBusDelegate services = CompanyBusDelegateImpl.getInstance();	
					services.updateLogo(usuario, logo);	
					logoForm.setMessage(this.getMensaje(request, "emp.logo.cargado.msg"));
					ActionMessages messages = new ActionMessages();
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("emp.logo.cargado.msg"));
					
					saveMessages(request, messages);
					
					nextAction = JSP_SUCCESS;
					request.setAttribute(LABEL_BOTON, LABEL_REGRESAR);
				}
				
			} catch (ServiceLocatorException e) {
				if (errors == null) errors = new ActionErrors();
				logoForm.setMessage(this.getMensaje(request, "can.foto.IOError.err"));
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "app.exp.tecnica.err"));
				logger.error(e);
			} catch (BusinessException e) {
				if (errors == null) errors = new ActionErrors();
				logoForm.setMessage(this.getMensaje(request, "can.foto.IOError.err"));
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(e.getMessage()));
				logger.error(e);
			} catch (IOException ioe) {
				if (errors == null) errors = new ActionErrors();
				logoForm.setMessage(this.getMensaje(request, "can.foto.IOError.err"));
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("can.foto.IOError.err"));
				logger.error(ioe);
			}
		} else {
			request.setAttribute(Globals.ERROR_KEY, errors);
			request.setAttribute(LABEL_BOTON, LABEL_CANCEL);
		}
		if (null != method && "registrar".equalsIgnoreCase(method))
			request.getSession().setAttribute("modal", getMessage(logoForm.getMessage()));
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Subir logotipo empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Subir logotipo empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
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
