package mx.gob.stps.portal.core.crm.persistencia.facade;

import mx.gob.stps.portal.persistencia.entity.CmrUsuario;
import mx.gob.stps.portal.persistencia.vo.CmrUsuarioVO;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 * Created by benjamin.vander on 06/12/2015.
 */
@Stateless
public class UsuarioCrmFacade implements  UsuarioCrmFacadeLocal{

    private static Logger logger = Logger.getLogger(UsuarioCrmFacade.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(CmrUsuarioVO vo) throws PersistenceException {
        long id = 0;

        CmrUsuario entity = getUsuario(vo);

        try{
            entityManager.persist(entity);
            entityManager.flush();

            id = entity.getIdCmtUsuario();

        }catch(Exception e){
            throw new PersistenceException(e);
        }

        return id;


    }

    @Override
    public CmrUsuarioVO get(Long id){
        CmrUsuario entity = entityManager.find(CmrUsuario.class, id);
        CmrUsuarioVO vo = getUsuario(entity);
        return vo;
    }

    @Override
    public void update(CmrUsuarioVO vo){
        try{
            long id = vo.getIdCmtUsuario();
            CmrUsuario entity = entityManager.find(CmrUsuario.class, id);
            entity.setClave(vo.getClave());
            entity.setCorreo(vo.getCorreo());
            entity.setUsuario(vo.getUsuario());
            entityManager.merge(entity);

        }catch(Exception e){
            e.printStackTrace(); logger.error(e);
            throw new PersistenceException(e);
        }
    }

    @Override
    public void delete(CmrUsuarioVO vo){
        long id = vo.getIdCmtUsuario();
        CmrUsuario entity = entityManager.find(CmrUsuario.class, id);
        entityManager.remove(entity);
    }

    private CmrUsuarioVO getUsuario(CmrUsuario entity) {
        CmrUsuarioVO vo = new CmrUsuarioVO();
        vo.setClave(entity.getClave());
        vo.setCorreo(entity.getCorreo());
        vo.setUsuario(entity.getUsuario());
        vo.setIdCmtUsuario(entity.getIdCmtUsuario());
        return vo;
    }

    private CmrUsuario getUsuario(CmrUsuarioVO vo) {
        CmrUsuario cmrUsuario = new CmrUsuario();
        cmrUsuario.setUsuario(vo.getUsuario());
        cmrUsuario.setClave(vo.getClave());
        cmrUsuario.setCorreo(vo.getCorreo());
        return cmrUsuario;
    }
}
