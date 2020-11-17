package mx.gob.stps.portal.core.oferta.detalle.service;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.persistencia.entity.OfertaCandidato;
import mx.gob.stps.portal.persistencia.entity.PostulacionExterna;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpresaAdminDTO;
import mx.gob.stps.portal.persistencia.vo.PostulacionExternaVO;
import mx.gob.stps.portal.utils.Catalogos;

@Local
public interface OfertaCandidatoAppServiceLocal {

	public OfertaEmpleoJB buscarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException;
	
	public List<OfertaCandidatoVO> findCandidatesByEstatus(long idOfertaEmpleo, long estatus) throws PersistenceException;

	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresa(
			Long idEmpresa, List<ESTATUS> estatus, List<ESTATUS> estatusOferta) throws PersistenceException;
	
	public List<OfertaEmpresaAdminDTO> obtenerOfertasEmpresa(Long idEmpresa, List<Catalogos.ESTATUS> estatus,List<Catalogos.ESTATUS> ofertaEstatus) throws PersistenceException;
	
	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresaPorEstatus(
			Long idEmpresa,
			List<mx.gob.stps.portal.utils.Catalogos.ESTATUS> candidatoEstatus,
			Long idOferta);
	
	public int registrarPostulacionExterna(PostulacionExternaVO postulacionExternaVo, long idUsuario);
	
	public List<PostulacionExterna> obtienePostulacionesCandidato(Long idCandidato);
	
	public boolean darSeguimientoPostulacionContratado(PostulacionExterna postulacion, List<PostulacionExterna> postulaciones);
	
	public boolean darSeguimientoPostulacion(PostulacionExterna postulacion);
	
	public boolean actualizaEstatusPPCCandidato(Long idCandidato);
	
	public void registraBitacora(Long idUsuario, long idPostulacionExt,Long idCandidato,Integer estatusPost);
	
	public void actualizaPostulacionesSNE(Long idCandidato);
	
	public List<PostulacionExterna> obtenerPostulacionContratado(Long idCandidato);

	public int getPostulacionCandidate(long idCandidato, List<Long> listOfertas) throws PersistenceException;
}
