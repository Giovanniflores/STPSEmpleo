package mx.gob.stps.portal.web.company.delegate;

import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_CATALOGO_OPCION;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_EMPRESA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_TELEFONO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceRemote;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.empresa.vo.RegistroEmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.service.CatalogoOpcionAppServiceRemote;
import mx.gob.stps.portal.core.infra.service.TelefonoAppServiceRemote;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.core.ws.renapo.exception.ConsultaWsPorCurpException;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.ws.renapo.vo.CurpVO;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public class EmpresaRegistroBusDelegateImpl implements EmpresaRegistroBusDelegate {
	
	public static EmpresaRegistroBusDelegate instance = new EmpresaRegistroBusDelegateImpl(); 
	
	public static EmpresaRegistroBusDelegate getInstance() {
		return instance;
	}
	
	/**
	 * esCorreoUnico
	 * 
	 * @param correoElectronico
	 * @return boolean
	 * @throws CorreoRepetidoException
	 * @throws ServiceLocatorException
	 */		
	public boolean esCorreoUnico(String correoElectronico)
			throws ServiceLocatorException {
		return getEmpresaRegistroAppService().esCorreoUnico(correoElectronico);
	}

	/**
	 * esUsuarioUnico
	 * 
	 * @param usuario
	 * @return boolean
	 * @throws ServiceLocatorException
	 */		
	public boolean esUsuarioUnico(String usuario)
			throws ServiceLocatorException {
		return getEmpresaRegistroAppService().esUsuarioUnico(usuario);
	}
	
	public boolean tieneOfertas(String correoElectronico)
			throws ServiceLocatorException{
		return getEmpresaRegistroAppService().tieneOfertas(correoElectronico);
	}

	/**
	 * registraEmpresa
	 * 
	 * @param registroEmpresaVO
	 * @return long
	 * @throws CorreoRepetidoException
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws PersistenceException
	 * @throws TechnicalException
	 */		
	public long registraEmpresa(RegistroEmpresaVO registroEmpresaVO)
			throws CorreoRepetidoException,
			BusinessException, TechnicalException,
			PersistenceException, ServiceLocatorException {
		return getEmpresaRegistroAppService().registraEmpresa(registroEmpresaVO);
	}
	
	/**
	 * regeneraEmpresa
	 * 
	 * @param registroEmpresaVO
	 * @return long
	 * @throws CorreoRepetidoException
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws PersistenceException
	 * @throws TechnicalException
	 */		
	public long regeneraEmpresa(RegistroEmpresaVO registroEmpresaVO)
			throws CorreoRepetidoException,
			BusinessException, TechnicalException,
			PersistenceException, ServiceLocatorException {
		return getEmpresaRegistroAppService().regeneraEmpresa(registroEmpresaVO);
	}	

	/**
	 * registraEmpresa
	 * 
	 * @param registroEmpresaVO
	 * @param idUsuario
	 * @return long
	 * @throws CorreoRepetidoException
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws PersistenceException
	 * @throws TechnicalException
	 * @throws MailException 
	 */		
	/*
	public long registraEmpresa(RegistroEmpresaVO registroEmpresaVO,
			long idUsuario) throws BusinessException, ServiceLocatorException,
			PersistenceException, TechnicalException, MailException, CorreoRepetidoException {
		return getEmpresaRegistroAppService().registraEmpresa(registroEmpresaVO, idUsuario);
		
	}*/
	
	@Override
	public EmpresaVO actualizaEmpresa(EmpresaVO empresaVO, long idUsuario, String changedFields, boolean isChangedPassword,
			boolean isChangedEmail, boolean isChangedIdPortal) throws ServiceLocatorException, TechnicalException {
		return getEmpresaRegistroAppService().actualizarEmpresa(empresaVO, idUsuario, changedFields, isChangedPassword, isChangedEmail, isChangedIdPortal);
	}	
	
	public EmpresaVO consultaEmpresa(long idEmpresa) throws ServiceLocatorException{
		return getEmpresaRegistroAppService().findEmpresaById(idEmpresa);
	}
	
	public EmpresaVO findEmpresaByCP(String rfc, String email, String cp) throws ServiceLocatorException, BusinessException, SQLException {
		return getEmpresaRegistroAppService().findEmpresaByCP(rfc, email, cp);
	}
	
	public void notificaRegistroEmpresa(long idUsuario, long idEmpresa, int estatusEmpresa, String nombreEmpresa,
			String correoElectronico, String contrasena, String idPortalEmpleo, String detalle) throws ServiceLocatorException, MailException, BusinessException {
		getEmpresaRegistroAppService().notificaEmpresaCreada(idUsuario, idEmpresa, estatusEmpresa, nombreEmpresa, 
				correoElectronico, contrasena, idPortalEmpleo, detalle);		
	}
	
	public void notificacionRecuperacionPswEmpresa(long idEmpresa, String usuario, String nombreEmpresa, String correoElectronico, String clave) throws ServiceLocatorException, MailException{
		getEmpresaRegistroAppService().notificacionRecuperacionPswEmpresa(idEmpresa, usuario, nombreEmpresa, correoElectronico, clave);
	}
	
	
	public List<TelefonoVO> initTelefonos(long idEmpresa, long idTipoPropietario) throws BusinessException, ServiceLocatorException{
		TelefonoAppServiceRemote telefonoAppServiceRemote = getTelefonoAppService();
		return telefonoAppServiceRemote.consultarTelefonos(idEmpresa, idTipoPropietario);
	}
	

	/**
	 * consultarCatalogo
	 * 
	 * @param idCatalogo
	 * @return List<CatalogoOpcionVO>
	 * @throws ServiceLocatorException
	 */	
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo)
			throws ServiceLocatorException {
		CatalogoOpcionAppServiceRemote catalogoOpcionAppServiceRemote = getCatalogoOpcionAppService();
		return catalogoOpcionAppServiceRemote.consultarCatalogo(idCatalogo);
	}
	
	private TelefonoAppServiceRemote getTelefonoAppService() throws ServiceLocatorException {
		TelefonoAppServiceRemote ejb = (TelefonoAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_TELEFONO);
		return ejb;
	}
	
	/**
	 * EmpresaAppService
	 * 
	 * @return EmpresaAppServiceRemote
	 * @throws ServiceLocatorException
	 */	
	private EmpresaAppServiceRemote getEmpresaRegistroAppService() throws ServiceLocatorException {
		EmpresaAppServiceRemote ejb = (EmpresaAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_EMPRESA);
		return ejb;
	}	
	
	/**
	 * CatalogoOpcionAppService
	 * 
	 * @return CandidatoRegistroAppServiceRemote
	 * @throws ServiceLocatorException
	 */
	private CatalogoOpcionAppServiceRemote getCatalogoOpcionAppService() throws ServiceLocatorException {
		CatalogoOpcionAppServiceRemote ejb = (CatalogoOpcionAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_CATALOGO_OPCION);
		return ejb;
	}

	@Override
	public Map<String, String> obtenerActividadEconomica(long idActEco) throws ServiceLocatorException {
		return getEmpresaRegistroAppService().obtenerActividadEconomica(idActEco);
	}

	public CurpVO consultaCURPPorDatosPersonales(CurpVO datosPersonales) throws ServiceLocatorException {
		try{
			return getEmpresaRegistroAppService().consultaCURPPorDatosPersonales(datosPersonales);
		} catch(Exception e){			
			throw new ServiceLocatorException(e);
		}
	}

	public boolean esCurpUnico(String curp) throws ServiceLocatorException{
		return getEmpresaRegistroAppService().esCurpUnico(curp);
	}

	public boolean esIdPortalEmpleoCurpUnico(String curp) throws ServiceLocatorException{
		return getEmpresaRegistroAppService().esIdPortalEmpleoCurpUnico(curp);
	}
	
	
	public CurpVO consultaDatosPersonalesPorCURP(CurpVO curpVo) throws ServiceLocatorException {
		return getEmpresaRegistroAppService().consultaDatosPersonalesPorCURP(curpVo);
	}

	public CurpVO consultaDatosPersonalesPorCURP(String CURP) throws ServiceLocatorException, ConsultaWsPorCurpException {
		
		return getEmpresaRegistroAppService().consultaDatosPersonalesPorCURP(CURP);		
	}

	@Override
	public boolean consultarPermisoGeolocalizacionRegistro()
			throws BusinessException, ServiceLocatorException {
		return getEmpresaRegistroAppService().consultarPermisoGeolocalizacionRegistro();
	}	
	
}
