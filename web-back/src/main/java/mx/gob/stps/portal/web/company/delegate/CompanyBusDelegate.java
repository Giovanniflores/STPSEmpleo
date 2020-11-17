package mx.gob.stps.portal.web.company.delegate;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

public interface CompanyBusDelegate {

	/**
	 * Ejecuta el llamado para la actualizaci&oacute;n del logotipo de una empresa,
	 * a trav&eacute;s del usuario asociado a ella.
	 * @param usuario el usuario asociado a la empresa
	 * @param logo la imagen a almacenar
	 * @throws BusinessException si no se cumple con alguna regla de negocio
	 * @throws ServiceLocatorException si no se encuentra al objeto remoto
	 * @author jose.jimenez
	 */
	public void updateLogo(UsuarioWebVO usuario, byte[] logo) throws BusinessException, ServiceLocatorException;

	/** Actulizat Empresa
	 * @param confirmacionRegistroVo
	 * @return 
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public void activarEmpresa(long idEmpresa) throws BusinessException, ServiceLocatorException;

	public String obtenerCapacitacionMixta(long idEmpresa) throws ServiceLocatorException, BusinessException;
	
	public Long salvarCapacitacionMixta(String texto,Long idEmpresa) throws ServiceLocatorException, BusinessException;
	
	public Long updateCapacitacionMixta(String texto,Long idEmpresa) throws ServiceLocatorException, BusinessException;

	//Obtener Empresa OAM
	public EmpresaVO findEmpresaByIdUsuario(long idUsuario) throws ServiceLocatorException, BusinessException;

	//Notifiacion Recuperacion Empresa Contraseña OAM
	public void notificacionRecuperacionContrasena(long idPropietario, String usuario, TIPO_PROPIETARIO tipoPropietario,
			String nombrePropietario, String correoElectronico, String url) throws MailException, ServiceLocatorException, BusinessException;
}
