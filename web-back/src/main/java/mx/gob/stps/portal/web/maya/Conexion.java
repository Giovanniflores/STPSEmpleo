package mx.gob.stps.portal.web.maya;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	Connection con;
	
	 String url = "jdbc:oracle:thin:@10.8.100.196:1521:empleo";
	 String user = "SIISNEPRO";
	 String password = "S$iSneN1";
	
	public Connection conectar() {
		try {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, user, password);
			
		}catch(Exception e) {
			
		}
		
		return con;
	}
	

}
