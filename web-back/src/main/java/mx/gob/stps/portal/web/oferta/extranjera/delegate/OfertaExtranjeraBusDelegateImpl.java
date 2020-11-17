package mx.gob.stps.portal.web.oferta.extranjera.delegate;

import java.util.List;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaExtranjeraAppServiceRemote;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraDescripcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraPostulacionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraVO;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public class OfertaExtranjeraBusDelegateImpl implements OfertaExtranjeraBusDelegate {
	
	private static OfertaExtranjeraBusDelegate instance = new OfertaExtranjeraBusDelegateImpl();
	
	public OfertaExtranjeraBusDelegateImpl() {
		
	}
	
	public static OfertaExtranjeraBusDelegate getInstance() {
		return instance;
	}

	public static void setInstance(OfertaExtranjeraBusDelegate instance) {
		OfertaExtranjeraBusDelegateImpl.instance = instance;
	}
	
	private OfertaExtranjeraAppServiceRemote getOfertaAppService() throws ServiceLocatorException {
		OfertaExtranjeraAppServiceRemote ejb = (OfertaExtranjeraAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_OFERTA_EXT);
		return ejb;
	}	

	@Override
	public int save(OfertaExtranjeraVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OfertaExtranjeraVO find(long idOfertaExtranjera) throws ServiceLocatorException {
		return getOfertaAppService().find(idOfertaExtranjera);
	}

	@Override
	public int update(OfertaExtranjeraVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OfertaExtranjeraVO> findByPaisEstatus(long idPais,
			ESTATUS estatus) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<OfertaExtranjeraVO> findAll() throws ServiceLocatorException {
		return getOfertaAppService().findAll();
	}

	@Override
	public List<OfertaExtranjeraDescripcionVO> getDescription(long idOfertaExtranjera) throws ServiceLocatorException {
		return getOfertaAppService().getDescription(idOfertaExtranjera);
	}

	@Override
	public int saveMatch(OfertaExtranjeraPostulacionVO vo) throws ServiceLocatorException {
		return getOfertaAppService().saveMatch(vo);
	}

	@Override
	public boolean existOfertaExtranjeraPostulacion(long idCandidato, long idOfertaExtranjera) throws ServiceLocatorException {
		return getOfertaAppService().existOfertaExtranjeraPostulacion(idCandidato, idOfertaExtranjera);
	}
}
