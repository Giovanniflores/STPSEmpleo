package mx.gob.stps.portal.core.domicilio.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

public class MunicipioDAO extends TemplateDAO {
	
	public MunicipioDAO(){
	}
	
	public MunicipioDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	public List<CatalogoOpcionVO> consultaMunicipios(long idEntidad) throws SQLException {
		List<CatalogoOpcionVO> municipios = new ArrayList<CatalogoOpcionVO> ();
		
		CachedRowSet cachedRowSet = executeQuery(new Object[]{idEntidad});
		while (cachedRowSet.next()) {
			CatalogoOpcionVO item = new CatalogoOpcionVO();
			item.setIdCatalogo(idEntidad);
			item.setIdCatalogoOpcion(cachedRowSet.getLong("ID_MUNICIPIO"));
			item.setOpcion(cachedRowSet.getString("MUNICIPIO"));

			municipios.add(item);		
		}

		return municipios;
	}		

	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ID_ENTIDAD, ID_MUNICIPIO, MUNICIPIO ");
		query.append("  FROM MUNICIPIO ");
		query.append(" WHERE ID_ENTIDAD = ? ");
		query.append(" ORDER BY MUNICIPIO ");		
		return query.toString();
		
	}

}
