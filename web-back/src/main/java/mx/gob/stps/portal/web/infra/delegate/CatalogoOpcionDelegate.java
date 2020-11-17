package mx.gob.stps.portal.web.infra.delegate;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.persistencia.vo.CatAreaVO;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;


public interface CatalogoOpcionDelegate {

	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo) throws ServiceLocatorException;
	
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, long[] arrIds) throws ServiceLocatorException;
	
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, boolean orderById) throws ServiceLocatorException;
	
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, long[] arrIds, boolean orderById) throws ServiceLocatorException;
	
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, String idCatPadre, boolean orderBy) throws ServiceLocatorException, SQLException;
	
	public List<CatalogoOpcionVO> consultarCatalogoOrdenAlfa(long idCatalogo, boolean orderById) throws ServiceLocatorException;	
	
	public String getOpcionById(long idCatalogo, long idCatalogoOpcion) throws ServiceLocatorException, SQLException; 

	public void cargaCatalogosCache() throws ServiceLocatorException;
	
	public List<CatSubareaVO> subareaListByIdArea(long idArea) throws ServiceLocatorException;

	public String consultarCatalogoJSON(long idCatalogo) throws ServiceLocatorException;
	
	public CatalogoOpcionVO findById(long idCatalogo, long idCatalogoOpcion) throws ServiceLocatorException;
	
	public String obtenerCatalogoDinamico(long idCatalogo, String opcion) throws ServiceLocatorException;
	
	public String obtenerCatalogoDinamico(long idCatalogo, String opcion, String clickaction) throws ServiceLocatorException;
	
	List<CatalogoOpcionVO> consultarCatalogoPorId(long idCat, long idCatOp, int ini, int fin, Long ... range) throws ServiceLocatorException;

	public List<CatalogoOpcionVO> getOptions(long idCat, long[] arrIds, boolean orderById, boolean uppercase) throws ServiceLocatorException;

	public List<CatAreaVO> areaList() throws ServiceLocatorException;
	
	public CatSubareaVO getSubAreaVOByIdAreaIdSubArea(long idArea, long idSubArea) throws ServiceLocatorException;
	
	public List<OfertaFuncionVO> getOfertaFuncionList(long idOfertaEmpleo) throws ServiceLocatorException;
}
