package mx.gob.stps.portal.core.infra.data;

import java.sql.Connection;

import javax.naming.Context;

public class ConnectionWraper {
	private Connection connection = null;
	private Context context = null;
	
	private ConnectionWraper(Connection connection, Context context){
		this.connection = connection;
		this.context = context;
	}
	
	public static final ConnectionWraper getInstance(Connection connection, Context context){
		return new ConnectionWraper(connection, context);
	}

	public Connection getConnection() {
		return connection;
	}
	public Context getContext() {
		return context;
	}
}
