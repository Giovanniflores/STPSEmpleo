package mx.gob.stps.portal.core.cil.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.cil.bo.AttentionRequest;
import mx.gob.stps.portal.core.cil.vo.BitacoraAtencionVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;

public class IntegracionLaboralDAO extends TemplateDAO {
	
	private int QUERY = 0;
	private int NEXT_ID = 1;
	private int EXIST_PASSWORD = 2;
	private int ATTENTION_REQUEST = 3;
	private int MAX_ATTENTION_ID = 4;
	private int DISABLE_PASSWORD = 5;
	private int VALID_PASSWORD = 6;
	
	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		if (QUERY == NEXT_ID)
			//query.append("SELECT MAX(id_atencion) FROM bitacora_atencion");
			query.append("SELECT SEQ_CIL_ATENCION.NEXTVAL FROM DUAL ");
		else if (QUERY  == ATTENTION_REQUEST)
			query.append("SELECT id_bitacora_atencion, id_tipo_atencion, to_char(fecha_inicio, 'DD/MM/YYYY HH24:MI:SS') as fecha_registro, detalle, contador FROM bitacora_atencion WHERE id_candidato=? AND id_cil=? AND id_atencion=?");
		else if (QUERY  == MAX_ATTENTION_ID)
			query.append("SELECT MAX(id_atencion) FROM bitacora_atencion WHERE id_candidato=? AND id_cil=?");
		else if (QUERY  == EXIST_PASSWORD)
			query.append("SELECT estatus FROM cil_codigo_acceso WHERE contrasena=? AND id_cil=?");
		else if (QUERY  == DISABLE_PASSWORD)
			query.append("SELECT MAX(id_cil_codigo_acceso) FROM cil_codigo_acceso WHERE id_cil=?");
		else if (QUERY  == VALID_PASSWORD)
			query.append("SELECT id_cil FROM cil_codigo_acceso WHERE contrasena=? AND estatus="+Constantes.ESTATUS.ACTIVO.getIdOpcion());
		return query.toString();
	}

	/**
	 * Obtiene el siguiente identificador de atención
	 * @return long idAtención
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */
	public long nextIdAtencion() throws SQLException {
		long next = 0;
		QUERY = NEXT_ID;
		CachedRowSet rs = executeQuery();
		while (rs.next()) {
			next = rs.getLong(1);
		}
		return next + 1;
	}
	
	/**
	 * Encuentra un grupo de registros de Bitácora Atención
	 * @param idCil				long		Identificador del CIL que se registra
	 * @param idCandidato		long 		Identificador del candidato que genera el registro
	 * @throws SQLException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */	
	public AttentionRequest find(long idCil, long idCandidato) throws SQLException {
		long idAtencion = getMaxIdAtencion(idCil, idCandidato);
		AttentionRequest attention = new AttentionRequest(idCil, idCandidato, idAtencion, null, null, null, null, null, null, null);
		Object[] params = { idCandidato, idCil, idAtencion };
		CachedRowSet rs = executeQuery(params);
		while (rs.next()) {
			BitacoraAtencionVO vo = new BitacoraAtencionVO(0, 0, 0, 0, null, null, 0);
			vo.setIdBitacoraAtencion(rs.getLong("id_bitacora_atencion"));
			try {
				String date = rs.getString("fecha_registro");
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				attention.setFechaInicio(formatter.parse(date));
			} catch (ParseException e) { e.printStackTrace(); }
			long idTipoAtencion = rs.getLong("id_tipo_atencion");
			vo.setContador(rs.getInt("contador"));
			vo.setDetalle(rs.getString("detalle"));
			vo.setIdTipoAtencion(idTipoAtencion);
			if (idTipoAtencion == Constantes.TIPO_SERVICIO.ACTUALIZAR_CV.getIdOpcion())
				attention.setActualizarCV(vo);
			else if (idTipoAtencion == Constantes.TIPO_SERVICIO.IMPRESION.getIdOpcion())
				attention.setImpresion(vo);
			else if (idTipoAtencion == Constantes.TIPO_SERVICIO.FOTOCOPIAS.getIdOpcion())
				attention.setFotocopias(vo);
			else if (idTipoAtencion == Constantes.TIPO_SERVICIO.OTRAS_BOLSAS.getIdOpcion())
				attention.setOtrasBolsas(vo);
			else if (idTipoAtencion == Constantes.TIPO_SERVICIO.ACTIVIDAD_PORTAL.getIdOpcion())
				attention.setActividadPortal(vo);
			else if (idTipoAtencion == Constantes.TIPO_SERVICIO.TELEFONO.getIdOpcion())
				attention.setLlamadas(vo);
		}
		return attention;
	}
	
	private long getMaxIdAtencion(long idCil, long idCandidato) throws SQLException {
		long idAtencion = 0;
		QUERY = MAX_ATTENTION_ID;
		Object[] params = { idCandidato, idCil };
		CachedRowSet rs = executeQuery(params);
		while (rs.next()) {
			idAtencion = rs.getLong(1);
		}
		QUERY = ATTENTION_REQUEST;
		return idAtencion;
	}
	
	/**
	 * Indica si el la credencial de acceso ya existe en la tabla CilCodigoAcceso
	 * @return boolean true en caso de que la credencial ya exista en la tabla, false en caso contrario
	 * @throws SQLException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */
	public boolean exist(String password, long idCil) throws SQLException {
		boolean exist = false;
		QUERY = EXIST_PASSWORD;
		Object[] params = { password, idCil };
		CachedRowSet rs = executeQuery(params);
		while (rs.next()) {
			if (rs.getLong(1) == 1) {
				exist = true;
				break;
			}
		}
		return exist;
	}
	
	/**
	 * Desactiva la credencial de acceso para el cil especificado
	 * @return boolean true en caso de que la credencial ya exista en la tabla, false en caso contrario
	 * @throws SQLException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */
	public long getIdCilCodeAccess(long idCil) throws SQLException {
		QUERY = DISABLE_PASSWORD;
		long idCilCodeAccess = 0;
		Object[] params = { idCil };
		CachedRowSet rs = executeQuery(params);
		while (rs.next()) {
			idCilCodeAccess = rs.getLong(1);
		}
		return idCilCodeAccess;
	}
	
	/**
	 * Verifica la credencial de acceso para el cil especificado
	 * @return boolean true en caso de que la credencial este vigente, false en caso contrario
	 * @throws SQLException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */
	public long isValidPassword(String password) throws SQLException {
		long idCIL = -1;
		QUERY = VALID_PASSWORD;
		Object[] params = { password };
		CachedRowSet rs = executeQuery(params);
		while (rs.next()) {
			idCIL = rs.getLong(1);
		}
		return idCIL;
	}
}