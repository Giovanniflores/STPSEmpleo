package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;

@Local
public interface CandidatoIdiomaFacadeLocal {

	public List<IdiomaVO> candidatoIdiomasList(long idCandidato) throws PersistenceException;
	
	public long persist(IdiomaVO idiomaVO) throws PersistenceException;
}