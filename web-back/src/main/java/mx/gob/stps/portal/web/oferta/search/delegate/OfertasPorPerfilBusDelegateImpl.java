package mx.gob.stps.portal.web.oferta.search.delegate;

import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_OFERTAS_XPERFIL;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasPorPerfilAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.persistencia.vo.PerfilTipoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 18/03/2011
 **/
public final class OfertasPorPerfilBusDelegateImpl implements OfertasPorPerfilBusDelegate {

	private static OfertasPorPerfilBusDelegate instance = new OfertasPorPerfilBusDelegateImpl();

	private OfertasPorPerfilBusDelegateImpl() {}

	public static OfertasPorPerfilBusDelegate getInstance() {
		return instance;
	}

	@Override
	public List<OfertaPorPerfilVO> obtenerOfertasPorPerfilEmpresa(long idCandidato, long idEmpresa) throws SQLException, ServiceLocatorException {
		return getOfertasPorPerfilAppService().obtenerOfertasPorPerfilEmpresa(idCandidato, idEmpresa);
	}

	@Override
	public List<OfertaPorPerfilVO> obtenerOfertasPorEmpresa(long idEmpresa) throws SQLException, ServiceLocatorException {
		return getOfertasPorPerfilAppService().obtenerOfertasPorEmpresa(idEmpresa);
	}
		
	/**
	 * Obtiene la referencia a los servicios de ofertas por perfil
	 * 
	 * @return instancia de servcios
	 * @throws ServiceLocatorException
	 *             Lanzada en caso de error en la conexion con los servicios
	 */
	private OfertasPorPerfilAppServiceRemote getOfertasPorPerfilAppService()throws ServiceLocatorException {
		OfertasPorPerfilAppServiceRemote ejb = (OfertasPorPerfilAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_OFERTAS_XPERFIL);
		return ejb;
	}
	
	@Override
	public List<PerfilTipoVO> perfilTipoList(long idOcupacion) throws SQLException, ServiceLocatorException {
		return getOfertasPorPerfilAppService().perfilTipoList(idOcupacion);
	}

	@Override
	public PerfilTipoVO find(long idPerfilTipo) throws SQLException, ServiceLocatorException {
		return getOfertasPorPerfilAppService().find(idPerfilTipo);
	}
}