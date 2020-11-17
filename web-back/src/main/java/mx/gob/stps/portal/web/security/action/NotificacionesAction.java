package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.form.NotificacionesForm;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class NotificacionesAction extends GenericAction {
	private static Logger logger = Logger.getLogger(NotificacionesAction.class);
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
        return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}

	public ActionForward notificaRegistroEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		NotificacionesForm dataForm = (NotificacionesForm)form;
		String msg = "";
		String type = ResultVO.TYPE_SUCCESS;
		
		String usuario = dataForm.getUsuario();

		SecutityDelegate services = SecutityDelegateImpl.getInstance();

		try{
			if (usuario!=null && !usuario.isEmpty()){
				//if (Utils.validaMail(correoElectronico)){
					services.notificaRegistroEmpresa(usuario);
					
					type = ResultVO.TYPE_SUCCESS;
					msg = getMensaje(request, "notificaciones.envio.correo.exito", usuario);
				/*} else {
					type = ResultVO.TYPE_ERROR;
					msg = getMensaje(request, "notificaciones.mail.error.formato", correoElectronico);
				}*/
			} else if (dataForm.getArchivoUsuarios()!=null){
				InputStream in = dataForm.getArchivoUsuarios().getInputStream();
				List<String> usuarios = cargaUsuarios(in);
				
				if (correosValidos(usuarios)){
					services.notificaRegistroEmpresa(usuarios);
					
					type = ResultVO.TYPE_SUCCESS;
					msg = getMensaje(request, "notificaciones.envio.lista.exito", String.valueOf(usuarios.size()));
				} else {
					type = ResultVO.TYPE_ERROR;
					msg = getMensaje(request, "notificaciones.mail.list.error");
				}
			} else {
				type = ResultVO.TYPE_ERROR;
				msg = getMensaje(request, "notificaciones.mail.error.sindatos");
			}
		} catch (PersistenceException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.PersistenceException");
		} catch (BusinessException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage());
		} catch (TechnicalException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.TechnicalException");
		} catch (MailException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.MailException");
		} catch (EncodingException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.EncodingException");
		} catch (ServiceLocatorException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.ServiceLocatorException");
		} catch (Exception e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.Exception");
		}

		return returnResult(mapping, dataForm, request, response, msg, type);
	}

	public ActionForward notificaRegistroCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		NotificacionesForm dataForm = (NotificacionesForm)form;
		String msg = "";
		String type = ResultVO.TYPE_SUCCESS;
		
		String usuario = dataForm.getUsuario();

		SecutityDelegate services = SecutityDelegateImpl.getInstance();

		try{
			if (usuario!=null && !usuario.isEmpty()){
				//if (Utils.validaMail(correoElectronico)){
					services.notificaRegistroCandidato(usuario);

					type = ResultVO.TYPE_SUCCESS;
					msg = getMensaje(request, "notificaciones.envio.correo.exito", usuario);
				/*} else {
					type = ResultVO.TYPE_ERROR;
					msg = getMensaje(request, "notificaciones.mail.error.formato", usuario);	
				}*/
			} else if (dataForm.getArchivoUsuarios()!=null){
				InputStream in = dataForm.getArchivoUsuarios().getInputStream();
				List<String> usuarios = cargaUsuarios(in);
				
				if (correosValidos(usuarios)){
					services.notificaRegistroCandidato(usuarios);
					
					type = ResultVO.TYPE_SUCCESS;
					msg = getMensaje(request, "notificaciones.envio.lista.exito", String.valueOf(usuarios.size()));
				} else {
					type = ResultVO.TYPE_ERROR;
					msg = getMensaje(request, "notificaciones.mail.list.error");
				}
			} else {
				type = ResultVO.TYPE_ERROR;
				msg = getMensaje(request, "notificaciones.mail.error.sindatos");
			}
		} catch (PersistenceException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.PersistenceException");
		} /*catch (BusinessException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage());
		} catch (TechnicalException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.TechnicalException");
		}*/ catch (MailException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.MailException");
		} catch (EncodingException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.EncodingException");
		} catch (ServiceLocatorException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.ServiceLocatorException");
		} catch (Exception e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.Exception");
		}

		return returnResult(mapping, dataForm, request, response, msg, type);
	}
	
	public ActionForward notificaRecuperaContrasenaEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		NotificacionesForm dataForm = (NotificacionesForm)form;
		String msg = "";
		String type = ResultVO.TYPE_SUCCESS;

		String usuario = dataForm.getUsuario();

		SecutityDelegate services = SecutityDelegateImpl.getInstance();

		try{
			if (usuario!=null && !usuario.isEmpty()){
				//if (Utils.validaMail(correoElectronico)){
					services.notificaRecuperaContrasenaEmpresa(usuario);
					
					type = ResultVO.TYPE_SUCCESS;
					msg = getMensaje(request, "notificaciones.envio.correo.exito", usuario);
				/*} else {
					type = ResultVO.TYPE_ERROR;
					msg = getMensaje(request, "notificaciones.mail.error.formato", usuario);	
				}*/
			} else if (dataForm.getArchivoUsuarios()!=null){
				InputStream in = dataForm.getArchivoUsuarios().getInputStream();
				List<String> usuarios = cargaUsuarios(in);
				
				if (correosValidos(usuarios)){
					services.notificaRecuperaContrasenaEmpresa(usuarios);
					
					type = ResultVO.TYPE_SUCCESS;
					msg = getMensaje(request, "notificaciones.envio.lista.exito", String.valueOf(usuarios.size()));
				} else {
					type = ResultVO.TYPE_ERROR;
					msg = getMensaje(request, "notificaciones.mail.list.error");
				}
			} else {
				type = ResultVO.TYPE_ERROR;
				msg = getMensaje(request, "notificaciones.mail.error.sindatos");
			}
		} catch (PersistenceException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.PersistenceException");
		} catch (BusinessException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage());
		} catch (TechnicalException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.TechnicalException");
		} catch (MailException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.MailException");
		} /*catch (EncodingException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.EncodingException");
		}*/ catch (ServiceLocatorException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.ServiceLocatorException");
		} catch (Exception e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.Exception");
		}

		return returnResult(mapping, dataForm, request, response, msg, type);
	}

	public ActionForward notificaRecuperaContrasenaCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		NotificacionesForm dataForm = (NotificacionesForm)form;
		String msg = "";
		String type = ResultVO.TYPE_SUCCESS;

		String usuario = dataForm.getUsuario();

		SecutityDelegate services = SecutityDelegateImpl.getInstance();
		
		try {
			if (usuario!=null && !usuario.isEmpty()){
				//if (Utils.validaMail(correoElectronico)){
					services.notificaRecuperaContrasenaCandidato(usuario);
					
					type = ResultVO.TYPE_SUCCESS;
					msg = getMensaje(request, "notificaciones.envio.correo.exito", usuario);
				/*} else {
					type = ResultVO.TYPE_ERROR;
					msg = getMensaje(request, "notificaciones.mail.error.formato", usuario);	
				}*/
			} else if (dataForm.getArchivoUsuarios()!=null){
				InputStream in = dataForm.getArchivoUsuarios().getInputStream();
				List<String> usuarios = cargaUsuarios(in);
				
				if (correosValidos(usuarios)){
					services.notificaRecuperaContrasenaCandidato(usuarios);
					
					type = ResultVO.TYPE_SUCCESS;
					msg = getMensaje(request, "notificaciones.envio.lista.exito", String.valueOf(usuarios.size()));
				} else {
					type = ResultVO.TYPE_ERROR;
					msg = getMensaje(request, "notificaciones.mail.list.error");
				}
			} else {
				type = ResultVO.TYPE_ERROR;
				msg = getMensaje(request, "notificaciones.mail.error.sindatos");
			}
		} catch (PersistenceException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.PersistenceException");
		} catch (BusinessException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage());
		} catch (TechnicalException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.TechnicalException");
		} catch (MailException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.MailException");
		} /*catch (EncodingException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.EncodingException");
		} */catch (ServiceLocatorException e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.ServiceLocatorException");
		} catch (Exception e){
			e.printStackTrace(); logger.error(e);
			type = ResultVO.TYPE_ERROR;
			msg = getMensaje(request, "notificaciones.exception.Exception");
		}
		
		return returnResult(mapping, dataForm, request, response, msg, type);
	}

	private List<String> cargaUsuarios(InputStream input) throws IOException {
		List<String> correos = new ArrayList<String>();
		
		if (input==null) throw new IllegalArgumentException("Referencia a datos no proporcionada");
		
		InputStreamReader reader = new InputStreamReader(input);
		BufferedReader in = new BufferedReader(reader);
		
		String line = null;
		while ((line = in.readLine())!= null){
			
			line = line.trim();
			
			if (!line.isEmpty()){
				correos.add(line);	
			}
		}

		in.close();
		
		return correos;
	}

	private boolean correosValidos(List<String> correos){

		if (correos==null || correos.isEmpty()) return false;
		
		/*for (String correo : correos){
			if (!Utils.validaMail(correo)){
				return false;
			}
		}*/

		return true;
	}
	
	private ActionForward returnResult(ActionMapping mapping, NotificacionesForm dataForm, HttpServletRequest request, HttpServletResponse response, String msg, String type) {
		ResultVO resultVO = new ResultVO(msg, type);
		
		request.setAttribute("ResultVO", resultVO);
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);			
	}
	
}
