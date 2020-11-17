package mx.gob.stps.portal.core.oferta.detalle.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaPostulacionAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.persistencia.facade.OfertaPostulacionFacadeLocal;

@Stateless(name = "OfertaPostulacionAppService", mappedName = "OfertaPostulacionAppService")
public class OfertaPostulacionAppService implements OfertaPostulacionAppServiceRemote {
	
	@EJB private OfertaPostulacionFacadeLocal OfertaPostulacionFacade;

	public OfertaPostulacionAppService() {
	}
	
	@Override
	public int create(OfertaPostulacionVO vo) throws BusinessException {
		int result = OfertaPostulacionFacade.create(vo);
		return result;
	}

	@Override
	public OfertaPostulacionVO findByPK(long idOfertaEmpleo, int anio, int mes) throws BusinessException {
		return OfertaPostulacionFacade.findByPK(idOfertaEmpleo, anio, mes);
	}

	@Override
	public void update(OfertaPostulacionVO vo) throws BusinessException{
		OfertaPostulacionFacade.update(vo);
	}

	@Override
	public void remove(long idOfertaEmpleo) throws BusinessException{
		OfertaPostulacionFacade.remove(idOfertaEmpleo);
	}
}
