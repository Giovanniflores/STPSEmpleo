package mx.gob.stps.portal.web.pdf.action;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.infra.data.ConexionFactory;
import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class PrintPdfCVAction extends GenericAction {
	
	private static Logger logger = Logger.getLogger(PrintPdfCVAction.class);
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
    	logger.debug("Inicia metodo init.......................");
    	logger.debug(request.getServletContext().getContextPath());
    	
    	logger.debug(".....................................");
    	
    	
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	
		long idCandidato = Utils.parseLong(request.getParameter("idCandidato"));
		
		ConexionFactory factory = ConexionFactory.getInstance();
    	Connection conn = null;
    	ConnectionWraper wraper;
		try {
			wraper = factory.getConnection();		
			conn = wraper.getConnection();
			InputStream istr = null;
			ServletOutputStream servletOutputStream = response.getOutputStream();
			byte[] bytes = null;
			istr = this.getClass().getClassLoader().getResourceAsStream("cvGrafico/report1.jrxml");
			
			logger.debug("istr: " + istr);
			
			JasperDesign jasperDes = JRXmlLoader.load(istr);
			JasperReport reporJ = JasperCompileManager.compileReport(jasperDes);		
		
			params.put("idCandidato", idCandidato);
			params.put("SUBREPORT_DIR", "cvGrafico/");
						
			JasperPrint jprint = JasperFillManager.fillReport(reporJ, params, conn);
			
			bytes = JasperExportManager.exportReportToPdf(jprint);
			 
		      response.setContentType("application/pdf");
		      response.setContentLength(bytes.length);
		 
		      servletOutputStream.write(bytes, 0, bytes.length);
		      servletOutputStream.flush();
		      servletOutputStream.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
	}
	



}
