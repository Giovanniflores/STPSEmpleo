package mx.gob.stps.portal.web.phone.action;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.infra.action.GenericAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.phone.form.PhoneForm;
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
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public final class PhoneAction extends GenericAction {

	private static Logger logger = Logger.getLogger(PhoneAction.class);

	/**
	 * Method 'init'
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */		
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(JSP_PHONE).getPath());
			PhoneForm phoneForm = (PhoneForm) form;   
	        //long[] lstFiltroTipoTelefono = new long[] {2, 3, 4};     
	        RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
	        CatalogoOpcionDelegate serviceCat = CatalogoOpcionDelegateImpl.getInstance();
	        
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
			
			if(request.getSession().getAttribute(LST_TELEFONOS_ADICIONALES)==null){
				logger.info("---checarTelefonosAdicionales en PhoneAction esta null :");	
				List<TelefonoVO> lst = new ArrayList<TelefonoVO>();		
				request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, lst);
			} else {
				List<TelefonoVO> checarTelefonosAdicionales = (List<TelefonoVO>) request.getSession().getAttribute("LST_TELEFONOS_ADICIONALES");	
				System.out.println("---checarTelefonosAdicionales en PhoneAction lstTelefonos.size() :" + checarTelefonosAdicionales.size());
				logger.info("---checarTelefonosAdicionales en PhoneAction lstTelefonos.size() :" + checarTelefonosAdicionales.size());				
			}
			
	    } catch (ServiceLocatorException e) {
	        logger.error(e);
	        registraError(request, "app.exp.locator.err");
	    } catch (Exception e) {
	        logger.error(e);
	        registraError(request, "app.exp.negocio.err");
	    }     		
		return mapping.findForward(JSP_PHONE);
	}
	
	
    public ActionForward salvarTelefonosAdicionales(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
    		HttpServletResponse response) {
    	UsuarioWebVO usuario = super.getUsuario(request.getSession());
    	PhoneForm phoneForm = (PhoneForm) form;    	
		String msg = null;
		String type = null;    	
    	ActionErrors errors = phoneForm.validate(mapping, request);
    	RegisterBusDelegate service = RegisterBusDelegateImpl.getInstance();
    	   	
    	
    	if (errors == null) {
    		try { 
				List<TelefonoVO> lstTelefonos = phoneForm.getLstTelefonos();
				request.getSession().setAttribute(LST_TELEFONOS_ADICIONALES, lstTelefonos);
				logger.info("---salvarTelefonosAdicionales en PhoneAction lstTelefonos.size() :" + lstTelefonos.size());
				if(null!=usuario){
					int idTipoUsuario = (int)usuario.getIdTipoUsuario();
					logger.info("---salvarTelefonosAdicionales en PhoneAction idTipoUsuario :" + idTipoUsuario);				
					if(idTipoUsuario == TIPO_USUARIO.CANDIDATO.getIdTipoUsuario()){
						service.eliminarTelefonos(usuario.getIdPropietario(), 								
								TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario(),
								MULTIREGISTRO.ADICIONAL.getIdOpcion());								
						for (TelefonoVO telefonoVO : lstTelefonos) {
								telefonoVO.setFechaAlta(new Date());
								telefonoVO.setIdPropietario(usuario.getIdPropietario());
								telefonoVO.setIdTipoPropietario(TIPO_PROPIETARIO.CANDIDATO.getIdTipoPropietario());	
								service.registrarTelefono(telefonoVO);		
						}				
						
					} else if(idTipoUsuario == TIPO_USUARIO.EMPRESA.getIdTipoUsuario()) {						
						if(null!=request.getSession().getAttribute("PROPIETARIO_TELEFONO")){
							String strTipoPropietario = (String)request.getSession().getAttribute("PROPIETARIO_TELEFONO");
							
							if(strTipoPropietario.equalsIgnoreCase("TERCERA_EMPRESA")){
								long lngIdPropietario = (Long)request.getSession().getAttribute("ID_TERCERA_EMPRESA_PROPIETARIO");							
								service.eliminarTelefonos(lngIdPropietario, 								
										TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario(),
										MULTIREGISTRO.ADICIONAL.getIdOpcion());			
								for (TelefonoVO telefonoVO : lstTelefonos) {
									logger.info("---salvarTelefonosAdicionales en PhoneAction telefonoVO.getTelefono() :" + telefonoVO.getTelefono());
										telefonoVO.setFechaAlta(new Date());
										telefonoVO.setIdPropietario(lngIdPropietario);
										telefonoVO.setIdTipoPropietario(TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());	
										service.registrarTelefono(telefonoVO);		
								}																
							} else if(strTipoPropietario.equalsIgnoreCase("CONTACTO")){
								
								long lngIdPropietario = (Long)request.getSession().getAttribute("ID_CONTACTO_PROPIETARIO");
								service.eliminarTelefonos(lngIdPropietario, 								
										TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario(),
										MULTIREGISTRO.ADICIONAL.getIdOpcion());			
								for (TelefonoVO telefonoVO : lstTelefonos) {
									logger.info("---salvarTelefonosAdicionales en PhoneAction telefonoVO.getTelefono() :" + telefonoVO.getTelefono());
										telefonoVO.setFechaAlta(new Date());
										telefonoVO.setIdPropietario(lngIdPropietario);
										telefonoVO.setIdTipoPropietario(TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());	
										service.registrarTelefono(telefonoVO);		
								}																
							}
						} else {
							service.eliminarTelefonos(usuario.getIdPropietario(), 								
									TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario(),
									MULTIREGISTRO.ADICIONAL.getIdOpcion());			
							for (TelefonoVO telefonoVO : lstTelefonos) {
								logger.info("---salvarTelefonosAdicionales en PhoneAction telefonoVO.getTelefono() :" + telefonoVO.getTelefono());
									telefonoVO.setFechaAlta(new Date());
									telefonoVO.setIdPropietario(usuario.getIdPropietario());
									telefonoVO.setIdTipoPropietario(TIPO_PROPIETARIO.EMPRESA.getIdTipoPropietario());	
									service.registrarTelefono(telefonoVO);		
							}			
							
							
						}
					} 	
				}
			    		
    		} catch (BusinessException e) {e.printStackTrace(); logger.error(e.getMessage());}
			  catch (SQLException e) {e.printStackTrace(); logger.error(e.getMessage());}
			  catch (ServiceLocatorException e) {e.printStackTrace(); logger.error(e.getMessage());}		
    		  catch (Exception e) {e.printStackTrace(); logger.error(e.getMessage());}	
			
    	}else{
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
    	
		try {
			String json = toJson(new ResultVO(msg, type));
			redirectJsonResponse(response, json);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	        return mapping.findForward(FORWARD_TEMPLATE_FORM);	        
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
	        return mapping.findForward(FORWARD_TEMPLATE_FORM);	
		}    
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
