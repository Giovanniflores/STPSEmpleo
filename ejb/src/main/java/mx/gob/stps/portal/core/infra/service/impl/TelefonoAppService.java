package mx.gob.stps.portal.core.infra.service.impl;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.service.TelefonoAppServiceLocal;
import mx.gob.stps.portal.core.infra.service.TelefonoAppServiceRemote;
import mx.gob.stps.portal.core.persistencia.facade.TelefonoFacadeLocal;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * Session Bean implementation class TelefonoAppService
 */
@Stateless(name = "TelefonoAppService", mappedName = "TelefonoAppService") 
public class TelefonoAppService implements TelefonoAppServiceRemote, TelefonoAppServiceLocal{

	@EJB private TelefonoFacadeLocal telefonoFacade;
	
	public TelefonoAppService() {}
	
	/**
	 * Obtiene un listado de objetos TelefonoVO relacionados a un propietario
	 * @param idPropietario
	 * @param idTipoPropietario
	 * @return List<TelefonoVO>
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */	
	public List<TelefonoVO> consultarTelefonos(long idPropietario, long idTipoPropietario){
		List<TelefonoVO> lstTelefonos = null;
		lstTelefonos = telefonoFacade.getTelefonosPropietario(idPropietario, idTipoPropietario);		
		return lstTelefonos;
		
	}	
	
	public long getIdTelefonoPrincipal(long idPropietario, long idTipoPropietario){
		return telefonoFacade.getIdTelefonoPrincipal(idPropietario, idTipoPropietario);
	}
	
	/**
	 * Registra un telefono
	 * @param vo
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */	
	public long registrarTelefono(TelefonoVO vo){
		return telefonoFacade.save(vo);
		
	}		
	
	/**
	 * Actualiza un telefono
	 * @param vo
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */	
	public void actualizarTelefono(TelefonoVO vo){
		telefonoFacade.update(vo);		
	}		
	
	/**
	 * Elimina un telefono
	 * @param vo
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */		
	public void eliminarTelefono(TelefonoVO vo){
		telefonoFacade.delete(vo);
	}
	
	
	/**
	 * Elimina todo telefono
	 * @param vo
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */		
	public void eliminarTelefonos(long idPropietario, long idTipoPropietario,long principal){
		telefonoFacade.deleteAll(idPropietario,idTipoPropietario,principal);
	}

	

	
}
