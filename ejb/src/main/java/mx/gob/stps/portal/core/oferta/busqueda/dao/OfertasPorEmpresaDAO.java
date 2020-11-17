package mx.gob.stps.portal.core.oferta.busqueda.dao;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PERSONA;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 22/03/2011
 **/
public class OfertasPorEmpresaDAO extends TemplateDAO {

	public OfertasPorEmpresaDAO(){}
	
	public OfertasPorEmpresaDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	/**
	 * Obtiene las ofertas relacionadas con una empresa  
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 22/03/2011
	 * @param int idEmpresa
	 * @throws SQLException
	 * @return List<OfertaPorPerfilVO>
	 **/
	public List<OfertaPorPerfilVO> obtenerOfertasPorEmpresa(long idEmpresa)
			throws SQLException {

		Object[] parametros = {idEmpresa};
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<OfertaPorPerfilVO> rows = new ArrayList<OfertaPorPerfilVO>();

		while (cachedRowSet.next()) {

			OfertaPorPerfilVO row = new OfertaPorPerfilVO();

			row.setIdCandidato(cachedRowSet.getLong(1));
			row.setIdOfertaEmpleo(cachedRowSet.getLong(2));
			row.setTituloOferta(cachedRowSet.getString(3));
			row.setUbicacion(cachedRowSet.getString(4));
			row.setEmpresa(cachedRowSet.getString(5));
			row.setSalario(cachedRowSet.getDouble(6));

			rows.add(row);
		}

		return rows;
	}

	@Override
	protected String getQuery() {
		return this.execQueryBusquedaPorPerfilEmpresa();
	}

	private String execQueryBusquedaPorPerfilEmpresa() {

		StringBuffer sqlString = new StringBuffer();

		sqlString.append("SELECT H.ID_EMPRESA, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION");
		sqlString.append(", CASE");
		sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + " THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " THEN I.RAZON_SOCIAL");
		//sqlString.append(" WHEN ((H.ID_TERCERA_EMPRESA IS NOT NULL) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + ")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		//sqlString.append(" WHEN ((H.ID_TERCERA_EMPRESA IS NOT NULL) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + ")) THEN J.RAZON_SOCIAL");
		sqlString.append(" END AS EMPRESA");
		sqlString.append(" ,H.SALARIO");

		sqlString.append(" FROM");
		sqlString.append(" DOMICILIO D");
		sqlString.append(" ,MUNICIPIO F");
		sqlString.append(" ,CATALOGO_OPCION G");
		sqlString.append(" ,OFERTA_EMPLEO H");
		sqlString.append(" ,EMPRESA I");
		//sqlString.append(" ,TERCERA_EMPRESA J");

		sqlString.append(" WHERE");
		sqlString.append(" H.ID_EMPRESA = ?");

		sqlString.append(" AND D.ID_TIPO_PROPIETARIO = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		sqlString.append(" AND D.ID_PROPIETARIO = H.ID_OFERTA_EMPLEO");

		sqlString.append(" AND D.ID_ENTIDAD = F.ID_ENTIDAD");
		sqlString.append(" AND D.ID_MUNICIPIO = F.ID_MUNICIPIO");

		sqlString
				.append(" AND G.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		sqlString.append(" AND D.ID_ENTIDAD = G.ID_CATALOGO_OPCION");

		sqlString.append(" AND H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());

		sqlString.append(" AND H.ID_EMPRESA = I.ID_EMPRESA");
		//sqlString.append(" AND H.ID_EMPRESA = J.ID_EMPRESA");

//		logger.info(sqlString.toString());
		return sqlString.toString();
	
	}

}
