package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import mx.gob.stps.portal.persistencia.entity.OfertaExterna;
import org.apache.log4j.Logger;

@Stateless
public class OfertaExternaFacade implements OfertaExternaFacadeLocal {
	private static Logger logger = Logger.getLogger(OfertaCarreraEspecFacade.class);

	@PersistenceContext
	private EntityManager entityManager;

	public Long save(Long idOfertaEmpleo, Long idOfertaBolsa) {
		Long idOfertaExterna;
		try {
			OfertaExterna entity = new OfertaExterna();
			entity.setIdOfertaBolsa(idOfertaBolsa);
			entity.setIdOfertaEmpleo(idOfertaEmpleo);
			entityManager.persist(entity);
			idOfertaExterna = entity.getIdOfertaExterna();
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return idOfertaExterna;
	}
	
	
	
	
}
