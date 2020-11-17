package mx.gob.stps.portal.core.domicilio.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;

public class MunicipioSFPDAO extends TemplateDAO {
	
	//public MunicipioSFPDAO(){}
	
	public MunicipioSFPDAO(Connection globalConnection){
		super(globalConnection);
	}	
	
	public long obtenerMunicipioSFP(long idEntidadSFP, long idMunicipioSFP) throws SQLException {
		long idMunicipio = 0;
		Object[] parametros = {idEntidadSFP, idMunicipioSFP}; 
		CachedRowSet cachedRowSet = executeQuery(parametros);

		if (cachedRowSet.next()) {
			idMunicipio = cachedRowSet.getLong("id_municipio");
		}
		
		return idMunicipio;
	}	

	@Override
	protected String getQuery() {
		return "SELECT ID_MUNICIPIO FROM MUNICIPIO_SFP WHERE ID_ENTIDAD_SFP = ? AND ID_MUNICIPIO_SFP = ?";
	}

}