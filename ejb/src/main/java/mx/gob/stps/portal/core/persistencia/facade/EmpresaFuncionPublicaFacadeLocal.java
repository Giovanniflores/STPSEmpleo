package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.EmpresaFPVO;

@Local
public interface EmpresaFuncionPublicaFacadeLocal {

	public List<EmpresaFPVO> getAllRows() throws PersistenceException;
	
	public boolean esEmpresaFuncionPublica(long idEmpresa) throws PersistenceException;
}
