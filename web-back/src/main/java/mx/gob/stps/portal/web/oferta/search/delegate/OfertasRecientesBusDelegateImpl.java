package mx.gob.stps.portal.web.oferta.search.delegate;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasRecientesAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaRecienteVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_OFERTAS_RECIENTES;

public final class OfertasRecientesBusDelegateImpl implements OfertasRecientesBusDelegate {

	private static OfertasRecientesBusDelegate instance = new OfertasRecientesBusDelegateImpl();

	private OfertasRecientesBusDelegateImpl() {
	}

	public static OfertasRecientesBusDelegate getInstance() {
		return instance;
	}

	@Override
	public List<OfertaRecienteVO> obtenerOfertasRecientes(int numRegistros)throws SQLException, ServiceLocatorException {
		return getOfertasRecientesAppService().getListaOfertasRecientes();		
	}

	@Override
	public List<OfertaPorCanalVO> obtenerOfertasRecientesTodas(int numRegistros) throws SQLException, ServiceLocatorException{
		return getOfertasRecientesAppService().getListaOfertasRecientesTodas();
	}

	@Override
	public List<OfertaRecienteVO> obtenerOfertasDestacadas(int numRegistros)throws SQLException, ServiceLocatorException {
		return getOfertasRecientesAppService().getListaOfertasDestacadas();
	}

	@Override
	public List<OfertaPorCanalVO> obtenerOfertasDestacadasTodas(int numRegistros)throws SQLException, ServiceLocatorException {
		return getOfertasRecientesAppService().getListaOfertasDestacadasTodas();
	}
	
	/**
	 * Obtiene la referencia a los servicios de ofertas por perfil
	 * 
	 * @return instancia de servcios
	 * @throws ServiceLocatorException
	 *             Lanzada en caso de error en la conexion con los servicios
	 */
	public OfertasRecientesAppServiceRemote getOfertasRecientesAppService()throws ServiceLocatorException {
		OfertasRecientesAppServiceRemote ejb = (OfertasRecientesAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_OFERTAS_RECIENTES);
		return ejb;
	}	

	public void initTimerOfertasRecientesDestacadas() throws SQLException, ServiceLocatorException {
		getOfertasRecientesAppService().initTimerOfertasRecientesDestacadas();
	}

	@Override
	public List<OfertaRecienteVO> obtenerOfertasCanada() throws ServiceLocatorException {
		return getOfertasRecientesAppService().getListaOfertasCanada();
	}

	@Override
	public List<OfertaPorCanalVO> obtenerOfertasCanadaTodas(int numeroRegistrosTodas) throws ServiceLocatorException {
		return getOfertasRecientesAppService().getListaOfertasCanadaTodas();
	}

	@Override
	public List<OfertaRecienteVO> obtenerOfertasGendarmeria(int numRegistros)
			throws SQLException, ServiceLocatorException {
		return getOfertasRecientesAppService().getListaOfertasGendarmeria();
	}

	@Override
	public List<OfertaPorCanalVO> obtenerOfertasGendarmeriaTodas(int numeroRegistrosTodas) throws SQLException, ServiceLocatorException {
		return getOfertasRecientesAppService().getListaOfertasGendarmeriaTodas();
	}
	
}