/**
 * Author: OMAROMAN
 * Date: 8/6/13
 * Time: 10:31 AM
 */
package mx.gob.stps.portal.web.infra.utils;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

@SuppressWarnings("unchecked")
public class UrlUtils {
	
	@SuppressWarnings("rawtypes")
	public static final HashMap m = new HashMap();
	
	static {
		m.put(34, ""); // ""
		m.put(40, "");   // (
		m.put(41, "");   // )
		m.put(47, "");   // /
		m.put(60, "");   // <
		m.put(61, "");   // =
		m.put(62, "");   // >
	}

    /**
     * Returns the URL (including query parameters) minus the scheme, host, and
     * context path.  This method probably be moved to a more general purpose
     * class.
     */
    public static String obtainRelativeUrl(HttpServletRequest request) {

        String baseUrl;

        if ((request.getServerPort() == 80) || (request.getServerPort() == 443)) {
            baseUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/";
        } else {
            baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        }

        StringBuffer buf = request.getRequestURL();

        if (request.getQueryString() != null) {
            buf.append("?");
            buf.append(request.getQueryString());
        }

        return buf.substring(baseUrl.length());
    }

    /**
     * Returns the base url (e.g, <tt>http://myhost:8080/myapp</tt>) suitable for
     * using in a base tag or building reliable urls.
     */
    public static String obtainBaseUrl( HttpServletRequest request ) {
        if ((request.getServerPort() == 80) || (request.getServerPort() == 443)) {
            return request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/";
        } else {
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        }
    }

    /**
     * Returns the file specified by <tt>path</tt> as returned by
     * <tt>ServletContext.getRealPath()</tt>.
     */
    public static File obtainRealFile(HttpServletRequest request, String path ) {
        return new File(request.getSession().getServletContext().getRealPath(path));
    }
    
    public static String suprXSS(String str) {
		try {
			StringWriter writer = new StringWriter((int)(str.length() * 1.5));
			dispersion(writer, str);
			return writer.toString();
		}catch (IOException ioe) {
			ioe.printStackTrace();
			return null;  
		}
    }

    public static void dispersion(Writer writer, String str) throws IOException {
    	int len = str.length();
    	for (int i = 0; i < len; i++) {
    		char c = str.charAt(i);
    		int ascii = (int) c;
    		String entityName = (String) m.get(ascii);
    		if (entityName == null) {
    			if (c > 0x7F) {
    				writer.write("&#");
    				writer.write(Integer.toString(c, 10));
    				writer.write(';');
    			} else {
    				writer.write(c);
    			}
    		} else {
    			writer.write(entityName);
    		}
    	}
    }
}
