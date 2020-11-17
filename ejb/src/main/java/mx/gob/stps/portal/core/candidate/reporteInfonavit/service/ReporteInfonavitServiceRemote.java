package mx.gob.stps.portal.core.candidate.reporteInfonavit.service;

import javax.ejb.Remote;

@Remote
public interface ReporteInfonavitServiceRemote {

	public void initTimer();

	public void destroyInitTimer();
}
