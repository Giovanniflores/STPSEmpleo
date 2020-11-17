package mx.gob.stps.portal.core.persistencia.facade;

import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_BUMERAN;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_MANPOWER;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_OCC;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_ADECCO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_UNIVERSAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_STPS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_VACANTES_ZONA_JOBS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_OFERTAS_SUPERCHAMBA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_PARAMETRO_TOTAL_OFERTAS_TRABAJOSMX;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.Parametro;

import org.apache.log4j.Logger;

/**
 * Implementacion de los metodos de persistencia para registros de la entidad "Parametro"
 * 
 * @author oscar.manzo
 *
 */
@Stateless
public class ParametroFacade implements ParametroFacadeLocal {

	private static Logger logger = Logger.getLogger(ParametroFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.ParametroFacadeLocal#save(java.lang.String, java.lang.String)
	 */
	public long save(String descripcion, String valor) throws PersistenceException {
		long idParametro = -1;
		
		try {
			Parametro entity = getEntity(descripcion, valor);
			entityManager.persist(entity);
			
			idParametro = entity.getIdParametro();
			
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return idParametro;
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.ParametroFacadeLocal#updateParametro(long, java.lang.String, java.lang.String)
	 */
	public void updateParametro(long idParametro, String descripcion, String valor){
		Parametro entity = getEntity(idParametro, descripcion, valor);
		entityManager.merge(entity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void update(long idParametro, String valor){
		try{
			Parametro entity = entityManager.find(Parametro.class, idParametro);
			if (entity!=null)
				entity.setValor(valor);
			entityManager.flush();
		}catch(Exception e){
			logger.error(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.ParametroFacadeLocal#updateOrSaveParametro(long, java.lang.String)
	 */
	public void updateOrSaveParametro(long idParametro, String valor){
		updateOrSaveParametro(idParametro, null, valor);
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.ParametroFacadeLocal#updateOrSaveParametro(long, java.lang.String, java.lang.String)
	 */
	public void updateOrSaveParametro(long idParametro, String descripcion, String valor){

		Parametro entity = entityManager.find(Parametro.class, idParametro);
		
		if (entity!=null){
			entity.setValor(valor);
			entityManager.flush();
		} else{
			save(descripcion, valor);
		}
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.ParametroFacadeLocal#findById(long)
	 */
	public ParametroVO findById(long idParametro) throws PersistenceException {
		ParametroVO vo = null;
		
		try{
			Parametro entity = entityManager.find(Parametro.class, idParametro);
			if (entity!=null){
				entityManager.refresh(entity);
				vo = getParametroVO(entity);
			}
		}catch(Exception e){
			throw new PersistenceException(e);
		}

		return vo;
	}	

	public List<ParametroVO> findAll() throws PersistenceException {
		List<ParametroVO> parametros = new ArrayList<ParametroVO>();
		String findAllParametros = "select p from Parametro p";
		try{
			Query query = entityManager.createQuery(findAllParametros);
			@SuppressWarnings("unchecked")
			List<Parametro> results = (List<Parametro>)query.getResultList();

			if (results!=null){
				for (Parametro entity : results){
					ParametroVO vo = getParametroVO(entity);
					parametros.add(vo);
				}
			}
		}catch(Exception e){
			throw new PersistenceException(e);
		}

		return parametros;
	}

	/**
	 * Consulta y suma los valores de los parametros para totales de ofertas
	 */
	public long consultaSumaParametrosOfertas() throws PersistenceException {
		long total = 0;
		
		try{
			Query query = entityManager.createNativeQuery("SELECT SUM(TO_NUMBER(VALOR)) FROM PARAMETRO WHERE ID_PARAMETRO IN (?1,?2,?3,?4,?5,?6,?7,?8,?9)");
			
			query.setParameter(1, ID_PARAMETRO_TOTAL_VACANTES_OCC);
			query.setParameter(2, ID_PARAMETRO_TOTAL_VACANTES_BUMERAN);
			query.setParameter(3, ID_PARAMETRO_TOTAL_VACANTES_MANPOWER);
			query.setParameter(4, ID_PARAMETRO_TOTAL_VACANTES_ADECCO);
			query.setParameter(5, ID_PARAMETRO_TOTAL_VACANTES_UNIVERSAL);
			query.setParameter(6, ID_PARAMETRO_TOTAL_VACANTES_STPS);
			query.setParameter(7, ID_PARAMETRO_TOTAL_VACANTES_ZONA_JOBS);
			query.setParameter(8, ID_PARAMETRO_TOTAL_OFERTAS_SUPERCHAMBA);
			query.setParameter(9, ID_PARAMETRO_TOTAL_OFERTAS_TRABAJOSMX);
			

			Number sum = (Number)query.getSingleResult();

			if (sum!=null)
				total = sum.longValue();

		}catch(Exception e){
			throw new PersistenceException(e);
		}

		return total;
	}
	
	public List<ParametroVO> consultaParametrosOfertasExternas() throws PersistenceException {
		List<ParametroVO> parametros = new ArrayList<ParametroVO>();
		
		try{
			Query query = entityManager.createNativeQuery("SELECT id_parametro,descripcion, valor FROM PARAMETRO WHERE ID_PARAMETRO IN (?1,?2,?3,?4,?5,?6)");
			
			query.setParameter(1, ID_PARAMETRO_TOTAL_VACANTES_OCC);
			query.setParameter(2, ID_PARAMETRO_TOTAL_VACANTES_BUMERAN);
			query.setParameter(3, ID_PARAMETRO_TOTAL_VACANTES_MANPOWER);
			query.setParameter(4, ID_PARAMETRO_TOTAL_VACANTES_ADECCO);
			query.setParameter(5, ID_PARAMETRO_TOTAL_VACANTES_UNIVERSAL);
			query.setParameter(6, ID_PARAMETRO_TOTAL_VACANTES_ZONA_JOBS);
			@SuppressWarnings("unchecked")
			List<Object> rowSet = query.getResultList();
			if (rowSet!=null){
				Iterator<Object> itRowSet = rowSet.iterator();
				while(itRowSet.hasNext()){
					Object[] rs = (Object[])itRowSet.next();			
					ParametroVO vo = new ParametroVO();
					vo.setIdParametro(Utils.toLong(rs[0]));
					vo.setDescripcion(Utils.toString(rs[1]));
					vo.setValor(Utils.toString(rs[2]));
					parametros.add(vo);	
				}			
			}
		}catch(Exception e){
			throw new PersistenceException(e);
		}

		return parametros;		
	}

	/**
	 * Genera un <mx.gob.stps.portal.core.autorizacion.vo.ParametroVO> a partir de un <mx.gob.stps.portal.core.persistencia.entity.Parametro>
	 * @param entity instancia del entity
	 * @return instancia del VO
	 */
	private ParametroVO getParametroVO(Parametro entity){
		ParametroVO vo = new ParametroVO();
		
		vo.setIdParametro(entity.getIdParametro());
		vo.setDescripcion(entity.getDescripcion());
		vo.setValor(entity.getValor());
		
		return vo;
	}
	
	/**
	 * Genera un <mx.gob.stps.portal.core.persistencia.entity.Parametro>
	 * @param descripcion
	 * @param valor
	 * @return
	 */
	private Parametro getEntity(String descripcion, String valor){
		return getEntity(-1, descripcion, valor);
	}
	
	/**
	 * Genera un <mx.gob.stps.portal.core.persistencia.entity.Parametro>
	 * @param idParametro
	 * @param descripcion
	 * @param valor
	 * @return
	 */
	private Parametro getEntity(long idParametro, String descripcion, String valor){
		Parametro entity = new Parametro();
		entity.setIdParametro(idParametro);
		entity.setDescripcion(descripcion);
		entity.setValor(valor);
		return entity;
	}

}