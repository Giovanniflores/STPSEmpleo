package mx.gob.stps.portal.core.ws.ofertas.service;

import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;



@Remote
public interface OfertasDYEAppServiceRemote {
	
	public List<OfertaEmpleoOutVO> buscarVacantesDyE();

}
