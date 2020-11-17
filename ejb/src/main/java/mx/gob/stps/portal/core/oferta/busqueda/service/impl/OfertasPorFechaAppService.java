package mx.gob.stps.portal.core.oferta.busqueda.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import mx.gob.stps.portal.core.oferta.busqueda.dao.OfertasPorFechaDAO;
import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasPorFechaAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaBusquedaKioscoVO;

/**
 * Session Bean implementation class OfertasPorFecha
 */
@Stateless(mappedName = "OfertasPorFechaAppService")
public class OfertasPorFechaAppService implements OfertasPorFechaAppServiceRemote {

    /**
     * Default constructor. 
     */
    public OfertasPorFechaAppService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<OfertaBusquedaKioscoVO> ofertasPorFecha(long idFecha, long orderBy) throws SQLException {
		OfertasPorFechaDAO dao = new OfertasPorFechaDAO();
		List<OfertaBusquedaKioscoVO> ofertas = dao.obtenerOfertasPorFecha(idFecha, orderBy);
		return ofertas;
	}

}
