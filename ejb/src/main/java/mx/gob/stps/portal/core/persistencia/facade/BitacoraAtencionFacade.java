package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import mx.gob.stps.portal.core.cil.bo.AttentionRequest;
import mx.gob.stps.portal.core.cil.vo.BitacoraAtencionVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.entity.BitacoraAtencion;

/**
 * Concentra los accesos a la persistencia de Bitacora Atención
 * 
 *
 */
@Stateless
public class BitacoraAtencionFacade implements BitacoraAtencionFacadeLocal {
	
	private static int ACTION_CREATE = 0;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(long idCil, long idCandidato, long idAtencion, long idTipoAtencion, Date fechaInicio, String detalle, long contador) throws PersistenceException {
		try {
			BitacoraAtencion entity = getEntity(ACTION_CREATE, idCil, idCandidato, idAtencion, idTipoAtencion, fechaInicio, detalle, contador);
			entityManager.persist(entity);
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}
	
	@Override
	public void update(long id_bitacora_atencion, long idCil, long idCandidato, long idAtencion, long idTipoAtencion, Date fechaInicio, String detalle, long contador) throws PersistenceException {
		try {
			BitacoraAtencion entity = getEntity(id_bitacora_atencion, idCil, idCandidato, idAtencion, idTipoAtencion, fechaInicio, detalle, contador);
			entityManager.merge(entity);
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}
	
	private BitacoraAtencion getEntity(long id_bitacora_atencion, long idCil, long idCandidato, long idAtencion, long idTipoAtencion, Date fechaInicio, String detalle, long contador){
		BitacoraAtencion entity = null;
		if (id_bitacora_atencion == ACTION_CREATE) entity = new BitacoraAtencion();
		else entity = entityManager.find(BitacoraAtencion.class, id_bitacora_atencion);
		entity.setContador(contador);
		entity.setDetalle(detalle);
		entity.setFechaInicio(fechaInicio);
		entity.setIdAtencion(idAtencion);
		entity.setIdCandidato(idCandidato);
		entity.setIdCil(idCil);
		entity.setIdTipoAtencion(idTipoAtencion);
		return entity;
	}

	@Override
	public AttentionRequest find(long idCil, long idCandidato) throws PersistenceException {
		String findLastAttention = "SELECT MAX(ba.idAtencion) FROM BitacoraAtencion ba  WHERE ba.idCandidato = :idCandidato AND ba.idCil = :idCil";
		String findMaxAttention = "SELECT MAX(ba.idAtencion) FROM BitacoraAtencion ba";
		String findAttentionRequestsByID = "SELECT ba FROM BitacoraAtencion ba WHERE ba.idCandidato = :idCandidato AND ba.idCil = :idCil AND ba.idAtencion = :idAtencion";
		Query query = entityManager.createQuery(findLastAttention);
		query.setParameter("idCil", idCil);
		query.setParameter("idCandidato", idCandidato);
		Long idAtencion = (Long)query.getSingleResult();
		if (null == idAtencion) {
			query = entityManager.createQuery(findMaxAttention);
			idAtencion = (Long)query.getSingleResult() + 1;
		}
		AttentionRequest attention = new AttentionRequest(idCil, idCandidato, idAtencion, null, null, null, null, null, null, null);
		query = entityManager.createQuery(findAttentionRequestsByID);
		query.setParameter("idCil", idCil);
		query.setParameter("idCandidato", idCandidato);
		query.setParameter("idAtencion", idAtencion);
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for(Object resultElement : result) {
			BitacoraAtencionVO vo = getEntity((BitacoraAtencion)resultElement);
			attention.setFechaInicio(vo.getFechaInicio());
			long idTipoAtencion = vo.getIdTipoAtencion();
			if (idTipoAtencion == Constantes.TIPO_SERVICIO.ACTUALIZAR_CV.getIdOpcion())
				attention.setActualizarCV(vo);
			else if (idTipoAtencion == Constantes.TIPO_SERVICIO.IMPRESION.getIdOpcion())
				attention.setImpresion(vo);
			else if (idTipoAtencion == Constantes.TIPO_SERVICIO.FOTOCOPIAS.getIdOpcion())
				attention.setFotocopias(vo);
			else if (idTipoAtencion == Constantes.TIPO_SERVICIO.OTRAS_BOLSAS.getIdOpcion())
				attention.setOtrasBolsas(vo);
			else if (idTipoAtencion == Constantes.TIPO_SERVICIO.ACTIVIDAD_PORTAL.getIdOpcion())
				attention.setActividadPortal(vo);
			else if (idTipoAtencion == Constantes.TIPO_SERVICIO.TELEFONO.getIdOpcion())
				attention.setLlamadas(vo); 
		}
		return attention;
	}
	
	private BitacoraAtencionVO getEntity(BitacoraAtencion entity) {
		BitacoraAtencionVO vo = new BitacoraAtencionVO(0, 0, 0, 0, null, null, 0);
		vo.setContador(entity.getContador());
		vo.setDetalle(entity.getDetalle());
		vo.setFechaInicio(entity.getFechaInicio());
		vo.setIdAtencion(entity.getIdAtencion());
		vo.setIdBitacoraAtencion(entity.getIdBitacoraAtencion());
		vo.setIdCandidato(entity.getIdCandidato());
		vo.setIdCil(entity.getIdCil());
		vo.setIdTipoAtencion(entity.getIdTipoAtencion());
		return vo;
	}
}