package mx.gob.stps.portal.core.persistencia.facade;

import java.io.Serializable;


import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.oferta.vo.OfertaUbicacionVO;
import mx.gob.stps.portal.core.oferta.vo.UbicacionCanadaVO;
@Local
public interface OfertaUbicacionFacadeLocal extends Serializable {

	public long save(OfertaUbicacionVO vo) throws PersistenceException;

	public UbicacionCanadaVO getUbicacionOfertaCanada(long idOfertaEmpleo);

	public OfertaUbicacionVO finfByIdOfertaEmpleo(long idOfertaEmpleo);
	
	public long update(OfertaUbicacionVO vo, Long idOfertaEmpleo) throws PersistenceException;
}
