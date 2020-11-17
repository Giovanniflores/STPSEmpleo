package mx.gob.stps.portal.core.debbuger.service;

import javax.ejb.Remote;

@Remote
public interface DepuracionOfertasAppServiceRemote {
	  public void initTimer();
	  public void destroyInitTimer();
}
