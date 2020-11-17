package mx.gob.stps.portal.web.offer.delegate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaCandidatoAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaEmpleoAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaIdiomaAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaPostulacionAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaRequisitoAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaVerDetalleAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaVerDetalleVO;
import mx.gob.stps.portal.core.oferta.pregunta.service.OfertaPreguntaAppServiceRemote;
import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaDetalleCortoVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.persistencia.entity.PostulacionExterna;
import mx.gob.stps.portal.persistencia.vo.OfertasRecientesVO;
import mx.gob.stps.portal.persistencia.vo.PostulacionExternaVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;

public final class OfferBusDelegateImpl implements OfferBusDelegate {
	
	private static OfferBusDelegate instance = new OfferBusDelegateImpl();
	
	private OfferBusDelegateImpl() {
	}	
	
	public static OfferBusDelegate getInstance(){
		return instance;
	}
	
	private OfertaVerDetalleAppServiceRemote getOfferAppService() throws ServiceLocatorException {
		OfertaVerDetalleAppServiceRemote ejb = (OfertaVerDetalleAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_OFFER_DETAIL);
		return ejb;
	}

	private OfertaPreguntaAppServiceRemote getOfferAskAppService() throws ServiceLocatorException {
		OfertaPreguntaAppServiceRemote ejb = (OfertaPreguntaAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_OFFER_QUESTION);
		return ejb;
	}
	
	private OfertaPostulacionAppServiceRemote getOfferPostAppService() throws ServiceLocatorException {
		OfertaPostulacionAppServiceRemote ejb = (OfertaPostulacionAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_OFFER_POST);
		return ejb;
	}
	
	private OfertaCandidatoAppServiceRemote getOfferCandidateAppService() throws ServiceLocatorException {
		OfertaCandidatoAppServiceRemote ejb = (OfertaCandidatoAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_OFFER_CANDIDATE);
		return ejb;
	}
	
	private OfertaEmpleoAppServiceRemote getOfferEmployAppService() throws ServiceLocatorException {
		OfertaEmpleoAppServiceRemote ejb = (OfertaEmpleoAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_OFFER);
		return ejb;
	}
	
	private OfertaRequisitoAppServiceRemote getOfferRequirementAppService() throws ServiceLocatorException {
		OfertaRequisitoAppServiceRemote ejb = (OfertaRequisitoAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_REQUIREMENT);
		return ejb;
	}
	
	private OfertaIdiomaAppServiceRemote getOfferLanguagesAppService() throws ServiceLocatorException {
		OfertaIdiomaAppServiceRemote ejb = (OfertaIdiomaAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_LANGUAGES);
		return ejb;
	}
	
//	private OfertaCompatibilidadAppServiceRemote getOfferCompatibilityAppService() throws ServiceLocatorException {
//		OfertaCompatibilidadAppServiceRemote ejb = (OfertaCompatibilidadAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_COMPATIBILITY);
//		return ejb;
//	}
	
	@Override
	public int create(OfertaVerDetalleVO vo) throws BusinessException, ServiceLocatorException {
		int result = getOfferAppService().create(vo);
		return result;
	}

	@Override
	public OfertaVerDetalleVO findByCount(long idOfertaVerDetalle, int anio, int mes) throws BusinessException, ServiceLocatorException {
		return getOfferAppService().findByPK(idOfertaVerDetalle, anio, mes);
	}

	@Override
	public void update(OfertaVerDetalleVO vo) throws ServiceLocatorException {
		getOfferAppService().update(vo);
	}

	@Override
	public void remove(long idOfertaVerDetalle) throws ServiceLocatorException {
		getOfferAppService().remove(idOfertaVerDetalle);
	}

	@Override
	public List<OfertaPreguntaVO> ofertaPreguntasList(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException {
		return getOfferAskAppService().ofertaPreguntasList(idOfertaEmpleo);
	}

	@Override
	public int create(OfertaPreguntaVO vo) throws BusinessException, ServiceLocatorException {
		return getOfferAskAppService().create(vo);
	}

	@Override
	public OfertaPreguntaVO findOQById(long idOfertaPregunta) throws BusinessException, ServiceLocatorException {
		return getOfferAskAppService().findById(idOfertaPregunta);
	}

	@Override
	public void update(OfertaPreguntaVO vo) throws BusinessException, ServiceLocatorException {
		getOfferAskAppService().update(vo);
	}

	@Override
	public void removeOQ(long idOfertaPregunta) throws BusinessException, ServiceLocatorException {
		getOfferAskAppService().remove(idOfertaPregunta);
	}

	@Override
	public int create(OfertaPostulacionVO vo) throws BusinessException, ServiceLocatorException {
		return getOfferPostAppService().create(vo);
	}

	@Override
	public OfertaPostulacionVO findOPById(long idOfertaEmpleo, int anio, int mes) throws BusinessException, ServiceLocatorException {
		return getOfferPostAppService().findByPK(idOfertaEmpleo, anio, mes);
	}

	@Override
	public void update(OfertaPostulacionVO vo) throws BusinessException, ServiceLocatorException {
		getOfferPostAppService().update(vo);
	}

	@Override
	public void removeOP(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException {
		getOfferPostAppService().remove(idOfertaEmpleo);
	}

	@Override
	public int create(OfertaCandidatoVO vo) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().create(vo);
	}

	@Override
	public OfertaCandidatoVO findOCById(long idOfertaCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().findById(idOfertaCandidato);
	}

	@Override
	public void update(OfertaCandidatoVO vo) throws BusinessException, ServiceLocatorException {
		getOfferCandidateAppService().update(vo);
	}

	@Override
	public void removeOC(long idOfertaCandidato) throws BusinessException, ServiceLocatorException {
		getOfferCandidateAppService().remove(idOfertaCandidato);
	}
	
	@Override
	public List<OfertaCandidatoVO> misOfertas(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().misOfertas(idCandidato);
	}

    @Override
	public List<OfertaEmpleoJB> misOfertasEmpleo(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().misOfertasEmpleo(idCandidato);
	}

    @Override
    public List<OfertaEmpleoJB> misPostulaciones(long idCandidato) throws BusinessException, ServiceLocatorException {
        return getOfferCandidateAppService().misPostulaciones(idCandidato);
    }

    @Override
    public List<OfertaEmpleoJB> misContratacionPpc(long idOfertaCandidato) throws BusinessException, ServiceLocatorException {
        return getOfferCandidateAppService().miContratacionPpc(idOfertaCandidato);
    }

	public int consultarOfertasContratadoPpc(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().consultarOfertasContratadoPpc(idCandidato);
	}

    @Override
    public List<OfertaEmpleoJB> misOfertasGuardadas(long idCandidato) throws BusinessException, ServiceLocatorException {
        return getOfferCandidateAppService().misOfertasGuardadas(idCandidato);
    }
	
	@Override
	public List<OfertaCandidatoVO> empresasMeBuscan(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().empresasMeBuscan(idCandidato);
	}
	
	public List<OfertaEmpleoJB> empresasMeBuscanOfertas(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().empresasMeBuscanOfertas(idCandidato);
	}
	
	/*@Override
	public int registrarOfertaEmpleo(OfertaEmpleoVO vo) throws BusinessException, ServiceLocatorException {
		return getOfferEmployAppService().registrarOfertaEmpleo(vo);
	}*/

	/*@Override
	public OfertaEmpleoVO buscarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException {
		return getOfferEmployAppService().buscarOfertaEmpleo(idOfertaEmpleo);
	}*/

	/*@Override
	public void actualizarOfertaEmpleo(OfertaEmpleoVO vo) throws BusinessException, ServiceLocatorException {
		getOfferEmployAppService().actualizarOfertaEmpleo(vo);
	}*/

	@Override
	public void eliminarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException {
		getOfferEmployAppService().eliminarOfertaEmpleo(idOfertaEmpleo);
	}
	@SuppressWarnings("rawtypes")
	public List<?> getCurrentPortalOffersCountByEntity() throws BusinessException, ServiceLocatorException {
		return getOfferEmployAppService().getCurrentPortalOffersCountByEntity(); 
	}
	
	public List<OfertasRecientesVO> getOfertasRecientes(int tipoConsulta) throws BusinessException, ServiceLocatorException{
		return getOfferEmployAppService().getOfertasRecientes(tipoConsulta);
	}
	
	
	public List<ParametroVO> getCurrentExternalOffersCount() throws BusinessException, ServiceLocatorException {
		return getOfferEmployAppService().getCurrentExternalOffersCountByEntity();
	}
	
	
	@Override
	public List<OfertaEmpleoVO> listaOfertaEmpleos(long idEmpresa)throws BusinessException, ServiceLocatorException {
		return getOfferEmployAppService().listaOfertaEmpleos(idEmpresa);
	}

	@Override
	public List<OfertaRequisitoVO> ofertaRequisitosList(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException {
		return getOfferRequirementAppService().ofertaRequisitosList(idOfertaEmpleo);
	}

	@Override
	public List<OfertaIdiomaVO> ofertaIdiomasCertList(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException {
		return getOfferLanguagesAppService().ofertaIdiomasList(idOfertaEmpleo);
	}

	@Override
	public HashMap<String, String> getUbicacion(long idOfertaEmpleo) throws SQLException, ServiceLocatorException {
		return getOfferCandidateAppService().getUbicacion(idOfertaEmpleo);
	}
	
	public List<RegistroUbicacionVO> getUbicaciones(long idOfertaEmpleo) throws SQLException, ServiceLocatorException {
		return getOfferCandidateAppService().getUbicaciones(idOfertaEmpleo);
	}

	@Override
	public HashMap<String, String> ofertaIdiomasList(long idOfertaEmpleo) throws SQLException, ServiceLocatorException {
		return getOfferCandidateAppService().ofertaIdiomasList(idOfertaEmpleo);
	}
	
	@Override
	public List<String> ofertaEspecialidadesList(long idOfertaEmpleo, long idNivelEstudio) throws SQLException, ServiceLocatorException {
		return getOfferCandidateAppService().ofertaEspecialidadesList(idOfertaEmpleo, idNivelEstudio);
	}

	@Override
	public String getCatalogoOpcion(long idCatalogo, long idCatalogoOpcion) throws SQLException, ServiceLocatorException {
		return getOfferCandidateAppService().getCatalogoOpcion(idCatalogo, idCatalogoOpcion);
	}

	@Override
	public List<String> sectorList(long idOfertaEmpleo) throws SQLException, ServiceLocatorException {
		return getOfferCandidateAppService().sectorList(idOfertaEmpleo);
	}

	@Override
	public List<String> prestacionesList(long idOfertaEmpleo) throws SQLException, ServiceLocatorException {
		return getOfferCandidateAppService().prestacionesList(idOfertaEmpleo);
	}

	@Override
	public String getMunicipio(long idEntidad, long idMunicipio) throws SQLException, ServiceLocatorException {
		return getOfferCandidateAppService().getMunicipio(idEntidad, idMunicipio);
	}

	@Override
	public int match(long idOfertaEmpleo, long idCandidato) throws ServiceLocatorException {
		return IndexerServiceLocator.getIndexerServiceRemote().match(idOfertaEmpleo, idCandidato);
	}

//	public List<OfertaPorPerfilVO> buscaOfertasRecomendadas(long idCandidato) throws ServiceLocatorException {
//		return getOfferCompatibilityAppService().buscaOfertasRecomendadas(idCandidato);
//	}
	
	/*@Override
	public List<OfertaPorPerfilVO> obtenerOfertasPorPerfil(long idCandidato)throws SQLException, ServiceLocatorException {
		return getOfferCompatibilityAppService().obtenerOfertasPorPerfil(idCandidato);
	}*/
	
	/*public List<OfertaPorPerfilVO> buscarOfertasPorPerfil(long idCandidato)throws SQLException, ServiceLocatorException {
		return getOfferCompatibilityAppService().buscarOfertasPorPerfil(idCandidato);
	}*/
	
//	public List<CandidatoVo> busquedaAsistidaCandidatos(long idOfertaEmpleo)
//	                         throws SQLException, PersistenceException, TechnicalException, ServiceLocatorException{
//		return IndexerServiceLocator.getIndexerServiceRemote().busquedaAsistidaCandidatos(idOfertaEmpleo);
//	}
	
	@Override
	public List<OfertaCandidatoVO> findByOfferCandidate(long idOfertaEmpleo, long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().findByOfferCandidate(idOfertaEmpleo, idCandidato);
	}

	//Son las ofertas de otras bolsas separadas por origen
	public List<OfertaEmpleoVO> consultaOfertasEntidad(int idEntidad)throws SQLException, ServiceLocatorException {
		return getOfferEmployAppService().consultaOfertasEntidad(idEntidad);
	}

	@Override
	//Es el total de las ofertas que vienen de otras bolsas
	public long consultaTotalOfertasPublicadas(){
		long total = 0;
		try{
			total = getOfferEmployAppService().consultaTotalOfertasPublicadas();
		}catch(ServiceLocatorException e){e.printStackTrace();}
		
		return total;
	}

	public OfertaEmpleoJB buscarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException{
		return getOfferCandidateAppService().buscarOfertaEmpleo(idOfertaEmpleo);
	}
	@SuppressWarnings("rawtypes")
	public List<?> getCurrentPortalOffersCountByEntityStudy() throws BusinessException, ServiceLocatorException{
		return getOfferEmployAppService().getCurrentPortalOffersCountByEntityStudy();	
	}
	@SuppressWarnings("rawtypes")
	public List<?> getCurrentPortalOffersCountByEntityExperience() throws BusinessException, ServiceLocatorException{
		return getOfferEmployAppService().getCurrentPortalOffersCountByEntityExperience();
	}
	@SuppressWarnings("rawtypes")
	public List<?> getCurrentOfferEconomicActivity() throws BusinessException, ServiceLocatorException{
		return getOfferEmployAppService().getCurrentOfferEconomicActivity();
	}
	@SuppressWarnings("rawtypes")
	public List<?> getCurrentPortalOffersCountByAreaOcupacion() throws BusinessException, ServiceLocatorException{
		return getOfferEmployAppService().getCurrentPortalOffersCountByAreaOcupacion();
	}	

	public List<OfertaDetalleCortoVO> consultaOfertasDescripcionCorta(List<Long> idsOfertas)  throws ServiceLocatorException {
		return getOfferEmployAppService().consultaOfertasDescripcionCorta(idsOfertas);
	}
	
	public Integer contarNumeroPlazasResultados(List<Long> indicesOfertas) throws ServiceLocatorException {
		return getOfferEmployAppService().contarNumeroPlazasResultados(indicesOfertas);
	}
	
	public List<OfertaCandidatoVO> findCandidatesByEstatus(long idOfertaEmpleo, long estatus) throws ServiceLocatorException{
		return getOfferCandidateAppService().findCandidatesByEstatus(idOfertaEmpleo, estatus);	
	}	
	
	public List<OfertaCandidatoVO> findAllOffersByCandidate(long idCandidato) throws ServiceLocatorException{
		return getOfferCandidateAppService().findAllOffersByCandidate(idCandidato);	
	}

	@Override
	public int registrarPostulacionExterna(PostulacionExternaVO postulacionExternaVo, long idUsuario) throws ServiceLocatorException {
		return getOfferCandidateAppService().registrarPostulacionExterna(postulacionExternaVo, idUsuario);
	}

	@Override
	public int closePendingPostRelatedOffer(long idOfertaEmpleo) throws ServiceLocatorException {
		return getOfferCandidateAppService().closePendingPostRelatedOffer(idOfertaEmpleo);
	}

	@Override
	public List<PostulacionExterna> obtenerPostulacionesCandidato(
			Long idCandidato) throws ServiceLocatorException {
		return getOfferCandidateAppService().obtienePostulacionesCandidato(idCandidato);
		
	}

	@Override
	public boolean darSeguimientoPostulacionExternaContratado(
			PostulacionExterna postulacion,
			List<PostulacionExterna> postulaciones)
			throws ServiceLocatorException {
			return getOfferCandidateAppService().darSeguimientoPostulacionContratado(postulacion, postulaciones);
	}

	@Override
	public boolean darSeguimientoPostulacion(PostulacionExterna postulacion) throws ServiceLocatorException{
		return getOfferCandidateAppService().darSeguimientoPostulacion(postulacion);
	}

	@Override
	public boolean actualizaEstatusPPCCandidato(Long idCandidato)
			throws ServiceLocatorException {
		return getOfferCandidateAppService().actualizaEstatusPPCCandidato(idCandidato);
	}

	@Override
	public void registraBitacora(Long idUsuario, long idPostulacionExt,
			Long idCandidato,Integer estatusPost) throws ServiceLocatorException {
		getOfferCandidateAppService().registraBitacora(idUsuario, idPostulacionExt, idCandidato,estatusPost);
		
	}

	@Override
	public void actualizaPostulacionesSNE(Long idCandidato)
			throws ServiceLocatorException {
		 getOfferCandidateAppService().actualizaPostulacionesSNE(idCandidato);
	}

	@Override
	public List<PostulacionExterna> obtenerPostulacionContratado(
			Long idCandidato) throws ServiceLocatorException {
		// TODO Auto-generated method stub
	return getOfferCandidateAppService().obtenerPostulacionContratado(idCandidato);
	}
	
	@Override
	public boolean validaPostulacionXOferta(long idCandidato, List<Long> listOfertas) throws BusinessException, ServiceLocatorException {
		boolean flag = false;
		int count = getOfferCandidateAppService().getPostulacionCandidate(idCandidato, listOfertas);
		if(count > 0)
			flag = true;
		
		return flag;
	}
}