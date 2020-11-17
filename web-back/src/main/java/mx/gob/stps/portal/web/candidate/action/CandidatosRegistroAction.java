package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ESTADO_CIVIL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_MEDIO_ENTERADO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_TIPO_DISCAPACIDAD;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_USUARIO_ANONIMO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.CAPTCHA_WORD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.ws.renapo.exception.ConsultaWsPorCurpException;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatosRegistroBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.CandidatosRegistroForm;
import mx.gob.stps.portal.web.entrevista.helper.ResultEntrevistaVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.ws.renapo.exception.CurpNotFoundException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/** Registro de Candidatos
 * @author jose.hernandez
 */
// TODO ELIMINAR CLASE, YA NO SE UTILIZA A PARTIR DEL NUEVO REGISTRO
public class CandidatosRegistroAction extends GenericAction {

	public static final String MENSAJE_EXISTO 	= "Se ha enviado un mensaje a su cuenta de correo, \n favor de validar su cuenta para concluir con el registro.";
	public static final String MENSAJE_ERROR 	= "La transacción no pudo ser completada, \n favor de intentarlo nuevamente después de unos instantes.";

	private static final Logger logger = Logger.getLogger(CandidatosRegistroAction.class);

	public CandidatosRegistroAction(){}	

	public ActionForward init (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		CandidatosRegistroForm candidatosRegistroForm = (CandidatosRegistroForm) form;
		candidatosRegistroForm.reset();
		String nss = request.getParameter("nss");
		String  credito = request.getParameter("credito");

		if((nss != null && !nss.trim().isEmpty()) || (credito != null && !credito.trim().isEmpty())){
			if(nss != null && !nss.trim().isEmpty()){
				candidatosRegistroForm.setNss(nss);
			}			
			if(credito != null && !credito.trim().isEmpty()){
				candidatosRegistroForm.setCreditoInfonavit(Long.valueOf(credito));
			}
		}

		request.getSession().setAttribute(BODY_JSP,	mapping.findForward(FORWARD_JSP).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro de Candidatos");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}



	/** Retorna al Inicio
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward retornaInicio (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		PropertiesLoader properties = PropertiesLoader.getInstance();		
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.home"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}

	/** Registrando candidato
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws BusinessException, ServiceLocatorException {		

		String json	= null;
		String msg 	= null;
		String type = null;
		long idAdministrador = 0;

		CandidatosRegistroForm candidatosRegistroForm = (CandidatosRegistroForm) form;
		CandidatosRegistroBusDelegate candidatosRegistroBusDelegate = CandidatosRegistroBusDelegateImpl.getInstance();
		CandidatoVo candidatoVo = getCandidatoVo(candidatosRegistroForm);

		UsuarioWebVO usuarioWebVO = getUsuario(request.getSession());
		if(usuarioWebVO != null && 
				usuarioWebVO.getIdTipoUsuario()== PERFIL.ADMINISTRADOR.getIdOpcion()){
			idAdministrador = usuarioWebVO.getIdUsuario();	
		} else {
			idAdministrador = ID_USUARIO_ANONIMO; 
		}		

		try{
			candidatosRegistroBusDelegate.registraCandidatos(candidatoVo,idAdministrador);	

			msg  = MENSAJE_EXISTO;
			type = ResultEntrevistaVO.TYPE_SUCCESS;	
		} catch(BusinessException e) {
			msg  = e.getMessage();
			type = ResultEntrevistaVO.TYPE_ERROR_CORREO;
		}  catch(ServiceLocatorException e) {
			msg  = MENSAJE_ERROR;
			type = ResultEntrevistaVO.TYPE_ERROR;			
			e.printStackTrace();				
		} catch (PersistenceException e) {
			logger.error(e);
			msg  = MENSAJE_ERROR;
			type = ResultEntrevistaVO.TYPE_ERROR_CORREO;
			e.printStackTrace();
		} catch (TechnicalException e) {
			logger.error(e);
			msg  = MENSAJE_ERROR;
			type = ResultEntrevistaVO.TYPE_ERROR_CORREO;
			e.printStackTrace();
		} catch (MailException e) {
			msg  = MENSAJE_ERROR + " .Error al enviar el correo.";
			type = ResultEntrevistaVO.TYPE_ERROR_CORREO;
			logger.error(e);
			e.printStackTrace();
		}	

		try {
			json = toJson(new ResultEntrevistaVO(msg, type));
			redirectJsonResponse(response, json);			
			return null;
		} catch (IOException e) {
			logger.error(e);
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			return mapping.findForward(FORWARD_TEMPLATE_FORM);
		}


	}

	/** Consultar por Wb de Renapo por Curp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public ActionForward verificarCurp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		CandidatosRegistroForm candidatosRegistroForm = (CandidatosRegistroForm) form;
		CandidatosRegistroBusDelegate candidatosRegistroBusDelegate = CandidatosRegistroBusDelegateImpl.getInstance();
		CandidatoVo candidatoVo = getCandidatoVo(candidatosRegistroForm);
		CandidatoVo candidato = new CandidatoVo();
		
		try{
			if (validaCaptcha(request)){
				try {
					String nombre = candidatoVo.getNombre();
					String apellido1 = candidatoVo.getApellido1();
					String apellido2 = candidatoVo.getApellido2();
					int genero = candidatoVo.getGenero();
					Date fechaNacimiento = candidatoVo.getFechaNacimiento();
					int idEntidadNacimiento = candidatoVo.getIdEntidadNacimiento();

					candidato = candidatosRegistroBusDelegate.consultaCURPPorDatosPersonales(nombre, apellido1, apellido2, genero, fechaNacimiento, idEntidadNacimiento);
					String estadoEntidad = valorCatalogoEntidadFederativa(candidato.getIdEntidadNacimiento());

					candidato.setEstadoEntidadString(estadoEntidad);

				} catch (ConsultaWsPorCurpException e) {
					logger.error("Error al consultar en web Service CandidatosRegistroAction.verificarCurp:-> " + e.getMessage());
					candidato.setMessage("Error");
				} catch (CurpNotFoundException e) {
					logger.error("Error al consultar en web Service CandidatosRegistroAction.verificarCurp:-> " + e.getMessage());
					candidato.setMessage("Error");
				}
			}else{
				candidato.setStatusOper("ERROR_CAPTCHA");
				String msgerr = getMensaje(request, "can.curp.captcha.err");
				candidato.setMessage(msgerr);
			}

			String json = toJson(candidato);
			redirectJsonResponse(response, json);		

		}catch(ServiceLocatorException e){
			logger.error(e); //TODO REPORTAR ERROR
		}catch(IOException e){
			logger.error(e); //TODO REPORTAR ERROR
		}

		return null;
	}

	/** Consultar por Wb de Renapo por datos Personales
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public ActionForward verificarDatosPersonales(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws BusinessException, ServiceLocatorException {	

		CandidatosRegistroForm candidatosRegistroForm = (CandidatosRegistroForm) form;
		CandidatosRegistroBusDelegate candidatosRegistroBusDelegate = CandidatosRegistroBusDelegateImpl.getInstance();
		CandidatoVo candidatoVo = getCandidatoVo(candidatosRegistroForm);
		CandidatoVo candidato = new CandidatoVo();

		try{
			if (validaCaptcha(request)){

				try {
					candidato = candidatosRegistroBusDelegate.consultaDatosPersonalesPorCURP(candidatoVo.getCurp());
					String estadoEntidad = valorCatalogoEntidadFederativa(candidato.getIdEntidadNacimiento());

					candidato.setEstadoEntidadString(estadoEntidad);
					logger.info(candidato);
				} catch (ConsultaWsPorCurpException e) {
					logger.error("Error al consultar en web Service CandidatosRegistroAction.verificarDatosPersonales:-> " + e.getMessage());
					candidato.setMessage("Error");
				} catch (CurpNotFoundException e) {
					logger.error("Error al consultar en web Service CandidatosRegistroAction.verificarDatosPersonales:-> " + e.getMessage());
					candidato.setMessage("Error");
				}

			}else{
				candidato.setStatusOper("ERROR_CAPTCHA");
				String msgerr = getMensaje(request, "can.curp.captcha.err");
				candidato.setMessage(msgerr);
			}

			String json = toJson(candidato);
			//logger.debug(json);

			redirectJsonResponse(response, json);

		}catch(ServiceLocatorException e){
			logger.error(e); //TODO REPORTAR ERROR
		}catch(IOException e){
			logger.error(e); //TODO REPORTAR ERROR
		}

		return null;
	}


	/**  Retormado un VO de una Form
	 * @param candidatosRegistroForm
	 * @return CandidatoVo 
	 */
	private CandidatoVo getCandidatoVo(CandidatosRegistroForm candidatosRegistroForm){

		CandidatoVo candidatoVo =  new CandidatoVo();		
		//Agrega domicilio al candidato al registrarse en el portal
		DomicilioVO domicilioVo = getDomicilioCandidatoRegistro(candidatosRegistroForm);
		candidatoVo.setDomicilioVo(domicilioVo);
		
		if(candidatosRegistroForm.getFechaNacimiento() != null){
			candidatoVo.setFechaNacimiento(getFecha(candidatosRegistroForm));
			candidatoVo.setEdad(Utils.getEdad(getFecha(candidatosRegistroForm)));
		}

		if(candidatosRegistroForm.getNss() != null && !candidatosRegistroForm.getNss().trim().isEmpty()){
			candidatoVo.setNss(candidatosRegistroForm.getNss());
		}

		if(candidatosRegistroForm.getCreditoInfonavit() != null){
			candidatoVo.setCreditoInfonavit(candidatosRegistroForm.getCreditoInfonavit());
		}

		candidatoVo.setIdUsuario(0);
		candidatoVo.setCurp(candidatosRegistroForm.getCurp());
		candidatoVo.setNombre(candidatosRegistroForm.getNombre());
		candidatoVo.setApellido1(candidatosRegistroForm.getApellido1());
		candidatoVo.setApellido2(candidatosRegistroForm.getApellido2());				
		candidatoVo.setGenero(candidatosRegistroForm.getGenero());						
		candidatoVo.setIdEntidadNacimiento(candidatosRegistroForm.getIdEntidadNacimiento());
		candidatoVo.setIdEstadoCivil(candidatosRegistroForm.getIdEstadoCivil());			
		candidatoVo.setIdTipoDiscapacidad(candidatosRegistroForm.getDiscapacidad());
		candidatoVo.setIdMedioPortal(candidatosRegistroForm.getPortalEnteraste());			
		candidatoVo.setConfidencialidadDatos(Utils.getNumeroBoolena(candidatosRegistroForm.getConfidencialidadDatos()));
		candidatoVo.setVeracidadDatos(Utils.getNumeroBoolena(candidatosRegistroForm.getVeracidadDatos()));
		candidatoVo.setAceptacionTerminos(Utils.getNumeroBoolena(candidatosRegistroForm.getAceptacionTerminos()));
		Calendar cal=Calendar.getInstance();
		cal.setTime(new java.util.Date());			
		candidatoVo.setFechaAlta(cal);
		candidatoVo.setEstatus(ESTATUS.INACTIVO.getIdOpcion());
		candidatoVo.setFechaConfirma(new Date());
		candidatoVo.setFechaUltimaActualizacion(new Date());

		candidatoVo.setCorreoElectronico(candidatosRegistroForm.getCorreoElectronico());
		candidatoVo.setEstiloCv(0);

		//logger.debug(" candidatoVo :-> " + candidatoVo.toString());

		return candidatoVo;	
	}


	private DomicilioVO getDomicilioCandidatoRegistro(
			CandidatosRegistroForm candidatosRegistroForm) {
		DomicilioVO domicilioVo = new DomicilioVO();
		domicilioVo.setCalle(" ");
		domicilioVo.setCodigoPostal(" ");
		domicilioVo.setIdColonia(0);
		domicilioVo.setIdTipoPropietario(TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		domicilioVo.setIdEntidad(candidatosRegistroForm.getEntidadFederativa());
		domicilioVo.setIdMunicipio(candidatosRegistroForm.getMunicipio_delegacion());
		domicilioVo.setNumeroExterior(" ");
		domicilioVo.setNumeroInterior(" ");
		domicilioVo.setEntreCalle(" ");
		domicilioVo.setyCalle(" ");
		domicilioVo.setFechaAlta(new Date());
		return domicilioVo;
		
	}

	/**
	 * @param candidatosRegistroForm
	 * @return
	 */
	private Date getFecha(CandidatosRegistroForm candidatosRegistroForm){
		String fechaAux  = "";
		String arreglo[] = candidatosRegistroForm.getFechaNacimiento(); 
		for (int i = 0; i < arreglo.length; i++) {			
			if(!"".equals(arreglo[i]))
				fechaAux = arreglo[i];
		}		
		return Utils.convert(fechaAux);

	}



	/**	 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward estadoCivil(ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws IOException {

		long filtroOpciones [] = {5,6};

		CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
		List<CatalogoOpcionVO> opciones = null;
		try {
			opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_ESTADO_CIVIL,filtroOpciones);
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		}				

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);		
		return null;
	}


	/**	 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward tipoDiscapacidad(ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws IOException {

		CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
		List<CatalogoOpcionVO> opciones = null;
		try {
			opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_TIPO_DISCAPACIDAD);
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		}		

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);		
		return null;
	}

	/**	 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward medioPortal(ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws IOException {

		CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
		List<CatalogoOpcionVO> opciones = null;
		try {
			opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_MEDIO_ENTERADO);
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		}		

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);		
		return null;
	}

	/**	 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward entidadesFederativas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		//long filtroOpciones [] = {33};

		CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
		List<CatalogoOpcionVO> opciones = null;
		try {
			opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_ENTIDAD_FEDERATIVA, true);
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		}		

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);		
		return null;
	}

	
	public ActionForward entidadesFederativasPreRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		long filtroOpciones [] = {33};

		CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
		List<CatalogoOpcionVO> opciones = null;
		try {
			opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_ENTIDAD_FEDERATIVA,filtroOpciones);
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		}		

		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);		
		return null;
	}
	
	public ActionForward entidadesFederativasOpcion(ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	throws IOException {

		long filtroOpciones [] = {33};
		CatalogoOpcionVO vo = new CatalogoOpcionVO();
		vo.setIdCatalogoOpcion(0);
		vo.setOpcion("");

		CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
		List<CatalogoOpcionVO> opciones = null;

		try {
			opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_ENTIDAD_FEDERATIVA,filtroOpciones);
			opciones.add(0,vo);
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		}		
		
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);		
		return null;
	}	

	/**
	 * @param idEstado
	 * @return
	 */
	private String valorCatalogoEntidadFederativa(long idEstado){
		CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
		String opcion = "";
		try {
			opcion = catalogoOpcionDelegate.getOpcionById(CATALOGO_OPCION_ENTIDAD_FEDERATIVA,idEstado);
		} catch (ServiceLocatorException e) {
			logger.error("Error al obtener valorCatalogoEntidadFederativa :-> " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error("Error al obtener valorCatalogoEntidadFederativa :-> " + e.getMessage());
			e.printStackTrace();
		}

		return opcion;
	}	

	public boolean validaCaptcha(HttpServletRequest request) {
		boolean captchavalido = false;
		HttpSession session = request.getSession();

		String word = (String)session.getAttribute(CAPTCHA_WORD); // clave secreta
		String code = request.getParameter("code"); // texto capturado

		if (word==null || word.trim().isEmpty() || code==null || code.trim().isEmpty()){
			captchavalido = false;
		}else if (word.trim().equalsIgnoreCase(code.trim())) {
			captchavalido = true;	
		}else{
			captchavalido = false;
			//logger.info("word : "+ word +" code : "+ code);
		}

		return captchavalido;
	}

	public ActionForward obtenerMunicipio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String idEntidad = request.getParameter("idEntidad");
		try {			
			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
			List<CatalogoOpcionVO> opciones = services.consultaMunicipios(Utils.parseLong(idEntidad));
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}	
	
	
	public ActionForward obtenerMunicipioRegistro(ActionMapping mapping, 
			ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CandidatosRegistroForm candidatosRegistroForm = (CandidatosRegistroForm) form;
		DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
		try {
			List<CatalogoOpcionVO> municipios = services.consultaMunicipios(candidatosRegistroForm.getEntidadFederativa());
			CatalogoVO cat = getCatalogo(municipios);
			String json = toJson(cat);
			redirectJsonResponse(response, json);		

		} catch (SQLException e) {
			logger.error("Error al obtener municipios :-> " + e.getMessage());
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			logger.error("Error al obtener municipios :-> " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Error al obtener municipios :-> " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}


