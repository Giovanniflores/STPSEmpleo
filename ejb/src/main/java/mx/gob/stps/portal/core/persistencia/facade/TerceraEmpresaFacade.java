package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.empresa.vo.TerceraEmpresaVO;
import mx.gob.stps.portal.core.persistencia.entity.TerceraEmpresa;

import org.apache.log4j.Logger;

/**
 * Implementacion de los metodos de persistencia para registros de la entidad "TerceraEmpresa"
 * 
 * @author haydee.vertti
 *
 */
//@Stateless
public class TerceraEmpresaFacade implements TerceraEmpresaFacadeLocal {

	private static Logger logger = Logger.getLogger(TerceraEmpresaFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Registra una TerceraEmpresa
	 * @param vo TerceraEmpresaVO
	 * @return Identificador de la TerceraEmpresa
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public long save(TerceraEmpresaVO vo) throws PersistenceException {
		try{
			TerceraEmpresa entity = getEntity(vo);
			entityManager.persist(entity);
			return entity.getIdTerceraEmpresa();
		} catch (Exception re) {
			logger.error(re); re.printStackTrace();
			throw new PersistenceException(re);
		}			
	}

	/**
	 * Elimina una TerceraEmpresa
	 * @param vo TerceraEmpresaVO
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */	
	public void delete(TerceraEmpresaVO vo) throws PersistenceException {
		try{
			entityManager.remove(getEntity(vo));
		} catch (Exception re) {
			logger.error(re); re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}

	public void delete(long idTerceraEmpresa) throws PersistenceException {
		try{
			TerceraEmpresa entity = entityManager.find(TerceraEmpresa.class, idTerceraEmpresa);
			entityManager.remove(entity);
		} catch (Exception re) {
			logger.error(re); re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}
	
	/**
	 * Method 'findById'
	 * Regresa un objeto TerceraEmpresaVO con los datos correspondientes a
	 * la Tercera Empresa cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return TerceraEmpresaVO
	 */	
	public TerceraEmpresaVO findById(long id) throws PersistenceException {
		try{
			TerceraEmpresa instance = entityManager.find(TerceraEmpresa.class, id);		
			return getTerceraEmpresaVO(instance);
		} catch (Exception re) {
			logger.error(re); re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}

	/**
	 * Method 'findAllByIdEmpresa'
	 * Regresa un listado de objetos TerceraEmpresaVO con los datos correspondientes a
	 * las Terceras Empresas cuyo identificador padre (idEmpresa) se proporciona
	 * 
	 * @param idEmpresa
	 * @return TerceraEmpresaVO
	 */	
	public List<TerceraEmpresaVO> findAllByIdEmpresa(long idEmpresa) throws PersistenceException {
		try{
			List<TerceraEmpresaVO> lstTerceras = new ArrayList<TerceraEmpresaVO>();
			//TerceraEmpresaDAO epaDAO = new TerceraEmpresaDAO();
			//lstTerceras =  epaDAO.findAllByIdEmpresa(idEmpresa);
			return lstTerceras;
		} catch (RuntimeException re) {
			logger.error(re); re.printStackTrace();
			throw new PersistenceException(re);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
			throw new PersistenceException(e);
		}		
	}
	
	public List<TerceraEmpresaVO> findByIdEmpresa(long idEmpresa) throws PersistenceException {
		List<TerceraEmpresaVO> tercerasEmpresas = new ArrayList<TerceraEmpresaVO>();
		
		try{
			String jpql = "select t from TerceraEmpresa t where t.idEmpresa = :idEmpresa";

			Query query = entityManager.createQuery(jpql);
			query.setParameter("idEmpresa", idEmpresa);

			List<TerceraEmpresa> results = query.getResultList();
			for(TerceraEmpresa terceraEmpresa : results){
				TerceraEmpresaVO vo = getTerceraEmpresaVO(terceraEmpresa);
				tercerasEmpresas.add(vo);
			}
			
		} catch (NoResultException re) {
			logger.error("Terceras Empresas no localizadas para la Empresa : "+ idEmpresa);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
			throw new PersistenceException(e);
		}		

		return tercerasEmpresas;
	}
	
	public int countOffersByIdTerceraEmpresa(long idTerceraEmpresa, int estatus) throws PersistenceException{
		int countOffers = 0;
		/*TerceraEmpresaDAO epaDAO = new TerceraEmpresaDAO();
		try {
			countOffers = epaDAO.countOffersByIdTerceraEmpresa(idTerceraEmpresa, estatus);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
			throw new PersistenceException(e);
		}*/
		return countOffers;
	}

	/**
	 * Method 'update'
	 * 
	 * @param vo
	 */		
	public void update(TerceraEmpresaVO vo) throws PersistenceException {
		try{
			TerceraEmpresa entity = entityManager.find(TerceraEmpresa.class, vo.getIdTerceraEmpresa());
			entity.setApellido1(vo.getApellido1());
			entity.setApellido2(vo.getApellido2());
			entity.setContactoEmpresa(vo.getContactoEmpresa());
			entity.setCorreoElectronico(vo.getCorreoElectronico());
			entity.setDescripcion(vo.getDescripcion());
			//entity.setEstatus(vo.getEstatus());
			//entity.setFechaAlta(vo.getFechaAlta());
			entity.setFechaUltimaActualizacion(vo.getFechaUltimaActualizacion());
			entity.setIdActividadEconomica(vo.getIdActividadEconomica());
			entity.setIdEmpresa(vo.getIdEmpresa());
			//entity.setIdTerceraEmpresa(vo.getIdTerceraEmpresa());
			entity.setIdTipoEmpresa(vo.getIdTipoEmpresa());
			entity.setIdTipoPersona(vo.getIdTipoPersona());
			entity.setNombre(vo.getNombre());
			entity.setNumeroEmpleados(vo.getNumeroEmpleados());
			entity.setRazonSocial(vo.getRazonSocial());
			entity.setNombreComercial(vo.getNombreComercial());
			entity.setRfc(vo.getRfc());
			entityManager.merge(entity);
			entityManager.flush();			
		} catch (Exception re) {
			logger.error(re); re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}

	/**
	 * Method 'actualizaEstatus'
	 * Actualiza el estatus a la Tercera Empresa correspondiente al valor proporcionado.
	 * 
	 * @param idTerceraEmpresa
	 * @param estatus
	 */		
	public void actualizaEstatus(long idTerceraEmpresa, int estatus)
			throws PersistenceException {
		try{
			TerceraEmpresa empresa = entityManager.find(TerceraEmpresa.class, idTerceraEmpresa);
			empresa.setEstatus(estatus);
		} catch (Exception re) {
			logger.error(re); re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}

	/**
	 * Method 'actualizaUltimaFecha'
	 * Actualiza la fecha de actualización a la Tercera Empresa correspondiente al valor proporcionado.
	 * 
	 * @param idTerceraEmpresa
	 * @param ultimaFecha
	 */	
	public void actualizaUltimaFecha(long idTerceraEmpresa, Date ultimaFecha)
			throws PersistenceException {
		try{
			TerceraEmpresa empresa = entityManager.find(TerceraEmpresa.class, idTerceraEmpresa);
			empresa.setFechaUltimaActualizacion(ultimaFecha);
		} catch (Exception re) {
			logger.error(re); re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}
	
	/**
	 * Method 'getTerceraEmpresaVO'
	 * Coloca los datos de un objeto Tercera Empresa en un VisualObject correspondiente
	 * @param empresa
	 * @return TerceraEmpresaVO
	 */		
	private TerceraEmpresaVO getTerceraEmpresaVO(TerceraEmpresa empresa){
		TerceraEmpresaVO vo = null; // new TerceraEmpresaVO();
		vo.setApellido1(empresa.getApellido1());
		vo.setApellido2(empresa.getApellido2());
		vo.setContactoEmpresa(empresa.getContactoEmpresa());
		vo.setCorreoElectronico(empresa.getCorreoElectronico());
		vo.setDescripcion(empresa.getDescripcion());
		vo.setEstatus(empresa.getEstatus());
		vo.setFechaAlta(empresa.getFechaAlta());
		vo.setFechaUltimaActualizacion(empresa.getFechaUltimaActualizacion());
		vo.setIdActividadEconomica(empresa.getIdActividadEconomica());
		vo.setIdEmpresa(empresa.getIdEmpresa());
		vo.setIdTerceraEmpresa(empresa.getIdTerceraEmpresa());
		vo.setIdTipoEmpresa(empresa.getIdTipoEmpresa());
		vo.setIdTipoPersona(empresa.getIdTipoPersona());
		vo.setNombre(empresa.getNombre());
		vo.setNumeroEmpleados(empresa.getNumeroEmpleados());
		vo.setRazonSocial(empresa.getRazonSocial());
		vo.setRfc(empresa.getRfc());
		vo.setNombreComercial(empresa.getNombreComercial());		
		return vo;
	}

	
	/**
	 * Method 'getEntity'
	 * Coloca los datos de un VisualObject en un objeto de tipo TerceraEmpresa
	 * @param vo
	 * @return TerceraEmpresa
	 */		
	private TerceraEmpresa getEntity(TerceraEmpresaVO vo){
		TerceraEmpresa entity = null; // new TerceraEmpresa();
		entity.setApellido1(vo.getApellido1());
		entity.setApellido2(vo.getApellido2());
		entity.setContactoEmpresa(vo.getContactoEmpresa());
		entity.setCorreoElectronico(vo.getCorreoElectronico());
		entity.setDescripcion(vo.getDescripcion());
		entity.setEstatus(vo.getEstatus());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaUltimaActualizacion(vo.getFechaUltimaActualizacion());
		entity.setIdActividadEconomica(vo.getIdActividadEconomica());
		entity.setIdEmpresa(vo.getIdEmpresa());
		//entity.setIdTerceraEmpresa(vo.getIdTerceraEmpresa());
		entity.setIdTipoEmpresa(vo.getIdTipoEmpresa());
		entity.setIdTipoPersona(vo.getIdTipoPersona());
		entity.setNombre(vo.getNombre());
		entity.setNumeroEmpleados(vo.getNumeroEmpleados());
		entity.setRazonSocial(vo.getRazonSocial());
		entity.setRfc(vo.getRfc());
		entity.setNombreComercial(vo.getNombreComercial());
		return entity;
	}
	
	
}
