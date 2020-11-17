package mx.gob.stps.portal.web.company.action;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.address.action.DomicilioAction;
import mx.gob.stps.portal.web.company.delegate.RegContDelegate;
import mx.gob.stps.portal.web.company.delegate.RegContDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.RegContForm;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * Administra los estados de la presentacion para el formulario de Registro de Contactos
 * 
 * @author haydee.vertti
 */
// TODO ELIMINAR CLASE YA NO SE USA
public class ContactoAction extends DomicilioAction{

	private static Logger logger = Logger.getLogger(ContactoAction.class);
	
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
			RegContForm contactoForm = (RegContForm) form;
			
	        // Se inicializan catalogos y se suben a sesión
			RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
			RegContDelegate serviceCont = RegContDelegateImpl.getInstance();
	        CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();
	        //long[] lstFiltroTipoTelefono = new long[] {2, 3, 4};     
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
			//request.getSession().setAttribute("TERMINOS_CONDICIONES", Constantes.TERMINOS_CONDICIONES); 	
			//	TODO CONSTANTES?	
			request.getSession().setAttribute("ESTATUS_ACTIVO_ID", Constantes.ESTATUS.ACTIVO.getIdOpcion());
			request.getSession().setAttribute("ESTATUS_ACTIVO_DES", Constantes.ESTATUS.ACTIVO.getOpcion());
			request.getSession().setAttribute("ESTATUS_INACTIVO_ID", Constantes.ESTATUS.INACTIVO.getIdOpcion());
			request.getSession().setAttribute("ESTATUS_INACTIVO_DES", Constantes.ESTATUS.INACTIVO.getOpcion());					
		    
			//obtener usuario y empresas y pasar a la forma
			/*try{
				UsuarioWebVO webVo = getUsuario(request.getSession());
				System.out.println("------------webVo.getIdUsuario():" + webVo.getIdUsuario());
				EmpresaVO empresaVo = service.findEmpresaById(webVo.getIdPropietario());
				System.out.println("------------empresaVo.getIdUsuario():" + empresaVo.getIdUsuario());
				long idEmpresaPadre = empresaVo.getIdEmpresa();
				System.out.println("------------idEmpresaPadre:" + idEmpresaPadre);
				//	TODO constante empresa padre 
				request.getSession().setAttribute("ID_EMPRESA_PADRE", idEmpresaPadre);				
				contactoForm.setIdEmpresa(idEmpresaPadre);				
																
				List<RegistroContactoVO> lstContactos = serviceCont.findAllByIdEmpresa(empresaVo.getIdEmpresa());
				if(lstContactos!=null){
					contactoForm.setLstContacto(lstContactos);
					System.out.println("------------lstContactos:" + lstContactos.size());
				} else {
					lstContactos = new ArrayList<RegistroContactoVO>();					
				}
				//	TODO constante lista de contactos				
				request.getSession().setAttribute("LST_REGISTRO_CONTACTOS", lstContactos);		
			}catch(Exception e){
				e.printStackTrace();
			}*/
			
	    } catch (ServiceLocatorException e) {
	        logger.error(e);
	        registraError(request, "app.exp.locator.err");
	    } catch (Exception e) {
	        logger.error(e);
	        registraError(request, "app.exp.negocio.err");
	    }     		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Contacto");
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
	
	public ActionForward cambiarEstatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("******-----ENTRANDO A CAMBIAR ESTATUS------*******");
		RegContForm contactoForm = (RegContForm) form;
		//String msg = null;
		//String type = null;    	
		long idSelectContactos = contactoForm.getIdContacto();
		
		RegContDelegate service = RegContDelegateImpl.getInstance();
		
		/*try {
			RegistroContactoVO teVo = service.findById(idSelectContactos);
			int estatusOriginal = teVo.getEstatus();
			System.out.println("******-----estatusOriginal: " + estatusOriginal);
			if(estatusOriginal == Constantes.ESTATUS.INACTIVO.getIdOpcion()) {
				service.actualizaEstatus(idSelectContactos, Constantes.ESTATUS.ACTIVO.getIdOpcion());
			}else{
				service.actualizaEstatus(idSelectContactos, Constantes.ESTATUS.INACTIVO.getIdOpcion());
			}
			List<RegistroContactoVO> lstContactos = service.findAllByIdEmpresa(teVo.getIdEmpresa());
			if(lstContactos!=null){
				contactoForm.setLstContacto(lstContactos);    				
			} else {
				lstContactos = new ArrayList<RegistroContactoVO>();					
			}
			request.getSession().setAttribute("LST_REGISTRO_CONTACTOS", lstContactos);	
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Contacto");
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
    	System.out.println("******-----ENTRANDO A SALVAR CONTACTOS------*******");
    	
    	RegContForm contactoForm = (RegContForm) form;
    	ActionErrors errors = contactoForm.validate(mapping, request);           
    	
    	/*if (errors == null) {    		
    		try{    			       	    			    			
    			RegistroContactoVO conactoVo = contactoForm.getTerceraEmpresa();
    			long idEmpresaPadre = Long.parseLong(request.getSession().getAttribute("ID_EMPRESA_PADRE").toString());    			
    			conactoVo.setIdEmpresa(idEmpresaPadre);
    			//DOMICILIO
    			DomicilioVO domicilio = contactoForm.getDomicilio();
    			conactoVo.setDomicilio(domicilio);    			
    			//TELEFONOS
    			List<TelefonoVO> lstTelefonos = contactoForm.getLstTelefonos();
    			conactoVo.setTelefonos(lstTelefonos);
    			
    			//long idUsuario = getUsuario(request.getSession()).getIdUsuario();
    			
    			//long idContato = 0;//service.registrarContacto(conactoVo, idUsuario);
    			
    			//String msg = getMensaje(request, "emp.registro.msg1");
    			//String type = ResultVO.TYPE_SUCCESS;
				
		        request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSP_SUCCESS").getPath());
		        return mapping.findForward(FORWARD_TEMPLATE_FORM);	      
		        
			} catch (Exception e) {
				logger.error(e);
				registraError(request, "emp.registro.msg2.err");
            }
    	} else{
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
		}*/
        request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Contacto");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Reg&iacute;strate en nuestra Bolsa de trabajo y postula la oferta de empleo que m&aacute;s se apegue seg&uacute;n tu curriculum vitae sin costo f&aacute;cil y sin intermediarios.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_FORM);	            					        
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
