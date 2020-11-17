package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;





import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.QueryTimeoutException;

import mx.gob.stps.portal.exception.BusinessException;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.entity.SalariosAplicaciones;
import mx.gob.stps.portal.persistencia.facade.TemplateFacade;
import mx.gob.stps.portal.persistencia.vo.SalariosAplicacionesVO;
import mx.gob.stps.portal.utils.converter.exceptions.NotFoundAnnotationException;

public interface SalariosAplicacionesFacadeLocal extends TemplateFacade<SalariosAplicaciones> {

	public List<SalariosAplicacionesVO> salariosAplicacionesList()
			throws NoResultException, NonUniqueResultException, QueryTimeoutException, InstantiationException, IllegalAccessException, NoSuchFieldException, NotFoundAnnotationException;
	
	public void actualizaSalariosApli(SalariosAplicacionesVO salariosApli)throws BusinessException, TechnicalException;	

}
