package mx.gob.stps.portal.web.ofertasSMS.delegate;

import mx.gob.stps.portal.core.oferta.envioSMS.service.OfertasSMSAppServiceRemote;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_OFERTAS_SMS;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;

public class OfertasSMSBusImpl implements OfertasSMSBusDelegate{
	
	private static OfertasSMSBusDelegate instance = new OfertasSMSBusImpl();

	public static OfertasSMSBusDelegate getInstance() {
		return instance;
	}

	private OfertasSMSAppServiceRemote getOfertasSMSService() throws ServiceLocatorException{
		OfertasSMSAppServiceRemote ejb = (OfertasSMSAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_OFERTAS_SMS);
		return ejb;		
	}
	
	public void iniciaOfertasSMS() throws ServiceLocatorException{
		getOfertasSMSService().initTimer();
	}

	@Override
	public void detieneOfertasSMS() throws ServiceLocatorException {
		getOfertasSMSService().destroyInitTimer();
	}
}