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
 * @author Mario Alberto V�zquez Flores
 * @since 22/03/2011
 **/
public class OfertasPorPerfilEmpresaDAO extends TemplateDAO {

	public OfertasPorPerfilEmpresaDAO(){}
	
	public OfertasPorPerfilEmpresaDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	/**
	 * Obtiene las ofertas relacionadas con el perfil de un candidato
	 * y de una empresa relacionada
	 * 
	 * @author Mario Alberto V�zquez Flores
	 * @since 22/03/2011
	 * @param long idCandidato, long idEmpresa
	 * @throws SQLException
	 * @return List<OfertaPorPerfilVO>
	 **/
	public List<OfertaPorPerfilVO> obtenerOfertasPorPerfilEmpresa(long idCandidato, long idEmpresa)
			throws SQLException {

		Object[] parametros = {idCandidato, idEmpresa};
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

		sqlString.append("SELECT A.ID_CANDIDATO, H.ID_OFERTA_EMPLEO, H.TITULO_OFERTA, G.OPCION || ', ' || F.MUNICIPIO AS UBICACION");
		sqlString.append(", CASE");
		sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + " THEN I.NOMBRE || ' ' || I.APELLIDO1 || ' ' || I.APELLIDO2");
		sqlString.append(" WHEN I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + " THEN I.RAZON_SOCIAL");
		//sqlString.append(" WHEN ((H.ID_TERCERA_EMPRESA IS NOT NULL) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() + ")) THEN J.NOMBRE || ' ' || J.APELLIDO1 || ' ' || J.APELLIDO2");
		//sqlString.append(" WHEN ((H.ID_TERCERA_EMPRESA IS NOT NULL) AND (I.ID_TIPO_PERSONA = "+ TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() + ")) THEN J.RAZON_SOCIAL");
		sqlString.append(" END AS EMPRESA");
		sqlString.append(" ,H.SALARIO");

		sqlString.append(" FROM");
		sqlString.append(" PERFIL_LABORAL A");
		sqlString.append(" ,EXPECTATIVA_LABORAL B");
		sqlString.append(" ,EXPECTATIVA_LUGAR C");
		sqlString.append(" ,DOMICILIO D");
		sqlString.append(" ,OFERTA_SECTOR E");
		sqlString.append(" ,MUNICIPIO F");
		sqlString.append(" ,CATALOGO_OPCION G");
		sqlString.append(" ,OFERTA_EMPLEO H");
		sqlString.append(" ,EMPRESA I");
		//sqlString.append(" ,TERCERA_EMPRESA J");

		sqlString.append(" WHERE");
		sqlString.append(" A.ID_CANDIDATO = ?");
		sqlString.append(" AND H.ID_EMPRESA = ?");
		sqlString.append(" AND A.ID_CANDIDATO = B.ID_CANDIDATO");
		sqlString.append(" AND A.ID_CANDIDATO = C.ID_CANDIDATO");

		sqlString.append(" AND C.ID_ENTIDAD_DESEADA = D.ID_ENTIDAD");
		sqlString.append(" AND C.ID_MUNICIPIO_DESEADO = D.ID_MUNICIPIO");
		sqlString.append(" AND D.ID_TIPO_PROPIETARIO = "+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());

		sqlString.append(" AND B.ID_SECTOR_DESEADO = E.ID_SECTOR");
		sqlString.append(" AND D.ID_PROPIETARIO = E.ID_OFERTA_EMPLEO");

		sqlString.append(" AND D.ID_ENTIDAD = F.ID_ENTIDAD");
		sqlString.append(" AND D.ID_MUNICIPIO = F.ID_MUNICIPIO");

		sqlString.append(" AND G.ID_CATALOGO = " + CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		sqlString.append(" AND D.ID_ENTIDAD = G.ID_CATALOGO_OPCION");

		sqlString.append(" AND E.ID_OFERTA_EMPLEO = H.ID_OFERTA_EMPLEO");
		sqlString.append(" AND B.ID_AREA_LABORAL_DESEADA = H.ID_AREA_LABORAL");
		sqlString.append(" AND B.SALARIO_PRETENDIDO <= H.SALARIO");

		sqlString.append(" AND B.ID_OCUPACION_DESEADA = H.ID_OCUPACION");
		sqlString.append(" AND B.ID_TIPO_EMPLEO_DESEADO = H.ID_TIPO_EMPLEO");
		sqlString.append(" AND H.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());

		sqlString.append(" AND A.DISPONIBILIDAD_VIAJAR = H.DISPONIBILIDAD_VIAJAR");
		sqlString.append(" AND A.DISPONIBILIDAD_RADICAR = H.DISPONIBILIDAD_RADICAR");

		sqlString.append(" AND H.ID_EMPRESA = I.ID_EMPRESA");
		//sqlString.append(" AND H.ID_EMPRESA = J.ID_EMPRESA");

//		logger.info(sqlString.toString());
		return sqlString.toString();
	}

}
