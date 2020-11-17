package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_MEDIO_ENTERADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_EMPRESA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_SOCIEDAD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.*;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAPTCHA_OBJ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.empresa.vo.RegistroEmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_TELEFONO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.utils.Password;
import mx.gob.stps.portal.utils.Catalogos.TIPO_EMPRESA;
import mx.gob.stps.portal.utils.Catalogos.WS_CURP_ERROR;
import mx.gob.stps.portal.utils.Catalogos.WS_CURP_OPERACION;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegate;
import mx.gob.stps.portal.web.company.delegate.EmpresaRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.RegistroEmpresaForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Constantes.MESES;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.action.LoginAction;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.ws.captcha.CaptchaLogicoObject;
import mx.gob.stps.portal.ws.renapo.vo.CurpVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class RegistroEmpresaAction extends GenericAction{

	private static Logger logger = Logger.getLogger(RegistroEmpresaAction.class);
	private static final String FORWARD_BENEFICIOS = "JSP_BENEFICIOS";	
	private static final String FORWARD_PREREGISTRO =  "JSP_PREREGISTRO";
	private static final String FORWARD_REGISTRO =  "JSP_REGISTRO";
	private static final String FORWARD_BIENVENIDA =  "JSP_BIENVENIDA_EMPRESA";
	private static final String FORWARD_DONDE_PUBLICAR_OFERTA = "JSP_DONDE_PUBLICAR_OFERTA";
	private static final String FORWARD_ACTION_EMPRESA_ESPACIO 		= "ACTION_EMPRESA";
	public static final String EXISTEN_OFERTAS_ACTIVAS = "existenOfertasActivas";
	public static final String SI = "Si";
	public static final String NO = "No";
	private static final String ERROR_MSG = "ERROR_MSG";
	private static final String VALOR_DEFAULT_PAGINA_WEB = "http://";
	private static final int NO_CONOZCO_MI_CURP = 1;
	private static final int SI_CONOZCO_MI_CURP = 2;
//	private CaptchaLogicoObject captcha;

	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;
		registroForm.reset();
		
		PropertiesLoader properties = PropertiesLoader.getInstance();		
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.home"));		

		inicializarValoresPorDefectoTipoEmpresa(registroForm);
		
		registroForm.setAccesoCelular(Constantes.CLAVE_TELEFONO_CELULAR);
		registroForm.setAccesoFijo(Constantes.CLAVE_TELEFONO_FIJO);
		registroForm.setIdTipoTelefonoCelular(Constantes.TELEFONO_CELULAR);
		registroForm.setIdTipoTelefonoFijo(Constantes.TELEFONO_FIJO);
		registroForm.setUrlTerminosCondiciones(Constantes.TERMINOS_CONDICIONES);
		registroForm.setPaginaWeb(VALOR_DEFAULT_PAGINA_WEB);

		inicializarUrlRecuperaContrasena(registroForm);
		inicializarEtiquetasRazonSocial(registroForm);
		inicializarConstantesComparablesTipoEmpresa(request);
//		inicializarCaptcha(registroForm);
		CaptchaLogicoObject captcha = getCaptcha(request);
		registroForm.setPregunta(captcha.getPregunta());
		
		try {
			registroForm.setPermisoGeolocalizacion(delegate.consultarPermisoGeolocalizacionRegistro());
		} catch (Exception e) {
			logger.error("Error al cargar el permiso de geolocalizacion en el registro", e);
		}

		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.init");
		return getForward(mapping, request, FORWARD_BENEFICIOS);
	}
	
	
	private void inicializarValoresPorDefectoTipoEmpresa(RegistroEmpresaForm registroForm){
		/*Los valores por defecto acordados son: Para tipo de empresa "Privada", para tipo 
		 * de persona "Moral". En caso de que el usuario seleccione tipo de persona "Fisica", 
		 * el valor por defecto para "conozco mi curp" sera "si". Pero para el juego de datos
		 * empresa "privada" de tipo "moral", lo logico es que el valor de "conoces tu curp" sea 
		 * "no", porque no se utiliza.
		 * */		
		registroForm.setIdTipoEmpresa(Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa());
		registroForm.setIdTipoPersona(Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona());
		registroForm.setIdConocesTuCurp(NO_CONOZCO_MI_CURP);		
	}
	
	
	private void inicializarUrlRecuperaContrasena(RegistroEmpresaForm registroForm){		
		PropertiesLoader properties = PropertiesLoader.getInstance();
		String strUrlRecuperaContrasena = properties.getProperty("app.swb.redirect.login.recuperar");
		registroForm.setUrlRecuperaContrasena(strUrlRecuperaContrasena);	
	}
	
	
	private void inicializarEtiquetasRazonSocial(RegistroEmpresaForm registroForm){
		registroForm.setEtiquetaEmpresaMoral("Razón social");
		registroForm.setEtiquetaEmpresaOng("Nombre de la organización");
		registroForm.setEtiquetaEmpresaPublica("Nombre de empresa/Institución pública");
	}
	
	
	private void inicializarConstantesComparablesTipoEmpresa(HttpServletRequest request){
		request.getSession().setAttribute("tipoEmpresaPrivada", TIPO_EMPRESA.PRIVADA.getTipoEmpresa());
		request.getSession().setAttribute("tipoEmpresaPublica", TIPO_EMPRESA.PUBLICA.getTipoEmpresa());
		request.getSession().setAttribute("tipoEmpresaOng", TIPO_EMPRESA.ONG.getTipoEmpresa());
	}
	
	
//	private void inicializarCaptcha(RegistroEmpresaForm form){
//
//		try {
//			RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;
//			captcha = new CaptchaLogicoObject();
//			registroForm.setPregunta(captcha.getPregunta());
//
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (mx.gob.stps.portal.exception.BusinessException e) {
//			e.printStackTrace();
//		} catch (mx.gob.stps.portal.exception.TechnicalException e) {
//			e.printStackTrace();
//		}
//	}

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
		}

		return captcha;
	}

	public ActionForward cancelar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.home"));
		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.cancelar");
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}	

	
	public ActionForward toRecuperaContrasena(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.login.recuperar"));
		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.toRecuperaContrasena");
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}	

	
	public ActionForward toPreregistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: Registro para empresas");
		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.toPreregistro");
		return getForward(mapping, request, FORWARD_PREREGISTRO);
	}

	
	public ActionForward toRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {	
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;
		registroForm.setCorreoElectronico(registroForm.getUsuario());
		registroForm.setCuentaRegenerada(false);
//		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: Registro para empresas");
		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.toRegistro");
		return getForward(mapping, request, FORWARD_REGISTRO);
	}			

	
	public ActionForward toBienvenida(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;
		registroForm.setUsuario((String)request.getAttribute("user"));
		registroForm.setContrasena((String)request.getAttribute("password"));
		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.toBienvenida");
		return getForward(mapping, request, FORWARD_BIENVENIDA);
	}

	
	public ActionForward toRegeneracion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {	
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;
		registroForm.setCorreoElectronico(registroForm.getUsuario());
		registroForm.setCuentaRegenerada(true);
		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.toRegeneracion");
		return getForward(mapping, request, FORWARD_REGISTRO);
	}

	
	public ActionForward toMiEspacio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;
		request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, NO);
		
		long idEmpresa = registroForm.getIdEmpresa();
		boolean empresaFirmada = firmaEmpresaPortalEmpleo(idEmpresa, request, response);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		ActionForward forward = null;	
		
		if (empresaFirmada){
			request.getSession().setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
			request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.login.redirect.portal.empresa")); 
			forward = mapping.findForward(FORWARD_BIENVENIDA);
		} else {
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.empresa.exception.Login"));
			forward = getForward(mapping, request, FORWARD_INICIO);
		}

		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.toMiEspacio");
		return forward;
	} 

	
	public ActionForward toMiEspacioRU(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		
		request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, NO);
		boolean empresaFirmada = false;
		
		int idEmpresa = Integer.parseInt( (String) request.getSession().getAttribute(ConstantesGenerales.RU_PARAM_ID_PROPIETARIO) );
		
		
		empresaFirmada = firmaEmpresaPortalEmpleo(idEmpresa, request, response);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		ActionForward forward = null;	
		
		if (empresaFirmada){
			request.getSession().setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
			request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.login.redirect.portal.empresa")); 
			forward = mapping.findForward(FORWARD_ACTION_EMPRESA_ESPACIO);
		} else {
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.empresa.exception.Login"));
			forward = getForward(mapping, request, FORWARD_INICIO);
		}

		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.toMiEspacio");
		return forward;
	} 
	
	public ActionForward toMisDatos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;		
		boolean empresaFirmada = firmaEmpresaPortalEmpleo(registroForm.getIdEmpresa(), request, response);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		ActionForward forward = null;
		
		if (empresaFirmada){
			request.getSession().setAttribute(TAB_MENU, TAB_MIS_OFERTAS);
			request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.login.redirect.portal.empresa")); 
			forward = mapping.findForward(FORWARD_DONDE_PUBLICAR_OFERTA);
		
		} else {
			request.setAttribute(ERROR_MSG, getMensaje(request, "registro.empresa.exception.Login"));
			forward = getForward(mapping, request, FORWARD_REDIRECT_SWB);
		}

		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.toMisDatos");
		return forward;
	}	

	
	private boolean firmaEmpresaPortalEmpleo(long idEmpresa, HttpServletRequest request, HttpServletResponse response){
		boolean logged = false;
		
		try{
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			EmpresaVO empresa = services.consultaEmpresa(idEmpresa);
			UsuarioVO usuario = services.consultaUsuario(empresa.getIdUsuario());
			LoginAction loginAction = new LoginAction();
			loginAction.firmaUsuarioPortal(request, response, usuario);
			logged = true;
			
		}catch(Exception e){
			e.printStackTrace(); 
			logger.error(e);
		}		
		return logged;
	}	
	
		
	private Calendar construirFecha(RegistroEmpresaForm registroForm){
		Calendar fecha = Calendar.getInstance();
		int mes  = registroForm.getMes()-1;
		fecha.set(registroForm.getAnio(), mes, registroForm.getDia());		
		return fecha;
	}
	
	
	private Date getFecha(int dia, int mes, int anio) {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(anio, mes-1, dia);
		return cal1.getTime();
	}	

	
	public ActionForward regenerar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws BusinessException, ServiceLocatorException {
		ActionForward forward = getForward(mapping, request, FORWARD_REGISTRO);
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;
		Calendar fechaActaONacimiento = null;
				
		try{
			RegistroEmpresaVO registroEmpresa  = new RegistroEmpresaVO();
			BeanUtils.copyProperties(registroEmpresa, registroForm);	
			
			if(registroEmpresa.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa() 
					&& registroEmpresa.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				Calendar fechaNacimiento = construirFecha(registroForm);				
				registroEmpresa.setFechaNacimiento(fechaNacimiento.getTime());
				registroEmpresa.setFechaActa(null);
				fechaActaONacimiento = fechaNacimiento;
				
				if(!validarFechaActaONacimiento(registroEmpresa, fechaActaONacimiento)){
					request.setAttribute(ERROR_MSG, "Debe seleccionar la fecha de nacimiento");
				}				
				
			} else if(registroEmpresa.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa() 
					&& registroEmpresa.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
				
				Calendar fechaActa = construirFecha(registroForm);
				registroEmpresa.setFechaActa(fechaActa.getTime());		
				registroEmpresa.setFechaNacimiento(null);
				fechaActaONacimiento = fechaActa;	
				
				if(!validarFechaActaONacimiento(registroEmpresa, fechaActaONacimiento)){
					request.setAttribute(ERROR_MSG, "Debe seleccionar la fecha de acta constitutiva");
				}			
				
			} else if(registroEmpresa.getIdTipoEmpresa() != Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()) {
				
				Calendar fechaActa = Calendar.getInstance();
				registroEmpresa.setFechaActa(fechaActa.getTime());	
				registroEmpresa.setFechaNacimiento(null);
			}			
			
			if(registroForm.getIdTipoActividadEconomica()>0){
				registroEmpresa.setIdActividadEconomica(registroForm.getIdTipoActividadEconomica());
			}			
			if(!registroForm.getUsuario().equalsIgnoreCase(registroForm.getCorreoElectronico())){
				registroForm.setUsuario(registroForm.getCorreoElectronico());
			}						
			if(registroEmpresa.getPaginaWeb().equalsIgnoreCase(VALOR_DEFAULT_PAGINA_WEB)){
				registroEmpresa.setPaginaWeb(null);
			}			
			
			if(registroEmpresa.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PUBLICA.getTipoEmpresa()){
				
				registroEmpresa.setRazonSocial(registroForm.getRazonSocialPublica());
				
			} else if(registroEmpresa.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.ONG.getTipoEmpresa()){
				
				registroEmpresa.setRazonSocial(registroForm.getRazonSocialOng());
				
			} else if(registroEmpresa.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()
					&& registroEmpresa.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()) {
				
				registroEmpresa.setRazonSocial(registroForm.getRazonSocial());
			}			

			registroEmpresa.setAcceso(registroForm.getAcceso());
			registroEmpresa.setClave(registroForm.getClave());
			registroEmpresa.setExtension(registroForm.getExtension());
			registroEmpresa.setTipoTelefono(registroForm.getTipoTelefono());
			registroEmpresa.setTelefono(registroForm.getTelefono());
			
			Integer tipotelefono = validaTipoTelefono((int)registroForm.getIdTipoTelefono(), registroForm.getAcceso());
			registroForm.setIdTipoTelefono(tipotelefono.longValue());
			
			String acceso = validaAcceso((int)registroForm.getIdTipoTelefono(), registroForm.getAcceso());
			registroForm.setAcceso(acceso);

			registroForm.setTelefonos(setTelefonos(registroForm.getTotalTelefonosAdicionales(),request, registroForm));
			registroEmpresa.setListaTelefonos(registroForm.getTelefonos());
					
			EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
			long idEmpresa = delegate.regeneraEmpresa(registroEmpresa);
			registroEmpresa.setIdEmpresa(idEmpresa);
			registroForm.setIdEmpresa(idEmpresa);
			EmpresaVO nuevaEmpresaVo = delegate.consultaEmpresa(idEmpresa);
			registroForm.setIdPortalEmpleo(nuevaEmpresaVo.getIdPortalEmpleo());	
			
			if(nuevaEmpresaVo.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PUBLICA.getTipoEmpresa()){
				
				registroForm.setFechaActa(Utils.converterDate(nuevaEmpresaVo.getFechaActa())); 
				registroForm.setRazonSocial(nuevaEmpresaVo.getRazonSocial());
				
			} else if(nuevaEmpresaVo.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.ONG.getTipoEmpresa()){
				
				registroForm.setFechaActa(Utils.converterDate(nuevaEmpresaVo.getFechaActa()));
				registroForm.setRazonSocial(nuevaEmpresaVo.getRazonSocial());

			} else if(nuevaEmpresaVo.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()
					&& nuevaEmpresaVo.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()) {
				
				registroForm.setFechaActa(Utils.converterDate(nuevaEmpresaVo.getFechaActa()));
				registroForm.setRazonSocial(nuevaEmpresaVo.getRazonSocial());
				
			} else if(nuevaEmpresaVo.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()
					&& nuevaEmpresaVo.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()) {
				
				registroForm.setFechaNacimiento(Utils.converterDate(nuevaEmpresaVo.getFechaNacimiento()));
				
				registroForm.setNombre(nuevaEmpresaVo.getNombre());
				registroForm.setApellido1(nuevaEmpresaVo.getApellido1());
				registroForm.setApellido2(nuevaEmpresaVo.getApellido2());
			}				
			
			forward = getForward(mapping, request, FORWARD_BIENVENIDA);			
		
		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage()));
		} catch (TechnicalException e) {
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.TechnicalException"));			
		} catch(PersistenceException e){
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.PersistenceException"));
			e.printStackTrace(); logger.error(e);
		} catch(ServiceLocatorException e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.ServiceLocatorException"));		
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		}

		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.regenerar");
		return forward;		
	}

	
	private boolean validarFechaActaONacimiento(RegistroEmpresaVO registroEmpresa, Calendar fechaActaONacimiento){
		boolean esFechaCorrecta = false;
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		esFechaCorrecta = registroEmpresa.getFechaActa()!=null||registroEmpresa.getFechaNacimiento()!=null;
		
		if(!esFechaCorrecta){
			return esFechaCorrecta;
			
		} else {
			if(registroEmpresa.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
				esFechaCorrecta = fechaActaONacimiento.get(Calendar.YEAR)>=year-90;
			}
				
			if(registroEmpresa.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				esFechaCorrecta = fechaActaONacimiento.get(Calendar.YEAR)>=year-90&&fechaActaONacimiento.get(Calendar.YEAR)<=year-15;
			}					
			return esFechaCorrecta;			
		}
	}
	
	
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws BusinessException, ServiceLocatorException {
		ActionForward forward = getForward(mapping, request, FORWARD_REGISTRO);
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;
		Calendar fechaActaONacimiento = null;
		
		try{
			
			RegistroEmpresaVO registroEmpresa  = new RegistroEmpresaVO();
						
			BeanUtils.copyProperties(registroEmpresa, registroForm);
						
			if(registroEmpresa.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa() 
					&& registroEmpresa.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
				
				Calendar fechaNacimiento = construirFecha(registroForm);				
				registroEmpresa.setFechaNacimiento(fechaNacimiento.getTime());
				registroEmpresa.setFechaActa(null);
				fechaActaONacimiento = fechaNacimiento;		
				if(!validarFechaActaONacimiento(registroEmpresa, fechaActaONacimiento)){
					request.setAttribute(ERROR_MSG, "Debe seleccionar la fecha de nacimiento");
				}			
								
			} else if(registroEmpresa.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa() 
					&& registroEmpresa.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
				
				Calendar fechaActa = construirFecha(registroForm);
				registroEmpresa.setFechaActa(fechaActa.getTime());		
				registroEmpresa.setFechaNacimiento(null);
				fechaActaONacimiento = fechaActa;
				if(!validarFechaActaONacimiento(registroEmpresa, fechaActaONacimiento)){
					request.setAttribute(ERROR_MSG, "Debe seleccionar la fecha de acta constitutiva");
				}				
				
			} else if(registroEmpresa.getIdTipoEmpresa() != Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()) {
				
				Calendar fechaActa = Calendar.getInstance();
				registroEmpresa.setFechaActa(fechaActa.getTime());	
				registroEmpresa.setFechaNacimiento(null);
			}
						
			if(registroForm.getIdTipoActividadEconomica()>0){
				registroEmpresa.setIdActividadEconomica(registroForm.getIdTipoActividadEconomica());
			}

			if(registroEmpresa.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PUBLICA.getTipoEmpresa()){
				
				registroEmpresa.setRazonSocial(registroForm.getRazonSocialPublica());
				
			} else if(registroEmpresa.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.ONG.getTipoEmpresa()){
				
				registroEmpresa.setRazonSocial(registroForm.getRazonSocialOng());
				
			} else if(registroEmpresa.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()
					&& registroEmpresa.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()) {
				
				registroEmpresa.setRazonSocial(registroForm.getRazonSocial());
			}
			
			
			if(registroEmpresa.getPaginaWeb().equalsIgnoreCase(VALOR_DEFAULT_PAGINA_WEB)){
				registroEmpresa.setPaginaWeb(null);
			}			
			
			registroEmpresa.setAcceso(Constantes.CLAVE_TELEFONO_FIJO);
			registroEmpresa.setClave(registroForm.getClave());
			registroEmpresa.setExtension(registroForm.getExtension());
			registroEmpresa.setTipoTelefono(Constantes.TELEFONO_FIJO);
			registroEmpresa.setTelefono(registroForm.getTelefono());

			Integer tipotelefono = validaTipoTelefono((int)registroForm.getIdTipoTelefono(), registroForm.getAcceso());
			registroForm.setIdTipoTelefono(tipotelefono.longValue());
			
			String acceso = validaAcceso((int)registroForm.getIdTipoTelefono(), registroForm.getAcceso());
			registroForm.setAcceso(acceso);

			registroForm.setTelefonos(setTelefonos(registroForm.getTotalTelefonosAdicionales(),request, registroForm));
			registroEmpresa.setListaTelefonos(registroForm.getTelefonos());
			
			
			EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
			long idEmpresa = delegate.registraEmpresa(registroEmpresa);
			
			registroEmpresa.setIdEmpresa(idEmpresa);
			registroForm.setIdEmpresa(idEmpresa);
			EmpresaVO nuevaEmpresaVo = delegate.consultaEmpresa(idEmpresa);
			registroForm.setIdPortalEmpleo(nuevaEmpresaVo.getIdPortalEmpleo());						
			
			if(nuevaEmpresaVo.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PUBLICA.getTipoEmpresa()){
				
				registroForm.setFechaActa(Utils.converterDate(nuevaEmpresaVo.getFechaActa())); 
				registroForm.setRazonSocial(nuevaEmpresaVo.getRazonSocial());
				
			} else if(nuevaEmpresaVo.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.ONG.getTipoEmpresa()){
				
				registroForm.setFechaActa(Utils.converterDate(nuevaEmpresaVo.getFechaActa()));
				registroForm.setRazonSocial(nuevaEmpresaVo.getRazonSocial());

			} else if(nuevaEmpresaVo.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()
					&& nuevaEmpresaVo.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()) {
				
				registroForm.setFechaActa(Utils.converterDate(nuevaEmpresaVo.getFechaActa()));
				registroForm.setRazonSocial(nuevaEmpresaVo.getRazonSocial());
				
			} else if(nuevaEmpresaVo.getIdTipoEmpresa() == Catalogos.TIPO_EMPRESA.PRIVADA.getTipoEmpresa()
					&& nuevaEmpresaVo.getIdTipoPersona() == Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()) {
				
				registroForm.setFechaNacimiento(Utils.converterDate(nuevaEmpresaVo.getFechaNacimiento()));
				
				registroForm.setNombre(nuevaEmpresaVo.getNombre());
				registroForm.setApellido1(nuevaEmpresaVo.getApellido1());
				registroForm.setApellido2(nuevaEmpresaVo.getApellido2());
			}		
			
			forward = getForward(mapping, request, FORWARD_BIENVENIDA);
			
		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.BusinessException", e.getMessage()));
		} catch (TechnicalException e) {
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.TechnicalException"));			
		} catch(PersistenceException e){
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.PersistenceException"));
			e.printStackTrace(); logger.error(e);
		} catch(ServiceLocatorException e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.ServiceLocatorException"));		
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
			request.setAttribute(ERROR_MSG, getMensaje(request, "notificaciones.exception.Exception"));
		}

		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.registrar");
		return forward;
	}
	
	
	public ActionForward notificaRegistroEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;
		String message = "";
		String type = "";		
		String correoElectronico = registroForm.getCorreoElectronico();
		String usuario = registroForm.getUsuario();
		String contrasena = registroForm.getContrasena();
		long idEmpresa  = registroForm.getIdEmpresa();
		String idPortalEmpleo = registroForm.getIdPortalEmpleo();
		
		if (correoElectronico!=null && !correoElectronico.isEmpty() && 
				Utils.validaMail(correoElectronico) &&
				usuario!=null && !usuario.isEmpty() && 
				contrasena!=null && !contrasena.isEmpty()){

			try {
				EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
				EmpresaVO empresaVo = delegate.consultaEmpresa(idEmpresa);

				delegate.notificaRegistroEmpresa(empresaVo.getIdUsuario(), idEmpresa, empresaVo.getEstatus(), 
						empresaVo.getNombreEmpresa(), correoElectronico, contrasena, idPortalEmpleo, "Notificacion de Registro de Empresa");

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

	public ActionForward notificacionRecuperacionPswEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String type = "";
		String message = "";
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;		
		String correoElectronico = registroForm.getCorreoElectronico();
		String usuario = registroForm.getUsuario();
		long idEmpresa  = registroForm.getIdEmpresa();
		String contrasena = registroForm.getContrasena();
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
	
	public ActionForward telefonoAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {	
		
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;
		
		List<TelefonoVO> telefonosAdicionales = registroForm.getTelefonosAdicionales();
		
		TelefonoVO telefonoVO = new TelefonoVO();
		telefonoVO.setAcceso(request.getParameter("accesoAdd"));
		telefonoVO.setClave(request.getParameter("claveAdd"));
		telefonoVO.setExtension(request.getParameter("extensionAdd"));
		telefonoVO.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
		if(null!=request.getParameter("accesoAdd") && request.getParameter("accesoAdd").equals("01")){
			telefonoVO.setIdTipoTelefono((int)TIPO_TELEFONO.FIJO.getIdOpcion());
		} else {
			telefonoVO.setIdTipoTelefono((int)TIPO_TELEFONO.CELULAR.getIdOpcion());
		}
		telefonoVO.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		telefonoVO.setTelefono(request.getParameter("telefonoAdd"));
				
		boolean existe =false;
		
		if(telefonosAdicionales==null){
			
			telefonosAdicionales = new ArrayList<TelefonoVO>();
			
		}else{
			
			for(TelefonoVO vo: telefonosAdicionales){
				
				if(vo.getAcceso().equals(request.getParameter("accesoAdd"))
						&&vo.getClave().equals(request.getParameter("claveAdd"))
						&&vo.getTelefono().equals(request.getParameter("telefonoAdd"))
						&&vo.getExtension().equals(request.getParameter("extensionAdd"))){
					existe=true;
				}
			}
		}		
		
		if(!existe){
			telefonosAdicionales.add(telefonoVO);
			registroForm.setTelefonosAdicionales(telefonosAdicionales);			
		}
		
		registroForm.setTotalTelefonosAdicionales(telefonosAdicionales.size());
		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.telefonoAdicional");
		return mapping.findForward("ACTION_REGISTROS_TABLA");
	}	

	
	public ActionForward deletePhone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String accesoEliminar=request.getParameter("accesoEliminar");
		String claveEliminar=request.getParameter("claveEliminar");
		String telefonoEliminar=request.getParameter("telefonoEliminar");
		String extensionEliminar=request.getParameter("extensionEliminar");
		
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm) form;
		
		List<TelefonoVO> telefonosAdicionales = registroForm.getTelefonosAdicionales();
		
		int x=0;
		
		for(TelefonoVO vo: telefonosAdicionales){
			
			if(vo.getAcceso().equals(accesoEliminar)
					&&vo.getClave().equals(claveEliminar)
					&&vo.getTelefono().equals(telefonoEliminar)
					&&vo.getExtension().equals(extensionEliminar)){
				
				telefonosAdicionales.remove(x);
				break;
			}
			x++;
		}
		
		registroForm.setTelefonosAdicionales(telefonosAdicionales);
		registroForm.setTotalTelefonosAdicionales(telefonosAdicionales.size());
		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: RegistroEmpresaAction.deletePhone");
		return mapping.findForward("ACTION_REGISTROS_TABLA");
	}

	
	private List<TelefonoVO> setTelefonos(int totalTelefonosAdicionales,
			HttpServletRequest request, RegistroEmpresaForm forma) {
		
		List<TelefonoVO> telefonos = new ArrayList<TelefonoVO>();
		List<TelefonoVO> telefonosAdicionales = new ArrayList<TelefonoVO>();
		
		TelefonoVO telefonoVo = new TelefonoVO();
		
		for(int x = 1;x<=totalTelefonosAdicionales;x++){
			
			telefonoVo = new TelefonoVO();
			String claveAdicional = request.getParameter("claveTelefonoAdicional_"+x);
			String telefonoAdicional = request.getParameter("telefonoAdicional_"+x);
			
			if((claveAdicional!=null && !claveAdicional.equals("")) || 
					(telefonoAdicional!=null && !telefonoAdicional.equals(""))){				
				telefonoVo.setAcceso(request.getParameter("accesoTelefonoAdicional_"+x));
				telefonoVo.setClave(request.getParameter("claveTelefonoAdicional_"+x));
				telefonoVo.setExtension(request.getParameter("extensionTelefonoAdicional_"+x));
				telefonoVo.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
				telefonoVo.setIdTipoTelefono(Integer.parseInt(request.getParameter("idTipoTelefonoAdicional_"+x)));
				telefonoVo.setIdTelefono(Long.parseLong(request.getParameter("idTelefonoAdicional_"+x)));
				telefonoVo.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
				telefonoVo.setTelefono(request.getParameter("telefonoAdicional_"+x));				

				Integer tipotelefono = validaTipoTelefono((int)telefonoVo.getIdTipoTelefono(), telefonoVo.getAcceso());
				telefonoVo.setIdTipoTelefono(tipotelefono);

				String acceso = validaAcceso((int)telefonoVo.getIdTipoTelefono(), telefonoVo.getAcceso());
				telefonoVo.setAcceso(acceso);

				telefonos.add(telefonoVo);
				telefonosAdicionales.add(telefonoVo);
			}
		}
		
		forma.setTelefonosAdicionales(telefonosAdicionales);
		return telefonos;
	}

	
	public ActionForward validaTieneOfertas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm)form;

		String type = "";
		String message = "";

		if (null!=registroForm.getUsuario() && !registroForm.getUsuario().isEmpty()){
			
			try {
				
				EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
				boolean tieneOfertas = delegate.tieneOfertas(registroForm.getUsuario());
				registroForm.setTieneOfertas(tieneOfertas);
				type = "exito"; message = tieneOfertas?"tieneOfertas":"noTieneOfertas";
				
			} catch (Exception e) {
				e.printStackTrace(); logger.error(e);
				type = "error"; message = "Error al validar si tiene ofertas.";
			}
		} else {
			type = "error"; message = "Usuario no indicado.";			
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

	
	public ActionForward validaUsuarioUnico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm)form;

		String type = "";
		String message = "";

		if (registroForm.getUsuario()!=null && !registroForm.getUsuario().isEmpty()){
			
			try {
				EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
				boolean unico = delegate.esUsuarioUnico(registroForm.getUsuario());
				registroForm.setUsuarioUnico(unico);

				type = "exito"; message = unico?"unico":"nounico";
			} catch (Exception e) {
				e.printStackTrace(); logger.error(e);
				type = "error"; message = "Error al validar el login.";
			}
		} else {
			type = "error"; message = "Usuario no indicado.";			
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
	
	
	private CurpVO establecerDatosDeConsultaParaCURP(RegistroEmpresaForm registroForm){

		CurpVO curpVo = new CurpVO();
		
		if(null!=registroForm.getCurp() && !registroForm.getCurp().equalsIgnoreCase("")){
			
			curpVo.setCurp(registroForm.getCurp());
			
		} else if(null!=registroForm.getNombre() && null!=registroForm.getApellido1()) {

			curpVo.setNombre(registroForm.getNombre());
			curpVo.setApellido1(registroForm.getApellido1());
			curpVo.setApellido2(registroForm.getApellido2());
			
			if(registroForm.getGenero() == Catalogos.GENERO.FEMENINO.getIdOpcion()){
				curpVo.setIdGenero(2);
				curpVo.setGenero("M");
			} else if(registroForm.getGenero() == Catalogos.GENERO.MASCULINO.getIdOpcion()) {
				curpVo.setIdGenero(1);
				curpVo.setGenero("H");
			}			

			curpVo.setIdEntidadNacimiento(registroForm.getIdEntidadNacimiento());		
			curpVo.setFechaNacimiento(getFecha(registroForm.getDia(), registroForm.getMes(), registroForm.getAnio()));			
		}
		
		return curpVo;
	}
	
	
	public ActionForward validaCurp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm)form;

		String type = "";
		String message = "";
		CurpVO datosCurp = null;
		
		try{

//			if (validateRespuestaUsuario(registroForm.getRespuestaUsuario(), request)){
				
				EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
				
				CurpVO datosPersonales = establecerDatosDeConsultaParaCURP(registroForm);
				
				/* COMENTADO PARA PRUEBA LOCAL */
				if(null!=registroForm.getCurp() && !registroForm.getCurp().equalsIgnoreCase("")){
				
					datosCurp = delegate.consultaDatosPersonalesPorCURP(datosPersonales.getCurp());
					datosCurp.setEntidadNacimiento(Catalogos.ENTIDADES_FEDERATIVAS.getDescripcion(registroForm.getIdEntidadNacimiento()));
										
				} else {
					
					datosCurp = delegate.consultaCURPPorDatosPersonales(datosPersonales);
					datosCurp.setEntidadNacimiento(Catalogos.ENTIDADES_FEDERATIVAS.getDescripcion(registroForm.getIdEntidadNacimiento()));					
				}
				
				
				/* Usar para prueba local 
				datosCurp = new CurpVO();
				datosCurp = delegate.consultaDatosPersonalesPorCURP("SASE760127MDFLNR06");
				System.out.println("---validaCurp");
				System.out.println("---datosCurp.getNombre(): " + datosCurp.getNombre());
				System.out.println("---datosCurp.getApellido1(): " + datosCurp.getApellido1());
				System.out.println("---datosCurp.getApellido2(): " + datosCurp.getApellido2());
				System.out.println("---datosCurp.getIdEntidadNacimiento(): " + datosCurp.getIdEntidadNacimiento());
				System.out.println("---termina validaCurp");
				*/
				
				/*XXX Codigo de prueba para caso de exito  
				datosCurp = new CurpVO();
				datosCurp.setNombre("ERIZBET");
				datosCurp.setApellido1("SALAMANCA");
				datosCurp.setApellido2("SANCHEZ");
				registroForm.setDia(27);
				registroForm.setMes(1);
				registroForm.setAnio(1976);
				datosCurp.setFechaNacimiento(construirFecha(registroForm).getTime());
				datosCurp.setFechaNac("27/01/1976");
				datosCurp.setIdEntidadNacimiento(registroForm.getIdEntidadNacimiento());				
				datosCurp.setEntidadNacimiento(Catalogos.ENTIDADES_FEDERATIVAS.getDescripcion(registroForm.getIdEntidadNacimiento()));
				datosCurp.setGenero("M");
				datosCurp.setCurp("SASE760127MDFLNR06");
				datosCurp.setStatusOper("EXITOSO");
				datosCurp.setOperacion(WS_CURP_OPERACION.OPERACION_EXITOSA);
				System.out.println("----datosCurp.toString():" + datosCurp.toString());
				*/
				
				/*XXX Codigo de prueba para caso de Curp no existe 
				datosCurp = new CurpVO();
				datosCurp.setOperacion(WS_CURP_OPERACION.OPERACION_NO_EXITOSA);
				datosCurp.setStatusOper(Catalogos.WS_CURP_OPERACION.OPERACION_NO_EXITOSA.toString());
				System.out.println("----datosCurp.toString():" + datosCurp.toString());
				*/
				
				/*XXX Codigo de prueba para caso de Curp no existe 
				datosCurp = new CurpVO();
				datosCurp.setWsError(WS_CURP_ERROR.CURP_NO_EXISTE);
				System.out.println("----datosCurp.toString():" + datosCurp.toString());
				*/
				
				if (null != datosCurp && null != datosCurp.getOperacion() && WS_CURP_OPERACION.OPERACION_EXITOSA == datosCurp.getOperacion()) {
					type = ResultVO.TYPE_SUCCESS;
					message = "CURP validado";					
				
				} else if (null != datosCurp && null != datosCurp.getOperacion() && WS_CURP_OPERACION.OPERACION_NO_EXITOSA == datosCurp.getOperacion()) {
					type = ResultVO.TYPE_ERROR; 					
					message = WS_CURP_ERROR.CURP_NO_EXISTE.getError();
					
				} else if (null != datosCurp && null!=datosCurp.getWsError()){
					type = ResultVO.TYPE_ERROR; 
					if(datosCurp.getWsError().getIdError()>0){
						//System.out.println("----curpVo.getWsError().getIdError():" + datosCurp.getWsError().getIdError());
						registroForm.setIdMotivoNoValidacion((long)datosCurp.getWsError().getIdError());
						message = datosCurp.getWsError().getError();
					}									
					message = "se produjo un error en la consulta de Curp.";
				}					
				
//			} else {
//				type = ResultVO.TYPE_ERROR;
//				message = getMensaje(request, "captcha.respuesta.incorrecta");
//			}
						
	    } catch (Exception ex) {
	    	ex.printStackTrace(); logger.error(ex);
			type = ResultVO.TYPE_ERROR;
			message = WS_CURP_ERROR.ERROR_EN_CONSULTA.getError();
		}		
		
		try {
			
			if(null==datosCurp){
				datosCurp = new CurpVO();
				datosCurp.setStatusOper(type);
				datosCurp.setMessage(message);
			}
			String json = toJson(datosCurp);
			redirectJsonResponse(response, json);				
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return null;
	}	
	
	
	public ActionForward validaCURPUnico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws BusinessException, ServiceLocatorException {
				
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm)form;
		CurpVO datosCurp = null;
		String type = "";
		String message = "";

		try {
			
//			if (validateRespuestaUsuario(registroForm.getRespuestaUsuario(), request)){
				
				EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
				
				CurpVO datosPersonales = establecerDatosDeConsultaParaCURP(registroForm);
				
				boolean unico = delegate.esCurpUnico(datosPersonales.getCurp());
				registroForm.setCurpUnico(unico);
				
				type = "exito"; message = unico?"unico":"nounico";
				
				if (!unico){
				
					datosCurp = delegate.consultaDatosPersonalesPorCURP(datosCurp);
					if (datosCurp!=null){
						datosCurp.getNombre();
						datosCurp.getApellido1();
						datosCurp.getApellido2();
						datosCurp.getEntidadNacimiento();
						datosCurp.getGenero();
						datosCurp.getCurp();

						Date fh = datosCurp.getFechaNacimiento();
						String fhNac = Utils.formatDDMMYYYY(fh);
						datosCurp.setFechaNac(fhNac);
					}					
				} else {
					type = ResultVO.TYPE_ERROR;
					message = WS_CURP_ERROR.ERROR_EN_CONSULTA.getError();
				}
				
//			} else {
//				type = "error";
//				message = "CURP no indicada.";
//			}


//		} catch (TechnicalException e1) {
//			e1.printStackTrace(); logger.error(e1);
//			type = ResultVO.TYPE_ERROR;
//			message = WS_CURP_ERROR.ERROR_EN_CONSULTA.getError();
		} catch (Exception e) {
				e.printStackTrace(); logger.error(e);
				type = "error"; message = "Error al validar el Curp.";
		}

		try {			
			//ResultVO resultado = new ResultVO(message, type);
			if(null==datosCurp){
				datosCurp = new CurpVO();
				datosCurp.setStatusOper(type);
				datosCurp.setMessage(message);
			}				
			
			String json = toJson(datosCurp);
			redirectJsonResponse(response, json);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward validaRespuestaCaptcha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws BusinessException, ServiceLocatorException {
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm)form;
		try {
			boolean respuestaCaptcha = validateRespuestaUsuario(registroForm.getRespuestaUsuario(), request);
			String json;
			if (respuestaCaptcha){
				json = "{'statusOper' : 'exito'}";
            } else {
				json = "{'statusOper' : 'error'}";
			}
			redirectJsonResponse(response, json);
		} catch (TechnicalException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void cambiaPregunta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {	
		
		String pregunta = null;
		
		try {
			RegistroEmpresaForm registroForm = (RegistroEmpresaForm)form;
			CaptchaLogicoObject captcha = getCaptcha(request);
			captcha.obtieneDesafioCaptcha();
			registroForm.setPregunta(captcha.getPregunta());
			pregunta = captcha.getPregunta();
			redirectJsonResponse(response, pregunta);	
			
		} catch (mx.gob.stps.portal.exception.BusinessException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}	
	
	
	public boolean validateRespuestaUsuario(String respuestaUsuario, HttpServletRequest request) throws TechnicalException{
		
		boolean esCorrecto = true;
		
		try{
			CaptchaLogicoObject captcha = getCaptcha(request);
			if (respuestaUsuario == null) {
				esCorrecto = false;
			} else if (!captcha.isRespuestaCorrecta(respuestaUsuario) ) {
				esCorrecto = false;
			}
		}catch (Exception e){
			logger.error("obtienePregunta: ha ocurrido un error");
			e.printStackTrace();
			throw new TechnicalException("Ha ocurrido un error al validar la respuesta");
		}
		
		return esCorrecto;
	}	
	
	
	public ActionForward validaCorreoElectronicoUnico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroEmpresaForm registroForm = (RegistroEmpresaForm)form;

		String type = "";
		String message = "";

		if (registroForm.getCorreoElectronico()!=null && !registroForm.getCorreoElectronico().isEmpty()){
			
			try {
				
				EmpresaRegistroBusDelegate delegate = EmpresaRegistroBusDelegateImpl.getInstance();
				boolean unico = delegate.esCorreoUnico(registroForm.getCorreoElectronico());
				registroForm.setCorreoElectronicoUnico(unico);

				type = "exito"; message = unico?"unico":"nounico";
			} catch (Exception e) {
				e.printStackTrace(); logger.error(e);
				type = "error"; message = "Error al validar el Correo.";
			}
		} else {
			type = "error"; message = "Correo electronico no indicado.";			
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

	
	public ActionForward tiposEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){

		try {
			CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
			List<CatalogoOpcionVO> opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_TIPO_EMPRESA, true);

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}

		return null;
	}	
	
	
	public ActionForward tiposSociedad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		try{
			
			CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();
			List<CatalogoOpcionVO> opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_TIPO_SOCIEDAD, true);
			
			redirectJsonCatalogo(opciones, response);
			
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}
		
		return null;
	}

	public ActionForward tiposActividadEconomica(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		RegistroEmpresaForm regEmpForm = (RegistroEmpresaForm) form;
		regEmpForm.reset(mapping, request);
		try {
			List<CatalogoOpcionVO> opciones = null;
			
			if (regEmpForm.getIdSubsector() > 0) {
				CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();
				opciones = catalogoOpcionDelegate.consultarCatalogoPorId(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA,
						regEmpForm.getIdSubsector(), 1, 3);	
			} else {
				opciones = new ArrayList<CatalogoOpcionVO>();
			}

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}				
		return null;
	}		
	
	public ActionForward tiposSector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long[] sector_filter = { 32, 33, 49 };
		try {
			CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
			List<CatalogoOpcionVO> opciones = catalogoOpcionDelegate.consultarCatalogo(Constantes.CATALOGO_OPCION_SECTOR, sector_filter, true);
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}
		return null;
	}
	
	public ActionForward tiposSubsector(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		RegistroEmpresaForm regEmpForm = (RegistroEmpresaForm) form;
		regEmpForm.reset(mapping, request);
		try {
			List<CatalogoOpcionVO> opciones;
			
			if (regEmpForm.getIdSector() > 0) {
				CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();
				if (regEmpForm.getIdSector() == 31) {
					opciones = catalogoOpcionDelegate.consultarCatalogoPorId(Constantes.CATALOGO_OPCION_SUBSECTOR, regEmpForm.getIdSector(), 1, 2, 31L, 32L, 33L);
				} else if (regEmpForm.getIdSector() == 48) {
					opciones = catalogoOpcionDelegate.consultarCatalogoPorId(Constantes.CATALOGO_OPCION_SUBSECTOR, regEmpForm.getIdSector(), 1, 2, 48L, 49L);
				} else {
					opciones = catalogoOpcionDelegate.consultarCatalogoPorId(Constantes.CATALOGO_OPCION_SUBSECTOR, regEmpForm.getIdSector(), 1, 2);
				}
			} else {
				opciones = new ArrayList<CatalogoOpcionVO>();
			}

			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);

		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}		
		return null;
	}

	public ActionForward medioPortal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_MEDIO_ENTERADO);

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {logger.error(e);}		

		return null;
	}
	
	public ActionForward dias(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			CatalogoOpcionVO zero = new CatalogoOpcionVO();
			zero.setIdCatalogo(0);
			zero.setOpcion("Día");
			opciones.add(zero);

			for (int i=1; i<=31; i++){
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(i);
				opcion.setOpcion(String.valueOf(i));
				opciones.add(opcion);
			}

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}

		return null;
	}

	public ActionForward meses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			CatalogoOpcionVO zero = new CatalogoOpcionVO();
			zero.setIdCatalogo(0);
			zero.setOpcion("Mes");
			opciones.add(zero);

			for (MESES mes : MESES.values()){
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(mes.getIdOpcion());
				opcion.setOpcion(String.valueOf(mes.getIdOpcion()));
				opciones.add(opcion);
			}

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}

		return null;
	}

	public ActionForward anios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			CatalogoOpcionVO zero = new CatalogoOpcionVO();
			zero.setIdCatalogo(0);
			zero.setOpcion("Año");
			opciones.add(zero);

			Calendar hoy = Calendar.getInstance();
			int year = hoy.get(Calendar.YEAR);

			for (int i=(year-15); i>= (year-90); i--){
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(i);
				opcion.setOpcion(String.valueOf(i));
				opciones.add(opcion);
			}

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {		
			e.printStackTrace(); logger.error(e);
		}		
		return null;
	}

	public ActionForward aniosactual(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();

			CatalogoOpcionVO zero = new CatalogoOpcionVO();
			zero.setIdCatalogo(0);
			zero.setOpcion("Año");
			opciones.add(zero);			

			Calendar hoy = Calendar.getInstance();
			int year = hoy.get(Calendar.YEAR);

			for (int i=year; i>= (year-90); i--){
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(i);
				opcion.setOpcion(String.valueOf(i));
				opciones.add(opcion);
			}

			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {		
			e.printStackTrace(); logger.error(e);
		}		
		return null;
	}
	
	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}	

	private ActionForward getForward(ActionMapping mapping, HttpServletRequest request, String forwardName) {
		HttpSession session = request.getSession();
		session.setAttribute(BODY_JSP, mapping.findForward(forwardName).getPath());	
        PropertiesLoader properties = PropertiesLoader.getInstance();	
		request.getSession().setAttribute(TITULO_PAGINA, "Registro de empresas");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Registro de empresas, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ request.getRequestURI().toString());
		return mapping.findForward(FORWARD_TEMPLATE_REGISTRO_EMPRESA);
	}	

	private Integer validaTipoTelefono(Integer idTipoTelefono, String acceso){		

		if(idTipoTelefono==null || 
			(idTipoTelefono!=null 
				&& (TIPO_TELEFONO.CELULAR.getIdOpcion() != idTipoTelefono.longValue() 
				&&  TIPO_TELEFONO.FIJO.getIdOpcion() != idTipoTelefono.longValue())
				)){
			
			if (acceso!=null && TIPO_TELEFONO.CELULAR.getAcceso().equals(acceso)){
				idTipoTelefono = (int)TIPO_TELEFONO.CELULAR.getIdOpcion();					
			} else if (acceso!=null && TIPO_TELEFONO.FIJO.getAcceso().equals(acceso)){
				idTipoTelefono = (int)TIPO_TELEFONO.FIJO.getIdOpcion();
			} else {
				idTipoTelefono = (int)TIPO_TELEFONO.FIJO.getIdOpcion();
			}
		}
			
		return idTipoTelefono;
	}

	private String validaAcceso(Integer idTipoTelefono, String acceso){		

		if(acceso==null ||
				(acceso!=null && (!TIPO_TELEFONO.CELULAR.getAcceso().equals(acceso) 
				&& !TIPO_TELEFONO.FIJO.getAcceso().equals(acceso))
				)){
			
				if (idTipoTelefono!=null && TIPO_TELEFONO.CELULAR.getIdOpcion() == idTipoTelefono.longValue()){
					acceso = TIPO_TELEFONO.CELULAR.getAcceso();
				} else if (idTipoTelefono!=null && TIPO_TELEFONO.FIJO.getIdOpcion() == idTipoTelefono.longValue()){
					acceso = TIPO_TELEFONO.FIJO.getAcceso();
				} else {
					acceso = TIPO_TELEFONO.FIJO.getAcceso();
				}
		}
		return acceso;
	}
}
