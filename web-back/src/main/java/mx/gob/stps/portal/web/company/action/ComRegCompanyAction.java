package mx.gob.stps.portal.web.company.action;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.address.action.DomicilioAction;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.ComRegCompanyForm;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * Administra los estados de la presentacion para el formulario de Registro de Empresa
 * 
 * @author haydee.vertti
 */
@Deprecated
public final class ComRegCompanyAction extends DomicilioAction  {

	private static Logger logger = Logger.getLogger(ComRegCompanyAction.class);
	
	/**
	 * Method 'init'
	 * Inicializa la plantilla
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */	
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro de Compa&ntilde;ia");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_FORM);		
	}
	
	public ActionForward registrarExitoso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
		return mapping.findForward("JSP_SUCCESS");		
	}		
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.setAttribute(URL_REDIRECT_SWB, properties.getProperty("app.swb.redirect.home"));
		return mapping.findForward(FORWARD_REDIRECT_SWB);
	}		
	
	
	/**
	 * Method 'registrar'
	 * Carga los catálogos necesarios para registrar una empresa por autorizar
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */		
    public ActionForward registrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
        	ComRegCompanyForm usuForm = (ComRegCompanyForm) form;       
        	usuForm.reset();
        	usuForm.setIdTipoTelefono(2);
        	usuForm.setCodigoPostal("");
        	usuForm.setCalle("");
        	usuForm.setNumeroExterior("");
        	usuForm.setNumeroInterior("");
        	usuForm.setEntreCalle("");
        	usuForm.setyCalle("");

            // Se inicializan catalogos y se suben a sesión
            CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();
            request.getSession().setAttribute("TERMINOS_CONDICIONES", Constantes.TERMINOS_CONDICIONES); 
            request.getSession().setAttribute(CAT_TIPO_EMPRESA, serviceCat.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_EMPRESA));
            request.getSession().setAttribute(CAT_ACTIVIDAD_ECONOMICA, serviceCat.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA));
            request.getSession().setAttribute(CAT_MEDIO_ENTERADO, serviceCat.consultarCatalogo(Constantes.CATALOGO_OPCION_MEDIO_ENTERADO));
            //CATALOGOS RELACIONADOS CON TELEFONOS
            request.getSession().setAttribute(CAT_TIPO_TELEFONO, serviceCat.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_TELEFONO));            
            List<CatalogoOpcionVO> opcionesTipoTelefono = serviceCat.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_TELEFONO);
			Iterator itLstTipoTelefono = opcionesTipoTelefono.iterator();
			while(itLstTipoTelefono.hasNext()){
				CatalogoOpcionVO vo = (CatalogoOpcionVO) itLstTipoTelefono.next();
				if(vo.getOpcion().equalsIgnoreCase("celular")){
					request.getSession().setAttribute(TELEFONO_CELULAR_ID, vo.getIdCatalogoOpcion());
					request.getSession().setAttribute(TELEFONO_CELULAR_DES, vo.getOpcion());
				} else {
					request.getSession().setAttribute(TELEFONO_FIJO_ID, vo.getIdCatalogoOpcion());
					request.getSession().setAttribute(TELEFONO_FIJO_DES, vo.getOpcion());					
				}				
			}						
			request.getSession().setAttribute("CLAVE_TELEFONO_FIJO", Constantes.CLAVE_TELEFONO_FIJO);
			request.getSession().setAttribute("CLAVE_TELEFONO_CELULAR", Constantes.CLAVE_TELEFONO_CELULAR); 
			
			usuForm.setHiddenDesActividadEconomica(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, usuForm.getIdActividadEconomica()));
			usuForm.setHiddenDesTipoEmpresa(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_EMPRESA,usuForm.getIdTipoEmpresa()));
			usuForm.setHiddenDesMedio(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_MEDIO_ENTERADO, usuForm.getIdMedio()));
						
        } catch (ServiceLocatorException e) {
            logger.error(e);
            registraError(request, "app.exp.locator.err");
        } catch (Exception e) {
            logger.error(e);
            registraError(request, "app.exp.negocio.err");
        }                       
        request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro de Compa&ntilde;ia");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);
    }	
    
    
	/**
	 * Obtiene los datos capturados por el usuario en el formulario e invoca el registro de empresa por autorizar
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */    
    public ActionForward salvar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	ComRegCompanyForm usuForm = (ComRegCompanyForm) form;    	
		String msg = null;
		String type = null;    	
    	ActionErrors errors = usuForm.validate(mapping, request);           

    	SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
    	SimpleDateFormat sdfDestination = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
        Calendar calAlta = Calendar.getInstance();
    	
		CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();		
		try {
			usuForm.setHiddenDesActividadEconomica(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, usuForm.getIdActividadEconomica()));
			usuForm.setHiddenDesTipoEmpresa(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_EMPRESA,usuForm.getIdTipoEmpresa()));
			usuForm.setHiddenDesMedio(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_MEDIO_ENTERADO, usuForm.getIdMedio()));
			
		} catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "emp.registro.locator.err");
		} catch (Exception e) {
			logger.error(e);
			registraError(request, "emp.registro.msg2.err");
        }
        
    	if (errors == null) {    		
    		try{    			
    			RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();       	    			    			
    			EmpresaPorAutorizarVO empresaVo = usuForm.getEmpresaPorAutorizar();    			
    			
    			if(empresaVo.getIdTipoPersona()==1){
        			java.util.Date fechaNac = sdfSource.parse(usuForm.getFechaNacimiento());    			
    				String fechaNacFormateada = sdfDestination.format(fechaNac);
    				java.util.Date dtFechaNacFormateada = sdfDestination.parse(fechaNacFormateada); 				
    				empresaVo.setFechaNacimiento(dtFechaNacFormateada);    				
    			} else {
        			java.util.Date fechaActa = sdfSource.parse(usuForm.getFechaActa());    			
    				String fechaActaFormateada = sdfDestination.format(fechaActa);
    				java.util.Date dtFechaActaFormateada = sdfDestination.parse(fechaActaFormateada); 				
    				empresaVo.setFechaActa(dtFechaActaFormateada);    				
    			}
				
    			//DOMICILIO
    			DomicilioVO domicilio = usuForm.getDomicilio();
    			empresaVo.setDomicilio(domicilio);    			
    			//TELEFONOS
    			/*COMENTAR EN PROD*/
    			List<TelefonoVO> lstTempTelefonos = setAllPhones(usuForm, empresaVo);
    			if(lstTempTelefonos!=null){
        			//logger.info("---sustituyendo salvar en ComUpdCompanyAction lstTempTelefonos.size() :" + lstTempTelefonos.size());  
        			Iterator itTemp = lstTempTelefonos.iterator();
        			while(itTemp.hasNext()){
        				TelefonoVO telTemp = (TelefonoVO) itTemp.next();
        				//System.out.println("----Actualizar.Lista Telefonos id:" + telTemp.getIdTelefono() + " tel:" + telTemp.getTelefono());
        			}    			    				
    			}
    			empresaVo.setTelefonos(lstTempTelefonos); 
    			/*TERMINA COMENTAR EN PROD*/
    			/* DESCOMENTAR EN PROD
    			List<TelefonoVO> lstTelefonos = usuForm.getTelefonos();
    			logger.info("---salvar en ComRegCompanyAction lstTelefonos.size() :" + lstTelefonos.size());    			
    			List<TelefonoVO> lstTempTelefonos;
    			if(null!=request.getSession().getAttribute("LST_TELEFONOS_ADICIONALES")){
    				lstTempTelefonos =  (List<TelefonoVO>)request.getSession().getAttribute("LST_TELEFONOS_ADICIONALES");
    				logger.info("---salvar en ComRegCompanyAction lstTempTelefonos.size() :" + lstTempTelefonos.size());
    				Iterator itTelefonos = lstTempTelefonos.iterator();
    				while(itTelefonos.hasNext()){
    					TelefonoVO tel = (TelefonoVO) itTelefonos.next();
    					logger.info("---salvar en ComRegCompanyAction tel :" + tel.getTelefono());
    					lstTelefonos.add(tel);
    				}    				
    			}				    			
    			logger.info("---sustituyendo salvar en ComRegCompanyAction lstTelefonos.size() :" + lstTelefonos.size());	
    			empresaVo.setTelefonos(lstTelefonos);
    			*/		    			
    			//REGISTRAR EMPRESA
    			long idEmpresa; 
    			UsuarioWebVO webVo = getUsuario(request.getSession());
    			if(null!=webVo){
    				long idTipoUsuario = webVo.getIdTipoUsuario();
    				//idEmpresa = service.registrarEmpresa(empresaVo, webVo.getIdUsuario());
    			} else {
    				//idEmpresa = service.registrarEmpresa(empresaVo);
    			}
    			
    			msg = getMensaje(request, "emp.registro.msg1");
				type = ResultVO.TYPE_SUCCESS;						
				request.getSession().setAttribute(msg, "emp.registro.msg1");
		        request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
				PropertiesLoader properties = PropertiesLoader.getInstance();
				request.getSession().setAttribute(TITULO_PAGINA, "Registro de Compa&ntilde;ia");
				request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
				request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
				request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
				request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		        return mapping.findForward(FORWARD_TEMPLATE_FORM);	        
    			
		        /*
			} catch (BusinessException e) {
				logger.error(e);
				registraError(request, "emp.registro.negocio.err");
    		} catch (ParseException e) {
    			logger.error(e);
    			registraError(request, e.getMessage());
			} catch (ServiceLocatorException e) {
				logger.error(e);
				registraError(request, "emp.registro.locator.err");
				*/
			} catch (Exception e) {
				logger.error(e);
				registraError(request, "emp.registro.msg2.err");
            }
			
    	} else{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Favor de revisar los datos proporcionados."));		
			request.setAttribute(Globals.ERROR_KEY, errors);
			//DEBUG
			/*
			Iterator itMessages = errors.get();
			while(itMessages.hasNext()){				
				Object actionMsg = itMessages.next(); 
				System.out.println("******-----errorAction:" + actionMsg.toString());
			}
			*/
			//END DEBUG
		}
        request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Registro de Compa&ntilde;ia");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);	        
    	
    }
  
    private List<TelefonoVO> setAllPhones(ComRegCompanyForm updForm, EmpresaPorAutorizarVO empresaVo){
    	List<TelefonoVO> lstFinal = new ArrayList<TelefonoVO>();    	
    	List<TelefonoVO> lstOldTelefonos = empresaVo.getTelefonos();
    	//System.out.println("--ComRegCompanyForm-setAllPhones lstOldTelefonos:" + lstOldTelefonos.size());
    	List<TelefonoVO> lstUpdatedAdditional = updForm.getTelefonos();    	
		//EN TODOS LOS CASOS DEBE HABER TELEFONO PRINCIPAL, OBTENER DATOS TELEFONO PRINCIPAL
    	TelefonoVO telPrincipal = setTelefonoPrincipal(updForm,lstOldTelefonos);
    	//System.out.println("--ComRegCompanyForm-setAllPhones telefonoPrincipal:" + telPrincipal.toString());    
    	lstFinal.add(telPrincipal);
    	if(lstUpdatedAdditional!=null){
    		//System.out.println("--ComRegCompanyForm-setAllPhones lstUpdatedAdditional:" + lstUpdatedAdditional.size());
        	Iterator itUpdatedAdditional = lstUpdatedAdditional.iterator();
        	while(itUpdatedAdditional.hasNext()){
        		TelefonoVO tempvo = (TelefonoVO)itUpdatedAdditional.next();    		
        		if(existsInList(lstFinal,tempvo)){
        			itUpdatedAdditional.remove();
        			//System.out.println("--ComRegCompanyForm-setAllPhones ELIMINANDO:" + tempvo.toString());
        		} else{
        			lstFinal.add(tempvo);
        			//System.out.println("--ComRegCompanyForm-setAllPhones add :" + tempvo.toString());
        			
        		}
        	}    		
    	}    	
    	return lstFinal;
    }
    
    private TelefonoVO setTelefonoPrincipal(ComRegCompanyForm updForm, List<TelefonoVO> lst){
		TelefonoVO telPrincipal = new TelefonoVO();
		if(lst!=null && lst.size()>0){
			telPrincipal = getTelefonoPrincipal(lst);			
			//System.out.println("--ComRegCompanyForm-setTelefonoPrincipal telefonoPrincipal:" + telPrincipal.toString());
		}
		telPrincipal.setAcceso(updForm.getAcceso());
		telPrincipal.setClave(updForm.getClave());
		telPrincipal.setExtension(updForm.getExtension());
		telPrincipal.setIdTipoTelefono(updForm.getIdTipoTelefono());
		telPrincipal.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		telPrincipal.setTelefono(updForm.getTelefono());
		return telPrincipal;
    }    
    
	private TelefonoVO getTelefonoPrincipal(List<TelefonoVO> lst){
		TelefonoVO telvo = new TelefonoVO();
    	if(lst!=null && lst.size()>0){
    		Iterator it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			//System.out.println("--ComRegCompanyForm-getTelefonoPrincipal:" + temp.toString());
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    				telvo = temp;
    				break;
    			}    			
    		}
    	}		
		return telvo;
	}    
	
	private boolean existsInList(List<TelefonoVO> lst, TelefonoVO t1){
		boolean exists = false;
    	if(lst!=null && lst.size()>0){
    		Iterator it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			//System.out.println("--existsInList temp.getIdTipoTelefono():" + temp.getIdTipoTelefono() + " t1.getIdTipoTelefono():" + t1.getIdTipoTelefono());
    			//System.out.println("--existsInList temp.getClave():" + temp.getClave() + " t1.getClave():" + t1.getClave());
    			//System.out.println("--existsInList temp.getPrincipal():" + temp.getPrincipal() + " t1.getPrincipal():" + t1.getPrincipal());
    			//System.out.println("--existsInList temp.getTelefono():" + temp.getTelefono() + " t1.getTelefono():" + t1.getTelefono());
    			//System.out.println("--existsInList temp.getExtension():" + temp.getExtension() + " t1.getExtension():" + t1.getExtension());
    			String ext1 = null==temp.getExtension() ? "" : temp.getExtension();
    			String ext2 = null==t1.getExtension() ? "" : t1.getExtension();
    			if(temp.getIdTipoTelefono().equals(t1.getIdTipoTelefono()) &&
    				temp.getClave().equalsIgnoreCase(t1.getClave()) &&
    				temp.getPrincipal().equals(t1.getPrincipal()) &&
    				temp.getTelefono().equalsIgnoreCase(t1.getTelefono()) &&
    				ext1.equalsIgnoreCase(ext2)){
    				exists = true;
    				break;
    			}
    		}
    	}			
		return exists;
	}
    
    private boolean hasPrincipalPhone(List<TelefonoVO> lst){
    	boolean principal = false;
    	if(lst!=null && lst.size()>0){
    		Iterator it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    				principal =  true;
    				break;
    			}    			
    		}
    	}
    	return principal;
    }
    
    private boolean hasAdditionalPhone(List<TelefonoVO> lst){
    	boolean additional = false;
    	if(lst!=null && lst.size()>0){
    		Iterator it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion()){
    				additional =  true;
    				break;
    			}    			
    		}
    	}
    	return additional;
    }
    
    
	/**
	 * Consulta y genera la salida en el formato necesario para generar el catalogo de Tipos de Empresa
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward tiposEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<CatalogoOpcionVO> opciones = (List<CatalogoOpcionVO>)session.getAttribute(CAT_TIPO_EMPRESA);				
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		//System.out.println("cat:" +  json);
		redirectJsonResponse(response, json);		
		return null;
	}    
	
	/**
	 * Consulta y genera la salida en el formato necesario para generar el catalogo de Tipos de Actividad Economica
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward tiposActividadEconomica(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<CatalogoOpcionVO> opciones = (List<CatalogoOpcionVO>)session.getAttribute(CAT_ACTIVIDAD_ECONOMICA);				
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);		
		return null;
	} 	
	
	
	/**
	 * Consulta y genera la salida en el formato necesario para generar el catalogo de Medios para enterarse
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward medioEnterado(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<CatalogoOpcionVO> opciones = (List<CatalogoOpcionVO>)session.getAttribute(CAT_MEDIO_ENTERADO);				
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);		
		return null;
	} 	
	
	/**
	 * Consulta y genera la salida en el formato necesario para generar el catalogo de Tipos de Teléfono
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward tipoTelefono(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<CatalogoOpcionVO> opciones = (List<CatalogoOpcionVO>)session.getAttribute(CAT_TIPO_TELEFONO);				
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);		
		return null;
	} 	

}
