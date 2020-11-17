package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;

import mx.gob.stps.portal.core.oferta.envioSMS.vo.OfertasSMSVO;

@Local
public interface OfertaSmsFacadeLocal {

	public boolean existeOfertaSms(long idCandidato, long idOfertaEmpleo);

	public void save(OfertasSMSVO sms);
	
	public List<Object[]> getListaCandidatos(int idCandidato, int maxRegistros);
}