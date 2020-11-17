package mx.gob.stps.portal.movil.web.seguridad.delegate;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_CATALOGO_OPCION;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_SEGURIDAD;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.service.CatalogoOpcionAppServiceRemote;
import mx.gob.stps.portal.core.seguridad.exception.UserInactiveException;
import mx.gob.stps.portal.core.seguridad.service.SeguridadAppServiceRemote;
import mx.gob.stps.portal.core.seguridad.vo.ConfirmacionVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

public final class SeguridadMovilDelegateImpl {

	private static final SeguridadMovilDelegateImpl INSTANCE = new SeguridadMovilDelegateImpl();

	private SeguridadMovilDelegateImpl() {
	}

	public static SeguridadMovilDelegateImpl getInstance() {
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.web.security.delegate.SecutityDelegate#consultaUsuario
	 * (long)
	 */
	public UsuarioVO consultaUsuario(long idUsuario) throws ServiceLocatorException {
		return getSeguridadAppService().consultaUsuario(idUsuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.web.security.delegate.SecutityDelegate#consultaUsuario
	 * (java.lang.String)
	 */
	public UsuarioVO consultaUsuarioPorLogin(String usuario) throws ServiceLocatorException {
		return getSeguridadAppService().consultaUsuarioPorLogin(usuario);
	}

	public long consultaPropietario(long idUsuario) throws ServiceLocatorException, SQLException, UserInactiveException {
		return getSeguridadAppService().consultaPropietario(idUsuario);
	}

	private SeguridadAppServiceRemote getSeguridadAppService() throws ServiceLocatorException {
		SeguridadAppServiceRemote ejb = (SeguridadAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_SEGURIDAD);
		return ejb;
	}

	private CatalogoOpcionAppServiceRemote getCatalogoOpcionAppService() throws ServiceLocatorException {
		CatalogoOpcionAppServiceRemote ejb = (CatalogoOpcionAppServiceRemote) ServiceLocator
				.getSessionRemote(JNDI_EJB_CATALOGO_OPCION);
		return ejb;
	}

	public List<CatalogoOpcionVO> consultaEntidades(long catalogoOpcionEntidadFederativa, long[] arrIds)
			throws ServiceLocatorException {
		return getCatalogoOpcionAppService().consultarCatalogo(catalogoOpcionEntidadFederativa, arrIds);
	}

	public MovilSessionVO existeMovilSessionVO(MovilSessionVO vo) throws ServiceLocatorException {
		return getSeguridadAppService().existeMovilSession(vo);
	}

	public MovilSessionVO guardarMovilSessionVO(MovilSessionVO vo) throws ServiceLocatorException {
		return getSeguridadAppService().guardarMovilSession(vo);
	}

	public MovilSessionVO actualizarMovilSessionVO(MovilSessionVO movilSession) throws ServiceLocatorException {
		return getSeguridadAppService().actualizarMovilSessionVO(movilSession);

	}

	public ConfirmacionVO confirmacionDirectaCandidato(String correoElectronico, String CURP) throws BusinessException,
			TechnicalException, ServiceLocatorException {
		return getSeguridadAppService().confirmacionDirectaCandidato(correoElectronico, CURP);
	}
	
	public ConfirmacionVO confirmacionReactivacionCandidato(String username, String CURP) throws BusinessException,
			TechnicalException, ServiceLocatorException{
		return getSeguridadAppService().confirmacionReactivacionCandidato(username, CURP);
	}

	public ConfirmacionVO confirmacionDirectaEmpresa(String correoElectronico, Date fecha) throws BusinessException, TechnicalException, ServiceLocatorException{
		return getSeguridadAppService().confirmacionDirectaEmpresa(correoElectronico, fecha);
	}
	
	public ConfirmacionVO confirmacionDirectaEmpresa(String username, String razonSocial) throws BusinessException, TechnicalException, ServiceLocatorException {
		return getSeguridadAppService().confirmacionDirectaEmpresa(username, razonSocial);
	}
	
	public ConfirmacionVO confirmacionDirectaEmpresaActivo(String correoElectronico, Date fecha) throws BusinessException, TechnicalException, ServiceLocatorException{
		return getSeguridadAppService().confirmacionDirectaEmpresaActivo(correoElectronico, fecha);
	}
	public void deleteMovilSessionVO(MovilSessionVO vo) throws ServiceLocatorException {
		getSeguridadAppService().deleteMovilSessionVO(vo);
	}

	public EmpresaVO consultaEmpresaPorIdUsuario(long idUsuario) throws ServiceLocatorException {
		//TODO implemento empresa
		return getSeguridadAppService().consultaEmpresaPorIdUsuario(idUsuario);
//		return new EmpresaVO();
	}

	

}
