package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;

import mx.gob.stps.portal.persistencia.entity.SalariosVigentes;
import mx.gob.stps.portal.persistencia.facade.TemplateFacade;
import mx.gob.stps.portal.persistencia.vo.SalariosVigentesVO;
import mx.gob.stps.portal.utils.converter.exceptions.NotFoundAnnotationException;

public interface SalariosVigentesFacadeLocal extends TemplateFacade<SalariosVigentes> {

	public List<SalariosVigentesVO> salariosList() throws NoResultException, NonUniqueResultException, QueryTimeoutException, InstantiationException, IllegalAccessException, NoSuchFieldException, NotFoundAnnotationException;
	
	public SalariosVigentesVO salarioVigente() throws PersistenceException;
	
	public long registroSalario(SalariosVigentesVO salario)throws PersistenceException;

}
