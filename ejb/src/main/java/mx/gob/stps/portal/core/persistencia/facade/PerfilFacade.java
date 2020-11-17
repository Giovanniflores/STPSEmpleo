package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.persistencia.entity.Perfil;
import mx.gob.stps.portal.core.seguridad.vo.PerfilVO;

@Stateless
public class PerfilFacade implements PerfilFacadeLocal{

	@PersistenceContext
	private EntityManager entityManager;		
	
	public long save(PerfilVO vo) throws PersistenceException {
		try {
			Perfil entity = getEntity(vo);
			entityManager.persist(entity);
			return entity.getIdPerfil();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}	
	}

	public PerfilVO findById(long id) throws PersistenceException {
		try{
			Perfil instance = entityManager.find(Perfil.class, id);
			return getPerfilVO(instance);
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}
	}

	public void actualizaEstatus(long idPerfil, int estatus) throws PersistenceException {
		try{
			Perfil perfil = entityManager.find(Perfil.class, idPerfil);
			perfil.setEstatus(estatus);
		} catch (RuntimeException re) {
			throw new PersistenceException(re);
		}				
	}

	public List<PerfilVO> findAll() throws PersistenceException {
		List<PerfilVO> perfiles = new ArrayList<PerfilVO>();
		String findAllPerfiles = "select p from Perfil p";
		try{
			Query query = entityManager.createQuery(findAllPerfiles);

			@SuppressWarnings("unchecked")
			List<Perfil> results = (List<Perfil>)query.getResultList();

			if (results!=null){
				for (Perfil entity : results){
					PerfilVO vo = getPerfilVO(entity);
					perfiles.add(vo);
				}
			}
		}catch(Exception e){
			throw new PersistenceException(e);
		}

		return perfiles;
	}
	
	private PerfilVO getPerfilVO(Perfil perfil){
		PerfilVO vo = new PerfilVO();
		vo.setDescripcion(perfil.getDescripcion());
		vo.setEstatus(perfil.getEstatus());
		vo.setIdPerfil(perfil.getIdPerfil());
		vo.setNombre(perfil.getNombre());
		return vo;
	}
	
	private Perfil getEntity(PerfilVO vo){
		Perfil entity = new Perfil();
		entity.setDescripcion(vo.getDescripcion());
		entity.setEstatus(vo.getEstatus());
		entity.setIdPerfil(vo.getIdPerfil());
		entity.setNombre(vo.getNombre());		
		return entity;
	}

}
