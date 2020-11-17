package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;


import mx.gob.stps.portal.core.cil.vo.CilCodigoAccesoVO;
import mx.gob.stps.portal.persistencia.entity.CilCodigoAcceso;

import org.apache.log4j.Logger;

/**
 * Implementacion de los metodos de persistencia para registros de la entidad "CilCodigoAcceso"
 * 
 * @author haydee.vertti
 *
 */
@Stateless
public class CilCodigoAccesoFacade implements CilCodigoAccesoFacadeLocal{

	private static Logger logger = Logger.getLogger(CilCodigoAcceso.class);
	
	@PersistenceContext
	private EntityManager entityManager;		
	
	/**
	 * Method 'save'
	 * Almacena los datos del ValueObject y regresa el valor del identificador creado.
	 * @param vo
	 * @return long
	 */	
	public long save(CilCodigoAccesoVO vo) throws PersistenceException {
		try {
			CilCodigoAcceso entity = getEntity(vo);
			entityManager.persist(entity);
			return entity.getIdCilCodigoAcceso();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}

	/**
	 * Method 'delete'
	 * 
	 * @param vo
	 */		
	public void delete(CilCodigoAccesoVO vo) throws PersistenceException {
		try {
			entityManager.remove(getEntity(vo));
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}

	
	public void delete(long idCilCodigoAcceso) throws PersistenceException {
		try {
			CilCodigoAcceso entity = entityManager.find(CilCodigoAcceso.class, idCilCodigoAcceso);			
			entityManager.remove(entity);

		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}		
	}
	

	/**
	 * Method 'findById'
	 * Regresa un objeto CilCodigoAccesoVO con los datos correspondientes al
	 * CilCodigoAcceso cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return CilCodigoAccesoVO
	 */	
	public CilCodigoAccesoVO findById(long id) throws PersistenceException {
		CilCodigoAccesoVO vo = null;
		try {
			CilCodigoAcceso instance = entityManager.find(CilCodigoAcceso.class, id);
			if(instance==null) return null;
			vo = getCilCodigoAccesoVO(instance);
			
		} catch (NoResultException re) {
			// No se localizaron registros
			return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return vo;
	}

	
	/**
	 * Method 'update'
	 * 
	 * @param vo
	 */	
	public void update(CilCodigoAccesoVO vo) throws PersistenceException {
		try {
			CilCodigoAcceso entity = entityManager.find(CilCodigoAcceso.class, vo.getIdCilCodigoAcceso());						
			entity.setIdCilCodigoAcceso(vo.getIdCilCodigoAcceso());
			entity.setIdCil(vo.getIdCil());
			entity.setContrasena(vo.getContrasena());
			entity.setEstatus(vo.getEstatus());
			entity.setFechaAlta(vo.getFechaAlta());
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}

	
	/**
	 * Method 'actualizaEstatus'
	 * Actualiza el estatus del CilCodigoAcceso, cuyo id se proporciona.
	 * 
	 * @param idCilCodigoAcceso
	 * @param estatus
	 */	
	public void actualizaEstatus(long idCilCodigoAcceso, int estatus)
			throws PersistenceException {
		try {
			CilCodigoAcceso cil = entityManager.find(CilCodigoAcceso.class, idCilCodigoAcceso);
			cil.setEstatus(estatus);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}
	
	
	/**
	 * Method 'getCilCodigoAccesoVO'
	 * Coloca los datos de un objeto CilCodigoAcceso en un VisualObject correspondiente
	 * @param cil
	 * @return CilCodigoAccesoVO
	 */		
	private CilCodigoAccesoVO getCilCodigoAccesoVO(CilCodigoAcceso cil){
		CilCodigoAccesoVO vo = new CilCodigoAccesoVO();
		vo.setIdCilCodigoAcceso(cil.getIdCilCodigoAcceso());
		vo.setIdCil(cil.getIdCil());
		vo.setContrasena(cil.getContrasena());
		vo.setEstatus(cil.getEstatus());
		vo.setFechaAlta(cil.getFechaAlta());
		return vo;
	}	
	
	/**
	 * Method 'getEntity'
	 * Coloca los datos de un VisualObject en un objeto de tipo CilCodigoAcceso
	 * @param vo
	 * @return CilCodigoAcceso
	 */		
	private CilCodigoAcceso getEntity(CilCodigoAccesoVO vo){
		CilCodigoAcceso entity = new CilCodigoAcceso();
		entity.setIdCilCodigoAcceso(vo.getIdCilCodigoAcceso());
		entity.setIdCil(vo.getIdCil());
		entity.setContrasena(vo.getContrasena());
		entity.setEstatus(vo.getEstatus());
		entity.setFechaAlta(vo.getFechaAlta());
		return entity;
	}	

}
