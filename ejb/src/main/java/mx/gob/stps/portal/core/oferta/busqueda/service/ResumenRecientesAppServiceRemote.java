package mx.gob.stps.portal.core.oferta.busqueda.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import mx.gob.stps.portal.core.oferta.vo.MiOfertaRecienteVO;
import mx.gob.stps.portal.core.oferta.vo.PostulacionRecienteVO;


/**
 * Define las operaciones que pueden realizarse remotamente sobre las ofertas
 * o postulaciones recientes.
 * @author jose.jimenez
 *
 */
@Remote
public interface ResumenRecientesAppServiceRemote {

	/**
	 * Obtiene los datos a mostrar en el apartado &quot;Mis Ofertas Recientes&quot;
	 * de la empresa relacionada.
	 * @param idEmpresa identificador de la empresa relacionada a las ofertas de empleo
	 *             a obtener
	 * @return el conjunto de ofertas recientemente creadas por la empresa
	 * @throws SQLException
	 */
	public List<MiOfertaRecienteVO> obtenerMisOfertasRecientes(long idEmpresa)
	throws SQLException;
	
	/**
	 * Obtiene los datos a mostrar en el apartado &quot;Postulaciones Recientes&quot;
	 * de la empresa relacionada.
	 * @param idEmpresa identificador de la empresa relacionada a las postulaciones
	 *             a obtener
	 * @return el conjunto de postulaciones recientemente hechas
	 * @throws SQLException
	 */
	public List<PostulacionRecienteVO> obtenerPostulacionesRecientes(long idEmpresa)
	throws SQLException;
	
	/**
	 * Obtine el número de ofertas activas de la empresa
	 * @param idEmpresa
	 * @return número de ofertas activas
	 * @throws SQLException
	 */
	public Long getCountOfertasActivas(long idEmpresa) throws SQLException;
}
