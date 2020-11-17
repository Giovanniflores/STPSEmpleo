package mx.gob.stps.portal.core.persistencia.facade;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;

@Local
public interface OfertaEmpleoPorCanalFacadeLocal {

	/**
	 * Obtiene los id's de las ofertas correspondientes al "canal" que se
	 * envie como parametro
	 * 
	 * @param canal CAPACIDADES, ESTUDIANTES, EGRESADOS, MAYORES
	 * @return List id's oferta
	 * @throws SQLException
	 */
	List<Long> getOfertasPorCanal(String canal) throws SQLException;
	
	/**
	 * Lleva a cabo la busqueda de los id's de las ofertas correspondientes al "canal"
	 * y los ordena en forma Ascendente o Descendete.
	 * 
	 * @param tipoOrdenamiento
	 * @param numeroColumna
	 * @param canal
	 * @return List id's oferta
	 * @throws SQLException
	 */
	List<Long> ordenarOfertasPorCanal(String tipoOrdenamiento, Integer numeroColumna, String canal) throws SQLException;
}
