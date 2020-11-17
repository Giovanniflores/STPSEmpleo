package mx.gob.stps.portal.web.security.filter;

import static mx.gob.stps.portal.web.infra.utils.Constantes.ACCIONES_REQ_AUTENTICACION;
import static mx.gob.stps.portal.web.infra.utils.Constantes.MSG_ERROR_SESSION;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.ActionsGenerales.ACTIONS_SIN_FILTRO_SEGURIDAD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.seguridad.vo.AccionVO;
import mx.gob.stps.portal.web.infra.utils.MessageLoader;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;

/**
 * Filtra las peticiones hacia los action declarados en el archivo de configuracion de struts 
 * Verifica que el usuario sea valido, y tenga los permisos de acceso a los recursos contenidos de determinado action
 * 
 * @author oscar.manzo
 */
public final class SecurityFilter implements Filter {
    private static Logger logger = Logger.getLogger(SecurityFilter.class);

    private FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        // ServletContext application = filterConfig.getServletContext();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest httpRequest = (HttpServletRequest)request;

    	HttpSession session = httpRequest.getSession();

    	String actionName = getActionName(httpRequest.getServletPath());

    	try{
        	/** Se verifican los permisos del modulo y los asociados al perfil del usuario **/
    		if (requiereAutenticacion(actionName, session)) {

        		MessageLoader messages = MessageLoader.getInstance();
            	PropertiesLoader properties = PropertiesLoader.getInstance();

            	UsuarioWebVO usuariologged = (UsuarioWebVO)session.getAttribute(USUARIO_APP);

        		if (usuariologged!=null){
        			
        			if (usuariologged.getAcciones()!=null && !usuariologged.getAcciones().isEmpty()){

        				if (usuariologged.getAcciones().contains(actionName)){
        					filterChain.doFilter(request, response);
        					return;
        				} else {
        					logger.info("Usuario ["+ usuariologged.getIdUsuario() +"] no cuenta con permisos para ["+ actionName +"]");
        					session.setAttribute(MSG_ERROR_SESSION, messages.getMessage("seguridad.sesion.msg.sinpermisos"));
        		    		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.logout.home"));
        					filterConfig.getServletContext().getRequestDispatcher("/redirectswb.do").forward(request, response);
        					//request.setAttribute(ERROR_MSG, messages.getMessage("seguridad.sesion.msg.sinpermisos"));
        					//filterConfig.getServletContext().getRequestDispatcher("/sinpermisos.do").forward(request, response);
                			return;
        				}
        			} else {
        				logger.info("Usuario ["+ usuariologged.getIdUsuario() +"] no cuenta con permisos asociados");
        				session.setAttribute(MSG_ERROR_SESSION, messages.getMessage("seguridad.sesion.msg.sinpermisos"));
    		    		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.logout.home"));
    					filterConfig.getServletContext().getRequestDispatcher("/redirectswb.do").forward(request, response);
        				//request.setAttribute(ERROR_MSG, messages.getMessage("seguridad.sesion.msg.sinpermisos"));
            			//filterConfig.getServletContext().getRequestDispatcher("/sinpermisos.do").forward(request, response);
            			return;
        			}
        		} else {
        			logger.info("Usuario NO autenticado ["+ actionName +"]");
        			session.setAttribute(MSG_ERROR_SESSION, messages.getMessage("seguridad.sesion.msg.nologged"));
    	    		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.logout.home"));
    				filterConfig.getServletContext().getRequestDispatcher("/redirectswb.do").forward(request, response);
        			//request.setAttribute(ERROR_MSG, messages.getMessage("seguridad.sesion.msg.nologged"));
        			//filterConfig.getServletContext().getRequestDispatcher("/sinpermisos.do").forward(request, response);
        			return;
        		}
        	}
        	
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error(e);
    	}
    	
        filterChain.doFilter(request, response);
        return;
    }

    public void destroy() {
        filterConfig = null;
    }

	private String getActionName(String servletPath) {

		if (servletPath != null) {
			servletPath = servletPath.substring(servletPath.lastIndexOf("/") + 1, servletPath.lastIndexOf("."));
		}

		return servletPath;
	}
	
	private boolean requiereAutenticacion(String actionName, HttpSession session){
		boolean aplica = false; // Por defecto todas requieren autenticacion

		if (actionName==null || actionName.trim().isEmpty()) return false;
		
		// Filtros que NO requieren configuracion en la base de datos
		if (ACTIONS_SIN_FILTRO_SEGURIDAD.getActions().contains(actionName)){
			return false;
		}
		
		@SuppressWarnings("unchecked")
		List<String> accionesPath = (List<String>)session.getAttribute(ACCIONES_REQ_AUTENTICACION);		

		if (accionesPath==null){
			accionesPath = consultaAccionesRequierenAutenticacion();
			session.setAttribute(ACCIONES_REQ_AUTENTICACION, accionesPath);
		}

		aplica = accionesPath.contains(actionName);
		
		return aplica;
	}

	private List<String> consultaAccionesRequierenAutenticacion(){
		List<String> accionesPath = new ArrayList<String>();

		try {
			logger.info("Se cargan permisos sobre recursos del sitio.");
			SecutityDelegate services = SecutityDelegateImpl.getInstance();
			List<AccionVO> acciones = services.consultaAccionesRequierenAutenticacion();

			if (acciones!=null && !acciones.isEmpty()){
				for (AccionVO accion : acciones){
					accionesPath.add(accion.getVinculo());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return accionesPath;
	}

}