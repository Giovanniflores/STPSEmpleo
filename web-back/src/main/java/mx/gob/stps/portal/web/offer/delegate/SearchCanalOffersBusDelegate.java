package mx.gob.stps.portal.web.offer.delegate;

import java.sql.SQLException;
import java.util.List;

import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;


/**
 * Define las operaciones a delegar a la capa de negocio sobre la ofertas a 
 * mostrar por canal. 
 * @author jose.jimenez
 *
 */
public interface SearchCanalOffersBusDelegate {
	
	/**
	 * Obtiene el listado de ofertas para el tipo de consulta definido por el 
	 * valor de {@code searchType}.
	 * @param searchType representa el canal para el que se est&aacute; realizando
	 * la consulta.
	 * @param resultsNumber indica el n&uacute;mero m&aacute;ximo de registros a
	 * regresar por la consulta.
	 * @return el listado de ofertas correspondientes a la consulta del canal 
	 * indicado por el valor de {@code searchType}.
	 */
	List<Long> getOffersPerCanal(String searchType) throws SQLException, TechnicalException, ServiceLocatorException;

	/**
	 * Obtiene el listado de ofertas de acuerdo a los filtros especificados 
	 * @return el listado de ofertas correspondientes a la consulta especificada
	 */
	public List<OfertaPorCanalVO> getOffersPerFilter(long idoffer, int district, String dateInitAdd, String dateFinalAdd, String dateInitUpd, String dateFinalUpd, String idPortal, String email, int status, int deleteRazon,
			String idEmpresa, String contacto, String telefono, String idEntidadSelect, String idMunicipio, String salarioRango, String salario, String titulo) throws TechnicalException, ServiceLocatorException;
	
	/**
	 * Obtiene el listado de registros en bitácora asociado al propietario especificado
	 * @return el listado de registros correspondientes al propietario especificado
	 */
	public List<BitacoraVO> getBitacora(long idOffer) throws TechnicalException, ServiceLocatorException;

	List<OfertaPorCanalVO> getOffersPerFilterAdminPublisher(long idoffer,
			String idPortal, String nombreEmpresa) throws ServiceLocatorException;	
	
	List<Long> ordenarOfertasPorCanal(String tipoOrdenamiento, Integer numeroColumna, String canal) throws TechnicalException, ServiceLocatorException, SQLException;
	
}
