package mx.gob.stps.portal.web.oferta.search.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.NUMERO_REGISTROS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.NUMERO_REGISTROS_TODAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REINTENTAR;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaRecienteVO;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.oferta.search.delegate.OfertasRecientesBusDelegate;
import mx.gob.stps.portal.web.oferta.search.delegate.OfertasRecientesBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.search.form.OfertasRecientesForm;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 22/03/2011
 **/
public class OfertasRecientesAction extends PagerAction {

	private static Logger logger = Logger.getLogger(OfertasRecientesAction.class);
	private static final String ACTION_DETALLE_OFERTA = "/detalleoferta.do?";
	private static final String METHOD_INIT = "method=init";
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ofertasRecientes(mapping, form, request, response);
		return mapping.findForward(FORWARD_JSP);
	}

	/**
	 * Obtiene las ofertas recientes 
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 22/03/2011
	 **/
	public ActionForward ofertasRecientes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		OfertasRecientesForm ofertasForm = (OfertasRecientesForm) form;
		ofertasForm.reset(mapping, request);

		String nextAction = FORWARD_JSP;

		try {
			OfertasRecientesBusDelegate services = OfertasRecientesBusDelegateImpl.getInstance();
			List<OfertaRecienteVO> ofertasRecientes = services.obtenerOfertasRecientes(NUMERO_REGISTROS);
			if (ofertasRecientes==null) ofertasRecientes = new ArrayList<OfertaRecienteVO>();

			for (OfertaRecienteVO oferta : ofertasRecientes)
				oferta.setTituloOferta(Utils.capitalize(oferta.getTituloOferta()));
			
			ofertasForm.setTituloReporte("Ofertas Recientes");
			ofertasForm.setOfertas(ofertasRecientes);
			
			session.setAttribute("OFERTA_RECIENTE", 1);
			session.setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=home");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			nextAction = FORWARD_REINTENTAR;
		}

		return mapping.findForward(nextAction);
	}

	/**
	 * Obtiene la totalidad de ofertas recientes, cuando se quieren ver todas
	 * 
	 * @author Sergio Rojas Aceña
	 * @since 09/02/2012
	 **/
	public ActionForward ofertasRecientesTodas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		String nextAction = FORWARD_TEMPLATE_RESPONSIVE;

		try {
			OfertasRecientesBusDelegate services = OfertasRecientesBusDelegateImpl.getInstance();
			List<OfertaPorCanalVO> ofertasCanal = services.obtenerOfertasRecientesTodas(NUMERO_REGISTROS_TODAS);

			this.PAGE_NUM_ROW = 10;
		    this.PAGE_JUMP_SIZE = 10;

		    request.getSession().removeAttribute(FULL_LIST);
		    request.getSession().setAttribute(FULL_LIST, ofertasCanal); //paginacion
		    
		    request.getSession().removeAttribute(NUM_PAGE_LIST);
		    request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);


		    request.getSession().removeAttribute("OFERTA_RECIENTE");		    
			session.setAttribute("OFERTA_RECIENTE", 1);
//			request.getSession().setAttribute("TITULO", "Ofertas de empleo recientes");
			request.setAttribute("TITULO", "Ofertas de empleo recientes");
			
		    //request.getSession().removeAttribute("_urlpageinvoke");	
			//session.setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=ofertasRecientesTodas");
	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Ofertas recientes");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Ofertas recientes, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);			
			nextAction = FORWARD_REINTENTAR;
		}
		
		//request.setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=ofertasRecientes");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_TODAS").getPath());

		return mapping.findForward(nextAction);
	}

	
	/**
	 * Obtiene las ofertas destacadas
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 22/03/2011
	 **/
	public ActionForward ofertasDestacadas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		OfertasRecientesForm ofertasForm = (OfertasRecientesForm) form;
		ofertasForm.reset(mapping, request);

		String nextAction = FORWARD_JSP;

		try {
			OfertasRecientesBusDelegate services = OfertasRecientesBusDelegateImpl.getInstance();
			List<OfertaRecienteVO> ofertasDestacadas = services.obtenerOfertasDestacadas(NUMERO_REGISTROS);

			if (ofertasDestacadas!=null){
				for (OfertaRecienteVO oferta : ofertasDestacadas){
					oferta.setTituloOferta(Utils.capitalize(oferta.getTituloOferta()));
				}
			}
			
			ofertasForm.setTituloReporte("Ofertas Destacadas");
			ofertasForm.setOfertas(ofertasDestacadas);
			
			session.setAttribute("OFERTA_RECIENTE", 2);
			
			session.setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=home");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			nextAction = FORWARD_REINTENTAR;
		}
		
		return mapping.findForward(nextAction);
	}
	
	public ActionForward ofertasCanada(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		OfertasRecientesForm ofertasForm = (OfertasRecientesForm) form;
		ofertasForm.reset(mapping, request);

		String nextAction = FORWARD_JSP;

		try {
			/* COMENTAR EN PRODUCCION 	   */
			OfertasRecientesBusDelegate services = OfertasRecientesBusDelegateImpl.getInstance();
			List<OfertaRecienteVO> ofertasCanada = services.obtenerOfertasCanada();
		 
			/*COMENTAR EN QA 
			List<OfertaRecienteVO> ofertasCanada = new ArrayList<OfertaRecienteVO>();
			OfertaRecienteVO ofTemp = new OfertaRecienteVO();
			//ofTemp.setIdOfertaEmpleo(10);
			//ofTemp.setTituloOferta("Técnicos en reparación mecánica de camiones y trailers");
			//ofTemp.setUbicacion("Ontario, Canadá.");
			//ofTemp.setVigencia("Salario neto mensual aproximado 21,632 pesos");
			//ofertasCanada.add(ofTemp);
			//ofTemp = new OfertaRecienteVO();
			
			ofTemp.setIdOfertaEmpleo(4);
			ofTemp.setTituloOferta("Techadores");
			ofTemp.setUbicacion("Alberta, Canadá.");
			ofTemp.setVigencia("Salario neto mensual aproximado 20,000 pesos");		
			ofertasCanada.add(ofTemp);
			ofTemp = new OfertaRecienteVO();
			
			ofTemp.setIdOfertaEmpleo(11);
			ofTemp.setTituloOferta("Mecánicos de Equipo Pesado");
			ofTemp.setUbicacion("Saskatchewan, Canadá.");
			ofTemp.setVigencia("Salario neto mensual aproximado 22,000 pesos");	
			ofertasCanada.add(ofTemp);
			
			ofTemp = new OfertaRecienteVO();
			ofTemp.setIdOfertaEmpleo(1);
			ofTemp.setTituloOferta("Técnicos en Reparación de Camiones de Camiones y Trailers");
			ofTemp.setUbicacion("Ontario,Canadá");
			ofTemp.setVigencia("Salario neto mensual aproximado 18,000 pesos");		
			ofertasCanada.add(ofTemp);			
			ofTemp = new OfertaRecienteVO();

			ofTemp.setIdOfertaEmpleo(5);			
			ofTemp.setTituloOferta("Carnicero");
			ofTemp.setUbicacion("Columbia Británica, Canadá.");
			ofTemp.setVigencia("Salario neto mensual aproximado 18,000 pesos");		
			ofertasCanada.add(ofTemp);	
			
			/*ofTemp = new OfertaRecienteVO();
			
			ofTemp.setIdOfertaEmpleo(6);
			ofTemp.setTituloOferta("Pintores de recubrimientos industriales");
			ofTemp.setUbicacion("Saskatchewan, Canadá.");
			ofTemp.setVigencia("Salario neto mensual aproximado 20,000 pesos");		
			ofertasCanada.add(ofTemp);
			ofTemp = new OfertaRecienteVO();

			ofTemp.setIdOfertaEmpleo(7);			
			ofTemp.setTituloOferta("Operador de equipo pesado");
			ofTemp.setUbicacion("Saskatchewan, Canadá.");
			ofTemp.setVigencia("Salario neto mensual aproximado 20,000 pesos");		
			ofertasCanada.add(ofTemp);	
			 
		*/
			if (ofertasCanada!=null){
				for (OfertaRecienteVO oferta : ofertasCanada){
					oferta.setTituloOferta(Utils.capitalize(oferta.getTituloOferta()));
				}
			}
		
			ofertasForm.setTituloReporte("Ofertas Canadá");
			ofertasForm.setOfertas(ofertasCanada);
			
			session.setAttribute("OFERTA_RECIENTE", 3);
			
			session.setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=home");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			nextAction = FORWARD_REINTENTAR;
		}
		
		return mapping.findForward(nextAction);
	}	

	public ActionForward ofertasGendarmeria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		OfertasRecientesForm ofertasForm = (OfertasRecientesForm) form;
		ofertasForm.reset(mapping, request);
		String nextAction = FORWARD_JSP;

		try {
			OfertasRecientesBusDelegate services = OfertasRecientesBusDelegateImpl.getInstance();
			List<OfertaRecienteVO> ofertasGendarmeria = services.obtenerOfertasGendarmeria(NUMERO_REGISTROS);
			if (ofertasGendarmeria==null) ofertasGendarmeria = new ArrayList<OfertaRecienteVO>();

			for (OfertaRecienteVO oferta : ofertasGendarmeria)
				oferta.setTituloOferta(Utils.capitalize(oferta.getTituloOferta()));
			
			ofertasForm.setTituloReporte("Ofertas Gendarmeria");
			ofertasForm.setOfertas(ofertasGendarmeria);
			
			session.setAttribute("OFERTA_RECIENTE", 4);
			session.setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=home");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			nextAction = FORWARD_REINTENTAR;
		}

		return mapping.findForward(nextAction);		
	}
	
	public ActionForward totalOfertasRecientes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		/*PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlredirect = properties.getProperty("app.swb.redirect.ofertas.recientes");
		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		return mapping.findForward(FORWARD_REDIRECT_SWB);*/
		return ofertasRecientesTodas(mapping, form, request, response);
	}	
	
	public ActionForward totalOfertasDestacadas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		/*PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlredirect = properties.getProperty("app.swb.redirect.ofertas.destacadas");
		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		return mapping.findForward(FORWARD_REDIRECT_SWB);*/
		return ofertasDestacadasTodas(mapping, form, request, response);
	}		
	
	public ActionForward totalOfertasCanada(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//PropertiesLoader properties = PropertiesLoader.getInstance();
		//String urlredirect = properties.getProperty("app.swb.redirect.ofertas.canada");
		String urlredirect = "https://www.empleo.gob.mx";
		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}			
	
	public ActionForward totalOfertasGendarmeria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return ofertasGendarmeriaTodas(mapping, form, request, response);
	}
	
	/**
	 * Obtiene la totalidad de ofertas destacadas, cuando se quieren ver todas
	 * 
	 * @author Sergio Rojas Aceña
	 * @since 09/02/2012
	 **/
	public ActionForward ofertasDestacadasTodas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		String nextAction = FORWARD_TEMPLATE_RESPONSIVE;

		try {
			OfertasRecientesBusDelegate services = OfertasRecientesBusDelegateImpl.getInstance();
			List<OfertaPorCanalVO> ofertasCanal = services.obtenerOfertasDestacadasTodas(NUMERO_REGISTROS_TODAS);

			this.PAGE_NUM_ROW = 10;					
		    this.PAGE_JUMP_SIZE = 10;

		    request.getSession().removeAttribute(FULL_LIST);		    
		    request.getSession().setAttribute(FULL_LIST, ofertasCanal); //paginacion
		    
		    request.getSession().removeAttribute(NUM_PAGE_LIST);
		    request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);

		    request.getSession().removeAttribute("OFERTA_RECIENTE");		    
			session.setAttribute("OFERTA_RECIENTE", 2);
			
			request.getSession().setAttribute("TITULO", "Ofertas destacadas");
	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Ofertas destacadas");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Ofertas de empleo destacadas, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			nextAction = FORWARD_REINTENTAR;
		}
//	    request.getSession().removeAttribute("_urlpageinvoke");
//		request.setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=ofertasDestacadas");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_TODAS").getPath());		
		return mapping.findForward(nextAction);
	}		
	
	
	public ActionForward ofertasCanadaTodas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		String nextAction = FORWARD_TEMPLATE_RESPONSIVE;

		try {
			OfertasRecientesBusDelegate services = OfertasRecientesBusDelegateImpl.getInstance();
			List<OfertaPorCanalVO> ofertasCanal = services.obtenerOfertasCanadaTodas(NUMERO_REGISTROS_TODAS);

			this.PAGE_NUM_ROW = 10;					
		    this.PAGE_JUMP_SIZE = 10;

		    request.getSession().removeAttribute(FULL_LIST);		    
		    request.getSession().setAttribute(FULL_LIST, ofertasCanal); //paginacion
		    
		    request.getSession().removeAttribute(NUM_PAGE_LIST);
		    request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);

		    request.getSession().removeAttribute("OFERTA_RECIENTE");		    
			session.setAttribute("OFERTA_RECIENTE", 3);
			
			request.getSession().setAttribute("TITULO", "Ofertas Canadá");
	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Ofertas Canad&aacute;");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Ofertas de empleo de Canad&aacute;, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			nextAction = FORWARD_REINTENTAR;
		}
	    request.getSession().removeAttribute("_urlpageinvoke");
		request.setAttribute("_urlpageinvoke", "ofertasRecientes.do?method=ofertasCanada");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_TODAS").getPath());		
		return mapping.findForward(nextAction);
	}		
	
	public ActionForward ofertasGendarmeriaTodas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();

		String nextAction = FORWARD_TEMPLATE_RESPONSIVE;
		try{
			OfertasRecientesBusDelegate services = OfertasRecientesBusDelegateImpl.getInstance();
			List<OfertaPorCanalVO> ofertasCanal = services.obtenerOfertasGendarmeriaTodas(NUMERO_REGISTROS_TODAS); 
			
			this.PAGE_NUM_ROW = 10;					
		    this.PAGE_JUMP_SIZE = 10;

		    request.getSession().removeAttribute(FULL_LIST);		    
		    request.getSession().setAttribute(FULL_LIST, ofertasCanal); //paginacion
		    
		    request.getSession().removeAttribute(NUM_PAGE_LIST);
		    request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);

		    request.getSession().removeAttribute("OFERTA_RECIENTE");		    
			session.setAttribute("OFERTA_RECIENTE", 4);
			
			request.getSession().setAttribute("TITULO", "Ofertas gendarmeria");
	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Ofertas en gendarmer&iacute;a");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Ofertas de empleo en gendarmer&iacute;a, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			nextAction = FORWARD_REINTENTAR;
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_TODAS").getPath());
		return mapping.findForward(nextAction);
	}
	
	public ActionForward detalleoferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idOfertaEmpleo = Utils.parseLong(request.getParameter("id_oferta_empleo"));

		//PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlredirect = request.getContextPath()+ACTION_DETALLE_OFERTA+METHOD_INIT;//properties.getProperty("app.swb.redirect.detalle.oferta");

		urlredirect += "&id_oferta_empleo="+ idOfertaEmpleo;

		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}
	
	public ActionForward detalleofertaCanada(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idOfertaEmpleo = Utils.parseLong(request.getParameter("id_oferta_empleo"));

		PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlredirect = properties.getProperty("app.swb.redirect.detalle.oferta.canada");

		urlredirect += "?id_oferta_empleo="+ idOfertaEmpleo + "#v" + idOfertaEmpleo;
		System.out.println("urlredirect:" + urlredirect);
		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}	

	@SuppressWarnings("rawtypes")
	protected List getRows(int pagenum, List<?> rows, HttpSession session){

		List rowsPage = super.getRows(pagenum, rows);

    	List<OfertaPorCanalVO> ofertasCanal = new ArrayList<OfertaPorCanalVO>();
    	
    	for (Object rowElement: rowsPage) {
    		OfertaPorCanalVO oferta = (OfertaPorCanalVO)rowElement;
    		ofertasCanal.add(oferta);
    	}
    	
	    UsuarioWebVO usuario = getUsuario(session);
	    if (usuario != null) {

	    	long idCandidato = usuario.getIdPropietario();
	    
	    	OfferBusDelegate offerServices = OfferBusDelegateImpl.getInstance();
            for (int i = 0; i<ofertasCanal.size(); i++){
            	OfertaPorCanalVO oferta = (OfertaPorCanalVO)ofertasCanal.get(i);
            	
            	try{
            		int compatibility = offerServices.match(oferta.getIdOfertaEmpleo(), idCandidato);
            		oferta.setCompatibility(compatibility);
            	}
            	catch (ServiceLocatorException e){
            		e.printStackTrace();
        			logger.error(e);
            	}          	
            }    

	    }		
		return ofertasCanal;
	}

	public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlHome = properties.getProperty("app.swb.redirect.home");
		request.setAttribute(URL_REDIRECT_SWB, urlHome);
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}

}
