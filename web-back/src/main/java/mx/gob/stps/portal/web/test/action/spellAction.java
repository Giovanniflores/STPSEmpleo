package mx.gob.stps.portal.web.test.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_HOME;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.web.infra.action.GenericAction;
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
 * @author Mario Alberto V�zquez Flores
 * @author Ricardo Geiringer Price
 * @since 26/04/2011 
 **/
public class spellAction extends GenericAction {

	private static Logger logger = Logger.getLogger(spellAction.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Portal del Empleo | Bolsa de Trabajo en M&eacute;xico");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Portal del Empleo: Portal para encontrar empleo en M&eacute;xico.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return mapping.findForward(FORWARD_TEMPLATE_HOME);
	}

	
	public ActionForward gogiespell(ActionMapping mapping,ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		
		logger.info("INFO: " + "Inicia SpellCheck");
		
		 // Configuracion SpellChecker
		 Configuration config = new Configuration();
		 config.setProxy("localhost", 7001,"http");
		 //config.setProxy(SPELL_SRV_NAME, SPELL_SRV_PORT, "http");
		 
		 SpellChecker checker = new SpellChecker(config);
		 checker.setOverHttps(true);
		 checker.setLanguage(Language.SPANISH);

		 // Envio de la informacion		 
		 SpellRequest requestg = new SpellRequest();
		 
		  System.out.println("xml del request a checar: " + request.getParameter("<?xml version"));
	      String xmlReq =  request.getParameter("<?xml version");
	      int beginIndex = xmlReq.indexOf("><text>");
	      xmlReq = xmlReq.substring(beginIndex);
	      xmlReq = xmlReq.replaceAll("</text></spellrequest>", ""); 
	      xmlReq = xmlReq.replaceFirst("><text>", "");
	      System.out.println("texto del request a checar: " + xmlReq);
	      requestg.setText(xmlReq);
		 
		 requestg.setIgnoreDuplicates(false); // Ignore duplicates

		 // Envia peticion
		 SpellResponse spellResponse = checker.check(requestg);
		 	
		 try {
	 
			 // Transformacion a XML del objeto SpellResponse
			 String respuesta = toXML(spellResponse);			
			 
			 //String prueba = new String(responseData);
			 logger.debug("Response XML:" + respuesta);
			   
			 response.setContentType("text/xml");
			 PrintWriter out = response.getWriter();
			 out.write(respuesta);
			 out.flush();
			 out.close();
			 response.flushBuffer();
												 
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 
		return null;
	}

	
	private String toXML(SpellResponse spellResponse) {
        
    StringBuilder respuestaXML = new StringBuilder();
    
    respuestaXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    int clipped = spellResponse.isClipped()?1:2;
    respuestaXML.append("<spellresult error=\"" + spellResponse.getError() + "\" clipped=\"" + clipped + "\" charschecked=\"" + spellResponse.getCharsChecked() + "\">");
    for( SpellCorrection sc : spellResponse.getCorrections() ){
    	respuestaXML.append("<c o=\"" + sc.getOffset()+ "\" l=\"" + sc.getLength() + "\" s=\"" + sc.getConfidence() + "\"> " + sc.getValue() + "</c>");
    }    
    respuestaXML.append("</spellresult>");
    
    return respuestaXML.toString();
  }
	
}






