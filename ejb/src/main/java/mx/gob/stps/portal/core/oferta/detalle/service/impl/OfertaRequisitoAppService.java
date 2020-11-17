package mx.gob.stps.portal.core.oferta.detalle.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaRequisitoAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.core.persistencia.facade.OfertaRequisitoFacadeLocal;

@Stateless(name = "OfertaRequisitoAppService", mappedName = "OfertaRequisitoAppService")
public class OfertaRequisitoAppService implements OfertaRequisitoAppServiceRemote {

	@EJB private OfertaRequisitoFacadeLocal ofertaRequisitoFacade;
	
	@Override
	public List<OfertaRequisitoVO> ofertaRequisitosList(long idOfertaEmpleo) throws BusinessException {
		return ofertaRequisitoFacade.ofertaRequisitosList(idOfertaEmpleo);
	}
}
