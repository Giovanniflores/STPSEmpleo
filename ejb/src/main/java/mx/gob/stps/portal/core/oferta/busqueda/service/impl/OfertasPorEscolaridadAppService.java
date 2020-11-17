package mx.gob.stps.portal.core.oferta.busqueda.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import mx.gob.stps.portal.core.oferta.busqueda.dao.OfertasPorEscolaridadDAO;
import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasPorEscolaridadAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaBusquedaKioscoVO;

/**
 * Session Bean implementation class OfertasPorEscolaridadAppService
 */
@Stateless(mappedName = "OfertasPorEscolaridadAppService")
public class OfertasPorEscolaridadAppService implements OfertasPorEscolaridadAppServiceRemote {

    /**
     * Default constructor. 
     */
    public OfertasPorEscolaridadAppService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<OfertaBusquedaKioscoVO> ofertasPorEscolaridad(long idEscolaridad, long orderBy)
			throws SQLException {
		
		OfertasPorEscolaridadDAO dao = new OfertasPorEscolaridadDAO();
		
		List<OfertaBusquedaKioscoVO> ofertas = dao.obtenerOfertasPorEscolaridad(idEscolaridad, orderBy);
		
		return ofertas;
	}

}
