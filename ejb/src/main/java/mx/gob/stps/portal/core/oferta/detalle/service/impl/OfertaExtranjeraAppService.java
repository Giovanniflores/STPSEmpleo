package mx.gob.stps.portal.core.oferta.detalle.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraDescripcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraPostulacionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraVO;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaExtranjeraAppServiceRemote;
import mx.gob.stps.portal.core.persistencia.facade.OfertaExtranjeraFacadeLocal;

@Stateless(name = "OfertaExtranjeraAppService", mappedName = "OfertaExtranjeraAppService")
public class OfertaExtranjeraAppService implements OfertaExtranjeraAppServiceRemote {
	
	@EJB private OfertaExtranjeraFacadeLocal ofertaExtranjeraFacade;

	@Override
	public int save(OfertaExtranjeraVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OfertaExtranjeraVO find(long idOfertaExtranjera) {
		return this.ofertaExtranjeraFacade.find(idOfertaExtranjera);
	}

	@Override
	public int update(OfertaExtranjeraVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OfertaExtranjeraVO> findByPaisEstatus(long idPais, ESTATUS estatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OfertaExtranjeraVO> findAll() {
		return this.ofertaExtranjeraFacade.findAll();
	}

	@Override
	public List<OfertaExtranjeraDescripcionVO> getDescription(long idOfertaExtranjera) {
		return this.ofertaExtranjeraFacade.getDescription(idOfertaExtranjera);
	}

	@Override
	public int saveMatch(OfertaExtranjeraPostulacionVO vo) {
		return this.ofertaExtranjeraFacade.saveMatch(vo);
	}

	@Override
	public boolean existOfertaExtranjeraPostulacion(long idCandidato, long idOfertaExtranjera) {
		return this.ofertaExtranjeraFacade.existOfertaExtranjeraPostulacion(idCandidato, idOfertaExtranjera);
	}

}
