package mx.gob.stps.portal.core.oferta.envioSMS.service;

import javax.ejb.Remote;

@Remote
public interface OfertasSMSAppServiceRemote {
	
	public void initTimer();
	
	public void destroyInitTimer();

	//public void timeout();
}
