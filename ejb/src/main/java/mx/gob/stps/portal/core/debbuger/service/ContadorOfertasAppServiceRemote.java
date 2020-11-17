package mx.gob.stps.portal.core.debbuger.service;

import javax.ejb.Remote;

@Remote
public interface ContadorOfertasAppServiceRemote {

	public void iniciaProcesoRecurrente();

	public void detieneProcesoRecurrente();	
}
