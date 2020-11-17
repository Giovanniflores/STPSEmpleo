package mx.gob.stps.portal.web.security.action;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraVO;
import mx.gob.stps.portal.persistencia.vo.OfertasRecientesVO;
// Articulos de su interess y Banner
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegate;
import mx.gob.stps.portal.web.crm.delegate.CrmBusDelegateImpl;
// Fin Articulos de su interess y Banner
import mx.gob.stps.portal.web.crm.form.InicioForm;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.oferta.extranjera.delegate.OfertaExtranjeraBusDelegateImpl;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

public final class InicioAction extends Action {
	
	private static Logger logger = Logger.getLogger(InicioAction.class);

	// Articulos de su interess y Banner
	private CrmBusDelegate servicio = CrmBusDelegateImpl.getInstance();
	// Fin Articulos de su interess y Banner

	/**
	 * Direcciona a la pagina de inicio del sitio aplicando la plantilla
	 * correspondiente
	 */
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServiceLocatorException {
		String fromAbriendoEspacios = request.getParameter("fromAbriendoEspacios");
		PropertiesLoader properties = PropertiesLoader.getInstance();
		if (null != fromAbriendoEspacios && fromAbriendoEspacios.equalsIgnoreCase("true"))
			request.getSession().setAttribute("bFromAbriendoEspacios", true);
		UsuarioWebVO user = (UsuarioWebVO) request.getSession().getAttribute(USUARIO_APP);
		if (null != user) {
			try {
				response.sendRedirect(properties.getProperty("app.swb.redirect.home") + "login.do?method=userlogged");
				logger.info("JGLC -> Ya existe una sesion!");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			obtenerOferta(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Articulos de su interess y Banner
		InicioForm articulosForm = (InicioForm) form;

		articulosForm.setArticulosFormList(servicio.getArticulosHome());
		articulosForm.setBannerFormList(servicio.getBannersHome());
		// Fin Articulos de su interess y Banner
		request.getSession().setAttribute(SHOW_BUSCADOR_OFERTAS, Utils.formatComa(getTotalOffers(request)));
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
//        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Portal del Empleo | Bolsa de Trabajo en M&eacute;xico");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return mapping.findForward(FORWARD_TEMPLATE_HOME);
	}

	private void obtenerOferta(HttpServletRequest request) throws Exception {

		String CONTADOR_OFERTAS = "CONTADOR_OFERTAS";
		ServletContext context = request.getSession().getServletContext();
		Integer contador = (Integer) context.getAttribute(CONTADOR_OFERTAS);

		if (context.getAttribute("recientes") == null || contador > 10000) {

			OfferBusDelegate service = OfferBusDelegateImpl.getInstance();

			List<OfertasRecientesVO> recientes = service.getOfertasRecientes(Constantes.OFERTARECIENTE);
			List<OfertasRecientesVO> destacadas = service.getOfertasRecientes(Constantes.OFERTADESTACADA);
			List<OfertasRecientesVO> gendarmeria = service.getOfertasRecientes(Constantes.OFERTAGENDARMERIA);
			List<OfertasRecientesVO> pet = service.getOfertasRecientes(Constantes.OFERTAPET);
			List<OfertaExtranjeraVO> externalOffers = OfertaExtranjeraBusDelegateImpl.getInstance().findAll();
			context.setAttribute("recientes", recientes);
			context.setAttribute("destacadas", destacadas);
			context.setAttribute("gendarmeria", gendarmeria);
			context.setAttribute("pet", pet);
			context.setAttribute("externalOffers", externalOffers);
			contador = 0;

		}

		contador++;
		context.setAttribute(CONTADOR_OFERTAS, contador);

	}
	
//	private void obtenerOferta(HttpServletRequest request) throws Exception {
//
//		OfferBusDelegate service = OfferBusDelegateImpl.getInstance();
//		List<OfertasRecientesVO> recientes = service.getOfertasRecientes(Constantes.OFERTARECIENTE);
//		List<OfertasRecientesVO> destacadas = service.getOfertasRecientes(Constantes.OFERTADESTACADA);
//		List<OfertasRecientesVO> gendarmeria = service.getOfertasRecientes(Constantes.OFERTAGENDARMERIA);
//		List<OfertasRecientesVO> pet = service.getOfertasRecientes(Constantes.OFERTAPET);
//		request.getSession().setAttribute("recientes", recientes);
//		request.getSession().setAttribute("destacadas", destacadas);
//		request.getSession().setAttribute("gendarmeria", gendarmeria);
//		request.getSession().setAttribute("pet", pet);
//	}
	
	private boolean isValidAccessToken(HttpServletRequest request) {
		boolean accessToken;
		PropertiesLoader properties = PropertiesLoader.getInstance();
		ServletContext context = request.getSession().getServletContext();
		String dateTimeStamp = (String)context.getAttribute("accessToken");
    	if (null == dateTimeStamp) {
    		accessToken = false;
    		context.setAttribute("accessToken", getTimeStamp());
    	}else {
	    	Date now = new Date();
	    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	    	long min = properties.getPropertyInt("schedule.ofertas.publicadas.periodo.min");
			if (min<=0) min = 60;
			long intervalDuration = min * 60 * 1000;
	    	try {
				Date previous = df.parse(dateTimeStamp);
				if (now.getTime() - previous.getTime() >= intervalDuration) {
					accessToken = false;
					context.setAttribute("accessToken", getTimeStamp());
				}
				else accessToken = true;
	    	}catch (Exception e) { accessToken = false; }
    	}
    	return accessToken;
    }
	
	private String getTimeStamp() {
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    	Date today = Calendar.getInstance().getTime(); 
    	return df.format(today);
    }
	
	private long getTotalOffers(HttpServletRequest request) {	
		long total = 0;
		try {
			OfferBusDelegate servicio = OfferBusDelegateImpl.getInstance();
			if (!isValidAccessToken(request)) {
				total = servicio.consultaTotalOfertasPublicadas();
				if (total <= 0)
					total = PropertiesLoader.getInstance().getPropertyInt("ofertas.total.disponibles");
				total = (total / 1000) * 1000;
				request.getSession().getServletContext().setAttribute(SHOW_BUSCADOR_OFERTAS, total);
			}else total = (Long)request.getSession().getServletContext().getAttribute(SHOW_BUSCADOR_OFERTAS);
		} catch (Exception e) {
			logger.error(e);
		}
		return total;
	}
}