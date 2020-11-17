package mx.gob.stps.portal.core.persistencia.facade;
import javax.ejb.Local;

import mx.gob.stps.portal.core.kiosco.vo.KioscoVO;

@Local
public interface KioscoFacadeLocal {

	KioscoVO find(String username);

}
