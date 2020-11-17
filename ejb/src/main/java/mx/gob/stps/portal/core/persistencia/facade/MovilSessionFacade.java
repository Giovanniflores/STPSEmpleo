package mx.gob.stps.portal.core.persistencia.facade;

import java.sql.SQLException;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.persistencia.entity.MovilSession;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

import org.apache.log4j.Logger;

/**
 * Concentra los accesos a la persistencia de Usuario
 * 
 * @author oscar.manzo
 * 
 */
@Stateless
public class MovilSessionFacade implements MovilSessionFacadeLocal {

	private static Logger logger = Logger.getLogger(MovilSessionFacade.class);

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal#save(mx
	 * .gob.stps.portal.core.seguridad.vo.MovilSessionVO)
	 */
	public MovilSessionVO save(MovilSessionVO vo) throws PersistenceException {

		MovilSession entity = getMovilSession(vo);

		try {
			entityManager.persist(entity);
			entityManager.flush();

		} catch (Exception e) {
			throw new PersistenceException(e);
		}

		return this.getMovilSessionVO(entity);
	}

	@Override
	public MovilSessionVO update(MovilSessionVO vo) {
		MovilSession entity = getMovilSession(vo);
		try {
			entityManager.merge(entity);
			entityManager.flush();

		} catch (Exception e) {
			throw new PersistenceException(e);
		}

		return this.getMovilSessionVO(entity);
	}

	public MovilSessionVO findByPk(MovilSessionVO vo)
			throws PersistenceException {

		MovilSessionVO movilExiste = new MovilSessionVO();
		try {
			Query query = entityManager
					.createQuery("SELECT m FROM MovilSession m WHERE m.idUsuario = :idUsuario and m.idDevice = :idDevice and m.idTipoUsuario = :idTipoUsuario ");
			query.setParameter("idUsuario", vo.getIdUsuario());
			query.setParameter("idDevice", vo.getIdDevice());
			query.setParameter("idTipoUsuario", vo.getIdTipoUsuario());

			MovilSession entity = (MovilSession) query.getSingleResult();
			entityManager.refresh(entity);

			movilExiste = getMovilSessionVO(entity);
			return movilExiste;
		} catch (NoResultException re) {
			logger.error("Session no localizado mediante el Login : "
					+ vo.getIdUsuario() + "  Device: " + vo.getIdDevice());
		} catch (RuntimeException re) {
			re.printStackTrace();
			MovilSessionVO movilSessionVO = new MovilSessionVO();
			movilSessionVO.setStatus(re.getMessage());
			//throw new PersistenceException(re);
		}

		return null;
	}
	
	public void deleteByPk(MovilSessionVO vo)
			throws PersistenceException {

		MovilSessionVO movilExiste = new MovilSessionVO();
		try {
			Query query = entityManager
					.createQuery("delete from MovilSession m WHERE m.idUsuario = :idUsuario and m.idDevice = :idDevice  and m.idTipoUsuario = :idTipoUsuario ");
			query.setParameter("idUsuario", vo.getIdUsuario());
			query.setParameter("idDevice", vo.getIdDevice());
			query.setParameter("idTipoUsuario", vo.getIdTipoUsuario());

			query.executeUpdate();
						
		} catch (NoResultException re) {
			logger.error("Session no localizado mediante el Login : "
					+ vo.getIdUsuario() + "  Device: " + vo.getIdDevice());
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		
	}

	/**
	 * Genera un MovilSessionVO a partir de un Usuario
	 * 
	 * @param entity
	 * @return
	 */
	private MovilSessionVO getMovilSessionVO(MovilSession entity) {
		MovilSessionVO vo = new MovilSessionVO();

		vo.setIdUsuario(entity.getIdUsuario());
		vo.setIdDevice(entity.getIdDevice());
		vo.setToken(entity.getCookie());
		vo.setPrimerLogin(entity.getPrimerLogin());
		vo.setUltimoLogin(entity.getUltimoLogin());
		vo.setUltimoCambio(entity.getUtlimoCambio());
		vo.setSalt(entity.getSalt());
		return vo;
	}

	/**
	 * Genera un Usuario a partir de un MovilSessionVO
	 * 
	 * @param vo
	 * @return
	 */
	private MovilSession getMovilSession(MovilSessionVO vo) {
		MovilSession entity = new MovilSession();

		// entity.setIdUsuario(vo.getIdUsuario());
		entity.setIdUsuario(vo.getIdUsuario());
		entity.setIdDevice(vo.getIdDevice());
		entity.setCookie(vo.getToken());
		entity.setPrimerLogin(vo.getPrimerLogin());
		entity.setUltimoLogin(vo.getUltimoLogin());
		entity.setUtlimoCambio(vo.getUltimoCambio());
		entity.setSalt(vo.getSalt());
		entity.setIdTipoUsuario(vo.getIdTipoUsuario());
		return entity;
	}

}
