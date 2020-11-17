package mx.gob.stps.portal.core.oferta.detalle.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;

public class CandidatoDetalleDAO extends TemplateDAO {
	
	private int QUERY = 0;
	private final static int FECHA_NAC = 1;
	private final static int URL_VIDEO = 2;
	private final static int SHORT_ID = 3;
	private final static int CAT_OPC = 4;
	
	//private CandidatoDetalleDAO() {}
	
	private CandidatoDetalleDAO(Connection globalConnection) {
		super(globalConnection);
	}
	
	/*public static CandidatoDetalleDAO getInstance(){
		return new CandidatoDetalleDAO();
	}*/

	public static CandidatoDetalleDAO getInstance(Connection globalConnection){
		return new CandidatoDetalleDAO(globalConnection);
	}
	
	public Date getFechaNacimientoCandidato(long idCandidato) throws SQLException {
		QUERY = FECHA_NAC;
		Date fechanac = null;
		Object[] params = new Object[1];
		params[0] = idCandidato;
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()) {
			fechanac = rowSet.getDate(1);
		}
		return fechanac;
	}
	
	public String getUrlVideoc(long idCandidato) throws SQLException {
		QUERY = URL_VIDEO;
		String url = null;
		Object[] params = { idCandidato };
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()) {
			url = rowSet.getString(1);
		}
		return url;
	}
	
	public String getShortId(long idCatalogoOpcion) throws SQLException {
		QUERY = SHORT_ID;
		String shortId = null;
		Object[] params = { idCatalogoOpcion };
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()) {
			shortId = rowSet.getString(1);
		}
		return shortId;
	}
	
	public HashMap<Integer, String> getCatOptions(long idCatalogo) throws SQLException {
		QUERY = CAT_OPC;
		HashMap<Integer, String> catOptions = new HashMap<Integer, String>();
		Object[] params = { idCatalogo };
		CachedRowSet rowSet = executeQuery(params);
		while (rowSet.next()) {
			catOptions.put(rowSet.getInt(1), rowSet.getString(2));
		}
		return catOptions;
	}
	
	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		if (QUERY == FECHA_NAC)
			query.append("SELECT fecha_nacimiento FROM SOLICITANTE WHERE id_candidato=?");
		else if (QUERY == URL_VIDEO)
			query.append("SELECT url_videoc FROM PERFIL_LABORAL WHERE id_candidato=?");
		else if (QUERY == SHORT_ID)
			query.append("SELECT id_corto FROM CATALOGO_OPCION WHERE id_catalogo_opcion=?");
		else
			query.append("SELECT id_catalogo_opcion, opcion FROM CATALOGO_OPCION WHERE id_catalogo = ?");
		return query.toString();
	}
}