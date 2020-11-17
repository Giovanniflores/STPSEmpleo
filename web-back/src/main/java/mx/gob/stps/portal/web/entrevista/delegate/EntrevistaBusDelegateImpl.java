/**
 * 
 */
package mx.gob.stps.portal.web.entrevista.delegate;

import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.entrevista.service.EntrevistaAppServiceRemote;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;

/**
 * @author jose.hernandez
 *
 */
public class  EntrevistaBusDelegateImpl implements EntrevistaBusDelegate {
	
	
	public static EntrevistaBusDelegate instace =  new EntrevistaBusDelegateImpl();
	
	
	public static EntrevistaBusDelegate getInstance(){
		return instace;
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.entrevista.delegate.EntrevistaBusDelegate#getEntrevistaProgramadaCandidato(mx.gob.stps.portal.core.entrevista.vo.EntrevistaVO)
	 */
	@Override
	public List<EntrevistaVO> getEntrevistaProgramadaCandidato(EntrevistaVO EntrevistaVO) throws BusinessException, ServiceLocatorException {
		return getEntrevistaAppServiceRemote().getEntrevistaProgramadaCandidato(EntrevistaVO);
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.entrevista.delegate.EntrevistaBusDelegate#getEntrevistaProgramadaEmpresa(mx.gob.stps.portal.core.entrevista.vo.EntrevistaVO)
	 */
	@Override
	public List<EntrevistaVO> getEntrevistaProgramadaEmpresa(
			EntrevistaVO EntrevistaVO) throws BusinessException,
			ServiceLocatorException {	
		return getEntrevistaAppServiceRemote().getEntrevistaProgramadaEmpresa(EntrevistaVO);
	}
	
	@Override
	public List<EntrevistaVO> getEntrevistaProgramadaCandidatoEnLinea(
			EntrevistaVO EntrevistaVO) throws BusinessException,
			ServiceLocatorException {		
		return getEntrevistaAppServiceRemote().getEntrevistaProgramadaCandidatoEnLinea(EntrevistaVO);
	}

	@Override
	public List<EntrevistaVO> getEntrevistaProgramadaEmpresaEnLinea(EntrevistaVO EntrevistaVO) throws BusinessException, ServiceLocatorException {
		return getEntrevistaAppServiceRemote().getEntrevistaProgramadaEmpresaEnLinea(EntrevistaVO);
	}
	
	@Override
	public EntrevistaVO buscaEntrevistaOfertaCandidatoActiva(long idCandidato, long idOfertaEmpleo) throws PersistenceException, ServiceLocatorException{	
		return getEntrevistaAppServiceRemote().buscaEntrevistaOfertaCandidatoActiva(idCandidato, idOfertaEmpleo);
	}

	
	/** EntrevistaAppServiceRemote
	 * @return EntrevistaAppServiceRemote
	 * @throws ServiceLocatorException
	 */
	private EntrevistaAppServiceRemote getEntrevistaAppServiceRemote() throws ServiceLocatorException {
		return (EntrevistaAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_ENTREVISTA);		
		
	}

	@Override
	public void rechazar(EntrevistaVO EntrevistaVO) throws BusinessException,
			ServiceLocatorException {
		getEntrevistaAppServiceRemote().rechazar(EntrevistaVO);
		
	}

	@Override
	public void aceptar(EntrevistaVO EntrevistaVO) throws BusinessException,
			ServiceLocatorException {
		getEntrevistaAppServiceRemote().aceptar(EntrevistaVO);
		
	}

	@Override
	public void cancelar(EntrevistaVO EntrevistaVO) throws BusinessException,
			ServiceLocatorException {
		getEntrevistaAppServiceRemote().cancelar(EntrevistaVO);
		
	}

	@Override
	public void reprogramar(EntrevistaVO EntrevistaVO)
			throws BusinessException, ServiceLocatorException {
		getEntrevistaAppServiceRemote().reprogramar(EntrevistaVO);
		
	}


	@Override
	public Boolean validarEntrevistaEnLinea(long idEntrevista) {		
		try {
			return getEntrevistaAppServiceRemote().validarEntrevistaEnLinea(idEntrevista);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void save(EntrevistaVO entrevistaVO)
			throws BusinessException, ServiceLocatorException {
		getEntrevistaAppServiceRemote().save(entrevistaVO);
		
	}
}
