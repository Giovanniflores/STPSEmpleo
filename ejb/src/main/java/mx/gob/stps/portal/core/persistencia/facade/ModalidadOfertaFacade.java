package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.persistencia.entity.Modalidad;
import mx.gob.stps.portal.persistencia.entity.ModalidadOferta;
import mx.gob.stps.portal.persistencia.vo.ModalidadOfertaVO;
import mx.gob.stps.portal.persistencia.vo.ModalidadVO;
import mx.gob.stps.portal.utils.ConstantesGenerales;

import org.apache.log4j.Logger;

@Stateless
public class ModalidadOfertaFacade implements ModalidadOfertaFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;

	private static Logger logger = Logger.getLogger(ModalidadOfertaFacade.class);

	@Override
	public long registrar(ModalidadOfertaVO modalidadOferta) throws PersistenceException {
		ModalidadOferta entity = getEntity(modalidadOferta);
		entityManager.persist(entity);

		return entity.getIdModalidadOferta();
	}

	@Override
	public int update(ModalidadOfertaVO modalidadOferta) throws PersistenceException {
		int result = 1;
		try {
			if (modalidadOferta != null && modalidadOferta.getIdModalidadOferta() > 0) {
				ModalidadOferta entity = entityManager.find(ModalidadOferta.class,modalidadOferta.getIdModalidadOferta());
				entity.setIdOfertaEmpleo(modalidadOferta.getIdOfertaEmpleo());
				entity.setIdModalidad(modalidadOferta.getIdModalidad());	
				entity.setNumeroRequisicion(modalidadOferta.getNumeroRequisicion());
				entity.setEstatus(modalidadOferta.getEstatus());
				entity.setFechaAlta(modalidadOferta.getFechaAlta());	
				entity.setIdUsuario(modalidadOferta.getIdUsuario());	
				entity.setIdOficina(modalidadOferta.getIdOficina());								
				entity.setIdSubprograma(modalidadOferta.getIdSubprograma());
				entity.setFechaModificacion(modalidadOferta.getFechaModificacion());				
				entityManager.merge(entity);

			} else {
				result = -1;
			}
		} catch (Exception e) {
			result = -1;
		}

		return result;
	}

	@Override
	public int delete(long idModalidadOferta) throws PersistenceException {
		int result = 1;
		try {
			ModalidadOferta entity = entityManager.find(ModalidadOferta.class, idModalidadOferta);
			entityManager.remove(entity);
		} catch (Exception e) {
			result = -1;
		}

		return result;
	}

	private ModalidadOferta getEntity(ModalidadOfertaVO modalidadOferta) {
		ModalidadOferta entity = new ModalidadOferta();
		
		entity.setIdModalidadOferta(modalidadOferta.getIdModalidadOferta());		
		entity.setIdOfertaEmpleo(modalidadOferta.getIdOfertaEmpleo());
		entity.setIdModalidad(modalidadOferta.getIdModalidad());	
		entity.setNumeroRequisicion(modalidadOferta.getNumeroRequisicion());
		entity.setEstatus(modalidadOferta.getEstatus());
		entity.setFechaAlta(modalidadOferta.getFechaAlta());	
		entity.setIdUsuario(modalidadOferta.getIdUsuario());	
		entity.setIdOficina(ConstantesGenerales.PORTAL_ID_OFICINA);
		entity.setFechaModificacion(modalidadOferta.getFechaModificacion());	
		entity.setIdSubprograma(modalidadOferta.getIdSubprograma());
			
		return entity;
	}

	@Override
	public List<ModalidadVO> obtenerModalidad(long idSubPrograma) throws PersistenceException {
		List<ModalidadVO> modalidadList = new ArrayList<ModalidadVO>();
		Query query = entityManager.createQuery("select m from Modalidad m where m.idSubPrograma = :idSubPrograma AND m.estatus = :estatus");		
		query.setParameter("idSubPrograma", idSubPrograma);
		query.setParameter("estatus", ESTATUS.ACTIVO.getIdOpcion());
		try {
			@SuppressWarnings("unchecked")
			List<Modalidad> results = (List<Modalidad>) query.getResultList();
			if (null != results && !results.isEmpty()) {
				for (Modalidad entity : results) {
					modalidadList.add(getVO(entity));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modalidadList;
	}
	
	private ModalidadVO getVO(Modalidad entity) {
		ModalidadVO vo = new ModalidadVO();
		vo.setIdModalidad(entity.getIdModalidad());
		vo.setModalidad(entity.getModalidad());
		vo.setEstatus(entity.getEstatus());
		vo.setFechaModificacion(entity.getFechaModificacion());
		vo.setIdSubprograma(entity.getIdSubPrograma());		
		return vo;
	}

	@Override
	public ModalidadOfertaVO obtenerModalidadOfertaByIdOferta(long idOferta) throws PersistenceException {
		ModalidadOferta entity = new ModalidadOferta();		
		entity = getEntityByIdOferta(idOferta);
		
		ModalidadOfertaVO vo = new ModalidadOfertaVO();		
		vo.setIdModalidadOferta(entity.getIdModalidadOferta());		
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		vo.setIdModalidad(entity.getIdModalidad());	
		vo.setNumeroRequisicion(entity.getNumeroRequisicion());
		vo.setEstatus(entity.getEstatus());
		vo.setFechaAlta(entity.getFechaAlta());	
		vo.setIdUsuario(entity.getIdUsuario());	
		vo.setIdOficina(entity.getIdOficina());								
		vo.setIdSubprograma(entity.getIdSubprograma());
		vo.setFechaModificacion(entity.getFechaModificacion());

		return vo;
	}
	
	@Override
	public ModalidadOferta findByIdOferta(long idOfertaEmpleo) {
		return getEntityByIdOferta(idOfertaEmpleo);
	}

	private ModalidadOferta getEntityByIdOferta(long idOfertaEmpleo) {
		ModalidadOferta entity = null;
		try {
			TypedQuery<ModalidadOferta> query = entityManager
					.createQuery(
							"SELECT m FROM ModalidadOferta m WHERE m.idOfertaEmpleo = :idOfertaEmpleo ",
							ModalidadOferta.class);
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			entity = query.getSingleResult();
		
		} catch (NoResultException re) {
			logger.error("Registro de Oferta no localizado, idOfertaEmpleo : " + idOfertaEmpleo);
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
		
		return entity;
	}

}
