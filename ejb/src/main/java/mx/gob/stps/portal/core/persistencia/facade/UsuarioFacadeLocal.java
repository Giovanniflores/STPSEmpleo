package mx.gob.stps.portal.core.persistencia.facade;

import java.sql.SQLException;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;

/**
 * Define metodo para la persistencia de Usuarios
 * 
 * @author oscar.manzo
 *
 */
@Local
public interface UsuarioFacadeLocal {

	/**
	 * Salva un registro de Usuario
	 * @param vo instancia de <mx.gob.stps.portal.core.seguridad.vo.UsuarioVO>
	 * @return identificadore de Usuario
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */
	public long save(UsuarioVO vo) throws PersistenceException;

	public void actualizaRegistroUsuario(UsuarioVO vo) throws PersistenceException;
	
	/**
	 * Consulta los datos de un Usuario
	 * @param idUsuario identificador del usuario
	 * @return instancia de <mx.gob.stps.portal.core.seguridad.vo.UsuarioVO>
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */
	public UsuarioVO find(long idUsuario) throws PersistenceException;
	
	/**
	 * Consulta los datos de un Usuario
	 * @param correoElectronico direccion de correo del Usuario
	 * @return instancia de <mx.gob.stps.portal.core.seguridad.vo.UsuarioVO>
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */
	public UsuarioVO findByUsuario(String usuario) throws PersistenceException;
	
	/**
	 * Consulta los datos de un Usuario
	 * @param correoElectronico direccion de correo del Usuario
	 * @return instancia de <mx.gob.stps.portal.core.seguridad.vo.UsuarioVO>
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */
	 
	 public UsuarioVO findByCandidatoId(String candidatoId) throws PersistenceException;
	
	/**
	 * Consulta los datos de un Usuario
	 * @param correoElectronico direccion de correo del Usuario
	 * @return instancia de <mx.gob.stps.portal.core.seguridad.vo.UsuarioVO>
	 * @throws PersistenceException Lanzada en caso de error durante el acceso a la capa de persistencia
	 */


	public UsuarioVO findbycorreo(String correoElectronico) throws PersistenceException;
	
	/** Activar Usuario
	 * @return
	 * @throws PersistenceException
	 */
	public Boolean activarUsuario(long idUsuario) throws PersistenceException;
	
	
	/**
	 * Actualiza password
	 * @param usuarioVO
	 * @throws PersistenceException
	 */
	
	 public void updatePassword(long idUsuario, String passw) throws PersistenceException;
	 
	 public void updateEmail(long idUsuario, String email) throws PersistenceException;

	/**
	 * @param idUsuario
	 * @return
	 * @throws PersistenceException
	 */
	 public void inactivarUsuario(long idUsuario) throws PersistenceException;
	 
	 public void inactivarUsuarioPorSolicitud(long idUsuario, int nuevoEstatus) throws PersistenceException;
		
	 public void updateSesionActiva(long idUsuario, int sesionActiva) throws PersistenceException;	

	 /**
	  * Coloca a Inactivas todas la sesiones activas de usuarios
	  * @throws PersistenceException
	  */
	 public void inactivaSesionesActivas() throws PersistenceException;

	 public void updatePasswordEstatus(long idUsuario, String passw, int estatus) throws PersistenceException;

	 public long consultaIdPropietario(long idUsuario) throws SQLException;

	 public boolean esCorreoUnico(String correo);
	 
	 public boolean esUsuarioUnico(String usuario);
}
