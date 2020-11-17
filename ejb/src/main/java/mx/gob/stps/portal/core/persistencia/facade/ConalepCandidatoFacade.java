package mx.gob.stps.portal.core.persistencia.facade;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import mx.gob.stps.portal.persistencia.entity.*;
import mx.gob.stps.portal.persistencia.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

@Stateless
public class ConalepCandidatoFacade implements ConalepCandidatoFacadeLocal {
	private static Logger logger = Logger.getLogger(ConalepCandidatoFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save(ConalepCandidatoVO conalepCandidatoVO) throws PersistenceException {
		try {
			ConalepCandidato conalepCandidato = new ConalepCandidato();
			BeanUtils.copyProperties(conalepCandidato, conalepCandidatoVO);
			if (estaRegistrado(conalepCandidatoVO.getIdCandidato())) {
				entityManager.merge(conalepCandidato);
				entityManager.flush();
			} else {
				entityManager.persist(conalepCandidato);
			}
		} catch (RuntimeException re) {
			logger.error("Error en " + this.getClass().getName() + " --> save " + re);
			throw new PersistenceException(re);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ConalepPlantelVO findPlantel(long idPlantel) throws PersistenceException {
		ConalepPlantelVO conalepPlantelVO = null;
		try {
			ConalepPlantel conalepPlantel = entityManager.find(ConalepPlantel.class, idPlantel);
			conalepPlantelVO = new ConalepPlantelVO();
			BeanUtils.copyProperties(conalepPlantelVO, conalepPlantel);
		} catch (RuntimeException re) {
			logger.error("Error en " + this.getClass().getName() + " --> save " + re);
			throw new PersistenceException(re);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return  conalepPlantelVO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ConalepCandidatoVO obtenerConalepCandidato(long idCandidato) throws PersistenceException {
		ConalepCandidatoVO conalepCandidatoVO = null;
		try {
			TypedQuery<ConalepCandidato> query = this.entityManager.createQuery("SELECT cc FROM ConalepCandidato cc WHERE cc.idCandidato = :idCandidato", ConalepCandidato.class);
			ConalepCandidato conalepCandidato = query.setParameter("idCandidato", idCandidato).getSingleResult();
			conalepCandidatoVO = new ConalepCandidatoVO();
			BeanUtils.copyProperties(conalepCandidatoVO, conalepCandidato);
		} catch (RuntimeException re) {
//			logger.error("Error en " + this.getClass().getName() + " --> obtenerConalepCandidato " + re);
//			throw new PersistenceException(re);
			// DO Nothing...
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return conalepCandidatoVO;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ConalepAreaNegocioVO> getConalepAreasNegocio() throws PersistenceException{
		
		List<ConalepAreaNegocioVO> returnList = new ArrayList<ConalepAreaNegocioVO>();
		
		try {
			TypedQuery<ConalepAreaNegocio> query = this.entityManager.createQuery("SELECT can FROM ConalepAreaNegocio can", ConalepAreaNegocio.class);
			List<ConalepAreaNegocio> resultList = query.getResultList();
			
			if(validaResultList(resultList)) {
				for(ConalepAreaNegocio e : resultList) {
					ConalepAreaNegocioVO conalepAreaNegocioVO = new ConalepAreaNegocioVO();
					BeanUtils.copyProperties(conalepAreaNegocioVO, e);
					returnList.add(conalepAreaNegocioVO);
				}
			}
			
		} catch (RuntimeException re) {
			logger.error("Error en " + this.getClass().getName() + " --> getConalepAreasNegocio " + re);
			throw new PersistenceException(re);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return returnList;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ConalepSubAreaNegocioVO> getConalepSubAreasNegocio(long idAreaNegocio) throws PersistenceException {

		List<ConalepSubAreaNegocioVO> returnList = new ArrayList<ConalepSubAreaNegocioVO>();

		try {
			TypedQuery<ConalepSubAreaNegocio> query = this.entityManager.createQuery("SELECT csan FROM ConalepSubAreaNegocio csan WHERE csan.idAreaNegocio = :idAreaNegocio", ConalepSubAreaNegocio.class);
			List<ConalepSubAreaNegocio> resultList = query.setParameter("idAreaNegocio", idAreaNegocio).getResultList();

			if(validaResultList(resultList)) {
				for(ConalepSubAreaNegocio e : resultList) {
					ConalepSubAreaNegocioVO conalepSubAreaNegocioVO = new ConalepSubAreaNegocioVO();
					BeanUtils.copyProperties(conalepSubAreaNegocioVO, e);
					returnList.add(conalepSubAreaNegocioVO);
				}
			}

		} catch (RuntimeException re) {
			logger.error("Error en " + this.getClass().getName() + " --> getConalepSubAreasNegocio " + re);
			throw new PersistenceException(re);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return returnList;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ConalepPlantelVO> getConalepPlanteles(long idEntidad) throws PersistenceException{
		List<ConalepPlantelVO> returnList = new ArrayList<ConalepPlantelVO>();
		
		try {
			TypedQuery<ConalepPlantel> query = this.entityManager.createQuery("SELECT cp FROM ConalepPlantel cp WHERE cp.idEntidad = :idEntidad", ConalepPlantel.class);
			List<ConalepPlantel> resultList = query.setParameter("idEntidad", idEntidad).getResultList();
			
			if(validaResultList(resultList)) {
				for(ConalepPlantel e : resultList) {
					ConalepPlantelVO conalepPlantelVO = new ConalepPlantelVO();
					BeanUtils.copyProperties(conalepPlantelVO, e);
					returnList.add(conalepPlantelVO);
				}
			}
			
		} catch (RuntimeException re) {
			logger.error("Error en " + this.getClass().getName() + " --> getConalepPlanteles " + re);
			throw new PersistenceException(re);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return returnList;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ConalepPlanEstudiosVO> getConalepPlanesEstudio() throws PersistenceException{
		List<ConalepPlanEstudiosVO> returnList = new ArrayList<ConalepPlanEstudiosVO>();
		
		try {
			TypedQuery<ConalepPlanEstudios> query = this.entityManager.createQuery("SELECT cpe FROM ConalepPlanEstudios cpe", ConalepPlanEstudios.class);
			List<ConalepPlanEstudios> resultList = query.getResultList();
			
			if(validaResultList(resultList)) {
				for(ConalepPlanEstudios e : resultList) {
					ConalepPlanEstudiosVO conalepPlanEstudiosVO = new ConalepPlanEstudiosVO();
					BeanUtils.copyProperties(conalepPlanEstudiosVO, e);
					returnList.add(conalepPlanEstudiosVO);
				}
			}
			
		} catch (RuntimeException re) {
			logger.error("Error en " + this.getClass().getName() + " --> getConalepPlanesEstudio " + re);
			throw new PersistenceException(re);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return returnList;
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean estaRegistrado(Long idCandidato) {
		try {
			TypedQuery<ConalepCandidato> query = this.entityManager.createQuery("SELECT cc FROM ConalepCandidato cc WHERE cc.idCandidato = :idCandidatoConalep", ConalepCandidato.class);
			ConalepCandidato candidato = query.setParameter("idCandidatoConalep", idCandidato).getSingleResult();
			
			if(candidato != null) {
				return true;
			}
			
		} catch (RuntimeException re) {
			// DO Nothing...
		}
		return false;
	}
	
	private <E> boolean validaResultList(List<E> resultList) {
		if(resultList != null && !resultList.isEmpty()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
