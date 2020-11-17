package mx.gob.stps.portal.core.entrevista.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.EntrevistaVO;

import org.apache.log4j.Logger;

/**
 * @author jose.hernandez
 *
 */
public  class EntrevistaDao  extends TemplateDAO {
	private static final Logger logger = Logger.getLogger(EntrevistaDao.class);
	
	private String CONSULTA_ENTREVISTA;

	/** buscaEntrevistaProgramada
	 * @param opcion
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<EntrevistaVO> buscaEntrevistaProgramada(PERFIL perfil, long id) throws SQLException{
		return buscaEntrevistaProgramada(id, getSqlEntrevistaProgramada(perfil));
	}
	
	/** buscaEntrevistaProgramada
	 * @param opcion
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public EntrevistaVO buscaEntrevistaOfertaCandidatoActiva(long idCandidato, long idOfertaEmpleo) throws SQLException{
		return buscaEntrevistaOfertacandidatoActiva(idCandidato, idOfertaEmpleo, getSqlEntrevistaOfertaCandidatoActiva());
	}
	
	/** buscaEntrevistaProgramada
	 * @param opcion
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<EntrevistaVO> buscaEntrevistaProgramadaEnLinea(PERFIL perfil, long id) throws SQLException{
		return buscaEntrevistaProgramada(id, getSqlEntrevistaProgramadaEnLinea(perfil));
	}
	
	/** Entrevista disponibles
	 * @param idCandidato
	 * @return
	 * @throws SQLException
	 */
	private List<EntrevistaVO> buscaEntrevistaProgramada(long id, String sql) throws SQLException{
		List<EntrevistaVO> 	listEntrevistaVo = new ArrayList<EntrevistaVO>();	
	
		EntrevistaVO entrevistaVo = null;
		this.CONSULTA_ENTREVISTA = sql;
		Long[] parametros = { id };
		CachedRowSet cachedRowSet = executeQuery(parametros);			
		try {				
			while (cachedRowSet.next()) {
				entrevistaVo = new EntrevistaVO();
					entrevistaVo.setTituloOferta(cachedRowSet.getString(1));
					entrevistaVo.setNombre(cachedRowSet.getString(2));
					entrevistaVo.setContactoEmpresa(cachedRowSet.getString(3));					
					entrevistaVo.setFechaString(Utils.getFechaFormato(cachedRowSet.getDate(4)));
					entrevistaVo.setHora(cachedRowSet.getString(5));
					entrevistaVo.setEstatus(cachedRowSet.getInt(6));
					entrevistaVo.setIdEntrevista(cachedRowSet.getInt(7));
					entrevistaVo.setRazonSocial(null != cachedRowSet.getString(8) ? cachedRowSet.getString(8) : "");
					entrevistaVo.setFechaFin(Utils.getFechaFormato(cachedRowSet.getDate(9)));
					entrevistaVo.setCorreoCandidato(cachedRowSet.getString(10));
					entrevistaVo.setCorreoEmpresa(cachedRowSet.getString(11));	
					entrevistaVo.setIdCandidato(cachedRowSet.getLong(12));
					entrevistaVo.setIdEmpresa(cachedRowSet.getLong(13));	
				listEntrevistaVo.add(entrevistaVo);				
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return listEntrevistaVo;		
	}
	
	/** Entrevista activa del candidato segun la oferta
	 * @param idCandidato
	 * @return
	 * @throws SQLException
	 */
	private EntrevistaVO buscaEntrevistaOfertacandidatoActiva(long idCandidato, long idOfertaEmpleo, String sql) throws SQLException{
		
		EntrevistaVO entrevistaVo = new EntrevistaVO();
		this.CONSULTA_ENTREVISTA = sql;
		Long[] parametros = { idCandidato,idOfertaEmpleo };
		CachedRowSet cachedRowSet = executeQuery(parametros);			

		try {					
			while (cachedRowSet.next()) {
				entrevistaVo = new EntrevistaVO();
					entrevistaVo.setTituloOferta(cachedRowSet.getString(1));
					entrevistaVo.setNombre(cachedRowSet.getString(2));
					entrevistaVo.setContactoEmpresa(cachedRowSet.getString(3));					
					entrevistaVo.setFechaString(Utils.getFechaFormato(cachedRowSet.getDate(4)));
					entrevistaVo.setHora(cachedRowSet.getString(5));
					entrevistaVo.setEstatus(cachedRowSet.getInt(6));
					entrevistaVo.setIdEntrevista(cachedRowSet.getInt(7));
					entrevistaVo.setRazonSocial(cachedRowSet.getString(8));
					entrevistaVo.setFechaFin(Utils.getFechaFormato(cachedRowSet.getDate(9)));
					entrevistaVo.setCorreoCandidato(cachedRowSet.getString(10));
					entrevistaVo.setCorreoEmpresa(cachedRowSet.getString(11));
					entrevistaVo.setIdCandidato(idCandidato);					
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		return entrevistaVo;	
	}
	
	
	/** Entrevista activa del candidato segun la oferta
	 * @param idCandidato
	 * @return
	 * @throws SQLException
	 */
	public Boolean validarEntrevistaEnTiempo(long idEntrevista) throws SQLException{
		
		Boolean bandera = false;		
		this.CONSULTA_ENTREVISTA = getSqlEntrevistaProgramadaEnLineaValidacionTiempo();
		Long[] parametros = {idEntrevista };
		CachedRowSet cachedRowSet = executeQuery(parametros);
		
		try {
			if (cachedRowSet.next()) 
				bandera = true;			
			
		}catch (SQLException e) {
			logger.error(e);
			throw new SQLException(e);
		}
		
		return bandera;	
	}
	
	
	/** Consulta para la ejecucion de sql
	 * @param opcion 
	 * @return String
	 */			
		private String getSqlEntrevistaProgramada(PERFIL perfil){
			StringBuilder sb = new StringBuilder();
			
			sb.append("		SELECT																				");			
			sb.append("		   EMP.titulo_oferta,																");			
			sb.append("		   sol.nombre||' '|| sol.apellido1 ||' '|| sol.apellido2  ,							");
			sb.append("		   EMP.NOMBRE_CONTACTO,																");
			sb.append("		   ENT.FECHA,																		");
			sb.append("		   ENT.HORA,																		");
			sb.append("		   ENT.ESTATUS,																		");
			sb.append("		   ent.id_entrevista,																");
			sb.append(" 	   EMP.NOMBRE_EMPRESA, 																");
			sb.append("		   EMP.fecha_fin,																	");
			sb.append("		   can.correo_electronico,															");
			sb.append("		   EMP.correo_electronico_contacto,													");
			sb.append("		   CAN.id_candidato,																");
			sb.append("		   EMP.ID_EMPRESA																	");			
			sb.append("		FROM ENTREVISTA ENT																	");
			sb.append("		JOIN CANDIDATO CAN ON(ENT.id_candidato = CAN.id_candidato)							");
			sb.append(" 	JOIN SOLICITANTE SOL ON(CAN.id_candidato = SOL.id_candidato)						");
			sb.append("		JOIN OFERTA_EMPLEO EMP ON(ENT.ID_OFERTA_EMPLEO = EMP.ID_OFERTA_EMPLEO )				");		
			sb.append("		WHERE  TO_DATE(to_char(fecha,'dd/mm/yyyy')||' '||HORA,'dd/mm/yyyy HH24:MI') >=		");
			sb.append("			   TO_DATE(to_char(sysdate,'dd/mm/yyyy HH24:MI'), 'dd/mm/yyyy HH24:MI')			");
			sb.append("		AND    EMP.fecha_fin   	>= TO_DATE(sysdate,'dd/mm/yyy')								");
			
			if(PERFIL.CANDIDATO == perfil)
			sb.append("		 AND CAN.id_candidato 	= ?															");
			
			if(PERFIL.EMPRESA == perfil)
			sb.append("		 AND EMP.id_empresa		= ?															");

		return sb.toString();
	}

	/** 
	 * Consulta para la ejecucion de sql conferencia de 15 mn
	 * @return String
	 */	
	private String getSqlEntrevistaProgramadaEnLinea(PERFIL perfil){
		StringBuilder sb = new StringBuilder();
	
			sb.append("		SELECT																				");			
			sb.append("		   EMP.titulo_oferta,																");			
			sb.append("		   sol.nombre||' '|| sol.apellido1 ||' '||sol.apellido2  ,							");
			sb.append("		   EMP.NOMBRE_CONTACTO,																");
			sb.append("		   ENT.FECHA,																		");
			sb.append("		   ENT.HORA,																		");
			sb.append("		   ENT.ESTATUS,																		");
			sb.append("		   ent.id_entrevista,																");
			sb.append(" 	   EMP.NOMBRE_EMPRESA, 																");
			sb.append("		   EMP.fecha_fin,																	");
			sb.append("		   can.correo_electronico,															");
			sb.append("		   EMP.correo_electronico_contacto,													");
			sb.append("		   CAN.id_candidato,																");
			sb.append("		   EMP.ID_EMPRESA																	");			
			sb.append("		FROM ENTREVISTA ENT																	");
			sb.append("		JOIN CANDIDATO CAN ON(ENT.id_candidato = CAN.id_candidato)							");
			sb.append("     JOIN SOLICITANTE SOL ON(SOL.id_candidato = CAN.id_candidato)  						");
			sb.append("		JOIN OFERTA_EMPLEO EMP ON(ENT.ID_OFERTA_EMPLEO = EMP.ID_OFERTA_EMPLEO )				");
			sb.append("		WHERE  TO_DATE(to_char(fecha,'dd/mm/yyyy')||' '||HORA,'dd/mm/yyyy HH24:MI') 		");
			sb.append("			BETWEEN 																		");
			sb.append("	    TO_DATE(to_char(sysdate - 15 / 1440,'dd/mm/yyyy HH24:MI'), 'dd/mm/yyyy HH24:MI')	");
			sb.append("			AND		 																		");
			sb.append("	    TO_DATE(to_char(sysdate + 15 / 1440,'dd/mm/yyyy HH24:MI'), 'dd/mm/yyyy HH24:MI')    ");
			sb.append("		AND    EMP.fecha_fin   	>= TO_DATE(sysdate,'dd/mm/yyy')								");
			sb.append("		AND  ENT.ESTATUS		= " + ESTATUS.ACEPTADA.getIdOpcion()+ "						");
			
			if(PERFIL.CANDIDATO == perfil)			
			sb.append("		 AND CAN.id_candidato 	= ?															");
			
			if(PERFIL.EMPRESA == perfil)
			sb.append("		 AND EMP.id_empresa		= ?															");

		return sb.toString();
	}
	
	
	/** 
	 * Consulta para la ejecucion de sql conferencia de 15 mn
	 * @return String
	 */	
	private String getSqlEntrevistaProgramadaEnLineaValidacionTiempo(){
		StringBuilder sb = new StringBuilder();
	
			sb.append("		SELECT COUNT(*)																		");
			sb.append("		FROM ENTREVISTA ENT																	");
			sb.append("		JOIN CANDIDATO CAN ON(ENT.id_candidato = CAN.id_candidato)							");
			sb.append("		JOIN OFERTA_EMPLEO EMP ON(ENT.ID_OFERTA_EMPLEO = EMP.ID_OFERTA_EMPLEO )				");
			sb.append("		JOIN EMPRESA ESA ON(EMP.ID_EMPRESA = ESA.ID_EMPRESA)								");			
			sb.append("		WHERE  TO_DATE(to_char(fecha,'dd/mm/yyyy')||' '||HORA,'dd/mm/yyyy HH24:MI') 		");
			sb.append("			BETWEEN 																		");
			sb.append("	    TO_DATE(to_char(sysdate - 15 / 1440,'dd/mm/yyyy HH24:MI'), 'dd/mm/yyyy HH24:MI')	");
			sb.append("			AND		 																		");
			sb.append("	    TO_DATE(to_char(sysdate + 15 / 1440,'dd/mm/yyyy HH24:MI'), 'dd/mm/yyyy HH24:MI')    ");
			sb.append("		AND  EMP.fecha_fin   	>= TO_DATE(sysdate,'dd/mm/yyy')								");
			sb.append("		AND  ENT.ESTATUS		= " + ESTATUS.ACEPTADA.getIdOpcion() + "						");
			sb.append("		AND  ent.id_entrevista	= ?															");
			
			System.out.println("getSqlEntrevistaProgramadaEnLineaValidacionTiempo-.>" + sb.toString());

		return sb.toString();
	}
 	

	/** Consulta para la ejecucion de sql
	 * obtiene la entrevista activa de la oferta y el candidato
	 * @return String
	 */	
	private String getSqlEntrevistaOfertaCandidatoActiva(){
		StringBuilder sb = new StringBuilder();

	
			sb.append("		SELECT																				");			
			sb.append("		   EMP.titulo_oferta,																");			
			sb.append("		   sol.nombre||' '|| sol.apellido1 ||' '||sol.apellido2  ,							");
			sb.append("		   EMP.NOMBRE_CONTACTO,																");
			sb.append("		   ENT.FECHA,																		");
			sb.append("		   ENT.HORA,																		");
			sb.append("		   ENT.ESTATUS,																		");
			sb.append("		   ent.id_entrevista,																");
			sb.append(" 	   EMP.NOMBRE_EMPRESA, 																");
			sb.append("		   EMP.fecha_fin,																	");
			sb.append("		   can.correo_electronico,															");
			sb.append("		   EMP.correo_electronico_contacto													");						
			sb.append("		FROM ENTREVISTA ENT																	");
			sb.append("		JOIN CANDIDATO CAN ON(ENT.id_candidato = CAN.id_candidato)							");
			sb.append("     JOIN SOLICITANTE SOL ON(SOL.id_candidato = CAN.id_candidato)						");
			sb.append("		JOIN OFERTA_EMPLEO EMP ON(ENT.ID_OFERTA_EMPLEO = EMP.ID_OFERTA_EMPLEO )				");
			sb.append("		WHERE  TO_DATE(to_char(fecha,'dd/mm/yyyy')||' '||HORA,'dd/mm/yyyy HH24:MI') >=		");
			sb.append("			   TO_DATE(to_char(sysdate,'dd/mm/yyyy HH24:MI'), 'dd/mm/yyyy HH24:MI')			");
			sb.append("		AND    EMP.fecha_fin   	>= TO_DATE(sysdate,'dd/mm/yyy')								");
			sb.append("		 AND CAN.id_candidato 	= ?															");
			sb.append("		 AND ENT.id_oferta_empleo 	= ?														");
			sb.append("		 AND (ENT.ESTATUS		= " + ESTATUS.ACEPTADA.getIdOpcion() + "						");
			sb.append("		   OR ENT.ESTATUS		= " + ESTATUS.NUEVA.getIdOpcion() + "							");
			sb.append("		   OR ENT.ESTATUS		= " + ESTATUS.REPROGRAMADA.getIdOpcion()+ ")					");
			
		return sb.toString();
	}

	@Override
	protected String getQuery() {		
		return CONSULTA_ENTREVISTA;
	}

}
