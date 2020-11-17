package mx.gob.stps.portal.core.empresa.service.impl;

import mx.gob.stps.portal.core.domicilio.service.DomicilioAppServiceLocal;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.service.TerceraEmpresaAppServiceRemote;
import mx.gob.stps.portal.core.empresa.vo.TerceraEmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.service.TelefonoAppServiceLocal;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import java.util.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * Session Bean implementation class TerceraEmpresaAppService
 */
//TODO ELIMINAR CLASE YA NO SE UTILIZA
//@Stateless(name = "TerceraEmpresaAppService", mappedName = "TerceraEmpresaAppService") 
public class TerceraEmpresaAppService implements TerceraEmpresaAppServiceRemote {

	private static Logger logger = Logger.getLogger(TerceraEmpresaAppService.class);
	//@EJB private TerceraEmpresaFacadeLocal terceraEmpresaFacade;
	@EJB private TelefonoAppServiceLocal telefonoAppService;	
	@EJB private DomicilioAppServiceLocal domicilioAppService;
	@EJB private BitacoraFacadeLocal bitacoraFacade;
	
	public TerceraEmpresaAppService() {}
	
	public long registrarEmpresa(TerceraEmpresaVO vo, long idUsuario) {		
		long idTerceraEmpresa = 0; 	
		String detalle = "";
		
		vo.setFechaAlta(new Date());		
		vo.setFechaUltimaActualizacion(new Date());		
		//System.out.println("TerceraEmpresaAppService.registrarEmpresa.Constantes.ESTATUS.ACTIVO.getIdOpcion():" + Constantes.ESTATUS.ACTIVO.getIdOpcion());
		vo.setEstatus(Constantes.ESTATUS.ACTIVO.getIdOpcion());		
		
		DomicilioVO domicilio = vo.getDomicilio();
		List<TelefonoVO> lstTelefonos = vo.getTelefonos();		
		
		//idTerceraEmpresa = terceraEmpresaFacade.save(vo);
		detalle = "idTerceraEmpresa=" + idTerceraEmpresa + "|idEmpresa=" + vo.getIdEmpresa() + "|";
		System.out.println("----TerceraEmpresaAppService.registrarEmpresa.idTerceraEmpresa:" + idTerceraEmpresa);
		
		domicilio.setFechaAlta(new Date());
		domicilio.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());
		domicilio.setIdPropietario(idTerceraEmpresa);				
		domicilioAppService.save(domicilio);
		detalle = detalle + "idDomicilio=" + domicilio.getIdDomicilio() + "|";
		System.out.println("----domicilioAppService.save(domicilio):");
		
		Iterator<TelefonoVO> itTel = lstTelefonos.iterator();
		while(itTel.hasNext()){
			TelefonoVO telVo = (TelefonoVO )itTel.next();
			telVo.setFechaAlta(new Date());
			telVo.setIdPropietario(idTerceraEmpresa);
			telVo.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());
			long idTelefono = telefonoAppService.registrarTelefono(telVo);
			detalle = detalle + "idTelefono=" + idTelefono + "|";
			System.out.println("----telefonoAppService.registrarTelefono.idTelefono:" + idTelefono);			
		}			
		
		bitacoraFacade.save(Constantes.EVENTO.REGISTRA_TERCERA_EMPRESA.getIdEvento(), idUsuario, 
				Constantes.EVENTO.REGISTRA_TERCERA_EMPRESA.getEvento(), Calendar.getInstance(), detalle,
				idTerceraEmpresa, TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());
		return idTerceraEmpresa;	
	}

	//@Override
	@SuppressWarnings("null")
	public TerceraEmpresaVO findEmpresaById(long id) {
		TerceraEmpresaVO terceraVo = null; //new TerceraEmpresaVO();		

		try {
			DomicilioVO domicilioVo = 
					domicilioAppService.buscarDomicilioIdPropietario(id, Constantes.TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());
			terceraVo.setDomicilio(domicilioVo);
			List<TelefonoVO> lstTelefonos = 
					telefonoAppService.consultarTelefonos(id, Constantes.TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());
			terceraVo.setTelefonos(lstTelefonos);
		} catch (BusinessException e) {
			e.printStackTrace();
		}		
		return terceraVo;
	}

	//@Override
	public long actualizarEmpresa(TerceraEmpresaVO vo, long idUsuario) {
		long idTerceraEmpresa = vo.getIdTerceraEmpresa();
		System.out.println("----TerceraEmpresaAppService.actualizar3aEmpresa :" + idTerceraEmpresa);
		String detalle = "";
		
		vo.setFechaUltimaActualizacion(new Date());
		
		//Obtener datos del anterior
		TerceraEmpresaVO oldTerceraEmpresa = findEmpresaById(idTerceraEmpresa);
		DomicilioVO oldDomicilio = oldTerceraEmpresa.getDomicilio();
		if(null==oldDomicilio){
			//A1: No tiene domicilio
			//Insert domicilio, configurar datos TIPO_PROPIETARIO.CONTACTO
			DomicilioVO domicilio = vo.getDomicilio();
			domicilioAppService.save(domicilio);	
			detalle = detalle + " insert id_domicilio=" + domicilio.getIdDomicilio();
			System.out.println("----domicilioAppService.save(domicilio):");
		} else {
			//A2: Si tiene domicilio
			//Update domicilio, configurar datos TIPO_PROPIETARIO.CONTACTO
			DomicilioVO domicilio = vo.getDomicilio();
			domicilioAppService.update(domicilio);
			detalle = detalle + " update id_domicilio=" + domicilio.getIdDomicilio();
			System.out.println("----domicilioAppService.update(domicilio):");
		}		
		//List<TelefonoVO> oldTelefonos = oldTerceraEmpresa.getTelefonos();
		List<TelefonoVO> oldTelefonos = telefonoAppService.consultarTelefonos(idTerceraEmpresa, Constantes.TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());
		//List<TelefonoVO> lstTelefonos = vo.getTelefonos();
		//EN TODOS LOS CASOS DEBE HABER TELEFONO PRINCIPAL, OBTENER DATOS TELEFONO PRINCIPAL NUEVO
		TelefonoVO telefonoPrincipal = getTelefonoPrincipal(vo.getTelefonos());
		System.out.println("---TerceraEmpresaAppService.actualizar3aEmpresa.telefonoPrincipal:" + telefonoPrincipal.toString());
		//OBTENER DATOS TELEFONOS ADICIONALES NUEVOS		
		List<TelefonoVO> telefonosAdicionales = getTelefonosAdicionales(vo.getTelefonos());
		System.out.println("---TerceraEmpresaAppService.actualizar3aEmpresa.telefonosAdicionales:" + telefonosAdicionales.size());
    	if(oldTelefonos==null || oldTelefonos.size()==0){
			//C1: NO HAY TELEFONOS ANTERIORES DE NINGUN TIPO
    		System.out.println("---CASO1");
    		//INSERTAR TELEFONO PRINCIPAL NUEVO 
    		telefonoPrincipal.setFechaAlta(new Date());
			telefonoPrincipal.setIdPropietario(vo.getIdTerceraEmpresa());
			telefonoPrincipal.setIdTipoPropietario(TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());  		
			long idTelefono = telefonoAppService.registrarTelefono(telefonoPrincipal);
			detalle = detalle + " insert id_Telefono=" + idTelefono;
			logger.info("---TerceraEmpresaAppService.actualizar3aEmpresa idTelefono :" + idTelefono + " getTelefono:" + telefonoPrincipal.getTelefono());
			/*
    		//INSERTAR TELEFONOS ADICIONALES NUEVO			
			if(telefonosAdicionales!=null && telefonosAdicionales.size()>0){
				Iterator ItAdicionales = telefonosAdicionales.iterator();
				while(ItAdicionales.hasNext()){
					TelefonoVO temp = (TelefonoVO) ItAdicionales.next();
					TelefonoVO nuevo = new TelefonoVO();
					nuevo.setAcceso(temp.getAcceso());
					nuevo.setClave(temp.getClave());
					nuevo.setExtension(temp.getExtension());
					nuevo.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
					nuevo.setTelefono(temp.getTelefono());
					nuevo.setTipoTelefono(temp.getTipoTelefono());
					nuevo.setFechaAlta(new Date());
					nuevo.setIdPropietario(vo.getIdTerceraEmpresa());
					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());    							
	    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
	    			detalle = detalle + " insert id_Telefono=" + idTelefonoAd;
	    			logger.info("---TerceraEmpresaAppService.actualizar3aEmpresa idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono());   
				}
			}	
			*/					
    	} else {
    		if(hasPrincipalPhone(oldTelefonos)){
    			if(hasAdditionalPhone(oldTelefonos)){
    				//C2: HAY PRINCIPAL Y ADICIONALES ANTERIORES
    				System.out.println("---CASO2");
    				//UPDATE TELEFONO PRINCIPAL CON NUEVO
    				TelefonoVO telPrin = getTelefonoPrincipal(oldTelefonos);    				
    				telefonoPrincipal.setIdTelefono(telPrin.getIdTelefono());    				
    				telefonoPrincipal.setIdPropietario(vo.getIdTerceraEmpresa());
    				telefonoPrincipal.setIdTipoPropietario(TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());      				
    				telefonoAppService.actualizarTelefono(telefonoPrincipal);   
    				detalle = detalle + " update id_Telefono=" + telefonoPrincipal.getIdTelefono();  
    				/*
    				//REMOVE TELEFONOS ADICIONALES ANTERIORES
    				if(oldTelefonos!=null && oldTelefonos.size()>0){
    					Iterator ItOldTelefonos = oldTelefonos.iterator();
    					while(ItOldTelefonos.hasNext()){    						
    						TelefonoVO temp = (TelefonoVO) ItOldTelefonos.next();
    						if(temp.getPrincipal()!=Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
        						String strTel = temp.getTelefono();
        						long lngTel = temp.getIdTelefono();
        						telefonoAppService.eliminarTelefono(temp);
        		    			logger.info("---TerceraEmpresaAppService.actualizar3aEmpresa eliminarTelefono idTelefonoA :" + lngTel + " getTelefono:" + strTel);    								    							
    						}
    					}
    				}    				    				    				
    				//INSERT TELEFONOS ADICIONALES NUEVOS
    				if(telefonosAdicionales!=null && telefonosAdicionales.size()>0){
    					Iterator ItAdicionales = telefonosAdicionales.iterator();
    					while(ItAdicionales.hasNext()){
    						TelefonoVO temp = (TelefonoVO) ItAdicionales.next();
        					TelefonoVO nuevo = new TelefonoVO();
        					nuevo.setAcceso(temp.getAcceso());
        					nuevo.setClave(temp.getClave());
        					nuevo.setExtension(temp.getExtension());
        					nuevo.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
        					nuevo.setTelefono(temp.getTelefono());
        					nuevo.setTipoTelefono(temp.getTipoTelefono());
        					nuevo.setFechaAlta(new Date());
        					nuevo.setIdPropietario(vo.getIdTerceraEmpresa());
        					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());   							    						
    		    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
    		    			detalle = detalle + " insert id_Telefono=" + idTelefonoAd;
    		    			logger.info("---TerceraEmpresaAppService.actualizar3aEmpresa idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono());    								
    					}
    				}       
    				*/				    				
    			} else {
    				//C3: HAY TELEFONO PRINCIPAL ANTERIOR	
    				System.out.println("---CASO3");    
    				//UPDATE TELEFONO PRINCIPAL CON NUEVO    				
    				TelefonoVO telPrin = getTelefonoPrincipal(oldTelefonos);    				
    				telefonoPrincipal.setIdTelefono(telPrin.getIdTelefono());
    				telefonoPrincipal.setIdPropietario(vo.getIdTerceraEmpresa());
    				telefonoPrincipal.setIdTipoPropietario(TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());      				    				
    				telefonoAppService.actualizarTelefono(telefonoPrincipal);
    				detalle = detalle + " update id_Telefono=" + telefonoPrincipal.getIdTelefono();  
    				/*
    				//INSERT TELEFONOS ADICIONALES NUEVOS
    				if(telefonosAdicionales!=null && telefonosAdicionales.size()>0){
    					Iterator ItAdicionales = telefonosAdicionales.iterator();
    					while(ItAdicionales.hasNext()){
    						TelefonoVO temp = (TelefonoVO) ItAdicionales.next();
        					TelefonoVO nuevo = new TelefonoVO();
        					nuevo.setAcceso(temp.getAcceso());
        					nuevo.setClave(temp.getClave());
        					nuevo.setExtension(temp.getExtension());
        					nuevo.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
        					nuevo.setTelefono(temp.getTelefono());
        					nuevo.setTipoTelefono(temp.getTipoTelefono());
        					nuevo.setFechaAlta(new Date());
        					nuevo.setIdPropietario(vo.getIdTerceraEmpresa());
        					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());  
        					logger.info("---TerceraEmpresaAppService.actualizar3aEmpresa getTelefono:" + nuevo.getTelefono());    
    		    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
    		    			logger.info("---TerceraEmpresaAppService.actualizar3aEmpresa idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono() + " OK");    
    		    			detalle = detalle + " insert id_Telefono=" + idTelefonoAd;    		    										
    					}
    				}   
    				*/ 				   				    				
    			}	
    		} else {
    			//C4: SOLO HAY TELEFONOS ADICIONALES ANTERIORES
				System.out.println("---CASO4");
        		//INSERTAR TELEFONO PRINCIPAL NUEVO
        		telefonoPrincipal.setFechaAlta(new Date());
    			telefonoPrincipal.setIdPropietario(vo.getIdTerceraEmpresa());
    			telefonoPrincipal.setIdTipoPropietario(TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());  		    			
    			long idTelefono = telefonoAppService.registrarTelefono(telefonoPrincipal);
    			detalle = detalle + " insert id_Telefono=" + idTelefono;
    			logger.info("---TerceraEmpresaAppService.actualizar3aEmpresa idTelefono :" + idTelefono + " getTelefono:" + telefonoPrincipal.getTelefono());    			
				//REMOVE TELEFONOS ADICIONALES ANTERIORES
				if(telefonosAdicionales!=null && telefonosAdicionales.size()>0){
					Iterator ItOldTelefonos = telefonosAdicionales.iterator();
					while(ItOldTelefonos.hasNext()){    						
						TelefonoVO temp = (TelefonoVO) ItOldTelefonos.next();
						if(temp.getPrincipal()!=Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    						String strTel = temp.getTelefono();
    						temp.setFechaAlta(new Date());
    						temp.setIdPropietario(vo.getIdTerceraEmpresa());
    						temp.setIdTipoPropietario(TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());  	    						
    						long lngTel = temp.getIdTelefono();
    						telefonoAppService.eliminarTelefono(temp);
    		    			logger.info("---TerceraEmpresaAppService.actualizar3aEmpresa eliminarTelefono idTelefonoA :" + lngTel + " getTelefono:" + strTel);    								    							
						}
					}
				}    				    			
				/*
				//INSERT TELEFONOS ADICIONALES NUEVOS    	
    			if(telefonosAdicionales!=null && telefonosAdicionales.size()>0){
    				Iterator ItAdicionales = telefonosAdicionales.iterator();
    				while(ItAdicionales.hasNext()){
    					TelefonoVO temp = (TelefonoVO) ItAdicionales.next();
    					TelefonoVO nuevo = new TelefonoVO();
    					nuevo.setAcceso(temp.getAcceso());
    					nuevo.setClave(temp.getClave());
    					nuevo.setExtension(temp.getExtension());
    					nuevo.setPrincipal(Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion());
    					nuevo.setTelefono(temp.getTelefono());
    					nuevo.setTipoTelefono(temp.getTipoTelefono());
    					nuevo.setFechaAlta(new Date());
    					nuevo.setIdPropietario(vo.getIdTerceraEmpresa());
    					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());  
    	    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
    	    			detalle = detalle + " insert id_Telefono=" + idTelefonoAd;
    	    			logger.info("---TerceraEmpresaAppService.actualizar3aEmpresa idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono());    								
    				}
    			}  
    			*/  			    			
    		}
    	}	
    	if(vo.getIdTipoPersona()==(long)Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona()){
    		vo.setRazonSocial(null);
    	} else {
    		vo.setNombre(null);
    		vo.setApellido1(null);
    		vo.setApellido2(null);
    	}
		//terceraEmpresaFacade.update(vo);		
		System.out.println("----TerceraEmpresaAppService.actualizarEmpresa.idTerceraEmpresa:" + idTerceraEmpresa);
		bitacoraFacade.save(EVENTO.MODIFICA_TERCERA_EMPRESA.getIdEvento(), idUsuario, 
				EVENTO.MODIFICA_TERCERA_EMPRESA.getEvento(), Calendar.getInstance(), detalle,
				idTerceraEmpresa, TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());	
		return idTerceraEmpresa;
	}

	private List<TelefonoVO> getTelefonosAdicionales(List<TelefonoVO> lst){
		ArrayList<TelefonoVO> lstAdicionales = new ArrayList<TelefonoVO>();
    	if(lst!=null && lst.size()>0){
    		Iterator<TelefonoVO> it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion()){
    				lstAdicionales.add(temp);
    			}    			
    		}
    	}			
		return lstAdicionales;
	}

	/**
	 * Method <b>getTelefonoPrincipal</b>
	 * Regresa un objeto de tipo TelefonoVO con los datos del telefono principal
	 * encontrado en una lista lst
	 * 
	 * @param lst
	 * @return TelefonoVO
	 */		
	private TelefonoVO getTelefonoPrincipal(List<TelefonoVO> lst){
		TelefonoVO telvo = new TelefonoVO();
    	if(lst!=null && lst.size()>0){
    		Iterator<TelefonoVO> it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			System.out.println("--UtilsPhone.getTelefonoPrincipal:" + temp.toString());
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    				telvo = temp;
    				break;
    			}    			
    		}
    	}		
		return telvo;
	}  		
	
	/**
	 * Method <b>hasPrincipalPhone</b>
	 * Indica si el listado contiene un telefono de tipo principal
	 * 
	 * @param lst
	 * @return boolean
	 */		
	private boolean hasPrincipalPhone(List<TelefonoVO> lst){
    	boolean principal = false;
    	if(lst!=null && lst.size()>0){
    		Iterator<TelefonoVO> it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			//System.out.println("--UtilsPhone.hasPrincipalPhone:" + temp.toString());
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    				principal =  true;
    				break;
    			}    			
    		}
    	}
    	return principal;
    }	
    
	/**
	 * Method <b>hasAdditionalPhone</b>
	 * Indica si el listado contiene al menos un telefono de tipo adicional
	 * 
	 * @param lst
	 * @return boolean
	 */	
    private boolean hasAdditionalPhone(List<TelefonoVO> lst){
    	boolean additional = false;
    	if(lst!=null && lst.size()>0){
    		Iterator<TelefonoVO> it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			//System.out.println("--UtilsPhone.hasAdditionalPhone:" + temp.toString());
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion()){
    				additional =  true;
    				break;
    			}    			
    		}
    	}
    	return additional;
    }

	/*@Override
	public List<TerceraEmpresaVO> findAllByIdEmpresa(long id) {
		List<TerceraEmpresaVO> lstTerceras = new ArrayList<TerceraEmpresaVO>();
		lstTerceras = terceraEmpresaFacade.findAllByIdEmpresa(id);		
		return lstTerceras;
	}

	@Override
	public void actualizaEstatus(long idTerceraEmpresa, int estatus) {
		terceraEmpresaFacade.actualizaEstatus(idTerceraEmpresa, estatus);
	}

	public void actualizaEstatus(long idTerceraEmpresa, int estatus, long idUsuario) {
		terceraEmpresaFacade.actualizaEstatus(idTerceraEmpresa, estatus);
		String detalle = "estatus=" + estatus + "|";
		bitacoraFacade.save(Constantes.EVENTO.MODIFICA_TERCERA_EMPRESA.getIdEvento(), idUsuario, 
				Constantes.EVENTO.MODIFICA_TERCERA_EMPRESA.getEvento(), Calendar.getInstance(), detalle,
				idTerceraEmpresa, TIPO_PROPIETARIO.TERCERA_EMPRESA.getIdTipoPropietario());		
	}
	
	
	@Override
	public int countOffersByIdTerceraEmpresa(long idTerceraEmpresa, int estatus) {
		return terceraEmpresaFacade.countOffersByIdTerceraEmpresa(idTerceraEmpresa, estatus);
	}*/

}