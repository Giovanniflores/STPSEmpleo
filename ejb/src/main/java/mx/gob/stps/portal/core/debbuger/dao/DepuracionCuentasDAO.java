package mx.gob.stps.portal.core.debbuger.dao;

import java.sql.Connection;
import java.sql.SQLException;

import mx.gob.stps.portal.core.candidate.dao.CandidatoDAO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;

import org.apache.log4j.Logger;

public class DepuracionCuentasDAO extends TemplateDAO{
	
	private static Logger logger = Logger.getLogger(CandidatoDAO.class);

	private int QUERY = 0;
	private final static int DELETE_CAND_IDIOMA= 1;
	private final static int DELETE_CAND_GRADO_ACAD = 2;
	private final static int DELETE_CAND_CONOC_HAB = 3;
	private final static int DELETE_CAND_COMPU_AVANZADA = 4;
	private final static int DELETE_CAND = 5;
	private final static int DELETE_USUARIOS_CAND = 6;	
	
	private final static int DELETE_EMP = 7;
	private final static int DELETE_USUARIOS_EMP = 8;
	private final static int DELETE_EMP_POR_AUTORIZAR = 9;
	
	private final static int SELECT = 10;
	
	/*private DepuracionCuentasDAO(){
		inicializa();
	}*/
	
	private DepuracionCuentasDAO(Connection connectionGlobal){
		super(connectionGlobal);
		inicializa();
	}
	
	private static void inicializa(){
	}
	
	/*public static DepuracionCuentasDAO getInstance(){
		return new DepuracionCuentasDAO();
	}*/

	public static DepuracionCuentasDAO getInstanceConnectionGlobal(Connection connectionGlobal){
		return new DepuracionCuentasDAO(connectionGlobal);
	}
		
	@Override
	protected String getQuery() {
		StringBuilder query = new StringBuilder();
		
		switch (QUERY){
			case DELETE_CAND_IDIOMA:
				query = getQueryDeleteCandidatosIdioma(); 
				break;
			case DELETE_CAND_GRADO_ACAD:
				query = getQueryDeleteCandidatoGradoAcademico();
				break;
			case DELETE_CAND_CONOC_HAB:
				query = getQueryDeleteCandidatosConocHabilidad();
				break;
			case DELETE_CAND_COMPU_AVANZADA:
				query = getQueryDeleteCandidatosCompuAvanzada();
				break;
			case DELETE_CAND:
				query = getQueryDeleteCandidatos();
				break;				
			case DELETE_USUARIOS_CAND:
				query = getQueryDeleteUsuariosCandidatos();
				break;
								
			case DELETE_EMP:
				query = getQueryDeleteEmpresas();
				break;
			case DELETE_USUARIOS_EMP:
				query = getQueryDeleteUsuariosEmp();
				break;
											
			case DELETE_EMP_POR_AUTORIZAR:
				break;							
		}
		
		
		return query.toString();
	}
	
	
	/* Query que regresa los ids de usuario que tienen estatus de INACTIVO y que han pasado 
	 * determinado numero de dias (NUMERO_DIAS_CADUCAN_CUENTAS), sin que se haya modificado el estatus.
	 * Excluyendo el usuario anonimo.	 
	 * La consulta se puede realizar por el tipo de usuario.
	 */
	
	private String getQueryIdUsuariosCaducanByTipo(int tipoUser){
		StringBuilder query = new StringBuilder();		
		query.append("  SELECT ID_USUARIO "); 
		query.append("  FROM USUARIO   ");
		query.append("  WHERE trunc(fecha_modificacion + " + Constantes.NUMERO_DIAS_CADUCAN_CUENTAS + ") <= TRUNC(SYSDATE) ");
		query.append("  AND estatus = " + ESTATUS.INACTIVO.getIdOpcion() );
		query.append("  AND id_tipo_usuario = " + tipoUser);
		query.append("  AND id_usuario <> " +  Constantes.ID_USUARIO_ANONIMO );		
		//logger.info("getQueryIdUsuariosCaducanByTipo:  " + query.toString());
		return query.toString();		
	}
	
		
	
	/* Query que regresa los candidatos que caducan en el periodo determinado*/
	
	private String getQueryIdCandidatosCaducan(){
		StringBuilder query = new StringBuilder();
		String qryUsersCaducan;		
		qryUsersCaducan = getQueryIdUsuariosCaducanByTipo(TIPO_USUARIO.CANDIDATO.getIdTipoUsuario());		
		query.append(" SELECT id_candidato "); 
		query.append(" FROM CANDIDATO ");
		query.append(" WHERE id_usuario IN (" + qryUsersCaducan+ ")");		
		//logger.info("getQueryIdCandidatosCaducan: " + query.toString());
        return query.toString();		
	}
			
	
	/*Query que ELIMINA de CANDIDATO_IDIOMA, los candidatos que caducan*/
	
	private StringBuilder getQueryDeleteCandidatosIdioma(){		
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM CANDIDATO_IDIOMA ");
		query.append(" WHERE id_candidato in ( ");		
		query.append(      getQueryIdCandidatosCaducan() );
		query.append( " ) ");				
		//logger.info("getQueryDeleteCandidatosIdioma: " +  query.toString());
		return query;
     }
	
	
	public int deleteCandIdioma(){		
		QUERY = DELETE_CAND_IDIOMA;
		int reg = -1;
         try {
			  reg = executeUpdate();
		} catch (SQLException e) {		
			logger.error(e);
		}
		return reg;
	}
	
	public int select(){		
		QUERY = SELECT;
		int reg = -1;
         try {
			  reg = executeUpdate();
		} catch (SQLException e) {		
			logger.error(e);
		}
		return reg;
	}
	
	
	
	public int deleteCandGradoAcademico(){		
		QUERY = DELETE_CAND_GRADO_ACAD;
		int reg = -1;
         try {
			  reg = executeUpdate();
		} catch (SQLException e) {		
			logger.error(e);
		}
		return reg;
	}
	
	public int deleteCandConocHab(){		
		QUERY = DELETE_CAND_CONOC_HAB;
		
		int reg = -1;
         try {
			  reg = executeUpdate();
		} catch (SQLException e) {		
			logger.error(e);
		}
		return reg;
	}
	
	
	public int deleteCandCompuAvanzada(){		
		QUERY = DELETE_CAND_COMPU_AVANZADA;
		
		int reg = -1;
         try {
			  reg = executeUpdate();
		} catch (SQLException e) {		
			logger.error(e);
		}
		return reg;
	}
	
	
	public int deleteCand(){		
		QUERY = DELETE_CAND;
		
		int reg = -1;
         try {
			  reg = executeUpdate();
		} catch (SQLException e) {		
			logger.error(e);
		}
		return reg;
	}
	
	public int deleteUsuariosCand(){
		QUERY = DELETE_USUARIOS_CAND;
		
		int reg = -1;
        try {
			  reg = executeUpdate();
		} catch (SQLException e) {		
			logger.error(e);
		}
		return reg;
	}
	
			
	/*Query que elimina de la tabla de CANDIDATO_GRADO_ACADEMICO, los candidatos que caducan*/
	
	private StringBuilder getQueryDeleteCandidatoGradoAcademico(){
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM candidato_grado_academico ");
		query.append( " WHERE id_candidato in ( ");		
		query.append(      getQueryIdCandidatosCaducan() );
		query.append( " ) ");				
		//logger.info("getQueryDeleteCandidatoGradoAcademico: " + query.toString());
		return query;
	}
		
	/*Query que elimina de la tabla CANDIDATO_CONOC_HABILIDAD, los candidatos que caducan*/
	private StringBuilder getQueryDeleteCandidatosConocHabilidad(){
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM candidato_conocim_habilidad ");
		query.append( " WHERE id_candidato in ( ");		
		query.append(      getQueryIdCandidatosCaducan() );
		query.append( " ) ");				
		//logger.info("getQueryDeleteCandidatosConocHabilidad: " + query.toString());
		return query;
	}
		
	/*Query que borra de la tabla de CANDIDATO_COMPU_AVANZADA, los candidatos que caducan en 
	 * el periodo definido por NUMERO_DIAS_CADUCAN_CUENTAS */
	private StringBuilder getQueryDeleteCandidatosCompuAvanzada(){
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM candidato_compu_avanzada ");
		query.append( " WHERE id_candidato in ( ");		
		query.append(      getQueryIdCandidatosCaducan() );
		query.append( " ) ");				
		//logger.info("getQueryDeleteCandidatosCompuAvanzada: " + query.toString());
		return query;		
	}
	/*Query que elimina de la tabla de CANDIDATOS, los candidatos que caducan**/
	private StringBuilder getQueryDeleteCandidatos(){
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM candidato ");
		query.append( " WHERE id_candidato in ( ");		
		query.append(      getQueryIdCandidatosCaducan() );
		query.append( " ) ");		
		//logger.info("getQueryDeleteCandidatos: " + query.toString());
		return query;		
	}
	
	/*Query que elimina de Usuarios las cuentas que caducan*/
	private StringBuilder getQueryDeleteUsuariosCandidatos(){
		StringBuilder query = new StringBuilder();
		String qryUsersCaducan;		
		qryUsersCaducan = getQueryIdUsuariosCaducanByTipo(TIPO_USUARIO.CANDIDATO.getIdTipoUsuario());		
		query.append( " DELETE FROM usuario where id_usuario in (" + qryUsersCaducan +")");		
		//logger.info("getQueryDeleteUsuarios: " + query.toString() );
		return query;		
	}
	
	
	
	
	
	private StringBuilder getQueryDeleteEmpresas(){		
		StringBuilder query = new StringBuilder();
		String qryUsersCaducan;		
		qryUsersCaducan = getQueryIdUsuariosCaducanByTipo(TIPO_USUARIO.EMPRESA.getIdTipoUsuario());		
		query.append( " DELETE FROM empresa " );
		query.append( " WHERE id_usuario in (" + qryUsersCaducan +")");			
		//logger.info("getQueryDeleteEmpresas: " + query.toString() );		
		return query;
	}
	
	
	public int deleteEmp(){
		QUERY = DELETE_EMP;
		
		int reg = -1;
        try {
			  reg = executeUpdate();
		} catch (SQLException e) {		
			logger.error(e);
		}
		return reg;
	}
	
	
	public int deleteUsuariosEmp(){
		QUERY = DELETE_USUARIOS_EMP;
		
		int reg = -1;
        try {
			  reg = executeUpdate();
		} catch (SQLException e) {		
			logger.error(e);
		}
		return reg;
	}
	
	
	private StringBuilder getQueryDeleteUsuariosEmp(){
		
		StringBuilder query = new StringBuilder();
		String qryUsersCaducan;		
		qryUsersCaducan = getQueryIdUsuariosCaducanByTipo(TIPO_USUARIO.EMPRESA.getIdTipoUsuario());		
		query.append( " DELETE FROM usuario where id_usuario in (" + qryUsersCaducan +")");		
		//logger.info("getQueryDeleteUsuariosEmp: " + query.toString() );	
		return query;
		
	}
	
	
	
	
	
}