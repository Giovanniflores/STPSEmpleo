package mx.gob.stps.portal.core.infra.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.persistencia.vo.CatAreaVO;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;

/**
 * Proporciona el acceso a los servicios para el modulo de Catalogos
 * 
 * @author haydee.vertti
 * 
 */
@Remote
public interface CatalogoOpcionAppServiceRemote {

	/**
	 * Obtiene un listado de objetos CatalogoOpcionVO, cada uno de los cuales
	 * representa una opción del catálogo
	 * 
	 * @param idCatalogo
	 * @return List<CatalogoOpcionVO>
	 * @throws BusinessException
	 *             Lanzada en caso de error en la regla de negocio
	 */
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo);

	/**
	 * Method 'getOpcionesCatalogo' Obtiene las opciones de un catálogo
	 * filtrando las que corresponden al listado de identificadores
	 * proporcionado. Por defecto, las opciones se presentan en orden
	 * alfabético.
	 * 
	 * @param idCatalogo
	 * @param long[] arrIds
	 * @return List<CatalogoOpcionVO>
	 */
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo,
			long[] arrIds);

	/**
	 * Method 'getOpcionesCatalogo' Obtiene las opciones de un catálogo, las
	 * opciones se presentan ordenados de acuerdo a su identificador
	 * 
	 * @param idCatalogo
	 * @param orderById
	 * @return List<CatalogoOpcionVO>
	 */
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo,
			boolean orderById);

	/**
	 * Method 'getOpcionesCatalogo' Obtiene las opciones de un catálogo
	 * filtrando las que corresponden al listado de identificadores
	 * proporcionado. Las opciones se presentan ordenadas de acuerdo a su
	 * identificador.
	 * 
	 * @param idCatalogo
	 * @param long[] arrIds
	 * @param orderById
	 * @return List<CatalogoOpcionVO>
	 */
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo,
			long[] arrIds, boolean orderById);

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.gob.stps.portal.core.infra.dao.CatalogoDAO
	 * #consultarCatalogo(long, java.lang.String, boolean)
	 */
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo,
			String idCatPadre, boolean orderBy) throws SQLException;
	
	/**
	 * Method 'getOpcionById'
	 * Obtiene la opciones de una opción de catálogoque corresponde al 
	 * identificador proporcionado.
	 *
	 * @param idCatalogo
	 * @param idCatalogoOpcion
	 * @return String
	 */		
	public String getOpcionById(long idCatalogo,  long idCatalogoOpcion);		

	public List<Long> findAllCatalogos() throws PersistenceException;
	
	public CatalogoOpcionVO findById(long idCatalogo, long idCatalogoOpcion);
	
	public String obtenerCatalogoDinamico(long idCatalogo, String opcion);
	
	public List<CatSubareaVO> subareaListByIdArea(long idArea);
	
	public String obtenerCatalogoDinamico(long idCatalogo, String opcion, String clickaction);
	
	public List<CatalogoOpcionVO> obtenerCatalogoPorId(long idCat, long idCatOp, int ini, int fin, Long ... range);

	public List<CatAreaVO> areaList();
	
	public CatSubareaVO getSubAreaVOByIdAreaIdSubArea(long idArea, long idSubArea);
	
	public List<OfertaFuncionVO> getOfertaFuncionList(long idOfertaEmpleo);
}