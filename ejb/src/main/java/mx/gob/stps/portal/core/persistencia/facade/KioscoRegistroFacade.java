package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.kiosco.vo.KioscoRegistroVO;
import mx.gob.stps.portal.persistencia.entity.KioscoRegistro;

/**
 * Session Bean implementation class KioscoRegistroFacade
 */
@Stateless(mappedName = "KioscoRegistroFacade")
public class KioscoRegistroFacade implements KioscoRegistroFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(KioscoRegistroVO vo) {
		try {

			KioscoRegistro entity = getEntity(vo);
			entityManager.persist(entity);
			
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}

	private KioscoRegistro getEntity(KioscoRegistroVO vo) {
		KioscoRegistro entity = new KioscoRegistro();
		
		entity.setEmpleadoActualmente(vo.getEmpleadoActualmente());
		entity.setFechaRegistro(vo.getFechaRegistro());
		entity.setGenero(vo.getGenero());
		entity.setIdExperienciaTotal(vo.getIdExperienciaTotal());
		entity.setIdKiosco(vo.getIdKiosco());
		entity.setIdNivelEstudios(vo.getIdNivelEstudios());
		entity.setIdRangoEdad(vo.getIdRangoEdad());
		entity.setIpOrigen(vo.getIpOrigen());
		entity.setRegistradoPortal(vo.getRegistradoPortal());
		
		return entity;
	}
		
	}


