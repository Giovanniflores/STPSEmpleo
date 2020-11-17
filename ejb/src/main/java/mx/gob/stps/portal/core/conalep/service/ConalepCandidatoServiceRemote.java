package mx.gob.stps.portal.core.conalep.service;

import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.persistencia.vo.*;

@Remote
public interface ConalepCandidatoServiceRemote {
	
	void save(ConalepCandidatoVO candidatoVO) ;
	ConalepPlantelVO findPlantel(long idPlantel);
	ConalepCandidatoVO getConalepCandidato(long idCandidato);
	List<ConalepAreaNegocioVO> getConalepAreasNegocio();
	List<ConalepSubAreaNegocioVO> getConalepSubAreasNegocio(long idAreaNegocio);
	List<ConalepPlantelVO> getConalepPlanteles(long idEntidad);
	List<ConalepPlanEstudiosVO> getConalepPlanesEstudio();
	boolean estaRegistrado(Long idCandidato);

}
