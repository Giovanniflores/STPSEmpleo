package mx.gob.stps.portal.core.domicilio.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 09/03/2011
 **/
public class ObtenerCodigoPostalDAO extends TemplateDAO {
	
	public ObtenerCodigoPostalDAO(){
	}
	
	public ObtenerCodigoPostalDAO(Connection globalConnection){
		super(globalConnection);
	}

	/**
	 * Obtiene un codigo postal correspondiente a la relacion
	 * Entidad-Municipio-Colonia
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 09/03/2011
	 * @param long entidad, long municipio, long colonia
	 * @throws SQLException
	 * @return String
	 **/
	public String obtenerCodigoPostal(long entidad, long municipio, long colonia)
			throws SQLException {

		Object[] parametros = { entidad, municipio, colonia };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		String rows = null;

		while (cachedRowSet.next()) {

			rows = cachedRowSet.getString(1);

		}

		return rows;
	}

	@Override
	protected String getQuery() {

		StringBuilder query = new StringBuilder();

		query.append("SELECT DISTINCT  C.CODIGO_POSTAL  FROM CODIGO_POSTAL C WHERE ID_ENTIDAD=? AND ID_MUNICIPIO=? AND ID_COLONIA=?");
		return query.toString();
	}

}
