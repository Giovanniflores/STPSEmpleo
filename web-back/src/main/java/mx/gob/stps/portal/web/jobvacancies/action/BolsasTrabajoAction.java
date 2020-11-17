package mx.gob.stps.portal.web.jobvacancies.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_BOLSAS_TRABAJO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_MI_ESP_CAND;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MENU;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TAB_MI_ESPACIO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.infra.utils.Constantes.BOLSAS_TRABAJO_EXTERNAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoInVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoSFPVO;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.HispavistaRestClient;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegate;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegateImpl;
import mx.gob.stps.portal.web.jobvacancies.form.BolsasTrabajoForm;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;

public class BolsasTrabajoAction extends PagerAction {
	protected static final String BOLSAS_EXTERNAS = "LISTABOLSASEXTERNAS";
	protected static final String OFERTAS_SFP = "OFERTAS_SFP";
	private static final String OFERTA_SFP = "OFERTA_SFP";
	private static final String DETALLE = "DETALLE";
	
	private static final String SECCION = "seccion";
	private static final String OTRAS_BOLSAS = "otras-bolsas";
	
	private static final String JSP_RESULT = "JSP_RESULT";

	private static final int CAMPO_TITULO 		= 1;
	private static final int CAMPO_UBICACION 	= 2;
	private static final int CAMPO_EMPRESA 		= 3;
	private static final int CAMPO_FECHA 		= 4;
	
	private static final int ORDEN_ASC 			= 1;
	private static final int ORDEN_DESC			= 2;
	
	private static Logger logger = Logger.getLogger(BolsasTrabajoAction.class);
	
	private boolean existenOfertas;

	private static final Map<String, String> catalogoFechas = new HashMap<String, String>();
	
	static{
		catalogoFechas.put("1", "Hoy");
		catalogoFechas.put("3", "Ultimos 3 días");
		catalogoFechas.put("7", "Ultimos 7 días");
		catalogoFechas.put("30", "Ultimos 30 días");
		catalogoFechas.put("60", "Ultimos 60 días");
	}

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		 HttpSession session = request.getSession();
		 BolsasTrabajoForm bolsasTrabajoForm = (BolsasTrabajoForm) form;
		 
		 session.removeAttribute("ofertas");
		 session.removeAttribute("existenOfertas");
		 session.removeAttribute(FULL_LIST);
		 session.removeAttribute(PAGE_LIST);
		 request.setAttribute("TITULO", "Buscar en otras bolsas de trabajo");
		 boolean fromPortal = false;
		 if(request.getParameter("fromPortal")!=null) fromPortal = true;
		 
		 bolsasTrabajoForm.setFromPortal(fromPortal);
		 UsuarioWebVO usuario = getUsuario(session);
		 
		 String template = null;
		 
		 if(usuario != null && !fromPortal){
			 
			 request.setAttribute(SECCION, OTRAS_BOLSAS);
			 session.setAttribute(TAB_MENU, TAB_MI_ESPACIO);
			 
			 session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			 template = FORWARD_TEMPLATE_MI_ESP_CAND;
		 }else{

			 session.setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
			 template = FORWARD_TEMPLATE_BOLSAS_TRABAJO;
		 }

		request.setAttribute("HTML_TITLE_TAG", "Portal del Empleo :: Búsqueda en otras bolsas de trabajo");
		 ocultaBarraArticulos(request);
	        PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "B&uacute;squeda en otras bolsas de trabajo");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Espacio Candidato, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		 return mapping.findForward(template);
	}

	/**
	 * Busca las ofertas disponibles en las bolsas de trabajo externas: 
	 * OCC, Bumeran, Manpower, Adecco y el Universal.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		BolsasTrabajoForm forma = (BolsasTrabajoForm)form;

		HttpSession session = request.getSession();

		String template = null;
		
		UsuarioWebVO usuario = getUsuario(session);

		try {
			session.removeAttribute(FULL_LIST);
			session.removeAttribute(PAGE_LIST);

			registraDetallesRequest(forma, request);
			
			if (forma.getEntidad()>0 && forma.getFecha()!=null && forma.getBusquedaen()> 0) {
				
				BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
				
				OfertaEmpleoInVO vo = new OfertaEmpleoInVO();
				vo.setEntidad(forma.getEntidad());
				vo.setFecha(forma.getFecha());
				vo.setPalabra(forma.getPalabra());
				vo.setBusquedaen(forma.getBusquedaen());
				
				List<OfertaEmpleoOutVO> listaBolsas = services.buscarOfertas(vo);

				if (listaBolsas==null) listaBolsas = new ArrayList<OfertaEmpleoOutVO>();
				
				if(listaBolsas.size() == 1){
					
					String existenResultados = listaBolsas.get(0).getFecha();
					if(existenResultados.equals("No se encontraron datos")){
						this.existenOfertas = false;
						session.setAttribute("existenOfertas", existenOfertas);
						request.setAttribute("noResultados", "No");
						
					}else{
						 session.setAttribute(FULL_LIST, listaBolsas); //paginacion
					}
				}else if(listaBolsas.isEmpty()){
					if(listaBolsas.isEmpty()){
						this.existenOfertas = false;
						session.setAttribute("existenOfertas", existenOfertas);
						request.setAttribute("noResultados", "No");
					}else{
						session.setAttribute(FULL_LIST, listaBolsas); //paginacion
					}
				}else if(!listaBolsas.isEmpty() && listaBolsas.size() > 1){
					session.setAttribute(FULL_LIST, listaBolsas); //paginacion
				}

				this.PAGE_NUM_ROW = 5;
			    this.PAGE_JUMP_SIZE = 5;

				request.setAttribute(BOLSAS_EXTERNAS, listaBolsas);
				
				session.removeAttribute(NUM_PAGE_LIST);
			    session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
			    
			    session.removeAttribute(NUM_PAGE_LIST);
			    session.setAttribute("tituloEmpresa", "Empresa");
					
			}

			if(usuario != null && !forma.isFromPortal()){
				request.setAttribute(SECCION, OTRAS_BOLSAS);
				template = FORWARD_TEMPLATE_MI_ESP_CAND;
			}else{
				template = FORWARD_TEMPLATE_BOLSAS_TRABAJO;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

			session.removeAttribute(FULL_LIST);
			session.removeAttribute(PAGE_LIST);
		} finally {
			if (usuario!=null)
				SecutityDelegateImpl.getInstance().busquedaOtrasBolsasTrabajo(usuario.getIdUsuario(), usuario.getIdPerfil());
		}
		
		forma.reset(mapping, request);
		ocultaBarraArticulos(request);

		session.setAttribute(BODY_JSP, mapping.findForward(JSP_RESULT).getPath());

        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "B&uacute;squeda en otras bolsas de trabajo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "B&uacute;squeda en otras bolsas de trabajo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(template);
	}

	public ActionForward buscarSFP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		BolsasTrabajoForm forma = (BolsasTrabajoForm)form;
		HttpSession session = request.getSession();
		
		String template = null;
		
		UsuarioWebVO usuario = getUsuario(session);
		
		try {
			session.removeAttribute(FULL_LIST);
			session.removeAttribute(PAGE_LIST);

			registraDetallesRequest(forma, request);
			
			//COMENTAR EN QA if (forma.getEntidad() > 0 && forma.getIdOcupacion() > 0 ){
				BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();	
				//COMENTAR EN QA List<OfertaEmpleoOutVO> ofertas = services.buscaOfertasSFP(forma.getEntidad(), forma.getIdOcupacion());
				List<OfertaEmpleoOutVO> ofertas = services.buscaOfertasSFP();
				
				if(ofertas.isEmpty()){
					this.existenOfertas = false;
					session.setAttribute("existenOfertas", existenOfertas);
					request.setAttribute("noResultados", "No");
				}else{
					session.removeAttribute(FULL_LIST);
				    session.setAttribute(FULL_LIST, ofertas); //paginacion
				}
				
				this.PAGE_NUM_ROW = 5;
			    this.PAGE_JUMP_SIZE = 5;
			    session.removeAttribute("ofertas");
			    session.removeAttribute(NUM_PAGE_LIST);
			    session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
			    session.removeAttribute(NUM_PAGE_LIST);
			//COMENTAR EN QA }
			
			 session.setAttribute("tituloEmpresa", "Empresa");
			
			if(usuario != null && !forma.isFromPortal()){
				request.setAttribute(SECCION, OTRAS_BOLSAS);
		        PropertiesLoader properties = PropertiesLoader.getInstance();
				request.getSession().setAttribute(TITULO_PAGINA, "Empresa");
				request.getSession().setAttribute(DESCRIPCION_PAGINA, "Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
				request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
				request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
				request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
				template = FORWARD_TEMPLATE_MI_ESP_CAND;
			}else{
				template = FORWARD_TEMPLATE_BOLSAS_TRABAJO;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e); 
		} finally {
			if (usuario!=null)
				SecutityDelegateImpl.getInstance().busquedaOtrasBolsasTrabajo(usuario.getIdUsuario(), usuario.getIdPerfil());
		}
		
		forma.reset(mapping, request);
		
		session.setAttribute(BODY_JSP, mapping.findForward(JSP_RESULT).getPath());
        return mapping.findForward(template);
	}

	public ActionForward buscarVacantesDisyEmp(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		BolsasTrabajoForm forma = (BolsasTrabajoForm)form;
		HttpSession session = request.getSession();
		
		String template = null;
		UsuarioWebVO usuario = getUsuario(session);
		
		BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
		
		try {
			registraDetallesRequest(forma, request);
			
			List<OfertaEmpleoOutVO> listaBolsas = services.buscarVacantesDyE();
			
			
			if(listaBolsas.size() == 1){
				
				String existenResultados = listaBolsas.get(0).getFecha();
				if(existenResultados.equals("No se encontraron datos")){
					this.existenOfertas = false;
					session.setAttribute("existenOfertas", existenOfertas);
					request.setAttribute("noResultados", "No");
					
				}else{
					 session.setAttribute(FULL_LIST, listaBolsas); //paginacion
				}
			}else if(listaBolsas.isEmpty()){
				if(listaBolsas.isEmpty()){
					this.existenOfertas = false;
					session.setAttribute("existenOfertas", existenOfertas);
					request.setAttribute("noResultados", "No");
				}else{
					session.setAttribute(FULL_LIST, listaBolsas); //paginacion
				}
			}else if(!listaBolsas.isEmpty() && listaBolsas.size() > 1){
				session.setAttribute(FULL_LIST, listaBolsas); //paginacion
			}


			this.PAGE_NUM_ROW = 10;
		    this.PAGE_JUMP_SIZE = 10;

			request.setAttribute(BOLSAS_EXTERNAS, listaBolsas);
			
			session.removeAttribute(NUM_PAGE_LIST);
		    session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
		    
		    session.removeAttribute(NUM_PAGE_LIST);

		    session.setAttribute("tituloEmpresa", "Tipo Discapacidad");
		
		
			if(usuario != null && !forma.isFromPortal()){
				request.setAttribute(SECCION, OTRAS_BOLSAS);
				template = FORWARD_TEMPLATE_MI_ESP_CAND;
			}else{
				template = FORWARD_TEMPLATE_BOLSAS_TRABAJO;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e); 
		} finally {
			if (usuario!=null)
				SecutityDelegateImpl.getInstance().busquedaOtrasBolsasTrabajo(usuario.getIdUsuario(), usuario.getIdPerfil());
		}
		
		forma.reset(mapping, request);
		
		session.setAttribute(BODY_JSP, mapping.findForward(JSP_RESULT).getPath());
		
		return mapping.findForward(template);
	}
	
	public ActionForward detalleOfertaSFP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
		BolsasTrabajoForm forma = (BolsasTrabajoForm)form;
		try {
			OfertaEmpleoSFPVO oferta = services.buscaOfertaSFP(forma.getIdOferta());
			request.setAttribute(OFERTA_SFP, oferta);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (ParseException e) {
			logger.error(e);
		}
		request.getSession().setAttribute(BODY_JSP,mapping.findForward(DETALLE).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Detalle oferta SFP");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}	
	
	public ActionForward detalleOfertaGenerico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		BolsasTrabajoForm forma = (BolsasTrabajoForm)form;
		
		long idOfertaEmpleo = forma.getIdOferta();

		PropertiesLoader properties = PropertiesLoader.getInstance();
		String urlredirect = properties.getProperty("app.swb.redirect.detalle.oferta");

		urlredirect += "?id_oferta_empleo="+ idOfertaEmpleo;

		request.setAttribute(URL_REDIRECT_SWB, urlredirect);
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}	
	
	public ActionForward regresar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(BOLSAS_EXTERNAS, null);
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "B&uacute;squeda en otras bolsas de trabajo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "B&uacute;squeda en otras bolsas de trabajo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
	}
	
	public ActionForward insertOfertasSFP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		try {
			BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
			services.iniciaTransferenciaOfertasSFP();
		}catch (ServiceLocatorException e) {
			logger.error(e); 
		}
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Oferta SFP");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);
		
	}

	private void registraDetallesRequest(BolsasTrabajoForm forma, HttpServletRequest request){
		BOLSAS_TRABAJO_EXTERNAS bolsa = BOLSAS_TRABAJO_EXTERNAS.getBolsa(forma.getBusquedaen());
		String bolsaNombre = "";
		if (bolsa!=null) {
			bolsaNombre = bolsa.getNombre();
		}
		
		ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(forma.getEntidad());

		String entidadDesc = "";
		if (entidad != null) {
			entidadDesc = entidad.getDescripcion();
		} else { // Hispavista
			entidadDesc = forma.getHispavistaState();
		}
		
		String fechaDesc = catalogoFechas.get(forma.getFecha());
		if (fechaDesc==null) {
			fechaDesc = "";
		}

		request.setAttribute("filtroBolsa", bolsaNombre);
		request.setAttribute("filtroPalabra", forma.getPalabra());
		request.setAttribute("filtroEntidad", entidadDesc);
		request.setAttribute("filtroFecha", fechaDesc);	// TODO: How to get this data from Hispavista. Do not exist
		request.setAttribute("busquedaen", String.valueOf(forma.getBusquedaen()));
	}

	public ActionForward ordenaPor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		HttpSession session = request.getSession();

		int campo = Utils.parseInt(request.getParameter("campo"));
		int orden = Utils.parseInt(request.getParameter("orden"));

		ordenarPor(session, campo, orden);

		return super.page(mapping, form, request, response);
	}
	
	@SuppressWarnings("unchecked")
	private void ordenarPor(HttpSession session, int campo, final int orden){
		try{
			List<OfertaEmpleoOutVO> ofertas = (List<OfertaEmpleoOutVO>)session.getAttribute(FULL_LIST);

			if (ofertas==null || ofertas.isEmpty()) return;
			
			Comparator<OfertaEmpleoOutVO> comparator = null;
			
			switch(campo){
				case CAMPO_TITULO:
					comparator = new Comparator<OfertaEmpleoOutVO>(){
						@Override
		                public int compare(OfertaEmpleoOutVO o1, OfertaEmpleoOutVO o2) {
		                	String valor01 = o1.getPuesto();
		                	String valor02 = o2.getPuesto();

		                	if (valor01==null && valor02==null) return 0;
		                	if (valor01==null && valor02!=null) return -1;
		                	if (valor01!=null && valor02==null) return 1;
		                	
		                	valor01 = valor01.trim().toUpperCase();
		                	valor02 = valor02.trim().toUpperCase();

		                	int compare = 0;
		                	switch(orden){
		                	case ORDEN_ASC:
		                		compare = valor01.compareTo(valor02);
		                		break;
		                	case ORDEN_DESC:
		                		compare = valor02.compareTo(valor01);
		                		break;
		                	default:
		                		compare = valor01.compareTo(valor02);
		                	}
		                	return compare;
		                }
					};
					break;
				case CAMPO_UBICACION:
					comparator = new Comparator<OfertaEmpleoOutVO>(){
						@Override
		                public int compare(OfertaEmpleoOutVO o1, OfertaEmpleoOutVO o2) {
		                	String valor01 = o1.getEstado();
		                	String valor02 = o2.getEstado();

		                	if (valor01==null && valor02==null) return 0;
		                	if (valor01==null && valor02!=null) return -1;
		                	if (valor01!=null && valor02==null) return 1;
		                	
		                	valor01 = valor01.trim().toUpperCase();
		                	valor02 = valor02.trim().toUpperCase();

		                	int compare = 0;
		                	switch(orden){
		                	case ORDEN_ASC:
		                		compare = valor01.compareTo(valor02);
		                		break;
		                	case ORDEN_DESC:
		                		compare = valor02.compareTo(valor01);
		                		break;
		                	default:
		                		compare = valor01.compareTo(valor02);
		                	}
		                	return compare;
		                }
					};
					break;
				case CAMPO_EMPRESA:
					comparator = new Comparator<OfertaEmpleoOutVO>(){
						@Override
		                public int compare(OfertaEmpleoOutVO o1, OfertaEmpleoOutVO o2) {
		                	String valor01 = o1.getEmpresa();
		                	String valor02 = o2.getEmpresa();

		                	if (valor01==null && valor02==null) return 0;
		                	if (valor01==null && valor02!=null) return -1;
		                	if (valor01!=null && valor02==null) return 1;
		                	
		                	valor01 = valor01.trim().toUpperCase();
		                	valor02 = valor02.trim().toUpperCase();

		                	int compare = 0;
		                	switch(orden){
		                	case ORDEN_ASC:
		                		compare = valor01.compareTo(valor02);
		                		break;
		                	case ORDEN_DESC:
		                		compare = valor02.compareTo(valor01);
		                		break;
		                	default:
		                		compare = valor01.compareTo(valor02);
		                	}
		                	return compare;
		                }
					};
					break;
				case CAMPO_FECHA:
					comparator = new Comparator<OfertaEmpleoOutVO>(){
						@Override
		                public int compare(OfertaEmpleoOutVO o1, OfertaEmpleoOutVO o2) {
		                	String valor01 = o1.getFecha();
		                	String valor02 = o2.getFecha();

		                	if (valor01==null && valor02==null) return 0;
		                	if (valor01==null && valor02!=null) return -1;
		                	if (valor01!=null && valor02==null) return 1;
		                	
		                	valor01 = valor01.trim().toUpperCase();
		                	valor02 = valor02.trim().toUpperCase();

		                	int compare = 0;
		                	switch(orden){
		                	case ORDEN_ASC:
		                		compare = valor01.compareTo(valor02);
		                		break;
		                	case ORDEN_DESC:
		                		compare = valor02.compareTo(valor01);
		                		break;
		                	default:
		                		compare = valor01.compareTo(valor02);
		                	}
		                	return compare;
		                }
					};
					break;
			}

			if (comparator==null) return;

	    	Collections.sort(ofertas, comparator);

	    	session.setAttribute(FULL_LIST, ofertas);

		}catch(Exception e){
			e.printStackTrace();
		}
	}





	public ActionForward obtainHispavistaCountryStatesViaAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CatalogoVO catalogoVO = HispavistaRestClient.requestGetCountryStates(HispavistaRestClient.ID_COUNTRY_MEXICO);
		// TODO: What if catalogoVO is null???
		String json = toJson(catalogoVO);
		try {
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward obtainHispavistaAreasViaAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CatalogoVO catalogoVO = HispavistaRestClient.requestGetAreas();
		String json = toJson(catalogoVO);
		try {
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward obtainHispavistaAreaProfessionsViaAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

//		if(request.getParameter("hispavistaIdArea") != null) {
//		}

		int idArea = Integer.valueOf(request.getParameter("hispavistaIdArea"));

		CatalogoVO catalogoVO = HispavistaRestClient.requestGetAreaProfessions(idArea);
		String json = toJson(catalogoVO);
		try {
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ActionForward buscarVacantesHispavista(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		BolsasTrabajoForm f = (BolsasTrabajoForm)form;

		String search = f.getPalabra();
		int idCountry = HispavistaRestClient.ID_COUNTRY_MEXICO;
		int idState = Integer.valueOf(f.getHispavistaIdState());
//		int idArea = Integer.valueOf(f.getHispavistaIdArea());
//		int idProfession = Integer.valueOf(f.getHispavistaIdProfession());
		int offset = 1;
		int num = 10;

		registraDetallesRequest(f, request);

//		List<OfertaEmpleoOutVO> listaBolsas = HispavistaRestClient.requestPostSearchJobOffers(search, idCountry, idState, offset, num);
		List<OfertaEmpleoOutVO> listaBolsas = new ArrayList<OfertaEmpleoOutVO>();
		
		HttpSession session = request.getSession();
		String template = null;
		UsuarioWebVO usuario = getUsuario(session);

		if (listaBolsas != null && listaBolsas.size() == 1) {
			String existenResultados = listaBolsas.get(0).getFecha();
			if(existenResultados.equals("No se encontraron datos")){
				this.existenOfertas = false;
				session.setAttribute("existenOfertas", existenOfertas);
				request.setAttribute("noResultados", "No");

			}else{
				session.setAttribute(FULL_LIST, listaBolsas); //paginacion
			}
		} else if (listaBolsas.isEmpty()) {
			if (listaBolsas.isEmpty()) {
				this.existenOfertas = false;
				session.setAttribute("existenOfertas", existenOfertas);
				request.setAttribute("noResultados", "No");
			} else {
				session.setAttribute(FULL_LIST, listaBolsas); //paginacion
			}
		} else if (!listaBolsas.isEmpty() && listaBolsas.size() > 1) {
			session.setAttribute(FULL_LIST, listaBolsas); //paginacion
		}

		this.PAGE_NUM_ROW = 10;
		this.PAGE_JUMP_SIZE = 10;

		request.setAttribute(BOLSAS_EXTERNAS, listaBolsas);

		session.removeAttribute(NUM_PAGE_LIST);
		session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);

//		session.removeAttribute(NUM_PAGE_LIST);
//		session.setAttribute("tituloEmpresa", "Tipo Discapacidad");	// TODO: What's this for???

		if (usuario != null && !f.isFromPortal()) {
			request.setAttribute(SECCION, OTRAS_BOLSAS);
			template = FORWARD_TEMPLATE_MI_ESP_CAND;
		} else {
			template = FORWARD_TEMPLATE_BOLSAS_TRABAJO;
		}

		if (usuario != null) {
			SecutityDelegateImpl.getInstance().busquedaOtrasBolsasTrabajo(usuario.getIdUsuario(), usuario.getIdPerfil());
		}

		f.reset(mapping, request);
//
		session.setAttribute(BODY_JSP, mapping.findForward(JSP_RESULT).getPath());

		// ----- PAGER -----
		// Overwrite PagerAction field
//		ACTION_PAGE_TABLE = "ACTION_REGISTROS_TABLA_HISPAVISTA";
//
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "B&uacute;squeda en otras bolsas de trabajo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "B&uacute;squeda en otras bolsas de trabajo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(template);
	}
}
