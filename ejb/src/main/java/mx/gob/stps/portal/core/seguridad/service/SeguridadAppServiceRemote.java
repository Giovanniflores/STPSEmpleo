package mx.gob.stps.portal.core.seguridad.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;
import mx.gob.stps.portal.core.seguridad.vo.AccionVO;
import mx.gob.stps.portal.core.seguridad.vo.ConfirmacionVO;
import mx.gob.stps.portal.core.seguridad.vo.PerfilVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

/**
 * Define los servicios para el modulo de Seguridad
 * 
 * @author oscar.manzo
 *
 */
@Remote
public interface SeguridadAppServiceRemote {

	/**
	 * Registra un usuario del sistema, los usuarios registrados son para la administracion
	 * por lo tanto se dan de alta como activos a diferencia de las empresas o candidatos  
	 * @param vo instancia de <mx.gob.stps.portal.core.seguridad.vo.UsuarioVO>
	 * @return identificador del usuario
	 * @throws LoginRepetidoException Lanzada en caso de ya existir un usuario con el mismo login
	 */
	public long registraUsuario(UsuarioVO vo) throws LoginRepetidoException, CorreoRepetidoException;
	
	/**
	 * Consulta un usuario a partir de su identificador
	 * @param idUsuario identificador de usuario
	 * @return instancia de <mx.gob.stps.portal.core.seguridad.vo.UsuarioVO>
	 */	
	public UsuarioVO consultaUsuario(long idUsuario);

	/**
	 * Consulta un usuario a partir de su correo electronico
	 * @param correoElectronico correo electronico del usuario
	 * @return instancia de <mx.gob.stps.portal.core.seguridad.vo.UsuarioVO>
	 */
	public UsuarioVO consultaUsuarioporCorreo(String correoElectronico);

	public UsuarioVO consultaUsuarioPorLogin(String usuario);
	
	public long consultaPropietario(long idUsuario) throws SQLException;
	
	public boolean esCorreoUnico(String correoElectronico) throws SQLException;

	public String consultaParametroPlantilla();

	public String recuperaContrasenaCandidato(String usuario, String curp, String correoActual, String correoNuevo) throws LoginRepetidoException, BusinessException, TechnicalException, MailException;

	public String recuperaContrasenaEmpresa(String usuario, String idPortalEmpleo, String correoActual, String correoNuevo) throws LoginRepetidoException, BusinessException, TechnicalException, SQLException, MailException;

	/**
	 * @param idCandidato
	 * @param correo
	 * @param contrasena
	 * @return
	 * @throws BusinessException
	 * @throws TechnicalException
	 * @throws PersistenceException
	 * @throws EncodingException
	 */
	public String confirmaContrasenaCandidato(long idCandidato, String correo, String contrasena) throws BusinessException, TechnicalException, PersistenceException, EncodingException;
	
	public String cambioContrasenaCandidato(long idCandidato,String contrasena) throws BusinessException, TechnicalException, PersistenceException, EncodingException;

	//GUARDA CAMBIO CONTRASEÑA OAM
    public String cambioContrasenaE(long idCandidato, String contrasena, EVENTO evento, int IdTipoPropietario) throws BusinessException, TechnicalException, PersistenceException, EncodingException;
	
	//GUARDA CAMBIO CONTRASEÑA OAM
	public String cambioContrasena(long idCandidato, String contrasena, EVENTO evento, int IdTipoPropietario) throws BusinessException, TechnicalException, PersistenceException, EncodingException;
	
	// SOLICITUD DE USUARIO Y CAMBIO CONTRASEÑA OAM
  public String bitacoraRecuperaContrasena(long idUsuario, EVENTO evento, long idCandidato, int IdTipoPropietario)
				throws BusinessException, TechnicalException, PersistenceException, EncodingException;

	/**
	 * @param idEmpresa
	 * @param correo
	 * @param contrasena
	 * @return
	 * @throws BusinessException
	 * @throws TechnicalException
	 * @throws PersistenceException
	 * @throws EncodingException
	 */
	public String confirmaContrasenaEmpresa(long idEmpresa, String correo, String contrasena) throws BusinessException, TechnicalException, PersistenceException, EncodingException;	
	
	public EmpresaVO consultaEmpresa(long idEmpresa) throws PersistenceException;

	public CandidatoVo consultaCandidato(long idCandidato) throws PersistenceException;
	public UsuarioVO consultaCandidatoPorID(String idCandidato2) throws PersistenceException;
	
	public long getIdCandidato(long idUsuario) throws PersistenceException;

	public void actualizaSesionActiva(long idUsuario, int sesionActiva) throws PersistenceException;

	public List<AccionVO> consultaAccionesRequierenAutenticacion() throws SQLException;
	
	public List<AccionVO> consultaAccionesPorPerfil(long idPerfil) throws SQLException;

	public List<AccionVO> consultaAccionesAsignadasPorPerfil(long idPerfil) throws SQLException;
	
	public List<PerfilVO> consultaPerfiles() throws SQLException;

	public void asignaAcciones(long idPerfil, long[] idsAcciones) throws SQLException;
	
	public void estableceAccionesReqUsuarioAutenticado(long[] idsAcciones) throws SQLException;

	public void inactivaSesionesActivas();
	
    /**
     * Genera un código de acceso para validación externa.
     * @param identificador del CIL
     * @return código válido bajo ciertos parámetros.
     */	
	public String generaCodigo(long idCIL);

	/**
	 * Retorna el identificador del CIL correspondiente o -1 en caso de que el código no sea válido o vigente.
	 * @param code Cadena de acceso que desea verificar
	 * @return identificador del CIL válido bajo ciertos parámetros.
	 */		
	public long isValidCode(String code);

	public ConfirmacionVO confirmacionDirectaEmpresa(String correoElectronico, Date fecha) throws BusinessException, TechnicalException;

	public ConfirmacionVO confirmacionDirectaEmpresa(String username, String razonSocial) throws BusinessException, TechnicalException;
	
	
	public ConfirmacionVO confirmacionDirectaCandidato(String correoElectronico, String CURP) throws BusinessException, TechnicalException;
	
	public ConfirmacionVO confirmacionReactivacionCandidato(String username, String CURP) throws BusinessException, TechnicalException;
	
	public void registraUltimoAccesoCandidato(long idCandidato) throws PersistenceException;
	
	
	//Start cambio movil
	
	/**
	 * Consultar si ya existe el cookie por este usuario
	 */
	public MovilSessionVO existeMovilSession(MovilSessionVO vo );
	
	/*
	 *  guardar el cookie
	 */
	public MovilSessionVO guardarMovilSession(MovilSessionVO vo);


	public MovilSessionVO actualizarMovilSessionVO(MovilSessionVO movilSession);
	
	public void deleteMovilSessionVO(MovilSessionVO vo);
	
	public EmpresaVO consultaEmpresaPorIdUsuario(long idUsuario) throws PersistenceException;

	public ConfirmacionVO confirmacionDirectaEmpresaActivo(String correoElectronico, Date fecha) throws BusinessException, TechnicalException;

	//Fin cambio movil

	public byte[] consultaImagen(int idUsuario, int idPerfil, int idPropietario);

	public byte[] agregaImagen(int idUsuario, int idPerfil, int idPropietario);
	
	public void eliminaImagen(int idUsuario);
}