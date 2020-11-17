package mx.gob.stps.portal.core.debbuger.service;

import javax.ejb.Remote;

@Remote
public interface DepuracionCuentasAppServiceRemote {
	
	  public void doAction();
	  public void initTimer();
	  public void destroyInitTimer();

}
