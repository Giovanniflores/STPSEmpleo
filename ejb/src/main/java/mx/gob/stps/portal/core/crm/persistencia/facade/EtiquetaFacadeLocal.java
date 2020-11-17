package mx.gob.stps.portal.core.crm.persistencia.facade;

import mx.gob.stps.portal.persistencia.entity.CmrPalabrasClaves;
import mx.gob.stps.portal.persistencia.vo.CmrPalabrasClavesVO;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

/**
 * Created by benjamin.vander on 03/12/2015.
 */
@Local
public interface EtiquetaFacadeLocal {

    public Long save(CmrPalabrasClavesVO vo) throws PersistenceException;

    public void update(CmrPalabrasClavesVO vo);

    public CmrPalabrasClaves get(Long id);

    public void delete(CmrPalabrasClavesVO vo);

    public Long save(String etiqueta);
}
