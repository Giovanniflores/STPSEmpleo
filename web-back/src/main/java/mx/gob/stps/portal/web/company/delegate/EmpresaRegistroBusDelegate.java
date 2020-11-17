package mx.gob.stps.portal.web.company.delegate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.empresa.vo.RegistroEmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.core.ws.renapo.exception.ConsultaWsPorCurpException;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.ws.renapo.vo.CurpVO;
//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public interface EmpresaRegistroBusDelegate {
	
	/**
	 * @param correoElectronico
	 * @return boolean
	 * @throws ServiceLocatorException
	 */		
	public boolean esCorreoUnico(String correoElectronico) throws ServiceLocatorException;
	
	/**
	 * @param usuario
	 * @return boolean
	 * @throws ServiceLocatorException
	 */		
	public boolean esUsuarioUnico(String usuario) throws ServiceLocatorException;
	
	/**
	 * @param correoElectronico
	 * @return boolean
	 * @throws ServiceLocatorException
	 */		
	public boolean tieneOfertas(String correoElectronico) throws ServiceLocatorException;
	
	/**
	 * @param registroEmpresaVO
	 * @return long
	 * @throws CorreoRepetidoException
	 * @throws BusinessException
	 * @throws TechnicalException	 
	 * @throws PersistenceException
	 * @throws ServiceLocatorException
	 */	
	public long registraEmpresa(RegistroEmpresaVO registroEmpresaVO) throws CorreoRepetidoException, 
    	BusinessException, TechnicalException, PersistenceException, ServiceLocatorException;
	
	/**
	 * @param registroEmpresaVO
	 * @return long
	 * @throws CorreoRepetidoException
	 * @throws BusinessException
	 * @throws TechnicalException	 
	 * @throws PersistenceException
	 * @throws ServiceLocatorException
	 */		
	public long regeneraEmpresa(RegistroEmpresaVO registroEmpresaVO) throws CorreoRepetidoException, 
	BusinessException, TechnicalException, PersistenceException, ServiceLocatorException;
	
	/**
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
	/* XXX Este metodo es candidato a desaparecer
	public long registraEmpresa(RegistroEmpresaVO registroEmpresaVO, long idUsuario) throws CorreoRepetidoException, BusinessException, ServiceLocatorException, 
		PersistenceException, TechnicalException, MailException;
	*/
	
	public EmpresaVO actualizaEmpresa(EmpresaVO empresaVO, long idUsuario, String changedFields, boolean isChangedPassword,
			boolean isChangedEmail, boolean isChangedIdPortal) throws ServiceLocatorException, TechnicalException;
	
	public EmpresaVO consultaEmpresa(long idEmpresa) throws ServiceLocatorException;
	
	public EmpresaVO findEmpresaByCP(String rfc, String email, String cp) throws ServiceLocatorException, BusinessException, SQLException;
	
	/**
	 * @param idCatalogo
	 * @return
	 * @throws ServiceLocatorException
	 */
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo) throws ServiceLocatorException;	
	
	/**
	 * @param correoElectronico
	 * @param usuario
	 * @param contrasena
	 */	
	public void notificaRegistroEmpresa(long idUsuario, long idEmpresa, int estatusEmpresa, String nombreEmpresa,
			String correoElectronico, String contrasena, String idPortalEmpleo, String detalle) throws ServiceLocatorException, MailException, BusinessException;
	
	public List<TelefonoVO> initTelefonos(long idEmpresa, long idTipoPropietario) throws BusinessException, ServiceLocatorException;
	
	Map<String, String> obtenerActividadEconomica(long idActEco) throws ServiceLocatorException;
	
	public CurpVO consultaCURPPorDatosPersonales(CurpVO datosPersonales) throws ServiceLocatorException;
	
	public boolean esCurpUnico(String curp) throws ServiceLocatorException;
	
	public boolean esIdPortalEmpleoCurpUnico(String curp) throws ServiceLocatorException;
	
	public CurpVO consultaDatosPersonalesPorCURP(CurpVO curpVo) throws ServiceLocatorException;
	
	public CurpVO consultaDatosPersonalesPorCURP(String CURP) throws ServiceLocatorException, ConsultaWsPorCurpException;
	
	public void notificacionRecuperacionPswEmpresa(long idEmpresa, String usuario, String nombreEmpresa, String correoElectronico, String clave) throws ServiceLocatorException, MailException;
	
	/**
	 * Consulta si esta habilidato el requerimiento de geolocalizacion
	 * @return true en caso de estar habilitado, false en caso contrario
	 * @throws BusinessException
	 */
	public boolean consultarPermisoGeolocalizacionRegistro()throws BusinessException, ServiceLocatorException; 
}
