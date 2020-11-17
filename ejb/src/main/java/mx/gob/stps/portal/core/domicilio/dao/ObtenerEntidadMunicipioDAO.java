package mx.gob.stps.portal.core.domicilio.dao;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 09/03/2011
 **/
public class ObtenerEntidadMunicipioDAO extends TemplateDAO {

	/**
	 * Obtiene los id entidad y municipio segun 
	 * el cosigo postal
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 09/03/2011
	 * @param String codigoPostal
	 * @throws SQLException
	 * @return CodigoPostalVO
	 **/
	public CodigoPostalVO obtenerEntidadMunicipioCp(String codigoPostal) throws SQLException {
		CodigoPostalVO rows = new CodigoPostalVO();

		CachedRowSet cachedRowSet = executeQuery(new Object[]{codigoPostal});
		while (cachedRowSet.next()) {
			CodigoPostalVO row = new CodigoPostalVO();
			row.setIdEntidad(cachedRowSet.getLong(1));
			row.setIdMunicipio(cachedRowSet.getLong(2));
			rows = row;
		}

		return rows;
	}

	@Override
	protected String getQuery() {
		return "SELECT DISTINCT C.ID_ENTIDAD, C.ID_MUNICIPIO FROM CODIGO_POSTAL C WHERE CODIGO_POSTAL = ?";
	}
}