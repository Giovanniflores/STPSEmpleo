package mx.gob.stps.portal.core.persistencia.facade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.persistencia.entity.OfertaEmpleoBecate;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoBecateVO;

import org.apache.log4j.Logger;

@Stateless
public class OfertaEmpleoBecateFacade implements OfertaEmpleoBecateFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;

	private static Logger logger = Logger.getLogger(OfertaEmpleoBecateFacade.class);

	public long registrarOferta(OfertaEmpleoBecateVO ofertaEmpleo) throws PersistenceException {
		OfertaEmpleoBecate entity= getEntity(ofertaEmpleo);
		entityManager.persist(entity);
		
		return entity.getIdOfertaEmpleo();
	}

	@Override
	public int update(OfertaEmpleoBecateVO ofertaEmpleo) throws PersistenceException {
		int result = 1;
		try{
			if(ofertaEmpleo != null && ofertaEmpleo.getIdOfertaEmpleo() > 0){
				OfertaEmpleoBecate entity = entityManager.find(OfertaEmpleoBecate.class, ofertaEmpleo.getIdOfertaEmpleo());
				entity.setClaveOferta(ofertaEmpleo.getClaveOferta());
				entity.setIdCurso(ofertaEmpleo.getIdCurso());
				entity.setIdTipoRecurso(ofertaEmpleo.getIdTipoRecurso());
				entity.setNumeroPlazas(ofertaEmpleo.getNumeroPlazas());
				entity.setIdHorarioImparticion(ofertaEmpleo.getIdHorarioImparticion());
				entity.setIdHorarioEntrada(ofertaEmpleo.getIdHorarioEntrada());
				entity.setIdHorarioSalida(ofertaEmpleo.getIdHorarioSalida());
				entity.setFechaInicio(ofertaEmpleo.getFechaInicio());
				entity.setFechaFin(ofertaEmpleo.getFechaFin());
				entity.setIdDuracion(ofertaEmpleo.getIdDuracion());
				entity.setIdSalario(ofertaEmpleo.getIdSalario());
				entity.setMonto(ofertaEmpleo.getMonto());
				entityManager.merge(entity);

			}else{
				result = -1;
			}
		}catch (Exception e) {
			result = -1; }
		
		return result;
	}

	@Override
	public int delete(long idOfertaEmpleo) throws PersistenceException {
		int result = 1;
		try {
			OfertaEmpleoBecate entity = entityManager.find(OfertaEmpleoBecate.class, idOfertaEmpleo);
			entityManager.remove(entity);
		}catch (Exception e) {
			result = -1; }
		
		return result;
	}

	@Override
	public List<OfertaEmpleoBecateVO> getOfertaEmpleoBecate(){
		List<OfertaEmpleoBecateVO> list = new ArrayList<OfertaEmpleoBecateVO>();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("SELECT c FROM OfertaEmpleoBecate c");
		sbQuery.append(" ORDER BY c.idOfertaEmpleo ASC");
		Query query = entityManager.createQuery(sbQuery.toString());
		
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for(Object resultElement : result){
			OfertaEmpleoBecateVO vo = getOfertaEmpleoBecateVO((OfertaEmpleoBecate)resultElement);			
			list.add(vo);
		}	
		return list;
	}
	
	private OfertaEmpleoBecate getEntity(OfertaEmpleoBecateVO ofertaEmpleo) {
		OfertaEmpleoBecate entity = new OfertaEmpleoBecate();
		if (ofertaEmpleo.getIdOfertaEmpleo() > 0) {
			entity.setIdOfertaEmpleo(ofertaEmpleo.getIdOfertaEmpleo());
			entity.setClaveOferta(ofertaEmpleo.getClaveOferta());
			entity.setIdCurso(ofertaEmpleo.getIdCurso());
			entity.setIdTipoRecurso(ofertaEmpleo.getIdTipoRecurso());
			entity.setNumeroPlazas(ofertaEmpleo.getNumeroPlazas());
			entity.setIdHorarioImparticion(ofertaEmpleo.getIdHorarioImparticion());
			entity.setIdHorarioEntrada(ofertaEmpleo.getIdHorarioEntrada());
			entity.setIdHorarioSalida(ofertaEmpleo.getIdHorarioSalida());
			entity.setFechaInicio(ofertaEmpleo.getFechaInicio());
			entity.setFechaFin(ofertaEmpleo.getFechaFin());
			entity.setIdDuracion(ofertaEmpleo.getIdDuracion());
			entity.setIdSalario(ofertaEmpleo.getIdSalario());
			entity.setMonto(ofertaEmpleo.getMonto());			
		}
		return entity;
	}
	
	private OfertaEmpleoBecateVO getOfertaEmpleoBecateVO(OfertaEmpleoBecate entity) {
		OfertaEmpleoBecateVO vo = new OfertaEmpleoBecateVO();
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		vo.setClaveOferta(entity.getClaveOferta());
		vo.setIdCurso(entity.getIdCurso());
		vo.setIdTipoRecurso(entity.getIdTipoRecurso());
		vo.setNumeroPlazas(entity.getNumeroPlazas());
		vo.setIdHorarioImparticion(entity.getIdHorarioImparticion());
		vo.setIdHorarioEntrada(entity.getIdHorarioEntrada());
		vo.setIdHorarioSalida(entity.getIdHorarioSalida());
		vo.setFechaInicio(entity.getFechaInicio());
		vo.setFechaFin(entity.getFechaFin());
		vo.setIdDuracion(entity.getIdDuracion());
		vo.setIdSalario(entity.getIdSalario());
		vo.setMonto(entity.getMonto());
		return vo;
	}

	@Override
	public List<OfertaEmpleoBecateVO> findById(long idOfertaEmpleo) throws PersistenceException {
		List<OfertaEmpleoBecateVO> list = new ArrayList<OfertaEmpleoBecateVO>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c FROM OfertaEmpleoBecate c WHERE c.idOfertaEmpleo=:idOfertaEmpleo");
		Query query = entityManager.createQuery(sb.toString());
		query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for (Object resultElement : result) {
			OfertaEmpleoBecateVO vo = getOfertaEmpleoBecateVO((OfertaEmpleoBecate) resultElement);
			list.add(vo);
		}
		return list;
	}

	@Override
	public OfertaEmpleoBecateVO obtenerOfertaById(long idOferta) throws PersistenceException {
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		OfertaEmpleoBecateVO vo = new OfertaEmpleoBecateVO();
		OfertaEmpleoBecate entity = findByIdOferta(idOferta);
		
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		vo.setClaveOferta(entity.getClaveOferta());		
		vo.setIdCurso(entity.getIdCurso());
		vo.setIdTipoRecurso(entity.getIdTipoRecurso());
		vo.setNumeroPlazas(entity.getNumeroPlazas());
		vo.setIdHorarioImparticion(entity.getIdHorarioImparticion());
		vo.setIdHorarioEntrada(entity.getIdHorarioEntrada());
		vo.setIdHorarioSalida(entity.getIdHorarioSalida());
		vo.setFechaInicioString(formatter.format(entity.getFechaInicio()));
		vo.setFechaFinString(formatter.format(entity.getFechaFin()));
		vo.setFechaInicio(entity.getFechaInicio());
		vo.setFechaFin(entity.getFechaFin());		
		vo.setIdDuracion(entity.getIdDuracion());
		vo.setIdSalario(entity.getIdSalario());
		vo.setMonto(entity.getMonto());
		
		return vo;
	}
	
	private OfertaEmpleoBecate findByIdOferta(long idOferta) {
		OfertaEmpleoBecate instance = null;

		instance = entityManager.find(OfertaEmpleoBecate.class, idOferta);

		return instance;
	}

	@Override
	public Boolean esOfertaBecate(long idOfertaEmpleo) {
		Boolean esOfertaBecate = false;
		try {
			OfertaEmpleoBecate entity = entityManager.find(
					OfertaEmpleoBecate.class, idOfertaEmpleo);

			if (entity != null)
				esOfertaBecate = true;

		} catch (NoResultException re) {
			logger.error("No se localizo el registro de la oferta de empleo : "
					+ idOfertaEmpleo);
		} catch (Exception re) {
			throw new PersistenceException(re);
		}

		return esOfertaBecate;
	}
}
