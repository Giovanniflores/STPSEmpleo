package mx.gob.stps.portal.web.infra.utils.fs;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import org.apache.commons.codec.binary.Base64;
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

import java.io.IOException;


public class B64ImgReplacedElementFactory implements ReplacedElementFactory {

    public ReplacedElement createReplacedElement(LayoutContext c, BlockBox box, UserAgentCallback uac, int cssWidth, int cssHeight) {
        
    	try{

    	Element e = box.getElement();
        if (e == null) {
            return null;
        }
        String nodeName = e.getNodeName();
        if (nodeName.equals("img")) {
            String attribute = e.getAttribute("src");
            FSImage fsImage;
            try {
                fsImage = buildImage(attribute, uac);
            } catch (BadElementException e1) {
                fsImage = null;
            } catch (IOException e1) {
                fsImage = null;
            }
            if (fsImage != null) {
                if (cssWidth != -1 || cssHeight != -1) {
                    fsImage.scale(cssWidth, cssHeight);
                }
                return new ITextImageElement(fsImage);
            }
        }

    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        
        return null;
    }

    protected FSImage buildImage(String srcAttr, UserAgentCallback uac) throws IOException, BadElementException {
        FSImage fsImage = null;
        
        try{

        if (srcAttr.startsWith("data:image/")) {
            String b64encoded = srcAttr.substring(srcAttr.indexOf("base64,") + "base64,".length(), srcAttr.length());
            byte[] decodedBytes = Base64.decodeBase64(b64encoded.getBytes());
            fsImage = new ITextFSImage(Image.getInstance(decodedBytes));
        } else {
            fsImage = uac.getImageResource(srcAttr).getImage();
        }
        
        }catch(Exception e){
        	System.out.println("buildImage.ImageResource:"+ srcAttr);
        	e.printStackTrace();
        }
        
        return fsImage;
    }

    public void remove(Element e) {
    }

    @Override
    public void setFormSubmissionListener(FormSubmissionListener formSubmissionListener) {
        // DO Nothing...
    }

    public void reset() {
    }
}
