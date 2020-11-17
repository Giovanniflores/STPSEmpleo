package mx.gob.stps.portal.core.oferta.detalle.service;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaPostulacionVO;

@Remote
public interface OfertaPostulacionAppServiceRemote {

	public int create(OfertaPostulacionVO vo) throws BusinessException;
	
	public OfertaPostulacionVO findByPK(long idOfertaEmpleo, int anio, int mes) throws BusinessException;
	
	public void update(OfertaPostulacionVO vo) throws BusinessException;
	
	public void remove(long idOfertaEmpleo) throws BusinessException;
}
