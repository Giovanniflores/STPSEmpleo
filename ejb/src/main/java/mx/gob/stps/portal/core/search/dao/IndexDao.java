package mx.gob.stps.portal.core.search.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;

import org.apache.log4j.Logger;

//TODO ELIMINAR ESTA CLASE, LA CONSULTA SE PASO A CandidatoFacade y OfertaFacade
public class IndexDao extends TemplateDAO {

	private static Logger logger = Logger.getLogger(IndexDao.class);
	private int QUERY = 0;
	private int INDEX_CANDIDATES = 1;
	private int INDEX_OFFERS = 2;
	private int OFFERS_TO_INDEX = 3;
	private int CANDIDATES_TO_INDEX = 4;

	private IndexDao(Connection globalConnection){super(globalConnection);}

	private static IndexDao getInstance(Connection globalConnection){
		return new IndexDao(globalConnection);
	}
	
	public List<Long> candidates(int lowerLimit, int upperLimit) {
		QUERY = INDEX_CANDIDATES;
		List<Long> ids = new ArrayList<Long>();
		Object[] parametros = { lowerLimit, upperLimit };
		try {
			CachedRowSet cachedRowSet = executeQuery(parametros);
			while (cachedRowSet.next()) {
				ids.add(cachedRowSet.getLong(1));
			}
		} catch (SQLException sql) {
			logger.error("Error al obtener candidatos", sql);
		}
		return ids;
	}
	
	public List<Long> offers(int lowerLimit, int upperLimit) {
		QUERY = INDEX_OFFERS;
		Object[] parametros = { lowerLimit, upperLimit };
		List<Long> ids = new ArrayList<Long>();
		try {
			CachedRowSet cachedRowSet = executeQuery(parametros);
			while (cachedRowSet.next()) {
				ids.add(cachedRowSet.getLong(1));
			}
		} catch (SQLException sql) {
			logger.error("Error al obtener ofertas", sql);
		}
		return ids;
	}
	
	public int offersToIndex() {
		int offers = 0;
		QUERY = OFFERS_TO_INDEX;
		try {
			CachedRowSet cachedRowSet = executeQuery();
			while (cachedRowSet.next()) {
				offers = cachedRowSet.getInt(1);
			}
		} catch (SQLException sql) {
			logger.error("Error al obtener ofertas", sql);
		}
		return offers;
	}
	
	public int candidatesToIndex() {
		int candidates = 0;
		QUERY = CANDIDATES_TO_INDEX;
		try {
			CachedRowSet cachedRowSet = executeQuery();
			while (cachedRowSet.next()) {
				candidates = cachedRowSet.getInt(1);
			}
		} catch (SQLException sql) {
			logger.error("Error al obtener candidatos", sql);
		}
		return candidates;
	}
	
	@Override
	protected String getQuery() {
		if (QUERY == INDEX_CANDIDATES)
			return "SELECT id_candidato FROM (SELECT ROWNUM r, id_candidato FROM CANDIDATO WHERE estatus=1) WHERE r > ? AND r <= ?";
		else if (QUERY == INDEX_OFFERS)
			return "SELECT id_oferta_empleo FROM (SELECT ROWNUM r, id_oferta_empleo FROM OFERTA_EMPLEO WHERE estatus=1) WHERE r > ? AND r <= ?";
		else if (QUERY == OFFERS_TO_INDEX)
			return "SELECT count(id_oferta_empleo) FROM OFERTA_EMPLEO WHERE estatus=1";
		else if (QUERY == CANDIDATES_TO_INDEX)
			return "SELECT count(id_candidato) FROM CANDIDATO WHERE estatus=1";
		return "";
	}
}