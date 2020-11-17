package mx.gob.stps.portal.core.oferta.busqueda.service.impl;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasPorCanalAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.persistencia.facade.BitacoraFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoPorCanalFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaFacadeLocal;
import org.apache.log4j.Logger;


@Stateless(name = "OfertasPorCanalAppService", mappedName = "OfertasPorCanalAppService")
public class OfertasPorCanalAppService implements OfertasPorCanalAppServiceRemote {
	@EJB private OfertaFacadeLocal ofertaFacade;
	@EJB private BitacoraFacadeLocal bitacoraFacade;
	@EJB private OfertaEmpleoPorCanalFacadeLocal ofertaEmpleoPorCanalFacade;
	private static Logger logger = Logger.getLogger(OfertasPorCanalAppService.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Long> obtenerOfertasPorCanal(String searchType) throws SQLException, TechnicalException {
		
		 return ofertaEmpleoPorCanalFacade.getOfertasPorCanal(searchType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<OfertaPorCanalVO> getOffersPerFilter(long idoffer, int district, String dateInitAdd, String dateFinalAdd, String dateInitUpd,
			String dateFinalUpd, String idPortal, String email, int status, int deleteRazon, String idEmpresa, String contacto, String telefono, String idEntidadSelect, String idMunicipio, String salarioRango, String salario, String titulo) throws TechnicalException {
		return ofertaFacade.getOffersPerFilter(idoffer, district, dateInitAdd, dateFinalAdd, dateInitUpd, dateFinalUpd, idPortal, email, status, deleteRazon, 
				idEmpresa, contacto, telefono, idEntidadSelect, idMunicipio, salarioRango, salario, titulo);
	}

	@Override
	public List<BitacoraVO> getBitacora(long idOffer) {
		int numrecords = 5;
		List<BitacoraVO> movimientos = bitacoraFacade.consultaBitacora(idOffer, TIPO_PROPIETARIO.OFERTA, numrecords);
		return movimientos;
	}

	@Override
	public List<OfertaPorCanalVO> getOffersPerFilterAdminPublisher(
			long idoffer, String idPortal, String nombreEmpresa) {
		return ofertaFacade.getOffersPerFilterAdminPublisher(idoffer,  idPortal, nombreEmpresa);
	}

	@Override
	public List<Long> ordenarOfertasPorCanal(String tipoOrdenamiento, Integer numeroColumna, String canal) throws SQLException,	TechnicalException {

		if(tipoOrdenamiento == null || numeroColumna == null)
			throw  new TechnicalException("Se debe indicar el tipo de ordenamiento y columna a ordenar");
		
		return ofertaEmpleoPorCanalFacade.ordenarOfertasPorCanal(tipoOrdenamiento, numeroColumna, canal);
	}	

}
