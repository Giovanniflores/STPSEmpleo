package mx.gob.stps.portal.web.company.action;

import mx.gob.stps.portal.core.domicilio.dao.ObtenerColoniaByIdDAO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.empresa.vo.RegistroContactoVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.address.action.DomicilioAction;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.ComRegContactFormv2;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.phone.UtilsPhone;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
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

/**
 * Administra los estados de la presentacion para el formulario de Registro de Contactos
 * 
 * @author haydee.vertti
 */
//TODO ELIMINAR CLASE YA NO SE USA
public class ComRegContactActionv2  extends DomicilioAction{
	
	private static Logger logger = Logger.getLogger(ComRegContactActionv2.class);

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
		System.out.println("******-----ENTRANDO A FORMULARIO REGISTRAR CONTACTO------*******");	
		try{	
			String strTipoEmpresaPadre="";
			String strPadreLeyendaNombre = ""; 			
			ComRegContactFormv2 regForm = (ComRegContactFormv2) form;
			regForm.reset();
			regForm.setCorreoElectronico("");
			regForm.setConfirmarCorreoElectronico("");
			regForm.setCodigoPostal("");
			regForm.setCalle("");
			regForm.setNumeroExterior("");
			regForm.setNumeroInterior("");
			regForm.setEntreCalle("");
			regForm.setyCalle("");
			regForm.setIdTipoTelefono(2);	
			regForm.setDomicilio(new DomicilioVO());
			System.out.println("-----checando IdDomicilio:" + regForm.getIdDomicilio() + " Domicilio:" + regForm.getDomicilio().toString());	
	        // Se inicializan catalogos y se suben a sesión
	        RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
	        CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();
	        request.getSession().setAttribute(CAT_TIPO_EMPRESA, serviceCat.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_EMPRESA));
	        request.getSession().setAttribute(CAT_ACTIVIDAD_ECONOMICA, serviceCat.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA));
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
			request.getSession().setAttribute("ESTATUS_ACTIVO_ID", Constantes.ESTATUS.ACTIVO.getIdOpcion());
			request.getSession().setAttribute("ESTATUS_ACTIVO_DES", Constantes.ESTATUS.ACTIVO.getOpcion());
			request.getSession().setAttribute("ESTATUS_INACTIVO_ID", Constantes.ESTATUS.INACTIVO.getIdOpcion());
			request.getSession().setAttribute("ESTATUS_INACTIVO_DES", Constantes.ESTATUS.INACTIVO.getOpcion());								
			try{
				UsuarioWebVO webVo = getUsuario(request.getSession());								
				long idEmpresa =  webVo.getIdPropietario();
				EmpresaVO empresaVo = service.findEmpresaById(idEmpresa);									
				long idEmpresaPadre = webVo.getIdPropietario();
				//System.out.println("------------idEmpresaPadre:" + idEmpresaPadre);
				request.getSession().setAttribute(ID_EMPRESA_PADRE, idEmpresaPadre);								
				if(empresaVo.getIdTipoPersona()==(long)Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){					
					strTipoEmpresaPadre = Constantes.TIPO_PERSONA.PERSONA_FISICA.getTipoPersona();
				} else {
					strTipoEmpresaPadre = Constantes.TIPO_PERSONA.PERSONA_MORAL.getTipoPersona();
				}		
				strPadreLeyendaNombre = empresaVo.getNombreEmpresa();
				request.getSession().setAttribute(DES_TIPO_EMPRESA_PADRE, strTipoEmpresaPadre);	
				regForm.setIdEmpresa(idEmpresaPadre);				
				regForm.setPadreIdTipoPersona(empresaVo.getIdTipoPersona());
				regForm.setPadreIdPortalEmpleo(empresaVo.getIdPortalEmpleo());
				regForm.setPadreNombre(empresaVo.getNombre());
				regForm.setPadreApellido1(empresaVo.getApellido1());
				regForm.setPadreApellido2(empresaVo.getApellido2());
				regForm.setPadreRazonSocial(empresaVo.getRazonSocial());
				regForm.setPadreContactoEmpresa(empresaVo.getContactoEmpresa());
				regForm.setPadreLeyendaNombre(strPadreLeyendaNombre);				
				List<RegistroContactoVO> lstContactos = null; //service.findAllContactsByIdEmpresa(empresaVo.getIdEmpresa());
				if(lstContactos!=null){
					regForm.setLstContactos(lstContactos);
				} else {
					lstContactos = new ArrayList<RegistroContactoVO>();					
				}		
				request.getSession().setAttribute(LST_CONTACTOS, lstContactos);
				//Cargar Terceras empresas
				/*List<TerceraEmpresaVO> lstTercerasEmpresas = service.findAllByIdEmpresa(empresaVo.getIdEmpresa());
				if(lstTercerasEmpresas!=null){
					regForm.setLstTercerasEmpresas(lstTercerasEmpresas);
				} else {
					lstTercerasEmpresas = new ArrayList<TerceraEmpresaVO>();					
				}*/
				request.getSession().setAttribute("PROPIETARIO_TELEFONO", "CONTACTO");
				//request.getSession().setAttribute(LST_TERCERAS_EMPRESAS, lstTercerasEmpresas);				
				List<TelefonoVO> lstTelefonos = new ArrayList<TelefonoVO>();
				request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, lstTelefonos);						
			}catch(Exception e){
		        logger.error(e);
		        registraError(request, "app.exp.negocio.err");
			}							
	    } catch (ServiceLocatorException e) {
	        logger.error(e);
	        registraError(request, "app.exp.locator.err");
	    } catch (Exception e) {
	        logger.error(e);
	        registraError(request, "app.exp.negocio.err");
	    }
		request.getSession().setAttribute("MODO_PAGINA", "MODO_ALTA");		
		ActionForward forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
		UsuarioWebVO usuario = getUsuario(request.getSession());
		if (usuario.getPublicador())
			forward = mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);	    
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Cambiar Contacto");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Cambiar contacto, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return forward;		
	}	
		
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("******-----ENTRANDO A FORMULARIO EDITAR CONTACTO------*******");
		ComRegContactFormv2 regForm = (ComRegContactFormv2) form;
		UsuarioWebVO usuario = getUsuario(request.getSession());
		RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
		long idSelectedContact = regForm.getSelectContact();		
		request.getSession().setAttribute("ID_CONTACTO_PROPIETARIO", idSelectedContact);
		RegistroContactoVO foundContact;
		try {
			foundContact = null; //service.findContactoById(idSelectedContact);
			if(null!=foundContact){
				
				int cuentaOfertas = 0;
				//cuentaOfertas = service.countOffersByIdContact(idSelectedContact, Constantes.ESTATUS.ACTIVO.getIdOpcion());
				
				//System.out.println("******-----cuentaOfertas: " + cuentaOfertas);
				if(cuentaOfertas>0) {
					System.out.println("----NO SE PUEDE CAMBIAR");
					registraMensaje(request, "contacto.registro.msg4");					
				} else {					
					regForm.setIdContacto(idSelectedContact);
					regForm.setEstatus(foundContact.getEstatus());
					System.out.println("------idContacto:" + foundContact.getIdContacto() + " estatus:" + regForm.getEstatus() + " tercera:" + foundContact.getIdTerceraEmpresa());
					regForm.setNombreContacto(foundContact.getNombreContacto());
					regForm.setCargo(foundContact.getCargo());
					//TERCERAs EMPRESAs
					UsuarioWebVO webVo = getUsuario(request.getSession());								
					long idEmpresa =  webVo.getIdPropietario();
					EmpresaVO empresaVo = service.findEmpresaById(idEmpresa);														
					/*List<TerceraEmpresaVO> lstTercerasEmpresas = service.findAllByIdEmpresa(empresaVo.getIdEmpresa());
					if(lstTercerasEmpresas!=null){
						regForm.setLstTercerasEmpresas(lstTercerasEmpresas);
					} else {
						lstTercerasEmpresas = new ArrayList<TerceraEmpresaVO>();					
					}*/
					//request.getSession().setAttribute(LST_TERCERAS_EMPRESAS, lstTercerasEmpresas);									
					regForm.setIdTerceraEmpresa(foundContact.getIdTerceraEmpresa());
					regForm.setSelectTerceraEmpresa(foundContact.getIdTerceraEmpresa());
					regForm.setCorreoElectronico(foundContact.getCorreoElectronico());
					regForm.setPreCorreoElectronico(foundContact.getCorreoElectronico());
					DomicilioVO domicilioVo = foundContact.getDomicilio();	
					if(null!=domicilioVo){
						regForm.setDomicilio(domicilioVo);
						regForm.setCodigoPostal(domicilioVo.getCodigoPostal());
						regForm.setCalle(domicilioVo.getCalle());
						regForm.setNumeroExterior(domicilioVo.getNumeroExterior());
						regForm.setNumeroInterior(domicilioVo.getNumeroInterior());
						regForm.setEntreCalle(domicilioVo.getEntreCalle());
						regForm.setyCalle(domicilioVo.getyCalle());			
						regForm.setIdColoniaSelected(domicilioVo.getIdColonia());
						//System.out.println("----domicilioVo.getIdColonia():" + domicilioVo.getIdColonia());
						ObtenerColoniaByIdDAO catColonia = new ObtenerColoniaByIdDAO();
						String desColonia = catColonia.obtenerColoniaPorId(domicilioVo.getIdColonia());
						//System.out.println("----desColonia:" + desColonia);
						regForm.setIdColoniaSelectedText(desColonia);
					}						
					List<TelefonoVO> lstTelefonos = foundContact.getTelefonos();
					List<TelefonoVO> lstTelefonosAdicionales = new ArrayList<TelefonoVO>();
					if(null!=lstTelefonos && lstTelefonos.size()>0){									
						Iterator<TelefonoVO> itLstTelefonos = lstTelefonos.iterator();
						while(itLstTelefonos.hasNext()){
							TelefonoVO telVo = (TelefonoVO) itLstTelefonos.next();
							System.out.println("----ComRegCOntactActionv2.editar telVo:" + telVo.toString());
							if(telVo.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
								regForm.setIdTipoTelefono((int)telVo.getIdTipoTelefono());
								regForm.setAcceso(telVo.getAcceso());
								regForm.setClave(telVo.getClave());
								regForm.setTelefono(telVo.getTelefono());
								regForm.setExtension(telVo.getExtension());		
							}						
							else {	
								lstTelefonosAdicionales.add(telVo);
							}
						}
					}	
					regForm.setLstTelefonos(lstTelefonos);	
					request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, lstTelefonosAdicionales);									
				}				
			}			
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			registraError(request, "app.exp.locator.err");
			e.printStackTrace();
		}
		//Definir el forward dependiendo del usuario
		request.getSession().setAttribute("MODO_PAGINA", "MODO_EDICION");
		ActionForward forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);		
		if (usuario.getPublicador())
			forward = mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Editar Contacto");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar contacto, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return forward;				
	}
	//TERMINA COMENTAR EN PRODUCCION
	/**/
		
	public ActionForward registrarExitoso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("******-----ENTRANDO A REGISTRO EXITOSO CONTACTO------*******");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
		return mapping.findForward("JSP_SUCCESS");		
	}		
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("******-----ENTRANDO A CANCELAR DE CONTACTO------*******");
		request.getSession().removeAttribute("MODO_PAGINA");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_CANCEL").getPath());
		return mapping.findForward("JSP_CANCEL");		
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
    	System.out.println("******-----ENTRANDO A SALVAR CONTACTO------*******");
    	String strMethod = request.getParameter("method");
    	ComRegContactFormv2 regForm = (ComRegContactFormv2) form;
		String msg = null;
		String type = null;    	
    	ActionErrors errors = regForm.validate(mapping, request); 
    	if (errors == null) {
    		/*try{    
    			regForm.setConfirmarCorreoElectronico(regForm.getConfirmarCorreoElectronico());	
    			
    			UsuarioWebVO webVo = getUsuario(request.getSession());	
    			long idEmpresaPadre = webVo.getIdPropietario();
    			RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance(); 
    			RegistroContactoVO contactoVo  = regForm.getContacto();   
    			contactoVo.setIdEmpresa(idEmpresaPadre);    
    			System.out.println("-----estatus del contacto:" + contactoVo.getEstatus());
    			//TERCERA EMPRESA
    			long lngTerceraEmpresa = regForm.getSelectTerceraEmpresa();
    			System.out.println("-----lngTerceraEmpresa:" + lngTerceraEmpresa);
    			if(lngTerceraEmpresa>0){
    				contactoVo.setIdTerceraEmpresa(lngTerceraEmpresa);
    			} 
    			//DOMICILIO
    			DomicilioVO domicilio = regForm.getDomicilio();
				contactoVo.setDomicilio(domicilio);
    			//TELEFONOS
    			List<TelefonoVO> lstTempTelefonos = setAllPhones(regForm, contactoVo);
    			contactoVo.setTelefonos(lstTempTelefonos); 
    			//TERMINA TELEFONOS				
    			long idContacto = regForm.getIdContacto();
    			System.out.println("-----idContacto:" + idContacto);
    			contactoVo.setIdContacto(idContacto);
    			//checar method?
    			if(idContacto>0){
    				//es edicion  
    				idContacto = service.actualizarContacto(contactoVo, webVo.getIdUsuario());
    			} else {
    				//es insercion
    				idContacto = service.registrarContacto(contactoVo, webVo.getIdUsuario());
    			}
    			msg = getMensaje(request, "emp.registro.msg1");
				type = ResultVO.TYPE_SUCCESS;	
				//COMENTAR EN PRODUCCION Y PROBAR: regForm.reset();
		        request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
		        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);	      		        
    		} catch (ServiceLocatorException e) {
    			logger.error(e);
    			registraError(request, "contacto.registro.msg2");
    		} catch (Exception e) {
    			logger.error(e);
    			registraError(request, "contacto.registro.msg3");
    		}*/
    	} else{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.message", "Favor de revisar los datos proporcionados."));		
			request.setAttribute(Globals.ERROR_KEY, errors);
			//DEBUG
			Iterator itMessages = errors.get();
			while(itMessages.hasNext()){				
				Object actionMsg = itMessages.next(); 
				System.out.println("******-----errorAction:" + actionMsg.toString());
			}
			//END DEBUG
		}
    	request.getSession().removeAttribute("PROPIETARIO_TELEFONO");
    	request.getSession().removeAttribute("ID_CONTACTO_PROPIETARIO");
        request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Salvar Contacto");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Salvar contacto, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);	            					        
    }    			

    private List<TelefonoVO> setAllPhones(ComRegContactFormv2 updForm, RegistroContactoVO contactoVo){
    	//System.out.println("******ComRegContactActionv2.setAllPhones");
    	List<TelefonoVO> lstFinal = new ArrayList<TelefonoVO>();    	
    	List<TelefonoVO> lstOldTelefonos = contactoVo.getTelefonos();
    	RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
    	List<TelefonoVO> lstUpdatedAdditional;
		try {
			lstUpdatedAdditional = service.consultarTelefonos(contactoVo.getIdEmpresa(), Constantes.TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());
			lstUpdatedAdditional = removeTelefonoPrincipal(lstUpdatedAdditional);
			//EN TODOS LOS CASOS DEBE HABER TELEFONO PRINCIPAL, OBTENER DATOS TELEFONO PRINCIPAL
	    	TelefonoVO telPrincipal = setTelefonoPrincipal(updForm,lstOldTelefonos);
	    	//System.out.println("--ComRegContactActionv2-setAllPhones telefonoPrincipal:" + telPrincipal.toString());    
	    	lstFinal.add(telPrincipal);
	    	if(lstUpdatedAdditional!=null){
	    		//System.out.println("--ComRegContactActionv2-setAllPhones lstUpdatedAdditional:" + lstUpdatedAdditional.size());
	        	Iterator<TelefonoVO> itUpdatedAdditional = lstUpdatedAdditional.iterator();
	        	while(itUpdatedAdditional.hasNext()){
	        		TelefonoVO tempvo = (TelefonoVO)itUpdatedAdditional.next();    	
	        		if(!UtilsPhone.existsInList(lstFinal,tempvo)){
	        			lstFinal.add(tempvo);
	        			//System.out.println("--ComRegContactActionv2-setAllPhones agregando :" + tempvo.toString());        			
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
    		Iterator<TelefonoVO> it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			//System.out.println("--ComRegContactActionv2-getTelefonoPrincipal:" + temp.toString());
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    				it.remove();
    				break;
    			}    			
    		}
    	}		
		return lst;
	}      
    
    private TelefonoVO setTelefonoPrincipal(ComRegContactFormv2 updForm, List<TelefonoVO> lst){
		TelefonoVO telPrincipal = new TelefonoVO();
		if(lst!=null && lst.size()>0){
			telPrincipal = UtilsPhone.getTelefonoPrincipal(lst);			
			//System.out.println("--ComRegContactActionv2-setTelefonoPrincipal telefonoPrincipal:" + telPrincipal.toString());
		}
		telPrincipal.setAcceso(updForm.getAcceso());
		telPrincipal.setClave(updForm.getClave());
		telPrincipal.setExtension(updForm.getExtension());
		telPrincipal.setIdTipoTelefono(updForm.getIdTipoTelefono());
		telPrincipal.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		telPrincipal.setTelefono(updForm.getTelefono());
		return telPrincipal;
    }      
    
	public ActionForward cambiarEstatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("******-----ENTRANDO A CAMBIAR ESTATUS------*******");
		ComRegContactFormv2 regForm = (ComRegContactFormv2) form;			
		String msg = null;
		String type = null;    	
    	ActionErrors errors = regForm.validate(mapping, request);      
    	System.out.println("******-----ENTRANDO A CAMBIAR ESTATUS validado------*******");
    	if (errors == null) {
    		long idSelectedContact = regForm.getSelectContact();    		
    		RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();   
    		UsuarioWebVO webVo = getUsuario(request.getSession());
    		try {    			
    			/*RegistroContactoVO teVo = service.findContactoById(idSelectedContact);
    			int estatusOriginal = teVo.getEstatus();
    			System.out.println("******-----estatusOriginal: " + estatusOriginal);
    			//RN02. No se debe permitir la inhabilitación de un contacto si tiene ofertas de empleo activas asociadas.
    			if(estatusOriginal == Constantes.ESTATUS.ACTIVO.getIdOpcion()){
    				int cuentaOfertas = service.countOffersByIdContact(teVo.getIdContacto(), Constantes.ESTATUS.ACTIVO.getIdOpcion());
    				System.out.println("******-----cuentaOfertas: " + cuentaOfertas);
    				if(cuentaOfertas>0){
    					System.out.println("----NO SE PUEDE CAMBIAR");
    					registraMensaje(request, "contacto.registro.msg1");
    				} else {
    					service.actualizaEstatusContacto(idSelectedContact, Constantes.ESTATUS.INACTIVO.getIdOpcion(), webVo.getIdUsuario());
    				}
    			} else if(estatusOriginal == Constantes.ESTATUS.INACTIVO.getIdOpcion()) {
    				service.actualizaEstatusContacto(idSelectedContact, Constantes.ESTATUS.ACTIVO.getIdOpcion(), webVo.getIdUsuario());
    			}*/
    			//REFRESCAR LOS CONTACTOS
    			List<RegistroContactoVO> lstContactos = null; //service.findAllContactsByIdEmpresa(teVo.getIdEmpresa());
    			if(lstContactos==null){
    				lstContactos = new ArrayList<RegistroContactoVO>();					
    			}
    			regForm.setLstContactos(lstContactos);    			
    			request.getSession().setAttribute(LST_CONTACTOS, lstContactos);				    			    			
    		} /*catch (BusinessException e) {
    			logger.error(e);
    			registraError(request, "contacto.registro.negocio.err");
    		} catch (SQLException e) {
    			logger.error(e);
    			registraError(request, "contacto.registro.locator.err");
    		} catch (ServiceLocatorException e) {
    			logger.error(e);
    			registraError(request, "contacto.registro.msg2");
    		} */catch (Exception e) {
    			logger.error(e);
    			registraError(request, "contacto.registro.msg3");
    		}		    		
    	}    	
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Cambiar Contacto");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Cambiar contacto, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);		
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
		request.getSession().setAttribute(TITULO_PAGINA, "Contacto");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Contacto, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ request.getRequestURI().toString());	
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
    }

    	
}
