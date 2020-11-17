package mx.gob.stps.portal.web.company.action;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.address.action.DomicilioAction;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.ComRegPartnerCompanyFormv2;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

//TODO ELIMINAR CLASE YA NO SE USA
public class ComRegPartnerCompanyActionv2 extends DomicilioAction{
	
	private static Logger logger = Logger.getLogger(ComRegPartnerCompanyActionv2.class);
	
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
			ComRegPartnerCompanyFormv2 regForm = (ComRegPartnerCompanyFormv2) form;
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
			Iterator<CatalogoOpcionVO> itLstTipoTelefono = opcionesTipoTelefono.iterator();
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
		            	String strFullName1 = "";
		            	String strFullName2 = "";
		            	String strName1 = (p1.getNombre()==null) ? "": p1.getNombre();  
		            	String strName2 = (p2.getNombre()==null) ? "": p2.getNombre();  
		            	String strFirstName1 = (p1.getApellido1()==null) ? "": p1.getApellido1();  
		            	String strFirstName2 = (p2.getApellido1()==null) ? "": p2.getApellido1();  
		            	String strSecondName1 = (p1.getApellido2()==null) ? "": p1.getApellido2();  
		            	String strSecondName2 = (p2.getApellido2()==null) ? "": p2.getApellido2();  
		            	String strCompanyName1 = (p1.getRazonSocial()==null) ? "": p1.getRazonSocial();  
		            	String strCompanyName2 = (p2.getRazonSocial()==null) ? "": p2.getRazonSocial();  
		            	
		            	if(p1.getIdTipoPersona()==(long)Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
		            		strFullName1 = strName1 + " " + strFirstName1 + " " + strSecondName1;		            		
		            	} else if(p1.getIdTipoPersona()==(long)Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
		            		strFullName1 = strCompanyName1;
		            	}
		            	if(p2.getIdTipoPersona()==(long)Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
		            		strFullName2 = strName2 + " " + strFirstName2 + " " + strSecondName2;
		            	} else if(p2.getIdTipoPersona()==(long)Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona()){
		            		strFullName2 = strCompanyName2;
		            	}
		               return strFullName1.compareToIgnoreCase(strFullName2);
		            }			 
		        });									
				regForm.setLstTercerasEmpresas(lstTerceras);
			} else {
				lstTerceras = new ArrayList<TerceraEmpresaVO>();					
			}				
			System.out.println("------------lstTerceras:" + lstTerceras.size());
			request.getSession().setAttribute(LST_TERCERAS_EMPRESAS, lstTerceras);*/
			
			request.getSession().setAttribute("PROPIETARIO_TELEFONO", "TERCERA_EMPRESA");
			request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, lstTelefonos);		
			regForm.setHiddenDesActividadEconomica(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, regForm.getIdActividadEconomica()));
			regForm.setHiddenDesTipoEmpresa(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_EMPRESA,regForm.getIdTipoEmpresa()));
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
		request.getSession().setAttribute(TITULO_PAGINA, "Editar Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return forward;				
	}	
	
	public ActionForward editar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	
		System.out.println("******-----ENTRANDO A FORMULARIO EDITAR TERCERA EMPRESA------*******");
		ComRegPartnerCompanyFormv2 regForm = (ComRegPartnerCompanyFormv2) form;
		UsuarioWebVO usuario = getUsuario(request.getSession());
		RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
		long idSelectedPartnerCompany = regForm.getSelectPartnerCompany();
		request.getSession().setAttribute("ID_TERCERA_EMPRESA_PROPIETARIO", idSelectedPartnerCompany);

		/*try{
			TerceraEmpresaVO foundVo = service.findById(idSelectedPartnerCompany);
			if(null!=foundVo){	
				int cuentaOfertas = service.countOffersByIdTerceraEmpresa(idSelectedPartnerCompany, Constantes.ESTATUS.ACTIVO.getIdOpcion());
				//System.out.println("******-----cuentaOfertas: " + cuentaOfertas);
				if(cuentaOfertas>0) {
					System.out.println("----NO SE PUEDE CAMBIAR");
					registraMensaje(request, "tercemp.registro.msg4");					
				} else {	
					regForm.setIdTerceraEmpresa(idSelectedPartnerCompany);
				        // Se inicializan catalogos y se suben a sesión
				        CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();
				        request.getSession().setAttribute(CAT_TIPO_EMPRESA, serviceCat.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_EMPRESA));
				        request.getSession().setAttribute(CAT_ACTIVIDAD_ECONOMICA, serviceCat.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA));
				        regForm.setHiddenDesActividadEconomica(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, foundVo.getIdActividadEconomica()));
				        regForm.setHiddenDesTipoEmpresa(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_EMPRESA,foundVo.getIdTipoEmpresa()));
					regForm.setRfc(foundVo.getRfc());
					regForm.setIdTipoPersona(foundVo.getIdTipoPersona());
					regForm.setNombre(foundVo.getNombre());
					regForm.setApellido1(foundVo.getApellido1());
					regForm.setApellido2(foundVo.getApellido2());					
					regForm.setRazonSocial(foundVo.getRazonSocial());
					regForm.setNombreComercial(foundVo.getNombreComercial());
					regForm.setDescripcion(foundVo.getDescripcion());
					regForm.setContactoEmpresa(foundVo.getContactoEmpresa());
					regForm.setIdTipoEmpresa(foundVo.getIdTipoEmpresa());
					regForm.setIdActividadEconomica(foundVo.getIdActividadEconomica());
					regForm.setNumeroEmpleados((int)foundVo.getNumeroEmpleados());	
					regForm.setCorreoElectronico(foundVo.getCorreoElectronico());
					regForm.setPreCorreoElectronico(foundVo.getCorreoElectronico());
					DomicilioVO domicilioVo = foundVo.getDomicilio();	
					System.out.println("-----DomicilioVO:" + domicilioVo.toString());
					if(null!=domicilioVo){
						regForm.setDomicilio(domicilioVo);
						regForm.setCodigoPostal(domicilioVo.getCodigoPostal());
						regForm.setCalle(domicilioVo.getCalle());
						regForm.setNumeroExterior(domicilioVo.getNumeroExterior());
						regForm.setNumeroInterior(domicilioVo.getNumeroInterior());
						regForm.setEntreCalle(domicilioVo.getEntreCalle());
						regForm.setyCalle(domicilioVo.getyCalle());		
						regForm.setIdColoniaSelected(domicilioVo.getIdColonia());
						ObtenerColoniaByIdDAO catColonia = new ObtenerColoniaByIdDAO();
						String desColonia = catColonia.obtenerColoniaPorId(domicilioVo.getIdColonia());
						//System.out.println("----desColonia:" + desColonia);
						regForm.setIdColoniaSelectedText(desColonia);						
					}
					List<TelefonoVO> lstTelefonos = foundVo.getTelefonos();
					List<TelefonoVO> lstTelefonosAdicionales = new ArrayList<TelefonoVO>();
					if(null!=lstTelefonos && lstTelefonos.size()>0){										
						Iterator<TelefonoVO> itLstTelefonos = lstTelefonos.iterator();
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
		}*/
		//Definir el forward dependiendo del usuario
		request.getSession().setAttribute("MODO_PAGINA", "MODO_EDICION");
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Editar Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		ActionForward forward = mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);		
		if (usuario.getPublicador())
			forward = mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());	

		request.getSession().setAttribute(TITULO_PAGINA, "Editar Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
		return forward;			
	}
	
	public ActionForward registrarExitoso(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
		return mapping.findForward("JSP_SUCCESS");		
	}		
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("MODO_PAGINA");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_CANCEL").getPath());
		return mapping.findForward("JSP_CANCEL");		
	}		
	
	public ActionForward cambiarEstatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("******-----ENTRANDO A CAMBIAR ESTATUS------*******");
    	ComRegPartnerCompanyFormv2 regForm = (ComRegPartnerCompanyFormv2) form;
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
				regForm.setLstTercerasEmpresas(lstTerceras);    				
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
    	}   			    	
	    request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Editar Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
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
    	ComRegPartnerCompanyFormv2 regForm = (ComRegPartnerCompanyFormv2) form;
		String msg = null;
		String type = null;    	
    	ActionErrors errors = regForm.validate(mapping, request);               	
    	CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();
    	if (errors == null) {    		
    		/*try{    		
    			regForm.setHiddenDesActividadEconomica(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA, regForm.getIdActividadEconomica()));
    			regForm.setHiddenDesTipoEmpresa(serviceCat.getOpcionById(Constantes.CATALOGO_OPCION_TIPO_EMPRESA,regForm.getIdTipoEmpresa()));
    			regForm.setConfirmarCorreoElectronico(regForm.getConfirmarCorreoElectronico());			
    			
    			UsuarioWebVO webVo = getUsuario(request.getSession());	
    			long idEmpresaPadre = webVo.getIdPropietario();    	    			
    			RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();       	    			    			
    			TerceraEmpresaVO terceraVo = regForm.getTerceraEmpresa();    			    					
    			terceraVo.setIdEmpresa(idEmpresaPadre);
    			//DOMICILIO
    			DomicilioVO domicilio = regForm.getDomicilio();
    			terceraVo.setDomicilio(domicilio);    	
    			//TELEFONOS
    			List<TelefonoVO> lstTempTelefonos = setAllPhones(regForm, terceraVo);
    			terceraVo.setTelefonos(lstTempTelefonos); 
    			//TERMINA TELEFONOS		    			    			
    			long idTerceraEmpresa = regForm.getIdTerceraEmpresa();
    			terceraVo.setIdTerceraEmpresa(idTerceraEmpresa);
    			//checar method?
    			if(idTerceraEmpresa>0){
    				//es edicion  
    				System.out.println("******-----EDICION de " + idTerceraEmpresa + " -- " + terceraVo.toString());        				
    				idTerceraEmpresa = service.actualizaEmpresa(terceraVo, webVo.getIdUsuario());
    			} else {
    				//es insercion
    				System.out.println("******-----CREACION------*******" + " -- " + terceraVo.toString());  
    				idTerceraEmpresa = service.registrarEmpresa(terceraVo, webVo.getIdUsuario());
    			}    			 
    			msg = getMensaje(request, "emp.registro.msg1");
				type = ResultVO.TYPE_SUCCESS;				
		        request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
		        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);	      		        
    		} catch (BusinessException e) {
    			logger.error(e);
    			registraError(request, "tercemp.registro.negocio.err");
    		} catch (ServiceLocatorException e) {
    			logger.error(e);
    			registraError(request, "tercemp.registro.msg2");
    		} catch (Exception e) {
    			logger.error(e);
    			registraError(request, "tercemp.registro.msg3");
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
    	request.getSession().removeAttribute("ID_TERCERA_EMPRESA_PROPIETARIO");
        request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Editar Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));	
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);	            					        
    }
	
    /*private List<TelefonoVO> setAllPhones(ComRegPartnerCompanyFormv2 updForm, TerceraEmpresaVO terceraVo){
    	//System.out.println("******ComRegPartnerCompanyAction.setAllPhones");
    	List<TelefonoVO> lstFinal = new ArrayList<TelefonoVO>();    	
    	List<TelefonoVO> lstOldTelefonos = terceraVo.getTelefonos();
    	RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
    	List<TelefonoVO> lstUpdatedAdditional;
		try {
			lstUpdatedAdditional = service.consultarTelefonos(terceraVo.getIdTerceraEmpresa(), Constantes.TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());
			lstUpdatedAdditional = removeTelefonoPrincipal(lstUpdatedAdditional);
			//lstUpdatedAdditional = updForm.getLstTelefonos();      	    	
			//EN TODOS LOS CASOS DEBE HABER TELEFONO PRINCIPAL, OBTENER DATOS TELEFONO PRINCIPAL
	    	TelefonoVO telPrincipal = setTelefonoPrincipal(updForm,lstOldTelefonos);
	    	System.out.println("--ComRegPartnerCompanyActionv2-setAllPhones telefonoPrincipal:" + telPrincipal.toString());    
	    	lstFinal.add(telPrincipal);
	    	if(lstUpdatedAdditional!=null){
	    		System.out.println("--ComRegPartnerCompanyAction-setAllPhones lstUpdatedAdditional:" + lstUpdatedAdditional.size());
	        	Iterator<TelefonoVO> itUpdatedAdditional = lstUpdatedAdditional.iterator();
	        	while(itUpdatedAdditional.hasNext()){
	        		TelefonoVO tempvo = (TelefonoVO)itUpdatedAdditional.next();    		
	        		if(!UtilsPhone.existsInList(lstFinal,tempvo)){
	        			lstFinal.add(tempvo);
	        			System.out.println("--ComRegPartnerCompanyAction-setAllPhones agregando :" + tempvo.toString());        			
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
    		Iterator<TelefonoVO> it = lst.iterator();    		
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
    
    private TelefonoVO setTelefonoPrincipal(ComRegPartnerCompanyFormv2 updForm, List<TelefonoVO> lst){
		TelefonoVO telPrincipal = new TelefonoVO();
		if(lst!=null && lst.size()>0){
			telPrincipal = UtilsPhone.getTelefonoPrincipal(lst);			
			System.out.println("--ComRegPartnerCompanyAction-setTelefonoPrincipal telefonoPrincipal:" + telPrincipal.toString());
		}
		telPrincipal.setAcceso(updForm.getAcceso());
		telPrincipal.setClave(updForm.getClave());
		telPrincipal.setExtension(updForm.getExtension());
		telPrincipal.setIdTipoTelefono(updForm.getIdTipoTelefono());
		telPrincipal.setPrincipal(Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion());
		telPrincipal.setTelefono(updForm.getTelefono());
		return telPrincipal;
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
		request.getSession().setAttribute(TITULO_PAGINA, "Editar Empresa");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Editar Empresa, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ request.getRequestURI().toString());	
        return mapping.findForward(FORWARD_TEMPLATE_MI_ESP_EMP);
    }	
	
}
