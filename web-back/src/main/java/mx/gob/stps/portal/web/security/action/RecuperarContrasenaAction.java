package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAPTCHA_OBJ;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAPTCHA_WORD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.persistencia.entity.Solicitante;
import mx.gob.stps.portal.persistencia.vo.DomicilioVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos.TIPO_USUARIO;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.CompanyBusDelegate;
import mx.gob.stps.portal.web.company.delegate.CompanyBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegate;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.fs.EncriptURL;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.form.RecuperaContrasenaForm;
import mx.gob.stps.portal.ws.captcha.CaptchaLogicoObject;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import javax.ejb.EJBTransactionRolledbackException;

public class RecuperarContrasenaAction extends GenericAction {

	private static final Logger logger = Logger.getLogger(RecuperarContrasenaAction.class);

	private static final String ERROR_MSG = "ERROR_MSG";
	//private static final String FORDWARD_PERFIL_CURP = "JSP_PERFIL_CURP";
	//private static final String FORWARD_PREREGISTRO = "JSP_PREREGISTRO";

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("RECUPERA_CONTRASENA", true);
		request.setAttribute("urlvalido", "false");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		try {
			RecuperaContrasenaForm contrasenaForm = (RecuperaContrasenaForm) form;
			// instanciamos el objeto que nos sirve las preguntas del captcha:
			// al instanciarlo genera una pregunta
			CaptchaLogicoObject captcha = getCaptcha(request);
			contrasenaForm.setPregunta(captcha.getPregunta());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}

	public ActionForward verificarCorreo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RecuperaContrasenaForm contrasenaForm = (RecuperaContrasenaForm) form;
		String type = "";
		String message = "";
		String json = "";
		if (contrasenaForm.getCorreoElectronico() != null && !contrasenaForm.getCorreoElectronico().isEmpty()) {
			try {
				if (!validateRespuestaUsuario(contrasenaForm.getRespuestaUsuario(), request)) {
					request.setAttribute(ERROR_MSG, getMensaje(request, "captcha.respuesta.incorrecta"));
					type = "error";
					message = "La respuesta no es correcta: por favor, ponga una nueva o cambie de pregunta";
				} else {
					EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
					boolean unico = delegate.esCorreoUnico(contrasenaForm.getCorreoElectronico());
					type = "exito";
					message = unico ? "unico" : "nounico";
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error al validar el Correo.";
			}
		} else {
			type = "error";
			message = "Correo electronico no valido.";
		}
		try {
			ResultVO resultado = new ResultVO(message, type);
			json = toJson(resultado);
			redirectJsonResponse(response, json);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward sendUrl4Recover(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RecuperaContrasenaForm contrasenaForm = (RecuperaContrasenaForm) form;
		String type = "";
		String json = "";
		String message = "";
		UsuarioVO user = null;
		EncriptURL ec64 = new EncriptURL();
		PropertiesLoader properties = PropertiesLoader.getInstance();
		String path = properties.getProperty("app.domain.portal");
		SecutityDelegate services = SecutityDelegateImpl.getInstance();
		if (null != contrasenaForm.getCorreoElectronico() && !contrasenaForm.getCorreoElectronico().isEmpty()) {
			try {
				if (!validateRespuestaUsuario(contrasenaForm.getRespuestaUsuario(), request)) {
					request.setAttribute(ERROR_MSG, getMensaje(request, "captcha.respuesta.incorrecta"));
					type = "error";
					message = "La respuesta no es correcta: por favor, ponga una nueva o cambie de pregunta";
				}else {
					EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
					boolean unique = delegate.esCorreoUnico(contrasenaForm.getCorreoElectronico());
					type = "exito";
					System.out.println("sendUrl4Recover: " + contrasenaForm.getCorreoElectronico());
					if (!unique) {
						user = services.consultaUsuarioPorCorreo(contrasenaForm.getCorreoElectronico());
						if (null == user) {
							type = "error";
							message = "Error No se Pudo Enviar el Correo";
						}else {
							Calendar today = GregorianCalendar.getInstance();
							SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
							String userdate = "idUsuario=" + user.getIdUsuario() + "&fecha=" + sdf.format(today.getTime());
							String urldecode = ec64.encode(userdate);
							String urlcode = path + "/sne/nuevaContrasena.do?method=init&amp;c=" + urldecode;
							if (user.getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
								CompanyBusDelegate companydelegate = CompanyBusDelegateImpl.getInstance();
								EmpresaRegistroBusDelegate empresadelegate = EmpresaRegistroBusDelegateImpl.getInstance();
								EmpresaVO empresavo = companydelegate.findEmpresaByIdUsuario(user.getIdUsuario());
								EmpresaVO empresaVo = empresadelegate.consultaEmpresa(empresavo.getIdEmpresa());
								companydelegate.notificacionRecuperacionContrasena(empresaVo.getIdEmpresa(), user.getUsuario(), TIPO_PROPIETARIO.EMPRESA,
										empresaVo.getNombreEmpresa(), empresaVo.getCorreoElectronico(), urlcode);
								services.bitacoraRecuperaContrasena(user.getIdUsuario(), EVENTO.SOLICITUD_CAMBIO_CONTRASEÑA, empresaVo.getIdEmpresa(),
										TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
							} else {
								long idPropietario = services.consultaPropietario(user.getIdUsuario());
								CandidatoBusDelegate candidatodelegate = CandidatoBusDelegateImpl.getInstance();
								Solicitante solicitante = candidatodelegate.obtenerSolicitante(idPropietario);
								logger.info(user.getCorreoElectronico()+"|"+solicitante.getNombre() + " " + solicitante.getApellido1() + " " + solicitante.getApellido2() + "|"+idPropietario+"|"+solicitante.getCorreoElectronico());
								
								//if(solicitante.getCorreoElectronico().trim()!=user.getCorreoElectronico().trim()){
									//throw new Exception("Las sessiones de juntaron"+solicitante.getIdSolicitante()+"|"+user.getIdUsuario());
								//}
								candidatodelegate.notificacionRecuperacionContrasena(idPropietario, user.getUsuario(), 
									TIPO_PROPIETARIO.CANDIDATO, solicitante.getNombre() + " " + solicitante.getApellido1() + " " + solicitante.getApellido2(),
									user.getCorreoElectronico(), urlcode);
								services.bitacoraRecuperaContrasena(user.getIdUsuario(), EVENTO.SOLICITUD_CAMBIO_CONTRASEÑA, idPropietario,
										TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
							}
							type = "exito";
							message = "Correo enviado a la direccion " + user.getCorreoElectronico();
						}
					}else message = unique ? "unico" : "nounico";
				}
			} catch (MailException me){
				logger.error("Ocurrió error al envías correo a: " + user.getCorreoElectronico());
				logger.error(me);
				type = "error";
				message = "Ocurrió error en el envío de correo. Por favor intente más tarde.";
			} catch (Exception e) {
				//e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Ocurrió error al recuperar contraseña. Por favor intente más tarde.";
			}
		} else {
			type = "error";
			message = "Correo electronico no valido.";
		}
		try {
			ResultVO resultado = new ResultVO(message, type);
			json = toJson(resultado);
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward validaCURPUnico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws BusinessException, ServiceLocatorException {
		String type = "";
		String message = "";
		CandidatoVo candidato = null;
		RecuperaContrasenaForm recover = (RecuperaContrasenaForm) form;
		recover.setCorreoElectronico(request.getParameter("correoElectronicoCandidato"));
		boolean validatePhone = null != recover.getTelefono() && !recover.getTelefono().isEmpty();
		boolean validateEmail = null != recover.getCorreoElectronico() && !recover.getCorreoElectronico().isEmpty();
		if (null != recover.getCurp() && !recover.getCurp().isEmpty() && (validatePhone || validateEmail)) {
			try {
				// Consultamos en la base de datos si la curp existe en el sistema
				CandidatoBusDelegate candidatodelegate = CandidatoBusDelegateImpl.getInstance();
				CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
				candidato = delegate.consultaCandidatoPorCURP(recover.getCurp());
				if (null != candidato) {
					if (validateEmail && null != candidato.getCorreoElectronico() && candidato.getCorreoElectronico().equalsIgnoreCase(recover.getCorreoElectronico())) {
						type = "exito";
						message = "Datos Correctos";
					}else {
						type = "error";
						message = "La dirección de correo electrónico no coincide con la CURP proporcionada";
					}
					if (existPhone(candidato.getIdCandidato(), recover.getTelefono(), candidatodelegate)) {
						if (!validateEmail) {
							type = "exito";
							message = "Datos Correctos";
						}
					}else if (validatePhone) {
						type = "error";
						message = "El teléfono no coincide con la CURP proporcionada";
					}
				}else {
					type = "error";
					message = "No existe candidato para la CURP proporcionada";
				}
			} catch (EJBTransactionRolledbackException e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error interno del Servicio intenta más tarde.";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error al validar el CURP.";
			}
		}else {
			type = "error";
			message = "Debe proporcionar curp, correo electrónico y/o teléfono para recuperar su contraseña";
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

	public ActionForward validaEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws BusinessException, ServiceLocatorException {
		RecuperaContrasenaForm recover = (RecuperaContrasenaForm) form;
		String type = "";
		String message = "";
		recover.setCorreoElectronico(request.getParameter("correoElectronicoEmpresa"));
		boolean validateRFC = null != recover.getRfc() && !recover.getRfc().isEmpty();
		boolean validateEmail = null != recover.getCorreoElectronico() && !recover.getCorreoElectronico().isEmpty();
		if (recover.getCodigoPostal() != null && !recover.getCodigoPostal().isEmpty() && (validateRFC || validateEmail)) {
			try {
				// Consultamos en la base de datos si la empresa existe en el sistema
				EmpresaRegistroBusDelegate empresa = EmpresaRegistroBusDelegateImpl.getInstance();
				EmpresaVO vo = empresa.findEmpresaByCP(recover.getRfc(), recover.getCorreoElectronico(), recover.getCodigoPostal());
				if (null != vo && vo.getIdEmpresa() > 0) {
					request.getSession().setAttribute("EmpresaVO", vo);
					type = "exito";
					message = "Los datos son correctos. Para ingresar al portal,";
				}else {
					type = "error";
					message = "Los datos son incorrectos. Para ingresar al portal,";
				}
			} catch (EJBTransactionRolledbackException e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error interno del Servicio intenta más tarde.";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error al validar la Empresa.";
			}
		} else {
			type = "error";
			message = "Los datos son incorrectos. Para ingresar al portal,";
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

	public ActionForward mensajeContrasena(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String type = "";
		String message = "";
		EncriptURL ec64 = new EncriptURL();
		PropertiesLoader properties = PropertiesLoader.getInstance();
		String path = properties.getProperty("app.domain.portal");
		RecuperaContrasenaForm contrasenaForm = (RecuperaContrasenaForm) form;
		String jspforward = mapping.findForward(FORWARD_JSP).getPath();
		try {
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			CandidatoBusDelegate candidatodelegate = CandidatoBusDelegateImpl.getInstance();
			UsuarioVO usuarioVo = null;
			String correoElectronico = contrasenaForm.getCorreoElectronico();
			usuarioVo = services.consultaUsuarioPorCorreo(correoElectronico);
			if (usuarioVo == null) {
				type = "error";
				message = "Error No se Pudo Enviar el Correo";
			} else {
				String usuario = usuarioVo.getUsuario();
				long idUsuario = usuarioVo.getIdUsuario();
				UsuarioVO usuariovo = services.consultaUsuario(idUsuario);
				long idPropietario = services.consultaPropietario(idUsuario);
				Calendar today = GregorianCalendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
				String cadena = "idUsuario=" + idUsuario + "&fecha=" + sdf.format(today.getTime());
				String urldecode = ec64.encode(cadena);
				String urlcode = path + "/sne/nuevaContrasena.do?method=init&amp;c=" + urldecode;
				if (usuariovo.getIdTipoUsuario() == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
					CompanyBusDelegate companydelegate = CompanyBusDelegateImpl.getInstance();
					EmpresaRegistroBusDelegate empresadelegate = EmpresaRegistroBusDelegateImpl.getInstance();
					EmpresaVO empresavo = companydelegate.findEmpresaByIdUsuario(idUsuario);
					EmpresaVO empresaVo = empresadelegate.consultaEmpresa(empresavo.getIdEmpresa());
					companydelegate.notificacionRecuperacionContrasena(idPropietario, usuario, TIPO_PROPIETARIO.EMPRESA,
							empresaVo.getNombreEmpresa(), empresaVo.getCorreoElectronico(), urlcode);
					services.bitacoraRecuperaContrasena(idUsuario, EVENTO.SOLICITUD_CAMBIO_CONTRASEÑA, idPropietario,
							TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				} else {
					Solicitante solicitante = candidatodelegate.obtenerSolicitante(idPropietario);
					candidatodelegate.notificacionRecuperacionContrasena(idPropietario,
							usuario, TIPO_PROPIETARIO.CANDIDATO, solicitante.getNombre() + " "
									+ solicitante.getApellido1() + " " + solicitante.getApellido2(),
							usuariovo.getCorreoElectronico(), urlcode);
					services.bitacoraRecuperaContrasena(idUsuario, EVENTO.SOLICITUD_CAMBIO_CONTRASEÑA, idPropietario,
							TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				}
				type = "exito";
				message = "Correo enviado a la direccion " + usuarioVo.getCorreoElectronico();
				jspforward = mapping.findForward(JSP_SUCCESS).getPath();
			}

		} catch (TechnicalException e) {
			e.printStackTrace();
			type = "error";
			message = "Error No se Pudo Enviar el Correo";
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.TechnicalException"));
		} catch (PersistenceException e) {
			type = "error";
			message = "Error No se Pudo Enviar el Correo";
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.PersistenceException"));
		} catch (Exception e) {
			type = "error";
			message = "Error No se Pudo Enviar el Correo";
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		}
		if ("exito" == type) {
			request.setAttribute("RECUPERA_CONTRASENA", true);
			request.getSession().setAttribute(BODY_JSP, jspforward);
			return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		} else {
			return null;
		}
	}
	
	public ActionForward confirmByEmail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String type = "";
		String message = "";
		RecuperaContrasenaForm contrasenaForm = (RecuperaContrasenaForm) form;
		String jspforward = mapping.findForward(FORWARD_JSP).getPath();
		try {
			String correoElectronico = contrasenaForm.getCorreoElectronico();
			type = "exito";
			message = "Correo enviado a la direccion " + correoElectronico;
			jspforward = mapping.findForward(JSP_SUCCESS).getPath();
		}catch (Exception e) {
			type = "error";
			message = "Error No se Pudo Enviar el Correo";
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		}
		if ("exito" == type) {
			request.setAttribute("RECUPERA_CONTRASENA", true);
			request.getSession().setAttribute(BODY_JSP, jspforward);
			return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		} else {
			return null;
		}
	}

	public ActionForward recuperaUsuarioCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String type = "";
		String message = "";
		String correocode = null;
		CandidatoVo candidato = null;
		String jspforward = mapping.findForward(FORWARD_JSP).getPath();
		RecuperaContrasenaForm recuperausuarioForm = (RecuperaContrasenaForm) form;
		if (recuperausuarioForm.getCurp() != null && !recuperausuarioForm.getCurp().isEmpty()) {
			try {
				CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
				CandidatoBusDelegate candidatodelegate = CandidatoBusDelegateImpl.getInstance();
				candidato = delegate.consultaCandidatoPorCURP(recuperausuarioForm.getCurp());
				if (candidato != null) {
					SecutityDelegate services = SecutityDelegateImpl.getInstance();
					UsuarioVO usuarioVo = services.consultaUsuario(candidato.getIdUsuario());
					long idCandidato = services.consultaPropietario(usuarioVo.getIdUsuario());
					Solicitante solicitante = candidatodelegate.obtenerSolicitante(idCandidato);
					int idTipoUsuario = (int) usuarioVo.getIdTipoUsuario();
					String usuario = usuarioVo.getUsuario();
					String correoElectronico = usuarioVo.getCorreoElectronico();
					if (null != correoElectronico) {
						correocode = correoElectronicoCode(correoElectronico);
						recuperausuarioForm.setCorreocode(correocode);
						recuperausuarioForm.setCorreoElectronico(correoElectronico);
						jspforward = mapping.findForward(FORWARD_NEXT).getPath();
					}else {
						recuperausuarioForm.setCorreocode(userCode(usuario));
						jspforward = mapping.findForward("NEXT_USER").getPath();
					}
					recuperausuarioForm.setNombre(solicitante.getNombre() + " " + solicitante.getApellido1() + " "
							+ solicitante.getApellido2());
					recuperausuarioForm.setCurp(recuperausuarioForm.getCurp());
					recuperausuarioForm.setIdTipoUsuario(idTipoUsuario);
					recuperausuarioForm.setUsuario(usuario);
					type = "exito";
					message = "Usuario Recuperado: " + recuperausuarioForm.getUsuario();
					services.bitacoraRecuperaContrasena(usuarioVo.getIdUsuario(), EVENTO.RECUPERA_USUARIO, idCandidato,
							TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				}
			} catch (EJBTransactionRolledbackException e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error interno del Servicio intenta más tarde.";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error al validar el CURP.";
			}
		} else {
			type = "error";
			message = "Los datos son incorrectos. Para ingresar al portal,";
		}
		if ("exito" == type) {
			request.setAttribute("RECUPERA_USUARIO", true);
			request.getSession().setAttribute(BODY_JSP, jspforward);
			return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		} else
			return null;
	}
	
	public ActionForward recoverUserByCURP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String type = "";
		String message = "";
		String codeMail = null;
		CandidatoVo candidato = null;
		String jspforward = mapping.findForward(FORWARD_JSP).getPath();
		RecuperaContrasenaForm recuperausuarioForm = (RecuperaContrasenaForm) form;
		if (recuperausuarioForm.getCurp() != null && !recuperausuarioForm.getCurp().isEmpty()) {
			try {
				CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
				CandidatoBusDelegate candidatodelegate = CandidatoBusDelegateImpl.getInstance();
				candidato = delegate.consultaCandidatoPorCURP(recuperausuarioForm.getCurp());
				if (null != candidato) {
					request.getSession().setAttribute("cand", candidato);
					SecutityDelegate services = SecutityDelegateImpl.getInstance();
					UsuarioVO user = services.consultaUsuario(candidato.getIdUsuario());
					Solicitante solicitante = candidatodelegate.obtenerSolicitante(candidato.getIdCandidato());
					int idTipoUsuario = (int) user.getIdTipoUsuario();
					String usuario = user.getUsuario();
					String email = user.getCorreoElectronico();
					if (null != email) {
						codeMail = correoElectronicoCode(email);
						recuperausuarioForm.setCorreocode(codeMail);
						recuperausuarioForm.setCorreoElectronico(email);
						jspforward = mapping.findForward(FORWARD_NEXT).getPath();
					}else {
						recuperausuarioForm.setCorreocode(userCode(usuario));
						jspforward = mapping.findForward("NEXT_USER").getPath();
					}
					recuperausuarioForm.setNombre(solicitante.getNombre() + " " + solicitante.getApellido1() + " "
							+ solicitante.getApellido2());
					recuperausuarioForm.setCurp(recuperausuarioForm.getCurp());
					recuperausuarioForm.setIdTipoUsuario(idTipoUsuario);
					recuperausuarioForm.setUsuario(usuario);
					type = "exito";
					message = "Usuario Recuperado: " + recuperausuarioForm.getUsuario();
					services.bitacoraRecuperaContrasena(user.getIdUsuario(), EVENTO.RECUPERA_USUARIO, candidato.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				}
			} catch (EJBTransactionRolledbackException e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error interno del Servicio intenta más tarde.";
			} catch (Exception e) {
				logger.error(e);
				type = "error";
				message = e.getMessage();
				jspforward = mapping.findForward(FORWARD_JSP).getPath();
			}
		} else {
			type = "error";
			message = "Los datos son incorrectos. Para ingresar al portal,";
		}
		if ("exito" == type) {
			request.setAttribute("RECUPERA_USUARIO", true);
			request.getSession().setAttribute(BODY_JSP, jspforward);
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
	
	public ActionForward confirmUserByCURP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}
	
	public ActionForward sendMail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String type = "";
		String message = "";
		CandidatoVo candidato = null;
		EncriptURL ec64 = new EncriptURL();
		Calendar today = GregorianCalendar.getInstance();
		PropertiesLoader properties = PropertiesLoader.getInstance();
		String jspforward = mapping.findForward(FORWARD_NEXT).getPath();
		request.getSession().setAttribute(BODY_JSP, jspforward);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		CandidatoBusDelegate candidatodelegate = CandidatoBusDelegateImpl.getInstance();
		String path = properties.getProperty("app.domain.portal");
		try {
			candidato = (CandidatoVo)request.getSession().getAttribute("cand");
			if (null != candidato) {
				SecutityDelegate services = SecutityDelegateImpl.getInstance();
				UsuarioVO user = services.consultaUsuario(candidato.getIdUsuario());
				String stamp = "idUsuario=" + candidato.getIdUsuario() + "&fecha=" + sdf.format(today.getTime());
				String urldecode = ec64.encode(stamp);
				String urlcode = path + "/sne/nuevaContrasena.do?method=init&amp;c=" + urldecode;
				Solicitante solicitante = candidatodelegate.obtenerSolicitante(candidato.getIdCandidato());
				candidatodelegate.notificacionRecuperacionContrasena(candidato.getIdCandidato(), user.getUsuario(), TIPO_PROPIETARIO.CANDIDATO, solicitante.getNombre() + " "
					+ solicitante.getApellido1() + " " + solicitante.getApellido2(), user.getCorreoElectronico(), urlcode);
				services.bitacoraRecuperaContrasena(user.getIdUsuario(), EVENTO.RECUPERA_USUARIO, candidato.getIdCandidato(), TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				type = "exito";
				message = "Usuario Recuperado: " + user.getUsuario();
			}
		}catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			type = "error";
			message = e.getMessage();
			System.out.println("sendMailPhoneError: " + e.getMessage());
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

	private boolean existPhone(long idCandidato, String telefono, CandidatoBusDelegate delegate) {
		if (null == telefono || telefono.isEmpty()) return false;
		try {
			List<TelefonoVO> phoneList = delegate.getTelefonoAppService().consultarTelefonos(idCandidato, TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
			for (TelefonoVO phone : phoneList) {
				if (phone.getTelefono().equalsIgnoreCase(telefono)) return true;
			}
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private String correoElectronicoCode(String correoElectronico) {
		String correocode = "";
		String[] email = new String[2];
		if (correoElectronico.contains("@")) {
			email = correoElectronico.split("@");
			String sub = "";
			for (int i = 0; i < email.length; i++) {
				String dominio = email[i];
				if (i == 0) {
					String servidor = dominio;
					if (servidor.length() > 3) {
						sub = dominio.substring(1, dominio.length() - 2);
					}
					if (servidor.length() == 1) {
						sub = dominio.substring(0, dominio.length());
					}
					if (dominio.length() == 2) {
						sub = dominio.substring(1, dominio.length());
					}
					if (dominio.length() == 3) {
						sub = dominio.substring(1, dominio.length() - 1);
					}

					char[] chars = sub.toCharArray();
					for (int j = 0; j < chars.length; j++) {
						chars[j] = '*';
					}

					StringBuilder sbEmail = new StringBuilder(dominio);
					if (dominio.length() > 3) {
						correocode += sbEmail.replace(1, dominio.length() - 2, String.valueOf(chars));
						correocode += "@";
					}
					if (dominio.length() == 1) {
						correocode += sbEmail.replace(0, dominio.length(), String.valueOf(chars));
						correocode += "@";
					}
					if (dominio.length() == 2) {
						correocode += sbEmail.replace(1, dominio.length(), String.valueOf(chars));
						correocode += "@";
					}
					if (dominio.length() == 3) {
						correocode += sbEmail.replace(1, dominio.length() - 1, String.valueOf(chars));
						correocode += "@";
					}
				}
				
				if (i == 1) {
					if (dominio.contains(".")) {
						String servidor = dominio.substring(0, dominio.indexOf("."));
						if (servidor.length() > 3) {
							sub = dominio.substring(1, dominio.indexOf(".") - 2);
						}
						if (servidor.length() == 1) {
							sub = dominio.substring(0, dominio.indexOf("."));
						}
						if (dominio.length() == 2) {
							sub = dominio.substring(1, dominio.indexOf("."));
						}
						if (dominio.length() == 3) {
							sub = dominio.substring(1, dominio.indexOf(".") - 1);
						}
					} else {
						if (dominio.length() > 3) {
							sub = dominio.substring(1, dominio.length() - 2);
						}
						if (dominio.length() == 1) {
							sub = dominio.substring(0, dominio.length());
						}
						if (dominio.length() == 2) {
							sub = dominio.substring(1, dominio.length());
						}
						if (dominio.length() == 3) {
							sub = dominio.substring(1, dominio.length() - 1);
						}
					}

					char[] chars = sub.toCharArray();
					for (int j = 0; j < chars.length; j++) {
						chars[j] = '*';
					}

					StringBuilder sbEmail = new StringBuilder(dominio);
					if (dominio.contains(".")) {
						String servidor = dominio.substring(0, dominio.indexOf("."));
						if (servidor.length() > 3) {
							correocode += sbEmail.replace(1, dominio.indexOf(".") - 2, String.valueOf(chars));
						}
						if (servidor.length() == 1) {
							correocode += sbEmail.replace(0, dominio.indexOf("."), String.valueOf(chars));
						}
						if (servidor.length() == 2) {
							correocode += sbEmail.replace(1, dominio.indexOf("."), String.valueOf(chars));
						}
						if (servidor.length() == 3) {
							correocode += sbEmail.replace(1, dominio.indexOf(".") - 1, String.valueOf(chars));
						}
					} else {
						if (dominio.length() > 3) {
							correocode += sbEmail.replace(1, dominio.length() - 2, String.valueOf(chars));
						}
						if (dominio.length() == 1) {
							correocode += sbEmail.replace(0, dominio.length(), String.valueOf(chars));
						}
						if (dominio.length() == 2) {
							correocode += sbEmail.replace(1, dominio.length(), String.valueOf(chars));
						}
						if (dominio.length() == 3) {
							correocode += sbEmail.replace(1, dominio.length() - 1, String.valueOf(chars));
						}
					}
				}
			}

		}
		return correocode;
	}
	
	private String userCode(String user) {
		StringBuilder code = new StringBuilder(user);
		char[] chars = user.toCharArray();
		for (int j = 0; j < chars.length; j++) {
			if (j == 0 || j == 3 || j == chars.length - 2 || j == chars.length - 1) continue;
			else code.replace(j, j+1, "*");
		}
		return code.toString();
	}

	public ActionForward recuperaUsuarioEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String jspforward = mapping.findForward(FORWARD_JSP).getPath();
		RecuperaContrasenaForm recover = (RecuperaContrasenaForm) form;
		String type = "";
		String message = "";
		if (null != recover.getCodigoPostal() && !recover.getCodigoPostal().isEmpty()
				&& null != request.getSession().getAttribute("EmpresaVO")) {
			try {
				EmpresaVO vo = (EmpresaVO)request.getSession().getAttribute("EmpresaVO");
				request.getSession().removeAttribute("EmpresaVO");
				DomicilioBusDelegate service = DomicilioBusDelegateImpl.getInstance();
				DomicilioVO domicilio = service.getDomicilio(vo.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				if (null != domicilio) {
					if (recover.getCodigoPostal().equals(domicilio.getCodigoPostal())) {
						SecutityDelegate services = SecutityDelegateImpl.getInstance();
						UsuarioVO usuarioVo = services.consultaUsuario(vo.getIdUsuario());
						int idTipoUsuario = (int) usuarioVo.getIdTipoUsuario();
						String usuario = usuarioVo.getUsuario();
						String correoElectronico = usuarioVo.getCorreoElectronico();
						String correocode = "";
						correocode = correoElectronicoCode(usuarioVo.getUsuario());
						recover.setCorreocode(correocode);
						recover.setIdEmpresa(vo.getIdEmpresa());
						recover.setNombre(recover.getRazonSocial());
						recover.setIdTipoUsuario(idTipoUsuario);
						recover.setCorreoElectronico(correoElectronico);
						recover.setUsuario(usuario);
						type = "exito";
						message = "Usuario Recuperado: " + recover.getUsuario();
						services.bitacoraRecuperaContrasena(usuarioVo.getIdUsuario(), EVENTO.RECUPERA_USUARIO, vo.getIdEmpresa(), TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
						jspforward = mapping.findForward(FORWARD_NEXT).getPath();
					}else {
						type = "error";
						message = "Los datos son incorrectos. Para ingresar al portal,";
					}
				}else {
					type = "error";
					message = "Los datos son incorrectos. Para ingresar al portal,";
				}
			}catch (EJBTransactionRolledbackException e) {
					e.printStackTrace();
					logger.error(e);
					type = "error";
					message = "Error interno del Servicio intenta más tarde.";
			}catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					type = "error";
					message = "Error al validar el CURP.";
			}
		}else {
			type = "error";
			message = "Los datos son incorrectos. Para ingresar al portal,";
		}
		if ("exito" == type) {
			request.setAttribute("RECUPERA_USUARIO", true);
			request.getSession().setAttribute(BODY_JSP, jspforward);
			return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		}else
			return null;
	}

	private CaptchaLogicoObject getCaptcha(HttpServletRequest request) {
		CaptchaLogicoObject captcha = null;
		try {
			captcha = (CaptchaLogicoObject) request.getSession().getAttribute(CAPTCHA_OBJ);

			if (captcha == null) {
				captcha = new CaptchaLogicoObject();
				request.getSession().setAttribute(CAPTCHA_OBJ, captcha);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return captcha;
	}

	public ActionForward toHome(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.home"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}

	public ActionForward captcha(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ResultVO result = null;

		if (!validaCaptcha(request)) {
			String msgerr = getMensaje(request, "can.curp.captcha.err");
			result = new ResultVO(msgerr, ResultVO.TYPE_ERROR);
		} else {
			result = new ResultVO("cadena de validacion correcta", ResultVO.TYPE_SUCCESS);
		}
		try {
			String json = toJson(result);
			redirectJsonResponse(response, json);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	private boolean validaCaptcha(HttpServletRequest request) {
		boolean captchavalido = false;
		HttpSession session = request.getSession();

		String word = (String) session.getAttribute(CAPTCHA_WORD); // clave
																	// secreta
		String code = request.getParameter("code"); // texto capturado

		if (word == null || word.trim().isEmpty() || code == null || code.trim().isEmpty()) {
			captchavalido = false;
			logger.error("validaCaptcha -> word : " + word + " code : " + code);
		} else if (word.trim().equalsIgnoreCase(code.trim())) {
			captchavalido = true;
		} else {
			captchavalido = false;
			logger.error("validaCaptcha -> word : " + word + " code : " + code);
		}
		return captchavalido;
	}

	// Código del nuevo captcha
	public void cambiaPregunta(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String pregunta = null;
		try {
			CaptchaLogicoObject captcha = getCaptcha(request);
			RecuperaContrasenaForm recuperaForm = (RecuperaContrasenaForm) form;
			captcha.obtieneDesafioCaptcha();
			recuperaForm.setPregunta(captcha.getPregunta());
			pregunta = captcha.getPregunta();
			redirectJsonResponse(response, pregunta);
		} catch (mx.gob.stps.portal.exception.BusinessException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public boolean validateRespuestaUsuario(String respuestaUsuario, HttpServletRequest request)
			throws TechnicalException {
		boolean esCorrecto = true;
		try {
			CaptchaLogicoObject captcha = getCaptcha(request);

			if (respuestaUsuario == null)
				esCorrecto = false;
			else if (!captcha.isRespuestaCorrecta(respuestaUsuario))
				esCorrecto = false;

		} catch (Exception e) {
			logger.error("obtienePregunta: ha ocurrido un error");
			e.printStackTrace();
			throw new TechnicalException("Ha ocurrido un error al validar la respuesta");
		}

		return esCorrecto;
	}

	public ActionForward veriCaptchaGoogle(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ResultVO result = null;
		if (!validaCaptchaGoogle(request)) {
			String msgerr = getMensaje(request, "can.curp.captcha.err");
			result = new ResultVO(msgerr, ResultVO.TYPE_ERROR);
		} else {
			result = new ResultVO("cadena de validacion correcta", ResultVO.TYPE_SUCCESS);
		}
		try {
			String json = toJson(result);
			redirectJsonResponse(response, json);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return null;
	}

	private boolean validaCaptchaGoogle(HttpServletRequest request) {

		boolean captchavalido = false;
		// HttpSession session = request.getSession();

		String remoteAddr = request.getRemoteAddr();
		ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
		reCaptcha.setPrivateKey("6Le1ZeMSAAAAAAXSYw_mo65yIrRUBpQU4PjKNbIW");

		String challenge = request.getParameter("recaptcha_challenge_field");
		// System.out.println(challenge);
		String uresponse = request.getParameter("recaptcha_response_field");
		// System.out.println(uresponse);
		ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

		if (reCaptchaResponse.isValid()) {
			// System.out.println("Answer was entered correctly!");
			captchavalido = true;
		} else {
			// System.out.println("Answer is wrong");
			captchavalido = false;
		}
		return captchavalido;
	}
}