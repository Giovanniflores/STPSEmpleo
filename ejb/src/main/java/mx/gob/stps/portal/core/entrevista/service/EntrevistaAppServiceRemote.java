package mx.gob.stps.portal.core.entrevista.service;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;

/**
 * @author jose.hernandez
 *
 */
@Remote
public interface EntrevistaAppServiceRemote {
	             	
	/** 
	 * @param EntrevistaVO
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public List<EntrevistaVO> getEntrevistaProgramadaCandidato(EntrevistaVO EntrevistaVO)throws PersistenceException;

	/**
	 * @param EntrevistaVO
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public List<EntrevistaVO> getEntrevistaProgramadaEmpresa(EntrevistaVO EntrevistaVO)throws PersistenceException;
	
	
	/**
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public void rechazar(EntrevistaVO EntrevistaVO) throws PersistenceException;
	
	/**
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public void aceptar(EntrevistaVO entrevistaVO) throws PersistenceException;
	
	/**
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public void cancelar(EntrevistaVO EntrevistaVO) throws PersistenceException;
	
	/**
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public void reprogramar(EntrevistaVO EntrevistaVO) throws PersistenceException;

	public void reprogramarEntrevista(EntrevistaVO entrevista) throws PersistenceException;
	
	/**
	 * @param EntrevistaVO
	 * @return
	 * @throws PersistenceException
	 */
	public List<EntrevistaVO> getEntrevistaProgramadaEmpresaEnLinea(EntrevistaVO EntrevistaVO) throws PersistenceException;

	/**
	 * @param EntrevistaVO
	 * @return
	 * @throws PersistenceException
	 */
	public List<EntrevistaVO> getEntrevistaProgramadaCandidatoEnLinea(EntrevistaVO EntrevistaVO) throws PersistenceException;
	
	/**
	 * Método que salva la programación de la entrevista
	 * @param entrevistaVO
	 * @throws PersistenceException
	 */
	public void save(EntrevistaVO entrevistaVO) throws PersistenceException;
	
	/** Entrevista en Linea
	 * @param idEntrevista
	 * @return
	 */
	public Boolean validarEntrevistaEnLinea(long idEntrevista);

	/**
	 * @param idCandidato
	 * @param idOfertaEmpleo
	 * @return
	 * @throws PersistenceException
	 */
	public EntrevistaVO buscaEntrevistaOfertaCandidatoActiva(long idCandidato,long idOfertaEmpleo) throws PersistenceException;

}
