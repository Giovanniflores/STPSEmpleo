package mx.gob.stps.portal.core.conalep.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.gob.stps.portal.persistencia.vo.*;
import org.apache.log4j.Logger;

import mx.gob.stps.portal.core.persistencia.facade.ConalepCandidatoFacadeLocal;

@Stateless(name = "ConalepCandidatoService",mappedName = "ConalepCandidatoService")
public class ConalepCandidatoServiceImpl implements ConalepCandidatoServiceRemote {
	private static Logger logger = Logger.getLogger(ConalepCandidatoServiceImpl.class);
	
	@EJB
	private ConalepCandidatoFacadeLocal conalepCandidatoFacade;

	@Override
	public void save(ConalepCandidatoVO candidatoVO) {
		try {
			conalepCandidatoFacade.save(candidatoVO);
		} catch (Exception e) {
			logger.error("Error en " + this.getClass().getName() + " --> save " + e);
			e.printStackTrace();			
		}		
	}

	public ConalepPlantelVO findPlantel(long idPlantel) {
		ConalepPlantelVO conalepPlantelVO = null;
		try {
			conalepPlantelVO = conalepCandidatoFacade.findPlantel(idPlantel);
		} catch (Exception e) {
			logger.error("Error en " + this.getClass().getName() + " --> findPlantel " + e);
			e.printStackTrace();
		}
		return conalepPlantelVO;
	}

	@Override
	public ConalepCandidatoVO getConalepCandidato(long idCandidato) {
		ConalepCandidatoVO conalepCandidatoVO = null;
		try {
			conalepCandidatoVO = conalepCandidatoFacade.obtenerConalepCandidato(idCandidato);
		} catch (Exception e) {
			logger.error("Error en " + this.getClass().getName() + " --> getConalepCandidato " + e);
			e.printStackTrace();
		}
		return conalepCandidatoVO;
	}

	@Override
	public List<ConalepAreaNegocioVO> getConalepAreasNegocio()  {
		List<ConalepAreaNegocioVO> areaNegocioVOList = new ArrayList<ConalepAreaNegocioVO>(); 
		try {
			areaNegocioVOList = conalepCandidatoFacade.getConalepAreasNegocio();
		} catch (Exception e) {
			logger.error("Error en " + this.getClass().getName() + " --> getConalepAreasNegocio " + e);
			e.printStackTrace();
		}
		return areaNegocioVOList;
		
	}

	@Override
	public List<ConalepSubAreaNegocioVO> getConalepSubAreasNegocio(long idAreaNegocio) {
		List<ConalepSubAreaNegocioVO> subAreaNegocioVOList = new ArrayList<ConalepSubAreaNegocioVO>();
		try {
			subAreaNegocioVOList = conalepCandidatoFacade.getConalepSubAreasNegocio(idAreaNegocio);
		} catch (Exception e) {
			logger.error("Error en " + this.getClass().getName() + " --> getConalepSubAreasNegocio " + e);
			e.printStackTrace();
		}
		return subAreaNegocioVOList;

	}

	@Override
	public List<ConalepPlantelVO> getConalepPlanteles(long idEntidad) {
		List<ConalepPlantelVO> conalepPlantelVOList = new ArrayList<ConalepPlantelVO>(); 
		try {
			conalepPlantelVOList = conalepCandidatoFacade.getConalepPlanteles(idEntidad);
		} catch (Exception e) {
			logger.error("Error en " + this.getClass().getName() + " --> getConalepPlanteles " + e);
			e.printStackTrace();
		}
		return conalepPlantelVOList; 
	}

	@Override
	public List<ConalepPlanEstudiosVO> getConalepPlanesEstudio(){
		List<ConalepPlanEstudiosVO> conalepPlanEstudiosVO = new ArrayList<ConalepPlanEstudiosVO>();
		try {
			conalepPlanEstudiosVO = conalepCandidatoFacade.getConalepPlanesEstudio();
		} catch (Exception e) {
			logger.error("Error en " + this.getClass().getName() + " --> getConalepPlanesEstudio " + e);
			e.printStackTrace();
		}
		return conalepPlanEstudiosVO;
 
	}

	@Override
	public boolean estaRegistrado(Long idCandidato) {
		try {
			return conalepCandidatoFacade.estaRegistrado(idCandidato);
		} catch (Exception e) {
			logger.error("Error en " + this.getClass().getName() + " --> estaRegistrado " + e);
			e.printStackTrace();
		}
		return Boolean.FALSE;
	}

}
