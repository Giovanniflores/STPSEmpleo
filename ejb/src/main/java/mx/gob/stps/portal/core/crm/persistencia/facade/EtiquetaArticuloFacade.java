package mx.gob.stps.portal.core.crm.persistencia.facade;

import mx.gob.stps.portal.persistencia.entity.CmrPalabrasClavesArticulo;
import mx.gob.stps.portal.persistencia.vo.CmrPalabrasClavesArticuloVO;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 * Created by benjamin.vander on 03/12/2015.
 */
@Stateless
public class EtiquetaArticuloFacade implements  EtiquetaArticuloFacadeLocal {

    private static Logger logger = Logger.getLogger(EtiquetaArticuloFacade.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(CmrPalabrasClavesArticuloVO vo){
        long idEtiquetaPalabrasClaves = 0;
        CmrPalabrasClavesArticulo entity = getEtiquetaPalabrasClaves(vo);

        try{
            entityManager.persist(entity);
            entityManager.flush();

            idEtiquetaPalabrasClaves = entity.getIdArticuloEtiqueta();

        }catch(Exception e){
            throw new PersistenceException(e);
        }


        return idEtiquetaPalabrasClaves;

    }

    @Override
    public Long save(long idArticulo, long idEtiqueta) {
        CmrPalabrasClavesArticuloVO vo = new CmrPalabrasClavesArticuloVO();
        vo.setIdArticulo(idArticulo);
        vo.setIdEtiqueta(idEtiqueta);
        return this.save(vo);
    }

    @Override
    public void update(CmrPalabrasClavesArticuloVO vo){
        try{
            long id = vo.getIdArticuloEtiqueta();
            CmrPalabrasClavesArticulo entity = entityManager.find(CmrPalabrasClavesArticulo.class, id);
            entity.setIdArticulo(vo.getIdArticulo());
            entity.setIdEtiqueta(vo.getIdEtiqueta());
            entityManager.merge(entity);

        }catch(Exception e){
            e.printStackTrace(); logger.error(e);
            throw new PersistenceException(e);
        }
    }

    @Override
    public void delete(CmrPalabrasClavesArticuloVO vo){
        long id = vo.getIdArticuloEtiqueta();
        CmrPalabrasClavesArticulo entity = entityManager.find(CmrPalabrasClavesArticulo.class, id);
        entityManager.remove(entity);
    }



    private CmrPalabrasClavesArticulo getEtiquetaPalabrasClaves(CmrPalabrasClavesArticuloVO vo) {
        CmrPalabrasClavesArticulo cmrPalabrasClavesArticulo = new CmrPalabrasClavesArticulo();
        cmrPalabrasClavesArticulo.setIdEtiqueta(vo.getIdEtiqueta());
        cmrPalabrasClavesArticulo.setIdArticulo(vo.getIdArticulo());
        return cmrPalabrasClavesArticulo;
    }

}
