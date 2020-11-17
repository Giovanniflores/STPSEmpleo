package mx.gob.stps.portal.core.infra.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.dao.CatalogoDAO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.service.CatalogoOpcionAppServiceRemote;
import mx.gob.stps.portal.core.persistencia.facade.CatalogoOpcionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
import mx.gob.stps.portal.persistencia.vo.CatAreaVO;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;

/**
 * Session Bean implementation class CatalogoOpcionAppService
 */
@Stateless(name = "CatalogoOpcionAppService", mappedName = "CatalogoOpcionAppService") 
public class CatalogoOpcionAppService implements CatalogoOpcionAppServiceRemote {

	@EJB private CatalogoOpcionFacadeLocal catalogoOpcionFacade;	
	
	@EJB private OfertaEmpleoFacadeLocal ofertaEmpleoFacadeLocal;
		
	public CatalogoOpcionAppService() {}
	
	/**
	 * Obtiene un listado de objetos CatalogoOpcionVO, cada uno de los cuales 
	 * representa una opción del catálogo
	 * @param idCatalogo
	 * @return List<CatalogoOpcionVO>
	 * @throws BusinessException Lanzada en caso de error en la regla de negocio
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo) {
		List<CatalogoOpcionVO> lstOpciones = null;
		lstOpciones = catalogoOpcionFacade.getOpcionesCatalogo(idCatalogo);		
		return lstOpciones;
	}

	/**
	 * Method 'getOpcionesCatalogo'
	 * Obtiene las opciones de un catálogo filtrando las que corresponden al 
	 * listado de identificadores proporcionado.
	 * Por defecto, las opciones se presentan en orden alfabético.
	 * @param idCatalogo
	 * @param long[] arrIds
	 * @return List<CatalogoOpcionVO>
	 */	
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, long[] arrIds) {
		List<CatalogoOpcionVO> lstOpciones = null;
		lstOpciones = catalogoOpcionFacade.getOpcionesCatalogo(idCatalogo, arrIds);		
		return lstOpciones;

	}

	/**
	 * Method 'getOpcionesCatalogo'
	 * Obtiene las opciones de un catálogo, las opciones se presentan ordenados de acuerdo a su identificador
	 * @param idCatalogo
	 * @param orderById
	 * @return List<CatalogoOpcionVO>
	 */	
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, boolean orderById) {
		List<CatalogoOpcionVO> lstOpciones = null;
		lstOpciones = catalogoOpcionFacade.getOpcionesCatalogo(idCatalogo, orderById);		
		return lstOpciones;
	}

	/**
	 * Method 'getOpcionesCatalogo'
	 * Obtiene las opciones de un catálogo filtrando las que corresponden al 
	 * listado de identificadores proporcionado.
	 * Las opciones se presentan ordenadas de acuerdo a su identificador.
	 * @param idCatalogo
	 * @param long[] arrIds
	 * @param orderById
	 * @return List<CatalogoOpcionVO>
	 */	
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, long[] arrIds, boolean orderById) {
		List<CatalogoOpcionVO> lstOpciones = null;
		lstOpciones = catalogoOpcionFacade.getOpcionesCatalogo(idCatalogo, arrIds, orderById);		
		return lstOpciones;
	}

	@Override
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, String idCatPadre, boolean orderBy) throws SQLException {
		CatalogoDAO catalogoDAO =  new CatalogoDAO();
		List<CatalogoOpcionVO> opciones = catalogoDAO.consultarCatalogo(idCatalogo, idCatPadre, orderBy);
		return opciones;
	}

	/**
	 * Method 'getOpcionById'
	 * Obtiene la opciones de una opción de catálogo que corresponde al 
	 * identificador proporcionado.
	 *
	 * @param idCatalogo
	 * @param idCatalogoOpcion
	 * 
	 * @return String
	 */	
	public String getOpcionById(long idCatalogo, long idCatalogoOpcion) {
		return catalogoOpcionFacade.getOpcionById(idCatalogo, idCatalogoOpcion);
	}
	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<Long> findAllCatalogos() throws PersistenceException {
		return catalogoOpcionFacade.findAllCatalogos();
	}
	
	public CatalogoOpcionVO findById(long idCatalogo, long idCatalogoOpcion) {
		return catalogoOpcionFacade.findById(idCatalogo, idCatalogoOpcion);
	}

	public String obtenerCatalogoDinamico(long idCatalogo, String opcion) {
		return catalogoOpcionFacade.obtenerCatalogoDinamico(idCatalogo, opcion);
	}

	public String obtenerCatalogoDinamico(long idCatalogo, String opcion, String clickaction) {
		return catalogoOpcionFacade.obtenerCatalogoDinamico(idCatalogo, opcion,clickaction);
	}
	/**
	 * Metodo que obtiene catalogos haciendo tratamiento del campo ID_CATALOGO_OPCION.
	 * Por ejemplo, se puede usar para traer el catalogo de SUBSECTOR en base al identificador seleccionado del SECTOR, de tal forma 
	 * el identificado 11 del catalogo sector traerá las opciones 11* del catalogo subsector, donde (*) es un número del 0-9
	 * 
	 * @param idCat		Identificador del catalogo que se va a consultar
	 * @param idCatOp	Identificador de la tabla catalogo opcion
	 * @param ini		Índice inicial donde se va a tomar la subcadena
	 * @param fin		Índice final donde se va a tomar la subcadena
	 * 
	 * @return
	 */	
	@Override
	public List<CatalogoOpcionVO> obtenerCatalogoPorId(long idCat, long idCatOp, int ini, int fin, Long ... range) {
		return catalogoOpcionFacade.consultaCatalogoPorId(idCat, idCatOp, ini, fin, range);
	}

	@Override
	public List<CatSubareaVO> subareaListByIdArea(long idArea) {
		return catalogoOpcionFacade.subareaListByIdArea(idArea);
	}
	
	@Override
	public List<CatAreaVO> areaList() {
		return catalogoOpcionFacade.areaList();
	}

	@Override
	public CatSubareaVO getSubAreaVOByIdAreaIdSubArea(long idArea, long idSubArea) {
		return catalogoOpcionFacade.getSubAreaVOByIdAreaIdSubArea(idArea, idSubArea);
	}

	@Override
	public List<OfertaFuncionVO> getOfertaFuncionList(long idOfertaEmpleo) {
		return ofertaEmpleoFacadeLocal.getOfertaFuncionList(idOfertaEmpleo);
	}
}