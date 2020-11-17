package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.persistencia.entity.OfertaPostulacion;

import org.apache.log4j.Logger;

@Stateless
public class OfertaPostulacionFacade implements OfertaPostulacionFacadeLocal {
	
	private static Logger logger = Logger.getLogger(OfertaPostulacionFacade.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public int create(OfertaPostulacionVO vo) throws PersistenceException {
		int result = -1;
		try {
			OfertaPostulacion entity = getEntity(vo);
			entityManager.persist(entity);
			result = 1;
		}catch (Exception re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
		return result;
	}

	@Override
	public OfertaPostulacionVO findByPK(long idOfertaEmpleo, int anio, int mes) throws PersistenceException {
		OfertaPostulacionVO vo = null;
		String offersByPostCount = "SELECT c FROM OfertaPostulacion c WHERE c.idOfertaEmpleo=:idOfertaEmpleo AND c.anio=:anio AND c.mes=:mes";
		try {
			Query query = entityManager.createQuery(offersByPostCount);
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			query.setParameter("anio", anio);
			query.setParameter("mes", mes);
			OfertaPostulacion entity = (OfertaPostulacion)query.getSingleResult();
			if (null != entity)
				vo = getOfertaPostulacionVO(entity);
		}catch (NoResultException re) {
			logger.error("Oferta Postulacion no localizado, idOfertaEmpleo : "+ idOfertaEmpleo);
		}catch (RuntimeException re) {
			logger.error(re.toString());
		}
		return vo;
	}

	@Override
	public void update(OfertaPostulacionVO vo) throws PersistenceException {
		try {
			OfertaPostulacion entity = getEntity(vo);
			entityManager.merge(entity);
		}catch (Exception re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}

	@Override
	public void remove(long idOfertaPostulacion) throws PersistenceException {
		try {
			OfertaPostulacion entity = entityManager.find(OfertaPostulacion.class, idOfertaPostulacion);
			entityManager.remove(entity);
		}catch (NoResultException re) {
			logger.error("Oferta Postulacion no localizado, idOfertaPostulacion : "+ idOfertaPostulacion);
		}catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}

	private OfertaPostulacionVO getOfertaPostulacionVO(OfertaPostulacion entity) {
		OfertaPostulacionVO vo = new OfertaPostulacionVO();
		vo.setIdOfertaPostulacion(entity.getIdOfertaPostulacion());
		vo.setAnio(Utils.toInt(entity.getAnio()));
		vo.setContador(Utils.toInt(entity.getContador()));
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		vo.setMes(Utils.toInt(entity.getMes()));
		return vo;
	}
	
	private OfertaPostulacion getEntity(OfertaPostulacionVO vo) {
		OfertaPostulacion detail = new OfertaPostulacion();
		detail.setIdOfertaPostulacion(vo.getIdOfertaPostulacion());
		detail.setAnio(Utils.toLong(vo.getAnio()));
		detail.setContador(Utils.toLong(vo.getContador()));
		detail.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
		detail.setMes(Utils.toLong(vo.getMes()));
		return detail;
	}
}