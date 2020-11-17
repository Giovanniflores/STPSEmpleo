package mx.gob.stps.portal.core.oferta.detalle.service;

import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;

@Remote
public interface OfertaIdiomaAppServiceRemote {

	public List<OfertaIdiomaVO> ofertaIdiomasList(long idOfertaEmpleo) throws BusinessException;
}
