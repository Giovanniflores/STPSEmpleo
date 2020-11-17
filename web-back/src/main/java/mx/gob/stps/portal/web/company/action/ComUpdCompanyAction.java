package mx.gob.stps.portal.web.company.action;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.address.action.DomicilioAction;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.ComUpdCompanyForm;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public class ComUpdCompanyAction extends DomicilioAction{

	private static Logger logger = Logger.getLogger(ComUpdCompanyAction.class);
	
	
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
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		ComUpdCompanyForm updForm = (ComUpdCompanyForm) form;
		
		try{
					
	        // Se inicializan catalogos y se suben a sesión
	        RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
	        CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();
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
		    request.getSession().setAttribute("CLAVE_TIPO_PERSONA_FISICA", Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona());
		    request.getSession().setAttribute("CLAVE_TIPO_PERSONA_MORAL", Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona());			
			//obtener usuario y empresa y pasar a la forma
			UsuarioWebVO webVo = getUsuario(request.getSession());
			long idTipoUsuario = webVo.getIdTipoUsuario();			
			long idEmpresa = webVo.getIdPropietario();	
			updForm.setIdTipoUsuario(idTipoUsuario);
			request.getSession().setAttribute("ID_TIPO_USUARIO", idTipoUsuario);
			request.getSession().setAttribute("ID_EMPRESA", webVo.getIdPropietario());
			EmpresaVO empresaVo = service.findEmpresaById(idEmpresa);		
				
			if(empresaVo!=null){
				PropertyUtils.copyProperties(updForm,empresaVo);
				updForm.setIdPortalEmpleo(empresaVo.getIdPortalEmpleo());
				
				DomicilioVO domicilioVo = empresaVo.getDomicilio();
				updForm.setDomicilio(domicilioVo);

				copiaDatosDomicilio(updForm, domicilioVo);
				//PropertyUtils.copyProperties(updForm, domicilioVo);
				
				List<TelefonoVO> lstTelefonos = empresaVo.getTelefonos();
				List<TelefonoVO> lstTelefonosAdicionales = new ArrayList<TelefonoVO>();
				//System.out.println("---lstTelefonos.size() get:" + lstTelefonos.size());
				//logger.info("---lstTelefonos.size() get:"+ lstTelefonos.size());				
				if(lstTelefonos.size()>0){										
					Iterator itLstTelefonos = lstTelefonos.iterator();
					while(itLstTelefonos.hasNext()){
						TelefonoVO telVo = (TelefonoVO) itLstTelefonos.next();
						//System.out.println("--ComUpdCompanyAction-init telefonos :" + telVo.toString());
						if(telVo.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
							updForm.setIdTipoTelefono(telVo.getIdTipoTelefono());
							updForm.setAcceso(telVo.getAcceso());
							updForm.setClave(telVo.getClave());
							updForm.setTelefono(telVo.getTelefono());
							updForm.setExtension(telVo.getExtension());		
							//datos para tipo de cambio
							updForm.setPreAcceso(telVo.getAcceso());
							updForm.setPreClave(telVo.getClave());
							updForm.setPreExtension(telVo.getExtension());
							updForm.setPreIdTipoTelefono(telVo.getIdTipoTelefono());
							updForm.setPreTelefono(telVo.getTelefono());
						}						
						else {							
							lstTelefonosAdicionales.add(telVo);
						}
					}
					updForm.setLstTelefonos(lstTelefonos);
					request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, lstTelefonosAdicionales);	
					//logger.info("---lstTelefonos.size() set:"+ lstTelefonos.size());					
				}													
				//hidden cbo descripciones	
				updForm.setHiddenDesActividadEconomica(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, empresaVo.getIdActividadEconomica()));
				updForm.setHiddenDesTipoEmpresa(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_EMPRESA,empresaVo.getIdTipoEmpresa()));
				
				if (domicilioVo!=null){
					updForm.setHiddenDesEntidad(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA,domicilioVo.getIdEntidad()));
					updForm.setHiddenDesMunicipio(domicilioVo.getMunicipio());
					updForm.setHiddenDesColonia(domicilioVo.getColonia());
					updForm.setHiddenCodigoPostal(domicilioVo.getCodigoPostal());
				}				
				//TIPO DE USUARIO
				updForm.setIdTipoUsuario(idTipoUsuario);
				//datos para tipo de cambio
				updForm.setPreContactoEmpresa(empresaVo.getContactoEmpresa());
				updForm.setPreCorreoElectronico(empresaVo.getCorreoElectronico());
				updForm.setPreDescripcion(empresaVo.getDescripcion());									
			}					
	    } catch (ServiceLocatorException e) {
	    	e.printStackTrace();
	        logger.error(e);
	        registraError(request, "app.exp.locator.err");
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	logger.error(e);
	        registraError(request, "app.exp.negocio.err");
	    }

		//logger.info("Consulta de detalle de la empresa a modificar");
		//logger.info(updForm.toString());
		
		ActionForward forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
		UsuarioWebVO usuario = getUsuario(request.getSession());
		if (usuario!=null && usuario.getIdTipoUsuario() == TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario())
			forward = mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		
		ocultaBarraArticulos(request);
		request.getSession().setAttribute(TAB_MENU, TAB_MIS_DATOS);
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Editar Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return forward;
	}
	
	public ActionForward actualizarExitoso(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
		return mapping.findForward("JSP_SUCCESS");		
	}
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {		
		//TIPO DE USUARIO
		UsuarioWebVO webVo = getUsuario(request.getSession());				
		long idTipoUsuario = webVo.getIdTipoUsuario();	
    	if(idTipoUsuario==Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
    		String strForward = "JSP_AUTORIZATION";
    		String strFilterPage = (request.getSession().getAttribute("FILTER_PAGE")==null 
    				? "": (String)request.getSession().getAttribute("FILTER_PAGE"));    		
    		if(strFilterPage.equalsIgnoreCase("FILTER_COMPANY")){
    			strForward = "ACTION_ADMIN";
    		}
    		request.getSession().setAttribute(BODY_JSP, mapping.findForward(strForward).getPath());
    		return mapping.findForward(strForward);	    		
    	} else {
    		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_CANCEL").getPath());
    		return mapping.findForward("JSP_CANCEL");	    		
    	}
	}			
	
	/**
	 * Obtiene los datos capturados por el usuario en el formulario e invoca la actualizacion de empresa
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */ 	
    public ActionForward actualizar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("******-----ENTRANDO A ACTUALIZAR EMPRESA------*******");
    	ComUpdCompanyForm updForm = (ComUpdCompanyForm) form;
		
    	//logger.info(updForm.toString());
    	
    	String msg = null;
		String type = null;    	
    	ActionErrors errors = updForm.validate(mapping, request); 
    	
    	CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();
		try {
			updForm.setHiddenDesActividadEconomica(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, updForm.getIdActividadEconomica()));
			updForm.setHiddenDesTipoEmpresa(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_EMPRESA,updForm.getIdTipoEmpresa()));			
		} catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "emp.registro.locator.err");
		} catch (Exception e) {
			logger.error(e);
			registraError(request, "emp.registro.msg2.err");
        }    	
    	
    	if (errors == null) {
    		try{
    			long idEmpresa;
    			EmpresaVO oldEmpresa =  null; 
    			String strChangedFields = "";
    			boolean isChangedEmail = false;
    			boolean isChangedBasicField = false;
    			boolean bIsChangedPortalId = false;
    			
    			RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
    			EmpresaVO empresaVo = updForm.getEmpresa(); 
    			PropertyUtils.copyProperties(empresaVo,updForm);    
    			//DOMICILIO
    			DomicilioVO domicilio = updForm.getDomicilio();
    			empresaVo.setDomicilio(domicilio);  
    			//TELEFONOS
    			List<TelefonoVO> lstTempTelefonos = setAllPhones(updForm, empresaVo);
    			if(lstTempTelefonos!=null){
        			logger.info("---sustituyendo salvar en ComUpdCompanyAction lstTempTelefonos.size() :" + lstTempTelefonos.size());  
        			Iterator itTemp = lstTempTelefonos.iterator();
        			while(itTemp.hasNext()){
        				TelefonoVO telTemp = (TelefonoVO) itTemp.next();
        				System.out.println("----Actualizar.Lista Telefonos id:" + telTemp.getIdTelefono() + " tel:" + telTemp.getTelefono());
        			}    			    				
    			}
    			empresaVo.setTelefonos(lstTempTelefonos); 
    			//TERMINA TELEFONOS
    			//TIPO DE USUARIO
				UsuarioWebVO webVo = getUsuario(request.getSession());				
				long idTipoUsuario = webVo.getIdTipoUsuario();	
				idEmpresa = webVo.getIdPropietario();
				oldEmpresa = service.findEmpresaById(webVo.getIdPropietario());				
				strChangedFields = getChangedFields(updForm,oldEmpresa);
				bIsChangedPortalId = isChangedPortalID(updForm, oldEmpresa);
				
    	    	if(idTipoUsuario==Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
    	    		if(!updForm.getCorreoElectronico().equalsIgnoreCase(updForm.getPreCorreoElectronico())){
    	    			isChangedEmail = true;
    	    			System.out.println("--es administrador--cambio el correo");
    	    		}    	    		
    	    		long idRegValidar=0; 
    	    		if(null!=request.getSession().getAttribute(PARAM_ID_REGISTRO_POR_VALIDAR)){
    	    			idRegValidar = (Long)request.getSession().getAttribute(PARAM_ID_REGISTRO_POR_VALIDAR);
    	    		} 	    		  	    		
    	    		request.getSession().setAttribute("UPDCOMPANY","NONCOMPANY");
	    			idEmpresa = service.actualizarEmpresa(empresaVo, webVo.getIdUsuario(), webVo.getIdTipoUsuario(), isChangedEmail, isChangedBasicField, bIsChangedPortalId, strChangedFields, idRegValidar);
	    			System.out.println("---idRegValidar:" + idRegValidar + "NONCOMPANY ---idEmpresa:" + idEmpresa);
	    			msg = getMensaje(request, "emp.actualizacion.msg1");
    	    		
    	    	} else if(idTipoUsuario==Constantes.TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {
    	    		if(!updForm.getCorreoElectronico().equalsIgnoreCase(updForm.getPreCorreoElectronico())){
    	    			isChangedEmail = true;
    	    			isChangedBasicField = isChangedBasicInfo(updForm);
    	    			System.out.println("---es empresa-cambio el correo --isChangedBasicField:" + isChangedBasicField);    	    			
    	    			request.getSession().setAttribute("UPDCOMPANY","EMAIL");
    	    			idEmpresa = service.actualizarEmpresa(empresaVo, webVo.getIdUsuario(), webVo.getIdTipoUsuario(), isChangedEmail, isChangedBasicField, bIsChangedPortalId, strChangedFields, 0);    	    			
	    				msg = getMensaje(request, "emp.actualizacion.msg6");    	    				
    	    			
    	    		} else {
        	    		if(isChangedBasicInfo(updForm)){
        	    			System.out.println("---NO cambio el correo, cambio info basica");
        	    			isChangedBasicField = true;
        	    			request.getSession().setAttribute("UPDCOMPANY","BASICDATA");
    	    				idEmpresa = service.actualizarEmpresa(empresaVo, webVo.getIdUsuario(), webVo.getIdTipoUsuario(), isChangedEmail, isChangedBasicField, bIsChangedPortalId, strChangedFields, 0);	    					    				
    	    				msg = getMensaje(request, "emp.actualizacion.msg6");    	    				
        	    		} else {
        	    			System.out.println("---NO cambio el correo, cambio info no basica");
        	    			request.getSession().setAttribute("UPDCOMPANY","NONBASICDATA");
        	    			idEmpresa = service.actualizarEmpresa(empresaVo, webVo.getIdUsuario(), webVo.getIdTipoUsuario(), isChangedEmail, isChangedBasicField, bIsChangedPortalId, strChangedFields, 0);    	    			
        	    			msg = getMensaje(request, "emp.actualizacion.msg1");
        	    		}    	    			
    	    		} 
    	    	}   			    			
				type = ResultVO.TYPE_SUCCESS;
				
				ActionForward forward = null;
				String strForward = "";
		        if(idTipoUsuario==Constantes.TIPO_USUARIO.ADMINISTRADOR.getIdTipoUsuario()){
		    		String strFilterPage = (request.getSession().getAttribute("FILTER_PAGE")==null 
		    				? "": (String)request.getSession().getAttribute("FILTER_PAGE"));    		
		    		if(strFilterPage.equalsIgnoreCase("FILTER_COMPANY")){
		    			strForward = "ACTION_ADMIN";
		    		} else{
		    			request.getSession().removeAttribute("FILTER_COMPANY");
		    			strForward = "JSP_AUTORIZATION";
		    		}
			        forward =  mapping.findForward(strForward);		
		        } else {		        	
		        	if(isChangedBasicField || isChangedEmail){
		        		strForward = "JSP_SUCCESS_BASIC_DATA";
		        	} else {
		        		strForward = "JSP_SUCCESS";
		        	}	
			        request.getSession().setAttribute(BODY_JSP, mapping.findForward(strForward).getPath());
					PropertiesLoader properties = PropertiesLoader.getInstance();
					request.getSession().setAttribute(TITULO_PAGINA, "Editar Empresa");
					request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
					request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
					request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
					request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
			        forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
		        }    
		        return forward;
    			
			} catch (BusinessException e) {
				logger.error(e);				
				registraError(request, "emp.actualizacion.negocio.err");
			} catch (ServiceLocatorException e) {
				logger.error(e);				
				registraError(request, "emp.actualizacion.locator.err");
			} catch (Exception e) {
				logger.error(e);				
				registraError(request, "emp.actualizacion.msg2.err");
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
		request.getSession().setAttribute(TITULO_PAGINA, "Editar Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
    	
    }	
	
    

    
    private List<TelefonoVO> setAllPhones(ComUpdCompanyForm updForm, EmpresaVO empresaVo){
    	List<TelefonoVO> lstFinal = new ArrayList<TelefonoVO>();    	
    	List<TelefonoVO> lstOldTelefonos = empresaVo.getTelefonos();
    	//System.out.println("--ComUpdCompanyAction-setAllPhones lstOldTelefonos:" + lstOldTelefonos.size());
    	RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
    	List<TelefonoVO> lstUpdatedAdditional;
		try {
			lstUpdatedAdditional = service.consultarTelefonos(empresaVo.getIdEmpresa(), Constantes.TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());
			lstUpdatedAdditional = removeTelefonoPrincipal(lstUpdatedAdditional);
	    	//lstUpdatedAdditional = updForm.getLstTelefonos();    	
			//EN TODOS LOS CASOS DEBE HABER TELEFONO PRINCIPAL, OBTENER DATOS TELEFONO PRINCIPAL
	    	TelefonoVO telPrincipal = setTelefonoPrincipal(updForm,lstOldTelefonos);
	    	//System.out.println("--ComUpdCompanyAction-setAllPhones telefonoPrincipal:" + telPrincipal.toString());    
	    	lstFinal.add(telPrincipal);
	    	if(lstUpdatedAdditional!=null){
	    		//System.out.println("--ComUpdCompanyAction-setAllPhones lstUpdatedAdditional:" + lstUpdatedAdditional.size());
	        	Iterator itUpdatedAdditional = lstUpdatedAdditional.iterator();
	        	while(itUpdatedAdditional.hasNext()){
	        		TelefonoVO tempvo = (TelefonoVO)itUpdatedAdditional.next();    		
	        		if(existsInList(lstFinal,tempvo)){
	        			itUpdatedAdditional.remove();
	        			System.out.println("--ComUpdCompanyAction-setAllPhones ELIMINANDO:" + tempvo.toString());
	        		} else{
	        			lstFinal.add(tempvo);
	        			System.out.println("--ComUpdCompanyAction-setAllPhones agregando :" + tempvo.toString());
	        			
	        		}
	        	}    		
	    	}    				
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
    	return lstFinal;
    }
    
	private List<TelefonoVO> removeTelefonoPrincipal(List<TelefonoVO> lst){
    	if(lst!=null && lst.size()>0){
    		Iterator it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			//System.out.println("--ComUpdCompanyAction-getTelefonoPrincipal:" + temp.toString());
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    				it.remove();
    				break;
    			}    			
    		}
    	}		
		return lst;
	}       
    
    private TelefonoVO setTelefonoPrincipal(ComUpdCompanyForm updForm, List<TelefonoVO> lst){
		TelefonoVO telPrincipal = new TelefonoVO();
		if(lst!=null && lst.size()>0){
			telPrincipal = getTelefonoPrincipal(lst);			
			//System.out.println("--ComUpdCompanyAction-setTelefonoPrincipal telefonoPrincipal:" + telPrincipal.toString());
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
    			//System.out.println("--ComUpdCompanyAction-getTelefonoPrincipal:" + temp.toString());
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
    
    
    private boolean isChangedBasicInfo(ComUpdCompanyForm updForm){
    	boolean changed = false;
    	String strPreDescripcion = null==updForm.getPreDescripcion() || updForm.getPreDescripcion().equalsIgnoreCase("") ? "" : updForm.getPreDescripcion();
    	String strPreContacto = null==updForm.getPreContactoEmpresa() || updForm.getPreContactoEmpresa().equalsIgnoreCase("") ? "" : updForm.getPreContactoEmpresa();
    	String strPreAcceso = null==updForm.getPreAcceso() || updForm.getPreAcceso().equalsIgnoreCase("") ? "" : updForm.getPreAcceso();
    	String strPreClave = null==updForm.getPreClave() || updForm.getPreClave().equalsIgnoreCase("") ? "" : updForm.getPreClave();
    	String strPreTel = null==updForm.getPreTelefono() || updForm.getPreTelefono().equalsIgnoreCase("") ? "" : updForm.getPreTelefono();
    	String strPreExt = null==updForm.getPreExtension() || updForm.getPreExtension().equalsIgnoreCase("") ? "" : updForm.getPreExtension();
    	//System.out.println("----updForm.getDescripcion():" + updForm.getDescripcion() + " updForm.getPreDescripcion():" + strPreDescripcion);
    	//System.out.println("----updForm.getContactoEmpresa():" + updForm.getContactoEmpresa() + " updForm.getPre():" + strPreContacto);
    	//System.out.println("----updForm.getIdTipoTelefono:" + updForm.getIdTipoTelefono() + " updForm.getPre():" + updForm.getPreIdTipoTelefono());
    	//System.out.println("----updForm.getAcceso():" + updForm.getAcceso() + " updForm.getPre():" + strPreAcceso);
    	//System.out.println("----updForm.getClave():" + updForm.getClave() + " updForm.getPre():" + strPreClave);
    	//System.out.println("----updForm.getTelefono():" + updForm.getTelefono() + " updForm.getPre():" + strPreTel);
    	//System.out.println("----updForm.getExtension():" + updForm.getExtension() + " updForm.getPre():" + strPreExt);
    	    	
    	if(!updForm.getDescripcion().equalsIgnoreCase(strPreDescripcion)){
    		//System.out.println("----isChangedBasicInfo descripcion");
    		changed = true;
    	} else if(!updForm.getContactoEmpresa().equalsIgnoreCase(strPreContacto)){
    		//System.out.println("----isChangedBasicInfo contacto");
    		changed = true;
    	} else if(updForm.getIdTipoTelefono()!=updForm.getPreIdTipoTelefono()){
    		//System.out.println("----isChangedBasicInfo idtipotel");
    		changed = true;
    	} else if(!updForm.getAcceso().equalsIgnoreCase(strPreAcceso)){
    		//System.out.println("----isChangedBasicInfo Acceso");
    		changed = true;
    	} else if(!updForm.getClave().equalsIgnoreCase(strPreClave)){
    		//System.out.println("----isChangedBasicInfo clave");
    		changed = true;
    	} else if(!updForm.getTelefono().equalsIgnoreCase(strPreTel)){
    		//System.out.println("----isChangedBasicInfo telefono");
    		changed = true;    		
    	} else if(!updForm.getExtension().equalsIgnoreCase(strPreExt)){
    		//System.out.println("----isChangedBasicInfo extension");
    		changed = true;    		    		
    	}
    	return changed;
    }


    private boolean isChangedPortalID(ComUpdCompanyForm updForm, EmpresaVO oldEmpresa){
    	//Los campos que forman el IDPortal son nombre, apellidos, razon social, fecha de nacimiento, fecha de acta y codigo postal, pero codigo postal
    	//es el unico que puede cambiar el usuario o el administrador
    	boolean changed = false;
    	//System.out.println("----entra a isChangedPortalID");   
    	String strPreCodigoPostal = null==oldEmpresa.getDomicilio().getCodigoPostal() || 
    			oldEmpresa.getDomicilio().getCodigoPostal().equalsIgnoreCase("") ? "" : oldEmpresa.getDomicilio().getCodigoPostal();    	
    	if(!updForm.getCodigoPostal().equalsIgnoreCase(strPreCodigoPostal)){
    		changed = true; 
    	}        	    	
    	return changed;
    }
    
    
    private String getChangedFields(ComUpdCompanyForm updForm, EmpresaVO oldEmpresa){
    	String strChangedFields = "";
    	
    	String strPreCorreoElectronico = null==updForm.getPreCorreoElectronico() || updForm.getPreCorreoElectronico().equalsIgnoreCase("") ? "" : updForm.getPreCorreoElectronico();
    	String strPreDescripcion = null==updForm.getPreDescripcion() || updForm.getPreDescripcion().equalsIgnoreCase("") ? "" : updForm.getPreDescripcion();
    	String strPreContacto = null==updForm.getPreContactoEmpresa() || updForm.getPreContactoEmpresa().equalsIgnoreCase("") ? "" : updForm.getPreContactoEmpresa();
    	
    	String strPreAcceso = null==updForm.getPreAcceso() || updForm.getPreAcceso().equalsIgnoreCase("") ? "" : updForm.getPreAcceso();
    	String strPreClave = null==updForm.getPreClave() || updForm.getPreClave().equalsIgnoreCase("") ? "" : updForm.getPreClave();
    	String strPreTel = null==updForm.getPreTelefono() || updForm.getPreTelefono().equalsIgnoreCase("") ? "" : updForm.getPreTelefono();
    	String strPreExt = null==updForm.getPreExtension() || updForm.getPreExtension().equalsIgnoreCase("") ? "" : updForm.getPreExtension();    	    	
    	
    	String strPreCalle = null==oldEmpresa.getDomicilio().getCalle() || oldEmpresa.getDomicilio().getCalle().equalsIgnoreCase("") ? "" : oldEmpresa.getDomicilio().getCalle();
    	String strPreCodigoPostal = null==oldEmpresa.getDomicilio().getCodigoPostal() || oldEmpresa.getDomicilio().getCodigoPostal().equalsIgnoreCase("") ? "" : oldEmpresa.getDomicilio().getCodigoPostal();
    	String strPreEntreCalle = null==oldEmpresa.getDomicilio().getEntreCalle() || oldEmpresa.getDomicilio().getEntreCalle().equalsIgnoreCase("") ? "" : oldEmpresa.getDomicilio().getEntreCalle();
    	
    	if(!updForm.getCorreoElectronico().equalsIgnoreCase(updForm.getPreCorreoElectronico())){
    		strChangedFields = strChangedFields + "correoElectronico=" + updForm.getPreCorreoElectronico() + "|";
    		//System.out.println("----isChangedField correoElectronico");
    	} 
    	if(!updForm.getDescripcion().equalsIgnoreCase(strPreDescripcion)){
    		strChangedFields = strChangedFields + "descripcion=" + updForm.getPreDescripcion() + "|";
    		//System.out.println("----isChangedField descripcion");
    	} 
    	if(!updForm.getContactoEmpresa().equalsIgnoreCase(updForm.getPreContactoEmpresa())){
    		strChangedFields = strChangedFields + "contactoEmpresa=" + updForm.getPreContactoEmpresa() + "|";
    		//System.out.println("----isChangedField contactoEmpresa");
    	} 
    	
    	if(updForm.getIdTipoTelefono()!=updForm.getPreIdTipoTelefono()){
    		strChangedFields = strChangedFields + "idTipoTelefono=" + updForm.getPreIdTipoTelefono() + "|";
    		//System.out.println("----isChangedField idTipoTelefono");
    	} 
    	
    	if(!updForm.getAcceso().equalsIgnoreCase(strPreAcceso)){
    		strChangedFields = strChangedFields + "acceso=" + updForm.getPreAcceso() + "|";
    		//System.out.println("----isChangedField acceso");
    	} 
    	if(!updForm.getClave().equalsIgnoreCase(strPreClave)){
    		strChangedFields = strChangedFields + "clave=" + updForm.getPreClave() + "|";
    	} 
    	if(!updForm.getTelefono().equalsIgnoreCase(strPreTel)){
    		strChangedFields = strChangedFields + "telefono=" + updForm.getTelefono() + "|";   
    		//System.out.println("----isChangedField telefono");
    	} 
    	if(!updForm.getExtension().equalsIgnoreCase(strPreExt)){
    		strChangedFields = strChangedFields + "extension=" + updForm.getExtension() + "|";   
    		//System.out.println("----isChangedField extension");
    	}    
    	
    	if(!updForm.getCalle().equalsIgnoreCase(strPreCalle)){
    		strChangedFields = strChangedFields + "calle=" + updForm.getCalle() + "|";    		    		
    	}    
    	if(!updForm.getCodigoPostal().equalsIgnoreCase(strPreCodigoPostal)){
    		strChangedFields = strChangedFields + "codigoPostal=" + updForm.getCodigoPostal() + "|";    		    		
    	}    
    	if(updForm.getConfidencial()!=oldEmpresa.getConfidencial()){
    		strChangedFields = strChangedFields + "confidencial=" + updForm.getConfidencial() + "|";    		    		
    	}    
    	if(!updForm.getEntreCalle().equalsIgnoreCase(strPreEntreCalle)){
    		strChangedFields = strChangedFields + "entreCalle=" + updForm.getEntreCalle() + "|";    		    		
    	}    
    	
    	if(updForm.getIdActividadEconomica()!= oldEmpresa.getIdActividadEconomica()){
    		strChangedFields = strChangedFields + "idActividadEconomica=" + updForm.getIdActividadEconomica() + "|";    		    		
    	}    
    	if(updForm.getIdColonia()!= oldEmpresa.getDomicilio().getIdColonia()){
    		strChangedFields = strChangedFields + "idColonia=" + updForm.getIdColonia() + "|";    		    		
    	}    
    	if(updForm.getIdEntidad()!= oldEmpresa.getDomicilio().getIdEntidad()){
    		strChangedFields = strChangedFields + "idEntidad=" + updForm.getIdEntidad() + "|";    		    		
    	}    
    	if(updForm.getIdMunicipio()!= oldEmpresa.getDomicilio().getIdMunicipio()){
    		strChangedFields = strChangedFields + "idMunicipio=" + updForm.getIdMunicipio() + "|";    		    		
    	}        	
    	if(updForm.getIdTipoEmpresa()!= oldEmpresa.getIdTipoEmpresa()){
    		strChangedFields = strChangedFields + "idTipoEmpresa=" + updForm.getIdTipoEmpresa() + "|";    		    		
    	}     	    	    	
    	
    	return strChangedFields;
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
			
	
	/**
	 * Method 'redirectForward'
	 * 
	 * @param mapping
	 * @param request
	 * @param MODO_VISTA
	 * @return ActionForward
	 */		
    private ActionForward redirectForward(ActionMapping mapping, HttpServletRequest request, String MODO_VISTA) {
        request.getSession().setAttribute("MODO_JSP", "MODO_JSP_ALT"); // ESTABLECE EL MODO DE DESPLIEGUE DE LA PAGINA
        request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Editar Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ request.getRequestURI().toString());
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
    }
    
    private void copiaDatosDomicilio(ComUpdCompanyForm forma, DomicilioVO domicilio){
    	
    	if (forma==null || domicilio==null) return;
    	
    	forma.setIdDomicilio(domicilio.getIdDomicilio());
    	forma.setIdTipoPropietario(domicilio.getIdTipoPropietario());
    	forma.setIdEntidad(domicilio.getIdEntidad());
    	forma.setIdMunicipio(domicilio.getIdMunicipio());
    	forma.setIdColonia(domicilio.getIdColonia());
    	forma.setCalle(domicilio.getCalle());
    	forma.setNumeroInterior(domicilio.getNumeroInterior());
    	forma.setNumeroExterior(domicilio.getNumeroExterior());
    	forma.setEntreCalle(domicilio.getEntreCalle());
    	forma.setyCalle(domicilio.getyCalle());
    	forma.setCodigoPostal(domicilio.getCodigoPostal());
    	//forma.setFechaAlta(domicilio.getFechaAlta());
    	//forma.setEstatus(domicilio.getEstatus());
    }
    
    
}
