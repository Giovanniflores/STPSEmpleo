package mx.gob.stps.portal.core.oferta.busqueda.service;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaBusquedaKioscoVO;

@Remote
public interface OfertasJovenesAppServiceRemote {

	List<OfertaBusquedaKioscoVO> ofertasJovenes(long idEntidad, long orderBy, long idMunicipio) throws SQLException;

}