package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.EmpresaPortalSisneVO;

@Local
public interface EmpresasPortalSisneFacadeLocal {
	
	public EmpresaPortalSisneVO findById(long idEmpresa) throws PersistenceException;	
	
}
