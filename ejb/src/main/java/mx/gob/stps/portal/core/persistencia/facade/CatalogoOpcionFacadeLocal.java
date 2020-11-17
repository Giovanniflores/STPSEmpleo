package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.persistencia.vo.CatAreaVO;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

@Local
public interface CatalogoOpcionFacadeLocal {

	/**
	 * Method 'save'
	 * Almacena los datos del ValueObject y regresa el valor del identificador creado.
	 * @param vo	CatalogoOpcionVO
	 * @return long	idCatalogoOpcion
	 */			
	public long save(CatalogoOpcionVO vo) throws PersistenceException;
	
	/**
	 * Method 'getOpcionesCatalogo'
	 * Obtiene las opciones de un catálogo.
	 * Por defecto, las opciones se presentan en orden alfabético.
	 * @param long	idCatalogo
	 * @return List<CatalogoOpcionVO>
	 */	
	public List<CatalogoOpcionVO> getOpcionesCatalogo(long idCatalogo) throws PersistenceException;
	
	/**
	 * Method 'getOpcionesCatalogo'
	 * Obtiene las opciones de un catálogo filtrando las que corresponden al 
	 * listado de identificadores proporcionado.
	 * Por defecto, las opciones se presentan en orden alfabético.
	 * @param idCatalogo
	 * @param  long[] arrIds
	 * @return List<CatalogoOpcionVO>
	 */		
	public List<CatalogoOpcionVO> getOpcionesCatalogo(long idCatalogo, long[] arrIds);
	
	/**
	 * Method 'getOpcionesCatalogo'
	 * Obtiene las opciones de un catálogo, las opciones se presentan ordenados de acuerdo a su identificador
	 * @param idCatalogo
	 * @param orderById
	 * @return List<CatalogoOpcionVO>
	 */		
	public List<CatalogoOpcionVO> getOpcionesCatalogo(long idCatalogo, boolean orderById);
	
	/**
	 * Method 'getOpcionesCatalogo'
	 * Obtiene las opciones de un catálogo filtrando las que corresponden al 
	 * listado de identificadores proporcionado.
	 * Las opciones se presentan ordenadas de acuerdo a su identificador.
	 * @param idCatalogo
	 * @param  long[] arrIds
	 * @param orderById
	 * @return List<CatalogoOpcionVO>
	 */		
	public List<CatalogoOpcionVO> getOpcionesCatalogo(long idCatalogo,  long[] arrIds, boolean orderById);

	/**
	 * Method 'getOpcionById'
	 * Obtiene la opciones de una opción de catálogoque corresponde al 
	 * identificador proporcionado.
	 *
	 * @param idCatalogo
	 * @param idCatalogoOpcion
	 * 
	 * @return String
	 */		
	public String getOpcionById(long idCatalogo,  long idCatalogoOpcion);
	
	
	/**
	 * Method 'getOpcionById'
	 * Obtiene la opciones de una opción de catálogoque corresponde al 
	 * intervalo de identificadores proporcionados.
	 *
	 * @param idCatalogo
	 * @param idCatalogoOpcion
	 * 
	 * @return String
	 */		
	public List<CatalogoOpcionVO> getOpcionByInterval(long idCatalogo,long op1, long op2);
	
	public List<Long> findAllCatalogos() throws PersistenceException;
	
	public List<Integer> findRelatedOptionByID(long idCatalogo, long idCatalogoOpcion) throws PersistenceException;

	public List<CatalogoOpcionVO> consultaOpciones(long idCatalogo, List<Integer> idsOpciones);

	public List<Long> consultaCatalogosAsociados(long idCatalogo);

	public CatalogoOpcionVO findById(long idCatalogo, long idCatalogoOpcion);

	public CatalogoOpcionVO consultaOpcionPorCatalogosAsociados(long idCatalogoConAsociados, long idCatalogoOpcion);
	
	public String obtenerCatalogoDinamico(long idCatalogo, String opcion);
	
	/**
	 * Method 'findById'
	 * Solo utilizar cuando el id de la opcion es unica.
	 *
	 * @param idCatalogoOpcion
	 * 
	 * @return CatalogoOpcionVO
	 */		
	public CatalogoOpcionVO findById(long idCatalogoOpcion);
	
	/**
	 * Metodo que obtiene catalogos haciendo tratamiento del campo ID_CATALOGO_OPCION.
	 * Por ejemplo, se puede usar para traer el catalogo de SUBSECTOR en base al identificador seleccionado del SECTOR, de tal forma 
	 * el identificado 11 del catalogo sector traerá las opciones 11* del catalogo subsector, donde (*) es un número del 0-9
	 * 
	 * @return
	 */
	List<CatalogoOpcionVO> consultaCatalogoPorId(long idCat, long idCatOp, int ini, int fin, Long ... range);

	public String obtenerCatalogoDinamico(long idCatalogo, String opcion,	String clickaction);
	
	public List<CatAreaVO> areaList();
	
	public List<CatSubareaVO> subareaListByIdArea(long idArea);

	public CatSubareaVO getSubAreaVOByIdAreaIdSubArea(long idArea, long idSubArea);
}

