package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;

@Local
public interface OfertaExternaFacadeLocal {

	public Long save(Long idOfertaEmpleo, Long idOfertaBolsa);
}
