package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.persistencia.entity.OfertaCandidato;
import mx.gob.stps.portal.persistencia.entity.PostulacionExterna;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.PostulacionExternaVO;

@Local
public interface PostulacionExternaFacadeLocal {
	
	/**
	 * Método para guardar el Registro de una postulacion Externa
	 */
	public int guardarRegistro(PostulacionExternaVO postulacionExternaVo);
	
	public long obtenerIdPostulacionExterna();
	
	public List<PostulacionExterna> obtenerPostulacionesCandidato(Long idCandidato);
	
	public boolean darSeguimientoPostulacionExternaContratado(PostulacionExterna postulacion, List<PostulacionExterna> postulaciones);
	
	public boolean darSeguimientoPostulacion(PostulacionExterna postulacion);
	
	public boolean actualizaEstatusPPCCandidato(Long idCandidato);
	
	public boolean actualizaEstatusOfertasSNE(OfertaCandidato oferta);
	
	public List<OfertaCandidato> encuentraPostulacionesSNE(Long idCandidato);
	
	public List<PostulacionExterna> obtenerPostulacionContratado(Long idCandidato);
}