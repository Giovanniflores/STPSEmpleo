package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
import mx.gob.stps.portal.persistencia.entity.OfertaPregunta;

public class OfertRivieraMayaFacade implements OfertaRivieraMayaFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger = Logger.getLogger(OfertRivieraMayaFacade.class);
	
	@Override
	public List<OfertaRivieraMayaVO> getOfertaRivieraMaya(){
	List<OfertaRivieraMayaVO> list = new ArrayList<OfertaRivieraMayaVO>();
	StringBuilder sb = new StringBuilder();
	sb.append("SELECT c FROM OfertaPregunta c WHERE c.idOfertaEmpleo=:idOfertaEmpleo");
	try{
		Query query = entityManager.createQuery(sb.toString());
		
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for(Object resultElement : result){
			/*OfertaPreguntaVO vo = getOfertaPreguntaVO((OfertaPregunta)resultElement);			
			list.add(vo);*/
		}			
	}catch(NoResultException e){
		logger.error("No se localizaron preguntas de la oferta");
	}catch(Exception e){
		e.printStackTrace(); logger.error(e);
		throw new PersistenceException(e);
	}
	
	return list;
	}

}
