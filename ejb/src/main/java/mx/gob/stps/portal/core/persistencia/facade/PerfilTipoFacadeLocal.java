package mx.gob.stps.portal.core.persistencia.facade;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import mx.gob.stps.portal.persistencia.vo.PerfilTipoVO;

@Local
public interface PerfilTipoFacadeLocal extends Serializable {

	List<PerfilTipoVO> perfilTipoList(long idOcupacion);

	PerfilTipoVO find(long idPerfilTipo);
}