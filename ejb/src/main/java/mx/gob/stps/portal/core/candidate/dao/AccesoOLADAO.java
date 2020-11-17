package mx.gob.stps.portal.core.candidate.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO;
import mx.gob.stps.portal.core.infra.utils.Constantes.GRADO_ESTUDIOS;

public class AccesoOLADAO extends TemplateDAO{
	private static final int QUERY_OCUPACION_PROPIETARIO = 1;
	private static final int QUERY_CARRERA_PROPIETARIO = 2;
	private static final int QUERY_OCUPACION = 11;
	private static final int QUERY_CARRERA = 22;
	
	private int QUERY = 0;
	private int ID_CATALOGO = 45;
	
	//public AccesoOLADAO(){}
	
	public AccesoOLADAO(Connection globalConnection){
		super(globalConnection);
	}
	
	/**
	 * Realiza la consulta para obtener la ocupacion del usuario que se logueo al portal
	 * @param idCandidato
	 * @return
	 * @throws SQLException
	 */
	public String getOcupacionPropietario(long idCandidato) throws SQLException {
		QUERY = QUERY_OCUPACION_PROPIETARIO;
		String ocupacionProp = "";
		String []params = {String.valueOf(idCandidato)};
		CachedRowSet rs = this.executeQuery(params);
		if (rs.next()){
			ocupacionProp = rs.getString(1);
		}
		return ocupacionProp;
	}
	
	/**
	 * Realiza la consulta para obtener la carrera del usuario que se logueo al portal
	 * @param idCandidato el usuario que se logueo
	 * @return la carrera en el PE del usuario que se logueo 
	 * @throws SQLException
	 */
	public String getCarreraPropietario(long idCandidato) throws SQLException {
		QUERY = QUERY_CARRERA_PROPIETARIO;
		String carreraProp = "";
		String []params = {String.valueOf(idCandidato)};
		CachedRowSet rs = this.executeQuery(params);

		if (rs.next()){
			carreraProp = rs.getString(1);
			
			if (rs.getLong(2) == GRADO_ESTUDIOS.CARRERA_COMERCIAL.getIdOpcion() || 
				rs.getLong(2) == GRADO_ESTUDIOS.CARRERA_TECNICA.getIdOpcion()){
				
				ID_CATALOGO = CATALOGO.TECNICA_COMERCIAL_BASICA.getIdCatalogo();
			
			}else if (rs.getLong(2) == GRADO_ESTUDIOS.PROFESIONAL_TECNICO.getIdOpcion() || 
					  rs.getLong(2) == GRADO_ESTUDIOS.CARRERA_TECNICA.getIdOpcion()){
			
				ID_CATALOGO = CATALOGO.TECNICA_COMERCIAL_SUPERIOR.getIdCatalogo();

			
			}else if (rs.getLong(2) == GRADO_ESTUDIOS.SUPERIOR_UNIVERSITARIO.getIdOpcion() || 
					rs.getLong(2) == GRADO_ESTUDIOS.LICENCIATURA.getIdOpcion()){
				
				ID_CATALOGO = CATALOGO.PROFESIONAL.getIdCatalogo();
				
			}else if (rs.getLong(2) == GRADO_ESTUDIOS.MAESTRIA.getIdOpcion() || 
					 rs.getLong(2) == GRADO_ESTUDIOS.DOCTORADO.getIdOpcion()){

				ID_CATALOGO = CATALOGO.MAESTRIA_DOCTORADO.getIdCatalogo();
			}
		}
		return carreraProp+ "#" + ID_CATALOGO;
	}
	
	/**
	 * En base a id de ocupacion del PE obtiene el id de ocupacion del OLA  
	 * @param ocupacionPortal el id de ocupacion en el portal
	 * @return el id de ocupacion en el OLA concatenado  con el caracter % el nombre de la ocupacion en el PE 
	 * @throws SQLException
	 */
	public String getOcupacionOLA(String ocupacionPortal) throws SQLException{
		QUERY =	QUERY_OCUPACION;
		String ocupacionOLA = "";
		String []params = {ocupacionPortal};
		CachedRowSet rs = this.executeQuery(params);
		if (rs.next()){
			ocupacionOLA = rs.getString(1);
			ocupacionOLA = ocupacionOLA + "%" + rs.getString(2);
		}
		return ocupacionOLA;
	}
	
	/**
	 * En base a id de carrera del PE obtiene el id de carrera del OLA  
	 * @param carreraPortal id de carrera en el portal
	 * @return el id de carrera en el OLA concatenado  con el caracter % el nombre de la carrera en el PE 
	 * @throws SQLException
	 */
	public String getCarreraOLA(String carreraPortal, int idCatalogo) throws SQLException{
		QUERY =	QUERY_CARRERA;
		String carreraOLA = "";
		if (idCatalogo != CATALOGO.SIN_ESPECIALIDAD.getIdCatalogo()){
		String []params = {String.valueOf(idCatalogo), carreraPortal};
		CachedRowSet rs = this.executeQuery(params);
			if (rs.next()){
			carreraOLA = rs.getString(1);
			carreraOLA = carreraOLA + "%" + rs.getString(2);
			}
		}
		return carreraOLA;
	}
	
	@Override
	protected String getQuery() {
		StringBuffer query = new StringBuffer();
		if(QUERY == QUERY_OCUPACION_PROPIETARIO) {
			query.append(" SELECT ID_OCUPACION_DESEADA ");
			query.append(" FROM EXPECTATIVA_LABORAL ");
			query.append(" WHERE ID_CANDIDATO = ?");
		} else if(QUERY == QUERY_CARRERA_PROPIETARIO){
			query.append(" SELECT ID_CARRERA_ESPECIALIDAD, ID_NIVEL_ESTUDIO ");
			query.append(" FROM CANDIDATO_GRADO_ACADEMICO ");
			query.append(" WHERE PRINCIPAL = 1 ");
			query.append(" AND ID_CANDIDATO = ? ");
		}else if (QUERY == QUERY_OCUPACION){
			query.append(" SELECT B.ID_OCUPACION_OLA, A.OPCION  ");
			query.append(" FROM CATALOGO_OPCION A, OCUPACION_OLA B ");
			query.append(" WHERE ID_CATALOGO = ");
			query.append( CATALOGO.OCUPACION.getIdCatalogo());
			query.append(" 	AND A.ID_CATALOGO_OPCION = B.ID_OCUPACION ");
			query.append("	AND B.ID_OCUPACION = ?");
		}else if(QUERY == QUERY_CARRERA){
			query.append(" SELECT B.ID_CARRERA, A.OPCION ");
			query.append(" FROM CATALOGO_OPCION A, CARRERA_OLA B ");
			query.append(" WHERE A.ID_CATALOGO = ? ");
			query.append(" 	AND A.ID_CATALOGO_OPCION = B.ID_CARRERA_ESPEC ");
			query.append("  AND B.ID_CARRERA_ESPEC = ?");
		}
		return query.toString();
	}
	

}
