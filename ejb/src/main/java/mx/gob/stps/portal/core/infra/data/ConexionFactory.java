package mx.gob.stps.portal.core.infra.data;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;

/**
 * Class <b>ConexionFactory</b> Permite construir la conexión a la base de datos.
 */
public final class ConexionFactory {

	private static Logger logger = Logger.getLogger(ConexionFactory.class);
	
	private static ConexionFactory instance = null;

	private static int count;
	
	private static DataSource dataSource = null;
	
	/**
	 * Constructor <b>ConexionFactory</b>
	 */
	private ConexionFactory(){}

	/**
	 * Method <b>getInstance</b> Obtiene la instancia de la conexion a la base
	 * de datos
	 * 
	 * @return instance.- Instancia de la conexion de la base de datos
	 */
	public static ConexionFactory getInstance() {
		if (instance == null)
			instance = new ConexionFactory();
		return instance;
	}

	/**
	 * Method <b>getConnection</b> Mediante el DataSource obtiene la conexion a
	 * la Base de Datos de Portal del Empleo
	 * 
	 * @param jndiName
	 *            .- Nombre del jndi.
	 * @throws Exception.- Excepción generada al ocurrir un error.
	 * @return Connection.- Conexión de la base de datos.
	 */
	
	public ConnectionWraper getConnection() throws Exception {
		//DataSource dataSource = null;
		Context context = null;
		Connection connection = null;
		
		PropertiesLoader properties = PropertiesLoader.getInstance();
		String jndiDS = properties.getProperty("app.data.source.jndi");
		
		try {
			if (dataSource==null){
				context = new InitialContext();
				dataSource = (DataSource) context.lookup(jndiDS);
			} 
			
			connection = dataSource.getConnection();
		} catch (NamingException ex) {
			throw new Exception(ex);
		}
		
		//logger.info("Conexion creada ["+ (++count) +" ,"+ connection.toString() +"]");
		
		return ConnectionWraper.getInstance(connection, null);
	}
	
//	public Connection getConnectionStpsEmpleo() throws Exception {
//		DataSource dataSource = null;
//		Context context = null;
//		Connection connection = null;
//		
//		PropertiesLoader properties = PropertiesLoader.getInstance();
//		String jndiDS = properties.getProperty("app.data.source.jndi");
//		
//		try {
//			context = new InitialContext();
//			dataSource = (DataSource) context.lookup(jndiDS);
//			connection = dataSource.getConnection();
//		} catch (NamingException ex) {
//			throw new Exception(ex);
//		}
//
//		//logger.info("Conexion creada ["+ (++count) +" ,"+ connection.toString() +"]");
//		
//		return connection;
//	}

	/*public Connection getConnection(String jndiName) throws Exception {
		Connection connection = null;
		connection = getDataSource(jndiName).getConnection();
		return connection;
	}*/
	
	/**
	 * Method <b>getDataSource</b> Obtiene el DataSource apartir del JNDI que
	 * busca en el Contexto
	 * 
	 * @param jndiName
	 *            .- Nombre del jndi.
	 * @throws Exception.- Excepción generada al ocurrir un error.
	 * @return DataSource.- Origen de datos.
	 */
//	private DataSource getDataSource(String jndiName) throws Exception {
//		DataSource dataSource = null;
//		Context context = null;
//		
//		try {
//			context = new InitialContext();
//			dataSource = (DataSource) context.lookup(jndiName);
//		} catch (NamingException ex) {
//			throw new Exception(ex);
//		}
//		return dataSource;
//	}
	
}