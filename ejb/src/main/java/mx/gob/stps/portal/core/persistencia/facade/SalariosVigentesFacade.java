package mx.gob.stps.portal.core.persistencia.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import mx.gob.stps.portal.persistencia.entity.SalariosVigentes;
import mx.gob.stps.portal.persistencia.facade.AbstractFacade;
import mx.gob.stps.portal.utils.converter.exceptions.NotFoundAnnotationException;
import mx.gob.stps.portal.persistencia.vo.SalariosVigentesVO;

@Stateless
public class SalariosVigentesFacade extends AbstractFacade<SalariosVigentes, SalariosVigentesVO> implements SalariosVigentesFacadeLocal {

    private static final Logger logger = Logger.getLogger(SalariosVigentes.class);

    @PersistenceContext
    private EntityManager entityManager;


    protected EntityManager getEntityManager() {
        return entityManager;
    }


    private SalariosVigentesVO getSalariosVigentesVO(SalariosVigentes entity) {
        SalariosVigentesVO vo = new SalariosVigentesVO();

        vo.setIdSalario(entity.getIdSalario());
        vo.setMonto(entity.getMonto());
        vo.setFechaAlta(entity.getFechaAlta());
        vo.setFechaModificacion(entity.getFechaModificacion());
        vo.setIdUsuario(entity.getIdSalario());

        return vo;
    }

    private SalariosVigentes getEntity(SalariosVigentesVO salariosVigentesVO) {

        SalariosVigentes entidad = new SalariosVigentes();
        entidad.setIdSalario(salariosVigentesVO.getIdSalario());
        entidad.setMonto(salariosVigentesVO.getMonto());
        entidad.setFechaAlta(salariosVigentesVO.getFechaAlta());
        entidad.setFechaModificacion(salariosVigentesVO.getFechaModificacion());
        entidad.setIdUsuario(salariosVigentesVO.getIdUsuario());


        return entidad;
    }


    public SalariosVigentesFacade() {
        super(SalariosVigentes.class, SalariosVigentesVO.class);
    }


    @Override
    public List<SalariosVigentesVO> salariosList() throws NoResultException,
            NonUniqueResultException, QueryTimeoutException,
            InstantiationException, IllegalAccessException,
            NoSuchFieldException, NotFoundAnnotationException {

        List<SalariosVigentesVO> salarios = new ArrayList<SalariosVigentesVO>();
        try {

            String select = "SELECT sv FROM SalariosVigentes sv";
            TypedQuery<SalariosVigentes> query = entityManager.createQuery(select, SalariosVigentes.class);
            List<SalariosVigentes> result = query.getResultList();
            for (SalariosVigentes resultElement : result) {
                SalariosVigentesVO vo = getSalariosVigentesVO(resultElement);
                salarios.add(vo);
            }

        } catch (NoResultException re) {
            // No se obtuvieron registros
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new PersistenceException(e);
        }

        return salarios;
    }


    @Override
    public SalariosVigentesVO salarioVigente() throws PersistenceException {
        SalariosVigentesVO vo = new SalariosVigentesVO();
        Object object = new Object();
        try {
            Query query = entityManager.createNativeQuery("SELECT SV.* FROM SALARIOS_VIGENTES SV WHERE SV.ID_SALARIO=(SELECT MAX (ID_SALARIO) FROM SALARIOS_VIGENTES) ");
            object = query.getSingleResult();
            if (object != null) {
                Object[] o = (Object[]) object;
                vo.setIdSalario(((BigDecimal) o[0]).longValue());
                vo.setMonto(((BigDecimal) o[1]).doubleValue());
                vo.setFechaAlta(((Date) o[2]));
                vo.setFechaModificacion(((Date) o[3]));
                vo.setIdUsuario(((BigDecimal) o[4]).longValue());
            }

        } catch (NoResultException re) {
            logger.error("Salario no localizado ");
        } catch (RuntimeException re) {
            logger.error(re);
            throw new PersistenceException(re);
        }

        return vo;
    }

    @Override
    public long registroSalario(SalariosVigentesVO salario) throws PersistenceException {
        try {
            SalariosVigentes entity = getEntity(salario);
            entityManager.persist(entity);
            return entity.getIdSalario();
        } catch (RuntimeException re) {
            re.printStackTrace();
            System.out.println(re.getStackTrace());
            System.out.println(re.getLocalizedMessage());
            System.out.println(re.getCause());
            System.out.println(re.getMessage());
            System.out.println(re.getClass());
            throw new PersistenceException(re);
        }
    }
}
