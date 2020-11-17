package mx.gob.stps.portal.core.persistencia.facade;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import mx.gob.stps.portal.exception.BusinessException;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.entity.SalariosAplicaciones;
import mx.gob.stps.portal.persistencia.entity.SalariosVigentes;
import mx.gob.stps.portal.persistencia.entity.Taller;
import mx.gob.stps.portal.persistencia.facade.AbstractFacade;
import mx.gob.stps.portal.utils.Catalogos;
import mx.gob.stps.portal.utils.ConstantesGenerales;
import mx.gob.stps.portal.utils.converter.exceptions.NotFoundAnnotationException;
import mx.gob.stps.portal.persistencia.vo.CandidatoEventoVO;
import mx.gob.stps.portal.persistencia.vo.SalariosAplicacionesVO;
import mx.gob.stps.portal.persistencia.vo.SalariosVigentesVO;
import mx.gob.stps.portal.persistencia.vo.TallerVO;
import mx.gob.stps.portal.persistencia.vo.UsuarioVO;

@Stateless
public class SalariosAplicacionesFacade extends AbstractFacade<SalariosAplicaciones, SalariosAplicacionesVO> implements SalariosAplicacionesFacadeLocal {

	private static final Logger logger = Logger.getLogger(SalariosAplicaciones.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	private SalariosAplicacionesVO getSalariosAplicacionesVO(SalariosAplicaciones entity) {
		SalariosAplicacionesVO vo = new SalariosAplicacionesVO();
		
		vo.setIdAplicacion(entity.getIdAplicacion());
		vo.setIdUsuario(entity.getIdUsuario());
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setFechaModificacion(entity.getFechaModificacion());
		vo.setNumeroSalarios(entity.getNumeroSalarios());
		vo.setNombreAplicacion(Catalogos.APLICACION.getAplicacion((int) entity.getIdAplicacion()).getOpcion());
		
		return vo;
	}
	
	private SalariosAplicaciones getEntity(SalariosAplicacionesVO salariosAplicacionesVO){
		
		SalariosAplicaciones entidad = new SalariosAplicaciones();
	    entidad.setIdAplicacion(salariosAplicacionesVO.getIdAplicacion());
	    entidad.setIdUsuario(salariosAplicacionesVO.getIdUsuario());
	    entidad.setFechaAlta(salariosAplicacionesVO.getFechaAlta());
	    entidad.setFechaModificacion(salariosAplicacionesVO.getFechaModificacion());
	    entidad.setNumeroSalarios(salariosAplicacionesVO.getNumeroSalarios());

		return entidad;
	}

	public SalariosAplicacionesFacade() {
		super(SalariosAplicaciones.class, SalariosAplicacionesVO.class);
	}

	@Override
	public List<SalariosAplicacionesVO> salariosAplicacionesList() throws NoResultException, NonUniqueResultException,
			QueryTimeoutException, InstantiationException,
			IllegalAccessException, NoSuchFieldException,
			NotFoundAnnotationException {
		List<SalariosAplicacionesVO> salariosAplicaciones = new ArrayList<SalariosAplicacionesVO>();
		try{
			
			String select = "SELECT sa FROM SalariosAplicaciones sa";
			TypedQuery<SalariosAplicaciones> query = entityManager.createQuery(select, SalariosAplicaciones.class);
			List<SalariosAplicaciones> result = query.getResultList();
			for(SalariosAplicaciones resultElement : result){
				SalariosAplicacionesVO vo = getSalariosAplicacionesVO(resultElement);
				salariosAplicaciones.add(vo);
			}
			
		} catch (NoResultException re) {
			// No se obtuvieron registros
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new PersistenceException(e);
		}
		return salariosAplicaciones;
	}


	@Override
	public void actualizaSalariosApli(SalariosAplicacionesVO salariosApli) throws BusinessException, TechnicalException {
		SalariosAplicaciones salarioActualiza = new SalariosAplicaciones();
		try {
			salarioActualiza = entityManager.find(SalariosAplicaciones.class, salariosApli.getIdAplicacion());
			salarioActualiza.setFechaModificacion(salariosApli.getFechaModificacion());
			salarioActualiza.setIdUsuario(salariosApli.getIdUsuario());
			salarioActualiza.setNumeroSalarios(salariosApli.getNumeroSalarios());
			entityManager.merge(salarioActualiza);

		} catch (Exception e) {
			e.printStackTrace();
			throw new TechnicalException(e);
		}
	}
}