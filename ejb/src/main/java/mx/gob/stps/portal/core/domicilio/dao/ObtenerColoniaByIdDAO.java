package mx.gob.stps.portal.core.domicilio.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;


/**
 * Obtiene descripcion de la colonia a partir del identificador.
 * @param idColonia (String)
 * @return String
 */
public class ObtenerColoniaByIdDAO extends TemplateDAO {
	
	public ObtenerColoniaByIdDAO(){}
	
	public ObtenerColoniaByIdDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	public String obtenerColoniaPorId(long idColonia) throws SQLException {
		String strColonia = "";
		Object[] parametros = {idColonia}; 
		CachedRowSet cachedRowSet = executeQuery(parametros);
		
		if (cachedRowSet.next()) {
			
			strColonia = cachedRowSet.getString("colonia");
		}

		return strColonia;
	}	
	
	@Override
	protected String getQuery() {
		
		StringBuilder query = new StringBuilder();
				
		query.append("SELECT colonia FROM codigo_postal WHERE id_colonia =?");		
		return query.toString();
	}	
}
