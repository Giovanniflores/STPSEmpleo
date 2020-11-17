package mx.gob.stps.portal.core.empresa.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.empresa.vo.RegistroEmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.core.oferta.registro.vo.EventoVO;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.ws.renapo.vo.CurpVO;

/**
 * Proporciona el acceso a los servicios para el modulo de Empresas
 * 
 * @author haydee.vertti
 *
 */
@Remote
public interface EmpresaAppServiceRemote {

	public EmpresaVO consultaEmpresa(long idEmpresa) throws PersistenceException;

	/**
	 * Registra una empresa por autorizar
	 * @param vo
	 * @return long
	 */	
	//XXX metodo candidato a eliminarse
	public long registrarEmpresa(EmpresaPorAutorizarVO vo);	
	
	
	/**
	 * Registra una empresa por autorizar
	 * @param vo
	 * @param long idUsuario
	 * @return long
	 */
	//XXX metodo candidato a eliminarse
	public long registrarEmpresa(EmpresaPorAutorizarVO vo, long idUsuario);		
	
	/**
	 * BUsca una empresa en base a su identificador
	 * @param vo
	 * @return EmpresaVO
	 */		
	public EmpresaVO findEmpresaById(long id);
	
	/**
	 * Method 'findEmpresaByIdUsuario'
	 * Regresa un objeto EmpresaVO con los datos correspondientes a
	 * la empresa cuyo identificador de usuario se proporciona
	 * 
	 * @param idUsuario
	 * @return EmpresaVO
	 * @throws BusinessException 
	 */		
	public EmpresaVO findEmpresaByIdUsuario(long idUsuario) throws BusinessException;
	
	/**
	 * Method 'findEmpresaByCP'
	 * Regresa un objeto EmpresaVO con los datos correspondientes a
	 * la empresa cuyo RFC de empresa se proporciona
	 * 
	 * @param RFC
	 * @param CodigoPostal
	 * @return EmpresaVO
	 * @throws BusinessException 
	 * @throws SQLException 
	 */		
	public EmpresaVO findEmpresaByCP(String rfc, String email, String cp) throws BusinessException, SQLException;
	
	/**
	 * Actualiza los datos de una empresa
	 * @param vo EmpresaVO
	 * @param idUsuario long
	 * @param idTipoUsuario long
	 * @param isChangedEmail boolean
	 * @param isChangedBasicField boolean
	 * @param isChangedIdPortal boolean
	 * @param changedFields String
	 * @return long
	 */		
	public long actualizarEmpresa(EmpresaVO vo, long idUsuario, long idTipoUsuario, 
			boolean isChangedEmail, boolean isChangedBasicField, boolean isChangedIdPortal, String changedFields, long idRegValidar) 
		throws BusinessException, MailException, TechnicalException, SQLException;
	
	public EmpresaVO actualizarEmpresa(EmpresaVO empresaVO, long idUsuario, String changedFields, boolean isChangedPassword,
			boolean isChangedEmail, boolean isChangedIdPortal)throws TechnicalException;	
	
	/**
	 * Actualiza el logotipo de la empresa identificada por el usuario asociado
	 * @param idUsuario el identificador del usuario asociado a la empresa
	 * @param idPerfil el identificador del perfil del usuario
	 * @param logotipo la imagen que representa el logotipo a almacenar
	 * @throws PersistenceException en caso de que el registro de la empresa no exista
	 * @throws BusinessException en caso de que la empresa no tenga estatus de &quot;Activa&quot;
	 */
	public void actualizaLogotipo(long idEmpresa, long idPerfil, byte[] logotipo) throws PersistenceException, BusinessException;
	

	/** Actulizando Empresas
	 * @throws PersistenceException
	 * @throws BusinessException
	 */
	public void actualizarEmpresaAutorizar(long idEmpresa)  throws PersistenceException, BusinessException;
	
	public EmpresaVO consultaEmpresaPorAutorizar(long idEmpresa);	
	
	public EmpresaPorAutorizarVO findEmpresaPorAutorizarById(long id);
	
	public long actualizarEmpresaPorAutorizar(EmpresaPorAutorizarVO vo, long idUsuario, long idTipoUsuario, 
			boolean isChangedEmail, boolean isChangedBasicField, boolean isChangedIdPortal, String changedFields, long idRegValidar) 
			throws BusinessException, MailException, TechnicalException, SQLException;	

	public List<EmpresaVO> consultaEmpresas(int idTipoPersona, String correoElectronico, String idPortalEmpleo,
                                            String nombre, String apellido1, String razonSocial, String fechaActa,
                                            String apellido2, Long idEmpresa, String contacto, String telefono, String domicilio, long idEntidad, long idMunicipio,	String usuario                                
                                            );

	public void desactivarEmpresa(long idEmpresa, long idUsuarioEmpresa, long idUsuarioBitacora, int idMotivoDesactivacion, String detalleDesactivacion);
	
	public void reactivarEmpresa(long idEmpresa, long idUsuarioEmpresa, long idUsuarioBitacora);
	
	public EmpresaVO recuperaEmpresaEliminada(long idEmpresa);

	public EmpresaPorAutorizarVO recuperaEmpresaPorAutorizarEliminada(long idEmpresa);

	public List<BitacoraVO> consultaMovimientos(long idEmpresa);

	public String obtenerCapacitacionMixta(long idEmpresa) throws BusinessException;
	
	public Long salvarCapacitacionMixta(String texto,Long idEmpresa) throws BusinessException;
	
	public Long updateCapacitacionMixta(String texto,Long idEmpresa) throws BusinessException;
	
	public boolean esCorreoUnico(String correoElectronico);
	
	public boolean esUsuarioUnico(String usuario);
	
	public boolean tieneOfertas(String correoElectronico);
	
	public long registraEmpresa(RegistroEmpresaVO registroEmpresaVO) throws CorreoRepetidoException, TechnicalException;
	
	//XXX Este metodo es candidato a desaparecer
	//public long registraEmpresa(RegistroEmpresaVO registroEmpresaVO, long idUsuarioRegistra) throws CorreoRepetidoException, TechnicalException;
	
	public long regeneraEmpresa(RegistroEmpresaVO registroEmpresaVO) throws CorreoRepetidoException, TechnicalException;
	
	//XXX Este metodo es candidato a desaparecer
	//public long regeneraEmpresa(RegistroEmpresaVO registroEmpresaVO, long idUsuarioRegistra) throws CorreoRepetidoException, TechnicalException;
	
	public void notificaEmpresaCreada(long idUsuario, long idEmpresa, int estatusEmpresa, String nombreEmpresa, 
			String correoEmpresa, String contrasena, String idPortalEmpleo, String detalle) throws BusinessException, MailException;
	
	public void notificacionRecuperacionPswEmpresa(long idEmpresa,	String usuario, String nombreEmpresa, String correoElectronico, String clave) throws MailException;
	
	//Notificacion Recupera Contraseña OAM
	public void notificacionRecuperacionContrasena(long idPropietario, String usuario, TIPO_PROPIETARIO tipoPropietario, String nombrePropietario,
			String correoElectronico, String url) throws MailException;
	
	public void notificacionRecuperacionPswMovilEmpresa(long idEmpresa,	String nombreEmpresa, String correoElectronico, String clave, String usuario) throws MailException;

	public void insertarEventoBitacora(EventoVO evento) throws  BusinessException;
	
	public void insertarEventoBitacora(EVENTO evento, long idUsuario, TIPO_REGISTRO tipoRegistro, long idRegistro, int estatusAnterior) throws  BusinessException;

	public List<CandidatoVo> buscadorCandidatosQuery(List<Long> indices) throws TechnicalException, SQLException;
	
	Map<String, String> obtenerActividadEconomica(long idActEco);
	
	public CurpVO consultaCURPPorDatosPersonales(CurpVO datosPersonales);
	
	public boolean esCurpUnico(String curp);
	
	public boolean esIdPortalEmpleoCurpUnico(String curp);
	
	public CurpVO consultaDatosPersonalesPorCURP(CurpVO curpVo);
	
	public CurpVO consultaDatosPersonalesPorCURP(String CURP);
	
	/**
	 * Consulta si esta habilidato el requerimiento de geolocalizacion
	 * @return true en caso de estar habilitado, false en caso contrario
	 * @throws BusinessException
	 */
	public boolean consultarPermisoGeolocalizacionRegistro()throws BusinessException; 
		
}
