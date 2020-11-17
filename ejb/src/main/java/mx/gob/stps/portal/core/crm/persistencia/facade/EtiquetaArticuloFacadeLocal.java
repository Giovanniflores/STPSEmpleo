package mx.gob.stps.portal.core.crm.persistencia.facade;

import mx.gob.stps.portal.persistencia.vo.CmrPalabrasClavesArticuloVO;

import javax.ejb.Local;

/**
 * Created by benjamin.vander on 03/12/2015.
 */
@Local
public interface EtiquetaArticuloFacadeLocal {
    Long save(CmrPalabrasClavesArticuloVO vo);
    Long save(long idArticulo,long idEtiqueta);

    void update(CmrPalabrasClavesArticuloVO vo);

    void delete(CmrPalabrasClavesArticuloVO vo);
}
