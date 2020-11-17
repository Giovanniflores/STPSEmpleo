package mx.gob.stps.portal.core.empresa.service.impl;

import mx.gob.stps.portal.core.domicilio.service.DomicilioAppServiceLocal;
import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.empresa.service.RegistroContactoAppServiceRemote;
import mx.gob.stps.portal.core.empresa.vo.RegistroContactoVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.service.TelefonoAppServiceLocal;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.DomicilioFacadeLocal;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import java.util.*;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * Session Bean implementation class RegistroContactoAppService
 */
// TODO ELIMINAR CLASE YA NO SE UTILIZA
//@Stateless(name = "RegistroContactoAppService", mappedName = "RegistroContactoAppService")
public class RegistroContactoAppService implements RegistroContactoAppServiceRemote {

	private static Logger logger = Logger.getLogger(RegistroContactoAppService.class);
	/*@EJB
	private ContactoFacadeLocal contactoFacade;*/
	@EJB
	private TelefonoAppServiceLocal telefonoAppService;
	@EJB
	private DomicilioAppServiceLocal domicilioAppService;
	@EJB 
	private BitacoraFacadeLocal bitacoraFacade;	
	@EJB
	private DomicilioFacadeLocal domicilioFacade;

	public RegistroContactoAppService() {
	}

	/**
	 * Registra una contacto
	 * 
	 * @param vo
	 * @return long
	 */
	public long registrarContacto(RegistroContactoVO vo, long idUsuario) {
		long idContacto = 0;
		String detalle = "";
		
		vo.setFechaAlta(new Date());
		vo.setFechaModificacion(new Date());
		vo.setEstatus(Constantes.ESTATUS.ACTIVO.getIdOpcion());
		
		DomicilioVO domicilio = vo.getDomicilio();
		List<TelefonoVO> lstTelefonos = vo.getTelefonos();

		//idContacto = contactoFacade.save(vo);
		detalle = detalle + "idContacto=" + idContacto + "|";
		System.out.println("----RegistroContactoAppService.registrarContacto.idContacto:" + idContacto);

		domicilio.setFechaAlta(new Date());
		domicilio.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());
		domicilio.setIdPropietario(idContacto);		
		domicilioAppService.save(domicilio);
		detalle = detalle + "idDomicilio=" + domicilio.getIdDomicilio() + "|";
		System.out.println("----domicilioAppService.save(domicilio):");

		Iterator<TelefonoVO> itTel = lstTelefonos.iterator();
		while (itTel.hasNext()) {
			TelefonoVO telVo = (TelefonoVO) itTel.next();
			telVo.setFechaAlta(new Date());
			telVo.setIdPropietario(idContacto);
			telVo.setIdTipoPropietario(Constantes.TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());
			long idTelefono = telefonoAppService.registrarTelefono(telVo);
			detalle = detalle + "idTelefono=" + idTelefono + "|";
			System.out.println("----telefonoAppService.registrarTelefono.idTelefono:" + idTelefono);
		}
				
		bitacoraFacade.save(Constantes.EVENTO.REGISTRA_CONTACTO.getIdEvento(), idUsuario, 
				Constantes.EVENTO.REGISTRA_CONTACTO.getEvento(), Calendar.getInstance(), detalle, 
				idContacto, TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());

		return idContacto;
	}

	public RegistroContactoVO findContactoById(long id) {
		RegistroContactoVO contactoVo = null; //new RegistroContactoVO();
		//contactoVo = contactoFacade.findById(id);
		DomicilioVO domicilioVo = new DomicilioVO();
		List<TelefonoVO> lstTelefonos = new ArrayList<TelefonoVO>();
		try {
			domicilioVo = domicilioAppService.buscarDomicilioIdPropietario(contactoVo.getIdContacto(), (long) Constantes.TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());

			// si no está informada la colonia o la delegación, efectuamos una búsqueda con el identificador de la colonia para recuperar el nombre
			if (domicilioVo.getColonia()   == null || domicilioVo.getColonia().isEmpty() || 
			    domicilioVo.getMunicipio() == null || domicilioVo.getMunicipio().isEmpty()) {
				
				if (domicilioVo.getIdColonia() != 0){

					CodigoPostalVO codigoPostalVO = domicilioFacade.consultaCodigoPostal(domicilioVo.getIdColonia());

					if (codigoPostalVO != null && codigoPostalVO.getColoniaDescripcion() != null){
						domicilioVo.setColonia(codigoPostalVO.getColoniaDescripcion());
						domicilioVo.setMunicipio(codigoPostalVO.getMunicipioDescripcion());
						domicilioVo.setEntidad(codigoPostalVO.getEntidadDescripcion());
					}
				}			
			}
			contactoVo.setDomicilio(domicilioVo);
			
			lstTelefonos = telefonoAppService.consultarTelefonos(contactoVo.getIdContacto(),(long) Constantes.TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());
			contactoVo.setTelefonos(lstTelefonos);

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return contactoVo;
	}

	public List<RegistroContactoVO> findAllContactsByIdEmpresa(long idEmpresa) {
		List<RegistroContactoVO> lstContactos = null;
		//lstContactos = contactoFacade.findAllByIdEmpresa(idEmpresa);
		return lstContactos;
	}

	public long actualizarContacto(RegistroContactoVO vo, long idUsuario) {
		long idContacto = vo.getIdContacto();
		System.out.println("----RegistroContactoAppService.actualizarContacto :" + idContacto);
		String detalle = "";
		
		vo.setFechaModificacion(new Date());	
		
		//Obtener datos del anterior
		RegistroContactoVO oldContacto = findContactoById(idContacto);
		DomicilioVO oldDomicilio = oldContacto.getDomicilio();
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
		//List<TelefonoVO> oldTelefonos = oldContacto.getTelefonos();
		List<TelefonoVO> oldTelefonos = telefonoAppService.consultarTelefonos(idContacto, 
					Constantes.TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());
		//List<TelefonoVO> lstTelefonos = vo.getTelefonos();
		//EN TODOS LOS CASOS DEBE HABER TELEFONO PRINCIPAL, OBTENER DATOS TELEFONO PRINCIPAL NUEVO
		TelefonoVO telefonoPrincipal = getTelefonoPrincipal(vo.getTelefonos());
		System.out.println("---RegistroContactoAppService.actualizarContacto.telefonoPrincipal:" + telefonoPrincipal.toString());
		//OBTENER DATOS TELEFONOS ADICIONALES NUEVOS		
		List<TelefonoVO> telefonosAdicionales = getTelefonosAdicionales(vo.getTelefonos());
		System.out.println("---RegistroContactoAppService.actualizarContacto.telefonosAdicionales:" + telefonosAdicionales.size());
    	if(oldTelefonos==null || oldTelefonos.size()==0){
			//C1: NO HAY TELEFONOS ANTERIORES DE NINGUN TIPO
    		System.out.println("---CASO1");
    		//INSERTAR TELEFONO PRINCIPAL NUEVO  
    		telefonoPrincipal.setFechaAlta(new Date());
			telefonoPrincipal.setIdPropietario(vo.getIdContacto());
			telefonoPrincipal.setIdTipoPropietario(TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());  		
			long idTelefono = telefonoAppService.registrarTelefono(telefonoPrincipal);
			detalle = detalle + " insert id_Telefono=" + idTelefono;
			logger.info("---RegistroContactoAppService.actualizarContacto idTelefono :" + idTelefono + " getTelefono:" + telefonoPrincipal.getTelefono());
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
					nuevo.setIdPropietario(vo.getIdEmpresa());
					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());    							
	    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
	    			detalle = detalle + " insert id_Telefono=" + idTelefonoAd;
	    			logger.info("---RegistroContactoAppService.actualizarContacto idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono());   
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
    				telefonoPrincipal.setIdPropietario(vo.getIdContacto());
    				telefonoPrincipal.setIdTipoPropietario(TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());      				
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
        		    			logger.info("---RegistroContactoAppService.eliminarTelefono idTelefonoA :" + lngTel + " getTelefono:" + strTel);    								    							
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
        					nuevo.setIdPropietario(vo.getIdEmpresa());
        					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());   							    						
    		    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
    		    			detalle = detalle + " insert id_Telefono=" + idTelefonoAd;
    		    			logger.info("---RegistroContactoAppService.actualizarContacto idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono());    								
    					}
    				}    
    				*/   				
    			} else {
    				//C3: HAY TELEFONO PRINCIPAL ANTERIOR	
    				System.out.println("---CASO3");
    				//UPDATE TELEFONO PRINCIPAL CON NUEVO
    				TelefonoVO telPrin = getTelefonoPrincipal(oldTelefonos);    				
    				telefonoPrincipal.setIdTelefono(telPrin.getIdTelefono());
    				telefonoPrincipal.setIdPropietario(vo.getIdContacto());
    				telefonoPrincipal.setIdTipoPropietario(TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());      				    				
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
        					nuevo.setIdPropietario(vo.getIdEmpresa());
        					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());      						
    		    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
    		    			detalle = detalle + " insert id_Telefono=" + idTelefonoAd;
    		    			logger.info("---RegistroContactoAppService.actualizarContacto idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono());    								
    					}
    				}    
    				*/				   				
    			}					
			} else {
				//C4: SOLO HAY TELEFONOS ADICIONALES ANTERIORES	
				System.out.println("---CASO4");
        		//INSERTAR TELEFONO PRINCIPAL NUEVO
        		telefonoPrincipal.setFechaAlta(new Date());
    			telefonoPrincipal.setIdPropietario(vo.getIdContacto());
    			telefonoPrincipal.setIdTipoPropietario(TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());  		    			
    			long idTelefono = telefonoAppService.registrarTelefono(telefonoPrincipal);
    			detalle = detalle + " insert id_Telefono=" + idTelefono;
    			logger.info("---RegistroContactoAppService.actualizarContacto idTelefono :" + idTelefono + " getTelefono:" + telefonoPrincipal.getTelefono());
    			/*
				//REMOVE TELEFONOS ADICIONALES ANTERIORES
				if(telefonosAdicionales!=null && telefonosAdicionales.size()>0){
					Iterator ItOldTelefonos = telefonosAdicionales.iterator();
					while(ItOldTelefonos.hasNext()){    						
						TelefonoVO temp = (TelefonoVO) ItOldTelefonos.next();
						if(temp.getPrincipal()!=Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    						String strTel = temp.getTelefono();
    						temp.setFechaAlta(new Date());
    						temp.setIdPropietario(vo.getIdEmpresa());
    						temp.setIdTipoPropietario(TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());  	    						
    						long lngTel = temp.getIdTelefono();
    						telefonoAppService.eliminarTelefono(temp);
    		    			logger.info("---RegistroContactoAppService.eliminarTelefono idTelefonoA :" + lngTel + " getTelefono:" + strTel);    								    							
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
    					nuevo.setIdPropietario(vo.getIdEmpresa());
    					nuevo.setIdTipoPropietario(TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());  
    	    			long idTelefonoAd = telefonoAppService.registrarTelefono(nuevo);
    	    			detalle = detalle + " insert id_Telefono=" + idTelefonoAd;
    	    			logger.info("---RegistroContactoAppService.actualizarContacto idTelefonoAd :" + idTelefonoAd + " getTelefono:" + nuevo.getTelefono());    								
    				}
    			}    	
    			*/					
			}
		}
		
    	//contactoFacade.update(vo);
		
    	System.out.println("----RegistroContactoAppService.actualizarContacto.idContacto:" + idContacto);
		bitacoraFacade.save(EVENTO.MODIFICA_CONTACTO.getIdEvento(), idUsuario, 
				EVENTO.MODIFICA_CONTACTO.getEvento(), Calendar.getInstance(), detalle,
				idContacto, TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());		
		return idContacto;
	}
	
	private List<TelefonoVO> getTelefonosAdicionales(List<TelefonoVO> lst){
		ArrayList<TelefonoVO> lstAdicionales = new ArrayList<TelefonoVO>();
    	if(lst!=null && lst.size()>0){
    		Iterator it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.ADICIONAL.getIdOpcion()){
    				lstAdicionales.add(temp);
    			}    			
    		}
    	}			
		return lstAdicionales;
	}
	
	private TelefonoVO getTelefonoPrincipal(List<TelefonoVO> lst){
		TelefonoVO telvo = new TelefonoVO();
    	if(lst!=null && lst.size()>0){
    		Iterator it = lst.iterator();    		
    		while(it.hasNext()){
    			TelefonoVO temp = (TelefonoVO) it.next();
    			if(temp.getPrincipal()==Constantes.MULTIREGISTRO.PRINCIPAL.getIdOpcion()){
    				System.out.println("---RegistroContactoAppService.getTelefonoPrincipal.principal:" + temp.toString());
    				telvo = temp;
    				break;
    			}    			
    		}
    	}		
		return telvo;
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
	

	public List<RegistroContactoVO> findAllByIdEmpresa(long id) {
		List<RegistroContactoVO> lstContacto = new ArrayList<RegistroContactoVO>();
		//lstContacto = contactoFacade.findAllByIdEmpresa(id);
		return lstContacto;
	}

	public void actualizaEstatus(long idContacto, int estatus) {
		//contactoFacade.actualizaEstatus(idContacto, estatus);
	}

	public void actualizaEstatus(long idContacto, int estatus, long idUsuario) {
		String detalle = "estatus=" + estatus + "|";
		//contactoFacade.actualizaEstatus(idContacto, estatus);
		bitacoraFacade.save(Constantes.EVENTO.MODIFICA_CONTACTO.getIdEvento(), idUsuario, 
				Constantes.EVENTO.MODIFICA_CONTACTO.getEvento(), Calendar.getInstance(), detalle, idContacto, TIPO_PROPIETARIO.CONTACTO.getIdTipoPropietario());		
	}

	/*public int countOffersByIdContact(long idContacto, int estatus){
		int activeOffers = 0;
		RegistroContactoDAO contDAO = new RegistroContactoDAO();
		try {
			activeOffers = contDAO.countOffersByIdContacto(idContacto, estatus);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return activeOffers;
	}*/
}