package mx.gob.stps.portal.web.autorization.delegate;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.autorizacion.vo.ParametroVO;
import mx.gob.stps.portal.core.autorizacion.vo.RegistroPorValidarVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaFraudulentaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.core.seguridad.vo.PublicadorVO;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

/**
 * Concentra las invocaciones a la capa de negocio para el Modulo de Autorizacion de registros
 * 
 * @author oscar.manzo
 *
 */
public interface AutorizationBusDelegate {

	/**
	 * Registra el parametro que define en minutos el tiempo del lapso entre cada ejecucion del proceso
	 * @param minutos
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */
	public void registraTiempoAsignacion(int minutos) throws BusinessException, ServiceLocatorException;

	public void registraBloqueAsignacion(int bloque) throws BusinessException, ServiceLocatorException;
	
	/**
	 * Consulta el tiempo configurado para el lapse de ejecucion del proceso de asignacion
	 * @return minutos
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */
	public int consultaTiempoAsignacion() throws BusinessException, ServiceLocatorException;

	public int consultaBloqueAsignacion() throws BusinessException, ServiceLocatorException;
	
	/**
	 * Consulta y asigna los Registros a validar por un usuario Publicador
	 * @param idUsuario identificador del usuario
	 * @return lista de <mx.gob.stps.portal.core.autorizacion.vo.RegistroPorValidarVO> registros por validar
	 * @throws SQLException Lanzada en caso de error durante la consulta y actualizacion de los registros
	 */
	public List<RegistroPorValidarVO> asignaRegistrosPorValidar(long idUsuario) throws SQLException, BusinessException, ServiceLocatorException;
	
	public List<RegistroPorValidarVO> actualizaRegistrosPorValidar(long idUsuario) throws ServiceLocatorException, BusinessException, SQLException;
	
	/**
	 * Establece el estatus del Registro por Validar cuando el Publicador lo selecciona para realizar la revision y validacion
	 * @param idRegValidar identificador del registro por validar
	 * @throws PersistenceException Lanzada en caso de error durante la persistencia del registro
	 */
	public void estableceRegistroEnRevision(long idRegValidar, long idUsuario) throws PersistenceException, ServiceLocatorException;

	/**
	 * Establece el estatus del Registro por Validar cuando es Rechazado por el Publicador
	 * @param idRegValidar identificador del registro por validar
	 * @param motivoRechazo identificador del motivo de rechazo
	 * @param idUsuario identificador del Publicador
	 * @throws PersistenceException Lanzada en caso de error durante la persistencia del registro
	 */
	public void rechazaRegistro(long idRegValidar, long idMotivoRechazo, String detalleRechazo, long idUsuario) throws ServiceLocatorException, PersistenceException, BusinessException;

	public void cancelaValidacionRegistro(long idRegValidar, long idUsuario) throws ServiceLocatorException, PersistenceException;
	
	/**
	 * Establece el estatus del Registro por Validar cuando es Aceptado por el Publicador
	 * @param idRegValidar identificador del registro por validar
	 * @param idUsuario identificador del Publicador
	 * @throws PersistenceException Lanzada en caso de error durante la persistencia del registro
	 */
	public void autorizaRegistro(long idRegValidar, long idUsuario) throws PersistenceException, ServiceLocatorException,
	                                                                       BusinessException, TechnicalException,
	                                                                       MailException, IndexerException;
	
	/**
	 * Establece el estatus del Registro por Validar cuando el Publicador ya no lo atendera
	 * @param idUsuario identificador del Publicador asignado al registro por Validar
	 * @throws SQLException Lanzada en caso de error durante la consulta y actualizacion de los registros
	 */
	public void desasignaRegistrosPorValidar(long idUsuario) throws SQLException, ServiceLocatorException;

	public List<EmpresaFraudulentaVO> consultaEmpresasFraudulentasSimilares(long idEmpresa) throws ServiceLocatorException, SQLException;

	public TestimonioVO consultaTestimonio(long idTestimonio) throws ServiceLocatorException, PersistenceException;
	
	public List<CatalogoOpcionVO> consultaMotivosRechazo(int intTipoRegistro) throws ServiceLocatorException;
	
	public long[] consultaRequiereDetalle() throws ServiceLocatorException;
	
	public List<ParametroVO> consultaParametros() throws ServiceLocatorException;
	
	public ParametroVO consultaParametro(long idParametro) throws PersistenceException, ServiceLocatorException;
	
	public void actualizaParametro(long idParametro, String valor) throws PersistenceException, ServiceLocatorException;
	
	public List<PublicadorVO> publishersList() throws ServiceLocatorException;
	
	/**
	 * Consulta los registros por validar pendientes
	 * @throws ServiceLocatorException Lanzada en caso de error durante la consulta de registros
	 */
	public int totalRegistrosPorValidar() throws ServiceLocatorException;	
	
	/**
	 * Consulta los registros por validar de acuerdo a ciertos parámetros de búsqueda
	 * @throws ServiceLocatorException Lanzada en caso de error durante la consulta de registros
	 */
	public List<PublicadorVO> productivityPublishersList(String name, String lastname, String rangeInit, String rangeFinal) throws ServiceLocatorException;
	
	public RegistroPorValidarVO consultaRegistroPorValidar(long idRegValidar) throws ServiceLocatorException;
	
	public void enviarEmpFraudulenta(long idEmpresa, long idUsuario) throws ServiceLocatorException, PersistenceException, BusinessException;

	public List<EmpresaFraudulentaVO> consultaEmpresasFraudulentasOferta(
			long idEmpresa) throws ServiceLocatorException;

	public EmpresaFraudulentaVO detalleEmpresaFraudulenta(
			long idEmpresaFraudulenta) throws ServiceLocatorException, BusinessException;
	
	/**
	 * Crea un registro en bitácora para la actualización de parámetros
	 */
	public int bitacoraEstatus (EVENTO evento, long idUsuario, String descripcion, TIPO_REGISTRO tipoRegistro,
			long idRegistro, int estatusAnterior, String idDetalle, int idTipoPropietario) throws ServiceLocatorException, BusinessException;
}