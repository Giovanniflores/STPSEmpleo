package mx.gob.stps.portal.movil.web.empresa.delegate;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_CANDIDATO;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_CATALOGO_OPCION;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_COMPATIBILITY;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_DOMICILIO;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_EMPRESA;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_OFERTA;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_OFFER_CANDIDATE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceRemote;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.domicilio.service.DomicilioAppServiceRemote;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceRemote;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.service.CatalogoOpcionAppServiceRemote;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaCandidatoAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.service.OfertaCompatibilidadAppServiceRemote;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.oferta.registro.service.OfertaAppServiceRemote;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroUbicacionVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.search.service.IndexadorServiceRemote;
import mx.gob.stps.portal.core.search.service.PortalEmpleoBuscadorServiceRemote;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.service.IndexerServiceLocator;
import mx.gob.stps.portal.movil.web.infra.service.ServiceLocator;
//import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpresaAdminDTO;
import mx.gob.stps.portal.utils.Catalogos;


public class EmpresaEspacioDelegateImpl implements EmpresaEspacioDelegate {

	private static EmpresaEspacioDelegate instance = new EmpresaEspacioDelegateImpl();

	public static EmpresaEspacioDelegate getInstance(){

		return instance;

	}

	private EmpresaAppServiceRemote getEmpresaAppService() throws ServiceLocatorException {
		EmpresaAppServiceRemote ejb = null;
		ejb = (EmpresaAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_EMPRESA);
		return ejb;
	}
	private OfertaCompatibilidadAppServiceRemote getOfertaCompatibilidadAppService() throws ServiceLocatorException {
		OfertaCompatibilidadAppServiceRemote ejb = (OfertaCompatibilidadAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_COMPATIBILITY);
		return ejb;
	}
	
	private OfertaAppServiceRemote getOfertaAppService() throws ServiceLocatorException {
		OfertaAppServiceRemote ejb = (OfertaAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_OFERTA);
		return ejb;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private PortalEmpleoBuscadorServiceRemote getPortalEmpleoBuscadorService() throws ServiceLocatorException {
//		PortalEmpleoBuscadorServiceRemote ejb = (PortalEmpleoBuscadorServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_WS_PORTAL_EMPLEO_BUSCADOR);
//		return ejb;
		return IndexerServiceLocator.getIndexadorServiceRemote();
	}


	public CandidatoAppServiceRemote getCandidatoAppService()throws ServiceLocatorException {
		CandidatoAppServiceRemote ejb = (CandidatoAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_CANDIDATO);
		return ejb;
	}
	
	private CatalogoOpcionAppServiceRemote getCatalogoOpcionAppService() throws ServiceLocatorException{
		CatalogoOpcionAppServiceRemote ejb = (CatalogoOpcionAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_CATALOGO_OPCION);
		return ejb;
	}
	
	private DomicilioAppServiceRemote getDomicilioAppService() throws ServiceLocatorException{
		DomicilioAppServiceRemote ejb = (DomicilioAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_DOMICILIO);
		return ejb;
	}
	
	private OfertaCompatibilidadAppServiceRemote getOfferCompatibilityAppService() throws ServiceLocatorException {
		OfertaCompatibilidadAppServiceRemote ejb = (OfertaCompatibilidadAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_COMPATIBILITY);
		return ejb;
	}
	
	private OfertaCandidatoAppServiceRemote getOfferCandidateAppService() throws ServiceLocatorException {
		OfertaCandidatoAppServiceRemote ejb = (OfertaCandidatoAppServiceRemote)ServiceLocator.getSessionRemote(JNDI_EJB_OFFER_CANDIDATE);
		return ejb;
	}
	

	@Override
	public EmpresaVO findEmpresaById(long id) throws BusinessException,
	SQLException, ServiceLocatorException {
		return getEmpresaAppService().findEmpresaById(id);
	}
	
	@Override
	public EmpresaVO findByIdUsuario(long idUsuario)
			throws PersistenceException, BusinessException, ServiceLocatorException {
		return getEmpresaAppService().findEmpresaByIdUsuario(idUsuario);

	}

	
	@Override
	public Map<String, String> obtenerActividadEconomica(long idActEco) throws BusinessException,
	SQLException, ServiceLocatorException{
		return getEmpresaAppService().obtenerActividadEconomica(idActEco);
	}

	@Override
	public EmpresaVO consultaEmpresaPorAutorizar(long idEmpresa) throws ServiceLocatorException {
		return getEmpresaAppService().consultaEmpresaPorAutorizar(idEmpresa);
	}
	
	

	@Override
	public List<Long> buscarCandidatosEmpleo(String cadena) throws TechnicalException, SQLException, ServiceLocatorException {
		//return getPortalEmpleoBuscadorService().buscarCandidatosEmpleo(cadena);
		return IndexerServiceLocator.getIndexadorServiceRemote().buscarCandidatosEmpleo(cadena);
	}
	
	@Override
	public List<Long> buscarCandidatosEmpleo(String search, Integer idEntidad) throws TechnicalException, SQLException, ServiceLocatorException {
		return IndexerServiceLocator.getIndexadorServiceRemote().buscarCandidatosEmpleo(search, idEntidad);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List buscadorCandidatos(int page, List<?> index)throws TechnicalException, SQLException, ServiceLocatorException {
		return getPortalEmpleoBuscadorService().buscadorCandidatos(page, index);
	}

	public InformacionGeneralVO showCandidateDetail(long idCandidato) throws ServiceLocatorException {
		return getCandidatoAppService().buscarInformacionGeneral(idCandidato);
	}

	public int match(long idOfertaEmpleo, long idCandidato)  throws ServiceLocatorException {
		return IndexerServiceLocator.getIndexadorService().match(idOfertaEmpleo, idCandidato);
	}
	
	@Override
	public PerfilLaboralVo consultaPerfilLaboral(long idCandidato)throws ServiceLocatorException, PersistenceException {
		return getCandidatoAppService().consultaPerfilLaboral(idCandidato);
	}

	@Override
	public List<OfertaEmpleoVO> obtenerOfertasEmpresa(long idEmpresa)throws BusinessException, ServiceLocatorException {
		return getOfertaAppService().obtenerOfertasEmpresa(idEmpresa);
	}
	
	public String getCatalogoOpcionById(long idCatalogo, long idCatalogoOpcion) throws ServiceLocatorException, SQLException {
		return getCatalogoOpcionAppService().getOpcionById(idCatalogo, idCatalogoOpcion);
	}

	@Override
	public MunicipioVO consultaMunicipio(long idEntidad, long idMunicipio) throws PersistenceException, ServiceLocatorException {
		return getDomicilioAppService().consultaMunicipio(idMunicipio,idEntidad);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CandidatoVo> busquedaAsistidaCandidatos(long idOfertaEmpleo) throws Exception {
		IndexadorServiceRemote indexadorService = IndexerServiceLocator.getIndexadorService();
		  List<Long[]> listado = indexadorService.busquedaAsistidaCandidatos(idOfertaEmpleo, Constantes.COMPATIBILITY_LIMIT, null, null);
	//	List<Long[]> listado = IndexerServiceLocator.getIndexadorServiceRemote().busquedaAsistidaCandidatos(idOfertaEmpleo,Constantes.COMPATIBILITY_LIMIT,null,null);
		List<CandidatoVo> candidatos = new ArrayList<CandidatoVo>(); 
		for(Long[] idCandidato :listado){
			CandidatoVo candidato = getCandidatoAppService().findById(idCandidato[0]);
			DomicilioVO domicilio = getCandidatoAppService().findDomicilioCandidato(idCandidato[0]);
			candidato.setCompatibilidad((int)(long)idCandidato[1]);
			candidato.setDomicilioVo(domicilio);
			candidato.setMunicipioEntidad(domicilio.getDomiciloCompletoString());
			
			candidatos.add(candidato);
		}
		return candidatos;
		//return IndexerServiceLocator.getIndexadorServiceRemote().busquedaCandidatos(idOfertaEmpleo);
		//return getOfertaCompatibilidadAppService().busquedaAsistidaCandidatos(idOfertaEmpleo);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Long[]> busquedaAsistidaIdCandidatos(long idOfertaEmpleo) throws PersistenceException, SQLException, TechnicalException, ServiceLocatorException {
		List<Long[]> listado = IndexerServiceLocator.getIndexadorService().busquedaAsistidaCandidatos(idOfertaEmpleo,Constantes.COMPATIBILITY_LIMIT);
		
		return listado;
	}

	@Override
	public List<IdiomaVO> buscarIdiomas(long idCandidato) throws SQLException, ServiceLocatorException {
		return getCandidatoAppService().buscarIdiomas(idCandidato);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CandidatoVo> buscadorCandidatos(List<Long> index) throws TechnicalException, SQLException, ServiceLocatorException{
		return getPortalEmpleoBuscadorService().buscadorCandidatos(index);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CandidatoVo> buscadorCandidatosQuery(List<Long> indices) throws TechnicalException, SQLException, ServiceLocatorException{
		return getEmpresaAppService().buscadorCandidatosQuery(indices);
	}

	@Override
	public List<OfertaCandidatoVO> findByOfferCandidate(long idOfertaEmpleo,
			long idCandidato) throws BusinessException, ServiceLocatorException {
		return getOfferCandidateAppService().findByOfferCandidate(idOfertaEmpleo, idCandidato);
	}

	@Override
	public List<OfertaCandidatoVO> findCandidatesByEstatus(long idOfertaEmpleo, long estatus) throws PersistenceException, ServiceLocatorException{
		return getOfferCandidateAppService().findCandidatesByEstatus(idOfertaEmpleo, estatus);
	}
	@Override
	public void createOfertaCandidato(OfertaCandidatoVO ofertaCandidato) throws BusinessException, ServiceLocatorException {
		getOfferCandidateAppService().create(ofertaCandidato);
	}
	

	@Override
	public List<RegistroUbicacionVO> getUbicacionesOferta(long idOfertaEmpleo) throws SQLException, ServiceLocatorException {
		return getOfferCandidateAppService().getUbicaciones(idOfertaEmpleo);
	}

	@Override
	public List<OfertaCandidatoOcupacionDTO>  obtenerOfertaCandidatoEmpresa(Long idEmpresa, List<Constantes.ESTATUS> estatus,List<Constantes.ESTATUS> estatusOferta)  throws PersistenceException, ServiceLocatorException{
		// 
		return getOfferCandidateAppService().obtenerOfertaCandidatoEmpresa(idEmpresa,estatus, estatusOferta);
		
	}


	@Override
	public List<OfertaEmpresaAdminDTO> obtenerOfertasEmpresa(Long idEmpresa,
			List<Catalogos.ESTATUS> estatus,List<Catalogos.ESTATUS> estatusOferta) throws PersistenceException, ServiceLocatorException {
		return getOfferCandidateAppService().obtenerOfertasEmpresa(idEmpresa, estatus,  estatusOferta);
		
	}
	
	@Override
	public List<OfertaCandidatoOcupacionDTO> obtenerOfertaCandidatoEmpresaPorEstatus(
			Long idEmpresa,


			List<Catalogos.ESTATUS> candidatoEstatus,Long idOferta, List<Catalogos.ESTATUS> ofertaEstatus)  throws ServiceLocatorException {
		return getOfferCandidateAppService().obtenerOfertaCandidatoEmpresaPorEstatus(idEmpresa,  candidatoEstatus, idOferta, ofertaEstatus);

	
	}

	@Override
	public void notificacionRecuperacionPswEmpresa(long idEmpresa, String usuario,
			String nombreEmpresa, String correoElectronico, String clave)
			throws MailException, ServiceLocatorException {
		
		//TODO: verificar la firma de llamada de este metodo
		//getEmpresaAppService().notificacionRecuperacionPswEmpresa(idEmpresa, usuario, nombreEmpresa, correoElectronico, clave);
	}
	
	@Override
	public void notificacionRecuperacionPswMovilEmpresa(long idEmpresa,
			String nombreEmpresa, String correoElectronico, String clave, String usuario)
			throws MailException, ServiceLocatorException {
		
		getEmpresaAppService().notificacionRecuperacionPswMovilEmpresa(idEmpresa, nombreEmpresa, correoElectronico, clave,usuario);
	}

	@Override
	public List<CandidatoVo> busquedaCandidatos(Long idOfertaEmpleo) throws Exception {
		List<CandidatoVo> candidatos = IndexerServiceLocator.getIndexadorServiceRemote().busquedaCandidatos(idOfertaEmpleo);
		return candidatos;
	}

	

}
