package mx.gob.stps.portal.core.oferta.detalle.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaIdiomaAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.persistencia.facade.OfertaIdiomaFacadeLocal;

@Stateless(name = "OfertaIdiomaAppService", mappedName = "OfertaIdiomaAppService")
public class OfertaIdiomaAppService implements OfertaIdiomaAppServiceRemote {

	@EJB private OfertaIdiomaFacadeLocal ofertaIdiomaFacade;
	
	@Override
	public List<OfertaIdiomaVO> ofertaIdiomasList(long idOfertaEmpleo) throws BusinessException {
		return ofertaIdiomaFacade.ofertaIdiomasList(idOfertaEmpleo);
	}
}