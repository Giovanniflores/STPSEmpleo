package mx.gob.stps.portal.core.oferta.detalle.service;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaIdiomaVO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaVerDetalleVO;

@Remote
public interface OfertaVerDetalleAppServiceRemote {
	
	public int create(OfertaVerDetalleVO vo) throws BusinessException;
	
	public OfertaVerDetalleVO findByPK(long idOfertaVerDetalle, int anio, int mes) throws BusinessException;
	
	public void update(OfertaVerDetalleVO vo);
	
	public void remove(long idOfertaVerDetalle);

	public IdiomaVO consultaIdioma(OfertaIdiomaVO ofertaIdioma);

	//public String consultarContactoOferta(long idContacto);
}
