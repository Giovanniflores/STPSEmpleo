package mx.gob.stps.portal.core.autorizacion.service;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;
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

/**
 * Proporciona el acceso a los servicios para el modulo de Autorizacion
 * 
 * @author oscar.manzo
 *
 */
@Remote
public interface AutorizacionAppServiceRemote {

	/**
	 * Registra el parametro que define en minutos el tiempo del lapso entre cada ejecucion del proceso
	 * @param minutos
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */
	public void registraTiempoAsignacion(int minutos) throws BusinessException;

	public void registraBloqueAsignacion(int bloque) throws BusinessException;
	
	/**
	 * Consulta el tiempo configurado para el lapse de ejecucion del proceso de asignacion
	 * @return minutos
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */
	public int consultaTiempoAsignacion() throws BusinessException;

	public int consultaBloqueAsignacion() throws BusinessException;
	
	/**
	 * Consulta y asigna los Registros a validar por un usuario Publicador
	 * @param idUsuario identificador del usuario
	 * @return lista de <mx.gob.stps.portal.core.autorizacion.vo.RegistroPorValidarVO> registros por validar
	 * @throws SQLException Lanzada en caso de error durante la consulta y actualizacion de los registros
	 */
	public List<RegistroPorValidarVO> asignaRegistrosPorValidar(long idUsuario) throws SQLException, BusinessException;
	
	public List<RegistroPorValidarVO> actualizaRegistrosPorValidar(long idUsuario) throws SQLException, BusinessException;
	
	/**
	 * Establece el estatus del Registro por Validar cuando el Publicador lo selecciona para realizar la revision y validacion
	 * @param idRegValidar identificador del registro por validar
	 * @throws PersistenceException Lanzada en caso de error durante la persistencia del registro
	 */
	public void estableceRegistroEnRevision(long idRegValidar, long idUsuario) throws PersistenceException;

	/**
	 * Establece el estatus del Registro por Validar cuando es Rechazado por el Publicador
	 * @param idRegValidar identificador del registro por validar
	 * @throws PersistenceException Lanzada en caso de error durante la persistencia del registro
	 */
	public void rechazaRegistro(long idRegValidar, long idMotivoRechazo, String detalleRechazo, long idUsuario) throws BusinessException;
	
	public void cancelaValidacionRegistro(long idRegValidar, long idUsuario) throws PersistenceException;
	
	/**
	 * Establece el estatus del Registro por Validar cuando es Aceptado por el Publicador
	 * @param idRegValidar identificador del registro por validar
	 * @throws PersistenceException Lanzada en caso de error durante la persistencia del registro
	 */
	public void autorizaRegistro(long idRegValidar, long idUsuario) throws PersistenceException, BusinessException, 
	                                                                       TechnicalException, MailException,
	                                                                       IndexerException;

	/**
	 * Establece el estatus del Registro por Validar cuando el Publicador ya no lo atendera
	 * @param idUsuario identificador del Publicador asignado al registro por Validar
	 * @throws SQLException Lanzada en caso de error durante la consulta y actualizacion de los registros
	 */
	public void desasignaRegistrosPorValidar(long idUsuario) throws SQLException;

	public List<EmpresaFraudulentaVO> consultaEmpresasFraudulentasSimilares(long idEmpresa) throws SQLException;	

	public List<CatalogoOpcionVO> consultaMotivosRechazo(int intTipoRegistro);
	
	public long[] consultaRequiereDetalle();

	public List<ParametroVO> consultaParametros();
	
	public ParametroVO consultaParametro(long idParametro) throws PersistenceException;
	
	public void actualizaParametro(long idParametro, String valor) throws PersistenceException;
		
	/**
	 * Consulta los registros por validar asignados a cada publicador
	 * @throws ServiceLocatorException Lanzada en caso de error durante la consulta de registros
	 */
	public List<PublicadorVO> publishersList() throws PersistenceException;	
	
	/**
	 * Consulta los registros por validar pendientes
	 * @throws ServiceLocatorException Lanzada en caso de error durante la consulta de registros
	 */
	public int totalRegistrosPorValidar() throws PersistenceException;	
	
	/**
	 * Consulta los registros por validar de acuerdo a ciertos parámetros de búsqueda
	 * @throws PersistenceException Lanzada en caso de error durante la persistencia del registro
	 */
	public List<PublicadorVO> productivityPublishersList(String name, String lastname, String rangeInit, String rangeFinal) throws PersistenceException;
	
	public RegistroPorValidarVO consultaRegistroPorValidar(long idRegValidar);
	
	public void enviarEmpFraudulenta(long idEmpresa, long idUsuario) throws PersistenceException, BusinessException;

	public List<EmpresaFraudulentaVO> consultaEmpresasFraudulentasOferta(
			long idEmpresa);

	public EmpresaFraudulentaVO consultaDetalleEmpresaFraudulenta(
			long idEmpresaFraudulenta) throws BusinessException;
	
	/**
	 * Crea un registro en bitácora para la actualización de parámetros
	 */
	public int binnacleStatusUpd(EVENTO evento, long idUsuario, String descripcion, TIPO_REGISTRO tipoRegistro,
			long idRegistro, int estatusAnterior, String idDetalle, int idTipoPropietario) throws PersistenceException, BusinessException;
}
