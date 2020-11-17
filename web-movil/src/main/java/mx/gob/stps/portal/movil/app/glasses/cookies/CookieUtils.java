package mx.gob.stps.portal.movil.app.glasses.cookies;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class CookieUtils {

	
	 public static Cookie findCookie(final Cookie cookies[], final String cookieName) {
	        List<Cookie> cookiesList = Arrays.asList(cookies);
	        for (Cookie cookie : cookiesList) {
	            if(cookie.getName().equals(cookieName)) {
	                return cookie;
	            }
	        }
	        return null;
	    }

	    /*
	     * This method returns (as String) the corresponding value for the cookie
	     * with the given 'cookieName' name if it exists,
	     * otherwise, it returns an empty string.
	     */
	    public static String readCookieValueByName(final Cookie cookies[], final String cookieName) {
	        Cookie cookie = findCookie(cookies, cookieName);
	        return cookie != null ? cookie.getValue() : "";
	    }

	    public static String readCookie(final HttpServletRequest request, final String name) {
	        Cookie cookie = getCookieByName(request, name);
	        if (cookie != null) {
//	            if (cookie.getPath() == null) {
//	                return null;
//	            }
	            return cookie.getValue();
	        }
	        return null;
	    }

	    /*
	    * This method adds the (name, value) cookie pair to the HttpServletResponse having expiration time
	    */
	    public static void writeCookie(final HttpServletRequest request, final HttpServletResponse response, final String name, final String value, final int expiration) {
	        Cookie cookie = new Cookie(name, value);
	        cookie.setMaxAge(expiration);
	        try {
	            cookie.setHttpOnly(true);
	        } catch (NoSuchMethodError e) {
	            //Logger.warn("This servlet container is kind of oldie!!!");
	        }
	        cookie.setPath(request.getContextPath());
	        response.addCookie(cookie);
	    }

	    public static void deleteCookie(final HttpServletRequest request, final HttpServletResponse response, final String cookieName) {
//	        Cookie cookie = findCookie(request.getCookies(), cookieName);
	        Cookie cookie = getCookieByName(request, cookieName);
	        if (cookie != null) {
	            cookie.setValue(null);
	            cookie.setMaxAge(0); // delete cookie
	            cookie.setPath(request.getContextPath()); // path must match in order to delete the cookie
	            response.addCookie(cookie);
	        }
	    }

	    /*
	     * This method updates the value for the 'name' named cookie if it already exists,
	     * otherwise, it creates a new (name,value) cookie and adds it to the HttpServletResponse
	     */
	    public static void updateCookie(final HttpServletResponse response, final String name, final String value) {
	        response.addCookie(new Cookie(name, value));
	    }

	    /* ¡¡¡¡¡WELL KNOWN FLASH COOKIES (INFO, WARNING, ERROR, SUCCESS)!!!!! */

	    public static void writeFlashCookie(final HttpServletRequest request, final HttpServletResponse response, final CookieFlash cookieFlash, final String value) {
	        final int MAX_AGE = 10; // in seconds
	        writeCookie(request, response, cookieFlash.getName(), value, MAX_AGE);
	    }

	    public static String readFlashCookie(final HttpServletRequest request, final CookieFlash cookieFlash) {
	        return readCookie(request, cookieFlash.getName());
	    }

	    public static void deleteFlashCookie(final HttpServletRequest request, final HttpServletResponse response, final CookieFlash cookieFlash) {
	        deleteCookie(request, response, cookieFlash.getName());
	    }
	    

	    public static String readCookieByName(final HttpServletRequest request, final String name) {
	        Cookie cookie = getCookieByName(request, name);
	        if (cookie != null) {
//	            if (cookie.getPath() == null) {
//	                return null;
//	            }
	            return cookie.getValue();
	        }
	        return null;
	    }
	    
	    
	    public static Cookie getCookieByName(final HttpServletRequest request, final String name){
	        Cookie[] cookies = request.getCookies();
	        for(Cookie cookie : cookies ){
	            if(cookie.getName().equals(name)){
	                return cookie;
	            }
	        }
	        return null;
	    }
}
