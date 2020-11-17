package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.persistencia.entity.Entrevista;

import org.apache.log4j.Logger;


@Stateless
public class EntrevistaFacade implements EntrevistaFacadeLocal {
	private static Logger logger = Logger.getLogger(EntrevistaFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public long save(EntrevistaVO vo) throws PersistenceException {
		try {

			Entrevista entity = getEntity(vo);
			//entity.setIdPortalEmpleo(generaIDPortalEmpleo(vo));
			entityManager.persist(entity);
			return entity.getIdEntrevista();
		} catch (RuntimeException re) {
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}		
	}
	
	public List<EntrevistaVO> getOpcionesCatalogo(long idEntrevista) throws PersistenceException {
		List<EntrevistaVO> lstOpciones = new ArrayList<EntrevistaVO>();
		
		Query query = entityManager.createQuery("SELECT c FROM CatalogoOpcion c WHERE c.idCatalogo=:idCat AND c.estatus=:catEstatus ORDER BY c.opcion");
		query.setParameter("idEntrevista", idEntrevista);
		query.setParameter("estatus", ESTATUS.ACTIVO.getIdOpcion());
		
		@SuppressWarnings("unchecked")
		List<Object> result1 = query.getResultList();
		for(Object resultElement : result1){
			EntrevistaVO vo = getEntrevistaVO((Entrevista)resultElement);						
			lstOpciones.add(vo);
		}
		return lstOpciones;
	}	
	
	
	private EntrevistaVO getEntrevistaVO(Entrevista entrevista){
		EntrevistaVO vo = new EntrevistaVO();
		vo.setIdEntrevista(entrevista.getIdEntrevista());
		vo.setIdOfertaEmpleo(entrevista.getIdOfertaEmpleo());
		vo.setIdCandidato(entrevista.getIdCandidato());
		vo.setFecha(entrevista.getFecha());
		vo.setHora(entrevista.getHora());
		vo.setFechaAlta(entrevista.getFechaAlta());
		vo.setFechaModificacion(entrevista.getFechaModificacion());		
		vo.setEstatus(entrevista.getEstatus());
		return vo;
	}	
	
	private Entrevista getEntity(EntrevistaVO vo){
		Entrevista entity = new Entrevista();
		entity.setIdEntrevista(vo.getIdEntrevista());
		entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
		entity.setIdCandidato(vo.getIdCandidato());
		entity.setFecha(vo.getFecha());
		entity.setHora(vo.getHora());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaModificacion(vo.getFechaModificacion());
		entity.setEstatus(vo.getEstatus());
		return entity;
	}

	@Override
	public void update(EntrevistaVO entrevistaVo) throws PersistenceException {
		Entrevista findEntrevista  = findEntrevista(entrevistaVo.getIdEntrevista());
		
		findEntrevista.setEstatus(entrevistaVo.getEstatus());
		
		findEntrevista.setFechaModificacion(entrevistaVo.getFechaModificacion());
			
		if("Reprogramación".equals(entrevistaVo.getTipo())){
			findEntrevista.setFecha(entrevistaVo.getFecha());
			findEntrevista.setHora(entrevistaVo.getHora());
		}
				
		entityManager.merge(findEntrevista);		
	}

	public void reprograma(EntrevistaVO entrevistaVo) throws PersistenceException {
		try{
			Entrevista entrevista = entityManager.find(Entrevista.class, entrevistaVo.getIdEntrevista());
			
			entrevista.setEstatus(entrevistaVo.getEstatus());
			entrevista.setFechaModificacion(entrevistaVo.getFechaModificacion());
			entrevista.setFecha(entrevistaVo.getFecha());
			entrevista.setHora(entrevistaVo.getHora());
			entityManager.merge(entrevista);
		} catch (NoResultException re) {
			logger.error("Entrevista no localizada.");
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException(e);
		}
	}

	private Entrevista findEntrevista(long idEntrevista) throws PersistenceException {	
		Entrevista entity = null;
		try{
			entity = entityManager.find(Entrevista.class, idEntrevista);
		}catch(Exception e){
			logger.error(e);
			throw new PersistenceException(e);
		}		
		return entity;
	}
	
	
	public void cancelaEntrevistas(long idOfertaEmpleo, ESTATUS cancelada, Date fechaModificacion, 
                                   ESTATUS nueva, ESTATUS aceptada, ESTATUS reprogramada) throws PersistenceException {

		if (idOfertaEmpleo<=0) throw new IllegalArgumentException("Identificador requerido");

		try{
		    String update = "UPDATE Entrevista AS e "+
			                   "SET e.estatus = :estatus, e.fechaModificacion = :fechaModif "+
			                 "WHERE e.idOfertaEmpleo = :idOfertaEmpleo "+
			                   "AND (e.estatus = :nueva OR e.estatus = :aceptada OR e.estatus = :reprogramada)";

			Query query = entityManager.createQuery(update);

			query.setParameter("estatus", cancelada.getIdOpcion());
			query.setParameter("fechaModif", fechaModificacion, TemporalType.TIMESTAMP);
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			query.setParameter("nueva", nueva.getIdOpcion());
			query.setParameter("aceptada", aceptada.getIdOpcion());
			query.setParameter("reprogramada", reprogramada.getIdOpcion());

			query.executeUpdate();

		} catch (RuntimeException re) {
			logger.error(re);
			throw new PersistenceException(re);
		}
	}

	public void actualizaEstatus(long idEntrevista, ESTATUS estatus, Date fechaModificacion) throws PersistenceException {

		if (idEntrevista<=0) throw new IllegalArgumentException("Identificador requerido");
		if (fechaModificacion==null) throw new IllegalArgumentException("Fecha requerida");

		try{
			Entrevista entity = entityManager.find(Entrevista.class, idEntrevista);
			entity.setEstatus(estatus.getIdOpcion());
			entity.setFechaModificacion(fechaModificacion);
			
		}catch(Exception e){
			logger.error(e);
			throw new PersistenceException(e);
		}

	}

	
}
