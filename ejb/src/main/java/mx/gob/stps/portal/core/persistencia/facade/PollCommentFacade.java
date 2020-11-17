package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.persistencia.entity.PollComment;

import org.apache.log4j.Logger;

@Stateless
public class PollCommentFacade implements PollCommentFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger = Logger.getLogger(PollCommentFacade.class);
	
	
	public long create(int item1, int item2, int item3, int item4, int item5,
			int item6, int item7, int item8, int item9, String description1,
			String description2, String description3, String description4,
			String description5) throws PersistenceException {
		int result = 1;
		java.util.Date fechaAlta = new Date();
		try{
			PollComment entity = getEntity(fechaAlta, item1, item2, item3, item4, item5, item6, 
					item7, item8, item9, description1, description2, description3, description4,
					description5);
			entityManager.persist(entity);
		}catch (Exception e) { result = -1; logger.error("Error al persistir pollcomment", e); }		
		return result;
	}
	
	private PollComment getEntity(Date fechaAlta, int item1, int item2, int item3, int item4, 
			int item5, int item6, int item7, int item8, int item9, String description1,
			String description2, String description3, String description4, String description5){
		PollComment entity = new PollComment();
		entity.setFechaAlta(fechaAlta);
		entity.setItem1(item1);
		entity.setItem2(item2);
		entity.setItem3(item3);
		entity.setItem4(item4);
		entity.setItem5(item5);
		entity.setItem6(item6);
		entity.setItem7(item7);
		entity.setItem8(item8);
		entity.setItem9(item9);
		entity.setDescription1(description1);
		entity.setDescription2(description2);
		entity.setDescription3(description3);
		entity.setDescription4(description4);
		entity.setDescription5(description5);
		return entity;
	}

}
