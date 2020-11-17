package mx.gob.stps.portal.web.oferta.extranjera.delegate;

import java.util.List;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraDescripcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraPostulacionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public interface OfertaExtranjeraBusDelegate {

	public int save(OfertaExtranjeraVO vo);
	
	public OfertaExtranjeraVO find(long idOfertaExtranjera) throws ServiceLocatorException;
	
	public int update(OfertaExtranjeraVO vo) throws ServiceLocatorException;
	
	public List<OfertaExtranjeraVO> findAll() throws ServiceLocatorException;
	
	public List<OfertaExtranjeraDescripcionVO> getDescription(long idOfertaExtranjera) throws ServiceLocatorException;
	
	public List<OfertaExtranjeraVO> findByPaisEstatus(long idPais, ESTATUS estatus) throws ServiceLocatorException;
	
	public int saveMatch(OfertaExtranjeraPostulacionVO vo) throws ServiceLocatorException;
	
	public boolean existOfertaExtranjeraPostulacion(long idCandidato, long idOfertaExtranjera) throws ServiceLocatorException;
}