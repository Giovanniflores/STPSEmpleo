package mx.gob.stps.portal.web.candidate.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
//import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.FiltraCandidatoForm;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;

import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;

import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class FiltraCandidatoAction extends PagerAction{
	private static Logger logger = Logger.getLogger(FiltraCandidatoAction.class);
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		FiltraCandidatoForm filterForm = (FiltraCandidatoForm)form;
		filterForm.reset();
		
		UsuarioWebVO webVo = getUsuario(request.getSession());
		filterForm.setIdUsuario(webVo.getIdUsuario());
		filterForm.setIdTipoUsuario(webVo.getIdTipoUsuario());
		filterForm.setIdPerfil(webVo.getIdPerfil());
		
		//paginacion
		List<CandidatoVo> lstCandidatos = new ArrayList<CandidatoVo>();
		request.getSession().setAttribute(FULL_LIST, lstCandidatos);		
		
		request.getSession().setAttribute("FILTER_PAGE", "FILTER_CANDIDATE");
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Candidatos");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Candidatos, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}

	public ActionForward verCandidato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		long idCandidato = Utils.parseLong(request.getParameter("idCandidato"));

		UsuarioWebVO usrWebVo = getUsuario(request.getSession());
		usrWebVo.setIdPropietario(idCandidato);		
		request.getSession().setAttribute("ID_CANDIDATO", idCandidato);	

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(JSP_SUCCESS).getPath());
		return mapping.findForward(JSP_SUCCESS);		
	}	
	
	
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		String nombre = null;
		String apellidoPaterno = null;
		String fecha = null;
		String curp = null;
		String correoElectronico = null;
		String apellido2 = null;
		String telefono = null; 
		long idEntidad = 0;
		long idMunicipio = 0;
		String domicilio = null;
		String usuario = null;		
		Date fechaNacimiento;
		Date dtFechaNacimientoFormateada;
		SimpleDateFormat sdfDestination = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
		FiltraCandidatoForm filterForm = (FiltraCandidatoForm)form;
		ActionErrors errors = filterForm.validate(mapping, request);
		int countNombre = 0;
		int otrosFiltos = 0;
		
		request.getSession().removeAttribute("NUM_PAGE_LIST");
		request.getSession().removeAttribute("TOTAL_PAGES");
		request.getSession().removeAttribute("PAGE_JUMP_SIZE");
		request.getSession().removeAttribute("NUM_PAGE_JUMP");
		request.getSession().removeAttribute("NUM_RECORDS_VISIBLE");
		request.getSession().removeAttribute("NUM_RECORDS_TOTAL");		
		
    	if (errors == null) {
    		List<CandidatoVo> lstCandidatos = new ArrayList<CandidatoVo>();
    		nombre = filterForm.getNombre().trim();
    		apellidoPaterno = filterForm.getApellido1().trim();
    		fecha = filterForm.getFechaNacimiento().trim();
    		curp = filterForm.getCurp().trim();
    		correoElectronico = filterForm.getCorreoElectronico().trim();
    		
    		apellido2 = filterForm.getApellido2();
    		telefono = filterForm.getTelefono();
    		idEntidad = filterForm.getIdEntidad();
    		idMunicipio = filterForm.getIdMunicipio();
    		domicilio = filterForm.getDomicilio();
    		usuario = filterForm.getUsuario();    		
    		
    		try{
            	//SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
    			//java.util.Date dtFechaNacimientoFormateada;
    			if(nombre.length() != 0)
    				countNombre++;
    			if(apellidoPaterno.length() != 0)
    				countNombre++;
    			
    			if(fecha.length() != 0)
    				otrosFiltos++;
    			if(curp.length() != 0)
    				otrosFiltos++;
    			if(correoElectronico.length() != 0)
    				otrosFiltos++;

    			
    			if(!filterForm.getFechaNacimiento().isEmpty()){
    				fechaNacimiento = sdfSource.parse(filterForm.getFechaNacimiento());
    				String fechaNacimientoFormateada = sdfDestination.format(fechaNacimiento);
    				dtFechaNacimientoFormateada = sdfDestination.parse(fechaNacimientoFormateada); 	
    			} else {
    				dtFechaNacimientoFormateada = null;
    			}    			

//    			if(nombre.length() == 0 && apellidoPaterno.length() == 0 && fecha.length() == 0 && curp.length() == 0 && correoElectronico.length() == 0){
//    				//request.setAttribute("noFiltros", "Se debe ingresar información en alguno de los filtros");
//    				request.getSession().setAttribute(FULL_LIST, lstCandidatos);
//    			}else if(countNombre != 0 && countNombre <2 && otrosFiltos == 0){
//    				request.setAttribute("noFiltros", "Se debe proporcionar Nombre y Apellido del Candidato");
//    				request.getSession().setAttribute(FULL_LIST, lstCandidatos);
//    			}else{
//    				CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
//		    		lstCandidatos = services.filtrarCandidatos(nombre, apellidoPaterno, dtFechaNacimientoFormateada, curp, correoElectronico, apellido2, telefono, idEntidad, idMunicipio, domicilio, usuario);
//		
//		    		filterForm.setLstCandidatos(lstCandidatos);
//		    		//paginacion
//		    		request.getSession().setAttribute(FULL_LIST, lstCandidatos);
//    			}

				CandidatoBusDelegate services = CandidatoBusDelegateImpl.getInstance();
	    		lstCandidatos = services.filtrarCandidatos(nombre, apellidoPaterno, dtFechaNacimientoFormateada, curp, correoElectronico, apellido2, telefono, idEntidad, idMunicipio, domicilio, usuario);
	
	    		filterForm.setLstCandidatos(lstCandidatos);
	    		//paginacion
	    		request.getSession().setAttribute(FULL_LIST, lstCandidatos);    			
			} catch (ParseException e) {
				registraError(request, e.getMessage());
			} catch (SQLException e) {
				registraError(request, e.getMessage());
			} catch (Exception e) {
				registraError(request, e.getMessage());
			}

			//String type = ResultVO.TYPE_SUCCESS;
			
	        request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

			PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Candidatos");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Candidatos, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
	        return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);	        				
			
		} else {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Favor de revisar los datos proporcionados."));		
			request.setAttribute(Globals.ERROR_KEY, errors);
			//DEBUG
			@SuppressWarnings("rawtypes")
			Iterator itMessages = errors.get();
			while(itMessages.hasNext()){				
				Object actionMsg = itMessages.next(); 
				System.out.println("******-----errorAction:" + actionMsg.toString());
			}
			//END DEBUG    		
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Candidatos");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Candidatos, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
	    return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);			    		
		
	}
	
	
	public ActionForward consultarEntidades(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CatalogoOpcionDelegate service = CatalogoOpcionDelegateImpl.getInstance();			
			List<CatalogoOpcionVO> opciones = service.consultarCatalogo(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
			int i = 0;
			int iRemove = 0;
			for (CatalogoOpcionVO o: opciones){
				if (o.getIdCatalogoOpcion() == (ENTIDADES_FEDERATIVAS.NACIDO_EN_EL_EXTRANJERO.getIdEntidad())){
					iRemove = i;
				}
				i++;
			}
			opciones.remove(iRemove);			
			redirectJson(response, opciones);
		} catch (PersistenceException e) {
			logger.error("Error en FiltraCandidatoAction.consultarCatalogo");			
			logger.error(e);
		} catch (ServiceLocatorException e) {
			logger.error("Error en FiltraCandidatoAction.consultarCatalogo");			
			logger.error(e);			
		} catch (IOException e) {
			logger.error("Error en FiltraCandidatoAction.consultarCatalogo");			
			logger.error(e);
		}
		return null;
	}
	
	public ActionForward consultarMunicipio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		String idEntidad = request.getParameter("idEntidad");
		try{
			if (idEntidad != null && !"".equals(idEntidad) && !"0".equals(idEntidad)){
				DomicilioBusDelegate service = DomicilioBusDelegateImpl.getInstance();
				List<CatalogoOpcionVO> municipios = service.consultaMunicipios(Long.valueOf(idEntidad));
				redirectJson(response, municipios);
			}
		} catch (SQLException e) {
			logger.error("Error en FiltraCandidatoAction.consultarMunicipio");			
			logger.error(e);			
		} catch (PersistenceException e) {
			logger.error("Error en FiltraCandidatoAction.consultarMunicipio");			
			logger.error(e);			
		} catch (ServiceLocatorException e) {
			logger.error("Error en FiltraCandidatoAction.consultarMunicipio");			
			logger.error(e);
		} catch (IOException e) {
			logger.error("Error en FiltraCandidatoAction.consultarCatalogo");			
			logger.error(e);
		}
		return null;
	}
	
	private void redirectJson(HttpServletResponse response, List<CatalogoOpcionVO> opciones) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}
	
}
