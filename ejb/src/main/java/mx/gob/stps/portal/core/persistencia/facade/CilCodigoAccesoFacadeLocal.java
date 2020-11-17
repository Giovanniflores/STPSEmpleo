package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.cil.vo.CilCodigoAccesoVO;


/**
 * Proporciona acceso a los metodos de persistencia para registros de la entidad "CilCodigoAcceso"
 * 
 * @author haydee.vertti
 *
 */
@Local
public interface CilCodigoAccesoFacadeLocal {

	/**
	 * Registra un CilCodigoAcceso
	 * @param vo CilCodigoAccesoVO
	 * @return Identificador del CilCodigoAcceso
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public long save(CilCodigoAccesoVO vo) throws PersistenceException;		
	
	/**
	 * Elimina un CilCodigoAcceso
	 * @param vo CilCodigoAccesoVO
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */		
	public void delete(CilCodigoAccesoVO vo) throws PersistenceException;	
	
	public void delete(long idCilCodigoAcceso) throws PersistenceException;	
	
	/**
	 * Method 'findById'
	 * Regresa un objeto CilCodigoAccesoVO con los datos correspondientes al
	 * CilCodigoAcceso cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return CilVO
	 */	
	public CilCodigoAccesoVO findById(long id) throws PersistenceException;		
	
	/**
	 * Method 'update'
	 * 
	 * @param vo
	 */		
	public void update(CilCodigoAccesoVO vo) throws PersistenceException;
	
	
	/**
	 * Method 'actualizaEstatus'
	 * Actualiza el estatus al CilCodigoAcceso correspondiente al valor proporcionado.
	 * 
	 * @param idCilCodigoAcceso
	 * @param estatus
	 */		
	public void actualizaEstatus(long idCilCodigoAcceso, int estatus) throws PersistenceException;
	
	
}
