package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;

@Local
public interface OfertaIdiomaFacadeLocal {
	
	public List<OfertaIdiomaVO> ofertaIdiomasList(long idOfertaEmpleo) throws PersistenceException;
}
