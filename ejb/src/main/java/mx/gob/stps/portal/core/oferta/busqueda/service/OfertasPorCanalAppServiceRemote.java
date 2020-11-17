package mx.gob.stps.portal.core.oferta.busqueda.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;


/**
 * Define las operaciones que pueden realizarse remotamente sobre los despliegues
 * de ofertas por canal.
 * @author jose.jimenez
 *
 */
@Remote
public interface OfertasPorCanalAppServiceRemote {

	/**
	 * Obtiene un conjunto de ofertas definidas por el tipo de consulta a hacer,
	 * determinado por el valor de {@code searchType}. Cada tipo de consulta 
	 * corresponde a los canales mostrados en la interfaz para candidatos del sitio.
	 * @param searchType representa el tipo de consulta a ejecutar. Los valores
	 * que se reconocen son: &quot;ESTUDIANTES&quot;, &quot;EGRESADOS&quot;, 
	 * &quot;MAYORES&quot;, &quot;CAPACIDADES&quot;.
	 * @param numRegistros indica el n&uacute;mero de registros a regresar
	 * @return el conjunto de ofertas a mostrar en el listado por canal
	 * @throws SQLException si existe alg&uacute;n problema al extraer los datos
	 * @throws TechnicalException si el valor de {@code searchType} no corresponde a
	 * alguno v&aacute;lido para la ejecuci&oacute;n de la consulta.
	 */
	public List<Long> obtenerOfertasPorCanal(String searchType) throws SQLException, TechnicalException;
	
	/**
	 * Obtiene un conjunto de ofertas definidas por filtros de consulta
	 * @return el conjunto de ofertas a mostrar en el listado filtrado
	 */
	public List<OfertaPorCanalVO> getOffersPerFilter(long idoffer, int district, String dateInitAdd, String dateFinalAdd, String dateInitUpd, String dateFinalUpd, String idPortal, String email, int status, int deleteRazon, 
				String idEmpresa, String contacto, String telefono, String idEntidadSelect, String idMunicipio, String salarioRango, String salario, String titulo) throws TechnicalException;
	
	/**
	 * Obtiene un conjunto de registros de bitacora relacionados con ofertas
	 * @return el conjunto de registros a mostrar en el listado
	 */
	public List<BitacoraVO> getBitacora(long idEmpresa) throws TechnicalException;

	public List<OfertaPorCanalVO> getOffersPerFilterAdminPublisher(
			long idoffer, String idPortal, String nombreEmpresa);	
	
	List<Long> ordenarOfertasPorCanal(String tipoOrdenamiento, Integer numeroColumna, String canal) throws SQLException, TechnicalException;
	
}
