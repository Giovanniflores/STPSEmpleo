package mx.gob.stps.portal.web.conalep.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.ConalepCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.ConalepPlantelVO;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO;
import mx.gob.stps.portal.web.conalep.delegate.ConalepCandidatoDelegate;
import mx.gob.stps.portal.web.conalep.delegate.ConalepCandidatoDelegateImpl;
import mx.gob.stps.portal.web.conalep.form.ConalepCandidatoForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConalepCandidatoAction extends GenericAction{

	private static Logger logger = Logger.getLogger(ConalepCandidatoAction.class);	

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ConalepCandidatoForm conalepCandidatoForm = (ConalepCandidatoForm) form;
		conalepCandidatoForm.reset();

		HttpSession session = request.getSession();
		UsuarioWebVO usuario = getUsuario(session);
		long idCandidato = usuario.getIdPropietario();
		try {
			ConalepCandidatoVO conalepCandidatoVO = getInstanceDelegate().obtenerConalepCandidato(idCandidato);
			if (conalepCandidatoVO != null) {	// Indeed Registered
				BeanUtils.copyProperties(conalepCandidatoForm, conalepCandidatoVO);
				// Find Plantel
				ConalepPlantelVO conalepPlantelVO = getInstanceDelegate().findPlantel(conalepCandidatoVO.getIdPlantel());
				conalepCandidatoForm.setIdEntidad(conalepPlantelVO.getIdEntidad());
			} else { // Not Registered
				conalepCandidatoForm.setIdCandidato(idCandidato);
			}

			// Check if candidate's address is geo-referenced
			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
			boolean direccionGeoReferenciada = services.estaGeoReferenciado(idCandidato);
			conalepCandidatoForm.setDireccionGeoReferenciada(direccionGeoReferenciada);
		} catch (Exception e) {
			logger.error("Error en " + this.getClass().getName() + " ---> init " + e);
			e.printStackTrace();
		}

		request.getSession().setAttribute(BODY_JSP, mapping.findForward("init").getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Candidatos de Conalep");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
//	public ActionForward catalogos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		response.setContentType("text/text;charset=utf-8");
//	    response.setHeader("cache-control", "no-cache");
//	    String catalogoSolicitado = request.getParameter("catalogo");
//	    PrintWriter out = response.getWriter();
//		CatalogoVO catalogoVO = getInstanceDelegate().getCatalogo(catalogoSolicitado);
//		String catalogoVoJsoned = toJson(catalogoVO);
//	    out.println(catalogoVoJsoned);
//	    out.flush();
//		return null;
//	}

	public ActionForward catalogoAreaNegocio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		CatalogoVO catalogoVO = getInstanceDelegate().obtenerCatalogoConalepAreaNegocio();
		String catalogoVoJsoned = toJson(catalogoVO);
		out.println(catalogoVoJsoned);
		out.flush();
		return null;
	}

	public ActionForward catalogoSubAreaNegocio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		long idAreaNegocio = Long.parseLong(request.getParameter("idAreaNegocio"));
		PrintWriter out = response.getWriter();
		CatalogoVO catalogoVO = getInstanceDelegate().obtenerCatalogoConalepSubAreaNegocio(idAreaNegocio);
		String catalogoVoJsoned = toJson(catalogoVO);
		out.println(catalogoVoJsoned);
		out.flush();
		return null;
	}

	public ActionForward catalogoEntidad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CatalogoOpcionDelegate catService = CatalogoOpcionDelegateImpl.getInstance();

		CatalogoVO catalogoVO = new CatalogoVO("value", "label");

		List<CatalogoOpcionVO> catalogoEntidad = null;
		try {
			catalogoEntidad = catService.consultarCatalogo(mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
			for (CatalogoOpcionVO entidadFederativa : catalogoEntidad) {
				if (entidadFederativa.getIdCatalogoOpcion() != mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS.NACIDO_EN_EL_EXTRANJERO.getIdEntidad()) { // EXCLUDE 'Nacido en el extranjero'
					catalogoVO.addItem(entidadFederativa.getOpcion(), entidadFederativa.getOpcion(), String.valueOf(entidadFederativa.getIdCatalogoOpcion()));
				}
			}
		} catch (ServiceLocatorException e) {
			logger.debug("Scope: catalogoEntidadFederativaList");
			registraError(request, "errors.app", e.getMessage());
		}

		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		String catalogoVoJsoned = toJson(catalogoVO);
		out.println(catalogoVoJsoned);
		out.flush();
		return null;
	}

	public ActionForward catalogoPlantel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		long idEntidad = Long.parseLong(request.getParameter("idEntidad"));
		PrintWriter out = response.getWriter();
		CatalogoVO catalogoVO = getInstanceDelegate().obtenerCatalogoConalepPlantel(idEntidad);
		String catalogoVoJsoned = toJson(catalogoVO);
		out.println(catalogoVoJsoned);
		out.flush();
		return null;
	}

	public ActionForward catalogoPlanEstudios(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();
		CatalogoVO catalogoVO = getInstanceDelegate().obtenerCatalogoPlanEstudios();
		String catalogoVoJsoned = toJson(catalogoVO);
		out.println(catalogoVoJsoned);
		out.flush();
		return null;
	}

	public ActionForward catalogoGeneracion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/text;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out = response.getWriter();

		CatalogoVO catalogoVO =  new CatalogoVO("value", "label");
		catalogoVO.addItem("Antes de 2000", "Antes de 2000", "1999");

		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR);      // The current year as an int

		for (int i = 2000; i <= year; i++ ) {
			catalogoVO.addItem(String.valueOf(i), String.valueOf(i), String.valueOf(i));
		}

		String catalogoVoJsoned = toJson(catalogoVO);
		out.println(catalogoVoJsoned);
		out.flush();
		return null;
	}
	
	@SuppressWarnings("finally")
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/text;charset=utf-8");
	    response.setHeader("cache-control", "no-cache");

		ConalepCandidatoForm conalepCandidatoForm = (ConalepCandidatoForm) form;
		ConalepCandidatoVO conalepCandidatoVO = new ConalepCandidatoVO();

	    PrintWriter out = response.getWriter();
	    try {
			BeanUtils.copyProperties(conalepCandidatoVO, conalepCandidatoForm);
			getInstanceDelegate().save(conalepCandidatoVO);

			CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
			boolean estatusGeoreferencia = services.consultarPermisoGeolocalizacion(conalepCandidatoVO.getIdCandidato());
			if (!estatusGeoreferencia) {
				// Update in DB: Candidato.estatus_georeferencia = true
				services.actualizaEstatusGeoreferencia(conalepCandidatoVO.getIdCandidato(), true);
			}
			out.print("{resp : success}");
		} catch (Exception e) {
			logger.error("Error en " + this.getClass().getName() + " ---> save " + e);
			e.printStackTrace();
			out.print("error");
		}finally {
			out.flush();
		}
		return null;
	}

	private ConalepCandidatoDelegate getInstanceDelegate() {
		ConalepCandidatoDelegate delegate = ConalepCandidatoDelegateImpl.getInstance();
		return delegate;
	}

	private boolean estaRegistrado(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			CandidatoAjaxVO candidato = (CandidatoAjaxVO)session.getAttribute(Constantes.CANDIDATO_HEAD);
			return getInstanceDelegate().estaRegistrado(candidato.getIdCandidato());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}
}