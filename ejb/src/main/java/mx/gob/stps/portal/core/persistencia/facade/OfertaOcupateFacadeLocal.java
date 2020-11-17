package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;

@Local
public interface OfertaOcupateFacadeLocal {

	public List<OfertaPorCanalVO> obtenerOfertasOcupate(List<Long> ids);

}
