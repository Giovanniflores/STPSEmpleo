/**
 * 
 */
package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Calendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.persistencia.entity.Testimonio;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;

import org.apache.log4j.Logger;

/**
 * @author concepcion.aguilar
 *
 */
@Stateless
public class TestimonioFacade implements TestimonioFacadeLocal {
	
	private static Logger logger = Logger.getLogger(ParametroFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public long save(TestimonioVO vo) throws PersistenceException {
		long idTestimonio = -1;
		
		try {
			Testimonio entity = getEntity(vo);
			entityManager.persist(entity);
			idTestimonio = entity.getIdTestimonio();
		} catch (RuntimeException re) {
			logger.error(re.toString());
			throw new PersistenceException(re);
		}
		
		return idTestimonio;
	}

	public TestimonioVO findById(long idTestimonio) throws PersistenceException {
		TestimonioVO vo = new TestimonioVO();
		
		try {
			Testimonio entity = entityManager.find(Testimonio.class, idTestimonio);
			if (entity!=null){
				//entityManager.refresh(entity);
				vo = getTestimonioVO(entity);
			}
		} catch (NoResultException no) {
			// No se localizaron registros
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
		
		return vo;
	}
	
	public int actualizaEstatus(long idTestimonio, int estatus) throws PersistenceException {
		int estatusAnterior = 0;
		
		try {
			Testimonio entity = entityManager.find(Testimonio.class, idTestimonio);
			estatusAnterior = entity.getEstatus();

			entity.setEstatus(estatus);
			entity.setFechaModificacion(Calendar.getInstance().getTime());
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
		
		return estatusAnterior;
	}
	/**
	 * Llena los datos de la entidad Testimonio
	 * @param vo objeto con los datos necesarios para guardar un registro en la
	 * tabla Testimonio
	 * @return el objeto Testimonio lleno
	 */
	private Testimonio getEntity(TestimonioVO vo){
		Testimonio entity = new Testimonio();
		entity.setDescripcion(vo.getDescripcion());
		entity.setEstatus(vo.getEstatus());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setIdPropietario(vo.getIdPropietario());
		entity.setIdTipoPropietario(vo.getIdTipoPropietario());
		return entity;
	}

	private TestimonioVO getTestimonioVO(Testimonio entity){
		TestimonioVO vo = new TestimonioVO();
		
		vo.setIdTestimonio(entity.getIdTestimonio());
		vo.setIdPropietario(entity.getIdPropietario());
		vo.setIdTipoPropietario(entity.getIdTipoPropietario());
		vo.setDescripcion(entity.getDescripcion());
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setEstatus(entity.getEstatus());

		return vo;
	}
	
}
