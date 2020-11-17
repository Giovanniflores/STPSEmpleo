package mx.gob.stps.portal.core.infra.data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import javax.naming.Context;
import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;

import org.apache.log4j.Logger;

import com.sun.rowset.CachedRowSetImpl;

/**
 * Define y ejecuta sentencias hacia la base de datos
 * 
 * @author oscar.manzo
 *
 */
public abstract class TemplateDAO {

	private static Logger logger = Logger.getLogger(TemplateDAO.class);
	
	/** Conexion a base de datos en caso de ejecutar distintas sentencias correspondientes a una misma transaccion **/
	private Connection globalConnection = null;
	private boolean isGlobalConnection = false;
	
	public boolean isGlobalConnection() {
		return isGlobalConnection;
	}

	public TemplateDAO(){
	}

	public TemplateDAO(Connection globalConnection){
		this.globalConnection = globalConnection;
		this.isGlobalConnection = true;
	}

	/**
	 * Obtiene la sentencias SQL a ejecutar
	 * @return SQL
	 */
	protected abstract String getQuery();		
	
	/**
	 * Obtiene la conexion hacia la base de datos
	 * Si se indico el nombre del Data Source en el constructor de ahi se obtiene la conexion
	 * en caso contrario se obtiene de Data Source definido para el sistema 
	 * @return
	 * @throws SQLException Lanzada en caso de error
	 */
	protected final ConnectionWraper getConnection() throws SQLException {

		// Si se establecio una conexion se utiliza en lugar de obtener una nueva 
		if (isGlobalConnection){
			if (globalConnection!=null && !globalConnection.isClosed()){
				return ConnectionWraper.getInstance(globalConnection, null);	
			}
		}
		
		ConnectionWraper wraper = null;

		try{
			ConexionFactory factory = ConexionFactory.getInstance();
			wraper = factory.getConnection();
		}catch(Exception e){
			throw new SQLException(e);
		}

		return wraper;
	}
	
	/**
	 * Ejecuta la sentencia SQL
	 * @return CachedRowSet para lectura de registros
	 * @throws SQLException Lanzada en caso de error durante la ejecucion de la sentencia
	 */
	protected final CachedRowSet executeQuery() throws SQLException {
		Object[] params = null;
		return executeQuery(params);
	}
	
	/**
	 * Ejecuta la sentencia SQL
	 * @param params Lista de argumentos hacia la sentencia
	 * @return CachedRowSet para lectura de registros
	 * @throws SQLException Lanzada en caso de error durante la ejecucion de la sentencia
	 */
	protected final CachedRowSet executeQuery(Object[] params) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		Context context = null;
		
		PropertiesLoader properties = PropertiesLoader.getInstance();
		int MAX_ROWS = properties.getPropertyInt("app.data.max.rowset");

		CachedRowSet cachedRowSet = new CachedRowSetImpl();

		try {
			ConnectionWraper wraper = getConnection();
			conn = wraper.getConnection();
			context = wraper.getContext();
			
			ps = conn.prepareStatement(getQuery());
			
			if (params!=null){
				int index = 0;
				for (Object param : params){
					index++;
					setField(ps, index, param);
				}
			}
			
			rs = ps.executeQuery();

			cachedRowSet.setMaxRows(MAX_ROWS);
			cachedRowSet.populate(rs);
			cachedRowSet.beforeFirst();

		} finally {
			try{
				if (rs!=null) rs.close();
			}catch(Exception e){e.printStackTrace();}

			try{
				if (ps!=null) ps.close();
			}catch(Exception e){e.printStackTrace();}

			// Se cierra la conexion solo en caso de haber sido obtenida
			if (!isGlobalConnection){
				if (conn!=null){
					try{
						conn.close();
					} catch (Exception e) {e.printStackTrace();}					
				}

				if (context!=null){
					logger.info("Cerrando context");
					try {
						context.close();
					} catch (Exception e) {e.printStackTrace();}
				}
			}
		}
		
		return cachedRowSet;
	}

	/**
	 * Ejecuta la sentencia SQL para la busqueda de Candidatos
	 * @param params Lista de argumentos hacia la sentencia, String query dinamico de acurdo a los campos de busqueda
	 * @return CachedRowSet para lectura de registros
	 * @throws SQLException Lanzada en caso de error durante la ejecucion de la sentencia
	 */
	protected final CachedRowSet executeQueryCandidato(Object[] params, String query) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		Context context = null;

		PropertiesLoader properties = PropertiesLoader.getInstance();
		int MAX_ROWS = properties.getPropertyInt("app.data.max.rowset");

		CachedRowSet cachedRowSet = new CachedRowSetImpl();

		try {
			ConnectionWraper wraper = getConnection();
			conn = wraper.getConnection();
			context = wraper.getContext();
			
			ps = conn.prepareStatement(query);
			
			if (params!=null){
				int index = 0;
				for (Object param : params){
					index++;
					setField(ps, index, param);
				}
			}
			
			rs = ps.executeQuery();

			cachedRowSet.setMaxRows(MAX_ROWS);
			cachedRowSet.populate(rs);
			cachedRowSet.beforeFirst();

		} finally {
			try{
				if (rs!=null) rs.close();
			}catch(Exception e){e.printStackTrace();}

			try{
				if (ps!=null) ps.close();
			}catch(Exception e){e.printStackTrace();}

			if (!isGlobalConnection){
				if (conn!=null){
					try{
						conn.close();
					} catch (Exception e) {e.printStackTrace();}					
				}

				if (context!=null){
					logger.info("Cerrando context");
					try {
						context.close();
					} catch (Exception e) {e.printStackTrace();}
				}
			}
		}
		
		return cachedRowSet;
	}
	
	protected final int executeUpdate() throws SQLException {
		Object[] params = null;
		return executeUpdate(params);
	}
	
	protected final int executeUpdate(Object[] params) throws SQLException {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		Context context = null;
		
		try {
			ConnectionWraper wraper = getConnection(); 
			conn = wraper.getConnection();
			context = wraper.getContext();
			
			ps = conn.prepareStatement(getQuery());
			
			if (params!=null){
				int index = 0;
				for (Object param : params){
					index++;
					setField(ps, index, param);
				}
			}
			
			result = ps.executeUpdate();

		} finally {
			try{
				if (ps!=null) ps.close();

				// Se cierra la conexion solo en caso de haber sido obtenida
				if (!isGlobalConnection){
					if (conn!=null){
						try{
							conn.close();
						} catch (Exception e) {e.printStackTrace();}					
					}

					if (context!=null){
						logger.info("Cerrando context");
						try {
							context.close();
						} catch (Exception e) {e.printStackTrace();}
					}
				}

			}catch(Exception e){e.printStackTrace();}
		}
		
		return result;
	}
	
	/**
	 * Establece los parametros hacia la sentencia
	 * @param ps PreparedStatement
	 * @param index posicion de parametro
	 * @param valueObj valor de parametro
	 * @throws SQLException
	 */
	private void setField(PreparedStatement ps, int index, Object valueObj) throws SQLException {

		if (valueObj != null) {
			if (valueObj instanceof Integer) {
				ps.setInt(index, ((Integer)valueObj).intValue());
			} else if (valueObj instanceof Long) {
				ps.setLong(index, ((Long) valueObj).longValue());
			} else if (valueObj instanceof String) {
				ps.setString(index, ((String) valueObj));
			} else if (valueObj instanceof Date) {
				ps.setTimestamp(index, new Timestamp(((Date) valueObj).getTime()));
			} else if (valueObj instanceof Timestamp) {
				ps.setTimestamp(index, (Timestamp) valueObj);
			} else if (valueObj instanceof Boolean) {
				if (((Boolean) valueObj).booleanValue()) {
					ps.setInt(index, 0);
				} else {
					ps.setInt(index, 1);
				}
			} else if (valueObj instanceof BigDecimal) {
				ps.setBigDecimal(index, (BigDecimal) valueObj);
			} else if (valueObj instanceof Float) {
				ps.setFloat(index, ((Float) valueObj).floatValue());
			} else if (valueObj instanceof Double) {
				ps.setDouble(index, ((Double) valueObj).doubleValue());
			} else if (valueObj instanceof byte[]) {
				ps.setBytes(index, (byte[]) valueObj);
			} else {
				ps.setObject(index, valueObj);
			}
		} else {
			ps.setNull(index, Types.VARCHAR);
		}
	}
	
	public void closeGlobalConnection(){
		try{
			if (isGlobalConnection)
				globalConnection.close();			
		}catch(Exception e){e.printStackTrace();}
	}
	
}
