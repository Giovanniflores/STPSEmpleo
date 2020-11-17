////package mx.gob.stps.portal.web.oferta.action;
////
////import mx.gob.stps.portal.web.infra.action.PagerAction;
////
////import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
////
////import java.util.ArrayList;
////import java.util.Collections;
////import java.util.Comparator;
////import java.util.List;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import javax.servlet.http.HttpSession;
////import org.apache.struts.action.ActionForm;
////import org.apache.struts.action.ActionForward;
////import org.apache.struts.action.ActionMapping;
////import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
////import mx.gob.stps.portal.web.infra.action.PagerAction;
////import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
////import mx.gob.stps.portal.web.infra.utils.Utils;
////import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegate;
////import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegateImpl;
////
////public class OfertaRivieraMayaAction extends PagerAction {
////	
////	protected static final String BOLSAS_EXTERNAS = "LISTABOLSASEXTERNAS";
////	private static final String JSP_RESULT = "JSP_RESULT";
////	private boolean existenOfertas;
////	private static final int ORDEN_ASC 			= 1;
////	private static final int ORDEN_DESC			= 2;
////	private static final int CAMPO_TITULO 		= 1;
////	private static final int CAMPO_UBICACION 	= 2;
////	private static final int CAMPO_EMPRESA 		= 3;
////	private static final int CAMPO_FECHA 		= 4;
////
////	@Override
////	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
////		HttpSession session = request.getSession();
////		
////		 List<OfertaRivieraMayaVO> listaOfertas = null;
////		try{
////			 session.removeAttribute(FULL_LIST);
////			 session.removeAttribute(PAGE_LIST);
////			
////			OfertasRivieraMayaDelegate service = OfertasRivieraMayaDelegateImpl.getInstance();
////			
////			listaOfertas =  service.obtenerOfertasRiveraMaya();
////			
////			if(listaOfertas == null)
////				listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
////			
////			if(listaOfertas.size() == 1){
////				if(listaOfertas.get(listaOfertas.size() - 1).getNombrepuesto().equals("No se encontraron datos")){
////					listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
////				}
////			}
////			if(listaOfertas.isEmpty()){
////				
////				//String existenResultados = "No se encontraron datos";
////				
////				this.existenOfertas = false;
////				session.setAttribute("existenOfertas", existenOfertas);
////				request.setAttribute("noResultados", "No");
////				
////				
////		
////			}else{
////				this.existenOfertas = true;
////				session.setAttribute("existenOfertas", existenOfertas);
////				request.setAttribute("noResultados", "No");
////				session.setAttribute(FULL_LIST, listaOfertas); //paginacion
////			}
////			
////			
////			this.PAGE_NUM_ROW = 5;
////		    this.PAGE_JUMP_SIZE = 5;
////		    
////		    request.setAttribute(BOLSAS_EXTERNAS, listaOfertas);
////			
////			session.removeAttribute(NUM_PAGE_LIST);
////		    session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
////		    
////		    session.removeAttribute(NUM_PAGE_LIST);
////		    session.setAttribute("tituloEmpresa", "Empresa");
////			
////		}catch(Exception e){
////			e.printStackTrace();
////		}
////		
////		session.setAttribute(BODY_JSP, mapping.findForward(JSP_RESULT).getPath());
////        PropertiesLoader properties = PropertiesLoader.getInstance();
////		session.setAttribute(TITULO_PAGINA, "Empleos en la Riviera Maya");
////		session.setAttribute(DESCRIPCION_PAGINA, "Empleos en la Riviera Maya, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
////		session.setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
////		session.setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
////		session.setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
////		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
////	}
////	
////	public ActionForward ordenaPor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
////		HttpSession session = request.getSession();
////
////		int campo = Utils.parseInt(request.getParameter("campo"));
////		int orden = Utils.parseInt(request.getParameter("orden"));
////
////		ordenarPor(session, campo, orden);
////
////		return super.page(mapping, form, request, response);
////	}
////	
////	@SuppressWarnings("unchecked")
////	private void ordenarPor(HttpSession session, int campo, final int orden){
////		try{
////			List<OfertaRivieraMayaVO> ofertas = (List<OfertaRivieraMayaVO>)session.getAttribute(FULL_LIST);
////
////			if (ofertas==null || ofertas.isEmpty()) return;
////			
////			Comparator<OfertaRivieraMayaVO> comparator = null;
////			
////			switch(campo){
////				case CAMPO_TITULO:
////					comparator = new Comparator<OfertaRivieraMayaVO>(){
////						@Override
////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
////		                	String valor01 = o1.getNombrepuesto();
////		                	String valor02 = o2.getNombrepuesto();
////
////		                	if (valor01==null && valor02==null) return 0;
////		                	if (valor01==null && valor02!=null) return -1;
////		                	if (valor01!=null && valor02==null) return 1;
////		                	
////		                	valor01 = valor01.trim().toUpperCase();
////		                	valor02 = valor02.trim().toUpperCase();
////
////		                	int compare = 0;
////		                	switch(orden){
////		                	case ORDEN_ASC:
////		                		compare = valor01.compareTo(valor02);
////		                		break;
////		                	case ORDEN_DESC:
////		                		compare = valor02.compareTo(valor01);
////		                		break;
////		                	default:
////		                		compare = valor01.compareTo(valor02);
////		                	}
////		                	return compare;
////		                }
////					};
////					break;
////				case CAMPO_UBICACION:
////					comparator = new Comparator<OfertaRivieraMayaVO>(){
////						@Override
////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
////		                	String valor01 = o1.getUbicacion();
////		                	String valor02 = o2.getUbicacion();
////
////		                	if (valor01==null && valor02==null) return 0;
////		                	if (valor01==null && valor02!=null) return -1;
////		                	if (valor01!=null && valor02==null) return 1;
////		                	
////		                	valor01 = valor01.trim().toUpperCase();
////		                	valor02 = valor02.trim().toUpperCase();
////
////		                	int compare = 0;
////		                	switch(orden){
////		                	case ORDEN_ASC:
////		                		compare = valor01.compareTo(valor02);
////		                		break;
////		                	case ORDEN_DESC:
////		                		compare = valor02.compareTo(valor01);
////		                		break;
////		                	default:
////		                		compare = valor01.compareTo(valor02);
////		                	}
////		                	return compare;
////		                }
////					};
////					break;
////				case CAMPO_EMPRESA:
////					comparator = new Comparator<OfertaRivieraMayaVO>(){
////						@Override
////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
////		                	String valor01 = o1.getRazonSocial();
////		                	String valor02 = o2.getRazonSocial();
////
////		                	if (valor01==null && valor02==null) return 0;
////		                	if (valor01==null && valor02!=null) return -1;
////		                	if (valor01!=null && valor02==null) return 1;
////		                	
////		                	valor01 = valor01.trim().toUpperCase();
////		                	valor02 = valor02.trim().toUpperCase();
////
////		                	int compare = 0;
////		                	switch(orden){
////		                	case ORDEN_ASC:
////		                		compare = valor01.compareTo(valor02);
////		                		break;
////		                	case ORDEN_DESC:
////		                		compare = valor02.compareTo(valor01);
////		                		break;
////		                	default:
////		                		compare = valor01.compareTo(valor02);
////		                	}
////		                	return compare;
////		                }
////					};
////					break;
////				case CAMPO_FECHA:
////					comparator = new Comparator<OfertaRivieraMayaVO>(){
////						@Override
////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
////		                	String valor01 = o1.getGradoescolaridad();
////		                	String valor02 = o2.getGradoescolaridad();
////
////		                	if (valor01==null && valor02==null) return 0;
////		                	if (valor01==null && valor02!=null) return -1;
////		                	if (valor01!=null && valor02==null) return 1;
////		                	
////		                	valor01 = valor01.trim().toUpperCase();
////		                	valor02 = valor02.trim().toUpperCase();
////
////		                	int compare = 0;
////		                	switch(orden){
////		                	case ORDEN_ASC:
////		                		compare = valor01.compareTo(valor02);
////		                		break;
////		                	case ORDEN_DESC:
////		                		compare = valor02.compareTo(valor01);
////		                		break;
////		                	default:
////		                		compare = valor01.compareTo(valor02);
////		                	}
////		                	return compare;
////		                }
////					};
////					break;
////			}
////
////			if (comparator==null) return;
////
////	    	Collections.sort(ofertas, comparator);
////
////	    	session.setAttribute(FULL_LIST, ofertas);
////
////		}catch(Exception e){
////			e.printStackTrace();
////		}
////	}
////
////}
//
//package mx.gob.stps.portal.web.oferta.action;
//
//import mx.gob.stps.portal.web.infra.action.PagerAction;
//
//import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
//
//import java.lang.String;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
//import mx.gob.stps.portal.web.infra.action.PagerAction;
//import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
//import mx.gob.stps.portal.web.infra.utils.Utils;
//import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegate;
//import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegateImpl;
//
//public class OfertaRivieraMayaAction extends PagerAction {
//	
//	protected static final String BOLSAS_EXTERNAS = "LISTABOLSASEXTERNAS";
//	private static final String JSP_RESULT = "JSP_RESULT";
//	private boolean existenOfertas;
//	private static final int ORDEN_ASC 			= 1;
//	private static final int ORDEN_DESC			= 2;
//	private static final int CAMPO_TITULO 		= 1;
//	private static final int CAMPO_UBICACION 	= 2;
//	private static final int CAMPO_EMPRESA 		= 3;
//	private static final int CAMPO_FECHA 		= 4;
//	
//	
//
//	@Override
//	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		
//		 List<OfertaRivieraMayaVO> listaOfertas = null;
//		try{
//			 session.removeAttribute(FULL_LIST);
//			 session.removeAttribute(PAGE_LIST);
//			
//			OfertasRivieraMayaDelegate service = OfertasRivieraMayaDelegateImpl.getInstance();
//			
//			listaOfertas =  service.obtenerOfertasRiveraMaya();
//			
//			if(listaOfertas == null)
//				listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
//			
//			if(listaOfertas.size() == 1){
//				if(listaOfertas.get(listaOfertas.size() - 1).getNombrepuesto().equals("No se encontraron datos")){
//					listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
//				}
//			}
//			if(listaOfertas.isEmpty()){
//				
//				//String existenResultados = "No se encontraron datos";
//				
//				this.existenOfertas = false;
//				session.setAttribute("existenOfertas", existenOfertas);
//				request.setAttribute("noResultados", "No");
//				
//				
//		
//			}else{
//				this.existenOfertas = true;
//				session.setAttribute("existenOfertas", existenOfertas);
//				request.setAttribute("noResultados", "No");
//				session.setAttribute(FULL_LIST, listaOfertas); //paginacion
//			}
//			
//			
//			this.PAGE_NUM_ROW = 5;
//		    this.PAGE_JUMP_SIZE = 5;
//		    
//		    request.setAttribute(BOLSAS_EXTERNAS, listaOfertas);
//			
//			session.removeAttribute(NUM_PAGE_LIST);
//		    session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
//		    
//		    session.removeAttribute(NUM_PAGE_LIST);
//		    session.setAttribute("tituloEmpresa", "Empresa");
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		session.setAttribute(BODY_JSP, mapping.findForward(JSP_RESULT).getPath());
//        PropertiesLoader properties = PropertiesLoader.getInstance();
//		session.setAttribute(TITULO_PAGINA, "Empleos en la Riviera Maya");
//		session.setAttribute(DESCRIPCION_PAGINA, "Empleos en la Riviera Maya, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
//		session.setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
//		session.setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
//		session.setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
//		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
//	}
//	
//	public ActionForward ordenaPor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
//		HttpSession session = request.getSession();
//
//		int campo = Utils.parseInt(request.getParameter("campo"));
//		int orden = Utils.parseInt(request.getParameter("orden"));
//
//		ordenarPor(session, campo, orden);
//
//		return super.page(mapping, form, request, response);
//	}
//	
//	
//	public ActionForward FiltraOfertas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
//		HttpSession session = request.getSession();
//		List<OfertaRivieraMayaVO> listaOfertas = null;
//		String texto = String.valueOf(request.getParameter("texto"));
//		String edo = String.valueOf(request.getParameter("edo"));
//	
//		try {
//			
//			session.removeAttribute(FULL_LIST);
//			 session.removeAttribute(PAGE_LIST);
//			
//			OfertasRivieraMayaDelegate service = OfertasRivieraMayaDelegateImpl.getInstance();
//			
//			listaOfertas =  service.obtenerOfertasSectur(Integer.parseInt(edo),texto);
//			
//			if(listaOfertas == null)
//				listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
//			
//			if(listaOfertas.size() == 1){
//				if(listaOfertas.get(listaOfertas.size() - 1).getNombrepuesto().equals("No se encontraron datos")){
//					listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
//				}
//			}
//			if(listaOfertas.isEmpty()){
//				
//				//String existenResultados = "No se encontraron datos";
//				
//				this.existenOfertas = false;
//				session.setAttribute("existenOfertas", existenOfertas);
//				request.setAttribute("noResultados", "No");
//				
//				
//		
//			}else{
//				this.existenOfertas = true;
//				session.setAttribute("existenOfertas", existenOfertas);
//				request.setAttribute("noResultados", "No");
//				session.setAttribute(FULL_LIST, listaOfertas); //paginacion
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return super.page(mapping, form, request, response);
//	}
//	
//	@SuppressWarnings("unchecked")
//	private void ordenarPor(HttpSession session, int campo, final int orden){
//		try{
//			List<OfertaRivieraMayaVO> ofertas = (List<OfertaRivieraMayaVO>)session.getAttribute(FULL_LIST);
//
//			if (ofertas==null || ofertas.isEmpty()) return;
//			
//			Comparator<OfertaRivieraMayaVO> comparator = null;
//			
//			switch(campo){
//				case CAMPO_TITULO:
//					comparator = new Comparator<OfertaRivieraMayaVO>(){
//						@Override
//		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//		                	String valor01 = o1.getNombrepuesto();
//		                	String valor02 = o2.getNombrepuesto();
//
//		                	if (valor01==null && valor02==null) return 0;
//		                	if (valor01==null && valor02!=null) return -1;
//		                	if (valor01!=null && valor02==null) return 1;
//		                	
//		                	valor01 = valor01.trim().toUpperCase();
//		                	valor02 = valor02.trim().toUpperCase();
//
//		                	int compare = 0;
//		                	switch(orden){
//		                	case ORDEN_ASC:
//		                		compare = valor01.compareTo(valor02);
//		                		break;
//		                	case ORDEN_DESC:
//		                		compare = valor02.compareTo(valor01);
//		                		break;
//		                	default:
//		                		compare = valor01.compareTo(valor02);
//		                	}
//		                	return compare;
//		                }
//					};
//					break;
//				case CAMPO_UBICACION:
//					comparator = new Comparator<OfertaRivieraMayaVO>(){
//						@Override
//		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//		                	String valor01 = o1.getUbicacion();
//		                	String valor02 = o2.getUbicacion();
//
//		                	if (valor01==null && valor02==null) return 0;
//		                	if (valor01==null && valor02!=null) return -1;
//		                	if (valor01!=null && valor02==null) return 1;
//		                	
//		                	valor01 = valor01.trim().toUpperCase();
//		                	valor02 = valor02.trim().toUpperCase();
//
//		                	int compare = 0;
//		                	switch(orden){
//		                	case ORDEN_ASC:
//		                		compare = valor01.compareTo(valor02);
//		                		break;
//		                	case ORDEN_DESC:
//		                		compare = valor02.compareTo(valor01);
//		                		break;
//		                	default:
//		                		compare = valor01.compareTo(valor02);
//		                	}
//		                	return compare;
//		                }
//					};
//					break;
//				case CAMPO_EMPRESA:
//					comparator = new Comparator<OfertaRivieraMayaVO>(){
//						@Override
//		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//		                	String valor01 = o1.getRazonSocial();
//		                	String valor02 = o2.getRazonSocial();
//
//		                	if (valor01==null && valor02==null) return 0;
//		                	if (valor01==null && valor02!=null) return -1;
//		                	if (valor01!=null && valor02==null) return 1;
//		                	
//		                	valor01 = valor01.trim().toUpperCase();
//		                	valor02 = valor02.trim().toUpperCase();
//
//		                	int compare = 0;
//		                	switch(orden){
//		                	case ORDEN_ASC:
//		                		compare = valor01.compareTo(valor02);
//		                		break;
//		                	case ORDEN_DESC:
//		                		compare = valor02.compareTo(valor01);
//		                		break;
//		                	default:
//		                		compare = valor01.compareTo(valor02);
//		                	}
//		                	return compare;
//		                }
//					};
//					break;
//				case CAMPO_FECHA:
//					comparator = new Comparator<OfertaRivieraMayaVO>(){
//						@Override
//		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//		                	String valor01 = o1.getGradoescolaridad();
//		                	String valor02 = o2.getGradoescolaridad();
//
//		                	if (valor01==null && valor02==null) return 0;
//		                	if (valor01==null && valor02!=null) return -1;
//		                	if (valor01!=null && valor02==null) return 1;
//		                	
//		                	valor01 = valor01.trim().toUpperCase();
//		                	valor02 = valor02.trim().toUpperCase();
//
//		                	int compare = 0;
//		                	switch(orden){
//		                	case ORDEN_ASC:
//		                		compare = valor01.compareTo(valor02);
//		                		break;
//		                	case ORDEN_DESC:
//		                		compare = valor02.compareTo(valor01);
//		                		break;
//		                	default:
//		                		compare = valor01.compareTo(valor02);
//		                	}
//		                	return compare;
//		                }
//					};
//					break;
//			}
//
//			if (comparator==null) return;
//
//	    	Collections.sort(ofertas, comparator);
//
//	    	session.setAttribute(FULL_LIST, ofertas);
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//}
//


package mx.gob.stps.portal.web.oferta.action;

import mx.gob.stps.portal.web.infra.action.PagerAction;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegate;
import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegateImpl;

public class OfertaRivieraMayaAction extends PagerAction {
	
	protected static final String BOLSAS_EXTERNAS = "LISTABOLSASEXTERNAS";
	private static final String JSP_RESULT = "JSP_RESULT";
	private boolean existenOfertas;
	private static final int ORDEN_ASC 			= 1;
	private static final int ORDEN_DESC			= 2;
	private static final int CAMPO_TITULO 		= 1;
	private static final int CAMPO_UBICACION 	= 2;
	private static final int CAMPO_EMPRESA 		= 3;
	private static final int CAMPO_FECHA 		= 4;
	
	

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		 List<OfertaRivieraMayaVO> listaOfertas = null;
		try{
			 session.removeAttribute(FULL_LIST);
			 session.removeAttribute(PAGE_LIST);
			
			OfertasRivieraMayaDelegate service = OfertasRivieraMayaDelegateImpl.getInstance();
			
			listaOfertas =  service.obtenerOfertasRiveraMaya();
			
			if(listaOfertas == null)
				listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
			
			if(listaOfertas.size() == 1){
				if(listaOfertas.get(listaOfertas.size() - 1).getNombrepuesto().equals("No se encontraron datos")){
					listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
				}
			}
			if(listaOfertas.isEmpty()){
				
				//String existenResultados = "No se encontraron datos";
				
				this.existenOfertas = false;
				session.setAttribute("existenOfertas", existenOfertas);
				request.setAttribute("noResultados", "No");
				
				
		
			}else{
				this.existenOfertas = true;
				session.setAttribute("existenOfertas", existenOfertas);
				request.setAttribute("noResultados", "No");
				session.setAttribute(FULL_LIST, listaOfertas); //paginacion
			}
			
			
			this.PAGE_NUM_ROW = 5;
		    this.PAGE_JUMP_SIZE = 5;
		    
		    request.setAttribute(BOLSAS_EXTERNAS, listaOfertas);
			
			session.removeAttribute(NUM_PAGE_LIST);
		    session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
		    
		    session.removeAttribute(NUM_PAGE_LIST);
		    session.setAttribute("tituloEmpresa", "Empresa");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		session.setAttribute(BODY_JSP, mapping.findForward(JSP_RESULT).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		session.setAttribute(TITULO_PAGINA, "Tu trabajo hace al turismo - Vacantes en el sector turístico");
		session.setAttribute(DESCRIPCION_PAGINA, "Tu trabajo hace al turismo - Vacantes en el sector turístico, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		session.setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		session.setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		session.setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
	}
	
	public ActionForward ordenaPor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		HttpSession session = request.getSession();

		int campo = Utils.parseInt(request.getParameter("campo"));
		int orden = Utils.parseInt(request.getParameter("orden"));

		ordenarPor(session, campo, orden);

		return super.page(mapping, form, request, response);
	}
	
	
	public ActionForward FiltraOfertas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		HttpSession session = request.getSession();
		List<OfertaRivieraMayaVO> listaOfertas = null;
		String busqueda = String.valueOf(request.getParameter("busqueda"));
		String edo = String.valueOf(request.getParameter("edo"));
	
		try {
			
			session.removeAttribute(FULL_LIST);
			 session.removeAttribute(PAGE_LIST);
			
			OfertasRivieraMayaDelegate service = OfertasRivieraMayaDelegateImpl.getInstance();
			
			listaOfertas =  service.obtenerOfertasSectur(Integer.parseInt(edo),busqueda);
			
			if(listaOfertas == null)
				listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
			
			if(listaOfertas.size() == 1){
				if(listaOfertas.get(listaOfertas.size() - 1).getNombrepuesto().equals("No se encontraron datos")){
					listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
				}
			}
			if(listaOfertas.isEmpty()){
				
				//String existenResultados = "No se encontraron datos";
				
				this.existenOfertas = false;
				session.setAttribute("existenOfertas", existenOfertas);
				request.setAttribute("noResultados", "No");
				
				
		
			}else{
				this.existenOfertas = true;
				session.setAttribute("existenOfertas", existenOfertas);
				request.setAttribute("noResultados", "No");
				session.setAttribute(FULL_LIST, listaOfertas); //paginacion
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.page(mapping, form, request, response);
	}
	
	@SuppressWarnings("unchecked")
	private void ordenarPor(HttpSession session, int campo, final int orden){
		try{
			List<OfertaRivieraMayaVO> ofertas = (List<OfertaRivieraMayaVO>)session.getAttribute(FULL_LIST);

			if (ofertas==null || ofertas.isEmpty()) return;
			
			Comparator<OfertaRivieraMayaVO> comparator = null;
			
			switch(campo){
				case CAMPO_TITULO:
					comparator = new Comparator<OfertaRivieraMayaVO>(){
						@Override
		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
		                	String valor01 = o1.getNombrepuesto();
		                	String valor02 = o2.getNombrepuesto();

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
					comparator = new Comparator<OfertaRivieraMayaVO>(){
						@Override
		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
		                	String valor01 = o1.getUbicacion();
		                	String valor02 = o2.getUbicacion();

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
					comparator = new Comparator<OfertaRivieraMayaVO>(){
						@Override
		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
		                	String valor01 = o1.getRazonSocial();
		                	String valor02 = o2.getRazonSocial();

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
					comparator = new Comparator<OfertaRivieraMayaVO>(){
						@Override
		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
		                	String valor01 = o1.getGradoescolaridad();
		                	String valor02 = o2.getGradoescolaridad();

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

}


//////package mx.gob.stps.portal.web.oferta.action;
//////
//////import mx.gob.stps.portal.web.infra.action.PagerAction;
//////
//////import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
//////import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
//////import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
//////import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
//////import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
//////import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
//////import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
//////
//////import java.util.ArrayList;
//////import java.util.Collections;
//////import java.util.Comparator;
//////import java.util.List;
//////import javax.servlet.http.HttpServletRequest;
//////import javax.servlet.http.HttpServletResponse;
//////import javax.servlet.http.HttpSession;
//////import org.apache.struts.action.ActionForm;
//////import org.apache.struts.action.ActionForward;
//////import org.apache.struts.action.ActionMapping;
//////import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
//////import mx.gob.stps.portal.web.infra.action.PagerAction;
//////import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
//////import mx.gob.stps.portal.web.infra.utils.Utils;
//////import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegate;
//////import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegateImpl;
//////
//////public class OfertaRivieraMayaAction extends PagerAction {
//////	
//////	protected static final String BOLSAS_EXTERNAS = "LISTABOLSASEXTERNAS";
//////	private static final String JSP_RESULT = "JSP_RESULT";
//////	private boolean existenOfertas;
//////	private static final int ORDEN_ASC 			= 1;
//////	private static final int ORDEN_DESC			= 2;
//////	private static final int CAMPO_TITULO 		= 1;
//////	private static final int CAMPO_UBICACION 	= 2;
//////	private static final int CAMPO_EMPRESA 		= 3;
//////	private static final int CAMPO_FECHA 		= 4;
//////
//////	@Override
//////	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//////		HttpSession session = request.getSession();
//////		
//////		 List<OfertaRivieraMayaVO> listaOfertas = null;
//////		try{
//////			 session.removeAttribute(FULL_LIST);
//////			 session.removeAttribute(PAGE_LIST);
//////			
//////			OfertasRivieraMayaDelegate service = OfertasRivieraMayaDelegateImpl.getInstance();
//////			
//////			listaOfertas =  service.obtenerOfertasRiveraMaya();
//////			
//////			if(listaOfertas == null)
//////				listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
//////			
//////			if(listaOfertas.size() == 1){
//////				if(listaOfertas.get(listaOfertas.size() - 1).getNombrepuesto().equals("No se encontraron datos")){
//////					listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
//////				}
//////			}
//////			if(listaOfertas.isEmpty()){
//////				
//////				//String existenResultados = "No se encontraron datos";
//////				
//////				this.existenOfertas = false;
//////				session.setAttribute("existenOfertas", existenOfertas);
//////				request.setAttribute("noResultados", "No");
//////				
//////				
//////		
//////			}else{
//////				this.existenOfertas = true;
//////				session.setAttribute("existenOfertas", existenOfertas);
//////				request.setAttribute("noResultados", "No");
//////				session.setAttribute(FULL_LIST, listaOfertas); //paginacion
//////			}
//////			
//////			
//////			this.PAGE_NUM_ROW = 5;
//////		    this.PAGE_JUMP_SIZE = 5;
//////		    
//////		    request.setAttribute(BOLSAS_EXTERNAS, listaOfertas);
//////			
//////			session.removeAttribute(NUM_PAGE_LIST);
//////		    session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
//////		    
//////		    session.removeAttribute(NUM_PAGE_LIST);
//////		    session.setAttribute("tituloEmpresa", "Empresa");
//////			
//////		}catch(Exception e){
//////			e.printStackTrace();
//////		}
//////		
//////		session.setAttribute(BODY_JSP, mapping.findForward(JSP_RESULT).getPath());
//////        PropertiesLoader properties = PropertiesLoader.getInstance();
//////		session.setAttribute(TITULO_PAGINA, "Empleos en la Riviera Maya");
//////		session.setAttribute(DESCRIPCION_PAGINA, "Empleos en la Riviera Maya, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
//////		session.setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
//////		session.setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
//////		session.setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
//////		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
//////	}
//////	
//////	public ActionForward ordenaPor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
//////		HttpSession session = request.getSession();
//////
//////		int campo = Utils.parseInt(request.getParameter("campo"));
//////		int orden = Utils.parseInt(request.getParameter("orden"));
//////
//////		ordenarPor(session, campo, orden);
//////
//////		return super.page(mapping, form, request, response);
//////	}
//////	
//////	@SuppressWarnings("unchecked")
//////	private void ordenarPor(HttpSession session, int campo, final int orden){
//////		try{
//////			List<OfertaRivieraMayaVO> ofertas = (List<OfertaRivieraMayaVO>)session.getAttribute(FULL_LIST);
//////
//////			if (ofertas==null || ofertas.isEmpty()) return;
//////			
//////			Comparator<OfertaRivieraMayaVO> comparator = null;
//////			
//////			switch(campo){
//////				case CAMPO_TITULO:
//////					comparator = new Comparator<OfertaRivieraMayaVO>(){
//////						@Override
//////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//////		                	String valor01 = o1.getNombrepuesto();
//////		                	String valor02 = o2.getNombrepuesto();
//////
//////		                	if (valor01==null && valor02==null) return 0;
//////		                	if (valor01==null && valor02!=null) return -1;
//////		                	if (valor01!=null && valor02==null) return 1;
//////		                	
//////		                	valor01 = valor01.trim().toUpperCase();
//////		                	valor02 = valor02.trim().toUpperCase();
//////
//////		                	int compare = 0;
//////		                	switch(orden){
//////		                	case ORDEN_ASC:
//////		                		compare = valor01.compareTo(valor02);
//////		                		break;
//////		                	case ORDEN_DESC:
//////		                		compare = valor02.compareTo(valor01);
//////		                		break;
//////		                	default:
//////		                		compare = valor01.compareTo(valor02);
//////		                	}
//////		                	return compare;
//////		                }
//////					};
//////					break;
//////				case CAMPO_UBICACION:
//////					comparator = new Comparator<OfertaRivieraMayaVO>(){
//////						@Override
//////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//////		                	String valor01 = o1.getUbicacion();
//////		                	String valor02 = o2.getUbicacion();
//////
//////		                	if (valor01==null && valor02==null) return 0;
//////		                	if (valor01==null && valor02!=null) return -1;
//////		                	if (valor01!=null && valor02==null) return 1;
//////		                	
//////		                	valor01 = valor01.trim().toUpperCase();
//////		                	valor02 = valor02.trim().toUpperCase();
//////
//////		                	int compare = 0;
//////		                	switch(orden){
//////		                	case ORDEN_ASC:
//////		                		compare = valor01.compareTo(valor02);
//////		                		break;
//////		                	case ORDEN_DESC:
//////		                		compare = valor02.compareTo(valor01);
//////		                		break;
//////		                	default:
//////		                		compare = valor01.compareTo(valor02);
//////		                	}
//////		                	return compare;
//////		                }
//////					};
//////					break;
//////				case CAMPO_EMPRESA:
//////					comparator = new Comparator<OfertaRivieraMayaVO>(){
//////						@Override
//////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//////		                	String valor01 = o1.getRazonSocial();
//////		                	String valor02 = o2.getRazonSocial();
//////
//////		                	if (valor01==null && valor02==null) return 0;
//////		                	if (valor01==null && valor02!=null) return -1;
//////		                	if (valor01!=null && valor02==null) return 1;
//////		                	
//////		                	valor01 = valor01.trim().toUpperCase();
//////		                	valor02 = valor02.trim().toUpperCase();
//////
//////		                	int compare = 0;
//////		                	switch(orden){
//////		                	case ORDEN_ASC:
//////		                		compare = valor01.compareTo(valor02);
//////		                		break;
//////		                	case ORDEN_DESC:
//////		                		compare = valor02.compareTo(valor01);
//////		                		break;
//////		                	default:
//////		                		compare = valor01.compareTo(valor02);
//////		                	}
//////		                	return compare;
//////		                }
//////					};
//////					break;
//////				case CAMPO_FECHA:
//////					comparator = new Comparator<OfertaRivieraMayaVO>(){
//////						@Override
//////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//////		                	String valor01 = o1.getGradoescolaridad();
//////		                	String valor02 = o2.getGradoescolaridad();
//////
//////		                	if (valor01==null && valor02==null) return 0;
//////		                	if (valor01==null && valor02!=null) return -1;
//////		                	if (valor01!=null && valor02==null) return 1;
//////		                	
//////		                	valor01 = valor01.trim().toUpperCase();
//////		                	valor02 = valor02.trim().toUpperCase();
//////
//////		                	int compare = 0;
//////		                	switch(orden){
//////		                	case ORDEN_ASC:
//////		                		compare = valor01.compareTo(valor02);
//////		                		break;
//////		                	case ORDEN_DESC:
//////		                		compare = valor02.compareTo(valor01);
//////		                		break;
//////		                	default:
//////		                		compare = valor01.compareTo(valor02);
//////		                	}
//////		                	return compare;
//////		                }
//////					};
//////					break;
//////			}
//////
//////			if (comparator==null) return;
//////
//////	    	Collections.sort(ofertas, comparator);
//////
//////	    	session.setAttribute(FULL_LIST, ofertas);
//////
//////		}catch(Exception e){
//////			e.printStackTrace();
//////		}
//////	}
//////
//////}
////
////package mx.gob.stps.portal.web.oferta.action;
////
////import mx.gob.stps.portal.web.infra.action.PagerAction;
////
////import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
////import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
////
////import java.lang.String;
////import java.util.ArrayList;
////import java.util.Collections;
////import java.util.Comparator;
////import java.util.List;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import javax.servlet.http.HttpSession;
////import org.apache.struts.action.ActionForm;
////import org.apache.struts.action.ActionForward;
////import org.apache.struts.action.ActionMapping;
////import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
////import mx.gob.stps.portal.web.infra.action.PagerAction;
////import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
////import mx.gob.stps.portal.web.infra.utils.Utils;
////import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegate;
////import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegateImpl;
////
////public class OfertaRivieraMayaAction extends PagerAction {
////	
////	protected static final String BOLSAS_EXTERNAS = "LISTABOLSASEXTERNAS";
////	private static final String JSP_RESULT = "JSP_RESULT";
////	private boolean existenOfertas;
////	private static final int ORDEN_ASC 			= 1;
////	private static final int ORDEN_DESC			= 2;
////	private static final int CAMPO_TITULO 		= 1;
////	private static final int CAMPO_UBICACION 	= 2;
////	private static final int CAMPO_EMPRESA 		= 3;
////	private static final int CAMPO_FECHA 		= 4;
////	
////	
////
////	@Override
////	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
////		HttpSession session = request.getSession();
////		
////		 List<OfertaRivieraMayaVO> listaOfertas = null;
////		try{
////			 session.removeAttribute(FULL_LIST);
////			 session.removeAttribute(PAGE_LIST);
////			
////			OfertasRivieraMayaDelegate service = OfertasRivieraMayaDelegateImpl.getInstance();
////			
////			listaOfertas =  service.obtenerOfertasRiveraMaya();
////			
////			if(listaOfertas == null)
////				listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
////			
////			if(listaOfertas.size() == 1){
////				if(listaOfertas.get(listaOfertas.size() - 1).getNombrepuesto().equals("No se encontraron datos")){
////					listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
////				}
////			}
////			if(listaOfertas.isEmpty()){
////				
////				//String existenResultados = "No se encontraron datos";
////				
////				this.existenOfertas = false;
////				session.setAttribute("existenOfertas", existenOfertas);
////				request.setAttribute("noResultados", "No");
////				
////				
////		
////			}else{
////				this.existenOfertas = true;
////				session.setAttribute("existenOfertas", existenOfertas);
////				request.setAttribute("noResultados", "No");
////				session.setAttribute(FULL_LIST, listaOfertas); //paginacion
////			}
////			
////			
////			this.PAGE_NUM_ROW = 5;
////		    this.PAGE_JUMP_SIZE = 5;
////		    
////		    request.setAttribute(BOLSAS_EXTERNAS, listaOfertas);
////			
////			session.removeAttribute(NUM_PAGE_LIST);
////		    session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
////		    
////		    session.removeAttribute(NUM_PAGE_LIST);
////		    session.setAttribute("tituloEmpresa", "Empresa");
////			
////		}catch(Exception e){
////			e.printStackTrace();
////		}
////		
////		session.setAttribute(BODY_JSP, mapping.findForward(JSP_RESULT).getPath());
////        PropertiesLoader properties = PropertiesLoader.getInstance();
////		session.setAttribute(TITULO_PAGINA, "Empleos en la Riviera Maya");
////		session.setAttribute(DESCRIPCION_PAGINA, "Empleos en la Riviera Maya, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
////		session.setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
////		session.setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
////		session.setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
////		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
////	}
////	
////	public ActionForward ordenaPor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
////		HttpSession session = request.getSession();
////
////		int campo = Utils.parseInt(request.getParameter("campo"));
////		int orden = Utils.parseInt(request.getParameter("orden"));
////
////		ordenarPor(session, campo, orden);
////
////		return super.page(mapping, form, request, response);
////	}
////	
////	
////	public ActionForward FiltraOfertas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
////		HttpSession session = request.getSession();
////		List<OfertaRivieraMayaVO> listaOfertas = null;
////		String texto = String.valueOf(request.getParameter("texto"));
////		String edo = String.valueOf(request.getParameter("edo"));
////	
////		try {
////			
////			session.removeAttribute(FULL_LIST);
////			 session.removeAttribute(PAGE_LIST);
////			
////			OfertasRivieraMayaDelegate service = OfertasRivieraMayaDelegateImpl.getInstance();
////			
////			listaOfertas =  service.obtenerOfertasSectur(Integer.parseInt(edo),texto);
////			
////			if(listaOfertas == null)
////				listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
////			
////			if(listaOfertas.size() == 1){
////				if(listaOfertas.get(listaOfertas.size() - 1).getNombrepuesto().equals("No se encontraron datos")){
////					listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
////				}
////			}
////			if(listaOfertas.isEmpty()){
////				
////				//String existenResultados = "No se encontraron datos";
////				
////				this.existenOfertas = false;
////				session.setAttribute("existenOfertas", existenOfertas);
////				request.setAttribute("noResultados", "No");
////				
////				
////		
////			}else{
////				this.existenOfertas = true;
////				session.setAttribute("existenOfertas", existenOfertas);
////				request.setAttribute("noResultados", "No");
////				session.setAttribute(FULL_LIST, listaOfertas); //paginacion
////			}
////			
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		return super.page(mapping, form, request, response);
////	}
////	
////	@SuppressWarnings("unchecked")
////	private void ordenarPor(HttpSession session, int campo, final int orden){
////		try{
////			List<OfertaRivieraMayaVO> ofertas = (List<OfertaRivieraMayaVO>)session.getAttribute(FULL_LIST);
////
////			if (ofertas==null || ofertas.isEmpty()) return;
////			
////			Comparator<OfertaRivieraMayaVO> comparator = null;
////			
////			switch(campo){
////				case CAMPO_TITULO:
////					comparator = new Comparator<OfertaRivieraMayaVO>(){
////						@Override
////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
////		                	String valor01 = o1.getNombrepuesto();
////		                	String valor02 = o2.getNombrepuesto();
////
////		                	if (valor01==null && valor02==null) return 0;
////		                	if (valor01==null && valor02!=null) return -1;
////		                	if (valor01!=null && valor02==null) return 1;
////		                	
////		                	valor01 = valor01.trim().toUpperCase();
////		                	valor02 = valor02.trim().toUpperCase();
////
////		                	int compare = 0;
////		                	switch(orden){
////		                	case ORDEN_ASC:
////		                		compare = valor01.compareTo(valor02);
////		                		break;
////		                	case ORDEN_DESC:
////		                		compare = valor02.compareTo(valor01);
////		                		break;
////		                	default:
////		                		compare = valor01.compareTo(valor02);
////		                	}
////		                	return compare;
////		                }
////					};
////					break;
////				case CAMPO_UBICACION:
////					comparator = new Comparator<OfertaRivieraMayaVO>(){
////						@Override
////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
////		                	String valor01 = o1.getUbicacion();
////		                	String valor02 = o2.getUbicacion();
////
////		                	if (valor01==null && valor02==null) return 0;
////		                	if (valor01==null && valor02!=null) return -1;
////		                	if (valor01!=null && valor02==null) return 1;
////		                	
////		                	valor01 = valor01.trim().toUpperCase();
////		                	valor02 = valor02.trim().toUpperCase();
////
////		                	int compare = 0;
////		                	switch(orden){
////		                	case ORDEN_ASC:
////		                		compare = valor01.compareTo(valor02);
////		                		break;
////		                	case ORDEN_DESC:
////		                		compare = valor02.compareTo(valor01);
////		                		break;
////		                	default:
////		                		compare = valor01.compareTo(valor02);
////		                	}
////		                	return compare;
////		                }
////					};
////					break;
////				case CAMPO_EMPRESA:
////					comparator = new Comparator<OfertaRivieraMayaVO>(){
////						@Override
////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
////		                	String valor01 = o1.getRazonSocial();
////		                	String valor02 = o2.getRazonSocial();
////
////		                	if (valor01==null && valor02==null) return 0;
////		                	if (valor01==null && valor02!=null) return -1;
////		                	if (valor01!=null && valor02==null) return 1;
////		                	
////		                	valor01 = valor01.trim().toUpperCase();
////		                	valor02 = valor02.trim().toUpperCase();
////
////		                	int compare = 0;
////		                	switch(orden){
////		                	case ORDEN_ASC:
////		                		compare = valor01.compareTo(valor02);
////		                		break;
////		                	case ORDEN_DESC:
////		                		compare = valor02.compareTo(valor01);
////		                		break;
////		                	default:
////		                		compare = valor01.compareTo(valor02);
////		                	}
////		                	return compare;
////		                }
////					};
////					break;
////				case CAMPO_FECHA:
////					comparator = new Comparator<OfertaRivieraMayaVO>(){
////						@Override
////		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
////		                	String valor01 = o1.getGradoescolaridad();
////		                	String valor02 = o2.getGradoescolaridad();
////
////		                	if (valor01==null && valor02==null) return 0;
////		                	if (valor01==null && valor02!=null) return -1;
////		                	if (valor01!=null && valor02==null) return 1;
////		                	
////		                	valor01 = valor01.trim().toUpperCase();
////		                	valor02 = valor02.trim().toUpperCase();
////
////		                	int compare = 0;
////		                	switch(orden){
////		                	case ORDEN_ASC:
////		                		compare = valor01.compareTo(valor02);
////		                		break;
////		                	case ORDEN_DESC:
////		                		compare = valor02.compareTo(valor01);
////		                		break;
////		                	default:
////		                		compare = valor01.compareTo(valor02);
////		                	}
////		                	return compare;
////		                }
////					};
////					break;
////			}
////
////			if (comparator==null) return;
////
////	    	Collections.sort(ofertas, comparator);
////
////	    	session.setAttribute(FULL_LIST, ofertas);
////
////		}catch(Exception e){
////			e.printStackTrace();
////		}
////	}
////
////}
////
//
//
//package mx.gob.stps.portal.web.oferta.action;
//
//import mx.gob.stps.portal.web.infra.action.PagerAction;
//
//import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_RESPONSIVE;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
//import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;
//
//import java.lang.String;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionForward;
//import org.apache.struts.action.ActionMapping;
//import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
//import mx.gob.stps.portal.web.infra.action.PagerAction;
//import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
//import mx.gob.stps.portal.web.infra.utils.Utils;
//import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegate;
//import mx.gob.stps.portal.web.ofertasRiviera.delegate.OfertasRivieraMayaDelegateImpl;
//
//public class OfertaRivieraMayaAction extends PagerAction {
//	
//	protected static final String BOLSAS_EXTERNAS = "LISTABOLSASEXTERNAS";
//	private static final String JSP_RESULT = "JSP_RESULT";
//	private boolean existenOfertas;
//	private static final int ORDEN_ASC 			= 1;
//	private static final int ORDEN_DESC			= 2;
//	private static final int CAMPO_TITULO 		= 1;
//	private static final int CAMPO_UBICACION 	= 2;
//	private static final int CAMPO_EMPRESA 		= 3;
//	private static final int CAMPO_FECHA 		= 4;
//	
//	
//
//	@Override
//	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		
//		 List<OfertaRivieraMayaVO> listaOfertas = null;
//		try{
//			 session.removeAttribute(FULL_LIST);
//			 session.removeAttribute(PAGE_LIST);
//			
//			OfertasRivieraMayaDelegate service = OfertasRivieraMayaDelegateImpl.getInstance();
//			
//			listaOfertas =  service.obtenerOfertasRiveraMaya();
//			
//			if(listaOfertas == null)
//				listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
//			
//			if(listaOfertas.size() == 1){
//				if(listaOfertas.get(listaOfertas.size() - 1).getNombrepuesto().equals("No se encontraron datos")){
//					listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
//				}
//			}
//			if(listaOfertas.isEmpty()){
//				
//				//String existenResultados = "No se encontraron datos";
//				
//				this.existenOfertas = false;
//				session.setAttribute("existenOfertas", existenOfertas);
//				request.setAttribute("noResultados", "No");
//				
//				
//		
//			}else{
//				this.existenOfertas = true;
//				session.setAttribute("existenOfertas", existenOfertas);
//				request.setAttribute("noResultados", "No");
//				session.setAttribute(FULL_LIST, listaOfertas); //paginacion
//			}
//			
//			
//			this.PAGE_NUM_ROW = 5;
//		    this.PAGE_JUMP_SIZE = 5;
//		    
//		    request.setAttribute(BOLSAS_EXTERNAS, listaOfertas);
//			
//			session.removeAttribute(NUM_PAGE_LIST);
//		    session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
//		    
//		    session.removeAttribute(NUM_PAGE_LIST);
//		    session.setAttribute("tituloEmpresa", "Empresa");
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		session.setAttribute(BODY_JSP, mapping.findForward(JSP_RESULT).getPath());
//        PropertiesLoader properties = PropertiesLoader.getInstance();
//		session.setAttribute(TITULO_PAGINA, "Empleos en la Riviera Maya");
//		session.setAttribute(DESCRIPCION_PAGINA, "Empleos en la Riviera Maya, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
//		session.setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
//		session.setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
//		session.setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
//		return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
//	}
//	
//	public ActionForward ordenaPor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
//		HttpSession session = request.getSession();
//
//		int campo = Utils.parseInt(request.getParameter("campo"));
//		int orden = Utils.parseInt(request.getParameter("orden"));
//
//		ordenarPor(session, campo, orden);
//
//		return super.page(mapping, form, request, response);
//	}
//	
//	
//	public ActionForward FiltraOfertas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
//		HttpSession session = request.getSession();
//		List<OfertaRivieraMayaVO> listaOfertas = null;
//		String busqueda = String.valueOf(request.getParameter("busqueda"));
//		String edo = String.valueOf(request.getParameter("edo"));
//	
//		try {
//			
//			session.removeAttribute(FULL_LIST);
//			 session.removeAttribute(PAGE_LIST);
//			
//			OfertasRivieraMayaDelegate service = OfertasRivieraMayaDelegateImpl.getInstance();
//			
//			listaOfertas =  service.obtenerOfertasSectur(Integer.parseInt(edo),busqueda);
//			
//			if(listaOfertas == null)
//				listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
//			
//			if(listaOfertas.size() == 1){
//				if(listaOfertas.get(listaOfertas.size() - 1).getNombrepuesto().equals("No se encontraron datos")){
//					listaOfertas = new ArrayList<OfertaRivieraMayaVO>();
//				}
//			}
//			if(listaOfertas.isEmpty()){
//				
//				//String existenResultados = "No se encontraron datos";
//				
//				this.existenOfertas = false;
//				session.setAttribute("existenOfertas", existenOfertas);
//				request.setAttribute("noResultados", "No");
//				
//				
//		
//			}else{
//				this.existenOfertas = true;
//				session.setAttribute("existenOfertas", existenOfertas);
//				request.setAttribute("noResultados", "No");
//				session.setAttribute(FULL_LIST, listaOfertas); //paginacion
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return super.page(mapping, form, request, response);
//	}
//	
//	@SuppressWarnings("unchecked")
//	private void ordenarPor(HttpSession session, int campo, final int orden){
//		try{
//			List<OfertaRivieraMayaVO> ofertas = (List<OfertaRivieraMayaVO>)session.getAttribute(FULL_LIST);
//
//			if (ofertas==null || ofertas.isEmpty()) return;
//			
//			Comparator<OfertaRivieraMayaVO> comparator = null;
//			
//			switch(campo){
//				case CAMPO_TITULO:
//					comparator = new Comparator<OfertaRivieraMayaVO>(){
//						@Override
//		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//		                	String valor01 = o1.getNombrepuesto();
//		                	String valor02 = o2.getNombrepuesto();
//
//		                	if (valor01==null && valor02==null) return 0;
//		                	if (valor01==null && valor02!=null) return -1;
//		                	if (valor01!=null && valor02==null) return 1;
//		                	
//		                	valor01 = valor01.trim().toUpperCase();
//		                	valor02 = valor02.trim().toUpperCase();
//
//		                	int compare = 0;
//		                	switch(orden){
//		                	case ORDEN_ASC:
//		                		compare = valor01.compareTo(valor02);
//		                		break;
//		                	case ORDEN_DESC:
//		                		compare = valor02.compareTo(valor01);
//		                		break;
//		                	default:
//		                		compare = valor01.compareTo(valor02);
//		                	}
//		                	return compare;
//		                }
//					};
//					break;
//				case CAMPO_UBICACION:
//					comparator = new Comparator<OfertaRivieraMayaVO>(){
//						@Override
//		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//		                	String valor01 = o1.getUbicacion();
//		                	String valor02 = o2.getUbicacion();
//
//		                	if (valor01==null && valor02==null) return 0;
//		                	if (valor01==null && valor02!=null) return -1;
//		                	if (valor01!=null && valor02==null) return 1;
//		                	
//		                	valor01 = valor01.trim().toUpperCase();
//		                	valor02 = valor02.trim().toUpperCase();
//
//		                	int compare = 0;
//		                	switch(orden){
//		                	case ORDEN_ASC:
//		                		compare = valor01.compareTo(valor02);
//		                		break;
//		                	case ORDEN_DESC:
//		                		compare = valor02.compareTo(valor01);
//		                		break;
//		                	default:
//		                		compare = valor01.compareTo(valor02);
//		                	}
//		                	return compare;
//		                }
//					};
//					break;
//				case CAMPO_EMPRESA:
//					comparator = new Comparator<OfertaRivieraMayaVO>(){
//						@Override
//		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//		                	String valor01 = o1.getRazonSocial();
//		                	String valor02 = o2.getRazonSocial();
//
//		                	if (valor01==null && valor02==null) return 0;
//		                	if (valor01==null && valor02!=null) return -1;
//		                	if (valor01!=null && valor02==null) return 1;
//		                	
//		                	valor01 = valor01.trim().toUpperCase();
//		                	valor02 = valor02.trim().toUpperCase();
//
//		                	int compare = 0;
//		                	switch(orden){
//		                	case ORDEN_ASC:
//		                		compare = valor01.compareTo(valor02);
//		                		break;
//		                	case ORDEN_DESC:
//		                		compare = valor02.compareTo(valor01);
//		                		break;
//		                	default:
//		                		compare = valor01.compareTo(valor02);
//		                	}
//		                	return compare;
//		                }
//					};
//					break;
//				case CAMPO_FECHA:
//					comparator = new Comparator<OfertaRivieraMayaVO>(){
//						@Override
//		                public int compare(OfertaRivieraMayaVO o1, OfertaRivieraMayaVO o2) {
//		                	String valor01 = o1.getGradoescolaridad();
//		                	String valor02 = o2.getGradoescolaridad();
//
//		                	if (valor01==null && valor02==null) return 0;
//		                	if (valor01==null && valor02!=null) return -1;
//		                	if (valor01!=null && valor02==null) return 1;
//		                	
//		                	valor01 = valor01.trim().toUpperCase();
//		                	valor02 = valor02.trim().toUpperCase();
//
//		                	int compare = 0;
//		                	switch(orden){
//		                	case ORDEN_ASC:
//		                		compare = valor01.compareTo(valor02);
//		                		break;
//		                	case ORDEN_DESC:
//		                		compare = valor02.compareTo(valor01);
//		                		break;
//		                	default:
//		                		compare = valor01.compareTo(valor02);
//		                	}
//		                	return compare;
//		                }
//					};
//					break;
//			}
//
//			if (comparator==null) return;
//
//	    	Collections.sort(ofertas, comparator);
//
//	    	session.setAttribute(FULL_LIST, ofertas);
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//
//}