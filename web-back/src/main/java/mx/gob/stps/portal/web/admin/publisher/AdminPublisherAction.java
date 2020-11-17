package mx.gob.stps.portal.web.admin.publisher;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.seguridad.vo.PublicadorVO;
import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;

import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegateImpl;
import mx.gob.stps.portal.web.autorization.form.ParametrizarForm;

public class AdminPublisherAction extends PagerAction {
	
	private static Logger logger = Logger.getLogger(AdminPublisherAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("NUM_PAGE_LIST");
		session.removeAttribute("TOTAL_PAGES");
		session.removeAttribute("PAGE_JUMP_SIZE");
		session.removeAttribute("NUM_PAGE_JUMP");
		session.removeAttribute("NUM_RECORDS_VISIBLE");
		session.removeAttribute("NUM_RECORDS_TOTAL");
		ParametrizarForm paramform = (ParametrizarForm)form;
		AutorizationBusDelegate service = AutorizationBusDelegateImpl.getInstance();
		try {
			long records = 0;
			paramform.reset();
			paramform.setValor((null != getParameter() ?  getParameter().getValor() : ""));
			List<PublicadorVO> publisherList = service.publishersList();
			records = service.totalRegistrosPorValidar();
			this.PAGE_NUM_ROW = 5;
    		this.PAGE_JUMP_SIZE = 5;
    		request.getSession().setAttribute("_RECORDS", String.valueOf(records));
    		request.getSession().setAttribute(FULL_LIST, publisherList); //paginacion
    		request.getSession().setAttribute("ParametrizarForm", paramform);
    		page(mapping, form, request, response); // Primera paginacion
		} catch (Exception e) { e.printStackTrace(); }
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();

		request.getSession().setAttribute(TITULO_PAGINA, "Administrador de publicaciones");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administrador de publicaciones, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}
	
	/**
	 * Obtiene los datos capturados por el usuario e invoca el registros de prametro
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward balance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ParametrizarForm paramform = (ParametrizarForm)form;
		String msg = "";
		String type = "";
		ActionErrors errors = null;
		if (errors==null) { 
			try {
				AutorizationBusDelegate services = AutorizationBusDelegateImpl.getInstance();
				services.actualizaParametro(2, paramform.getValor());
				msg = getMensaje(request, "aut.param.msg1");
				type = ResultVO.TYPE_SUCCESS;
			} catch (ServiceLocatorException e) {
				logger.error(e);
				msg = getMensaje(request, "app.exp.locator.err");
				type = ResultVO.TYPE_ERROR;
			} catch (Exception e) {
				logger.error(e);
				msg = getMensaje(request, "aut.param.msg2");
				type = ResultVO.TYPE_ERROR;
			}
		}
		try {
			String json = toJson(new ResultVO(msg, type));
			redirectJsonResponse(response, json);
			return null;
		} catch (IOException e) {
			logger.error(e);
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

			PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Administrador de publicaciones");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administrador de publicaciones, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
	        return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}
	}
	
	public ParametroVO getParameter() {
		ParametroVO vo = null;
		try {
			AutorizationBusDelegate services = AutorizationBusDelegateImpl.getInstance();
			vo = services.consultaParametro(2);
		}catch (ServiceLocatorException e) {logger.error(e);}
		return vo;
	}
}
