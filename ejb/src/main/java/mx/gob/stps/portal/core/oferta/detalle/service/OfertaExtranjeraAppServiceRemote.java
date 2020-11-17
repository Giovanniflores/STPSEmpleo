package mx.gob.stps.portal.core.oferta.detalle.service;

import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraDescripcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraPostulacionVO;

@Remote
public interface OfertaExtranjeraAppServiceRemote {
	
	public int save(OfertaExtranjeraVO vo);
	
	public OfertaExtranjeraVO find(long idOfertaExtranjera);
	
	public int update(OfertaExtranjeraVO vo);
	
	public List<OfertaExtranjeraVO> findAll();
	
	public List<OfertaExtranjeraDescripcionVO> getDescription(long idOfertaExtranjera);
	
	public List<OfertaExtranjeraVO> findByPaisEstatus(long idPais, ESTATUS estatus);
	
	public int saveMatch(OfertaExtranjeraPostulacionVO vo);
	
	public boolean existOfertaExtranjeraPostulacion(long idCandidato, long idOfertaExtranjera);
}