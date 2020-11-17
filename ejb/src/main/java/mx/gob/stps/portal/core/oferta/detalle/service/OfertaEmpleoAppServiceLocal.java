package mx.gob.stps.portal.core.oferta.detalle.service;

import javax.ejb.Local;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;

@Local
public interface OfertaEmpleoAppServiceLocal {
	
	public void actualizaTotalOfertasPublicadasSTPS();

	public OfertaEmpleoVO buscarOfertaEmpleo(long idOfertaEmpleo) throws BusinessException;
}