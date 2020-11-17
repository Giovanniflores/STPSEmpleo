package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.PerfilTipo;
import mx.gob.stps.portal.persistencia.entity.PerfilConocimiento;
import mx.gob.stps.portal.persistencia.entity.PerfilHabilidadPK;

import mx.gob.stps.portal.persistencia.vo.PerfilTipoVO;
import mx.gob.stps.portal.persistencia.vo.PerfilHabilidadVO;
import mx.gob.stps.portal.persistencia.vo.PerfilConocimientoVO;

@Stateless
public class PerfilTipoFacade implements PerfilTipoFacadeLocal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 720041459000278870L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger = Logger.getLogger(PerfilTipoFacade.class);

	@Override
	public List<PerfilTipoVO> perfilTipoList(long idOcupacion) throws PersistenceException {
		List<PerfilTipoVO> perfiles = new ArrayList<PerfilTipoVO>();
		try {
			Query q = entityManager.createQuery("SELECT p FROM PerfilTipo p WHERE p.IdOcupacion = :IdOcupacion");
			q.setParameter("IdOcupacion", idOcupacion);
			@SuppressWarnings("unchecked")
			List<PerfilTipo> results =  (List<PerfilTipo>)q.getResultList();
			if (null != results && !results.isEmpty()) {
				for (PerfilTipo entity : results) {
					PerfilTipoVO vo = getVO(entity);
					perfiles.add(vo);
				}
			}
		}catch (Exception e) {
			logger.error(e);
			throw new PersistenceException(e);
		}
		return perfiles;
	}

	@Override
	public PerfilTipoVO find(long idPerfilTipo) throws PersistenceException {
		PerfilTipo entity = entityManager.find(PerfilTipo.class, idPerfilTipo);
		PerfilTipoVO vo = getVO(entity);
		vo.setPerfilHabilidades(perfilHabilidadList(idPerfilTipo));
		vo.setPerfilConocimientos(perfilConocimientoList(idPerfilTipo));
		return vo;
	}
	
	private PerfilTipoVO getVO(PerfilTipo entity) {
		if (null == entity) return null;
		PerfilTipoVO vo = new PerfilTipoVO();
		vo.setIdPerfil(entity.getIdPerfil());
		vo.setIdOcupacion(entity.getIdOcupacion());
		vo.setDescripcion(entity.getDescripcion());
		vo.setFunciones(entity.getFunciones());
		vo.setEstatus(entity.getEstatus());
		vo.setEquipos(entity.getEquipos());
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setFechaModificacion(entity.getFechaModificacion());
		return vo;
	}
	
	private List<PerfilHabilidadVO> perfilHabilidadList(long idPerfilTipo) throws PersistenceException {
		List<PerfilHabilidadVO> perfilHabilidadList = new ArrayList<PerfilHabilidadVO>();
		Query query = entityManager.createNativeQuery("SELECT id_habilidad FROM perfil_habilidad WHERE id_perfil_tipo = " + idPerfilTipo);
		try {
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			for (Object resultElement : result) {
				PerfilHabilidadVO ph = new PerfilHabilidadVO();
				PerfilHabilidadPK phk = new PerfilHabilidadPK();
				phk.setIdHabilidad(Utils.toLong(resultElement));
				phk.setIdPerfil(idPerfilTipo);
				ph.setId(phk);
				perfilHabilidadList.add(ph);
			}
		}catch (Exception e) {
			logger.error(e);
			throw new PersistenceException(e);
		}
		return perfilHabilidadList;
	}
	
	private List<PerfilConocimientoVO> perfilConocimientoList(long idPerfilTipo) throws PersistenceException {
		List<PerfilConocimientoVO> perfilConocimientoList = new ArrayList<PerfilConocimientoVO>();
		Query query = entityManager.createQuery("SELECT pc FROM PerfilConocimiento pc WHERE pc.idPerfil = :idPerfilTipo");
		query.setParameter("idPerfilTipo", idPerfilTipo);
		try {
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			for (Object entity : result) {
				PerfilConocimientoVO vo = getVO((PerfilConocimiento)entity);
				perfilConocimientoList.add(vo);
			}
		}catch (Exception e) {
			logger.error(e);
			throw new PersistenceException(e);
		}
		return perfilConocimientoList;
	}
	
	private PerfilConocimientoVO getVO(PerfilConocimiento entity) {
		PerfilConocimientoVO vo = new PerfilConocimientoVO();
		vo.setIdPerfil(entity.getidPerfil());
		vo.setConocimiento(entity.getConocimiento());
		vo.setIdExperiencia(entity.getIdExperiencia());
		vo.setIdPerfilConocimiento(entity.getIdPerfilConocimiento());
		return vo;
	}
}