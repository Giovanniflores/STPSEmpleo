package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.persistencia.vo.*;

@Local
public interface ConalepCandidatoFacadeLocal {
	
	void save(ConalepCandidatoVO conalepCandidatoVO) throws PersistenceException;
	ConalepPlantelVO findPlantel(long idPlantel) throws PersistenceException;
	ConalepCandidatoVO obtenerConalepCandidato(long idCandidato) throws PersistenceException;
	List<ConalepAreaNegocioVO> getConalepAreasNegocio() throws PersistenceException;
	List<ConalepSubAreaNegocioVO> getConalepSubAreasNegocio(long idAreaNegocio) throws PersistenceException;
	List<ConalepPlantelVO> getConalepPlanteles(long idEntidad) throws PersistenceException;
	List<ConalepPlanEstudiosVO> getConalepPlanesEstudio() throws PersistenceException;
	boolean estaRegistrado(Long idCandidato) throws PersistenceException;

}
