package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;

@Local
public interface OfertaPreguntaFacadeLocal {

	public int create(OfertaPreguntaVO vo) throws PersistenceException;
	
	public OfertaPreguntaVO findById(long idOfertaPregunta) throws PersistenceException;
	
	public void update(OfertaPreguntaVO vo) throws PersistenceException;
	
	public void remove(long idOfertaPregunta) throws PersistenceException;
	
	public List<OfertaPreguntaVO> ofertaPreguntasList(long idOfertaEmpleo) throws PersistenceException;
}
