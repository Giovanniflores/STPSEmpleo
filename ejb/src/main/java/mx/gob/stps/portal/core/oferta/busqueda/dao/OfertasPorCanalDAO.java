package mx.gob.stps.portal.core.oferta.busqueda.dao;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_DOMINIO;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_MINIMA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.EXPERIENCIA_EGRESADOS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.ConnectionWraper;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.BOLSA_TRABAJO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_CORREO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CONTACTO_TELEFONO;
import mx.gob.stps.portal.core.infra.utils.Constantes.EDAD_REQUISITO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.SITUACION_ACADEMICA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_DISCAPACIDAD;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_EMPLEO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.detalle.helper.Utils;
import mx.gob.stps.portal.core.oferta.vo.UbicacionCanadaVO;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

/**
 * Ejecuta las consultas a BD para obtener los listados de ofertas de empleo
 * de acuerdo al canal en que se muestra cada listado.
 * @author jose.jimenez
 * @author oscar.manzo Adaptacion de Stored Procedures
 */
public class OfertasPorCanalDAO extends TemplateDAO {

	private String kindOfQuery = null;
	
	private static Logger logger = Logger.getLogger(OfertasPorCanalDAO.class);
	
	public OfertasPorCanalDAO(){}
	
	public OfertasPorCanalDAO(Connection connectionGlobal){
		super(connectionGlobal);
	}

	@Override
	protected String getQuery() {

		/*if (this.kindOfQuery.equalsIgnoreCase(CANAL_ESTUDIANTES)) {
			return getQueryOfertasEstudiantes();
		} else if (this.kindOfQuery.equalsIgnoreCase(CANAL_EGRESADOS)) {
			return getQueryOfertasEgresados();
		} else if (this.kindOfQuery.equalsIgnoreCase(CANAL_MAYORES)) {
			return getQueryOfertasMayores();
		} else if (this.kindOfQuery.equalsIgnoreCase(CANAL_CAPACIDADES)) {
			return getQueryOfertasCapacidades();
		} else */if (this.kindOfQuery.equalsIgnoreCase("OCUPATE")){
			return getQueryOfertasOcupate();
		} else if (this.kindOfQuery.equalsIgnoreCase("OFERTAS_EMPRESA")) {
			return getQueryOfertasEmpresa();
		}else if (this.kindOfQuery.equalsIgnoreCase("UBICACION_CANADA")) {
			return getUbucacionCanadaQuery();
		}	
		return null;
	}

	
	/**
     * Obtiene la oferta para mostrarse en los resultados de búsqueda del ocupate. {@literal idOfertaEmpleo}
     * es el identificador de la oferta de empleo.
     *
     * @param int idOfertaEmpleo identificador de la oferta de empleo.
     * @return List<OfertaPorCanalVO> el conjunto de ofertas que cumplen la condicion del ocupate.
     * @throws SQLException si ocurre alg&uacute;n problema al consultar la base de datos.
     **/
    /*public List<OfertaPorCanalVO> obtenerOfertasOcupate(int idOfertaEmpleo) throws SQLException {
        this.kindOfQuery = "OCUPATE";
        
		Connection conn = getConnection();

		CallableStatement stmt = conn.prepareCall(getQuery());
		stmt.setInt(1, idOfertaEmpleo);
		stmt.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR
		stmt.execute();
		ResultSet cachedRowSet = ((OracleCallableStatement)stmt).getCursor(2);        
        
        //Object[] parametros = { idOfertaEmpleo };
        //CachedRowSet cachedRowSet = executeQuery(parametros);
        List<OfertaPorCanalVO> rows = new ArrayList<OfertaPorCanalVO>();
        while (cachedRowSet.next()) {
            OfertaPorCanalVO row = new OfertaPorCanalVO();
            if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion()) {               
            	row.setBolsaTrabajo(BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion());			
            } else if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion()) {
                row.setBolsaTrabajo(BOLSA_TRABAJO.TRABAJA_EN.getOpcion());
            }
            row.setIdOfertaEmpleo(cachedRowSet.getLong(2));
            row.setTituloOferta(cachedRowSet.getString(3));
            row.setUbicacion(cachedRowSet.getString(4));
            row.setEmpresa(cachedRowSet.getString(5));
            row.setSalario(cachedRowSet.getDouble(6));
            row.setGradoEstudio(cachedRowSet.getString(7));
            row.setCarrera(cachedRowSet.getString(8));
            row.setFunciones(cachedRowSet.getString(9));
            row.setEdad(cachedRowSet.getString(10));
            row.setIdiomas(cachedRowSet.getString(11));
            row.setHorario(cachedRowSet.getString(12));
            row.setNumeroPlazas(cachedRowSet.getInt(13));
            row.setMedioContacto(cachedRowSet.getString(14));
            row.setLimitePostulantes(cachedRowSet.getInt(15));
            row.setPostulados(cachedRowSet.getInt(16));
            row.setFechaInicioString(Utils.formatDate(cachedRowSet.getDate(17)) );
            rows.add(row);
        }
        
		try{if (cachedRowSet!=null) cachedRowSet.close();}catch(Exception e){e.printStackTrace();}
		try{if (stmt!=null) stmt.close();}catch(Exception e){e.printStackTrace();}

		/ ** Se esta utilizando una sola conexion para diversas consultas, por lo tanto no se cierra ** /
		if (!isGlobalConnection()){
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}	
		}

        return rows;
    }*/

	/**
     * Obtiene la oferta para mostrarse en los resultados de búsqueda del ocupate. {@literal idOfertaEmpleo}
     * es el identificador de la oferta de empleo.
     *
     * @param int idOfertaEmpleo identificador de la oferta de empleo.
     * @return List<OfertaPorCanalVO> el conjunto de ofertas que cumplen la condicion del ocupate.
     * @throws SQLException si ocurre alg&uacute;n problema al consultar la base de datos.
     **/
    /*public List<OfertaPorCanalVO> obtenerOfertasEspecificas(int idOfertaEmpleo, String discriminante) throws SQLException {
        this.kindOfQuery = "OCUPATE";
		Connection conn = getConnection();
		CallableStatement stmt = conn.prepareCall(getQuery());
		stmt.setInt(1, idOfertaEmpleo);
		stmt.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR
		stmt.execute();
		ResultSet cachedRowSet = ((OracleCallableStatement)stmt).getCursor(2);
        //Object[] parametros = { idOfertaEmpleo };
        //CachedRowSet cachedRowSet = executeQuery(parametros);
        List<OfertaPorCanalVO> rows = new ArrayList<OfertaPorCanalVO>();
        while (cachedRowSet.next()) {
            OfertaPorCanalVO row = new OfertaPorCanalVO();
            if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion()) {               
            	row.setBolsaTrabajo(BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion());			
            } else if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion()) {
                row.setBolsaTrabajo(BOLSA_TRABAJO.TRABAJA_EN.getOpcion());
            }
            else if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.CANADA.getIdOpcion()) {
                row.setBolsaTrabajo(BOLSA_TRABAJO.CANADA.getOpcion());
            }
            row.setIdOfertaEmpleo(cachedRowSet.getLong(2));
            row.setTituloOferta(cachedRowSet.getString(3));
            row.setUbicacion(cachedRowSet.getString(4));
            row.setEmpresa(cachedRowSet.getString(5));
            row.setSalario(cachedRowSet.getDouble(6));
            row.setGradoEstudio(cachedRowSet.getString(7));
            row.setCarrera(cachedRowSet.getString(8));
            row.setFunciones(cachedRowSet.getString(9));
            row.setEdad(cachedRowSet.getString(10));
            row.setIdiomas(cachedRowSet.getString(11));
            row.setHorario(cachedRowSet.getString(12));
            row.setNumeroPlazas(cachedRowSet.getInt(13));
            row.setMedioContacto(cachedRowSet.getString(14));
            row.setLimitePostulantes(cachedRowSet.getInt(15));
            row.setPostulados(cachedRowSet.getInt(16));
            row.setFechaInicio(cachedRowSet.getDate(17));
            row.setFechaInicioString(Utils.formatDate(cachedRowSet.getDate(17)) );
            row.setHabilidadGeneral(parserQuery(cachedRowSet.getString(18), discriminante));
            row.setExperiencia(parseExperience(cachedRowSet.getInt(19)));
            row.setEntidad(cachedRowSet.getString(20));
            row.setMunicipio(cachedRowSet.getString(21));
            rows.add(row);
        }
		try{if (cachedRowSet!=null) cachedRowSet.close();}catch(Exception e){e.printStackTrace();}
		try{if (stmt!=null) stmt.close();}catch(Exception e){e.printStackTrace();}
		/ ** Se esta utilizando una sola conexion para diversas consultas, por lo tanto no se cierra ** /
		if (!isGlobalConnection()){
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}	
		}
        return rows;
    }*/
	
	/**
	 * Obtiene las ofertas dirigidas a estudiantes. {@literal numRegistros}
	 * es el n&uacute;mero m&aacute;ximo de registros que se mostrar&aacute;n 
	 * paginados, debe ser mayor que 1.
	 * 
	 * @param int numRegistros n&uacute;mero m&aacute;ximo de registros que se 
	 * 		  mostrar&aacute;n paginados
	 * @return List<OfertaPorCanalVO> el conjunto de ofertas que est&aacute;n 
	 * 		   dirigidas a estudiantes, limitado por el valor recibido en {@literal numRegistros} 
	 * @throws SQLException si ocurre alg&uacute;n problema al consultar la base de datos.
	 **/
//	public List<OfertaPorCanalVO> obtenerOfertasXCanal(String kindOfQuery, int numRegistros)throws SQLException {
//		
//		this.kindOfQuery = kindOfQuery;
//		
//		Context context = null;
//		Connection conn = null;
//
//		ConnectionWraper wraper = getConnection();
//		conn = wraper.getConnection();
//		context = wraper.getContext();
//		
//		List<OfertaPorCanalVO> rows = new ArrayList<OfertaPorCanalVO>();
//		if(null!=conn){
//			CallableStatement stmt = conn.prepareCall(getQuery());
//			if(null!=stmt){
//				stmt.setInt(1, numRegistros);
//				stmt.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR
//				stmt.execute();		
//				
//				ResultSet cachedRowSet = ((OracleCallableStatement)stmt).getCursor(2);			
//				//Object[] parametros = { numRegistros };
//				//CachedRowSet cachedRowSet = executeQuery(parametros);		
//							
//				if(null!=cachedRowSet){
//					while (cachedRowSet.next()) {
//
//						OfertaPorCanalVO row = new OfertaPorCanalVO();
//
//						if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion()) {
//							row.setBolsaTrabajo(BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion());
//						} else if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion()) {
//							row.setBolsaTrabajo(BOLSA_TRABAJO.TRABAJA_EN.getOpcion());
//						}
//						row.setIdOfertaEmpleo(cachedRowSet.getLong(2));
//						row.setTituloOferta(cachedRowSet.getString(3));
//						row.setUbicacion(cachedRowSet.getString(4));
//						row.setEmpresa(cachedRowSet.getString(5));
//						row.setSalario(cachedRowSet.getDouble(6));
//						row.setGradoEstudio(cachedRowSet.getString(7));
//						row.setCarrera(cachedRowSet.getString(8));
//						row.setFunciones(cachedRowSet.getString(9));
//						row.setEdad(cachedRowSet.getString(10));
//						row.setIdiomas(cachedRowSet.getString(11));
//						row.setHorario(cachedRowSet.getString(12));
//						row.setNumeroPlazas(cachedRowSet.getInt(13));
//						row.setMedioContacto(cachedRowSet.getString(14));
//						
//						rows.add(row);
//					}				
//				}
//				
//				try{if (cachedRowSet!=null) cachedRowSet.close();}catch(Exception e){e.printStackTrace();}
//				try{if (stmt!=null) stmt.close();}catch(Exception e){e.printStackTrace();}				
//			}						
//		}
//
//		/** Se esta utilizando una sola conexion para diversas consultas, por lo tanto no se cierra **/
//		if (!isGlobalConnection()){
//			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}
//			
//			if (context!=null){
//				try {
//					context.close();
//				} catch (Exception e) {e.printStackTrace();}
//			}
//		}
//		
//		return rows;
//	}

	public List<OfertaPorCanalVO> obtenerOfertasXCanal(long idEmpresa) throws SQLException {
				
		this.kindOfQuery = "OFERTAS_EMPRESA";
		List<OfertaPorCanalVO> rows = new ArrayList<OfertaPorCanalVO>();
		
		Context context = null;
		Connection conn = null;

		ConnectionWraper wraper = getConnection();
		conn = wraper.getConnection();
		context = wraper.getContext();

		if(null!=conn){
			CallableStatement stmt = conn.prepareCall(getQuery());		
			if(null!=stmt){
				stmt.setLong(1, idEmpresa);
				stmt.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR
				stmt.execute();
				ResultSet cachedRowSet = ((OracleCallableStatement)stmt).getCursor(2);
				
				//Object[] parametros = { idEmpresa};
				//CachedRowSet cachedRowSet = executeQuery(parametros);		
				if(null!=cachedRowSet){
					while (cachedRowSet.next()) {

						OfertaPorCanalVO row = new OfertaPorCanalVO();

						if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion()) {
							row.setBolsaTrabajo(BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion());
						} else if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion()) {
							row.setBolsaTrabajo(BOLSA_TRABAJO.TRABAJA_EN.getOpcion());
						}
						 else if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.CANADA.getIdOpcion()) {
								row.setBolsaTrabajo(BOLSA_TRABAJO.CANADA.getOpcion());
							}
						row.setIdOfertaEmpleo(cachedRowSet.getLong(2));
						row.setTituloOferta(cachedRowSet.getString(3));
						row.setUbicacion(cachedRowSet.getString(4));
						row.setEmpresa(cachedRowSet.getString(5));
						row.setSalario(cachedRowSet.getDouble(6));
						row.setGradoEstudio(cachedRowSet.getString(7));
						row.setCarrera(cachedRowSet.getString(8));
						row.setFunciones(cachedRowSet.getString(9));
						row.setEdad(cachedRowSet.getString(10));
						row.setIdiomas(cachedRowSet.getString(11));
						row.setHorario(cachedRowSet.getString(12));
						row.setNumeroPlazas(cachedRowSet.getInt(13));
						row.setMedioContacto(cachedRowSet.getString(14));
						row.setFechaInicio(cachedRowSet.getDate(15));
						row.setFechaFin(cachedRowSet.getDate(16));
						row.setFechaInicioString(Utils.formatDate(cachedRowSet.getDate(15)));
						row.setFechaFinString(Utils.formatDate(cachedRowSet.getDate(16)));
						
						if(BOLSA_TRABAJO.CANADA.getOpcion().equals(row.getBolsaTrabajo())){

							UbicacionCanadaVO ubicacionCanada = getUbicacionOfertaCanada(row.getIdOfertaEmpleo());
							if(ubicacionCanada!=null){
								row.setUbicacion(ubicacionCanada.getProvincia()+", "+ubicacionCanada.getCiudad());
								
							}
							else {
								row.setUbicacion("No especificado");						
							}
						}
						
						rows.add(row);
					}							
				}
				try{if (cachedRowSet!=null) cachedRowSet.close();}catch(Exception e){e.printStackTrace();}
				try{if (stmt!=null) stmt.close();}catch(Exception e){e.printStackTrace();}
				
			}			
		}
		/** Se esta utilizando una sola conexion para diversas consultas, por lo tanto no se cierra **/
		if (!isGlobalConnection()){
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}	

			if (context!=null){
				try {
					context.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}

		return rows;
	}

	private UbicacionCanadaVO getUbicacionOfertaCanada(long idOfertaEmpleo) throws SQLException {

		this.kindOfQuery = "UBICACION_CANADA";
		Object[] parametros = {idOfertaEmpleo};
		CachedRowSet cachedRowSet = executeQuery(parametros);
		UbicacionCanadaVO vo = null;
		
		if(cachedRowSet.next()){
			vo = new UbicacionCanadaVO();
			
			vo.setIdProvincia(cachedRowSet.getLong(1));
			vo.setProvincia(cachedRowSet.getString(2));
			vo.setIdCiudad(cachedRowSet.getLong(3));
			vo.setCiudad(cachedRowSet.getString(4));
		}
		
		return vo;
	}

	/**
	 * Crea la sentencia de SQL a ejecutar para obtener las ofertas para estudiantes.
	 * @return el objeto {@code String} que representa la consulta a ejecutar.
	 */
	// TODO ELIMINAR CONSULTA Y STORED PROCEDURE, SE HA MIGRADO A UNA FACADE
	private String getQueryOfertasEstudiantes() {

		StringBuilder sqlString = new StringBuilder();

		sqlString.append("BEGIN ");
		sqlString.append("SP_OFERTAS_ESTUDIANTES ");
		sqlString.append("(?, ");
		sqlString.append(" "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +", ");
		sqlString.append(" "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_GRADO_ESTUDIOS +", ");
		sqlString.append(" "+ EDAD_REQUISITO.SI.getIdOpcion() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_IDIOMAS +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.SI.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.NO.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.SI.getIdContactoCorreo() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.NO.getIdContactoCorreo() +", ");
		sqlString.append(" "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +", ");
		sqlString.append(" "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ");
		sqlString.append(" "+ ESTATUS.ACTIVO.getIdOpcion() +", ");
		sqlString.append(" "+ SITUACION_ACADEMICA.ESTUDIANTE.getIdOpcion() +", ");
		sqlString.append(" "+ TIPO_EMPLEO.MEDIO_TIEMPO.getIdOpcion() +", ");
		sqlString.append(" "+ TIPO_EMPLEO.FINES_SEMANA.getIdOpcion() +", ");
		sqlString.append(" ? ); ");
		sqlString.append("END; ");

		/*
		sqlString.append(" SELECT * ");
		sqlString.append(" FROM ("); 
		
		sqlString.append("SELECT H.FUENTE, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION");
		sqlString.append(", CASE");
		sqlString.append(" WHEN (((NVL (H.ID_TERCERA_EMPRESA,0) = 0)     AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) = 0)     AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN I.RAZON_SOCIAL");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN J.RAZON_SOCIAL");
		sqlString.append(" END AS EMPRESA");
		sqlString.append(" ,H.SALARIO");
		sqlString.append(" ,F_DESC_CATALOGO(H.ID_NIVEL_ESTUDIO,"+ CATALOGO_OPCION_GRADO_ESTUDIOS + ") AS GRADO_ESTUDIO ");
		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((K.ID_OFERTA_EMPLEO IS NULL) OR (K.ID_CARRERA_ESPECIALIDAD <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(K.ID_CARRERA_ESPECIALIDAD,F_CAT_ASOCIADO(" + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO))");
		sqlString.append(" END AS CARRERA");

		sqlString.append(" ,H.FUNCIONES ");
		sqlString.append(" ,CASE ");
		sqlString.append(" WHEN (H.EDAD_REQUISITO = "+ EDAD_REQUISITO.SI.getIdOpcion() +") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA");
		sqlString.append("   else 'N/A'");
		sqlString.append(" END AS EDAD");

		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(L.ID_IDIOMA," + CATALOGO_OPCION_IDIOMAS + ")");
		sqlString.append(" END AS IDIOMA");
				
		sqlString.append(" ,H.HORA_ENTRADA || ' - ' || H.HORA_SALIDA AS HORARIO ");
		sqlString.append(" ,H.NUMERO_PLAZAS ");
		
		sqlString.append(" ,CASE");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Teléfono y Correo Electrónico '");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.NO.getIdContactoCorreo() + ")) THEN 'Teléfono'");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.NO.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Correo Electrónico'");
		sqlString.append("   ELSE ' '");
	    sqlString.append(" END AS CONTACTO ");

	    sqlString.append("FROM EMPRESA I, OFERTA_EMPLEO H "); 
	    sqlString.append("    LEFT JOIN TERCERA_EMPRESA J ON H.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA "); 
	    sqlString.append("    LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO "); 
	    sqlString.append("    LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion()); 
	    sqlString.append("    LEFT JOIN DOMICILIO D ON H.ID_OFERTA_EMPLEO = D.ID_PROPIETARIO AND D.ID_TIPO_PROPIETARIO = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario()); 
	    sqlString.append("    LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO "); 
	    sqlString.append("    LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA); 
	    sqlString.append(" WHERE H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion() + " AND (H.id_situacion_academica = "+ SITUACION_ACADEMICA.ESTUDIANTE.getIdOpcion());	//Para estudiantes
	    sqlString.append(" OR H.id_tipo_empleo = "+ TIPO_EMPLEO.MEDIO_TIEMPO.getIdOpcion());		//Para estudiantes
	    sqlString.append(" OR H.id_tipo_empleo = "+ TIPO_EMPLEO.FINES_SEMANA.getIdOpcion());		//Para estudiantes
	    sqlString.append(") "); 
	    sqlString.append("    AND H.ID_EMPRESA = I.ID_EMPRESA ");
	    sqlString.append(") WHERE ROWNUM < ? ");
		*/

	    //logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	/**
	 * Crea la sentencia de SQL a ejecutar para obtener las ofertas para estudiantes.
	 * @return el objeto {@code String} que representa la consulta a ejecutar.
	 */
	private String getQueryOfertasOcupate() {

		StringBuilder sqlString = new StringBuilder();
		
		sqlString.append("BEGIN ");
		sqlString.append(" SP_OFERTAS_OCUPATE ");
		sqlString.append(" ("+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +", ");
		sqlString.append(" "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_GRADO_ESTUDIOS +", ");
		sqlString.append(" "+ EDAD_REQUISITO.SI.getIdOpcion() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_IDIOMAS +", ");
		sqlString.append(" "+ CATALOGO_OPCION_DOMINIO +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.SI.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.NO.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.SI.getIdContactoCorreo() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.NO.getIdContactoCorreo() +", ");
		sqlString.append(" "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +", ");
		sqlString.append(" "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ");
		sqlString.append(" "+ ESTATUS.ACTIVO.getIdOpcion() +", ");
		sqlString.append(" ? , ");
		sqlString.append(" ? ); ");
		sqlString.append("END;");
		
		/*
		sqlString.append(" SELECT DISTINCT * ");
		sqlString.append(" FROM ("); 
		sqlString.append("SELECT H.FUENTE, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION");
		sqlString.append(", CASE");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) = 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) = 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN I.RAZON_SOCIAL");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN J.RAZON_SOCIAL");
		sqlString.append(" END AS EMPRESA");
		sqlString.append(" ,H.SALARIO");
		sqlString.append(" ,F_DESC_CATALOGO(H.ID_NIVEL_ESTUDIO,"+ CATALOGO_OPCION_GRADO_ESTUDIOS + ") AS GRADO_ESTUDIO ");
		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((K.ID_OFERTA_EMPLEO IS NULL) OR (K.ID_CARRERA_ESPECIALIDAD <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(K.ID_CARRERA_ESPECIALIDAD,F_CAT_ASOCIADO(" + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO))");
		sqlString.append(" END AS CARRERA");
		sqlString.append(" ,H.FUNCIONES ");
		sqlString.append(" ,CASE ");
		sqlString.append(" WHEN (H.EDAD_REQUISITO = "+ EDAD_REQUISITO.SI.getIdOpcion() + ") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA");
		sqlString.append("   else 'N/A'");
		sqlString.append(" END AS EDAD");
		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(L.ID_IDIOMA," + CATALOGO_OPCION_IDIOMAS + ")");
		sqlString.append(" END AS IDIOMA");
		sqlString.append(" ,H.HORA_ENTRADA || ' - ' || H.HORA_SALIDA AS HORARIO ");
		sqlString.append(" ,H.NUMERO_PLAZAS ");
		sqlString.append(" ,CASE");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Teléfono y Correo Electrónico '");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.NO.getIdContactoCorreo() + ")) THEN 'Teléfono'");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.NO.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Correo Electrónico'");
		sqlString.append("   ELSE ' '");
	    sqlString.append(" END AS CONTACTO ");
	    sqlString.append("FROM EMPRESA I ");
	    sqlString.append("    LEFT JOIN OFERTA_EMPLEO H ON I.ID_EMPRESA = H.ID_EMPRESA "); 
	    sqlString.append("    LEFT JOIN TERCERA_EMPRESA J ON H.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA "); 
	    sqlString.append("    LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO "); 
	    sqlString.append("    LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion()); 
	    sqlString.append("    LEFT JOIN DOMICILIO D ON H.ID_OFERTA_EMPLEO = D.ID_PROPIETARIO AND D.ID_TIPO_PROPIETARIO = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario()); 
	    sqlString.append("    LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO "); 
	    sqlString.append("    LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA); 
	    sqlString.append(" WHERE H.ID_OFERTA_EMPLEO = ? AND H.ESTATUS = "+ ESTATUS.ACTIVO.getIdOpcion() +")");
	    */
	    
	    //logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	/**
	 * Crea la sentencia de SQL a ejecutar para obtener las ofertas para egresados.
	 * @return el objeto {@code String} que representa la consulta a ejecutar.
	 */
	// TODO ELIMINAR CONSULTA Y STORED PROCEDURE, SE HA MIGRADO A UNA FACADE
	private String getQueryOfertasEgresados() {

		StringBuilder sqlString = new StringBuilder();
		
		sqlString.append("BEGIN ");
		sqlString.append("SP_OFERTAS_EGRESADOS ");
		sqlString.append("(?, ");
		sqlString.append(" "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +", ");
		sqlString.append(" "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_GRADO_ESTUDIOS +", ");
		sqlString.append(" "+ EDAD_REQUISITO.SI.getIdOpcion() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_IDIOMAS +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.SI.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.NO.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.SI.getIdContactoCorreo() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.NO.getIdContactoCorreo() +", ");
		sqlString.append(" "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +", ");
		sqlString.append(" "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ");
		sqlString.append(" "+ GRADO_ESTUDIOS.CARRERA_TECNICA.getIdOpcion() +", ");
		sqlString.append(" "+ EXPERIENCIA_EGRESADOS +", ");
		sqlString.append(" "+ ESTATUS.ACTIVO.getIdOpcion() +", ");
		sqlString.append(" ? ); ");
		sqlString.append("END;");
		
		/*
		sqlString.append(" SELECT * FROM ("); 
		
		sqlString.append("SELECT H.FUENTE, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION");
		sqlString.append(", CASE");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) = 0)     AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) = 0)     AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN I.RAZON_SOCIAL");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN J.RAZON_SOCIAL");
		sqlString.append(" END AS EMPRESA");
		sqlString.append(" ,H.SALARIO");

		sqlString.append(" ,F_DESC_CATALOGO(H.ID_NIVEL_ESTUDIO,"+ CATALOGO_OPCION_GRADO_ESTUDIOS + ") AS GRADO_ESTUDIO ");

		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((K.ID_OFERTA_EMPLEO IS NULL) OR (K.ID_CARRERA_ESPECIALIDAD <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(K.ID_CARRERA_ESPECIALIDAD,F_CAT_ASOCIADO(" + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO))");
		sqlString.append(" END AS CARRERA");

		sqlString.append(" ,H.FUNCIONES ");
		sqlString.append(" ,CASE ");
		sqlString.append(" WHEN (H.EDAD_REQUISITO = "+ EDAD_REQUISITO.SI.getIdOpcion() +") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA");
		sqlString.append("   else 'N/A'");
		sqlString.append(" END AS EDAD");

		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(L.ID_IDIOMA," + CATALOGO_OPCION_IDIOMAS + ")");
		sqlString.append(" END AS IDIOMA");
				
		sqlString.append(" ,H.HORA_ENTRADA || ' - ' || H.HORA_SALIDA AS HORARIO ");
		sqlString.append(" ,H.NUMERO_PLAZAS ");
		
		sqlString.append(" ,CASE");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Teléfono y Correo Electrónico '");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.NO.getIdContactoCorreo() + ")) THEN 'Teléfono'");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.NO.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Correo Electrónico'");
		sqlString.append("   ELSE ' '");
	    sqlString.append(" END AS CONTACTO ");

	    sqlString.append("FROM EMPRESA I, OFERTA_EMPLEO H "); 
	    sqlString.append("    LEFT JOIN TERCERA_EMPRESA J ON H.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA "); 
	    sqlString.append("    LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO "); 
	    sqlString.append("    LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion()); 
	    sqlString.append("    LEFT JOIN DOMICILIO D ON H.ID_OFERTA_EMPLEO = D.ID_PROPIETARIO AND D.ID_TIPO_PROPIETARIO = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario()); 
	    sqlString.append("    LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO "); 
	    sqlString.append("    LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA); 
	    sqlString.append(" WHERE H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion() + " AND H.id_nivel_estudio > "+ GRADO_ESTUDIOS.CARRERA_TECNICA.getIdOpcion());	//Para egresados
	    sqlString.append(" AND H.experiencia_anios < "+ EXPERIENCIA_EGRESADOS);						//Para egresados
	    sqlString.append("    AND H.ID_EMPRESA = I.ID_EMPRESA ");
	    sqlString.append(") WHERE ROWNUM < ? ");
		*/
		
	    //logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	/**
	 * Crea la sentencia de SQL a ejecutar para obtener las ofertas para adultos mayores.
	 * @return el objeto {@code String} que representa la consulta a ejecutar.
	 */
	// TODO ELIMINAR CONSULTA Y STORED PROCEDURE, SE HA MIGRADO A UNA FACADE
	private String getQueryOfertasMayores() {

		StringBuilder sqlString = new StringBuilder();
		
		sqlString.append("BEGIN ");
		sqlString.append("SP_OFERTAS_ADULTOS_MAYORES ");
		sqlString.append("(?, ");
		sqlString.append(" "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +", ");
		sqlString.append(" "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_GRADO_ESTUDIOS +", ");
		sqlString.append(" "+ EDAD_REQUISITO.SI.getIdOpcion() +", ");
		sqlString.append(" "+ EDAD_REQUISITO.NO.getIdOpcion() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_IDIOMAS +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.SI.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.NO.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.SI.getIdContactoCorreo() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.NO.getIdContactoCorreo() +", ");
		sqlString.append(" "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +", ");
		sqlString.append(" "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ");
		sqlString.append(" "+ EDAD_MINIMA +", ");
		sqlString.append(" "+ ESTATUS.ACTIVO.getIdOpcion() +", ");
		sqlString.append(" ? ); ");
		sqlString.append("END;");
		
		/*
		sqlString.append(" SELECT * ");
		sqlString.append(" FROM ("); 
		
		sqlString.append("SELECT H.FUENTE, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION");
		sqlString.append(", CASE");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) = 0)     AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) = 0)     AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN I.RAZON_SOCIAL");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN J.RAZON_SOCIAL");
		sqlString.append(" END AS EMPRESA");
		sqlString.append(" ,H.SALARIO");
		sqlString.append(" ,F_DESC_CATALOGO(H.ID_NIVEL_ESTUDIO,"+ CATALOGO_OPCION_GRADO_ESTUDIOS + ") AS GRADO_ESTUDIO ");
		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((K.ID_OFERTA_EMPLEO IS NULL) OR (K.ID_CARRERA_ESPECIALIDAD <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(K.ID_CARRERA_ESPECIALIDAD,F_CAT_ASOCIADO(" + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO))");
		sqlString.append(" END AS CARRERA");
		sqlString.append(" ,H.FUNCIONES ");
		sqlString.append(" ,CASE ");
		sqlString.append(" WHEN (H.EDAD_REQUISITO = "+ EDAD_REQUISITO.SI.getIdOpcion() +") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA");
		sqlString.append("   else 'N/A'");
		sqlString.append(" END AS EDAD");

		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(L.ID_IDIOMA," + CATALOGO_OPCION_IDIOMAS + ")");
		sqlString.append(" END AS IDIOMA");
				
		sqlString.append(" ,H.HORA_ENTRADA || ' - ' || H.HORA_SALIDA AS HORARIO ");
		sqlString.append(" ,H.NUMERO_PLAZAS ");
		
		sqlString.append(" ,CASE");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Teléfono y Correo Electrónico '");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.NO.getIdContactoCorreo() + ")) THEN 'Teléfono'");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.NO.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Correo Electrónico'");
		sqlString.append("   ELSE ' '");
	    sqlString.append(" END AS CONTACTO ");

	    sqlString.append("FROM EMPRESA I, OFERTA_EMPLEO H "); 
	    sqlString.append("    LEFT JOIN TERCERA_EMPRESA J ON H.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA "); 
	    sqlString.append("    LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO "); 
	    sqlString.append("    LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion()); 
	    sqlString.append("    LEFT JOIN DOMICILIO D ON H.ID_OFERTA_EMPLEO = D.ID_PROPIETARIO AND D.ID_TIPO_PROPIETARIO = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario()); 
	    sqlString.append("    LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO "); 
	    sqlString.append("    LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA); 
	    sqlString.append(" WHERE H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion() + " AND (H.edad_minima > "+ EDAD_MINIMA);							//Para mayores
	    sqlString.append(" OR H.edad_requisito = "+ EDAD_REQUISITO.NO.getIdOpcion());		//Para mayores
	    sqlString.append(") "); 
	    sqlString.append("    AND H.ID_EMPRESA = I.ID_EMPRESA ");
	    sqlString.append(") WHERE ROWNUM < ? ");
	    */
	    //logger.debug(sqlString.toString());
		return sqlString.toString();
	}
	
	/**
	 * Crea la sentencia de SQL a ejecutar para obtener las ofertas para personas
	 * con capacidades diferentes.
	 * @return el objeto {@code String} que representa la consulta a ejecutar.
	 */
	// TODO ELIMINAR CONSULTA Y STORED PROCEDURE, SE HA MIGRADO A UNA FACADE
	private String getQueryOfertasCapacidades() {

		StringBuilder sqlString = new StringBuilder();
		
		sqlString.append("BEGIN ");
		sqlString.append(" SP_OFERTAS_CAPACIDADES_DIF ");
		sqlString.append(" (?, ");
		sqlString.append(" "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +", ");
		sqlString.append(" "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_GRADO_ESTUDIOS +", ");
		sqlString.append(" "+ EDAD_REQUISITO.SI.getIdOpcion() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_IDIOMAS +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.SI.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.NO.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.SI.getIdContactoCorreo() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.NO.getIdContactoCorreo() +", ");
		sqlString.append(" "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +", ");
		sqlString.append(" "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ");
		sqlString.append(" "+ ESTATUS.ACTIVO.getIdOpcion() +", ");
		sqlString.append(" "+ TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion() +", ");
		sqlString.append(" ? ); ");
		sqlString.append("END;");
		
		/*
		sqlString.append(" SELECT * ");
		sqlString.append(" FROM ("); 
		
		sqlString.append("SELECT H.FUENTE, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION");
		sqlString.append(", CASE");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) = 0)     AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) = 0)     AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN I.RAZON_SOCIAL");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN J.RAZON_SOCIAL");
		sqlString.append(" END AS EMPRESA");
		sqlString.append(" ,H.SALARIO");
		sqlString.append(" ,F_DESC_CATALOGO(H.ID_NIVEL_ESTUDIO,"+ CATALOGO_OPCION_GRADO_ESTUDIOS + ") AS GRADO_ESTUDIO ");
		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((K.ID_OFERTA_EMPLEO IS NULL) OR (K.ID_CARRERA_ESPECIALIDAD <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(K.ID_CARRERA_ESPECIALIDAD,F_CAT_ASOCIADO(" + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO))");
		sqlString.append(" END AS CARRERA");
		sqlString.append(" ,H.FUNCIONES ");
		sqlString.append(" ,CASE ");
		sqlString.append(" WHEN (H.EDAD_REQUISITO = "+ EDAD_REQUISITO.SI.getIdOpcion() +") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA");
		sqlString.append("   else 'N/A'");
		sqlString.append(" END AS EDAD");

		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(L.ID_IDIOMA," + CATALOGO_OPCION_IDIOMAS + ")");
		sqlString.append(" END AS IDIOMA");

		sqlString.append(" ,H.HORA_ENTRADA || ' - ' || H.HORA_SALIDA AS HORARIO ");
		sqlString.append(" ,H.NUMERO_PLAZAS ");
		
		sqlString.append(" ,CASE");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Teléfono y Correo Electrónico '");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.NO.getIdContactoCorreo() + ")) THEN 'Teléfono'");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.NO.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Correo Electrónico'");
		sqlString.append("   ELSE ' '");
	    sqlString.append(" END AS CONTACTO ");

	    sqlString.append("FROM EMPRESA I, OFERTA_EMPLEO H "); 
	    sqlString.append("    LEFT JOIN TERCERA_EMPRESA J ON H.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA "); 
	    sqlString.append("    LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO "); 
	    sqlString.append("    LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion()); 
	    sqlString.append("    LEFT JOIN DOMICILIO D ON H.ID_OFERTA_EMPLEO = D.ID_PROPIETARIO AND D.ID_TIPO_PROPIETARIO = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario()); 
	    sqlString.append("    LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO "); 
	    sqlString.append("    LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA); 
	    sqlString.append(" WHERE H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion() + " AND H.id_discapacidad > "+ TIPO_DISCAPACIDAD.NINGUNA.getIdOpcion());	//Para capacidades dftes.
	    sqlString.append("    AND H.ID_EMPRESA = I.ID_EMPRESA ");
	    sqlString.append(") WHERE ROWNUM < ? ");
		*/
		
		//logger.debug(sqlString.toString());
	    return sqlString.toString();
	}

	/**
	 * Crea la sentencia de SQL a ejecutar para obtener las ofertas para personas
	 * con capacidades diferentes.
	 * @return el objeto {@code String} que representa la consulta a ejecutar.
	 */
	private String getQueryOfertasEmpresa(){
		StringBuilder sqlString = new StringBuilder();

		sqlString.append("BEGIN ");
		sqlString.append(" SP_OFERTAS_EMPRESA "); 
		sqlString.append(" ("+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +", ");
		sqlString.append(" "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_GRADO_ESTUDIOS +", ");
		sqlString.append(" "+ EDAD_REQUISITO.SI.getIdOpcion() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_IDIOMAS +", ");
		sqlString.append(" "+ CATALOGO_OPCION_DOMINIO +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.SI.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_TELEFONO.NO.getIdContactoTelefono() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.SI.getIdContactoCorreo() +", ");
		sqlString.append(" "+ CONTACTO_CORREO.NO.getIdContactoCorreo() +", ");
		sqlString.append(" "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion() +", ");
		sqlString.append(" "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario() +", ");
		sqlString.append(" "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA +", ");
		sqlString.append(" "+ ESTATUS.ACTIVO.getIdOpcion() +", ");
		sqlString.append(" ? , ");
		sqlString.append(" ? ); ");
		sqlString.append("END;");

		/*
		sqlString.append(" SELECT * ");
		sqlString.append(" FROM ("); 
		sqlString.append("SELECT H.FUENTE, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION");
		sqlString.append(", CASE");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) = 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) = 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN I.RAZON_SOCIAL");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() +")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) !=0 ) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() +")) THEN J.RAZON_SOCIAL");
		sqlString.append(" END AS EMPRESA");
		sqlString.append(" ,H.SALARIO");
		sqlString.append(" ,F_DESC_CATALOGO(H.ID_NIVEL_ESTUDIO,"+ CATALOGO_OPCION_GRADO_ESTUDIOS + ") AS GRADO_ESTUDIO ");
		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((K.ID_OFERTA_EMPLEO IS NULL) OR (K.ID_CARRERA_ESPECIALIDAD <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(K.ID_CARRERA_ESPECIALIDAD,F_CAT_ASOCIADO(" + CATALOGO_OPCION_GRADO_ESTUDIOS + ", H.ID_NIVEL_ESTUDIO))");
		sqlString.append(" END AS CARRERA");
		sqlString.append(" ,H.FUNCIONES ");
		sqlString.append(" ,CASE ");
		sqlString.append(" WHEN (H.EDAD_REQUISITO = "+ EDAD_REQUISITO.SI.getIdOpcion() +") THEN EDAD_MINIMA || ' - ' || EDAD_MAXIMA");
		sqlString.append("   else 'N/A'");
		sqlString.append(" END AS EDAD");
		sqlString.append(" ,CASE");
		sqlString.append(" WHEN ((L.ID_OFERTA_EMPLEO IS NULL) OR (L.ID_IDIOMA <= 0)) THEN ' '");
		sqlString.append(" ELSE F_DESC_CATALOGO(L.ID_IDIOMA," + CATALOGO_OPCION_IDIOMAS + ")");
		sqlString.append(" END AS IDIOMA");
		sqlString.append(" ,H.HORA_ENTRADA || ' - ' || H.HORA_SALIDA AS HORARIO ");
		sqlString.append(" ,H.NUMERO_PLAZAS ");
		sqlString.append(" ,CASE");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Teléfono y Correo Electrónico '");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.SI.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.NO.getIdContactoCorreo() + ")) THEN 'Teléfono'");
		sqlString.append("   WHEN ((H.CONTACTO_TEL = " + CONTACTO_TELEFONO.NO.getIdContactoTelefono() + ")  AND (H.CONTACTO_CORREO = " + CONTACTO_CORREO.SI.getIdContactoCorreo() + ")) THEN 'Correo Electrónico'");
		sqlString.append("   ELSE ' '");
	    sqlString.append(" END AS CONTACTO, H.FECHA_INICIO, H.FECHA_FIN ");
	    sqlString.append("FROM EMPRESA I, OFERTA_EMPLEO H "); 
	    sqlString.append("    LEFT JOIN TERCERA_EMPRESA J ON H.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA "); 
	    sqlString.append("    LEFT JOIN OFERTA_CARRERA_ESPEC K ON H.ID_OFERTA_EMPLEO = K.ID_OFERTA_EMPLEO "); 
	    sqlString.append("    LEFT JOIN OFERTA_IDIOMA L ON H.ID_OFERTA_EMPLEO = L.ID_OFERTA_EMPLEO AND L.PRINCIPAL = "+ MULTIREGISTRO.PRINCIPAL.getIdOpcion());
	    sqlString.append("    LEFT JOIN DOMICILIO D ON H.ID_OFERTA_EMPLEO = D.ID_PROPIETARIO AND D.ID_TIPO_PROPIETARIO = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
	    sqlString.append("    LEFT JOIN MUNICIPIO F ON D.ID_ENTIDAD = F.ID_ENTIDAD AND D.ID_MUNICIPIO = F.ID_MUNICIPIO "); 
	    sqlString.append("    LEFT JOIN CATALOGO_OPCION G ON D.ID_ENTIDAD = G.ID_CATALOGO_OPCION AND G.ID_CATALOGO = "+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
	    sqlString.append(" WHERE H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion() + " AND H.ID_EMPRESA = ?");	
	    sqlString.append("    AND H.ID_EMPRESA = I.ID_EMPRESA ");
	    sqlString.append(") ");		
		*/

		//logger.debug(sqlString.toString());
		return sqlString.toString();
		
	}

	/**
     * Obtiene la oferta para mostrarse en los resultados de búsqueda del ocupate. {@literal idOfertaEmpleo}
     * es el identificador de la oferta de empleo.
     *
     * @param int idOfertaEmpleo identificador de la oferta de empleo.
     * @return List<OfertaPorCanalVO> el conjunto de ofertas que cumplen la condicion del ocupate.
     * @throws SQLException si ocurre alg&uacute;n problema al consultar la base de datos.
     **/
    /*public List<OfertaPorCanalVO> obtenerOfertasOcupate(int idOfertaEmpleo, String discriminante) throws SQLException {
        this.kindOfQuery = "OCUPATE";
		Connection conn = getConnection();
		CallableStatement stmt = conn.prepareCall(getQuery());
		stmt.setInt(1, idOfertaEmpleo);
		stmt.registerOutParameter(2, OracleTypes.CURSOR); //REF CURSOR
		stmt.execute();
		ResultSet cachedRowSet = ((OracleCallableStatement)stmt).getCursor(2);
        //Object[] parametros = { idOfertaEmpleo };
        //CachedRowSet cachedRowSet = executeQuery(parametros);
        List<OfertaPorCanalVO> rows = new ArrayList<OfertaPorCanalVO>();
        while (cachedRowSet.next()) {
            OfertaPorCanalVO row = new OfertaPorCanalVO();
            if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.PORTAL_EMPLEO.getIdOpcion()) {               
            	row.setBolsaTrabajo(BOLSA_TRABAJO.PORTAL_EMPLEO.getOpcion());			
            } else if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.TRABAJA_EN.getIdOpcion()) {
                row.setBolsaTrabajo(BOLSA_TRABAJO.TRABAJA_EN.getOpcion());
            }
            else if (cachedRowSet.getInt(1) == BOLSA_TRABAJO.CANADA.getIdOpcion()) {
                row.setBolsaTrabajo(BOLSA_TRABAJO.CANADA.getOpcion());
            }
            row.setIdOfertaEmpleo(cachedRowSet.getLong(2));
            row.setTituloOferta(cachedRowSet.getString(3));
            row.setUbicacion(cachedRowSet.getString(4));
            row.setEmpresa(cachedRowSet.getString(5));
            row.setSalario(cachedRowSet.getDouble(6));
            row.setGradoEstudio(cachedRowSet.getString(7));
            row.setCarrera(cachedRowSet.getString(8));
            row.setFunciones(cachedRowSet.getString(9));
            row.setEdad(cachedRowSet.getString(10));
            row.setIdiomas(cachedRowSet.getString(11));
            row.setHorario(cachedRowSet.getString(12));
            row.setNumeroPlazas(cachedRowSet.getInt(13));
            row.setMedioContacto(cachedRowSet.getString(14));
            row.setLimitePostulantes(cachedRowSet.getInt(15));
            row.setPostulados(cachedRowSet.getInt(16));
            row.setFechaInicio(cachedRowSet.getDate(17));
            row.setFechaInicioString(Utils.formatDate(cachedRowSet.getDate(17)) );
            row.setHabilidadGeneral(parserQuery(cachedRowSet.getString(18), discriminante));
            row.setExperiencia(parseExperience(cachedRowSet.getInt(19)));
            rows.add(row);
       
        }
		try{if (cachedRowSet!=null) cachedRowSet.close();}catch(Exception e){e.printStackTrace();}
		try{if (stmt!=null) stmt.close();}catch(Exception e){e.printStackTrace();}
		/ ** Se esta utilizando una sola conexion para diversas consultas, por lo tanto no se cierra ** /
		if (!isGlobalConnection()){
			try{if (conn!=null) conn.close();}catch(Exception e){e.printStackTrace();}	
		}
        return rows;
    }*/
    
    /*private String parseExperience(int source) {
    	StringBuilder experience = new StringBuilder();
    	switch (source) {
    		case 1: experience.append(EXPERIENCIA.NINGUNA.getOpcion()); break;
    		case 2: experience.append(EXPERIENCIA.MENOR_UNO.getOpcion());break;
    		case 3: experience.append(EXPERIENCIA.MENOR_DOS.getOpcion());break;
    		case 4: experience.append(EXPERIENCIA.MENOR_TRES.getOpcion());break;
    		case 5: experience.append(EXPERIENCIA.MENOR_CUATRO.getOpcion());break;
    		case 6: experience.append(EXPERIENCIA.MENOR_CINCO.getOpcion());break;
    		case 7: experience.append(EXPERIENCIA.MAS_CINCO.getOpcion());break;
    		default : experience.append(EXPERIENCIA.NO_REQUISITO.getOpcion());
    	}
    	return experience.toString();
    }

	private String parserQuery(String source, String pattern) {
		if (null == source || source.isEmpty())
			return "";
		else if (null == pattern || pattern.isEmpty())
			return source;
		source = source.toLowerCase();
		pattern = pattern.toLowerCase();
		String [] terms = pattern.split(" ");
		StringBuilder analizer = new StringBuilder();
		List<String> termList = new ArrayList<String>();
		for (int i=0; i<terms.length; i++)
			termList.add(terms[i]);
		String [] sources = source.split(" ");
		for (int j=0; j<sources.length; j++) {
			if (termList.contains(sources[j]))
				analizer.append(" <font style=\"color:red\">" + sources[j] + "</font>");
			else
				analizer.append(" " + sources[j]);
		}
		return analizer.toString().trim();
	}*/
	
	
	private String getUbucacionCanadaQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT CO.ID_CATALOGO_OPCION, CO.OPCION,CC.ID_CIUDAD,CC.CIUDAD ");
		sb.append("FROM CATALOGO_OPCION CO, CIUDAD_CANADA CC, OFERTA_UBICACION OU,OFERTA_EMPLEO OE  ");
		sb.append("WHERE OE.ID_OFERTA_EMPLEO = OU.ID_OFERTA_EMPLEO ");
		sb.append("AND CO.ID_CATALOGO =  "+Constantes.CATALOGO_OPCION_PROVINCIAS_CANADA);
		sb.append("AND OE.ID_OFERTA_EMPLEO = ? ");
		sb.append("AND OU.ID_ENTIDAD = CO.ID_CATALOGO_OPCION ");
		sb.append("AND OU.ID_ENTIDAD = CC.ID_PROVINCIA ");
		sb.append("AND OU.ID_MUNICIPIO = CC.ID_CIUDAD ");
		
		
		return sb.toString();
	}
}
