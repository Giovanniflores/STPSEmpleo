package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.CandidatoGradoAcademico;

@Stateless
public class CandidatoGradoAcademicoFacade implements CandidatoGradoAcademicoFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<GradoAcademicoVO> candidatoGradosAcademicosList(long idCandidato) throws PersistenceException {
		List<GradoAcademicoVO> list = new ArrayList<GradoAcademicoVO>();

		Query query = entityManager.createQuery("SELECT ga FROM CandidatoGradoAcademico ga WHERE ga.idCandidato = :idCandidato");
		query.setParameter("idCandidato", idCandidato);
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for(Object resultElement : result){
			GradoAcademicoVO vo = getGradoAcademicoVO((CandidatoGradoAcademico)resultElement);
			list.add(vo);
		}
		return list;
	}

	private GradoAcademicoVO getGradoAcademicoVO(CandidatoGradoAcademico entity) {
		GradoAcademicoVO vo = new GradoAcademicoVO();
		vo.setEscuela(entity.getEscuela());
		vo.setFin(vo.getFin());
		vo.setIdCandidatoGradoAcademico(entity.getIdCandidato());
		vo.setIdCarreraEspecialidad(entity.getIdCarreraEspecialidad());
		vo.setIdNivelEstudio(entity.getIdNivelEstudio());
		vo.setIdSituacionAcademica(entity.getIdSituacionAcademica());
		vo.setInicio(Utils.toInt(entity.getInicio()));
		vo.setPrincipal(Utils.toInt(entity.getPrincipal()));
		return vo;
	}
}
