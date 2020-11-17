package mx.gob.stps.portal.web.company.delegate;

import mx.gob.stps.portal.core.debbuger.service.ContadorOfertasAppServiceRemote;
import mx.gob.stps.portal.core.debbuger.service.DepuracionCuentasAppServiceRemote;
import mx.gob.stps.portal.core.debbuger.service.DepuracionOfertasAppServiceRemote;
import mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceRemote;
import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.service.TelefonoAppServiceRemote;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;

import java.sql.SQLException;
import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;


public final class RegisterBusDelegateImpl implements RegisterBusDelegate {
	
	private static RegisterBusDelegate instance = new RegisterBusDelegateImpl();
	
	public static RegisterBusDelegate getInstance(){
		return instance;
	}	

	public long registrarEmpresa(EmpresaPorAutorizarVO vo) throws BusinessException, SQLException, ServiceLocatorException {
		return getEmpresaAppService().registrarEmpresa(vo);
	}

	public long registrarEmpresa(EmpresaPorAutorizarVO vo, long idUsuario) throws BusinessException, SQLException, ServiceLocatorException {
		return getEmpresaAppService().registrarEmpresa(vo, idUsuario);
	}	

	public EmpresaVO consultaEmpresaPorAutorizar(long idEmpresa) throws ServiceLocatorException {
		return getEmpresaAppService().consultaEmpresaPorAutorizar(idEmpresa);
	}	
	
	public EmpresaPorAutorizarVO findEmpresaPorAutorizarById(long idEmpresa) throws BusinessException, ServiceLocatorException{
		return getEmpresaAppService().findEmpresaPorAutorizarById(idEmpresa);
	}
	
	public long actualizarEmpresaPorAutorizar(EmpresaPorAutorizarVO vo, long idUsuario, long idTipoUsuario, 
			                                  boolean isChangedEmail, boolean isChangedBasicField, boolean isChangedIdPortal, String changedFields, long idRegValidar) throws BusinessException, MailException, TechnicalException, SQLException, ServiceLocatorException{
		return getEmpresaAppService().actualizarEmpresaPorAutorizar(vo, idUsuario, idTipoUsuario, isChangedEmail, isChangedBasicField, isChangedIdPortal, changedFields, idRegValidar);
	}	

	public long registrarTelefono(TelefonoVO vo)throws BusinessException, SQLException, ServiceLocatorException {
		return getTelefonoAppService().registrarTelefono(vo);
	}
	
	public void eliminarTelefono(TelefonoVO vo) throws BusinessException, SQLException, ServiceLocatorException {
		getTelefonoAppService().eliminarTelefono(vo);
	}	
	
	public void eliminarTelefonos(long idPropietario, long idTipoPropietario, long principal)throws BusinessException, SQLException, ServiceLocatorException {
		 getTelefonoAppService().eliminarTelefonos(idPropietario,idTipoPropietario,principal);
	}
	
	public List<TelefonoVO> consultarTelefonos(long idPropietario, long idTipoPropietario)throws BusinessException, SQLException, ServiceLocatorException{
		return getTelefonoAppService().consultarTelefonos(idPropietario, idTipoPropietario);
	}
	
	@Override
	public void actualizarTelefono(TelefonoVO vo) throws BusinessException, SQLException, ServiceLocatorException {
		getTelefonoAppService().actualizarTelefono(vo);
	}	
	
	/*public RegistroContactoVO findContactoById(long idContacto) throws BusinessException, SQLException, ServiceLocatorException {
		return getRegistroContactoAppService().findContactoById(idContacto);
	}

	public List<RegistroContactoVO> findAllContactsByIdEmpresa(long idEmpresa) throws BusinessException, SQLException, ServiceLocatorException {
		return getRegistroContactoAppService().findAllByIdEmpresa(idEmpresa);
	}*/

	/*public void actualizaEstatusContacto(long idContacto, int estatus) throws SQLException, ServiceLocatorException, BusinessException {
		getRegistroContactoAppService().actualizaEstatus(idContacto, estatus);
	}

	public void actualizaEstatusContacto(long idContacto, int estatus, long idUsuario) throws SQLException, ServiceLocatorException, BusinessException {
		getRegistroContactoAppService().actualizaEstatus(idContacto, estatus, idUsuario);
	}
	
	public int countOffersByIdContact(long idContacto, int estatus) throws SQLException, ServiceLocatorException, BusinessException {	
		return getRegistroContactoAppService().countOffersByIdContact(idContacto, estatus);
	}*/
	
	/*public long registrarContacto(RegistroContactoVO vo, long idUsuario) throws ServiceLocatorException {
		return getRegistroContactoAppService().registrarContacto(vo, idUsuario);
	}	
	
	public long actualizarContacto(RegistroContactoVO vo, long idUsuario) throws ServiceLocatorException {
		return getRegistroContactoAppService().actualizarContacto(vo, idUsuario);
	}*/
	

	public EmpresaVO findEmpresaById(long id) throws BusinessException, SQLException, ServiceLocatorException {		
		return getEmpresaAppService().findEmpresaById(id);
	}
		
	public EmpresaVO findEmpresaByIdUsuario(long idUsuario) throws BusinessException,
		SQLException, ServiceLocatorException {		
	return getEmpresaAppService().findEmpresaByIdUsuario(idUsuario);
	}
	
	@Override
	public long actualizarEmpresa(EmpresaVO vo, long idUsuario, long idTipoUsuario, boolean isChangedEmail,
			boolean isChangedBasicField, boolean isChangedIdPortal, String changedFields, long idRegValidar)
			throws SQLException, ServiceLocatorException, BusinessException, MailException, TechnicalException {
		return getEmpresaAppService().actualizarEmpresa(vo, idUsuario, idTipoUsuario, isChangedEmail, isChangedBasicField, isChangedIdPortal, changedFields, idRegValidar);
	}
	
	/*public long actualizaEmpresa(TerceraEmpresaVO vo, long idUsuario) throws SQLException, ServiceLocatorException, BusinessException{
		return getTerceraEmpresaAppService().actualizarEmpresa(vo, idUsuario);
	}*/

	/*@Override
	public List<TerceraEmpresaVO> findAllByIdEmpresa(long idEmpresa)throws BusinessException, SQLException, ServiceLocatorException {
		return getTerceraEmpresaAppService().findAllByIdEmpresa(idEmpresa);
	}*/
		
	/*@Override
	public long registrarEmpresa(TerceraEmpresaVO vo, long idUsuario) throws SQLException, ServiceLocatorException, BusinessException {
		return getTerceraEmpresaAppService().registrarEmpresa(vo, idUsuario);
	}*/		
	
	/*@Override
	public TerceraEmpresaVO findById(long id) throws BusinessException, SQLException, ServiceLocatorException {
		return getTerceraEmpresaAppService().findEmpresaById(id);
	}*/	
	
	/*@Override
	public void actualizaEstatus(long idTerceraEmpresa, int estatus) throws SQLException, ServiceLocatorException, BusinessException {	
		getTerceraEmpresaAppService().actualizaEstatus(idTerceraEmpresa, estatus);
	}*/

	/*public void actualizaEstatus(long idTerceraEmpresa, int estatus, long idUsuario) throws SQLException, ServiceLocatorException, BusinessException {	
		getTerceraEmpresaAppService().actualizaEstatus(idTerceraEmpresa, estatus, idUsuario);
	}*/	
	
	
	/*@Override
	public int countOffersByIdTerceraEmpresa(long idTerceraEmpresa, int estatus) throws SQLException, ServiceLocatorException, BusinessException {
		return getTerceraEmpresaAppService().countOffersByIdTerceraEmpresa(idTerceraEmpresa, estatus);
	}*/

	/*private AutorizacionAppServiceRemote getAutorizacionAppService() throws ServiceLocatorException {
		AutorizacionAppServiceRemote ejb = null;
		ejb = (AutorizacionAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_AUTORIZACION);
		return ejb;
	}*/
	
	private EmpresaAppServiceRemote getEmpresaAppService() throws ServiceLocatorException {
		EmpresaAppServiceRemote ejb = null;
		ejb = (EmpresaAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_EMPRESA);
		return ejb;
	}		

	private TelefonoAppServiceRemote getTelefonoAppService() throws ServiceLocatorException {
		TelefonoAppServiceRemote ejb = null;
		ejb = (TelefonoAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_TELEFONO);
		return ejb;
	}
	
	/*private RegistroContactoAppServiceRemote getRegistroContactoAppService()throws ServiceLocatorException {
		RegistroContactoAppServiceRemote ejb = null;
		ejb = (RegistroContactoAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_REGISTRO_CONTACTO);
		return ejb;
	}*/
	

	/*private TerceraEmpresaAppServiceRemote getTerceraEmpresaAppService() throws ServiceLocatorException {
		TerceraEmpresaAppServiceRemote ejb = null;
		ejb = (TerceraEmpresaAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_TERCERA_EMPRESA);
		return ejb;
	}*/

	public void iniciaDepuracionCuentas() throws ServiceLocatorException {
		getDepuracionCuentasAppService().initTimer();
	}

	public void detieneDepuracionCuentas() throws ServiceLocatorException {
		getDepuracionCuentasAppService().destroyInitTimer();
	}

	private DepuracionCuentasAppServiceRemote getDepuracionCuentasAppService() throws ServiceLocatorException {
		DepuracionCuentasAppServiceRemote ejb = null;
		ejb = (DepuracionCuentasAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_DEPURACION_CUENTAS);
		return ejb;
	}

	public void iniciaDepuracionOfertas() throws ServiceLocatorException {
		getDepuracionOfertasAppService().initTimer();
	}

	public void detieneDepuracionOfertas() throws ServiceLocatorException {
		getDepuracionOfertasAppService().destroyInitTimer();
	}
	
	public void iniciaContadorOfertasPublicadas() throws ServiceLocatorException {
		getContadorOfertasAppService().iniciaProcesoRecurrente();
	}

	public void detieneContadorOfertasPublicadas() throws ServiceLocatorException {
		getContadorOfertasAppService().detieneProcesoRecurrente();
	}

	public List<EmpresaVO> consultaEmpresas(int idTipoPersona, String correoElectronico, String idPortalEmpleo,
                                            String nombre, String apellido1, String razonSocial, String fechaActa,
                                            String apellido2, Long idEmpresa, String contacto, String telefono, String domicilio, long idEntidad, long idMunicipio,	String usuario) throws ServiceLocatorException {
		return getEmpresaAppService().consultaEmpresas(idTipoPersona, correoElectronico, idPortalEmpleo, nombre, apellido1, razonSocial, fechaActa,				
				apellido2, idEmpresa, contacto, telefono, domicilio, idEntidad, idMunicipio, usuario);
	}

	public void desactivarEmpresa(long idEmpresa, long idUsuarioEmpresa, long idUsuarioBitacora, int idMotivoDesactivacion, String detalleDesactivacion) throws ServiceLocatorException{
		getEmpresaAppService().desactivarEmpresa(idEmpresa, idUsuarioEmpresa, idUsuarioBitacora, idMotivoDesactivacion, detalleDesactivacion);
	}
	
	public void reactivarEmpresa(long idEmpresa, long idUsuarioEmpresa, long idUsuarioBitacora) throws ServiceLocatorException{
		getEmpresaAppService().reactivarEmpresa(idEmpresa, idUsuarioEmpresa, idUsuarioBitacora);
	}
	
	public EmpresaVO recuperaEmpresaEliminada(long idEmpresa) throws ServiceLocatorException {
		return getEmpresaAppService().recuperaEmpresaEliminada(idEmpresa);
	}

	public EmpresaPorAutorizarVO recuperaEmpresaPorAutorizarEliminada(long idEmpresa) throws ServiceLocatorException {
		return getEmpresaAppService().recuperaEmpresaPorAutorizarEliminada(idEmpresa);
	}
	
	public List<BitacoraVO> consultaMovimientos(long idEmpresa) throws ServiceLocatorException {
		return getEmpresaAppService().consultaMovimientos(idEmpresa);
	}
	
	private DepuracionOfertasAppServiceRemote getDepuracionOfertasAppService() throws ServiceLocatorException {
		DepuracionOfertasAppServiceRemote ejb = null;
		ejb = (DepuracionOfertasAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_DEPURACION_OFERTAS);
		return ejb;
	}

	private ContadorOfertasAppServiceRemote getContadorOfertasAppService() throws ServiceLocatorException {
		ContadorOfertasAppServiceRemote ejb = null;
		ejb = (ContadorOfertasAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_CONTADOR_OFERTAS);
		return ejb;
	}

}
