package mx.gob.stps.portal.web.infra.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import mx.gob.stps.portal.core.infra.service.CatalogoOpcionAppServiceRemote;
import mx.gob.stps.portal.persistencia.vo.CatAreaVO;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.OfertaFuncionVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;


public class CatalogoOpcionDelegateImpl implements CatalogoOpcionDelegate {

	private static CatalogoOpcionDelegate instance = new CatalogoOpcionDelegateImpl();
	
	private static final HashMap <Long, List<CatalogoOpcionVO>> catalogos = new HashMap <Long, List<CatalogoOpcionVO>>();
	private static final HashMap <Long, String> catalogosJSON = new HashMap <Long, String>();
	
	public static CatalogoOpcionDelegate getInstance(){
		return instance;
	}	
	
	/**
	 * Carga la lista de Catalogos
	 */
	public void cargaCatalogosCache() throws ServiceLocatorException {
		catalogos.clear();
		catalogosJSON.clear();
		
		List<Long> idsCatalogos = getCatalogoOpcionAppService().findAllCatalogos();

		for (long idCatalogo : idsCatalogos){
			consultarCatalogo(idCatalogo);
		}
	}

	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo) throws ServiceLocatorException {		
		List<CatalogoOpcionVO> catalogo = getCatalogo(idCatalogo);
		return catalogo;		
	}	
	
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, long[] arrIds)  throws ServiceLocatorException{

		List<CatalogoOpcionVO> catalogo = getCatalogo(idCatalogo);

		List<CatalogoOpcionVO> catalogoFiltra = filtraCatalogo(catalogo, arrIds);

		return catalogoFiltra;		
		//return getCatalogoOpcionAppService().consultarCatalogo(idCatalogo, arrIds);
	}

	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, boolean orderById) throws ServiceLocatorException {
		List<CatalogoOpcionVO> catalogo = getCatalogo(idCatalogo);
		
		if (orderById) ordena(catalogo);
		
		return catalogo;
		//return getCatalogoOpcionAppService().consultarCatalogo(idCatalogo, orderById);	
	}
	
	@Override
	public List<CatalogoOpcionVO> getOptions(long idCat, long[] arrIds, boolean orderById, boolean uppercase) throws ServiceLocatorException {
		List<CatalogoOpcionVO> opcionFilterList = null;
		List<CatalogoOpcionVO> opcionList = getCatalogo(idCat);
		if (arrIds.length > 0) {
			opcionFilterList = filtraCatalogo(opcionList, arrIds);
			opcionList = opcionFilterList;
		}
		if (orderById) ordenAlfabetico(opcionList);
		if (uppercase) {
			opcionFilterList = new ArrayList<CatalogoOpcionVO>();
			for (CatalogoOpcionVO vo : opcionList) {
				CatalogoOpcionVO option = vo;
				option.setOpcion(vo.getOpcion().toUpperCase());
				opcionFilterList.add(option);
			}
			opcionList = opcionFilterList;
		}
		return opcionList;
	}

	public List<CatalogoOpcionVO> consultarCatalogoOrdenAlfa(long idCatalogo, boolean orderById) throws ServiceLocatorException {
		List<CatalogoOpcionVO> catalogo = getCatalogo(idCatalogo);

		if (orderById) ordenAlfabetico(catalogo);

		return catalogo;	
	}

	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, long[] arrIds, boolean orderById) throws ServiceLocatorException {

		List<CatalogoOpcionVO> catalogo = getCatalogo(idCatalogo);

		List<CatalogoOpcionVO> catalogoFiltra = filtraCatalogo(catalogo, arrIds);

		if (orderById) ordena(catalogoFiltra);

		//return getCatalogoOpcionAppService().consultarCatalogo(idCatalogo, arrIds, orderById);
		return catalogoFiltra;
	}

	public String consultarCatalogoJSON(long idCatalogo) throws ServiceLocatorException {
		String catjson = null;
		
		if (catalogosJSON.containsKey(idCatalogo))
			catjson = catalogosJSON.get(idCatalogo);
		else{
			List<CatalogoOpcionVO> catalogo = getCatalogo(idCatalogo);
			
			catjson = toJson(catalogo);
			catalogosJSON.put(idCatalogo, catjson);
		}

		return catjson;
	}
	
	private List<CatalogoOpcionVO> getCatalogo(long idCatalogo) throws ServiceLocatorException {
		List<CatalogoOpcionVO> catalogo = null;
		
		if (catalogos.containsKey(idCatalogo)){
			catalogo = catalogos.get(idCatalogo);
		}else{
			catalogo = getCatalogoOpcionAppService().consultarCatalogo(idCatalogo);
			catalogos.put(idCatalogo, catalogo);
			
			// Catalogos el formato JSON para los controles de DOJO
			String catjson = toJson(catalogo);
			catalogosJSON.put(idCatalogo, catjson);
		}

		/** Se hace una copia de la lista apra evitar la modificacion de la lista original,
		 * ya que esta compartida por todos los usuarios **/
		List<CatalogoOpcionVO> clone = cloneList(catalogo);
		
		return clone;
	}
	
	private List<CatalogoOpcionVO> filtraCatalogo(List<CatalogoOpcionVO> catalogo, long[] arrIds){
		
		List<CatalogoOpcionVO> catalogoFiltra = new ArrayList<CatalogoOpcionVO>();
		for (CatalogoOpcionVO item : catalogo){
			if (!contain(item.getIdCatalogoOpcion(), arrIds)){
				catalogoFiltra.add(item);
			}
		}

		return catalogoFiltra;
	}
	
	private boolean contain(long id, long[] ids){
		for (long l : ids){
			if (id == l) return true;
		}
		return false;
	}
	
	private void ordena(List<CatalogoOpcionVO> catalogo){
    	Collections.sort(catalogo, new Comparator<CatalogoOpcionVO>(){
            @Override
            public int compare(CatalogoOpcionVO o1, CatalogoOpcionVO o2) {
            	return Long.valueOf(o1.getIdCatalogoOpcion()).compareTo(o2.getIdCatalogoOpcion());
            }
    	});
	}

	private void ordenAlfabetico(List<CatalogoOpcionVO> catalogo){
    	Collections.sort(catalogo, new Comparator<CatalogoOpcionVO>(){
            @Override
            public int compare(CatalogoOpcionVO o1, CatalogoOpcionVO o2) {
            	return o1.getOpcion().compareTo(o2.getOpcion());
            }
    	});
	}

	private String toJson(List<CatalogoOpcionVO> catalogo){
		CatalogoVO cat = Utils.getCatalogo(catalogo);
		String json = Utils.toJson(cat);
		return json;
	}
	
	@Override
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo, String idCatPadre, boolean orderBy) throws ServiceLocatorException, SQLException {
		return getCatalogoOpcionAppService().consultarCatalogo(idCatalogo, idCatPadre, orderBy);
	}
	
	@Override
	public String getOpcionById(long idCatalogo, long idCatalogoOpcion) throws ServiceLocatorException, SQLException {
		return getCatalogoOpcionAppService().getOpcionById(idCatalogo, idCatalogoOpcion);
	}	
	
	private CatalogoOpcionAppServiceRemote getCatalogoOpcionAppService() throws ServiceLocatorException{
		CatalogoOpcionAppServiceRemote ejb = (CatalogoOpcionAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_CATALOGO_OPCION);
		return ejb;
	}

	public CatalogoOpcionVO findById(long idCatalogo, long idCatalogoOpcion) throws ServiceLocatorException{
		return getCatalogoOpcionAppService().findById(idCatalogo, idCatalogoOpcion);				
	}

	public String obtenerCatalogoDinamico(long idCatalogo, String opcion) throws ServiceLocatorException{
		return getCatalogoOpcionAppService().obtenerCatalogoDinamico(idCatalogo, opcion);
	}
	
	public String obtenerCatalogoDinamico(long idCatalogo, String opcion,String clickaction) throws ServiceLocatorException{
		return getCatalogoOpcionAppService().obtenerCatalogoDinamico(idCatalogo, opcion,clickaction);
	}

	private List<CatalogoOpcionVO> cloneList(List<CatalogoOpcionVO> list){
		List<CatalogoOpcionVO> clone = new ArrayList<CatalogoOpcionVO>();

		if (list!=null){
			for (CatalogoOpcionVO vo  :list){
				clone.add(vo);
			}
		}

		return clone;
	}

	@Override
	public List<CatalogoOpcionVO> consultarCatalogoPorId(long idCat, long idCatOp, int ini, int fin, Long ... range) throws ServiceLocatorException {
		return getCatalogoOpcionAppService().obtenerCatalogoPorId(idCat, idCatOp, ini, fin, range);
	}

	@Override
	public List<CatSubareaVO> subareaListByIdArea(long idArea) throws ServiceLocatorException {
		return getCatalogoOpcionAppService().subareaListByIdArea(idArea);
	}
	
	@Override
	public List<CatAreaVO> areaList() throws ServiceLocatorException {
		return getCatalogoOpcionAppService().areaList();
	}

	@Override
	public CatSubareaVO getSubAreaVOByIdAreaIdSubArea(long idArea, long idSubArea) throws ServiceLocatorException {
		return getCatalogoOpcionAppService().getSubAreaVOByIdAreaIdSubArea(idArea, idSubArea);
	}

	@Override
	public List<OfertaFuncionVO> getOfertaFuncionList(long idOfertaEmpleo) throws ServiceLocatorException {
		return getCatalogoOpcionAppService().getOfertaFuncionList(idOfertaEmpleo);
	}
}
