package mx.gob.stps.portal.core.oferta.busqueda.service;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaBusquedaKioscoVO;

@Remote
public interface OfertasPorFechaAppServiceRemote {

	List<OfertaBusquedaKioscoVO> ofertasPorFecha(long idFecha, long orderBy) throws SQLException;

}
