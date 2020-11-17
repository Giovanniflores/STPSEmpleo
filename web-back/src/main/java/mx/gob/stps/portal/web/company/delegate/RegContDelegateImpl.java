package mx.gob.stps.portal.web.company.delegate;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.service.TelefonoAppServiceRemote;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;

import java.sql.SQLException;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;


public final class RegContDelegateImpl implements RegContDelegate {

	//private static Logger logger = Logger.getLogger(RegContDelegateImpl.class);
	
	private static RegContDelegate instance = new RegContDelegateImpl();
	
	public static RegContDelegate getInstance(){
		return instance;
	}

	//TELEFONO
	
	public long registrarTelefono(TelefonoVO vo)throws BusinessException, SQLException, ServiceLocatorException {
		return getTelefonoAppService().registrarTelefono(vo);
	}

	//TERCERAS EMPRESAS
	
	/*@Override
	public List<RegistroContactoVO> findAllByIdEmpresa(long idContacto) throws BusinessException, SQLException, ServiceLocatorException {
		return getContactoAppService().findAllByIdEmpresa(idContacto);
	}
		
	@Override
	public long registrarContacto(RegistroContactoVO vo, long idUsuario) throws SQLException, ServiceLocatorException, BusinessException {
		return getContactoAppService().registrarContacto(vo, idUsuario);
	}
	
	@Override
	public RegistroContactoVO findById(long id) throws BusinessException, SQLException, ServiceLocatorException {
		return getContactoAppService().findContactoById(id);
	}*/
	
	/*@Override
	public void actualizaEstatus(long idContacto, int estatus) throws SQLException, ServiceLocatorException, BusinessException {	
		getContactoAppService().actualizaEstatus(idContacto, estatus);
	}	
	
	private RegistroContactoAppServiceRemote getContactoAppService() throws ServiceLocatorException {
		RegistroContactoAppServiceRemote ejb = null;
		ejb = (RegistroContactoAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_REGISTRO_CONTACTO);
		return ejb;
	}*/

	private TelefonoAppServiceRemote getTelefonoAppService() throws ServiceLocatorException {
		TelefonoAppServiceRemote ejb = null;
		ejb = (TelefonoAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_TELEFONO);
		return ejb;
	}

}