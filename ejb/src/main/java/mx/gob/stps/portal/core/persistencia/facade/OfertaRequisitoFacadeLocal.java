package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;

@Local
public interface OfertaRequisitoFacadeLocal {

	public List<OfertaRequisitoVO> ofertaRequisitosList(long idOfertaEmpleo) throws PersistenceException;	
}
