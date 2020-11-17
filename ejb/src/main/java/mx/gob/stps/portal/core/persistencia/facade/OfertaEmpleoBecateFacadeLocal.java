package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoBecateVO;

@Local
public interface OfertaEmpleoBecateFacadeLocal {
	
	public long registrarOferta(OfertaEmpleoBecateVO ofertaEmpleo) throws PersistenceException;
	
	public int update(OfertaEmpleoBecateVO ofertaEmpleo) throws PersistenceException;
	
	public int delete(long idOfertaEmpleo) throws PersistenceException;
	
	public List<OfertaEmpleoBecateVO> getOfertaEmpleoBecate() throws PersistenceException;
	
	public List<OfertaEmpleoBecateVO> findById(long idOferta) throws PersistenceException;
	
	public OfertaEmpleoBecateVO obtenerOfertaById(long idOferta) throws PersistenceException;

	public Boolean esOfertaBecate(long idOfertaEmpleo) throws PersistenceException;
}
