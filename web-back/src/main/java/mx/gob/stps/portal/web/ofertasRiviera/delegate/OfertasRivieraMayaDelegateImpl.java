////package mx.gob.stps.portal.web.ofertasRiviera.delegate;
////
////import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_OFERTAS_RIVIERA_MAYA;
////
////import java.util.List;
////
////import mx.gob.stps.portal.core.oferta.ofertasRiviera.service.OfertasRivieraMayaServiceRemote;
////import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
////import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
////import mx.gob.stps.portal.web.infra.service.ServiceLocator;
////
////public final class OfertasRivieraMayaDelegateImpl implements OfertasRivieraMayaDelegate{
////	
////	private static OfertasRivieraMayaDelegate instance = new OfertasRivieraMayaDelegateImpl();
////	
////	private OfertasRivieraMayaDelegateImpl(){};
////	
////	public static OfertasRivieraMayaDelegate getInstance() {
////		return instance;
////	}
////
//////	@Override
//////	public void iniciarOfertasRivieraMaya()throws ServiceLocatorException {
//////		getOfertasRivieraMayaService().initTimer();
//////	}
//////	
////	private OfertasRivieraMayaServiceRemote getOfertasRivieraMayaService () throws ServiceLocatorException{
////		OfertasRivieraMayaServiceRemote ejb = (OfertasRivieraMayaServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_OFERTAS_RIVIERA_MAYA);
////		return ejb;
////	}
////
////	@Override
////	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() throws ServiceLocatorException {
////		
////		return getOfertasRivieraMayaService().obtenerOfertasRiveraMaya();
////	}
////
////}
//
//package mx.gob.stps.portal.web.ofertasRiviera.delegate;
//
//import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_OFERTAS_RIVIERA_MAYA;
//
//import java.util.List;
//
//import mx.gob.stps.portal.core.oferta.ofertasRiviera.service.OfertasRivieraMayaServiceRemote;
//import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
//import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
//import mx.gob.stps.portal.web.infra.service.ServiceLocator;
//
//public final class OfertasRivieraMayaDelegateImpl implements OfertasRivieraMayaDelegate{
//	
//	private static OfertasRivieraMayaDelegate instance = new OfertasRivieraMayaDelegateImpl();
//	
//	private OfertasRivieraMayaDelegateImpl(){};
//	
//	public static OfertasRivieraMayaDelegate getInstance() {
//		return instance;
//	}
//
////	@Override
////	public void iniciarOfertasRivieraMaya()throws ServiceLocatorException {
////		getOfertasRivieraMayaService().initTimer();
////	}
////	
//	private OfertasRivieraMayaServiceRemote getOfertasRivieraMayaService () throws ServiceLocatorException{
//		OfertasRivieraMayaServiceRemote ejb = (OfertasRivieraMayaServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_OFERTAS_RIVIERA_MAYA);
//		return ejb;
//	}
//
//	@Override
//	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() throws ServiceLocatorException {
//		
//		return getOfertasRivieraMayaService().obtenerOfertasRiveraMaya();
//	}
//	
//	@Override
//	public List<OfertaRivieraMayaVO> obtenerOfertasSectur(int edo,String busqueda) throws ServiceLocatorException {
//		
//		return getOfertasRivieraMayaService().obtenerOfertasSectur(edo,busqueda);
//	}
//
//}

package mx.gob.stps.portal.web.ofertasRiviera.delegate;

import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_OFERTAS_RIVIERA_MAYA;

import java.util.List;

import mx.gob.stps.portal.core.oferta.ofertasRiviera.service.OfertasRivieraMayaServiceRemote;
import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;

public final class OfertasRivieraMayaDelegateImpl implements OfertasRivieraMayaDelegate{
	
	private static OfertasRivieraMayaDelegate instance = new OfertasRivieraMayaDelegateImpl();
	
	private OfertasRivieraMayaDelegateImpl(){};
	
	public static OfertasRivieraMayaDelegate getInstance() {
		return instance;
	}

//	@Override
//	public void iniciarOfertasRivieraMaya()throws ServiceLocatorException {
//		getOfertasRivieraMayaService().initTimer();
//	}
//	
	private OfertasRivieraMayaServiceRemote getOfertasRivieraMayaService () throws ServiceLocatorException{
		OfertasRivieraMayaServiceRemote ejb = (OfertasRivieraMayaServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_OFERTAS_RIVIERA_MAYA);
		return ejb;
	}

	@Override
	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() throws ServiceLocatorException {
		
		return getOfertasRivieraMayaService().obtenerOfertasRiveraMaya();
	}
	
	@Override
	public List<OfertaRivieraMayaVO> obtenerOfertasSectur(int edo,String busqueda) throws ServiceLocatorException {
		
		return getOfertasRivieraMayaService().obtenerOfertasSectur(edo,busqueda);
	}

}
