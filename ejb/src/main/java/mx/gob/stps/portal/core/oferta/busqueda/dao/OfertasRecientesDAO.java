package mx.gob.stps.portal.core.oferta.busqueda.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaRecienteVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 22/03/2011
 **/
// TODO ELIMINAR CLASE, NO SE HACE REFERENCIA
public class OfertasRecientesDAO extends TemplateDAO {

	private OfertasRecientesDAO(){}
	
	private OfertasRecientesDAO(Connection globalConnection){
		super(globalConnection);
	}
	
	/**
	 * Obtiene las ofertas activas recientes
	 * 
	 * @author Mario Alberto Vázquez Flores
	 * @since 22/03/2011
	 * @param long numRegistros
	 * @throws SQLException
	 * @return List<OfertaRecienteVO>
	 **/
	private List<OfertaRecienteVO> obtenerOfertasRecientes(int numRegistros) throws SQLException {

		numRegistros += 1;
		Object[] parametros = { numRegistros };
		CachedRowSet cachedRowSet = executeQuery(parametros);

		List<OfertaRecienteVO> rows = new ArrayList<OfertaRecienteVO>();

		while (cachedRowSet.next()) {

			OfertaRecienteVO row = new OfertaRecienteVO();

			row.setIdOfertaEmpleo(cachedRowSet.getLong(1));
			row.setTituloOferta(cachedRowSet.getString(2));
			row.setUbicacion(cachedRowSet.getString(3));
			row.setVigencia(cachedRowSet.getString(4));
			rows.add(row);
		}

		return rows;
	}

	@Override
	protected String getQuery() {
		return this.execQueryOfertasRecientes();
	}

	private String execQueryOfertasRecientes() {

		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT ID_OFERTA_EMPLEO,TITULO_OFERTA,UBICACION,VIGENCIA FROM ( ");
		sqlString.append(" SELECT OE.ID_OFERTA_EMPLEO as ID_OFERTA_EMPLEO,OE.TITULO_OFERTA as TITULO_OFERTA, ");
		sqlString.append(" F_DESC_CATALOGO(25, dom.id_entidad) || ', ' || Mun.MUNICIPIO AS UBICACION, ");		
		sqlString.append(" to_char(OE.FECHA_INICIO, 'DD') || ' de ' ||  rtrim(to_char(OE.FECHA_INICIO, 'Month')) || ' ' || ");
		sqlString.append(" trim(to_char(OE.FECHA_INICIO, 'yyyy')) || ' - ' || ");
		sqlString.append(" to_char(OE.FECHA_FIN, 'DD') || ' de ' ||  rtrim(to_char(OE.FECHA_FIN, 'Month')) || ' ' || ");
		sqlString.append(" trim(to_char(OE.FECHA_FIN,'yyyy')) as VIGENCIA ");
		sqlString.append(" FROM  OFERTA_EMPLEO OE ");
		sqlString.append(" LEFT JOIN DOMICILIO dom ");
		sqlString.append(" ON oe.ID_OFERTA_EMPLEO = dom.id_propietario ");
		sqlString.append(" LEFT JOIN MUNICIPIO mun ");
		sqlString.append(" ON dom.id_municipio = mun.id_municipio AND dom.id_entidad = mun.id_entidad ");
		sqlString.append(" WHERE OE.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
		sqlString.append(" and dom.id_propietario=oe.ID_OFERTA_EMPLEO "); 
		sqlString.append(" and dom.id_tipo_propietario=" + TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
	    sqlString.append(" ORDER BY oe.FECHA_INICIO DESC, oe.ID_OFERTA_EMPLEO DESC");
		sqlString.append(" ) ");
		sqlString.append(" WHERE ROWNUM < ? ");
		/*
		sqlString.append("SELECT ");
		sqlString.append(" ID_OFERTA_EMPLEO ");
		sqlString.append(" ,TITULO_OFERTA ");
		sqlString.append(" ,UBICACION ");
		sqlString.append(" ,FINI || ' - ' || FFIN AS VIGENCIA");
		sqlString.append(" FROM ");
		sqlString.append("( SELECT ");
		sqlString.append(" OE.ID_OFERTA_EMPLEO ");
		sqlString.append(" ,OE.TITULO_OFERTA ");
		sqlString.append(" ,CO.OPCION || ', ' || M.MUNICIPIO AS UBICACION ");
		sqlString.append(" ,to_char(OE.FECHA_INICIO, 'DD') || ' de ' ||  rtrim(to_char(OE.FECHA_INICIO, 'Month')) || ' ' || trim(to_char(OE.FECHA_INICIO, 'yyyy')) FINI ");
		sqlString.append(" ,to_char(OE.FECHA_FIN, 'DD') || ' de ' ||  rtrim(to_char(OE.FECHA_FIN, 'Month')) || ' ' || trim(to_char(OE.FECHA_FIN, 'yyyy')) FFIN ");		
		sqlString.append(" FROM  ");
		sqlString.append(" OFERTA_EMPLEO OE ");
		sqlString.append(" ,DOMICILIO D ");
		sqlString.append(" ,CATALOGO_OPCION CO ");
		sqlString.append(" ,MUNICIPIO M ");
		sqlString.append(" WHERE ");
		sqlString.append(" D.ID_PROPIETARIO = OE.ID_OFERTA_EMPLEO ");
		sqlString.append(" AND D.ID_ENTIDAD = M.ID_ENTIDAD ");
		sqlString.append(" AND D.ID_MUNICIPIO = M.ID_MUNICIPIO ");
		sqlString.append(" AND D.ID_ENTIDAD = CO.ID_CATALOGO_OPCION ");
		sqlString.append(" AND CO.ID_CATALOGO = "
				+ CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		sqlString.append(" AND D.ID_TIPO_PROPIETARIO = "
				+ TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario());
		sqlString.append(" AND OE.ESTATUS = " + ESTATUS.ACTIVO.getIdOpcion());
		sqlString.append(" ORDER BY  OE.FECHA_MODIFICACION DESC ");
		sqlString.append(" ) ");
		sqlString.append(" WHERE ROWNUM < ? ");
		*/
//		logger.info(sqlString.toString());
		return sqlString.toString();

	}

}
