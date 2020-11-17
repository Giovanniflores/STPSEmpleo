package mx.gob.stps.portal.web.infra.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xeustechnologies.googleapi.spelling.Configuration;
import org.xeustechnologies.googleapi.spelling.Language;
import org.xeustechnologies.googleapi.spelling.SpellChecker;
import org.xeustechnologies.googleapi.spelling.SpellCorrection;
import org.xeustechnologies.googleapi.spelling.SpellRequest;
import org.xeustechnologies.googleapi.spelling.SpellResponse;

/**
 * @author Mario Alberto Vázquez Flores
 * @author Ricardo Geiringer Price
 * @since 26/04/2011 
 **/
public class SpellCheckAction extends GenericAction {

	private static Logger logger = Logger.getLogger(SpellCheckAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	public ActionForward gogiespell(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//TODO SE INACTIVA LA VERIFICACION
		/*
		 try {		
			//logger.info("INFO: " + "Inicia SpellCheck");
			PropertiesLoader properties = PropertiesLoader.getInstance();
	
			 // Configuracion SpellChecker
			 Configuration config = new Configuration();
			 config.setProxy(properties.getProperty("app.spell.host"), 
					         properties.getPropertyInt("app.spell.port"), 
					         properties.getProperty("app.spell.scheme"));
	
			 SpellChecker checker = new SpellChecker(config);
			 checker.setOverHttps(true);
			 checker.setLanguage(Language.SPANISH);
			 
			 // Envio de la informacion		 
			 SpellRequest requestg = new SpellRequest();
			 
			  //System.out.println("xml del request a checar: " + request.getParameter("<?xml version"));
		      String xmlReq =  request.getParameter("<?xml version");
		      int beginIndex = xmlReq.indexOf("><text>");
		      xmlReq = xmlReq.substring(beginIndex);
		      xmlReq = xmlReq.replaceAll("</text></spellrequest>", ""); 
		      xmlReq = xmlReq.replaceFirst("><text>", "");
		     
		      requestg.setText( xmlReq);
		      //System.out.println("texto del request a checar: " + requestg.getText());
		      requestg.setIgnoreWordsWithAllCaps(false);
			  requestg.setIgnoreDuplicates(false); // Ignore duplicates
			  
			 // Envia peticion
			 SpellResponse spellResponse = checker.check(requestg);
			 // Transformacion a XML del objeto SpellResponse
			 String respuesta = toXML(spellResponse);			
			 
			 //String prueba = new String(responseData);
			 //logger.debug("Response XML:" + respuesta);
			   
			 response.setContentType("text/xml");
			 PrintWriter out = response.getWriter();
			 out.write(respuesta);
			 out.flush();
			 out.close();
			 response.flushBuffer();

		 } catch (IOException e) {
			 logger.error("IOException lanzada al invocar el servicio de SpellCheck de Google.");
			 //logger.error(e);
		 } catch (Exception e) {
			 logger.error("Exception lanzada al invocar el servicio de SpellCheck de Google.");
			 //logger.error(e);
		 }
		 */
		return null;
	}

	
	private String toXML(SpellResponse spellResponse) {
        
    StringBuilder respuestaXML = new StringBuilder();
    
    respuestaXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    int clipped = spellResponse.isClipped()?1:2;
    respuestaXML.append("<spellresult error=\"" + spellResponse.getError() + "\" clipped=\"" + clipped + "\" charschecked=\"" + spellResponse.getCharsChecked() + "\">");
    try{
	    for( SpellCorrection sc : spellResponse.getCorrections() ){
		    	respuestaXML.append("<c o=\"" + sc.getOffset()+ "\" l=\"" + sc.getLength() + "\" s=\"" + sc.getConfidence() + "\"> " + sc.getValue() + "</c>");
		}    
    } catch (NullPointerException e){
    	
    }
    respuestaXML.append("</spellresult>");
    
    return respuestaXML.toString();
  }
	
}






