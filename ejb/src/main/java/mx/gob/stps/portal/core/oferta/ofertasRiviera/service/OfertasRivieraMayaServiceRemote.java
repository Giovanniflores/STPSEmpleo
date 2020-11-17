////package mx.gob.stps.portal.core.oferta.ofertasRiviera.service;
////
////import java.util.List;
////
////import javax.ejb.Remote;
////
////import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
////
////
////@Remote
////public interface OfertasRivieraMayaServiceRemote {
////
//////	public void initTimer();
//////
//////	public void destroyInitTimer();
////	
////	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() ;
////	
////	
////}
//
//
//package mx.gob.stps.portal.core.oferta.ofertasRiviera.service;
//
//import java.util.List;
//
//import javax.ejb.Remote;
//
//import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;
//
//
//@Remote
//public interface OfertasRivieraMayaServiceRemote {
//
////	public void initTimer();
////
////	public void destroyInitTimer();
//	
//	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() ;
//	public List<OfertaRivieraMayaVO> obtenerOfertasSectur(int edo, String busqueda) ;
//	
//	
//}
//


package mx.gob.stps.portal.core.oferta.ofertasRiviera.service;

import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.oferta.vo.OfertaRivieraMayaVO;


@Remote
public interface OfertasRivieraMayaServiceRemote {

//	public void initTimer();
//
//	public void destroyInitTimer();
	
	public List<OfertaRivieraMayaVO> obtenerOfertasRiveraMaya() ;
	public List<OfertaRivieraMayaVO> obtenerOfertasSectur(int edo, String busqueda) ;
	
	
}
