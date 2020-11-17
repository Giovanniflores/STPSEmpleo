package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.dao.RegistroContactoDAO;
import mx.gob.stps.portal.core.empresa.vo.RegistroContactoVO;
import mx.gob.stps.portal.core.persistencia.entity.Contacto;

import org.apache.log4j.Logger;

/**
 * Implementacion de los metodos de persistencia para registros de la entidad "Contacto"
 * 
 * @author Carlos Sardaneta
 *
 */
//TODO ELIMINAR CLASE YA NO SE UTILIZA
//@Stateless
public class ContactoFacade implements ContactoFacadeLocal {

	private static Logger logger = Logger.getLogger(ContactoFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	/**
	 * Registra un contacto
	 * @param vo RegistroContactoVO
	 * @return Identificador de la Contacto
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public long save(RegistroContactoVO vo) throws PersistenceException {
		try{
			Contacto entity = getEntity(vo);
			entityManager.persist(entity);
			return entity.getIdContacto();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}			
	}

	/**
	 * Elimina una Contacto
	 * @param vo RegistroContactoVO
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */	
	public void delete(RegistroContactoVO vo) throws PersistenceException {
		try{
			//entityManager.remove(getEntity(vo));			
			Contacto entity = entityManager.find(Contacto.class, vo.getIdContacto());
			entityManager.remove(entity);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}

	/**
	 * Method 'findById'
	 * Regresa un objeto RegistroContactoVO con los datos correspondientes al
	 * Contacto cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return RegistroContactoVO
	 */	
	public RegistroContactoVO findById(long id) throws PersistenceException {
		try{
			Contacto instance = entityManager.find(Contacto.class, id);		
			return getRegistroContactoVO(instance);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}

	/**
	 * Method 'findAllByIdEmpresa'
	 * Regresa un listado de objetos RegistroContactoVO con los datos correspondientes al
	 * Contacto cuyo identificador padre (idEmpresa) se proporciona
	 * 
	 * @param idEmpresa
	 * @return RegistroContactoVO
	 */	
	public List<RegistroContactoVO> findAllByIdEmpresa(long idEmpresa) throws PersistenceException {
		try{
			List<RegistroContactoVO> lstContacto = new ArrayList<RegistroContactoVO>();
			//RegistroContactoDAO epaDAO = new RegistroContactoDAO();
			//lstContacto =  epaDAO.findAllByIdEmpresa(idEmpresa);
			return lstContacto;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(e);
		}		
	}
	
	/**
	 * Method 'update'
	 * 
	 * @param vo
	 */		
	public void update(RegistroContactoVO vo) throws PersistenceException {
		try{			
			//Contacto entity = new Contacto();
			Contacto entity = entityManager.find(Contacto.class, vo.getIdContacto());			
			entity.setIdContacto(vo.getIdContacto());
			entity.setIdEmpresa(vo.getIdEmpresa());
			entity.setNombreContacto(vo.getNombreContacto());
			entity.setCargo(vo.getCargo());
			//entity.setEstatus(vo.getEstatus());
			entity.setFechaModificacion(vo.getFechaModificacion());
			entity.setIdTerceraEmpresa(vo.getIdTerceraEmpresa());
			entity.setCorreoElectronico(vo.getCorreoElectronico());	
			entityManager.merge(entity);
			entityManager.flush();			
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}

	/**
	 * Method 'actualizaEstatus'
	 * Actualiza el estatus del Contacto correspondiente al valor proporcionado.
	 * 
	 * @param idContacto
	 * @param estatus
	 */		
	public void actualizaEstatus(long idContacto, int estatus)
			throws PersistenceException {
		try{
			Contacto contacto = entityManager.find(Contacto.class, idContacto);
			contacto.setEstatus(estatus);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}

	/**
	 * Method 'actualizaUltimaFecha'
	 * Actualiza la fecha de actualización del Contacto correspondiente al valor proporcionado.
	 * 
	 * @param idContacto
	 * @param ultimaFecha
	 */	
	public void actualizaUltimaFecha(long idContacto, Date ultimaFecha)
			throws PersistenceException {
		try{
			Contacto contacto = entityManager.find(Contacto.class, idContacto);
			contacto.setFechaModificacion(ultimaFecha);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}				
	}
	
	/**
	 * Method 'getRegistroContactoVO'
	 * Coloca los datos de un objeto Contacto en un VisualObject correspondiente
	 * @param empresa
	 * @return RegistroContactoVO
	 */		
	private RegistroContactoVO getRegistroContactoVO(Contacto contacto){
		RegistroContactoVO vo = null; //new RegistroContactoVO();
		vo.setIdContacto(contacto.getIdContacto());
		vo.setIdEmpresa(contacto.getIdEmpresa());
		vo.setNombreContacto(contacto.getNombreContacto());
		vo.setCargo(contacto.getCargo());
		vo.setEstatus(contacto.getEstatus());
		vo.setFechaAlta(contacto.getFechaAlta());
		vo.setFechaModificacion(contacto.getFechaModificacion());
		vo.setIdTerceraEmpresa(contacto.getIdTerceraEmpresa());
		vo.setCorreoElectronico(contacto.getCorreoElectronico());		
		return vo;
	}

	
	/**
	 * Method 'getEntity'
	 * Coloca los datos de un VisualObject en un objeto de tipo Contacto
	 * @param vo
	 * @return Contacto
	 */		
	private Contacto getEntity(RegistroContactoVO vo){
		Contacto entity = null; //new Contacto();
		entity.setIdContacto(vo.getIdContacto());
		entity.setIdEmpresa(vo.getIdEmpresa());
		if(null!=vo.getNombreContacto()){
			entity.setNombreContacto(vo.getNombreContacto().toUpperCase());
		}
		if(null!=vo.getCargo()){
			entity.setCargo(vo.getCargo().toUpperCase());
		}
		entity.setEstatus(vo.getEstatus());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaModificacion(vo.getFechaModificacion());
		if(entity.getIdTerceraEmpresa()>0 && vo.getIdTerceraEmpresa()==0){
			entity.setIdTerceraEmpresa(vo.getIdTerceraEmpresa());
		} else if(vo.getIdTerceraEmpresa()>0){
			entity.setIdTerceraEmpresa(vo.getIdTerceraEmpresa());
		}				
		entity.setCorreoElectronico(vo.getCorreoElectronico());
		return entity;
	}

	public String getContactoOfertaById(long idContacto) {
		String contactoOferta = "";
		Contacto entity = entityManager.find(Contacto.class, idContacto);
		
		if(entity!=null)contactoOferta=entity.getNombreContacto();
		
		return contactoOferta;
		
	}

	
	
	
}
