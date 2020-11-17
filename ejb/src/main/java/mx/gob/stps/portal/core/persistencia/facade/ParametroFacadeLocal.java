package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;

/**
 * Proporciona acceso a los metodos de persistencia para registros de la entidad "Parametro"
 * 
 * @author oscar.manzo
 *
 */
@Local
public interface ParametroFacadeLocal {

	/**
	 * Registra un Parametro
	 * @param descripcion Descripcion del parametro
	 * @param valor Valor del parametro
	 * @return identificador del parametro
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public long save(String descripcion, String valor) throws PersistenceException;

	public void update(long idParametro, String valor);
	
	/**
	 * Consulta un registro
	 * @param idParametro identificador del parametro
	 * @return instancia de <mx.gob.stps.portal.core.autorizacion.vo.ParametroVO>
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public ParametroVO findById(long idParametro) throws PersistenceException;
	
	/**
	 * Actualiza los datos del Parametro
	 * @param idParametro identificador del parametro
	 * @param descripcion Descripcion del parametro
	 * @param valor Valor del parametro
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public void updateParametro(long idParametro, String descripcion, String valor) throws PersistenceException;
	
	/**
	 * Realiza el registro de un Parametro en caso de no existir, en caso contrario se actualizan sus datos
	 * @param idParametro identificador del parametro
	 * @param valor Valor del parametro
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public void updateOrSaveParametro(long idParametro, String valor) throws PersistenceException;

	/**
	 * Realiza el registro de un Parametro en caso de no existir, en caso contrario se actualizan sus datos 
	 * @throws PersistenceException
	 * @param idParametro identificador del parametro
	 * @param descripcion Descripcion del parametro
	 * @param valor Valor del parametro
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public void updateOrSaveParametro(long idParametro, String descripcion, String valor) throws PersistenceException;
	
	public List<ParametroVO> findAll() throws PersistenceException;

	/**
	 * Consulta el total de ofertas publicadas en el Portal del empleo
	 * @return
	 * @throws PersistenceException
	 */
	public long consultaSumaParametrosOfertas() throws PersistenceException;

	public List<ParametroVO> consultaParametrosOfertasExternas() throws PersistenceException; 
}