package mx.gob.stps.portal.web.offer.delegate;

import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_RESUMEN_RECIENTES;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.core.oferta.busqueda.service.ResumenRecientesAppServiceRemote;
import mx.gob.stps.portal.core.oferta.vo.MiOfertaRecienteVO;
import mx.gob.stps.portal.core.oferta.vo.PostulacionRecienteVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;

import org.apache.log4j.Logger;

/**
 * Implementaci&oacute;n de {@link mx.gob.stps.portal.web.offer.delegate.RecentOfferSummaryBusDelegate}
 * @author jose.jimenez
 *
 */
public class RecentOfferSummaryBusDelegateImpl implements RecentOfferSummaryBusDelegate {

	private static Logger logger = Logger.getLogger(RecentOfferSummaryBusDelegateImpl.class);

	/**
	 * Mantiene la referencia a la &uacute;nica instancia existente de este objeto
	 */
	private static RecentOfferSummaryBusDelegate instance = new RecentOfferSummaryBusDelegateImpl();
	
	
	/**
	 * Crea una instancia de este objeto.
	 */
	private RecentOfferSummaryBusDelegateImpl() {}
	
	/**
	 * Obtiene una referencia a la &uacute;nica instancia existente de este objeto
	 * @return la referencia a la instancia de este objeto
	 */
	public static RecentOfferSummaryBusDelegate getInstance() {
		return instance;
	}
	
	/**
	 * Obtiene una instancia del servicio remoto en la capa de negocio para 
	 * realizar las operaciones necesarias
	 * @return una instancia del servicio remoto
	 * @throws ServiceLocatorException en caso de que no sea posible encontrar la
	 * clase del servicio por JNDI.
	 */
	public ResumenRecientesAppServiceRemote getResumenRecientesAppService() throws ServiceLocatorException {		
		ResumenRecientesAppServiceRemote ejb = (ResumenRecientesAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_RESUMEN_RECIENTES);
		return ejb;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MiOfertaRecienteVO> getMyRecentOffers(long idEmpresa) {
		List<MiOfertaRecienteVO> results = null;

		try {
			results = getResumenRecientesAppService().obtenerMisOfertasRecientes(idEmpresa);
		} catch (ServiceLocatorException sle) {
			logger.error("No se pudo obtener servicio de Resumen Ofertas", sle);
		} catch (SQLException sqle) {
			logger.error("Error de SQL al obtener Resumen de ofertas recientes", sqle);
		}
		
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PostulacionRecienteVO> getRecentPostulations(long idEmpresa) {
		List<PostulacionRecienteVO> results = null;

		try {
			results = this.getResumenRecientesAppService().obtenerPostulacionesRecientes(idEmpresa);
		} catch (ServiceLocatorException sle) {
			logger.error("No se pudo obtener servicio de Resumen Ofertas", sle);
		} catch (SQLException sqle) {
			logger.error("Error de SQL al obtener Resumen de postulaciones recientes", sqle);
		}

		return results;
	}
	
	@Override
	public Long getCountOfertasActivas(long idEmpresa) {
		Long count = null;
		try{
			count = this.getResumenRecientesAppService().getCountOfertasActivas(idEmpresa);
		}catch (ServiceLocatorException sle) {
			logger.error("No se pudo obtener servicio de Resumen Ofertas", sle);
		} catch (SQLException sqle) {
			logger.error("Error de SQL al obtener Resumen de postulaciones recientes", sqle);
		}
		return count;
	}

}
