package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.persistencia.entity.EmpresaCapMix;

import org.apache.log4j.Logger;

@Stateless
public class EmpresaCapMixtaFacade implements EmpresaCapMixtaFacadeLocal{

private static Logger logger = Logger.getLogger(EmpresaCapMixtaFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public long save(String texto, Long idEmpresa) throws PersistenceException {
		
		try {
			EmpresaCapMix entity = new EmpresaCapMix();
			entity.setIdEmpresa(idEmpresa);
			entity.setTexto(texto);
			entity.setFechaModificacion(new Date());
			entityManager.persist(entity);
			return entity.getIdEmpresaCapMix();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		
		
	}
	
	@Override
	public String obtenerCapacitacionMixta(long idEmpresa) throws PersistenceException {
		String resultado = null;
		try {
			String queryString = "select o.texto from EmpresaCapMixta o where o.idEmpresa =" + idEmpresa;
			Query query = entityManager.createQuery(queryString);
			resultado = (String) query.getSingleResult();
			
		}catch(NoResultException ex){
			logger.error(ex);
			resultado = null;
		}catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException(e);
		}
		
		return resultado;
	}

	@Override
	public Long updateCapacitacionMixta(String texto, Long idEmpresa) throws PersistenceException {
		Long idEmpresaR = null;
		try{
			EmpresaCapMix entity = entityManager.find(EmpresaCapMix.class, idEmpresa);
			entity.setTexto(texto);
			entity.setFechaModificacion(new Date());
			entityManager.merge(entity);
			idEmpresaR = entity.getIdEmpresa();
		}catch (PersistenceException e) {
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException(e);
		}
		return idEmpresaR;
	}

}
