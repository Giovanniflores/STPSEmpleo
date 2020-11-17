package mx.gob.stps.portal.core.crm.persistencia.facade;

import mx.gob.stps.portal.persistencia.entity.CmrPalabrasClaves;
import mx.gob.stps.portal.persistencia.vo.CmrPalabrasClavesVO;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 * Created by benjamin.vander on 03/12/2015.
 */
@Stateless
public class EtiquetaFacade implements EtiquetaFacadeLocal {
    private static Logger logger = Logger.getLogger(EtiquetaFacade.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long save(CmrPalabrasClavesVO vo) throws PersistenceException{
        long idEtiqueta = 0;

        CmrPalabrasClaves entity = getEtiqueta(vo);

        try{
            entityManager.persist(entity);
            entityManager.flush();

            idEtiqueta = entity.getIdEtiqueta();

        }catch(Exception e){
            throw new PersistenceException(e);
        }

        return idEtiqueta;


    }

    @Override
    public void update(CmrPalabrasClavesVO vo){
        try{
            long id = vo.getIdEtiqueta();
            CmrPalabrasClaves entity = entityManager.find(CmrPalabrasClaves.class, id);
            entity.setEtiqueta(vo.getEtiqueta());
            entityManager.merge(entity);

        }catch(Exception e){
            e.printStackTrace(); logger.error(e);
            throw new PersistenceException(e);
        }
    }

    @Override
    public CmrPalabrasClaves get(Long id){
        CmrPalabrasClaves entity = entityManager.find(CmrPalabrasClaves.class, id);
        return entity;
    }

    @Override
    public void delete(CmrPalabrasClavesVO vo){
        long id = vo.getIdEtiqueta();
        CmrPalabrasClaves entity = entityManager.find(CmrPalabrasClaves.class, id);
        entityManager.remove(entity);
    }

    @Override
    public Long save(String etiqueta) {
        CmrPalabrasClavesVO vo = new CmrPalabrasClavesVO();
        vo.setEtiqueta(etiqueta);
        return this.save(vo);
    }

    private CmrPalabrasClaves getEtiqueta(CmrPalabrasClavesVO vo) {
        CmrPalabrasClaves entity = new CmrPalabrasClaves();
        entity.setEtiqueta(vo.getEtiqueta());
        return entity;
    }
}
