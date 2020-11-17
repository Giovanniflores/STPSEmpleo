package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraDescripcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraPostulacionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaExtranjeraVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.OfertaExtranjera;
import mx.gob.stps.portal.persistencia.entity.OfertaExtranjeraPostulacion;

@Stateless
public class OfertaExtranjeraFacade implements OfertaExtranjeraFacadeLocal {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int save(OfertaExtranjeraVO vo)  throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OfertaExtranjeraVO find(long idOfertaExtranjera) {
		OfertaExtranjera entity = entityManager.find(OfertaExtranjera.class, idOfertaExtranjera);
		OfertaExtranjeraVO vo = this.getVO(entity);
		return vo;
	}

	@Override
	public int update(OfertaExtranjeraVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<OfertaExtranjeraVO> findAll() {
		List<OfertaExtranjeraVO> list = new ArrayList<OfertaExtranjeraVO>();
		try {
			StringBuilder builder = new StringBuilder();
			//builder.append("SELECT c FROM OfertaExtranjera c WHERE c.estatus =:estatus");
			builder.append("SELECT c FROM OfertaExtranjera c WHERE c.estatus =:estatus order by c.idOfertaExtranjera desc");
			Query query = entityManager.createQuery(builder.toString());
			query.setParameter("estatus", ESTATUS.ACTIVO.getIdOpcion());
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			for (Object resultElement : result) {
				OfertaExtranjera element = (OfertaExtranjera)resultElement;
				OfertaExtranjeraVO vo = getVO(element);
				list.add(vo);
			}
			
		} catch (NoResultException re) {
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return list;
	}
	
	@Override
	public boolean existOfertaExtranjeraPostulacion(long idCandidato, long idOfertaExtranjera) {
		OfertaExtranjeraPostulacion entity = null;
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT c FROM OfertaExtranjeraPostulacion c WHERE c.idCandidato =:idCandidato AND c.idOfertaExtranjera =:idOfertaExtranjera");
		Query query = entityManager.createQuery(builder.toString());
		query.setParameter("idCandidato", idCandidato);
		query.setParameter("idOfertaExtranjera", idOfertaExtranjera);
		try {
			entity = (OfertaExtranjeraPostulacion)query.getSingleResult();
		}catch (NoResultException e) {
			entity = null;
		}
		return null != entity;
	}
	
	@Override
	public List<OfertaExtranjeraDescripcionVO> getDescription(long idOfertaExtranjera) {
		List<OfertaExtranjeraDescripcionVO> list = new ArrayList<OfertaExtranjeraDescripcionVO>();
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT ID_OFERTA_EXTRANJERA_DESC, DESCRIPCION FROM OFERTA_EXTRANJERA_DESCRIPCION WHERE ID_OFERTA_EXTRANJERA ="+idOfertaExtranjera);
			Query query = entityManager.createNativeQuery(builder.toString());
			query.setParameter("idOfertaExtranjera", idOfertaExtranjera);
			@SuppressWarnings("unchecked")
			List<Object[]> rows = query.getResultList();
			if (null != rows) {
				for (Object[] row : rows) {
					OfertaExtranjeraDescripcionVO vo = new OfertaExtranjeraDescripcionVO();
					vo.setDescripcion(Utils.toString(row[1]));
					vo.setIdOfertaExtranjera(idOfertaExtranjera);
					vo.setIdOfertaExtranjeraDesc(Utils.toLong(row[0]));
					list.add(vo);
				}
			}
		} catch  (NoResultException re) {
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return list;
	}
	
	@Override
	public int saveMatch(OfertaExtranjeraPostulacionVO vo) throws PersistenceException {
		if (null != vo.getIdOfertaExtranjerapostulacion() && vo.getIdOfertaExtranjerapostulacion() > 0) return -1;
		if (existOfertaExtranjeraPostulacion(vo.getIdCandidato(), vo.getIdOfertaExtranjera())) return -1;
		OfertaExtranjeraPostulacion entity = new OfertaExtranjeraPostulacion();
		entity.setFechaAlta(new Date());
		entity.setSalarioC(vo.getSalarioC());
		entity.setIdCandidato(vo.getIdCandidato());
		entity.setIdDominioC(vo.getIdDominioC());
		entity.setIdIdiomaC(vo.getIdIdiomaC());
		entity.setIdNivelEstudioC(vo.getIdNivelEstudioC());
		entity.setIdOcupacionC(vo.getIdOcupacionC());
		entity.setIdOfertaExtranjera(vo.getIdOfertaExtranjera());
		entity.setIdCarreraEspecialidadC(vo.getIdCarreraEspecialidadC());
		entityManager.persist(entity);
		return 1;
	}

	@Override
	public List<OfertaExtranjeraVO> findByPaisEstatus(long idPais,
			ESTATUS estatus) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private OfertaExtranjeraVO getVO(OfertaExtranjera entity) {
		OfertaExtranjeraVO vo = new OfertaExtranjeraVO();
		vo.setPais(entity.getPais());
		vo.setSector(entity.getSector());
		vo.setGenero(entity.getGenero());
		vo.setEstatus(entity.getEstatus());
		vo.setEstudios(entity.getEstudios());
		vo.setProvincia(entity.getProvincia());
		vo.setHospedaje(entity.getHospedaje());
		vo.setNumeroPlazas(entity.getNumeroPlazas());
		vo.setOcupacion(entity.getOcupacion());
		vo.setOtrosIdiomas(entity.getOtrosIdiomas());
		vo.setRequisitos(entity.getRequisitos());
		vo.setSalarioMensual(entity.getSalarioMensual());
		vo.setTituloOferta(entity.getTituloOferta());
		vo.setTransportacion(entity.getTransportacion());
		vo.setFechaRegistro(entity.getFechaRegistro());
		vo.setDuracionContrato(entity.getDuracionContrato());
		vo.setExperienciaLaboral(entity.getExperienciaLaboral());
		vo.setIdOfertaExtranjera(entity.getIdOfertaExtranjera());
		vo.setInformacionAdicional(entity.getInformacionAdicional());
		vo.setNivelIdiomaPrincipal(entity.getNivelIdiomaPrincipal());
		
		return vo;
	}
}
