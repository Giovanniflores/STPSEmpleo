package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;

@Local
public interface OfertaCarreraEspecFacadeLocal {

	public Long save(OfertaCarreraEspecialidadVO vo) throws PersistenceException;
	
	public int update(OfertaCarreraEspecialidadVO vo, Long idOfertaEmpleo) throws PersistenceException;
	
	
}
