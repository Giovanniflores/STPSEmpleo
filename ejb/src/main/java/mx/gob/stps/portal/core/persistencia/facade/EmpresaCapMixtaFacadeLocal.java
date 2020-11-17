package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

@Local
public interface EmpresaCapMixtaFacadeLocal {

	public long save(String texto, Long idEmpresa) throws PersistenceException;
	
	public String obtenerCapacitacionMixta(long idEmpresa) throws PersistenceException;
	
	public Long updateCapacitacionMixta(String texto,Long idEmpresa) throws PersistenceException;
}
