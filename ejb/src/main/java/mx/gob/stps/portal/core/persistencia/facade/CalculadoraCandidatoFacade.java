package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.CalculadoraCandidato;

@Stateless
public class CalculadoraCandidatoFacade implements CalculadoraCandidatoFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(long idCandidato, double suma) throws PersistenceException {
		try{
			CalculadoraCandidato entity = new CalculadoraCandidato();
			entity.setIdCandidato(idCandidato);
			entity.setSuma(Utils.toLong(suma));
			entityManager.persist(entity);
		}catch(Exception e){
			throw new PersistenceException(e);
		}
	}
	
	@Override
	public CalculadoraCandidato findCalculadoraCandidato(long idPropietario) {
		CalculadoraCandidato calculadoraCandidato = entityManager.find(CalculadoraCandidato.class, idPropietario);
		return calculadoraCandidato;
	}
	
	@Override
	public void update(long idCandidato, double suma) throws PersistenceException{
		try {
			CalculadoraCandidato entity = new CalculadoraCandidato();
			entity.setIdCandidato(idCandidato);
			entity.setSuma(Utils.toLong(suma));
			entityManager.merge(entity);
		}catch(Exception e){
			throw new PersistenceException(e);
		}
	}
}
