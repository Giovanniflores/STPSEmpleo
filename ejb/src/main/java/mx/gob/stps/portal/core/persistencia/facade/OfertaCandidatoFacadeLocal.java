package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.NotificacionCandidatoVO;
import mx.gob.stps.portal.core.candidate.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoADesactivarVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.vo.MiOfertaRecienteVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.persistencia.entity.NotificacionCandidato;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpresaAdminDTO;
import mx.gob.stps.portal.utils.Catalogos;

@Local
public interface OfertaCandidatoFacadeLocal {
	
	public int create(OfertaCandidatoVO vo) throws PersistenceException;
	
	public OfertaCandidatoVO findById(long idOfertaCandidato) throws PersistenceException;
	
	public void update(OfertaCandidatoVO vo) throws PersistenceException;
	
	public void remove(long idOfertaCandidato) throws PersistenceException;
	
	public List<OfertaCandidatoVO> misOfertas(long idCandidato) throws PersistenceException;
	
	public List<OfertaCandidatoVO> misOfertasRelacionadas(long idCandidato) throws PersistenceException;
	
	public List<OfertaCandidatoVO> empresasMeBuscan(long idCandidato) throws PersistenceException;
	
	public List<OfertaCandidatoVO> findByOfferCandidate(long idOfertaEmpleo, long idCandidato) throws PersistenceException;
	
	public List<OfertaPostulacionVO> obtienePostulantesDeEmpresa(long idEmpresa);
	
	public List<MiOfertaRecienteVO> obtenerMisOfertasRecientes(long idEmpresa);
	
	public List<OfertaCandidatoADesactivarVO> consultaOfertasRelacionadas(long idCandidato);

	public List<OfertaEmpleoVO> consultaTotalPostuladosPorOferta(long idEmpresa);

	public void actualizaCompatibilidad(long idOfertaEmpleo, long idCandidato, int compatibilidad);
	
	public void notificacionCandidato(NotificacionCandidato candidato);
	
	public List<OfertaCandidatoVO> findCandidatesByEstatus(long idOfertaEmpleo, long estatus) throws PersistenceException;
	
	public List<OfertaCandidatoVO> findAllOffersByCandidate(long idCandidato) throws PersistenceException;
	
	public int closePendingPostRelatedOffer(long idOfertaEmpleo) throws PersistenceException;
	
	public List<OfertaCandidatoOcupacionDTO>  obtenerOfertaCandidatoEmpresa(Long idEmpresa,List<Constantes.ESTATUS> estatus,List<ESTATUS> estatusOferta)  throws PersistenceException;

	public List<OfertaEmpresaAdminDTO> obtenerOfertasEmpresa(Long idEmpresa, List<Catalogos.ESTATUS> estatus,List<Catalogos.ESTATUS> estatusOferta) throws PersistenceException;

	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresaPorEstatus(
			Long idEmpresa,
			List<mx.gob.stps.portal.utils.Catalogos.ESTATUS> candidatoEstatus,
			Long idOferta);

	List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresaPorEstatus(
			Long idEmpresa,
			List<mx.gob.stps.portal.utils.Catalogos.ESTATUS> candidatoEstatus,
			Long idOferta,
			List<mx.gob.stps.portal.utils.Catalogos.ESTATUS> ofertaEstatus);
	
	public List<mx.gob.stps.portal.persistencia.vo.OfertaCandidatoVO> findAllOffersEstatusByCandidate(long idCandidato, int estatus) throws PersistenceException;

	public int postulacionCandidatoXOferta(long idCandidato, List<Long> listOfertas);
}
