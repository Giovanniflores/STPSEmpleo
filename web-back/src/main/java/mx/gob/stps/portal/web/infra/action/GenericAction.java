package mx.gob.stps.portal.web.infra.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.SIN_ARTICULOS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.web.infra.utils.DateConverter;
import mx.gob.stps.portal.web.infra.utils.StringConverterDate;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.MessageResources;

/**
 * Clase GenericAction
 * 
 * Plantilla para los recursos Action de la aplicacion.
 * 
 * @author oscar.manzo
 *
 */
public abstract class GenericAction extends DispatchAction {
	
	static {
		DateConverter dateConverter = new DateConverter();
		dateConverter.setFormatPattern(Constantes.formatDateForma);
		StringConverterDate myStringConverter = new StringConverterDate();
		myStringConverter.setFormatPattern(Constantes.formatDateForma);
		ConvertUtils.register(dateConverter, java.util.Date.class);
		ConvertUtils.register(myStringConverter, String.class);
	}

	public abstract ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Indica si se encuentra firmado el usuario
	 * @param session
	 * @return
	 */
	protected boolean isLogged(HttpSession session){
		return getUsuario(session)!=null;
	}
	
	/**
	 * Obtiene el usuario firmado en el sistema
	 * @param session
	 * @return instancia de <mx.gob.stps.portal.web.security.vo.UsuarioWebVO>
	 */
	protected UsuarioWebVO getUsuario(HttpSession session){
		UsuarioWebVO usuario = (UsuarioWebVO)session.getAttribute(USUARIO_APP);
		
		return usuario;
	}

	protected void registraMensaje(HttpServletRequest request, String mensaje){
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(mensaje));

		saveMessages(request, messages);
	}
	
	protected void registraMensaje(HttpServletRequest request, String mensaje, String param01){
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(mensaje, param01));

		saveMessages(request, messages);
	}
	
	/**
	 * Registra el mensaje de error para su despliegue en la pagina
	 * @param request
	 * @param mensaje Mensaje de error a desplegar
	 * @param param01 Parametro del mensaje {0}
	 */
	protected void registraError(HttpServletRequest request, String mensaje, String param01){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(mensaje, param01));
		saveErrors(request, errors);
	}

	/**
	 * Registra una lista de mensajes de error para su despliegue en la pagina
	 * @param request
	 * @param mensajes Lista de mensajes
	 */
	protected void registraError(HttpServletRequest request, String... mensajes){
		ActionErrors errors = new ActionErrors();

		if (mensajes!=null){
			for (String msg : mensajes){
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));		
			}
		}

		saveErrors(request, errors);
	}
	
	/**
	 * Registra el mensaje de error para su despliegue en la pagina
	 * @param request
	 * @param mensaje Mensajes de error a desplegar
	 */
	protected void registraError(HttpServletRequest request, String mensaje){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(mensaje));
		saveErrors(request, errors);
	}

	/**
	 * Obtiene un mensaje correspondiente a una llave del archivo de propiedades
	 * @param request
	 * @param key
	 * @return
	 */
	protected String getMensaje(HttpServletRequest request, String key){
		MessageResources resources = (MessageResources)request.getAttribute(Globals.MESSAGES_KEY);
		
		String mensaje = resources.getMessage(key);
		
		return mensaje;
	}

	protected String getMensaje(HttpServletRequest request, String key, String... args){
		MessageResources resources = (MessageResources)request.getAttribute(Globals.MESSAGES_KEY);
		
		String mensaje = resources.getMessage(key, args);
		
		return mensaje;
	}
	
	/**
	 * Transforma una lista de objetos a su equivalente JSON.
	 *
	 * @param list the list
	 * @return the string
	 */
	protected String toJson(List<?> list){
		return Utils.toJson(list);
	}

	/**
	 * Transforma objeto a su equivalente JSON.
	 *
	 * @param obj the obj
	 * @return the string
	 */
	protected String toJson(Object obj){
		return Utils.toJson(obj);
	}
	
	/**
	 * Redirecciona la respuesta enviando la salida JSON.
	 *
	 * @param response the response
	 * @param json the json
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void redirectJsonResponse(HttpServletResponse response, String json) throws IOException{
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
		response.flushBuffer();
	}

	/**
	 * Genera un objeto de CatalogoVO para desplegar los controles Select de Dojo de acuerdo a las especificaciones del framework
	 * @param opciones
	 * @return
	 */
	protected CatalogoVO getCatalogo(List<CatalogoOpcionVO> opciones){
		return Utils.getCatalogo(opciones);
		
	}

	/**
	 * Establece la bandera para ocultar el banner de articulos en el espacio del candidato y empresa
	 * @param request
	 */
	protected void ocultaBarraArticulos(HttpServletRequest request){
		request.setAttribute(SIN_ARTICULOS, "true");
	}
	
}