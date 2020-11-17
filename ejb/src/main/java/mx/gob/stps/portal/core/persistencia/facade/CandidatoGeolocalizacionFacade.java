package mx.gob.stps.portal.core.persistencia.facade;

import mx.gob.stps.portal.persistencia.entity.CandidatoGeolocalizacion;
import mx.gob.stps.portal.persistencia.vo.CandidatoGeolocalizacionVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.lang.reflect.InvocationTargetException;

@Stateless
public class CandidatoGeolocalizacionFacade implements CandidatoGeolocalizacionFacadeLocal {

    private static Logger logger = Logger.getLogger(CandidatoGeolocalizacionFacade.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void create(CandidatoGeolocalizacionVO candidatoGeolocalizacionVO) throws PersistenceException {
        try {
            CandidatoGeolocalizacion candidatoGeolocalizacion = new CandidatoGeolocalizacion();
            BeanUtils.copyProperties(candidatoGeolocalizacion, candidatoGeolocalizacionVO);

            entityManager.persist(candidatoGeolocalizacion);

        } catch (RuntimeException re) {
            logger.error(re.toString());
            re.printStackTrace();
            throw new PersistenceException(re);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CandidatoGeolocalizacionVO findByCandidatoId(long candidatoId) {
        // TODO: Implement logic
        return null;
    }

    @Override
    public void update(CandidatoGeolocalizacionVO candidatoGeolocalizacionVO) throws PersistenceException {
        // TODO: Implement logic
    }
}
