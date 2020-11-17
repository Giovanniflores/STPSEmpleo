package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.vo.CatAreaVO;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.entity.CatArea;
import mx.gob.stps.portal.persistencia.entity.CatSubarea;
import mx.gob.stps.portal.persistencia.entity.Catalogo;
import mx.gob.stps.portal.persistencia.entity.CatalogoOpcion;
import mx.gob.stps.portal.persistencia.entity.CatalogoOpcionPk;

import org.apache.log4j.Logger;

/**
 * Concentra los accesos a la persistencia de CatalogoOpcion
 * 
 *
 */
@Stateless
public class CatalogoOpcionFacade implements CatalogoOpcionFacadeLocal {

	private static Logger logger = Logger.getLogger(CatalogoOpcionFacade.class);
	
	@PersistenceContext
	private EntityManager entityManager;		
	
	/**
	 * Method 'save'
	 * Almacena los datos del ValueObject y regresa el valor del identificador creado.
	 * @param vo	CatalogoOpcionVO
	 * @return long	idCatalogoOpcion
	 */		
	public long save(CatalogoOpcionVO vo) throws PersistenceException {
		try {
			CatalogoOpcion entity = getEntity(vo);
			entityManager.persist(entity);
			return entity.getIdCatalogoOpcion();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}
	
	/**
	 * Method 'getOpcionesCatalogo'
	 * Obtiene las opciones de un cat·logo.
	 * Por defecto, las opciones se presentan en orden alfabÈtico.
	 * @param long	idCatalogo
	 * @return List<CatalogoOpcionVO>
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<CatalogoOpcionVO> getOpcionesCatalogo(long idCatalogo) throws PersistenceException {
		List<CatalogoOpcionVO> lstOpciones = new ArrayList<CatalogoOpcionVO>();
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM CatalogoOpcion c WHERE c.idCatalogo=:idCat AND c.estatus=:catEstatus ORDER BY c.opcion ");
			Query query =  entityManager.createQuery(sb.toString());			
			query.setParameter("idCat", idCatalogo);		
			query.setParameter("catEstatus", ESTATUS.ACTIVO.getIdOpcion());
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for(Object resultElement : result1){
				CatalogoOpcionVO vo = getCatalogoOpcionVO((CatalogoOpcion)resultElement);	
				lstOpciones.add(vo);
			}	
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace(); 
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}		
		return lstOpciones;
	}	

	/**
	 * Method 'getOpcionesCatalogo'
	 * Obtiene las opciones de un cat·logo filtrando las que corresponden al 
	 * listado de identificadores proporcionado.
	 * Por defecto, las opciones se presentan en orden alfabÈtico.
	 * @param idCatalogo
	 * @param  long[] arrIds
	 * @return List<CatalogoOpcionVO>
	 */		
	public List<CatalogoOpcionVO> getOpcionesCatalogo(long idCatalogo,  long[] arrIds) throws PersistenceException {
		List<CatalogoOpcionVO> lstOpciones = new ArrayList<CatalogoOpcionVO>();
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM CatalogoOpcion c WHERE c.idCatalogo=:idCat AND c.estatus=:catEstatus ORDER BY c.opcion ");
			Query query =  entityManager.createQuery(sb.toString());			
			query.setParameter("idCat", idCatalogo);		
			query.setParameter("catEstatus", ESTATUS.ACTIVO.getIdOpcion());
			
			List<Long> lstIds = new ArrayList<Long>();
			for(long l : arrIds) lstIds.add(l);			
			
			@SuppressWarnings("unchecked")
			List<Object> result1 = query.getResultList();
			for(Object resultElement : result1){
				CatalogoOpcionVO vo = getCatalogoOpcionVO((CatalogoOpcion)resultElement);	
				if(!lstIds.contains(vo.getIdCatalogoOpcion())){
					lstOpciones.add(vo);
				} 
			}	
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace(); 
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}		
		return lstOpciones;
	}	
	
	/**
	 * Method 'getOpcionesCatalogo'
	 * Obtiene las opciones de un cat·logo, las opciones se presentan ordenados de acuerdo a su identificador
	 * @param idCatalogo
	 * @param orderById
	 * @return List<CatalogoOpcionVO>
	 */	
	public List<CatalogoOpcionVO> getOpcionesCatalogo(long idCatalogo, boolean orderById) throws PersistenceException {
		List<CatalogoOpcionVO> lstOpciones = new ArrayList<CatalogoOpcionVO>();	
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM CatalogoOpcion c WHERE c.idCatalogo=:idCat AND c.estatus=:catEstatus ORDER BY c.idCatalogoOpcion ");
			if(orderById){
				Query query =  entityManager.createQuery(sb.toString());			
				query.setParameter("idCat", idCatalogo);		
				query.setParameter("catEstatus", ESTATUS.ACTIVO.getIdOpcion());
				@SuppressWarnings("unchecked")
				List<Object> result1 = query.getResultList();
				for(Object resultElement : result1){
					CatalogoOpcionVO vo = getCatalogoOpcionVO((CatalogoOpcion)resultElement);	
					lstOpciones.add(vo);
				}					
			} else {
				lstOpciones = getOpcionesCatalogo(idCatalogo);
			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace(); 
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}		
		return lstOpciones;
	}	
	
	/**
	 * Method 'getOpcionesCatalogo'
	 * Obtiene las opciones de un cat·logo filtrando las que corresponden al 
	 * listado de identificadores proporcionado.
	 * Las opciones se presentan ordenadas de acuerdo a su identificador.
	 * @param idCatalogo
	 * @param  long[] arrIds
	 * @param orderById
	 * @return List<CatalogoOpcionVO>
	 */			
	public List<CatalogoOpcionVO> getOpcionesCatalogo(long idCatalogo,  long[] arrIds, boolean orderById)throws PersistenceException {
		List<CatalogoOpcionVO> lstOpciones = new ArrayList<CatalogoOpcionVO>();
		StringBuilder sb = new StringBuilder();
		try {	
			sb.append("SELECT c FROM CatalogoOpcion c WHERE c.idCatalogo=:idCat AND c.estatus=:catEstatus ORDER BY c.idCatalogoOpcion ");
			if(orderById){
				Query query =  entityManager.createQuery(sb.toString());			
				query.setParameter("idCat", idCatalogo);		
				query.setParameter("catEstatus", ESTATUS.ACTIVO.getIdOpcion());
				
				List<Long> lstIds = new ArrayList<Long>();
				for(long l : arrIds) lstIds.add(l);			
								
				@SuppressWarnings("unchecked")
				List<Object> result1 = query.getResultList();
				for(Object resultElement : result1){
					CatalogoOpcionVO vo = getCatalogoOpcionVO((CatalogoOpcion)resultElement);					
					if(!lstIds.contains(vo.getIdCatalogoOpcion())){
						lstOpciones.add(vo);
					}
				}					
			} else {
				lstOpciones = getOpcionesCatalogo(idCatalogo, arrIds);
			}
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace(); 
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}		
		return lstOpciones;
		
	}
	
	/**
	 * Method 'getOpcionById'
	 * Obtiene la opciones de una opciÛn de cat·logoque corresponde al 
	 * identificador proporcionado.
	 *
	 * @param idCatalogo
	 * @param idCatalogoOpcion
	 * @return String
	 */		
	public String getOpcionById(long idCatalogo, long idCatalogoOpcion) {
		String strOpcion = "";
		StringBuilder sb = new StringBuilder();		
		try {	
				sb.append("SELECT c FROM CatalogoOpcion c WHERE c.idCatalogo=:idCat AND c.idCatalogoOpcion=:idOpcion AND c.estatus=:catEstatus ORDER BY c.idCatalogoOpcion ");
				Query query =  entityManager.createQuery(sb.toString());			
				query.setParameter("idCat", idCatalogo);
				query.setParameter("idOpcion", idCatalogoOpcion);		
				query.setParameter("catEstatus", ESTATUS.ACTIVO.getIdOpcion());
												
				Object result1 = query.getSingleResult();
				if(result1!=null){
					CatalogoOpcionVO catOpcion = getCatalogoOpcionVO((CatalogoOpcion)result1);
					strOpcion = catOpcion.getOpcion();
				}					
		} catch (NoResultException re) {
			// No se localizaron registros
			//re.printStackTrace(); 
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}		
		return strOpcion;
	}
	
	
	/**
	 * Method 'getOpcionByInterval'
	 * Obtiene la opciones de un cat·logo que corresponde al 
	 * identificador proporcionado, dentro del rango de las 
	 * opciones proporcionadas.
	 *
	 * @param idCatalogo
	 * @param op1
	 * @param op2
	 * @return CatalogoOpcionVO
	 */		
	public List<CatalogoOpcionVO> getOpcionByInterval(long idCatalogo,long op1, long op2)  throws PersistenceException {
		List<CatalogoOpcionVO> lstOpciones = new ArrayList<CatalogoOpcionVO>();
		StringBuilder sb = new StringBuilder();
		try {
				sb.append("SELECT c FROM CatalogoOpcion c WHERE c.idCatalogo=:idCat AND c.idCatalogoOpcion > :idOpcion1 AND c.idCatalogoOpcion < :idOpcion2 AND c.estatus=:catEstatus ORDER BY c.opcion ");
			
				Query query =  entityManager.createQuery(sb.toString());	
				query.setParameter("idCat", idCatalogo);
				query.setParameter("idOpcion1", op1);
				query.setParameter("idOpcion2", op2);		
				query.setParameter("catEstatus", ESTATUS.ACTIVO.getIdOpcion());
				@SuppressWarnings("unchecked")
				List<Object> result1 = query.getResultList();
				for(Object resultElement : result1){
					CatalogoOpcionVO vo = getCatalogoOpcionVO((CatalogoOpcion)resultElement);	
					lstOpciones.add(vo);
				}					
			
		} catch (NoResultException re) {
			// No se localizaron registros
			re.printStackTrace(); 
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}		
		return lstOpciones;
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Long> findAllCatalogos() throws PersistenceException {
		List<Long> ids = new ArrayList<Long>();
		StringBuilder sb = new StringBuilder();
		try{
			sb.append("select c from Catalogo c");
			Query query = entityManager.createQuery(sb.toString());

			@SuppressWarnings("unchecked")
			List<Catalogo> results = (List<Catalogo>)query.getResultList();

			if (results!=null){
				for (Catalogo entity : results){
					long id = entity.getIdCatalogo();
					ids.add(id);
				}
			}

		}catch(Exception e){
			throw new PersistenceException(e);
		}

		return ids;
	}	
	
	@Override
	public List<Integer> findRelatedOptionByID(long idCatalogo, long idCatalogoOpcion) throws PersistenceException {
		List<Integer> ids = new ArrayList<Integer>();
		String option = String.valueOf(idCatalogoOpcion);
		StringBuilder sb = new StringBuilder();
		if (option.length() > 4) {
			try {
				sb.append("SELECT c FROM CatalogoOpcion c WHERE c.idCatalogo=:idCat AND c.estatus=:catEstatus AND c.idCatalogoOpcion like :shortIDCatalogoOpcion ");
				Query query = entityManager.createQuery(sb.toString());
				query.setParameter("idCat", idCatalogo);
				query.setParameter("catEstatus", Constantes.ESTATUS.ACTIVO.getIdOpcion());
				query.setParameter("shortIDCatalogoOpcion", option.substring(0,4) + "%");
				@SuppressWarnings("unchecked")
				List<CatalogoOpcion> results = (List<CatalogoOpcion>)query.getResultList();
				if (results!=null && !results.isEmpty()){
					for (CatalogoOpcion entity : results){
						int id = (int)entity.getIdCatalogoOpcion();
						ids.add(id);
					}
				}
			}catch(Exception e) { throw new PersistenceException(e); }
		}
		return ids;
	}
	
	/**
	 * Method 'getCatalogoOpcionVO'
	 * Coloca los datos de un objeto CatalogoOpcion en un VisualObject correspondiente
	 * @param catOpcion
	 * @return CatalogoOpcionVO
	 */		
	private CatalogoOpcionVO getCatalogoOpcionVO(CatalogoOpcion catOpcion){
		CatalogoOpcionVO vo = new CatalogoOpcionVO();
		vo.setEstatus(catOpcion.getEstatus());
		vo.setFechaAlta(catOpcion.getFechaAlta());
		vo.setFechaModificacion(catOpcion.getFechaModificacion());
		vo.setIdCatalogo(catOpcion.getIdCatalogo());
		vo.setIdCatalogoOpcion(catOpcion.getIdCatalogoOpcion());
		vo.setOpcion(catOpcion.getOpcion());
		if(catOpcion.getIdCatalagoAsociado() != null)
			vo.setIdCatalagoAsociado(catOpcion.getIdCatalagoAsociado());
		if(catOpcion.getIdCorto() != null)
			vo.setIdCorto(catOpcion.getIdCorto());
		return vo;
	}	
	
	/**
	 * Method 'getEntity'
	 * Coloca los datos de un VisualObject en un objeto de tipo CatalogoOpcion
	 * @param vo
	 * @return CatalogoOpcion
	 */	
	private CatalogoOpcion getEntity(CatalogoOpcionVO vo){
		CatalogoOpcion entity = new CatalogoOpcion();
		entity.setEstatus(vo.getEstatus());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setFechaModificacion(vo.getFechaModificacion());
		entity.setIdCatalogo(vo.getIdCatalogo());
		entity.setIdCatalogoOpcion(vo.getIdCatalogoOpcion());
		entity.setOpcion(vo.getOpcion());
		entity.setIdCorto(vo.getIdCorto());
		return entity;
	}

	public List<CatalogoOpcionVO> consultaOpciones(long idCatalogo, List<Integer> idsOpciones){
		
		if (idCatalogo <= 0) throw new IllegalArgumentException("Identificador de catalogo requerido.");
		if (idsOpciones==null || idsOpciones.isEmpty()) throw new IllegalArgumentException("Identificadores requeridos de opciones de catalogo.");
		
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
		
		try {
			String in = "";
			for (Integer idOpcion : idsOpciones) in += idOpcion.intValue() +",";
			if (in.endsWith(",")) in = in.substring(0, in.length()-1);
			
			String jpql = "SELECT c FROM CatalogoOpcion c WHERE c.idCatalogo = :idCatalogo AND c.idCatalogoOpcion IN ("+ in +")";
	
			Query query = entityManager.createQuery(jpql);
			query.setParameter("idCatalogo", idCatalogo);
	
			@SuppressWarnings("unchecked")
			List<CatalogoOpcion> results = (List<CatalogoOpcion>)query.getResultList();
	
			if (results!=null && !results.isEmpty()){
				for (CatalogoOpcion entity : results){
					CatalogoOpcionVO opcion = getCatalogoOpcionVO(entity);
					opciones.add(opcion);
				}
			}		
			
		} catch (NoResultException e) {
			logger.error("No se localizaron registros de catalogos");
		} catch (RuntimeException e) {
			e.printStackTrace(); logger.error(e);
			//throw new PersistenceException(re);
		}
		
		return opciones;
	}
	
	public List<Long> consultaCatalogosAsociados(long idCatalogo){
		
		if (idCatalogo <= 0) throw new IllegalArgumentException("Identificador de catalogo requerido.");
		
		List<Long> idsCatalogosAsociados = new ArrayList<Long>();
		
		try {
			String jpql = "SELECT distinct c.idCatalagoAsociado FROM CatalogoOpcion c WHERE c.idCatalogo = :idCatalogo";
	
			Query query = entityManager.createQuery(jpql);
			query.setParameter("idCatalogo", idCatalogo);
	
			@SuppressWarnings("unchecked")
			List<Object> results = query.getResultList();
	
			if (results!=null && !results.isEmpty()){
				for (Object rs : results){
					long idCatalogoAsociado = Utils.toLong(rs);
					idsCatalogosAsociados.add(idCatalogoAsociado);
				}
			}		
			
		} catch (NoResultException e) {
			logger.error("No se localizo el catalogo :"+ idCatalogo);
		} catch (RuntimeException e) {
			e.printStackTrace(); logger.error(e);
			//throw new PersistenceException(re);
		}
		
		return idsCatalogosAsociados;
	}

	public CatalogoOpcionVO findById(long idCatalogoOpcion) {
		CatalogoOpcionVO catOpcion = null;
		List<CatalogoOpcionVO> lstOpciones = new ArrayList<CatalogoOpcionVO>();		
		try {			
			String jpql = "SELECT c FROM CatalogoOpcion c WHERE c.idCatalogoOpcion  = :idCatalogoOpcion ";			
			Query query = entityManager.createQuery(jpql);
			query.setParameter("idCatalogoOpcion", idCatalogoOpcion);
	
			@SuppressWarnings("unchecked")
			List<CatalogoOpcion> results = (List<CatalogoOpcion>)query.getResultList();
	
			if (results!=null && !results.isEmpty()){
				for (CatalogoOpcion entity : results){
					CatalogoOpcionVO opcion = getCatalogoOpcionVO(entity);
					lstOpciones.add(opcion);
				}
			}		
			if(null!=lstOpciones){				
				catOpcion = lstOpciones.get(0);
			}
		} catch (NoResultException re) {
			logger.error("Opcion de catalogo no localizada ("+ idCatalogoOpcion +")");
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}		
		return catOpcion;		
	}
	
	/**
	 * Method 'findById'
	 * Regresa un objecto CatalogoOpcionVO con los datos correspondientes al
	 * identificador de catalogo e identificador de opcion proporcionados
	 * @param idCatalogo
	 * @param idCatalogoOpcion
	 * @return CatalogoOpcionVO
	 */		
	public CatalogoOpcionVO findById(long idCatalogo, long idCatalogoOpcion) {
		CatalogoOpcionVO catOpcion = null;
		
		try {			
			CatalogoOpcionPk pk = new CatalogoOpcionPk(idCatalogo, idCatalogoOpcion);
			CatalogoOpcion entity = entityManager.find(CatalogoOpcion.class, pk);

			if (entity!=null)
				catOpcion = getCatalogoOpcionVO(entity);
			else
				logger.error("Opcion de catalogo no localizada ("+ idCatalogo +","+ idCatalogoOpcion +")");
		} catch (NoResultException re) {
			logger.error("Opcion de catalogo no localizada ("+ idCatalogo +","+ idCatalogoOpcion +")");
		} catch (RuntimeException re) {
			re.printStackTrace(); throw new PersistenceException(re);
		}
		
		return catOpcion;
	}
	
	public CatalogoOpcionVO consultaOpcionPorCatalogosAsociados(long idCatalogoConAsociados, long idCatalogoOpcion){
		
		if (idCatalogoConAsociados <= 0) throw new IllegalArgumentException("Identificador de catalogo requerido.");
		if (idCatalogoOpcion <= 0) throw new IllegalArgumentException("Identificador de catalogo opcion requerido.");
		
		CatalogoOpcionVO opcion = null;

		try {
			String jpql = "select o " +
					      "  from CatalogoOpcion o " +
					      " where o.idCatalogo in (select distinct c.idCatalagoAsociado " +
					                               " from CatalogoOpcion c " +
					                              " where c.idCatalogo = :idCatalogoConAsociados) " +
					      "   and o.idCatalogoOpcion = :idCatalogoOpcion";
	
			Query query = entityManager.createQuery(jpql);
			query.setParameter("idCatalogoConAsociados", idCatalogoConAsociados);
			query.setParameter("idCatalogoOpcion",       idCatalogoOpcion);
			CatalogoOpcion entity = (CatalogoOpcion)query.getSingleResult();
	
			if (entity!=null)
				opcion = getCatalogoOpcionVO(entity);

		} catch (NoResultException e) {
			logger.error("No se localizo la opcion ("+ idCatalogoOpcion +") de los catalogos asociados al catalogo ("+ idCatalogoConAsociados +")");
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException(e);
		}
		
		return opcion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String obtenerCatalogoDinamico(long idCatalogo, String opcion) {
		List<Object> resultado = null;
		StringBuilder listaHtml = new StringBuilder();
		String opcionCatalogo;
		String opcionRegEx = opcion;
		String replament[] = {"[a|·]", "[e|È]", "[i|Ì]", "[o|Û]", "[u|˙]"};
		String vocales[] = {"a", "e", "i", "o", "u"};
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select id_catalogo_opcion, opcion from catalogo_opcion ");
			sb.append("where id_catalogo = " + idCatalogo );
			sb.append(" and TRANSLATE(UPPER(opcion),'¡…Õ”⁄','AEIOU')");
			if(opcion.length() ==2)
				sb.append(" like ''||TRANSLATE(UPPER('" + opcion + "'),'¡…Õ”⁄','AEIOU')||'%'");
			else
				sb.append(" like '%'||TRANSLATE(UPPER('" + opcion + "'),'¡…Õ”⁄','AEIOU')||'%'");
			Query query = entityManager.createNativeQuery(sb.toString());
			resultado = query.getResultList();
			
			for(int i = 0; i <= 4; i++){
				opcionRegEx = opcionRegEx.replaceAll(vocales[i], replament[i]);
			}
			
			for(Object objeto : resultado){
				Object[] arreglo = (Object[]) objeto;
				opcionCatalogo = (String) arreglo[1];
				opcionCatalogo = opcionCatalogo.replaceAll("(?i)"+ opcionRegEx, "<b style='font-weight:bold; background-color:yellow;'>"+ opcion +"</b>");
				listaHtml.append("<li onclick=\"setOcupacion(" + arreglo[0] +", '" + arreglo[1] + "');\" id='" + arreglo[0] + "'>" + opcionCatalogo + "</li>");
			}
		}catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException(e);
		}
		return listaHtml.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String obtenerCatalogoDinamico(long idCatalogo, String opcion,String clickaction) {
		List<Object> resultado = null;
		StringBuilder listaHtml = new StringBuilder();
		String opcionCatalogo;
		String opcionRegEx = opcion;
		String replament[] = {"[a|·]", "[e|È]", "[i|Ì]", "[o|Û]", "[u|˙]"};
		String vocales[] = {"a", "e", "i", "o", "u"};
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select id_catalogo_opcion, opcion from catalogo_opcion ");
			sb.append("where id_catalogo = " + idCatalogo );
			sb.append(" and TRANSLATE(UPPER(opcion),'¡…Õ”⁄','AEIOU')");
			if(opcion.length() ==2)
				sb.append(" like ''||TRANSLATE(UPPER('" + opcion + "'),'¡…Õ”⁄','AEIOU')||'%'");
			else
				sb.append(" like '%'||TRANSLATE(UPPER('" + opcion + "'),'¡…Õ”⁄','AEIOU')||'%'");
			Query query = entityManager.createNativeQuery(sb.toString());
			resultado = query.getResultList();
			
			for(int i = 0; i <= 4; i++){
				opcionRegEx = opcionRegEx.replaceAll(vocales[i], replament[i]);
			}
			
			for(Object objeto : resultado){
				Object[] arreglo = (Object[]) objeto;
				opcionCatalogo = (String) arreglo[1];
				opcionCatalogo = opcionCatalogo.replaceAll("(?i)"+ opcionRegEx, "<b style='font-weight:bold; background-color:yellow;'>"+ opcion +"</b>");
				listaHtml.append("<li onclick=\"" + clickaction + "(" + arreglo[0] +", '" + arreglo[1] + "');\" id='" + arreglo[0] + "'>" + opcionCatalogo + "</li>");
			}
		}catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			throw new PersistenceException(e);
		}
		return listaHtml.toString();
	}
	
	
	/**
	 * Metodo que obtiene catalogos haciendo tratamiento del campo ID_CATALOGO_OPCION.
	 * Por ejemplo, se puede usar para traer el catalogo de SUBSECTOR en base al identificador seleccionado del SECTOR, de tal forma 
	 * el identificado 11 del catalogo sector traer· las opciones 11* del catalogo subsector, donde (*) es un n˙mero del 0-9
	 * 
	 * @param idCat		Identificador del catalogo que se va a consultar
	 * @param idCatOp	Identificador de la tabla catalogo opcion
	 * @param ini		Õndice inicial donde se va a tomar la subcadena
	 * @param fin		Õndice final donde se va a tomar la subcadena
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CatalogoOpcionVO> consultaCatalogoPorId(long idCat, long idCatOp, int ini, int fin, Long ... range) {
		StringBuilder query = new StringBuilder("SELECT c FROM CatalogoOpcion c ");
		query.append("WHERE c.idCatalogo = :idCat ");
		if (range != null && range.length > 0) {
			query.append("AND SUBSTRING(c.idCatalogoOpcion, :ini, :fin) IN ( ");
			for (int i=1; i<=range.length; i++) {
				query.append("?").append(i).append(", ");
			}
			query.append(")");			
		} else {
			query.append("AND SUBSTRING(c.idCatalogoOpcion, :ini, :fin) = :idCatOp ");			
		}
		query.append(" ORDER BY c.opcion");
		String consulta = query.toString();
		consulta = consulta.replace(", )", ") ");
		Query typed = entityManager.createQuery(consulta);
		typed.setParameter("idCat", idCat);
		typed.setParameter("ini", ini);
		typed.setParameter("fin", fin);
		if (range != null && range.length > 0) {
			for (int i=1; i<=range.length; i++) {
				typed.setParameter(i, String.valueOf(range[i-1]));
			}
		} else {
			typed.setParameter("idCatOp", String.valueOf(idCatOp));
		}
		List<CatalogoOpcion> list = typed.getResultList();
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
		for (CatalogoOpcion c : list) {
			opciones.add(getCatalogoOpcionVO(c));
		}
		return opciones;
	}
	
	@Override
	public List<CatAreaVO> areaList() {
		List<CatAreaVO> areaList =  new ArrayList<CatAreaVO>();
		try {
			String select = "SELECT sa FROM CatArea sa";
			Query query = entityManager.createQuery(select);
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			for (Object resultElement : result){
				CatAreaVO vo = getCatAreaVO((CatArea)resultElement);
				areaList.add(vo);
			}
		} catch (NoResultException re) {
			// No se obtuvieron registros
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new PersistenceException(e);
		}
		return areaList;
	}

	@Override
	public List<CatSubareaVO> subareaListByIdArea(long idArea) {
		List<CatSubareaVO> subareaList =  new ArrayList<CatSubareaVO>();
		try {
			String select = "SELECT sa FROM CatSubarea sa WHERE sa.idArea=:idArea";
			Query query = entityManager.createQuery(select);
			query.setParameter("idArea", idArea);
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			for (Object resultElement : result){
				CatSubareaVO vo = getCatSubareaVO((CatSubarea)resultElement);
				subareaList.add(vo);
			}
		} catch (NoResultException re) {
			// No se obtuvieron registros
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new PersistenceException(e);
		}
		return subareaList;
	}
	
	@Override
	public CatSubareaVO getSubAreaVOByIdAreaIdSubArea(long idArea, long idSubarea) {
		CatSubareaVO vo = null;
		try {
			String select = "SELECT sa FROM CatSubarea sa WHERE sa.idArea=:idArea AND sa.idSubarea=:idSubarea";
			Query query = entityManager.createQuery(select);
			query.setParameter("idArea", idArea);
			query.setParameter("idSubarea", idSubarea);
			CatSubarea entity = (CatSubarea) query.getSingleResult();
			vo = getCatSubareaVO((CatSubarea)entity);
		}catch (NoResultException re) {
			// No se obtuvieron registros
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new PersistenceException(e);
		}
		return vo;
	}
	
	private CatSubareaVO getCatSubareaVO(CatSubarea entity) {
		CatSubareaVO vo = new CatSubareaVO();
		vo.setIdArea(entity.getIdArea());
		vo.setIdSubarea(entity.getIdSubarea());
		vo.setNumCatArea(entity.getNumCatArea());
		vo.setDescripcion(entity.getDescripcion());
		return vo;
	}
	
	private CatAreaVO getCatAreaVO(CatArea entity) {
		CatAreaVO vo = new CatAreaVO();
		vo.setIdArea(entity.getIdArea());
		vo.setDescripcion(entity.getDescripcion());
		return vo;
	}
}
