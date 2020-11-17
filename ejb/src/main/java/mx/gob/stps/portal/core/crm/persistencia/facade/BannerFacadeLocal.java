package mx.gob.stps.portal.core.crm.persistencia.facade;

import mx.gob.stps.portal.persistencia.vo.CmrBannerVO;

import javax.ejb.Local;
import javax.persistence.PersistenceException;
import java.sql.SQLException;

/**
 * Created by benjamin.vander on 14/12/2015.
 */
@Local
public interface BannerFacadeLocal {
    /* (non-Javadoc)
 * @see mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal#save(mx.gob.stps.portal.core.seguridad.vo.UsuarioVO)
 */
    long save(CmrBannerVO vo) throws PersistenceException, SQLException;

    void update(CmrBannerVO vo) throws PersistenceException;

    void delete(CmrBannerVO vo);

    CmrBannerVO get(Long id) throws SQLException;
}
