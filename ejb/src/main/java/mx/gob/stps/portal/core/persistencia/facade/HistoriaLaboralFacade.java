package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.persistencia.entity.HistoriaLaboral;
import mx.gob.stps.portal.persistencia.facade.AbstractFacade;
import mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.utils.converter.Converter;
import mx.gob.stps.portal.utils.converter.exceptions.NotFoundAnnotationException;

@Stateless
public class HistoriaLaboralFacade extends AbstractFacade<HistoriaLaboral, HistoriaLaboralVO> implements HistoriaLaboralFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;

	public HistoriaLaboralFacade() {
		super(HistoriaLaboral.class, HistoriaLaboralVO.class);
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<HistoriaLaboralVO> getByIdCandidato(long idCandidato) {
		List<HistoriaLaboral> registros = new ArrayList<HistoriaLaboral>();

		try {
			Query query = entityManager.createQuery("SELECT empleos FROM HistoriaLaboral empleos WHERE empleos.idCandidato=:idCandidato");
			query.setParameter("idCandidato", idCandidato);

			registros = query.getResultList();

		} catch (NoResultException re) {
			// No se localizaron registros
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return convierteEntidadAVo(registros);

	}
	
	private List<HistoriaLaboralVO> convierteEntidadAVo(List<HistoriaLaboral> entities){
		List<HistoriaLaboralVO> registros = new ArrayList<HistoriaLaboralVO>();
		
		if(entities!=null){
			for(HistoriaLaboral entity : entities){
				try {
					HistoriaLaboralVO vo = Converter.convertEOtoVO(entity);
					
					registros.add(vo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return registros;
	}

	@Override
	public int borrar(Long idCandidato) {
		try {
			Query query = entityManager.createQuery("DELETE  FROM HistoriaLaboral empleos WHERE empleos.idCandidato=:idCandidato");
			query.setParameter("idCandidato", idCandidato);

			return query.executeUpdate();

		} catch (NoResultException re) {
			// No se localizaron registros
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return -1;
	}

	@Override
	public long create(HistoriaLaboralVO historiaLaboralVO) throws InstantiationException, NoSuchFieldException, IllegalAccessException, NotFoundAnnotationException {
		HistoriaLaboral historiaLaboral = new HistoriaLaboral();
		Converter.convertVOtoEO(historiaLaboralVO,historiaLaboral);
		return create(historiaLaboral);
	}
	
	
}