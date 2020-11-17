package mx.gob.stps.portal.web.conalep.delegate;

import java.util.List;

import mx.gob.stps.portal.persistencia.vo.*;
import mx.gob.stps.portal.core.conalep.service.ConalepCandidatoServiceRemote;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;

public class ConalepCandidatoDelegateImpl implements ConalepCandidatoDelegate {
	
	private static final String JNDI_NAME = "ConalepCandidatoService#mx.gob.stps.portal.core.conalep.service.ConalepCandidatoServiceRemote"; 

	private static final String CATALOGO_CONALEP_AREA_NEGOCIO = "conalepAreaNegocio";
	private static final String CATALOGO_CONALEP_SUB_AREA_NEGOCIO = "conalepSubAreaNegocio";
	private static final String CATALOGO_CONALEP_PLANTEL = "conalepPlantel";
	private static final String CATALOGO_CONALEP_PLAN_ESTUDIOS = "conalepPlanEstudios";
	
	private static ConalepCandidatoDelegate instance = new ConalepCandidatoDelegateImpl();	
	
	public ConalepCandidatoDelegateImpl() { }
	
	public static ConalepCandidatoDelegate getInstance() {
		return instance;
	}
	
	private ConalepCandidatoServiceRemote getService() throws ServiceLocatorException {
		ConalepCandidatoServiceRemote ejb = (ConalepCandidatoServiceRemote) ServiceLocator.getSessionRemote(JNDI_NAME);
		return ejb;
	}

	@Override
	public void save(ConalepCandidatoVO candidatoVO) throws BusinessException, ServiceLocatorException {
		getService().save(candidatoVO);
	}

	@Override
	public ConalepPlantelVO findPlantel(long idPlantel) throws BusinessException, ServiceLocatorException {
		return getService().findPlantel(idPlantel);
	}

	@Override
	public ConalepCandidatoVO obtenerConalepCandidato(long idCandidato) throws BusinessException, ServiceLocatorException {
		return getService().getConalepCandidato(idCandidato);
	}
	
	@Override
	public CatalogoVO getCatalogo(String catalogoSolicitado) throws BusinessException, ServiceLocatorException {
		CatalogoVO result =  new CatalogoVO("value", "label");

		ConalepCandidatoServiceRemote service = getService();

	    if(catalogoSolicitado.equals(CATALOGO_CONALEP_AREA_NEGOCIO)) {
			List<ConalepAreaNegocioVO> resultList = service.getConalepAreasNegocio();
			for (ConalepAreaNegocioVO vo : resultList) {
				result.addItem(vo.getAreaNegocio(), vo.getAreaNegocio(), String.valueOf(vo.getIdAreaNegocio()));
			}
		} else if(catalogoSolicitado.equals(CATALOGO_CONALEP_SUB_AREA_NEGOCIO)) {		// TODO: Delete this block else-if
			List<ConalepSubAreaNegocioVO> resultList = service.getConalepSubAreasNegocio(0);
			for (ConalepSubAreaNegocioVO vo : resultList) {
				result.addItem(vo.getSubAreaNegocio(), vo.getSubAreaNegocio(), String.valueOf(vo.getIdSubAreaNegocio()));
			}
	    } else if(catalogoSolicitado.equals(CATALOGO_CONALEP_PLANTEL)) {
	    	List<ConalepPlantelVO> resultList = service.getConalepPlanteles(0);			// TODO: Delete this block else-if
	    	for(ConalepPlantelVO vo : resultList) {
	    		result.addItem(vo.getPlantel(), vo.getPlantel(), String.valueOf(vo.getIdPlantel()));
	    	} 
	    } else if(catalogoSolicitado.equals(CATALOGO_CONALEP_PLAN_ESTUDIOS)) {
	    	List<ConalepPlanEstudiosVO> resultList = service.getConalepPlanesEstudio();
	    	for (ConalepPlanEstudiosVO vo : resultList) {
	    		result.addItem(vo.getPlanEstudios(), vo.getPlanEstudios(), String.valueOf(vo.getIdPlanEstudios()));
	    	}  
	    }
	    
	    return result;
	}

	@Override
	public CatalogoVO obtenerCatalogoConalepAreaNegocio() throws BusinessException, ServiceLocatorException {
		CatalogoVO result =  new CatalogoVO("value", "label");

		ConalepCandidatoServiceRemote service = getService();
		List<ConalepAreaNegocioVO> resultList = service.getConalepAreasNegocio();
		for (ConalepAreaNegocioVO vo : resultList) {
			result.addItem(vo.getAreaNegocio(), vo.getAreaNegocio(), String.valueOf(vo.getIdAreaNegocio()));
		}

		return result;
	}

	@Override
	public CatalogoVO obtenerCatalogoConalepSubAreaNegocio(long idAreaNegocio) throws BusinessException, ServiceLocatorException {
		CatalogoVO result =  new CatalogoVO("value", "label");

		ConalepCandidatoServiceRemote service = getService();
		List<ConalepSubAreaNegocioVO> resultList = service.getConalepSubAreasNegocio(idAreaNegocio);
		for (ConalepSubAreaNegocioVO vo : resultList) {
			result.addItem(vo.getSubAreaNegocio(), vo.getSubAreaNegocio(), String.valueOf(vo.getIdSubAreaNegocio()));
		}

		return result;
	}

	@Override
	public CatalogoVO obtenerCatalogoConalepPlantel(long idEntidad) throws BusinessException, ServiceLocatorException {
		CatalogoVO result =  new CatalogoVO("value", "label");

		ConalepCandidatoServiceRemote service = getService();
		List<ConalepPlantelVO> resultList = service.getConalepPlanteles(idEntidad);
		for (ConalepPlantelVO vo : resultList) {
			result.addItem(vo.getPlantel(), vo.getPlantel(), String.valueOf(vo.getIdPlantel()));
		}

		return result;
	}

	@Override
	public CatalogoVO obtenerCatalogoPlanEstudios() throws BusinessException, ServiceLocatorException {
		CatalogoVO result =  new CatalogoVO("value", "label");

		ConalepCandidatoServiceRemote service = getService();
		List<ConalepPlanEstudiosVO> resultList = service.getConalepPlanesEstudio();
		for (ConalepPlanEstudiosVO vo : resultList) {
			result.addItem(vo.getPlanEstudios(), vo.getPlanEstudios(), String.valueOf(vo.getIdPlanEstudios()));
		}

		return result;
	}

	@Override
	public boolean estaRegistrado(Long idCandidato) throws BusinessException, ServiceLocatorException {
		return getService().estaRegistrado(idCandidato);
	}

}
