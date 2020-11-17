package mx.gob.stps.portal.web.company.delegate;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

import java.sql.SQLException;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;


public interface RegContDelegate {
	

	//TELEFONOS
	public long registrarTelefono(TelefonoVO vo)throws BusinessException, SQLException, ServiceLocatorException; 
	
	
	//CONTACTOS
	//public RegistroContactoVO findById(long id) throws BusinessException, SQLException, ServiceLocatorException;
	
	//public List<RegistroContactoVO> findAllByIdEmpresa(long idEmpresa)throws BusinessException, SQLException, ServiceLocatorException; 
	
	//public long registrarContacto(RegistroContactoVO vo, long idUsuario) throws SQLException, ServiceLocatorException, BusinessException;
	
	//public void actualizaEstatus(long idTerceraEmpresa, int estatus) throws SQLException, ServiceLocatorException, BusinessException;
	
}
