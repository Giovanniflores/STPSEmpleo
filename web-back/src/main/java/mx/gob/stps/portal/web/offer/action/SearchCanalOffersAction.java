package mx.gob.stps.portal.web.offer.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_BUSQUEDAS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CANAL_CAPACIDADES;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CANAL_EGRESADOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CANAL_ESTUDIANTES;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CANAL_MAYORES;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.offer.delegate.SearchCanalOffersBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.SearchCanalOffersBusDelegateImpl;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Muestra las ofertas activas por Canal (Candidatos)
 * @author jose.jimenez
 *
 */
public class SearchCanalOffersAction extends PagerSpecificAction {

	private static Logger logger = Logger.getLogger(SearchCanalOffersAction.class);
	
	private static final String ESTUDIANTE = "Estudiantes";
	private static final String RECIEN_EGRESADOS = "Recién Egresados";
	private static final String ADULTOS_MAYORES = "Adultos mayores";
	private static final String  PERSONAS_DISCAPACIDAD = "Personas con discapacidad";
	
	/**
	 * Obtiene las ofertas activas por canal en base al par&aacute;metro recibido
	 */
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		logger.info("----SearchCanalOffersAction.init: inicio metodo");
		List<Long> ofertasCanal = null;
		String tipoConsulta = request.getParameter("searchType");
		String tipoConsultaUrl = "";
		HttpSession session = request.getSession();
		session.removeAttribute(FULL_LIST);
		session.removeAttribute("SPECIFIC_SEARCH");
		session.removeAttribute("_urlpageinvoke");
		session.removeAttribute("NUM_REGISTROS");
		
		try{
			
			SearchCanalOffersBusDelegate canalOffersServices = SearchCanalOffersBusDelegateImpl.getInstance();
	
			// Obtiene las ofertas del canal seleccionado
			ofertasCanal = canalOffersServices.getOffersPerCanal(tipoConsulta.trim());
			
			if(tipoConsulta.trim().equals(CANAL_ESTUDIANTES)){
				request.setAttribute("TITULO_CONSULTA", "Ofertas para estudiantes");
				tipoConsulta = ESTUDIANTE;
				tipoConsultaUrl = "ESTUDIANTES";
			}
			
			else if(tipoConsulta.trim().equals(CANAL_EGRESADOS)){
				request.setAttribute("TITULO_CONSULTA", "Ofertas para recién egresados");
				
				tipoConsulta = RECIEN_EGRESADOS;
				tipoConsultaUrl = "EGRESADOS";
			}
				
			else if(tipoConsulta.trim().equals(CANAL_MAYORES)){
				request.setAttribute("TITULO_CONSULTA", "Ofertas para adultos mayores");
				
				tipoConsulta = ADULTOS_MAYORES;
				tipoConsultaUrl = "MAYORES";
			}
				
			else if(tipoConsulta.trim().equals(CANAL_CAPACIDADES)){
				request.setAttribute("TITULO_CONSULTA", "Ofertas para personas con discapacidad");
				
				tipoConsulta = PERSONAS_DISCAPACIDAD;
				tipoConsultaUrl = "CAPACIDADES";
				
			}
				
				
			
			session.removeAttribute(NUM_PAGE_LIST);
			session.setAttribute("SPECIFIC_SEARCH", tipoConsulta);
			session.setAttribute(FULL_LIST, ofertasCanal);
			session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
			request.setAttribute("tipoConsultaUrl", tipoConsultaUrl);
			request.getSession().setAttribute("_urlpageinvoke", "canalOfertas.do?method=init&searchType=" + tipoConsultaUrl);
			
		} catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "aut.error.locator");
		} catch (SQLException e) {
			logger.error(e);
			registraError(request, "aut.error.sql.persist");
		} catch (Exception e) {
			logger.error(e);
			registraError(request, "aut.error.sql.persist");
		}
		logger.info("----SearchCanalOffersAction.init: CanalOferta = " + tipoConsulta);
		logger.info("----SearchCanalOffersAction.init: fin metodo");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		return mapping.findForward(FORWARD_TEMPLATE_BUSQUEDAS);
		
	}
	
	public ActionForward orderByColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		logger.info("----SearchCanalOffersAction.orderByColumn: inicio ordenamiento columna");
		HttpSession session = request.getSession();
		String tipoOrdenamiento = request.getParameter("tipoOrden");
		String numeroColumna = request.getParameter("tipoColumna");
		String canal = request.getParameter("ofertaCanal");
		List<Long> indices = null;
		try{
			SearchCanalOffersBusDelegate canalOffersServices = SearchCanalOffersBusDelegateImpl.getInstance();
			Integer columna = null;
			if(numeroColumna != null)
				columna = Integer.parseInt(numeroColumna);
			// Obtiene las ofertas del canal seleccionado y las ordena en forma Ascendente o Descendente
			indices = canalOffersServices.ordenarOfertasPorCanal(tipoOrdenamiento, columna, canal);
			
			session.removeAttribute(FULL_LIST);
			session.setAttribute(FULL_LIST, indices);
		}catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		}catch(Exception e){
			logger.error(e);
		}
		logger.info("----SearchCanalOffersAction.orderByColumn: ordenacion exitosa");
		return page(mapping, form, request, response);
	}

}
