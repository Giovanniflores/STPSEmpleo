package mx.gob.stps.portal.core.oferta.busqueda.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import mx.gob.stps.portal.core.oferta.busqueda.dao.OfertasPorAreaLaboralDAO;
import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasPorAreaLaboralAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaBusquedaKioscoVO;

/**
 * Session Bean implementation class OfertasPorAreaLaboralAppService
 */
@Stateless(mappedName = "OfertasPorAreaLaboralAppService")
public class OfertasPorAreaLaboralAppService implements OfertasPorAreaLaboralAppServiceRemote {

    /**
     * Default constructor. 
     */
    public OfertasPorAreaLaboralAppService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<OfertaBusquedaKioscoVO> ofertasPorAreaLaboral(long idAreaLaboral, long orderBy) throws SQLException {
		
		OfertasPorAreaLaboralDAO dao = new OfertasPorAreaLaboralDAO();
		
		List<OfertaBusquedaKioscoVO> ofertas = dao.obtenerOfertasPorAreaLaboral(idAreaLaboral, orderBy);
		
		return ofertas;
	
	}

}
