package mx.gob.stps.portal.core.domicilio.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.domicilio.vo.ColoniaVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 09/03/2011
 **/
public class ObtenerColoniasDAO extends TemplateDAO {

	public ObtenerColoniasDAO(){}

	public ObtenerColoniasDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	/**
	 * Obtiene una lista de colonias segun la relacion Entidad-Municipio
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 09/03/2011
	 * @param long entidad, long municipio
	 * @throws SQLException
	 * @return List<ColoniaVO>
	 **/
	public List<ColoniaVO> obtenerColonias(long entidad, long municipio)throws SQLException {
		List<ColoniaVO> rows = new ArrayList<ColoniaVO>();

		CachedRowSet cachedRowSet = executeQuery(new Object[]{entidad, municipio});
		while (cachedRowSet.next()) {
			ColoniaVO row = new ColoniaVO();
			row.setIdColonia(cachedRowSet.getLong(1));
			row.setDescColonia(cachedRowSet.getString(2));
			rows.add(row);
		}

		return rows;
	}

	public List<CatalogoOpcionVO> consultaColonias(long entidad, long municipio)throws SQLException {
		List<CatalogoOpcionVO> rows = new ArrayList<CatalogoOpcionVO>();

		CachedRowSet cachedRowSet = executeQuery(new Object[]{entidad, municipio});
		while (cachedRowSet.next()) {
			CatalogoOpcionVO row = new CatalogoOpcionVO();
			row.setIdCatalogoOpcion(cachedRowSet.getLong(1));
			row.setOpcion(cachedRowSet.getString(2));
			rows.add(row);
		}

		return rows;
	}

	
	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT C.ID_COLONIA, C.COLONIA  FROM CODIGO_POSTAL C WHERE ID_ENTIDAD = ? AND ID_MUNICIPIO = ? ORDER BY 2");
		return query.toString();
	}
}