package mx.gob.stps.portal.core.oferta.busqueda.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import mx.gob.stps.portal.core.oferta.busqueda.dao.OfertasJovenesDAO;
import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasJovenesAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaBusquedaKioscoVO;

/**
 * Session Bean implementation class OfertasJovenesAppService
 */
@Stateless(mappedName = "OfertasJovenesAppService")
public class OfertasJovenesAppService implements OfertasJovenesAppServiceRemote {

	@Override
	public List<OfertaBusquedaKioscoVO> ofertasJovenes(long idEntidad, long orderBy, long idMunicipio) throws SQLException {
		
		OfertasJovenesDAO dao = new OfertasJovenesDAO();
		
		List<OfertaBusquedaKioscoVO> ofertas = dao.obtenerOfertasJovenes(idEntidad, orderBy, idMunicipio);
		
		return ofertas;
		
		
	}

}
