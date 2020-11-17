/**
 * Author: OMAROMAN
 * Date: 8/6/13
 * Time: 10:48 AM
 */
package mx.gob.stps.portal.web.infra.utils;

import mx.gob.stps.portal.web.infra.utils.fs.B64ImgReplacedElementFactory;
import mx.gob.stps.portal.web.infra.utils.fs.MediaReplacedElementFactory;
import org.apache.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.w3c.dom.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.FSEntityResolver;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class HtmlUtils {

    private static Logger logger = Logger.getLogger(HtmlUtils.class);

    private static TemplateEngine templateEngine;

    private static void initTemplateEngine() {
        if (templateEngine == null) {

            TemplateResolver templateResolver = new ServletContextTemplateResolver();

            // XHTML is the default mode, but we will set it anyway for better understanding of code
            templateResolver.setTemplateMode("XHTML");
            // This will convert "home" to "/WEB-INF/templates/home.html"
            templateResolver.setPrefix("/cv_templates/");
            templateResolver.setSuffix(".html");
            templateResolver.setCharacterEncoding("UTF-8");
            // Set template cache TTL to 1 hour. If not set, entries would live in cache until expelled by LRU
    //        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));

            // Cache is set to true by default. Set to false if you want templates to
            // be automatically updated when modified.
            templateResolver.setCacheable(true);

            templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);
        }
    }

    public static String processHtmlTemplate(final String templateName, final WebContext ctx) {

//        TemplateEngine templateEngine = getInstanceOfTemplateEngine();
        initTemplateEngine();

        // Create the HTML document using Thymeleaf
        final String htmlContent = templateEngine.process(templateName, ctx);
//        logger.debug(htmlContent);

        return htmlContent;
//        return StringEscapeUtils.escapeHtml(htmlContent);
    }

    public static void convertHtmlToPdfAndWriteToBrowseStream(String html, HttpServletRequest request, HttpServletResponse response) {
        // parse the markup into an xml Document
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            builder.setEntityResolver(FSEntityResolver.instance());
            Document doc = builder.parse(new InputSource(new ByteArrayInputStream(html.getBytes("UTF-8"))));

            // NOTE:
            // If you pass a URL for the document to the renderer, then it will infer
            // the base URL. For example the document URL
            // http://myserver.com/pdf/mydoc.xhtml would result in a base
            // URL of http://myserver.com/pdf/ However, if you pass in a
            // pre-parsed Document object instead of a URL, then the
            // renderer will have no idea what the base URL is. You can manually
            // set the base URL using the second argument to the setDocument() method
            // for referencing any external resources (images, css, etc).
            String baseUrl = UrlUtils.obtainBaseUrl(request);

            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            sharedContext.getTextRenderer().setSmoothingThreshold(0);

            // For the B64 image replacer, just the factory here
            sharedContext.setReplacedElementFactory(new B64ImgReplacedElementFactory());

            // For the URI image raplacer, set the factory here
            sharedContext.setReplacedElementFactory(new MediaReplacedElementFactory(renderer.getSharedContext().getReplacedElementFactory(), baseUrl));

            renderer.setDocument(doc, baseUrl);
            renderer.layout();

            response.setContentType("application/pdf");

            StringBuilder sbContentDispValue = new StringBuilder();
            sbContentDispValue.append("attachment");  //determina si lo despliega en pantalla o si muestra un dialogo de guardar como ("attachment")
            sbContentDispValue.append("; filename=\"");

            StringBuilder sbFilename = new StringBuilder();
            sbFilename.append("CV_");
            sbFilename.append(System.currentTimeMillis());
            sbFilename.append(".pdf");

            sbContentDispValue.append(sbFilename).append("\"");

            response.setHeader("Content-disposition", sbContentDispValue.toString());
            OutputStream browserStream = response.getOutputStream();
            renderer.createPDF(browserStream);
            renderer.finishPDF();
            browserStream.flush();
//            browserStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static byte[] convertHtmlToPdfAndWriteToBrowseStream(String html, HttpServletRequest request) {
    	 byte[] buf = null;
        // parse the markup into an xml Document
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            builder.setEntityResolver(FSEntityResolver.instance());
            Document doc = builder.parse(new InputSource(new ByteArrayInputStream(html.getBytes("UTF-8"))));

            // NOTE:
            // If you pass a URL for the document to the renderer, then it will infer
            // the base URL. For example the document URL
            // http://myserver.com/pdf/mydoc.xhtml would result in a base
            // URL of http://myserver.com/pdf/ However, if you pass in a
            // pre-parsed Document object instead of a URL, then the
            // renderer will have no idea what the base URL is. You can manually
            // set the base URL using the second argument to the setDocument() method
            // for referencing any external resources (images, css, etc).
            String baseUrl = UrlUtils.obtainBaseUrl(request);

            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            sharedContext.getTextRenderer().setSmoothingThreshold(0);

            // For the B64 image replacer, just the factory here
            sharedContext.setReplacedElementFactory(new B64ImgReplacedElementFactory());

            // For the URI image raplacer, set the factory here
            sharedContext.setReplacedElementFactory(new MediaReplacedElementFactory(renderer.getSharedContext().getReplacedElementFactory(), baseUrl));

            renderer.setDocument(doc, baseUrl);
            renderer.layout();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            renderer.createPDF(outputStream);
            renderer.finishPDF();            
            buf = outputStream.toByteArray();
            outputStream.flush();
//          browserStream.close();            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return buf;
    }
}
