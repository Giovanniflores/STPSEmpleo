package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;
import mx.gob.stps.portal.persistencia.entity.OfertaPregunta;

import org.apache.log4j.Logger;

@Stateless
public class OfertaPreguntaFacade implements OfertaPreguntaFacadeLocal {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger = Logger.getLogger(OfertaPreguntaFacade.class);

	@Override
	public List<OfertaPreguntaVO> ofertaPreguntasList(long idOfertaEmpleo) throws PersistenceException {
		List<OfertaPreguntaVO> list = new ArrayList<OfertaPreguntaVO>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c FROM OfertaPregunta c WHERE c.idOfertaEmpleo=:idOfertaEmpleo");
		try{
			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			for(Object resultElement : result){
				OfertaPreguntaVO vo = getOfertaPreguntaVO((OfertaPregunta)resultElement);			
				list.add(vo);
			}			
		}catch(NoResultException e){
			logger.error("No se localizaron preguntas de la oferta");
		}catch(Exception e){
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException(e);
		}
		
		return list;
	}

	@Override
	public int create(OfertaPreguntaVO vo) throws PersistenceException {
		int result = -1;
		try {
			OfertaPregunta entity = getEntity(vo);
			if (null != entity) {
				entityManager.persist(entity);
				result = 1;
			}
		}catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
		return result;
	}

	@Override
	public OfertaPreguntaVO findById(long idOfertaPregunta) throws PersistenceException {
		OfertaPreguntaVO vo = null;
		try {
			OfertaPregunta entity = entityManager.find(OfertaPregunta.class, idOfertaPregunta);
			if (null != entity)
				vo = getOfertaPreguntaVO(entity);
		}catch (RuntimeException re) {
			logger.error(re.toString());
		}
		return vo;
	}

	@Override
	public void update(OfertaPreguntaVO vo) throws PersistenceException {
		try {
			OfertaPregunta entity = getEntity(vo);
			if (null != entity)
				entityManager.merge(entity);
		}catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}

	@Override
	public void remove(long idOfertaPregunta) throws PersistenceException {
		try {
			OfertaPregunta entity = entityManager.find(OfertaPregunta.class, idOfertaPregunta);
			if (null != entity)
				entityManager.remove(entity);
		}catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
	}
	
	private OfertaPregunta getEntity(OfertaPreguntaVO vo) {
		OfertaPregunta entity = new OfertaPregunta();
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaRespuesta(vo.getFechaRespuesta());
		entity.setIdCandidato(vo.getIdCandidato());
		entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
		if (vo.getIdOfertaPregunta() > 0)
			entity.setIdOfertaPregunta(vo.getIdOfertaPregunta());
		entity.setPregunta(vo.getPregunta());
		entity.setRespuesta(vo.getRespuesta());
		return entity;
	}
	
	private OfertaPreguntaVO getOfertaPreguntaVO(OfertaPregunta entity) {
		OfertaPreguntaVO vo = new OfertaPreguntaVO();
		vo.setIdOfertaPregunta(entity.getIdOfertaPregunta());
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setFechaRespuesta(entity.getFechaRespuesta());
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		vo.setPregunta(entity.getPregunta());
		vo.setRespuesta(entity.getRespuesta());
		return vo;
	}
}