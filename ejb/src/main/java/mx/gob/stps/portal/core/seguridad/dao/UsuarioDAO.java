package mx.gob.stps.portal.core.seguridad.dao;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;

public final class UsuarioDAO extends TemplateDAO {

	private UsuarioDAO(){}
	
	public static UsuarioDAO getInstance(){
		return new UsuarioDAO();
	}

	public boolean esCorreoUnico(String correo) throws SQLException {
		boolean existe = false;
		
		CachedRowSet rowSet = executeQuery(new Object[]{correo,correo,correo,correo});
		
		existe = rowSet!=null && rowSet.next();

		return !existe;
	}
	
	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT CORREO_ELECTRONICO FROM USUARIO 	WHERE LOWER(CORREO_ELECTRONICO)  = LOWER(?) ");
		query.append("	UNION ");
		query.append("SELECT CORREO_ELECTRONICO FROM CANDIDATO WHERE LOWER(CORREO_ELECTRONICO)  = LOWER(?) ");
		query.append("	UNION ");
		query.append("SELECT CORREO_ELECTRONICO FROM EMPRESA 	WHERE LOWER(CORREO_ELECTRONICO)  = LOWER(?) ");
		query.append("	UNION ");
		query.append("SELECT CORREO_ELECTRONICO FROM EMPRESA_POR_AUTORIZAR WHERE LOWER(CORREO_ELECTRONICO)  = LOWER(?)	");
		return query.toString();
	}	
}
