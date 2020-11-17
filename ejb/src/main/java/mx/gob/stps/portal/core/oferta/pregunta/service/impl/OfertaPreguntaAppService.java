package mx.gob.stps.portal.core.oferta.pregunta.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.pregunta.service.OfertaPreguntaAppServiceRemote;
import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;
import mx.gob.stps.portal.core.persistencia.facade.OfertaPreguntaFacadeLocal;

@Stateless(name = "OfertaPreguntaAppService", mappedName = "OfertaPreguntaAppService")
public class OfertaPreguntaAppService implements OfertaPreguntaAppServiceRemote {
	
	@EJB private OfertaPreguntaFacadeLocal ofertaPreguntaFacade;

	public OfertaPreguntaAppService() {
	}
	
	@Override
	public List<OfertaPreguntaVO> ofertaPreguntasList(long idOfertaEmpleo) throws BusinessException {
		return ofertaPreguntaFacade.ofertaPreguntasList(idOfertaEmpleo);
	}

	@Override
	public int create(OfertaPreguntaVO vo) throws BusinessException {
		return ofertaPreguntaFacade.create(vo);
	}

	@Override
	public OfertaPreguntaVO findById(long idOfertaPregunta) throws BusinessException {
		return ofertaPreguntaFacade.findById(idOfertaPregunta);
	}

	@Override
	public void update(OfertaPreguntaVO vo) throws BusinessException {
		ofertaPreguntaFacade.update(vo);
	}

	@Override
	public void remove(long idOfertaPregunta) throws BusinessException {
		ofertaPreguntaFacade.remove(idOfertaPregunta);
	}
}