package mx.gob.stps.portal.core.oferta.pregunta.service;

import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.pregunta.vo.OfertaPreguntaVO;

@Remote
public interface OfertaPreguntaAppServiceRemote {
	
	public int create(OfertaPreguntaVO vo) throws BusinessException;
	
	public OfertaPreguntaVO findById(long idOfertaPregunta) throws BusinessException;
	
	public void update(OfertaPreguntaVO vo) throws BusinessException;
	
	public void remove(long idOfertaPregunta) throws BusinessException;
	
	public List<OfertaPreguntaVO> ofertaPreguntasList(long idOfertaEmpleo) throws BusinessException;
}
