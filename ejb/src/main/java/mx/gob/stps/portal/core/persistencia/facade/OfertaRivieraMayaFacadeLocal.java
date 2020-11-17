package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;
import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;

 @Local
public interface OfertaRivieraMayaFacadeLocal {

	 public List<OfertaRivieraMayaVO> getOfertaRivieraMaya() throws PersistenceException;
}
