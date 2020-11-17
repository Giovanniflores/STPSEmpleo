package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;

/**
 * Proporciona acceso a los metodos de persistencia para registros de la entidad "EmpresaPorAutorizar"
 * 
 * @author haydee.vertti
 *
 */
@Local
public interface EmpresaPorAutorizarFacadeLocal {
	
	/**
	 * Registra una empresa por autorizar
	 * @param vo EmpresaPorAutorizarVO
	 * @return Identificador de la empresa por autorizar
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public long save(EmpresaPorAutorizarVO vo) throws PersistenceException;
	
	/**
	 * Elimina una empresa por autorizar
	 * @param vo EmpresaPorAutorizarVO
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */		
	public void delete(EmpresaPorAutorizarVO vo) throws PersistenceException;
	
	public void delete(long idEmpresa) throws PersistenceException;
	
	/**
	 * Method 'findById'
	 * Regresa un objeto EmpresaPorAutorizarVO con los datos correspondientes a
	 * la empresa por  autorizar cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return EmpresaPorAutorizarVO
	 */	
	public EmpresaPorAutorizarVO findById(long id) throws PersistenceException;
	
	/**
	 * Method 'update'
	 * 
	 * @param vo
	 */		
	public void update(EmpresaPorAutorizarVO vo) throws PersistenceException;
	
	/**
	 * Method 'actualizaEstatus'
	 * Actualiza el estatus a la empresa por autorizar correspondiente al valor proporcionado.
	 * 
	 * @param idEmpresa
	 * @param estatus
	 */		
	public void actualizaEstatus(long idEmpresa, int estatus) throws PersistenceException;
	
	/**
	 * Method 'actualizaUltimaFecha'
	 * Actualiza la fecha de actualización a la empresa por autorizar correspondiente al valor proporcionado.
	 * 
	 * @param idEmpresa
	 * @param ultimaFecha
	 */		
	public void actualizaUltimaFecha(long idEmpresa, Date ultimaFecha) throws PersistenceException;	
	
	/**
	 * Method 'generaIDPortalEmpleo'
	 * Genera el identificador del portal del empleo correspondiente a la empresa por autorizar
	 * cuyos datos se proporcionan.
	 * 
	 * @param idEmpresa
	 * @param estatus
	 */		
	public String generaIDPortalEmpleo(EmpresaPorAutorizarVO vo) throws PersistenceException;
	
	/**
	 * Method 'actualizaIDPortalEmpleo'
	 * Actauliza el identificador del portal del empleo correspondiente a la empresa por autorizar
	 * cuyo ID se proporciona, usando el idPortalEmpleo enviado.
	 * 
	 * @param idEmpresa
	 * @param idPortalEmpleo
	 * @param estatus
	 */			
	public void actualizaIDPortalEmpleo(long idEmpresa, String idPortalEmpleo) throws PersistenceException;
	

	public EmpresaPorAutorizarVO consultaEmpresaPorCorreo(String correoElectronico) throws PersistenceException;
}
