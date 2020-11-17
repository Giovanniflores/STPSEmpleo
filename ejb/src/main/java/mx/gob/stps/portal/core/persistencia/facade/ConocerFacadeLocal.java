package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.ConocerConfigVO;
import mx.gob.stps.portal.persistencia.entity.ConocerConfig;

@Local
public interface ConocerFacadeLocal {

	public ConocerConfigVO save(ConocerConfigVO vo) throws PersistenceException;
	
	public ConocerConfigVO consultaConocerConfigByIdCandidato(Long idCandidato) throws PersistenceException;
}
