package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.vo.OfertaUbicacionVO;
import mx.gob.stps.portal.core.oferta.vo.UbicacionCanadaVO;
import mx.gob.stps.portal.persistencia.entity.OfertaUbicacion;

@Stateless
public class OfertaUbicacionFacade implements OfertaUbicacionFacadeLocal {

	private static final long serialVersionUID = 5479863869234523372L;
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	public long save(OfertaUbicacionVO vo) throws PersistenceException{
		
			try{
				OfertaUbicacion entity = getEntity(vo);
				entityManager.persist(entity);
				return entity.getIdOfertaUbicacion();
			} catch (RuntimeException re) {
				re.printStackTrace();
				throw new PersistenceException(re);

			}
	}

	public long update(OfertaUbicacionVO vo, Long idOfertaEmpleo) throws PersistenceException {
		try{
			
			OfertaUbicacion entity = entityManager.find(OfertaUbicacion.class, Utils.toLong(vo.getIdOfertaUbicacion()));
			if(null==entity){
				entity = new OfertaUbicacion();
			}
			entity.setIdEntidad(Utils.toLong(vo.getIdEntidad()));
			entity.setIdMunicipio(Utils.toLong(vo.getIdMunicipio()));
			entity.setFechaAlta(vo.getFechaAlta());
			entity.setIdOfertaEmpleo(idOfertaEmpleo);
			//entity.setIdOfertaUbicacion(vo.getIdOfertaUbicacion());			
			entityManager.merge(entity);
			return 1L;
		}catch(NoResultException re){
			throw new PersistenceException(re);
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

	}
	
	private OfertaUbicacion getEntity(OfertaUbicacionVO vo){
		
		OfertaUbicacion entity = new OfertaUbicacion();
		
		if (vo != null){
			
			entity.setIdOfertaUbicacion(vo.getIdOfertaUbicacion());
			entity.setIdOfertaEmpleo(vo.getIdOfertaEmpleo());
			entity.setIdEntidad(Utils.toLong(vo.getIdEntidad()));			
			entity.setIdMunicipio(Utils.toLong(vo.getIdMunicipio()));
			if (vo.getFechaAlta() != null)
				entity.setFechaAlta(vo.getFechaAlta());		
		}
		
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UbicacionCanadaVO getUbicacionOfertaCanada(long idOfertaEmpleo) {
		UbicacionCanadaVO vo = null;
		String queryUbicacionCanada = getUbucacionCanadaQuery(idOfertaEmpleo);
		
		Query query = entityManager.createNativeQuery(queryUbicacionCanada);

		List<Object> rowSet = query.getResultList();
		Iterator<Object> itRowSet = rowSet.iterator();

		if (itRowSet.hasNext()){
			Object[] obj = (Object[])itRowSet.next();			

			vo = new UbicacionCanadaVO();
			vo.setIdProvincia(Utils.toLong(obj[0]));
			vo.setProvincia(Utils.toString(obj[1]));
			vo.setIdCiudad(Utils.toLong(obj[2]));
			vo.setCiudad(Utils.toString(obj[3]));
		}

		return vo;
	}

	private String getUbucacionCanadaQuery(long idOfertaEmpleo) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT CO.ID_CATALOGO_OPCION, CO.OPCION,CC.ID_CIUDAD,CC.CIUDAD ");
		sb.append("FROM CATALOGO_OPCION CO, CIUDAD_CANADA CC, OFERTA_UBICACION OU,OFERTA_EMPLEO OE  ");
		sb.append("WHERE OE.ID_OFERTA_EMPLEO = OU.ID_OFERTA_EMPLEO ");
		sb.append("AND CO.ID_CATALOGO =  "+Constantes.CATALOGO_OPCION_PROVINCIAS_CANADA);
		sb.append("AND OE.ID_OFERTA_EMPLEO = "+idOfertaEmpleo);
		sb.append("AND OU.ID_ENTIDAD = CO.ID_CATALOGO_OPCION ");
		sb.append("AND OU.ID_ENTIDAD = CC.ID_PROVINCIA ");
		sb.append("AND OU.ID_MUNICIPIO = CC.ID_CIUDAD ");
		
		
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public OfertaUbicacionVO finfByIdOfertaEmpleo(long idOfertaEmpleo){
		OfertaUbicacionVO vo = null;
		try{
			Query query = entityManager.createQuery("SELECT c FROM OfertaUbicacion c WHERE c.idOfertaEmpleo = :idOfertaEmpleo");
			query.setParameter("idOfertaEmpleo", idOfertaEmpleo);
			
			List<Object> rowSet = query.getResultList();
			
			if (rowSet!=null && !rowSet.isEmpty()){
				vo = new OfertaUbicacionVO();
				
				for (Object obj : rowSet){
					OfertaUbicacion entity = (OfertaUbicacion)obj;			
					vo.setIdOfertaUbicacion	(Utils.toInt(entity.getIdOfertaUbicacion()));
					vo.setIdOfertaEmpleo	(Utils.toInt(entity.getIdOfertaEmpleo()));
					vo.setIdEntidad			(Utils.toInt(entity.getIdEntidad()));
					vo.setIdMunicipio		(Utils.toInt(entity.getIdMunicipio()));
					vo.setFechaAlta			(entity.getFechaAlta());
					break;
				}
			}
			
		} catch (NoResultException re) {
			// Sin registro
		} catch (Exception re) {
			re.printStackTrace();
		}

		return vo;
	}
	
}
