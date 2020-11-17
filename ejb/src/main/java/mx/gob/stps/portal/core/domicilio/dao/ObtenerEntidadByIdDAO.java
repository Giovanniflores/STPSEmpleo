package mx.gob.stps.portal.core.domicilio.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 09/03/2011
 **/
public class ObtenerEntidadByIdDAO extends TemplateDAO {

	public ObtenerEntidadByIdDAO(){}
	
	public ObtenerEntidadByIdDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	/**
	 * Obtiene descripcion de una entidad
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 09/03/2011
	 * @param long catalogo, long entidad
	 * @throws SQLException
	 * @return List<CatalogoOpcionVO>
	 **/
	public List<CatalogoOpcionVO> obtenerEntidadPorId(long catalogo,long entidad) throws SQLException {
		List<CatalogoOpcionVO> rows = new ArrayList<CatalogoOpcionVO>();

		CachedRowSet cachedRowSet = executeQuery(new Object[]{catalogo, entidad});
		while (cachedRowSet.next()) {
			CatalogoOpcionVO row = new CatalogoOpcionVO();

			row.setIdCatalogoOpcion(cachedRowSet.getLong(1));
			row.setIdCatalogo(cachedRowSet.getLong(2));
			row.setOpcion(cachedRowSet.getString(3));
			row.setFechaAlta(new Date());
			row.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
			row.setFechaModificacion(new Date());
			rows.add(row);
		}

		return rows;
	}

	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT B.ID_CATALOGO_OPCION, ");
		query.append("       B.ID_CATALOGO, ");
		query.append("       B.OPCION ");
		query.append("  FROM CATALOGO_OPCION B ");
		query.append(" WHERE B.ID_CATALOGO = ? ");
		query.append("   AND B.ID_CATALOGO_OPCION = ? ");
		return query.toString();
	}
}