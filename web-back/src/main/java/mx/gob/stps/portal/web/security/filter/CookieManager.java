package mx.gob.stps.portal.web.security.filter;

import static mx.gob.stps.portal.web.infra.utils.Constantes.COOKIE_USUARIO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CookieManager {
	
	private static final CookieManager INSTANCE = new CookieManager();
	
	private CookieManager(){}
	
	public static CookieManager getInstance(){
		return INSTANCE;
	}
	
    public void createUserCookie(HttpServletResponse response, String userName){
    	createCookie(response, COOKIE_USUARIO, userName);
    }

    public void removeUserCookie(HttpServletRequest request, HttpServletResponse response){
    	removeCookie(request, response, COOKIE_USUARIO);    	
    }

    public void removeUsuarioCookie(HttpServletRequest request, HttpServletResponse response){
    	removeCookieUsuario(request, response, COOKIE_USUARIO);    	
    }
    public Cookie findUserCookie(HttpServletRequest request){
		return findCookie(request, COOKIE_USUARIO);
	}

    public void createCookie(HttpServletResponse response, String cookiename, String cookievalue){
        Cookie cookie = new Cookie(cookiename, cookievalue);
        cookie.setMaxAge(60 * 15); // min
        cookie.setSecure(true);
        response.addCookie(cookie);    	
    }

    private void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookiename){
    	// Se busca y elimina la cookie de sesion de usuario
    	Cookie[] cookies = request.getCookies();
    	for(int i=0;i<cookies.length;i++)
    	if (cookies!=null){
    		for (Cookie cookie : cookies){
    			
    			if (cookiename.equals(cookie.getName())){
    				cookie.setValue("");
    				cookie.setMaxAge(0); // Elimina la Cookie
    				response.addCookie(cookie);
    			}
    		}
    	}
    }
    
    private void removeCookieUsuario(HttpServletRequest request, HttpServletResponse response, String cookiename){
    	// Se busca y elimina la cookie de sesion de usuario
    	Cookie cookie = new Cookie(cookiename, "");
		cookie.setValue("");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

	@SuppressWarnings("unused")
	public void removeCookieRediIt(HttpServletRequest request,HttpServletResponse response, String cookiename) {
		// Se busca y elimina la cookie de sesion de red it
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
//				System.out.println("-------cookie------- " + cookie.getName());
//				System.out.println("------cookie path------ " + cookie.getPath());
				if (cookiename.equals(cookie.getName()) && (cookie.getPath() != null && cookie.getPath() != "/")) {
					cookie.setValue("");
					cookie.setMaxAge(0); // Elimina la Cookie
					response.addCookie(cookie);
				}
			}
		}
	}

    public Cookie findCookie(HttpServletRequest request, String cookiename){
		Cookie usercookie = null;

		if (request.getCookies()!=null){
			for (Cookie cookie : request.getCookies()){
				if (cookiename.equalsIgnoreCase(cookie.getName())){
					usercookie = cookie;
					break;
				}
			}
	    }
		
		return usercookie;
	}
    
}
