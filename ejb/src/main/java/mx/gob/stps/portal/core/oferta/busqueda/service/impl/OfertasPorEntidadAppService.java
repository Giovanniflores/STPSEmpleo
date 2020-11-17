package mx.gob.stps.portal.core.oferta.busqueda.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import mx.gob.stps.portal.core.oferta.busqueda.dao.OfertasPorEntidadDAO;
import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasPorEntidadAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaBusquedaKioscoVO;

/**
 * Session Bean implementation class OfertasPorEntidadAppService
 */
@Stateless(mappedName = "OfertasPorEntidadAppService")
public class OfertasPorEntidadAppService implements OfertasPorEntidadAppServiceRemote {

	private static Logger logger = Logger.getLogger(OfertasPorEntidadAppService.class);
  
    public List<OfertaBusquedaKioscoVO> ofertasPorEntidad(long idEntidad, long orderBy, long idMunicipio) throws SQLException {
       
        OfertasPorEntidadDAO dao = new OfertasPorEntidadDAO();
        
        List<OfertaBusquedaKioscoVO> ofertas = dao.obtenerOfertasPorEntidad(idEntidad, orderBy, idMunicipio);
		
        logger.info("Lista Ofertas Tamaño "+ ofertas.size());
        
        return ofertas;
        
    }

}
