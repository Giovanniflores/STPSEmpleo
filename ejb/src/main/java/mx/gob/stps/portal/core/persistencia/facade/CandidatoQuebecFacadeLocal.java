package mx.gob.stps.portal.core.persistencia.facade;


import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.persistencia.vo.CandidatoQuebecVO;

@Local
public interface CandidatoQuebecFacadeLocal {

	public int persistCandidatoQuebec(long idCandidato) throws PersistenceException;
	
	public CandidatoQuebecVO findByID(long idCandidato) throws PersistenceException;
}
