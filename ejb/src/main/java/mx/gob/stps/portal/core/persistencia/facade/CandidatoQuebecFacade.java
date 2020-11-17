package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.persistencia.entity.CandidatoQuebec;
import mx.gob.stps.portal.persistencia.vo.CandidatoQuebecVO;

@Stateless
public class CandidatoQuebecFacade implements CandidatoQuebecFacadeLocal {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public CandidatoQuebecFacade() {}

	@Override
	public int persistCandidatoQuebec(long idCandidato) throws PersistenceException {
		try {
			 CandidatoQuebec entity = entityManager.find(CandidatoQuebec.class, idCandidato);
			 if (null == entity) {
				 entity = new CandidatoQuebec();
				 entity.setIdCandidato(idCandidato);
				 entity.setFechaRegistro(new Date());
				 entityManager.persist(entity);
				 return 1;
			 }else return 0;
		}catch (RuntimeException re) {
			re.printStackTrace();
			return -1;
		}
	}
	
	public CandidatoQuebecVO findByID(long idCandidato) throws PersistenceException {
		try {
			 CandidatoQuebec entity = entityManager.find(CandidatoQuebec.class, idCandidato);
			 if (null != entity) {
				 CandidatoQuebecVO vo = new CandidatoQuebecVO();
				 vo.setIdCandidato(idCandidato);
				 vo.setFechaRegistro(entity.getFechaRegistro());
				 return vo;
			 }else return null;
		}catch (RuntimeException re) {
			re.printStackTrace();
			return null;
		}
	}
}