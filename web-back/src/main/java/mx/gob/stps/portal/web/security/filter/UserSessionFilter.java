package mx.gob.stps.portal.web.security.filter;

import static mx.gob.stps.portal.core.infra.utils.Constantes.SESION_ACTIVA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.ACTION_REQUESTED;
import static mx.gob.stps.portal.web.infra.utils.Constantes.HOST_CIL;
import static mx.gob.stps.portal.web.infra.utils.Constantes.HOST_PORTAL;
import static mx.gob.stps.portal.web.infra.utils.Constantes.MSG_ERROR_SESSION;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.ActionsGenerales.ACTIONS_SIN_FILTRO_SEGURIDAD;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.web.infra.utils.MessageLoader;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.action.LoginAction;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;

/**
 * Filtra las peticiones hacia los action declarados en el archivo de configuracion de struts 
 * 
 * @author oscar.manzo
 */
public final class UserSessionFilter implements Filter {
    private static Logger logger = Logger.getLogger(UserSessionFilter.class);

    private FilterConfig filterConfig;
    
    public void init(FilterConfig filterConfig) throws ServletException {
    	this.filterConfig = filterConfig;
        // ServletContext application = filterConfig.getServletContext();
    }

    public void destroy() {
        filterConfig = null;
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest httpRequest = (HttpServletRequest)request;
    	HttpServletResponse httpResponse = (HttpServletResponse) response;

    	HttpSession session = httpRequest.getSession(false);

    	//logger.warn("RemoteHost:"+ httpRequest.getRemoteHost() +" RemoteAddr:"+ httpRequest.getRemoteAddr() +" RemotePort:"+ httpRequest.getRemotePort());
    	
    	// PARA PRUEBAS DE INVOCACION EN LA REPLICACION DE SESSIONES
    	/*String URL_LOCAL = httpRequest.getScheme()+"://"+ httpRequest.getServerName() +":"+ httpRequest.getServerPort()+ httpRequest.getContextPath() +"/"+ httpRequest.getRequestURI() + httpRequest.getQueryString();
    	logger.info("Invocando ---> "+ URL_LOCAL);*/

    	/*String referer = httpRequest.getHeader("Referer");
    	logger.info("Referer ---> "+ referer);*/
    	
    	/** SE AGREGA LA CABECERA DE P3P PARA RESOLVER EL BLOQUEO DE COOKIES EN IE Y EVITAR QUE GENERE NUEVAS SESIONES **/
    	String headerp3p = PropertiesLoader.getInstance().getProperty("app.header.p3p");
    	httpResponse.setHeader("P3P", headerp3p);
    	//httpResponse.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");

    	String actionName = getActionName(httpRequest.getServletPath());

    	/* CALS quitar
    	if (!ACTIONS_SIN_FILTRO_SEGURIDAD.getActions().contains(actionName) && session==null){
    		logger.error("Se ha detectado un ingreso SIN SESSION solicitando en ingreso a "+ actionName +" !!!");
    		session = httpRequest.getSession(true); // Crea la sesion y direcciona hacia home
    		
    		if (hasUserCookie(httpRequest))
    			session.setAttribute(MSG_ERROR_SESSION, MessageLoader.getInstance().getMessage("seguridad.sesion.destroyed.msg"));

    		request.setAttribute(URL_REDIRECT_SWB, PropertiesLoader.getInstance().getProperty("app.swb.redirect.logout.home"));
			filterConfig.getServletContext().getRequestDispatcher("/redirectswb.do").forward(request, response);
			return;
    	}
    	*/
    	try{
    		recreateUserCookie(httpRequest, httpResponse);
    		
    		if (session==null) session = httpRequest.getSession(true);

        	session.setAttribute(ACTION_REQUESTED, actionName); //  Se registra el action invocado

        	validaHostAcceso(httpRequest); // Se verifica el dominio de acceso al sitio
        	
        	/** A CAUSA DE PROBLEMAS EN EL CLUSTER POR LA REPLICACION DE SESIONES
        	 * SE REESCRIBE EL USUARIO EN LA SESION, YA QUE INDICA QUE ASI SE ACTIVA LA REPLICA DE LA MISMA **/
        	UsuarioWebVO usuariologged = (UsuarioWebVO)session.getAttribute(USUARIO_APP);
        	if (usuariologged!=null) session.setAttribute(USUARIO_APP, usuariologged);

        	/** Se veirifca el usuario firmado, en caso de no localizarse se busca la cookie del usuario,
        	 * si se encuentra se firma al usuario dentro del sitio **/
        	if (usuariologged==null){

        		String username = null;
        		boolean usercookie = false;
        		
        		Cookie cookie = CookieManager.getInstance().findUserCookie(httpRequest);
    			if (cookie!=null  && cookie.getValue()!=null && !cookie.getValue().isEmpty() ){
    				username = cookie.getValue();

    				if (username!=null && !username.isEmpty())
    					usercookie = true;
    			}
        		
        		if (usercookie){
        			logger.info("Cookie de Usuario valida, se autofirma al usuario dentro del Portal.");
        			try {
        				SecutityDelegate services = SecutityDelegateImpl.getInstance();
    					UsuarioVO usuario = services.consultaUsuarioPorLogin(username);
    					
    					if (usuario!=null){
    						boolean isSesionActiva = usuario.getSesionActiva() == SESION_ACTIVA;
    						logger.info("Usuario con sesion activa ["+ isSesionActiva +"]");

    						//if (isSesionActiva){
    						LoginAction loginAction = new LoginAction();
    						loginAction.firmaUsuarioPortal(httpRequest, httpResponse, usuario);
    						//}
    					}
    				} catch (Exception e) {logger.error(e);}
        		}
        	}
        	
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error(e);
    	}
    	
        filterChain.doFilter(request, response);
        return;
    }

	private String getActionName(String servletPath) {

		if (servletPath != null) {
			servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1, servletPath.lastIndexOf("."));
		}

		return servletPath;
	}

	/**
	 * Verifica el parametro del host y establece la bandera en session para indicar el dominio donde entra el usuario
	 * Valida si entra desde un CIL
	 * 
	 * @param request
	 */
	private void validaHostAcceso(HttpServletRequest request){
		HttpSession session = request.getSession();

		if (session.getAttribute(HOST_PORTAL)!=null) return;

		PropertiesLoader properties = PropertiesLoader.getInstance();
		String hostparam = properties.getProperty("cil.host.param.name");
		String host = request.getParameter(hostparam);
		
		if (host==null || host.isEmpty()) return;
		host = host.trim().toLowerCase();

		String regex = properties.getProperty("cil.host.pattern");

		if (host.matches(regex))
			session.setAttribute(HOST_PORTAL, HOST_CIL);
		
	}

	/**
	 * Mientras se cuente con una sesion y un usuario firmado en el Portal, se verifica que exista la Cookie del usuario
	 * en caso contrario se vuelve a crear, hasta que el usuario finalice la sesion de manera explicita se elimina la Cookie
	 * Si sale del Portal y no regresa durante el tiempo de vida de la Cookie esta se elimina automaticamente
	 * @param request
	 * @param response
	 */
	private void recreateUserCookie(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		if (session==null) return;

		UsuarioWebVO usuario = (UsuarioWebVO)session.getAttribute(USUARIO_APP);
		if (usuario==null) return;
		
		Cookie cookie = CookieManager.getInstance().findUserCookie(request);
		if (cookie==null)
			CookieManager.getInstance().createUserCookie(response, usuario.getUsuario());
	}
	
	private boolean hasUserCookie(HttpServletRequest request){
		Cookie cookie = CookieManager.getInstance().findUserCookie(request);
		return cookie!=null;
	}
	
}