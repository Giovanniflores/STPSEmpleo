package mx.gob.stps.portal.core.debbuger.service.impl;

import javax.ejb.Local;

@Local
public interface DepuracionOfertasAppServiceLocal {
	 public void initTimer();
	  public void destroyInitTimer();
}
