package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaRequisitoVO;
import mx.gob.stps.portal.persistencia.entity.OfertaRequisito;

@Stateless
public class OfertaRequisitoFacade implements OfertaRequisitoFacadeLocal {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<OfertaRequisitoVO> ofertaRequisitosList(long idOfertaEmpleo) throws PersistenceException {
		List<OfertaRequisitoVO> list = new ArrayList<OfertaRequisitoVO>();
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT c FROM OfertaRequisito c WHERE c.idOfertaEmpleo=:idOfertaEmpleo");
			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			for(Object resultElement : result){		
				OfertaRequisitoVO vo = getOfertaRequisitoVO((OfertaRequisito)resultElement);	
				if(null!=vo){
					list.add(vo);
				}			
			}			
		} catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	private OfertaRequisitoVO getOfertaRequisitoVO(OfertaRequisito entity) {
		OfertaRequisitoVO vo = new OfertaRequisitoVO();
		vo.setDescripcion(entity.getDescripcion());
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setIdDominio(entity.getIdDominio());
		vo.setIdExperiencia(entity.getIdExperiencia());
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		vo.setIdOfertaRequisito(entity.getIdOfertaRequisito());
		vo.setIdTipoRequisito(entity.getIdTipoRequisito());
		vo.setPrincipal(entity.getPrincipal());
		return vo;
	}
}
