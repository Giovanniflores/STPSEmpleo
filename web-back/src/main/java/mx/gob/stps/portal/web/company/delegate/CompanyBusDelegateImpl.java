package mx.gob.stps.portal.web.company.delegate;

import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_EMPRESA;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceRemote;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;

public class CompanyBusDelegateImpl implements CompanyBusDelegate {


	private static Logger logger = Logger.getLogger(CompanyBusDelegate.class);
	
	private static CompanyBusDelegate instance = new CompanyBusDelegateImpl();

	
	private CompanyBusDelegateImpl() {}
	
	/**
	 * Obtiene una instancia de la Interfaz
	 * @return CompanyBusDelegate
	 * */
	public static CompanyBusDelegate getInstance() {
		return instance;
	}
	
	/**
	 * Realiza la invocación remota del servicio de Candidatos
	 * @throws BusinessException
	 * @return void
	 * */
	public EmpresaAppServiceRemote getEmpresaAppService() throws ServiceLocatorException {
		EmpresaAppServiceRemote ejb = (EmpresaAppServiceRemote)
		ServiceLocator.getSessionRemote(JNDI_EJB_EMPRESA);
		return ejb;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateLogo(UsuarioWebVO usuario, byte[] logo) throws BusinessException, ServiceLocatorException {
		
		try {
			if (usuario != null) {
				getEmpresaAppService().actualizaLogotipo(usuario.getIdPropietario(), usuario.getIdPerfil(), logo);
			} else {
				throw new BusinessException("errors.user.notAllowed");
			}
		} catch (PersistenceException pe) {
			logger.error("Al cargar el logotipo de la empresa", pe);
			throw new BusinessException("app.exp.tecnica.err");
		}

	}

	@Override
	public void activarEmpresa(long idEmpresa) throws BusinessException, ServiceLocatorException {
		getEmpresaAppService().actualizarEmpresaAutorizar(idEmpresa);
	}

	@Override
	public String obtenerCapacitacionMixta(long idEmpresa) throws ServiceLocatorException, BusinessException {
		return getEmpresaAppService().obtenerCapacitacionMixta(idEmpresa);
	}

	@Override
	public Long salvarCapacitacionMixta(String texto,Long idEmpresa) throws ServiceLocatorException, BusinessException {
		return getEmpresaAppService().salvarCapacitacionMixta(texto, idEmpresa);
	}

	@Override
	public Long updateCapacitacionMixta(String texto, Long idEmpresa) throws ServiceLocatorException, BusinessException {
		return getEmpresaAppService().updateCapacitacionMixta(texto, idEmpresa);
	}
	
	//Obtener Empresa OAM
	@Override
	public EmpresaVO findEmpresaByIdUsuario(long idUsuario) throws ServiceLocatorException, BusinessException {
		return getEmpresaAppService().findEmpresaByIdUsuario(idUsuario);
	}
	
	//NOTIFICA RECUPERACION CONTRASENA OAM
	@Override
		public void notificacionRecuperacionContrasena(long idPropietario, String usuario, TIPO_PROPIETARIO tipoPropietario, String nombrePropietario,
				String correoElectronico, String url) throws MailException, ServiceLocatorException, BusinessException {
				getEmpresaAppService().notificacionRecuperacionContrasena(idPropietario, usuario, tipoPropietario, nombrePropietario, correoElectronico, url);
		}
}