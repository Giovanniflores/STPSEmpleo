package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.cil.vo.SeguimientoColocacionVO;
import mx.gob.stps.portal.core.persistencia.entity.SeguimientoColocacion;

@Stateless
public class SeguimientoColocacionFacade implements SeguimientoColocacionFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void create(SeguimientoColocacionVO seguimientoColocacionVO) throws PersistenceException {
		try {
			SeguimientoColocacion entity = getEntity(seguimientoColocacionVO);
			entityManager.persist(entity);
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}
	
	@Override
	public boolean exist(long idCil, long idCandidato, long idAtencion) throws PersistenceException {
		boolean exist = false;
		String existTraceUserEmployment = "SELECT c FROM SeguimientoColocacion c WHERE	c.idCil = :idCil AND c.idCandidato = :idCandidato AND c.idAtencion = :idAtencion";
		SeguimientoColocacion entity = null;
		Query query = entityManager.createQuery(existTraceUserEmployment);
		query.setParameter("idCil", idCil);
		query.setParameter("idCandidato", idCandidato);
		query.setParameter("idAtencion", idAtencion);
		try {
			entity = (SeguimientoColocacion)query.getSingleResult();
		}catch (javax.persistence.NoResultException noresult) { }
		if (null != entity && entity.getIdAtencion() > 0)
			exist = true;
		return exist;
	}
	
	private SeguimientoColocacion getEntity(SeguimientoColocacionVO vo) {
		SeguimientoColocacion entity = new SeguimientoColocacion();
		entity.setEstatus(vo.getEstatus());
		entity.setFechaColocacion(vo.getFechaColocacion());
		entity.setFechaSeguimiento(vo.getFechaSeguimiento());
		entity.setHoraSeguimiento(vo.getHoraSeguimiento());
		entity.setIdCandidato(vo.getIdCandidato());
		entity.setIdCausa(vo.getIdCausa());
		entity.setIdCil(vo.getIdCil());
		entity.setIdSeguimientoColocacion(vo.getIdSeguimientoColocacion());
		entity.setIdTiposeguimiento(vo.getIdTiposeguimiento());
		entity.setIdUsuario(vo.getIdUsuario());
		entity.setOtraCausa(vo.getOtraCausa());
		entity.setIdAtencion(vo.getIdAtencion());
		return entity;
	}
}
