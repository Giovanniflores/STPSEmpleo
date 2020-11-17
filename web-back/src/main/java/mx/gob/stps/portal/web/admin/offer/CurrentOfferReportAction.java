package mx.gob.stps.portal.web.admin.offer;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.net.URLEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS;
import mx.gob.stps.portal.web.admin.form.CurrentOfferReportForm;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.offer.action.OfferDetailsAction;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.core.oferta.vo.CurrentOfferAreaOcupacionVO;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CurrentOfferReportAction extends GenericAction {

	private static Logger logger = Logger.getLogger(OfferDetailsAction.class);
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		CurrentOfferReportForm offerForm = (CurrentOfferReportForm) form; 
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		ArrayList ofertasPortalEntidad = new ArrayList();
		List<ParametroVO> ofertasExternasEntidad = new ArrayList<ParametroVO>();
		long cuentaPortal = 0;
		long cuentaExternas = 0;
		long cuentaTotal = 0;

		try {
			ofertasPortalEntidad = (ArrayList) services.getCurrentPortalOffersCountByEntity();
			
			ofertasExternasEntidad = services.getCurrentExternalOffersCount();
			request.getSession().setAttribute("ofertasPortalEntidad", ofertasPortalEntidad);
			request.getSession().setAttribute("ofertasExternasEntidad", ofertasExternasEntidad);					
			offerForm.setOfertasPortalEntidad(ofertasPortalEntidad);
			offerForm.setOfertasExternasEntidad(ofertasExternasEntidad);
			//Contar ofertas solo del portal
			//System.out.println("--trasnd--ofertasPortalEntidad:" + ofertasPortalEntidad.size());			
		    
			for(int i=0; i<ofertasPortalEntidad.size(); i++){
		    	ArrayList arrTemp = (ArrayList) ofertasPortalEntidad.get(i);
		    	//System.out.println("--trasnd--arrTemp:" + arrTemp.size());
		        String entidad = arrTemp.get(0).toString();
		        String strCuenta = arrTemp.get(5).toString();
		        long cuenta = Long.parseLong(strCuenta);	
		        //System.out.println("---entidad:" + entidad + " cuenta:"  + cuenta);
		        cuentaPortal = cuentaPortal + cuenta;		 	        
		    }		  
		    
		    //System.out.println("---cuentaPortal:" + cuentaPortal);		    
		     offerForm.setTotalOfertasPortal(cuentaPortal);

			//Contar ofertas externas
		    Iterator itExt = ofertasExternasEntidad.iterator();
		    while (itExt.hasNext()) {
		    	ParametroVO parametro =  (ParametroVO)itExt.next();
		        long cuenta = Utils.parseLong(parametro.getValor());
		        //System.out.println("---entidad:" + parametro.getDescripcion() + " cuenta:"  + cuenta);
		        cuentaExternas = cuentaExternas + cuenta;
		    }			     
		    //System.out.println("---cuentaExternas:" + cuentaExternas);
		    offerForm.setTotalOfertasExternas(cuentaExternas);
		    cuentaTotal = cuentaPortal + cuentaExternas;
		    System.out.println("---cuentaTotal:" + cuentaTotal);
		    offerForm.setTotalOfertas(cuentaTotal);
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Reporte actual de oferta");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reporte actual de oferta, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);		

	}

	public ActionForward ofertasEntidadGenero(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
			
		CurrentOfferReportForm offerForm = (CurrentOfferReportForm) form;

		ArrayList ofertasPortalEntidadGenero = new ArrayList();		
		
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		
	     offerForm.setTotalOfertasPortal(Utils.toLong(0));
	     offerForm.setTotalOfertasPortalMasculino(Utils.toLong(0));
	     offerForm.setTotalOfertasPortalFemenino(Utils.toLong(0));
	     offerForm.setTotalOfertasPortalNoRequerido(Utils.toLong(0));
	     offerForm.setTotalOfertasIndiferente(Utils.toLong(0));
	     offerForm.clearOfertasPortalEntidad();

		try {
			ofertasPortalEntidadGenero = (ArrayList) services.getCurrentPortalOffersCountByEntity();

	    	@SuppressWarnings("rawtypes")
			ArrayList ofertasEntidadTemp = new ArrayList();

		    for(int i=0; i<ofertasPortalEntidadGenero.size(); i++){

		    	@SuppressWarnings("rawtypes")
		    	ArrayList arrTemp = (ArrayList) ofertasPortalEntidadGenero.get(i);
		    	ofertasEntidadTemp.add(arrTemp);
		        
				offerForm.setTotalOfertasPortalMasculino(offerForm.getTotalOfertasPortalMasculino()+Long.parseLong(arrTemp.get(1).toString()));
				offerForm.setTotalOfertasPortalFemenino(offerForm.getTotalOfertasPortalFemenino()+Long.parseLong(arrTemp.get(2).toString()));
				offerForm.setTotalOfertasPortalNoRequerido(offerForm.getTotalOfertasPortalNoRequerido()+Long.parseLong(arrTemp.get(3).toString()));
				offerForm.setTotalOfertasIndiferente(offerForm.getTotalOfertasIndiferente()+Long.parseLong(arrTemp.get(4).toString()));
				offerForm.setTotalOfertasPortal(offerForm.getTotalOfertasPortal()+Long.parseLong(arrTemp.get(5).toString()));				
			}
			offerForm.setTotalOfertasPortal(offerForm.getTotalOfertasPortal());		    
		    offerForm.setOfertasPortalEntidad(ofertasEntidadTemp);    

		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
    	
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	
        return mapping.findForward("JSP_ENTIDAD_GENERO");
	}
	
	public ActionForward ofertasEntidadEscolaridad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		CurrentOfferReportForm offerForm = (CurrentOfferReportForm) form;
				
		ArrayList ofertasPortalEntidadEscolaridad = new ArrayList();		
		
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		
		offerForm.setTotalOfertasSinInstruccion(Utils.toLong(0));			
		offerForm.setTotalOfertasLeerEscribir(Utils.toLong(0));
		offerForm.setTotalOfertasPrimaria(Utils.toLong(0));
		offerForm.setTotalOfertasSecundaria(Utils.toLong(0));
		offerForm.setTotalOfertasCarreraComercial(Utils.toLong(0));
		offerForm.setTotalOfertasCarreraTecnica(Utils.toLong(0));
		offerForm.setTotalOfertasProfesionalTecnico(Utils.toLong(0));
		offerForm.setTotalOfertasPrepa(Utils.toLong(0));
		offerForm.setTotalOfertasUniversitario(Utils.toLong(0));
		offerForm.setTotalOfertasLicenciatura(Utils.toLong(0));
		offerForm.setTotalOfertasMaestria(Utils.toLong(0));
		offerForm.setTotalOfertasDoctorado(Utils.toLong(0));
		offerForm.setTotalOfertasEntidadEscolaridad(Utils.toLong(0));		
		offerForm.clearOfertasPortalEntidadEscolaridad();

		try {
			ofertasPortalEntidadEscolaridad = (ArrayList) services.getCurrentPortalOffersCountByEntityStudy();
			
			ArrayList ofertasEntidadTemp = new ArrayList();
		    for(int i=0; i<ofertasPortalEntidadEscolaridad.size(); i++){
		    	
		    	@SuppressWarnings("rawtypes")
				ArrayList arrTemp = (ArrayList) ofertasPortalEntidadEscolaridad.get(i);
				
				ofertasEntidadTemp.add(arrTemp);
				
				offerForm.setTotalOfertasSinInstruccion(offerForm.getTotalOfertasSinInstruccion()+Long.parseLong(arrTemp.get(1).toString()));
				offerForm.setTotalOfertasLeerEscribir(offerForm.getTotalOfertasLeerEscribir()+Long.parseLong(arrTemp.get(2).toString()));
				offerForm.setTotalOfertasPrimaria(offerForm.getTotalOfertasPrimaria()+Long.parseLong(arrTemp.get(3).toString()));
				offerForm.setTotalOfertasSecundaria(offerForm.getTotalOfertasSecundaria()+Long.parseLong(arrTemp.get(4).toString()));
				offerForm.setTotalOfertasCarreraComercial(offerForm.getTotalOfertasCarreraComercial()+Long.parseLong(arrTemp.get(5).toString()));
				offerForm.setTotalOfertasCarreraTecnica(offerForm.getTotalOfertasCarreraTecnica()+Long.parseLong(arrTemp.get(6).toString()));
				offerForm.setTotalOfertasProfesionalTecnico(offerForm.getTotalOfertasProfesionalTecnico()+Long.parseLong(arrTemp.get(7).toString()));
				offerForm.setTotalOfertasPrepa(offerForm.getTotalOfertasPrepa()+Long.parseLong(arrTemp.get(8).toString()));
				offerForm.setTotalOfertasUniversitario(offerForm.getTotalOfertasUniversitario()+Long.parseLong(arrTemp.get(9).toString()));
				offerForm.setTotalOfertasLicenciatura(offerForm.getTotalOfertasLicenciatura()+Long.parseLong(arrTemp.get(10).toString()));
				offerForm.setTotalOfertasMaestria(offerForm.getTotalOfertasMaestria()+Long.parseLong(arrTemp.get(11).toString()));
				offerForm.setTotalOfertasDoctorado(offerForm.getTotalOfertasDoctorado()+Long.parseLong(arrTemp.get(12).toString()));
				offerForm.setTotalOfertasEntidadEscolaridad(offerForm.getTotalOfertasEntidadEscolaridad()+Long.parseLong(arrTemp.get(13).toString()));			
			}
			offerForm.setOfertasPortalEntidadEscolaridad(ofertasEntidadTemp);    

		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}			
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());		
        return mapping.findForward("JSP_ENTIDAD_ESCOLARIDAD");	
	}
	

	public ActionForward ofertasEntidadExperiencia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		CurrentOfferReportForm offerForm = (CurrentOfferReportForm) form;
				
		ArrayList ofertasPortalEntidadExperiencia = new ArrayList();		

		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();

		offerForm.setTotalOfertasSinExperiencia(Utils.toLong(0));	
		offerForm.setTotalOfertasHasta1Anio(Utils.toLong(0));
		offerForm.setTotalOfertasDe1A2Anios(Utils.toLong(0));	
		offerForm.setTotalOfertasDe2A3Anios(Utils.toLong(0));	
		offerForm.setTotalOfertasDe3A4Anios(Utils.toLong(0));	
		offerForm.setTotalOfertasDe4A5Anios(Utils.toLong(0));
		offerForm.setTotalOfertasMasDe5Anios(Utils.toLong(0));
		offerForm.setTotalOfertasEntidadExperiencia(Utils.toLong(0));
		offerForm.setTotalOfertasExpNoRequerida(Utils.toLong(0));		
		offerForm.clearOfertasPortalEntidadExperiencia();

		try {
			ofertasPortalEntidadExperiencia = (ArrayList) services.getCurrentPortalOffersCountByEntityExperience();
	
			ArrayList ofertasEntidadTemp = new ArrayList();
		    for(int i=0; i<ofertasPortalEntidadExperiencia.size(); i++){
		    	
		    	@SuppressWarnings("rawtypes")
				ArrayList arrTemp = (ArrayList) ofertasPortalEntidadExperiencia.get(i);
				
				ofertasEntidadTemp.add(arrTemp);
				
				offerForm.setTotalOfertasSinExperiencia(offerForm.getTotalOfertasSinExperiencia()+Long.parseLong(arrTemp.get(1).toString()));	
				offerForm.setTotalOfertasHasta1Anio(offerForm.getTotalOfertasHasta1Anio()+Long.parseLong(arrTemp.get(2).toString()));
				offerForm.setTotalOfertasDe1A2Anios(offerForm.getTotalOfertasDe1A2Anios()+Long.parseLong(arrTemp.get(3).toString()));	
				offerForm.setTotalOfertasDe2A3Anios(offerForm.getTotalOfertasDe2A3Anios()+Long.parseLong(arrTemp.get(4).toString()));	
				offerForm.setTotalOfertasDe3A4Anios(offerForm.getTotalOfertasDe3A4Anios()+Long.parseLong(arrTemp.get(5).toString()));	
				offerForm.setTotalOfertasDe4A5Anios(offerForm.getTotalOfertasDe4A5Anios()+Long.parseLong(arrTemp.get(6).toString()));
				offerForm.setTotalOfertasMasDe5Anios(offerForm.getTotalOfertasMasDe5Anios()+Long.parseLong(arrTemp.get(7).toString()));		
				offerForm.setTotalOfertasExpNoRequerida(offerForm.getTotalOfertasExpNoRequerida()+Long.parseLong(arrTemp.get(8).toString()));
				offerForm.setTotalOfertasEntidadExperiencia(offerForm.getTotalOfertasEntidadExperiencia()+Long.parseLong(arrTemp.get(9).toString()));
			}
			offerForm.setOfertasPortalEntidadExperiencia(ofertasEntidadTemp);    

		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}			
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        return mapping.findForward("JSP_ENTIDAD_EXPERIENCIA");
	}
	

	public ActionForward ofertasPorActividadEconomica(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	
		CurrentOfferReportForm offerForm = (CurrentOfferReportForm) form;
		
		ArrayList ofertasActividadEconomica = new ArrayList();

		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();

		offerForm.setTotalOfertasPortalActividadEconomica(Utils.toLong(0));
		offerForm.clearOfertasPortalActividadEconomica();
				
		try {
			ofertasActividadEconomica = (ArrayList) services.getCurrentOfferEconomicActivity();
	
			ArrayList ofertasTemp = new ArrayList();
		    for(int i=0; i<ofertasActividadEconomica.size(); i++){
		    	
		    	@SuppressWarnings("rawtypes")
				ArrayList arrTemp = (ArrayList) ofertasActividadEconomica.get(i);
				
		    	ofertasTemp.add(arrTemp);
				
				offerForm.setTotalOfertasPortalActividadEconomica(offerForm.getTotalOfertasPortalActividadEconomica()+Long.parseLong(arrTemp.get(1).toString()));
			}
			offerForm.setOfertasPortalActividadEconomica(ofertasTemp);    

		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}

		//request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        //return mapping.findForward("JSP_OFERTAS_ACT_ECONOMICA");
        
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_OFERTAS_ACT_ECONOMICA").getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Ofertas por Actividad Econ&oacute;mica");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Ofertas por Actividad Econ&oacute;mica, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward ofertasPorAreaOcupacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		CurrentOfferReportForm offerForm = (CurrentOfferReportForm) form;		
		
		ArrayList<CurrentOfferAreaOcupacionVO> listaOfertasAreaOcupacion = new ArrayList<CurrentOfferAreaOcupacionVO>();
		
		OfferBusDelegate services = OfferBusDelegateImpl.getInstance();
		
		offerForm.setTotalOfertasAreaOcupacion(Utils.toLong(0));
		offerForm.clearOfertasAreaOcupacion();
		
		try {
			listaOfertasAreaOcupacion = (ArrayList) services.getCurrentPortalOffersCountByAreaOcupacion();
			offerForm.setOfertasAreaOcupacion(listaOfertasAreaOcupacion);
	
		    for(int i=0; i<listaOfertasAreaOcupacion.size(); i++){

		    	CurrentOfferAreaOcupacionVO arrTemp = (CurrentOfferAreaOcupacionVO) listaOfertasAreaOcupacion.get(i);

				offerForm.setTotalOfertasAreaOcupacion(offerForm.getTotalOfertasAreaOcupacion()+Utils.toLong(arrTemp.getAreaTotalPlazas()));
			}

		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}		
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_OFERTAS_AREA_OCUPACION").getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Ofertas por area de ocupaci&oacute;n");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Ofertas por area de ocupaci&oacute;n, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);				
	}
	
	public ActionForward ofertasPorAreaOcupacionReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		CurrentOfferReportForm offerForm = (CurrentOfferReportForm) form;

		//
		
		try {
			
			HSSFWorkbook archivo = ofertasPorAreaOcupacionGeneraExcel(offerForm.getOfertasAreaOcupacion());


			ServletOutputStream ouputStream = response.getOutputStream();
				
			String nombreArchivo = new String("\"Ofertas por area laboral y ocupacion.xls\"");
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","attachment; filename="+nombreArchivo);

			archivo.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
			logger.info("Se envía a salida el report "+nombreArchivo);
			
		} catch (IOException e) {
			logger.error("Error en ofertasPorAreaOcupacionReport");
			e.printStackTrace();
		}		

		return null;
	}
		
	private HSSFWorkbook ofertasPorAreaOcupacionGeneraExcel(List<CurrentOfferAreaOcupacionVO> listaOfertasAreaOcupacion){
		
		HSSFWorkbook libroExcel = new HSSFWorkbook();
		
		HSSFCellStyle estiloCelda = libroExcel.createCellStyle();
		HSSFCellStyle estiloCeldaTitulo = libroExcel.createCellStyle();
		HSSFCellStyle estiloCeldaEncabezado = libroExcel.createCellStyle();
		HSSFCellStyle estiloCeldaNombreArea = libroExcel.createCellStyle();				
		HSSFCellStyle estiloCeldaNombreOcupacion = libroExcel.createCellStyle();
		HSSFCellStyle estiloCeldaRangoOcupacion = libroExcel.createCellStyle();		
		
		HSSFFont fontEncabezado = libroExcel.createFont();
		fontEncabezado.setFontHeightInPoints((short) 11);
		fontEncabezado.setFontName("Calibri");
		fontEncabezado.setBoldweight(Font.BOLDWEIGHT_BOLD);			
		estiloCeldaEncabezado.setFont(fontEncabezado);
		
		estiloCelda.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		estiloCelda.setBorderTop(HSSFCellStyle.BORDER_THIN);
		estiloCelda.setBorderRight(HSSFCellStyle.BORDER_THIN);
		estiloCelda.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		estiloCelda.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		HSSFFont font = libroExcel.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setFontName("Calibri");
		estiloCelda.setFont(font);		
		
		estiloCeldaTitulo.setFont(fontEncabezado);
		
		estiloCeldaEncabezado.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		estiloCeldaEncabezado.setBorderTop(HSSFCellStyle.BORDER_THIN);
		estiloCeldaEncabezado.setBorderRight(HSSFCellStyle.BORDER_THIN);
		estiloCeldaEncabezado.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		estiloCeldaEncabezado.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		estiloCeldaEncabezado.setFont(fontEncabezado);		
		
		estiloCeldaTitulo.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		estiloCeldaTitulo.setBorderTop(HSSFCellStyle.BORDER_THIN);
		estiloCeldaTitulo.setBorderRight(HSSFCellStyle.BORDER_THIN);
		estiloCeldaTitulo.setBorderLeft(HSSFCellStyle.BORDER_THIN);		
		estiloCeldaTitulo.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				
		estiloCeldaNombreOcupacion.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		
		estiloCeldaNombreArea.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		estiloCeldaNombreArea.setBorderTop(HSSFCellStyle.BORDER_THIN);
		estiloCeldaNombreArea.setBorderRight(HSSFCellStyle.BORDER_THIN);
		estiloCeldaNombreArea.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		estiloCeldaNombreArea.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		HSSFFont fontNombreArea = libroExcel.createFont();
		fontNombreArea.setFontHeightInPoints((short) 14);
		fontNombreArea.setFontName("Calibri");
		estiloCeldaNombreArea.setFont(fontNombreArea);
		
		estiloCeldaRangoOcupacion.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		estiloCeldaRangoOcupacion.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		estiloCeldaRangoOcupacion.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		estiloCeldaRangoOcupacion.setBorderTop(HSSFCellStyle.BORDER_THIN);
		estiloCeldaRangoOcupacion.setBorderRight(HSSFCellStyle.BORDER_THIN);
		estiloCeldaRangoOcupacion.setBorderLeft(HSSFCellStyle.BORDER_THIN);		
		
		int numeroFila=0;		
		// Hoja
		HSSFSheet hoja = libroExcel.createSheet();
		libroExcel.setSheetName(0, "Área laboral y ocupación");
		hoja.setColumnWidth(1, 100*256);
		
		// Nombre y fecha de generación del reporte
		HSSFRow fila = hoja.createRow(numeroFila);
		CellRangeAddress rangoCeldas = new CellRangeAddress(numeroFila, numeroFila, 0, 1);
		hoja.addMergedRegion(rangoCeldas);		
		HSSFCell celda = fila.createCell(0);
		fila.createCell(0).setCellStyle(estiloCelda);
		fila.createCell(1).setCellStyle(estiloCelda);
		fila.getCell(0).setCellValue("Plazas vigentes por área laboral y ocupación");
		numeroFila++;
		
		fila = hoja.createRow(numeroFila);		
		rangoCeldas = new CellRangeAddress(numeroFila, numeroFila, 0, 1);
		hoja.addMergedRegion(rangoCeldas);		
		fila.createCell(0).setCellStyle(estiloCelda);
		fila.createCell(1).setCellStyle(estiloCelda);
		fila.getCell(0).setCellValue("Fecha de generación reporte: "+ Utils.getFechaFormato(new Date()));		
		numeroFila++;

		fila = hoja.createRow(numeroFila);
		numeroFila++;		
		
		// Encabezado de las columnas
		fila = hoja.createRow(numeroFila);
		
		rangoCeldas = new CellRangeAddress(numeroFila, numeroFila, 0, 1);
		hoja.addMergedRegion(rangoCeldas);
		
		celda = fila.createCell(0);
		celda.setCellValue("Area Laboral");
		celda.setCellStyle(estiloCeldaTitulo);
		fila.createCell(1).setCellStyle(estiloCeldaTitulo);
		
		celda = fila.createCell(2);
		celda.setCellValue("Subtotal");		
		celda.setCellStyle(estiloCeldaTitulo);		
		
		celda = fila.createCell(3);		
		celda.setCellValue("Total");		
		celda.setCellStyle(estiloCeldaTitulo);
		
		int totalPlazas = 0;
		
		//recorremos la lista de áreas laborales
		for(CurrentOfferAreaOcupacionVO area  : listaOfertasAreaOcupacion){
			numeroFila++;

			fila = hoja.createRow(numeroFila);
			
			rangoCeldas = new CellRangeAddress(numeroFila, numeroFila, 0, 2);
			hoja.addMergedRegion(rangoCeldas);
			
			celda = fila.createCell(0);
			if (area.getArea() != null)
				celda.setCellValue(area.getArea());
			/*celda.getRow().setHeight((short)(18*256));*/
			celda.setCellStyle(estiloCelda);
			
			fila.createCell(1).setCellStyle(estiloCelda);
			fila.createCell(2).setCellStyle(estiloCelda);

			celda = fila.createCell(3);
			celda.setCellValue(area.getAreaTotalPlazas());
			totalPlazas = totalPlazas + area.getAreaTotalPlazas();
			celda.setCellStyle(estiloCelda);
			
			//recorremos la lista de ocupaciones
			if (area.getListaOcupaciones() != null && !area.getListaOcupaciones().isEmpty()){
				int numFilaPrimeraOcupacion = 0;
				int numOcupacionesDelArea = 0;
				
				for (Object[] ocupacion : area.getListaOcupaciones()){
					
					numOcupacionesDelArea++;					
					numeroFila++;

					fila = hoja.createRow(numeroFila);
					
					//nombre de la ocupación
					celda = fila.createCell(1);					
					if (ocupacion[0] != null)
						celda.setCellValue(String.valueOf(ocupacion[0]));
					celda.setCellStyle(estiloCelda);
					
					//número de ofertas de la ocupación de la ocupación
					celda = fila.createCell(2);
					if (ocupacion[1] != null)
						celda.setCellValue(Integer.valueOf(String.valueOf(ocupacion[1])));
					celda.setCellStyle(estiloCelda);
					
					numFilaPrimeraOcupacion = (numFilaPrimeraOcupacion == 0 ? numeroFila : numFilaPrimeraOcupacion);

				}
				//fusionamos el rango de celdas de la primera columna donde pondremos el texto "Ocupación"
				rangoCeldas = new CellRangeAddress(numFilaPrimeraOcupacion, numeroFila, 0, 0);
				hoja.addMergedRegion(rangoCeldas);
				
				fila = hoja.getRow(numFilaPrimeraOcupacion);

				celda = fila.createCell(0);
				
				celda.setCellValue("Ocupación");
				
				celda.setCellStyle(estiloCeldaRangoOcupacion);
			}
		}

		// total de plazas agregado
		numeroFila++;
		fila = hoja.createRow(numeroFila);
		
		rangoCeldas = new CellRangeAddress(numeroFila, numeroFila, 0, 2);
		hoja.addMergedRegion(rangoCeldas);
		
		celda = fila.createCell(0);		
		celda.setCellStyle(estiloCelda);
		celda.setCellValue("TOTAL");
		
		celda = fila.createCell(1);		
		celda.setCellStyle(estiloCelda);
		celda = fila.createCell(2);		
		celda.setCellStyle(estiloCelda);

		celda = fila.createCell(3);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue(totalPlazas);

		return libroExcel;		
	}
	
}