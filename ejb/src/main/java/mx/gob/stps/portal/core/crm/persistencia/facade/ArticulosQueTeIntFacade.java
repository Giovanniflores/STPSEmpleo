package mx.gob.stps.portal.core.crm.persistencia.facade;

import mx.gob.stps.portal.persistencia.entity.CmrArticulosQueTeInt;
import mx.gob.stps.portal.persistencia.vo.CmrArticulosQueTeIntVO;
import mx.gob.stps.portal.utils.Utils;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.sql.SQLException;

/**
 * Created by benjamin.vander on 03/12/2015.
 */
@Stateless
public class ArticulosQueTeIntFacade implements  ArticulosQueTeIntFacadeLocal{

    private static Logger logger = Logger.getLogger(ArticulosQueTeIntFacade.class);

    @PersistenceContext
    private EntityManager entityManager;

    /* (non-Javadoc)
	 * @see mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal#save(mx.gob.stps.portal.core.seguridad.vo.UsuarioVO)
	 */
    public long save(CmrArticulosQueTeIntVO vo) throws PersistenceException, SQLException {
        long idArticulo = 0;

        CmrArticulosQueTeInt entity = getArticulo(vo);

        try{
            entityManager.persist(entity);
            entityManager.flush();

            idArticulo = entity.getId();

        }catch(Exception e){
            throw new PersistenceException(e);
        }

        return idArticulo;
    }

    @Override
    public void update(CmrArticulosQueTeIntVO vo) throws PersistenceException {
        try{
            long id = vo.getId();
            CmrArticulosQueTeInt entity = entityManager.find(CmrArticulosQueTeInt.class, id);
            assignarValores(vo, entity);
            entityManager.merge(entity);

        }catch(Exception e){
            e.printStackTrace(); logger.error(e);
            throw new PersistenceException(e);
        }
    }

    @Override
    public void delete(CmrArticulosQueTeIntVO vo) {
        long id = vo.getId();
        CmrArticulosQueTeInt entity = entityManager.find(CmrArticulosQueTeInt.class, id);
        entityManager.remove(entity);

    }
    @Override
    public CmrArticulosQueTeIntVO get(Long id) throws SQLException {

        CmrArticulosQueTeInt entity = entityManager.find(CmrArticulosQueTeInt.class, id);
        CmrArticulosQueTeIntVO cmrArticulosQueTeIntVO = new CmrArticulosQueTeIntVO();
        assignarValoresToVo(cmrArticulosQueTeIntVO, entity);

        return cmrArticulosQueTeIntVO;
    }

    private void assignarValoresToVo(CmrArticulosQueTeIntVO cmrArticulosQueTeIntVO, CmrArticulosQueTeInt entity) {
        cmrArticulosQueTeIntVO.setId(entity.getId());
        cmrArticulosQueTeIntVO.setActivo(Utils.getBooleanConfidencialidad(entity.getActivo()));
        cmrArticulosQueTeIntVO.setArticulo(new String(entity.getArticulo()));
        cmrArticulosQueTeIntVO.setDescripcion(entity.getDescripcion());
        cmrArticulosQueTeIntVO.setFecha(Utils.getFechaFormato(entity.getFecha()));
        cmrArticulosQueTeIntVO.setFuente(entity.getFuente());
        cmrArticulosQueTeIntVO.setIndexable(Utils.getBooleanConfidencialidad(entity.getIndexable()));
        cmrArticulosQueTeIntVO.setMostrarEnHome(Utils.getBooleanConfidencialidad(entity.getMostrarEnHome()));
        cmrArticulosQueTeIntVO.setTitulo(entity.getTitulo());
        cmrArticulosQueTeIntVO.setUrl(entity.getUrl());

    }

    private void assignarValores(CmrArticulosQueTeIntVO vo, CmrArticulosQueTeInt entity) throws SQLException {
        entity.setActivo(Utils.getNumeroConfidencialidad(vo.getActivo()));

        entity.setArticulo(vo.getArticulo().getBytes());
        entity.setDescripcion(vo.getDescripcion());
        entity.setFecha(Utils.convert(vo.getFecha()));
        entity.setFuente(vo.getFuente());
        entity.setIndexable(Utils.getNumeroConfidencialidad(vo.getIndexable()));
        entity.setMostrarEnHome(Utils.getNumeroConfidencialidad(vo.getMostrarEnHome()));
        entity.setTitulo(vo.getTitulo());

        entity.setUrl(vo.getUrl());
    }

    private CmrArticulosQueTeInt getArticulo(CmrArticulosQueTeIntVO vo) throws SQLException {
        CmrArticulosQueTeInt entity = new CmrArticulosQueTeInt();

        assignarValores(vo, entity);

        return entity;
    }
}
