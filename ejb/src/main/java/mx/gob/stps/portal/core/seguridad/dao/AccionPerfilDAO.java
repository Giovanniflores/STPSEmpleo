package mx.gob.stps.portal.core.seguridad.dao;

import static mx.gob.stps.portal.core.infra.utils.Constantes.AUTENTICADO_NO_REQUERIDO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.AUTENTICADO_REQUERIDO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.seguridad.vo.AccionVO;

import org.apache.log4j.Logger;

public final class AccionPerfilDAO extends TemplateDAO {
	private static Logger logger = Logger.getLogger(AccionPerfilDAO.class);
	
	private int QUERY = 0;
	private final static int QUERY_ACCION_CON_AUT          = 1;
	private final static int QUERY_ACCION_PERFIL           = 2;
	private final static int QUERY_ACCION_ASIGNADAS_PERFIL = 3;
	private final static int UPDATE_ACCION_CON_AUT         = 4;
	private final static int UPDATE_ACCION_SIN_AUT         = 5;
	private final static int DELETE_ACCIONES               = 6;
	private final static int INSERT_ACCIONES_PERFIL        = 7;
	
	private String idsAccionesUpdate;
	
	private AccionPerfilDAO(){}
	
	private AccionPerfilDAO(Connection conn){
		super(conn);
	}
	
	public static AccionPerfilDAO getInstance(){
		return new AccionPerfilDAO();
	}

	public static AccionPerfilDAO getInstance(Connection conn){
		return new AccionPerfilDAO(conn);
	}
	
	public List<AccionVO> consultaAccionesRequierenAutenticacion() throws SQLException {
		QUERY = QUERY_ACCION_CON_AUT;		

		List<AccionVO> acciones = new ArrayList<AccionVO>();
		
		try{
			CachedRowSet rowSet = executeQuery(new Object[]{AUTENTICADO_NO_REQUERIDO,
					                                        AUTENTICADO_NO_REQUERIDO,
					                                        AUTENTICADO_REQUERIDO});

			while (rowSet.next())
				acciones.add(createVO(rowSet));

		}catch(Exception e){
			throw new SQLException(e);
		}

		return acciones;
	}

	public List<AccionVO> consultaAccionesPorPerfil(long idPerfil) throws SQLException {
		QUERY = QUERY_ACCION_PERFIL;		

		List<AccionVO> acciones = new ArrayList<AccionVO>();
		
		try{
			CachedRowSet rowSet = executeQuery(new Object[]{AUTENTICADO_NO_REQUERIDO, idPerfil});

			while (rowSet.next())
				acciones.add(createVO(rowSet));

		}catch(Exception e){
			throw new SQLException(e);
		}

		return acciones;
	}

	public List<AccionVO> consultaAccionesSignadasPorPerfil(long idPerfil) throws SQLException {
		QUERY = QUERY_ACCION_ASIGNADAS_PERFIL;		

		List<AccionVO> acciones = new ArrayList<AccionVO>();
		
		try{
			CachedRowSet rowSet = executeQuery(new Object[]{AUTENTICADO_NO_REQUERIDO, idPerfil});

			while (rowSet.next()){
				AccionVO vo = createVO(rowSet);
				vo.setAsignado(rowSet.getInt("ASIGNADO"));

				acciones.add(vo);
			}
		}catch(Exception e){
			throw new SQLException(e);
		}

		return acciones;
	}

	public void actualizaAccionesReqUsuarioAutenticado(long[] idsAcciones) throws SQLException {
		QUERY = UPDATE_ACCION_CON_AUT;
		
		StringBuilder in = new StringBuilder();
		for (long id : idsAcciones) in.append(id +",");

		idsAccionesUpdate = in.substring(0, in.length()-1);

		executeUpdate(new Object[]{AUTENTICADO_REQUERIDO});
	}

	/**
	 * Actualiza todas las acciones para que no requieran un usuario autenticado
	 * @param idPerfil
	 * @param idsAcciones
	 * @throws SQLException
	 */
	public void actualizaAccionesLimpiaReqAutenticado() throws SQLException {
		QUERY = UPDATE_ACCION_SIN_AUT;
		executeUpdate(new Object[]{AUTENTICADO_NO_REQUERIDO});
	}
	
	public void eliminaPermisos(long idPerfil) throws SQLException {
		QUERY = DELETE_ACCIONES;
		executeUpdate(new Object[]{idPerfil});
	}
	
	public void asignaPermisos(long idPerfil, long[] idsAcciones) throws SQLException {
		QUERY = INSERT_ACCIONES_PERFIL;
		
		Context context = null;
		Connection conn = null;
		
		ConnectionWraper wraper = getConnection();
		conn = wraper.getConnection();
		context = wraper.getContext();
		
		PreparedStatement ps = conn.prepareStatement(getQuery());
		
		for (long idAccion : idsAcciones){
			ps.setLong(1, idPerfil);
			ps.setLong(2, idAccion);
			ps.addBatch();
			ps.clearParameters();
		}

		ps.executeBatch();

		try{
			ps.close();
		}catch(Exception e){logger.error(e);}
		
		if (context!=null){
			logger.info("Cerrando context");
			try {
				context.close();
			} catch (Exception e) {e.printStackTrace();}
		}
	}

	private AccionVO createVO(CachedRowSet rowSet) throws SQLException {
		AccionVO vo = new AccionVO();

		vo.setIdAccion(rowSet.getLong("ID_ACCION"));
		vo.setVinculo(rowSet.getString("VINCULO")); 
		vo.setAutenticado(rowSet.getInt("AUTENTICADO"));
		vo.setDescripcion(rowSet.getString("DESCRIPCION"));
		return vo;
	}
	
	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		
		switch (QUERY){
			case QUERY_ACCION_CON_AUT:
				query.append("SELECT ID_ACCION, VINCULO, NVL(AUTENTICADO, ?) AS AUTENTICADO, DESCRIPCION ");
				query.append("  FROM ACCION ");
				query.append(" WHERE NVL(AUTENTICADO, ?) = ? ");
				break;
			case QUERY_ACCION_PERFIL:				
				query.append("SELECT ACC.ID_ACCION, ACC.VINCULO, NVL(ACC.AUTENTICADO, ?) AS AUTENTICADO, ACC.DESCRIPCION ");
				query.append("  FROM PERFIL_ACCION PEFA, ");
				query.append("       ACCION ACC ");
				query.append(" WHERE PEFA.ID_PERFIL = ? ");
				query.append("   AND ACC.ID_ACCION = PEFA.ID_ACCION ");
				break;
			case QUERY_ACCION_ASIGNADAS_PERFIL:
				query.append("SELECT ACC.ID_ACCION, ACC.VINCULO, NVL(ACC.AUTENTICADO, ?) AS AUTENTICADO, ACC.DESCRIPCION, ");
				query.append("       DECODE(NVL(PEF.ID_PERFIL,0),0,0,1) AS ASIGNADO ");
				query.append("  FROM PERFIL_ACCION PEF, ");
				query.append("       ACCION ACC ");
				query.append(" WHERE PEF.ID_PERFIL(+) = ? ");
				query.append("   AND PEF.ID_ACCION(+) = ACC.ID_ACCION ");
				break;
			case UPDATE_ACCION_CON_AUT:
				query.append("UPDATE ACCION SET AUTENTICADO = ? WHERE ID_ACCION IN ("+ idsAccionesUpdate +")");
				break;
			case UPDATE_ACCION_SIN_AUT:
				query.append("UPDATE ACCION SET AUTENTICADO = ?");
				break;
			case DELETE_ACCIONES:
				query.append("DELETE FROM PERFIL_ACCION WHERE ID_PERFIL = ? ");
				break;
			case INSERT_ACCIONES_PERFIL:
				query.append("INSERT INTO PERFIL_ACCION (ID_PERFIL, ID_ACCION) VALUES (?, ?)");
				break;
		}
		
		return query.toString();
	}	
}
