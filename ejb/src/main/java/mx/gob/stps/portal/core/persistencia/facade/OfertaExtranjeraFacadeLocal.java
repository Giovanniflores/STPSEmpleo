package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraDescripcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraPostulacionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraVO;

@Local
public interface OfertaExtranjeraFacadeLocal {

	public int save(OfertaExtranjeraVO vo)  throws PersistenceException ;
	
	public OfertaExtranjeraVO find(long idOfertaExtranjera);
	
	public int update(OfertaExtranjeraVO vo);
	
	public List<OfertaExtranjeraVO> findAll();
	
	public List<OfertaExtranjeraDescripcionVO> getDescription(long idOfertaExtranjera);
	
	public List<OfertaExtranjeraVO> findByPaisEstatus(long idPais, ESTATUS estatus);
	
	public int saveMatch(OfertaExtranjeraPostulacionVO vo) throws PersistenceException ;
	
	public boolean existOfertaExtranjeraPostulacion(long idCandidato, long idOfertaExtranjera);
}
