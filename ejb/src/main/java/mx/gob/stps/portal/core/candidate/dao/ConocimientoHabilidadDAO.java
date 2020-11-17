//package mx.gob.stps.portal.core.candidate.dao;
//
//import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.sql.rowset.CachedRowSet;
//
//import org.apache.log4j.Logger;
//
//import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
//import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
//import mx.gob.stps.portal.core.infra.data.TemplateDAO;
//import mx.gob.stps.portal.core.infra.utils.Constantes;
//
//public class ConocimientoHabilidadDAO extends TemplateDAO {
//	
//	private static Logger logger = Logger.getLogger(ConocimientoHabilidadDAO.class);
//
//	private String consulta; 
//	
//	public ConocimientoHabilidadDAO(Connection globalConnection){
//		super(globalConnection);
//	}	
//	
//	@Override
//	protected String getQuery() {
//
//		if (consulta.equals("OBTENER_CONOCIMIENTOS")) {
//			return this.setQueryConocimientos();
//		} else if (consulta.equals("OBTENER_HABILIDADES")) {
//			return this.setQueryHabilidades();
//		}
//		return null;
//	}
//
//	/**
//	 * Genera la cadena que representa la consulta a base de datos para obtener
//	 * los conocimientos del Candidato para el despliegue del detalle del candidato.
//	 * @return un objeto {@code String} que representa la consulta parametrizada 
//	 * a la base de datos para obtener la informaci&oacute;n especificada.
//	 */
//	private String setQueryConocimientos() {
//		
//		StringBuilder query = new StringBuilder(128);
//		
//		query.append("SELECT id_candidato_conocim_habilidad, id_candidato, ");
//		query.append("id_tipo_conocim_habilidad, conocimiento_habilidad, ");
//		query.append("id_experiencia, id_dominio, descripcion, fecha_alta, ");
//		query.append("principal FROM candidato_conocim_habilidad "); 
//		query.append("WHERE id_candidato = ? AND id_tipo_conocim_habilidad = ");
//		query.append(Constantes.CONOC_HAB.CONOCIMIENTO.getIdOpcion());
//		query.append(" AND ROWNUM <= ");
//		query.append(Constantes.CONOC_HABIL_MAX_RESULTADOS);
//		query.append(" ORDER BY principal ASC");
//		logger.debug(query.toString());
//		return query.toString();
//	}
//	
//	/**
//	 * Extrae de la base de datos, los conocimientos del candidato para 
//	 * mostrarlos en las pantallas de detalle del candidato.
//	 * @param idCandidato representa el identificador del candidato del cual se
//	 *        quiere obtener la informaci&oacute;n
//	 * @return un conjunto de objetos {@code ConocimientoHabilidadVO} que 
//	 * 		   contiene la informaci&oacute;n a mostrar
//	 * @throws SQLException en caso de ocurrir alg&uacute;n problema al consultar en la BD
//	 */
//	public ArrayList<ConocimientoHabilidadVO> buscarConocimientos(long idCandidato)
//			throws SQLException {
//		
//		consulta = "OBTENER_CONOCIMIENTOS";
//		Object[] parametros = {new Long(idCandidato)};
//		CachedRowSet cachedRowSet = executeQuery(parametros);
//		ArrayList<ConocimientoHabilidadVO> conocimientos = new ArrayList<ConocimientoHabilidadVO>();
//	
//		while (cachedRowSet.next()) {
//			ConocimientoHabilidadVO vo = new ConocimientoHabilidadVO();
//			vo.setIdCandidatoConocimHabilidad(cachedRowSet.getLong(1));
//			vo.setIdTipoConocimHabilidad(cachedRowSet.getLong(3));
//			vo.setConocimientoHabilidad(cachedRowSet.getString(4));
//			vo.setIdExperiencia(cachedRowSet.getLong(5));
//			vo.setIdDominio(cachedRowSet.getLong(6));
//			vo.setDescripcion(cachedRowSet.getString(7));
//			vo.setPrincipal(cachedRowSet.getInt(9));
//			conocimientos.add(vo);
//		}
//		return conocimientos;
//	}
//	
//	/**
//	 * Genera la cadena que representa la consulta a base de datos para obtener
//	 * las habilidades del Candidato para el despliegue del detalle del candidato.
//	 * @return un objeto {@code String} que representa la consulta parametrizada 
//	 * a la base de datos para obtener la informaci&oacute;n especificada.
//	 */
//	private String setQueryHabilidades() {
//		
//		StringBuilder query = new StringBuilder(128);
//		
//		query.append("SELECT id_candidato_conocim_habilidad, id_candidato, ");
//		query.append("id_tipo_conocim_habilidad, conocimiento_habilidad, ");
//		query.append("id_experiencia, id_dominio, descripcion, fecha_alta, ");
//		query.append("principal FROM candidato_conocim_habilidad "); 
//		query.append("WHERE id_candidato = ? AND id_tipo_conocim_habilidad = ");
//		query.append(Constantes.CONOC_HAB.HABILIDAD.getIdOpcion());
//		query.append(" AND ROWNUM <= ");
//		query.append(Constantes.CONOC_HABIL_MAX_RESULTADOS);
//		query.append(" ORDER BY principal ASC");
//		logger.debug(query.toString());
//		return query.toString();
//	}
//	
//	/**
//	 * Extrae de la base de datos, las habilidades del candidato para 
//	 * mostrarlas en las pantallas de detalle del candidato.
//	 * @param idCandidato representa el identificador del candidato del cual se
//	 *        quiere obtener la informaci&oacute;n
//	 * @return un conjunto de objetos {@code ConocimientoHabilidadVO} que 
//	 * 		   contiene la informaci&oacute;n a mostrar
//	 * @throws SQLException en caso de ocurrir alg&uacute;n problema al consultar en la BD
//	 */
//	public ArrayList<ConocimientoHabilidadVO> buscarHabilidades(long idCandidato)
//			throws SQLException {
//		
//		consulta = "OBTENER_HABILIDADES";
//		Object[] parametros = {new Long(idCandidato)};
//		CachedRowSet cachedRowSet = executeQuery(parametros);
//		ArrayList<ConocimientoHabilidadVO> habilidades = new ArrayList<ConocimientoHabilidadVO>();
//	
//		while (cachedRowSet.next()) {
//			ConocimientoHabilidadVO vo = new ConocimientoHabilidadVO();
//			vo.setIdCandidatoConocimHabilidad(cachedRowSet.getLong(1));
//			vo.setIdTipoConocimHabilidad(cachedRowSet.getLong(3));
//			vo.setConocimientoHabilidad(cachedRowSet.getString(4));
//			vo.setIdExperiencia(cachedRowSet.getLong(5));
//			vo.setIdDominio(cachedRowSet.getLong(6));
//			vo.setDescripcion(cachedRowSet.getString(7));
//			vo.setPrincipal(cachedRowSet.getInt(9));
//			habilidades.add(vo);
//		}
//		return habilidades;
//	}
//	
//	/**
//	 * Metodo que realiza la busqueda de certificaciones de un candidato.
//	 * @param idCandidato Identificador del candidato.
//	 * @return Lista con las certificaciones.
//	 * @throws SQLException Excepcion en caso de un error de SQL.
//	 */
//	public List<String> buscarCertificaciones(long idCandidato) throws SQLException {
//		List<String> lista = new ArrayList<String>();
//		Object[] parametros = {new Long(idCandidato)};
//		CachedRowSet cachedRowSet = executeQueryCandidato(parametros, getQueryCertificaciones(idCandidato));
//		
//		while (cachedRowSet.next()) {
//			lista.add(cachedRowSet.getString("CERTIFICACION"));
//		}
//		return lista;
//	}
//	
//	/**
//	 * Metodo que arma el query para buscar las certificaciones
//	 * del candidato.
//	 * @param idCandidato Identificador del candidato.
//	 * @return String con el query armado.
//	 */
//	private String getQueryCertificaciones(long idCandidato){
//		StringBuilder query = new StringBuilder();
//		
//		query.append("SELECT F_DESC_CATALOGO(")
//			.append ("ID_CATALOGO")
//			.append(",ID_CATALOGO_OPCION) ")
//			.append("AS CERTIFICACION ")
//			.append("FROM CAN_CREDENCIAL_CERTIFICACION ")
//			.append("WHERE ID_CANDIDATO = ?");	
//		
//		logger.debug(query.toString());
//		
//		return query.toString();
//	}
//	
//	/**
//	 * Metodo que arma el query para buscar los Sistemas Especializados
//	 * del candidato.
//	 * @param idCandidato Identificador del candidato.
//	 * @return String con el query armado.
//	 */
//	public String buscarSistemas(long idCandidato) throws SQLException {
//		StringBuilder lista = new StringBuilder();
//		Object[] parametros = {new Long(idCandidato)};
//		CachedRowSet cachedRowSet = executeQueryCandidato(parametros, getQuerySistemas(idCandidato));
//		
//		while (cachedRowSet.next()) {
//			lista.append(", ").append(cachedRowSet.getString("SISTEMA"));
//			
//		}
//		return lista.toString().substring(2);
//	}
//	
//	/**
//	 * Metodo que arma el query para buscar los Sistemas Especializados
//	 * del candidato.
//	 * @param idCandidato Identificador del candidato.
//	 * @return String con el query armado.
//	 */
//	private String getQuerySistemas(long idCandidato){
//		StringBuilder query = new StringBuilder();
//		
//		query.append("SELECT F_DESC_CATALOGO(")
//			.append (Constantes.CATALOGO_SISTEMAS)
//			.append(",ID_SISTEMA_ESPECIALIZADO) ")
//			.append("AS SISTEMA ")
//			.append("FROM CAN_SISTEMA_ESPECIALIZADO ")
//			.append("WHERE ID_CANDIDATO = ?");		
//		logger.debug(query.toString());
//		return query.toString();
//	}
//		
//	/**
//	 * Metodo que arma el query para buscar conocimientos de computacion
//	 * del candidato.
//	 * @param idCandidato Identificador del candidato.
//	 * @return String con el query armado.
//	 */
//	public String buscarComputacion(long idCandidato) throws SQLException {
//		StringBuilder lista = new StringBuilder();
//		Object[] parametros = {new Long(idCandidato)};
//		CachedRowSet cachedRowSet = executeQueryCandidato(parametros, getQueryComputacion());
//		
//		while (cachedRowSet.next()) {
//			lista.append(", ").append(cachedRowSet.getString("ID"));
//			
//		}
//		return lista.toString().substring(2);
//	}
//	
//	public List<IdiomaVO> buscarIdiomas(long idCandidato) throws SQLException {
//		List<IdiomaVO> lista = new ArrayList<IdiomaVO>();
//		Object[] parametros = {new Long(idCandidato)};
//		logger.debug(getQueryIdiomas());
//		logger.debug(idCandidato);
//		CachedRowSet cachedRowSet = executeQueryCandidato(parametros, getQueryIdiomas());
//		IdiomaVO vo;
//		while (cachedRowSet.next()) {
//			vo = new IdiomaVO();
//			vo.setIdioma(cachedRowSet.getString("ID"));
//			vo.setIdDominioComprension(cachedRowSet.getInt("comprender"));
//			vo.setIdDominioEscrito(cachedRowSet.getInt("escribir"));
//			vo.setIdDominioHabla(cachedRowSet.getInt("hablar"));
//			vo.setIdDominioLectura(cachedRowSet.getInt("leer"));
//			lista.add(vo);
//		}
//		if(lista != null){
//			logger.debug("idioma.size: " + lista.size());
//		}
//		return lista;
//	}
//	
//	
//	/**
//	 * Metodo que arma el query para buscar conocimientos de computacion
//	 * del candidato.
//	 * @param idCandidato Identificador del candidato.
//	 * @return String con el query armado.
//	 */
//	private String getQueryComputacion(){
//		StringBuilder query = new StringBuilder();
//		
//		query.append("SELECT F_DESC_CATALOGO(")
//			.append (Constantes.CATALOGO_CERTIFICACION)
//			.append(",ID_SISTEMA_ESPECIALIZADO) ")
//			.append("AS ID ")
//			.append("FROM CAN_SISTEMA_ESPECIALIZADO ")
//			.append("WHERE ID_CANDIDATO = ? ");
//		logger.debug(query.toString());
//		return query.toString();
//	}
//	
//	private String getQueryIdiomas(){
//		StringBuilder query = new StringBuilder();
//
//		query.append("SELECT F_DESC_CATALOGO(" + CATALOGO_OPCION_IDIOMAS)
//			.append (", I.ID_IDIOMA) AS ID")
//			.append(",nvl(I.ID_DOMINIO_COMPRENSION,0) as comprender , nvl(I.ID_DOMINIO_ESCRITO,0) as escribir ,")
//			.append("nvl(I.ID_DOMINIO_HABLA,0) as hablar , nvl(I.ID_DOMINIO_LECTURA,0) as leer ")
//			.append("FROM CANDIDATO_IDIOMA I ")
//			.append("WHERE I.ID_CANDIDATO = ? ")
//			.append("AND( I.ID_DOMINIO_COMPRENSION is not NULL ")
//			.append("OR I.ID_DOMINIO_ESCRITO is not NULL ")
//			.append("OR I.ID_DOMINIO_HABLA is not NULL ")
//			.append("OR I.ID_DOMINIO_LECTURA is not NULL)");
//		logger.debug(query.toString());
//		return query.toString();
//	}
//}
// 

package mx.gob.stps.portal.core.candidate.dao;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import org.apache.log4j.Logger;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;

public class ConocimientoHabilidadDAO extends TemplateDAO {
	
	private static Logger logger = Logger.getLogger(ConocimientoHabilidadDAO.class);

	private String consulta; 
	
	public ConocimientoHabilidadDAO(Connection globalConnection){
		super(globalConnection);
	}	
	
	@Override
	protected String getQuery() {

		if (consulta.equals("OBTENER_CONOCIMIENTOS")) {
			return this.setQueryConocimientos();
		} else if (consulta.equals("OBTENER_HABILIDADES")) {
			return this.setQueryHabilidades();
		}
		return null;
	}

	/**
	 * Genera la cadena que representa la consulta a base de datos para obtener
	 * los conocimientos del Candidato para el despliegue del detalle del candidato.
	 * @return un objeto {@code String} que representa la consulta parametrizada 
	 * a la base de datos para obtener la informaci&oacute;n especificada.
	 */
	private String setQueryConocimientos() {
		
		StringBuilder query = new StringBuilder(128);
		
		query.append("SELECT id_candidato_conocim_habilidad, id_candidato, ");
		query.append("id_tipo_conocim_habilidad, conocimiento_habilidad, ");
		query.append("id_experiencia, id_dominio, descripcion, fecha_alta, ");
		query.append("principal FROM candidato_conocim_habilidad "); 
		query.append("WHERE id_candidato = ? AND id_tipo_conocim_habilidad = ");
		query.append(Constantes.CONOC_HAB.CONOCIMIENTO.getIdOpcion());
		query.append(" AND ROWNUM <= ");
		query.append(Constantes.CONOC_HABIL_MAX_RESULTADOS);
		query.append(" ORDER BY principal ASC");
		logger.debug(query.toString());
		return query.toString();
	}
	
	/**
	 * Extrae de la base de datos, los conocimientos del candidato para 
	 * mostrarlos en las pantallas de detalle del candidato.
	 * @param idCandidato representa el identificador del candidato del cual se
	 *        quiere obtener la informaci&oacute;n
	 * @return un conjunto de objetos {@code ConocimientoHabilidadVO} que 
	 * 		   contiene la informaci&oacute;n a mostrar
	 * @throws SQLException en caso de ocurrir alg&uacute;n problema al consultar en la BD
	 */
	public ArrayList<ConocimientoHabilidadVO> buscarConocimientos(long idCandidato)
			throws SQLException {
		
		consulta = "OBTENER_CONOCIMIENTOS";
		Object[] parametros = {new Long(idCandidato)};
		CachedRowSet cachedRowSet = executeQuery(parametros);
		ArrayList<ConocimientoHabilidadVO> conocimientos = new ArrayList<ConocimientoHabilidadVO>();
	
		while (cachedRowSet.next()) {
			ConocimientoHabilidadVO vo = new ConocimientoHabilidadVO();
			vo.setIdCandidatoConocimHabilidad(cachedRowSet.getLong(1));
			vo.setIdTipoConocimHabilidad(cachedRowSet.getLong(3));
			vo.setConocimientoHabilidad(cachedRowSet.getString(4));
			vo.setIdExperiencia(cachedRowSet.getLong(5));
			vo.setIdDominio(cachedRowSet.getLong(6));
			vo.setDescripcion(cachedRowSet.getString(7));
			vo.setPrincipal(cachedRowSet.getInt(9));
			conocimientos.add(vo);
		}
		return conocimientos;
	}
	
	/**
	 * Genera la cadena que representa la consulta a base de datos para obtener
	 * las habilidades del Candidato para el despliegue del detalle del candidato.
	 * @return un objeto {@code String} que representa la consulta parametrizada 
	 * a la base de datos para obtener la informaci&oacute;n especificada.
	 */
	private String setQueryHabilidades() {
		
		StringBuilder query = new StringBuilder(128);
		
		query.append("SELECT id_candidato_conocim_habilidad, id_candidato, ");
		query.append("id_tipo_conocim_habilidad, conocimiento_habilidad, ");
		query.append("id_experiencia, id_dominio, descripcion, fecha_alta, ");
		query.append("principal FROM candidato_conocim_habilidad "); 
		query.append("WHERE id_candidato = ? AND id_tipo_conocim_habilidad = ");
		query.append(Constantes.CONOC_HAB.HABILIDAD.getIdOpcion());
		query.append(" AND ROWNUM <= ");
		query.append(Constantes.CONOC_HABIL_MAX_RESULTADOS);
		query.append(" ORDER BY principal ASC");
		logger.debug(query.toString());
		return query.toString();
	}
	
	/**
	 * Extrae de la base de datos, las habilidades del candidato para 
	 * mostrarlas en las pantallas de detalle del candidato.
	 * @param idCandidato representa el identificador del candidato del cual se
	 *        quiere obtener la informaci&oacute;n
	 * @return un conjunto de objetos {@code ConocimientoHabilidadVO} que 
	 * 		   contiene la informaci&oacute;n a mostrar
	 * @throws SQLException en caso de ocurrir alg&uacute;n problema al consultar en la BD
	 */
	public ArrayList<ConocimientoHabilidadVO> buscarHabilidades(long idCandidato)
			throws SQLException {
		
		consulta = "OBTENER_HABILIDADES";
		Object[] parametros = {new Long(idCandidato)};
		CachedRowSet cachedRowSet = executeQuery(parametros);
		ArrayList<ConocimientoHabilidadVO> habilidades = new ArrayList<ConocimientoHabilidadVO>();
	
		while (cachedRowSet.next()) {
			ConocimientoHabilidadVO vo = new ConocimientoHabilidadVO();
			vo.setIdCandidatoConocimHabilidad(cachedRowSet.getLong(1));
			vo.setIdTipoConocimHabilidad(cachedRowSet.getLong(3));
			vo.setConocimientoHabilidad(cachedRowSet.getString(4));
			vo.setIdExperiencia(cachedRowSet.getLong(5));
			vo.setIdDominio(cachedRowSet.getLong(6));
			vo.setDescripcion(cachedRowSet.getString(7));
			vo.setPrincipal(cachedRowSet.getInt(9));
			habilidades.add(vo);
		}
		return habilidades;
	}
	
	/**
	 * Metodo que realiza la busqueda de certificaciones de un candidato.
	 * @param idCandidato Identificador del candidato.
	 * @return Lista con las certificaciones.
	 * @throws SQLException Excepcion en caso de un error de SQL.
	 */
	public List<String> buscarCertificaciones(long idCandidato) throws SQLException {
		List<String> lista = new ArrayList<String>();
		Object[] parametros = {new Long(idCandidato)};
		CachedRowSet cachedRowSet = executeQueryCandidato(parametros, getQueryCertificaciones(idCandidato));
		
		while (cachedRowSet.next()) {
			lista.add(cachedRowSet.getString("CERTIFICACION"));
		}
		return lista;
	}
	
	/**
	 * Metodo que arma el query para buscar las certificaciones
	 * del candidato.
	 * @param idCandidato Identificador del candidato.
	 * @return String con el query armado.
	 */
	private String getQueryCertificaciones(long idCandidato){
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT F_DESC_CATALOGO(")
			.append ("ID_CATALOGO")
			.append(",ID_CATALOGO_OPCION) ")
			.append("AS CERTIFICACION ")
			.append("FROM CAN_CREDENCIAL_CERTIFICACION ")
			.append("WHERE ID_CANDIDATO = ?");	
		
		logger.debug(query.toString());
		
		return query.toString();
	}
	
	/**
	 * Metodo que arma el query para buscar los Sistemas Especializados
	 * del candidato.
	 * @param idCandidato Identificador del candidato.
	 * @return String con el query armado.
	 */
	public String buscarSistemas(long idCandidato) throws SQLException {
		StringBuilder lista = new StringBuilder();
		Object[] parametros = {new Long(idCandidato)};
		CachedRowSet cachedRowSet = executeQueryCandidato(parametros, getQuerySistemas(idCandidato));
		
		while (cachedRowSet.next()) {
			lista.append(", ").append(cachedRowSet.getString("SISTEMA"));
			
		}
		return lista.toString().substring(2);
	}
	
	/**
	 * Metodo que arma el query para buscar los Sistemas Especializados
	 * del candidato.
	 * @param idCandidato Identificador del candidato.
	 * @return String con el query armado.
	 */
	private String getQuerySistemas(long idCandidato){
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT F_DESC_CATALOGO(")
			.append (Constantes.CATALOGO_SISTEMAS)
			.append(",ID_SISTEMA_ESPECIALIZADO) ")
			.append("AS SISTEMA ")
			.append("FROM CAN_SISTEMA_ESPECIALIZADO ")
			.append("WHERE ID_CANDIDATO = ?");		
		logger.debug(query.toString());
		return query.toString();
	}
		
	/**
	 * Metodo que arma el query para buscar conocimientos de computacion
	 * del candidato.
	 * @param idCandidato Identificador del candidato.
	 * @return String con el query armado.
	 */
	public String buscarComputacion(long idCandidato) throws SQLException {
		StringBuilder lista = new StringBuilder();
		Object[] parametros = {new Long(idCandidato)};
		CachedRowSet cachedRowSet = executeQueryCandidato(parametros, getQueryComputacion());
		
		while (cachedRowSet.next()) {
			lista.append(", ").append(cachedRowSet.getString("ID"));
			
		}
		return lista.toString().substring(2);
	}
	
	public List<IdiomaVO> buscarIdiomas(long idCandidato) throws SQLException {
		List<IdiomaVO> lista = new ArrayList<IdiomaVO>();
		Object[] parametros = {new Long(idCandidato)};
		logger.debug(getQueryIdiomas());
		logger.debug(idCandidato);
		CachedRowSet cachedRowSet = executeQueryCandidato(parametros, getQueryIdiomas());
		IdiomaVO vo;
		while (cachedRowSet.next()) {
			vo = new IdiomaVO();
			vo.setIdioma(cachedRowSet.getString("ID"));
			vo.setIdDominioComprension(cachedRowSet.getInt("comprender"));
			vo.setIdDominioEscrito(cachedRowSet.getInt("escribir"));
			vo.setIdDominioHabla(cachedRowSet.getInt("hablar"));
			vo.setIdDominioLectura(cachedRowSet.getInt("leer"));
			vo.setCertificacion(cachedRowSet.getString("descripcion"));
			lista.add(vo);
		}
		if(lista != null){
			logger.debug("idioma.size: " + lista.size());
		}
		return lista;
	}
	
	
	/**
	 * Metodo que arma el query para buscar conocimientos de computacion
	 * del candidato.
	 * @param idCandidato Identificador del candidato.
	 * @return String con el query armado.
	 */
	private String getQueryComputacion(){
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT F_DESC_CATALOGO(")
			.append (Constantes.CATALOGO_CERTIFICACION)
			.append(",ID_SISTEMA_ESPECIALIZADO) ")
			.append("AS ID ")
			.append("FROM CAN_SISTEMA_ESPECIALIZADO ")
			.append("WHERE ID_CANDIDATO = ? ");
		logger.debug(query.toString());
		return query.toString();
	}
	
	private String getQueryIdiomas(){
		StringBuilder query = new StringBuilder();

		query.append("SELECT F_DESC_CATALOGO(" + CATALOGO_OPCION_IDIOMAS)
			.append (", I.ID_IDIOMA) AS ID")
			.append(",nvl(I.ID_DOMINIO_COMPRENSION,0) as comprender , nvl(I.ID_DOMINIO_ESCRITO,0) as escribir ,")
			.append("nvl(I.ID_DOMINIO_HABLA,0) as hablar , nvl(I.ID_DOMINIO_LECTURA,0) as leer ,")
			.append("CASE ID_IDIOMA WHEN  5 THEN   F_DESC_CATALOGO(30, ID_CERTIFICACION) ")
			.append("WHEN  1 THEN   F_DESC_CATALOGO(37, ID_CERTIFICACION) ")
			.append("WHEN  2 THEN   F_DESC_CATALOGO(32, ID_CERTIFICACION) ")
			.append("WHEN  3 THEN   F_DESC_CATALOGO(35, ID_CERTIFICACION) ")
			.append("WHEN  4 THEN   F_DESC_CATALOGO(31, ID_CERTIFICACION) ")
			.append("WHEN  5 THEN   F_DESC_CATALOGO(30, ID_CERTIFICACION) ")
			.append("WHEN  6 THEN   F_DESC_CATALOGO(33, ID_CERTIFICACION) ")
			.append("WHEN  7 THEN   F_DESC_CATALOGO(36, ID_CERTIFICACION) ")
			.append("WHEN  8 THEN   F_DESC_CATALOGO(89, ID_CERTIFICACION) ELSE '' END descripcion ")
			.append("FROM CANDIDATO_IDIOMA I ")
			.append("WHERE I.ID_CANDIDATO = ? ")
			.append("AND( I.ID_DOMINIO_COMPRENSION is not NULL ")
			.append("OR I.ID_DOMINIO_ESCRITO is not NULL ")
			.append("OR I.ID_DOMINIO_HABLA is not NULL ")
			.append("OR I.ID_DOMINIO_LECTURA is not NULL)");
		logger.debug(query.toString());
		return query.toString();
	}
}
 