package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaVerDetalleVO;

@Local
public interface OfertaVerDetalleFacadeLocal {
	
	public int create(OfertaVerDetalleVO vo) throws PersistenceException;
	
	public OfertaVerDetalleVO findByPK(long idOfertaVerDetalle, int anio, int mes) throws PersistenceException;
	
	public void update(OfertaVerDetalleVO vo) throws PersistenceException;
	
	public void remove(long idOfertaVerDetalle) throws PersistenceException;
}