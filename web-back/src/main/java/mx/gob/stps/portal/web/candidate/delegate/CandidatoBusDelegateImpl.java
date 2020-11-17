package mx.gob.stps.portal.web.candidate.delegate;

import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_CANDIDATO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_DEPURACION_CANDIDATO_VIGENCIA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_REPORTE_INFOVAVIT;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_TELEFONO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.reporteInfonavit.service.ReporteInfonavitServiceRemote;
import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceRemote;
import mx.gob.stps.portal.core.candidate.vo.BusquedaCandidatosVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoOtroEstudioVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ComputacionAvanzadaVO;
import mx.gob.stps.portal.core.candidate.vo.ConocerConfigVO;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.core.candidate.vo.CurriculumVo;
import mx.gob.stps.portal.core.candidate.vo.EscolaridadVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaLugarVO;
import mx.gob.stps.portal.core.candidate.vo.ExpectativaVO;
import mx.gob.stps.portal.core.candidate.vo.ExperienciaVO;
import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.core.candidate.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.candidate.vo.OtroMedioVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.candidate.vo.ResultadoBusquedaCandidatosVO;
import mx.gob.stps.portal.core.debbuger.service.DepuracionCandidatosAppServiceRemote;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.service.TelefonoAppServiceRemote;
import mx.gob.stps.portal.core.infra.utils.Constantes.MULTIREGISTRO;
import mx.gob.stps.portal.core.infra.utils.Constantes.RESPUESTA_IMMS_CONSULTA_NSS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.CandidatoVerDetalleVO;
import mx.gob.stps.portal.core.oferta.detalle.bo.PerfilBO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoADesactivarVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.persistencia.entity.Solicitante;
import mx.gob.stps.portal.persistencia.vo.BeneficiarioVO;
import mx.gob.stps.portal.persistencia.vo.CanalizacionCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.EstandarConocerVO;
import mx.gob.stps.portal.persistencia.vo.FormatoLPAVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATCultivosVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATHclinicaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATMaquinariaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATVO;
import mx.gob.stps.portal.persistencia.vo.FormatoSNE01VO;
import mx.gob.stps.portal.persistencia.vo.ModalidadCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadPtatBeneficiarioVO;
import mx.gob.stps.portal.persistencia.vo.ReferenciaLaboralVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.company.form.BusquedaCandidatosForm;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.offer.wrapper.PerfilJB;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.ws.conocer.exception.ConocerWSException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * @author Felipe Juárez Ramírez
 * @since 28 de Febrero de 2011
 * @category BusinessDelegate
 */
public final class CandidatoBusDelegateImpl implements CandidatoBusDelegate {

	private static Logger logger = Logger.getLogger(CandidatoBusDelegateImpl.class);

	private static CandidatoBusDelegate instance = new CandidatoBusDelegateImpl();

	private CandidatoBusDelegateImpl() {
	}

	/**
	 * Obtiene una instancia de la Interfaz
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 28/02/2011
	 * @return CandidatoBusDelegate
	 * */
	public static CandidatoBusDelegate getInstance() {
		return instance;
	}

	/**
	 * Realiza la invocación remota del servicio de Candidatos
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 28/02/2011
	 * @throws ServiceLocatorException
	 * @return CandidatoAppServiceRemote
	 * */
	public CandidatoAppServiceRemote getCandidatoAppService() throws ServiceLocatorException {
		CandidatoAppServiceRemote ejb = (CandidatoAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_CANDIDATO);
		return ejb;
	}
	
	public DepuracionCandidatosAppServiceRemote getDepuracionCandidatoAppService() throws ServiceLocatorException {
		DepuracionCandidatosAppServiceRemote ejb = (DepuracionCandidatosAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_DEPURACION_CANDIDATO_VIGENCIA);
		return ejb;
	}
	
	/**
	 * Realiza la invocación remota del servicio de Telefonos
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 29/03/2011
	 * @param PerfilForm
	 * @throws BusinessException
	 *             , ServiceLocatorException
	 * @return void
	 * */
	public TelefonoAppServiceRemote getTelefonoAppService() throws ServiceLocatorException {
		TelefonoAppServiceRemote ejb = (TelefonoAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_TELEFONO);
		return ejb;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#init(mx
	 * .gob.stps.portal.web.candidate.form.PerfilForm)
	 */
	public PerfilVO initPerfil(long idCandidato) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarPerfil(idCandidato);
	}
	
	//Obtener Solicitante OAM
		public Solicitante obtenerSolicitante(long idCandidato) throws BusinessException, ServiceLocatorException{
			return getCandidatoAppService().obtenerSolicitante(idCandidato);
		}
	
	@Override
	public List<TelefonoVO> initTelefonos(long idCandidato, long idTipoPropietario) throws BusinessException, ServiceLocatorException {
		return getTelefonoAppService().consultarTelefonos(idCandidato, idTipoPropietario);
	}
	
	@Override
	public List<OtroMedioVO> initOtrosMedios(long idCandidato) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarOtrosMedios(idCandidato);
	}
	
	@Override
	public PerfilVO initPerfilCandidato(long idCandidato) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarPerfilCandidato(idCandidato);
	}
	
	public void iniciarDepuracionDeCandidatosPorVigencia(){
		try {
			getDepuracionCandidatoAppService().iniciaProcesoRecurrente();
		} catch (ServiceLocatorException e) {
			logger.error(e);
		}
	}
	
	public void detenerDepuracionDeCandidatosPorVigencia(){
		try {
			getDepuracionCandidatoAppService().detieneProcesoRecurrente();
		} catch (ServiceLocatorException e) {
			logger.error(e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * registrarPerfil(mx.gob.stps.portal.core.candidate.vo.PerfilVO)
	 */
	public PerfilVO registrarPerfil(PerfilVO perfil) throws BusinessException, ServiceLocatorException, PersistenceException, TechnicalException, SQLException, MailException, IndexerException{
		return getCandidatoAppService().registrarPerfil(perfil);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * registrarEscolaridad(mx.gob.stps.portal.core.candidate.vo.EscolaridadVO)
	 */
	@Override
	public EscolaridadVO registrarEscolaridad(EscolaridadVO escolaridad) throws BusinessException, ServiceLocatorException, PersistenceException, TechnicalException, SQLException, IndexerException {
		return getCandidatoAppService().registrarEscolaridad(escolaridad);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * initGrados(long)
	 */
	@Override
	public List<GradoAcademicoVO> initGrados(long idCandidato, int principal) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarGrados(idCandidato, principal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * agregarGrado(long, GradoAcademicoVO)
	 */
	@Override
	public void agregarGrado(long idCandidato, GradoAcademicoVO gradoVO) throws ServiceLocatorException, PersistenceException {
		gradoVO.setIdCandidatoGradoAcademico(getCandidatoAppService().agregarGrado(idCandidato, gradoVO));
		//logger.info("idGenerado: " + gradoVO.getIdCandidatoGradoAcademico());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * borrarGrado(long)
	 */
	@Override
	public void borrarGrado(long idCandidatoGradoAcademico) throws ServiceLocatorException, PersistenceException {
		getCandidatoAppService().borrarGrado(idCandidatoGradoAcademico);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * initIdiomas(IdiomaForm)
	 */
	@Override
	public List<IdiomaVO> initIdiomas(long idCandidato, int principal) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarIdiomas(idCandidato, principal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * agregarGrado(long, IdiomaVO)
	 */
	@Override
	public void agregarIdioma(long idCandidato, IdiomaVO idiomaVO)throws ServiceLocatorException, PersistenceException {
		idiomaVO.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());

		long idCandidatoIdioma = getCandidatoAppService().agregarIdioma(idCandidato, idiomaVO);

		idiomaVO.setIdCandidatoIdioma(idCandidatoIdioma);
		//logger.info("idGenerado: " + idiomaVO.getIdCandidatoIdioma());
	}

	/*
	 * (non-Javadoc) 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * borrarIdioma(long)
	 */
	@Override
	public void borrarIdioma(long idCandidatoIdioma) throws ServiceLocatorException, PersistenceException {
		getCandidatoAppService().borrarIdioma(idCandidatoIdioma);
	}
	/*
	 * (non-Javadoc) 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * initConocsHabs(ConocimHabilidadForm)
	 */
	@Override
	public List<ConocimientoHabilidadVO> initConocsHabs(long idCandidato, long idTipoConocimHabilidad, int principal) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarConocHabs(idCandidato, idTipoConocimHabilidad, principal);
	}
	
	@Override
	public List<ConocimientoHabilidadVO> buscarConocsHabs(long idCandidato, long idTipoConocimHabilidad) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarConocHabs(idCandidato, idTipoConocimHabilidad);
	}	
	
	public List<Long> consultaHabilidades(long idCandidato) throws ServiceLocatorException {
		return getCandidatoAppService().consultaHabilidades(idCandidato);
	}
	
	/*
	 * (non-Javadoc) 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * agregarConocHab(long, ConocimientoHabilidadVO)
	 */
	@Override
	public void agregarConocHab(long idCandidato, ConocimientoHabilidadVO conocHabVO) throws ServiceLocatorException, PersistenceException {
		conocHabVO.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		long idCandidatoConocimHabilidad = getCandidatoAppService().agregarConocHab(idCandidato, conocHabVO);
		conocHabVO.setIdCandidatoConocimHabilidad(idCandidatoConocimHabilidad);
	}
	/*
	 * (non-Javadoc) 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * borrarConocHab(long, int)
	 */
	@Override
	public void borrarConocHab(long idCandidatoConocimHabilidad) throws ServiceLocatorException, PersistenceException {
		getCandidatoAppService().borrarConocHab(idCandidatoConocimHabilidad);
		
	}
	/*
	 * (non-Javadoc) 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * initCompuAvanzadas(ComputacionAvanzadaForm)
	 */
	@Override
	public List<ComputacionAvanzadaVO> initCompuAvanzadas(long idCandidato, int principal)throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarCompuAvanzadas(idCandidato, principal);
	}
	/*
	 * (non-Javadoc) 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * agregarCompuAvanzada(long, ComputacionAvanzadaVO)
	 */
	@Override
	public void agregarCompuAvanzada(long idCandidato, ComputacionAvanzadaVO compAvanVO) throws ServiceLocatorException, PersistenceException {
		compAvanVO.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		long idCandidatoCompuAvanzada = getCandidatoAppService().agregarCompuAvanzada(idCandidato, compAvanVO);
		compAvanVO.setIdCandidatoCompuAvanzada(idCandidatoCompuAvanzada);
	}
	/*
	 * (non-Javadoc) 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * borrarCompuAvanzada(long)
	 */
	@Override
	public void borrarCompuAvanzada(long idCandidatoCompuAvanzada) throws ServiceLocatorException, PersistenceException {
		getCandidatoAppService().borrarCompuAvanzada(idCandidatoCompuAvanzada);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * registrarExperiencia(mx.gob.stps.portal.core.candidate.vo.EscolaridadVO)
	 */
	@Override
	public ExperienciaVO registrarExperiencia(ExperienciaVO experiencia) throws BusinessException, ServiceLocatorException, PersistenceException, TechnicalException, SQLException, IndexerException {
		return getCandidatoAppService().registrarExperiencia(experiencia);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * initHistLaboral(HistoriaLaboralForm)
	 */
	@Override
	public List<HistoriaLaboralVO> initHistLaboral(long idCandidato, int principal) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarHistLaboral(idCandidato, principal);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * agregarHistLaboral(long, HistoriaLaboralVO)
	 */
	@Override
	public void agregarHistLaboral(long idCandidato, HistoriaLaboralVO histLaboralVO) throws ServiceLocatorException, PersistenceException {
		histLaboralVO.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		histLaboralVO.setIdHistorialLaboral(getCandidatoAppService().agregarHistLaboral(idCandidato, histLaboralVO));
		//logger.info("idGenerado: " + histLaboralVO.getIdHistorialLaboral());
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * borrarHistLaboral(long)
	 */
	@Override
	public void borrarHistLaboral(long idHistorialLaboral) throws ServiceLocatorException, PersistenceException {
		getCandidatoAppService().borrarHistLaboral(idHistorialLaboral);
	}
	
	@Override
	public ExpectativaVO registrarExpectativa(ExpectativaVO expectativa) throws BusinessException, ServiceLocatorException, PersistenceException, TechnicalException, SQLException, IndexerException {
		return getCandidatoAppService().registrarExpectativa(expectativa);
	}
	
	@Override
	public List<ExpectativaLaboralVO> initExpecLaboral(long idCandidato, int principal) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarExpecLaboral(idCandidato, principal);
	}

	@Override
	public void agregarExpecLaboral(long idCandidato,ExpectativaLaboralVO expecLaboralVO) throws ServiceLocatorException, PersistenceException {
		expecLaboralVO.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		expecLaboralVO.setIdExpectativaLaboral(getCandidatoAppService().agregarExpecLaboral(idCandidato, expecLaboralVO));
		//logger.info("idGenerado: " + expecLaboralVO.getIdExpectativaLaboral());
	}

	@Override
	public void borrarExpecLaboral(long idExpectativaLaboral) throws ServiceLocatorException, PersistenceException {
		getCandidatoAppService().borrarExpecLaboral(idExpectativaLaboral);
	}
	
	@Override
	public List<ExpectativaLugarVO> initExpecLugar(long idCandidato, int principal) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarExpecLugar(idCandidato, principal);
	}

	@Override
	public void agregarExpecLugar(long idCandidato, ExpectativaLugarVO expecLugarVO) throws ServiceLocatorException, PersistenceException {
		expecLugarVO.setPrincipal(MULTIREGISTRO.ADICIONAL.getIdOpcion());
		expecLugarVO.setIdExpectativaLugar(getCandidatoAppService().
				agregarExpecLugar(idCandidato, expecLugarVO));
		//logger.info("idGenerado: " + expecLugarVO.getIdExpectativaLugar());
	}

	@Override
	public void borrarExpecLugar(long idExpectativaLugar) throws ServiceLocatorException, PersistenceException {
		getCandidatoAppService().borrarExpecLugar(idExpectativaLugar);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * actualizarCurriculum(long,
	 * mx.gob.stps.portal.core.candidate.vo.CurriculumVo)
	 */
	@Override
	public void actualizarCurriculum(UsuarioWebVO usuario, CurriculumVo vo) throws BusinessException, ServiceLocatorException {
		try {
			getCandidatoAppService().actualizarCurriculum(usuario.getIdPropietario(), usuario.getIdPerfil(), vo);
		} catch (PersistenceException pe) {
			pe.printStackTrace();
			
			if (pe.getMessage().equalsIgnoreCase("No existe registro")) {
				// Se reutiliza el mensaje para guardarFoto(long, byte[])
				throw new BusinessException("can.foto.sinPerfil.err"); 
			} else {
				throw new BusinessException("app.exp.tecnica.err");
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void guardarFoto(UsuarioWebVO usuario, byte[] fotografia) throws BusinessException, ServiceLocatorException {
		try {
			getCandidatoAppService().guardarFoto(usuario.getIdPropietario(), usuario.getIdPerfil(), fotografia);
		} catch (PersistenceException pe) {
			if (pe.getMessage().equalsIgnoreCase("No existe registro")) {
				throw new BusinessException("can.foto.sinPerfil.err");
			} else {
				throw new BusinessException("app.exp.tecnica.err");
			}
		}
	}
	
	@Override
	public void guardarSumaCalcu(long idPropietario, double suma) throws ServiceLocatorException, PersistenceException{
		getCandidatoAppService().guardarSumaCalcu(idPropietario, suma);
	}

	@Override
	public InformacionGeneralVO showCandidateDetail(long idCandidato) {
		InformacionGeneralVO vo = null;

		try{
			vo = getCandidatoAppService().buscarInformacionGeneral(idCandidato);
		}catch(ServiceLocatorException e){
			e.printStackTrace();
		}

		return vo;
	}
	
	public PerfilLaboralVo consultaPerfilLaboral(long idCandidato) throws ServiceLocatorException, PersistenceException {
		return getCandidatoAppService().consultaPerfilLaboral(idCandidato);
	}

	@Override
	public List<GradoAcademicoVO> buscarGrados(long idCandidato) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarGrados(idCandidato);
	}

	@Override
	public List<IdiomaVO> buscarIdiomas(long idCandidato) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarIdiomas(idCandidato);
	}

	@Override
	public List<ComputacionAvanzadaVO> buscarCompuAvanzadas(long idCandidato) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarCompuAvanzadas(idCandidato);
	}

	@Override
	public List<HistoriaLaboralVO> buscarHistLaboral(long idCandidato) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarHistLaboral(idCandidato);
	}

	@Override
	public List<ExpectativaLaboralVO> buscarExpecLaboral(long idCandidato) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarExpecLaboral(idCandidato);
	}

	@Override
	public List<ExpectativaLugarVO> buscarExpecLugar(long idCandidato) throws ServiceLocatorException, SQLException {
		return getCandidatoAppService().buscarExpecLugar(idCandidato);
	}
	
	/**
	 * Método que obtiene el contador del candidato
	 * @param idCandidato
	 * @return CandidatoVerDetalleVO
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException 
	 */
	public CandidatoVerDetalleVO obtenerCandidatoVerDetalle(long idCandidato, int anio, int mes) throws ServiceLocatorException, BusinessException, SQLException {
		return getCandidatoAppService().obtenerCandidatoVerDetalle(idCandidato, anio, mes);
	}
	
	/**
	 * Método para actualizar el contador del candidato
	 * @param vo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException
	 */
	public void actualizarCandidatoVerDetalle(CandidatoVerDetalleVO vo) throws BusinessException, ServiceLocatorException, SQLException{
		getCandidatoAppService().actualizarCandidatoVerDetalle(vo);
	}
	
	/**
	 * Método que crea un contador para el candidato
	 * @param vo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException
	 */
	public void crearCandidatoVerDetalle(CandidatoVerDetalleVO vo) throws BusinessException, ServiceLocatorException, SQLException{
		getCandidatoAppService().crearCandidatoVerDetalle(vo);
	}
	
	public void contabilizaDetalleCandidato(long idCandidato){
		try{
			getCandidatoAppService().contabilizaDetalleCandidato(idCandidato);
		}catch(ServiceLocatorException e){
			logger.error(e);
		}
	}
	
	@Override
	public CandidatoVo buscarDatosHeaderTemplateCandidato(long idCandidato) throws SQLException, ServiceLocatorException {
		return getCandidatoAppService().buscarDatosHeaderTemplateCandidato(idCandidato);
	}

	@Override
	public int getEstatusCV(long idCandidato) throws SQLException, ServiceLocatorException {
		return getCandidatoAppService().getEstatusCV(idCandidato);
	}
	
	public int getEstiloCV(long idCandidato) throws ServiceLocatorException{
		return getCandidatoAppService().getEstiloCV(idCandidato);
	}	
	
	public void setEstiloCV(long idCandidato, int estiloCv) throws ServiceLocatorException{
		getCandidatoAppService().setEstiloCV(idCandidato, estiloCv);
	}
	// solo regresa el trabajo actual
	public PerfilJB loadPerfil(long idCandidato) throws BusinessException, ServiceLocatorException {//try full_load
		return loadPerfil(idCandidato,false);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate#
	 * getPerfilVO(long idcandidato)
	 * regresa el trabajo actual o el ultimo
	 */
	public PerfilJB loadPerfil(long idCandidato,boolean ultimo) throws BusinessException, ServiceLocatorException {//try full_load
		PerfilJB perfil = new PerfilJB();
		PerfilBO perfilBO = getCandidatoAppService().loadPerfil(idCandidato, ultimo);
		try {
			if (null != perfilBO) {
				BeanUtils.copyProperties(perfil, perfilBO);
				perfil.setEstiloCV(perfilBO.getEstiloCV());
				perfil.setFechaNacimiento(perfilBO.getFechaNacimiento());
				perfil.setCalle(perfilBO.getCalle());
				perfil.setIdDomicilio(perfilBO.getIdDomicilio());
				perfil.setNumeroExterior(perfilBO.getNumeroExterior());
				perfil.setNumeroInterior(perfilBO.getNumeroInterior());
				perfil.setIdEntidad(perfilBO.getIdEntidad());
				perfil.setIdMunicipio(perfilBO.getIdMunicipio());
				perfil.setIdColonia(perfilBO.getIdColonia());
				perfil.setIdLocalidad(perfilBO.getIdLocalidad());
				perfil.setDomicilioReferencia(perfilBO.getDomicilioReferencia());
				perfil.setIdTipoPropietario((int)perfilBO.getIdTipoPropietario());
				int intIni = Utils.parseInt(String.valueOf(perfilBO.getHoraContactoIni()));
				int intFin = Utils.parseInt(String.valueOf(perfilBO.getHoraContactoFin()));
				perfil.setHorarioLlamar(intIni, intFin);	
				perfil.setExpectativaLaboral(perfilBO.getExpectativaPrincipal());
				perfil.setConocimientoPrincipal(perfilBO.getConocimientoPrincipal());
				perfil.setConocimientos(perfilBO.getConocimientos());
				perfil.setTrabajoActual(perfilBO.getTrabajoActual());
				perfil.setCurp(perfilBO.getCurp());
				perfil.setIdiomaPrincipal(perfilBO.getIdiomaPrincipal());
				perfil.setIdLicencia(perfilBO.getPerfilVO().getIdLicencia());
			}
		} catch (IllegalAccessException iae) { iae.printStackTrace();
		} catch (InvocationTargetException ite) { ite.printStackTrace(); }
		return perfil;
	}

	public PerfilJB loadPerfilTrabajoActualUltimo(long idCandidato,boolean ultimo) throws BusinessException, ServiceLocatorException {//try full_load
		PerfilJB perfil = new PerfilJB();
		PerfilBO perfilBO = getCandidatoAppService().loadPerfilTrabajoActualUltimo(idCandidato, ultimo);
		try {
			if (null != perfilBO) {
				BeanUtils.copyProperties(perfil, perfilBO);

				perfil.setEstiloCV(perfilBO.getEstiloCV());
				perfil.setFechaNacimiento(perfilBO.getFechaNacimiento());
				perfil.setCalle(perfilBO.getCalle());
				perfil.setNumeroExterior(perfilBO.getNumeroExterior());
				perfil.setNumeroInterior(perfilBO.getNumeroInterior());
				int intIni = Utils.parseInt(String.valueOf(perfilBO.getHoraContactoIni()));
				int intFin = Utils.parseInt(String.valueOf(perfilBO.getHoraContactoFin()));
				perfil.setHorarioLlamar(intIni, intFin);		
				
				perfil.setConocimientoPrincipal(perfilBO.getConocimientoPrincipal());
				perfil.setConocimientos(perfilBO.getConocimientos());
				
				perfil.setTrabajoActual(perfilBO.getTrabajoActual());
				/**/
				perfil.setCurp(perfilBO.getCurp());
			}
		} catch (IllegalAccessException iae) { iae.printStackTrace();
		} catch (InvocationTargetException ite) { ite.printStackTrace(); }
		
		return perfil;
	}

	
	public List<CandidatoVo> filtrarCandidatos(String nombre, String apellido1, Date fechaNacimiento, String curp, String correoElectronico, String apellido2, String telefono, long idEntidad, long idMunicipio, String domicilio, String usuario) throws SQLException, ServiceLocatorException{
		return getCandidatoAppService().filtrarCandidatos(nombre, apellido1, fechaNacimiento, curp, correoElectronico, apellido2, telefono, idEntidad, idMunicipio, domicilio, usuario);	
	}

	private ReporteInfonavitServiceRemote getReporteInfonavitService () throws ServiceLocatorException{
		ReporteInfonavitServiceRemote ejb = (ReporteInfonavitServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_REPORTE_INFOVAVIT);
		return ejb;
	}
	
	@Override
	public void iniciaReporteInfonavit() throws ServiceLocatorException {
		getReporteInfonavitService().initTimer();
		
	}

	@Override
	public void detieneReporteInfonavit() throws ServiceLocatorException {
		getReporteInfonavitService().destroyInitTimer();
		
	}	

	public List<EstandarConocerVO> consultaConocer(long idCandidato) throws ConocerWSException, ServiceLocatorException {
		return getCandidatoAppService().consultaConocer(idCandidato);
	}
	
	public ConocerConfigVO registrarConocerConfig(ConocerConfigVO vo) throws ServiceLocatorException {
		return getCandidatoAppService().registraConocerConfig(vo);
	}
	
	public ConocerConfigVO consultaConocerConfigByIdCandidato(long idCandidato) throws ServiceLocatorException {
		return getCandidatoAppService().consultaConocerConfigByIdCandidato(idCandidato);			
	}
	
	@Override
	public long registrarOtroEstudio(CandidatoOtroEstudioVO otroEstudioVO) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppService().registrarOtroEstudio(otroEstudioVO);
	}

	@Override
	public long actualizarOtroEstudio(CandidatoOtroEstudioVO otroEstudioVO) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppService().actualizarOtroEstudio(otroEstudioVO);
	}	
	
	@Override
	public long eliminarOtroEstudio(long idCandidatoEstudio) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppService().eliminarOtroEstudio(idCandidatoEstudio);
	}

	@Override
	public List<CandidatoOtroEstudioVO> otrosEstudiosList(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppService().otrosEstudiosList(idCandidato) ;
	}
	
	@Override
	public long actualizarCandidatoComputacion(long idCandidatoComputacion, long idCandidato, int procesadorTxt, int hojaCalculo, int internet, int redesSociales, String otros) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppService().actualizarCandidatoComputacion(idCandidatoComputacion, idCandidato, procesadorTxt, hojaCalculo, internet, redesSociales, otros);
	}

	@Override
	public ConocimientoComputacionVO findConocimientosComputacion(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppService().findConocimientosComputacion(idCandidato);
	}	
	
	@Override
	public int actualizarConocHab(ConocimientoHabilidadVO conocHabVO) throws ServiceLocatorException, PersistenceException {
		return getCandidatoAppService().actualizarConocHab(conocHabVO);
	}
	
	public int guardarCandidatoHabilidades(long idCandidato, long[] habilidades) throws ServiceLocatorException{
		return getCandidatoAppService().guardarCandidatoHabilidades(idCandidato, habilidades);
	}

	/* CAMBIO EN PROCESO */
	public int consultarEstatus(long idCandidato) throws ServiceLocatorException, PersistenceException {
		return getCandidatoAppService().consultarEstatus(idCandidato);
	}	
	
	public Integer consultarPpcEstatus(long idCandidato) throws ServiceLocatorException, PersistenceException {
		return getCandidatoAppService().consultarPpcEstatus(idCandidato);
	}	
	
	
	public void desactivarCandidato(long idCandidato, long idUsuario) throws ServiceLocatorException {
		getCandidatoAppService().desactivarCandidato(idCandidato, idUsuario);
	}
	
	public void desactivarCandidato(long idCandidato, long idUsuario, int idMotivoDesactivacion, String detalleDesactivacion) throws ServiceLocatorException {
		getCandidatoAppService().desactivarCandidato(idCandidato, idUsuario, idMotivoDesactivacion, detalleDesactivacion);
	}	

	public void reactivarCandidato(long idCandidato, long idUsuario)
			throws ServiceLocatorException {
		getCandidatoAppService().reactivarCandidato(idCandidato, idUsuario);
	}	
	
	public String obtenerLoginUsuario(long idCandidato) throws ServiceLocatorException{
		return getCandidatoAppService().obtenerLoginUsuario(idCandidato);
	}
	
	public List<OfertaCandidatoADesactivarVO> buscarOfertasRelacionadas(long idCandidato)
		throws ServiceLocatorException{
		return getCandidatoAppService().buscarOfertasRelacionadas(idCandidato);
	}

	@Override
	public List<ResultadoBusquedaCandidatosVO> busquedaEspecificaCandidatos(BusquedaCandidatosForm form) throws ServiceLocatorException, BusinessException {
		List<ResultadoBusquedaCandidatosVO> resultado = null;
		try {
			BusquedaCandidatosVO vo = new BusquedaCandidatosVO();
			BeanUtils.copyProperties(vo, form);
			
			resultado = getCandidatoAppService().busquedaEspecificaCandidatos(vo);
			 
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		}catch (SQLException e) {
			logger.error(e);
		}
		return resultado;
	}

	/*
	public RESPUESTA_IMMS_CONSULTA_NSS consultaNssIMMS(String curp, String nss) throws IllegalArgumentException, ServiceLocatorException, BusinessException{
		
		if (curp == null || curp.trim().isEmpty())
			throw new IllegalArgumentException("El parámetro curp es requerido");
		
		if (nss == null || nss.trim().isEmpty())
			throw new IllegalArgumentException("El parámetro nss es requerido");
		
	
		//srojas: simulación de respuesta
		String lastDigit = nss.substring(nss.length()-1); 
		if (lastDigit.equals("0")){
			return RESPUESTA_IMMS_CONSULTA_NSS.SERVICIO_NO_DISPONIBLE;
			
		} else if (lastDigit.equals("1") || lastDigit.equals("2") || lastDigit.equals("3") || lastDigit.equals("4") || lastDigit.equals("5")){
			return RESPUESTA_IMMS_CONSULTA_NSS.NSS_REGISTRADO;
			
		} else {
			return RESPUESTA_IMMS_CONSULTA_NSS.NSS_NO_REGISTRADO; 			
		}
		//srojas	
	}
	*/
	public int actualizaRegistroPPC(long idCandidato, Date ppcFechaInscripcion, int ppcAceptacionTerminos, Integer ppcEstatusIMSS, Date ppcFechaBajaIMSS, String ppcTipoContratoIMSS, String nss) throws IllegalArgumentException, BusinessException, ServiceLocatorException{		
		return getCandidatoAppService().actualizaRegistroPPC(idCandidato, ppcFechaInscripcion, ppcAceptacionTerminos, ppcEstatusIMSS, ppcFechaBajaIMSS, ppcTipoContratoIMSS, nss);		
	}

    @Override
    public CandidatoVo findPpcSdTermsAndConditionsData(final long idUsuario) throws ServiceLocatorException {
        return getCandidatoAppService().findPpcSdTermsAndConditionsData(idUsuario);
    }

	
	public int actualizaRegistroPPCSinValidacion(long idCandidato, Date ppcFechaInscripcion, int ppcAceptacionTerminos, Integer ppcEstatusIMSS, Date ppcFechaBajaIMSS, String ppcTipoContratoIMSS, String nss) throws IllegalArgumentException, BusinessException, ServiceLocatorException{
		return getCandidatoAppService().actualizaRegistroPPCSinValidacion(idCandidato, ppcFechaInscripcion, ppcAceptacionTerminos, ppcEstatusIMSS, ppcFechaBajaIMSS, ppcTipoContratoIMSS, nss);
	}
	
	
	/**
	 * Seguimiento a postulación de candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idCandidato, seguimientoPostulacion
	 * @throws ServiceLocatorException
	 * @return int 
	 * @throws BusinessException 
	 **/
	@Override
	public int registrarSeguimientoPostulacion(long idUsuario, OfertaCandidatoVO oc, String estatus) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().registrarSeguimientoPostulacion(idUsuario, oc, estatus);
	}
	
	/**
	 * Resultado entrevista postulación de candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idOfertaCandidato, resultadoEntrevista
	 * @throws ServiceLocatorException, BusinessException
	 * @return int 
	 **/
	@Override
	public int resultadoEntrevistaPostulacion(long idUsuario, OfertaCandidatoVO oc, String estatus) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().resultadoEntrevistaPostulacion(idUsuario, oc, estatus);
	}
	/**
	 * Actualiza estatus del candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idCandidato,  estatusPPC
	 * @throws ServiceLocatorException, BusinessException
	 * @return int 
	 **/
	@Override
	public int actualizaEstatusPPC(long idCandidato, int estatusPPC, Integer ppcIdMotivoFuera) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppService().actualizaEstatusPPC(idCandidato, estatusPPC, ppcIdMotivoFuera);
	}
	
	/**
	 * Actualiza estatus contratado PPC del candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idCandidato,  estatusPPC
	 * @throws ServiceLocatorException, BusinessException
	 * @return int 
	 **/
	@Override
	public int setContratadoPPC(long idCandidato, int estatusPPC, Integer ppcIdMotivoFuera, String fechaColocacion, String medioColocacion, String nombreEmpresa, String tituloOferta) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppService().setContratadoPPC(idCandidato, estatusPPC, ppcIdMotivoFuera, fechaColocacion, medioColocacion, nombreEmpresa, tituloOferta);
	}
	public RESPUESTA_IMMS_CONSULTA_NSS consultaNssIMMS(String curp, String nss) throws IllegalArgumentException, TechnicalException, ServiceLocatorException{		
		return getCandidatoAppService().consultaNssIMMS(curp, nss);
	}
	
	public void enviaNotificacionInscripcionPPC(long idCandidato) throws IllegalArgumentException, MailException, ServiceLocatorException, TechnicalException{
		getCandidatoAppService().enviaNotificacionInscripcionPPC(idCandidato);
	}

	/**
	 * Obtiene vigencia del candidato
	 * @author Sergio Téllez
	 * @since 16/06/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 * @throws BusinessException 
	 **/
	@Override
	public boolean isEffectiveLimitCandidate(long idCandidato) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().isEffectiveLimitCandidate(idCandidato);
	}

	/**
	 * Actualiza vigencia del candidato
	 * @author Sergio Téllez
	 * @since 16/06/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	@Override
	public int upgradeEffective(long idCandidato) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().upgradeEffective(idCandidato);
	}

	/**
	 * Consulta el tipo de empleo del candidato
	 * @author Sergio Téllez
	 * @since 12/08/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	@Override
	public String getJobType(long idCandidato) throws ServiceLocatorException, BusinessException {
		List<ExpectativaLaboralVO> expectativaLaboralList;
		try {
			expectativaLaboralList = getCandidatoAppService().buscarExpecLaboral(idCandidato);
			if (!expectativaLaboralList.isEmpty()) {
				ExpectativaLaboralVO el = expectativaLaboralList.get(0);
				if (null != el && null != el.getTipoEmpleoDeseado()) return el.getTipoEmpleoDeseado();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Actualiza los datos complementarios del candidato
	 * @author Sergio Téllez
	 * @since 15/10/2015
	 * @param modalidadCandidato
	 * @param modalidadCandidatoPTAT
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	@Override
	public int updateCandidateComplementData(ModalidadCandidatoVO mc, FormatoPTATVO ptat, List<FormatoPTATMaquinariaVO> engines, List<FormatoPTATCultivosVO> products, List<BeneficiarioVO> contacts, List<BeneficiarioVO> beneficiaries, List<IdiomaVO> langs, List<FormatoPTATHclinicaVO> histories) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().updateCandidateComplementData(mc, ptat, contacts, beneficiaries, langs, engines, products, histories);
	}

	/**
	 * Verifica si el candidato tiene un registro previo en el programa solicitado
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @param idModalidad
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	@Override
	public ModalidadCandidatoVO getModalidadCandidato(long idCandidato, long idModalidad, long idSubprograma) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().getModalidadCandidato(idCandidato, idModalidad, idSubprograma);
	}
	
	@Override
	public FormatoPTATVO getFormatoPTATVO(long idCandidato) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().getFormatoPTATVO(idCandidato);
	}

	@Override
	public List<FormatoPTATCultivosVO> getFormatoPTATCultivos(long idCandidato) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().getFormatoPTATCultivos(idCandidato);
	}

	@Override
	public List<ModalidadPtatBeneficiarioVO> getModalidadPtatBeneficiarioList(long idModalidadCandidatoPTAT) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().getModalidadPtatBeneficiarioList(idModalidadCandidatoPTAT);
	}
	
	@Override
	public boolean consultarPermisoGeolocalizacionRegistro()throws BusinessException, ServiceLocatorException{
		return getCandidatoAppService().consultarPermisoGeolocalizacionRegistro();
	}

	@Override
	public boolean consultarPermisoGeolocalizacion(long idCandidato) throws BusinessException,
			ServiceLocatorException {
		return getCandidatoAppService().consultarPermisoGeolocalizacion(idCandidato);
	}

	@Override
	public void actualizaEstatusGeoreferencia(long idCandidato, boolean estatus) throws BusinessException, ServiceLocatorException {
		getCandidatoAppService().actualizaEstatusGeoreferencia(idCandidato, estatus);
	}
	
	@Override
	public FormatoSNE01VO findSNEByCandidate(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppService().findSNEByCandidate(idCandidato);
	}
	
	@Override
	public FormatoLPAVO findLPAByCandidate(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppService().findLPAByCandidate(idCandidato);
	}
	
	@Override
	public List<BeneficiarioVO> getBeneficiarioList(long idCandidato, long idTipoFormato, boolean isContact) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().getBeneficiarioList(idCandidato, idTipoFormato, isContact);
 	}
	
	@Override
	public int updatePerfilSNE(PerfilVO perfil, ExpectativaLaboralVO el, HistoriaLaboralVO lastJob, ModalidadCandidatoVO mc, List<BeneficiarioVO> beneficiaries, FormatoSNE01VO sne) throws BusinessException, ServiceLocatorException {
		int result = 0;
		try {
			result = getCandidatoAppService().updatePerfilComp(perfil, el, lastJob, sne, mc, beneficiaries);
		} catch (Exception e) {
			e.printStackTrace(); result = -1;
		}
		return result;
	}
	
	@Override
	public int createBeneficiario(BeneficiarioVO beneficiario) throws ServiceLocatorException, BusinessException {
		return  getCandidatoAppService().createBeneficiario(beneficiario);
	}
	
	@Override
	public int removeBeneficiario(long idBeneficiario) throws ServiceLocatorException, BusinessException {
		return  getCandidatoAppService().removeBeneficiario(idBeneficiario);
	}

	@Override
	public int saveLangList(long idCandidato, List<IdiomaVO> langList) throws ServiceLocatorException, BusinessException {
		int result = 0;
		for (IdiomaVO idiomaVO : langList) {
			try {
				long iresult = getCandidatoAppService().persistLang(idiomaVO);
				if (iresult > 0) result++;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		return result;
	}
	
	@Override
	public int updateLPA(long idCandidato, FormatoLPAVO mml, ReferenciaLaboralVO referencia, GradoAcademicoVO gradoAcademicoVO)  throws ServiceLocatorException, BusinessException {
		return  getCandidatoAppService().updateLPA(idCandidato, mml, referencia, gradoAcademicoVO);
	}

	@Override
	public List<ReferenciaLaboralVO> getReferenciaLaboraList(long idCandidato) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().getReferenciaLaboraList(idCandidato);
	}

	@Override
	public int removeReferenciaLaboral(ReferenciaLaboralVO referencia) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().removeReferenciaLaboral(referencia);
	}

	@Override
	public List<FormatoPTATHclinicaVO> getDiseases(long idCandidato) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().getDiseases(idCandidato);
	}

	@Override
	public List<FormatoPTATMaquinariaVO> getFormatoPTATMaquinaria(long idCandidato) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().getFormatoPTATMaquinaria(idCandidato);
	}

	@Override
	public long update(mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO conocHabVO) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().update(conocHabVO);
	}

	@Override
	public List<CanalizacionCandidatoVO> getCanalizacionCandidatoList(long idCandidato) throws ServiceLocatorException, BusinessException {
		return getCandidatoAppService().getCanalizacionCandidatoList(idCandidato);
	}
	
	//Notifiacion Recuperacion Empresa Contraseña OAM
		public void notificacionRecuperacionContrasena(long idPropietario, String usuario, TIPO_PROPIETARIO tipoPropietario,
				String nombrePropietario, String correoElectronico, String url) throws MailException, ServiceLocatorException, BusinessException{
			getCandidatoAppService().notificacionRecuperacionContrasena(idPropietario, usuario, tipoPropietario, nombrePropietario, correoElectronico, url);
		}
}