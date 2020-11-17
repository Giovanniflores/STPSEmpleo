package mx.gob.stps.portal.movil.web.oferta.delegate;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_CATALOGO_OPCION;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_COMPATIBILITY;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_OFERTA;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_OFFER_CANDIDATE;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_OFFER_POST;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_OFFER_QUESTION;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.NotificacionCandidatoVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.service.CatalogoOpcionAppServiceRemote;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaCandidatoAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaCompatibilidadAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaPostulacionAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaPostulacionVO;
import mx.gob.stps.portal.core.oferta.pregunta.service.OfertaPreguntaAppServiceRemote;
import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;
import mx.gob.stps.portal.core.oferta.registro.service.OfertaAppServiceRemote;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.movil.web.infra.service.IndexerServiceLocator;
import mx.gob.stps.portal.core.search.service.IndexadorServiceRemote;
import mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceRemote;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.persistencia.entity.NotificacionCandidato;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales;

public final class OfertaDelegateImpl {

	private static OfertaDelegateImpl instance = new OfertaDelegateImpl();
	
	private OfertaDelegateImpl(){}	

	public static OfertaDelegateImpl getInstance(){
		return instance;
	}
	
	private PortalEmpleoBuscadorServiceRemote getPortalEmpleoBuscadorService() throws ServiceLocatorException {
//		PortalEmpleoBuscadorServiceRemote ejb = (PortalEmpleoBuscadorServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR);
//		return ejb;
		return IndexerServiceLocator.getIndexadorServiceRemote();
	}	
	
	private IndexadorServiceRemote getPEBuscadorService() throws ServiceLocatorException {
		return IndexerServiceLocator.getIndexadorService();
	}	

	private OfertaCandidatoAppServiceRemote getOfferCandidateAppService() throws ServiceLocatorException {
		OfertaCandidatoAppServiceRemote ejb = (OfertaCandidatoAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_OFFER_CANDIDATE);
		return ejb;
	}
	
	private OfertaAppServiceRemote getOfertaAppService() throws ServiceLocatorException {
		OfertaAppServiceRemote ejb = (OfertaAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_OFERTA);
		return ejb;
	}

	private OfertaPostulacionAppServiceRemote getOfertaPostulacionAppService() throws ServiceLocatorException {
		OfertaPostulacionAppServiceRemote ejb = (OfertaPostulacionAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_OFFER_POST);
		return ejb;
	}
	
	private OfertaPreguntaAppServiceRemote getOfertaPreguntaAppService() throws ServiceLocatorException {
		OfertaPreguntaAppServiceRemote ejb = (OfertaPreguntaAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_OFFER_QUESTION);
		return ejb;
	}

	private OfertaCompatibilidadAppServiceRemote getOfferCompatibilityAppService() throws ServiceLocatorException {
		OfertaCompatibilidadAppServiceRemote ejb = (OfertaCompatibilidadAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_COMPATIBILITY);
		return ejb;
	}

	
	private CatalogoOpcionAppServiceRemote getCatalogoOpcionAppService() throws ServiceLocatorException {
		CatalogoOpcionAppServiceRemote ejb = (CatalogoOpcionAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_CATALOGO_OPCION);
		return ejb;
	}
	
	public List<ResultInfoBO> buscarOfertasEmpleo(String cadena, Integer location) throws TechnicalException, SQLException, ServiceLocatorException {
    	return getPortalEmpleoBuscadorService().buscarOfertasEmpleo(cadena, location);
    }
	
	@SuppressWarnings("rawtypes")
	public List buscarOfertasEspecificas(int page, List<Long> index, String pattern) throws TechnicalException, SQLException, ServiceLocatorException {
	 	return getPortalEmpleoBuscadorService().buscarOfertasEspecificas(page, index, pattern);
	}
 
	public List buscarOfertasOcupate(int page, List<ResultInfoBO> index) throws TechnicalException, SQLException, ServiceLocatorException {
		return getPortalEmpleoBuscadorService().buscarOfertasOcupate(page, index);
		
	}	

	public OfertaEmpleoJB buscarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException{
		return getOfferCandidateAppService().buscarOfertaEmpleo(idOfertaEmpleo);
	}

	public List<OfertaEmpleoJB> buscarOfertasEmpleo(List<Long> idsOfertas) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().buscarOfertasEmpleo(idsOfertas);
	}
	
	public void actualizarFechaOfertaCanceladaActiva(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException {
		getOfertaAppService().updateFechaOfertaCanceladaActivada(idOfertaEmpleo);
		
	}
	
	public void insertarCandidatoNotificacion(NotificacionCandidato candidato) throws ServiceLocatorException, BusinessException{
		getOfertaAppService().insertarNotificacionCandidato(candidato);
	}
	
	public long findCandidatoNotificacion(Long idCantidato) throws ServiceLocatorException{
		return getOfertaAppService().findCandidatoNotificacion(idCantidato);
		 
	}
	public void updateCandidatoNotificacion(NotificacionCandidato candidato) throws ServiceLocatorException, BusinessException{
		getOfertaAppService().updateCandidatoNotificacion2(candidato);
	}
	
	public void updateEstatusNotificacion (NotificacionCandidato candidato) throws ServiceLocatorException, BusinessException{
		getOfertaAppService().updateEstatusNotificacion(candidato);
	}
	
	public List<OfertaCandidatoVO> findByOfferCandidate(long idOfertaEmpleo, long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().findByOfferCandidate(idOfertaEmpleo, idCandidato);
	}

	public int postulaOferta(OfertaCandidatoVO vo) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().create(vo);
	}

	public void actualizaPostulacionOferta(OfertaCandidatoVO vo) throws BusinessException, ServiceLocatorException {
		if(vo.getEstatus() == ESTATUS.EN_PROCESO.getIdOpcion() || 
				vo.getEstatus() == ESTATUS.CONTRATADO.getIdOpcion() ||
				vo.getEstatus() == ESTATUS.NO_ACEPTADO.getIdOpcion()) {
			vo.setFechaSeguimiento(Calendar.getInstance().getTime());
				
			}
		if(vo.getEstatus() != ESTATUS.CONTRATADO.getIdOpcion()){
			vo.setFechaInicioContratacion(null);
		}
		
		//ESTATUS != 20 OR ESTATUS = 20 AND FECHA_INICIO_CONTRATA IS NOT NULL
		if(vo.getEstatus() == ESTATUS.EN_PROCESO.getIdOpcion()){
			vo.setFechaInicioContratacion(Calendar.getInstance().getTime());
		}
		getOfferCandidateAppService().update(vo);
	}
	
	public void eliminaAsociacionConOferta(long idOfertaCandidato) throws BusinessException, ServiceLocatorException {
		getOfferCandidateAppService().remove(idOfertaCandidato);
	}

	public OfertaPostulacionVO consultaOfertaPostulada(long idOfertaEmpleo, int anio, int mes) throws BusinessException, ServiceLocatorException {
		return getOfertaPostulacionAppService().findByPK(idOfertaEmpleo, anio, mes);
	}

	public int inicializaContadorPostulacion(OfertaPostulacionVO vo) throws BusinessException, ServiceLocatorException {
		return getOfertaPostulacionAppService().create(vo);
	}
	
	public void incrementaContadorPostulacion(OfertaPostulacionVO vo) throws BusinessException, ServiceLocatorException {
		getOfertaPostulacionAppService().update(vo);
	}

	public List<OfertaPreguntaVO> ofertaPreguntasList(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException {
		return getOfertaPreguntaAppService().ofertaPreguntasList(idOfertaEmpleo);
	}

	public OfertaPreguntaVO obtieneOfertaPregunta(long idOfertaPregunta) throws BusinessException, ServiceLocatorException {
		return getOfertaPreguntaAppService().findById(idOfertaPregunta);
	}

	public void updateOfertaPregunta(OfertaPreguntaVO ofertaPreguntaVO) throws BusinessException, ServiceLocatorException {
		getOfertaPreguntaAppService().update(ofertaPreguntaVO);
		
	}
	
	public List<OfertaEmpleoJB> misOfertasEmpleo(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().misOfertasEmpleo(idCandidato);
	}
	
	public List<OfertaCandidatoVO> empresasMeBuscan(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().empresasMeBuscan(idCandidato);
	}
	
	public List<OfertaEmpleoJB> empresasMeBuscanOfertas(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().empresasMeBuscanOfertas(idCandidato);
	}

	public List<OfertaPorPerfilVO> buscaOfertasRecomendadas(long idCandidato) throws ServiceLocatorException {
		return getPortalEmpleoBuscadorService().buscarOfertasRecomendadas(idCandidato, Constantes.COMPATIBILITY_LIMIT);
		
	}
	
	public List<Long[]> buscarOfertasRecomendadas(long idCandidato, int compatibilidadLimite) throws ServiceLocatorException {
		return getPEBuscadorService().buscaOfertasRecomendadas(idCandidato, compatibilidadLimite);
		
	}

	public int match(long idOfertaEmpleo, long idCandidato) throws ServiceLocatorException {
		return getOfferCompatibilityAppService().match(idOfertaEmpleo, idCandidato);
	}

	public void activaOfertaEmpleo(long idOfertaEmpleo, long idUsuario) throws ServiceLocatorException {
		getOfertaAppService().activaOfertaEmpleo(idOfertaEmpleo, idUsuario);
		
	}

	public void cancelaOfertaEmpleo(long idOfertaEmpleo, long idUsuario) throws ServiceLocatorException {
		getOfertaAppService().cancelaOfertaEmpleo(idOfertaEmpleo, idUsuario);
		
	}
	
	public void eliminaOfertaPorEmpresa(long idOfertaEmpleo, long idUsuario) throws ServiceLocatorException {
		getOfertaAppService().eliminaOfertaPorEmpresa(idOfertaEmpleo, idUsuario);
		
	}

	public List<OfertaCandidatoVO> consultaOfertasCandidato(int idOfertaEmpleo, long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().findByOfferCandidate(idOfertaEmpleo, idCandidato);	
	}

	public String getOpcionById(long catalogoOpcionEntidadFederativa,
			int intValue) throws ServiceLocatorException {
		return getCatalogoOpcionAppService().getOpcionById(catalogoOpcionEntidadFederativa, intValue);
	}
	
	public OfertaEmpleoVO consultaOfertaPush(long idOfertaEmpleo) throws ServiceLocatorException {
		return getOfertaAppService().consultaOfertaPush(idOfertaEmpleo);
	}
	
	public OfertaEmpleoVO consultaOfertaEmpleo(long idOfertaEmpleo) throws ServiceLocatorException {
		return getOfertaAppService().consultaOfertaEmpleo(idOfertaEmpleo);
	}
	
	public OfertaEmpleoVO consultaOfertaEmpleoActiva(long idOfertaEmpleo) throws ServiceLocatorException {
		return getOfertaAppService().consultaOfertaEmpleoActiva(idOfertaEmpleo);
	}




	public OfertaCandidatoVO findById(long idOfertaCandidato)
			throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().findById(idOfertaCandidato);
	}


	public void desactivarOfertaPostulado(OfertaCandidatoVO vo)
			throws BusinessException, ServiceLocatorException {
		vo.setIdFuente((long) Catalogos.APLICACION.PORTAL_DEL_EMPLEO
				.getIdOpcion());
		vo.setIdOficina(ConstantesGenerales.PORTAL_ID_OFICINA);
		vo.setIdVinculado((long) Catalogos.APLICACION.PORTAL_DEL_EMPLEO
				.getIdOpcion());
		vo.setPostulacionCartera(0);

		getOfferCandidateAppService().update(vo);
	}


	public List<OfertaEmpleoJB> empresasMeBuscanOfertas(
			long idCandidato,
			List<Catalogos.ESTATUS> listEstatus,
			int diasDifferencia) throws BusinessException,
			ServiceLocatorException {
		return getOfferCandidateAppService().empresasMeBuscanOfertas(
				idCandidato, listEstatus, diasDifferencia);
	}





	// Start cambio movil
	public List<OfertaEmpleoJB> misOfertasEmpleo(
			long idCandidato,
			List<Catalogos.ESTATUS> listEstatus,
			int diasDifferencia) throws BusinessException,
			ServiceLocatorException {
		return getOfferCandidateAppService().misOfertasEmpleo(idCandidato,
				listEstatus, diasDifferencia);
	}
	// Fin cambio movil

}