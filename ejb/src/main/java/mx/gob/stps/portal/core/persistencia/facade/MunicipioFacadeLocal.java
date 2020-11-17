package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;
import mx.gob.stps.portal.persistencia.vo.DomicilioVO;
import mx.gob.stps.portal.core.domicilio.vo.MunicipioVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

@Local
public interface MunicipioFacadeLocal {
	
	public List<MunicipioVO> consultaMunicipioByName(long idEntidad, String nombreMunicipio) throws PersistenceException;
	
	public void setEntidadMunicipio(DomicilioVO domicilio) throws PersistenceException;
	
	public List<CatalogoOpcionVO> getLocalidades(long idEntidad, long idMunicipio) throws PersistenceException;
}
