package mx.gob.stps.portal.web.maya;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class VacantesTrenMayaDAO {
	
	PreparedStatement ps;
	ResultSet rs;
	Conexion c = new Conexion();
	Connection con;
	
	public List listar() {
		List<Vacantes>lista=new ArrayList<>();
		String sql = "SELECT * FROM OFERTA_EMPLEO WHERE ESTATUS = 1";
		try {
			con =  c.conectar();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Vacantes v = new Vacantes();
				v.settitulo_oferta(rs.getString(1));
				v.setsalario(rs.getString(2));
				v.setfecha_publicacion(rs.getString(3));
				v.seturl(rs.getString(4));
				lista.add(v);
			}
		}catch(Exception e) {
			
		}
		
		return lista;
		
	}
}
