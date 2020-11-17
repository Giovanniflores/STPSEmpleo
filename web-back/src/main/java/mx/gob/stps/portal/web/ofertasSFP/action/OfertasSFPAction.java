package mx.gob.stps.portal.web.ofertasSFP.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_FORM;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegate;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegateImpl;
import mx.gob.stps.portal.web.ofertasSFP.form.OfertasSFPForm;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OfertasSFPAction extends PagerAction{

	protected static final String OFERTAS_SFP = "OFERTAS_SFP";
	private static final String DETALLE = "DETALLE";
	//private static final String OFERTA_SFP = "OFERTA_SFP";
	private static final String TABLA_OFERTAS_SFP = "_OFERTAS_SFP";
	private static final int PAGE_NUM_ROW_SFP = 10;	
	private static final String ORDENAR_POR_TITULO_OFERTA = "1";
	private static final String ORDENAR_POR_UBICACION = "2";
	private static final String ORDENAR_POR_EMPRESA = "3";
	private static final String ORDENAR_POR_SALARIO = "4";
	private static final String ORDENAR_POR_FECHA_PUBLICACION = "5";
	private static final String ORDENAR_ASCENDENTE = "asc";
		
	private static Logger logger = Logger.getLogger(OfertasSFPAction.class);
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OfertasSFPForm forma = (OfertasSFPForm)form;	
		this.PAGE_NUM_ROW = PAGE_NUM_ROW_SFP;
		buscar(mapping,forma,request,response);		
//		request.setAttribute("TITULO", "Vacantes en la Administración Pública");
		request.setAttribute("TITULO", "Ofertas de la Administración Pública");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Ofertas de la Administraci&oacute;n P&uacute;blica");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
			
	@SuppressWarnings({ "rawtypes", "unchecked" })
    protected List getRows(int pagenum, List<?> rows, HttpSession session){
		List<OfertaPorCanalVO> offersList = new ArrayList<OfertaPorCanalVO>();
		List<OfertaPorCanalVO> rowsPage = super.getRows(pagenum, rows);
		
		try{
			if(null!=rowsPage){
				OfferBusDelegate offerServices = OfferBusDelegateImpl.getInstance();
				UsuarioWebVO usuario = null;
				if(null!=getUsuario(session)){					
					usuario = getUsuario(session);
				}
				
				for (Object rowElement: rowsPage) {
					OfertaPorCanalVO oferta = (OfertaPorCanalVO)rowElement;
					if(null!=usuario){
				    	int compatibility = offerServices.match(oferta.getIdOfertaEmpleo(), usuario.getIdPropietario());
				    	oferta.setCompatibility(compatibility);								
					}
										
					offersList.add(oferta);
				}
			
			}						
		} catch (Exception e) {e.printStackTrace(); logger.error(e);}
		return offersList;
    } 			

	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OfertasSFPForm forma = (OfertasSFPForm)form;
		request.getSession().removeAttribute(DETALLE);
		
		try {
				BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();	
				List<OfertaPorCanalVO> ofertas = services.buscaTodasOfertasSFP();
				
				if (ofertas==null) ofertas = new ArrayList<OfertaPorCanalVO>();				
				
				forma.setRegistros(ofertas);
				
				if (ofertas.isEmpty()){
					request.getSession().setAttribute(DETALLE, 1);
				}
				
	    		request.getSession().removeAttribute(FULL_LIST + TABLA_OFERTAS_SFP);
				request.getSession().setAttribute(FULL_LIST + TABLA_OFERTAS_SFP, ofertas);			
				
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); 
			logger.error(e);
		} catch (SQLException e) {
			e.printStackTrace(); 
			logger.error(e);
		} catch (Exception e) {
			e.printStackTrace(); 
			logger.error(e);
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.getInput());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute("TITULO", "Vacantes en la Administración Pública");
		request.getSession().setAttribute(TITULO_PAGINA, "Vacantes en la Administraci&oacute;n P&uacute;blica");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);
	}
	
	public ActionForward buscarOfertaSFP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idOfertaEmpleo = Utils.parseLong(request.getParameter("idOferta"));
		return new ActionForward("/detalleoferta.do?method=init&id_oferta_empleo="+ idOfertaEmpleo, false);
	}
	
	
	public ActionForward pageTable(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();		
		String difTablaPager = request.getParameter("tablaPager");
		int pagenum = 1;
		return this.page(pagenum, mapping, session, difTablaPager);		
	}
	
	
	public ActionForward orderByColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		OfertasSFPForm forma = (OfertasSFPForm)form;
		String tipoOrdenamiento = request.getParameter("tipoOrden");
		String numeroColumna = request.getParameter("tipoColumna");
		try{	
			List<OfertaPorCanalVO> ofertas = forma.getRegistros();
			Comparator<OfertaPorCanalVO> comparator = null;
			if(numeroColumna.equalsIgnoreCase(ORDENAR_POR_TITULO_OFERTA)){
				comparator = new Comparator<OfertaPorCanalVO>(){
					public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {						
						return o1.getTituloOferta().compareTo(o2.getTituloOferta());
					}
				};								
			} else if(numeroColumna.equalsIgnoreCase(ORDENAR_POR_UBICACION)){
				comparator = new Comparator<OfertaPorCanalVO>(){
					public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {						
		            	String s1 = o1.getEntidad();
		            	if(null!=o1.getMunicipio())
		            		s1 = s1 + o1.getMunicipio();
		            	String s2 = o2.getEntidad();
		            	if(null!=o2.getMunicipio())
		            		s2 = s2 + o2.getMunicipio();            	
		            	
		            	return s1.compareTo(s2);
					}
				};					
			} else if(numeroColumna.equalsIgnoreCase(ORDENAR_POR_EMPRESA)){
				comparator = new Comparator<OfertaPorCanalVO>(){
					public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {						
						return o1.getEmpresa().compareTo(o2.getEmpresa());
					}
				};					
				
			} else if(numeroColumna.equalsIgnoreCase(ORDENAR_POR_SALARIO)){
				comparator = new Comparator<OfertaPorCanalVO>(){
					public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {						
						return Double.valueOf(o1.getSalario()).compareTo(Double.valueOf(o2.getSalario()));
					}
				};					
			} else if(numeroColumna.equalsIgnoreCase(ORDENAR_POR_FECHA_PUBLICACION)){
				comparator = new Comparator<OfertaPorCanalVO>(){
					public int compare(OfertaPorCanalVO o1, OfertaPorCanalVO o2) {						
						return o1.getFechaInicio().compareTo(o2.getFechaInicio());
					}
				};					
			}
			
			Collections.sort(ofertas, comparator);
			
			if(!tipoOrdenamiento.equalsIgnoreCase(ORDENAR_ASCENDENTE)){
				Collections.reverse(ofertas);
			}					
			forma.setRegistros(ofertas);
    		request.getSession().removeAttribute(FULL_LIST + TABLA_OFERTAS_SFP);
			request.getSession().setAttribute(FULL_LIST + TABLA_OFERTAS_SFP, ofertas);			
			/*if(null!=ofertas){
				OfertaPorCanalVO test = (OfertaPorCanalVO) ofertas.get(0); 
			}*/
						
		}catch(Exception e){
			e.printStackTrace(); 
			logger.error(e);
		}
				
		request.getSession().setAttribute("ACTION_REGISTROS_TABLA", mapping.findForward("ACTION_REGISTROS_TABLA_OFERTAS_SFP").getPath());
		return pageTable(mapping, forma, request, response);
	}
	
	
}