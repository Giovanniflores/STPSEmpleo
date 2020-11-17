package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.cil.bo.AttentionRequest;

/**
 * Define los m�todos para la persistencia de Bit�cora Atenci�n
 * 
 *
 */
@Local
public interface BitacoraAtencionFacadeLocal {

	/**
	 * Salva un registro de Bit�cora Atenci�n
	 * @param idCil				long		Identificador del CIL que se registra
	 * @param idCandidato		long 		Identificador del candidato que genera el registro
	 * @param idAtencion		long		Identificador de atenci�n que genera el registro
	 * @param idTipoAtencion	long		Identificador de tipo de atenci�n que genera el registro
	 * @param fechaInicio		Calendar	Fecha en que se registra el evento en la bit�cora atenci�n
	 * @param detalle			String		Cadena con los parametros m�s importantes que se modificaron durante el evento
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */	
	public void save(long idCil, long idCandidato, long idAtencion, long idTipoAtencion, Date fechaInicio, String detalle, long contador) throws PersistenceException;

	/**
	 * Actualiza un registro de Bit�cora Atenci�n
	 * @param idCil				long		Identificador del CIL que se registra
	 * @param idCandidato		long 		Identificador del candidato que genera el registro
	 * @param idAtencion		long		Identificador de atenci�n que genera el registro
	 * @param idTipoAtencion	long		Identificador de tipo de atenci�n que genera el registro
	 * @param fechaInicio		Calendar	Fecha en que se registra el evento en la bit�cora atenci�n
	 * @param detalle			String		Cadena con los parametros m�s importantes que se modificaron durante el evento
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */	
	public void update(long id_bitacora_atencion, long idCil, long idCandidato, long idAtencion, long idTipoAtencion, Date fechaInicio, String detalle, long contador) throws PersistenceException;
	
	/**
	 * Obtiene un registro de Bit�cora Atenci�n
	 * @param idCil				long				Identificador del CIL que se registra
	 * @param idCandidato		long 				Identificador del candidato que genera el registro
	 * @return 					AttentionRequest	Registros que conforman la atenci�n solicitada
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */	
	public AttentionRequest find(long idCil, long idCandidato) throws PersistenceException;
}