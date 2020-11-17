package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mx.gob.stps.portal.core.infra.utils.Utils;

@Stateless
public class OLAFacade implements OLAFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Integer> consultaCarrerasOLA(int idCarreraOla){
		
		String sql = "SELECT ID_CARRERA_ESPEC FROM CARRERA_OLA_WS WHERE ID_CARRERA_OLA = ?1";
		
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, idCarreraOla);

		@SuppressWarnings("unchecked")
		List<Object> rowSet = query.getResultList();

		List<Integer> idsCarrera = new ArrayList<Integer>();
		for (Object rs : rowSet) {
			int idCarrera = Utils.toInt(rs);
			idsCarrera.add(idCarrera);
		}

		return idsCarrera;
	}

	public List<Integer> consultaOcupacionesOLA(int idOcupacionOla){
		
		String sql = "SELECT ID_OCUPACION FROM OCUPACION_OLA_WS WHERE ID_OCUPACION_OLA = ?1";
		
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, idOcupacionOla);

		@SuppressWarnings("unchecked")
		List<Object> rowSet = query.getResultList();

		List<Integer> idsOcupacion = new ArrayList<Integer>();
		for (Object rs : rowSet) {
			int idOcupacion = Utils.toInt(rs);
			idsOcupacion.add(idOcupacion);
		}

		return idsOcupacion;
	}
	
}
