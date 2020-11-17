package mx.gob.stps.portal.movil.web.infra.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.USERLOGGED;
import mx.gob.stps.portal.movil.web.infra.utils.Utils;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;

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
	
	protected static final String MODO_PAGINA = "MODO_PAGINA";
	protected static final String MODO_ALTA = "MODO_ALTA";
	protected static final String MODO_EDICION = "MODO_EDICION";
	protected static final String MODO_DETALLE = "MODO_DETALLE";
	
	public abstract ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * Indica si se encuentra firmado el usuario
	 * @param session
	 * @return
	 */
	protected boolean isLogged(HttpSession session){
		return getUsuarioFirmado(session)!=null;
	}
	
	/**
	 * Obtiene el usuario firmado en el sistema
	 * @param session
	 * @return instancia de <mx.gob.stps.portal.web.security.vo.UsuarioWebVO>
	 */
	protected UsuarioFirmadoVO getUsuarioFirmado(HttpSession session){
		UsuarioFirmadoVO usuario = (UsuarioFirmadoVO)session.getAttribute(USERLOGGED);
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
		if (json==null) json = "";
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
		response.flushBuffer();
	}


}