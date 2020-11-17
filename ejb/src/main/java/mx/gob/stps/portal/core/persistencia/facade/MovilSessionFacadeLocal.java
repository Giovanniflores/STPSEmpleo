package mx.gob.stps.portal.core.persistencia.facade;

import java.sql.SQLException;

import javax.ejb.Local;
import javax.persistence.PersistenceException;



import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

/**
 * Define metodo para la persistencia de Usuarios
 * 
 * @author oscar.manzo
 *
 */
@Local
public interface MovilSessionFacadeLocal {

	public MovilSessionVO save(MovilSessionVO vo) throws PersistenceException;
	public MovilSessionVO findByPk(MovilSessionVO vo) throws PersistenceException;
	public void deleteByPk(MovilSessionVO vo) throws PersistenceException;
	public MovilSessionVO update(MovilSessionVO vo);
	}
