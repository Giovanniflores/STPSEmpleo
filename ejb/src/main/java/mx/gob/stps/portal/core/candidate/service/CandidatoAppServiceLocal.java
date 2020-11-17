package mx.gob.stps.portal.core.candidate.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;

@Local
public interface CandidatoAppServiceLocal {
	
	public void notificaCandidato(CandidatoVo candidatoVO) 
	throws PersistenceException, BusinessException, TechnicalException, MailException;
	
	
	/**
	 * Notifica a candidato que su cuenta de email ha sido modificada
	 * @param candidatoVo
	 * @param idUsuario
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @throws TechnicalException
	 * @throws MailException
	 */
	public void notificaCandidato(CandidatoVo candidatoVo, long idUsuarioAdmin, String emailanterior)
	throws PersistenceException, BusinessException, TechnicalException,
	MailException;
	
	
	/**
	 * Notifica a candidato cuando se esta registrando
	 * @param candidatoVo
	 * @param idUsuarioAdmin
	 * @param emailanterior
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @throws TechnicalException
	 * @throws MailException
	 */
	
	public void notificaCandidatoInactivaRegistrando(CandidatoVo candidatoVo, long idUsuarioAdmin)
	throws PersistenceException, BusinessException, TechnicalException,
	MailException;
	
	
	/**
	 * Notifica a candidato cuando se esta actualizando
	 * @param candidatoVo
	 * @param idUsuarioAdmin
	 * @param emailanterior
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @throws TechnicalException
	 * @throws MailException
	 */
	public void notificaCandidatoInactivaActualizando(CandidatoVo candidatoVo, long idUsuarioAdmin, String emailanterior)
	throws PersistenceException, BusinessException, TechnicalException,
	MailException;
	
	
	
	/**
	 * @param candidato
	 * @param correoElectronico
	 * @throws TechnicalException
	 * @throws MailException
	 */
	public void notificaCambioContrasena(CandidatoVo candidato, String correoElectronico) throws TechnicalException, MailException;


	/**
	 * @param candidatoVo
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @throws TechnicalException
	 * @throws MailException
	 */
	//void notificaPasswordCandidato(CandidatoVo candidatoVo)	throws PersistenceException, BusinessException, TechnicalException,	MailException;	
	
	/**
	 * Genera un reporte con todos los Candidatos 
	 * que se registraron desde una liga en el portal de INFONAVIT
	 * y tienen una Nunero de seguro social o credito INFONAVIT registrado
	 * 
	 * @throws PersistenceException
	 */
	public List<CandidatoVo> consultaCandidatoInfonavit() throws PersistenceException;
	
	/* CAMBIO EN PROCESO */	
	public int consultarEstatus(long idCandidato);	
	public Integer consultarPpcEstatus(long idCandidato);
	public void desactivarCandidato(long idCandidato, long idUsuario);	
	public void desactivarCandidato(long idCandidato, long idUsuario, int idMotivoDesactivacion, String detalleDesactivacion);
	public void reactivarCandidato(long idCandidato, long idUsuario);	
	public String obtenerLoginUsuario(long idCandidato);
	public List<Long> consultaIdCandidatosFueraDeVigencia() throws SQLException;
	public List<CandidatoVo> consultaCandidatosAvisoDeVigencia() throws SQLException;
	public void notificaCandidatoFueraDeVigencia(CandidatoVo candidatoVo)throws PersistenceException, BusinessException, TechnicalException,MailException;

	public int actualizaRegistroPPC(long idCandidato, Date ppcFechaInscripcion, int ppcAceptacionTerminos, Integer ppcEstatusIMSS, Date ppcFechaBajaIMSS, String ppcTipoContratoIMSS, String nss) throws IllegalArgumentException, BusinessException;
}


