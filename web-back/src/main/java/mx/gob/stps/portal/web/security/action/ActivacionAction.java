package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_EMPRESA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAPTCHA_OBJ;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAPTCHA_WORD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.seguridad.vo.ConfirmacionVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.form.ActivacionForm;
import mx.gob.stps.portal.ws.captcha.CaptchaLogicoObject;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ActivacionAction extends GenericAction {
	private static final Logger logger = Logger.getLogger(ActivacionAction.class);
	
	private static final String ERROR_MSG = "ERROR_MSG";
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("RECUPERA_CONTRASENA", true);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		// instanciamos el objeto que nos sirve las preguntas del captcha: al instanciarlo genera una pregunta
		try {
			ActivacionForm activacionForm = (ActivacionForm) form;
			
			CaptchaLogicoObject captcha = getCaptcha(request);
			
			activacionForm.setPregunta(captcha.getPregunta());
			
			inicializarEtiquetasRazonSocial(activacionForm);
			request.getSession().setAttribute("tituloPagina", "Activaci&oacute;n");
			request.getSession().setAttribute("descripcionPagina", "Activaci&oacute;n");
			request.getSession().setAttribute("facebookImage", request.getContextPath() + "/css/images/compartir-contenido.jpg");
			request.getSession().setAttribute("twitterImage", request.getContextPath() + "/css/images/contenido-compartir-tweetA.jpg");
			request.getSession().setAttribute("urlEspecifica", request.getRequestURL().toString());
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}
	
	private void inicializarEtiquetasRazonSocial(ActivacionForm activacionForm){
		activacionForm.setEtiquetaEmpresaMoral("Razón social");
		activacionForm.setEtiquetaEmpresaOng("Nombre de la organización");
		activacionForm.setEtiquetaEmpresaPublica("Nombre de empresa/Institución pública");
	}	
	
	//Código del nuevo captcha (Abriendo Espacios)
	public void cambiaPregunta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	
		String pregunta = null;
		try {
			CaptchaLogicoObject captcha = getCaptcha(request);
			ActivacionForm activacionForm = (ActivacionForm) form;
			captcha.obtieneDesafioCaptcha();
			activacionForm.setPregunta(captcha.getPregunta());
			pregunta = captcha.getPregunta();
			redirectJsonResponse(response, pregunta);	
		} catch (mx.gob.stps.portal.exception.BusinessException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}catch(Exception e){
			//e.printStackTrace();
		}	
	}
	
	public boolean validateRespuestaUsuario(String respuestaUsuario, HttpServletRequest request) throws TechnicalException{
		boolean esCorrecto = true;
		try{
			CaptchaLogicoObject captcha = getCaptcha(request);
			
			if (respuestaUsuario == null)
				esCorrecto = false;
			else if (!captcha.isRespuestaCorrecta(respuestaUsuario) )
				esCorrecto = false;
		
		}catch (Exception e){
			logger.error("obtienePregunta: ha ocurrido un error");
			e.printStackTrace();
			throw new TechnicalException("Ha ocurrido un error al validar la respuesta");
		}
		
		return esCorrecto;
	}
	//Fin nuevo captcha (Abriendo Espacios)
	
	public ActionForward activacionCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		int estatusUsuario = -1;
		ActivacionForm activacionForm = (ActivacionForm)form;
		String jspforward = mapping.findForward(FORWARD_JSP).getPath();
		try {
			if (!validateRespuestaUsuario(activacionForm.getRespuestaUsuario(), request)) {
				request.setAttribute(ERROR_MSG, getMensaje(request, "captcha.respuesta.incorrecta"));
			} else {
				String curp = activacionForm.getCurp();
				String usuario = activacionForm.getUsuario();
				SecutityDelegate services = SecutityDelegateImpl.getInstance();
				UsuarioVO usuarioVo = null;
				try {
					usuarioVo = services.consultaUsuarioPorLogin(usuario);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
				}
				if (null==usuarioVo) {
					throw new BusinessException("El nombre de usuario proporcionado no es válido. Por favor verifique que el nombre de usuario sea el mismo con el cual se registro en el Portal.");				
				} else {			
					ConfirmacionVO confirmacionVo = null;
					estatusUsuario = usuarioVo.getEstatus();
					activacionForm.setIdCandidato(services.getIdCandidato(usuarioVo.getIdUsuario()));
					if (estatusUsuario == ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion()) {
						request.getSession().setAttribute("UsuarioInactivoAPeticion", usuario);					
						confirmacionVo = services.confirmacionReactivacionCandidato(usuario, curp);
						activacionForm.setConfirmacion(confirmacionVo);	
						activacionForm.setIdTipoUsuario(TIPO_USUARIO.CANDIDATO.getIdTipoUsuario());	
					}else if(estatusUsuario == ESTATUS.INACTIVO_POR_VIGENCIA.getIdOpcion()) {	
						request.getSession().setAttribute("UsuarioInactivoPorVigencia", usuario);					
						confirmacionVo = services.confirmacionReactivacionCandidato(usuario, curp);
						activacionForm.setConfirmacion(confirmacionVo);	
						activacionForm.setIdTipoUsuario(TIPO_USUARIO.CANDIDATO.getIdTipoUsuario());
						request.setAttribute(ERROR_MSG, getMensaje(request, "seguridad.login.msg.usu.inactivo.vig"));
					}else if(estatusUsuario == ESTATUS.INACTIVO_POR_BAJA_ADMINISTRADOR.getIdOpcion()) {
						request.getSession().setAttribute("UsuarioInactivoPorAdmor", usuario);
						request.setAttribute(ERROR_MSG, getMensaje(request, "seguridad.login.msg.usu.inactivo.admor"));
					}else {
						confirmacionVo = services.confirmacionDirectaCandidato(usuario, curp);
						activacionForm.setConfirmacion(confirmacionVo);	
						activacionForm.setIdTipoUsuario(TIPO_USUARIO.CANDIDATO.getIdTipoUsuario());										
					}
				}
				jspforward = mapping.findForward(JSP_SUCCESS).getPath();
			}
		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage()));
		} catch (TechnicalException e) {
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.TechnicalException"));
		} catch(PersistenceException e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.PersistenceException"));
		} catch(ServiceLocatorException e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.ServiceLocatorException"));
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		}
		request.setAttribute("RECUPERA_CONTRASENA", true);
		request.getSession().setAttribute(BODY_JSP, jspforward);
		request.getSession().setAttribute("tituloPagina", "recupera contraseña");
		request.getSession().setAttribute("descripcionPagina", "Portal del Empleo: recupera tu contraseña.");
		request.getSession().setAttribute("facebookImage", request.getContextPath() + "/css/images/compartir-contenido.jpg");
		request.getSession().setAttribute("twitterImage", request.getContextPath() + "/css/images/contenido-compartir-tweetA.jpg");
		request.getSession().setAttribute("urlEspecifica", request.getRequestURL().toString());
		
        return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);					
	}
	
	public ActionForward activacionEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		String jspforward = mapping.findForward(FORWARD_JSP).getPath();
		ActivacionForm activacionForm = (ActivacionForm)form;

		try{
			if(!validateRespuestaUsuario(activacionForm.getRespuestaUsuario(), request)){
				request.setAttribute(ERROR_MSG, getMensaje(request, "captcha.respuesta.incorrecta"));
			}else {
				UsuarioVO usuarioVo = null;
				String usuario = activacionForm.getUsuario();
				SecutityDelegate services = SecutityDelegateImpl.getInstance();
				try {
					usuarioVo = services.consultaUsuarioPorLogin(usuario);
				}catch (Exception e) {
					e.printStackTrace();
				}
				if (null == usuarioVo || usuarioVo.getIdTipoUsuario() == 3) {
					throw new BusinessException("El nombre de usuario proporcionado no es válido. Por favor verifique que el nombre de usuario sea el mismo con el cual se registro en el Portal.");				
				
				} else if(usuarioVo.getEstatus() == ESTATUS.ELIMINADA_ADMIN.getIdOpcion()) {
					request.getSession().setAttribute("EmpresaInactivaPorAdmor", usuario);
					request.setAttribute(ERROR_MSG, getMensaje(request, "seguridad.login.msg.empresa.desactivada"));
									
				} else {
					
					ConfirmacionVO confirmacionVo = null;
					
					Long idTipoEmpresa = (long)activacionForm.getIdTipoEmpresa();
					
					if(null==idTipoEmpresa){						
						
						throw new BusinessException("El tipo de empresa proporcionado no es válido. Por favor verifique que el nombre de usuario sea el mismo con el cual se registro en el Portal.");				
					
					} else {
												
						if(idTipoEmpresa.equals(Catalogos.TIPO_EMPRESA.ONG.getTipoEmpresa()) 
								|| idTipoEmpresa.equals(Catalogos.TIPO_EMPRESA.PUBLICA.getTipoEmpresa())){							
							
							String razonSocial = activacionForm.getRazonSocial();

							if(null==razonSocial){
								
								throw new BusinessException("El nombre de empresa proporcionado no es válido. Por favor verifique que el nombre de usuario sea el mismo con el cual se registro en el Portal.");
							
							} else {
								
								RegisterBusDelegate empresaService = RegisterBusDelegateImpl.getInstance();
								
								EmpresaVO empresaDelUsuario = empresaService.findEmpresaByIdUsuario(usuarioVo.getIdUsuario());
								
								if(null==empresaDelUsuario)
									throw new BusinessException("El nombre de usuario proporcionado no es válido. Por favor verifique que el nombre de usuario sea el mismo con el cual se registro en el Portal.");
								activacionForm.setIdEmpresa(empresaDelUsuario.getIdEmpresa());
								confirmacionVo = services.confirmacionDirectaEmpresa(usuario, razonSocial);		
								if(null!=confirmacionVo){
									System.out.println("confirmacionVo.getContrasena():" + confirmacionVo.getContrasena());
								}									
								activacionForm.setConfirmacion(confirmacionVo);
								activacionForm.setIdTipoUsuario(TIPO_USUARIO.EMPRESA.getIdTipoUsuario());	
																							
							}																		
						
						} else if(idTipoEmpresa.equals(Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa())) {
							
							Calendar fechaActivacion = Calendar.getInstance();
							fechaActivacion.set(Calendar.DATE,  activacionForm.getDia());
							fechaActivacion.set(Calendar.MONTH, activacionForm.getMes()-1);
							fechaActivacion.set(Calendar.YEAR,  activacionForm.getAnio());
							
							confirmacionVo = services.confirmacionDirectaEmpresa(usuario, fechaActivacion.getTime());
							if(null!=confirmacionVo){
								System.out.println("confirmacionVo.getContrasena():" + confirmacionVo.getContrasena());
							}																					
							activacionForm.setConfirmacion(confirmacionVo);
							activacionForm.setIdTipoUsuario(TIPO_USUARIO.EMPRESA.getIdTipoUsuario());
							activacionForm.setIdEmpresa(confirmacionVo.getIdEmpresa());
						}
						
					}					
				}
	
				jspforward = mapping.findForward(FORWARD_NEXT).getPath();
			}
			
		} catch (BusinessException e) {
			//e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage()));
		} catch (TechnicalException e) {
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.TechnicalException"));
		} catch(PersistenceException e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.PersistenceException"));
		} catch(ServiceLocatorException e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.ServiceLocatorException"));
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		}		
		request.setAttribute("RECUPERA_CONTRASENA", true);
		request.getSession().setAttribute(BODY_JSP, jspforward);
		request.getSession().setAttribute("tituloPagina", "recupera contraseña");
		request.getSession().setAttribute("descripcionPagina", "Portal del Empleo: recupera tu contraseña.");
		request.getSession().setAttribute("facebookImage", request.getContextPath() + "/css/images/compartir-contenido.jpg");
		request.getSession().setAttribute("twitterImage", request.getContextPath() + "/css/images/contenido-compartir-tweetA.jpg");
		request.getSession().setAttribute("urlEspecifica", request.getRequestURL().toString());
        return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);					
	}	
	
    public ActionForward captcha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	ResultVO result = null;
    	
    	if (!validaCaptcha(request)){
    		String msgerr = getMensaje(request, "can.curp.captcha.err");
    		result = new ResultVO(msgerr, ResultVO.TYPE_ERROR);
    	} else{
    		result = new ResultVO("cadena de validacion correcta", ResultVO.TYPE_SUCCESS);
    	}

		try{
			String json = toJson(result);
			redirectJsonResponse(response, json);		
		}catch(Exception e){
			e.printStackTrace(); logger.error(e); 
		}

		return null;
    }
    
    private boolean validaCaptcha(HttpServletRequest request) {
		boolean captchavalido = false;
		HttpSession session = request.getSession();

		String word = (String)session.getAttribute(CAPTCHA_WORD); // clave secreta
		String code = request.getParameter("code"); // texto capturado
				
		if (word==null || word.trim().isEmpty() || code==null || code.trim().isEmpty()){
			captchavalido = false;
			logger.error("validaCaptcha -> word : "+ word +" code : "+ code);
		}else if (word.trim().equalsIgnoreCase(code.trim())) {
			captchavalido = true;	
		}else{
			captchavalido = false;
			logger.error("validaCaptcha -> word : "+ word +" code : "+ code);
		}

		return captchavalido;
	}

    public ActionForward toHome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {   	
    	PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.home"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);		
    }

    public ActionForward toLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	ActionForward forward = null;
    	ActivacionForm activacionForm = (ActivacionForm)form;
    	    	
    	try{
    		SecutityDelegate services = SecutityDelegateImpl.getInstance();
    		UsuarioVO usuario = services.consultaUsuarioPorLogin(activacionForm.getUsuario());
        	
        	LoginAction loginAction = new LoginAction();
        	loginAction.firmaUsuarioPortal(request, response, usuario);
        	
        	forward = loginAction.redirectSWB(usuario.getIdPerfil(), mapping, request, response, activacionForm.getUsuario());

    	}catch(Exception e){
    		e.printStackTrace(); logger.error(e);

        	PropertiesLoader properties = PropertiesLoader.getInstance();
        	String path = properties.getProperty("app.swb.redirect.home");

        	if (activacionForm.getIdTipoUsuario()==TIPO_USUARIO.EMPRESA.getIdTipoUsuario()){
        		path = properties.getProperty("app.swb.redirect.registro.empresa");
        	
        	} else if (activacionForm.getIdTipoUsuario()==TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()){
        		path = properties.getProperty("app.swb.redirect.registro.candidato");
        	}

    		request.setAttribute(URL_REDIRECT_SWB, path);
    		forward = mapping.findForward(FORWARD_REDIRECT_SWB);
    	}
    	
    	return forward;
    }
    
    public ActionForward veriCaptchaGoogle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
    	ResultVO result = null;
    	
    	if (!validaCaptchaGoogle(request)){
    		String msgerr = getMensaje(request, "can.curp.captcha.err");
    		result = new ResultVO(msgerr, ResultVO.TYPE_ERROR);
    	} else{
    		result = new ResultVO("cadena de validacion correcta", ResultVO.TYPE_SUCCESS);
    	}

		try{
			String json = toJson(result);
			redirectJsonResponse(response, json);		
		}catch(Exception e){
			e.printStackTrace(); logger.error(e); 
		}

		
    	
    	/*
    	HttpClient client = new DefaultHttpClient();
    	HttpGet request = new HttpGet("http://www.vogella.com");
    	HttpResponse response = client.execute(request);

    	// Get the response
    	BufferedReader rd = new BufferedReader
    	  (new InputStreamReader(response.getEntity().getContent()));
    	    
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    	  te*/
    	
    	
        return null;
    	
    	
    }
    
    private boolean validaCaptchaGoogle(HttpServletRequest request) {
    	
    	boolean captchavalido = false;
		//HttpSession session = request.getSession();
    	
    	String remoteAddr = request.getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6Le1ZeMSAAAAAAXSYw_mo65yIrRUBpQU4PjKNbIW");

        String challenge = request.getParameter("recaptcha_challenge_field");
        //System.out.println(challenge);
        String uresponse = request.getParameter("recaptcha_response_field");
        //System.out.println(uresponse);
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

        if (reCaptchaResponse.isValid()) {
          //System.out.println("Answer was entered correctly!");
          captchavalido = true;
        } else {
          //System.out.println("Answer is wrong");
          captchavalido = false;
        }
		return captchavalido;
    	
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
			e.printStackTrace();
		}
		

		return captcha;
	}
	
	
	public ActionForward tiposEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		try {
			CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
			List<CatalogoOpcionVO> opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_TIPO_EMPRESA, true);

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}

		return null;
	}		
	
	
	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}		
	
}
