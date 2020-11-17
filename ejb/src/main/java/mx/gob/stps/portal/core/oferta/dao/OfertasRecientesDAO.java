package mx.gob.stps.portal.core.oferta.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;

import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.BOLSA_TRABAJO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class OfertasRecientesDAO extends TemplateDAO {

	public OfertasRecientesDAO(){}
	
	public OfertasRecientesDAO(Connection connectionGlobal){
		super(connectionGlobal);
	}
	
	@Override
	protected String getQuery() {
		return null;
	}
	
	private String setQueryOfertasRecientes(long idFuente) {   
		StringBuffer query = new StringBuffer();
		query.append("BEGIN SP_OFERTAS_RECIENTES ");
		query.append(" ( ?, "+ idFuente +", ? ); ");
		query.append(" END;");
		return query.toString();
	}	

	private String setQueryOfertasDestacadas() {   
		StringBuffer query = new StringBuffer();
		query.append("BEGIN SP_OFERTAS_DESTACADAS ");
		query.append(" ( ?, ? ); ");
		query.append(" END;");
		return query.toString();
	}	

	private String setQueryOfertasGendarmeria() {   
		StringBuffer query = new StringBuffer();
		query.append("BEGIN SP_OFERTAS_GENDARMERIA ");
		query.append(" ( ?, ? ); ");
		query.append(" END;");
		return query.toString();
	}	

	public List<OfertaPorCanalVO> obtenerOfertasRecientesTodas(int numRegistros, long idFuente) {
		return obtenerOfertas(numRegistros, setQueryOfertasRecientes(idFuente));
	}
	
	public List<OfertaPorCanalVO> obtenerOfertasDestacadasTodas(int numRegistros) {
		return obtenerOfertas(numRegistros, setQueryOfertasDestacadas());
	}
	
	public List<OfertaPorCanalVO> obtenerOfertasGendarmeriaTodas(int numRegistros) {
		return obtenerOfertas(numRegistros, setQueryOfertasGendarmeria());
	}
	
	private List<OfertaPorCanalVO> obtenerOfertas(int numRegistros, String query) {
		Context context = null;
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet cachedRowSet = null;
		
		List<OfertaPorCanalVO> ofertas = new ArrayList<OfertaPorCanalVO>();

		try{
			ConnectionWraper wraper = getConnection();
			conn = wraper.getConnection();
			context = wraper.getContext();

			stmt = conn.prepareCall  (query);
			stmt.setInt				 (1, numRegistros);
			stmt.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR
			stmt.execute();
			cachedRowSet = ((OracleCallableStatement)stmt).getCursor(2);

			while (cachedRowSet.next()) {
		    	OfertaPorCanalVO oferta = getOfertaRecienteDetalle(cachedRowSet);
		    	ofertas.add(oferta);
		    }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{if (cachedRowSet!=null) cachedRowSet.close();}catch(Exception e){e.printStackTrace();}
			try{if (stmt!=null) stmt.close();}catch(Exception e){e.printStackTrace();}
			
			/** Se esta utilizando una sola conexion para diversas consultas, por lo tanto no se cierra **/
			if (!isGlobalConnection()){
				try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
				
				if (context!=null){
					try {context.close();} catch (Exception e) {e.printStackTrace();}
				}
			}
		}
	    
		return ofertas;
	}

	private OfertaPorCanalVO getOfertaRecienteDetalle(ResultSet row) throws SQLException {
		OfertaPorCanalVO vo = new OfertaPorCanalVO();
		
		int bolsa = row.getInt(1);
		String bolsaTrabajo = null;
		
		if (bolsa == (int)BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion()) bolsaTrabajo = BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion(); else 
		if (bolsa == (int)BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion()) bolsaTrabajo = BOLSA_TRABAJO.TRABAJA_EN.getOpcion(); else 
		if (bolsa == (int)BOLSA_TRABAJO.CANADA.getIdOpcion()) bolsaTrabajo = BOLSA_TRABAJO.CANADA.getOpcion(); else 
		if (bolsa == (int)BOLSA_TRABAJO.SNE.getIdOpcion()) bolsaTrabajo = BOLSA_TRABAJO.SNE.getOpcion();
		else bolsaTrabajo = BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion();

		vo.setBolsaTrabajo		(bolsaTrabajo);  //BOLSA,
		vo.setIdOfertaEmpleo	(row.getLong	(2)); //ID_OFERTA_EMPLEO,
		vo.setTituloOferta		(row.getString	(3)); //TITULO_OFERTA,		
		vo.setUbicacion			(row.getString	(4)); //UBICACION,		
		//VIGENCIA,
		vo.setSalario			(row.getDouble	(6)); //SALARIO,
		vo.setEmpresa			(row.getString	(7)); //EMPRESA,		
		vo.setGradoEstudio		(row.getString	(8)); //GRADO_ESTUDIO,		
		vo.setCarrera			(row.getString	(9)); //CARRERA,
		vo.setFunciones			(row.getString	(10)); //FUNCIONES,
		vo.setEdad				(row.getString	(11)); //EDAD,
		vo.setIdiomas			(row.getString	(12)); //IDIOMA,		
		vo.setHorario			(row.getString	(13)); //HORARIO,	
		vo.setNumeroPlazas		(row.getInt		(14)); //NUMERO_PLAZAS,
		vo.setMedioContacto		(row.getString	(15)); //CONTACTO,
		vo.setFechaInicio		(row.getDate	(16)); //FECHA_INICIO,		
		vo.setFechaFin			(row.getDate	(17)); //FECHA_FIN,
		vo.setHabilidadGeneral	(row.getString	(18)); //HABILIDAD_GENERAL,
		vo.setExperiencia		(row.getString	(19)); //EXPERIENCIA_ANIOS,
		vo.setCompetencias		(row.getString	(20)); //COMPETENCIAS		

		return vo;
	}
	
}
