package mx.gob.stps.portal.core.crm.persistencia.facade;

import mx.gob.stps.portal.persistencia.entity.CmrBanner;
import mx.gob.stps.portal.persistencia.vo.CmrBannerVO;
import mx.gob.stps.portal.utils.Utils;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.sql.SQLException;

/**
 * Created by benjamin.vander on 14/12/2015.
 */

@Stateless
public class BannerFacade implements BannerFacadeLocal{
    private static Logger logger = Logger.getLogger(BannerFacade.class);

    @PersistenceContext
    private EntityManager entityManager;

    /* (non-Javadoc)
 * @see mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal#save(mx.gob.stps.portal.core.seguridad.vo.UsuarioVO)
 */
    @Override
    public long save(CmrBannerVO vo) throws PersistenceException, SQLException {
        long id = 0;

        CmrBanner entity = getArticulo(vo);

        try{
            entityManager.persist(entity);
            entityManager.flush();

            id = entity.getIdBanner();

        }catch(Exception e){
            throw new PersistenceException(e);
        }

        return id;
    }

    @Override
    public void update(CmrBannerVO vo) throws PersistenceException {
        try{
            long id = vo.getIdBanner();
            CmrBanner entity = entityManager.find(CmrBanner.class, id);
            assignarValores(vo, entity);
            entityManager.merge(entity);

        }catch(Exception e){
            e.printStackTrace(); logger.error(e);
            throw new PersistenceException(e);
        }
    }

    @Override
    public void delete(CmrBannerVO vo) {
        long id = vo.getIdBanner();
        CmrBanner entity = entityManager.find(CmrBanner.class, id);
        entityManager.remove(entity);

    }
    @Override
    public CmrBannerVO get(Long id) throws SQLException {

        CmrBanner entity = entityManager.find(CmrBanner.class, id);
        CmrBannerVO vo = new CmrBannerVO();
        assignarValoresToVo(vo, entity);

        return vo;
    }

    private void assignarValoresToVo(CmrBannerVO vo, CmrBanner entity) {
        vo.setIdBanner(entity.getIdBanner());
        vo.setActiva(Utils.getBooleanConfidencialidad(entity.getActiva()));
        vo.setCaption(entity.getCaption());
        vo.setFechaFin(Utils.getFechaFormato(entity.getFechFina()));
        vo.setFechaInicio(Utils.getFechaFormato(entity.getFechaInicio()));
        vo.setImagen(entity.getImagen());
        vo.setDescripcion(entity.getDescripcion());
        vo.setDescripcionLarga(entity.getDescripcionLarga());
        vo.setIndice(entity.getIndice());
        vo.setLink(entity.getLink());
        vo.setNuevaVentana(Utils.getBooleanConfidencialidad(entity.getNuevaVentana()));
        vo.setTipoImagen(entity.getTipoImagen());
        vo.setAltura(entity.getAltura());
        vo.setAncho(entity.getAncho());
    }

    private void assignarValores(CmrBannerVO vo, CmrBanner entity) throws SQLException {
        entity.setActiva(Utils.getNumeroConfidencialidad(vo.isActiva()));
        entity.setCaption(vo.getCaption());
        entity.setFechaInicio(Utils.convert(vo.getFechaInicio()));

        entity.setFechaFin(Utils.convert(vo.getFechaFin()));

        entity.setAltura(vo.getAltura());
        entity.setAncho(vo.getAltura());
        entity.setDescripcion(vo.getDescripcion());
        entity.setDescripcionLarga(vo.getDescripcionLarga());
        entity.setIndice(vo.getIndice());
        entity.setLink(vo.getLink());
        entity.setNuevaVentana(Utils.getNumeroConfidencialidad(vo.getNuevaVentana()));
        if(vo.getImagen().length>0) {
            entity.setImagen(vo.getImagen());
            entity.setTipoImagen(vo.getTipoImagen());
        }
    }

    private CmrBanner getArticulo(CmrBannerVO vo) throws SQLException {
        CmrBanner entity = new CmrBanner();

        assignarValores(vo, entity);

        return entity;
    }

}
