package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.seguridad.vo.PerfilVO;

@Local
public interface PerfilFacadeLocal {

	public long save(PerfilVO vo) throws PersistenceException;
	
	public PerfilVO findById(long id) throws PersistenceException;
	
	public void actualizaEstatus(long idPerfil, int estatus) throws PersistenceException;
	
	public List<PerfilVO> findAll() throws PersistenceException;	
}
