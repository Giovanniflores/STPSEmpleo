package mx.gob.stps.portal.web.candidate.action;


import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.beanutils.BeanUtils;

import mx.gob.stps.portal.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.utils.Catalogos.DECISION;
import mx.gob.stps.portal.utils.Catalogos.TRABAJA_ACTUALMENTE;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.web.offer.wrapper.PerfilJB;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.EscolaridadForm;
import mx.gob.stps.portal.web.candidate.form.ProgramaForm;
import mx.gob.stps.portal.core.candidate.vo.CandidatoOtroEstudioVO;
import mx.gob.stps.portal.web.infra.helper.BeneficiarioBO;
import mx.gob.stps.portal.web.infra.helper.DiscapacidadBO;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.IDIOMAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONOC_HAB;
import mx.gob.stps.portal.core.infra.utils.Constantes.MODALIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.RECIBE_OFERTA;
import mx.gob.stps.portal.core.infra.utils.Constantes.MOSTRAR_EMPRESA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_FORMATO_BENEFICIARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.LST_TELEFONOS_ADICIONALES;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.persistencia.vo.FormatoLPAVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATCultivosVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATHclinicaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATMaquinariaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoDTO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.persistencia.vo.DomicilioVO;
import mx.gob.stps.portal.persistencia.vo.BeneficiarioVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.FormatoSNE01VO;
import mx.gob.stps.portal.persistencia.vo.ReferenciaLaboralVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadCandidatoVO;

public class InformacionProgramaAction extends GenericAction {
	
	/** Nombre del forward que direcciona a la pagina movilidad interna **/
	public static final String FORWARD_MI = "MI";
	
	private static String formatPattern = "yyyy-MM-dd";
	
	long[] filtro_idioma = {Constantes.IDIOMAS.NO_REQUISITO.getIdOpcion()};
	
	private static Logger logger = Logger.getLogger(InformacionProgramaAction.class);
	
	public static final String LST_TELEFONOS_2DELETE = "LST_TELEFONOS_2DELETE";

	/**
	 * Retrieve offer list to program parameter
	 * 
	 * @author Sergio Téllez
	 * @since 18/09/2015
	 **/
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		int[] exclude = {1,2,3,4,17,18,26,27,28,29,30,31};
		ProgramaForm programaForm = (ProgramaForm)form;
		MODALIDAD modalidad = MODALIDAD.findByParam(request.getParameter("program"));
		programaForm.setModalidad(modalidad);
		if (null != modalidad && isCandidateInProgram(request, programaForm)) return edit(mapping, form, request, response);
		setModality(request, programaForm, exclude);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Informaci&oacute;n Programa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Informaci&oacute;n Programa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	private ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ProgramaForm programaForm = (ProgramaForm)form;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		long idCandidato = getUsuario(request.getSession()).getIdPropietario();
		try {
			if (programaForm.getModalidad().getParam().equalsIgnoreCase("ptat")) {
				List<FormatoPTATHclinicaVO> enfermedades = services.getDiseases(idCandidato);
				List<FormatoPTATCultivosVO> products = services.getFormatoPTATCultivos(idCandidato);
				List<FormatoPTATMaquinariaVO> engines = services.getFormatoPTATMaquinaria(idCandidato);
				List<BeneficiarioVO> contacts = services.getBeneficiarioList(idCandidato, TIPO_FORMATO_BENEFICIARIO.PTAT.getIdOpcion(), true);
				List<BeneficiarioVO> beneficiaries = services.getBeneficiarioList(idCandidato, TIPO_FORMATO_BENEFICIARIO.PTAT.getIdOpcion(), false);
				programaForm.setEngines(engines);
				programaForm.setProducts(products);
				programaForm.setEnfermedades(enfermedades);
				request.getSession().setAttribute("LST_CONTACTS", getBussinesObject(contacts));
				request.getSession().setAttribute("LST_BENEFICIARIES_PTAT", beneficiaries);
			}
			response.sendRedirect(request.getContextPath()+"/perfilComplementario.do?method=complement&program="+programaForm.getModalidad().getParam());
		} catch (ServiceLocatorException | BusinessException  | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward complement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ProgramaForm programaForm = (ProgramaForm) form;
		UsuarioWebVO user = getUsuario(request.getSession());
		String FORWARD = mapping.findForward(FORWARD_JSP).getPath();
		String param = null != request.getParameter("program") ? request.getParameter("program") : "";
		programaForm.setModalidad(MODALIDAD.findByParam(param));
		loadEntities(request.getSession(), programaForm);
		initProfile(request, programaForm, user.getIdPropietario(), param);
		if (param.equalsIgnoreCase("ptat")) {
			int[] exclude = {1,2,3,4,5,6,7,9,16,17,26,27,28,29,30,31};
			programaForm.setPath(getPath(MODALIDAD.RC.getIdModalidad(), exclude));
		}else if (param.equalsIgnoreCase("mml")) {
			int[] exclude = {1,2,3,4,5,6,7,8,16,17,26,27,28,29,30,31};
			programaForm.setPath(getPath(MODALIDAD.RC.getIdModalidad(), exclude));
			request.getSession().setAttribute("OTHER_STUDIES_LIST", getOtherStudies(user.getIdPropietario()));	
			FORWARD = mapping.findForward(FORWARD_NEXT).getPath();
		}else if (param.equalsIgnoreCase("misi")) {
			FORWARD = mapping.findForward(FORWARD_MI).getPath();
			int[] exclude = {1,2,3,4,5,7,8,9,16,17,26,27,28,29,30,31};
			programaForm.setPath(getPath(MODALIDAD.RC.getIdModalidad(), exclude));
			request.getSession().setAttribute("OTHER_STUDIES_LIST", getOtherStudies(user.getIdPropietario()));
		}else if (param.equalsIgnoreCase("misa")) {
			FORWARD = mapping.findForward(FORWARD_MI).getPath();
			int[] exclude = {1,2,3,4,5,6,8,9,16,17,26,27,28,29,30,31};
			programaForm.setPath(getPath(MODALIDAD.RC.getIdModalidad(), exclude));
			FORWARD = mapping.findForward(FORWARD_MI).getPath();
			request.getSession().setAttribute("OTHER_STUDIES_LIST", getOtherStudies(user.getIdPropietario()));
		}else if (param.equalsIgnoreCase("beca")) {
			FORWARD = mapping.findForward(FORWARD_MI).getPath();
			int[] exclude = {1,2,3,4,5,6,7,8,9,17,26,27,28,29,30,31};
			programaForm.setPath(getPath(MODALIDAD.RC.getIdModalidad(), exclude));
			request.getSession().setAttribute("OTHER_STUDIES_LIST", getOtherStudies(user.getIdPropietario()));
		}else if (param.equalsIgnoreCase("fa")) {
			FORWARD = mapping.findForward(FORWARD_MI).getPath();
			int[] exclude = {1,2,3,4,6,7,8,9,16,17,26,27,28,29,30,31};
			programaForm.setPath(getPath(MODALIDAD.RC.getIdModalidad(), exclude));
			request.getSession().setAttribute("OTHER_STUDIES_LIST", getOtherStudies(user.getIdPropietario()));
		}
		request.getSession().setAttribute(BODY_JSP, FORWARD);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Informaci&oacute;n Programa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Informaci&oacute;n Programa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	public ActionForward strengthenProfile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ProgramaForm programaForm = (ProgramaForm) form;
		programaForm.setModalidad(MODALIDAD.MML);
		loadEntities(request.getSession(), programaForm);
		initProfile(request, programaForm, getUsuario(request.getSession()).getIdPropietario(), programaForm.getModalidad().getParam());
		int[] exclude = {1,2,3,4,5,6,7,8,16,17,26,27,28,29,30,31};
		programaForm.setPath(getPath(MODALIDAD.RC.getIdModalidad(), exclude));
		String FORWARD = mapping.findForward("MML_PERFIL").getPath();
		request.getSession().setAttribute("OTHER_STUDIES_LIST", getOtherStudies(getUsuario(request.getSession()).getIdPropietario()));
		request.getSession().setAttribute(BODY_JSP, FORWARD);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Informaci&oacute;n Programa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Informaci&oacute;n Programa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	public ActionForward languages(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ProgramaForm programaForm = (ProgramaForm) form;
		programaForm.setModalidad(MODALIDAD.MML);
		loadEntities(request.getSession(), programaForm);
		initProfile(request, programaForm, getUsuario(request.getSession()).getIdPropietario(), programaForm.getModalidad().getParam());
		int[] exclude = {1,2,3,4,5,6,7,8,16,17,26,27,28,29,30,31};
		programaForm.setPath(getPath(MODALIDAD.RC.getIdModalidad(), exclude));
		String FORWARD = mapping.findForward("MML_PERFIL_LANG").getPath();
		request.getSession().setAttribute("OTHER_STUDIES_LIST", getOtherStudies(getUsuario(request.getSession()).getIdPropietario()));
		request.getSession().setAttribute(BODY_JSP, FORWARD);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Informaci&oacute;n Programa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Informaci&oacute;n Programa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	public ActionForward workReferences(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ProgramaForm programaForm = (ProgramaForm) form;
		programaForm.setModalidad(MODALIDAD.MML);
		loadEntities(request.getSession(), programaForm);
		String FORWARD = mapping.findForward("MML_PERFIL_WORK").getPath();
		initProfile(request, programaForm, getUsuario(request.getSession()).getIdPropietario(), programaForm.getModalidad().getParam());
		int[] exclude = {1,2,3,4,5,6,7,8,16,17,26,27,28,29,30,31};
		programaForm.setPath(getPath(MODALIDAD.RC.getIdModalidad(), exclude));
		if (null == programaForm.getReferenciaPrincipal().getDomicilio())
			FORWARD = mapping.findForward("MML_LAST_WORK").getPath();
		request.getSession().setAttribute("OTHER_STUDIES_LIST", getOtherStudies(getUsuario(request.getSession()).getIdPropietario()));
		request.getSession().setAttribute(BODY_JSP, FORWARD);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Informaci&oacute;n Programa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Informaci&oacute;n Programa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward savesne(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int result = 0;
		ResultVO msg =  null;
		ProgramaForm programaForm = (ProgramaForm) form;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		List<BeneficiarioVO> beneficiaries = (List<BeneficiarioVO>)request.getSession().getAttribute("LST_BENEFICIARIES");
		if (beneficiaries.isEmpty())
			msg = new ResultVO(getMensaje(request, "can.guardar.beneficiario.err.empty"), ResultVO.TYPE_ERROR);
		else {
			try {
				result = services.updatePerfilSNE(getPerfil(programaForm, request), getExpectativa(programaForm, request), getLastJob(programaForm, request),
						programaForm.getModalidadCandidato(), beneficiaries, getSNE(programaForm, request));
				saveAddPhoneList(request);
				saveAddStudieList(request);
				deleteBeneficiariesList(request);
				saveAddKnowledgeList(programaForm, request);
			} catch (Exception e) {
				msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
				e.printStackTrace(); logger.error(e);
			}
			if (result > 0)
				msg = new ResultVO(getMensaje(request, "can.guardar.participacion", programaForm.getModalidad().getNombreCorto()), ResultVO.TYPE_SUCCESS);		
			else msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
		}
		String json = toJson(msg);
		redirectJsonResponse(response, json);
		return null;		
	}
	
	public ActionForward savemml(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int result = 0;
		ResultVO msg =  null;
		ProgramaForm programaForm = (ProgramaForm) form;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		List<BeneficiarioVO> beneficiaries = new ArrayList<BeneficiarioVO>();
		try {
			ReferenciaLaboralVO referencia = getReferenceLastJob(programaForm, request);
			referencia.setTelefono(getPhoneReference(programaForm, request));
			result = services.updatePerfilSNE(getPerfil(programaForm, request), null, null, programaForm.getModalidadCandidato(), beneficiaries, null);
			if (result > 0) {
				result = services.updateLPA(programaForm.getPerfil().getIdCandidato(), getMML(programaForm, request), referencia, getLastDegree(programaForm, request));
				saveRequiredLang(request, programaForm, false);
				saveAddStudieList(request);
			}
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		if (result > 0)
			msg = new ResultVO(getMensaje(request, "can.guardar.participacion", programaForm.getModalidad().getNombreCorto()), ResultVO.TYPE_SUCCESS);		
		else msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
		String json = toJson(msg);
		redirectJsonResponse(response, json);
		return null;		
	}
	
	public ActionForward saveStrengthenProfile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int result = 0;
		ResultVO msg =  null;
		ProgramaForm programaForm = (ProgramaForm) form;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		List<BeneficiarioVO> beneficiaries = new ArrayList<BeneficiarioVO>();
		try {
			result = services.updatePerfilSNE(getPerfil(programaForm, request), null, null, null, beneficiaries, null);
			if (result > 0) {
				result = services.updateLPA(programaForm.getPerfil().getIdCandidato(), getMML(programaForm, request), null, getLastDegree(programaForm, request));
				saveAddStudieList(request);
			}
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		if (result > 0)
			msg = new ResultVO(getMensaje(request, "can.guardar.ext", programaForm.getModalidad().getNombreCorto()), ResultVO.TYPE_SUCCESS);		
		else msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
		String json = toJson(msg);
		redirectJsonResponse(response, json);
		return null;		
	}
	
	public ActionForward saveStrengthenLangs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int result = 0;
		ResultVO msg =  null;
		ProgramaForm programaForm = (ProgramaForm) form;
		try {
			result = saveAddLangList(request, programaForm);
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		if (result > 0)
			msg = new ResultVO(getMensaje(request, "can.guardar.ext", programaForm.getModalidad().getNombreCorto()), ResultVO.TYPE_SUCCESS);		
		else msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
		String json = toJson(msg);
		redirectJsonResponse(response, json);
		return null;		
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward savecomplement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int result = 0;
		ResultVO msg =  null;
		ProgramaForm programaForm = (ProgramaForm) form;
		UsuarioWebVO user = getUsuario(request.getSession());
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		List<BeneficiarioVO> beneficiaries = (List<BeneficiarioVO>)request.getSession().getAttribute("LST_BENEFICIARIES_PTAT");
		try {
			Long idCandidato = user.getIdPropietario();
			services.updatePerfilSNE(getPerfil(programaForm, request), null, null, null, null, null);
			result = services.updateCandidateComplementData(getModalidad(programaForm), getFormatoPTAT(request, programaForm), getEngines(request, idCandidato), getProducts(request, idCandidato), getContacts(request), beneficiaries, getDomainLangs(request, programaForm), getClinicalHistory(request, idCandidato));
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		if (result > 0)
			msg = new ResultVO(getMensaje(request, "can.guardar.participacion", programaForm.getModalidad().getNombreCorto()), ResultVO.TYPE_SUCCESS);		
		else msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
		String json = toJson(msg);
		redirectJsonResponse(response, json);
		return null;		
	}
	
	public ActionForward saveReference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int result = 0;
		ResultVO msg =  null;
		ProgramaForm programaForm = (ProgramaForm) form;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		try {
			ReferenciaLaboralVO reference = programaForm.getReferenciaPrincipal();
			reference.setDomicilio(getDomicilioReference(request));
			reference.setTelefono(getPhoneReference(programaForm, request));
			reference.getTrabajoActual().setPuesto(request.getParameter("puesto"));
			reference.getTrabajoActual().setEmpresa(request.getParameter("empresa"));
			reference.getTrabajoActual().setFuncion(request.getParameter("funcion"));
			reference.getTrabajoActual().setPersonasCargo(Utils.parseLong(request.getParameter("personasCargo")));
			reference.getTrabajoActual().setIdJerarquia(Utils.parseLong(request.getParameter("idJerarquia")));
			reference.getTrabajoActual().setLaboresInicial(Utils.convert(request.getParameter("laboresInicial")));
			reference.getTrabajoActual().setLaboresFinal(Utils.convert(request.getParameter("laboresFinal")));
			reference.getTrabajoActual().setSalarioMensual(Utils.parseDouble(request.getParameter("salarioMensual")));
			if (null != request.getParameter("confidencialidadEmpresa"))
				reference.getTrabajoActual().setConfidencialidadEmpresa(Constantes.DECISION.SI.getIdOpcion());
			reference.getTrabajoActual().setHerramientas(request.getParameter("herramientas"));
			reference.getTrabajoActual().setRefNombre(request.getParameter("nombreReferencia"));
			reference.getTrabajoActual().setRefApellido1(request.getParameter("primerApellidoReferencia"));
			reference.getTrabajoActual().setRefApellido2(request.getParameter("segundoApellidoReferencia"));
			reference.getTrabajoActual().setRefPuesto(request.getParameter("puestoReferencia"));
			result = services.updateLPA(programaForm.getPerfil().getIdCandidato(), null, reference, null);
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		if (result > 0)
			msg = new ResultVO(getMensaje(request, "can.guardar.ext"), ResultVO.TYPE_SUCCESS);		
		else msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
		String json = toJson(msg);
		redirectJsonResponse(response, json);
		return null;		
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward addContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		@SuppressWarnings("unused")
		ResultVO msg =  null;
		UsuarioWebVO user = getUsuario(request.getSession());
		List<BeneficiarioVO> contacts = new ArrayList<BeneficiarioVO>();
		try {
			if (null != request.getSession().getAttribute("LST_CONTACTS"))
				contacts = (List<BeneficiarioVO>)request.getSession().getAttribute("LST_CONTACTS");
			BeneficiarioVO beneficiario = new BeneficiarioVO();
			beneficiario.setPrimerApellido(request.getParameter("primerApellidoCto"));
			beneficiario.setSegundoApellido(request.getParameter("segundoApellidoCto"));
			beneficiario.setIdParentesco(Utils.parseLong(request.getParameter("idParentescoCto")));
			beneficiario.setNombre(request.getParameter("nombreContacto"));
			beneficiario.setIdCandidato(user.getIdPropietario());
			beneficiario.setIdTipoFormato((long)TIPO_FORMATO_BENEFICIARIO.PTAT.getIdOpcion());
			beneficiario.setContacto(Constantes.DECISION.SI.getIdOpcion());
			TelefonoVO phone = new TelefonoVO();
			phone.setClave(request.getParameter("clave"));
			phone.setExtension(request.getParameter("extension"));
			phone.setIdTipoTelefono(Utils.parseInt(request.getParameter("tipoTelefono")));
			phone.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			phone.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.BENEFICIARIO.getIdTipoPropietario());
			phone.setTelefono(request.getParameter("telefono"));
			if (phone.getIdTipoTelefono() == 1) phone.setAcceso("044");
			else phone.setAcceso("01");
			beneficiario.setTelefono(phone);
			contacts.add(contacts.size(), beneficiario);
		}catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		if (contacts.size() >= Constantes.REGISTROS_ADICIONALES_PROGRAMAS)
			request.getSession().setAttribute("display", "none");
		request.getSession().setAttribute("LST_CONTACTS", contacts);
		msg = new ResultVO(getMensaje(request, "can.guardar.ext"), ResultVO.TYPE_SUCCESS);
		return mapping.findForward("CONTACTS");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward createReference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int result = 0;
		ProgramaForm programaForm = (ProgramaForm) form;
		ReferenciaLaboralVO reference = new ReferenciaLaboralVO();
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		List<ReferenciaLaboralVO> references = new ArrayList<ReferenciaLaboralVO>();
		mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO historial = new mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO();
		try {
			reference.setDomicilio(getDomicilioReference(request));
			if (null != request.getSession().getAttribute("LST_REFERENCES"))
				references = (List<ReferenciaLaboralVO>)request.getSession().getAttribute("LST_REFERENCES");
			historial.setPuesto(request.getParameter("puesto"));
			historial.setEmpresa(request.getParameter("empresa"));
			historial.setFuncion(request.getParameter("funcion"));
			historial.setPersonasCargo(Utils.parseLong(request.getParameter("personasCargo")));
			historial.setIdJerarquia(Utils.parseLong(request.getParameter("idJerarquia")));
			historial.setLaboresInicial(Utils.convert(request.getParameter("laboresInicial")));
			historial.setLaboresFinal(Utils.convert(request.getParameter("laboresFinal")));
			historial.setSalarioMensual(Utils.parseDouble(request.getParameter("salarioMensual")));
			if (null != request.getParameter("confidencialidadEmpresa"))
				historial.setConfidencialidadEmpresa(Constantes.DECISION.SI.getIdOpcion());
			historial.setHerramientas(request.getParameter("herramientas"));
			historial.setRefNombre(request.getParameter("nombreReferencia"));
			historial.setRefApellido1(request.getParameter("primerApellidoReferencia"));
			historial.setRefApellido2(request.getParameter("segundoApellidoReferencia"));
			historial.setRefPuesto(request.getParameter("puestoReferencia"));
			reference.setTrabajoActual(historial);
			reference.setTelefono(getPhoneReference(programaForm, request));
			result = services.updateLPA(programaForm.getPerfil().getIdCandidato(), null, reference, null);
			if (result > 0)
				references.add(reference);
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
		}
		if (references.size() >= Constantes.REGISTROS_ADICIONALES_PROGRAMAS)
			request.getSession().setAttribute("display", "none");
		request.getSession().setAttribute("LST_REFERENCES", references);
		return mapping.findForward("REFERENCES");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward deleteReference(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		@SuppressWarnings("unused")
		ResultVO msg =  null;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		List<ReferenciaLaboralVO> references = new ArrayList<ReferenciaLaboralVO>();
		List<ReferenciaLaboralVO> tmpReferences = new ArrayList<ReferenciaLaboralVO>();
		try {
			if (null != request.getSession().getAttribute("LST_REFERENCES"))
				references = (List<ReferenciaLaboralVO>)request.getSession().getAttribute("LST_REFERENCES");
			long idReference = Utils.parseLong(request.getParameter("idReference"));
			for (ReferenciaLaboralVO reference : references) {
				if (idReference == reference.getTrabajoActual().getIdHistorialLaboral())
					services.removeReferenciaLaboral(reference);
				else tmpReferences.add(reference);
			}
			if (tmpReferences.size() < Constantes.REGISTROS_ADICIONALES_PROGRAMAS)
				request.getSession().setAttribute("display", "block");
		}catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		request.getSession().setAttribute("LST_REFERENCES", tmpReferences);
		return mapping.findForward("REFERENCES");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward deleteContact(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		@SuppressWarnings("unused")
		ResultVO msg =  null;
		List<BeneficiarioVO> contacts = new ArrayList<BeneficiarioVO>();
		List<BeneficiarioVO> cts2del = new ArrayList<BeneficiarioVO>();
		try {
			if (null != request.getSession().getAttribute("LST_CONTACTS"))
				contacts = (List<BeneficiarioVO>)request.getSession().getAttribute("LST_CONTACTS");
			if (contacts.isEmpty()) return mapping.findForward("CONTACTS");
			int indexContact = Utils.parseInt(request.getParameter("indexContact"));
			if (-1 == indexContact) return mapping.findForward("CONTACTS");
			if (contacts.size() < indexContact) return mapping.findForward("CONTACTS");
			BeneficiarioVO contact = contacts.get(indexContact-1);
			contacts.remove(indexContact-1);
			if (null != contact.getIdBeneficiario() && contact.getIdBeneficiario() > 0) {
				if (null != request.getSession().getAttribute("CTS2DELIST"))
					cts2del = (List<BeneficiarioVO>)request.getSession().getAttribute("CTS2DELIST");
				cts2del.add(contact);
				request.getSession().setAttribute("CTS2DELIST", cts2del);
			}
			if (contacts.size() < Constantes.REGISTROS_ADICIONALES_PROGRAMAS)
				request.getSession().setAttribute("display", "block");
		}catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		request.getSession().setAttribute("LST_REFERENCES", contacts);
		return mapping.findForward("CONTACTS");
	}
	
	private FormatoSNE01VO getSNE(ProgramaForm programaForm, HttpServletRequest request) {
		programaForm.getSne().setCapacitacionDesc(request.getParameter("necesitaCapacitacion"));
		programaForm.getSne().setIdLenguaIndigena(Utils.parseLong(request.getParameter("idLenguaIndigena")));
		programaForm.getSne().setLenguaIndigenaConocimiento(Utils.parseInt(request.getParameter("lenguaIndigena")));
		programaForm.getSne().setCapacitacionRequiere(Utils.parseInt(request.getParameter("requiereCapacitacion")));
		programaForm.getSne().setCapacitacionDispone6h(Utils.parseInt(request.getParameter("tiempoCapacitacion")));
		programaForm.getSne().setNegocioTiene(Utils.parseInt(request.getParameter("negocioPropio")));
		programaForm.getSne().setNegocioDescGiro(request.getParameter("tipoNegocio"));
		programaForm.getSne().setNegocioPretende(Utils.parseInt(request.getParameter("pretendeNegocioPropio")));
		programaForm.getSne().setNegocioTieneRecursos(Utils.parseInt(request.getParameter("recursosNegocio")));
		programaForm.getSne().setBeneficiarioIdPrograma(request.getParameter("idProgramaApoyo"));
		programaForm.getSne().setBeneficiarioPrograma(Utils.parseInt(request.getParameter("beneficiarioApoyo")));
		programaForm.getSne().setClabeInterbancaria(request.getParameter("clabe"));
		programaForm.getSne().setIdBanco(Utils.parseInt(request.getParameter("idBanco")));
		if (null != programaForm.getPerfil().getUltimaContratacion()) {
			programaForm.getSne().setVacanteEnviado((int)Constantes.DECISION.SI.getIdOpcion());
			programaForm.getSne().setVacanteColocado((int)Constantes.DECISION.SI.getIdOpcion());
			programaForm.getSne().setVacanteNombre(programaForm.getPerfil().getUltimaContratacion().getTituloOferta());
			programaForm.getSne().setVacanteFecha(programaForm.getPerfil().getUltimaContratacion().getFechaColocacion());
		}else {
			programaForm.getSne().setVacanteNombre(request.getParameter("tituloOferta"));
			programaForm.getSne().setVacanteFecha(Utils.convert(request.getParameter("fechaVinculacion")));
			programaForm.getSne().setVacanteEnviado(Utils.parseInt(request.getParameter("cubrirVacante")));
			programaForm.getSne().setVacanteColocado(Utils.parseInt(request.getParameter("colocadoVacante")));
		}
		/**if (programaForm.getPerfil().getPerfilVO().getApoyoProspera() == Constantes.APOYO_OPORTUNIDADES.SI.getIdOpcion() 
				|| programaForm.getPerfil().getPerfilVO().getApoyoProspera() == Constantes.APOYO_OPORTUNIDADES.NO.getIdOpcion()) {
			programaForm.getSne().setBeneficiarioProspera(programaForm.getPerfil().getPerfilVO().getApoyoProspera());
		}else programaForm.getSne().setBeneficiarioProspera(Utils.parseInt(request.getParameter("ayudaProspera")));**/
		programaForm.getSne().setIdUsuario(programaForm.getPerfil().getIdUsuario());
		programaForm.getSne().setIdCandidato(programaForm.getPerfil().getIdCandidato());
		programaForm.getSne().setIdFuente(Constantes.SISTEMAS_PORTAL.PORTAL.getIdOpcion());
		programaForm.getSne().setDisponibilidadRadicarPais(Utils.parseInt(request.getParameter("disponibilidadRadicarPais")));
		return programaForm.getSne();
	}
	
	private GradoAcademicoVO getLastDegree(ProgramaForm programaForm, HttpServletRequest request) {
		programaForm.getPerfil().getGradoPrincipal().setEscuela(request.getParameter("institucion"));
		programaForm.getPerfil().getGradoPrincipal().setFechaInicio(Utils.convert(request.getParameter("fechaIngreso")));
		programaForm.getPerfil().getGradoPrincipal().setFechaFin(Utils.convert(request.getParameter("fechaTerminacion")));
		programaForm.getPerfil().getGradoPrincipal().setLugar(request.getParameter("lugar"));
		return programaForm.getPerfil().getGradoPrincipal();
	}
	
	private HistoriaLaboralVO getLastJob(ProgramaForm programaForm, HttpServletRequest request) {
		programaForm.getPerfil().getTrabajoActual().setEmpresa(request.getParameter("empresa"));
		programaForm.getPerfil().getTrabajoActual().setPuesto(request.getParameter("puesto"));
		programaForm.getPerfil().getTrabajoActual().setLaboresInicial(Utils.convert(request.getParameter("laboresInicial")));
		programaForm.getPerfil().getTrabajoActual().setLaboresFinal(Utils.convert(request.getParameter("laboresFinal")));
		programaForm.getPerfil().getTrabajoActual().setFuncion(request.getParameter("funcion"));
		programaForm.getPerfil().getTrabajoActual().setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		if (null != request.getParameter("confidencialidadEmpresa") && !request.getParameter("confidencialidadEmpresa").isEmpty())
			programaForm.getPerfil().getTrabajoActual().setConfidencialidadEmpresa(Utils.parseInt(request.getParameter("confidencialidadEmpresa")));
		if (null != request.getParameter("idJerarquia") && !request.getParameter("idJerarquia").isEmpty())
			programaForm.getPerfil().getTrabajoActual().setIdJerarquia(Utils.parseInt(request.getParameter("idJerarquia")));
		if (null != request.getParameter("personasCargo") && !request.getParameter("personasCargo").isEmpty())
			programaForm.getPerfil().getTrabajoActual().setPersonasCargo(Utils.parseInt(request.getParameter("personasCargo")));
		if (null != request.getParameter("salarioMensual") && !request.getParameter("salarioMensual").isEmpty())
			programaForm.getPerfil().getTrabajoActual().setSalarioMensual(Utils.parseDouble(request.getParameter("salarioMensual")));
		if (0 == programaForm.getPerfil().getTrabajoActual().getIdHistorialLaboral()) {
			programaForm.getPerfil().getTrabajoActual().setTrabajoActual((int)TRABAJA_ACTUALMENTE.SI.getIdOpcion());
		}
		if (null != request.getParameter("herramientas")) {
			programaForm.getPerfil().getTrabajoActual().setHerramientas(request.getParameter("herramientas"));
			programaForm.getPerfil().getTrabajoActual().setRefNombre(request.getParameter("nombreReferencia"));
			programaForm.getPerfil().getTrabajoActual().setRefApellido1(request.getParameter("primerApellidoReferencia"));
			programaForm.getPerfil().getTrabajoActual().setRefApellido2(request.getParameter("segundoApellidoReferencia"));
			programaForm.getPerfil().getTrabajoActual().setRefPuesto(request.getParameter("puestoReferencia"));
		}
		return programaForm.getPerfil().getTrabajoActual();
	}
	
	private ExpectativaLaboralVO getExpectativa(ProgramaForm programaForm, HttpServletRequest request) {
		if (null == programaForm.getPerfil().getExpectativaLaboral())
			programaForm.getPerfil().setExpectativaLaboral(new ExpectativaLaboralVO());
		programaForm.getPerfil().getExpectativaLaboral().setIdTipoContrato(Utils.parseLong(request.getParameter("idTipoContrato")));
		programaForm.getPerfil().getExpectativaLaboral().setIdTipoEmpleoDeseado(Utils.parseLong(request.getParameter("idTipoEmpleo")));
		return programaForm.getPerfil().getExpectativaLaboral();
	}
	
	private mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO getCurrentJob(PerfilJB perfil) {
		mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO currentJob = new mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO();
		if (null != perfil && null != perfil.getTrabajoActual()) {
			currentJob.setIdHistorialLaboral(perfil.getTrabajoActual().getIdHistorialLaboral());
			currentJob.setEmpresa(perfil.getTrabajoActual().getEmpresa());
			currentJob.setLaboresInicial(perfil.getTrabajoActual().getLaboresInicial());
			currentJob.setLaboresFinal(perfil.getTrabajoActual().getLaboresFinal());
			currentJob.setFuncion(perfil.getTrabajoActual().getFuncion());
			currentJob.setTrabajoActual((long)perfil.getTrabajoActual().getTrabajoActual());
			currentJob.setConfidencialidadEmpresa((long)perfil.getTrabajoActual().getConfidencialidadEmpresa());
			currentJob.setHerramientas(perfil.getTrabajoActual().getHerramientas());
			currentJob.setRefApellido1(perfil.getTrabajoActual().getRefApellido1());
			currentJob.setRefApellido2(perfil.getTrabajoActual().getRefApellido2());
			currentJob.setRefNombre(perfil.getTrabajoActual().getRefNombre());
			currentJob.setRefPuesto(perfil.getTrabajoActual().getRefPuesto());
			currentJob.setSalarioMensual(-1 != perfil.getTrabajoActual().getSalarioMensual() ? perfil.getTrabajoActual().getSalarioMensual() : null);
		}
		return currentJob;
	}
	
	private PerfilVO getPerfil(ProgramaForm programaForm, HttpServletRequest request) {
		PerfilVO vo = programaForm.getPerfil().getPerfilVO();
		vo.setCalle(programaForm.getPerfil().getCalle());
		vo.setyCalle(request.getParameter("yCalle"));
		vo.setPrincipal(programaForm.getPerfil().getPrincipal());
		vo.setIdEntidad(programaForm.getPerfil().getIdEntidad());
		vo.setIdColonia(programaForm.getPerfil().getIdColonia());
		vo.setEntreCalle(request.getParameter("entreCalle"));
		vo.setIdMunicipio(programaForm.getPerfil().getIdMunicipio());
		vo.setIdDomicilio(programaForm.getPerfil().getIdDomicilio());
		vo.setCodigoPostal(programaForm.getPerfil().getCodigoPostal());
		vo.setDomicilioRef(request.getParameter("referenciaDomicilio"));
		vo.setIdLocalidad(Utils.parseLong(request.getParameter("idLocalidad")));
		if (Utils.parseLong(request.getParameter("idEstadoCivil")) > -1)
			vo.setIdEstadoCivil(Utils.parseLong(request.getParameter("idEstadoCivil")));
		else vo.setIdEstadoCivil(programaForm.getPerfil().getIdEstadoCivil());
		vo.setNumeroExterior(programaForm.getPerfil().getNumeroExterior());
		vo.setNumeroInterior(request.getParameter("numeroInterior"));
		if (getIdRecibeOferta(request) > -1)
			vo.setIdRecibeOferta(getIdRecibeOferta(request));
		else vo.setIdRecibeOferta(programaForm.getPerfil().getIdRecibeOferta());
		vo.setContactoCorreo(programaForm.getPerfil().getContactoCorreo());
		vo.setContactoTelefono(programaForm.getPerfil().getContactoTelefono());
		vo.setIdTipoPropietario(programaForm.getPerfil().getIdTipoPropietario());
		if (null != request.getParameterValues("discapacidades"))
			vo.setDiscapacidades(getDiscapacidades(request.getParameterValues("discapacidades")));
		else vo.setDiscapacidades(programaForm.getPerfil().getPerfilVO().getDiscapacidades());
		if (null != request.getParameter("licManejoTiene") && 2 == Utils.parseInt(request.getParameter("licManejoTiene")))
			vo.setIdLicencia(Utils.parseInt(request.getParameter("licManejoTipo")));
		else vo.setIdLicencia(5);
		if (null != request.getParameter("folioProspera"))
			vo.setFolioProspera(request.getParameter("folioProspera"));
		vo.setDisponibilidadRadicarPais(Utils.parseInt(request.getParameter("disponibilidadRadicarPais")));
		return vo;
	}
	
	public void saveRequiredLang(HttpServletRequest request, ProgramaForm programaForm, boolean saveAll) {
		IdiomaVO lang = programaForm.getIdiomaReqIng();
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		long idCandidato = getUsuario(request.getSession()).getIdPropietario();
		List<IdiomaVO> langList = new ArrayList<IdiomaVO>();
		lang.setIdDominioEscrito(Utils.parseInt(request.getParameter("idDominioEscrito")));
		lang.setIdDominioHabla(Utils.parseInt(request.getParameter("idDominioHabla")));
		lang.setIdDominioLectura(Utils.parseInt(request.getParameter("idDominioLectura")));
		lang.setExamenId(Utils.parseInt(request.getParameter("examenId")));
		lang.setExamenPresentado(Utils.parseInt(request.getParameter("examenPresentado")));
		lang.setExamenPuntos(Utils.parseInt(request.getParameter("examenPuntos")));
		lang.setExamenFecha(Utils.convert(request.getParameter("examenFecha")));
		if (null != request.getParameter("idDominioReq")) lang.setIdDominio(Utils.parseInt(request.getParameter("idDominioReq")));
		if (null != request.getParameter("idCertificacionReq")) lang.setIdCertificacion(Utils.parseInt(request.getParameter("idCertificacionReq")));
		langList.add(lang);
		try {
			services.saveLangList(idCandidato, langList);
    	} catch (Exception e) { 
    		e.printStackTrace();
    		logger.error(e); 
    	}		
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward saveAddStudie(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		List<CandidatoOtroEstudioVO> otherStudies = null;
		UsuarioWebVO usuario = super.getUsuario(request.getSession());
    	int idTipoEstudio = Utils.parseInt(request.getParameter("studieTypeAdd"));
    	String nombreEstudio = request.getParameter("studieAdd");
    	String institucion = request.getParameter("institutionAdd");
    	String fechaInicio = request.getParameter("dateini");
    	String fechaFinal = request.getParameter("datefin");
    	int idEstatusAcademico = Utils.parseInt(request.getParameter("statusacademicAdd"));
    	CandidatoOtroEstudioVO studie = new CandidatoOtroEstudioVO();
    	studie.setIdEstatusAcademico(idEstatusAcademico);
    	studie.setNombreEstudio(nombreEstudio);
    	studie.setFechaFin(Utils.convert(fechaFinal));
    	studie.setFechaInicio(Utils.convert(fechaInicio));
    	studie.setInstitucion(institucion);
    	studie.setIdTipoEstudio(idTipoEstudio);
    	studie.setIdCandidato(usuario.getIdPropietario());
    	otherStudies = (List<CandidatoOtroEstudioVO>)request.getSession().getAttribute("OTHER_STUDIES_LIST");
		if (null != otherStudies) {
			if (otherStudies.isEmpty())
				studie.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			else
				studie.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
		}else {
			studie.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			otherStudies = new ArrayList<CandidatoOtroEstudioVO>();
		}
    	try {
    		otherStudies.add(studie);
		} catch (Exception e) { logger.error(e); }	
	    return mapping.findForward("OTROS_ESTUDIOS");	            	    	
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward deleteStudie(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<CandidatoOtroEstudioVO> studies2Del = null;
		String idEstudio = request.getParameter("idStudie");
		List<CandidatoOtroEstudioVO> studiesTmp = new ArrayList<CandidatoOtroEstudioVO>();
		if (null != request.getSession().getAttribute("OTHER_STUDIES_2DEL"))
			studies2Del = (List<CandidatoOtroEstudioVO>)request.getSession().getAttribute("OTHER_STUDIES_2DEL");
		else studies2Del = new ArrayList<CandidatoOtroEstudioVO>();
		List<CandidatoOtroEstudioVO> otherStudies = (List<CandidatoOtroEstudioVO>)request.getSession().getAttribute("OTHER_STUDIES_LIST");
		for (CandidatoOtroEstudioVO vo : otherStudies) {
			if (vo.getRow() == Utils.parseLong(idEstudio)) {
				if (vo.getIdCandidatoOtroEstudio() > 0) studies2Del.add(vo);
			}else studiesTmp.add(vo);
		}
		request.getSession().setAttribute("OTHER_STUDIES_LIST", studiesTmp);
		request.getSession().setAttribute("OTHER_STUDIES_2DEL", studies2Del);
        return mapping.findForward("OTROS_ESTUDIOS");
	}
	
	private void saveAddStudieList(HttpServletRequest request) {
		List<CandidatoOtroEstudioVO> otherStudies = null;
		request.getSession().removeAttribute("OTHER_STUDIES_LIST");
		for (int i=1; i<11; i++) {
			if (null != request.getParameter("idCandidateStudie" + i)) {
				long idTipoEstudio = Utils.parseLong(request.getParameter("studieTypeAdd" + i));
				String nombreEstudio = null != request.getParameter("studieAdd"  + i) ? request.getParameter("studieAdd"  + i) : "";
		    	String institucion = null != request.getParameter("institutionAdd"  + i) ? request.getParameter("institutionAdd"  + i) : "";
		    	long dayIniAdd = Utils.parseLong(request.getParameter("dayIniAdd" + i));
		    	long dayFinAdd = Utils.parseLong(request.getParameter("dayFinAdd" + i));
		    	long monthIniAdd = Utils.parseLong(request.getParameter("monthIniAdd" + i));
		    	long monthFinAdd = Utils.parseLong(request.getParameter("monthFinAdd" + i));
		    	long yearIniAdd = Utils.parseLong(request.getParameter("yearIniAdd" + i));
		    	long yearFinAdd = Utils.parseLong(request.getParameter("yearFinAdd" + i));
		    	long idEstatusAcademico = Utils.parseLong(request.getParameter("statusAcademicAdd" + i));
		    	long idCandidatoOtroEstudio = Utils.parseLong(request.getParameter("idCandidateStudie" + i));
		    	int principalAdd = Utils.parseInt(request.getParameter("principalAdd" + i));
		    	CandidatoOtroEstudioVO studie = new CandidatoOtroEstudioVO();
		    	String fechaFin = "" + yearFinAdd + "-" + monthFinAdd + "-" + dayFinAdd; 
		    	String fechaInicio = "" + yearIniAdd + "-" + monthIniAdd + "-" + dayIniAdd; 
		    	studie.setFechaFin(mx.gob.stps.portal.web.infra.utils.Utils.convertWebDate(fechaFin));
		    	studie.setFechaInicio(mx.gob.stps.portal.web.infra.utils.Utils.convertWebDate(fechaInicio));
		    	studie.setIdCandidatoOtroEstudio(idCandidatoOtroEstudio);
		    	studie.setIdEstatusAcademico(idEstatusAcademico);
		    	studie.setIdTipoEstudio(idTipoEstudio);
		    	studie.setInstitucion(institucion);
		    	studie.setNombreEstudio(nombreEstudio);
		    	studie.setPrincipal(principalAdd);
		    	try {
					CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
					if (idCandidatoOtroEstudio > 0)
						services.actualizarOtroEstudio(studie);
					else {
						studie.setIdOtroEstudio(0L);
						studie.setIdCandidato(getUsuario(request.getSession()).getIdPropietario());
						long id = services.registrarOtroEstudio(studie);
						studie.setIdCandidatoOtroEstudio(id);
					}
					otherStudies.add(studie);
		    	} catch (Exception e) { logger.error(e); }	
			}
		}
		deleteStudieList(request);
		request.getSession().setAttribute("OTHER_STUDIES_LIST", otherStudies);	            	    	
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward saveAddPhone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		java.util.Date date = new Date();
		List<TelefonoDTO> phoneAddList = null;
		UsuarioWebVO user = getUsuario(request.getSession());
		String clave = request.getParameter("claveAdd");
		String acceso = request.getParameter("accesoAdd");
		String telefono = request.getParameter("telefonoAdd");
		Integer idTipoTelefono = Utils.parseInt(request.getParameter("tipoAdd"));
		String extension = request.getParameter("extensionAdd");
		TelefonoDTO phone = new TelefonoDTO();
		phone.setAcceso(acceso);
		phone.setClave(clave);
		phone.setExtension(extension);
		phone.setFechaAlta(date);
		phone.setIdTipoTelefono((int) idTipoTelefono);
		phoneAddList = (List<TelefonoDTO>) request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES);
		if (null != phoneAddList) {
			if (phoneAddList.isEmpty()) {
				phone.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			} else {
				phone.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
			}
		} else {
			phone.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			phoneAddList = new ArrayList<TelefonoDTO>();
		}
		phone.setIdTipoPropietario((long)Constantes.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
		phone.setIdPropietario(user.getIdPropietario());
		phone.setTelefono(telefono);
		Long idPhone = 0L;
		try {
			phone.setIdTelefono(idPhone);
			phoneAddList.add(phone);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return mapping.findForward("TELEFONOS");
	}
	
	private void saveAddPhoneList(HttpServletRequest request) {
		java.util.Date date = new Date();
		List<TelefonoVO> phoneAddList = new ArrayList<TelefonoVO>();
		UsuarioWebVO user = getUsuario(request.getSession());
		request.getSession().removeAttribute(LST_TELEFONOS_ADICIONALES);
		for (int i=1; i<3; i++) {
			if (null != request.getParameter("idTelefonoAdd" + i)) {
				long idTelefono = Utils.parseLong(request.getParameter("idTelefonoAdd" + i));
				String clave = request.getParameter("claveAdd" + i);
				String telefono = request.getParameter("telefonoAdd" + i);
				Integer idTipoTelefono = Utils.parseInt(request.getParameter("idTipoTelefonoAdd" + i));
				String extension = request.getParameter("extensionAdd" + i);
				TelefonoVO phone = new TelefonoVO();
				phone.setClave(clave);
				phone.setIdTelefono(idTelefono);
				phone.setExtension(extension);
				phone.setFechaAlta(date);
				phone.setIdTipoTelefono((int)idTipoTelefono);
				phone.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());
				phone.setIdPropietario(user.getIdPropietario());
				phone.setTelefono(telefono);
				phone.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
				if (phone.getIdTipoTelefono() == Constantes.TELEFONO_CELULAR)
					phone.setAcceso(Constantes.TIPO_TELEFONO.CELULAR.getAcceso());
				else phone.setAcceso(Constantes.TIPO_TELEFONO.FIJO.getAcceso());
				RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
				try {
					if (idTelefono > 0)
						service.actualizarTelefono(phone);
					else {
						long idPhone = service.registrarTelefono(phone);
						phone.setIdTelefono(idPhone);
					}
				} catch (Exception e) { e.printStackTrace(); logger.error(e); }
				phoneAddList.add(phone);
			}
		}
		deletePhoneList(request);
		request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, getAddPhoneList(phoneAddList));	            	    	
	}
	
	private void initProfile(HttpServletRequest request, ProgramaForm programaForm, long idCandidato, String param) {
		IdiomaVO idiomaReq = new IdiomaVO();
		ReferenciaLaboralVO referenciaReq = new ReferenciaLaboralVO();
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		DomicilioBusDelegate delegate = DomicilioBusDelegateImpl.getInstance();
		try {
			PerfilJB perfil = services.loadPerfil(idCandidato);
			if (null != perfil) {
				programaForm.setDiscapacidad(new DiscapacidadBO(perfil.getPerfilVO().getDiscapacidades()));
				referenciaReq.setTrabajoActual(getCurrentJob(perfil));
				request.getSession().setAttribute("fechaIngreso", getParseDate(referenciaReq.getTrabajoActual().getLaboresInicial()));
				request.getSession().setAttribute("fechaTerminacion", getParseDate(referenciaReq.getTrabajoActual().getLaboresFinal()));
				if (param.equalsIgnoreCase("mml")) {
					TelefonoVO telefonoReq = new TelefonoVO();
					DomicilioVO domicilioReq = null;
					List<TelefonoVO> phoneList = services.initTelefonos(referenciaReq.getTrabajoActual().getIdHistorialLaboral(), Constantes.TIPO_PROPIETARIO.REFERENCIA_LABORAL.getIdTipoPropietario());
					if (null != phoneList && !phoneList.isEmpty()) telefonoReq = phoneList.get(0);
					referenciaReq.setTelefono(telefonoReq);
					domicilioReq = delegate.getDomicilio(referenciaReq.getTrabajoActual().getIdHistorialLaboral(), Constantes.TIPO_PROPIETARIO.REFERENCIA_LABORAL.getIdTipoPropietario());
					if (null != domicilioReq) referenciaReq.setDomicilio(domicilioReq);
					else referenciaReq.setDomicilio(new DomicilioVO());
					programaForm.setReferenciaPrincipal(referenciaReq);
					FormatoLPAVO mml = services.findLPAByCandidate(idCandidato);
					idiomaReq = requiredLang(idCandidato, Constantes.IDIOMAS.INGLES.getIdOpcion(), perfil);
					if (null != idiomaReq) {
						programaForm.setIdiomaReqIng(idiomaReq);
						request.getSession().setAttribute("fechaExamen", getParseDate(idiomaReq.getExamenFecha()));
					}else {
						idiomaReq = new IdiomaVO();
						idiomaReq.setIdIdioma(Constantes.IDIOMAS.INGLES.getIdOpcion());
						idiomaReq.setIdCandidato(idCandidato);
						idiomaReq.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
						programaForm.setIdiomaReqIng(idiomaReq);
					}
					request.getSession().setAttribute("IDIOMAS_LIST", perfil.getIdiomas());
					initLangList(request, perfil.getIdiomas(), programaForm);
					programaForm.setUltimoGrado(perfil.getGradoPrincipal());
					programaForm.setMml(null != mml ? mml : new FormatoLPAVO());
					programaForm.setIdiomasDependientes(obtenerCatalogosAsociadosDeIdiomas(request));
					List<ReferenciaLaboralVO> references = services.getReferenciaLaboraList(idCandidato);
					//if (!references.isEmpty()) programaForm.setReferenciaPrincipal(references.get(0));
					request.getSession().setAttribute("LST_REFERENCES", references);
					request.getSession().setAttribute("fechaIngresoInst", getParseDate(programaForm.getUltimoGrado().getFechaInicio()));
					request.getSession().setAttribute("fechaTerminacionInst", getParseDate(programaForm.getUltimoGrado().getFechaFin()));
					if (perfil.getIdLicencia() == 5) programaForm.setLicManejoTiene(1);
					else if (perfil.getIdLicencia() > 0) programaForm.setLicManejoTiene(2);
				}else if (param.equalsIgnoreCase("misi") || param.equalsIgnoreCase("beca") || param.equalsIgnoreCase("fa") || param.equalsIgnoreCase("misa")) {
					FormatoSNE01VO sne = services.findSNEByCandidate(idCandidato);
					programaForm.setSne(null != sne ? sne : new FormatoSNE01VO());
					if (null != perfil.getUltimaContratacion()) {
						programaForm.getSne().setVacanteEnviado((int)Constantes.DECISION.SI.getIdOpcion());
						programaForm.getSne().setVacanteColocado((int)Constantes.DECISION.SI.getIdOpcion());
						programaForm.getSne().setVacanteNombre(perfil.getUltimaContratacion().getTituloOferta());
						request.getSession().setAttribute("disabledOffer", "disabled=\"disabled\"");
						request.getSession().setAttribute("fechaVinculacion", getParseDate(perfil.getUltimaContratacion().getFechaColocacion()));
					}
					if (perfil.getPerfilVO().getApoyoProspera() == Constantes.APOYO_PROSPERA.SI.getIdOpcion() 
							|| perfil.getPerfilVO().getApoyoProspera() == Constantes.APOYO_PROSPERA.NO.getIdOpcion()) {
						request.getSession().setAttribute("disabledProsp", "disabled=\"disabled\"");
					}
					List<BeneficiarioVO> beneficiaries = services.getBeneficiarioList(idCandidato, TIPO_FORMATO_BENEFICIARIO.MLI.getIdOpcion(), false);
					request.getSession().setAttribute("LST_BENEFICIARIES", getWrapperBeneficiaryList(beneficiaries));
					request.getSession().setAttribute("KNOWLEDGE_LIST", perfil.getConocimientos());
					if (beneficiaries.size() >= Constantes.REGISTROS_ADICIONALES_PROGRAMAS)
						request.getSession().setAttribute("addisplay", "block");
					request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, getAddPhoneList(perfil.getSecundarios()));
					request.getSession().setAttribute("laboresInicial", getParseDate(perfil.getTrabajoActual().getLaboresInicial()));
					request.getSession().setAttribute("laboresFinal", getParseDate(perfil.getTrabajoActual().getLaboresFinal()));
				}else {
					FormatoPTATVO ptat = services.getFormatoPTATVO(idCandidato);
					if (null == ptat) ptat = new FormatoPTATVO();
					programaForm.setPtat(ptat);
					idiomaReq = requiredLang(idCandidato, Constantes.IDIOMAS.INGLES.getIdOpcion(), perfil);
					if (null != idiomaReq) programaForm.setIdiomaReqIng(idiomaReq);
					IdiomaVO idiomaReqFrn = requiredLang(idCandidato, Constantes.IDIOMAS.FRANCES.getIdOpcion(), perfil);
					if (null != idiomaReqFrn) programaForm.setIdiomaReqFrn(idiomaReqFrn);
					request.getSession().setAttribute("addisplay", "block");
					request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, perfil.getSecundarios());
				}
				programaForm.setPerfil(perfil);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getParseDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
		if (null != date) return formatter.format(date);
		return "";
	}
	
	private IdiomaVO requiredLang(long idCandidato, long idIdioma, PerfilJB perfil) {
		if (null != perfil && null != perfil.getIdiomaPrincipal() && perfil.getIdiomaPrincipal().getIdIdioma() == idIdioma) {
			perfil.getIdiomaPrincipal().setExamenPresentado((int)Constantes.DECISION.SI.getIdOpcion());
			return perfil.getIdiomaPrincipal();
		}else {
			List<IdiomaVO> idiomaList = perfil.getIdiomas();
			if (!idiomaList.isEmpty()) {
				for (IdiomaVO idioma : idiomaList) {
					if (idioma.getIdIdioma() == idIdioma) {
						if (idioma.getIdCertificacion() > 0  && idioma.getIdCertificacion() != 2)
							idioma.setExamenPresentado((int)Constantes.DECISION.SI.getIdOpcion());
						return idioma;
					}
				}
			}
		}
		return null;
	}
	
	private List<CandidatoOtroEstudioVO> getOtherStudies(long idCandidato) {
		List<CandidatoOtroEstudioVO> otherStudies = new ArrayList<CandidatoOtroEstudioVO>();
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		try {
			otherStudies = services.otrosEstudiosList(idCandidato);
		} catch (Exception e) {
			e.printStackTrace();
			otherStudies = new ArrayList<CandidatoOtroEstudioVO>();
		}
		return otherStudies;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward createBeneficiary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		@SuppressWarnings("unused")
		ResultVO msg =  null;
		DomicilioVO domicilio = new DomicilioVO();
		UsuarioWebVO user = getUsuario(request.getSession());
		List<BeneficiarioBO> beneficiaries = new ArrayList<BeneficiarioBO>();
		try {
			if (null != request.getSession().getAttribute("LST_BENEFICIARIES"))
				beneficiaries = (List<BeneficiarioBO>)request.getSession().getAttribute("LST_BENEFICIARIES");
			BeneficiarioBO beneficiario = new BeneficiarioBO();
			beneficiario.setPrimerApellido(request.getParameter("apellido1"));
			beneficiario.setSegundoApellido(request.getParameter("apellido2"));
			if (-1 != Utils.parseInt(request.getParameter("edad")))
				beneficiario.setEdad(Utils.parseLong(request.getParameter("edad")));
			beneficiario.setIdParentesco(Utils.parseLong(request.getParameter("parentesco")));
			beneficiario.setNombre(request.getParameter("nombre"));
			beneficiario.setIdCandidato(user.getIdPropietario());
			beneficiario.setPorcentaje(Utils.parseInt(request.getParameter("porcentaje")));
			beneficiario.setIdTipoFormato((long)Constantes.TIPO_FORMATO_BENEFICIARIO.MLI.getIdOpcion());
			beneficiario.setContacto(Constantes.DECISION.NO.getIdOpcion());
			domicilio.setCalle(request.getParameter("calle"));
			domicilio.setCodigoPostal(request.getParameter("cp"));
			domicilio.setEntreCalle(request.getParameter("ecalle"));
			domicilio.setIdColonia(Utils.parseLong(request.getParameter("idcolonia")));
			domicilio.setIdEntidad(Utils.parseLong(request.getParameter("identidad")));
			domicilio.setIdMunicipio(Utils.parseLong(request.getParameter("idmunicipio")));
			domicilio.setIdLocalidad(Utils.parseLong(request.getParameter("idlocalidad")));
			domicilio.setIdPropietario(user.getIdPropietario());
			domicilio.setIdTipoPropietario(new Long(Constantes.TIPO_PROPIETARIO.BENEFICIARIO.getIdTipoPropietario()));
			domicilio.setNumeroExterior(request.getParameter("numext"));
			domicilio.setNumeroInterior(request.getParameter("numint"));
			domicilio.setyCalle(request.getParameter("ycalle"));
			beneficiario.setDomicilio(domicilio);
			if (!isValidPercentage(beneficiario.getPorcentaje(), beneficiaries))
				msg = new ResultVO(getMensaje(request, "can.guardar.beneficiario.porcentaje"), ResultVO.TYPE_ERROR);
			else {
				beneficiaries.add(beneficiario);
			}
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		if (beneficiaries.size() >= Constantes.REGISTROS_ADICIONALES_PROGRAMAS)
			request.getSession().setAttribute("display", "none");
		request.getSession().setAttribute("LST_BENEFICIARIES", beneficiaries);
	    return mapping.findForward("BENEFICIARIOS");	
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward removeBeneficiary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		@SuppressWarnings("unused")
		ResultVO msg =  null;
		List<BeneficiarioBO> beneficiaries = new ArrayList<BeneficiarioBO>();
		List<BeneficiarioBO> beneficiartmp = new ArrayList<BeneficiarioBO>();
		List<BeneficiarioBO> beneficiaries2Del = new ArrayList<BeneficiarioBO>();
		long idbeneficiario = Utils.parseLong(request.getParameter("idbeneficiario"));
		try {
			if (null != request.getSession().getAttribute("LST_BENEFICIARIES"))
				beneficiaries = (List<BeneficiarioBO>)request.getSession().getAttribute("LST_BENEFICIARIES");
			if (null != request.getSession().getAttribute("LST_BENEFICIARIES_2DEL"))
				beneficiaries2Del = (List<BeneficiarioBO>)request.getSession().getAttribute("LST_BENEFICIARIES_2DEL");
			for (BeneficiarioBO beneficiario : beneficiaries) {
				if (beneficiario.getIndex() == idbeneficiario) {
					if (beneficiario.getIdBeneficiario() > 0) beneficiaries2Del.add(beneficiario);
				}else beneficiartmp.add(beneficiario);
			}
			if (beneficiartmp.size() < Constantes.REGISTROS_ADICIONALES_PROGRAMAS)
				request.getSession().setAttribute("display", "block");
		}catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		request.getSession().setAttribute("LST_BENEFICIARIES", beneficiartmp);
		request.getSession().setAttribute("LST_BENEFICIARIES_2DEL", beneficiaries2Del);
	    return mapping.findForward("BENEFICIARIOS");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward saveBeneficiary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		@SuppressWarnings("unused")
		ResultVO msg =  null;
		DomicilioVO domicilio = new DomicilioVO();
		ProgramaForm programaForm = (ProgramaForm)form;
		UsuarioWebVO user = getUsuario(request.getSession());
		List<BeneficiarioVO> beneficiaries = new ArrayList<BeneficiarioVO>();
		DomicilioBusDelegate service = DomicilioBusDelegateImpl.getInstance();
		try {
			if (null != request.getSession().getAttribute("LST_BENEFICIARIES_PTAT"))
				beneficiaries = (List<BeneficiarioVO>)request.getSession().getAttribute("LST_BENEFICIARIES_PTAT");
			BeneficiarioVO beneficiary = new BeneficiarioVO();
			beneficiary.setIdCandidato(user.getIdPropietario());
			beneficiary.setContacto(Constantes.DECISION.NO.getIdOpcion());
			beneficiary.setNombre(request.getParameter("nombre_beneficiario"));
			beneficiary.setPrimerApellido(request.getParameter("apellido_1_beneficiario"));
			beneficiary.setSegundoApellido(request.getParameter("apellido_2_beneficiario"));
			if (-1 != Utils.parseLong(request.getParameter("edadBeneficiario")))
				beneficiary.setEdad(Utils.parseLong(request.getParameter("edadBeneficiario")));
			beneficiary.setIdTipoFormato((long)TIPO_FORMATO_BENEFICIARIO.PTAT.getIdOpcion());
			beneficiary.setPorcentaje(Utils.parseInt(request.getParameter("porcentajeBeneficiario")));
			beneficiary.setIdParentesco(Utils.parseLong(request.getParameter("idParentescoBeneficiario")));
			String[] values = request.getParameterValues("estado");
			if (null != values) {
				for (int i=0; i<values.length; i++) {
					if (1 == Utils.parseInt(values[i])) beneficiary.setDependiente(Constantes.DECISION.SI.getIdOpcion());
					else if (2 == Utils.parseInt(values[i])) beneficiary.setBeneficiario(Constantes.DECISION.SI.getIdOpcion());
					else if (3 == Utils.parseInt(values[i])) beneficiary.setFinado(Constantes.DECISION.SI.getIdOpcion());
				}
			}
			domicilio.setCalle(request.getParameter("calleBeneficiario"));
			domicilio.setCodigoPostal(request.getParameter("codigoPostalBeneficiario"));
			domicilio.setEntreCalle(request.getParameter("entreCalleBeneficiario"));
			domicilio.setIdColonia(Utils.parseLong(request.getParameter("idColoniaBeneficiario")));
			domicilio.setIdEntidad(Utils.parseLong(request.getParameter("idEntidadBeneficiario")));
			domicilio.setIdMunicipio(Utils.parseLong(request.getParameter("idMunicipioBeneficiario")));
			domicilio.setIdLocalidad(Utils.parseLong(request.getParameter("idLocalidadBeneficiario")));
			domicilio.setIdTipoPropietario(new Long(Constantes.TIPO_PROPIETARIO.BENEFICIARIO.getIdTipoPropietario()));
			domicilio.setNumeroExterior(request.getParameter("numExtBeneficiario"));
			domicilio.setNumeroInterior(request.getParameter("numIntBeneficiario"));
			domicilio.setyCalle(request.getParameter("yCalleBeneficiario"));
			service.setEntidadMunicipio(domicilio);
			beneficiary.setDomicilio(domicilio);
			if (null != beneficiary.getDependiente() && beneficiary.getDependiente() == Constantes.DECISION.SI.getIdOpcion() || (beneficiary.getEdad() > 0 && beneficiary.getEdad() < 18))
				programaForm.getPtat().setNumeroDependientes(null!=programaForm.getPtat().getNumeroDependientes() ? programaForm.getPtat().getNumeroDependientes() + 1 : 1);
			beneficiaries.add(beneficiary);
		} catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		if (beneficiaries.size() >= Constantes.REGISTROS_ADICIONALES_PROGRAMAS)
			request.getSession().setAttribute("display", "none");
		request.getSession().setAttribute("LST_BENEFICIARIES_PTAT", beneficiaries);
	    return mapping.findForward("ACTION_REGISTROS_TABLA");	
	}
	
	private List<BeneficiarioBO> getWrapperBeneficiaryList(List<BeneficiarioVO> beneficiaries) {
		List<BeneficiarioBO> wrapperList = new ArrayList<BeneficiarioBO>();
		for (BeneficiarioVO vo : beneficiaries) {
			BeneficiarioBO bo = new BeneficiarioBO();
			try {
				BeanUtils.copyProperties(bo, vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			wrapperList.add(bo);
		}
		return wrapperList;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward deleteBeneficiary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		@SuppressWarnings("unused")
		ResultVO msg =  null;
		List<BeneficiarioVO> beneficiaries = new ArrayList<BeneficiarioVO>();
		List<BeneficiarioVO> beneficiartmp = new ArrayList<BeneficiarioVO>();
		try {
			if (null != request.getSession().getAttribute("LST_BENEFICIARIES_PTAT"))
				beneficiaries = (List<BeneficiarioVO>)request.getSession().getAttribute("LST_BENEFICIARIES_PTAT");
			if (beneficiaries.isEmpty()) return mapping.findForward("ACTION_REGISTROS_TABLA");
			int indexBeneficiary = Utils.parseInt(request.getParameter("idbeneficiario"));
			if (-1 == indexBeneficiary) return mapping.findForward("ACTION_REGISTROS_TABLA");
			if (beneficiaries.size() < indexBeneficiary) return mapping.findForward("ACTION_REGISTROS_TABLA");
			BeneficiarioVO vo = beneficiaries.get(indexBeneficiary);
			beneficiaries.remove(indexBeneficiary);
			if (null != vo.getIdBeneficiario() && vo.getIdBeneficiario() > 0) {
				if (null != request.getSession().getAttribute("BVO2DELIST"))
					beneficiartmp = (List<BeneficiarioVO>)request.getSession().getAttribute("BVO2DELIST");
				beneficiartmp.add(vo);
				request.getSession().setAttribute("BVO2DELIST", beneficiartmp);
			}
			if (beneficiaries.size() < Constantes.REGISTROS_ADICIONALES_PROGRAMAS)
				request.getSession().setAttribute("display", "block");
		}catch (Exception e) {
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace(); logger.error(e);
		}
		request.getSession().setAttribute("LST_BENEFICIARIES_PTAT", beneficiaries);
	    return mapping.findForward("ACTION_REGISTROS_TABLA");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward isValidPercent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResultVO msg =  null;
		ProgramaForm programaForm = (ProgramaForm) form;
		int percent = Utils.parseInt(request.getParameter("porcentaje"));
		List<BeneficiarioVO> beneficiaries = new ArrayList<BeneficiarioVO>();
		if (programaForm.getModalidad().getParam().equalsIgnoreCase("ptat")) {
			if (null != request.getSession().getAttribute("LST_BENEFICIARIES_PTAT"))
				beneficiaries = (List<BeneficiarioVO>)request.getSession().getAttribute("LST_BENEFICIARIES_PTAT");
		}else if (null != request.getSession().getAttribute("LST_BENEFICIARIES"))
			beneficiaries = (List<BeneficiarioVO>)request.getSession().getAttribute("LST_BENEFICIARIES");
		if (-1 == percent) msg = new ResultVO(getMensaje(request, "can.guardar.beneficiario.err.porcentaje"), ResultVO.TYPE_ERROR);
		else if (!isValidPercentage(percent, this.getWrapperBeneficiaryList(beneficiaries))) msg = new ResultVO(getMensaje(request, "can.guardar.beneficiario.porcentaje"), ResultVO.TYPE_ERROR);
		else msg = new ResultVO(getMensaje(request, "boton.aceptar"), ResultVO.TYPE_SUCCESS);
		String json = toJson(msg);
		try {
			redirectJsonResponse(response, json);
		} catch (IOException e) {logger.error(e);}
		return null;
	}
	
	private boolean isValidPercentage(int porcentaje, List<BeneficiarioBO> beneficiaries) {
		int pcg = porcentaje;
		for (BeneficiarioVO beneficiary : beneficiaries) {
			pcg += beneficiary.getPorcentaje();
		}
		if (pcg < 101) return true;
		return false;
	}
	
	public ActionForward idiomas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long[] filtro_idioma = {Constantes.IDIOMAS.NO_REQUISITO.getIdOpcion(), Constantes.IDIOMAS.NINGUNO.getIdOpcion()};
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward saveAddLang(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResultVO msg =  null;
		IdiomaVO lang = new IdiomaVO();
		List<IdiomaVO> idiomasVO = null;
		long idCertificacion = Utils.parseLong(request.getParameter("idCertificacionAdd"));
		long idDominio = Utils.parseLong(request.getParameter("idDominioAdd"));
		long idioma = Utils.parseLong(request.getParameter("idIdiomaAdd"));
		lang.setIdCertificacion(idCertificacion);
		lang.setIdDominio(idDominio);
		lang.setIdIdioma(idioma);
		UsuarioWebVO user = getUsuario(request.getSession());
		try {
			// Validar idioma duplicado
			idiomasVO = this.getLangList(user.getIdPropietario());
			if (null != idiomasVO) {
				if (idiomasVO.isEmpty())
					lang.setPrincipal( Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				else
					lang.setPrincipal( Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
			} else {
				lang.setPrincipal( Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
				idiomasVO = new ArrayList<IdiomaVO>();
			}
			boolean _existe = false;
			for (IdiomaVO idiomaPrev : idiomasVO) {
				_existe = (idiomaPrev.getIdIdioma() == idioma);
				if (_existe)
					break;
			}
			if (!_existe) {
				CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
				services.agregarIdioma(user.getIdPropietario(), lang);
				idiomasVO.add(lang);
			}
			msg = new ResultVO(getMensaje(request, "can.guardar.ext"), ResultVO.TYPE_SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			msg = new ResultVO(getMensaje(request, "can.guardar.err"), ResultVO.TYPE_ERROR);
		}
		String json = toJson(msg);
		redirectJsonResponse(response, json);
		return null;
	}
	
	public ActionForward idiomaDominio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ProgramaForm programaForm = (ProgramaForm)form;
		String idioma = request.getParameter("vIdioma");
		String dominio = request.getParameter("idDominio");
		if (idioma!=null&&!idioma.equals("")) {
			long idiomal = Long.valueOf(idioma);
			long idCertifiacionAdd=0;
			long idMultiRegistro=Long.parseLong(request.getParameter("idMultiRegistro"));
			long idDominio = 0L;
			if (dominio != null && !dominio.equals("")) {
				idDominio = Long.parseLong(dominio);
			}
			if (idMultiRegistro==1) {
				programaForm.getIdiomasAdicionales().get(0).setIdIdioma(idiomal);
				programaForm.getIdiomasAdicionales().get(0).setIdCertificacion(idCertifiacionAdd);
				programaForm.getIdiomasAdicionales().get(0).setIdDominio(idDominio);
				if (3 == idDominio) {
					programaForm.getIdiomasAdicionales().get(0).setIdDominioEscrito((int)idDominio);
					programaForm.getIdiomasAdicionales().get(0).setIdDominioHabla((int)idDominio);
					programaForm.getIdiomasAdicionales().get(0).setIdDominioLectura((int)idDominio);
				}else {
					programaForm.getIdiomasAdicionales().get(0).setIdDominioEscrito(0);
					programaForm.getIdiomasAdicionales().get(0).setIdDominioHabla(0);
					programaForm.getIdiomasAdicionales().get(0).setIdDominioLectura(0);
				}
				programaForm.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idiomal, programaForm.getIdiomasDependientes()));
				
			}
			if (idMultiRegistro==2) {
				programaForm.getIdiomasAdicionales().get(1).setIdIdioma(idiomal);
				programaForm.getIdiomasAdicionales().get(1).setIdCertificacion(0);
				programaForm.getIdiomasAdicionales().get(1).setIdDominio(idDominio);
				if (3 == idDominio) {
					programaForm.getIdiomasAdicionales().get(1).setIdDominioEscrito((int)idDominio);
					programaForm.getIdiomasAdicionales().get(1).setIdDominioHabla((int)idDominio);
					programaForm.getIdiomasAdicionales().get(1).setIdDominioLectura((int)idDominio);
				}else {
					programaForm.getIdiomasAdicionales().get(1).setIdDominioEscrito(0);
					programaForm.getIdiomasAdicionales().get(1).setIdDominioHabla(0);
					programaForm.getIdiomasAdicionales().get(1).setIdDominioLectura(0);
				}
				programaForm.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(idiomal, programaForm.getIdiomasDependientes()));
			}
			if (idMultiRegistro==3) {
				programaForm.getIdiomasAdicionales().get(2).setIdIdioma(idiomal);
				programaForm.getIdiomasAdicionales().get(2).setIdCertificacion(0);
				programaForm.getIdiomasAdicionales().get(2).setIdDominio(idDominio);
				if (3 == idDominio) {
					programaForm.getIdiomasAdicionales().get(2).setIdDominioEscrito((int)idDominio);
					programaForm.getIdiomasAdicionales().get(2).setIdDominioHabla((int)idDominio);
					programaForm.getIdiomasAdicionales().get(2).setIdDominioLectura((int)idDominio);
				}else {
					programaForm.getIdiomasAdicionales().get(2).setIdDominioEscrito(0);
					programaForm.getIdiomasAdicionales().get(2).setIdDominioHabla(0);
					programaForm.getIdiomasAdicionales().get(2).setIdDominioLectura(0);
				}
				programaForm.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(idiomal, programaForm.getIdiomasDependientes()));
			}			
		}
		return mapping.findForward("JSP_LANG");
	}
	
	public int saveAddLangList(HttpServletRequest request, ProgramaForm programaForm) {	
		int result = 0;
		List<IdiomaVO> langs = new ArrayList<IdiomaVO>();
		programaForm.getPerfil().getIdiomaPrincipal().setIdDominioHabla(Utils.parseInt(request.getParameter("idDominioHabla")));
		programaForm.getPerfil().getIdiomaPrincipal().setIdDominioEscrito(Utils.parseInt(request.getParameter("idDominioEscrito")));
		programaForm.getPerfil().getIdiomaPrincipal().setIdDominioLectura(Utils.parseInt(request.getParameter("idDominioLectura")));
		langs.add(programaForm.getPerfil().getIdiomaPrincipal());
		for (IdiomaVO lang : programaForm.getIdiomasAdicionales()) {
			for (result = 1; result < 4; result++) {
				if (null != request.getParameter("idCandidatoIdiomaAdicional_" + result) 
						&& lang.getIdCandidatoIdioma() == Utils.parseInt(request.getParameter("idCandidatoIdiomaAdicional_" + result))) {
					lang.setIdIdioma(Utils.parseInt(request.getParameter("idIdiomaAdicional_" + result)));
					lang.setIdDominio(Utils.parseInt(request.getParameter("idDominioIdiomaAdd_" + result)));
					lang.setIdCertificacion(Utils.parseInt(request.getParameter("idCertificacionIdiomaAdd_" + result)));
					lang.setIdDominioEscrito(Utils.parseInt(request.getParameter("idDominioHablaAdd" + result)));
					lang.setIdDominioHabla(Utils.parseInt(request.getParameter("idDominioEscritoAdd" + result)));
					lang.setIdDominioLectura(Utils.parseInt(request.getParameter("idDominioLecturaAdd" + result)));
					langs.add(lang);
					result++;
				}
			}
		}
		try {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			long idCandidato = getUsuario(request.getSession()).getIdPropietario();
			result = services.saveLangList(idCandidato, langs);
    	} catch (Exception e) { 
    		e.printStackTrace();
    		logger.error(e); 
    	}	
		return result;
    }
	
	public ActionForward eliminarIdiomaAdicional(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idIdiomaAdd=Long.parseLong(request.getParameter("idIdiomaEliminar"));
		long idCertificacionIdiomaAdd=Long.parseLong(request.getParameter("idCertificacionIdiomaEliminar"));
		EscolaridadForm forma = (EscolaridadForm) form;
		List<IdiomaVO> idiomasAdicionales = forma.getIdiomasAdicionales();
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		int indiceIdioma=0;
		for(IdiomaVO vo: idiomasAdicionales){
			if((vo.getIdIdioma()==idIdiomaAdd)&&(vo.getIdCertificacion()==idCertificacionIdiomaAdd)) {
				try {
					services.borrarIdioma(vo.getIdCandidatoIdioma());
				} catch (Exception e) { logger.error(e); e.printStackTrace();}
				idiomasAdicionales.remove(indiceIdioma);
				break;
			}
			indiceIdioma++;
		}
		for(int i=0;i<idiomasAdicionales.size();i++){
			if(i==0)forma.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idiomasAdicionales.get(i).getIdIdioma(),forma.getIdiomasDependientes()));
			if(i==1)forma.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(idiomasAdicionales.get(i).getIdIdioma(),forma.getIdiomasDependientes()));
			if(i==2)forma.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(idiomasAdicionales.get(i).getIdIdioma(),forma.getIdiomasDependientes()));
		}
		forma.setIdiomasAdicionales(idiomasAdicionales);
		forma.setTotalIdiomasAdicionales(forma.getIdiomasAdicionales().size());
		return mapping.findForward("JSP_LANG");
	}
	
	public ActionForward addLang(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idDominio = 0;
		ProgramaForm programaForm = (ProgramaForm) form;
		long idIdiomaAdd=Long.parseLong(request.getParameter("idIdiomaAdd"));
		String idDominioIdiomaAdd=request.getParameter("idDominioIdiomaAdd");
		String idCertificacionIdiomaAddStr = request.getParameter("idCertificacionIdiomaAdd");
		long idCertificacionIdiomaAdd=0L;
		if (idCertificacionIdiomaAddStr != null && !idCertificacionIdiomaAddStr.isEmpty())
			idCertificacionIdiomaAdd = Utils.parseLong(request.getParameter("idCertificacionIdiomaAdd"));
		if (idIdiomaAdd > 0) {
			boolean existe = existeIdiomaEnListado(programaForm.getIdiomasAdicionales(), idIdiomaAdd, idCertificacionIdiomaAdd);
			if (!existe) {
				if(programaForm.getIdiomasAdicionales()==null || programaForm.getIdiomasAdicionales().isEmpty())
					programaForm.setIdiomasAdicionales(new ArrayList<IdiomaVO>());
				if (idDominioIdiomaAdd!=null && !idDominioIdiomaAdd.equals(""))
					idDominio = Long.parseLong(idDominioIdiomaAdd);
				agregarCatalogosIdiomasDependientes(programaForm, idIdiomaAdd);
				IdiomaVO idiomaNuevo = setIdiomaAdicional(idIdiomaAdd, idCertificacionIdiomaAdd, idDominio, Utils.parseInt(request.getParameter("idHabloIdiomaAdd")), Utils.parseInt(request.getParameter("idEscriboIdiomaAdd")), Utils.parseInt(request.getParameter("idLeoIdiomaAdd")));
				programaForm.getIdiomasAdicionales().add(idiomaNuevo);				
			}				
			programaForm.setTotalIdiomasAdicionales(programaForm.getIdiomasAdicionales().size());
		}
		return mapping.findForward("JSP_LANG");
	}
	
	@SuppressWarnings("unchecked")
	private void saveAddKnowledgeList(ProgramaForm programaForm, HttpServletRequest request) {
		request.getSession().removeAttribute("KNOWLEDGE_LIST");
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		ConocimientoHabilidadVO principal = getConocimientoPrincipal(programaForm, request);
		try {
			services.update(principal);
		} catch (Exception e) { logger.error(e); }
		for (int i=1; i<4; i++) {
			String conocimientoHabilidad = request.getParameter("conocimientoAdd" + i);
			if (null != conocimientoHabilidad && !conocimientoHabilidad.isEmpty()) {
				long idCandidatoConocimHabilidad = Utils.parseLong(request.getParameter("idCandidatoConocAdd" + i));
				long idExperiencia = Utils.parseLong(request.getParameter("idExperienciaSelectAdd" + i));
		    	String descripcion = null != request.getParameter("descripcionConAdd"  + i) ? request.getParameter("descripcionConAdd"  + i) : "";
		    	ConocimientoHabilidadVO conocHabVO = new ConocimientoHabilidadVO();
		    	conocHabVO.setDescripcion(descripcion);
		    	conocHabVO.setIdExperiencia(idExperiencia);
		    	conocHabVO.setConocimientoHabilidad(conocimientoHabilidad);
		    	conocHabVO.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		    	conocHabVO.setIdCandidatoConocimHabilidad(idCandidatoConocimHabilidad);
		    	conocHabVO.setIdTipoConocimHabilidad(CONOC_HAB.CONOCIMIENTO.getIdOpcion());
		    	conocHabVO.setIdCandidato(getUsuario(request.getSession()).getIdPropietario());
		    	try {
					services.update(conocHabVO);
		    	} catch (Exception e) { logger.error(e); }	
			}
		}
		List <ConocimientoHabilidadVO> know2DeList = (List <ConocimientoHabilidadVO>)request.getSession().getAttribute("KNOWLEDGE_2DELIST");
		if (null != know2DeList && !know2DeList.isEmpty()) {
			try {
				for (ConocimientoHabilidadVO vo : know2DeList) {
					services.borrarConocHab(vo.getIdCandidatoConocimHabilidad());
				}
			} catch (Exception e) { logger.error(e); }
			request.getSession().removeAttribute("KNOWLEDGE_2DELIST");
		}
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward saveAddKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	long idExperiencia = Utils.parseLong(request.getParameter("idExperienciaSelectAdd"));
    	String conocimientoHabilidad = request.getParameter("conocimientoAdd");
    	String descripcion = request.getParameter("descripcionConAdd");
    	ConocimientoHabilidadVO conocHabVO = new ConocimientoHabilidadVO();
    	conocHabVO.setDescripcion(descripcion);
    	conocHabVO.setIdExperiencia(idExperiencia);
    	conocHabVO.setConocimientoHabilidad(conocimientoHabilidad);
    	conocHabVO.setIdTipoConocimHabilidad(CONOC_HAB.CONOCIMIENTO.getIdOpcion());
    	conocHabVO.setIdCandidato(getUsuario(request.getSession()).getIdPropietario());
    	List<ConocimientoHabilidadVO> conocsHabsVO = (List<ConocimientoHabilidadVO>)request.getSession().getAttribute("KNOWLEDGE_LIST");
    	conocHabVO.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
		conocsHabsVO.add(conocHabVO);
		request.getSession().setAttribute("KNOWLEDGE_LIST", conocsHabsVO);
		return mapping.findForward("CONOCIMIENTOS");	            	    	
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward deleteKnowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		int row = Utils.parseInt(request.getParameter("idKnow"));
		List<ConocimientoHabilidadVO> conocList = new ArrayList<ConocimientoHabilidadVO>();
		List<ConocimientoHabilidadVO> conocTmpList = new ArrayList<ConocimientoHabilidadVO>();
		List<ConocimientoHabilidadVO> conocDelList = new ArrayList<ConocimientoHabilidadVO>();
		try {
			if (null != request.getSession().getAttribute("KNOWLEDGE_LIST"))
				conocList = (List<ConocimientoHabilidadVO>)request.getSession().getAttribute("KNOWLEDGE_LIST");
			if (conocList.isEmpty() || -1 == row) return mapping.findForward("CONOCIMIENTOS");
			for (ConocimientoHabilidadVO vo : conocList) {
				if (row == vo.getRow()) {
					if (vo.getIdCandidatoConocimHabilidad() > 0) {
						if (null != request.getSession().getAttribute("KNOWLEDGE_2DELIST"))
							conocDelList = (List<ConocimientoHabilidadVO>)request.getSession().getAttribute("KNOWLEDGE_2DELIST");
						conocDelList.add(vo);
						request.getSession().setAttribute("KNOWLEDGE_2DELIST", conocDelList);
					}
				}else conocTmpList.add(vo);
			}
		} catch (Exception e) {logger.error(e); }
		request.getSession().setAttribute("KNOWLEDGE_LIST", conocTmpList);
		return mapping.findForward("CONOCIMIENTOS");
	}
	
	private boolean isExclude(int program, int[] exclude) {
		for (int i=0; i<exclude.length; i++) {
			if (program == exclude[i]) return true;
		}
		return false;
	}
	
	private List<String> getRequisites(int program) {
		List<String> requisites = new ArrayList<String>();
		if (program == MODALIDAD.PTAT.getIdModalidad()) {
			requisites.add("Ser de nacionalidad mexicana.");
			requisites.add("Ser jornalero(a) agrícola, campesino(a) o peón(a) de campo y tener experiencia laboral en dichas ocupaciones.");
			requisites.add("Conocer el proceso para la siembra y cosecha de los siguientes cultivos: cereales, vegetales, flores, frutas (particularmente la fresa) y el tábaco, así como tener experiencia laboral en invernaderos o viveros.");
			requisites.add("Preferentemente tener entre 22 y 45 años de edad.");
			requisites.add("Ser casado preferentemente o vivir en unión libre.");
			requisites.add("Contar con estudios mínimos de tercero de primaria y máximo primero de preparatoria.");
			requisites.add("Radicar en zona rural.");
		}else if (program == MODALIDAD.MML.getIdModalidad()) {
			requisites.add("Contar con la nacionalidad mexicana y residir en el territorio nacional.");
			requisites.add("Ser mayor de 18 años.");
			//requisites.add("Contar con una identificación oficial vigente (IFE, Pasaporte o Cartilla del Servicio Militar).");
			//requisites.add("Contar con la Cédula Unica de Registro de Población (CURP).");
			//requisites.add("Comprobante de último grado de estudios.");
			//requisites.add("Acreditar el dominio del idioma de acuerdo al requerimiento del empleador y de la ocupación ofertada.");
			//requisites.add("Tener experiencia laboral comprobable de acuerdo a la vacante ofertada.");
		}else if (program == MODALIDAD.MLISA.getIdModalidad()) {
			requisites.add("Ser jornalero agrícola con disposición para migrar temporalmente.");
			requisites.add("Estar desempleado o subempleado.");
			requisites.add("Tener 16 años o más.");
			requisites.add("Realizar el trámite \"Solicitud de Registro al programa de Apoyo al Empleo\".");
		}else if (program == MODALIDAD.MLISIS.getIdModalidad()) {
			requisites.add("Ser buscador de empleo, con disposición para cambiar de residencia de manera temporal.");
			requisites.add("Tener 18 años o más.");
		}else if (program == MODALIDAD.BECATE.getIdModalidad()) {
			requisites.add("Ser Buscador de empleo.");
			requisites.add("Edad 16 años o más.");
			requisites.add("Estar registrado en el Servicio Nacional de Empleo.");
			requisites.add("Cubrir el perfil establecido en el “Programa de Capacitación”.");
		}else if (program == MODALIDAD.FA.getIdModalidad()) {
			requisites.add("Ser Buscador de empleo.");
			requisites.add("Edad 18 años o más.");
			requisites.add("Estar registrado en el SNE.");
			requisites.add("Tener experiencia de por lo menos seis meses en las actividades inherentes al proceso y/o desarrollo del negocio propuesto, lo cual se corroborará en la entrevista que se realice al Solicitante de empleo canalizado.");
			//requisites.add("<a href=\"#\">Descargar requisitos</a>");
		}
		return requisites;
	}
	
	private List<String> getDocumentation(int program) {
		List<String> requisites = new ArrayList<String>();
		if (program == MODALIDAD.PTAT.getIdModalidad()) {
			requisites.add("Credencial de elector vigente del candidato y de la esposa o concubina.");
			requisites.add("Segunda identificación oficial: pre-cartilla o cartilla del Servicio Militar Nacional liberada y resellada en su caso; certificado de primaria o secundaria con fotografía, o pasaporte.");
			requisites.add("Acta de nacimiento del interesado y de sus dependientes económicos.");
			requisites.add("Acta de matrimonio o constancia de concubinato.");
			requisites.add("Comprobante de domicilio oficial a nombre del interesado, o en su defecto presentar credencial de elector vigente con la misma dirección de un comprobante de domicilio en zona rural.");
			requisites.add("Clave Única de Registro de Población (CURP).");
			requisites.add("Comprobante de últimos estudios con escolaridad mínima de tercero de primaria y como máximo, primero de preparatoria.");
			requisites.add("Una fotografía tamaño infantil reciente.");
			requisites.add("Carta de Naturalización (Si Aplica).");
		}else if (program == MODALIDAD.MLISA.getIdModalidad()) {
			requisites.add("Identificación oficial vigente.");
			requisites.add("Clave Única de Registro de Población (CURP).");
			requisites.add("En caso de menores de 18 años, identificación con fotografía y firma, expedida por autoridad municipal, estatal o federal.");
			requisites.add("Una fotografía tamaño infantil reciente.");
			requisites.add("Registro del Solicitante (descargue aquí <a href=\"/jsp/download/sne/SNE-01.pdf\" target=\"_blank\">formato SNE-01</a>), debidamente requisitado.");
			requisites.add("CLABE INTERBANCARIA de 18 posiciones. (Sólo en caso de que sean o hayan sido sujetos de apoyo económico de otros Programas Federales y que su cuenta bancaria esté activa).");
			requisites.add("Si el solicitante NO tiene cuenta bancaria, comprobante de domicilio reciente.");
			requisites.add("Carta de Naturalización (Si Aplica).");
		}else if (program == MODALIDAD.MLISIS.getIdModalidad()) {
			requisites.add("Identificación oficial vigente (cédula profesional o pasaporte).");
			requisites.add("Clave Única de Registro de Población (CURP).");
			requisites.add("Acta de Nacimiento.");
			requisites.add("Comprobante del último nivel de escolaridad.");
			requisites.add("Comprobante del conocimiento del idioma.");
			requisites.add("Comprobante de experiencia laboral.");
			requisites.add("CLABE INTERBANCARIA de 18 posiciones. (Sólo en caso de que sean o hayan sido sujetos de apoyo económico de otros Programas Federales y que su cuenta bancaria esté activa).");
			requisites.add("Una fotografía tamaño infantil reciente.");
			requisites.add("Registro del Solicitante (descargue aquí <a href=\"/jsp/download/sne/SNE-01.pdf\" target=\"_blank\">formato SNE-01</a>), debidamente requisitado.");
			requisites.add("Carta de Naturalización (Si Aplica).");
		}else if (program == MODALIDAD.MML.getIdModalidad()) {
			requisites.add("Identificación oficial vigente.");
			requisites.add("Clave Única de Registro de Población (CURP).");
			requisites.add("Acta de Nacimiento.");
			requisites.add("Comprobante de Estudios.");
			requisites.add("Comprobante de Idioma.");
			requisites.add("Comprobante de experiencia laboral.");
			requisites.add("Referencias.");
			requisites.add("Cuestionario de Migración (descargue aquí <a href=\"/jsp/download/sne/CM.pdf\" target=\"_blank\">cuestionario</a>).");
			requisites.add("Registro del Solicitante (descargue aquí <a href=\"/jsp/download/sne/LPA.pdf\" target=\"_blank\">formato LPA</a>), debidamente requisitado.");
			requisites.add("Una fotografía tamaño infantil reciente.");
			requisites.add("Carta de Naturalización (Si Aplica).");
		}else if (program == MODALIDAD.BECATE.getIdModalidad()) {
			requisites.add("Documento que acredite tu nivel máximo de escolaridad conforme a lo establecido en el “Programa de Capacitación”.");
			requisites.add("Identificación oficial vigente. (Credencial para votar vigente expedida por el Instituto Nacional Electoral o cédula profesional o pasaporte vigente o Cartilla del Servicio Militar Nacional. En caso de menores de 18 años y personas repatriadas, se aceptará una identificación con fotografía y firma, expedida por el gobierno federal, estatal o municipal. Tratándose de personas preliberadas, se aceptará también la carta de preliberación que emita el Centro de Readaptación Social correspondiente).");
			requisites.add("Clave Única de Registro de Población (CURP).");
			requisites.add("Comprobante de domicilio con una antigüedad no mayor a tres meses, que acredite el lugar en que habita el Buscador de empleo (por ejemplo: recibo de luz, teléfono, agua, predial o carta de avecindad con fotografía).");
			requisites.add("Registro del Solicitante (descargue aquí <a href=\"/jsp/download/sne/SNE-01.pdf\" target=\"_blank\">formato SNE-01</a>), debidamente requisitado.");
		}else if (program == MODALIDAD.FA.getIdModalidad()) {
			requisites.add("Identificación oficial vigente. (Credencial para votar vigente expedida por el Instituto Nacional Electoral o cédula profesional o pasaporte vigente o Cartilla del Servicio Militar Nacional. En caso de menores de 18 años y personas repatriadas, se aceptará una identificación con fotografía y firma, expedida por el gobierno federal, estatal o municipal. Tratándose de personas preliberadas, se aceptará también la carta de preliberación que emita el Centro de Readaptación Social correspondiente).");
			requisites.add("Clave Única de Registro de Población (CURP).");
			requisites.add("Comprobante de domicilio con una antigüedad no mayor a tres meses, que acredite el lugar en que habita el Buscador de empleo (por ejemplo: recibo de luz, teléfono, agua, predial o carta de avecindad con fotografía).");
			requisites.add("Propuesta de negocio.");
			requisites.add("“Carta Compromiso” firmada por todos los integrantes del negocio.");
		}
		return requisites;
	}
	
	private void loadEntities(HttpSession session, ProgramaForm programaForm) {
		CatalogoOpcionVO empty = new CatalogoOpcionVO();
		empty.setIdCatalogoOpcion(0);
		empty.setOpcion("");
		try {
			if (programaForm.getModalidad().equals(MODALIDAD.PTAT)) {
				List<CatalogoOpcionVO> height = getHeight();
				session.setAttribute("estatura", height);
				List<CatalogoOpcionVO> weight = getWeight();
				session.setAttribute("peso", weight);
				long[] filtroExp = {1, 8};
				List<CatalogoOpcionVO> experiencia = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_EXPERIENCIA, filtroExp, true);
				session.setAttribute("experiencia", experiencia);
				List<CatalogoOpcionVO> complexion = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_COMPLEXION, true);
				session.setAttribute("complexion", complexion);
				List<CatalogoOpcionVO> competencia = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_DOMINIO, true);
				session.setAttribute("competencia", competencia);
				long[] filtro = {5, 6};
				List<CatalogoOpcionVO> estadoscivil = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_ESTADO_CIVIL, filtro, true);
				estadoscivil.add(0,empty);
				session.setAttribute("estadoscivil", estadoscivil);
				List<CatalogoOpcionVO> relationship = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_PARENTESCO, true);
				relationship.add(0,empty);
				session.setAttribute("parentesco", relationship);
				List<CatalogoOpcionVO> licenceStatus = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_SITUACION_LICENCIA, true);
				licenceStatus.add(0,empty);
				session.setAttribute("situacionlicencia", licenceStatus);
				List<CatalogoOpcionVO> vehicles = getEngines(programaForm.getEngines(), Constantes.CATALOGO_TIPO_AUTOMOVIL);
				session.setAttribute("vehicles", vehicles);
				List<CatalogoOpcionVO> licenciaManejo = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_TIPO_LICENCIA, true);
				licenciaManejo.add(0,empty);
				session.setAttribute("licenciaManejo", licenciaManejo);
				List<FormatoPTATCultivosVO> products = programaForm.getProducts();
				List<CatalogoOpcionVO> vegetables = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_VERDURAS_LEGUMBRES_CAMPO);
				session.setAttribute("vegetales", vegetables);
				List<CatalogoOpcionVO> flowers = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_FLORES_CAMPO);
				session.setAttribute("flores", flowers);
				List<CatalogoOpcionVO> trees = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_ARBOLES);
				session.setAttribute("arboles", trees);
				List<CatalogoOpcionVO> fruits = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_FRUTAS);
				session.setAttribute("frutas", fruits);
				List<CatalogoOpcionVO> tobacco = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_TABACO);
				session.setAttribute("tabaco", tobacco);
				List<CatalogoOpcionVO> cereals = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_CEREALES);
				session.setAttribute("cereales", cereals);
				List<CatalogoOpcionVO> legumbresViv = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_VERDURAS_LEGUMBRES_VIVERO);
				session.setAttribute("legumbresViv", legumbresViv);
				List<CatalogoOpcionVO> floresViv = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_FLORES_VIVERO);
				session.setAttribute("floresViv", floresViv);
				List<CatalogoOpcionVO> arbolesViv = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_ARBOLES_VIVERO);
				session.setAttribute("arbolesViv", arbolesViv);
				List<CatalogoOpcionVO> cattle = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_GANADO);
				session.setAttribute("ganado", cattle);
				List<CatalogoOpcionVO> beefCattle = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_RASTRO);
				session.setAttribute("rastro", beefCattle);
				List<CatalogoOpcionVO> apiculture = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_APICULTURA);
				session.setAttribute("apicultura", apiculture);
				List<CatalogoOpcionVO> related = getAgriculturalOp(products, Constantes.CATALOGO_PTAT_OTROS_RUBROS);
				session.setAttribute("otros", related);
				List<FormatoPTATHclinicaVO> diseases =  programaForm.getEnfermedades();
				List<CatalogoOpcionVO> contagiosas = getDiseasesOp(diseases, Constantes.CATALOGO_ENFERMEDADES_CONTAGIOSAS);
				session.setAttribute("contagiosas", contagiosas);
				List<CatalogoOpcionVO> cronicas = getDiseasesOp(diseases, Constantes.CATALOGO_ENFERMEDADES_CRONICAS);
				session.setAttribute("cronicas", cronicas);
				List<CatalogoOpcionVO> fisicas = getDiseasesOp(diseases, Constantes.CATALOGO_LIMITACIONES_FISICAS);
				session.setAttribute("fisicas", fisicas);
				List<CatalogoOpcionVO> lesiones = getDiseasesOp(diseases, Constantes.CATALOGO_ENFERMEDADES_LESIONES);
				session.setAttribute("lesiones", lesiones);
			}else if (programaForm.getModalidad().equals(MODALIDAD.MLISIS) || programaForm.getModalidad().equals(MODALIDAD.BECATE)
					|| programaForm.getModalidad().equals(MODALIDAD.FA) || programaForm.getModalidad().equals(MODALIDAD.MLISA)) {
				long[] filtro = {5, 6};
				List<CatalogoOpcionVO> estadoscivil = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_ESTADO_CIVIL, filtro, true);
				estadoscivil.add(0,empty);
				session.setAttribute("estadoscivil", estadoscivil);
				List<CatalogoOpcionVO> tipoVialidad = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_VIALIDAD, true);
				session.setAttribute("tipoVialidad", tipoVialidad);
				List<CatalogoOpcionVO> tipoAsentamiento = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_ASENTAMIENTO, true);
				session.setAttribute("tipoAsentamiento", tipoAsentamiento);
				List<CatalogoOpcionVO> discapacidad = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_DISCAPACIDAD, true);
				session.setAttribute("discapacidad", discapacidad);
				List<CatalogoOpcionVO> asentamiento = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_ASENTAMIENTO, true);
				session.setAttribute("asentamiento", asentamiento);
				long[] filterExp = {8};
				List<CatalogoOpcionVO> opciExperiencia = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_EXPERIENCIA, filterExp, true);
				session.setAttribute("opciExperiencia", opciExperiencia);
				List<CatalogoOpcionVO> tiposempleo = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_EMPLEO, true);
				session.setAttribute("tiposempleo", tiposempleo);
				List<CatalogoOpcionVO> tiposcontrato = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_CONTRATO, true);
				session.setAttribute("tiposcontrato", tiposcontrato);
				List<CatalogoOpcionVO> jerarquias = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_JERARQUIA_PUESTO, true);
				session.setAttribute("jerarquias", jerarquias);
				List<CatalogoOpcionVO> personascargo = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_PERSONAS_CARGO, true);
				session.setAttribute("personascargo", personascargo);
				List<CatalogoOpcionVO> lenguaIndigena = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_LENGUAS_INDIGENAS, true);
				session.setAttribute("lenguaIndigena", lenguaIndigena);
				List<CatalogoOpcionVO> relationship = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_PARENTESCO, true);
				relationship.add(0,empty);
				session.setAttribute("parentesco", relationship);
				List<CatalogoOpcionVO> bancos = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_BANCOS, true);
				bancos.add(0,empty);
				session.setAttribute("bancos", bancos);
				session.setAttribute("mostrarEmpSI", MOSTRAR_EMPRESA.SI.getIdOpcion());
				session.setAttribute("mostrarEmpNO", MOSTRAR_EMPRESA.NO.getIdOpcion());
				session.setAttribute("recibeTEL", RECIBE_OFERTA.TELEFONO.getIdRecibeOferta());
				session.setAttribute("recibeCORREO", RECIBE_OFERTA.CORREO.getIdRecibeOferta());
				session.setAttribute("recibeAMBAS", RECIBE_OFERTA.AMBOS.getIdRecibeOferta());
				session.setAttribute("requiereCapacitacionSI", DECISION.SI.getIdOpcion());
				session.setAttribute("requiereCapacitacionNO", DECISION.NO.getIdOpcion());
				session.setAttribute("tiempoCapacitacionSI", DECISION.SI.getIdOpcion());
				session.setAttribute("tiempoCapacitacionNO", DECISION.NO.getIdOpcion());
				session.setAttribute("negocioPropioSI", DECISION.SI.getIdOpcion());
				session.setAttribute("negocioPropioNO", DECISION.NO.getIdOpcion());
				session.setAttribute("pretendeNegocioPropioSI", DECISION.SI.getIdOpcion());
				session.setAttribute("pretendeNegocioPropioNO", DECISION.NO.getIdOpcion());
				session.setAttribute("beneficiarioApoyoPropioSI", DECISION.SI.getIdOpcion());
				session.setAttribute("beneficiarioApoyoNO", DECISION.NO.getIdOpcion());
			}else if (programaForm.getModalidad().equals(MODALIDAD.MML)) {
				loadOptionsIdiomas(programaForm);
				long[] filterExp = {5};
				List<CatalogoOpcionVO> licenciaManejo = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_TIPO_LICENCIA, filterExp, true);
				licenciaManejo.add(0,empty);
				session.setAttribute("licenciaManejo", licenciaManejo);
				List<CatalogoOpcionVO> examen = getExamen();
				session.setAttribute("examen", examen);
				List<CatalogoOpcionVO> sector = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_SECTOR, true);
				session.setAttribute("sector", sector);
				List<CatalogoOpcionVO> opcGrados = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS, true);
				session.setAttribute("opcGrados", opcGrados);
				List<CatalogoOpcionVO> competencia = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_DOMINIO, true);
				session.setAttribute("competencia", competencia);
				List<CatalogoOpcionVO> jerarquias = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_JERARQUIA_PUESTO, true);
				session.setAttribute("jerarquias", jerarquias);
				List<CatalogoOpcionVO> personascargo = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Constantes.CATALOGO_OPCION_PERSONAS_CARGO, true);
				session.setAttribute("personascargo", personascargo);
				session.setAttribute("mostrarEmpSI", MOSTRAR_EMPRESA.SI.getIdOpcion());
				session.setAttribute("mostrarEmpNO", MOSTRAR_EMPRESA.NO.getIdOpcion());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	private List<CatalogoOpcionVO> getEngines(List<FormatoPTATMaquinariaVO> engines, long catalog) {
		long[] vehiclesFilter = {};
		List<CatalogoOpcionVO> enginesCat = new ArrayList<CatalogoOpcionVO>();
		try {
			enginesCat = CatalogoOpcionDelegateImpl.getInstance().getOptions(Constantes.CATALOGO_TIPO_AUTOMOVIL, vehiclesFilter, true, true);
			if (null != engines && !engines.isEmpty()) {
				for (FormatoPTATMaquinariaVO engine : engines) {
					for (CatalogoOpcionVO opcion : enginesCat) {
						if (engine.getIdMaquinaria() == opcion.getIdCatalogoOpcion()) opcion.setIdCorto("selected");
					}
				}
			}
		}catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return enginesCat;
	}
	
	private List<CatalogoOpcionVO> getAgriculturalOp(List<FormatoPTATCultivosVO> products, long catalog) {
		List<CatalogoOpcionVO> agriculturalCat = new ArrayList<CatalogoOpcionVO>();
		try {
			agriculturalCat = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(catalog,true);
			if (null != products && !products.isEmpty()) {
				for (FormatoPTATCultivosVO product : products) {
					if (product.getIdtipoCultivo() == catalog) {
						for (CatalogoOpcionVO opcion : agriculturalCat) {
							if (product.getIdCultivo() == opcion.getIdCatalogoOpcion()) opcion.setIdCorto("selected");
						}
					}
				}
			}
		}catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return agriculturalCat;
	}
	
	private List<CatalogoOpcionVO> getDiseasesOp(List<FormatoPTATHclinicaVO> diseases, long catalog) {
		List<CatalogoOpcionVO> diseaseCat = new ArrayList<CatalogoOpcionVO>();
		try {
			diseaseCat = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(catalog,true);
			if (null != diseases && !diseases.isEmpty()) {
				for (FormatoPTATHclinicaVO disease : diseases) {
					if (disease.getIdCatalogoPatologias() == catalog) {
						for (CatalogoOpcionVO opcion : diseaseCat) {
							if (disease.getIdPatogia() == opcion.getIdCatalogoOpcion()) opcion.setIdCorto("selected");
						}
					}
				}
			}
		}catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return diseaseCat;
	}
	
	private List<MODALIDAD> getPath(int program, int[] exclude) {
		List<MODALIDAD> path = new ArrayList<MODALIDAD>();
		for (MODALIDAD modalidad : MODALIDAD.values()) {
				if (modalidad.getIdModalidad() != program && !isExclude(modalidad.getIdModalidad(), exclude))
					path.add(modalidad);
		}
		return path;
	}
	
	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}
	
	private List<CatalogoOpcionVO> getHeight() {
		List<CatalogoOpcionVO> height = new ArrayList<CatalogoOpcionVO>();
		CatalogoOpcionVO empty = new CatalogoOpcionVO();
		empty.setIdCatalogoOpcion(0);
		empty.setOpcion("");
		height.add(empty);
		CatalogoOpcionVO small = new CatalogoOpcionVO();
		small.setIdCatalogoOpcion(1);
		small.setOpcion("Entre 1.5m y 1.68m");
		height.add(small);
		CatalogoOpcionVO middle = new CatalogoOpcionVO();
		middle.setIdCatalogoOpcion(2);
		middle.setOpcion("Entre 1.69m y 1.78m");
		height.add(middle);
		CatalogoOpcionVO big = new CatalogoOpcionVO();
		big.setIdCatalogoOpcion(3);
		big.setOpcion("Mayor 1.78m");
		height.add(big);
		return height;
	}
	
	private List<CatalogoOpcionVO> getWeight() {
		List<CatalogoOpcionVO> weight = new ArrayList<CatalogoOpcionVO>();
		CatalogoOpcionVO empty = new CatalogoOpcionVO();
		empty.setIdCatalogoOpcion(0);
		empty.setOpcion("");
		weight.add(empty);
		CatalogoOpcionVO small = new CatalogoOpcionVO();
		small.setIdCatalogoOpcion(1);
		small.setOpcion("Entre 45Kg y 70Kg");
		weight.add(small);
		CatalogoOpcionVO middle = new CatalogoOpcionVO();
		middle.setIdCatalogoOpcion(2);
		middle.setOpcion("Entre 71Kg y 89Kg");
		weight.add(middle);
		CatalogoOpcionVO big = new CatalogoOpcionVO();
		big.setIdCatalogoOpcion(3);
		big.setOpcion("Mayor 89Kg");
		weight.add(big);
		return weight;
	}
	
	private List<CatalogoOpcionVO> getExamen() {
		List<CatalogoOpcionVO> examen = new ArrayList<CatalogoOpcionVO>();
		CatalogoOpcionVO empty = new CatalogoOpcionVO();
		empty.setIdCatalogoOpcion(0);
		empty.setOpcion("");
		examen.add(empty);
		CatalogoOpcionVO knowledge = new CatalogoOpcionVO();
		knowledge.setIdCatalogoOpcion(1);
		knowledge.setOpcion("Conocimientos");
		examen.add(knowledge);
		CatalogoOpcionVO skills = new CatalogoOpcionVO();
		skills.setIdCatalogoOpcion(2);
		skills.setOpcion("Habilidades");
		examen.add(skills);
		CatalogoOpcionVO experiences = new CatalogoOpcionVO();
		experiences.setIdCatalogoOpcion(3);
		experiences.setOpcion("Experiencias");
		examen.add(experiences);
		return examen;
	}
	
	private FormatoLPAVO getMML(ProgramaForm programaForm, HttpServletRequest request) {
		FormatoLPAVO mml = programaForm.getMml();
		mml.setObjetivos(request.getParameter("objetivos"));
		mml.setObservaciones(request.getParameter("observaciones"));
		mml.setEntretenimiento(request.getParameter("entretenimiento"));
		return mml;
	}
	
	private ModalidadCandidatoVO getModalidad(ProgramaForm programaForm) {
		ModalidadCandidatoVO mc = null;
		if (null != programaForm.getModalidadCandidato()) mc = programaForm.getModalidadCandidato();
		else {
			mc = new ModalidadCandidatoVO();
			mc.setIdCandidato(programaForm.getPerfil().getIdCandidato());
			mc.setEstatus((short)Constantes.ESTATUS.ACTIVO.getIdOpcion());
			mc.setIdModalidad((long)programaForm.getModalidad().getIdModalidad());
			mc.setIdSubprograma((long)programaForm.getModalidad().getIdSubprograma());
		}
		return mc;
	}
	
	private void getValues(List<FormatoPTATCultivosVO> products, String[] values, long idCandidato, long idRubroAgropecuario) {
		if (null != values && idCandidato > 0) {
			for (int i=0; i<values.length; i++) {
				FormatoPTATCultivosVO b = new FormatoPTATCultivosVO();
				b.setIdCandidato(idCandidato);
				b.setIdCultivo(Utils.parseInt(values[i]));
				b.setIdtipoCultivo((int)idRubroAgropecuario);
				products.add(b);
			}
		}
	}
	
	private void getValues(List<FormatoPTATHclinicaVO> histories, String[] values, long idCandidato, int idCatalogoPatologias) {
		if (null != values && idCandidato > 0) {
			for (int i=0; i<values.length; i++) {
				FormatoPTATHclinicaVO vo = new FormatoPTATHclinicaVO();
				vo.setIdCandidato(idCandidato);
				vo.setIdCatalogoPatologias(idCatalogoPatologias);
				vo.setIdPatogia(Utils.parseInt(values[i]));
				histories.add(vo);
			}
		}
	}
	
	private List<FormatoPTATMaquinariaVO> getEngines(HttpServletRequest request, long idCandidato) {
		List<FormatoPTATMaquinariaVO> engines = new ArrayList<FormatoPTATMaquinariaVO>();
		String [] values = request.getParameterValues("manejoMaquinaria");
		for (int i=0; i<values.length; i++) {
			FormatoPTATMaquinariaVO vo = new FormatoPTATMaquinariaVO();
			vo.setIdCandidato(idCandidato);
			vo.setIdMaquinaria(Utils.parseInt(values[i]));
			engines.add(vo);
		}
		return engines;
	}
	
	private List<FormatoPTATCultivosVO> getProducts(HttpServletRequest request, long idCandidato) {
		List<FormatoPTATCultivosVO> products = new ArrayList<FormatoPTATCultivosVO>();
		for (Constantes.RUBRO_AGROPECUARIO rubro : Constantes.RUBRO_AGROPECUARIO.values()) {
			getValues(products, request.getParameterValues(rubro.getParameter()), idCandidato, rubro.getIdOpcion());
		}
		return products;
	}
	
	private List<FormatoPTATHclinicaVO> getClinicalHistory(HttpServletRequest request, long idCandidato) {
		List<FormatoPTATHclinicaVO> histories = new ArrayList<FormatoPTATHclinicaVO>();
		for (Constantes.HISTORIA_CLINICA hist : Constantes.HISTORIA_CLINICA.values()) {
			getValues(histories, request.getParameterValues(hist.getParameter()), idCandidato, hist.getIdOpcion());
		}
		return histories;
	}
	
	private boolean isCandidateInProgram(HttpServletRequest request, ProgramaForm programaForm) {
		boolean isCandidateInProgram = false;
		ModalidadCandidatoVO mc = null;
		UsuarioWebVO user = getUsuario(request.getSession());
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		try {
			if (null != user) {
				mc = services.getModalidadCandidato(user.getIdPropietario(), programaForm.getModalidad().getIdModalidad(), programaForm.getModalidad().getIdSubprograma());
				if (null != mc && mc.getIdModalidadCandidato() > 0)
					isCandidateInProgram = true;
				else {
					mc = new ModalidadCandidatoVO();
					mc.setEstatus((short)Constantes.ESTATUS.CANDIDATO_SOLICITA_INSCRIPCION.getIdOpcion());
					mc.setFechaRegistro(new Date());
					mc.setIdCandidato(user.getIdPropietario());
					mc.setIdModalidad((long)programaForm.getModalidad().getIdModalidad());
					mc.setIdSubprograma((long)programaForm.getModalidad().getIdSubprograma());
					mc.setIdUsuario(user.getIdUsuario());
				}
				programaForm.setModalidadCandidato(mc);
			}
		} catch (Exception e) {
		}
		return isCandidateInProgram;
	}
	
	private void setModality(HttpServletRequest request, ProgramaForm programaForm, int[] exclude) {
		try {
			if (programaForm.getModalidad().equals(MODALIDAD.PTAT)) {
				programaForm.setPath(getPath(MODALIDAD.PTAT.getIdModalidad(), exclude));
				programaForm.setDescPrograma("Es una alternativa de ocupación segura, ordenada y legal para trabajadoras y trabajadores agrícolas mexicanos que se encuentren en período de desempleo, para laborar temporalmente en las granjas agrícolas canadienses en actividades de cultivo y cosecha de verduras y frutas, tabaco, árboles y pasto, así como en actividades de apicultura y horticultura.");
				programaForm.setDescRequisitos("Las personas interesadas deben cumplir los siguientes requisitos:");
				programaForm.setRequisitos(getRequisites(MODALIDAD.PTAT.getIdModalidad()));
				programaForm.setDocumentos(getDocumentation(MODALIDAD.PTAT.getIdModalidad()));
				programaForm.setDescDocumentos("Documentación que deberá presentar en la OSNE (Deberá presentarse en original y copia para cotejo):");
			}else if (programaForm.getModalidad().equals(MODALIDAD.MML)) {
				programaForm.setPath(getPath(MODALIDAD.MML.getIdModalidad(), exclude));
				programaForm.setDescPrograma("Con base a las necesidades de personal de los empleadores canadienses, las Oficinas del SNE reclutan y seleccionan a personas desempleadas y subempleadas que cuenten con la experiencia laboral mínima requerida por el empleador en las ocupaciones ofertadas. Es importante destacar que el SNE es la única instancia autorizada para el reclutamiento, selección y contratación de trabajadores, garantizando de ese modo que la atención que se les brinda sea personal y gratuita, sin la participación de intermediarios.");
				programaForm.setDescRequisitos("Las personas interesadas deben cumplir los siguientes requisitos:");
				programaForm.setRequisitos(getRequisites(MODALIDAD.MML.getIdModalidad()));
				programaForm.setDocumentos(getDocumentation(MODALIDAD.MML.getIdModalidad()));
				programaForm.setDescDocumentos("Documentación que deberá presentar en la OSNE (Deberá presentarse en original y copia para cotejo):");
			}else if (programaForm.getModalidad().equals(MODALIDAD.MLISA)) {
				programaForm.setPath(getPath(MODALIDAD.MLISA.getIdModalidad(), exclude));
				programaForm.setDescPrograma("Dirigido a jornaleros agrícolas de 16 años y más, que se encuenran en busca de empleo y que en sus localidades de origen no tienen la oportunidad de colocarse en una actividad productiva remunerada por lo que requieren trasladarse y residir en otra entidad y/o localidad en la que se demanda fuerza de trabajo temporal en el sector agrícola.");
				programaForm.setDescRequisitos("Las personas interesadas deben cumplir los siguientes requisitos:");
				programaForm.setRequisitos(getRequisites(MODALIDAD.MLISA.getIdModalidad()));
				programaForm.setDocumentos(getDocumentation(MODALIDAD.MLISA.getIdModalidad()));
				programaForm.setDescDocumentos("Documentación que deberá presentar en la OSNE (Deberá presentarse en original y copia para cotejo):");
			}else if (programaForm.getModalidad().equals(MODALIDAD.MLISIS)) {
				programaForm.setPath(getPath(MODALIDAD.MLISIS.getIdModalidad(), exclude));
				programaForm.setDescPrograma("El subprograma de Movilidad Laboral es una política activa de empleo que otorga apoyos económicos y propicia condiciones favorables para la movilidad laboral de la población objetivo que en sus lugares de origen no logran colocarse en un empleo, en atención a los requerimientos del mercado laboral en los distintos sectores económicos.");
				programaForm.setDescRequisitos("Las personas interesadas deben cumplir los siguientes requisitos:");
				programaForm.setDescDocumentos("Documentación que deberá presentar en la OSNE (Deberá presentarse en original y copia para cotejo):");
				programaForm.setRequisitos(getRequisites(MODALIDAD.MLISIS.getIdModalidad()));
				programaForm.setDocumentos(getDocumentation(MODALIDAD.MLISIS.getIdModalidad()));
			}else if (programaForm.getModalidad().equals(MODALIDAD.BECATE)) {
				programaForm.setModalidad(MODALIDAD.BECATE);
				programaForm.setPath(getPath(MODALIDAD.BECATE.getIdModalidad(), exclude));
				programaForm.setDescPrograma("Apoya a Buscadores de empleo que requieren capacitarse para facilitar su colocación o el desarrollo de una actividad productiva por cuenta propia. La capacitación y apoyo a los Buscadores de empleo se implementa a través de la impartición de cursos.");
				programaForm.setDescRequisitos("Las personas interesadas deben cumplir los siguientes requisitos:");
				programaForm.setRequisitos(getRequisites(MODALIDAD.BECATE.getIdModalidad()));
				programaForm.setDocumentos(getDocumentation(MODALIDAD.BECATE.getIdModalidad()));
				programaForm.setDescDocumentos("Documentación que deberá presentar en la OSNE (Deberá presentarse en original y copia para cotejo):");
			}else if (programaForm.getModalidad().equals(MODALIDAD.FA)) {
				programaForm.setModalidad(MODALIDAD.FA);
				programaForm.setPath(getPath(MODALIDAD.FA.getIdModalidad(), exclude));
				programaForm.setDescRequisitos("Las personas interesadas deben cumplir los siguientes requisitos:");
				programaForm.setDescPrograma("Apoya mediante la entrega de apoyos en especie que consisten en mobiliario, maquinaria, equipo y/o herramienta a los Solicitantes de empleo seleccionados que cuentan con las competencias y experiencia laboral, para desarrollar un negocio.");
				programaForm.setRequisitos(getRequisites(MODALIDAD.FA.getIdModalidad()));
				programaForm.setDescDocumentos("Documentación que deberá presentar en la OSNE (Deberá presentarse en original y copia para cotejo):");
				programaForm.setDocumentos(getDocumentation(MODALIDAD.FA.getIdModalidad()));
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public TelefonoVO getPhoneReference(ProgramaForm programaForm, HttpServletRequest request) {
		String clave = request.getParameter("clave");
		String acceso = request.getParameter("acceso");
		String telefono = request.getParameter("telefono");
		Integer idTipoTelefono = Utils.parseInt(request.getParameter("tipoTelefono"));
		String extension = request.getParameter("extension");
		TelefonoVO phone = new TelefonoVO();
		phone.setAcceso(acceso);
		phone.setClave(clave);
		phone.setExtension(extension);
		phone.setIdTipoTelefono((int) idTipoTelefono);
		phone.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		phone.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.REFERENCIA_LABORAL.getIdTipoPropietario());
		phone.setTelefono(telefono);
		if (programaForm.getReferenciaPrincipal().getTelefono().getIdTelefono() > 0) {
			phone.setFechaAlta(programaForm.getReferenciaPrincipal().getTelefono().getFechaAlta());
			phone.setIdTelefono(programaForm.getReferenciaPrincipal().getTelefono().getIdTelefono());
			phone.setIdPropietario(programaForm.getReferenciaPrincipal().getTelefono().getIdPropietario());
		}
		return phone;
	}
	
	private int getIdRecibeOferta(HttpServletRequest request) {
		int recibeOferta = 0;
		if (null == request.getParameterValues("recibeOferta")) return -1; 
		if (0 == request.getParameterValues("recibeOferta").length)
			recibeOferta = Constantes.RECIBE_OFERTA.NO.getIdRecibeOferta();
		else if (2 == request.getParameterValues("recibeOferta").length)
			recibeOferta = Constantes.RECIBE_OFERTA.AMBOS.getIdRecibeOferta();
		else { 
			for (int i=0; i<request.getParameterValues("recibeOferta").length; i++) {
				if (Constantes.RECIBE_OFERTA.TELEFONO.getIdRecibeOferta() == Utils.parseInt(request.getParameterValues("recibeOferta")[i]))
					recibeOferta = Constantes.RECIBE_OFERTA.TELEFONO.getIdRecibeOferta();
				recibeOferta = Constantes.RECIBE_OFERTA.CORREO.getIdRecibeOferta();
			}
		}
		return recibeOferta;
	}
	
	private String getDiscapacidades(String[] values) {
		DiscapacidadBO disc = new DiscapacidadBO(null);
		for (int i=0; i<values.length; i++) {
			disc.setIdDiscapacidad(Utils.parseInt(values[i]));
		}
		return disc.getDiscapacidades();
	}
	
	private ReferenciaLaboralVO getReferenceLastJob(ProgramaForm programaForm, HttpServletRequest request) {
		if (null != request.getParameter("empresa") && !request.getParameter("empresa").trim().isEmpty()) {
			programaForm.getReferenciaPrincipal().getDomicilio().setCalle(request.getParameter("calleEmpresa"));
			programaForm.getReferenciaPrincipal().getDomicilio().setCodigoPostal(request.getParameter("codigoPostalEmpresa"));
			programaForm.getReferenciaPrincipal().getDomicilio().setDomicilioRef(request.getParameter("referenciaEmpresa"));
			//programaForm.getReferenciaPrincipal().getDomicilio().setEntreCalle(entreCalle);
			programaForm.getReferenciaPrincipal().getDomicilio().setIdColonia(Utils.parseLong(request.getParameter("idColoniaEmpresa")));
			programaForm.getReferenciaPrincipal().getDomicilio().setIdEntidad(Utils.parseLong(request.getParameter("idEntidadEmpresa")));
			programaForm.getReferenciaPrincipal().getDomicilio().setIdLocalidad(Utils.parseLong(request.getParameter("idLocalidadEmpresa")));
			programaForm.getReferenciaPrincipal().getDomicilio().setIdMunicipio(Utils.parseLong(request.getParameter("idMunicipioEmpresa")));
			programaForm.getReferenciaPrincipal().getDomicilio().setIdPropietario((long)TIPO_PROPIETARIO.REFERENCIA_LABORAL.getIdTipoPropietario());
			programaForm.getReferenciaPrincipal().getDomicilio().setNumeroExterior(request.getParameter("numExtEmpresa"));
			programaForm.getReferenciaPrincipal().getDomicilio().setNumeroInterior(request.getParameter("numIntEmpresa"));
			//programaForm.getReferenciaPrincipal().getDomicilio().setyCalle(yCalle);
			programaForm.getReferenciaPrincipal().getTrabajoActual().setEmpresa(request.getParameter("empresa"));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setConfidencialidadEmpresa(Utils.parseLong(request.getParameter("confidencialidadEmpresa")));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setPuesto(request.getParameter("puesto"));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setIdJerarquia(Utils.parseLong(request.getParameter("idJerarquia")));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setPersonasCargo(Utils.parseLong(request.getParameter("personasCargo")));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setSalarioMensual(Utils.parseDouble(request.getParameter("salarioMensual")));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setLaboresInicial(Utils.convert(request.getParameter("laboresInicial")));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setLaboresFinal(Utils.convert(request.getParameter("laboresFinal")));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setFuncion(request.getParameter("funcion"));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setPrincipal((long)Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
			if (0 == programaForm.getReferenciaPrincipal().getTrabajoActual().getIdHistorialLaboral()) {
				programaForm.getReferenciaPrincipal().getTrabajoActual().setTrabajoActual((long)TRABAJA_ACTUALMENTE.SI.getIdOpcion());
			}
			programaForm.getReferenciaPrincipal().getTrabajoActual().setHerramientas(request.getParameter("herramientas"));
		}
		if (null != request.getParameter("nombreReferencia")) {
			programaForm.getReferenciaPrincipal().getTrabajoActual().setRefNombre(request.getParameter("nombreReferencia"));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setRefApellido1(request.getParameter("primerApellidoReferencia"));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setRefApellido2(request.getParameter("segundoApellidoReferencia"));
			programaForm.getReferenciaPrincipal().getTrabajoActual().setRefPuesto(request.getParameter("puestoReferencia"));
		}
		return programaForm.getReferenciaPrincipal();
	}
	
	private ConocimientoHabilidadVO getConocimientoPrincipal(ProgramaForm programaForm, HttpServletRequest request) {
		if ((null == programaForm.getPerfil().getConocimientoPrincipal() || programaForm.getPerfil().getConocimientoPrincipal().getIdCandidatoConocimHabilidad() == 0) && null != request.getParameter("conocimiento")) {
			programaForm.getPerfil().setConocimientoPrincipal(new ConocimientoHabilidadVO());
			programaForm.getPerfil().getConocimientoPrincipal().setIdCandidato(getUsuario(request.getSession()).getIdPropietario());
			programaForm.getPerfil().getConocimientoPrincipal().setIdTipoConocimHabilidad(Constantes.CONOC_HAB.CONOCIMIENTO.getIdOpcion());
			programaForm.getPerfil().getConocimientoPrincipal().setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		}
		programaForm.getPerfil().getConocimientoPrincipal().setConocimientoHabilidad(request.getParameter("conocimiento"));
		programaForm.getPerfil().getConocimientoPrincipal().setDescripcion(request.getParameter("descripcionCon"));
		programaForm.getPerfil().getConocimientoPrincipal().setIdExperiencia(Utils.parseLong(request.getParameter("idExperienciaCon")));
		return programaForm.getPerfil().getConocimientoPrincipal();
	}
	
	DomicilioVO getDomicilioReference(HttpServletRequest request) {
		DomicilioVO domicilio = new DomicilioVO();
		domicilio.setCalle(request.getParameter("calleEmpresa"));
		domicilio.setCodigoPostal(request.getParameter("codigoPostalEmpresa"));
		domicilio.setDomicilioRef(request.getParameter("referenciaEmpresa"));
		//domicilio.setEntreCalle(entreCalle);
		domicilio.setIdColonia(Utils.parseLong(request.getParameter("idColoniaEmpresa")));
		domicilio.setIdEntidad(Utils.parseLong(request.getParameter("idEntidadEmpresa")));
		domicilio.setIdLocalidad(Utils.parseLong(request.getParameter("idLocalidadEmpresa")));
		domicilio.setIdMunicipio(Utils.parseLong(request.getParameter("idMunicipioEmpresa")));
		domicilio.setIdPropietario((long)TIPO_PROPIETARIO.REFERENCIA_LABORAL.getIdTipoPropietario());
		domicilio.setNumeroExterior(request.getParameter("numExtEmpresa"));
		domicilio.setNumeroInterior(request.getParameter("numIntEmpresa"));
		//domicilio.setyCalle(yCalle);
		return domicilio;
	}
	
	private void loadOptionsIdiomas(ProgramaForm programaForm){
		try {
			programaForm.setIdiomasDependientes(getDependentLangs());
			programaForm.setCatalogoDominios(CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_DOMINIO, true));
			programaForm.setCatalogoIdiomas(CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma, true));							
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		}			
	}
	
	private String[] getDependentLangs() {		
		int sumarParaLibrarIndiceCero = 1;
		String[] idiomasDependientes = null;
		try {
			List<CatalogoOpcionVO> idiomas = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma, true);			
			idiomasDependientes = new String[idiomas.size()+sumarParaLibrarIndiceCero];
			idiomasDependientes[0] = "0";
			for (CatalogoOpcionVO opcion : idiomas) {
				idiomasDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());	
			}
		}catch(Exception ex){
			logger.error(ex);
			ex.printStackTrace();
		}
		return idiomasDependientes;
	}
	
	private String[] obtenerCatalogosAsociadosDeIdiomas(HttpServletRequest request) {
		int sumarParaLibrarIndiceCero = 1;
		String[] idiomasDependientes = null;
		try{
			List<CatalogoOpcionVO> idiomas = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma, true);			
			idiomasDependientes = new String[idiomas.size()+sumarParaLibrarIndiceCero];
			idiomasDependientes[0] = "0";

			for (CatalogoOpcionVO opcion : idiomas) {
				idiomasDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());	
			}
			setDepCat(request, filtro_idioma, sumarParaLibrarIndiceCero);
		}catch(Exception ex){
			logger.error(ex);
			ex.printStackTrace();
		}
		return idiomasDependientes;
	}
	
	private void setDepCat(HttpServletRequest request, long[] filtro_idioma, int sumarParaLibrarIndiceCero) throws ServiceLocatorException {
		List<CatalogoOpcionVO> opcGrados= new ArrayList<CatalogoOpcionVO>();
		List<CatalogoOpcionVO> opcIdiomas= new ArrayList<CatalogoOpcionVO>();
		if (request.getSession().getAttribute("depIdioma") == null) {
			opcIdiomas = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma);
			String[] depCat = new String[opcIdiomas.size() + sumarParaLibrarIndiceCero];
			depCat[0] = "0";
			for (CatalogoOpcionVO opcion : opcIdiomas) {
				depCat[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());				
			}
			request.getSession().setAttribute("depIdioma", depCat);
		}
		if (request.getSession().getAttribute("depGrado") == null) {
			opcGrados = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);				
			String[] depCat = new String[opcGrados.size() + sumarParaLibrarIndiceCero];
			depCat[0] = "0";
			for (CatalogoOpcionVO opcion : opcGrados ) {
				depCat[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
			}
			request.getSession().setAttribute("depGrado", depCat);
		}
	}
	
	private void initLangList(HttpServletRequest request, List<IdiomaVO> idiomas, ProgramaForm programaForm) throws ServiceLocatorException {
		int contadorIdiomasAdicionales=1;
		int sumarParaLibrarIndiceCero = 1;
		List<IdiomaVO> idiomasAdicionales = new ArrayList<IdiomaVO>();
		List<CatalogoOpcionVO> opcGrados= new ArrayList<CatalogoOpcionVO>();
		List<CatalogoOpcionVO> opcIdiomas= new ArrayList<CatalogoOpcionVO>();
		for (IdiomaVO vo: idiomas) {
			if (vo.getPrincipal()==MULTIREGISTRO.ADICIONAL.getIdOpcion()) {
				idiomasAdicionales.add(vo);
				if(contadorIdiomasAdicionales==1)
					programaForm.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(vo.getIdIdioma(), programaForm.getIdiomasDependientes()));
				if(contadorIdiomasAdicionales==2)
					programaForm.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(vo.getIdIdioma(), programaForm.getIdiomasDependientes()));
				if(contadorIdiomasAdicionales==3)
					programaForm.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(vo.getIdIdioma(), programaForm.getIdiomasDependientes()));
				contadorIdiomasAdicionales++;
			}
		}	
		programaForm.setIdiomasAdicionales(idiomasAdicionales);
		programaForm.setTotalIdiomasAdicionales(idiomasAdicionales.size());
		if (request.getSession().getAttribute("depGrado") == null) {
			opcGrados = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);				
			//Carga en sesiòn los catalogos asociados a cada opcion
			String[] depCat = new String[opcGrados.size() + sumarParaLibrarIndiceCero];
			depCat[0] = "0";
			for (CatalogoOpcionVO opcion : opcGrados ) {
				depCat[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
			}
			request.getSession().setAttribute("depGrado", depCat);
		}
		if (request.getSession().getAttribute("depIdioma") == null) {
			opcIdiomas = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS, filtro_idioma);
			// Carga en sesión los catalogos asociados a cada opcion
			String[] depCat = new String[opcIdiomas.size() + sumarParaLibrarIndiceCero];
			depCat[0] = "0";
			for (CatalogoOpcionVO opcion : opcIdiomas) {
				depCat[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());					
			}
			request.getSession().setAttribute("depIdioma", depCat);
		}
	}
	
	private List<IdiomaVO> getLangList(long idCandidato) {
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		List<IdiomaVO> langsList = null;
		try {
			langsList = services.buscarIdiomas(idCandidato);		
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return langsList;
	}
	
	private List<CatalogoOpcionVO> setIdiomasMultiRegistro(long idIdioma, String[] idiomasDep) {
		List<CatalogoOpcionVO> lista = new ArrayList<CatalogoOpcionVO>();
		try {
			lista = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(Long.valueOf(idiomasDep[(int) idIdioma]));
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	private List<BeneficiarioBO> getBussinesObject(List<BeneficiarioVO> benefList) {
		List<BeneficiarioBO> bussines = new ArrayList<BeneficiarioBO>();
		if (null == benefList) return bussines;
		for (BeneficiarioVO vo : benefList) {
			BeneficiarioBO bo = new BeneficiarioBO();
			try {
				BeanUtils.copyProperties(bo, vo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			bussines.add(bo);
		}
		return bussines;
	}
	
	private List<TelefonoDTO> getAddPhoneList(List<TelefonoVO> phoneList) {
		List<TelefonoDTO> dtos= new ArrayList<TelefonoDTO>();
		for (TelefonoVO ph : phoneList) {
			TelefonoDTO dto = new TelefonoDTO();
			dto.setAcceso(ph.getAcceso());
			dto.setClave(ph.getClave());
			dto.setExtension(ph.getExtension());
			dto.setFechaAlta(ph.getFechaAlta());
			dto.setIdPropietario(ph.getIdPropietario());
			dto.setIdTelefono(ph.getIdTelefono());
			dto.setIdTipoPropietario(ph.getIdTipoPropietario().longValue());
			dto.setIdTipoTelefono(ph.getIdTipoTelefono());
			dto.setPrincipal(ph.getPrincipal());
			dto.setTelefono(ph.getTelefono());
			dtos.add(dto);
		}
		return dtos;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward deletePhone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idPhone = request.getParameter("idPhone");
		List<TelefonoDTO> phoneTmpList = new ArrayList<TelefonoDTO>();
		List<TelefonoDTO> phoneDelList = null != request.getSession().getAttribute(LST_TELEFONOS_2DELETE) ? (List<TelefonoDTO>)request.getSession().getAttribute("LST_TELEFONOS_2DELETE") : new ArrayList<TelefonoDTO>();
		List<TelefonoDTO> phoneAddList = (List<TelefonoDTO>)request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES);
		if (idPhone!=null && phoneAddList!=null && !phoneAddList.isEmpty()) { 
			for (TelefonoDTO dto : phoneAddList) {
				if (dto.getRow() != Utils.parseLong(idPhone)) phoneTmpList.add(dto);
				else if (dto.getIdTelefono() > 0) phoneDelList.add(dto);
			}
		}
		request.getSession().setAttribute(LST_TELEFONOS_2DELETE, phoneDelList);
		request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, phoneTmpList);
        return mapping.findForward("TELEFONOS");
	}
	
	@SuppressWarnings("unchecked")
	private void deletePhoneList(HttpServletRequest request) {
		List<TelefonoDTO> phoneDelList = (List<TelefonoDTO>)request.getSession().getAttribute(LST_TELEFONOS_2DELETE);
		if (null != phoneDelList && !phoneDelList.isEmpty()) {
			for (TelefonoDTO dto : phoneDelList) {
				TelefonoVO vo = new TelefonoVO();
				vo.setIdTelefono(dto.getIdTelefono());
				RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
				try {
					service.eliminarTelefono(vo);
				} catch (Exception e) { e.printStackTrace(); }
			}
		}
		request.getSession().removeAttribute(LST_TELEFONOS_2DELETE);
	}
	
	@SuppressWarnings("unchecked")
	private void deleteStudieList(HttpServletRequest request) {
		List<CandidatoOtroEstudioVO> studies2Del = (List<CandidatoOtroEstudioVO>)request.getSession().getAttribute("OTHER_STUDIES_2DEL");
		if (null == studies2Del) return;
		for (CandidatoOtroEstudioVO vo : studies2Del) {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			try {
				services.eliminarOtroEstudio(vo.getIdCandidatoOtroEstudio());
			} catch (BusinessException | ServiceLocatorException e) {
				logger.error(e);
			}
		}
		request.getSession().removeAttribute("OTHER_STUDIES_2DEL");
	}
	
	@SuppressWarnings("unchecked")
	private List<BeneficiarioVO> getContacts(HttpServletRequest request) {
		List<BeneficiarioBO> bussines = null;
		List<BeneficiarioVO> contacts = new ArrayList<BeneficiarioVO>();
		if (null != request.getSession().getAttribute("LST_CONTACTS")) {
			bussines = (List<BeneficiarioBO>)request.getSession().getAttribute("LST_CONTACTS");
			for (BeneficiarioBO bo : bussines) {
				if (bo.getIndex() > 0) {
					bo.setNombre(request.getParameter("nombreContacto_"+bo.getIndex()));
					bo.setPrimerApellido(request.getParameter("primerApellidoCto_"+bo.getIndex()));
					bo.setSegundoApellido(request.getParameter("segundoApellidoCto_"+bo.getIndex()));
					bo.setIdParentesco(Utils.parseLong(request.getParameter("idParentesco_"+bo.getIndex())));
					bo.getTelefono().setClave(request.getParameter("clave_"+bo.getIndex()));
					bo.getTelefono().setExtension(request.getParameter("extension_"+bo.getIndex()));
					bo.getTelefono().setIdTipoTelefono(Utils.parseInt(request.getParameter("tipoTelefono_"+bo.getIndex())));
					bo.getTelefono().setTelefono(request.getParameter("telefono_"+bo.getIndex()));
					if (bo.getTelefono().getIdTipoTelefono() == 1) bo.getTelefono().setAcceso("044");
					else bo.getTelefono().setAcceso("01");
					BeneficiarioVO vo = new BeneficiarioVO();
					try {
						BeanUtils.copyProperties(vo, bo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					contacts.add(vo);
				}
			}
		}
		return contacts;
	}
	
	private FormatoPTATVO getFormatoPTAT(HttpServletRequest request, ProgramaForm programaForm) {
		FormatoPTATVO ptat = programaForm.getPtat();
		ptat.setPeso(Utils.parseInt(request.getParameter("idPeso")));
		ptat.setEstatura(Utils.parseInt(request.getParameter("idEstatura")));
		ptat.setIdComplexion(Utils.parseInt(request.getParameter("idComplexion")));
		if (null != request.getParameter("correoElectronico2") &&  !request.getParameter("correoElectronico2").isEmpty())
				ptat.setCorreoElectronico2(request.getParameter("correoElectronico2"));
		ptat.setNumeroHijos(Utils.parseInt(request.getParameter("totalHijos")));
		ptat.setHijosMenores18(Utils.parseInt(request.getParameter("hijosMenores")));
		ptat.setLicenciaSituacion(Utils.parseInt(request.getParameter("idSituacionLicencia")));
		if (null == ptat.getNumeroDependientes()) ptat.setNumeroDependientes(0);
		ptat.setIdExperienciaAgricola(Utils.parseInt(request.getParameter("idExperienciaAgricola")));
		ptat.setTieneIntervencionesMedicas(Utils.parseInt(request.getParameter("tieneIntervenciones")));
		ptat.setLicenciaInternacionalDisp(Utils.parseInt(request.getParameter("licenciaInternacional")));
		ptat.setOtrosTiposCultivo(null != request.getParameter("otrosTiposCultivo") ? request.getParameter("otrosTiposCultivo") : "");
		ptat.setCaseta(getCaseta(request, programaForm));
		return ptat;
	}
	
	private TelefonoVO getCaseta(HttpServletRequest request, ProgramaForm programaForm) {
		TelefonoVO caseta = null;
		if (null != programaForm.getPtat().getCaseta()) caseta =  programaForm.getPtat().getCaseta();
		else caseta = new TelefonoVO();
		caseta.setClave(request.getParameter("clavec"));
		caseta.setTelefono(request.getParameter("telefonoc"));
		caseta.setExtension(request.getParameter("extensionc"));
		caseta.setPrincipal(MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		caseta.setIdPropietario(programaForm.getPerfil().getIdCandidato());
		caseta.setIdTipoTelefono(Utils.parseInt(request.getParameter("tipoTelefonoc")));
		if (caseta.getIdTipoTelefono() == Constantes.TELEFONO_CELULAR)
			caseta.setAcceso(Constantes.TIPO_TELEFONO.CELULAR.getAcceso());
		else caseta.setAcceso(Constantes.TIPO_TELEFONO.FIJO.getAcceso());
		caseta.setIdTipoPropietario(TIPO_PROPIETARIO.CASETA.getIdTipoPropietario());
		return caseta;
	}
	
	private List<IdiomaVO> getDomainLangs(HttpServletRequest request, ProgramaForm programaForm) {
		List<IdiomaVO> langs = new ArrayList<IdiomaVO>();
		if (null != programaForm.getIdiomaReqIng()) {
			programaForm.getIdiomaReqIng().setIdDominio(Utils.parseLong(request.getParameter("idDominioIngles")));
			langs.add(programaForm.getIdiomaReqIng());
		}else {
			IdiomaVO english = new IdiomaVO();
			english.setIdCandidato(programaForm.getPerfil().getIdCandidato());
			english.setIdDominio(Utils.parseLong(request.getParameter("idDominioIngles")));
			english.setIdIdioma(IDIOMAS.INGLES.getIdOpcion());
			english.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
			english.setIdCertificacion(Constantes.CATALOGO_OPCION_NINGUNA);
			langs.add(english);
		}
		if (null != programaForm.getIdiomaReqFrn()) {
			programaForm.getIdiomaReqFrn().setIdDominio(Utils.parseLong(request.getParameter("idDominioFrances")));
			langs.add(programaForm.getIdiomaReqFrn());
		}else {
			IdiomaVO french = new IdiomaVO();
			french.setIdCandidato(programaForm.getPerfil().getIdCandidato());
			french.setIdDominio(Utils.parseLong(request.getParameter("idDominioFrances")));
			french.setIdIdioma(IDIOMAS.FRANCES.getIdOpcion());
			french.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
			french.setIdCertificacion(Constantes.CATALOGO_OPCION_NINGUNA);
			langs.add(french);
		}
		return langs;
	}
	
	private IdiomaVO setIdiomaAdicional(long idIdioma, long idCertificacion, long idDominio, int idDominioHabla, int idDominioEscrito, int idDominioLectura) {
		IdiomaVO lang = new IdiomaVO();
		lang.setIdIdioma(idIdioma);
		lang.setIdDominio(idDominio);
		lang.setIdCertificacion(idCertificacion);
		lang.setIdDominioHabla(idDominioHabla);
		lang.setIdDominioEscrito(idDominioEscrito);
		lang.setIdDominioLectura(idDominioLectura);
		lang.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());	
		return lang;
	}
	
	private void agregarCatalogosIdiomasDependientes(ProgramaForm programaForm, long idIdioma){
		int totalIdiomas = programaForm.getIdiomasAdicionales().size()+1;
		if (totalIdiomas==1)
			programaForm.setIdiomasMultiRegistro1(setIdiomasMultiRegistro(idIdioma, programaForm.getIdiomasDependientes()));
		if (totalIdiomas==2)
			programaForm.setIdiomasMultiRegistro2(setIdiomasMultiRegistro(idIdioma, programaForm.getIdiomasDependientes()));
		if (totalIdiomas==3)
			programaForm.setIdiomasMultiRegistro3(setIdiomasMultiRegistro(idIdioma, programaForm.getIdiomasDependientes()));		
	}
	
	private boolean existeIdiomaEnListado(List<IdiomaVO> listado, long idIdioma, long idCertificacion){
		boolean existe = false;
		if (listado!=null && !listado.isEmpty()) {
			for(IdiomaVO vo: listado){
				if((vo.getIdIdioma()==idIdioma)&&(vo.getIdCertificacion()==idCertificacion)){
					existe=true;
					break;
				}
			}
		}
		return existe;
	}
	
	@SuppressWarnings("unchecked")
	private void deleteBeneficiariesList(HttpServletRequest request) {
		List<BeneficiarioBO> benef2Del = (List<BeneficiarioBO>)request.getSession().getAttribute("LST_BENEFICIARIES_2DEL");
		if (null == benef2Del) return;
		for (BeneficiarioBO bo : benef2Del) {
			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			try {
				services.removeBeneficiario(bo.getIdBeneficiario());
			} catch (BusinessException | ServiceLocatorException e) {
				logger.error(e);
			}
		}
		request.getSession().removeAttribute("LST_BENEFICIARIES_2DEL");
	}
}
