package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;

@Local
public interface OLAFacadeLocal {

	public List<Integer> consultaCarrerasOLA(int idCarreraOla);
	
	public List<Integer> consultaOcupacionesOLA(int idOcupacionOla);
	
}
