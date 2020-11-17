package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.cil.vo.SeguimientoColocacionVO;

@Local
public interface SeguimientoColocacionFacadeLocal {
	
	/** 
	 * Persistiendo seguimiento colocacion para los cil
	 * @param SeguimientoColocaionVO
	 * @return
	 * @throws PersistenceException
	 */
	public void create(SeguimientoColocacionVO seguimientoColocacionVO) throws PersistenceException;
	
	/** 
	 * Buscar seguimiento colocacion en los cil
	 * @param SeguimientoColocaionVO
	 * @return
	 * @throws PersistenceException
	 */
	public boolean exist(long idCil, long idCandidato, long idAtencion) throws PersistenceException;
}