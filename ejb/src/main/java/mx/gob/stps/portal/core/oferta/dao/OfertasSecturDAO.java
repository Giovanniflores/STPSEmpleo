////package mx.gob.stps.portal.core.oferta.dao;
////
////import java.sql.CallableStatement;
////import java.sql.Connection;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////import java.util.ArrayList;
////import java.util.List;
////
////import javax.naming.Context;
////
////import oracle.jdbc.OracleTypes;
////import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
////import mx.gob.stps.portal.core.infra.data.TemplateDAO;
////import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
////
////public class OfertasSecturDAO extends TemplateDAO {
////
////	@Override
////	protected String getQuery() {
////		// TODO Auto-generated method stub
////		return null;
////	}
////	
////	
////	public List<OfertaRivieraMayaVO> obtenerOfertas() throws SQLException {
////		Connection dbConnection = null;
////		CallableStatement callableStatement = null;
////		ResultSet rs = null;
////		String getDBUSERCursorSql = "{call getOfertas(?)}";
////
////		Context context = null;
////		Connection conn = null;
////		CallableStatement c = null;
////		ResultSet cachedRowSet = null;
////		List<OfertaRivieraMayaVO>	list	=	 new ArrayList<OfertaRivieraMayaVO>();
////		
////		try{
////			
////			ConnectionWraper wraper = getConnection();
////			dbConnection = wraper.getConnection();
////			context = wraper.getContext();
////			callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);
////			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
////			// execute getDBUSERCursor store procedure
////			callableStatement.executeUpdate();
////			// get cursor and cast it to ResultSet
////			rs = (ResultSet) callableStatement.getObject(1);
////			while (rs.next()) {
////				OfertaRivieraMayaVO vo	=	new OfertaRivieraMayaVO();
////				vo.setVigencia(rs.getString("vigencia"));
////				vo.setGradoescolaridad(rs.getString("gradoescolaridad"));
////				vo.setExperiencia(rs.getString("experiencia"));
////				vo.setEstatus(rs.getString("estatus"));
////				vo.setIdOferta(rs.getLong("idOferta"));
////				vo.setIdEempresa(rs.getLong("idEempresa"));
////				vo.setPrestaciones(rs.getString("prestaciones"));
////				vo.setSueldo(rs.getString("sueldo"));
////				vo.setNombrepuesto(rs.getString("nombrepuesto"));
////				vo.setRazonSocial(rs.getString("razonSocial"));
////				vo.setNombre(rs.getString("razonSocial"));
////				vo.setNombreContacto(rs.getString("nombreContacto"));
////				vo.setCorreoContacto(rs.getString("correoContacto"));
////				vo.setUbicacion(rs.getString("ubicacion"));
////				vo.setDescripcion(rs.getString("descripcion"));
////				vo.setConocimientos(rs.getString("conocimientos"));
////				list.add(vo);
////				/*System.out.println(vo.gevigencia+"|"+	gradoescolaridad+"|"+	experiencia+"|"+	
////									estatus+"|"+	idOferta	
////								+"|"+	idEempresa+"|"+	prestaciones+"|"+	sueldo +"|"+
////								nombrepuesto	+"|"+	razonSocial	
////								+"|"+	nombreContacto	+"|"+	correoContacto	
////								+"|"+	ubicacion	+"|"+	descripcion+"|"+
////								conocimientos	);*/
////			}
////
////		} catch (SQLException e) {
////
////			System.out.println(e.getMessage());
////
////		} finally {
////
////			if (rs != null) {
////				rs.close();
////			}
////
////			if (callableStatement != null) {
////				callableStatement.close();
////			}
////
////			if (dbConnection != null) {
////				dbConnection.close();
////			}
////
////		}
////		return list;
////	}
////
////
////}
//
//package mx.gob.stps.portal.core.oferta.dao;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.Context;
//
//import oracle.jdbc.OracleTypes;
//import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
//import mx.gob.stps.portal.core.infra.data.TemplateDAO;
//import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
//
//public class OfertasSecturDAO extends TemplateDAO {
//
//	@Override
//	protected String getQuery() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	
//	public List<OfertaRivieraMayaVO> obtenerOfertas() throws SQLException {
//		Connection dbConnection = null;
//		CallableStatement callableStatement = null;
//		ResultSet rs = null;
//		String getDBUSERCursorSql = "{call getOfertas(?)}";
//
//		Context context = null;
//		Connection conn = null;
//		CallableStatement c = null;
//		ResultSet cachedRowSet = null;
//		List<OfertaRivieraMayaVO>	list	=	 new ArrayList<OfertaRivieraMayaVO>();
//		
//		try{
//			
//			ConnectionWraper wraper = getConnection();
//			dbConnection = wraper.getConnection();
//			context = wraper.getContext();
//			callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);
//			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
//			// execute getDBUSERCursor store procedure
//			callableStatement.executeUpdate();
//			// get cursor and cast it to ResultSet
//			rs = (ResultSet) callableStatement.getObject(1);
//			
//			while (rs.next()) {
//				OfertaRivieraMayaVO vo	=	new OfertaRivieraMayaVO();
//				vo.setVigencia(rs.getString("vigencia"));
//				vo.setGradoescolaridad(rs.getString("gradoescolaridad"));
//				vo.setExperiencia(rs.getString("experiencia"));
//				vo.setEstatus(rs.getString("estatus"));
//				vo.setIdOferta(rs.getLong("idOferta"));
//				vo.setIdEempresa(rs.getLong("idEempresa"));
//				vo.setPrestaciones(rs.getString("prestaciones"));
//				vo.setSueldo(rs.getString("sueldo"));
//				vo.setNombrepuesto(rs.getString("nombrepuesto"));
//				vo.setRazonSocial(rs.getString("razonSocial"));
//				vo.setNombre(rs.getString("razonSocial"));
//				vo.setNombreContacto(rs.getString("nombreContacto"));
//				vo.setCorreoContacto(rs.getString("correoContacto"));
//				vo.setUbicacion(rs.getString("ubicacion"));
//				vo.setDescripcion(rs.getString("descripcion"));
//				vo.setConocimientos(rs.getString("conocimientos"));
//				list.add(vo);
//				
//				System.out.println(vo.getVigencia() +"|"+	vo.getGradoescolaridad()+"|"+	vo.getExperiencia()+"|"+	
//									vo.getEstatus() +"|"+	vo.getIdOferta()	
//								+"|"+	vo.getIdEempresa() +"|"+	vo.getPrestaciones() +"|"+ vo.getSueldo() +"|"+
//								vo.getNombrepuesto() +"|"+ vo.getRazonSocial()	
//								+"|"+	vo.getNombreContacto() +"|"+ vo.getCorreoContacto()
//								+"|"+	vo.getUbicacion()	+"|"+ vo.getDescripcion()+"|"+
//								vo.getConocimientos()	);
//			}
//
//		} catch (SQLException e) {
//
//			System.out.println(e.getMessage());
//
//		} finally {
//
//			if (rs != null) {
//				rs.close();
//			}
//
//			if (callableStatement != null) {
//				callableStatement.close();
//			}
//
//			if (dbConnection != null) {
//				dbConnection.close();
//			}
//
//		}
//		return list;
//	}
//
//
//	public List<OfertaRivieraMayaVO> obtenerOfertasFiltro(int edo,String busqueda) throws SQLException {
//		Connection dbConnection = null;
//		CallableStatement callableStatement = null;
//		ResultSet rs = null;
//		
//		String getDBUSERCursorSql = "{call getOfertasFiltradas(?,?,?)}";
//
//		Context context = null;
//		Connection conn = null;
//		CallableStatement c = null;
//		ResultSet cachedRowSet = null;
//		List<OfertaRivieraMayaVO>	list	=	 new ArrayList<OfertaRivieraMayaVO>();
//		
//		try{
//			
//			if (busqueda == null || busqueda.equals("")) {
//				busqueda="0";
//			 }
//			
//			
//			ConnectionWraper wraper = getConnection();
//			dbConnection = wraper.getConnection();
//			context = wraper.getContext();
//			callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);
//			callableStatement.setString(1, busqueda);
//			callableStatement.setInt(2, edo);
//			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
//			// execute getDBUSERCursor store procedure
//			callableStatement.executeQuery();
//			// get cursor and cast it to ResultSet
//			rs = (ResultSet) callableStatement.getObject(3);
//			while (rs.next()) {
//				OfertaRivieraMayaVO vo	=	new OfertaRivieraMayaVO();
//				vo.setVigencia(rs.getString("vigencia"));
//				vo.setGradoescolaridad(rs.getString("gradoescolaridad"));
//				vo.setExperiencia(rs.getString("experiencia"));
//				vo.setEstatus(rs.getString("estatus"));
//				vo.setIdOferta(rs.getLong("idOferta"));
//				vo.setIdEempresa(rs.getLong("idEempresa"));
//				vo.setPrestaciones(rs.getString("prestaciones"));
//				vo.setSueldo(rs.getString("sueldo"));
//				vo.setNombrepuesto(rs.getString("nombrepuesto"));
//				vo.setRazonSocial(rs.getString("razonSocial"));
//				vo.setNombre(rs.getString("razonSocial"));
//				vo.setNombreContacto(rs.getString("nombreContacto"));
//				vo.setCorreoContacto(rs.getString("correoContacto"));
//				vo.setUbicacion(rs.getString("ubicacion"));
//				vo.setDescripcion(rs.getString("descripcion"));
//				vo.setConocimientos(rs.getString("conocimientos"));
//				list.add(vo);
//				
//				//System.out.println(vo.getVigencia() +"|"+	vo.getGradoescolaridad()+"|"+	vo.getExperiencia()+"|"+	
//					//				vo.getEstatus() +"|"+	vo.getIdOferta()	
//						//		+"|"+	vo.getIdEempresa() +"|"+	vo.getPrestaciones() +"|"+ vo.getSueldo() +"|"+
//							//	vo.getNombrepuesto() +"|"+ vo.getRazonSocial()	
//								//+"|"+	vo.getNombreContacto() +"|"+ vo.getCorreoContacto()
//							//	+"|"+	vo.getUbicacion()	+"|"+ vo.getDescripcion()+"|"+
//								//vo.getConocimientos()	);
//			}
//
//		} catch (SQLException e) {
//
//			System.out.println(e.getMessage());
//
//		} finally {
//
//			if (rs != null) {
//				rs.close();
//			}
//
//			if (callableStatement != null) {
//				callableStatement.close();
//			}
//
//			if (dbConnection != null) {
//				dbConnection.close();
//			}
//
//		}
//		return list;
//	}
//	
//}
//

package mx.gob.stps.portal.core.oferta.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;

import oracle.jdbc.OracleTypes;
import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;

public class OfertasSecturDAO extends TemplateDAO {

	@Override
	protected String getQuery() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public List<OfertaRivieraMayaVO> obtenerOfertas() throws SQLException {
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		String getDBUSERCursorSql = "{call getOfertas(?)}";

		Context context = null;
		Connection conn = null;
		CallableStatement c = null;
		ResultSet cachedRowSet = null;
		List<OfertaRivieraMayaVO>	list	=	 new ArrayList<OfertaRivieraMayaVO>();
		
		try{
			
			ConnectionWraper wraper = getConnection();
			dbConnection = wraper.getConnection();
			context = wraper.getContext();
			callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			// execute getDBUSERCursor store procedure
			callableStatement.executeUpdate();
			// get cursor and cast it to ResultSet
			rs = (ResultSet) callableStatement.getObject(1);
			
			while (rs.next()) {
				OfertaRivieraMayaVO vo	=	new OfertaRivieraMayaVO();
				vo.setVigencia(rs.getString("vigencia"));
				vo.setGradoescolaridad(rs.getString("gradoescolaridad"));
				vo.setExperiencia(rs.getString("experiencia"));
				vo.setEstatus(rs.getString("estatus"));
				vo.setIdOferta(rs.getLong("idOferta"));
				vo.setIdEempresa(rs.getLong("idEempresa"));
				vo.setPrestaciones(rs.getString("prestaciones"));
				vo.setSueldo(rs.getString("sueldo"));
				vo.setNombrepuesto(rs.getString("nombrepuesto"));
				vo.setRazonSocial(rs.getString("razonSocial"));
				vo.setNombre(rs.getString("razonSocial"));
				vo.setNombreContacto(rs.getString("nombreContacto"));
				vo.setCorreoContacto(rs.getString("correoContacto"));
				vo.setUbicacion(rs.getString("ubicacion"));
				vo.setDescripcion(rs.getString("descripcion"));
				vo.setConocimientos(rs.getString("conocimientos"));
				list.add(vo);
				
				System.out.println(vo.getVigencia() +"|"+	vo.getGradoescolaridad()+"|"+	vo.getExperiencia()+"|"+	
									vo.getEstatus() +"|"+	vo.getIdOferta()	
								+"|"+	vo.getIdEempresa() +"|"+	vo.getPrestaciones() +"|"+ vo.getSueldo() +"|"+
								vo.getNombrepuesto() +"|"+ vo.getRazonSocial()	
								+"|"+	vo.getNombreContacto() +"|"+ vo.getCorreoContacto()
								+"|"+	vo.getUbicacion()	+"|"+ vo.getDescripcion()+"|"+
								vo.getConocimientos()	);
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (rs != null) {
				rs.close();
			}

			if (callableStatement != null) {
				callableStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return list;
	}


	public List<OfertaRivieraMayaVO> obtenerOfertasFiltro(int edo,String busqueda) throws SQLException {
		Connection dbConnection = null;
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		
		String getDBUSERCursorSql = "{call getOfertasFiltradas(?,?,?)}";

		Context context = null;
		Connection conn = null;
		CallableStatement c = null;
		ResultSet cachedRowSet = null;
		List<OfertaRivieraMayaVO>	list	=	 new ArrayList<OfertaRivieraMayaVO>();
		
		try{
			
			if (busqueda == null || busqueda.equals("")) {
				busqueda="0";
			 }
			
			
			ConnectionWraper wraper = getConnection();
			dbConnection = wraper.getConnection();
			context = wraper.getContext();
			callableStatement = dbConnection.prepareCall(getDBUSERCursorSql);
			callableStatement.setString(1, busqueda);
			callableStatement.setInt(2, edo);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			// execute getDBUSERCursor store procedure
			callableStatement.executeQuery();
			// get cursor and cast it to ResultSet
			rs = (ResultSet) callableStatement.getObject(3);
			while (rs.next()) {
				OfertaRivieraMayaVO vo	=	new OfertaRivieraMayaVO();
				vo.setVigencia(rs.getString("vigencia"));
				vo.setGradoescolaridad(rs.getString("gradoescolaridad"));
				vo.setExperiencia(rs.getString("experiencia"));
				vo.setEstatus(rs.getString("estatus"));
				vo.setIdOferta(rs.getLong("idOferta"));
				vo.setIdEempresa(rs.getLong("idEempresa"));
				vo.setPrestaciones(rs.getString("prestaciones"));
				vo.setSueldo(rs.getString("sueldo"));
				vo.setNombrepuesto(rs.getString("nombrepuesto"));
				vo.setRazonSocial(rs.getString("razonSocial"));
				vo.setNombre(rs.getString("razonSocial"));
				vo.setNombreContacto(rs.getString("nombreContacto"));
				vo.setCorreoContacto(rs.getString("correoContacto"));
				vo.setUbicacion(rs.getString("ubicacion"));
				vo.setDescripcion(rs.getString("descripcion"));
				vo.setConocimientos(rs.getString("conocimientos"));
				list.add(vo);
				
				//System.out.println(vo.getVigencia() +"|"+	vo.getGradoescolaridad()+"|"+	vo.getExperiencia()+"|"+	
					//				vo.getEstatus() +"|"+	vo.getIdOferta()	
						//		+"|"+	vo.getIdEempresa() +"|"+	vo.getPrestaciones() +"|"+ vo.getSueldo() +"|"+
							//	vo.getNombrepuesto() +"|"+ vo.getRazonSocial()	
								//+"|"+	vo.getNombreContacto() +"|"+ vo.getCorreoContacto()
							//	+"|"+	vo.getUbicacion()	+"|"+ vo.getDescripcion()+"|"+
								//vo.getConocimientos()	);
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (rs != null) {
				rs.close();
			}

			if (callableStatement != null) {
				callableStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return list;
	}
	
}
