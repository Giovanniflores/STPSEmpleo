package mx.gob.stps.portal.core.debbuger.service;

import javax.ejb.Remote;

@Remote
public interface DepuracionCandidatosAppServiceRemote {
	
	public void iniciaProcesoRecurrente();

	public void detieneProcesoRecurrente();	

}
