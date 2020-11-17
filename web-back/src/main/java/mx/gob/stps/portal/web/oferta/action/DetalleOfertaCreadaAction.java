package mx.gob.stps.portal.web.oferta.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_EMP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.web.candidate.delegate.AdmonCandidatosBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.AdmonCandidatosForm;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Clase Action de las ofertas creadas por la empresa.
 * @author Sergio Tellez
 *
 */
public class DetalleOfertaCreadaAction  extends PagerAction{

	private static final String TABLA_CANDIDATOS = "_CANDIDATOS";
	private static final String TABLA_POSTULADOS = "_POSTULADOS";

	private static Logger logger = Logger.getLogger(DetalleOfertaCreadaAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<CandidatoVo> candidatos = new ArrayList<CandidatoVo>();

		AdmonCandidatosForm admonCandidatosForm = (AdmonCandidatosForm) form;
		long idOferta = Utils.parseLong(request.getParameter("ido"));
		
		//UsuarioWebVO webVo = getUsuario(request.getSession());		
		
		try {
			OfertaPorPerfilVO oferta = AdmonCandidatosBusDelegateImpl.getInstance().obtenerDatosOferta(idOferta);
			admonCandidatosForm.setOfertaDetalle(oferta);

			candidatos = IndexerServiceLocator.getIndexadorServiceRemote().busquedaCandidatos(idOferta);

			admonCandidatosForm.setCandidatos(candidatos);
			//admonCandidatosForm.setPostulados(postulados);
			
			
			PAGE_NUM_ROW = 5;
			session.setAttribute(FULL_LIST + TABLA_CANDIDATOS, candidatos);
			session.setAttribute("NUM_REGISTROS" + TABLA_CANDIDATOS, PAGE_NUM_ROW);
			//session.setAttribute(FULL_LIST + TABLA_POSTULADOS, postulados);
			session.setAttribute("NUM_REGISTROS" + TABLA_POSTULADOS, PAGE_NUM_ROW );
			session.removeAttribute(NUM_PAGE_LIST + "_CANDIDATOS");

		} catch (SQLException sql) { logger.error(sql);
		} catch (PersistenceException pe) { pe.printStackTrace();
		} catch (BusinessException be) { be.printStackTrace();
		} catch (ServiceLocatorException se) { logger.error(se);
		} catch (Exception te) { te.printStackTrace(); }
		ocultaBarraArticulos(request);
		request.getSession().setAttribute("_urlpageinvoke", "detalleOfertaCreada.do?method=init&ido="+idOferta);
    	request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta creada");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Oferta creada, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
	}
	
	public ActionForward pageTable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		AdmonCandidatosForm admonCandidatosForm = (AdmonCandidatosForm) form;
		
		if (admonCandidatosForm.getTablaPager().equals(""))
			return this.page(1, mapping, session );
		else
			return this.page(1, mapping, session, admonCandidatosForm.getTablaPager());
	}
	
	public final ActionForward prevPageTable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		AdmonCandidatosForm admonCandidatosForm = (AdmonCandidatosForm) form;

		if (admonCandidatosForm.getTablaPager().equals(""))
			return this.prev(mapping, admonCandidatosForm, request, response);
		else
			return this.prev(mapping, admonCandidatosForm, request, response);
	}
	
	public final ActionForward nextPageTable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		AdmonCandidatosForm admonCandidatosForm = (AdmonCandidatosForm) form;

		if (admonCandidatosForm.getTablaPager().equals(""))
			return this.next(mapping, admonCandidatosForm, request, response);
		else
			return this.next(mapping, admonCandidatosForm, request, response);
	}
}