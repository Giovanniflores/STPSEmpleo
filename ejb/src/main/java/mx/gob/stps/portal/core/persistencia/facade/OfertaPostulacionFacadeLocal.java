package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaPostulacionVO;

@Local
public interface OfertaPostulacionFacadeLocal {
	
	public int create(OfertaPostulacionVO vo) throws PersistenceException;
	
	public OfertaPostulacionVO findByPK(long idOfertaEmpleo, int anio, int mes) throws PersistenceException;
	
	public void update(OfertaPostulacionVO vo) throws PersistenceException;
	
	public void remove(long idOfertaPostulacion) throws PersistenceException;

}
