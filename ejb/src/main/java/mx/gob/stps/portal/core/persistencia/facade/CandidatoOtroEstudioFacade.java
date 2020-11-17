package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.candidate.vo.CandidatoOtroEstudioVO;
import mx.gob.stps.portal.persistencia.entity.CandidatoOtroEstudio;

@Stateless
public class CandidatoOtroEstudioFacade implements CandidatoOtroEstudioFacadeLocal {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Registra otro estudio del candidato
	 * @param vo CandidatoOtroEstudioVO
	 * @return Identificador de otro estudio del candidato
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	@Override
	public long create(CandidatoOtroEstudioVO otroEstudio) throws PersistenceException {
		CandidatoOtroEstudio entity = getEntity(otroEstudio);
		entityManager.persist(entity);
		return entity.getIdCandidatoOtroEstudio();
	}

	@Override
	public int update(CandidatoOtroEstudioVO otroEstudio) throws PersistenceException {
		int result = 1;
		try {
			if (otroEstudio != null && otroEstudio.getIdCandidatoOtroEstudio() > 0) {
				CandidatoOtroEstudio entity = entityManager.find(CandidatoOtroEstudio.class, otroEstudio.getIdCandidatoOtroEstudio());
				entity.setFechaFin(otroEstudio.getFechaFin());
				entity.setFechaInicio(otroEstudio.getFechaInicio());
				entity.setIdEstatusAcademico(otroEstudio.getIdEstatusAcademico());
				//entity.setIdOtroEstudio(otroEstudio.getIdOtroEstudio());
				entity.setIdTipoEstudio(otroEstudio.getIdTipoEstudio());
				entity.setInstitucion(otroEstudio.getInstitucion());
				entity.setNombreEstudio(otroEstudio.getNombreEstudio());
				//entity.setPrincipal(Utils.toLong(otroEstudio.getPrincipal()));
				entityManager.merge(entity);
			}else result = -1;
		}catch (Exception e) { result = -1; }
		return result;
	}

	@Override
	public int delete(long idCandidatoOtroEstudio) throws PersistenceException {
		int result = 1;
		try {
			CandidatoOtroEstudio entity = entityManager.find(CandidatoOtroEstudio.class, idCandidatoOtroEstudio);
			entityManager.remove(entity);
		}catch (Exception e) { result = -1; }
		return result;
	}

	@Override
	public List<CandidatoOtroEstudioVO> getCandidatoOtroEstudiosList(long idCandidato) throws PersistenceException {
		List<CandidatoOtroEstudioVO> list = new ArrayList<CandidatoOtroEstudioVO>();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("SELECT c FROM CandidatoOtroEstudio c WHERE c.idCandidato=:idCandidato");
		Query query = entityManager.createQuery(sbQuery.toString());
		query.setParameter("idCandidato", idCandidato);
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for(Object resultElement : result){
			CandidatoOtroEstudioVO vo = getCandidatoOtroEstudioVO((CandidatoOtroEstudio)resultElement);			
			list.add(vo);
		}	
		return list;
	}
	
	private CandidatoOtroEstudio getEntity(CandidatoOtroEstudioVO otroEstudio) {
		CandidatoOtroEstudio entity = new CandidatoOtroEstudio();
		if (otroEstudio.getIdCandidato() > 0) {
			entity.setFechaFin(otroEstudio.getFechaFin());
			entity.setFechaInicio(otroEstudio.getFechaInicio());
			entity.setIdCandidato(otroEstudio.getIdCandidato());
			entity.setIdEstatusAcademico(otroEstudio.getIdEstatusAcademico());
			entity.setIdOtroEstudio(otroEstudio.getIdOtroEstudio());
			entity.setIdTipoEstudio(otroEstudio.getIdTipoEstudio());
			entity.setInstitucion(otroEstudio.getInstitucion());
			entity.setNombreEstudio(otroEstudio.getNombreEstudio());
			entity.setPrincipal(Utils.toLong(otroEstudio.getPrincipal()));
		}
		return entity;
	}
	
	private CandidatoOtroEstudioVO getCandidatoOtroEstudioVO(CandidatoOtroEstudio entity) {
		CandidatoOtroEstudioVO vo = new CandidatoOtroEstudioVO();
		vo.setFechaFin(entity.getFechaFin());
		vo.setFechaInicio(entity.getFechaInicio());
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setIdCandidatoOtroEstudio(entity.getIdCandidatoOtroEstudio());
		vo.setIdEstatusAcademico(entity.getIdEstatusAcademico());
		vo.setIdOtroEstudio(entity.getIdOtroEstudio());
		vo.setIdTipoEstudio(entity.getIdTipoEstudio());
		vo.setInstitucion(entity.getInstitucion());
		vo.setNombreEstudio(entity.getNombreEstudio());
		vo.setPrincipal(Utils.toInt(entity.getPrincipal()));
		return vo;
	}
}