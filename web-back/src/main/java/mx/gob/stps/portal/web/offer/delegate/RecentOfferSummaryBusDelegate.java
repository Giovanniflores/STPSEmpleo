package mx.gob.stps.portal.web.offer.delegate;

import java.util.List;

import mx.gob.stps.portal.core.oferta.vo.MiOfertaRecienteVO;
import mx.gob.stps.portal.core.oferta.vo.PostulacionRecienteVO;


/**
 * Define las operaciones a delegar a la capa de negocio sobre la ofertas y 
 * postulaciones recientes. 
 * @author jose.jimenez
 *
 */
public interface RecentOfferSummaryBusDelegate {
	
	
	/**
	 * Obtiene las &uacute;ltimas ofertas activas registradas por la empresa.
	 * @param idEmpresa identificador de la empresa de la que se desea obtener la
	 * informaci&oacute;n 
	 * @return el listado a mostrar de las &uacute;ltimas ofertas activas.
	 */
	public List<MiOfertaRecienteVO> getMyRecentOffers(long idEmpresa);
	
	/**
	 * Obtiene las &uacute;ltimas postulaciones a ofertas activas registradas 
	 * por la empresa.
	 * @param idEmpresa identificador de la empresa de la que se desea obtener la
	 * informaci&oacute;n 
	 * @return el listado a mostrar de las &uacute;ltimas postulaciones a ofertas activas.
	 */
	public List<PostulacionRecienteVO> getRecentPostulations(long idEmpresa);

	/**
	 * Obtine el número de ofertas activas de la empresa
	 * @param idEmpresa
	 * @return número de ofertas activas
	 */
	public Long getCountOfertasActivas(long idEmpresa);
}
