package mx.gob.stps.portal.core.infra.service;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import javax.ejb.Remote;
import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

/**
 * Proporciona el acceso a los servicios relacionados con la entidad Telefono
 * 
 * @author haydee.vertti
 *
 */
@Remote
public interface TelefonoAppServiceRemote {

	/**
	 * Obtiene un listado de objetos TelefonoVO relacionados a un propietario
	 * @param idPropietario
	 * @param idTipoPropietario
	 * @return List<TelefonoVO>
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */	
	public List<TelefonoVO> consultarTelefonos(long idPropietario, long idTipoPropietario);		
	
	public long getIdTelefonoPrincipal(long idPropietario, long idTipoPropietario);
	/**
	 * Registra un telefono
	 * @param vo
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */	
	public long registrarTelefono(TelefonoVO vo);		
	
	public void actualizarTelefono(TelefonoVO vo);
	
	public void eliminarTelefono(TelefonoVO vo);
	
	public void eliminarTelefonos(long idPropietario, long idTipoPropietario, long principal);
}
