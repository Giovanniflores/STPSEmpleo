package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

/**
 * Proporciona acceso a los metodos de persistencia para registros de la entidad "Teléfono"
 * 
 * @author haydee.vertti
 *
 */

@Local
public interface TelefonoFacadeLocal {

	/**
	 * Registra un número telefónico
	 * @param vo TelefonoVO
	 * @return identificador del telefono
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public long save(TelefonoVO vo) throws PersistenceException;

	/**
	 * Elimina un número telefónico
	 * @param vo TelefonoVO
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */	
	public void delete(TelefonoVO vo) throws PersistenceException;
		
	/**
	 * Method 'findById'
	 * Regresa un objeto TelefonoVO con los datos correspondientes al teléfono
	 * cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return TelefonoVO
	 */		
	public TelefonoVO findById(long id) throws PersistenceException;
	
	/**
	 * Method 'update'
	 * 
	 * @param vo
	 */		
	public void update(TelefonoVO vo) throws PersistenceException;
	
	/**
	 * Method 'getTelefonosPropietario'
	 * Obtiene un listado de Objetos de tipo TelefonoVO pertenecientes a un propietario
	 * 
	 * @param idPropietario
	 * @param idTipoPropietario
	 * @return List<TelefonoVO> 
	 */			
	public List<TelefonoVO> getTelefonosPropietario(long idPropietario, long idTipoPropietario) throws PersistenceException;	
	
	/**
	 * Method 'getTelefonosPropietario'
	 * Obtiene un listado de Objetos de tipo TelefonoVO pertenecientes a un propietario y a un tipo de telefono específico
	 * 
	 * @param idPropietario
	 * @param idTipoPropietario
	 * @param idTipoTelefono
	 * @return List<TelefonoVO> 
	 */			
	public List<TelefonoVO> getTelefonosPropietario(long idPropietario, long idTipoPropietario, long idTipoTelefono) throws PersistenceException;
	
	public long getIdTelefonoPrincipal(long idPropietario, long idTipoPropietario) throws PersistenceException;
	
	public TelefonoVO getTelefonoPrincipal(long idPropietario, long idTipoPropietario) throws PersistenceException;
	
	/** Elimina todo los telefonos 
	 * @param idPropietario
	 * @param idTipoPropietario
	 * @param principal
	 * @throws PersistenceException
	 */
	public void deleteAll(long idPropietario, long idTipoPropietario,long principal) throws PersistenceException;
	
	public void borrarTelefonos(long idPropietario, long idTipoPropietario);	
}
