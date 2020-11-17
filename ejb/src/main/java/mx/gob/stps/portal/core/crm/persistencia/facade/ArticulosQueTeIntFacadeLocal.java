package mx.gob.stps.portal.core.crm.persistencia.facade;

import mx.gob.stps.portal.persistencia.vo.CmrArticulosQueTeIntVO;

import javax.ejb.Local;
import javax.persistence.PersistenceException;
import java.sql.SQLException;

/**
 * Created by benjamin.vander on 03/12/2015.
 */
@Local
public interface ArticulosQueTeIntFacadeLocal {


    /* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal#save(mx.gob.stps.portal.core.seguridad.vo.UsuarioVO)
	 */
    public long save(CmrArticulosQueTeIntVO vo) throws PersistenceException, SQLException;
    public void update(CmrArticulosQueTeIntVO vo) throws PersistenceException;

    void delete(CmrArticulosQueTeIntVO vo);

    CmrArticulosQueTeIntVO get(Long id) throws SQLException;
}
