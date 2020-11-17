package mx.gob.stps.portal.web.ofertasSMS.delegate;


import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public interface OfertasSMSBusDelegate {
	
	public void iniciaOfertasSMS() throws ServiceLocatorException;

	public void detieneOfertasSMS() throws ServiceLocatorException;

}
