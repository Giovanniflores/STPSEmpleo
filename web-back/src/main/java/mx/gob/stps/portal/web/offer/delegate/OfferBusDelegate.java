package mx.gob.stps.portal.web.offer.delegate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaVerDetalleVO;
import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaDetalleCortoVO;
import mx.gob.stps.portal.persistencia.entity.OfertaCandidato;
import mx.gob.stps.portal.persistencia.entity.PostulacionExterna;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertasRecientesVO;
import mx.gob.stps.portal.persistencia.vo.PostulacionExternaVO;
import mx.gob.stps.portal.web.candidate.vo.CandidatoAjaxVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public interface OfferBusDelegate {

	/** Ver detalle de la oferta **/
	public int create(OfertaVerDetalleVO vo) throws BusinessException, ServiceLocatorException;
	
	public OfertaVerDetalleVO findByCount(long idOfertaEmpleo, int anio, int mes)throws BusinessException, ServiceLocatorException;
	
	public void update(OfertaVerDetalleVO vo) throws ServiceLocatorException;
	
	public void remove(long idOfertaVerDetalle) throws ServiceLocatorException;
	
	/** Preguntas de la oferta **/
	public int create(OfertaPreguntaVO vo) throws BusinessException, ServiceLocatorException;
	
	public OfertaPreguntaVO findOQById(long idOfertaPregunta) throws BusinessException, ServiceLocatorException;
	
	public void update(OfertaPreguntaVO vo) throws BusinessException, ServiceLocatorException;
	
	public void removeOQ(long idOfertaPregunta) throws BusinessException, ServiceLocatorException;
	
	public List<OfertaPreguntaVO> ofertaPreguntasList(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException;
	
	/** Postulacion de oferta **/
	public int create(OfertaPostulacionVO vo) throws BusinessException, ServiceLocatorException;
	
	public OfertaPostulacionVO findOPById(long idOfertaVerDetalle, int anio, int mes) throws BusinessException, ServiceLocatorException;
	
	public void update(OfertaPostulacionVO vo) throws BusinessException , ServiceLocatorException;
	
	public void removeOP(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException;
	
	/** Oferta Candidato **/
	public int create(OfertaCandidatoVO vo) throws BusinessException, ServiceLocatorException;
	
	public OfertaCandidatoVO findOCById(long idOfertaCandidato) throws BusinessException, ServiceLocatorException;
	
	public void update(OfertaCandidatoVO vo) throws BusinessException, ServiceLocatorException;
	
	public void removeOC(long idOfertaCandidato) throws BusinessException, ServiceLocatorException;
	
	/** Oferta Empleo **/
	//public int registrarOfertaEmpleo(OfertaEmpleoVO vo) throws BusinessException, ServiceLocatorException;
	
	//public OfertaEmpleoVO buscarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException;
	
	public OfertaEmpleoJB buscarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException;
	
	//public void actualizarOfertaEmpleo(OfertaEmpleoVO vo) throws BusinessException, ServiceLocatorException;
	
	public void eliminarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException;
	
	public List<OfertaEmpleoVO> listaOfertaEmpleos(long idEmpresa) throws BusinessException, ServiceLocatorException;
	
	public List<ParametroVO> getCurrentExternalOffersCount() throws BusinessException, ServiceLocatorException;
		
	public List getCurrentPortalOffersCountByEntity() throws BusinessException, ServiceLocatorException;
	
	public List<OfertasRecientesVO> getOfertasRecientes(int tipoConsulta) throws BusinessException, ServiceLocatorException;
		
	/** Oferta Candidato **/
	public List<OfertaCandidatoVO> misOfertas(long idCandidato) throws BusinessException, ServiceLocatorException;
	
	public List<OfertaEmpleoJB> misOfertasEmpleo(long idCandidato) throws BusinessException, ServiceLocatorException;

    public List<OfertaEmpleoJB> misPostulaciones(long idCandidato) throws BusinessException, ServiceLocatorException;

    public List<OfertaEmpleoJB> misContratacionPpc(long idOfertaCandidato) throws BusinessException, ServiceLocatorException;

	public int consultarOfertasContratadoPpc(long idCandidato) throws BusinessException, ServiceLocatorException;

    public List<OfertaEmpleoJB> misOfertasGuardadas(long idCandidato) throws BusinessException, ServiceLocatorException;
	
	public List<OfertaCandidatoVO> empresasMeBuscan(long idCandidato) throws BusinessException, ServiceLocatorException;
	
	public List<OfertaEmpleoJB> empresasMeBuscanOfertas(long idCandidato) throws BusinessException, ServiceLocatorException;
	
	public List<OfertaRequisitoVO> ofertaRequisitosList(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException;
	
	public List<OfertaIdiomaVO> ofertaIdiomasCertList(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException;
	
	public HashMap<String, String> getUbicacion(long idOfertaEmpleo) throws SQLException, ServiceLocatorException;
	
	public List<RegistroUbicacionVO> getUbicaciones(long idOfertaEmpleo) throws SQLException, ServiceLocatorException;
	
	public HashMap<String, String> ofertaIdiomasList(long idOfertaEmpleo) throws SQLException, ServiceLocatorException;
	
	public List<String> ofertaEspecialidadesList(long idOfertaEmpleo, long idNivelEstudio) throws SQLException, ServiceLocatorException;
	
	public List<String> sectorList(long idOfertaEmpleo) throws SQLException, ServiceLocatorException;
	
	public List<String> prestacionesList(long idOfertaEmpleo) throws SQLException, ServiceLocatorException;
	
	public String getCatalogoOpcion(long idCatalogo, long idCatalogoOpcion) throws SQLException, ServiceLocatorException;
	
	public String getMunicipio(long idEntidad, long idMunicipio) throws SQLException, ServiceLocatorException;
	
	public int match(long idOfertaEmpleo, long idCandidato) throws ServiceLocatorException;
	
//	public List<OfertaPorPerfilVO> buscaOfertasRecomendadas(long idCandidato) throws ServiceLocatorException;
	
	//public List<OfertaPorPerfilVO> obtenerOfertasPorPerfil(long idCandidato)throws SQLException, ServiceLocatorException;
	
	//public List<OfertaPorPerfilVO> buscarOfertasPorPerfil(long idCandidato)throws SQLException, ServiceLocatorException;
	
	public List<OfertaCandidatoVO> findByOfferCandidate(long idOfertaEmpleo, long idCandidato) throws BusinessException, ServiceLocatorException;

	public List<OfertaEmpleoVO> consultaOfertasEntidad(int idEntidad)throws SQLException, ServiceLocatorException;

	public long consultaTotalOfertasPublicadas();
	
//	public List<CandidatoVo> busquedaAsistidaCandidatos(long idOfertaEmpleo) throws SQLException, PersistenceException, TechnicalException, ServiceLocatorException;
	
	public List getCurrentPortalOffersCountByEntityStudy() throws BusinessException, ServiceLocatorException;

	public List getCurrentPortalOffersCountByEntityExperience() throws BusinessException, ServiceLocatorException;
	
	public List getCurrentOfferEconomicActivity() throws BusinessException, ServiceLocatorException;	

	public List getCurrentPortalOffersCountByAreaOcupacion() throws BusinessException, ServiceLocatorException;

	public List<OfertaDetalleCortoVO> consultaOfertasDescripcionCorta(List<Long> idsOfertas)  throws ServiceLocatorException;
	
	public Integer contarNumeroPlazasResultados(List<Long> indicesOfertas) throws ServiceLocatorException;
	
	public List<OfertaCandidatoVO> findCandidatesByEstatus(long idOfertaEmpleo, long estatus) throws ServiceLocatorException;
	
	public List<OfertaCandidatoVO> findAllOffersByCandidate(long idCandidato) throws ServiceLocatorException;
	
	public int closePendingPostRelatedOffer(long idOfertaEmpleo) throws ServiceLocatorException;
	
	public int registrarPostulacionExterna(PostulacionExternaVO postulacionExternaVo, long idUsuario) throws ServiceLocatorException;
	
	public List<PostulacionExterna> obtenerPostulacionesCandidato(Long idCandidato) throws ServiceLocatorException;
	
	public boolean darSeguimientoPostulacionExternaContratado(PostulacionExterna postulacion,
			List<PostulacionExterna> postulaciones) throws ServiceLocatorException;
	
	public boolean darSeguimientoPostulacion(PostulacionExterna postulacion) throws ServiceLocatorException;
	
	public boolean actualizaEstatusPPCCandidato(Long idCandidato) throws ServiceLocatorException;
	
	public void registraBitacora(Long idUsuario, long idPostulacionExt,Long idCandidato,Integer estatusPost) throws ServiceLocatorException;
	
	public void actualizaPostulacionesSNE(Long idCandidato) throws ServiceLocatorException;
	
	public List<PostulacionExterna> obtenerPostulacionContratado(Long idCandidato) throws ServiceLocatorException;

	public boolean validaPostulacionXOferta(long idCandidato, List<Long> listOfertas)throws BusinessException, ServiceLocatorException;
}