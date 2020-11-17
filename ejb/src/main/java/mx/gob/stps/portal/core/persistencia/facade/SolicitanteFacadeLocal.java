package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.persistencia.entity.Solicitante;
import mx.gob.stps.portal.persistencia.vo.SolicitanteVO;

@Local
public interface SolicitanteFacadeLocal {

	Long save(SolicitanteVO solicitanteVO)  throws PersistenceException;
	
	
	void actualizarCorreoElectronico(Solicitante entity) throws PersistenceException;
	
	Solicitante findByIdCandidato(Long idCandidato) throws PersistenceException;
}
