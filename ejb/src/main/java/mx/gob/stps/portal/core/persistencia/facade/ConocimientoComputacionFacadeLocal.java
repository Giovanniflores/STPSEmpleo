package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;

@Local
public interface ConocimientoComputacionFacadeLocal {
	
	public long registraConocimientosComputacion(long idPropietario, int idTipoPropietario, int procesadorTxt, int hojaCalculo, int internet, int redesSociales, String otros);
	
	public ConocimientoComputacionVO consultaConocimientosComputacion(long idPropietario, int idTipoPropietario);
	
	/**
	 * Guarda conocimientos computación del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param long idCandidatoComputacion
	 * @param long idCandidatoComputacion
	 * @param int procesadorTxt
	 * @param int hojaCalculo
	 * @param int internet
	 * @param int internet
	 * @param String otros
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public long actualizarConocimientosComputacion(long idCandidatoComputacion, long idPropietario, int idTipoPropietario, 
			                                       int procesadorTxt, int hojaCalculo, int internet, int redesSociales, String otros) throws PersistenceException;
	
	/**
	 * Obtiene conocimientos computacion del perfil de un candidato
	 * @author Sergio Téllez
	 * @since 26/05/2012
	 * @param idCandidato
	 * @throws BusinessException, ServiceLocatorException
	 * @return long
	 **/
	public ConocimientoComputacionVO findConocimientosComputacion(long idPropietario, int idTipoPropietario) throws PersistenceException;

	public void borrarConocimientosComputacion(long idPropietario, int idTipoPropietario);
}
