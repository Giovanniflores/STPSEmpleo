package mx.gob.stps.portal.core.oferta.busqueda.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import mx.gob.stps.portal.core.infra.data.TemplateDAO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaBusquedaKioscoVO;

public class OfertasPorEntidadDAO extends TemplateDAO{
	
	private String ORDER_BY= "OE.FECHA_ALTA DESC";
	private long ID_MUNICIPIO = 0;
	
	public List<OfertaBusquedaKioscoVO> obtenerOfertasPorEntidad(long idEntidad, long orderBy, long idMunicipio) throws SQLException {
		
		ID_MUNICIPIO = idMunicipio;
		
		switch((int)orderBy){
		
		case 1 : ORDER_BY = "CO.OPCION ";
			break;
		
		case 2 : ORDER_BY = "MU.MUNICIPIO ";
			break;
		
		}
		
		
		List<OfertaBusquedaKioscoVO> ofertas = new ArrayList<OfertaBusquedaKioscoVO>();
		Object[] parametros = {Constantes.ESTATUS.ACTIVO.getIdOpcion(),idEntidad,Constantes.CATALOGO_OPCION_OCUPACION};
		CachedRowSet cachedRowSet = executeQuery(parametros);
		
		while(cachedRowSet.next()){
			
			OfertaBusquedaKioscoVO ofertaPorEntidadVO = new OfertaBusquedaKioscoVO();
			ofertaPorEntidadVO.setIdOfertaEmpleo(cachedRowSet.getLong(1));
			ofertaPorEntidadVO.setUbicacion(cachedRowSet.getString(2));
			ofertaPorEntidadVO.setFechaAlta(Utils.getFechaFormato(cachedRowSet.getDate(3)));
			ofertaPorEntidadVO.setOcupacion(cachedRowSet.getString(4));
			ofertas.add(ofertaPorEntidadVO);
		}
		
		return ofertas;
		
	}
	
	@Override
	protected String getQuery() {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append("SELECT OE.ID_OFERTA_EMPLEO,MU.MUNICIPIO,OE.FECHA_ALTA, CO.OPCION ");
		sqlString.append("FROM OFERTA_EMPLEO OE, OFERTA_UBICACION DO, CATALOGO_OPCION CO, MUNICIPIO MU ");
		sqlString.append("WHERE OE.ESTATUS = ? " );
		//sqlString.append("AND DO.ID_TIPO_PROPIETARIO = ? ");
		sqlString.append("AND DO.ID_ENTIDAD = ? ");
		sqlString.append("AND DO.ID_OFERTA_EMPLEO = OE.ID_OFERTA_EMPLEO ");
		sqlString.append("AND DO.ID_ENTIDAD = MU.ID_ENTIDAD ");
		sqlString.append("AND DO.ID_MUNICIPIO = MU.ID_MUNICIPIO ");
		if(ID_MUNICIPIO>0)sqlString.append("AND MU.ID_MUNICIPIO = "+ID_MUNICIPIO);
		sqlString.append("AND OE.ID_OCUPACION = CO.ID_CATALOGO_OPCION ");
		sqlString.append("AND CO.ID_CATALOGO = ? ");
		sqlString.append("ORDER BY "+ORDER_BY);
		
		return sqlString.toString();
	}

	

}
