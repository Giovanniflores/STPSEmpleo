package mx.gob.stps.portal.core.persistencia.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import mx.gob.stps.portal.core.domicilio.vo.CodigoPostalVO;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.registro.vo.RegistroEntidadesVO;
import mx.gob.stps.portal.persistencia.entity.CiudadCanada;
import mx.gob.stps.portal.persistencia.entity.CodigoPostal;
import mx.gob.stps.portal.persistencia.entity.Domicilio;
import mx.gob.stps.portal.persistencia.entity.Municipio;
import mx.gob.stps.portal.persistencia.entity.MunicipioPk;
import oracle.spatial.geometry.JGeometry;

import org.apache.log4j.Logger;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 01/03/2011
 **/
@Stateless
public class DomicilioFacade implements DomicilioFacadeLocal {

	@PersistenceContext
	private EntityManager entityManager;
	private static Logger logger = Logger.getLogger(DomicilioFacade.class);
	
	public void save(DomicilioVO domicilio) throws PersistenceException {

		try {
			Domicilio entity = getEntity(domicilio);
			entityManager.persist(entity);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}
	
	public void saveSFP(DomicilioVO domicilio) throws PersistenceException {

		try {
			Domicilio entity = getEntitySFP(domicilio);
			entityManager.persist(entity);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}
	}
	
	public void update(DomicilioVO domicilio) throws PersistenceException{
		try {
			Domicilio entity = entityManager.find(Domicilio.class, domicilio.getIdDomicilio());
			getEntityUpdate(domicilio,entity);
			entity.setIdDomicilio(domicilio.getIdDomicilio());
			entityManager.merge(entity);
		} catch (RuntimeException re) {
			logger.error(re.toString());
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
	}

	public DomicilioVO buscarDomicilio(long idDomicilio) {

		Domicilio entity = entityManager.find(Domicilio.class, idDomicilio);
		return getDomicilioVO(entity);
	}

	//Cambio
	public DomicilioVO buscarDomicilioIdPropietario(long idPropietario, long idTipoPropietario) {
		DomicilioVO vo = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT d FROM Domicilio d WHERE d.idPropietario=:propietario AND d.idTipoPropietario=:tipoPropietario ORDER BY d.idDomicilio ");
			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("propietario", idPropietario);
			query.setParameter("tipoPropietario", idTipoPropietario);			
			//Domicilio entity = (Domicilio)query.getSingleResult();
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			if (result!=null){
				Domicilio entity = null; // TODO VERIFICAR - SOLO PUEDE TENER UN DOMICILIO

				for (Object elemento : result) {
					entity = (Domicilio)elemento;
				}
				if (entity!=null){
					vo = getDomicilioVO(entity);
				}

			}
		} catch (NoResultException re) {
			//re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		
		
		return vo;
	}	
	
	public Domicilio getDomicilio(long idPropietario, long idTipoPropietario) {
		Domicilio entity = null; // TODO VERIFICAR - SOLO PUEDE TENER UN DOMICILIO
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT d FROM Domicilio d WHERE d.idPropietario=:propietario AND d.idTipoPropietario=:tipoPropietario ORDER BY d.idDomicilio ");
			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("propietario", idPropietario);
			query.setParameter("tipoPropietario", idTipoPropietario);			
			//Domicilio entity = (Domicilio)query.getSingleResult();
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			if (result!=null){
				for (Object elemento : result) {
					entity = (Domicilio)elemento;
				}
			}
		} catch (NoResultException re) {
			//re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}		

		return entity;
	}
	
	public MunicipioVO consultaMunicipio(long idMunicipio, long idEntidad) throws PersistenceException {
		MunicipioVO municipioVO = null;

		try {
			MunicipioPk pk = new MunicipioPk(idEntidad, idMunicipio);
			Municipio entity = entityManager.find(Municipio.class, pk);
			
			if (entity!=null)
				municipioVO = getMunicipioVO(entity);

		} catch (NoResultException re) {
			re.printStackTrace(); logger.error(re);
		} catch (RuntimeException re) {
			re.printStackTrace();  logger.error(re);
			throw new PersistenceException(re);
		}
		
		return municipioVO;
	}
	
	public CodigoPostalVO consultaCodigoPostal(long idColonia) throws PersistenceException {
		CodigoPostalVO vo = new CodigoPostalVO();
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT c FROM CodigoPostal c WHERE c.idColonia=:idColonia");
			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("idColonia", idColonia);

			CodigoPostal entity = null;
			@SuppressWarnings("unchecked")
			List<CodigoPostal> result = query.getResultList();
			if (result!=null && !result.isEmpty()){
				entity = result.get(0);

				vo.setColoniaDescripcion(entity.getColonia());
				vo.setEntidadDescripcion(entity.getEntidad());
				vo.setMunicipioDescripcion(entity.getMunicipio());
			}
		} catch (NoResultException re) {
			re.printStackTrace(); logger.error(re); // No se localizaron registros
		} catch (RuntimeException re) {
			re.printStackTrace(); logger.error(re);
			throw new PersistenceException(re);
		}

		return vo;
	}
	
	public List<MunicipioVO> obtenerMunicipio(long idEntidad, long idMunicipio) throws PersistenceException {
		List<MunicipioVO> listaMunicipios = new ArrayList<MunicipioVO>();
		String getMunicipiosEnt = "SELECT c FROM Municipio c WHERE c.idEntidad=:idEnt AND c.idMunicipio=:idMun  order by c.municipio";
		try {

			Query query = entityManager.createQuery(getMunicipiosEnt);
			query.setParameter("idEnt", idEntidad);
			query.setParameter("idMun", idMunicipio);
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();

			for (Object nElemento : result) {

				MunicipioVO vo = getMunicipioVO((Municipio) nElemento);
				listaMunicipios.add(vo);
			}

		} catch (NoResultException re) {
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return listaMunicipios;
	}

	public List<MunicipioVO> obtenerMunicipio(long idEntidad)throws PersistenceException {
		List<MunicipioVO> listaMunicipios = new ArrayList<MunicipioVO>();
		String getMunicipios = "SELECT c FROM Municipio c WHERE c.idEntidad=:idEnt order by c.municipio";
		try {
			Query query = entityManager.createQuery(getMunicipios);
			query.setParameter("idEnt", idEntidad);
			@SuppressWarnings("unchecked")
			
			List<Object> result = query.getResultList();

			for (Object nElemento : result) {
				MunicipioVO vo = getMunicipioVO((Municipio) nElemento);
				listaMunicipios.add(vo);
			}

		} catch (NoResultException re) {
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return listaMunicipios;

	}

	private DomicilioVO getDomicilioVO(Domicilio entity) {

		DomicilioVO vo = new DomicilioVO();		
		CodigoPostalVO co = new CodigoPostalVO();
		vo.setIdDomicilio(entity.getIdDomicilio());
		vo.setIdTipoPropietario(entity.getIdTipoPropietario());
		vo.setIdEntidad(entity.getIdEntidad());
		vo.setIdMunicipio(entity.getIdMunicipio());
		vo.setIdColonia(entity.getIdColonia());
		vo.setCalle(entity.getCalle());
		vo.setNumeroInterior(entity.getNumeroInterior());
		vo.setNumeroExterior(entity.getNumeroExterior());
		vo.setEntreCalle(entity.getEntreCalle());
		vo.setyCalle(entity.getYCalle());
		vo.setCodigoPostal(entity.getCodigoPostal());
		vo.setFechaAlta(entity.getFechaAlta());
		vo.setIdPropietario(entity.getIdPropietario());
		co = consultaCodigoPostal(entity.getIdColonia());
		vo.setColonia(co.getColoniaDescripcion());
		vo.setMunicipio(co.getMunicipioDescripcion());
		vo.setEntidad(co.getEntidadDescripcion());
		//vo.setGeoReferencia(entity.getGeoReferencia());
		if (null != entity.getIdLocalidad())
			vo.setIdLocalidad(entity.getIdLocalidad());
		vo.setDomicilioReferencia(entity.getDomicilioRef());
		return vo;
	}

	private Domicilio getEntity(DomicilioVO vo) {
		return getEntity(vo,new Domicilio());
	}
	
	private Domicilio getEntity(DomicilioVO vo,Domicilio entity) {
		//entity.setIdDomicilio(vo.getIdDomicilio());
		entity.setIdTipoPropietario(vo.getIdTipoPropietario());
		entity.setIdEntidad(vo.getIdEntidad());
		entity.setIdMunicipio(vo.getIdMunicipio());
		entity.setIdColonia(vo.getIdColonia());
		entity.setCalle(vo.getCalle());
		entity.setNumeroInterior(vo.getNumeroInterior());
		entity.setNumeroExterior(vo.getNumeroExterior());
		entity.setEntreCalle(vo.getEntreCalle());
		entity.setYCalle(vo.getyCalle());
		entity.setCodigoPostal(vo.getCodigoPostal());
		entity.setFechaAlta(new Date());
		entity.setIdPropietario(vo.getIdPropietario());
		if(vo.getLongitud()!=null && vo.getLongitud()!=null){
			JGeometry geoReferencia = new JGeometry(vo.getLatitud(), vo.getLongitud(), 4326);
//			entity.setGeoReferencia(geoReferencia);
		}
		entity.setIdColonia(vo.getIdColonia());
		entity.setDomicilioRef(vo.getDomicilioReferencia());
		return entity;
	}
	
	private Domicilio getEntityUpdate(DomicilioVO vo,Domicilio entity) {
		//entity.setIdDomicilio(vo.getIdDomicilio());
		entity.setIdTipoPropietario(vo.getIdTipoPropietario());
		entity.setIdEntidad(vo.getIdEntidad());
		entity.setIdMunicipio(vo.getIdMunicipio());
		entity.setIdColonia(vo.getIdColonia());
		entity.setCalle(vo.getCalle());
		entity.setNumeroInterior(vo.getNumeroInterior());
		entity.setNumeroExterior(vo.getNumeroExterior());
		entity.setEntreCalle(vo.getEntreCalle());
		entity.setYCalle(vo.getyCalle());
		entity.setCodigoPostal(vo.getCodigoPostal());
		//entity.setFechaAlta(vo.getFechaAlta());
		entity.setIdPropietario(vo.getIdPropietario());
		if(vo.getLongitud()!=null && vo.getLongitud()!=null){
			JGeometry geoReferencia = new JGeometry(vo.getLatitud(), vo.getLongitud(), 4326);
//			entity.setGeoReferencia(geoReferencia);
		}
		
		return entity;
	}

	private Domicilio getEntitySFP(DomicilioVO vo) {

		Domicilio entity = new Domicilio();

		entity.setIdDomicilio(vo.getIdDomicilio());
		entity.setIdTipoPropietario(vo.getIdTipoPropietario());
		entity.setIdEntidad(vo.getIdEntidad());
		entity.setIdMunicipio(vo.getIdMunicipio());
		entity.setIdColonia(vo.getIdColonia());
		entity.setCalle(vo.getCalle());
		entity.setNumeroInterior(vo.getNumeroInterior());
		entity.setNumeroExterior(vo.getNumeroExterior());
		entity.setEntreCalle(vo.getEntreCalle());
		entity.setYCalle(vo.getyCalle());
		entity.setCodigoPostal(vo.getCodigoPostal());
		entity.setFechaAlta(vo.getFechaAlta());
		entity.setIdPropietario(vo.getIdPropietario());
		return entity;
	}
	
	private MunicipioVO getMunicipioVO(Municipio entity) {

		MunicipioVO vo = new MunicipioVO();

		vo.setIdEntidad(entity.getIdEntidad());
		vo.setIdMunicipio(entity.getIdMunicipio());
		vo.setMunicipio(entity.getMunicipio());

		return vo;
	}
	
	public RegistroEntidadesVO obtieneEntidadMunicipio(long idEntidad, long idMunicipio) throws PersistenceException{
		
		RegistroEntidadesVO vo = new RegistroEntidadesVO();

		StringBuilder sql = new StringBuilder();		
		sql.append("SELECT ID_ENTIDAD, F_DESC_CATALOGO(?1, ID_ENTIDAD) DESC_ENTIDAD, ID_MUNICIPIO, MUNICIPIO ");
		sql.append("  FROM MUNICIPIO ");
		sql.append(" WHERE ID_ENTIDAD = ?2 ");
		sql.append("   AND ID_MUNICIPIO = ?3 ");
		
	    Query query = entityManager.createNativeQuery(sql.toString());
	    query.setParameter(1, String.valueOf(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA));
	    query.setParameter(2, String.valueOf(idEntidad));
	    query.setParameter(3, String.valueOf(idMunicipio));	    
	    
	    try{
	    	Object[] row = (Object[])query.getSingleResult();
		    if (row != null){
		    	vo.setEntidad(Utils.toLong(row[0]));
		    	vo.setEntidadDescripcion(Utils.toString(row[1]));
		    	vo.setMunicipio(Utils.toLong(row[2]));
		    	vo.setMunicipioDescripcion(Utils.toString(row[3]));	    	
		    }	    		    
	    } catch(PersistenceException pe){	    	
	    	logger.error("Error al consultar la entidad y el municipio");
	    	pe.printStackTrace();
	    	throw pe;	    	
	    }

		return vo;
	}

	public String obtenerCodigoPostal(long entidad, long municipio, long colonia) throws PersistenceException {

		String sql = "SELECT DISTINCT C.CODIGO_POSTAL FROM CODIGO_POSTAL C WHERE ID_ENTIDAD=?1 AND ID_MUNICIPIO=?2 AND ID_COLONIA=?3";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, entidad);
		query.setParameter(2, municipio);
		query.setParameter(3, colonia);

		@SuppressWarnings("unchecked")
		List<Object> rowSet = query.getResultList();

		String rows = null;

		for (Object rs : rowSet) {			
			rows = (String)rs;
		}

		return rows;
	}

	@Override
	public List<CatalogoOpcionVO> obtenerCiudadesCanada(long idEntidad) {
		List<CatalogoOpcionVO> ciudadesCanada = new ArrayList<CatalogoOpcionVO>();
		String getCiudadesEnt = "SELECT c FROM CiudadCanada c WHERE c.idProvincia=:idEnt  order by c.ciudad";
		try {
			
			Query query = entityManager.createQuery(getCiudadesEnt);
			query.setParameter("idEnt", idEntidad);
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();
			for (Object nElemento : result) {

				CatalogoOpcionVO vo = new CatalogoOpcionVO();
				CiudadCanada entity = (CiudadCanada) nElemento;
				vo.setIdCatalogoOpcion(entity.getIdCiudad());
				vo.setOpcion(entity.getCiudad());
				ciudadesCanada.add(vo);
			}

		} catch (NoResultException re) {
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}
		return ciudadesCanada;
	}

	@Override
	public List<MunicipioVO> obtenerCiudadCanada(int entidad, int idMunicipio) {
		
		List<MunicipioVO> listaMunicipios = new ArrayList<MunicipioVO>();
		String getCiudadesProv = "SELECT c FROM CiudadCanada c WHERE c.idProvincia=:idEnt AND  c.idCiudad=:idMun  order by c.ciudad";
		try {

			Query query = entityManager.createQuery(getCiudadesProv);
			query.setParameter("idEnt", entidad);
			query.setParameter("idMun", idMunicipio);
			@SuppressWarnings("unchecked")
			List<Object> result = query.getResultList();

			for (Object nElemento : result) {

				MunicipioVO vo = getCiudadCanadaVO((CiudadCanada) nElemento);
				listaMunicipios.add(vo);
			}

		} catch (NoResultException re) {
			re.printStackTrace();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return listaMunicipios;
	}

	private MunicipioVO getCiudadCanadaVO(CiudadCanada entity) {
		MunicipioVO vo = new MunicipioVO();

		vo.setIdEntidad(entity.getIdProvincia());
		vo.setIdMunicipio(entity.getIdCiudad());
		vo.setMunicipio(entity.getCiudad());

		return vo;
	}

	@Override
	public mx.gob.stps.portal.persistencia.vo.DomicilioVO getDomicilioByKeys(long idPropietario, long idTipoPropietario) throws PersistenceException {
		mx.gob.stps.portal.persistencia.vo.DomicilioVO vo = null;
		Domicilio entity = this.getDomicilio(idPropietario, idTipoPropietario);
		if (null != entity) {
			vo = new mx.gob.stps.portal.persistencia.vo.DomicilioVO();
			vo.setCalle(entity.getCalle());
			vo.setCodigoPostal(entity.getCodigoPostal());
			vo.setEntreCalle(entity.getEntreCalle());
			vo.setFechaAlta(entity.getFechaAlta());
			vo.setIdColonia(entity.getIdColonia());
			vo.setIdDomicilio(entity.getIdDomicilio());
			vo.setIdEntidad(entity.getIdEntidad());
			vo.setIdLocalidad(entity.getIdLocalidad());
			vo.setIdMunicipio(entity.getIdMunicipio());
			vo.setIdPropietario(entity.getIdPropietario());
			vo.setIdTipoPropietario(entity.getIdTipoPropietario());
			vo.setNumeroExterior(entity.getNumeroExterior());
			vo.setNumeroInterior(entity.getNumeroInterior());
			vo.setyCalle(entity.getYCalle());
		}
		return vo;
	}

	public boolean estaGeoReferenciado(long idPropietario) throws PersistenceException {

		boolean geoReferencia = false;

		String sql = "SELECT D.GEO_REFERENCIA FROM DOMICILIO D WHERE D.ID_PROPIETARIO=?1";

		try {
			Query query = entityManager.createNativeQuery(sql);
			query.setParameter(1, idPropietario);

			@SuppressWarnings("unchecked")
			Object singleResult = query.getSingleResult();
			if (singleResult != null) {
				geoReferencia = true;
			}
		} catch (NoResultException re) {
			// DO Nothing...
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new PersistenceException(re);
		}

		return geoReferencia;
	}
}