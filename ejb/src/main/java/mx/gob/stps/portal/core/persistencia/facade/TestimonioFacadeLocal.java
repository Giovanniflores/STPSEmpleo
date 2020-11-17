/**
 * 
 */
package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;

/**
 * @author concepcion.aguilar
 *
 */
@Local
public interface TestimonioFacadeLocal {
	/**
	 * Guarda un registro en la tabla testimonio
	 * @param vo objeto que trae los datos necesario para guardar la entidad testimonio
	 * @return trae el numero generado por la secuencia de la tabla testimonio
	 * @throws PersistenceException
	 */
	public long save(TestimonioVO vo) throws PersistenceException;

	public TestimonioVO findById(long idTestimonio) throws PersistenceException;
	
	public int actualizaEstatus(long idTestimonio, int estatus) throws PersistenceException;
}
