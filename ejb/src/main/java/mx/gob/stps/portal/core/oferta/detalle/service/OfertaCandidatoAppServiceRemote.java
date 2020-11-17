package mx.gob.stps.portal.core.oferta.detalle.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.persistencia.entity.PostulacionExterna;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpresaAdminDTO;
import mx.gob.stps.portal.persistencia.vo.PostulacionExternaVO;
import mx.gob.stps.portal.utils.Catalogos;

@Remote
public interface OfertaCandidatoAppServiceRemote {

	public int create(OfertaCandidatoVO vo) throws BusinessException;
	
	public OfertaCandidatoVO findById(long idOfertaCandidato) throws BusinessException;
	
	public void update(OfertaCandidatoVO vo) throws BusinessException;
	
	public void remove(long idOfertaCandidato) throws BusinessException;
	
	public List<OfertaCandidatoVO> misOfertas(long idCandidato) throws BusinessException;
	
	public List<OfertaEmpleoJB> misOfertasEmpleo(long idCandidato) throws BusinessException;

    public List<OfertaEmpleoJB> misPostulaciones(long idCandidato) throws BusinessException;

    public List<OfertaEmpleoJB> miContratacionPpc(final long idOfertaCandidato) throws BusinessException;

    public int consultarOfertasContratadoPpc(long idCandidato) throws BusinessException;

    public List<OfertaEmpleoJB> misOfertasGuardadas(long idCandidato) throws BusinessException;
	
	public List<OfertaCandidatoVO> empresasMeBuscan(long idCandidato) throws BusinessException;

	public List<OfertaEmpleoJB> empresasMeBuscanOfertas(long idCandidato) throws BusinessException;
	
	public HashMap<String, String> getUbicacion(long idOfertaEmpleo) throws SQLException;
	
	public List<RegistroUbicacionVO> getUbicaciones(long idOfertaEmpleo) throws SQLException;
	
	public HashMap<String, String> ofertaIdiomasList(long idOfertaEmpleo) throws SQLException;
	
	public List<String> ofertaEspecialidadesList(long idOfertaEmpleo, long idNivelEstudio) throws SQLException;

	public String getCatalogoOpcion(long idCatalogo, long idCatalogoOpcion) throws SQLException;

	public List<String> sectorList(long idOfertaEmpleo) throws SQLException;

	public List<String> prestacionesList(long idOfertaEmpleo) throws SQLException;

	public String getMunicipio(long idEntidad, long idMunicipio) throws SQLException;
	
	public List<OfertaCandidatoVO> findByOfferCandidate(long idOfertaEmpleo, long idCandidato) throws BusinessException;

	public OfertaEmpleoJB buscarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException;
	
	public List<OfertaEmpleoJB> buscarOfertasEmpleo(List<Long> idsOfertas) throws BusinessException;
	
	public List<OfertaCandidatoVO> findCandidatesByEstatus(long idOfertaEmpleo, long estatus) throws PersistenceException;

	public List<OfertaCandidatoVO> findAllOffersByCandidate(long idCandidato) throws PersistenceException;
	
	public int closePendingPostRelatedOffer(long idOfertaEmpleo) throws PersistenceException;
	
	//Start Cambio Movil
	public List<OfertaEmpleoJB> misOfertasEmpleo(long idCandidato,List<Catalogos.ESTATUS> listEstatus,int diasDifferencia) throws BusinessException ;
	
	public List<OfertaEmpleoJB> empresasMeBuscanOfertas(long idCandidato, List<Catalogos.ESTATUS> listEstatus,int diasDifferencia) throws BusinessException;
	
	public List<OfertaCandidatoOcupacionDTO>  obtenerOfertaCandidatoEmpresa(Long idEmpresa, List<ESTATUS> estatus,List<ESTATUS> estatusOferta)  throws PersistenceException;
	
	public List<OfertaEmpresaAdminDTO> obtenerOfertasEmpresa(Long idEmpresa,	List<Catalogos.ESTATUS> estatus,List<Catalogos.ESTATUS> estatusOferta) throws PersistenceException ;

	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresaPorEstatus(
			Long idEmpresa,
			List<mx.gob.stps.portal.utils.Catalogos.ESTATUS> candidatoEstatus,
			Long idOferta);

	
	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresaPorEstatus(
			Long idEmpresa,
			List<Catalogos.ESTATUS> candidatoEstatus,
			Long idOferta, List<Catalogos.ESTATUS> ofertaEstatus);
	
	// Fin Cambio Movil
	
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
