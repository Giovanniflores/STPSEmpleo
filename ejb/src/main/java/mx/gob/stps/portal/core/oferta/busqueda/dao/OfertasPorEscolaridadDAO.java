package mx.gob.stps.portal.core.oferta.busqueda.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaBusquedaKioscoVO;

public class OfertasPorEscolaridadDAO extends TemplateDAO{
	private String ORDER_BY= "OE.FECHA_ALTA DESC";
	@Override
	protected String getQuery() {
		StringBuilder sqlString = new StringBuilder();
		
		sqlString.append("SELECT OE.ID_OFERTA_EMPLEO, CO2.OPCION,OE.FECHA_ALTA, CO1.OPCION, MU.MUNICIPIO ");
		sqlString.append("FROM OFERTA_EMPLEO OE, DOMICILIO DO, CATALOGO_OPCION CO1, MUNICIPIO MU, CATALOGO_OPCION CO2 ");
		sqlString.append("WHERE OE.ESTATUS = ? ");
		sqlString.append("AND OE.ID_NIVEL_ESTUDIO = ? ");
		sqlString.append("AND DO.ID_TIPO_PROPIETARIO = ? ");
		sqlString.append("AND DO.ID_PROPIETARIO = OE.ID_OFERTA_EMPLEO ");
		sqlString.append("AND DO.ID_ENTIDAD = MU.ID_ENTIDAD ");
		sqlString.append("AND DO.ID_MUNICIPIO = MU.ID_MUNICIPIO ");
		sqlString.append("AND OE.ID_OCUPACION = CO1.ID_CATALOGO_OPCION ");
		sqlString.append("AND CO1.ID_CATALOGO = ? ");
		sqlString.append("AND DO.ID_ENTIDAD = CO2.ID_CATALOGO_OPCION ");
		sqlString.append("AND CO2.ID_CATALOGO = ? ");
		sqlString.append("ORDER BY "+ORDER_BY);
		
		return sqlString.toString();
	}

	public List<OfertaBusquedaKioscoVO> obtenerOfertasPorEscolaridad(
			long idEscolaridad, long orderBy) throws SQLException {
		List<OfertaBusquedaKioscoVO> ofertas = new ArrayList<OfertaBusquedaKioscoVO>();
		Object[] parametros = {Constantes.ESTATUS.ACTIVO.getIdOpcion(), idEscolaridad,Constantes.TIPO_PROPIETARIO.OFERTA.getIdTipoPropietario(), Constantes.CATALOGO_OPCION_OCUPACION, Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA};
		
		switch((int)orderBy){

		case 1 : ORDER_BY = "CO1.OPCION ";
		break;

		case 2 : ORDER_BY = "CO2.OPCION ";
		break;

		}
		
		CachedRowSet cachedRowSet = executeQuery(parametros);
		
		

		while(cachedRowSet.next()){

			OfertaBusquedaKioscoVO vo = new OfertaBusquedaKioscoVO();
			vo.setIdOfertaEmpleo(cachedRowSet.getLong(1));
			vo.setUbicacion(cachedRowSet.getString(2));
			vo.setFechaAlta(Utils.getFechaFormato(cachedRowSet.getDate(3)));
			vo.setOcupacion(cachedRowSet.getString(4));
			vo.setMunicipio(cachedRowSet.getString(5));
			ofertas.add(vo);
		}

		return ofertas;
	}

}
