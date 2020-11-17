package mx.gob.stps.portal.core.persistencia.facade;

import static mx.gob.stps.portal.core.infra.utils.Constantes.SESION_ACTIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.SESION_INACTIVA;

import java.sql.SQLException;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.Usuario;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;

import org.apache.log4j.Logger;

/**
 * Concentra los accesos a la persistencia de Usuario
 * 
 * @author oscar.manzo
 *
 */
@Stateless
public class UsuarioFacade implements UsuarioFacadeLocal {

	private static Logger logger = Logger.getLogger(UsuarioFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal#save(mx.gob.stps.portal.core.seguridad.vo.UsuarioVO)
	 */
	public long save(UsuarioVO vo) throws PersistenceException {
		long idUsuario = 0;
		
		Usuario entity = getUsuario(vo);

		try{
			entityManager.persist(entity);
			entityManager.flush();
			
			idUsuario = entity.getIdUsuario();			

		}catch(Exception e){
			throw new PersistenceException(e);
		}
		
		return idUsuario;
	}

	/**
	 * Actualiza los datos basicos de un candidato, utilizado desde el registro de un candidato
	 */
	public void actualizaRegistroUsuario(UsuarioVO vo) throws PersistenceException {
		try{
			long idUsuario = vo.getIdUsuario();
			Usuario entity = entityManager.find(Usuario.class, idUsuario);

			entity.setUsuario(vo.getUsuario());
			entity.setContrasena(vo.getContrasena());
			//entity.setNombre(vo.getNombre());
			//entity.setApellido1(vo.getApellido1());
			//entity.setApellido2(vo.getApellido2());
			entity.setCorreoElectronico(vo.getCorreoElectronico());
			entity.setEstatus(vo.getEstatus());
			entity.setFechaModificacion(vo.getFechaModificacion());
			//entity.setIdRegistro(vo.getIdRegistro());
			//entity.setIdTipoUsuario(vo.getIdTipoUsuario());
			//entity.setIdEntidad(vo.getIdEntidad());
			//entity.setIdPerfil(vo.getIdPerfil());
			//entity.setFechaAlta(vo.getFechaAlta());
			//entity.setSesionActiva(vo.getSesionActiva());

			entityManager.merge(entity);

		}catch(Exception e){
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException(e);
		}
	}
	
	public UsuarioVO findByUsuario(String usuario) throws PersistenceException {
		UsuarioVO vo = null;
		
		try{
			Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE LOWER(u.usuario) = LOWER(:usuario) ");
			query.setParameter("usuario", usuario);
			
			Usuario entity = (Usuario)query.getSingleResult();
			entityManager.refresh(entity);
			
			vo = getUsuarioVO(entity);
			
		} catch (NoResultException re) {
			//logger.error("Usuario no localizado mediante el Login : "+ usuario);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		
		return vo;
	}
	
	
	public UsuarioVO findByCandidatoId(String candidatoId) throws PersistenceException {
		UsuarioVO vo = null;
		
		try{
			Query query = entityManager.createQuery("SELECT u FROM Usuario u, Candidato c WHERE u.idUsuario = c.idUsuario AND c.idCandidato = :id_candidato ");
			query.setParameter("id_candidato", Long.parseLong(candidatoId));
			
			Usuario entity = (Usuario)query.getSingleResult();
			entityManager.refresh(entity);
			
			vo = getUsuarioVO(entity);
			
		} catch (NoResultException re) {
			logger.error("Usuario no localizado con candidatoId : "+ candidatoId);
		} catch (RuntimeException re) {
			logger.error("RUNTIME EXCEPTION",re);
			re.printStackTrace();
			throw new PersistenceException(re);
		} catch (Exception e) {
			logger.error("EXCEPTION", e);
			e.printStackTrace();
		}
		
		return vo;
	}
	
	
	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal#find(long)
	 */
	public UsuarioVO find(long idUsuario) throws PersistenceException {
		UsuarioVO vo = null;
		
		try{
			Usuario entity = entityManager.find(Usuario.class, idUsuario);
			
			vo = getUsuarioVO(entity);

		} catch (NoResultException re) {
			logger.error("Usuario no localizado, idUsuario : "+ idUsuario);
		}catch(Exception e){
			throw new PersistenceException(e);
		}
		
		return vo;
	}

	/* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal#find(java.lang.String)
	 */
	public UsuarioVO findbycorreo(String correoElectronico) throws PersistenceException {
		UsuarioVO vo = null;
		
		try{
			Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE LOWER(u.correoElectronico) = LOWER(:correo)");
			query.setParameter("correo", correoElectronico);
			
			Usuario entity = (Usuario)query.getSingleResult();
			entityManager.refresh(entity);
			
			vo = getUsuarioVO(entity);
			
		} catch (NoResultException re) {
			logger.error("Usuario no localizado mediante el correo : "+ correoElectronico);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		
		return vo;
	}
		
	private Usuario findUsuarioEntity(long idUsuario) throws PersistenceException {
		Usuario entity = null;

		try{
			 entity = entityManager.find(Usuario.class, idUsuario);
		} catch (NoResultException re) {
			logger.error("Usuario no localizado mediante el idUsuario : "+ idUsuario);
		}catch(Exception e){
			throw new PersistenceException(e);
		}		
		return entity;
	}
	
	/**
	 * Genera un UsuarioVO a partir de un Usuario 
	 * @param entity
	 * @return
	 */
	private UsuarioVO getUsuarioVO(Usuario entity){
		UsuarioVO vo = new UsuarioVO();
				
		vo.setIdUsuario(entity.getIdUsuario());
		vo.setCorreoElectronico(entity.getCorreoElectronico());
		vo.setIdTipoUsuario(entity.getIdTipoUsuario()!=null?entity.getIdTipoUsuario().longValue():0);
		vo.setContrasena(entity.getContrasena());
		vo.setIdRegistro(entity.getIdRegistro()!=null?entity.getIdRegistro():0);
		vo.setEstatus(entity.getEstatus());
		vo.setFechaModificacion(entity.getFechaModificacion());
		vo.setIdEntidad(entity.getIdEntidad()!=null?entity.getIdEntidad():0);
		vo.setIdPerfil(entity.getIdPerfil()!=null?entity.getIdPerfil():0);
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setNombre(entity.getNombre());
		vo.setApellido1(entity.getApellido1());
		vo.setApellido2(entity.getApellido2());
		vo.setSesionActiva(entity.getSesionActiva()!=null?entity.getSesionActiva():0);
		vo.setUsuario(entity.getUsuario());
		return vo;
	}
	
	/**
	 * Genera un Usuario a partir de un UsuarioVo
	 * @param vo
	 * @return
	 */
	private Usuario getUsuario(UsuarioVO vo){
		Usuario entity = new Usuario();
				
		//entity.setIdUsuario(vo.getIdUsuario());
		entity.setCorreoElectronico(vo.getCorreoElectronico());
		entity.setIdTipoUsuario(vo.getIdTipoUsuario());
		entity.setContrasena(vo.getContrasena());
		entity.setIdRegistro(vo.getIdRegistro());
		entity.setEstatus(vo.getEstatus());
		entity.setFechaModificacion(vo.getFechaModificacion());
		entity.setIdEntidad(vo.getIdEntidad());
		entity.setIdPerfil(vo.getIdPerfil());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setNombre(vo.getNombre());
		entity.setApellido1(vo.getApellido1());
		entity.setApellido2(vo.getApellido2());
		entity.setSesionActiva(vo.getSesionActiva());
		entity.setUsuario(vo.getUsuario());
		return entity;
	}

	@Override
	public Boolean activarUsuario(long idUsuario) throws PersistenceException {
		Boolean bandera = false;
		Usuario usuario = findUsuarioEntity(idUsuario);

		if(usuario != null){
			usuario.setEstatus(ESTATUS.ACTIVO.getIdOpcion());
			usuario.setFechaModificacion(new Date());
			entityManager.merge(usuario);
			entityManager.flush();
			bandera = true;
		}else{
			throw new PersistenceException("No se encontro el Usuario");
		}		
		return bandera;
	}
	
	
	
	@Override
	public void inactivarUsuario(long idUsuario) throws PersistenceException {

		Usuario usuario = findUsuarioEntity(idUsuario);

		if(usuario != null && idUsuario == usuario.getIdUsuario()){
			usuario.setEstatus(ESTATUS.INACTIVO.getIdOpcion());
			usuario.setFechaModificacion(new Date());
			entityManager.merge(usuario);
			entityManager.flush();
		}else{
			throw new PersistenceException("No se encontro el Usuario");
		}		
	}
	
	public void inactivarUsuarioPorSolicitud(long idUsuario, int nuevoEstatus) throws PersistenceException {
		Usuario usuario = findUsuarioEntity(idUsuario);
		if(usuario != null && idUsuario == usuario.getIdUsuario()){
			usuario.setEstatus(nuevoEstatus);
			if(nuevoEstatus == ESTATUS.INACTIVO_A_PETICION_DEL_CANDIDATO.getIdOpcion()){
				usuario.setFechaModificacion(new Date());
			}			
			entityManager.merge(usuario);
			entityManager.flush();
		}else{
			throw new PersistenceException("No se encontro el Usuario");
		}		
	}		

	public void updateEmail(long idUsuario, String email)throws PersistenceException {
		try{
			Usuario usuario = findUsuarioEntity(idUsuario);			
			
			if(usuario != null && idUsuario == usuario.getIdUsuario() &&  email != null && !email.trim().isEmpty()){
				usuario.setCorreoElectronico(email);
				usuario.setFechaModificacion(new Date());
				entityManager.merge(usuario);
				entityManager.flush();
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}
	
	
	@Override
	public void updatePassword(long idUsuario, String passw) throws PersistenceException {
		try{
			Usuario usuario = findUsuarioEntity(idUsuario);			
			if(usuario != null && idUsuario == usuario.getIdUsuario() &&  passw != null && !passw.trim().isEmpty()){
				usuario.setContrasena(passw);						
				usuario.setFechaModificacion(new Date());
				entityManager.merge(usuario);
				entityManager.flush();
			}
						
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}

	public void updatePasswordEstatus(long idUsuario, String passw, int estatus) throws PersistenceException {
		try{
			Usuario entity = entityManager.find(Usuario.class, idUsuario);			
			
			entity.setContrasena(passw);
			entity.setFechaModificacion(new Date());
			entity.setEstatus(estatus);
			entityManager.merge(entity);
			entityManager.flush();

		} catch (NoResultException re) {
			logger.error("Usuario no localizado mediante el idUsuario : "+ idUsuario);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}
	
	public void updateSesionActiva(long idUsuario, int sesionActiva) throws PersistenceException {
		try {
			Usuario instance = entityManager.find(Usuario.class, idUsuario);
			instance.setSesionActiva(sesionActiva);
			entityManager.flush();
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}

	/**
	 * Coloca a Inactivas todas la sesiones activas de usuarios
	 * @throws PersistenceException
	 */
	public void inactivaSesionesActivas() throws PersistenceException {
		try{
	        String update = "UPDATE Usuario AS u "+
	                           "SET u.sesionActiva = "+ SESION_INACTIVA +" "+
	                         "WHERE u.sesionActiva = :sesionActivaActual";

			Query query = entityManager.createQuery(update);
			//query.setParameter("sesionActiva", SESION_INACTIVA);
			query.setParameter("sesionActivaActual", SESION_ACTIVA);

			query.executeUpdate();
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}

	public long consultaIdPropietario(long idUsuario) throws SQLException{
		StringBuilder select = new StringBuilder();
		select.append("SELECT DECODE(ID_TIPO_USUARIO, ");
		select.append(" ?1, (SELECT ID_EMPRESA FROM EMPRESA WHERE ID_USUARIO = USU.ID_USUARIO), ");
		select.append(" ?2, (SELECT ID_CANDIDATO FROM CANDIDATO WHERE ID_USUARIO = USU.ID_USUARIO), ");
		select.append("ID_USUARIO) AS ID_PROPIETARIO ");
		select.append(" FROM USUARIO USU ");
		select.append("WHERE USU.ID_USUARIO = ?3 ");		

		Query query = entityManager.createNativeQuery(select.toString());
		query.setParameter(1, TIPO_USUARIO.EMPRESA.getIdTipoUsuario());
		query.setParameter(2, TIPO_USUARIO.CANDIDATO.getIdTipoUsuario());
		query.setParameter(3, idUsuario);
		
		long idPropietario = Utils.toLong(query.getSingleResult());

		return idPropietario;
	}
	
	/**
	 * TODO para reemplazar el UsuarioDAO, este metodo aun no se encuentra en la interfase,
	 * es la consulta que se encuentra en el DAO para poder cambiarla y eliminar UsuarioDAO
	 */
	public boolean esCorreoUnico(String correo) {
		boolean existe = false;
		try {
			StringBuilder select = new StringBuilder();
			select.append("SELECT CORREO_ELECTRONICO FROM USUARIO 	WHERE LOWER(CORREO_ELECTRONICO)  = LOWER(?1) ");
			select.append("	UNION ");
			select.append("SELECT CORREO_ELECTRONICO FROM CANDIDATO WHERE LOWER(CORREO_ELECTRONICO)  = LOWER(?2) ");
			select.append("	UNION ");
			select.append("SELECT CORREO_ELECTRONICO FROM EMPRESA 	WHERE LOWER(CORREO_ELECTRONICO)  = LOWER(?3) ");
			select.append("	UNION ");
			select.append("SELECT CORREO_ELECTRONICO FROM EMPRESA_POR_AUTORIZAR WHERE LOWER(CORREO_ELECTRONICO)  = LOWER(?4)	");		
			//Cambio para el incidencia 7749 debe estar unico el correo tanto como correo que como usuario
			//select.append(" UNION ");
			//select.append("SELECT USUARIO FROM USUARIO 	WHERE LOWER(USUARIO)  = LOWER(?5) ");
			Query query = entityManager.createNativeQuery(select.toString());
			query.setParameter(1, correo);
			query.setParameter(2, correo);
			query.setParameter(3, correo);
			query.setParameter(4, correo);
			query.setParameter(5, correo);

			String correoElectronico = Utils.toString(query.getSingleResult());

			existe = correoElectronico!=null && !correoElectronico.trim().isEmpty();
	
		} catch (NoResultException re) {
			logger.error("Correo electronico no ubicado, correo : "+ correo);
		}
		
		return !existe;
	}

	public boolean esUsuarioUnico(String usuario) {
		boolean existe = false;

		try{
			StringBuilder select = new StringBuilder();
			select.append("SELECT USUARIO FROM USUARIO WHERE LOWER(USUARIO)  = LOWER(?1) ");

			Query query = entityManager.createNativeQuery(select.toString());
			query.setParameter(1, usuario);

			String login = Utils.toString(query.getSingleResult());

			existe = login!=null && !login.trim().isEmpty();

		} catch (NoResultException re) {
			logger.error("Usuario (Login) no ubicado, usuario : "+ usuario);
		}
		
		return !existe;
	}
}
