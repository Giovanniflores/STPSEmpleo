package mx.gob.stps.portal.core.persistencia.facade;

import mx.gob.stps.portal.persistencia.vo.CandidatoGeolocalizacionVO;
import javax.ejb.Local;
import javax.persistence.PersistenceException;

@Local
public interface CandidatoGeolocalizacionFacadeLocal {

    public void create(CandidatoGeolocalizacionVO candidatoGeolocalizacionVO) throws PersistenceException;
    public CandidatoGeolocalizacionVO findByCandidatoId(long candidatoId);
    public void update(CandidatoGeolocalizacionVO candidatoGeolocalizacionVO) throws PersistenceException;
}
