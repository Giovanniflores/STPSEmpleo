package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.HistEmpresaPorAutorizarVO;

/**
 * Proporciona acceso a los metodos de persistencia para registros de la entidad "HistEmpresaPorAutorizar"
 * 
 * @author haydee.vertti
 *
 */
@Local
public interface HistEmpresaPorAutorizarFacadeLocal {

	/**
	 * Respalda una empresa por autorizar en el historico
	 * @param vo HistEmpresaPorAutorizarVO
	 * @return Identificador de la empresa por autorizar en el historico
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */	
	public long save(HistEmpresaPorAutorizarVO vo) throws PersistenceException;
	
	/**
	 * Method 'findById'
	 * Regresa un objeto HistEmpresaPorAutorizarVO con los datos correspondientes a
	 * la empresa por  autorizar en el historico,cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return EmpresaPorAutorizarVO
	 */	
	public HistEmpresaPorAutorizarVO findById(long id) throws PersistenceException;
	
	
}
