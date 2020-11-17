package mx.gob.stps.portal.web.security.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaExternaTotalVO;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegate;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegateImpl;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TransferenciaOfertasAction extends GenericAction {
	private static Logger logger = Logger.getLogger(TransferenciaOfertasAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Transferencia de Oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Transferencia de Oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString())); 
        return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}

	public ActionForward transferencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	
		Date fechaTransferencia = null;
		String fecha = request.getParameter("fecha");
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			fechaTransferencia = sdf.parse(fecha);

			if (fechaTransferencia==null) throw new BusinessException("oferta.externa.trans.fecha.error");

			Date hoy = Calendar.getInstance().getTime();

			if (fechaTransferencia.after(hoy))throw new BusinessException("oferta.externa.trans.hoy.error");
			
			try{
				BolsasTrabajoBusDelegate service = BolsasTrabajoBusDelegateImpl.getInstance();
				OfertaExternaTotalVO totales = service.transfiereOfertasExternasSFP(fechaTransferencia);
					
				request.setAttribute("totales", totales);

				registraMensaje(request, "oferta.externa.trans.exito", Utils.formatDDMMYYYY(fechaTransferencia));

			}catch(Exception e){
				e.printStackTrace(); logger.error(e);
				registraError(request, "oferta.externa.trans.error");
			}			

		} catch (BusinessException e) {
			logger.error(e);
			registraError(request, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "oferta.externa.trans.error");
		}

		request.getSession().setAttribute(BODY_JSP, mapping.getInput());

        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Transferencia de Oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Transferencia de Oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString())); 
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}	

}