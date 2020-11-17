package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.cil.bo.AttentionRequest;

/**
 * Define los métodos para la persistencia de Bitácora Atención
 * 
 *
 */
@Local
public interface BitacoraAtencionFacadeLocal {

	/**
	 * Salva un registro de Bitácora Atención
	 * @param idCil				long		Identificador del CIL que se registra
	 * @param idCandidato		long 		Identificador del candidato que genera el registro
	 * @param idAtencion		long		Identificador de atención que genera el registro
	 * @param idTipoAtencion	long		Identificador de tipo de atención que genera el registro
	 * @param fechaInicio		Calendar	Fecha en que se registra el evento en la bitácora atención
	 * @param detalle			String		Cadena con los parametros más importantes que se modificaron durante el evento
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */	
	public void save(long idCil, long idCandidato, long idAtencion, long idTipoAtencion, Date fechaInicio, String detalle, long contador) throws PersistenceException;

	/**
	 * Actualiza un registro de Bitácora Atención
	 * @param idCil				long		Identificador del CIL que se registra
	 * @param idCandidato		long 		Identificador del candidato que genera el registro
	 * @param idAtencion		long		Identificador de atención que genera el registro
	 * @param idTipoAtencion	long		Identificador de tipo de atención que genera el registro
	 * @param fechaInicio		Calendar	Fecha en que se registra el evento en la bitácora atención
	 * @param detalle			String		Cadena con los parametros más importantes que se modificaron durante el evento
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */	
	public void update(long id_bitacora_atencion, long idCil, long idCandidato, long idAtencion, long idTipoAtencion, Date fechaInicio, String detalle, long contador) throws PersistenceException;
	
	/**
	 * Obtiene un registro de Bitácora Atención
	 * @param idCil				long				Identificador del CIL que se registra
	 * @param idCandidato		long 				Identificador del candidato que genera el registro
	 * @return 					AttentionRequest	Registros que conforman la atención solicitada
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */	
	public AttentionRequest find(long idCil, long idCandidato) throws PersistenceException;
}