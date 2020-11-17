/**
 * 
 */
package mx.gob.stps.portal.core.candidate.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

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
import mx.gob.stps.portal.core.candidate.vo.OfertaCandidatoResumenVo;
import mx.gob.stps.portal.core.candidate.vo.OtroMedioVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilLaboralVo;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.candidate.vo.ResultadoBusquedaCandidatosVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.vo.DestinatarioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.RESPUESTA_IMMS_CONSULTA_NSS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.vo.CandidatoVerDetalleVO;
import mx.gob.stps.portal.core.oferta.detalle.bo.PerfilBO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoADesactivarVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.persistencia.vo.CanalizacionCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.FormatoLPAVO;
import mx.gob.stps.portal.persistencia.entity.Solicitante;
import mx.gob.stps.portal.persistencia.vo.BeneficiarioVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATCultivosVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATHclinicaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATMaquinariaVO;
import mx.gob.stps.portal.persistencia.vo.FormatoPTATVO;
import mx.gob.stps.portal.persistencia.vo.FormatoSNE01VO;
import mx.gob.stps.portal.persistencia.vo.EstandarConocerVO;
import mx.gob.stps.portal.persistencia.vo.ReferenciaLaboralVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadPtatBeneficiarioVO;
import mx.gob.stps.portal.ws.conocer.exception.ConocerWSException;

/**
 * @author Felipe Juárez Ramírez
 * @since 1 de Marzo de 2011
 * 
 */
@Remote
public interface CandidatoAppServiceRemote {

	/**
	 * Realiza la invocacion remota del EJB para este proceso.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 01/03/2011
	 * @param perfil
	 * @throws PersistenceException
	 * @return PerfilVO
	 * @throws TechnicalException 
	 * @throws SQLException 
	 * @throws BusinessException 
	 **/
	public PerfilVO registrarPerfil(PerfilVO perfil) throws PersistenceException, TechnicalException, SQLException, BusinessException,
	                                                        MailException, IndexerException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 03/03/2011
	 * @param long
	 * @throws SQLException
	 * @return PerfilVO
	 **/
	public PerfilVO buscarPerfil(long idCandidato) throws SQLException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 30/03/2011
	 * @param long
	 * @throws SQLException
	 * @return List<OtroMedioVO>
	 **/
	public List<OtroMedioVO> buscarOtrosMedios(long idCandidato) throws SQLException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 28/03/2011
	 * @param long
	 * @throws SQLException
	 * @return PerfilVO
	 **/
	public PerfilVO buscarPerfilCandidato(long idCandidato) throws SQLException;
	
	/**
	 * Realiza la invocacion del EJB para este proceso.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/03/2011
	 * @param escolaridad
	 * @throws PersistenceException
	 * @return EscolaridadVO
	 * @throws TechnicalException 
	 * @throws SQLException 
	 * @throws BusinessException 
	 **/
	public EscolaridadVO registrarEscolaridad(EscolaridadVO escolaridad)
			throws PersistenceException, TechnicalException, SQLException, BusinessException, IndexerException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 19/03/2011
	 * @param idCandidato
	 * @param principal
	 * @return List<GradoAcademicoVO>
	 **/
	public List<GradoAcademicoVO> buscarGrados(long idCandidato, int principal) throws SQLException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @return List<GradoAcademicoVO>
	 **/
	public List<GradoAcademicoVO> buscarGrados(long idCandidato) throws SQLException;
	
	/**
	 * Realiza la invocacion del EJB para esta operaciòn.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 22/03/2011
	 * @param long
	 * @param GradoAcademicoVO
	 * @return long
	 **/
	public long agregarGrado(long idCandidato, GradoAcademicoVO gradoVO) throws PersistenceException;
	
	/**
	 * Realiza la invocacion del EJB para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 22/03/2011
	 * @param long
	 * @return void
	 **/
	public void borrarGrado(long idCandidatoGradoAcademico) throws PersistenceException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param idCandidato
	 * @param principal
	 * @return List<IdiomaVO>
	 **/
	public List<IdiomaVO> buscarIdiomas(long idCandidato, int principal) throws SQLException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @return List<IdiomaVO>
	 **/
	public List<IdiomaVO> buscarIdiomas(long idCandidato) throws SQLException;
	
	/**
	 * Realiza la invocacion del EJB para esta operaciòn.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @param IdiomaVO
	 * @return long
	 **/
	public long agregarIdioma(long idCandidato, IdiomaVO idiomaVO) throws PersistenceException;
	
	/**
	 * Realiza la invocacion del EJB para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @return void
	 **/
	public void borrarIdioma(long idCandidatoIdioma) throws PersistenceException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @param long
	 * @param int
	 * @return List<ConocimientoHabilidadVO>
	 **/
	public List<ConocimientoHabilidadVO> buscarConocHabs(long idCandidato, 
			long idTipoConocimHabilidad, int principal) throws SQLException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @param idTipoConocimHabilidad
	 * @return List<ConocimientoHabilidadVO>
	 **/
	public List<ConocimientoHabilidadVO> buscarConocHabs(long idCandidato, 
			long idTipoConocimHabilidad) throws SQLException;
	
	/**
	 * Realiza la invocacion del EJB para esta operaciòn.
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @param ConocimientoHabilidadVO
	 * @return long
	 **/
	public long agregarConocHab(long idCandidato, ConocimientoHabilidadVO conocHabVO) throws PersistenceException;
	
	/**
	 * Actualiza un conocimiento o habilidad a un candidato
	 * 
	 * @author Sergio Téllez Vázquez
	 * @since 26/06/2012
	 * @param ConocimientoHabilidadVO
	 * @throws ServiceLocatorException, PersistenceException
	 * @return void
	 **/
	public int actualizarConocHab(ConocimientoHabilidadVO conocHabVO)throws PersistenceException;
	
	/**
	 * Realiza la invocacion del EJB para este proceso.
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @return void
	 **/
	public void borrarConocHab(long idCandidatoConocimHabilidad) throws PersistenceException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 24/03/2011
	 * @param long
	 * @param int
	 * @return List<ComputacionAvanzadaVO>
	 **/
	public List<ComputacionAvanzadaVO> buscarCompuAvanzadas(long idCandidato, 
			int principal) throws SQLException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @return List<ComputacionAvanzadaVO>
	 **/
	public List<ComputacionAvanzadaVO> buscarCompuAvanzadas(long idCandidato) 
	throws SQLException;
	
	/**
	 * Realiza la invocacion del EJB para esta operaciòn.
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @param ConocimientoHabilidadVO
	 * @return long
	 **/
	public long agregarCompuAvanzada(long idCandidato,
			ComputacionAvanzadaVO compAvanVO) throws PersistenceException;
	
	/**
	 * Realiza la invocacion del EJB para este proceso.
	 * @author Felipe Juárez Ramírez
	 * @since 23/03/2011
	 * @param long
	 * @return void
	 **/
	public void borrarCompuAvanzada(long idCandidatoCompuAvanzada) throws PersistenceException;
	
	/**
	 * Realiza la invocacion remota del EJB para este proceso.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 10/03/2011
	 * @param experiencia
	 * @throws PersistenceException
	 * @return ExperienciaVO
	 * @throws TechnicalException 
	 * @throws SQLException 
	 * @throws BusinessException 
	 **/
	public ExperienciaVO registrarExperiencia(ExperienciaVO experiencia)
			throws PersistenceException, TechnicalException, SQLException, BusinessException, IndexerException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 25/03/2011
	 * @param long
	 * @param int
	 * @return List<HistoriaLaboralVO>
	 **/
	public List<HistoriaLaboralVO> buscarHistLaboral(long idCandidato, 
			int principal) throws SQLException;
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @return List<HistoriaLaboralVO>
	 **/
	public List<HistoriaLaboralVO> buscarHistLaboral(long idCandidato) 
	throws SQLException;
	
	/**
	 * Realiza la invocacion del EJB para esta operaciòn.
	 * @author Felipe Juárez Ramírez
	 * @since 25/03/2011
	 * @param long
	 * @param HistoriaLaboralVO
	 * @return long
	 **/
	public long agregarHistLaboral(long idCandidato,
			HistoriaLaboralVO histLaboralVO) throws PersistenceException;
	
	/**
	 * Realiza la invocacion del EJB para este proceso.
	 * @author Felipe Juárez Ramírez
	 * @since 25/03/2011
	 * @param long
	 * @return void
	 **/
	public void borrarHistLaboral(long idHistorialLaboral) throws PersistenceException;
	
	/**
	 * Realiza la invocacion remota del EJB para este proceso.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 10/03/2011
	 * @param expectativa
	 * @throws PersistenceException
	 * @return ExpectativaVO
	 * @throws TechnicalException 
	 * @throws SQLException 
	 * @throws BusinessException 
	 **/
	public ExpectativaVO registrarExpectativa(ExpectativaVO expectativa)
			throws PersistenceException, TechnicalException, SQLException, BusinessException, IndexerException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @param int
	 * @return List<ExpectativaLaboralVO>
	 **/
	public List<ExpectativaLaboralVO> buscarExpecLaboral(long idCandidato, 
			int principal) throws SQLException;
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @return List<ExpectativaLaboralVO>
	 **/
	public List<ExpectativaLaboralVO> buscarExpecLaboral(long idCandidato) 
	throws SQLException;
	
	/**
	 * Realiza la invocacion del EJB para esta operaciòn.
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @param ExpectativaLaboralVO
	 * @return long
	 **/
	public long agregarExpecLaboral(long idCandidato,
			ExpectativaLaboralVO expecLaboralVO) throws PersistenceException;
	
	/**
	 * Realiza la invocacion del EJB para este proceso.
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @return void
	 **/
	public void borrarExpecLaboral(long idExpectativaLaboral) throws PersistenceException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @param int
	 * @return List<ExpectativaLugarVO>
	 **/
	public List<ExpectativaLugarVO> buscarExpecLugar(long idCandidato, 
			int principal) throws SQLException;
	
	/**
	 * Realiza la invocacion del DAO para esta consulta.
	 * 
	 * @author Felipe Juárez Ramírez
	 * @since 09/04/2011
	 * @param idCandidato
	 * @return List<ExpectativaLugarVO>
	 **/
	public List<ExpectativaLugarVO> buscarExpecLugar(long idCandidato) 
	throws SQLException;
	
	/**
	 * Realiza la invocacion del EJB para esta operaciòn.
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @param ExpectativaLugarVO
	 * @return long
	 **/
	public long agregarExpecLugar(long idCandidato,
			ExpectativaLugarVO expecLugarVO) throws PersistenceException;
	
	/**
	 * Realiza la invocacion del EJB para este proceso.
	 * @author Felipe Juárez Ramírez
	 * @since 27/03/2011
	 * @param long
	 * @return void
	 **/
	public void borrarExpecLugar(long idExpectativaLugar) throws PersistenceException;
	
	/**
	 * Expone la actualizaci&oacute;n de la informaci&oacute;n del video
	 * curriculum para su acceso de manera remota
	 * 
	 * @param idCandidato
	 *            el identificador del candidato a actualizar
	 * @param vo
	 *            contiene la informaci&oacute;n del video curriculum a
	 *            actualizar
	 * @throws PersistenceException
	 *             en caso de presentarse alg&uacute;n problema con las
	 *             operaciones de persistencia de los datos
	 * @author jose.jimenez
	 */
	public void actualizarCurriculum(long idCandidato, long idPerfil, CurriculumVo vo)
	        throws PersistenceException, BusinessException;

	/**
	 * Expone el almacenamiento de la fotografia del candidato para su acceso de
	 * manera remota
	 * 
	 * @param idCandidato
	 *            el identificador del candidato a actualizar
	 * @param fotografia
	 *            la imagen a almacenar
	 * @throws PersistenceException
	 *             en caso de presentarse alg&uacute;n problema con las
	 *             operaciones de persistencia de los datos
	 * @author jose.jimenez
	 */
	public void guardarFoto(long idCandidato, long idPerfil, byte[] fotografia)
	        throws PersistenceException, BusinessException;

	/**
	 * Expone la b&uacute;squeda de la secci&oacute;n &quot;Informaci&oacte;n 
	 * general&quot; para las pantallas del detalle del candidato
	 * @param idCandidato representa el identificador del candidato del cual se
	 *        quiere obtener la informaci&oacute;n
	 * @return un objeto {@code InformacionGeneralVO} que contiene la informaci&oacute;n
	 *         a mostrar. O {@literal null} si el {@code idCandidato} recibido
	 *         no es mayor a {@literal 0} o se produce un error al consultar la BD.
	 */
	public InformacionGeneralVO buscarInformacionGeneral(long idCandidato);
	
	public PerfilLaboralVo consultaPerfilLaboral(long idCandidato) throws PersistenceException;
	
	/**
	 * Guarda la suma de los gastos de un candidato
	 * @param idPropietario es el id del propietario al que se le asociara la suma
	 * @param suma monto de los gastos mensuales
	 * @throws PersistenceException si hay algun error al acceder a la BD
	 */
	public void guardarSumaCalcu(long idPropietario, double suma)throws PersistenceException;
	
	/**
	 * Obtiene los canditatos vinculados o seleccionados para una oferta de empleo
	 * @param idEmpresa
	 * @param idOferta
	 * @return List<CandidatoVo> lista de candidatos
	 * @throws SQLException
	 * @author Ricardo Geiringer
	 * @since  31/03/2011
	 * */
	public List<CandidatoVo> obtenerCandidatos(long idEmpresa,
			long idOferta) throws SQLException;
	
	/**
	 * Obtiene los canditatos postulados o en proceso para una oferta de empleo
	 * @param idEmpresa
	 * @param idOferta
	 * @return List<CandidatoVo> lista de postulados
	 * @throws SQLException
	 * @author Ricardo Geiringer
	 * @since  31/03/2011
	 * */
	public List<CandidatoVo> obtenerPostulados(long idEmpresa,
			long idOferta) throws SQLException;
	
	/**
	 * Elimina el vinculo del candidato con la oferta de empleo
	 * @param idOfertaCandidato
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @throws SQLException
	 * @author Ricardo Geiringer
	 * @since  31/03/2011
	 */
	public void eliminarCandidatosOferta(long idOfertaCandidato) throws PersistenceException, BusinessException,
			SQLException;

	/**
	 * Rechaza el proceso de un candidato en la oferta de empleo y envia un mail de notificacion
	 * @param idOfertaCandidato
	 * @param idMotivo
	 * @param destinatarioVO
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @throws SQLException
	 * @throws MailException 
	 * @author Ricardo Geiringer
	 * @since  31/03/2011
	 */
	public void rechazarOfertaCandidatoEnProceso(long idOfertaCandidato, int idMotivo, String motivoDesc,
			DestinatarioVO destinatarioVO) throws PersistenceException,
			BusinessException, SQLException, MailException;
	/**
	 * Obtiene los datos relacionados con la oferta de candidato.
	 * @param idOfertaCandidato
	 * @return OfertaCandidatoResumenVo objeto con los datos resumidos de la oferta
	 * @throws PersistenceException
	 * @throws SQLException
	 * @author Ricardo Geiringer
	 * @since  06/04/2011
	 */
	public OfertaCandidatoResumenVo obtenerOfertaCandidatoResumen(long idOfertaCandidato)
			throws PersistenceException, SQLException;
	
	/**
	 * Expone los medios de busqueda de candidatos para su acceso de manera remota
	 * 
	 * @param idEmpresa
	 * 			el identificador de la empresa
	 * @param idOferta
	 * 			el identificador de la oferta
	 * @throws SQLException
	 *          en caso de presentarse alg&uacute;n problema con la
	 *          consulta a base de datos
	 * @author Sergio Téllez
	 */
	public List<CandidatoVo> obtenerCandidatosOfertasActivas(long idEmpresa, long idOferta) throws SQLException;

	/**
	 * Contrata al candidato relacionado con la oferta de candidato y encia un correo de notificacion
	 * @param idOfertaCandidato
	 * @param fechaContrato
	 * @param destinatarioVO
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @throws SQLException
	 * @throws MailException 
	 * @author Ricardo Geiringer
	 * @since  07/04/2011
	 */
	public void aprobarOfertaCandidato(long idOfertaCandidato, Date fechaContrato,
			DestinatarioVO destinatarioVO)	throws PersistenceException, BusinessException, SQLException, MailException;

	/**
	 * Coloca en proceso al candidato relacionado con la oferta de candidato y encia un correo de notificacion
	 * @param idOfertaCandidato
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @throws SQLException
	 */
	void procesarOfertaCandidatoEnProceso(long idOfertaCandidato, long idUsuario)
			throws PersistenceException, BusinessException, SQLException;
	
	/**
	 * Programa una entrevista con el candidato y envia un correo de notificacion
	 * @param idOfertaCandidato
	 * @param fecha
	 * @param hora
	 * @param destinatarioVO
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @throws SQLException
	 * @throws MailException 
	 */
	public void programarEntrevistaOfertaCandidato(long idOfertaCandidato, Date fecha,String hora,
			DestinatarioVO destinatarioVO) throws PersistenceException,
			BusinessException, SQLException, MailException;

	/**
	 * Vincula la oferta a un candidato para que le aparezca en empresas que lo buscan
	 * @param idOfertaCandidato
	 * @param destinatarioVO
	 * @throws PersistenceException
	 * @throws BusinessException
	 * @throws SQLException
	 * @throws MailException 
	 */
	public void vincularOfertaCandidato(long idOfertaCandidato,
			DestinatarioVO destinatarioVO) throws PersistenceException,
			BusinessException, SQLException, MailException;

	/**
	 * Usa el  id del usuario registrado para obtener los datos del header del espacio de
	 * candidatos
	 * @param idCandidato
	 * @return
	 * @throws SQLException
	 */
	public CandidatoVo buscarDatosHeaderTemplateCandidato(long idCandidato)
	throws SQLException;
	
	/**
	 * Obtiene el estatus del CV del candidato
	 * @param idCandidato
	 * @return
	 * @throws SQLException
	
	 */
	public int getEstatusCV(long idCandidato)
	throws SQLException;
	
	public void setEstiloCV(long idCandidato, int estiloCv);
	
	public int getEstiloCV(long idCandidato);
	
	/**
	 * Método que obtiene el contador del candidato
	 * @param idCandidato
	 * @return CandidatoVerDetalleVO
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException 
	 */
	public CandidatoVerDetalleVO obtenerCandidatoVerDetalle(long idCandidato, int anio, int mes) throws BusinessException, SQLException;
	
	/**
	 * Método para actualizar el contador del candidato
	 * @param vo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException
	 */
	public void actualizarCandidatoVerDetalle(CandidatoVerDetalleVO vo) throws BusinessException, SQLException;
	
	/**
	 * Método que crea un contador para el candidato
	 * @param vo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException
	 */
	public void crearCandidatoVerDetalle(CandidatoVerDetalleVO vo) throws BusinessException, SQLException;
	
	
	
	/**
	 * Expone el empleo actual del candidato para su acceso de manera remota
	 * 
	 * @param idCandidato
	 * 			el identificador del candidato
	 * @param trabajoActual
	 * 			Indicador de si el empleo es o no actual
	 * @throws SQLException
	 *          en caso de presentarse alg&uacute;n problema con la
	 *          consulta a base de datos
	 * @author Sergio Téllez
	 */
	public List<HistoriaLaboralVO> buscarHistEmpleoActual(long idCandidato, int trabajoActual) throws SQLException;
	
	/**
	 * Expone los medios de busqueda de empleo del candidato para su acceso de manera remota
	 * 
	 * @param idCandidato
	 * 			el identificador del candidato
	 * @throws SQLException
	 *          en caso de presentarse alg&uacute;n problema con la
	 *          consulta a base de datos
	 * @author Sergio Téllez
	 */
	public List<String> mediosBusqueda(long idCandidato) throws SQLException;
	
	/**
	 * Realiza la carga de datos del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 27/03/2011
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return void
	 **/
	public PerfilBO loadPerfil(long idCandidato) throws BusinessException;
	public PerfilBO loadPerfil(long idCandidato, boolean ultimo) throws BusinessException;
	public PerfilBO loadPerfilTrabajoActualUltimo(long idCandidato, boolean ultimo) throws BusinessException;
	/**
	 * Guarda otros estudios del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param OtrosEstudiosVO
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public long registrarOtroEstudio(CandidatoOtroEstudioVO otroEstudioVO) throws BusinessException;
	
	/**
	 * Actualiza otros estudios del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param OtrosEstudiosVO
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public long actualizarOtroEstudio(CandidatoOtroEstudioVO otroEstudioVO) throws BusinessException;
	
	
	/**
	 * elimina otros estudios del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param OtrosEstudiosVO
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public long eliminarOtroEstudio(long idCandidatoEstudio) throws BusinessException;
	
	/**
	 * Obtiene otros estudios del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public List<CandidatoOtroEstudioVO> otrosEstudiosList(long idCandidato) throws BusinessException;
	
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
	public long actualizarCandidatoComputacion(long idCandidatoComputacion, long idCandiato, int procesadorTxt, int hojaCalculo, int internet, int redesSociales, String otros) throws BusinessException;
	
	/**
	 * Obtiene conocimientos computacion del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public ConocimientoComputacionVO findConocimientosComputacion(long idCandidato) throws BusinessException;	
	
	/**
	 * Contabiliza la visualizacion del detalle del candidato
	 * @param idCandidato
	 */
	public void contabilizaDetalleCandidato(long idCandidato);

	public List<CandidatoVo> filtrarCandidatos(String nombre, String apellido1,  Date fechaNacimiento, String curp, String correoElectronico, String apellido2, String telefono, long idEntidad, long idMunicipio, String domicilio, String usuario) throws SQLException;
	
	/**
	 * Actualiza el campo ID_RECIBE_OFERTA de la tabla PERFIL_LABORAL se invoca desde kioscos para activar la opcion de recibir ofertas por correo.
	 * @author Juan Ortiz
	 * @since 12/10/2011
	 * @param idCandidato
	 * @return void
	 **/
	
	public void actualizaRecibeOfertaCorreo(long idCandidato);

	public List<EstandarConocerVO> consultaConocer(long idCandidato) throws ConocerWSException;

	public ConocerConfigVO registraConocerConfig(ConocerConfigVO vo);
	
	public ConocerConfigVO consultaConocerConfigByIdCandidato(long idCandidato);
	
	public CandidatoVo findById(long idCandidato);
	
	public DomicilioVO findDomicilioCandidato(long idCandidato);
	
	/* CAMBIO EN PROCESO */	
	public int consultarEstatus(long idCandidato);
	public Integer consultarPpcEstatus(long idCandidato);	
	public void desactivarCandidato(long idCandidato, long idUsuario);	
	public void desactivarCandidato(long idCandidato, long idUsuario, int idMotivoDesactivacion, String detalleDesactivacion); 
	public void reactivarCandidato(long idCandidato, long idUsuario);
	public List<OfertaCandidatoADesactivarVO> buscarOfertasRelacionadas(long idCandidato);
	public String obtenerLoginUsuario(long idCandidato);
	public List<Long> consultaHabilidades(long idCandidato);
	
	public List<ResultadoBusquedaCandidatosVO> busquedaEspecificaCandidatos(BusquedaCandidatosVO form) throws SQLException;
	
	//Start Cambio Movil
	/**
	 * Realiza la carga de datos del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 27/03/2011
	 * @param idUsuario
	 * @throws BusinessException, ServiceLocatorException
	 * @return void
	 **/
	public PerfilBO loadPerfilUsuario(long idUsuario) throws BusinessException;
	
	public void actualizarRegistroCandidato(CandidatoVo candidatoVO);	
	//Fin cambio movil
	
	public int guardarCandidatoHabilidades(long idCandidato, long[] habilidades);
	
	public int actualizaRegistroPPCSinValidacion(long idCandidato, Date ppcFechaInscripcion, int ppcAceptacionTerminos, Integer ppcEstatusIMSS, Date ppcFechaBajaIMSS, String ppcTipoContratoIMSS, String nss) throws IllegalArgumentException, BusinessException;

	public int actualizaRegistroPPC(long idCandidato, Date ppcFechaInscripcion, int ppcAceptacionTerminos, Integer ppcEstatusIMSS, Date ppcFechaBajaIMSS, String ppcTipoContratoIMSS, String nss) throws IllegalArgumentException, BusinessException;	
	
    public CandidatoVo findPpcSdTermsAndConditionsData(final long idUsuario);
    
	public RESPUESTA_IMMS_CONSULTA_NSS consultaNssIMMS(String curp, String nss) throws IllegalArgumentException, TechnicalException;
    
    /**
	 * Seguimiento a postulación de candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idCandidato, seguimientoPostulacion
	 * @throws BusinessException
	 * @return int 
	 **/
	public int registrarSeguimientoPostulacion(long idUsuario, OfertaCandidatoVO oc, String estatus) throws BusinessException;
	
	/**
	 * Resultado entrevista postulación de candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idOfertaCandidato, resultadoEntrevista
	 * @throws ServiceLocatorException, BusinessException
	 * @return int 
	 **/
	public int resultadoEntrevistaPostulacion(long idUsuario, OfertaCandidatoVO oc, String estatus) throws BusinessException;
	
	/**
	 * Actualiza estatus PPC del candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idCandidato,  estatusPPC
	 * @throws ServiceLocatorException, BusinessException
	 * @return int 
	 **/
	public int actualizaEstatusPPC(long idCandidato, int estatusPPC, Integer ppcIdMotivoFuera) throws BusinessException;
	
	/**
	 * Actualiza estatus PPC del candidato
	 * @author Sergio Téllez
	 * @since 05/12/2014
	 * @param idCandidato,  estatusPPC
	 * @throws ServiceLocatorException, BusinessException
	 * @return int 
	 **/
	public int setContratadoPPC(long idCandidato, int estatusPPC, Integer ppcIdMotivoFuera, String fechaColocacion, String medioColocacion, String nombreEmpresa, String tituloOferta) throws BusinessException;
	
	/**
	 * Obtiene los canditatos postulados de una empresa
	 * @param idEmpresa
	 * @return List<CandidatoVo> lista de postulados
	 * @throws SQLException
	 * @author Sergio Téllez
	 * @since  19/12/2014
	 * */
	public List<CandidatoVo> postulatesByEmpresaList(long idEmpresa) throws SQLException;
	
	public void enviaNotificacionInscripcionPPC(long idCandidato) throws IllegalArgumentException, MailException, TechnicalException;

	/**
	 * Obtiene vigencia del candidato
	 * @author Sergio Téllez
	 * @since 16/06/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public boolean isEffectiveLimitCandidate(long idCandidato) throws BusinessException;	
	
	/**
	 * Actualiza vigencia del candidato
	 * @author Sergio Téllez
	 * @since 16/06/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public int upgradeEffective(long idCandidato) throws BusinessException;
	
	/**
	 * Actualiza los datos complementarios del candidato
	 * @author Sergio Téllez
	 * @since 15/10/2015
	 * @param modalidadCandidato
	 * @param modalidadCandidatoPTAT
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public int updateCandidateComplementData(ModalidadCandidatoVO mc, FormatoPTATVO ptat, List<BeneficiarioVO> contacts, List<BeneficiarioVO> beneficiaries, List<IdiomaVO> langs, List<FormatoPTATMaquinariaVO> engines, List<FormatoPTATCultivosVO> products, List<FormatoPTATHclinicaVO> histories) throws BusinessException;
	
	/**
	 * Verifica si el candidato tiene un registro previo en el programa solicitado
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @param idModalidad
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public ModalidadCandidatoVO getModalidadCandidato(long idCandidato, long idModalidad, long idSubprograma) throws BusinessException;
	
	public FormatoPTATVO getFormatoPTATVO(long idCandidato) throws BusinessException;
	
	/**
	 * Obtiene los productos de datos complementarios PTAT
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public List<FormatoPTATCultivosVO> getFormatoPTATCultivos(long idCandidato) throws BusinessException;
	
	/**
	 * Obtiene los beneficiarios de datos complementarios PTAT
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return boolean 
	 **/
	public List<ModalidadPtatBeneficiarioVO> getModalidadPtatBeneficiarioList(long idModalidadCandidatoPTAT) throws BusinessException;
	
	/**
	 * obtiene referencias laborales asociados al perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return List<ReferenciaLaboralVO>
	 **/
	public List<ReferenciaLaboralVO> getReferenciaLaboraList(long idCandidato) throws BusinessException;
	
	/**
	 * Consulta si esta habilidato el requerimiento de geolocalizacion
	 * @return true en caso de estar habilitado, false en caso contrario
	 * @throws BusinessException
	 */
	public boolean consultarPermisoGeolocalizacionRegistro()throws BusinessException; 
	
	/**
	 * Consulta si esta habilidato el requerimiento de geolocalizacion
	 * @param idCandidato
	 * @return true en caso de estar habilitado, false en caso contrario
	 * @throws BusinessException
	 */
	public boolean consultarPermisoGeolocalizacion(long idCandidato)throws BusinessException;

	public void actualizaEstatusGeoreferencia(long idCandidato, boolean estatus) throws BusinessException;
	
	/**
	 * Persiste el perfil complementario asociado al candidato
	 * @author Sergio Téllez
	 * @since 02/12/2015
	 * @param PerfilVO
	 * @return 1 en caso de actualización, -1 en caso contrario
	 * @throws BusinessException
	 */
	public int updatePerfilComp(PerfilVO perfil, ExpectativaLaboralVO el, HistoriaLaboralVO hl, FormatoSNE01VO sne, ModalidadCandidatoVO mc, List<BeneficiarioVO> beneficiarioList) throws BusinessException;
	
	/**
	 * Consulta el formato SNE asociado al candidato
	 * @author Sergio Téllez
	 * @since 02/12/2015
	 * @param idCandidato
	 * @return FormatoSNE01VO
	 * @throws BusinessException
	 */
	public FormatoSNE01VO findSNEByCandidate(long idCandidato) throws BusinessException;
	
	/**
	 * Consulta el formato LAP asociado al candidato
	 * @author Sergio Téllez
	 * @since 02/12/2015
	 * @param idCandidato
	 * @return FormatoLPAVO
	 * @throws BusinessException
	 */
	public FormatoLPAVO findLPAByCandidate(long idCandidato) throws BusinessException;

	/**
	 * Obtiene lista de beneficiarios SNE asociados al candidato
	 * @author Sergio Téllez
	 * @since 07/12/2015
	 * @param idCandidato
	 * @return n registros en caso de actualización, -1 en caso contrario
	 * @throws BusinessException
	 */
	public List<BeneficiarioVO> getBeneficiarioList(long idCandidato, long idTipoFormato, boolean isContact) throws BusinessException;

	/**
	 * elimina beneficiario SNE01 asociado al perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param idBeneficiario
	 * @throws BusinessException
	 * @return int
	 **/
	public int removeBeneficiario(long idBeneficiario) throws BusinessException;

	/**
	 * crea beneficiario SNE01 asociado al perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param beneficiario
	 * @throws BusinessException
	 * @return int
	 **/
	public int createBeneficiario(BeneficiarioVO beneficiario) throws BusinessException;

	/**
	 * Persiste el formato LPA asociado al candidato
	 * @author Sergio Téllez
	 * @since 14/01/2016
	 * @param FormatoLPAVO
	 * @return 1 en caso de actualización, -1 en caso contrario
	 * @throws BusinessException
	 */
	public int updateLPA(long idCandidato, FormatoLPAVO mml, ReferenciaLaboralVO referencia, GradoAcademicoVO gradoAcademicoVO) throws BusinessException;
	
	/**
	 * Persistir idioma asociado al candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 14/01/2016
	 * @param IdiomaVO
	 * @return long
	 * @throws BusinessException 
	 **/
	public long persistLang(IdiomaVO idiomaVO) throws BusinessException;

	/**
	 * elimina referencia laboral asociado al perfil de un candidato.
	 * 
	 * @author Sergio Téllez
	 * @since 24/11/2015
	 * @param referencia
	 * @throws BusinessException
	 * @return int
	 **/
	public int removeReferenciaLaboral(ReferenciaLaboralVO referencia) throws BusinessException;

	/**
	 * Obtiene la hisoria clínica asociada a los datos complementarios del candidato
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return List<FormatoPTATHclinicaVO> 
	 **/
	public List<FormatoPTATHclinicaVO> getDiseases(long idCandidato) throws BusinessException;
	
	/**
	 * Obtiene el tipo de maquinaria asociada a los datos complementarios del candidato
	 * @author Sergio Téllez
	 * @since 21/10/2015
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return List<FormatoPTATMaquinariaVO> 
	 **/
	public List<FormatoPTATMaquinariaVO> getFormatoPTATMaquinaria(long idCandidato) throws BusinessException;
	
	/**
	 * Persiste un conocimiento o habilidad a un candidato
	 * 
	 * @author Sergio Téllez Vázquez
	 * @since 07/03/2016
	 * @param ConocimientoHabilidadVO
	 * @throws ServiceLocatorException, PersistenceException
	 * @return void
	 **/
	public long update(mx.gob.stps.portal.persistencia.vo.ConocimientoHabilidadVO conocHabVO) throws BusinessException;
	
	/**
	 * Obtiene la canalización asociada a los datos complementarios del candidato
	 * @author Sergio Téllez
	 * @since 25/05/2016
	 * @param idCandidato
	 * @throws ServiceLocatorException
	 * @return List<CanalizacionCandidatoVO> 
	 **/
	public List<CanalizacionCandidatoVO> getCanalizacionCandidatoList(long idCandidato) throws BusinessException;
	
	/**
	 * Método que obtiene los candidatos nominales vinculados a una oferta del subprograma PTAT
	 * @author Sergio Téllez
	 * @since 06/06/2016
	 * @param idOferta
	 * @return List<Long>
	 * @throws BusinessException
	 */
	public List<Long> getNominalList(long idOferta) throws BusinessException;
	
	/**
	 * Método que obtiene los datos del Solicitante
	 * @author Octavio Alvarez
	 * @since 16/09/2016
	 * @param idCandidato
	 * @return List<Long>
	 * @throws BusinessException
	 */
	public Solicitante obtenerSolicitante(long idCandidato) throws BusinessException;
	
	//Notificacion Recupera Contraseña OAM
	public void notificacionRecuperacionContrasena(long idPropietario, String usuario, TIPO_PROPIETARIO tipoPropietario, String nombrePropietario,
			String correoElectronico, String url) throws MailException;

}