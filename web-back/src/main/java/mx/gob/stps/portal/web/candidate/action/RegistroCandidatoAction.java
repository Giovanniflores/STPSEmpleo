package mx.gob.stps.portal.web.candidate.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuthService;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.candidate.vo.RegistroCandidatoVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.RESPUESTA_IMMS_CONSULTA_NSS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_INGRESO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_TELEFONO;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.CurpRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.core.ws.renapo.exception.ConsultaWsPorCurpException;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.utils.WebUtils;
import mx.gob.stps.portal.utils.pdform.RequirementsToPPCSDVO;
import mx.gob.stps.portal.utils.pdform.RequirementsToPpcSdPdfFormFillerDataAccess;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.RegistroCandidatoForm;
import mx.gob.stps.portal.web.candidate.vo.CandidatoRespuestaBaseAjaxVO;
import mx.gob.stps.portal.web.candidate.vo.RespuestaBaseAjaxVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Constantes.*;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.action.LoginAction;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.filter.CookieManager;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.ws.captcha.CaptchaLogicoObject;
import mx.gob.stps.portal.ws.geonames.GeoNames;
import mx.gob.stps.portal.ws.renapo.exception.CurpNotFoundException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.BirthDate;

import javax.ejb.EJBTransactionRolledbackException;
import javax.persistence.PersistenceException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static mx.gob.stps.portal.core.infra.utils.Constantes.TERMINOS_CONDICIONES;
import static mx.gob.stps.portal.utils.ConstantesGenerales.*;
import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

//import static mx.gob.stps.portal.web.infra.utils.Constantes.formatDateForma;

public class RegistroCandidatoAction extends GenericAction {
	private static Logger logger = Logger.getLogger(RegistroCandidatoAction.class);

	private static final String FORDWARD_PREREGISTRO = "JSP_PREREGISTRO";
	private static final String FORDWARD_PRE_BIENVENIDA = "JSP_PRE_BIENVENIDA";
	private static final String FORDWARD_PERFIL_CURP = "JSP_PERFIL_CURP";
	private static final String FORDWARD_PERFIL_CURP_PPC = "JSP_PERFIL_CURP_PPC";
	private static final String FORDWARD_PERFIL_DATOS = "JSP_PERFIL_DATOS";
	private static final String FORDWARD_PERFIL_TERMINA = "JSP_PERFIL_TERMINA";

	private static final String FORDWARD_BENEFICIOS = "JSP_CONTENIDO";
	private static final String FORDWARD_TERMINOS_GENERALES = "JSP_TERMGEN";
	private static final String FORDWARD_TERMINOS_PPC = "JSP_TERMCOND";
	private static final String FORDWARD_REGPPC_TERMINOS_PPC = "JSP_REGPPC_TERMCOND";
	
	private static final String FORDWARD_ACTION_CANDIDATO = "ACTION_CANDIDATO";

	private static final String FORDWARD_CONTENIDOPPC_USERLOGGED = "JSP_CONTENIDOPPC_USERLOGGED";	

	private static final String ERROR_MSG = "ERROR_MSG";

	private static final String PARAM_USUARIO = "usuario";
	private static final String PARAM_CONTRASENA = "contrasena";
	private static final String PARAM_CONFIRMACION = "confirmacion";

	private static final String ATRB_ACEPTATERMINOS_SI = "aceptaTerminosSi";
	private static final String ATRB_ACEPTATERMINOS_NO = "aceptaTerminosNo";
	
	private static final String ATRB_RENAPO_SI = "renapoSi";
	private static final String ATRB_RENAPO_NO = "renapoNo";
	
	private static final String CURP_UNICO = "unico";
	private static final String	CURP_NOUNICO = "nounico";
	private static final String	CURP_NOUNICO_PPC = "nounico_ppc";
	
	// private static final String PPC_SD_SOLICITUD = "PPC_SD_SOLICITUD";

	private final static String SEPARADOR = "|";
	private final static String REGISTRO_ARGUMENTOS = "uregdata"; // Argumento
																	// con los
																	// datos del
																	// usuarios
																	// concatenados
	// private CaptchaLogicoObject captcha;

	private final static String ACTUALIZAR_DESDE_CURP = "actualizarDesdeCurp";
	
	//private static final String LOGGEDUSER_REG_PPC = "LoggedUser_Reg_PPC"; 
	//private static final String LOGGEDUSER_REG_NOTPPC = "LoggedUser_Reg_NotPPC";
	
	private static final String DATOS_INCORRECTOS = "Datos_Incorrectos";
	private static final String CURP_NOT_RENAPO = "Curp_Not_Renapo";
	private static final String DATOS_NOT_RENAPO = "Datos_Not_Renapo";	
	private static final String NOTLOGGEDUSER_NOTREG = "NotLoggedUser_NotReg"; 	
	private static final String NOTLOGGEDUSER_REG_AND_FUERAPPC = "NotLoggedUser_Reg_and_FueraPPC";	
	private static final String NOTLOGGEDUSER_REG_AND_PPC = "NotLoggedUser_Reg_and_PPC";
	private static final String NOTLOGGEDUSER_REG_AND_NOTPPC = "NotLoggedUser_Reg_and_NotPPC";
	private static final String IMSS_NOTAVAILABLE = "IMSS_NotAvailable";	
	private static final String NSS_REG_IN_IMSS = "NSS_Reg_in_IMSS";
	private static final String NSS_NOTREG_IN_IMSS = "NSS_NotReg_in_IMSS";
	private static final String RESULT_ERROR = "Result_Error";
	private static final String RESULT_SUCCESS = "Result_Success";	


	//social

	String baseUrl = "https://graph.facebook.com/";
	String fbUserToken;
	String pageToken;
	ObjectMapper objectMapper = new ObjectMapper();
	//social

	private boolean verificaParametros(HttpServletRequest request, RegistroCandidatoForm registroForm) {
		String usuario = request.getParameter(PARAM_USUARIO);
		String contrasena = request.getParameter(PARAM_CONTRASENA);
		String confirmacion = request.getParameter(PARAM_CONFIRMACION);

		if (usuario == null || usuario.trim().isEmpty()) {
			// logger.debug("Sin usuario");
			return false;
		}

		registroForm.setUsuario(usuario);
		registroForm.setRegistroDirecto(true); // Si cuenta con el nombre de
												// usuario se considera que es
												// registro desde Home

		if (contrasena == null || contrasena.trim().isEmpty()) {
			logger.debug("Sin contrasena");
			return false;
		}

		registroForm.setContrasena(contrasena);

		if (confirmacion == null || confirmacion.trim().isEmpty()) {
			logger.debug("Sin confirmacion");
			return false;
		}

		registroForm.setConfirmacion(confirmacion);

		if (!contrasena.equals(confirmacion)) {
			logger.debug("Contrasena con es igual a la Confirmacion");
			return false;
		}

		String regexpmail = "^[a-zA-Z0-9_-]+(?:\\.[a-zA-Z0-9_-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-_]*[a-zA-Z0-9-])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$";
		String regexppwd = "^[a-zA-Z0-9]{8,12}";
		String regexplogin = "^[a-zA-ZáéíóúÁÉÍÓÚ0-9]{8,12}$";

		boolean esCorreo = false;
		logger.debug(usuario.indexOf("@"));
		if (usuario.indexOf("@") >= 0) {
			if (!Utils.validaPatron(regexpmail, usuario)) {
				logger.debug("Correo invalido");
				return false;
			}
			esCorreo = true;
		} else {
			if (!Utils.validaPatron(regexplogin, usuario)) {
				logger.debug("Usuario invalido");
				return false;
			}
		}

		registroForm.setEsCorreo(esCorreo ? 1 : 0);

		if (!Utils.validaPatron(regexppwd, contrasena)) {
			logger.debug("Contrasena invalida");
			return false;
		}

		if (!Utils.validaPatron(regexppwd, confirmacion)) {
			logger.debug("Confirmacion invalida");
			return false;
		}

		boolean unico = false;
		try {
			CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
			unico = delegate.esUsuarioUnico(usuario);

			if (!unico) {
				logger.debug("Usuario repetido");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return false;
		}

		registroForm.setUsuarioUnico(unico);

		if (esCorreo)
			registroForm.setCorreoElectronico(usuario);

		return true;
	}

	// Codigo del nuevo captcha (Abriendo Espacios)
	public void cambiaPregunta(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String pregunta = null;
		try {
			CaptchaLogicoObject captcha = getCaptcha(request);

			RegistroCandidatoForm activacionForm = (RegistroCandidatoForm) form;
			captcha.obtieneDesafioCaptcha();
			activacionForm.setPregunta(captcha.getPregunta());
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

	// Fin nuevo captcha (Abriendo Espacios)

	// Inicio de carga de la pagina para registrar el candidato
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		//srojas: para eliminar datos en sesion de registros anteriores
		registroForm.reset();
		//srojas

		HttpSession session = request.getSession();
		ActionForward forward = null;
		try {
			// instanciamos el objeto que nos sirve las preguntas del captcha:
			// al instanciarlo genera una pregunta
			try {
				CaptchaLogicoObject captcha = getCaptcha(request);

				//srojas: eliminar si va correcto el registro 
				//RegistroCandidatoForm activacionForm = (RegistroCandidatoForm) form;

				//activacionForm.setPregunta(captcha.getPregunta());
				//srojas
				registroForm.setPregunta(captcha.getPregunta());
				
			} catch (Exception e) {
				//e.printStackTrace();
			}
			/**
			 * El registro de los parametros de Infonavit se realiza en la clase
			 * RegistroCandidatoAction y en la clase SearchSpecificOffers a
			 * causa del requerimiento RQ_PORTALEMP_201209-001
			 **/
			String nss = request.getParameter("nss");
			String credito = request.getParameter("credito");
			if ((registroForm.getIdEntidad() == 0) && (registroForm.getNumeroSeguroSocial() == null)
					&& (registroForm.getCreditoInfonavit() == null)) {
				registroForm.reset(); // Limpiar todos los campos

				if (nss != null && !nss.trim().isEmpty()) {
					registroForm.setNumeroSeguroSocial(nss);
				}
				if (credito != null && !credito.trim().isEmpty()) {
					registroForm.setCreditoInfonavit(Utils.parseLong(credito));
				}
			}

			registroForm.setAccesoCelular(CLAVE_TELEFONO_CELULAR);
			registroForm.setAccesoFijo(CLAVE_TELEFONO_FIJO);

			registroForm.setUrlTerminosCondiciones(TERMINOS_CONDICIONES);
			registroForm.setIdTipoTelefonoFijo(TELEFONO_FIJO);
			registroForm.setIdTipoTelefonoCelular(TELEFONO_CELULAR);
			// if(session.getAttribute(ConstantesGenerales.PPC_SD_SOLICITUD) !=
			// null){
			// registroForm.setPpcSdSolicitud((Boolean)session.getAttribute(ConstantesGenerales.PPC_SD_SOLICITUD));
			// }

			// TO TEST
			// registroForm.setPpcSdSolicitud(true);
			// registroForm.setRegistroPPC(true);

			boolean parametrosLoginCorrectos = verificaParametros(request, registroForm);
			if (parametrosLoginCorrectos) {
				forward = toCurp(mapping, registroForm, request, response);
			} else {
				//forward = getForward(mapping, request, FORDWARD_PERFIL_CURP);
				forward = getForward(mapping, request, FORDWARD_BENEFICIOS);
			}
			registroForm.setPpcSdSolicitud(registroForm.isRegistroPPC());
			Boolean registroPPC = (Boolean) request.getSession().getAttribute(ConstantesGenerales.PPC_SD_SOLICITUD);
			if (registroPPC != null && registroPPC == true) {
				registroForm.setRegistroPPC(true);
				registroForm.setPpcSdSolicitud(true);
			}
			
			//srojas: para que no se muestre el campo NSS cuando accedemos desde el registrate al Portal
			registroForm.setRegistroPPC(false);
			registroForm.setPpcSdSolicitud(false);				

			/** Se obtiene el parametro de registro a traves de publicidad **/
			CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
			String parameterName = delegate.consultaNombreParametro();

			if (parameterName != null) {
				String parameterValue = request.getParameter(parameterName);
				if (parameterValue != null && !parameterValue.trim().isEmpty())
					session.setAttribute(parameterName, parameterValue);
			}

			// Poner la lista de activides
			List<CatalogoOpcionVO> opcHabilidades = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_HABILIDADES, true);
			registroForm.setOpcHabilidades(opcHabilidades);

			// Poner la lista de Puestos
			//Verificar permisos para geolocalizacion en el registro de candidatos
			CandidatoBusDelegate candidateServices = CandidatoBusDelegateImpl.getInstance();
			registroForm.setPermisoGeolocalizacion(candidateServices.consultarPermisoGeolocalizacionRegistro());

		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e);
		}

		//benjivds: revisar si es social
		//cambio facebook
		OAuthService service = null;
		Profile profile = null;
		if(registroForm.getoAuthService() != null){
			service = registroForm.getoAuthService();
		}
		if(service != null){
			Verifier v = new Verifier(registroForm.getCode());
			Token accessToken = service.getAccessToken(null, v); // returns short term token for FB User
			// Exchange the code for an AccessToken and retrieve the profile

			Response resp = getResponseForProfile(service,accessToken);
			if (resp.isSuccessful()) {
				try {
					String facebookUserId = getFacebookUserId(resp);
					profile = getFacebookProfile(resp);
					mapearPerfilSocialAEmpleo(profile, registroForm);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			registroForm.setoAuthService(null);



		}
		//fin cambio facebook call
		SocialAuthManager manager = null;
		if (registroForm.getSocialAuthManager() != null) {
			manager = registroForm.getSocialAuthManager();
		}
		if (manager != null) {
			List<Contact> contactsList = new ArrayList<Contact>();

			try {
				//obtener los parametros
				Map<String, String> paramsMap = new HashMap<String, String>();
				for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
					String key = entry.getKey();
					String values[] = entry.getValue();
					paramsMap.put(key, values[0].toString()); // Only 1 value is
				}
				//Autenticar a la red idicado

				AuthProvider provider = manager.connect(paramsMap);
				//obtener los datos del perfil
				profile = provider.getUserProfile();
				mapearPerfilSocialAEmpleo(profile, registroForm);
				registroForm.setSocialAuthManager(null);
		/*		contactsList = provider.getContactList();
				if (contactsList != null && contactsList.size() > 0) {
					for (Contact p : contactsList) {
						if (StringUtils.isEmpty(p.getFirstName())
								&& StringUtils.isEmpty(p.getLastName())) {
							p.setFirstName(p.getDisplayName());
						}
					}
				}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//benjivds

		request.setAttribute("RECUPERA_CONTRASENA", false);
		return forward;
	}




	//social


	private String getFacebookUserId(Response response)
			throws IOException {
		String responseBody = response.getBody();
		JsonNode jsonNode;
		jsonNode = objectMapper.readTree(responseBody);
		JsonNode idNode = jsonNode.get("id");
		return idNode.asText();
	}

	private Profile getFacebookProfile(Response response) throws IOException {
		Profile profile = new Profile();
		String responseBody = response.getBody();
		JsonNode jsonNode;
		jsonNode = objectMapper.readTree(responseBody);
		JsonNode idNode = jsonNode.get("id"); //birthday,name,email,first_name,last_name,gender,location
		idNode = jsonNode.get("first_name");
		profile.setFirstName(idNode.asText());
		idNode = jsonNode.get("last_name");
		profile.setLastName(idNode.asText());
		idNode = jsonNode.get("name");
		profile.setFullName(idNode.asText());
		profile.setDob(new BirthDate());
		idNode = jsonNode.get("birthday");
		String birthday = idNode.asText();
		Date formattedDate = null;
		Calendar myCal = new GregorianCalendar();
		try {
			if(birthday.length() == 10){
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				formattedDate = formatter.parse(birthday);
				myCal.setTime(formattedDate);

				profile.getDob().setDay(myCal.get(Calendar.DAY_OF_MONTH));
				profile.getDob().setMonth(myCal.get(Calendar.MONTH) + 1);
				profile.getDob().setYear(myCal.get(Calendar.YEAR));
			}else {
				if(birthday.length() == 5){
					SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
					formattedDate = formatter.parse(birthday);
					myCal.setTime(formattedDate);
					profile.getDob().setDay(myCal.get(Calendar.DAY_OF_MONTH));
					profile.getDob().setMonth(myCal.get(Calendar.MONTH)+1);
				}else {
					if (birthday.length() == 4) {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
						formattedDate = formatter.parse(birthday);
						profile.getDob().setYear(myCal.get(Calendar.YEAR));

					}
				}
			}
		} catch (ParseException e) {
			log.trace("RegistroCandiatoAction Error Parsing birthday of Facebook" + e);
		}

		idNode =jsonNode.get("gender");
		profile.setGender(idNode.asText());
		idNode = jsonNode.get("location");
		idNode = idNode.get("name");
		profile.setLocation(idNode.asText());
		return profile;
	}

	private Token getAccessToken(OAuthService oAuthService,String code) {
		Verifier verifier = new Verifier(code);
		return oAuthService.getAccessToken(Token.empty(), verifier);
	}

	private Response getResponseForProfile(OAuthService oAuthService,Token accessToken) {
		OAuthRequest oauthRequest =
				new OAuthRequest(Verb.GET,
						"https://graph.facebook.com/me?fields=id,birthday,name,email,first_name,last_name,gender,hometown,location",oAuthService);
		oAuthService.signRequest(accessToken, oauthRequest);
		return oauthRequest.send();
	}


	private void mapearPerfilSocialAEmpleo(Profile profile, RegistroCandidatoForm registroForm) {
		registroForm.setNombre(profile.getFirstName());
		registroForm.setApellido1(profile.getLastName());
		registroForm.setAnio(profile.getDob().getYear());
		registroForm.setDia(profile.getDob().getDay());
		registroForm.setMes(profile.getDob().getMonth());

		//Genero 1 = Hombre, Genero 2 = Mujer
		if(profile.getGender() != null && !profile.getGender().isEmpty())
		{
			if(profile.getGender().equals("male")){
				registroForm.setGenero(registroForm.getGeneroMasculino());
			}
			else
			{
				registroForm.setGenero(registroForm.getGeneroFemenino());
			}
		}

		//registroForm.setGenero(profile.getGender());
		registroForm.setCorreoElectronico(profile.getEmail());

	}
	//fin social

	public ActionForward toRecuperaContrasena(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		StringBuilder ACTION_URL = new StringBuilder();
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute("RECUPERA_CONTRASENA", true);
		ACTION_URL.append(null != request.getContextPath() ? request.getContextPath() : "").append(properties.getProperty("app.swb.redirect.login.recuperar"));
		request.setAttribute(URL_REDIRECT_SWB, ACTION_URL.toString());
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}

	public ActionForward redirectRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute("RECUPERA_CONTRASENA", false);
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.register.portal"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}

	public ActionForward toBeneficios(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;
		
		if (isLogged(request.getSession())){
			return getForward(mapping, request, FORDWARD_CONTENIDOPPC_USERLOGGED);			
		}
		
		registroForm.reset();
			
		registroForm.setRegistroPPC(Boolean.TRUE.booleanValue());
		// Se ocupara el valor del setRegistroPPC para saber si es un registro
		// de PPC

		request.getSession().setAttribute(ConstantesGenerales.PPC_SD_SOLICITUD, Boolean.TRUE);

		return getForward(mapping, request, FORDWARD_BENEFICIOS);
	}

	public ActionForward toTerminosGenerales(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		return getForward(mapping, request, FORDWARD_TERMINOS_GENERALES);
	}

	public ActionForward toTerminosPPC(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return getForward(mapping, request, FORDWARD_TERMINOS_PPC);
	}

	public ActionForward toConfirmacionRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		return getForward(mapping, request, FORDWARD_PERFIL_TERMINA);
	}

	public ActionForward toRegistroPPC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		// Agregamos al request las respuestas que puede devolver la verificacion de curp y nss para que se puedan manejar las respuestas desde el formulario
		request.setAttribute("DATOS_INCORRECTOS", DATOS_INCORRECTOS);
		request.setAttribute("CURP_NOT_RENAPO", CURP_NOT_RENAPO);
		request.setAttribute("DATOS_NOT_RENAPO", DATOS_NOT_RENAPO);
		request.setAttribute("NOTLOGGEDUSER_REG_AND_FUERAPPC", NOTLOGGEDUSER_REG_AND_FUERAPPC);		
		request.setAttribute("NOTLOGGEDUSER_REG_AND_PPC", NOTLOGGEDUSER_REG_AND_PPC);
		request.setAttribute("NOTLOGGEDUSER_REG_AND_NOTPPC", NOTLOGGEDUSER_REG_AND_NOTPPC);
		request.setAttribute("NOTLOGGEDUSER_NOTREG", NOTLOGGEDUSER_NOTREG);
		request.setAttribute("IMSS_NOTAVAILABLE", IMSS_NOTAVAILABLE);
		request.setAttribute("NSS_NOTREG_IN_IMSS", NSS_NOTREG_IN_IMSS);	
		request.setAttribute("RESULT_ERROR", RESULT_ERROR);		
		request.setAttribute("RESULT_SUCCESS", RESULT_SUCCESS);

		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;
		try {
			request.getSession().removeAttribute(CAPTCHA_OBJ);
			CaptchaLogicoObject captcha = getCaptcha(request);

			registroForm.setPregunta(captcha.getPregunta());
		} catch (Exception e) {
			e.printStackTrace();
		}

		registroForm.setEdicionPerfilLaboral(false);
		registroForm.setRegistroPPC(true);
		registroForm.setPpcSdSolicitud(true);
		
		ActionForward forward = getForward(mapping, request, FORDWARD_PERFIL_CURP_PPC);
		return forward;
	}

	public ActionForward toPreregistro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("RECUPERA_CONTRASENA", false);
		request.setAttribute(ACTUALIZAR_DESDE_CURP, false);

		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;
		registroForm.setEdicionPerfilLaboral(false);
		registroForm.setPpcSdSolicitud(registroForm.isRegistroPPC());
		Boolean registroPPC = (Boolean) request.getSession().getAttribute(ConstantesGenerales.PPC_SD_SOLICITUD);
		if (registroPPC != null && registroPPC) {
			registroForm.setPpcSdSolicitud(true);

		}
		logger.error("form ppc value = " + registroForm.isRegistroPPC() + " session attribute = " + registroPPC);

		return getForward(mapping, request, FORDWARD_PREREGISTRO);
	}

	// new para saber en la pantalla si es para actualizar datos
	public ActionForward toActualizarPreregistro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("RECUPERA_CONTRASENA", false);
		request.setAttribute(ACTUALIZAR_DESDE_CURP, true);
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;
		registroForm.setEdicionPerfilLaboral(true);
		registroForm.setPpcSdSolicitud(registroForm.isRegistroPPC());

		return getForward(mapping, request, FORDWARD_PREREGISTRO);
	}

	public ActionForward toBienvenida(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("RECUPERA_CONTRASENA", false);
		return getForward(mapping, request, FORDWARD_PRE_BIENVENIDA);
	}

	public ActionForward clearSocial(ActionMapping mapping, ActionForm form, HttpServletRequest request,
									 HttpServletResponse response){
		RegistroCandidatoForm registroCandidatoForm = (RegistroCandidatoForm) form;
		registroCandidatoForm.setSocialAuthManager(null);
		registroCandidatoForm.setId(null);
		registroCandidatoForm.setoAuthService(null);

		return getForward(mapping, request, FORDWARD_PERFIL_CURP);
	}
	
	
	// este m?todo se llama despues de haber validado el usuario para validar
	// el curp
	public ActionForward toCurp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("RECUPERA_CONTRASENA", false);
		return getForward(mapping, request, FORDWARD_PERFIL_CURP);
	}

	/** INVOCADO DESDE SWB PARA DESPLIEGUE DE LA PAGINA **/
	public ActionForward toPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		/**
		 * Se verifica si tiene el argumento de datos y en caso que el usuario
		 * haya perdido los datos capturados se establecen
		 **/
		String datosUsuario = request.getParameter(REGISTRO_ARGUMENTOS);
		if ((registroForm.getCurp() == null || registroForm.getCurp().isEmpty()) && datosUsuario != null)
			setDatosRequest(registroForm, datosUsuario);

		validaDatosBasicos(registroForm, request);

		estableceDatosPersonalesCatalogos(registroForm,request);

		return getForward(mapping, request, FORDWARD_PERFIL_DATOS);
	}

	/**
	 * INVOCADO DESDE LA PAGINA DE CURP PARA QUE CARQUE LOS DATOS Y DIRECCIONE
	 * HACIA SWB, ESTABLECE EL INDICADOR PARA EDICION DE CANDIDATO
	 **/
	public ActionForward toEditarPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;
		registroForm.setEdicionPerfilLaboral(true);

		/**
		 * Se verifica si tiene el argumento de datos y en caso que el usuario
		 * haya perdido los datos capturados se establecen
		 **/
		String datosUsuario = request.getParameter(REGISTRO_ARGUMENTOS);
		if ((registroForm.getCurp() == null || registroForm.getCurp().isEmpty()) && datosUsuario != null)
			setDatosRequest(registroForm, datosUsuario);

		return toDatosPersonales(mapping, registroForm, request, response);
	}

	/**
	 * INVOCADO DESDE LA PAGINA DE CURP PARA QUE CARQUE LOS DATOS Y DIRECCIONE
	 * HACIA SWB
	 **/
	public ActionForward toDatosPersonales(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		validaDatosBasicos(registroForm, request);

		estableceDatosPersonalesCatalogos(registroForm, request);

		/**
		 * Se envian los datos como parametros para evitar perderlos en el salto
		 * de pantallas de SWB
		 **/
		// String datosUsuario = getDatosConcatenados(registroForm);

		// Redireccionamiento a la siguiente seccion en SWB
		// PropertiesLoader properties = PropertiesLoader.getInstance();
		// String urlswb =
		// properties.getProperty("app.swb.redirect.registro.candidato.perfil")
		// +"?"+ REGISTRO_ARGUMENTOS +"="+ datosUsuario;
		// request.setAttribute(URL_REDIRECT_SWB, urlswb);
		// return mapping.findForward(FORWARD_REDIRECT_SWB);

		// TODO remove this part testing
		// registroForm.getHistoriaLaboralVO().setLogro("LOGRO SET BY");
		// registroForm.getHistoriaLaboralVO().setAniosLaborados(66L);

		return getForward(mapping, request, FORDWARD_PERFIL_DATOS);
	}

	private void estableceDatosPersonalesCatalogos(RegistroCandidatoForm registroForm, HttpServletRequest request) {
		try {
			// revisar si es de ppc_sd

			registroForm.setAccesoCelular(CLAVE_TELEFONO_CELULAR);
			registroForm.setAccesoFijo(CLAVE_TELEFONO_FIJO);
			registroForm.setAcceso(CLAVE_TELEFONO_CELULAR);

			List<CatalogoOpcionVO> grados = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_GRADO_ESTUDIOS, true);

			String[] gradosDependientes = new String[grados.size() + 1];
			gradosDependientes[0] = "0";

			for (CatalogoOpcionVO opcion : grados) {
				gradosDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
			}

			registroForm.setGradosDependientes(gradosDependientes);

			List<CatalogoOpcionVO> idiomas = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_IDIOMAS);

			// Carga en sesion los catalogos asociados a cada opcion
			String[] idiomasDependientes = new String[idiomas.size() + 1];
			idiomasDependientes[0] = "0";

			for (CatalogoOpcionVO opcion : idiomas) {
				if (opcion.getIdCatalogoOpcion() == 10) {
					idiomasDependientes[8] = String.valueOf(opcion.getIdCatalagoAsociado());
				} else {
					idiomasDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
				}
			}

			registroForm.setIdiomasDependientes(idiomasDependientes);

			List<CatalogoOpcionVO> otrosMedios = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_OTROS_MEDIOS, true);

			if (otrosMedios != null) {

				Comparator<CatalogoOpcionVO> comparator = new Comparator<CatalogoOpcionVO>() {
					@Override
					public int compare(CatalogoOpcionVO o1, CatalogoOpcionVO o2) {
						int test = 0;

						if (o1.getIdCatalogoOpcion() == o2.getIdCatalogoOpcion()) {
							test = 0;
						} else if (o1.getIdCatalogoOpcion() > o2.getIdCatalogoOpcion()) {
							test = 1;
						} else if (o1.getIdCatalogoOpcion() < o2.getIdCatalogoOpcion()) {
							test = -1;
						}

						return test;
					}
				};

				Collections.sort(otrosMedios, comparator);
			}

			registroForm.setOtrosMedios(otrosMedios);

			ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(registroForm.getIdEntidadNacimiento());
			if (entidad != null) {
				registroForm.setEntidadNacimiento(entidad.getDescripcion());
			}

			int edad = Utils.calculaEdad(registroForm.getDia(), registroForm.getMes(), registroForm.getAnio());
			registroForm.setEdad(edad);

			List<CatalogoOpcionVO> opcHabilidades = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_HABILIDADES, true);
			registroForm.setOpcHabilidades(opcHabilidades);
			
			//poner los valores para renapo
			request.setAttribute(ATRB_RENAPO_SI, Catalogos.APOYO_PROSPERA.SI);
			request.setAttribute(ATRB_RENAPO_NO, Catalogos.APOYO_PROSPERA.NO);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	@SuppressWarnings("unchecked")
	private boolean validaDatosBasicos(RegistroCandidatoForm registroForm, HttpServletRequest request) {
		boolean valido = true;

		ActionErrors errors = registroForm.validate();
		if (errors == null || errors.isEmpty()) {
			return true;
		}

		valido = false;

		StringBuilder buf = new StringBuilder();

		Iterator<ActionMessage> iterator = (Iterator<ActionMessage>) errors.get();
		while (iterator.hasNext()) {
			ActionMessage message = iterator.next();
			Object[] values = message.getValues();

			String msg = null;
			if (values != null && values.length > 0) {
				msg = (String) values[0];
				buf.append(msg + "<br/>");
			}
		}
		
		
		String errmsg = "<b>Su sesión ha expirado debido al tiempo de inactividad en el sistema.</b>";
		
//		String errmsg = "<b>Hubo un error en el registro reg?strese otra vez.<b>No se cuenta con los datos b?sicos para el registro:</b><br/>" + buf.toString()
//				+ "<b>Favor de verificar su captura o intentar de nuevo el registro, "
//				+ "notificar al administrador en caso de continuar con el problema.</b>";

		request.setAttribute(ERROR_MSG, errmsg);

		return valido;
	}

	public ActionForward crearPdfRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)throws ServiceLocatorException, TechnicalException, mx.gob.stps.portal.exception.TechnicalException{
		
		 long idUsuario = getUsuario(request.getSession()).getIdUsuario();
		 	RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form; 		
	        CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
	        CandidatoVo candidatoVo = service.findPpcSdTermsAndConditionsData(idUsuario);

	        assert candidatoVo != null : "Something went wrong; candidatoVo object is not supposed to be null, therefore pdf file cannot be created";

	        RequirementsToPPCSDVO requirementsToPPCSDVO = new RequirementsToPPCSDVO();
	        requirementsToPPCSDVO.setAddressState(candidatoVo.getDomicilioVo().getEntidad());
	        requirementsToPPCSDVO.setAddressMunicipality(candidatoVo.getDomicilioVo().getMunicipio());
	        requirementsToPPCSDVO.setAcceptanceDateOfPPCSD(candidatoVo.getPpcFechaInscripcion());
	        StringBuilder fullName = new StringBuilder();
	        fullName.append(candidatoVo.getNombre()).append(" ").append(candidatoVo.getApellido1());
	        if(candidatoVo.getApellido2() != null) {
	            fullName.append(" ").append(candidatoVo.getApellido2());
	        }
	        requirementsToPPCSDVO.setFullName(fullName.toString());
	        requirementsToPPCSDVO.setCurp(candidatoVo.getCurp());
	        requirementsToPPCSDVO.setNss(candidatoVo.getNss());

	        Map<String,String> pdfFormDataMap = null;
	        try {
	            pdfFormDataMap = requirementsToPPCSDVO.toPdfFormDataMap();
	            String formattedDate =  mx.gob.stps.portal.utils.Utils.getFechaFormato(new Date());
	            pdfFormDataMap.put("registrationDate", formattedDate != null ? formattedDate : "");
	            pdfFormDataMap.put("username", registroForm.getUsuario());
	            pdfFormDataMap.put("password", registroForm.getContrasena());
	        } catch (Exception e) {
	            logger.error(e.getMessage());
	            return mapping.getInputForward();   // Do not exist
	        }

	        String pdfOutputName = RequirementsToPpcSdPdfFormFillerDataAccess.REQUIREMENTS_TO_PPCSD_PDF_SUGGESTED_OUTPUT_NAME;

	        RequirementsToPpcSdPdfFormFillerDataAccess.createRequirementsToPpcSdPdfFormAndWriteIntoHttpResponseStreamInline(pdfOutputName, response, pdfFormDataMap);
/*
 * DataAccessToPEVO dataAccessToPEVO = new DataAccessToPEVO();
	        dataAccessToPEVO.setRegistrationDate(new Date());
	        dataAccessToPEVO.setUsername(login);
	        dataAccessToPEVO.setPassword(password);
	        
			InputStream pdfInputStream = DataAccessToPePdfFormFiller.obtainDataAccessToPeTemplatePdfAsInputStream();
			
			ByteArrayOutputStream salidaPdfDatosAutenticacion;
					
			salidaPdfDatosAutenticacion = PdfFormFiller.createPdfFormAndWriteIntoByteArray(pdfInputStream, dataAccessToPEVO.toPdfFormDataMap());
		
 */
	        return null;
	
	}
	
	public ActionForward notificaActivacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String type = "";
		String message = "";
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;
		String usuario = registroForm.getUsuario();
		String contrasena = registroForm.getContrasena();
		long idCandidato = registroForm.getIdCandidato();
		String correoElectronico = registroForm.getCorreoElectronico();
		if (idCandidato > 0 && correoElectronico != null && !correoElectronico.isEmpty() && Utils.validaMail(correoElectronico)
				 && usuario != null && !usuario.isEmpty() && contrasena != null && !contrasena.isEmpty()) {
			try {
				CandidatoBusDelegate candidatoDelegate = CandidatoBusDelegateImpl.getInstance();
				Integer estatusPPC = candidatoDelegate.consultarPpcEstatus(idCandidato);
				CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
				CandidatoVo candidatoVo = candidatoDelegate.findPpcSdTermsAndConditionsData(idCandidato);
				if (null != estatusPPC ){//&& estatusPPC.intValue() == Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion()) {
					int entidad = (int)candidatoVo.getDomicilioVo().getIdEntidad();
					int municipio = (int)candidatoVo.getDomicilioVo().getIdMunicipio();
					String nombreCandidato = candidatoVo.getNombre();
					String apellido1Candidato = candidatoVo.getApellido1();
					String apellido2Candidato = candidatoVo.getApellido2();
					String curp = candidatoVo.getCurp();
					String numeroSeguroSocial = registroForm.getNumeroSeguroSocial();
					if (nombreCandidato != null && !nombreCandidato.isEmpty()
							&& apellido1Candidato != null && !apellido1Candidato.isEmpty() ) {
						if (entidad > 0 && municipio > 0 && nombreCandidato != null && !nombreCandidato.isEmpty()
								&& apellido1Candidato != null && !apellido1Candidato.isEmpty() && curp != null
								&& !curp.isEmpty() && numeroSeguroSocial != null && !numeroSeguroSocial.isEmpty() 
								&& estatusPPC.intValue() == Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion()) {
							delegate.notificaRegistroCandidatoPpc(correoElectronico, usuario, contrasena,
									Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion(), entidad, municipio, nombreCandidato,
									apellido1Candidato, apellido2Candidato, curp, numeroSeguroSocial);
						} else {
							delegate.notificaRecuperacionPsw(correoElectronico, idCandidato, contrasena, nombreCandidato, apellido1Candidato, apellido2Candidato);
						}
						type = "exito";
						message = "Correo enviado a la direccion " + correoElectronico;
					}											
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
	
	public ActionForward notificaRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		String message = "";
		String type = "";

		String correoElectronico = registroForm.getCorreoElectronico();
		String usuario = registroForm.getUsuario();
		String contrasena = registroForm.getContrasena();
		long idCandidato = registroForm.getIdCandidato();

		if (idCandidato > 0 && correoElectronico != null && !correoElectronico.isEmpty()
				&& Utils.validaMail(correoElectronico) && usuario != null && !usuario.isEmpty() && contrasena != null
				&& !contrasena.isEmpty()) {

			try {

				CandidatoBusDelegate candidatoDelegate = CandidatoBusDelegateImpl.getInstance();
				Integer estatusPPC = candidatoDelegate.consultarPpcEstatus(idCandidato);

				CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();

				if (null != estatusPPC ){//&& estatusPPC.intValue() == Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion()) {
					int entidad = registroForm.getIdEntidad();
					int municipio = registroForm.getIdMunicipio();
					String nombreCandidato = registroForm.getNombre();
					String apellido1Candidato = registroForm.getApellido1();
					String apellido2Candidato = registroForm.getApellido2();
					String curp = registroForm.getCurp();
					String numeroSeguroSocial = registroForm.getNumeroSeguroSocial();

					if (nombreCandidato != null && !nombreCandidato.isEmpty()
							&& apellido1Candidato != null && !apellido1Candidato.isEmpty() ) {
						

						
						if (entidad > 0 && municipio > 0 && nombreCandidato != null && !nombreCandidato.isEmpty()
								&& apellido1Candidato != null && !apellido1Candidato.isEmpty() && curp != null
								&& !curp.isEmpty() && numeroSeguroSocial != null && !numeroSeguroSocial.isEmpty() 
								&& estatusPPC.intValue() == Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion()) {

							delegate.notificaRegistroCandidatoPpc(correoElectronico, usuario, contrasena,
									Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion(), entidad, municipio, nombreCandidato,
									apellido1Candidato, apellido2Candidato, curp, numeroSeguroSocial);
						} else {
							delegate.notificaRegistroCandidato(correoElectronico, usuario, contrasena, nombreCandidato,
									apellido1Candidato, apellido2Candidato,  
									Catalogos.ESTATUS.NO_INSCRITO_PPC.getIdOpcion());
						}
						
						type = "exito";
						message = "Correo enviado a la direccion " + correoElectronico;
						
					}											
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

	public ActionForward validaUsuarioUnico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		String type = "";
		String message = "";

		if (registroForm.isCorreo()) {
			if (registroForm.getUsuario() != null && !registroForm.getUsuario().isEmpty()) {
				try {
					CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
					boolean unico = delegate.esCorreoUnico(registroForm.getUsuario());
					registroForm.setCorreoElectronicoUnico(unico);

					type = "exito";
					message = unico ? "unico" : "nounico";
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					type = "error";
					message = "Error al validar el Correo.";
				}
			} else {
				type = "error";
				message = "Correo electronico no indicado.";
			}
		} else {

			if (registroForm.getUsuario() != null && !registroForm.getUsuario().isEmpty()) {
				try {
					CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
					boolean unico = delegate.esUsuarioUnico(registroForm.getUsuario());
					registroForm.setUsuarioUnico(unico);

					type = "exito";
					message = unico ? "unico" : "nounico";
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					type = "error";
					message = "Error al validar el login.";
				}
			} else {
				type = "error";
				message = "Usuario no indicado.";
			}

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

	public ActionForward validaCorreoElectronicoUnico(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		String type = "";
		String message = "";
		String correo = "";
		// cuando esta deshabilidatado un campo en html se pasa al form como
		// null
		if (registroForm.getCorreoElectronicoGuardado() != null
				&& !registroForm.getCorreoElectronicoGuardado().isEmpty()) {
			registroForm.setCorreoElectronico(registroForm.getCorreoElectronicoGuardado());
		}
		if (registroForm.getCorreoElectronico() != null && !registroForm.getCorreoElectronico().isEmpty()) {
			try {
				CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
				boolean unico = delegate.esCorreoUnico(registroForm.getCorreoElectronico());
				registroForm.setCorreoElectronicoUnico(unico);

				type = "exito";
				message = unico ? "unico" : "nounico";
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error al validar el Correo.";
			}
		} else {
			type = "error";
			message = "Correo electronico no indicado.";
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

	
	public boolean respuestaNSSValida(int valor){
		for(RESPUESTA_IMMS_CONSULTA_NSS item : RESPUESTA_IMMS_CONSULTA_NSS.values() ){
			if(item.getIdOpcion() == valor){
				return true;
			}
		}
		return false;
	}
	
	public ActionForward validaCURPUnico(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws BusinessException, ServiceLocatorException {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		CandidatoVo candidato = null;
		CandidatoRespuestaBaseAjaxVO respuesta = new CandidatoRespuestaBaseAjaxVO();
		String type = "";
		String message = "";
		
		if(registroForm.isRegistroPPC()){
			/*srojas: pendiente de borrar este codigo, mientras se hacen algunas pruebas
			//check if nss es correcto
			String result = verificarNSS(response, registroForm);
			
			if(respuestaNSSValida(Integer.valueOf(result))){
				if(RESPUESTA_IMMS_CONSULTA_NSS.NSS_REGISTRADO.getIdOpcion() == Integer.valueOf(result)){
					// codigo para validar si el numero de seguro social es unico
//					/
//					if(!esNSSUnico(registroForm.getNss())){
//						String json = toJson(new String("{\"result\" : \"" + "101" + "\", \"statusOper\":\"nss_error\", \"nss\" : \"" + registroForm.getNss()
//								+ "\"}"));
//						try {
//							redirectJsonResponse(response, json);
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}/
				}
				else{
					String json = toJson(new String("{\"result\" : \"" + result + "\", \" statusOper\":\"nss_error\", \"nss\" : \"" + registroForm.getNss()
							+ "\"}"));
					try {
						redirectJsonResponse(response, json);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		*/	
		}
		
		if (registroForm.getCurp() != null && !registroForm.getCurp().isEmpty()) {
			try {
				
				// Consultamos en la base de datos si la curp existe en el sistema
				CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
				//srojas: eliminar
				//boolean unico = delegate.esCurpUnico(registroForm.getCurp());
				CandidatoVo cand = delegate.consultaCandidatoPorCURP(registroForm.getCurp());
				
				boolean unico = true;
				if (cand != null && cand.getIdCandidato() > 0L){
					
					unico = false;

					if (cand.getPpcEstatus() == Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion() 	||
						cand.getPpcEstatus() == Catalogos.ESTATUS.INACTIVO_PPC.getIdOpcion()){
							message = CURP_NOUNICO_PPC;
							
					} else {
						message = CURP_NOUNICO;
					}
				} else {
					message = CURP_UNICO;
					
				}

				registroForm.setCurpUnico(unico);

				type = "exito";

				if (!unico) {
					// Si ya existe el CURP se recuperan los datos del candidato
					candidato = delegate.consultaCandidatoPorCURP(registroForm.getCurp());

					if (candidato != null) {
							respuesta.setNombre(candidato.getNombre());
							respuesta.setApellido1(candidato.getApellido1());
							respuesta.setApellido2(candidato.getApellido2());
							respuesta.setIdEntidadNacimiento(candidato.getIdEntidadNacimiento());
							respuesta.setGenero(candidato.getGenero());
							respuesta.setCurp(candidato.getCurp());

							Date fh = candidato.getFechaNacimiento();
							String fhNac = Utils.formatDDMMYYYY(fh);
							candidato.setFechaNacimientoString(fhNac);
							respuesta.setFechaNacimientoString(fhNac);
					}
				} else {
					if (PropertiesLoader.getInstance().getProperty("app.registro.candidato.verifica.curp").equals("MOCK_RENAPO")) {
						respuesta.setNombre(request.getParameter("mockNombre"));
						respuesta.setApellido1(request.getParameter("mockApellido1"));
						respuesta.setApellido2(request.getParameter("mockApellido2"));
						respuesta.setIdEntidadNacimiento(Integer.parseInt(request.getParameter("mockIdEntidadFederativa")));
						respuesta.setGenero(Integer.parseInt(request.getParameter("mockGenero")));
						respuesta.setCurp(request.getParameter("mockCurp"));
						respuesta.setFechaNacimientoString(request.getParameter("mockFechaNacimientoDDMMYYYY"));
					}
				}
			} catch (EJBTransactionRolledbackException e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error interno del Servicio intenta más tarde.";
			}

			catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				type = "error";
				message = "Error al validar el Correo.";
			}
		} else {
			type = "error";
			message = "CURP no indicada.";
		}

		try {
			// ResultVO resultado = new ResultVO(message, type);
			// if (candidato==null) candidato = new CandidatoVo();
			// candidato.setStatusOper(type);
			// candidato.setMessage(message);
			respuesta.setMessage(message);
			respuesta.setStatusOper(type);

			String json = toJson(respuesta);
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private boolean esNSSUnico(String nss) throws ServiceLocatorException {
		CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
		
		boolean unico = delegate.esNSSUnico(nss);
		return unico;
	}

	/*
	 * srojas: ahora ya no se utilizara este metodo, se utilizara
	 * enviaDatosRegistro y hay que poner las validaciones ahi public
	 * ActionForward registrar(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * BusinessException, ServiceLocatorException { ActionForward forward =
	 * getForward(mapping, request, FORDWARD_PERFIL_DATOS);
	 * 
	 * RegistroCandidatoForm registroForm = (RegistroCandidatoForm)form;
	 * 
	 * boolean valido = validaDatosBasicos(registroForm, request);
	 * 
	 * if (!valido){ return forward; }
	 * 
	 * try{ RegistroCandidatoVO registroCandidato = new RegistroCandidatoVO();
	 * BeanUtils.copyProperties(registroCandidato, registroForm);
	 * 
	 * Calendar fechaNacimiento = Calendar.getInstance();
	 * fechaNacimiento.set(registroForm.getAnio(), registroForm.getMes()-1,
	 * registroForm.getDia());
	 * registroCandidato.setFechaNacimiento(fechaNacimiento.getTime());
	 * 
	 * Calendar fechaInicioBusqueda = Calendar.getInstance();
	 * fechaInicioBusqueda.set(registroForm.getAnioBuscar(),
	 * registroForm.getMesBuscar()-1, registroForm.getDiaBuscar());
	 * registroCandidato.setFechaInicioBusqueda(fechaInicioBusqueda.getTime());
	 * 
	 * Integer tipotelefono =
	 * validaTipoTelefono(registroCandidato.getTipoTelefono(),
	 * registroCandidato.getAcceso());
	 * registroCandidato.setTipoTelefono(tipotelefono.intValue());
	 * 
	 * String acceso = validaAcceso(registroCandidato.getTipoTelefono(),
	 * registroCandidato.getAcceso()); registroCandidato.setAcceso(acceso);
	 * 
	 * registroCandidato.setDiscapacidad((int)Constantes.TIPO_DISCAPACIDAD.NINGUNA
	 * .getIdOpcion());
	 * registroCandidato.setIdHabilidades(registroForm.getIdHabilidades());
	 * 
	 * //todo verificar los datos de historialLaboral
	 * mapearHistorialLaboral(registroCandidato,registroForm,0L);
	 * CandidatosRegistroBusDelegate delegate =
	 * CandidatosRegistroBusDelegateImpl.getInstance(); long idCandidato =
	 * delegate.registraCandidato(registroCandidato);
	 * 
	 * registroForm.setIdCandidato(idCandidato);
	 * //delegate.guardarCandidatoHabilidades(idCandidato,
	 * registroForm.getIdHabilidades());
	 * //delegate.borrarRegistrosYRegistrarHistorialLaboral
	 * (registroForm.getHistoriaLaboralVO());
	 * 
	 * if (!registroForm.isRegistroPPC()){ forward = getForward(mapping,
	 * request, FORDWARD_PERFIL_TERMINA); } else { forward = getForward(mapping,
	 * request, FORDWARD_TERMINOS_PPC); }
	 * 
	 * } catch(LoginRepetidoException e) { e.printStackTrace(); logger.error(e);
	 * request.setAttribute(ERROR_MSG, getMensaje(request,
	 * "registro.candidato.exception.LoginRepetidoException")); }
	 * catch(CorreoRepetidoException e) { e.printStackTrace(); logger.error(e);
	 * request.setAttribute(ERROR_MSG, getMensaje(request,
	 * "registro.candidato.exception.CorreoRepetidoException")); } catch
	 * (CurpRepetidoException e) { e.printStackTrace(); logger.error(e);
	 * request.setAttribute(ERROR_MSG, getMensaje(request,
	 * "registro.candidato.exception.CurpRepetidoException")); } catch
	 * (BusinessException e) { e.printStackTrace(); logger.error(e);
	 * request.setAttribute(ERROR_MSG, getMensaje(request,
	 * "notificaciones.exception.BusinessException", e.getMessage())); } catch
	 * (TechnicalException e) { e.printStackTrace(); logger.error(e);
	 * request.setAttribute(ERROR_MSG, getMensaje(request,
	 * "notificaciones.exception.TechnicalException")); }
	 * catch(PersistenceException e){ request.setAttribute(ERROR_MSG,
	 * getMensaje(request, "notificaciones.exception.PersistenceException"));
	 * e.printStackTrace(); logger.error(e); } catch(ServiceLocatorException e){
	 * e.printStackTrace(); logger.error(e); request.setAttribute(ERROR_MSG,
	 * getMensaje(request, "notificaciones.exception.ServiceLocatorException"));
	 * } catch(Exception e){ e.printStackTrace(); logger.error(e);
	 * request.setAttribute(ERROR_MSG, getMensaje(request,
	 * "notificaciones.exception.Exception")); }finally{
	 * registroForm.setNumeroSeguroSocial(null);
	 * registroForm.setCreditoInfonavit(null); }
	 * 
	 * verificaPublicidad(request, registroForm.getCurp(),
	 * TIPO_INGRESO.ALTA.getIdTipoIngreso());
	 * 
	 * return forward; }
	 */
	private void mapearHistorialLaboral(RegistroCandidatoVO registroCandidato, RegistroCandidatoForm registroForm,
			long idCandidato) {

		HistoriaLaboralVO historiaLaboral = new HistoriaLaboralVO();
		historiaLaboral.setIdCandidato(idCandidato);
		historiaLaboral.setEmpresa(registroForm.getNombreSocialEmpresa());

		historiaLaboral.setPuesto(registroForm.getPuestoDesempenado());
		if (Utils.esNumero(registroForm.getSalarioRecibido())) {
			historiaLaboral.setSalarioMensual(Double.valueOf(registroForm.getSalarioRecibido()));
		}

		historiaLaboral.setIdJerarquia(registroForm.getIdJerquia());
		historiaLaboral.setPersonasCargo(registroForm.getIdPersonaACargo());
		historiaLaboral.setFuncion(registroForm.getFuncionesDesempenadas());

		if (Utils.esNumero(registroForm.getAnioLaboresInicial())) {
			historiaLaboral.setAnioLaboresInicial(Integer.valueOf(registroForm.getAnioLaboresInicial()));
		}
		if (Utils.esNumero(registroForm.getMesLaboresInicial())) {
			historiaLaboral.setMesLaboresInicial(Integer.valueOf(registroForm.getMesLaboresInicial()));
		}
		if (Utils.esNumero(registroForm.getDiaLaboresInicial())) {
			historiaLaboral.setDiaLaboresInicial(Integer.valueOf(registroForm.getDiaLaboresInicial()));
		}
		if (Utils.esNumero(registroForm.getAnioLaboresFinal())) {
			historiaLaboral.setAnioLaboresFinal(Integer.valueOf(registroForm.getAnioLaboresFinal()));
		}
		if (Utils.esNumero(registroForm.getMesLaboresFinal())) {
			historiaLaboral.setMesLaboresFinal(Integer.valueOf(registroForm.getMesLaboresFinal()));
		}
		if (Utils.esNumero(registroForm.getDiaLaboresFinal())) {
			historiaLaboral.setDiaLaboresFinal(Integer.valueOf(registroForm.getDiaLaboresFinal()));
		}

		historiaLaboral.setConfidencialidadEmpresa(Long.valueOf(registroForm.getOmitirTrabajoEnPerfil()));
		// private String confidencial;
		// private int omitirTrabajoEnPerfil;
		// solo hay un regisro entonces es el principal
		if (Utils.esNumeroYMasGrandeQue0(registroForm.getDiaLaboresFinal())
				&& Utils.esNumeroYMasGrandeQue0(registroForm.getMesLaboresFinal())
				&& Utils.esNumeroYMasGrandeQue0(registroForm.getAnioLaboresFinal())) {
			String cadenaFecha = registroForm.getDiaLaboresFinal() + "/" + registroForm.getMesLaboresFinal() + "/"
					+ registroForm.getAnioLaboresFinal();
			historiaLaboral.setLaboresFinal(Utils.convertFecha(cadenaFecha));
		}
		if (Utils.esNumeroYMasGrandeQue0(registroForm.getDiaLaboresInicial())
				&& Utils.esNumeroYMasGrandeQue0(registroForm.getMesLaboresInicial())
				&& Utils.esNumeroYMasGrandeQue0(registroForm.getAnioLaboresInicial())) {
			String cadenaFecha = registroForm.getDiaLaboresInicial() + "/" + registroForm.getMesLaboresInicial() + "/"
					+ registroForm.getAnioLaboresInicial();
			historiaLaboral.setLaboresInicial(Utils.convertFecha(cadenaFecha));
		}
		historiaLaboral.setIdExperiencia(Long.valueOf(registroForm.getIdExperienciaTotal()));
		historiaLaboral.setPrincipal(1L);
		registroCandidato.setHistoriaLaboralVO(historiaLaboral);
		//TODO: revisar regla para ver si es trabajo actual o no fechaInicio != null y fechaFin == null
		if(historiaLaboral.getLaboresInicial() != null && historiaLaboral.getLaboresFinal() == null){
			historiaLaboral.setTrabajoActual(Long.valueOf(Constantes.TRABAJO_ACTUAL.SI.getIdOpcion()));
		} else {
			historiaLaboral.setTrabajoActual(Long.valueOf(Constantes.TRABAJO_ACTUAL.NO.getIdOpcion()));
		}

	}

	public ActionForward toMiEspacio(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward = null;

		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		boolean logged = firmaCandidatoPortalEmpleo(registroForm.getIdCandidato(), request, response);

		if (logged) {
			PropertiesLoader properties = PropertiesLoader.getInstance();
			String urlCandidato = properties.getProperty("app.login.redirect.portal.candidato");
			urlCandidato += Utils.codifica(registroForm.getUsuario());

			request.setAttribute(URL_REDIRECT_SWB, urlCandidato);
			forward = mapping.findForward(FORWARD_REDIRECT_SWB);
		} else {
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.candidato.exception.Login"));
			forward = getForward(mapping, request, FORDWARD_PERFIL_TERMINA);
		}
		request.getSession().removeAttribute("registroCandidatoForm");
		return forward;
	}
	
	public ActionForward toMiEspacioRU(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		ActionForward forward = null;
		int idCandidato = Integer.parseInt( (String) request.getSession().getAttribute(ConstantesGenerales.RU_PARAM_ID_PROPIETARIO) );
		String candUser = (String) request.getSession().getAttribute(ConstantesGenerales.RU_PARAM_USER) ;
		boolean logged = firmaCandidatoPortalEmpleo(idCandidato, request, response);

		if (logged) {
			PropertiesLoader properties = PropertiesLoader.getInstance();
			String urlCandidato = properties.getProperty("app.login.redirect.portal.candidato");
			urlCandidato += Utils.codifica(candUser);

			forward = mapping.findForward(FORDWARD_ACTION_CANDIDATO);
		} else {
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.candidato.exception.Login"));
			forward = getForward(mapping, request, FORDWARD_PERFIL_TERMINA);
		}
		request.getSession().removeAttribute("registroCandidatoForm");
		return forward;
	}

	public ActionForward toMisDatos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward = null;

		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		boolean logged = firmaCandidatoPortalEmpleo(registroForm.getIdCandidato(), request, response);

		if (logged) {

			/**
			 * Se establece un parametro para indicar al action del mi espacio
			 * que direccione al TAB de mis datos
			 **/
			PropertiesLoader properties = PropertiesLoader.getInstance();
			String urlMisDatos = properties.getProperty("app.login.redirect.portal.candidato");
			urlMisDatos += Utils.codifica(registroForm.getUsuario()) + "&" + CANDIDATO_REG_MIS_DATOS + "=true";

			request.setAttribute(URL_REDIRECT_SWB, urlMisDatos);
			forward = mapping.findForward(FORWARD_REDIRECT_SWB);
		} else {
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.candidato.exception.Login"));
			forward = getForward(mapping, request, FORDWARD_PERFIL_TERMINA);
		}

		return forward;
	}
	
	public ActionForward toQuebec(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UsuarioWebVO user = getUsuario(request.getSession());
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;
		if (null != user) {
			registroForm.setUsuario(user.getUsuario());
			registroForm.setCorreoElectronico(user.getCorreoElectronico());
			try {
				SecutityDelegate security = SecutityDelegateImpl.getInstance();
				CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
				UsuarioVO userVO = security.consultaUsuario(user.getIdUsuario());
				if (null != userVO) {
					registroForm.setContrasena(userVO.getContrasena());
					registroForm.setNombre(userVO.getNombre());
					registroForm.setApellido1(userVO.getApellido1());
				}
				PerfilVO perfil = services.initPerfil(user.getIdPropietario());
				if (perfil != null)
					registroForm.setGenero(perfil.getIdGenero());
			}catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}else registroForm.setRegistroDirecto(true);
		request.getSession().setAttribute("_TITLE", "Registro para candidatos en Quebec");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_QUEBEC").getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro para candidatos en Quebec");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Registro para candidatos en Quebec, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO);
	}

	private boolean firmaCandidatoPortalEmpleo(long idCandidato, HttpServletRequest request,
			HttpServletResponse response) {
		boolean logged = false;

		try {
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			CandidatoVo candidato = services.consultaCandidato(idCandidato);

			long idUsuario = candidato.getIdUsuario();

			UsuarioVO usuario = services.consultaUsuario(idUsuario);

			LoginAction loginAction = new LoginAction();
			loginAction.firmaUsuarioPortal(request, response, usuario);

			logged = true;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return logged;
	}

	public ActionForward entidades(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_ENTIDAD_FEDERATIVA, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return null;
	}

	public ActionForward dias(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			CatalogoOpcionVO label = new CatalogoOpcionVO();
			label.setIdCatalogoOpcion(0);
			label.setOpcion("Día");
			opciones.add(label);

			for (int i = 1; i <= 31; i++) {
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(i);
				opcion.setOpcion(String.valueOf(i));
				opciones.add(opcion);
			}

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return null;
	}

	public ActionForward meses(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			CatalogoOpcionVO label = new CatalogoOpcionVO();
			label.setIdCatalogoOpcion(0);
			label.setOpcion("Mes");
			opciones.add(label);

			for (MESES mes : MESES.values()) {
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(mes.getIdOpcion());
				opcion.setOpcion(String.valueOf(mes.getIdOpcion()));
				opciones.add(opcion);
			}

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return null;
	}

	public ActionForward anios(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int year = mx.gob.stps.portal.utils.Utils.getAnoActualInt();
			List<CatalogoOpcionVO> opciones = mx.gob.stps.portal.utils.Utils.createOpcionListFromInt(year - 15, year
					- ConstantesGenerales.ANOS_MENOS_QUE_ACTUAL, "Año");
			/*
			 * List<CatalogoOpcionVO> opciones = new
			 * ArrayList<CatalogoOpcionVO>();
			 * 
			 * CatalogoOpcionVO label = new CatalogoOpcionVO();
			 * label.setIdCatalogoOpcion(0); label.setOpcion("Anio");
			 * opciones.add(label);
			 * 
			 * int year = mx.gob.stps.portal.utils.Utils.getAnoActualInt();
			 * 
			 * for (int i=(year-15); i>=
			 * (year-ConstantesGenerales.ANOS_MENOS_QUE_ACTUAL); i--){
			 * CatalogoOpcionVO opcion = new CatalogoOpcionVO();
			 * opcion.setIdCatalogoOpcion(i);
			 * opcion.setOpcion(String.valueOf(i)); opciones.add(opcion); }
			 */
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	public ActionForward aniosactual(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int year = mx.gob.stps.portal.utils.Utils.getAnoActualInt();
			List<CatalogoOpcionVO> opciones = mx.gob.stps.portal.utils.Utils.createOpcionListFromInt(year, year
					- ConstantesGenerales.ANOS_MENOS_QUE_ACTUAL, "Año");

			/*
			 * List<CatalogoOpcionVO> opciones = new
			 * ArrayList<CatalogoOpcionVO>();
			 * 
			 * CatalogoOpcionVO label = new CatalogoOpcionVO();
			 * label.setIdCatalogoOpcion(0); label.setOpcion("Anio");
			 * opciones.add(label);
			 * 
			 * int year = mx.gob.stps.portal.utils.Utils.getAnoActualInt();
			 * 
			 * for (int i=year; i>=
			 * (year-ConstantesGenerales.ANOS_MENOS_QUE_ACTUAL); i--){
			 * CatalogoOpcionVO opcion = new CatalogoOpcionVO();
			 * opcion.setIdCatalogoOpcion(i);
			 * opcion.setOpcion(String.valueOf(i)); opciones.add(opcion); }
			 */
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	public ActionForward aniosbusca(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// List<CatalogoOpcionVO> opciones = new
			// ArrayList<CatalogoOpcionVO>();
			int year = mx.gob.stps.portal.utils.Utils.getAnoActualInt();
			List<CatalogoOpcionVO> opciones = mx.gob.stps.portal.utils.Utils.createOpcionListFromInt(year, year - 10, "Año");

			/*
			 * CatalogoOpcionVO label = new CatalogoOpcionVO();
			 * label.setIdCatalogoOpcion(0); label.setOpcion("Anio");
			 * opciones.add(label); int year =
			 * mx.gob.stps.portal.utils.Utils.getAnoActualInt();
			 * 
			 * for (int i=year; i>= (year-10); i--){ CatalogoOpcionVO opcion =
			 * new CatalogoOpcionVO(); opcion.setIdCatalogoOpcion(i);
			 * opcion.setOpcion(String.valueOf(i)); opciones.add(opcion); }
			 */
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	public ActionForward gradosEstudio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_GRADO_ESTUDIOS, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}

	public ActionForward carreras(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			long idCatDep = Utils.parseLong(request.getParameter("idCatDep"));
			String catalogojson = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogoJSON(idCatDep);
			redirectJsonResponse(response, catalogojson);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward situacionesAcademicas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			long idEscolaridad = Utils.parseLong(request.getParameter("idEscolaridad"));
			long[] filtro = mx.gob.stps.portal.core.infra.utils.Utils.getFiltroSituacionAcademica(idEscolaridad);
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_ESTATUS_GRADO, filtro, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward idiomas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			long[] filtro = { 9 };
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_IDIOMAS, filtro, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward certificaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			long idCatDep = Utils.parseLong(request.getParameter("idCatDep"));

			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance()
					.consultarCatalogo(idCatDep, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward dominios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_DOMINIO, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward experiencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			long[] filtro = { 8 };
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_EXPERIENCIA, filtro, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward ocupacionesDeseadas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_OCUPACION, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward ocupaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			// Checa el tipo de codificacion del request AJAX de Dojo.
			@SuppressWarnings("unused")
			String encoding = request.getCharacterEncoding();
			String catalogo = request.getParameter("search");
			String clickaction = request.getParameter("action");
			CatalogoOpcionDelegate services = CatalogoOpcionDelegateImpl.getInstance();

			String json = "";
			if (clickaction == null || clickaction.equals("")) {
				json = services.obtenerCatalogoDinamico(CATALOGO_OPCION_OCUPACION, catalogo);
			} else {
				json = services.obtenerCatalogoDinamico(CATALOGO_OPCION_OCUPACION, catalogo, clickaction);
			}

			redirectJsonResponse(response, json);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward tiposEmpleo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_TIPO_EMPLEO);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * //Poner la lista de Jeraqui del puesto List<CatalogoOpcionVO> opcJeraquia
	 * = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
	 * CATALOGO_OPCION_JERARQUIA_PUESTO, true);
	 * registroForm.setOpcJeraquia(opcJeraquia); List<CatalogoOpcionVO>
	 * opcPersonasACargo =
	 * CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo
	 * (CATALOGO_OPCION_PERSONAS_CARGO, true);
	 * registroForm.setOpcPersonasACargo(opcPersonasACargo);
	 **/
	public ActionForward jeraquia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opcJeraquia = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_JERARQUIA_PUESTO);
			redirectJsonCatalogo(opcJeraquia, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward personasACargo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opcPersonasACargo = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_PERSONAS_CARGO, true);
			redirectJsonCatalogo(opcPersonasACargo, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward tiposContrato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_TIPO_CONTRATO);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward otrosMedios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(
					CATALOGO_OPCION_OTROS_MEDIOS, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	public ActionForward motivosBuscaTrabajo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			int idTrabaja = Utils.parseInt(request.getParameter("idTrab"));
			RegistroCandidatoForm formAction = (RegistroCandidatoForm) form;
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
			if (!formAction.isRegistroPPC()) {
				if (idTrabaja == Catalogos.TRABAJA_ACTUALMENTE.SI.getIdOpcion())
					opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_SI_TRABAJA);
				else if (idTrabaja == Catalogos.TRABAJA_ACTUALMENTE.NO.getIdOpcion())
					// Get catalog filtered
					opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_NO_TRABAJA, new long[]{Catalogos.RAZON_BUSQUEDA.REPATRIADOS.getIdOpcion()});
			} else {
				// Get catalog filtered
				opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_NO_TRABAJA, new long[]{Catalogos.RAZON_BUSQUEDA.REPATRIADOS.getIdOpcion()});
			}
			redirectJsonCatalogo(opciones, response);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return null;
	}

	public ActionForward medioPortal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_MEDIO_ENTERADO);

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
		}

		return null;
	}

	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}

	// TODO TEMPORAL
	protected CatalogoVO getCatalogo(List<CatalogoOpcionVO> opciones) {
		CatalogoVO catalogo = new CatalogoVO("value", "label");

		if (opciones == null)
			return catalogo;

		for (mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO op : opciones) {
			catalogo.addItem(op.getOpcion(), String.valueOf(op.getIdCatalogoOpcion()),
					String.valueOf(op.getIdCatalogoOpcion()));
		}
		return catalogo;

	}

	private ActionForward getForward(ActionMapping mapping, HttpServletRequest request, String forwardName) {
		HttpSession session = request.getSession();
		session.setAttribute(BODY_JSP, mapping.findForward(forwardName).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro para candidatos");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Registro para candidatos en Quebec, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ request.getRequestURI().toString());
		return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_CANDIDATO);
	}

	public ActionForward validaCaptcha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;
		RespuestaBaseAjaxVO candidato = new RespuestaBaseAjaxVO();

		try {
			if (validateRespuestaUsuario(registroForm.getRespuestaUsuario(), request)) {
				setCaptchaExito(request, candidato);

			} else {
				setCaptchaErroneo(request, candidato);
			}

			String json = toJson(candidato);
			redirectJsonResponse(response, json);
		} catch (TechnicalException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setCaptchaErroneo(HttpServletRequest request, CandidatoVo candidato) {
		candidato.setStatusOper("ERROR_CAPTCHA");
		String msgerr = getMensaje(request, "captcha.respuesta.incorrecta");
		candidato.setMessage(msgerr);
	}

	private void setCaptchaErroneo(HttpServletRequest request, RespuestaBaseAjaxVO baseAjax) {
		baseAjax.setStatusOper("ERROR_CAPTCHA");
		String msgerr = getMensaje(request, "captcha.respuesta.incorrecta");
		baseAjax.setMessage(msgerr);
	}

	private void setCaptchaExito(HttpServletRequest request, RespuestaBaseAjaxVO baseAjax) {
		baseAjax.setStatusOper("captcha");
		String msgerr = getMensaje(request, "captcha");
		baseAjax.setMessage(msgerr);
	}

	public ActionForward verificarCurp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;
		CandidatoVo candidato = new CandidatoVo();

		try {
			if (validateRespuestaUsuario(registroForm.getRespuestaUsuario(), request)) {
				// System.out.println("captcha valido");
				try {
					Calendar fechaNac = Calendar.getInstance();
					fechaNac.set(Calendar.DATE, registroForm.getDia());
					fechaNac.set(Calendar.MONTH, registroForm.getMes() - 1);
					fechaNac.set(Calendar.YEAR, registroForm.getAnio());

					String nombre = registroForm.getNombre();
					String apellido1 = registroForm.getApellido1();
					String apellido2 = registroForm.getApellido2();
					int genero = registroForm.getGenero();
					Date fechaNacimiento = fechaNac.getTime();
					int idEntidadNacimiento = registroForm.getIdEntidadNacimiento();

					if (PropertiesLoader.getInstance().getProperty("app.registro.candidato.verifica.curp").equals("REAL_RENAPO")) {
						CandidatosRegistroBusDelegate candidatosRegistroBusDelegate = CandidatosRegistroBusDelegateImpl.getInstance();
						candidato = candidatosRegistroBusDelegate.consultaCURPPorDatosPersonales(nombre, apellido1, apellido2, genero, fechaNacimiento, idEntidadNacimiento);
					} else if (PropertiesLoader.getInstance().getProperty("app.registro.candidato.verifica.curp").equals("MOCK_RENAPO")) {
						// NOTE: Get the following data from view, in order to not redeploy, just reload resources
						candidato.setMessage(request.getParameter("mockMessage"));
						candidato.setStatusOper(request.getParameter("mockStatusOper"));
						candidato.setCurp(request.getParameter("mockCurp"));
					}

					candidato.setFechaNacimiento(mx.gob.stps.portal.utils.Utils.convert(fechaNacimiento.toString()));

					String estadoEntidad = "";
					ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS
							.getEntidad(candidato.getIdEntidadNacimiento());
					if (entidad != null)
						estadoEntidad = entidad.getDescripcion();

					candidato.setEstadoEntidadString(estadoEntidad);
					registroForm.setCurp(candidato.getCurp());

					// TODO: Uncomment
//				} catch (ConsultaWsPorCurpException e) {
//					e.printStackTrace();
//					logger.error(e);
//					candidato.setStatusOper("exception");
//					candidato.setMessage("Error al invocar el servicio de CURP");
//				}
				}catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					candidato.setStatusOper("exception");
					candidato.setMessage("Error al invocar el servicio de CURP");
				}
				if(registroForm.isRegistroPPC()){
					this.verificarNSS(request, response, registroForm);
				}
			} else {
				// System.out.println("captcha no valido");
				setCaptchaErroneo(request, candidato);
			}
		} catch (TechnicalException e1) {
			e1.printStackTrace();
			logger.error(e1);
			candidato.setStatusOper("exception");
			candidato.setMessage("TechnicalException " + e1.getMessage());
		}

		try{
			String json = toJsonDesdeCandidato(candidato);
			redirectJsonResponse(response, json);
		}catch(Exception e){
			e.printStackTrace(); logger.error(e); 
		}

		return null;
	}

	private String toJsonDesdeCandidato(CandidatoVo candidato) {
		RespuestaBaseAjaxVO repuesta = new RespuestaBaseAjaxVO(candidato.getMessage(),candidato.getStatusOper(),candidato.getCurp());
		return  toJson(repuesta);
	}

	/**
	 * Consultar por Wb de Renapo por datos Personales
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public ActionForward verificarDatosPersonales(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		CandidatoVo candidato = new CandidatoVo();
		RegistroCandidatoForm registroCandidatoForm = (RegistroCandidatoForm) form;
		try {
			if (validateRespuestaUsuario(registroCandidatoForm.getRespuestaUsuario(), request)) {
				// System.out.println("captcha valido");
				try {
					if (PropertiesLoader.getInstance().getProperty("app.registro.candidato.verifica.curp").equals("REAL_RENAPO")) {
						// REAL_RENAPO
						String CURP = request.getParameter("curp");

						//Consulta de datos personales a Renapo a partir de la CURP
						CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
						candidato = delegate.consultaDatosPersonalesPorCURP(CURP);
						if(candidato.getFechaNacimientoString() != null && candidato.getFechaNacimientoString() != ""){
							candidato.setFechaNacimiento(mx.gob.stps.portal.utils.Utils.stringDDMMYYToDate(candidato.getFechaNacimientoString()));
						}

						String estadoEntidad = new String();
						ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(candidato.getIdEntidadNacimiento());
						if (entidad != null) {
							estadoEntidad = entidad.getDescripcion();
						}

						candidato.setEstadoEntidadString(estadoEntidad);

					} else if (PropertiesLoader.getInstance().getProperty("app.registro.candidato.verifica.curp").equals("MOCK_RENAPO")) {
						// MOCK_RENAPO
						candidato.setNombre(request.getParameter("mockNombre"));
						candidato.setApellido1(request.getParameter("mockApellido1"));
						candidato.setApellido2(request.getParameter("mockApellido2"));
						candidato.setIdEntidadNacimiento(Integer.parseInt(request.getParameter("mockIdEntidadFederativa")));
						candidato.setGenero(Integer.parseInt(request.getParameter("mockGenero")));
						candidato.setCurp(request.getParameter("mockCurp"));
						candidato.setFechaNacimientoString(request.getParameter("mockFechaNacimientoDDMMYYYY"));
						candidato.setStatusOper(request.getParameter("mockStatusOper"));
					}
				} catch (ConsultaWsPorCurpException e) {
					e.printStackTrace();
					logger.error(e);
					candidato.setStatusOper("exception");
					candidato.setMessage("Error al invocar el servicio de CURP");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					candidato.setStatusOper("exception");
					candidato.setMessage("Error al invocar el servicio de CURP");
				}
			} else {
				// System.out.println("captcha no valido");
				setCaptchaErroneo(request, candidato);

			}
		} catch (TechnicalException e1) {
			e1.printStackTrace();
		}

		try {
			String json = toJson(candidato);
			redirectJsonResponse(response, json);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		return null;
	}

	private void setDatosRequest(RegistroCandidatoForm form, String data) {
		if (data == null || data.isEmpty()) {
			return;
		}

		data = Utils.decodifica(data);

		StringTokenizer tokens = new StringTokenizer(data, SEPARADOR, false);

		int index = 0;
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();

			index++;
			switch (index) {
			case 1:
				form.setUsuario(token);
				break;
			case 2:
				form.setContrasena(token);
				break;
			case 3:
				form.setConfirmacion(token);
				break;
			case 4:
				form.setCurp(token);
				break;
			case 5:
				form.setNombre(token);
				break;
			case 6:
				form.setApellido1(token);
				break;
			case 7:
				form.setApellido2(token);
				break;
			case 8:
				form.setDia(Utils.parseInt(token));
				break;
			case 9:
				form.setMes(Utils.parseInt(token));
				break;
			case 10:
				form.setAnio(Utils.parseInt(token));
				break;
			case 11:
				form.setIdEntidadNacimiento(Utils.parseInt(token));
				break;
			case 12:
				form.setGenero(Utils.parseInt(token));
				break;
			}// switch
		}// while

	}

	private void verificaPublicidad(HttpServletRequest request, String curp, int idTipoIngreso) {

		if (curp == null || curp.trim().isEmpty())
			return;

		HttpSession session = request.getSession();

		int MAX_LENGTH = 200;

		try {
			CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();

			String parameterName = delegate.consultaNombreParametro();
			String cookieName = delegate.consultaNombreCookie();

			if (parameterName == null)
				return;
			if (cookieName == null)
				return;

			String parameterValue = null;
			String cookieValue = null;

			parameterValue = (String) session.getAttribute(parameterName);

			Cookie cookie = CookieManager.getInstance().findCookie(request, cookieName);
			if (cookie != null)
				cookieValue = cookie.getValue();

			// Almenos se debe tener la cookie o el parametro para registrar
			if ((parameterValue == null || parameterValue.isEmpty()) && (cookieValue == null || cookieValue.isEmpty()))
				return;

			if (parameterValue != null && parameterValue.length() > MAX_LENGTH)
				parameterValue = parameterValue.substring(0, MAX_LENGTH);

			if (cookieValue != null && cookieValue.length() > MAX_LENGTH)
				cookieValue = cookieValue.substring(0, MAX_LENGTH);

			delegate.registraCandidatoPublicidad(curp, cookieValue, parameterValue, idTipoIngreso);

			session.removeAttribute(parameterName);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
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
			e.printStackTrace();
			;
		}

		return captcha;
	}

	private Integer validaTipoTelefono(Integer idTipoTelefono, String acceso) {

		if (idTipoTelefono != null && TIPO_TELEFONO.CELULAR.getIdOpcion() != idTipoTelefono.longValue()
				&& TIPO_TELEFONO.FIJO.getIdOpcion() != idTipoTelefono.longValue()) {
			if (TIPO_TELEFONO.CELULAR.getAcceso().equals(acceso)) {
				idTipoTelefono = (int) TIPO_TELEFONO.CELULAR.getIdOpcion();
			} else if (TIPO_TELEFONO.FIJO.getAcceso().equals(acceso)) {
				idTipoTelefono = (int) TIPO_TELEFONO.FIJO.getIdOpcion();
			} else {
				idTipoTelefono = (int) TIPO_TELEFONO.FIJO.getIdOpcion();
			}
		} else {
			idTipoTelefono = (int) TIPO_TELEFONO.FIJO.getIdOpcion();
		}

		return idTipoTelefono;
	}

	private String validaAcceso(Integer idTipoTelefono, String acceso) {

		if (acceso != null && !TIPO_TELEFONO.CELULAR.getAcceso().equals(acceso)
				&& !TIPO_TELEFONO.FIJO.getAcceso().equals(acceso)) {

			if (idTipoTelefono != null) {
				if (TIPO_TELEFONO.CELULAR.getIdOpcion() == idTipoTelefono.longValue()) {
					acceso = TIPO_TELEFONO.CELULAR.getAcceso();
				} else if (TIPO_TELEFONO.FIJO.getIdOpcion() == idTipoTelefono.longValue()) {
					acceso = TIPO_TELEFONO.FIJO.getAcceso();
				} else {
					acceso = TIPO_TELEFONO.FIJO.getAcceso();
				}
			} else {
				acceso = TIPO_TELEFONO.FIJO.getAcceso();
			}
		} else {
			acceso = TIPO_TELEFONO.FIJO.getAcceso();
		}

		return acceso;
	}

	public ActionForward verificaCurpNss(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		String result = verificarNSS(request, response, registroForm);
		String json = toJson(new String("{\"result\" : \"" + result + "\", \"nss\" : \"" + registroForm.getNss() + "\"}"));
		
		try {
			redirectJsonResponse(response, json);
		} catch (IOException e) {
		
			e.printStackTrace();
			logger.error(e);
	
		}
		return null;
	}

//	srojas: borrar
	private String verificarNSS(HttpServletRequest request, HttpServletResponse response, RegistroCandidatoForm registroForm) {
		
			String result = "";
			String resultErroneo = new String("{\"result\":\"-1\"}");

			try {
				CandidatoBusDelegate delegate = CandidatoBusDelegateImpl.getInstance();
				RESPUESTA_IMMS_CONSULTA_NSS respuestaIMMS = delegate.consultaNssIMMS(registroForm.getCurp(),
						registroForm.getNss());

				if (RESPUESTA_IMMS_CONSULTA_NSS.SERVICIO_NO_DISPONIBLE.equals(respuestaIMMS)) {
					result = IMSS_NOTAVAILABLE;

				} else if (RESPUESTA_IMMS_CONSULTA_NSS.NSS_NO_REGISTRADO.equals(respuestaIMMS)) {
					result = NSS_NOTREG_IN_IMSS;

				} else if (RESPUESTA_IMMS_CONSULTA_NSS.NSS_REGISTRADO.equals(respuestaIMMS)) {
					//srojas
					result = "1";
					
					if (isLogged(request.getSession())){
						
						// Nos vamos a base de datos para ver si esta inscrito al ppc
						CandidatosRegistroBusDelegate delegateCandidato = CandidatosRegistroBusDelegateImpl.getInstance();
						CandidatoVo candidatoVo = delegateCandidato.consultaCandidatoPorCURP(registroForm.getCurp());

//						if (Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion() == candidatoVo.getPpcEstatus()){
//							result = LOGGEDUSER_REG_PPC;
//							
//						} else if (Catalogos.ESTATUS.INACTIVO_PPC.getIdOpcion() == candidatoVo.getPpcEstatus()){
//							result = LOGGEDUSER_REG_PPC;						
//						
//						} else {
//							//srojas: autentica al usuario y envia a terminos y condiciones
//							result = LOGGEDUSER_REG_NOTPPC;
//							result = "srojas";
//							
//						}
						
					} else {						
						// Nos vamos a base de datos para ver si existe en el sistema el candidato loggeado						

					}

					// TODO ASIGNAR VALORES QUE REGRESA EL IMSS
					Integer ppcEstatusIMSS = null;
					Date ppcFechaBajaIMSS = null;
					String ppcTipoContratoIMSS = null;

					registroForm.setPpcEstatusIMSS(ppcEstatusIMSS);
					registroForm.setPpcFechaBajaIMSS(ppcFechaBajaIMSS);
					registroForm.setPpcTipoContratoIMSS(ppcTipoContratoIMSS);

				} else {
					logger.error("Respuesta de consultaNssIMMS "
							+ (respuestaIMMS != null ? respuestaIMMS.getIdOpcion() : "null"));
					result = resultErroneo;
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				result = resultErroneo;
			}

			return result;
		

		
		
	}

	public ActionForward enviaDatosRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws BusinessException, ServiceLocatorException {

		String forwardName = FORDWARD_PERFIL_DATOS;
		request.getSession().removeAttribute("registroCandidatoPPCSDForm");
		try {
			RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

			// Req. Repatriados
			String country = GeoNames.requestCountryCode(registroForm.getPositionCoordsLatitude(), registroForm.getPositionCoordsLongitude());
			registroForm.setCountry(country);
			String acceptLanguage = WebUtils.getAcceptLanguage(request);
			if (acceptLanguage.length() > 20) {
				acceptLanguage = acceptLanguage.substring(0, 20);
			}
			registroForm.setAcceptLanguage(acceptLanguage);
			// ---
			if (!country.startsWith("_")) {
				if (country.equals("Estados Unidos")) {
					registroForm.setMostrarMensajeRepatriados(true);
				}
			} else {
				if (acceptLanguage.toLowerCase().contains("en-us")) {
					registroForm.setMostrarMensajeRepatriados(true);
				}
			}

			// Si es un registro a PPC, reenviamos a Terminos y Condiciones y se
			// pospone el registro definitivo
			request.setAttribute(ATRB_ACEPTATERMINOS_SI, Catalogos.ACEPTACION_TERMINOS.SI);
			request.setAttribute(ATRB_ACEPTATERMINOS_NO, Catalogos.ACEPTACION_TERMINOS.NO);
			if (registroForm.isRegistroPPC()) {
				forwardName = FORDWARD_TERMINOS_PPC;
			} else {	// en caso contrario hacemos el registro del nuevo candidato
				forwardName = registrarPortal(registroForm, request,response);
				firmaCandidatoPortalEmpleo(registroForm.getIdCandidato(), request, response);
				// request.getSession().removeAttribute("registroCandidatoForm");
				if (registroForm.getIdGradoEstudio() == Catalogos.GRADO_ESTUDIOS.PROFESIONAL_TECNICO.getIdOpcion()) {
					registroForm.setCandidatoConalep(true);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
			forwardName = FORDWARD_PERFIL_DATOS;
		}

		return getForward(mapping, request, forwardName);
	}

	private String registrarPortal(RegistroCandidatoForm form, HttpServletRequest request, HttpServletResponse response) {

		String forwardName = FORDWARD_PERFIL_DATOS;

		try {

			boolean valido = validaDatosBasicos(form, request);

			if (!valido) {
				return forwardName;
			}
			
			//srojas: se esta produciendo un error en BeanUtils.copyProperties por el formato de la fecha, confiaremos solo en los campos anio, mes y dia
			form.setFechaNacimiento(null);

			RegistroCandidatoVO registroCandidato = new RegistroCandidatoVO();
			BeanUtils.copyProperties(registroCandidato, form);

			Calendar fechaNacimiento = Calendar.getInstance();
			fechaNacimiento.set(form.getAnio(), form.getMes() - 1, form.getDia());
			registroCandidato.setFechaNacimiento(fechaNacimiento.getTime());

			Calendar fechaInicioBusqueda = Calendar.getInstance();
			fechaInicioBusqueda.set(form.getAnioBuscar(), form.getMesBuscar() - 1, form.getDiaBuscar());
			registroCandidato.setFechaInicioBusqueda(fechaInicioBusqueda.getTime());

			Integer tipotelefono = validaTipoTelefono(registroCandidato.getTipoTelefono(), registroCandidato.getAcceso());
			registroCandidato.setTipoTelefono(tipotelefono.intValue());

			String acceso = validaAcceso(registroCandidato.getTipoTelefono(), registroCandidato.getAcceso());
			registroCandidato.setAcceso(acceso);

			registroCandidato.setDiscapacidad((int) Constantes.TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion());

			registroCandidato.setIdHabilidades(form.getIdHabilidades());

			// validar datos de multiregistro
			registroCandidato.setIdOcupacionDeseada2(form.getIdOcupacionDeseada2());
			registroCandidato.setIdExperiencia2(form.getIdExperiencia2());
			mapearHistorialLaboral(registroCandidato, form, 0L);
			registroCandidato.setDatosConfidenciales(form.getRadioOne());
			CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
			if(form.isRegistroPPC()){
				registroCandidato.setTrabajas((int)Catalogos.TRABAJA_ACTUALMENTE.NO.getIdOpcion());
			}
			long idCandidato = delegate.registraCandidato(registroCandidato);
			form.setIdCandidato(idCandidato);

			form.setCreditoInfonavit(null);
			verificaPublicidad(request, form.getCurp(), TIPO_INGRESO.ALTA.getIdTipoIngreso());

			forwardName = FORDWARD_PERFIL_TERMINA;
			

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG,
					getMensaje(request, "notificaciones.exception.IllegalArgumentException", e.getMessage()));

		} catch (LoginRepetidoException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.candidato.exception.LoginRepetidoException"));

		} catch (CorreoRepetidoException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.candidato.exception.CorreoRepetidoException"));

		} catch (CurpRepetidoException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.candidato.exception.CurpRepetidoException"));

		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG,
					getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage()));

		} catch (TechnicalException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.TechnicalException"));

		} catch (PersistenceException e) {
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.PersistenceException"));
			e.printStackTrace();
			logger.error(e);

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.ServiceLocatorException"));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		}

		return forwardName;

	}

	public ActionForward registrarPortalyPPC(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginRepetidoException, CorreoRepetidoException,
			CurpRepetidoException, BusinessException, TechnicalException, PersistenceException,
			ServiceLocatorException, Exception {

		String forwardName = FORDWARD_PERFIL_DATOS;

		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		try {
			request.setAttribute(ATRB_ACEPTATERMINOS_SI, Catalogos.ACEPTACION_TERMINOS.SI);
			request.setAttribute(ATRB_ACEPTATERMINOS_NO, Catalogos.ACEPTACION_TERMINOS.NO);
			if (registroForm.getNss() == null || registroForm.getNss().trim().isEmpty())
				return getForward(mapping, request, FORDWARD_TERMINOS_PPC);
			
			forwardName = registrarPortal(registroForm, request,response);

			// Si no hay ido bien el registro, retornamos al formulario
			if (!FORDWARD_PERFIL_TERMINA.equals(forwardName))
				return getForward(mapping, request, forwardName);

			CandidatoBusDelegate delegatePPC = CandidatoBusDelegateImpl.getInstance();
			delegatePPC.actualizaRegistroPPC(registroForm.getIdCandidato(), new Date(),
					registroForm.getAceptacionTerminos(), registroForm.getPpcEstatusIMSS(),
					registroForm.getPpcFechaBajaIMSS(), registroForm.getPpcTipoContratoIMSS(), registroForm.getNss());
			
			if (Catalogos.ACEPTACION_TERMINOS.SI.getIdOpcion() == registroForm.getAceptacionTerminos()){
				try{
					// Si han aceptado los terminos enviamos un correo de confirmacion al usuario
	
					delegatePPC.enviaNotificacionInscripcionPPC(registroForm.getIdCandidato());

				} catch (Exception e){
					logger.error("Error en RegistroCandidatoAction.registrarPortalyPPC al enviar la notificacion de inscripcion al PPC");
					e.printStackTrace();
				}
			}

			firmaCandidatoPortalEmpleo(registroForm.getIdCandidato(), request, response);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG,
					getMensaje(request, "notificaciones.exception.IllegalArgumentException", e.getMessage()));

		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG,
					getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage()));

		} catch (PersistenceException e) {
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.PersistenceException"));
			e.printStackTrace();
			logger.error(e);

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.ServiceLocatorException"));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		}

		return getForward(mapping, request, forwardName);
	}

	public ActionForward enviaDatosActualizacion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String forwardName = FORDWARD_PERFIL_DATOS;

		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		try {

			// Si es un registro a PPC, reenviamos a Terminos y Condiciones y se
			// pospone el registro definitivo
			if (registroForm.isRegistroPPC()) {

				request.setAttribute(ATRB_ACEPTATERMINOS_SI, Catalogos.ACEPTACION_TERMINOS.SI);
				request.setAttribute(ATRB_ACEPTATERMINOS_NO, Catalogos.ACEPTACION_TERMINOS.NO);

				forwardName = FORDWARD_TERMINOS_PPC;

				// en caso contrario hacemos el registro del nuevo candidato
			} else {
				forwardName = actualizarPortal(registroForm, request);
				firmaCandidatoPortalEmpleo(registroForm.getIdCandidato(), request, response);
				// request.getSession().removeAttribute("registroCandidatoForm");
				request.setAttribute(ATRB_ACEPTATERMINOS_SI, Catalogos.ACEPTACION_TERMINOS.SI);
				request.setAttribute(ATRB_ACEPTATERMINOS_NO, Catalogos.ACEPTACION_TERMINOS.NO);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
			forwardName = FORDWARD_PERFIL_DATOS;
		}

		return getForward(mapping, request, forwardName);
	}

	private String actualizarPortal(RegistroCandidatoForm form, HttpServletRequest request) {

		String forwardName = FORDWARD_PERFIL_DATOS;

		try {

			boolean valido = validaDatosBasicos(form, request);

			if (!valido) {
				return forwardName;
			}

			if (form.isCurpUnico()) {
				throw new BusinessException("Solo se permite editar los datos de un candidato ya registrado.");
			}

			RegistroCandidatoVO registroCandidato = new RegistroCandidatoVO();
			BeanUtils.copyProperties(registroCandidato, form);

			Calendar fechaNacimiento = Calendar.getInstance();
			fechaNacimiento.set(form.getAnio(), form.getMes() - 1, form.getDia());
			registroCandidato.setFechaNacimiento(fechaNacimiento.getTime());

			Calendar fechaInicioBusqueda = Calendar.getInstance();
			fechaInicioBusqueda.set(form.getAnioBuscar(), form.getMesBuscar() - 1, form.getDiaBuscar());
			registroCandidato.setFechaInicioBusqueda(fechaInicioBusqueda.getTime());

			CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
			CandidatoVo candidato = delegate.consultaCandidatoPorCURP(registroCandidato.getCurp());

			if (candidato == null) {
				throw new BusinessException("No se pudo localizar al candidato mediante la CURP ("
						+ registroCandidato.getCurp() + ").");
			}

			// validar datos de multiregistro
			registroCandidato.setIdOcupacionDeseada2(form.getIdOcupacionDeseada2());
			registroCandidato.setIdExperiencia2(form.getIdExperiencia2());

			long idCandidato = candidato.getIdCandidato();
			form.setIdCandidato(idCandidato);
			Integer tipotelefono = validaTipoTelefono(registroCandidato.getTipoTelefono(),
					registroCandidato.getAcceso());
			registroCandidato.setTipoTelefono(tipotelefono.intValue());

			String acceso = validaAcceso(registroCandidato.getTipoTelefono(), registroCandidato.getAcceso());
			registroCandidato.setAcceso(acceso);

			registroCandidato.setIdHabilidades(form.getIdHabilidades());
			mapearHistorialLaboral(registroCandidato, form, idCandidato);
			registroCandidato.setDatosConfidenciales(form.getRadioOne());
			if (!form.isRegistroPPC()) {
				// Actualizar el registro de ppc porque no se ponen en 0 con el
				// persistence
				// Cambio para que no se actualiza revisando con Nati por flujo
				// no podemos perder los datos
				// CandidatoBusDelegate delegatePPC =
				// CandidatoBusDelegateImpl.getInstance();
				// delegatePPC.actualizaRegistroPPCSinValidacion(form.getIdCandidato(),
				// new Date(), 0, null, null, null, null);

			}

			delegate.actualizaRegistroCandidato(idCandidato, registroCandidato);

			// NOTA debe revisar cuando ingresan con curp buscar el usuario para
			// actualizar los datos
			/**
			 * cambio llevar los datos a la persistencia
			 * delegate.guardarCandidatoHabilidades(idCandidato,
			 * registroForm.getIdHabilidades());
			 * delegate.borrarRegistrosYRegistrarHistorialLaboral
			 * (registroForm.getHistoriaLaboralVO());
			 */

			verificaPublicidad(request, form.getCurp(), TIPO_INGRESO.ACTUALIZACION.getIdTipoIngreso());

			forwardName = FORDWARD_PERFIL_TERMINA;

		} catch (LoginRepetidoException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.candidato.exception.LoginRepetidoException"));

		} catch (CorreoRepetidoException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.candidato.exception.CorreoRepetidoException"));

		} catch (CurpRepetidoException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.candidato.exception.CurpRepetidoException"));

		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG,
					getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage()));

		} catch (TechnicalException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.TechnicalException"));

		} catch (PersistenceException e) {
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.PersistenceException"));
			e.printStackTrace();
			logger.error(e);

		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.ServiceLocatorException"));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		}

		return forwardName;
	}

	public ActionForward actualizarPortalyPPC(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws LoginRepetidoException, CorreoRepetidoException,
			CurpRepetidoException, BusinessException, TechnicalException, PersistenceException,
			ServiceLocatorException, Exception {

		String forwardName = FORDWARD_PERFIL_DATOS;

		RegistroCandidatoForm registroForm = (RegistroCandidatoForm) form;

		try {

			
			if (registroForm.getNss() == null || registroForm.getNss().trim().isEmpty())
				return getForward(mapping, request, FORDWARD_TERMINOS_PPC);

			// registrarPortal(registroForm, request);
			forwardName = actualizarPortal(registroForm, request);

			if (!FORDWARD_PERFIL_TERMINA.equals(forwardName))
				return getForward(mapping, request, forwardName);

			CandidatoBusDelegate delegatePPC = CandidatoBusDelegateImpl.getInstance();
			delegatePPC.actualizaRegistroPPC(registroForm.getIdCandidato(), new Date(),
					registroForm.getAceptacionTerminos(), registroForm.getPpcEstatusIMSS(),
					registroForm.getPpcFechaBajaIMSS(), registroForm.getPpcTipoContratoIMSS(), registroForm.getNss());

			// request.getSession().removeAttribute("registroCandidatoForm");

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.IllegalArgumentException"));
			forwardName = FORDWARD_PERFIL_DATOS;

		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG,
					getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage()));
			forwardName = FORDWARD_PERFIL_DATOS;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
			forwardName = FORDWARD_PERFIL_DATOS;
		}

		return getForward(mapping, request, forwardName);

	}
	
	public ActionForward continuaRegistroByDatosPersonales(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		String result = null;

		RegistroCandidatoForm registroCandidatoForm = (RegistroCandidatoForm)form;		
		
		try{
			
			// Componemos la fecha de nacimiento
			Calendar fechaNacimiento = Calendar.getInstance();
			
			fechaNacimiento.set(Calendar.DATE, registroCandidatoForm.getDia());
			fechaNacimiento.set(Calendar.MONTH, (registroCandidatoForm.getMes() > 0 ? registroCandidatoForm.getMes() - 1 : 0));
			fechaNacimiento.set(Calendar.YEAR, registroCandidatoForm.getAnio());
			
			//Consulta a Renapo para validar los datos
			//srojas: cuando esta el servicio de Renapo disponible, habra que cambiar la respuesta
			CandidatosRegistroBusDelegate delegateCandidatosRegistro = CandidatosRegistroBusDelegateImpl.getInstance();
			CandidatoVo respuestaRenapo = delegateCandidatosRegistro.consultaCURPPorDatosPersonales(
							registroCandidatoForm.getNombre().toUpperCase(),
							registroCandidatoForm.getApellido1().toUpperCase(),
							registroCandidatoForm.getApellido2().toUpperCase(),
							registroCandidatoForm.getGenero(),
							fechaNacimiento.getTime(),
							registroCandidatoForm.getIdEntidad());
			
			if (respuestaRenapo.getCurp() == null || respuestaRenapo.getCurp().trim().isEmpty())
				throw new CurpNotFoundException();
			
			if (respuestaRenapo.getFechaNacimientoString() == null || respuestaRenapo.getFechaNacimientoString().trim().isEmpty())
				throw new CurpNotFoundException();
			
			respuestaRenapo.setFechaNacimiento(mx.gob.stps.portal.utils.Utils.stringDDMMYYToDate(respuestaRenapo.getFechaNacimientoString()));			

			if (respuestaRenapo == null || respuestaRenapo.getCurp() ==  null || respuestaRenapo.getCurp().trim().isEmpty())
				throw new NullPointerException("No se ha recuperado la instancia respuestaRenapo");
				
			String[] respuesta = continuaRegistro(respuestaRenapo.getCurp(), registroCandidatoForm.getNss(), isLogged(request.getSession()));			
			
			result = new String("{\"result\" : \""+respuesta[0]+"\"}");
			
			// curp registrada en sistema pero no en PPC
			if (respuesta[0].equals(NOTLOGGEDUSER_REG_AND_NOTPPC) || respuesta[0].equals(NOTLOGGEDUSER_REG_AND_PPC)){

				if (respuesta[1] != null)
					registroCandidatoForm.setIdCandidato(Long.valueOf(respuesta[1]).longValue());				
				else 
					throw new NullPointerException("No se ha podido recuperar el codigo de usuario");

			datosToRegistroCandidatoForm(respuestaRenapo, registroCandidatoForm);					
			
			} else if (respuesta[0].equals(NOTLOGGEDUSER_NOTREG)){
				datosToRegistroCandidatoForm(respuestaRenapo, registroCandidatoForm);			
			}			

		} catch (CurpNotFoundException e){
			logger.error("No se han encontrado en Renapo los datos personales:");
			logger.error(
					(registroCandidatoForm.getNombre() != null 		? registroCandidatoForm.getNombre()		+" " : new String())	+			
					(registroCandidatoForm.getApellido1() != null 	? registroCandidatoForm.getApellido1()	+" " : new String())	+
					(registroCandidatoForm.getApellido2() != null 	? registroCandidatoForm.getApellido2()	+" " : new String())
					);						
			logger.error(registroCandidatoForm.getGenero());
			logger.error(registroCandidatoForm.getFechaNacimiento() != null ? registroCandidatoForm.getFechaNacimiento() : new String());			
			logger.error((registroCandidatoForm.getEntidadNacimiento() != null ? registroCandidatoForm.getEntidadNacimiento() : new String()));
			result = new String("{\"result\" : \""+DATOS_NOT_RENAPO+"\"}");
			
		} catch (Exception e){
			logger.error("Error en RegistroCandidatoAction.continuaRegistroByDatosPersonales");
			e.printStackTrace();
			result = new String("{\"result\" : \""+RESULT_ERROR+"\"}");
		}

		try{
			String json = toJson(result);
			redirectJsonResponse(response, json);
		} catch (Exception e){
			logger.error("Error en RegistroCandidatoAction.continuaRegistroByDatosPersonales");
			e.printStackTrace();			
		}

		return null;			
	}

	public ActionForward continuaRegistroByCurp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		String result = null;

		RegistroCandidatoForm registroCandidatoForm = (RegistroCandidatoForm)form;

		try{
			
			boolean datosCorrectos = true;
			
			if (registroCandidatoForm.isRegistroPPC()){
				if (registroCandidatoForm.getCurp() == null || registroCandidatoForm.getCurp().isEmpty()){
					datosCorrectos = false;
					result = new String("{\"result\" : \""+DATOS_INCORRECTOS+"\"}");
				}
			}
			
			if (datosCorrectos){
				//Consulta a Renapo para validar los datos
				//srojas: cuando esta el servicio de Renapo disponible, habra que cambiar la respuesta
				CandidatosRegistroBusDelegate delegateCandidatosRegistro = CandidatosRegistroBusDelegateImpl.getInstance();
				CandidatoVo respuestaRenapo = delegateCandidatosRegistro.consultaDatosPersonalesPorCURP(registroCandidatoForm.getCurp());
				
				if (respuestaRenapo.getCurp() == null || respuestaRenapo.getCurp().trim().isEmpty())
					throw new CurpNotFoundException();

				respuestaRenapo.setFechaNacimiento(mx.gob.stps.portal.utils.Utils.stringDDMMYYToDate(respuestaRenapo.getFechaNacimientoString()));
				
				String[] respuesta = continuaRegistro(respuestaRenapo.getCurp(), registroCandidatoForm.getNss(), isLogged(request.getSession()));
				
				result = new String("{\"result\" : \""+respuesta[0]+"\"}");
					
				// curp registrada en sistema pero no en PPC
				if (respuesta[0].equals(NOTLOGGEDUSER_REG_AND_NOTPPC) || respuesta[0].equals(NOTLOGGEDUSER_REG_AND_PPC)){
					// autentica a usuario y presenta terminos y condiciones (paso 6)
					if (respuesta[1] != null)
						registroCandidatoForm.setIdCandidato(Long.valueOf(respuesta[1]).longValue());				
					else 
						throw new NullPointerException("No se ha podido recuperar el codigo de usuario");

					datosToRegistroCandidatoForm(respuestaRenapo, registroCandidatoForm);					
					
				} else if (respuesta[0].equals(NOTLOGGEDUSER_NOTREG)){
					datosToRegistroCandidatoForm(respuestaRenapo, registroCandidatoForm);
					
				}
			}
			
		} catch (CurpNotFoundException e){
			logger.error("No se han encontrado en Renapo la curp :"+(registroCandidatoForm.getCurp() != null ? registroCandidatoForm.getCurp() : new String()));
			result = new String("{\"result\" : \""+CURP_NOT_RENAPO+"\"}");			
			
		} catch (Exception e){
			logger.error("Error en RegistroCandidatoAction.continuaRegistroByCurp");
			e.printStackTrace();
			result = new String("{\"result\" : \""+RESULT_ERROR+"\"}");
		}

		try{
			String json = toJson(result);
			redirectJsonResponse(response, json);
		} catch (Exception e){
			logger.error("Error en RegistroCandidatoAction.continuaRegistroByCurp");
			e.printStackTrace();			
		}

		return null;
	}
	
	private void datosToRegistroCandidatoForm(CandidatoVo datosRenapo , RegistroCandidatoForm form) throws IllegalArgumentException{

		if (datosRenapo == null)
			throw new IllegalArgumentException("El parametro datosRenapo es requerido");

		if (form == null)
			throw new IllegalArgumentException("El parametro form es requerido");
		
		form.setCurp(datosRenapo.getCurp());
		form.setNombre(datosRenapo.getNombre());
		form.setApellido1(datosRenapo.getApellido1());
		form.setApellido2(datosRenapo.getApellido2());
		
		if (datosRenapo.getFechaNacimiento()  == null)
			throw new IllegalArgumentException("El atributo fechaNacimiento es requerido");
		
		form.setFechaNacimiento(datosRenapo.getFechaNacimientoString());
		
		Calendar calFechaNacimiento = Calendar.getInstance();
		calFechaNacimiento.setTime(datosRenapo.getFechaNacimiento());
		form.setDia(calFechaNacimiento.get(Calendar.DATE));		
		form.setMes(calFechaNacimiento.get(Calendar.MONTH));
		form.setAnio(calFechaNacimiento.get(Calendar.YEAR));		
		
		form.setIdEntidadNacimiento(datosRenapo.getIdEntidadNacimiento());		
		form.setGenero(datosRenapo.getGenero());		
	}

	private String[] continuaRegistro(String curp, String nss, boolean isUserLogged) throws ServiceLocatorException, IllegalArgumentException, TechnicalException{
		
		//SROJAS: si usuario logged
		//isUserLogged)
		
		String[] result = new String[2];
		
		// Consultamos si existe en el sistema
		CandidatosRegistroBusDelegate delegateCandidatosRegistro = CandidatosRegistroBusDelegateImpl.getInstance();		
		CandidatoVo candidato = delegateCandidatosRegistro.consultaCandidatoPorCURP(curp);

		String respuestaIMMS = null;
		
		// si nss no esta informado estamos en un registro de PPC
		if (nss != null && !nss.isEmpty())
			respuestaIMMS = consultaNssIMMS(curp, nss);
		
		if (candidato != null && candidato.getIdCandidato() > 0L){

			result[1] = String.valueOf(candidato.getIdCandidato());
			
			// Consultamos si esta registrado en el PPC
			if (Catalogos.ESTATUS.FUERA_PPC.getIdOpcion() == candidato.getPpcEstatus()){
				result[0] = NOTLOGGEDUSER_REG_AND_FUERAPPC;
				
			} else if (isRegistradoPPC(candidato.getPpcEstatus())){
				result[0] = NOTLOGGEDUSER_REG_AND_PPC;

			} else {
				
				if (respuestaIMMS.equals(NSS_REG_IN_IMSS)) {
					result[0] = NOTLOGGEDUSER_REG_AND_NOTPPC;
				} else {
					result[0] = respuestaIMMS;
				}
			}

		} else {
			if (respuestaIMMS.equals(NSS_REG_IN_IMSS)) {
				result[0] = NOTLOGGEDUSER_NOTREG;
			} else {
				result[0] = respuestaIMMS;
			}
		}
		
		return result;
	}
	
	public ActionForward autenticaAutomaticamente(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){		

		String result = null;
		
		RegistroCandidatoForm registroCandidatoForm = (RegistroCandidatoForm)form;
		
		try{
			
			if (registroCandidatoForm.getIdCandidato() <= 0L)
				throw new NullPointerException("No se ha podido recuperar el identificador del candidato");
			
			boolean usuarioFirmado = firmaCandidatoPortalEmpleo(registroCandidatoForm.getIdCandidato(), request, response);
			
			if (usuarioFirmado){			
				result = new String("{\"result\" : \""+RESULT_SUCCESS+"\"}");
				
			} else { 
				logger.error("Error en RegistroCandidatoAction.autenticaAutomaticamente: no se ha podido firmar al candidato idCandidato: "+registroCandidatoForm.getIdCandidato());	
				result = new String("{\"result\" : \""+RESULT_ERROR+"\"}");
			}
		
		} catch (Exception e){
			String msg = "Error en RegistroCandidatoAction.autenticaAutomaticamente, idCandidato: "+registroCandidatoForm.getIdCandidato();
			logger.error(msg);
			e.printStackTrace();
			result = new String("{\"result\" : \""+RESULT_ERROR+"\"}");
		}

		try{
			String json = toJson(result);
			redirectJsonResponse(response, json);
		} catch (Exception e){
			logger.error("Error en RegistroCandidatoAction.autenticaAutomaticamente");
			e.printStackTrace();			
		}		
		
		return null;
	}
	
	public ActionForward deleteRegistroFormFromSession(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		String result = new String("{\"result\" : \""+RESULT_ERROR+"\"}");

		try{					
			request.getSession().removeAttribute("registroCandidatoForm");
			result = new String("{\"result\" : \""+RESULT_SUCCESS+"\"}");
		} catch (Exception e){
			logger.error("Error en RegistroCandidatoAction.deleteRegistroFormFromSession");
			e.printStackTrace();
		}
		 
		try{
			String json = toJson(result);
			redirectJsonResponse(response, json);
		} catch (Exception e){
			logger.error("Error en RegistroCandidatoAction.continuaRegistroByDatosPersonales");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ActionForward showTerminosCondiciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {				

		
		RegistroCandidatoForm registroCandidatoForm = (RegistroCandidatoForm)form;

		try{			

//			srojas: pendiente redireccionar
//			if (registroCandidatoForm.getCurp() == null || ((RegistroCandidatoPPCSDForm)form).getCurp().trim().isEmpty()){
//				logger.info("No se ha podido recuperar la curp del candidato en sesion");
//				response.sendRedirect(request.getContextPath()+"/registroCandidatoPPCSD.do?method=init");
//				return null;				
//			}
//			
//			if (((RegistroCandidatoPPCSDForm)form).getNss() == null || ((RegistroCandidatoPPCSDForm)form).getNss().trim().isEmpty()){
//				logger.info("No se ha podido recuperar el nss del candidato en sesion");
//				response.sendRedirect(request.getContextPath()+"/registroCandidatoPPCSD.do?method=init");
//				return null;				
//			}
//	
//			if (((RegistroCandidatoPPCSDForm)form).getIdCandidato() <= 0L){
//				logger.info("No se ha podido recuperar idCandidato del candidato en sesion");
//				response.sendRedirect(request.getContextPath()+"/registroCandidatoPPCSD.do?method=init");
//				return null;				
//			}
			
			request.setAttribute("curp", registroCandidatoForm.getCurp());
			request.setAttribute("nss", registroCandidatoForm.getNss());
			request.setAttribute("idCandidato", String.valueOf(registroCandidatoForm.getIdCandidato()));
			
			request.setAttribute("aceptaTerminosSi", Catalogos.ACEPTACION_TERMINOS.SI);
			request.setAttribute("aceptaTerminosNo", Catalogos.ACEPTACION_TERMINOS.NO);		
			
			request.getSession().removeAttribute("registroCandidatoForm");

			return getForward(mapping, request, FORDWARD_REGPPC_TERMINOS_PPC);
		} catch (Exception e){
			logger.error("Ha ocurrido un error en showTerminosCondiciones");			
			e.printStackTrace();
			return null;
		}
	}	
	
	public ActionForward toIniciaRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroCandidatoAction.toIniciaRegistro");
		return getForward(mapping, request, FORDWARD_PERFIL_CURP);
	}
		
	private String consultaNssIMMS(String curp, String nss) throws IllegalArgumentException, TechnicalException, ServiceLocatorException{
		
		CandidatoBusDelegate delegateCandidato = CandidatoBusDelegateImpl.getInstance();
		RESPUESTA_IMMS_CONSULTA_NSS respuestaIMMS = delegateCandidato.consultaNssIMMS(curp, nss);			
		
		if (RESPUESTA_IMMS_CONSULTA_NSS.SERVICIO_NO_DISPONIBLE.equals(respuestaIMMS)){
			return IMSS_NOTAVAILABLE;
			
		} else if (RESPUESTA_IMMS_CONSULTA_NSS.NSS_NO_REGISTRADO.equals(respuestaIMMS)){
			return NSS_NOTREG_IN_IMSS;
			
		} else if (RESPUESTA_IMMS_CONSULTA_NSS.NSS_REGISTRADO.equals(respuestaIMMS)){
			return NSS_REG_IN_IMSS;
			
		} else{
			String msg = "Error en la respuesta consultaNssIMMS, respuestaIMMS: "+(respuestaIMMS != null ? respuestaIMMS.getOpcion() : "");
			logger.error(msg);
			throw new IllegalArgumentException(msg);
		}
		
	}

	private boolean isRegistradoPPC(int ppcEstatus) throws IllegalArgumentException{
		
		if (ppcEstatus < 0)
			throw new IllegalArgumentException("El parametro ppcEstatus es requerido");
		
		if (ppcEstatus == Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion())
			return true;
		else if (ppcEstatus == Catalogos.ESTATUS.INACTIVO_PPC.getIdOpcion())		
			return true;

		return false;
	}
	
	public ActionForward keepQuebecApplication(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		String result = new String("{\"result\" : \""+RESULT_ERROR+"\"}");
		try {	
			UsuarioWebVO user = getUsuario(request.getSession());
			CandidatosRegistroBusDelegate delegate = CandidatosRegistroBusDelegateImpl.getInstance();
			int persist = delegate.persistCandidatoQuebec(user.getIdPropietario());
			if (persist > 0) {
				result = new String("{\"result\" : \""+RESULT_SUCCESS+"\"}");
				request.getSession().setAttribute("_quebec", "done");
			}
		} catch (Exception e){
			logger.error("Error in keepQuebecApplication");
			e.printStackTrace();
		}
		try{
			String json = toJson(result);
			redirectJsonResponse(response, json);
		} catch (Exception e){
			logger.error("Error en keepQuebecApplication to json");
			e.printStackTrace();
		}
		return null;
	}
}
