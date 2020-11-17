package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.TerceraEmpresaVO;

/**
 * Proporciona acceso a los metodos de persistencia para registros de la entidad "TerceraEmpresa"
 * 
 * @author haydee.vertti
 *
 */
//@Local
public interface TerceraEmpresaFacadeLocal {
	
	/**
	 * Registra una TerceraEmpresa
	 * @param vo TerceraEmpresaVO
	 * @return Identificador de la TerceraEmpresa
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	//public long save(TerceraEmpresaVO vo) throws PersistenceException;	
	
	/**
	 * Elimina una TerceraEmpresa
	 * @param vo TerceraEmpresaVO
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */		
	//public void delete(TerceraEmpresaVO vo) throws PersistenceException;	
	
	//public void delete(long idTerceraEmpresa) throws PersistenceException;
	
	/**
	 * Method 'findById'
	 * Regresa un objeto TerceraEmpresaVO con los datos correspondientes a
	 * la Tercera Empresa cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return TerceraEmpresaVO
	 */	
	//public TerceraEmpresaVO findById(long id) throws PersistenceException;
	
	
	//public List<TerceraEmpresaVO> findAllByIdEmpresa(long idEmpresa) throws PersistenceException;
	
	//public List<TerceraEmpresaVO> findByIdEmpresa(long idEmpresa) throws PersistenceException;
	
	//public int countOffersByIdTerceraEmpresa(long idTerceraEmpresa, int estatus) throws PersistenceException;
	
	/**
	 * Method 'update'
	 * 
	 * @param vo
	 */		
	//public void update(TerceraEmpresaVO vo) throws PersistenceException;
	
	/**
	 * Method 'actualizaEstatus'
	 * Actualiza el estatus a la Tercera Empresa correspondiente al valor proporcionado.
	 * 
	 * @param idTerceraEmpresa
	 * @param estatus
	 */		
	//public void actualizaEstatus(long idTerceraEmpresa, int estatus) throws PersistenceException;
	
	/**
	 * Method 'actualizaUltimaFecha'
	 * Actualiza la fecha de actualización a la Tercera Empresa correspondiente al valor proporcionado.
	 * 
	 * @param idTerceraEmpresa
	 * @param ultimaFecha
	 */		
	//public void actualizaUltimaFecha(long idTerceraEmpresa, Date ultimaFecha) throws PersistenceException;	
	
}
