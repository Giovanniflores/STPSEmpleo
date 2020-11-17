package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.utils.Constantes.DOMINIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.persistencia.entity.CandidatoConocimHabilidad;

@Stateless
public class CandidatoConocimHabilidadFacade implements CandidatoConocimHabilidadFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public long update(ConocimientoHabilidadVO conocimientoHabilidadVO) throws PersistenceException {
		CandidatoConocimHabilidad entity = null;
		if (null == conocimientoHabilidadVO) return -1;
		try {
			if (conocimientoHabilidadVO.getIdCandidatoConocimHabilidad() > 0) {
				entity = entityManager.find(CandidatoConocimHabilidad.class, conocimientoHabilidadVO.getIdCandidatoConocimHabilidad());
				if (null != entity) entityManager.merge(getEntity(entity, conocimientoHabilidadVO));
			}else {
				entity = getEntity(new CandidatoConocimHabilidad(), conocimientoHabilidadVO);
				entityManager.persist(entity);
				entityManager.flush();
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}
		return entity.getIdCandidatoConocimHabilidad();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConocimientoHabilidadVO> candidatoConocimHabilidadList(long idCandidato) throws PersistenceException {
		List<ConocimientoHabilidadVO> list = new ArrayList<ConocimientoHabilidadVO>();
		StringBuilder findConocimientosByCandidato = new StringBuilder();
		findConocimientosByCandidato.append("SELECT c FROM CandidatoConocimHabilidad c WHERE c.idCandidato = :idCandidato ");
		Query query = entityManager.createQuery(findConocimientosByCandidato.toString());
		query.setParameter("idCandidato", idCandidato);
		List<Object> result = query.getResultList();
		for(Object resultElement : result) {
			ConocimientoHabilidadVO vo = getConocimientoHabilidadVO((CandidatoConocimHabilidad)resultElement);
			list.add(vo);
		}
		return list;
	}

	private ConocimientoHabilidadVO getConocimientoHabilidadVO(CandidatoConocimHabilidad entity) {
		ConocimientoHabilidadVO vo = new ConocimientoHabilidadVO();
		vo.setConocimientoHabilidad(entity.getConocimientoHabilidad());
		vo.setDescripcion(entity.getDescripcion());
		vo.setIdDominio(entity.getIdDominio());
		vo.setIdCandidatoConocimHabilidad(entity.getIdCandidatoConocimHabilidad());
		vo.setIdExperiencia(entity.getIdExperiencia());
		vo.setIdTipoConocimHabilidad(entity.getIdTipoConocimHabilidad());
		vo.setPrincipal(Utils.toInt(entity.getPrincipal()));
		return vo;
	}
	
	private CandidatoConocimHabilidad getEntity(CandidatoConocimHabilidad entity, ConocimientoHabilidadVO vo) {
		entity.setDescripcion(vo.getDescripcion());
		entity.setPrincipal((long)vo.getPrincipal());
		entity.setIdExperiencia(vo.getIdExperiencia());
		entity.setConocimientoHabilidad(vo.getConocimientoHabilidad());
		entity.setIdTipoConocimHabilidad(vo.getIdTipoConocimHabilidad());
		if (entity.getIdCandidatoConocimHabilidad() < 1) {
			entity.setFechaAlta(new Date());
			entity.setIdCandidato(vo.getIdCandidato());
			if (vo.getIdExperiencia() == EXPERIENCIA.NINGUNA.getIdOpcion())
				entity.setIdDominio(DOMINIO.NINGUNO.getIdOpcion());
			else if (vo.getIdExperiencia() == EXPERIENCIA.MAS_CINCO.getIdOpcion())
				entity.setIdDominio(DOMINIO.AVANZADO.getIdOpcion());
			else entity.setIdDominio(DOMINIO.INTERMEDIO.getIdOpcion());
		}
		return entity;
	}
}