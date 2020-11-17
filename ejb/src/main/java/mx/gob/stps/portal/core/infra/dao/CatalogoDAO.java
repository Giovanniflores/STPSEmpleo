/**
 * 
 */
package mx.gob.stps.portal.core.infra.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

import org.apache.log4j.Logger;

/**
 * @author Felipe Júarez Ramírez
 * @since 25/03/2011
 * 
 */
public class CatalogoDAO extends TemplateDAO {

	public CatalogoDAO(){}
	
	public CatalogoDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	private static Logger logger = Logger.getLogger(CatalogoDAO.class);

	private boolean orden;

	/**
	 * Consulta un catalogo asociado buscando que las 2 primeras posiciones
	 * coincidan con la clave del catalogo padre. Se utilizo para los catalogos
	 * Area laboral y Ocupacion inicialmente. 
	 * Soporta ordenamiento ascendente por id o descripcion.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @param String
	 * @param boolean
	 * @throws SQLException
	 * @return PerfilVO
	 **/
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, String idCatPadre, boolean orderBy) throws SQLException {
		orden = orderBy;

		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
		
		try {
			CachedRowSet cachedRowSet = executeQuery(new Object[]{idCatalogo, idCatPadre});

			while (cachedRowSet.next()) {
				CatalogoOpcionVO opcion = new CatalogoOpcionVO();
				opcion.setIdCatalogoOpcion(cachedRowSet.getLong(1));
				opcion.setIdCatalogo(cachedRowSet.getLong(2));
				opcion.setOpcion(cachedRowSet.getString(3));
				opcion.setFechaAlta(cachedRowSet.getDate(4));
				opcion.setEstatus(cachedRowSet.getInt(5));
				opcion.setFechaModificacion(cachedRowSet.getDate(6));
				opcion.setIdCatalagoAsociado(cachedRowSet.getLong(7));
				opciones.add(opcion);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}

		return opciones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.core.infra.data.TemplateDAO#getQuery()
	 */
	@Override
	protected String getQuery() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT co.id_catalogo_opcion, ");
		sqlString.append("co.id_catalogo, ");
		sqlString.append("co.opcion, ");
		sqlString.append("co.fecha_alta, ");
		sqlString.append("co.estatus, ");
		sqlString.append("co.fecha_modificacion, ");
		sqlString.append("co.id_catalago_asociado ");
		sqlString.append("FROM CATALOGO_OPCION co ");
		sqlString.append("WHERE co.id_catalogo = ? ");
		sqlString.append("AND to_char(co.id_catalogo_opcion) LIKE ? ");
		sqlString.append("ORDER BY ");

		if (orden)
			sqlString.append("co.opcion");
		else
			sqlString.append("co.id_catalogo_opcion");

		return sqlString.toString();
	}

}
