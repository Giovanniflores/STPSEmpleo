package mx.gob.stps.portal.core.oferta.detalle.service;

import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;

@Remote
public interface OfertaRequisitoAppServiceRemote {
	
	public List<OfertaRequisitoVO> ofertaRequisitosList(long idOfertaEmpleo) throws BusinessException;	
	
}