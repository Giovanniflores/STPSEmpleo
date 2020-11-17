package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;
import mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO;

@Local
public interface CandidatoConocimHabilidadFacadeLocal {

	public long update(ConocimientoHabilidadVO conocimientoHabilidadVO) throws PersistenceException;
	
	public List<ConocimientoHabilidadVO> candidatoConocimHabilidadList(long idCandidato) throws PersistenceException;
}