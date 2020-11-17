package mx.gob.stps.portal.core.debbuger.service.impl;

import javax.ejb.Local;
import javax.ejb.Timer;

@Local
public interface DepuracionCuentasAppServiceLocal {
	 public void doAction();
	 public void initTimer();
	  public void destroyInitTimer();
}
