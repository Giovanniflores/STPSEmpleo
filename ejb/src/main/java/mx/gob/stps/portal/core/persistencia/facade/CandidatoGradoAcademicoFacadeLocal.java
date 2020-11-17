package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;

@Local
public interface CandidatoGradoAcademicoFacadeLocal {
	
	public List<GradoAcademicoVO> candidatoGradosAcademicosList(long idCandidato) throws PersistenceException;;
}
