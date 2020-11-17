package mx.gob.stps.portal.web.offer.action;

import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.seguridad.RegistroUsuarioAplicacion;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.exception.LoginException;
import mx.gob.stps.portal.exception.PasswordException;
import mx.gob.stps.portal.utils.Password;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.offer.wrapper.PerfilJB;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegate;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

public class MyOffersEmployAction extends GenericAction {

    private static Logger logger = Logger.getLogger(MyOffersEmployAction.class);
    
    private long idCandidato;

    @Override
    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Mis ofertas de empleo");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Mis ofertas de empleo, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_OFFER);
    }
 
    public ActionForward genera(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		InformacionGeneralVO infoVO = null;
		UsuarioWebVO usuario = getUsuario(request.getSession());
		try{
			if(usuario== null){
	    		logger.info("JGLC sesion caduca en generacion del cv");
				String errmsg = "<b>Su sesión ha caducado, intentelo nuevamente.</b>";
				request.setAttribute(ERROR_MSG, errmsg);
				return mapping.findForward(FORWARD_INICIO);
			}
			
			long idCandidato = Utils.parseLong(request.getParameter("idCandidato"));
			logger.debug(String.format("idCandidato: %d", idCandidato));
	        			
			if (idCandidato > 0) {
				CandidatoBusDelegate candidateServices = CandidatoBusDelegateImpl.getInstance();
				infoVO = candidateServices.showCandidateDetail(idCandidato);
			}			
			request.setAttribute("detalle", infoVO);
			request.setAttribute("idCandidato", idCandidato);

            if (usuario != null) {
                SecutityDelegateImpl.getInstance().consultaCurriculo(usuario.getIdUsuario(), usuario.getIdPerfil());
            }            

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(JSP_SUCCESS).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Mi Curriculum");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Visualizacion del mi curriculum");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_CAND);
    }

    public ActionForward generaImpresion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	logger.debug("Inicia metodo generaImpresion...");
		InformacionGeneralVO infoVO = null;
		UsuarioWebVO usuario = getUsuario(request.getSession());
		try{
			if(usuario== null){
	    		validaUsuario(request);
			}
			if (idCandidato > 0) {
				CandidatoBusDelegate candidateServices = CandidatoBusDelegateImpl.getInstance();
				infoVO = candidateServices.showCandidateDetail(idCandidato);
				logger.debug("Se obtuvo la informacion y se registro la consulta.......");
			}	
			request.setAttribute("detalle", infoVO);
			request.setAttribute("idCandidato", idCandidato);
            if (usuario != null) {
                SecutityDelegateImpl.getInstance().consultaCurriculo(usuario.getIdUsuario(), usuario.getIdPerfil());
            }            
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(JSP_SUCCESS).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Mi Curriculum");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Visualizacion del mi curriculum para imprimir");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(JSP_SUCCESS);
    }

	
    private void validaUsuario(HttpServletRequest request) throws Exception{
    	logger.debug("Inicia metodo validaUsuario...");
    	
    	StringBuilder token = new StringBuilder();
    	String userName = null;
    	String password = null;
    	String parametro = "";
    	UsuarioVO usuario = null;
    	
    	if (request.getParameter("token") != null && request.getParameter("token").length()>0) {
    		if (request.getParameter("token").contains(" ")){
        		token = new StringBuilder(request.getParameter("token").toString().replace(" ", "+"));
        	} else {    		
        		token.append(request.getParameter("token"));
        	}
    	}
    	
    	parametro = Password.decrypt(token.toString().trim());
    	userName = parametro.substring(parametro.indexOf("U:")+2, parametro.indexOf("&P:"));
    	password= parametro.substring(parametro.indexOf("P:")+2, parametro.indexOf("&ID:"));
    	idCandidato= Long.parseLong(parametro.substring(parametro.indexOf("ID:")+3));   
    	RegistroUsuarioAplicacion.registrarUsuarioLogeado(userName);
    	SecutityDelegate services = SecutityDelegateImpl.getInstance();
		usuario = services.consultaUsuarioPorLogin(userName);
		if (usuario==null) throw new LoginException("Usuario no localizado.");
		
		boolean isPswValid = validatePassword(usuario.getContrasena(), password);
		if (!isPswValid) throw new PasswordException("Contraseña incorrecta");
		
	}
	
    /**
     * Valida con correspondencia de la contraseña
     * @param storedPassword
     * @param password
     * @return
     * @throws Exception
     */
    private boolean validatePassword(String storedPassword, String password) throws Exception {
    	if (storedPassword==null || storedPassword.isEmpty()) return false;
    	if (password==null || password.isEmpty()) return false;
        String codificadoPassword = Password.codificaPassword(password);
        if (!storedPassword.equals(codificadoPassword)) return false;

        return true;
    }
}
