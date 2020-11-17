package mx.gob.stps.portal.web.company.delegate;

import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

import java.sql.SQLException;
import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;


public interface RegisterBusDelegate {

	//EMPRESAS POR AUTORIZAR
	public long registrarEmpresa(EmpresaPorAutorizarVO vo) throws SQLException, ServiceLocatorException, BusinessException;
	public long registrarEmpresa(EmpresaPorAutorizarVO vo, long idUsuario) throws SQLException, ServiceLocatorException, BusinessException;

	public EmpresaPorAutorizarVO findEmpresaPorAutorizarById(long idEmpresa) throws BusinessException, ServiceLocatorException;
	public long actualizarEmpresaPorAutorizar(EmpresaPorAutorizarVO vo, long idUsuario, long idTipoUsuario, 
			boolean isChangedEmail, boolean isChangedBasicField, boolean isChangedIdPortal, String changedFields, long idRegValidar) throws BusinessException, MailException, TechnicalException, SQLException, ServiceLocatorException;
	
	//TELEFONOS
	public long registrarTelefono(TelefonoVO vo)throws BusinessException, SQLException, ServiceLocatorException; 
	public void eliminarTelefonos(long idPropietario, long idTipoPropietario, long principal)throws BusinessException, SQLException, ServiceLocatorException;
	public void eliminarTelefono(TelefonoVO vo)throws BusinessException, SQLException, ServiceLocatorException;	
	public List<TelefonoVO> consultarTelefonos(long idPropietario, long idTipoPropietario)throws BusinessException, SQLException, ServiceLocatorException;
	public void actualizarTelefono(TelefonoVO vo)throws BusinessException, SQLException, ServiceLocatorException;
	
	//EMPRESAS
	public EmpresaVO findEmpresaById(long id)throws BusinessException, SQLException, ServiceLocatorException; 
	
	public EmpresaVO findEmpresaByIdUsuario(long idUsuario)throws BusinessException, SQLException, ServiceLocatorException;
	
	public long actualizarEmpresa(EmpresaVO vo, long idUsuario, long idTipoUsuario, boolean isChangedEmail, 
			boolean isChangedBasicField, boolean isChangedIdPortal, String changedFields, long idRegValidar) 
	throws SQLException, ServiceLocatorException, BusinessException, MailException, TechnicalException;

	public EmpresaVO consultaEmpresaPorAutorizar(long idEmpresa) throws ServiceLocatorException;

	public void iniciaDepuracionCuentas() throws ServiceLocatorException;
	
	public void detieneDepuracionCuentas() throws ServiceLocatorException;

	public void iniciaDepuracionOfertas() throws ServiceLocatorException;
	
	public void detieneDepuracionOfertas() throws ServiceLocatorException;

	public void iniciaContadorOfertasPublicadas() throws ServiceLocatorException;

	public void detieneContadorOfertasPublicadas() throws ServiceLocatorException;

	public List<EmpresaVO> consultaEmpresas(int idTipoPersona, String correoElectronico, String idPortalEmpleo,
            String nombre, String apellido1, String razonSocial, String fechaActa,
            String apellido2, Long idEmpresa, String contacto, String telefono, String domicilio, long idEntidad, long idMunicipio,	String usuario) throws ServiceLocatorException;

	public void desactivarEmpresa(long idEmpresa, long idUsuarioEmpresa, long idUsuarioBitacora, int idMotivoDesactivacion, String detalleDesactivacion) throws ServiceLocatorException;
	
	public void reactivarEmpresa(long idEmpresa, long idUsuarioEmpresa, long idUsuarioBitacora) throws ServiceLocatorException;
	
	public EmpresaVO recuperaEmpresaEliminada(long idEmpresa) throws ServiceLocatorException;

	public EmpresaPorAutorizarVO recuperaEmpresaPorAutorizarEliminada(long idEmpresa) throws ServiceLocatorException;

	public List<BitacoraVO> consultaMovimientos(long idEmpresa) throws ServiceLocatorException;
}
