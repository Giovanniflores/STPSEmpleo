package mx.gob.stps.portal.web.infra.utils.fs;

import com.lowagie.text.Image;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Replaced element in order to replace elements like
 * <tt>&lt;div class="media" data-src="image.png" /></tt> with the real
 * media content.
 */
public class MediaReplacedElementFactory implements ReplacedElementFactory {

    private enum Locator {
        URL,
        SYSTEM_PATH,
        CLASS_PATH
    }

    private Locator locator;

    private static Logger logger = Logger.getLogger(MediaReplacedElementFactory.class);

    private final ReplacedElementFactory superFactory;
    private URL baseUrl;
    private File baseSystemPath;
    private String baseClassPath;
    private String mediaFullLocation;

    public MediaReplacedElementFactory(ReplacedElementFactory superFactory) {
        this.superFactory = superFactory;
    }

    public MediaReplacedElementFactory(ReplacedElementFactory superFactory, URL baseUrl) {
        this(superFactory);
        this.baseUrl = baseUrl;
        locator = Locator.URL;
    }

    public MediaReplacedElementFactory(ReplacedElementFactory superFactory, File baseSystemPath) {
        this(superFactory);
        this.baseSystemPath = baseSystemPath;
        locator = Locator.SYSTEM_PATH;
    }

    public MediaReplacedElementFactory(ReplacedElementFactory superFactory, String baseClassPath) {
        this(superFactory);
        this.baseClassPath = baseClassPath;
        locator = Locator.CLASS_PATH;
    }

    @Override
    public ReplacedElement createReplacedElement(LayoutContext layoutContext, BlockBox blockBox, UserAgentCallback userAgentCallback, int cssWidth, int cssHeight) {
        Element element = blockBox.getElement();
        if (element == null) {
            return null;
        }

        try{
        
        String nodeName = element.getNodeName();
        String className = element.getAttribute("class");
        // Replace any <div class="media" data-src="image.png" /> with the
        // binary data of `image.png` into the PDF.
        if ("div".equals(nodeName) && "media".equals(className)) {
            if (!element.hasAttribute("data-src")) {
                throw new RuntimeException("An element with class `media` is missing a `data-src` attribute indicating the media file.");
            }

            InputStream inputStream = null;
            try {
                String dataSrc = element.getAttribute("data-src");
                
                inputStream = retrieveInputStream(dataSrc);
                
                if(null!=inputStream){
                	
                    final byte[] bytes = IOUtils.toByteArray(inputStream);
                    
                    if(null!=bytes){
                    	
                        final Image image = Image.getInstance(bytes);
                        
                        if(null!=image){

                            final FSImage fsImage = new ITextFSImage(image);
                            
                            if (fsImage != null) {
                            	
                                if ((cssWidth != -1) || (cssHeight != -1)) {
                                    fsImage.scale(cssWidth, cssHeight);
                                }
                                return new ITextImageElement(fsImage);
                            }                           	
                        }
                    }                	
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw new RuntimeException("There was a problem trying to read a template embedded graphic.", e);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
        }
        
        return this.superFactory.createReplacedElement(layoutContext, blockBox, userAgentCallback, cssWidth, cssHeight);
        
        }catch(Exception e){
        	e.printStackTrace();
        }

        return null;
    }

    private InputStream retrieveInputStream(String dataSrc) throws IOException {
        InputStream inputStream = null;

        switch (locator) {
            case URL:
                String imageUrl = String.format("%s/%s", baseUrl, dataSrc);
                mediaFullLocation = imageUrl;
                logger.info(String.format("imageUrl: %s", imageUrl));
                URL url = new URL(imageUrl);
                URLConnection conn = url.openConnection();
                inputStream = conn.getInputStream();
                break;
            case SYSTEM_PATH:
                String imageSystemPath = String.format("%s/%s", baseSystemPath, dataSrc);
                mediaFullLocation = imageSystemPath;                
                logger.info(String.format("imageSystemPath: %s", imageSystemPath));
                inputStream = new FileInputStream(imageSystemPath);
                break;
            case CLASS_PATH:
                String imageClassPath = String.format("%s/%s", baseClassPath, dataSrc);
                mediaFullLocation = imageClassPath;
                logger.info(String.format("imageClassPath: %s", imageClassPath));
                inputStream = this.getClass().getResourceAsStream(imageClassPath);
                break;
        }

        return inputStream;
    }

    @Override
    public void reset() {
        this.superFactory.reset();
    }

    @Override
    public void remove(Element e) {
        this.superFactory.remove(e);
    }

    @Override
    public void setFormSubmissionListener(FormSubmissionListener listener) {
        this.superFactory.setFormSubmissionListener(listener);
    }
}

