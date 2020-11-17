package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.candidate.vo.ConocerConfigVO;
import mx.gob.stps.portal.persistencia.entity.ConocerConfig;

import org.apache.log4j.Logger;

@Stateless
public class ConocerFacade implements ConocerFacadeLocal {
	
	private static Logger logger = Logger.getLogger(ConocerFacade.class);

	@PersistenceContext
	private EntityManager entityManager;

	public ConocerConfigVO save(ConocerConfigVO vo) throws PersistenceException {

		try {

			ConocerConfig entity = null;
			ConocerConfig conocerConfig = conocerConfigFindByIdCandidato(vo.getIdCandidato());
			// Si ya existía en CONOCER_CONFIG un registro para el candidato
			if (conocerConfig != null) {
				conocerConfig.setPublicarEnDatos(vo.getDeseaPublicar());
				conocerConfig.setVolverPreguntar(vo.getVolverPreguntar());				
				conocerConfig.setFechaModificacion(new Date());
				conocerConfig.setPublicarEnPerfil(vo.getPublicarEnPerfil());
				entity = conocerConfig;
			}else{
				entity = getEntity(vo);
				entity.setFechaModificacion(new Date());				
				entityManager.persist(entity);
			}

			return setEntity(entity);
		} catch (RuntimeException re) {
			logger.error("Error al salvar las preferencias en CONOCER_CONFIG");
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}
	
	public ConocerConfigVO consultaConocerConfigByIdCandidato(Long idCandidato) throws PersistenceException {
		ConocerConfigVO vo = null;
		ConocerConfig conocerConfig = conocerConfigFindByIdCandidato(idCandidato);

		if (conocerConfig != null) {
			vo = setEntity(conocerConfig);
		}		

		return vo;
	}

	private ConocerConfig conocerConfigFindByIdCandidato(Long idCandidato) throws PersistenceException {
		
		ConocerConfig conocerConfig = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select c from ConocerConfig c where	 c.idCandidato = :idCandidato");
		
		if (idCandidato != null) {
			
			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("idCandidato", idCandidato);
			
			try {
				conocerConfig = (ConocerConfig)query.getSingleResult();
				
				if (conocerConfig!=null){
					entityManager.refresh(conocerConfig);
				}				
			} catch (NoResultException re){
				//logger.info("No existe registro en CONOCER_CONFIG para idCandidato="+idCandidato);
			}				
		}
		return conocerConfig;
	}
	
	private ConocerConfig getEntity(ConocerConfigVO vo) {

		ConocerConfig conocerConfig = new ConocerConfig();

		conocerConfig.setIdConocer(vo.getIdConocer());
		conocerConfig.setIdCandidato(vo.getIdCandidato());
		conocerConfig.setPublicarEnDatos(vo.getDeseaPublicar());
		conocerConfig.setPublicarEnPerfil(vo.getPublicarEnPerfil());
		conocerConfig.setVolverPreguntar(vo.getVolverPreguntar());
		conocerConfig.setFechaModificacion(vo.getFechaModificacion());

		return conocerConfig;
	}

	private ConocerConfigVO setEntity(ConocerConfig entity){
		ConocerConfigVO vo = new ConocerConfigVO();
		
		vo.setIdConocer(entity.getIdConocer());
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setDeseaPublicar(entity.getPublicarEnDatos());
		vo.setPublicarEnPerfil(entity.getPublicarEnPerfil());
		vo.setVolverPreguntar(entity.getVolverPreguntar());
		vo.setFechaModificacion(entity.getFechaModificacion());
		
		return vo;
	}
	
}
