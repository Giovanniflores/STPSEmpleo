package mx.gob.stps.portal.core.persistencia.facade;
import javax.ejb.Local;

import mx.gob.stps.portal.core.kiosco.vo.KioscoRegistroVO;

@Local
public interface KioscoRegistroFacadeLocal {

	void save(KioscoRegistroVO vo);

}
