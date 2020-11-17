//package mx.gob.stps.portal.web.ofertasRiviera.delegate;
//
//import java.util.List;
//
//import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
//import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
//
//public interface OfertasRivieraMayaDelegate {
//
////	public void iniciarOfertasRivieraMaya() throws ServiceLocatorException;
//	
//	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() throws ServiceLocatorException;
//}

package mx.gob.stps.portal.web.ofertasRiviera.delegate;

import java.util.List;

import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public interface OfertasRivieraMayaDelegate {

//	public void iniciarOfertasRivieraMaya() throws ServiceLocatorException;
	
	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() throws ServiceLocatorException;
	public List<OfertaRivieraMayaVO> obtenerOfertasSectur(int edo,String busqueda) throws ServiceLocatorException;
}
