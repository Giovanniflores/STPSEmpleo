package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.entity.Municipio;
import mx.gob.stps.portal.persistencia.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

/**
 * Session Bean implementation class MunicipioFacade
 */
@Stateless(mappedName = "MunicipioFacade")
public class MunicipioFacade implements MunicipioFacadeLocal {
    
	private static Logger logger = Logger.getLogger(Municipio.class);
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	@SuppressWarnings("unchecked")
	public List<MunicipioVO> consultaMunicipioByName(long idEntidad, String nombreMunicipio) throws PersistenceException {

		List<MunicipioVO> municipiosVO = new ArrayList<MunicipioVO>();
		
		try{

			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT * ");
			sql.append("FROM MUNICIPIO M ");
			sql.append("WHERE 1 = 1 ");
					
			if (idEntidad > 0)
				sql.append("AND M.ID_ENTIDAD = "+idEntidad+" ");
			
			if (nombreMunicipio != null && !nombreMunicipio.isEmpty())
				sql.append("AND TRANSLATE(UPPER(M.MUNICIPIO), 'аимсз', 'AEIOU') = TRANSLATE(UPPER('"+nombreMunicipio+"'), 'аимсз', 'AEIOU') ");
			
			Query query = entityManager.createNativeQuery(sql.toString());			
			logger.info(sql.toString());
			
			List<Object[]> result = query.getResultList();
			
			if (result != null && !result.isEmpty()){
				
				for(Object[] row : result){					
					MunicipioVO vo = new MunicipioVO();					
					vo.setIdEntidad(Utils.toLong(row[0]));
					vo.setIdMunicipio(Utils.toLong(row[1]));
					vo.setMunicipio(Utils.toString(row[2]));
					municipiosVO.add(vo);
				}
			}

		}catch (NoResultException re) {
			// No se localizaron registros
			return null;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return municipiosVO;
	}
	
	public void setEntidadMunicipio(DomicilioVO domicilio) throws PersistenceException {
		if (null == domicilio) return;
		StringBuilder sql = new StringBuilder();		
		sql.append("SELECT ID_ENTIDAD, F_DESC_CATALOGO(?1, ID_ENTIDAD) DESC_ENTIDAD, ID_MUNICIPIO, MUNICIPIO ");
		sql.append("  FROM MUNICIPIO ");
		sql.append(" WHERE ID_ENTIDAD = ?2 ");
		sql.append("   AND ID_MUNICIPIO = ?3 ");
		
	    Query query = entityManager.createNativeQuery(sql.toString());
	    query.setParameter(1, String.valueOf(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA));
	    query.setParameter(2, String.valueOf(domicilio.getIdEntidad()));
	    query.setParameter(3, String.valueOf(domicilio.getIdMunicipio()));	    
	    
	    try {
	    	Object[] row = (Object[])query.getSingleResult();
		    if (row != null){
		    	domicilio.setEntidad(Utils.toString(row[1]));
		    	domicilio.setMunicipio(Utils.toString(row[3]));	    	
		    }	    
		    sql = new StringBuilder();
		    sql.append("SELECT C.ID_COLONIA, C.COLONIA FROM CODIGO_POSTAL C WHERE ID_ENTIDAD = ? AND ID_MUNICIPIO = ? AND ID_COLONIA = ?");
		    query = entityManager.createNativeQuery(sql.toString());
		    query.setParameter(1, String.valueOf(domicilio.getIdEntidad()));
		    query.setParameter(2, String.valueOf(domicilio.getIdMunicipio()));
		    query.setParameter(3, String.valueOf(domicilio.getIdColonia()));
		    row = (Object[])query.getSingleResult();
		    if (row != null)
		    	domicilio.setColonia(Utils.toString(row[1]));  	
	    } catch(PersistenceException pe){	    	
	    	logger.error("Error al consultar la entidad, el municipio y la colonia");
	    	pe.printStackTrace();
	    	throw pe;	    	
	    }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatalogoOpcionVO> getLocalidades(long idEntidad, long idMunicipio) throws PersistenceException {
		List<CatalogoOpcionVO> options = new ArrayList<CatalogoOpcionVO>();
		Query query = entityManager.createNativeQuery("SELECT L.CLOC_ID, L.CLOC_NOMBRE FROM LOCALIDAD_INEGI L, MUNICIPIO M WHERE M.CMUN_ID = L.CMUN_ID AND M.ID_ENTIDAD = ? AND M.ID_MUNICIPIO = ?");
		query.setParameter(1, idEntidad);
		query.setParameter(2, idMunicipio);
		try {
			List<Object[]> result = query.getResultList();
			if (result != null && !result.isEmpty()) {
				for(Object[] row : result) {
					CatalogoOpcionVO vo = new CatalogoOpcionVO();					
					vo.setIdCatalogoOpcion(Utils.toLong(row[0]));
					vo.setOpcion(Utils.toString(row[1]));
					options.add(vo);
				}
			}
		}catch(PersistenceException pe){	    	
	    	logger.error("Error al consultar localidades para el idMunicipio: " + idMunicipio);
	    	pe.printStackTrace();	    	
	    }
		return options;
	}
}
