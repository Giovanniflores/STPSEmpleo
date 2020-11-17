package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.utils.ConstantesGenerales.CATALOGO_MOTIVOS_NO_PROGRAMACION_ENTREVISTA;
import static mx.gob.stps.portal.utils.ConstantesGenerales.CATALOGO_MOTIVOS_NO_ACEPTACION;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.Catalogos.APLICACION;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS_POSTULACION_EXTERNA;
import mx.gob.stps.portal.utils.Catalogos.FUISTE_CONTRATADO;
import mx.gob.stps.portal.utils.Catalogos.MOTIVOS_FUERA_PPC;
import mx.gob.stps.portal.utils.Catalogos.MOTIVOS_RECHAZO_SEGUIMIENTO;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Constantes.MESES;
import mx.gob.stps.portal.persistencia.entity.PostulacionExterna;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.SeguimientoPostulacionForm;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;

public class SeguimientoPostulacionAction extends GenericAction {
	
	private static final int TRACKING = 1;
	private static final int INTERVIEW = 2;
	private static Logger logger = Logger.getLogger(SeguimientoPostulacionAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idOfertaCandidato = 0;
		OfertaEmpleoJB offer = null;
		OfertaCandidatoVO oc = null;
		HttpSession session = request.getSession();
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		SeguimientoPostulacionForm spf = (SeguimientoPostulacionForm)form;
		if (null != request.getParameter("idOfertaCandidato")) {
			idOfertaCandidato = Utils.parseLong(request.getParameter("idOfertaCandidato"));
			try {
				oc = services.findOCById(idOfertaCandidato);
				offer = services.buscarOfertaEmpleo(oc.getIdOfertaEmpleo());
				spf.setOffer(offer);
				spf.setOfferCand(oc);
				spf.load();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
		if (oc.getEstatus() == ESTATUS.EN_PROCESO.getIdOpcion()) {
			session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());
			session.setAttribute("_urlpageinvoke", "misofertas.do?method=misPostulaciones");	
		} else session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());	
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Seguimiento de Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Seguimiento de Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	public ActionForward tracking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String contactEnt = null;
		String scheduledInterview = null;
		HttpSession session = request.getSession();
		SeguimientoPostulacionForm spf = (SeguimientoPostulacionForm)form;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		try {
			OfertaCandidatoVO vo = spf.getOfferCand();
			UsuarioWebVO userLogged = getUsuario(request.getSession());
			spf.setEstatusPPC(services.consultarPpcEstatus(spf.getOfferCand().getIdCandidato()));
			contactEnt = spf.getDiaContacto() + "/" + spf.getMesContacto() + "/" + spf.getAnioContacto();
			scheduledInterview = 0 != spf.getDiaEntrevista() ? spf.getDiaEntrevista() + "/" + spf.getMesEntrevista() + "/" + spf.getAnioEntrevista() : "";
			vo.setConseguioEntrevista(spf.getConseguisteEntrevista());
			vo.setMotivoNoEntrevista(spf.getMotivoNoEntrevista());
			vo.setFechaContacto(Utils.toDate(contactEnt));
			vo.setFechaEntrevista(!"".equals(scheduledInterview) ? Utils.toDate(scheduledInterview) : null);
			vo.setFechaSeguimiento(new Date());
			vo.setIdOficinaSeguimiento(10000L);
			vo.setIdUsuarioSeguimiento(userLogged.getIdUsuario());
			vo.setIdFuenteSeguimiento((long)Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.PORTAL.getIdOpcion());
			if (spf.getMotivoNoEntrevista() > 1 && spf.getMotivoNoEntrevista() != 3)
				vo.setEstatus(ESTATUS.NO_ACEPTADO.getIdOpcion());
			if (spf.getConseguisteEntrevista() == Catalogos.DECISION.SI.getIdOpcion() && null != vo.getFechaEntrevista())
				vo.setFechaInicioContratacion(new Date());
			int result = services.registrarSeguimientoPostulacion(userLogged.getIdUsuario(), vo, ESTATUS.getDescripcion(vo.getEstatus()));
			if (result > 0) spf.setControl(TRACKING);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Seguimiento de Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Seguimiento de Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	public ActionForward interview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		session.setAttribute("_urlpageinvoke", "misofertas.do?method=misPostulaciones");
		request.getSession().setAttribute(TITULO_PAGINA, "Seguimiento de Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Seguimiento de Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	public ActionForward result(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String scheduledContract = null;
		HttpSession session = request.getSession();
		SeguimientoPostulacionForm spf = (SeguimientoPostulacionForm)form;
		CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
		try {
			UsuarioWebVO userLogged = getUsuario(request.getSession());
			scheduledContract = 0 != spf.getDiaContrato() ? spf.getDiaContrato() + "/" + spf.getMesContrato() + "/" + spf.getAnioContrato() : "";
			int estatusPPC = services.consultarPpcEstatus(spf.getOfferCand().getIdCandidato());
			Integer ppcIdMotivoFuera = null;
			OfertaCandidatoVO oc = spf.getOfferCand();
			if (spf.getFuisteContratado() == FUISTE_CONTRATADO.SI.getIdOpcion()) {
				oc.setEstatus(ESTATUS.CONTRATADO.getIdOpcion());
				if (estatusPPC == mx.gob.stps.portal.utils.Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion() || estatusPPC == mx.gob.stps.portal.utils.Catalogos.ESTATUS.INACTIVO_PPC.getIdOpcion()) {
					estatusPPC = ESTATUS.FUERA_PPC.getIdOpcion();
					ppcIdMotivoFuera = MOTIVOS_FUERA_PPC.CANDIDATO_COLOCADO_EN_OFERTA_SNE.getIdOpcion();
				}
			}else if (spf.getFuisteContratado() == FUISTE_CONTRATADO.NO.getIdOpcion()) {
				oc.setEstatus(ESTATUS.NO_ACEPTADO.getIdOpcion());
				if (spf.getEstatusPPC() == ESTATUS.INACTIVO_PPC.getIdOpcion() && !postulacionesPendientes(oc))
					estatusPPC = mx.gob.stps.portal.utils.Catalogos.ESTATUS.ACTIVO_PPC.getIdOpcion();
			}else if (spf.getFuisteContratado() == FUISTE_CONTRATADO.EN_ESPERA.getIdOpcion()) {
				oc.setEstatus(ESTATUS.EN_PROCESO.getIdOpcion());
				oc.setFechaInicioContratacion(new Date());
			}
			oc.setIdOficinaSeguimiento(10000L);
			oc.setIdUsuarioSeguimiento(userLogged.getIdUsuario());
			oc.setIdFuenteSeguimiento((long)Catalogos.CATALOGO_FUENTE_QUE_REGISTRA_OFERTA.PORTAL.getIdOpcion());
			oc.setFuisteContratado(spf.getFuisteContratado());
			oc.setIdMotivo(spf.getMotivoNoContrato());
			oc.setFechaColocacion(!"".equals(scheduledContract) ? Utils.toDate(scheduledContract) : null);
			oc.setMotivoDesc(MOTIVOS_RECHAZO_SEGUIMIENTO.OTRO.getIdOpcion() == spf.getMotivoNoContrato() ? spf.getMotivoNoContratoEspecifique() : null);
			oc.setFechaSeguimiento(new Date());
			int result = services.resultadoEntrevistaPostulacion(userLogged.getIdUsuario(), oc, ESTATUS.getDescripcion(oc.getEstatus()));
			if (result > 0) {
				spf.setControl(INTERVIEW);
				spf.setEstatusPPC(estatusPPC);
				actionsTakenAfterContract(oc.getIdOfertaEmpleo(), oc.getIdCandidato(), oc.getEstatus());
				if (estatusPPC == mx.gob.stps.portal.utils.Catalogos.ESTATUS.FUERA_PPC.getIdOpcion())
					services.setContratadoPPC(oc.getIdCandidato(), estatusPPC, ppcIdMotivoFuera, scheduledContract, 
							APLICACION.PORTAL_DEL_EMPLEO.getOpcion(), spf.getOffer().getEmpresa().getNombreEmpresa(), spf.getOffer().getTituloOferta());
				else services.actualizaEstatusPPC(oc.getIdCandidato(), estatusPPC, ppcIdMotivoFuera);
				if (oc.getEstatus() == ESTATUS.CONTRATADO.getIdOpcion()) 
					session.setAttribute("_urlpageinvoke", "misofertas.do?method=miContratacionPpc&idOC=" + oc.getIdOfertaCandidato());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Seguimiento de Postulaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Seguimiento de Postulaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
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
				opcion.setOpcion(mes.getOpcion());
				opciones.add(opcion);
			}
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	public ActionForward motivoNoEntrevista(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_MOTIVOS_NO_PROGRAMACION_ENTREVISTA, true);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	public ActionForward motivoNoContrato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			long filter[] = {4, 5, 8, 10, 11, 14};
			List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_MOTIVOS_NO_ACEPTACION, filter);
			redirectJsonCatalogo(opciones, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	private void redirectJsonCatalogo(List<CatalogoOpcionVO> opciones, HttpServletResponse response) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}
	
	private boolean postulacionesPendientes(OfertaCandidatoVO current) throws BusinessException, ServiceLocatorException {
		OfferBusDelegate offerService = OfferBusDelegateImpl.getInstance();
		List<OfertaCandidatoVO> OfertaCandidatoList = offerService.findAllOffersByCandidate(current.getIdCandidato());
		for (OfertaCandidatoVO oc : OfertaCandidatoList) {
			if (oc.getIdOfertaCandidato() == current.getIdOfertaCandidato()) continue;
			if (oc.getEstatus() == ESTATUS.POSTULADO.getIdOpcion() || oc.getEstatus() == ESTATUS.EN_PROCESO.getIdOpcion())
				return true;
		}
		return false;
	}
	
	private int actionsTakenAfterContract(long idOferta, long idCandidato, long estatus) {
		OfertaEmpleoVO ofertaVO = null;
		OfertaBusDelegate offerService = OfertaBusDelegateImpl.getInstance();
		try {
			if (estatus != ESTATUS.CONTRATADO.getIdOpcion()) return -1;
			ofertaVO = offerService.consultaOfertaEmpleo(idOferta);
			int awardedVacancies = null != ofertaVO.getPlazasCubiertas() ? ofertaVO.getPlazasCubiertas().intValue() + 1 : 1;
			ofertaVO.setPlazasCubiertas(awardedVacancies);
			offerService.update(ofertaVO);
			if (ofertaVO.getNumeroPlazas() == awardedVacancies) {
				offerService.actualizaEstatus(idOferta, ESTATUS.CUBIERTA.getIdOpcion());
				closePendingPostRelatedOffer(idOferta);
			}
			updatePendingPostByCandidate(idCandidato);
		} catch (Exception e) {
			logger.error("Error in actionsTakenAfterContract", e);
			e.printStackTrace();
		}
		return 1;
	}
	
	private void closePendingPostRelatedOffer(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException {
		OfferBusDelegate offerService = OfferBusDelegateImpl.getInstance();
		offerService.closePendingPostRelatedOffer(idOfertaEmpleo);
	}
	
	private void updatePendingPostByCandidate(long idCandidato) {
		List<PostulacionExterna> externas;
		List<OfertaCandidatoVO> ofertaCandidatoList;
		OfferBusDelegate offerService = OfferBusDelegateImpl.getInstance();
		try {
			ofertaCandidatoList = offerService.findAllOffersByCandidate(idCandidato);
			for (OfertaCandidatoVO oc : ofertaCandidatoList) {
				if (oc.getEstatus() == ESTATUS.POSTULADO.getIdOpcion() || oc.getEstatus() == ESTATUS.EN_PROCESO.getIdOpcion()) {
					oc.setEstatus(ESTATUS.CONTRATADO_EN_OTRA_OFERTA.getIdOpcion());
					offerService.update(oc);
				}
			}
			externas = offerService.obtenerPostulacionesCandidato(idCandidato);
			for (PostulacionExterna externa : externas) {
				externa.setEstatus(ESTATUS_POSTULACION_EXTERNA.CONTRATADO_OTRA_OFERTA.getIdOpcion());
				offerService.darSeguimientoPostulacion(externa);
			}
		} catch (Exception e) {
			logger.error("Error updatePendingPostByCandidate ", e);
			e.printStackTrace();
		}
	}
}