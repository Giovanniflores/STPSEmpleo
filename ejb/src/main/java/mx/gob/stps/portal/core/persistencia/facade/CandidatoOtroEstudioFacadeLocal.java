package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.CandidatoOtroEstudioVO;

@Local
public interface CandidatoOtroEstudioFacadeLocal {
	
	public long create(CandidatoOtroEstudioVO otroEstudio) throws PersistenceException;
	
	public int update(CandidatoOtroEstudioVO otroEstudio) throws PersistenceException;
	
	public int delete(long idCandidatoOtroEstudio) throws PersistenceException;
	
	public List<CandidatoOtroEstudioVO> getCandidatoOtroEstudiosList(long idCandidato) throws PersistenceException;
}
