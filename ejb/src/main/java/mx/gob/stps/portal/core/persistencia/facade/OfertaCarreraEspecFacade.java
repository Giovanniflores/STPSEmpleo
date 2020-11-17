package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.persistencia.entity.OfertaCandidato;
import mx.gob.stps.portal.persistencia.entity.OfertaCarreraEspec;

import org.apache.log4j.Logger;

@Stateless
public class OfertaCarreraEspecFacade implements OfertaCarreraEspecFacadeLocal {
	private static Logger logger = Logger.getLogger(OfertaCarreraEspecFacade.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	private OfertaCarreraEspec getNewEntity(OfertaCarreraEspecialidadVO vo){
		OfertaCarreraEspec entity = new OfertaCarreraEspec();
		entity.setIdCarreraEspecialidad(vo.getId());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setIdOfertaEmpleo(vo.getIdRegistro());
		entity.setPrincipal(Utils.toLong(vo.getPrincipal()));		
		return entity;
	}
	
	public Long save(OfertaCarreraEspecialidadVO vo) throws PersistenceException {
		try {
			OfertaCarreraEspec entity = getNewEntity(vo);
			entityManager.persist(entity);
			entityManager.flush();
			vo.setIdRegistro(entity.getIdCarreraEspecialidad());
			return entity.getIdCarreraEspecialidad();
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}
	
	public int update(OfertaCarreraEspecialidadVO vo, Long idOfertaEmpleo) throws PersistenceException {
		try{			

			OfertaCarreraEspec entity = entityManager.find(OfertaCarreraEspec.class, vo.getIdRegistro());	
			if(null==entity){
				entity = new OfertaCarreraEspec();				
			}
			entity.setFechaAlta(vo.getFechaAlta());			
			entity.setPrincipal(Utils.toLong(vo.getPrincipal()));			
			entity.setIdCarreraEspecialidad(vo.getId());
			//entity.setIdOfertaCarrera(vo.getIdRegistro());
			entity.setIdOfertaEmpleo(idOfertaEmpleo);						
			entityManager.merge(entity);
			return 1;
		}catch(NoResultException re){
			logger.error("No se encontraron carreras con numero de idOfertaEmpleo");
			throw new PersistenceException(re);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}
	
	

}
