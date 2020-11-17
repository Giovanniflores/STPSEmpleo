package mx.gob.stps.portal.web.autorization.delegate;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.autorizacion.service.AutorizacionAppServiceRemote;
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
import mx.gob.stps.portal.core.testimonio.service.TestimonioAppServiceRemote;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;

/**
 * Concentra las invocaciones a la capa de negocio para el Modulo de Autorizacion de registros
 * 
 * @author oscar.manzo
 *
 */
public final class AutorizationBusDelegateImpl implements AutorizationBusDelegate {

	private static AutorizationBusDelegate instance = new AutorizationBusDelegateImpl();

	private AutorizationBusDelegateImpl(){}	

	public static AutorizationBusDelegate getInstance(){
		return instance;
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate#registraTiempoAsignacion(int)
	 */
	public void registraTiempoAsignacion(int minutos) throws BusinessException, ServiceLocatorException {
		getAutorizacionAppService().registraTiempoAsignacion(minutos);
	}

	public void registraBloqueAsignacion(int bloque) throws BusinessException, ServiceLocatorException{
		getAutorizacionAppService().registraBloqueAsignacion(bloque);
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate#consultaTiempoAsignacion()
	 */
	public int consultaTiempoAsignacion() throws BusinessException, ServiceLocatorException {
		return getAutorizacionAppService().consultaTiempoAsignacion();
	}

	public int consultaBloqueAsignacion() throws BusinessException, ServiceLocatorException {
		return getAutorizacionAppService().consultaBloqueAsignacion();
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate#asignaRegistrosPorValidar(long)
	 */
	public List<RegistroPorValidarVO> asignaRegistrosPorValidar(long idUsuario) throws ServiceLocatorException, SQLException, BusinessException {
		return getAutorizacionAppService().asignaRegistrosPorValidar(idUsuario);
	}

	public List<RegistroPorValidarVO> actualizaRegistrosPorValidar(long idUsuario) throws ServiceLocatorException, SQLException, BusinessException {
		return getAutorizacionAppService().actualizaRegistrosPorValidar(idUsuario);
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate#estableceRegistroEnRevision(long)
	 */
	public void estableceRegistroEnRevision(long idRegValidar, long idUsuario) throws ServiceLocatorException, PersistenceException {
		getAutorizacionAppService().estableceRegistroEnRevision(idRegValidar, idUsuario);
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate#rechazaRegistro(long, long, String, long)
	 */
	public void rechazaRegistro(long idRegValidar, long idMotivoRechazo, String detalleRechazo, long idUsuario) throws ServiceLocatorException, PersistenceException, BusinessException {
		getAutorizacionAppService().rechazaRegistro(idRegValidar, idMotivoRechazo, detalleRechazo, idUsuario);
	}

	public void cancelaValidacionRegistro(long idRegValidar, long idUsuario) throws ServiceLocatorException, PersistenceException{
		getAutorizacionAppService().cancelaValidacionRegistro(idRegValidar, idUsuario);
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate#autorizaRegistro(long, long)
	 */
	public void autorizaRegistro(long idRegValidar, long idUsuario) throws ServiceLocatorException, PersistenceException,
	BusinessException, TechnicalException,
	MailException, IndexerException {
		getAutorizacionAppService().autorizaRegistro(idRegValidar, idUsuario);
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate#desasignaRegistrosPorValidar(long)
	 */
	public void desasignaRegistrosPorValidar(long idUsuario) throws ServiceLocatorException, SQLException {
		getAutorizacionAppService().desasignaRegistrosPorValidar(idUsuario);
	}

	public List<EmpresaFraudulentaVO> consultaEmpresasFraudulentasSimilares(long idEmpresa) throws ServiceLocatorException, SQLException {
		return getAutorizacionAppService().consultaEmpresasFraudulentasSimilares(idEmpresa);
	}

	public TestimonioVO consultaTestimonio(long idTestimonio) throws ServiceLocatorException, PersistenceException {
		return getTestimonioAppService().consultaTestimonio(idTestimonio);
	}

	public List<CatalogoOpcionVO> consultaMotivosRechazo(int intTipoRegistro) throws ServiceLocatorException {
		return getAutorizacionAppService().consultaMotivosRechazo(intTipoRegistro);
	}

	public long[] consultaRequiereDetalle() throws ServiceLocatorException{
		return getAutorizacionAppService().consultaRequiereDetalle();
	}

	public List<ParametroVO> consultaParametros() throws ServiceLocatorException {
		return getAutorizacionAppService().consultaParametros();
	}

	public ParametroVO consultaParametro(long idParametro) throws PersistenceException, ServiceLocatorException {
		return getAutorizacionAppService().consultaParametro(idParametro);
	}

	public void actualizaParametro(long idParametro, String valor) throws PersistenceException, ServiceLocatorException{
		getAutorizacionAppService().actualizaParametro(idParametro, valor);
	}

	/**
	 * Obtiene la referencia a los servicios de seguridad
	 * @return instancia de servcios
	 * @throws ServiceLocatorException Lanzada en caso de error en la conexion con los servicios
	 */
	private AutorizacionAppServiceRemote getAutorizacionAppService() throws ServiceLocatorException {
		AutorizacionAppServiceRemote ejb = (AutorizacionAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_AUTORIZACION);
		return ejb;
	}	

	private TestimonioAppServiceRemote getTestimonioAppService() throws PersistenceException, ServiceLocatorException {
		TestimonioAppServiceRemote ejb = (TestimonioAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_TESTIMONIO);
		return ejb;
	}

	@Override
	public List<PublicadorVO> publishersList() throws ServiceLocatorException {
		return getAutorizacionAppService().publishersList();
	}	

	@Override
	public int totalRegistrosPorValidar() throws ServiceLocatorException {
		return getAutorizacionAppService().totalRegistrosPorValidar();
	}		

	@Override
	public List<PublicadorVO> productivityPublishersList(String name, String lastname, String rangeInit, String rangeFinal) throws ServiceLocatorException {
		return getAutorizacionAppService().productivityPublishersList(name, lastname, rangeInit, rangeFinal);
	}

	public RegistroPorValidarVO consultaRegistroPorValidar(long idRegValidar) throws ServiceLocatorException {
		return getAutorizacionAppService().consultaRegistroPorValidar(idRegValidar);	
	}

	public void enviarEmpFraudulenta(long idEmpresa, long idUsuario) throws ServiceLocatorException, PersistenceException, BusinessException{
		getAutorizacionAppService().enviarEmpFraudulenta(idEmpresa, idUsuario);
	}

	@Override
	public List<EmpresaFraudulentaVO> consultaEmpresasFraudulentasOferta(
			long idEmpresa) throws ServiceLocatorException {

		return getAutorizacionAppService().consultaEmpresasFraudulentasOferta(idEmpresa) ;
	}

	@Override
	public EmpresaFraudulentaVO detalleEmpresaFraudulenta(
			long idEmpresaFraudulenta) throws ServiceLocatorException, BusinessException {
		return getAutorizacionAppService().consultaDetalleEmpresaFraudulenta(idEmpresaFraudulenta);
	}

	@Override
	public int bitacoraEstatus(EVENTO evento, long idUsuario, String descripcion, TIPO_REGISTRO tipoRegistro,
			long idRegistro, int estatusAnterior, String idDetalle, int idTipoPropietario) throws ServiceLocatorException, BusinessException {
		return getAutorizacionAppService().binnacleStatusUpd(evento, idUsuario, descripcion, tipoRegistro, idRegistro, estatusAnterior, idDetalle, idTipoPropietario);
	}
}
