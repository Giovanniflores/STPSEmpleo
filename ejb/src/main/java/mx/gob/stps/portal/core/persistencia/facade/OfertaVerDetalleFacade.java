package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaVerDetalleVO;
import mx.gob.stps.portal.persistencia.entity.OfertaVerDetalle;

import org.apache.log4j.Logger;

@Stateless
public class OfertaVerDetalleFacade implements OfertaVerDetalleFacadeLocal {

	private static Logger logger = Logger.getLogger(OfertaVerDetalleFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public int create(OfertaVerDetalleVO vo) throws PersistenceException {
		int result = -1;
		try {
			OfertaVerDetalle entity = getEntity(vo);
			entityManager.persist(entity);
			result = 1;
		}catch (RuntimeException re) {
			logger.error(re.toString());
		}
		return result;
	}

	public OfertaVerDetalleVO findByPK(long idOfertaEmpleo, int anio, int mes) throws PersistenceException {
		OfertaVerDetalleVO vo = null;
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM OfertaVerDetalle c WHERE c.idOfertaEmpleo=:idOfertaEmpleo AND c.anio=:anio AND c.mes=:mes");
			TypedQuery<OfertaVerDetalle> query = entityManager.createQuery(sb.toString(), OfertaVerDetalle.class);
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			query.setParameter("anio", anio);
			query.setParameter("mes", mes);
			
			OfertaVerDetalle entity = null;
			List<OfertaVerDetalle> entities = query.getResultList();
			if (entities!=null && !entities.isEmpty()){
				entity = entities.get(0);
			}
						
			if (null != entity)
				vo = getOfertaVerDetalleVO(entity);
			
		} catch(NoResultException e){
			// No trae registros
		} catch (RuntimeException re) {
			re.printStackTrace();
			logger.error(re.toString());
		}
		return vo;
	}
	
	public void update(OfertaVerDetalleVO vo) throws PersistenceException {
		try {
			OfertaVerDetalle entity = getEntity(vo);
			entityManager.merge(entity);
		}catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}
	
	public void remove(long idOfertaVerDetalle) throws PersistenceException {
		try {
			OfertaVerDetalle entity = entityManager.find(OfertaVerDetalle.class, idOfertaVerDetalle);
			entityManager.remove(entity);
		}catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}
	
	private OfertaVerDetalleVO getOfertaVerDetalleVO(OfertaVerDetalle entity) {
		OfertaVerDetalleVO vo = new OfertaVerDetalleVO();
		vo.setIdOfertaVerDetalle(entity.getIdOfertaVerDetalle());
		vo.setAnio(Utils.toInt(entity.getAnio()));
		vo.setContador(Utils.toInt(entity.getContador()));
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		vo.setMes(Utils.toInt(entity.getMes()));
		return vo;
	}

	private OfertaVerDetalle getEntity(OfertaVerDetalleVO vo) {
		OfertaVerDetalle detail = new OfertaVerDetalle();
		detail.setIdOfertaVerDetalle(vo.getIdOfertaVerDetalle());
		detail.setAnio(Utils.toLong(vo.getAnio()));
		detail.setContador(Utils.toLong(vo.getContador()));
		detail.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
		detail.setMes(Utils.toLong(vo.getMes()));
		return detail;
	}
}
