package mx.gob.stps.portal.web.company.action;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.address.action.DomicilioAction;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.ComRegPartnerCompanyForm;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * Administra los estados de la presentacion para el formulario de Registro de Tercera Empresa
 * 
 * @author haydee.vertti
 */
//TODO ELIMINAR CLASE YA NO SE USA
public class ComRegPartnerCompanyAction extends DomicilioAction{

	private static Logger logger = Logger.getLogger(ComRegPartnerCompanyAction.class);

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
	@SuppressWarnings("unchecked")
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("******-----ENTRANDO A FORMULARIO REGISTRAR TERCERA EMPRESA------*******");	
		try{
			ComRegPartnerCompanyForm regForm = (ComRegPartnerCompanyForm) form;
			List<TelefonoVO> lstTelefonos = new ArrayList<TelefonoVO>();
						
			regForm.reset();
			regForm.setIdTipoTelefono(2);
			regForm.setDescripcion("");
			regForm.setCorreoElectronico("");
			regForm.setConfirmarCorreoElectronico("");
			regForm.setCodigoPostal("");
			regForm.setCalle("");
			regForm.setNumeroExterior("");
			regForm.setNumeroInterior("");
			regForm.setEntreCalle("");
        	regForm.setyCalle("");
			
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
//			TODO CONSTANTES?
			request.getSession().setAttribute("CLAVE_TELEFONO_FIJO", Constantes.CLAVE_TELEFONO_FIJO);
			request.getSession().setAttribute("CLAVE_TELEFONO_CELULAR", Constantes.CLAVE_TELEFONO_CELULAR); 				
		    request.getSession().setAttribute("CLAVE_TIPO_PERSONA_FISICA", Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona());
		    request.getSession().setAttribute("CLAVE_TIPO_PERSONA_MORAL", Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona());
			request.getSession().setAttribute("ESTATUS_ACTIVO_ID", Constantes.ESTATUS.ACTIVO.getIdOpcion());
			request.getSession().setAttribute("ESTATUS_ACTIVO_DES", Constantes.ESTATUS.ACTIVO.getOpcion());
			request.getSession().setAttribute("ESTATUS_INACTIVO_ID", Constantes.ESTATUS.INACTIVO.getIdOpcion());
			request.getSession().setAttribute("ESTATUS_INACTIVO_DES", Constantes.ESTATUS.INACTIVO.getOpcion());					
			System.out.println("******-----catalogos OK------*******");				
			
			//obtener usuario y empresas y pasar a la forma
			try{
				UsuarioWebVO webVo = getUsuario(request.getSession());
				
				long idEmpresa = webVo.getIdPropietario();								
				EmpresaVO empresaVo = service.findEmpresaById(idEmpresa);				
				long idEmpresaPadre =  webVo.getIdPropietario();
				System.out.println("------------idEmpresaPadre:" + idEmpresaPadre);
				request.getSession().setAttribute(ID_EMPRESA_PADRE, idEmpresaPadre);				
				String strTipoEmpresaPadre="";
				String strPadreLeyendaNombre = ""; 
				long lngConstante = (long)Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona();
				if(empresaVo.getIdTipoPersona()==lngConstante){
					strTipoEmpresaPadre = Constantes.TIPO_PERSONA.PERSONA_FISICA.getTipoPersona();
					strPadreLeyendaNombre = empresaVo.getNombre() + " " +  empresaVo.getApellido1() + " " + empresaVo.getApellido2();
				} else {
					strTipoEmpresaPadre = Constantes.TIPO_PERSONA.PERSONA_MORAL.getTipoPersona();
					strPadreLeyendaNombre = empresaVo.getRazonSocial();
				}				
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

				/*List<TerceraEmpresaVO> lstTerceras = new ArrayList<TerceraEmpresaVO>();
				lstTerceras = service.findAllByIdEmpresa(idEmpresaPadre);		
				
				if(null!=lstTerceras){
					//ordenar empresas por nombre o razon social
			        Collections.sort(lstTerceras, new Comparator(){
			        	 
			            public int compare(Object o1, Object o2) {
			            	TerceraEmpresaVO p1 = (TerceraEmpresaVO) o1;
			            	TerceraEmpresaVO p2 = (TerceraEmpresaVO) o2;
			            	String strName1 = "";
			            	String strName2 = "";
			            	
			            	if(p1.getIdTipoPersona()==(long)Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
			            		strName1 = p1.getNombre() + " " + p1.getApellido1() + " " + p1.getApellido2();
			            	} else if(p1.getIdTipoPersona()==(long)Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
			            		strName1 = p1.getRazonSocial();
			            	}
			            	if(p2.getIdTipoPersona()==(long)Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
			            		strName2 = p2.getNombre() + " " + p2.getApellido1() + " " + p2.getApellido2();
			            	} else if(p2.getIdTipoPersona()==(long)Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
			            		strName2 = p2.getRazonSocial();
			            	}
			               return strName1.compareToIgnoreCase(strName2);
			            }			 
			        });					
					
					regForm.setLstTerceras(lstTerceras);
				} else {
					lstTerceras = new ArrayList<TerceraEmpresaVO>();					
				}		
				request.getSession().setAttribute(LST_TERCERAS_EMPRESAS, lstTerceras);*/
				request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, lstTelefonos);		
				
				regForm.setHiddenDesActividadEconomica(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, regForm.getIdActividadEconomica()));
				regForm.setHiddenDesTipoEmpresa(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_EMPRESA,regForm.getIdTipoEmpresa()));
				
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
		
		ActionForward forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);

		UsuarioWebVO usuario = getUsuario(request.getSession());
		if (usuario.getPublicador())
			forward = mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);

	    request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return forward;		
	}	
	
	/*
	//COMENTAR EN PRODUCCION
	//NO BORRAR, ES UN CAMBIO EN PROGRESO	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	
		System.out.println("******-----ENTRANDO A FORMULARIO EDITAR TERCERA EMPRESA------*******");
		ComRegPartnerCompanyForm regForm = (ComRegPartnerCompanyForm) form;
		UsuarioWebVO usuario = getUsuario(request.getSession());
		RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
		long idSelectedPartnerCompany = regForm.getSelectPartnerCompany();
		//List<TerceraEmpresaVO> lstTerceras = regForm.getLstTerceras();
		TerceraEmpresaVO foundVo;
		try{
			foundVo = service.findById(idSelectedPartnerCompany);
			if(null!=foundVo){	
				int cuentaOfertas = service.countOffersByIdTerceraEmpresa(idSelectedPartnerCompany, Constantes.ESTATUS.ACTIVO.getIdOpcion());
				//System.out.println("******-----cuentaOfertas: " + cuentaOfertas);
				if(cuentaOfertas>0) {
					System.out.println("----NO SE PUEDE CAMBIAR");
					registraMensaje(request, "tercemp.registro.msg4");					
				} else {	
					//TODO: incluir idTerceraEmpresa en el FORM 
					//regForm.setIdTerceraEmpresa(idSelectedPartnerCompany);
					//System.out.println("------idTerceraEmpresa:" + foundVo.getIdTerceraEmpresa());
					regForm.setRfc(foundVo.getRfc());
					//tipo de persona?
					//nombre
					//apellido1
					//apellido2
					//razon social
					regForm.setNombreComercial(foundVo.getNombreComercial());
					regForm.setDescripcion(foundVo.getDescripcion());
					regForm.setContactoEmpresa(foundVo.getContactoEmpresa());
					//tipo de empresa?
					//actividad economica?
					regForm.setNumeroEmpleados((int)foundVo.getNumeroEmpleados());						
					DomicilioVO domicilioVo = foundVo.getDomicilio();	
					if(null!=domicilioVo){
						regForm.setDomicilio(domicilioVo);
						regForm.setCodigoPostal(domicilioVo.getCodigoPostal());
						regForm.setCalle(domicilioVo.getCalle());
						regForm.setNumeroExterior(domicilioVo.getNumeroExterior());
						regForm.setNumeroInterior(domicilioVo.getNumeroInterior());
						regForm.setEntreCalle(domicilioVo.getEntreCalle());
						regForm.setyCalle(domicilioVo.getyCalle());									
					}
					List<TelefonoVO> lstTelefonos = foundVo.getTelefonos();
					List<TelefonoVO> lstTelefonosAdicionales = new ArrayList<TelefonoVO>();
					if(lstTelefonos.size()>0){										
						Iterator itLstTelefonos = lstTelefonos.iterator();
						while(itLstTelefonos.hasNext()){
							TelefonoVO telVo = (TelefonoVO) itLstTelefonos.next();
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
						regForm.setLstTelefonos(lstTelefonos);	
						request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, lstTelefonosAdicionales);				
					}						
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
		ActionForward forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);		
		if (usuario.getPublicador())
			forward = mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());		
		return forward;			
	}
	*/
	
	public ActionForward registrarExitoso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
		return mapping.findForward("JSP_SUCCESS");		
	}		
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_CANCEL").getPath());
		return mapping.findForward("JSP_CANCEL");		
	}			
	
	public ActionForward cambiarEstatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("******-----ENTRANDO A CAMBIAR ESTATUS------*******");
    	ComRegPartnerCompanyForm regForm = (ComRegPartnerCompanyForm) form;

		String msg = null;
		String type = null;    	
    	ActionErrors errors = regForm.validate(mapping, request);    
    	System.out.println("******-----ENTRANDO A CAMBIAR ESTATUS validado------*******");
    	if (errors == null) {
    		System.out.println("******-----errors == null");
    		long idSelectedPartnerCompany = regForm.getSelectPartnerCompany();
    		System.out.println("******-----ENTRANDO A CAMBIAR ESTATUS idSelectedPartnerCompany:" + idSelectedPartnerCompany);
    		RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
    		
    		UsuarioWebVO webVo = getUsuario(request.getSession());
    		System.out.println("******-----ENTRANDO A CAMBIAR ESTATUS webVo:" + webVo.getIdUsuario());
    		
    		/*try {
    			TerceraEmpresaVO teVo = service.findById(idSelectedPartnerCompany);
    			int estatusOriginal = teVo.getEstatus();
    			System.out.println("******-----estatusOriginal: " + estatusOriginal);
    			//RN02. No se debe permitir la inhabilitación de una tercera empresa si tiene ofertas de empleo activas asociadas.
    			if(estatusOriginal == Constantes.ESTATUS.ACTIVO.getIdOpcion()){
    				int cuentaOfertas = service.countOffersByIdTerceraEmpresa(teVo.getIdTerceraEmpresa(), Constantes.ESTATUS.ACTIVO.getIdOpcion());
    				System.out.println("******-----cuentaOfertas: " + cuentaOfertas);
    				if(cuentaOfertas>0){
    					System.out.println("----NO SE PUEDE CAMBIAR");
    					registraMensaje(request, "tercemp.registro.msg1");
    				} else {	
    					service.actualizaEstatus(idSelectedPartnerCompany, Constantes.ESTATUS.INACTIVO.getIdOpcion(), webVo.getIdUsuario());
    				}
    			} else if(estatusOriginal == Constantes.ESTATUS.INACTIVO.getIdOpcion()) {
    				service.actualizaEstatus(idSelectedPartnerCompany, Constantes.ESTATUS.ACTIVO.getIdOpcion(), webVo.getIdUsuario());
    			}		
    			
    			List<TerceraEmpresaVO> lstTerceras = service.findAllByIdEmpresa(teVo.getIdEmpresa());
    			if(lstTerceras==null){
    				lstTerceras = new ArrayList<TerceraEmpresaVO>();
    			}

				regForm.setLstTerceras(lstTerceras);
    			request.getSession().setAttribute(LST_TERCERAS_EMPRESAS, lstTerceras);						    			
    			
    		} catch (BusinessException e) {
    			logger.error(e);
    			registraError(request, "tercemp.registro.negocio.err");
    		} catch (SQLException e) {
    			logger.error(e);
    			registraError(request, "tercemp.registro.locator.err");
    		} catch (ServiceLocatorException e) {
    			logger.error(e);
    			registraError(request, "tercemp.registro.msg2");
    		} catch (Exception e) {
    			logger.error(e);
    			registraError(request, "tercemp.registro.msg3");
    		}*/

    	} else {
    		System.out.println("******-----errors != null"); 		
    	}   	
		    	
		//request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	    request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);		
    
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
    	System.out.println("******-----ENTRANDO A SALVAR TERCERA EMPRESA------*******");
    	
    	ComRegPartnerCompanyForm regForm = (ComRegPartnerCompanyForm) form;
		String msg = null;
		String type = null;    	
    	ActionErrors errors = regForm.validate(mapping, request);           
    	
    	CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();
		try {
			regForm.setHiddenDesActividadEconomica(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, regForm.getIdActividadEconomica()));
			regForm.setHiddenDesTipoEmpresa(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_EMPRESA,regForm.getIdTipoEmpresa()));
			regForm.setConfirmarCorreoElectronico(regForm.getConfirmarCorreoElectronico());
			
		} catch (ServiceLocatorException e) {
			logger.error(e);
			registraError(request, "emp.registro.locator.err");
		} catch (Exception e) {
			logger.error(e);
			registraError(request, "emp.registro.msg2.err");
        }    	
    	
    	
    	if (errors == null) {
    		
    		try{    		
    			UsuarioWebVO webVo = getUsuario(request.getSession());	
    			RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();       	    			    			
    			//TerceraEmpresaVO terceraVo = regForm.getTerceraEmpresa();
    			
    			long idEmpresaPadre = webVo.getIdPropietario();    			
    			//long idEmpresaPadre = Long.parseLong(request.getSession().getAttribute(ID_EMPRESA_PADRE).toString());    			
    			//terceraVo.setIdEmpresa(idEmpresaPadre);
    			//DOMICILIO
    			DomicilioVO domicilio = regForm.getDomicilio();
    			//terceraVo.setDomicilio(domicilio);    	
    			//TELEFONOS
    			//List<TelefonoVO> lstTempTelefonos = setAllPhones(regForm, terceraVo);
    			//terceraVo.setTelefonos(lstTempTelefonos); 
    			//TERMINA TELEFONOS		    			    			
    			/*    		
    			//COMENTAR EN PRODUCCION 	
    			long idTerceraEmpresa = regForm.getIdTerceraEmpresa();
    			terceraVo.setIdTerceraEmpresa(idTerceraEmpresa);
    			//checar method?
    			if(idTerceraEmpresa>0){
    				//es edicion  
    				idTerceraEmpresa = service.actualizaEmpresa(terceraVo, webVo.getIdUsuario());
    			} else {
    				//es insercion
    				idTerceraEmpresa = service.registrarEmpresa(terceraVo, webVo.getIdUsuario());
    			}    			 
    			//TERMINA COMENTAR EN PRODUCCION
    			*/
    			//DESCOMENTAR EN PRODUCCION    			
    			//long idTerceraEmpresa = service.registrarEmpresa(terceraVo, webVo.getIdUsuario());
    			//TERMINA DESCOMENTAR EN PRODUCCION
    			msg = getMensaje(request, "emp.registro.msg1");
				type = ResultVO.TYPE_SUCCESS;
				
		        request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
				PropertiesLoader properties = PropertiesLoader.getInstance();
				request.getSession().setAttribute(TITULO_PAGINA, "Salvar Empresa");
				request.getSession().setAttribute(DESCRIPCION_PAGINA, "Salvar Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
				request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
				request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
				request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);	      
		        
    		} /*catch (BusinessException e) {
    			logger.error(e);
    			registraError(request, "tercemp.registro.negocio.err");
    		} catch (ServiceLocatorException e) {
    			logger.error(e);
    			registraError(request, "tercemp.registro.msg2");
    		} */catch (Exception e) {
    			logger.error(e);
    			registraError(request, "tercemp.registro.msg3");
    		}		    		
		        		        
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
        request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);	            					        
    }
	
    /*private List<TelefonoVO> setAllPhones(ComRegPartnerCompanyForm updForm, TerceraEmpresaVO terceraVo){
    	//System.out.println("******ComRegPartnerCompanyAction.setAllPhones");
    	List<TelefonoVO> lstFinal = new ArrayList<TelefonoVO>();    	
    	List<TelefonoVO> lstOldTelefonos = terceraVo.getTelefonos();
    	RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
    	List<TelefonoVO> lstUpdatedAdditional;
		try {
			lstUpdatedAdditional = service.consultarTelefonos(terceraVo.getIdEmpresa(), Constantes.TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());
			lstUpdatedAdditional = removeTelefonoPrincipal(lstUpdatedAdditional);
			//lstUpdatedAdditional = updForm.getLstTelefonos();      	    	
			//EN TODOS LOS CASOS DEBE HABER TELEFONO PRINCIPAL, OBTENER DATOS TELEFONO PRINCIPAL
	    	TelefonoVO telPrincipal = setTelefonoPrincipal(updForm,lstOldTelefonos);
	    	//System.out.println("--ComRegPartnerCompanyAction-setAllPhones telefonoPrincipal:" + telPrincipal.toString());    
	    	lstFinal.add(telPrincipal);
	    	if(lstUpdatedAdditional!=null){
	    		//System.out.println("--ComRegPartnerCompanyAction-setAllPhones lstUpdatedAdditional:" + lstUpdatedAdditional.size());
	        	Iterator itUpdatedAdditional = lstUpdatedAdditional.iterator();
	        	while(itUpdatedAdditional.hasNext()){
	        		TelefonoVO tempvo = (TelefonoVO)itUpdatedAdditional.next();    		
	        		if(existsInList(lstFinal,tempvo)){
	        			itUpdatedAdditional.remove();
	        			//System.out.println("--ComRegPartnerCompanyAction-setAllPhones ELIMINANDO:" + tempvo.toString());
	        		} else{
	        			lstFinal.add(tempvo);
	        			//System.out.println("--ComRegPartnerCompanyAction-setAllPhones agregando :" + tempvo.toString());        			
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
    }*/
    
	private List<TelefonoVO> removeTelefonoPrincipal(List<TelefonoVO> lst){
    	if(lst!=null && lst.size()>0){
    		Iterator it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			//System.out.println("--ComRegPartnerCompanyAction-getTelefonoPrincipal:" + temp.toString());
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    				it.remove();
    				break;
    			}    			
    		}
    	}		
		return lst;
	}      
    
    private TelefonoVO setTelefonoPrincipal(ComRegPartnerCompanyForm updForm, List<TelefonoVO> lst){
		TelefonoVO telPrincipal = new TelefonoVO();
		if(lst!=null && lst.size()>0){
			telPrincipal = getTelefonoPrincipal(lst);			
			//System.out.println("--ComRegPartnerCompanyAction-setTelefonoPrincipal telefonoPrincipal:" + telPrincipal.toString());
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
    			//System.out.println("--ComRegPartnerCompanyAction-getTelefonoPrincipal:" + temp.toString());
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
		request.getSession().setAttribute(TITULO_PAGINA, "Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ request.getRequestURI().toString());	
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
    }
        
}
