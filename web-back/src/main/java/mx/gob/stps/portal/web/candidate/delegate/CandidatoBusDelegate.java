package mx.gob.stps.portal.web.candidate.delegate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

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
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.service.TelefonoAppServiceRemote;
import mx.gob.stps.portal.core.infra.utils.Constantes.RESPUESTA_IMMS_CONSULTA_NSS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.vo.CandidatoVerDetalleVO;
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
import mx.gob.stps.portal.web.offer.wrapper.PerfilJB;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import mx.gob.stps.portal.ws.conocer.exception.ConocerWSException;

public interface CandidatoBusDelegate {

	public TelefonoAppServiceRemote getTelefonoAppService() throws ServiceLocatorException;

	/**
	 * Realiza la carga inicial de datos del perfil de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 28/02/2011
	 * @param PerfilForm
	 * @throws BusinessException
	 *             , ServiceLocatorException
	 * @return void
	 **/
	public PerfilVO initPerfil(long idCandidato)
			throws ServiceLocatorException, SQLException;
	
	/**
	 * Realiza la carga inicial de otros medios de busqueda del perfil de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 30/02/2011
	 * @param idCandidato
	 * @throws ServiceLocatorException, SQLException
	 * @return void
	 **/
	public List<OtroMedioVO> initOtrosMedios(long idCandidato)
			throws ServiceLocatorException, SQLException;

	/**
	 * Realiza la carga inicial de los telefonos de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 29/03/2011
	 * @param idCandidato
	 * @param idTipoPropietario
	 * @throws BusinessException
	 * @return List<TelefonoVO>
	 **/
	public List<TelefonoVO> initTelefonos(long idCandidato,
			long idTipoPropietario) throws BusinessException,
			ServiceLocatorException;

	/**
	 * Realiza la carga inicial de datos del perfil de un candidato, para las
	 * secciones Escolaridad, Experiencia y Expectativas
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 29/02/2011
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return void
	 **/
	public PerfilVO initPerfilCandidato(long idCandidato)
			throws ServiceLocatorException, SQLException;

	/**
	 * Actualiza o registra los Datos personales del perfil de un candidato.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 28/02/2011
	 * @param perfil
	 * @throws BusinessException, ServiceLocatorException
	 * @return PerfilVO
	 * @throws SQLException 
	 * @throws TechnicalException 
	 * @throws PersistenceException 
	 **/
	public PerfilVO registrarPerfil(PerfilVO perfil) throws BusinessException,
			ServiceLocatorException, PersistenceException, TechnicalException, SQLException,
			MailException, IndexerException;

	/**
	 * Actualiza o registra la escolaridad del perfil de un candidato.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/03/2011
	 * @param escolaridad
	 * @throws BusinessException, ServiceLocatorException
	 * @return EscolaridadVO
	 * @throws SQLException 
	 * @throws TechnicalException 
	 * @throws PersistenceException 
	 **/
	public EscolaridadVO registrarEscolaridad(EscolaridadVO escolaridad)
			throws BusinessException, ServiceLocatorException, PersistenceException, TechnicalException, SQLException, IndexerException;

	/**
	 * Realiza la carga inicial de grados academicos de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 19/03/2011
	 * @param long
	 * @throws ServiceLocatorException
	 * @return void
	 **/
	public List<GradoAcademicoVO> initGrados(long idCandidato, int principal)
			throws ServiceLocatorException, SQLException;
	
	/**
	 * Realiza la carga inicial de grados academicos de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return List<GradoAcademicoVO>
	 **/
	public List<GradoAcademicoVO> buscarGrados(long idCandidato)
			throws ServiceLocatorException, SQLException;
	
	/**
	 * Agrega un grado academico de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 22/03/2011
	 * @param long
	 * @throws ServiceLocatorException
	 * @return void
	 **/
	public void agregarGrado(long idCandidato, GradoAcademicoVO gradoVO)
			throws ServiceLocatorException, PersistenceException;

	/**
	 * Borra un grado academico de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 22/03/2011
	 * @param long
	 * @throws ServiceLocatorException
	 * @return void
	 **/
	public void borrarGrado(long idCandidatoGradoAcademico)
			throws ServiceLocatorException, PersistenceException;

	/**
	 * Realiza la carga inicial de idiomas de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param idCandidato
	 * @param principal
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return void
	 **/
	public List<IdiomaVO> initIdiomas(long idCandidato, int principal)
			throws ServiceLocatorException, SQLException;
	
	/**
	 * Realiza la carga inicial de idiomas de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return List<IdiomaVO>
	 **/
	public List<IdiomaVO> buscarIdiomas(long idCandidato)
			throws ServiceLocatorException, SQLException;

	/**
	 * Agrega un idioma a un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @param IdiomaVO
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void agregarIdioma(long idCandidato, IdiomaVO idiomaVO)
			throws ServiceLocatorException, PersistenceException;

	/**
	 * Borra un idioma de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void borrarIdioma(long idCandidatoIdioma)
			throws ServiceLocatorException, PersistenceException;

	/**
	 * Realiza la carga inicial de conocimientos o habilidades de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param ConocimHabilidadForm
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return void
	 **/
	public List<ConocimientoHabilidadVO> initConocsHabs(long idCandidato,
			long idTipoConocimHabilidad, int principal)
			throws ServiceLocatorException, SQLException;
	
	/**
	 * Realiza la carga inicial de conocimientos o habilidades de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @param idTipoConocimHabilidad
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return List<ConocimientoHabilidadVO>
	 **/
	public List<ConocimientoHabilidadVO> buscarConocsHabs(long idCandidato,
			long idTipoConocimHabilidad)
			throws ServiceLocatorException, SQLException;


	/**
	 * Agrega un conocimiento o habilidad a un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @param IdiomaVO
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void agregarConocHab(long idCandidato,
			ConocimientoHabilidadVO conocHabVO) throws ServiceLocatorException,
			PersistenceException;

	/**
	 * Actualiza un conocimiento o habilidad a un candidato
	 * 
	 * @author Sergio Téllez Vázquez
	 * @since 26/06/2012
	 * @param ConocimientoHabilidadVO
	 * @throws ServiceLocatorException, PersistenceException
	 * @return void
	 **/
	public int actualizarConocHab(ConocimientoHabilidadVO conocHabVO) throws ServiceLocatorException, PersistenceException;
	
	/**
	 * Persiste un conocimiento o habilidad a un candidato
	 * 
	 * @author Sergio Téllez Vázquez
	 * @since 07/03/2016
	 * @param ConocimientoHabilidadVO
	 * @throws ServiceLocatorException, PersistenceException
	 * @return void
	 **/
	public long update(mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO conocHabVO) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Borra un conocimiento o habilidad de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void borrarConocHab(long idCandidatoConocimHabilidad)
			throws ServiceLocatorException, PersistenceException;

	/**
	 * Realiza la carga inicial de conocimientos o habilidades de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 24/03/2011
	 * @param ComputacionAvanzadaForm
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return void
	 **/
	public List<ComputacionAvanzadaVO> initCompuAvanzadas(long idCandidato,
			int principal) throws ServiceLocatorException, SQLException;
	
	/**
	 * Realiza la carga inicial de conocimientos o habilidades de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return List<ComputacionAvanzadaVO>
	 **/
	public List<ComputacionAvanzadaVO> buscarCompuAvanzadas(long idCandidato) 
	throws ServiceLocatorException, SQLException;

	/**
	 * Agrega un conocimiento o habilidad a un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 24/03/2011
	 * @param long
	 * @param ComputacionAvanzadaVO
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void agregarCompuAvanzada(long idCandidato,
			ComputacionAvanzadaVO compAvanVO) throws ServiceLocatorException,
			PersistenceException;

	/**
	 * Borra un conocimiento o habilidad de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 24/03/2011
	 * @param long
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void borrarCompuAvanzada(long idCandidatoCompuAvanzada)
			throws ServiceLocatorException, PersistenceException;

	/**
	 * Actualiza la o registra experiencia laboral del perfil de un candidato.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 10/03/2011
	 * @param experiencia
	 * @throws BusinessException, ServiceLocatorException
	 * @return ExperienciaVO
	 * @throws SQLException 
	 * @throws TechnicalException 
	 * @throws PersistenceException 
	 **/
	public ExperienciaVO registrarExperiencia(ExperienciaVO experiencia)
			throws BusinessException, ServiceLocatorException, PersistenceException, TechnicalException, SQLException, IndexerException;

	/**
	 * Realiza la carga inicial del Historial laboral de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 25/03/2011
	 * @param idCandidato
	 * @param principal
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return List<HistoriaLaboralVO>
	 **/
	public List<HistoriaLaboralVO> initHistLaboral(long idCandidato,
			int principal) throws ServiceLocatorException, SQLException;
	
	/**
	 * Realiza la carga inicial del Historial laboral de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return List<HistoriaLaboralVO>
	 **/
	public List<HistoriaLaboralVO> buscarHistLaboral(long idCandidato) 
	throws ServiceLocatorException, SQLException;

	/**
	 * Agrega un conocimiento o habilidad a un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 24/03/2011
	 * @param long
	 * @param HistoriaLaboralVO
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void agregarHistLaboral(long idCandidato,
			HistoriaLaboralVO histLaboralVO) throws ServiceLocatorException,
			PersistenceException;

	/**
	 * Borra un conocimiento o habilidad de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 24/03/2011
	 * @param long
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void borrarHistLaboral(long idHistorialLaboral)
			throws ServiceLocatorException, PersistenceException;

	/**
	 * Actualiza o registra la expectativa laboral del perfil de un candidato.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 10/03/2011
	 * @param expectativa
	 * @throws BusinessException, ServiceLocatorException
	 * @return ExpectativaVO
	 * @throws SQLException 
	 * @throws TechnicalException 
	 * @throws PersistenceException 
	 **/
	public ExpectativaVO registrarExpectativa(ExpectativaVO expectativa)
			throws BusinessException, ServiceLocatorException, PersistenceException, TechnicalException, SQLException, IndexerException;

	/**
	 * Realiza la carga inicial de Expectativas laborales de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param HistoriaLaboralForm
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return void
	 **/
	public List<ExpectativaLaboralVO> initExpecLaboral(long idCandidato,
			int principal) throws ServiceLocatorException, SQLException;
	
	/**
	 * Realiza la carga inicial de Expectativas laborales de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return List<ExpectativaLaboralVO>
	 **/
	public List<ExpectativaLaboralVO> buscarExpecLaboral(long idCandidato) 
	throws ServiceLocatorException, SQLException;

	/**
	 * Agrega una expectativa laboral a un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @param ExpectativaLaboralVO
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void agregarExpecLaboral(long idCandidato,
			ExpectativaLaboralVO expecLaboralVO)
			throws ServiceLocatorException, PersistenceException;

	/**
	 * Borra una expectativa laboral de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void borrarExpecLaboral(long idExpectativaLaboral)
			throws ServiceLocatorException, PersistenceException;

	/**
	 * Realiza la carga inicial de Expectativas de lugares de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param idCandidato
	 * @param principal
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return void
	 **/
	public List<ExpectativaLugarVO> initExpecLugar(long idCandidato,
			int principal) throws ServiceLocatorException, SQLException;
	
	/**
	 * Realiza la carga inicial de Expectativas de lugares de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 *             , SQLException
	 * @return List<ExpectativaLugarVO>
	 **/
	public List<ExpectativaLugarVO> buscarExpecLugar(long idCandidato) 
	throws ServiceLocatorException, SQLException;

	/**
	 * Agrega una expectativa de lugar a un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @param ExpectativaLugarVO
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void agregarExpecLugar(long idCandidato,
			ExpectativaLugarVO expecLugarVO) throws ServiceLocatorException,
			PersistenceException;

	/**
	 * Borra una expectativa de lugar de un candidato
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @throws ServiceLocatorException
	 *             , PersistenceException
	 * @return void
	 **/
	public void borrarExpecLugar(long idExpectativaLugar)
			throws ServiceLocatorException, PersistenceException;

	/**
	 * Actualiza o registra los datos del video curriculum de un candidato
	 * 
	 * @param idCandidato
	 *            el identificador del candidato cuya informaci&oacute;n se
	 *            actualiza
	 * @param vo
	 *            la informaci&oacute;n del curriculum a actualizar
	 * @throws BusinessException
	 *             si no se cumple con alguna regla de negocio
	 * @throws ServiceLocatorException
	 *             si no se encuentra al objeto remoto
	 * @author jose.jimenez
	 */
	public void actualizarCurriculum(UsuarioWebVO usuario, CurriculumVo vo)
			throws BusinessException, ServiceLocatorException;

	/**
	 * Almacena la fotograf&iacute;a de un candidato
	 * 
	 * @param idCandidato
	 *            el identificador del candidato cuya foto se almacena
	 * @param vo
	 *            la imagen a almacenar
	 * @throws BusinessException
	 *             si no se cumple con alguna regla de negocio
	 * @throws ServiceLocatorException
	 *             si no se encuentra al objeto remoto
	 * @author jose.jimenez
	 */
	public void guardarFoto(UsuarioWebVO usuario, byte[] fotografia)
			throws BusinessException, ServiceLocatorException;
	
	/**
	 * Guarda la suma de los gastos de un candidato
	 * @param idPropietario es el id del propietario al que se le asociara la suma
	 * @param suma monto de los gastos mensuales
	 * @throws ServiceLocatorException si no se encuentra al objeto remoto
	 * @throws PersistenceException si hay algun error al acceder a la BD
	 */
	public void guardarSumaCalcu(long idPropietario, double suma) throws ServiceLocatorException, PersistenceException;

	/**
	 * Realiza el llamado al proceso para obtener el detalle del candidato
	 * relacionado al identificador recibido.
	 * @param idCandidato identificador del candidato del que se desea mostrar
	 *        el detalle
	 */
	public InformacionGeneralVO showCandidateDetail(long idCandidato);

	public PerfilLaboralVo consultaPerfilLaboral(long idCandidato)
			throws ServiceLocatorException, PersistenceException;
	
	/**
	 * Realiza la carga de datos del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 27/03/2011
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return void
	 **/
	public PerfilJB loadPerfil(long idCandidato) throws BusinessException, ServiceLocatorException;

	public PerfilJB loadPerfil(long idCandidato, boolean ultimo) throws BusinessException, ServiceLocatorException;
	
	public PerfilJB loadPerfilTrabajoActualUltimo(long idCandidato, boolean ultimo) throws BusinessException, ServiceLocatorException; 
	
	/**
	 * Guarda otros estudios del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param OtrosEstudiosVO
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public long registrarOtroEstudio(CandidatoOtroEstudioVO otroEstudioVO) throws BusinessException, ServiceLocatorException;
	
	/**
	 * Actualiza otros estudios del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param OtrosEstudiosVO
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public long actualizarOtroEstudio(CandidatoOtroEstudioVO otroEstudioVO) throws BusinessException, ServiceLocatorException;	
	
	/**
	 * elimina otros estudios del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param OtrosEstudiosVO
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public long eliminarOtroEstudio(long idCandidatoEstudio) throws BusinessException, ServiceLocatorException;
	
	/**
	 * Obtiene otros estudios del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public List<CandidatoOtroEstudioVO> otrosEstudiosList(long idCandidato) throws BusinessException, ServiceLocatorException;	
	
	/**
	 * Guarda conocimientos computación del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param long idCandidatoComputacion
	 * @param long idCandidatoComputacion
	 * @param int procesadorTxt
	 * @param int hojaCalculo
	 * @param int internet
	 * @param int internet
	 * @param String otros
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public long actualizarCandidatoComputacion(long idCandidatoComputacion, long idCandidato, int procesadorTxt, int hojaCalculo, int internet, int redesSociales, String otros) throws BusinessException, ServiceLocatorException;
	
	/**
	 * Obtiene otros estudios del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public ConocimientoComputacionVO findConocimientosComputacion(long idCandidato) throws BusinessException, ServiceLocatorException;	
	
	/**
	 * Método que obtiene el contador del candidato
	 * @param idCandidato
	 * @return CandidatoVerDetalleVO
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException 
	 */
	public CandidatoVerDetalleVO obtenerCandidatoVerDetalle(long idCandidato, int anio, int mes) throws BusinessException, ServiceLocatorException, SQLException;
	
	/**
	 * Método para actualizar el contador del candidato
	 * @param vo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException
	 */
	public void actualizarCandidatoVerDetalle(CandidatoVerDetalleVO vo) throws BusinessException, ServiceLocatorException, SQLException;
	
	/**
	 * Método que crea un contador para el candidato
	 * @param vo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException
	 */
	public void crearCandidatoVerDetalle(CandidatoVerDetalleVO vo) throws BusinessException, ServiceLocatorException, SQLException;
	
	/**
	 * Usa el id del usuario registrado para obtener los datos del header del espacio de
	 * candidatos
	 * @param idCandidato
	 * @return
	 * @throws SQLException
	 * @throws ServiceLocatorException 
	 */
	public CandidatoVo buscarDatosHeaderTemplateCandidato(long idCandidato)
			throws SQLException, ServiceLocatorException;
	
	/**
	 * Pbtiene el estatus del CV del candidato
	 * @param idCandidato
	 * @return
	 * @throws SQLException
	 * @throws ServiceLocatorException
	 */
	public int getEstatusCV(long idCandidato)
			throws SQLException, ServiceLocatorException;
	
	public int getEstiloCV(long idCandidato)
		throws ServiceLocatorException;
	
	public void setEstiloCV(long idCandidato, int estiloCv) 
		throws ServiceLocatorException;

	/**
	 * Contabiliza la visualizacion del detalle del candidato
	 * @param idCandidato
	 */
	public void contabilizaDetalleCandidato(long idCandidato);
	
	public List<CandidatoVo> filtrarCandidatos(String nombre, String apellido1, Date fechaNacimiento, String curp, String correoElectronico , String apellido2, String telefono, long idEntidad, long idMunicipio, String domicilio, String usuario) throws SQLException, ServiceLocatorException;
	
	/**
	 * Inicializa el timer para envio de reporte de candidatos
	 * a INFONAVIT
	 * @throws ServiceLocatorException
	 */
	public void iniciaReporteInfonavit() throws ServiceLocatorException;

	/**
	 * Detiene el timer para envio de reporte de candidatos
	 * a INFONAVIT
	 * 
	 * @throws ServiceLocatorException
	 */
	public void detieneReporteInfonavit() throws ServiceLocatorException;
	
	public List<EstandarConocerVO> consultaConocer(long idCandidato) throws ConocerWSException, ServiceLocatorException;

	public ConocerConfigVO registrarConocerConfig(ConocerConfigVO vo) throws ServiceLocatorException;
	
	public ConocerConfigVO consultaConocerConfigByIdCandidato(long idCandidato) throws ServiceLocatorException;	
	
	/* CAMBIO EN PROCESO */
	public int consultarEstatus(long idCandidato) throws ServiceLocatorException;		
	public Integer consultarPpcEstatus(long idCandidato) throws ServiceLocatorException, PersistenceException;
	public void desactivarCandidato(long idCandidato, long idUsuario) throws ServiceLocatorException;
	public void desactivarCandidato(long idCandidato, long idUsuario, int idMotivoDesactivacion, String detalleDesactivacion) throws ServiceLocatorException;
	public void reactivarCandidato(long idCandidato, long idUsuario) throws ServiceLocatorException;	
	public String obtenerLoginUsuario(long idCandidato) throws ServiceLocatorException;
	public List<OfertaCandidatoADesactivarVO> buscarOfertasRelacionadas(long idCandidato) throws ServiceLocatorException;	
	public List<Long> consultaHabilidades(long idCandidato) throws ServiceLocatorException;
	/* BUSQUEDA ESPECIFICA DE CANDIDATOS */
	public List<ResultadoBusquedaCandidatosVO> busquedaEspecificaCandidatos(BusquedaCandidatosForm form) throws ServiceLocatorException, BusinessException;	
	public void iniciarDepuracionDeCandidatosPorVigencia() throws ServiceLocatorException;
	public void detenerDepuracionDeCandidatosPorVigencia() throws ServiceLocatorException;
	
	public int actualizaRegistroPPCSinValidacion(long idCandidato, Date ppcFechaInscripcion, int ppcAceptacionTerminos, Integer ppcEstatusIMSS, Date ppcFechaBajaIMSS, String ppcTipoContratoIMSS, String nss) throws IllegalArgumentException, BusinessException, ServiceLocatorException;
	
	public int actualizaRegistroPPC(long idCandidato, Date ppcFechaInscripcion, int ppcAceptacionTerminos, Integer ppcEstatusIMSS, Date ppcFechaBajaIMSS, String ppcTipoContratoIMSS, String nss) throws IllegalArgumentException, BusinessException, ServiceLocatorException;
	
	public CandidatoVo findPpcSdTermsAndConditionsData(final long idUsuario) throws ServiceLocatorException;
	
	
	/**
	 * Seguimiento a postulación de candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idOfertaCandidato, seguimientoPostulacion
	 * @throws ServiceLocatorException, BusinessException
	 * @return int 
	 **/
	public int registrarSeguimientoPostulacion(long idUsuario, OfertaCandidatoVO oc, String estatus) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Resultado entrevista postulación de candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idOfertaCandidato, resultadoEntrevista
	 * @throws ServiceLocatorException, BusinessException
	 * @return int 
	 **/
	public int resultadoEntrevistaPostulacion(long idUsuario, OfertaCandidatoVO oc, String estatus) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Actualiza estatus del candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idCandidato,  estatusPPC
	 * @throws ServiceLocatorException, BusinessException
	 * @return int 
	 **/
	public int actualizaEstatusPPC(long idCandidato, int estatusPPC, Integer ppcIdMotivoFuera) throws BusinessException, ServiceLocatorException;

	/**
	 * Actualiza estatus contratado PPC del candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idCandidato,  estatusPPC
	 * @throws ServiceLocatorException, BusinessException
	 * @return int 
	 **/
	public int setContratadoPPC(long idCandidato, int estatusPPC, Integer ppcIdMotivoFuera, String fechaColocacion, String medioColocacion, String nombreEmpresa, String tituloOferta) throws BusinessException, ServiceLocatorException;
	
	public RESPUESTA_IMMS_CONSULTA_NSS consultaNssIMMS(String curp, String nss) throws IllegalArgumentException, TechnicalException, ServiceLocatorException;
	
	public void enviaNotificacionInscripcionPPC(long idCandidato) throws IllegalArgumentException, MailException, ServiceLocatorException, TechnicalException;
	
	/**
	 * Obtiene vigencia del candidato
	 * @author Sergio Téllez
	 * @since 16/06/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public boolean isEffectiveLimitCandidate(long idCandidato) throws ServiceLocatorException, BusinessException;	
	
	/**
	 * Actualiza vigencia del candidato
	 * @author Sergio Téllez
	 * @since 16/06/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public int upgradeEffective(long idCandidato) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Consulta el tipo de empleo del candidato
	 * @author Sergio Téllez
	 * @since 12/08/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public String getJobType(long idCandidato) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Actualiza los datos complementarios del candidato
	 * @author Sergio Téllez
	 * @since 15/10/2015
	 * @param modalidadCandidato
	 * @param modalidadCandidatoPTAT
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public int updateCandidateComplementData(ModalidadCandidatoVO mc, FormatoPTATVO ptat, List<FormatoPTATMaquinariaVO> engines, List<FormatoPTATCultivosVO> products, List<BeneficiarioVO> contacts, List<BeneficiarioVO> beneficiaries, List<IdiomaVO> langs, List<FormatoPTATHclinicaVO> histories) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Verifica si el candidato tiene un registro previo en el programa solicitado
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @param idModalidad
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public ModalidadCandidatoVO getModalidadCandidato(long idCandidato, long idModalidad, long idSubprograma) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Obtiene datos complementarios PTAT
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public FormatoPTATVO getFormatoPTATVO(long idCandidato) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Obtiene los productos de datos complementarios PTAT
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public List<FormatoPTATCultivosVO> getFormatoPTATCultivos(long idCandidato) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Obtiene los beneficiarios de datos complementarios PTAT
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public List<ModalidadPtatBeneficiarioVO> getModalidadPtatBeneficiarioList(long idModalidadCandidatoPTAT) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Consulta si esta habilidato el requerimiento de geolocalizacion
	 * @return true en caso de estar habilitado, false en caso contrario
	 * @throws BusinessException
	 * @throws ServiceLocatorException 
	 */
	public boolean consultarPermisoGeolocalizacionRegistro()throws BusinessException, ServiceLocatorException;
	
	/**
	 * Consulta si esta habilidato el requerimiento de geolocalizacion
	 * @param idCandidato
	 * @return true en caso de estar habilitado, false en caso contrario
	 * @throws BusinessException
	 * @throws ServiceLocatorException 
	 */
	public boolean consultarPermisoGeolocalizacion(long idCandidato)throws BusinessException, ServiceLocatorException;

	public void actualizaEstatusGeoreferencia(long idCandidato, boolean estatus) throws BusinessException, ServiceLocatorException;
	
	/**
	 * obtiene SNE01 del perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return FormatoSNE01VO
	 **/
	public FormatoSNE01VO findSNEByCandidate(long idCandidato) throws BusinessException, ServiceLocatorException;
	
	/**
	 * obtiene LPA del perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return FormatoSNE01VO
	 **/
	public FormatoLPAVO findLPAByCandidate(long idCandidato) throws BusinessException, ServiceLocatorException;
	
	/**
	 * obtiene beneficiarios SNE01 asociados al perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return List<BeneficiarioVO>
	 **/
	public List<BeneficiarioVO> getBeneficiarioList(long idCandidato, long idTipoFormato, boolean isContact) throws ServiceLocatorException, BusinessException;
	
	/**
	 * obtiene referencias laborales asociados al perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return List<ReferenciaLaboralVO>
	 **/
	public List<ReferenciaLaboralVO> getReferenciaLaboraList(long idCandidato) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Actualiza Datos personales y complementarios del perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param PerfilVO
	 * @throws BusinessException, ServiceLocatorException
	 * @return int
	 **/
	public int updatePerfilSNE(PerfilVO perfil, ExpectativaLaboralVO el, HistoriaLaboralVO lastJob, ModalidadCandidatoVO mc, List<BeneficiarioVO> beneficiaries, FormatoSNE01VO sne) throws BusinessException, ServiceLocatorException;

	/**
	 * elimina beneficiario SNE01 asociado al perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param idBeneficiario
	 * @throws BusinessException, ServiceLocatorException
	 * @return int
	 **/
	public int removeBeneficiario(long idBeneficiario) throws ServiceLocatorException, BusinessException;

	/**
	 * crea beneficiario SNE01 asociado al perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param beneficiario
	 * @throws ServiceLocatorException, BusinessException
	 * @return int
	 **/
	public int createBeneficiario(BeneficiarioVO beneficiario) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Actualiza idiomas de un candidato
	 * 
	 * @author Sergio Téllez
	 * @since 13/01/2016
	 * @param List<IdiomaVO>
	 * @param IdiomaVO
	 * @throws ServiceLocatorException, PersistenceException
	 * @return int
	 **/
	public int saveLangList(long idCandidato, List<IdiomaVO> langList) throws ServiceLocatorException, BusinessException;

	/**
	 * Persiste el formato LPA asociado al candidato
	 * @author Sergio Téllez
	 * @since 14/01/2016
	 * @param FormatoLPAVO
	 * @return 1 en caso de actualización, -1 en caso contrario
	 * @throws BusinessException
	 */
	public int updateLPA(long idCandidato, FormatoLPAVO mml, ReferenciaLaboralVO referencia, GradoAcademicoVO gradoAcademicoVO) throws ServiceLocatorException, BusinessException;

	/**
	 * elimina referencia laboral asociado al perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param referencia
	 * @throws BusinessException, ServiceLocatorException
	 * @return int
	 **/
	public int removeReferenciaLaboral(ReferenciaLaboralVO referencia) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Obtiene la hisoria clínica asociada a los datos complementarios del candidato
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return List<FormatoPTATHclinicaVO> 
	 **/
	public List<FormatoPTATHclinicaVO> getDiseases(long idCandidato) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Obtiene el tipo de maquinaria asociada a los datos complementarios del candidato
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return List<FormatoPTATMaquinariaVO> 
	 **/
	public List<FormatoPTATMaquinariaVO> getFormatoPTATMaquinaria(long idCandidato) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Obtiene la canalización asociada a los datos complementarios del candidato
	 * @author Sergio Téllez
	 * @since 25/05/2016
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return List<CanalizacionCandidatoVO> 
	 **/
	public List<CanalizacionCandidatoVO> getCanalizacionCandidatoList(long idCandidato) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Obtiene Solicitante con los datos complementarios del candidato
	 * @author Octavio Alvarez
	 * @since 19/09/2016
	 * @param idCandidato
	 * @return Solicitante 
	 **/
	//Obtener Solicitante OAM
	public Solicitante obtenerSolicitante(long idCandidato) throws BusinessException, ServiceLocatorException;
	
	//Notifiacion Recuperacion Empresa Contraseña OAM
	public void notificacionRecuperacionContrasena(long idPropietario, String usuario, TIPO_PROPIETARIO tipoPropietario,
				String nombrePropietario, String correoElectronico, String url) throws MailException, ServiceLocatorException, BusinessException;
}