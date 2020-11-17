package mx.gob.stps.portal.core.oferta.busqueda.dao;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ESTATUS;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.candidate.vo.OfertaCandidatoResumenVo;
import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.utils.Catalogos.TIPO_EMPRESA;

//import org.apache.log4j.Logger;

public class OfertasCandidatoResumenDAO extends TemplateDAO {

	//private static Logger logger = Logger.getLogger(OfertasPorPerfilDAO.class);

	public OfertasCandidatoResumenDAO(){}
	
	public OfertasCandidatoResumenDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	public OfertaCandidatoResumenVo obtenerOfertasPorIDOfertaCandidato(long idOfertaCandidato) throws SQLException {

		Object[] parametros = {idOfertaCandidato};
		CachedRowSet cachedRowSet = executeQuery(parametros);
		while (cachedRowSet.next()) {
			OfertaCandidatoResumenVo oferta = new OfertaCandidatoResumenVo();
			oferta.setIdEmpresa(cachedRowSet.getLong(1));
			oferta.setIdOfertaEmpleo(cachedRowSet.getLong(2));
			oferta.setTituloOferta(cachedRowSet.getString(3));
			oferta.setNombreEmpresa(cachedRowSet.getString(4));
			oferta.setCorreoEmpresa(cachedRowSet.getString(5));
			oferta.setNombreCandidato(cachedRowSet.getString(6));
			oferta.setCorreoCandidato(cachedRowSet.getString(7));
			oferta.setIdEstatus(cachedRowSet.getLong(8));
			oferta.setDescEstatus(cachedRowSet.getString(9));
			oferta.setIdOfertaCandidato(cachedRowSet.getLong(10));
			oferta.setIdCandidato(cachedRowSet.getLong(11));			
			oferta.setTituloOferta(cachedRowSet.getString(12));
			oferta.setFecha(cachedRowSet.getDate(13));
			oferta.setIdMotivo(cachedRowSet.getLong(14));
			oferta.setFuente(cachedRowSet.getLong(15));
			return oferta;
		}
		return new OfertaCandidatoResumenVo();
	}

	@Override
	protected String getQuery() {
		return this.execQueryBusquedaPorId();
	}

	private String execQueryBusquedaPorId() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT  H.ID_EMPRESA, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, ");
		sqlString.append(" CASE");
		sqlString.append(" WHEN I.ID_TIPO_EMPRESA = "+ TIPO_EMPRESA.PRIVADA.getTipoEmpresa() + " AND I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + " THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		sqlString.append(" WHEN I.ID_TIPO_EMPRESA = "+ TIPO_EMPRESA.PRIVADA.getTipoEmpresa() + " AND I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " THEN I.RAZON_SOCIAL");
		sqlString.append(" WHEN I.ID_TIPO_EMPRESA = "+ TIPO_EMPRESA.PUBLICA.getTipoEmpresa() + " THEN I.RAZON_SOCIAL");
		sqlString.append(" WHEN I.ID_TIPO_EMPRESA = "+ TIPO_EMPRESA.ONG.getTipoEmpresa() + " THEN I.RAZON_SOCIAL");
		//sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + " THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		//sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " THEN I.RAZON_SOCIAL");
		//sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (J.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + ")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		//sqlString.append(" WHEN ((NVL (H.ID_TERCERA_EMPRESA,0) != 0) AND (J.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + ")) THEN J.RAZON_SOCIAL");
		sqlString.append(" END AS EMPRESA");
		sqlString.append(", I.CORREO_ELECTRONICO");
		sqlString.append(", S.NOMBRE || ' ' || S.APELLIDO1 || ' ' || S.APELLIDO2 AS NOMBRECANDIDATO");
		sqlString.append(", C.CORREO_ELECTRONICO");
		sqlString.append(", D.ESTATUS");
		sqlString.append(", F_DESC_CATALOGO("+ CATALOGO_OPCION_ESTATUS + ",D.ESTATUS)");
		sqlString.append(", D.ID_OFERTA_CANDIDATO");
		sqlString.append(", C.ID_CANDIDATO");
		sqlString.append(", H.TITULO_OFERTA");
		sqlString.append(", D.FECHA_ALTA");
		sqlString.append(", D.ID_MOTIVO");
		sqlString.append(", D.FUENTE");
		sqlString.append(" FROM");
		sqlString.append(" CANDIDATO C");
		sqlString.append(",  SOLICITANTE S");
		sqlString.append(", OFERTA_CANDIDATO D");
		sqlString.append(", OFERTA_EMPLEO H");
		sqlString.append(", EMPRESA I");
		//sqlString.append(", TERCERA_EMPRESA J");
		sqlString.append(" WHERE");
		//sqlString.append("  H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
		sqlString.append("  1 = 1");
		sqlString.append(" AND C.ID_CANDIDATO = S.ID_CANDIDATO ");
		sqlString.append(" AND H.ID_EMPRESA = I.ID_EMPRESA");
		//sqlString.append(" AND H.ID_TERCERA_EMPRESA = J.ID_TERCERA_EMPRESA(+)");
		sqlString.append(" AND D.ID_CANDIDATO = C.ID_CANDIDATO");
		sqlString.append(" AND D.ID_OFERTA_EMPLEO = H.ID_OFERTA_EMPLEO");
		sqlString.append(" AND D.ID_OFERTA_CANDIDATO = ?");
		//logger.info(sqlString.toString());
		return sqlString.toString();
	}
}
