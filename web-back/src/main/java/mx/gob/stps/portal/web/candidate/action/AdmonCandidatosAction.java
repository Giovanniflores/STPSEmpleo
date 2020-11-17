package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_NEXT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.SIN_ARTICULOS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.web.candidate.delegate.AdmonCandidatosBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.AdmonCandidatosBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.AdmonCandidatosForm;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdmonCandidatosAction extends PagerAction {
	private static Logger logger = Logger.getLogger(AdmonCandidatosAction.class);

	private static final String TABLA_CANDIDATOS = "_CANDIDATOS";
	private static final String TABLA_POSTULADOS = "_POSTULADOS";
	private static final String TABLA_NOMINALES = "_NOMINALES";
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idOferta = 0;
		HttpSession session = request.getSession();
		List<CandidatoVo> nominales = new ArrayList<CandidatoVo>();
		List<CandidatoVo> candidatosLista = new ArrayList<CandidatoVo>();
		AdmonCandidatosForm admonCandidatosForm = (AdmonCandidatosForm) form;
		admonCandidatosForm.reset(mapping, request);
		try {
			AdmonCandidatosBusDelegate services = AdmonCandidatosBusDelegateImpl.getInstance();
			if (!(request.getParameter("ido") != null && !request.getParameter("ido").isEmpty())) {
				return null;
			}
			//Se obtiene el detalle de la oferta 
			OfertaPorPerfilVO oferta;
			idOferta = Long.parseLong(request.getParameter("ido"));
			oferta = services.obtenerDatosOferta(idOferta);
			admonCandidatosForm.setOfertaDetalle(oferta);
			//Se obtienen los candidatos seleccionados, postulados y nominales de la oferta
			List<Long> nominalList = services.getNominalList(idOferta);
			List<CandidatoVo> candidatos = services.obtenerCandidatos(oferta.getIdEmpresa(), oferta.getIdOfertaEmpleo());
			for (CandidatoVo candidatoVo : candidatos) {
				if (candidatoVo.getEstatus() != ESTATUS.SELECCIONADA.getIdOpcion()) {
					if (isCandidateNominal(nominalList, candidatoVo.getIdCandidato())) nominales.add(candidatoVo);
					else candidatosLista.add(candidatoVo);
				}
			}
			List<CandidatoVo> postulados = services.obtenerPostulados(oferta.getIdEmpresa(), oferta.getIdOfertaEmpleo());
			//Se establecen atributos al form
			admonCandidatosForm.setCandidatos(candidatosLista);
			admonCandidatosForm.setPostulados(postulados);
			admonCandidatosForm.setNominales(nominales);
			
			//set para el paginador de la tabla candidato
			session.setAttribute(FULL_LIST + TABLA_CANDIDATOS, candidatos);
			session.setAttribute("NUM_REGISTROS" + TABLA_CANDIDATOS, PAGE_NUM_ROW);
			
			//set para el paginador de la tabla postulado
			session.setAttribute(FULL_LIST + TABLA_POSTULADOS, postulados);
			session.setAttribute("NUM_REGISTROS" + TABLA_POSTULADOS, PAGE_NUM_ROW );
			
			//set para el paginador de la tabla nominales
			session.setAttribute(FULL_LIST + TABLA_NOMINALES, nominales);
			session.setAttribute("NUM_REGISTROS" + TABLA_NOMINALES, PAGE_NUM_ROW );
			
			//Se establece el id predeterminado de las ofertas que estan en proceso (deshabilita checkbox)
			session.setAttribute("idEstatusProceso", ESTATUS.EN_PROCESO.getIdOpcion());
			
			request.setAttribute(SIN_ARTICULOS, "true");
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (PersistenceException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		}
		session.setAttribute("_urlpageinvoke", "admonCandidatos.do?method=init&ido="+idOferta);
		session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		session.setAttribute(TITULO_PAGINA, "Administraci&oacute;n de Candidato");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administraci&oacute;n de Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	public ActionForward borrarOfertaCandidatos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResultVO resultVO = null;
		try {
			AdmonCandidatosForm admonCandidatosForm = (AdmonCandidatosForm) form;
			AdmonCandidatosBusDelegate services = AdmonCandidatosBusDelegateImpl.getInstance();
			OfertaPorPerfilVO oferta = admonCandidatosForm.getOfertaDetalle();
			//Se borran las ofertas de candidato
			StringTokenizer listaOfertasCanditatosEliminar = new StringTokenizer(admonCandidatosForm.getListaIdOfertasCandidatos(), "|");
			while (listaOfertasCanditatosEliminar.hasMoreTokens()) {   
				services.eliminarCandidatos(Long.parseLong(listaOfertasCanditatosEliminar.nextToken()));
			}
			//Se actualizan valores del form
			if (admonCandidatosForm.getTablaPager().equals(TABLA_CANDIDATOS)) {
				List<CandidatoVo> candidatos = services.obtenerCandidatos(oferta.getIdEmpresa(), oferta.getIdOfertaEmpleo());
				admonCandidatosForm.setCandidatos(candidatos);
			}
			if (admonCandidatosForm.getTablaPager().equals(TABLA_POSTULADOS)) {
				List<CandidatoVo> postulados = services.obtenerPostulados(oferta.getIdEmpresa(), oferta.getIdOfertaEmpleo());
				admonCandidatosForm.setPostulados(postulados);
			}
			if (admonCandidatosForm.getTablaPager().equals(TABLA_NOMINALES)) {
				List<CandidatoVo> nominales = new ArrayList<CandidatoVo>();
				List<CandidatoVo> candidateList = new ArrayList<CandidatoVo>();
				List<Long> nominalList = services.getNominalList(oferta.getIdOfertaEmpleo());
				List<CandidatoVo> candidatos = services.obtenerCandidatos(oferta.getIdEmpresa(), oferta.getIdOfertaEmpleo());
				for (CandidatoVo candidatoVo : candidatos) {
					if (candidatoVo.getEstatus() != ESTATUS.SELECCIONADA.getIdOpcion()) {
						if (isCandidateNominal(nominalList, candidatoVo.getIdCandidato())) nominales.add(candidatoVo);
						else candidateList.add(candidatoVo);
					}
				}
				admonCandidatosForm.setCandidatos(candidateList);
				admonCandidatosForm.setNominales(nominales);
			}
			//Mensaje de exito
			resultVO = new ResultVO(getMensaje(request, "can.oferta.eliminar.lista.ext"), ResultVO.TYPE_SUCCESS);
		} catch (ServiceLocatorException e) {
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace();
		} catch (PersistenceException e) {
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace();
		} catch (BusinessException e) {
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace();
		} catch (SQLException e) {
			resultVO = new ResultVO(getMensaje(request, "can.oferta.procesar.err"), ResultVO.TYPE_ERROR);
			e.printStackTrace();
		}
		String json = toJson(resultVO);
		redirectJsonResponse(response, json);
		return null;
	}
	
	
	/**
	 * Permite manejar el paginador de varias tablas
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward pageTable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		AdmonCandidatosForm admonCandidatosForm = (AdmonCandidatosForm) form;
		
		int pagenum = 1;
		Integer pagenumParam = (Integer)session.getAttribute(NUM_PAGE_LIST + admonCandidatosForm.getCandidatos());
		if (pagenumParam!=null) pagenum = pagenumParam;
		if (admonCandidatosForm.getTablaPager().equals(""))
			return this.page(pagenum, mapping, session );
		else
			session.setAttribute(FULL_LIST + TABLA_CANDIDATOS, admonCandidatosForm.getCandidatos());
			session.setAttribute(FULL_LIST + TABLA_POSTULADOS, admonCandidatosForm.getPostulados());
			session.setAttribute(FULL_LIST + TABLA_NOMINALES, admonCandidatosForm.getNominales());
			return this.page(pagenum, mapping, session, admonCandidatosForm.getTablaPager());
	}
	
	public ActionForward postulates(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idEmpresa = 0;
		HttpSession session = request.getSession();
		AdmonCandidatosForm admonCandidatosForm = (AdmonCandidatosForm) form;
		admonCandidatosForm.reset(mapping, request);
		try {
			AdmonCandidatosBusDelegate services = AdmonCandidatosBusDelegateImpl.getInstance();
			if (!(request.getParameter("idEmpresa") != null && !request.getParameter("idEmpresa").isEmpty()))
				return null;
			else idEmpresa = Utils.toLong(request.getParameter("idEmpresa"));
			//Se obtienen los candidatos postulados de la empresa
			List<CandidatoVo> postulados = services.postulatesByEmpresaList(idEmpresa);
			admonCandidatosForm.setCandidatos(new ArrayList<CandidatoVo>());
			admonCandidatosForm.setPostulados(postulados);
			
			//set para el paginador de la tabla candidato
			session.setAttribute(FULL_LIST + TABLA_CANDIDATOS, new ArrayList<CandidatoVo>());
			session.setAttribute("NUM_REGISTROS" + TABLA_CANDIDATOS, PAGE_NUM_ROW);
			
			//set para el paginador de la tabla postulado
			session.setAttribute(FULL_LIST + TABLA_POSTULADOS, postulados);
			session.setAttribute("NUM_REGISTROS" + TABLA_POSTULADOS, PAGE_NUM_ROW );
			
			//Se setea el id por defecto de las ofertas que estan en proceso (deshabulita los checkbox)
			session.setAttribute("idEstatusProceso", ESTATUS.EN_PROCESO.getIdOpcion());
			session.setAttribute("_urlpageinvoke", "admonCandidatos.do?method=postulates&idEmpresa="+idEmpresa);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (PersistenceException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
		}
		session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_NEXT).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		session.setAttribute(TITULO_PAGINA, "Mis postulantes");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Empresas, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo ");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	private boolean isCandidateNominal(List<Long> nominalList, long id) {
		for (Long idNominal : nominalList) {
			if (id == idNominal.longValue()) return true;
		}
		return false;
	}
}
