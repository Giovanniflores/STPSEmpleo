package mx.gob.stps.portal.core.crm.persistencia.facade;

import mx.gob.stps.portal.persistencia.vo.CmrUsuarioVO;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

/**
 * Created by benjamin.vander on 06/12/2015.
 */

@Local
public interface UsuarioCrmFacadeLocal {
    Long save(CmrUsuarioVO vo) throws PersistenceException;

    CmrUsuarioVO get(Long id);

    void update(CmrUsuarioVO vo);

    void delete(CmrUsuarioVO vo);
}
