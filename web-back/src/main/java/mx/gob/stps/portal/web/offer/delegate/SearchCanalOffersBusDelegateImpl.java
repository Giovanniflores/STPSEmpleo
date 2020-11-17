package mx.gob.stps.portal.web.offer.delegate;


import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_OFERTAS_XCANAL;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasPorCanalAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;

import org.apache.log4j.Logger;


/**
 * Implementaci&oacute;n de {@link mx.gob.stps.portal.web.offer.delegate.SearchCanalOffersBusDelegate}
 * @author jose.jimenez
 *
 */
public class SearchCanalOffersBusDelegateImpl implements
		SearchCanalOffersBusDelegate {

	private static Logger logger = Logger.getLogger(SearchCanalOffersBusDelegateImpl.class);
	
	/**
	 * Mantiene la referencia a la &uacute;nica instancia existente de este objeto
	 */
	private static SearchCanalOffersBusDelegate instance = new SearchCanalOffersBusDelegateImpl();
	
	
	/**
	 * Crea una instancia de este objeto.
	 */
	private SearchCanalOffersBusDelegateImpl() {}

	/**
	 * Obtiene una referencia a la &uacute;nica instancia existente de este objeto
	 * @return la referencia a la instancia de este objeto
	 */
	public static SearchCanalOffersBusDelegate getInstance() {
		return instance;
	}
	
	/**
	 * Obtiene una instancia del servicio remoto en la capa de negocio para 
	 * realizar las operaciones necesarias
	 * @return una instancia del servicio remoto
	 * @throws ServiceLocatorException en caso de que no sea posible encontrar la
	 * clase del servicio por JNDI.
	 */
	public OfertasPorCanalAppServiceRemote getOfertaEmpleoAppService() 
			throws ServiceLocatorException {
		
		OfertasPorCanalAppServiceRemote ejb = (OfertasPorCanalAppServiceRemote) 
				ServiceLocator.getSessionRemote(JNDI_EJB_OFERTAS_XCANAL);
		return ejb;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> getOffersPerCanal(String searchType) throws SQLException, TechnicalException, ServiceLocatorException {
		
		List<Long> results = null;
		results = getOfertaEmpleoAppService().obtenerOfertasPorCanal(searchType);
		
		return results;
	}

	@Override
	public List<OfertaPorCanalVO> getOffersPerFilter(long idoffer, int district, String dateInitAdd, String dateFinalAdd, String dateInitUpd,
			String dateFinalUpd, String idPortal, String email, int status, int deleteRazon, 
			String idEmpresa, String contacto, String telefono, String idEntidadSelect, String idMunicipio, String salarioRango, String salario, String titulo) throws TechnicalException, ServiceLocatorException {
		return getOfertaEmpleoAppService().getOffersPerFilter(idoffer, district, dateInitAdd, dateFinalAdd, dateInitUpd, dateFinalUpd, idPortal, email, status, deleteRazon,
				idEmpresa, contacto, telefono, idEntidadSelect, idMunicipio, salarioRango, salario, titulo) ;
	}

	@Override
	public List<BitacoraVO> getBitacora(long idOffer) throws TechnicalException, ServiceLocatorException {
		return getOfertaEmpleoAppService().getBitacora(idOffer);
	}

	@Override
	public List<OfertaPorCanalVO> getOffersPerFilterAdminPublisher(
			long idoffer, String idPortal, String nombreEmpresa) throws ServiceLocatorException {
		return getOfertaEmpleoAppService().getOffersPerFilterAdminPublisher(idoffer, idPortal, nombreEmpresa) ;
	}

	@Override
	public List<Long> ordenarOfertasPorCanal(String tipoOrdenamiento, Integer numeroColumna, String canal) throws TechnicalException, ServiceLocatorException, SQLException {

		return getOfertaEmpleoAppService().ordenarOfertasPorCanal(tipoOrdenamiento, numeroColumna, canal);
	}	
	
}
