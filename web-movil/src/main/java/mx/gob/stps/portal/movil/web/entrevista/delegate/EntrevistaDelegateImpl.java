package mx.gob.stps.portal.movil.web.entrevista.delegate;

import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.entrevista.service.EntrevistaAppServiceRemote;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI;

public class EntrevistaDelegateImpl {
	
	private static EntrevistaDelegateImpl instance = new EntrevistaDelegateImpl();

	public static EntrevistaDelegateImpl getInstance() {
		return instance;
	}
	
	private EntrevistaAppServiceRemote getEntrevistaAppServiceRemote() throws ServiceLocatorException {
		return (EntrevistaAppServiceRemote)ServiceLocator.getSessionRemote(ConstantesJNDI.JNDI_EJB_ENTREVISTA);		
		
	}

	public List<EntrevistaVO> getEntrevistaProgramadaEmpresa(EntrevistaVO entrevistaVO) throws PersistenceException, ServiceLocatorException {
		return getEntrevistaAppServiceRemote().getEntrevistaProgramadaEmpresa(entrevistaVO);
	}

	public void aceptarEntrevista(EntrevistaVO entrevistaVo) throws PersistenceException, ServiceLocatorException {
		getEntrevistaAppServiceRemote().aceptar(entrevistaVo);
	}

	public void cancelarEntrevista(EntrevistaVO entrevistaVo) throws PersistenceException, ServiceLocatorException {
		getEntrevistaAppServiceRemote().cancelar(entrevistaVo);
	}

	public void rechazarEntrevista(EntrevistaVO entrevistaVo) throws PersistenceException, ServiceLocatorException {
		getEntrevistaAppServiceRemote().rechazar(entrevistaVo);		
	}

	public void reprogramarEntrevista(EntrevistaVO entrevista) throws PersistenceException, ServiceLocatorException {
		getEntrevistaAppServiceRemote().reprogramarEntrevista(entrevista);
	}

	public List<EntrevistaVO> getEntrevistaProgramadaCandidatoEnLinea(EntrevistaVO EntrevistaVO) throws BusinessException, ServiceLocatorException {
		return getEntrevistaAppServiceRemote().getEntrevistaProgramadaCandidatoEnLinea(EntrevistaVO);
	}

	public List<EntrevistaVO> getEntrevistaProgramadaCandidato(EntrevistaVO entrevistaVo) throws PersistenceException, ServiceLocatorException {
		return getEntrevistaAppServiceRemote().getEntrevistaProgramadaCandidato(entrevistaVo);
	}

	public EntrevistaVO buscaEntrevistaOfertaCandidatoActiva(long idCandidato,
			long idOferta) throws PersistenceException, ServiceLocatorException {
		return getEntrevistaAppServiceRemote().buscaEntrevistaOfertaCandidatoActiva(idCandidato, idOferta);
	}

	public void save(EntrevistaVO entrevistaVo) throws PersistenceException, ServiceLocatorException {
		getEntrevistaAppServiceRemote().save(entrevistaVo);
		
	}
	
	
	

	
	
	

}
