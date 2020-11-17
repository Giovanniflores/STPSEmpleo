package mx.gob.stps.portal.movil.app.service.util;

import javax.ejb.Local;

import mx.gob.stps.portal.exception.BusinessException;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.vo.CaptchaPreguntasVO;

@Local
public interface CaptchaPreguntasWSServiceLocal {

	public CaptchaPreguntasVO obtieneDesafioCaptcha() throws BusinessException;
	
}
