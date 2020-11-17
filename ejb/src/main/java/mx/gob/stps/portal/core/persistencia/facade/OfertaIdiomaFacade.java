package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.persistencia.entity.OfertaIdioma;

@Stateless
public class OfertaIdiomaFacade implements OfertaIdiomaFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<OfertaIdiomaVO> ofertaIdiomasList(long idOfertaEmpleo) throws PersistenceException {
		List<OfertaIdiomaVO> list = new ArrayList<OfertaIdiomaVO>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c FROM OfertaIdioma c WHERE c.idOfertaEmpleo=:idOfertaEmpleo");
		Query query = entityManager.createQuery(sb.toString());
		query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for(Object resultElement : result){
			OfertaIdiomaVO vo = getOfertaIdiomaVO((OfertaIdioma)resultElement);			
			list.add(vo);
		}
		return list;
	}

	private OfertaIdiomaVO getOfertaIdiomaVO(OfertaIdioma entity) {
		OfertaIdiomaVO vo = new OfertaIdiomaVO();
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setIdCertificacion(entity.getIdCertificacion());
		vo.setIdDominio(entity.getIdDominio());
		vo.setIdIdioma(entity.getIdIdioma());
		vo.setIdOfertaEmpleo(entity.getIdOfertaEmpleo());
		vo.setIdOfertaIdioma(entity.getIdOfertaIdioma());
		vo.setPrincipal(Utils.toInt(entity.getPrincipal()));
		return vo;
	}
	
	
}
