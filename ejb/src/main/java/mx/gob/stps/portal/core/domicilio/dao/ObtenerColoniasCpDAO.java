package mx.gob.stps.portal.core.domicilio.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.domicilio.vo.ColoniaVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 09/03/2011
 **/
public class ObtenerColoniasCpDAO extends TemplateDAO {

	public ObtenerColoniasCpDAO(){
	}

	public ObtenerColoniasCpDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	/**
	 * Obtiene una lista de colonias segun el codigo postal
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 09/03/2011
	 * @param String codPostal
	 * @throws SQLException
	 * @return List<ColoniaVO>
	 **/
	public List<ColoniaVO> obtenerColoniasCp(String codPostal)throws SQLException {

		Object[] parametros = {codPostal};
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<ColoniaVO> rows = new ArrayList<ColoniaVO>();

		while (cachedRowSet.next()) {

			ColoniaVO row = new ColoniaVO();
			row.setIdColonia(cachedRowSet.getLong(1));
			row.setDescColonia(cachedRowSet.getString(2));
			rows.add(row);
		}

		return rows;
	}

	@Override
	protected String getQuery() {
		return "SELECT C.ID_COLONIA, C.COLONIA FROM CODIGO_POSTAL C WHERE CODIGO_POSTAL = ? ORDER BY 2";
	}

}
