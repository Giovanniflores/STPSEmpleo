package mx.gob.stps.portal.core.oferta.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.oferta.vo.PostulacionRecienteVO;

/**
 * Ejecuta las consultas a BD para obtener las ofertas de empleo recientes
 * y las postulaciones recientes asociadas a una empresa.
 * @author jose.jimenez
 *
 */
public class RecientesDAO extends TemplateDAO {

	public RecientesDAO(){}
	
	public RecientesDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	/** Representa el nombre de la consulta a ejecutar */
	private String kindOfQuery;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getQuery() {
		/*if (this.kindOfQuery.equalsIgnoreCase("MIS_OFERTAS")) {
			return getQueryMisOfertasRecientes();
		} else*/ 
		if (this.kindOfQuery.equalsIgnoreCase("POSTULACIONES")) {
			return getQueryPostulacionesRecientes();
		}
		return null;
	}

	/**
	 * Obtiene las ofertas creadas recientemente por la empresa cuyo identificador
	 * se especifica como par&aacute;metro.
	 * 
	 * @param idEmpresa identificador de la empresa relacionada a las ofertas de empleo
	 *             a obtener
	 * @return el conjunto de ofertas recientemente creadas por la empresa
	 * @throws SQLException si ocurre alg&uacute;n problema al consultar la base de datos.
	 */
	/*public List<MiOfertaRecienteVO> obtenerMisOfertasRecientes(long idEmpresa) throws SQLException {
		this.kindOfQuery = "MIS_OFERTAS";
		List<MiOfertaRecienteVO> rows = new ArrayList<MiOfertaRecienteVO>();
		
		Object[] parametros = { idEmpresa };
		
		CachedRowSet cachedRowSet = executeQuery(parametros);
		while (cachedRowSet.next()) {
			MiOfertaRecienteVO row = new MiOfertaRecienteVO(cachedRowSet.getLong(1), cachedRowSet.getString(2));
			rows.add(row);
		}

		return rows;
	}*/
	
	/**
	 * Obtiene las postulaciones recientemente hechas a las ofertas de empleo activas
	 * de la empresa cuyo identificador se recibe como par&aacute;metro.
	 * @param idEmpresa identificador de la empresa relacionada a las ofertas de empleo
	 *             de las que se obtiene las postulaciones.
	 * @return el conjunto de postulaciones recientemente hechas
	 * @throws SQLException si ocurre alg&uacute;n problema al consultar la base de datos.
	 */
	public List<PostulacionRecienteVO> obtenerPostulacionesRecientes(long idEmpresa)
		throws SQLException {
		
		this.kindOfQuery = "POSTULACIONES";
		Object[] parametros = { idEmpresa };
		CachedRowSet cachedRowSet = executeQuery(parametros);
		List<PostulacionRecienteVO> rows = new ArrayList<PostulacionRecienteVO>();
		
		while (cachedRowSet.next()) {
			PostulacionRecienteVO row = new PostulacionRecienteVO();
			StringBuilder sb = new StringBuilder(128);
			if (cachedRowSet.getString(1) != null) {
				sb.append(cachedRowSet.getString(1));
			}
			if (cachedRowSet.getString(2) != null) {
				sb.append(" ");
				sb.append(cachedRowSet.getString(2));
			}
			if (cachedRowSet.getString(3) != null) {
				sb.append(" ");
				sb.append(cachedRowSet.getString(3));
			}
			row.setNombreCandidato(sb.toString());
			row.setIdCandidato(cachedRowSet.getLong(4));
			row.setTituloOferta(cachedRowSet.getString(5));
			row.setFechaAltaOferta(cachedRowSet.getDate(6));
			row.setIdOfertaCandidato(cachedRowSet.getLong(7));
			rows.add(row);
		}
		
		return rows;
	}
	
	/**
	 * Crea el objeto {@code String} que representa la consulta a BD parametrizada
	 * para obtener las ofertas activas recientes de una empresa.
	 * @return un objeto {@code String} que representa la consulta a BD a ejecutar
	 */
	/*private String getQueryMisOfertasRecientes() {
		
		StringBuilder sb = new StringBuilder(128);
		sb.append("SELECT * FROM (");
		sb.append("	SELECT id_oferta_empleo, titulo_oferta ");
		sb.append("	FROM oferta_empleo ");
		sb.append("	WHERE id_empresa = ? AND estatus = ");
		sb.append(Constantes.ESTATUS.ACTIVO.getIdOpcion());
		sb.append("	ORDER BY fecha_modificacion DESC ");
		sb.append(") WHERE ROWNUM <= "+ Constantes.NUM_MAX_OFERTAS_RECIENTES);
		return sb.toString();
	}*/
	
	/**
	 * Crea el objeto {@code String} que representa la consulta a BD parametrizada
	 * para obtener las postulaciones recientes a ofertas activas de una empresa.
	 * @return un objeto {@code String} que representa la consulta a BD a ejecutar
	 */
	private String getQueryPostulacionesRecientes() {
		
		StringBuilder sb = new StringBuilder(128);
		sb.append("SELECT * FROM ( "); 
		sb.append("	SELECT s.nombre, s.apellido1, s.apellido2, c.id_candidato,");
		sb.append("	oe.titulo_oferta, oc.fecha_alta, oc.id_oferta_candidato ");
		sb.append("	FROM oferta_candidato oc, oferta_empleo oe, candidato c, solicitante s ");
		sb.append("	WHERE oe.id_empresa = ? AND oe.estatus = ");
		sb.append(Constantes.ESTATUS.ACTIVO.getIdOpcion());
		sb.append(" AND ");
		sb.append("	      oe.id_oferta_empleo = oc.id_oferta_empleo AND oc.estatus = ");
		sb.append(Constantes.ESTATUS.POSTULADO.getIdOpcion());
		sb.append(" AND ");
		sb.append("	      oc.id_candidato = c.id_candidato ");
		sb.append("AND c.id_candidato = s.id_candidato ");
		sb.append("AND c.estatus = ");
		sb.append(Constantes.ESTATUS.ACTIVO.getIdOpcion());
		sb.append("	ORDER BY oc.fecha_alta DESC ");
		sb.append(") WHERE ROWNUM < ");
		sb.append((Constantes.NUM_MAX_POSTULACIONES_RECIENTES) + 1);
		return sb.toString();
	}
}
