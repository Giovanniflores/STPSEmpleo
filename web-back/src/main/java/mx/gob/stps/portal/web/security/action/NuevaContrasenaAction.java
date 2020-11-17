package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_CANCEL;


import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.persistencia.entity.Solicitante;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.CompanyBusDelegate;
import mx.gob.stps.portal.web.company.delegate.CompanyBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegate;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.utils.fs.EncriptURL;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.filter.CookieManager;
import mx.gob.stps.portal.web.security.form.NuevaContrasenaForm;

public class NuevaContrasenaAction extends GenericAction {

	private static final Logger logger = Logger.getLogger(ActivacionAction.class);
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String jspforward = "";
		EncriptURL ec64 = new EncriptURL();
		jspforward = mapping.findForward(JSP_CANCEL).getPath();
		try {
			String paramValue = "";
			NuevaContrasenaForm nuevacontrasenaForm = (NuevaContrasenaForm) form;
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();
				String[] paramValues = request.getParameterValues(paramName);
				for (int i = 0; i < paramValues.length; i++) {
					paramValue = paramValues[i];
				}
			}
			if (paramValue != null && !paramValue.trim().isEmpty()) {
				SecutityDelegate services = SecutityDelegateImpl.getInstance();
				CandidatoBusDelegate delegate = CandidatoBusDelegateImpl.getInstance();
				DateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
				String cadena = ec64.decode(paramValue);
				String[] params = cadena.split("&");
				String inputDateString = "";
				long idUsuario = 0;
				Date fecha = null;
				for (int i = 0; i < params.length; i++) {
					if (params[i].contains("idUsuario")) {
						String idUserlong = params[i].replaceAll("idUsuario=", "");
						idUsuario = Long.parseLong(idUserlong);
					} else {
						inputDateString = params[i].replaceAll("fecha=", "");
						fecha = df.parse(inputDateString);
					}
				}
				if (CompararDia(fecha)) {
					UsuarioVO usuario = services.consultaUsuario(idUsuario);
					long idCandidato = services.consultaPropietario(idUsuario);
					if (usuario.getIdTipoUsuario() != TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
						Solicitante solicitante = delegate.obtenerSolicitante(idCandidato);
						nuevacontrasenaForm.setIdTipoUsuario(usuario.getIdTipoUsuario());
						nuevacontrasenaForm.setIdCandidato(idCandidato);
						nuevacontrasenaForm.setNombre(solicitante.getNombre() + " " + solicitante.getApellido1() + " "
								+ solicitante.getApellido2());
						nuevacontrasenaForm.setIdUsuario(idUsuario);
					} else {
						CompanyBusDelegate empresadelegate = CompanyBusDelegateImpl.getInstance();
						EmpresaRegistroBusDelegate empresabus = EmpresaRegistroBusDelegateImpl.getInstance();
						EmpresaVO empresavo = empresadelegate.findEmpresaByIdUsuario(idUsuario);
						empresavo = empresabus.consultaEmpresa(empresavo.getIdEmpresa());
						nuevacontrasenaForm.setNombre(empresavo.getRazonSocial());
						nuevacontrasenaForm.setIdTipoUsuario(usuario.getIdTipoUsuario());
						nuevacontrasenaForm.setIdCandidato(idCandidato);
						nuevacontrasenaForm.setIdUsuario(idUsuario);
					}
					jspforward = mapping.findForward(JSP_SUCCESS).getPath();
				} else {
					request.setAttribute("urlvalido", "true");
					jspforward = mapping.findForward(FORWARD_JSP).getPath();

				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("NUEVA_CONTRASENA", true);
		request.getSession().setAttribute(BODY_JSP, jspforward);
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}

	public static boolean CompararDia(Date aDate) {
		return aDate.getTime() > System.currentTimeMillis() - Constantes.DIA_MILISEGUNDOS;
	}

	public ActionForward toHome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {   	
    	PropertiesLoader.getInstance();
    	ActionForward forward = null;
    	LogoutAction logout = new LogoutAction();
    	forward = logout.execute(mapping, form, request, response);
    	return forward;		
    }

	public ActionForward Guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String type = "";
		String message = "";
		String respuesta = "";
		NuevaContrasenaForm nuevacontrasenaForm = (NuevaContrasenaForm) form;
		SecutityDelegate security = SecutityDelegateImpl.getInstance();
		try {
			UsuarioVO usuariovo = security.consultaUsuario(nuevacontrasenaForm.getIdUsuario());
			long idPropietario = security.consultaPropietario(nuevacontrasenaForm.getIdUsuario());
			if (usuariovo.getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()) {
				CandidatoVo candidatovo = security.consultaCandidato(idPropietario);
				CandidatoBusDelegate candidatodelegate = CandidatoBusDelegateImpl.getInstance();
				respuesta = security.cambioContrasena(idPropietario, nuevacontrasenaForm.getContrasena(),
						EVENTO.CAMBIO_CONTRASEÑA, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				if (respuesta.equals("exito")){
					if (candidatovo.getEstatus() != ESTATUS.ACTIVO.getIdOpcion()){
						if (usuariovo.getEstatus() != ESTATUS.ACTIVO.getIdOpcion()){
							candidatodelegate.reactivarCandidato(idPropietario, usuariovo.getIdUsuario());
						}
					}
				}
			} else {
				RegisterBusDelegate registerdelegate = RegisterBusDelegateImpl.getInstance();
				EmpresaRegistroBusDelegateImpl.getInstance();
				EmpresaVO empresaVo = registerdelegate.findEmpresaByIdUsuario(usuariovo.getIdUsuario());
				respuesta = security.cambioContrasenaE(idPropietario, nuevacontrasenaForm.getContrasena(),
						EVENTO.CAMBIO_CONTRASEÑA, TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				if (respuesta.equals("exito")){
					if (usuariovo.getEstatus() != ESTATUS.ACTIVO.getIdOpcion()) {
						registerdelegate.reactivarEmpresa(empresaVo.getIdEmpresa(), empresaVo.getIdUsuario(),
							empresaVo.getIdUsuario());
					}
				}
			}
			if (respuesta.equals("exito")) {
				type = "exito";
				message = "Cambio de Contraseña Exitoso";
			}
		} catch (Exception e) {
			type = "error";
			message = "Ocurrio un Error al guardar contraseña";
		}
		try {
			ResultVO resultado = new ResultVO(message, type);
			String json = toJson(resultado);
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward Aceptar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		NuevaContrasenaForm nuevacontrasenaForm = (NuevaContrasenaForm) form;
		SecutityDelegate services = SecutityDelegateImpl.getInstance();
		CandidatoBusDelegate delegate = CandidatoBusDelegateImpl.getInstance();
		long idUsuario = nuevacontrasenaForm.getIdUsuario();
		long idCandidato;
		try {
			UsuarioVO usuario = services.consultaUsuario(idUsuario);
			idCandidato = services.consultaPropietario(idUsuario);
			if (usuario.getIdTipoUsuario() == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()) {
				Solicitante solicitante = delegate.obtenerSolicitante(idCandidato);
				nuevacontrasenaForm.setUsuario(usuario.getUsuario());
				nuevacontrasenaForm.setIdTipoUsuario(usuario.getIdTipoUsuario());
				nuevacontrasenaForm.setIdCandidato(idCandidato);
				nuevacontrasenaForm.setNombre(solicitante.getNombre()+" "+solicitante.getApellido1()+" "+solicitante.getApellido2());
				nuevacontrasenaForm.setIdUsuario(idUsuario);
				nuevacontrasenaForm.setContrasena(nuevacontrasenaForm.getContrasena());
				nuevacontrasenaForm.setCorreoElectronico(usuario.getCorreoElectronico());
			} else if (usuario.getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
				CompanyBusDelegate empresadelegate = CompanyBusDelegateImpl.getInstance();
				EmpresaRegistroBusDelegate empresabus = EmpresaRegistroBusDelegateImpl.getInstance();
				EmpresaVO empresavo = empresadelegate.findEmpresaByIdUsuario(idUsuario);
				empresavo = empresabus.consultaEmpresa(empresavo.getIdEmpresa());
				nuevacontrasenaForm.setUsuario(usuario.getUsuario());
				nuevacontrasenaForm.setNombre(empresavo.getRazonSocial());
				nuevacontrasenaForm.setIdEmpresa(empresavo.getIdEmpresa());
				nuevacontrasenaForm.setIdTipoUsuario(usuario.getIdTipoUsuario());
				nuevacontrasenaForm.setIdCandidato(idCandidato);
				nuevacontrasenaForm.setIdUsuario(idUsuario);
				nuevacontrasenaForm.setContrasena(nuevacontrasenaForm.getContrasena());
				nuevacontrasenaForm.setCorreoElectronico(usuario.getCorreoElectronico());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jspforward = mapping.findForward(FORWARD_NEXT).getPath();
		request.setAttribute("NUEVA_CONTRASENA", true);
		request.getSession().setAttribute(BODY_JSP, jspforward);
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}

	public ActionForward notificaCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String type = "";
		String message = "";
		NuevaContrasenaForm registroForm = (NuevaContrasenaForm) form;
		String usuario = registroForm.getUsuario();
		String contrasena = StringEscapeUtils.escapeXml(registroForm.getContrasena());
		long idUsuario = registroForm.getIdUsuario();
		long idCandidato = registroForm.getIdCandidato();
		String correoElectronico = registroForm.getCorreoElectronico();
		if (idCandidato > 0 && correoElectronico != null && !correoElectronico.isEmpty()
				&& Utils.validaMail(correoElectronico) && usuario != null && !usuario.isEmpty() && contrasena != null
				&& !contrasena.isEmpty()) {
			try {
				CandidatoBusDelegate candidatoDelegate = CandidatoBusDelegateImpl.getInstance();
				CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
				CandidatoVo candidatoVo = candidatoDelegate.findPpcSdTermsAndConditionsData(idUsuario);
				String nombreCandidato = candidatoVo.getNombre();
				String apellido1Candidato = candidatoVo.getApellido1();
				String apellido2Candidato = candidatoVo.getApellido2();
				if (nombreCandidato != null && !nombreCandidato.isEmpty() && apellido1Candidato != null
						&& !apellido1Candidato.isEmpty()) {
					delegate.notificaRecuperacionPsw(correoElectronico, idCandidato, contrasena, nombreCandidato,
							apellido1Candidato, apellido2Candidato);
					type = "exito";
					message = "Correo enviado a la direccion " + correoElectronico;
				}
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error al enviar correo.";
			} catch (MailException e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error al enviar correo.";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error al enviar correo.";
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
		return null;
	}
	
	public ActionForward notificacionEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String type = "";
		String message = "";
		NuevaContrasenaForm registroForm = (NuevaContrasenaForm) form;		
		String correoElectronico = registroForm.getCorreoElectronico();
		String usuario = registroForm.getUsuario();
		long idEmpresa  = registroForm.getIdEmpresa();
		String contrasena = StringEscapeUtils.escapeXml(registroForm.getContrasena());
		if (correoElectronico!=null && !correoElectronico.isEmpty() && Utils.validaMail(correoElectronico) && 
				usuario!=null && !usuario.isEmpty() && contrasena!=null && !contrasena.isEmpty()) {
			try {
				EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
				EmpresaVO empresaVo = delegate.consultaEmpresa(idEmpresa);
				delegate.notificacionRecuperacionPswEmpresa(idEmpresa, usuario, empresaVo.getNombreEmpresa(), correoElectronico, contrasena);
				type = "exito"; message = "Correo enviado a la direccion "+ correoElectronico;
			} catch (ServiceLocatorException e) {
				e.printStackTrace(); logger.error(e);
				type = "error"; message = "Error al enviar correo.";
			} catch (MailException e) {
				e.printStackTrace(); logger.error(e);
				type = "error"; message = "Error al enviar correo.";
			} catch (Exception e) {
				e.printStackTrace(); logger.error(e);
				type = "error"; message = "Error al enviar correo.";
			}
		} else {
			type = "errormaildate"; message = "Campos requeridos para el envio de la notificacion por correo.";
		}
		try {
			ResultVO resultado = new ResultVO(message, type);
			String json = toJson(resultado);
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;		
	}

	public ActionForward toLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		NuevaContrasenaForm nuevacontrasenaForm = (NuevaContrasenaForm) form;
		try {
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			UsuarioVO user = services.consultaUsuario(nuevacontrasenaForm.getIdUsuario());
			forward = profileForward(mapping, request, response, user);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("NEW PASSWORD ERROR " + e);
			PropertiesLoader properties = PropertiesLoader.getInstance();
			String path = properties.getProperty("app.swb.redirect.home");
			request.setAttribute(URL_REDIRECT_SWB, path);
			forward = mapping.findForward(FORWARD_REDIRECT_SWB);
		}
		return forward;
	}
	
	private ActionForward profileForward(ActionMapping mapping, HttpServletRequest request, 
			HttpServletResponse response, UsuarioVO user) throws SQLException, ServiceLocatorException {
		String urlAction = null;
		ActionForward forward = null;
		LoginAction loginAction = new LoginAction();
		loginAction.firmaUsuarioPortal(request, response, user);
		urlAction = getUrlAction(user.getIdPerfil());
		request.setAttribute(URL_REDIRECT_SWB, urlAction);
		request.removeAttribute("FROM_CIL");
		forward = mapping.findForward(FORWARD_REDIRECT_SWB);
		CookieManager.getInstance().removeCookieRediIt(request, response, "r3d1T024");
		return forward;
	}
	
	private String getUrlAction(long idPerfil) {
		String urlAction = null;
		PropertiesLoader properties = PropertiesLoader.getInstance();
    	if (idPerfil == PERFIL.EMPRESA.getIdOpcion()) {
    		urlAction = properties.getProperty("app.login.redirect.portal.empresa");
        } else if (isPerfilCandidato(idPerfil)) {
        	urlAction = properties.getProperty("app.login.redirect.portal.candidato");
        } else if (idPerfil == PERFIL.PUBLICADOR.getIdOpcion()){
        	urlAction = properties.getProperty("app.login.redirect.portal.publicador");
        } else if (idPerfil == PERFIL.SUPERVISOR_SNETEL.getIdOpcion()){
        	urlAction = properties.getProperty("app.login.redirect.portal.admin");
        } else if (idPerfil == PERFIL.ADMINISTRADOR_SNETEL.getIdOpcion()){
        	urlAction = properties.getProperty("app.login.redirect.portal.admin");
        } else if (idPerfil == PERFIL.ADMINISTRADOR.getIdOpcion() ||
        		   idPerfil == PERFIL.SUPERADMINISTRADOR.getIdOpcion()){
        	urlAction = properties.getProperty("app.login.redirect.portal.admin");
        } else if (idPerfil == PERFIL.ADMIN_TIPO_A.getIdOpcion()){
        	urlAction = properties.getProperty("app.login.redirect.portal.parametros");
        } else if (idPerfil == PERFIL.MONITOR_PE.getIdOpcion()){
        	urlAction = properties.getProperty("app.login.redirect.portal.monitor");
        }
    	return urlAction;
	}

	private boolean isPerfilCandidato(long idPerfil){
		if (idPerfil == mx.gob.stps.portal.utils.Utils.toLong(PERFIL.CANDIDATO.getIdOpcion()))
			return true;
		if (idPerfil == mx.gob.stps.portal.utils.Utils.toLong(PERFIL.CANDIDATO_ADULTO_MAYOR.getIdOpcion()))
			return true;		
		if (idPerfil == mx.gob.stps.portal.utils.Utils.toLong(PERFIL.CANDIDATO_DISCAPACIDAD.getIdOpcion()))
			return true;
		return false;
	}
}
