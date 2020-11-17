/**
 * 
 */
package mx.gob.stps.portal.web.entrevista.delegate;

import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

/**
 * @author jose.hernandez
 *
 */
public interface EntrevistaBusDelegate {
	
	/** 
	 * @param EntrevistaVO
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public List<EntrevistaVO> getEntrevistaProgramadaCandidato(EntrevistaVO EntrevistaVO) throws BusinessException, ServiceLocatorException;

	/**
	 * @param EntrevistaVO
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public List<EntrevistaVO> getEntrevistaProgramadaEmpresa(EntrevistaVO EntrevistaVO) throws BusinessException, ServiceLocatorException;
	
	
	/**
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public  void  rechazar(EntrevistaVO EntrevistaVO) throws BusinessException, ServiceLocatorException;
	
	/**
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public  void  aceptar(EntrevistaVO EntrevistaVO) throws BusinessException, ServiceLocatorException;
	
	/**
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public  void  cancelar(EntrevistaVO EntrevistaVO) throws BusinessException, ServiceLocatorException;
	
	/**
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public  void  reprogramar(EntrevistaVO EntrevistaVO) throws BusinessException, ServiceLocatorException;


	/**
	 * @param EntrevistaVO
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public List<EntrevistaVO> getEntrevistaProgramadaCandidatoEnLinea(EntrevistaVO EntrevistaVO) throws BusinessException,
			ServiceLocatorException;

	/**
	 * @param EntrevistaVO
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public List<EntrevistaVO> getEntrevistaProgramadaEmpresaEnLinea(EntrevistaVO EntrevistaVO) throws BusinessException,
			ServiceLocatorException;
	
	/** Validando el tiempo de la entrevista
	 * @param idEntrevista
	 * @return
	 */
	public Boolean validarEntrevistaEnLinea(long idEntrevista);


	/**
	 * Método que salva la entrevista el empleado
	 * @param entrevistaVO
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public void save(EntrevistaVO entrevistaVO) throws BusinessException, ServiceLocatorException;

	/**
	 * @param idCandidato
	 * @param idOfertaEmpleo
	 * @return
	 * @throws PersistenceException
	 * @throws ServiceLocatorException 
	 */
	public EntrevistaVO buscaEntrevistaOfertaCandidatoActiva(long idCandidato,	long idOfertaEmpleo) throws PersistenceException, ServiceLocatorException;
	
}

