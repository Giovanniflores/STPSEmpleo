
package mx.gob.stps.portal.web.security.listener;


import java.sql.SQLException;
import java.util.Calendar;
import org.apache.log4j.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegate;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.search.delegate.OfertasRecientesBusDelegate;
import mx.gob.stps.portal.web.oferta.search.delegate.OfertasRecientesBusDelegateImpl;

public class ApplicationListener implements ServletContextListener {
	
	private static Logger logger = Logger.getLogger(ApplicationListener.class);
	
	public void contextInitialized(ServletContextEvent event) {		
		logger.info(" ****************************** Portal del Empleo iniciado ****************************** "); 

		PropertiesLoader properties = PropertiesLoader.getInstance();
		String pathcss = properties.getProperty("app.path.css.swb");
		event.getServletContext().setAttribute("PATH_CSS_SWB", pathcss);
		logger.info("Ruta de estilos del Portal: "+ pathcss);

		String PATH_CSS_SWB_APP = properties.getProperty("app.path.css.swb.app");
		event.getServletContext().setAttribute("PATH_CSS_SWB_APP", PATH_CSS_SWB_APP);
		logger.info("Ruta de estilos del Portal [Aplicacion]: "+ PATH_CSS_SWB_APP);
		
		String DOMAIN_PORTAL 		= properties.getProperty("app.domain.portal");
		event.getServletContext().setAttribute("DOMAIN_PORTAL", DOMAIN_PORTAL);
		logger.info("Dominio de Portal [Aplicacion]: "+ DOMAIN_PORTAL);
		
		String DOMAIN_PORTAL_CIL 	= properties.getProperty("app.domain.portal.cil");
		event.getServletContext().setAttribute("DOMAIN_PORTAL_CIL", DOMAIN_PORTAL_CIL);
		logger.info("Dominio de Portal Cil [Cil]: "+ DOMAIN_PORTAL_CIL);
		
		String FTP_STATIC_CONTENT = properties.getProperty("app.ftp.static.content");
		event.getServletContext().setAttribute("FTP_STATIC_CONTENT", FTP_STATIC_CONTENT);
		logger.info("FTP Static Content Server: "+ FTP_STATIC_CONTENT);

        String DOMAIN_PORTAL_CITAS = properties.getProperty("app.login.redirect.portal.citas");
        event.getServletContext().setAttribute("DOMAIN_PORTAL_CITAS", DOMAIN_PORTAL_CITAS);
        logger.info("Dominio de Portal Citas [Citas]: " + DOMAIN_PORTAL_CITAS);
		
		Calendar inicioApp = Calendar.getInstance();
		event.getServletContext().setAttribute("FH_INICIO_APLICACION", inicioApp);
		/**/		
//		try {
//			BolsasTrabajoBusDelegate service = BolsasTrabajoBusDelegateImpl.getInstance();
//			service.inicializaIndexador();
//		}catch (Exception e){
//			logger.error(e);
//		}
		
		/*  COMENTAR LOCAL PARA HACER PRUEBAS */
		/*try {
			RegisterBusDelegate registerBusDelegate = RegisterBusDelegateImpl.getInstance();
			registerBusDelegate.iniciaDepuracionOfertas();
		} catch (ServiceLocatorException e) {			
			logger.error(e);
		}*/
		
		
//		try {
//			BolsasTrabajoBusDelegate service = BolsasTrabajoBusDelegateImpl.getInstance();
//			service.iniciaTransferenciaOfertasSFP();
//		} catch (ServiceLocatorException e) {
//			logger.error(e);
//		}
		
		
		try {
			CatalogoOpcionDelegate catalogos = CatalogoOpcionDelegateImpl.getInstance();
			catalogos.cargaCatalogosCache();
		} catch (ServiceLocatorException e) {
			logger.error(e);
		}

		/* */
//		try{
//			// Inactiva sesiones de usuario que pudieran estar atrapadas
//			SecutityDelegateImpl.getInstance().inactivaSesionesActivas();
//		} catch (Exception e) {
//			logger.error(e);
//		}

		try {
			RegisterBusDelegateImpl.getInstance().iniciaContadorOfertasPublicadas();
		} catch (ServiceLocatorException e) {			
			logger.error(e);
		}
		
		/* COMENTAR LOCAL PARA HACER PRUEBAS */
		//Incializa el timer para la consulta de todas las ofertas recientes
		OfertasRecientesBusDelegate serviceOfertasRecientes = OfertasRecientesBusDelegateImpl.getInstance();
		try{
			serviceOfertasRecientes.initTimerOfertasRecientesDestacadas();
		}catch(ServiceLocatorException e){
			logger.error(e);
		}catch(SQLException se){
			logger.error(se);
		}
			
			
		/*INICIA COMENTAR EN PRODUCCION*/
		/*CandidatoBusDelegate serviceCandidato = CandidatoBusDelegateImpl.getInstance();
		try {
			serviceCandidato.iniciarDepuracionDeCandidatosPorVigencia();
		} catch (ServiceLocatorException e) {
			logger.error(e);
		}*/	
		/*TERMINA COMENTAR EN PRODUCCION*/
		
		
		/*
		try {
			RegisterBusDelegate registerBusDelegate = RegisterBusDelegateImpl.getInstance();
			registerBusDelegate.iniciaDepuracionCuentas();
		} catch (ServiceLocatorException e) {			
			logger.error(e);
		}
		*/		
		
		/*
		OfertasSMSBusDelegate service = OfertasSMSBusImpl.getInstance();
		try {
			service.iniciaOfertasSMS();
		} catch (ServiceLocatorException e) {
			logger.error(e);
		}
		*/

		
		/*
		//Incializa el timer para el envi� del reporte a INFONAVIT		
		CandidatoBusDelegate serviceCandidato = CandidatoBusDelegateImpl.getInstance();
		try{
			serviceCandidato.iniciaReporteInfonavit();
		}catch(ServiceLocatorException e){
			logger.error(e);
		}
		 */
		

		
	}

	public void contextDestroyed(ServletContextEvent event) {
		logger.info(" ****************************** Portal del Empleo detenido ****************************** ");

//		try {
//			BolsasTrabajoBusDelegate service = BolsasTrabajoBusDelegateImpl.getInstance();
//			service.finalizaIndexador();
//			logger.info("Indexador finalizado...");
//		} catch (ServiceLocatorException e) {
//			logger.error(e); 
//		}

		try {
			RegisterBusDelegate registerBusDelegate = RegisterBusDelegateImpl.getInstance();
			registerBusDelegate.detieneDepuracionCuentas();
		} catch (ServiceLocatorException e) {			
			logger.error(e);
		}

		try {
			RegisterBusDelegate registerBusDelegate = RegisterBusDelegateImpl.getInstance();
			registerBusDelegate.detieneDepuracionOfertas();
		} catch (ServiceLocatorException e) {			
			logger.error(e);
		}
				
		try {
			BolsasTrabajoBusDelegate service = BolsasTrabajoBusDelegateImpl.getInstance();
			service.finalizaTransferenciaOfertasSFP();
		} catch (ServiceLocatorException e) {
			logger.error(e);
		}

		try {
			RegisterBusDelegate registerBusDelegate = RegisterBusDelegateImpl.getInstance();
			registerBusDelegate.detieneContadorOfertasPublicadas();
		} catch (ServiceLocatorException e) {			
			logger.error(e);
		}
		
//		OfertasSMSBusDelegate service = OfertasSMSBusImpl.getInstance();
//		try {
//			service.detieneOfertasSMS();
//		} catch (ServiceLocatorException e) {
//			logger.error(e);
//		}
		
		/*INICIA COMENTAR EN PRODUCCION*/
		/*
		CandidatoBusDelegate serviceCandidato = CandidatoBusDelegateImpl.getInstance();
		try {
			serviceCandidato.detenerDepuracionDeCandidatosPorVigencia();
		} catch (ServiceLocatorException e) {
			logger.error(e);
		}*/	
		/*TERMINA COMENTAR EN PRODUCCION*/

	}

}