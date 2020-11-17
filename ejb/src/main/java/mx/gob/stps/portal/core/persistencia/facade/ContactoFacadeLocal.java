package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.RegistroContactoVO;
import mx.gob.stps.portal.core.infra.vo.ContactoVO;

/**
 * Proporciona acceso a los metodos de persistencia para registros de la entidad "Contacto"
 * 
 * @author haydee.vertti
 *
 */
//@Local
public interface ContactoFacadeLocal {
	
	/**
	 * Registra una Contacto
	 * @param vo RegistroContactoVO
	 * @return Identificador del Contacto
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	//public long save(RegistroContactoVO vo) throws PersistenceException;	
	
	/**
	 * Elimina un Contacto
	 * @param vo RegistroContactoVO
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */		
	//public void delete(RegistroContactoVO vo) throws PersistenceException;	
	
	/**
	 * Method 'findById'
	 * Regresa un objeto RegistroContactoVO con los datos correspondientes al
	 * Contacto cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return RegistroContactoVO
	 */	
	//public RegistroContactoVO findById(long id) throws PersistenceException;
	
	
	//public List<RegistroContactoVO> findAllByIdEmpresa(long idEmpresa) throws PersistenceException;
	
	/**
	 * Method 'update'
	 * 
	 * @param vo
	 */		
	//public void update(RegistroContactoVO vo) throws PersistenceException;
	
	/**
	 * Method 'actualizaEstatus'
	 * Actualiza el estatus a la Tercera Empresa correspondiente al valor proporcionado.
	 * 
	 * @param idContacto
	 * @param estatus
	 */		
	//public void actualizaEstatus(long idContacto, int estatus) throws PersistenceException;
	
	/**
	 * Method 'actualizaUltimaFecha'
	 * Actualiza la fecha de actualización al Contacto correspondiente al valor proporcionado.
	 * 
	 * @param idContacto
	 * @param ultimaFecha
	 */		
	//public void actualizaUltimaFecha(long idContacto, Date ultimaFecha) throws PersistenceException;

	
	//public String getContactoOfertaById(long idContacto);	
	
}