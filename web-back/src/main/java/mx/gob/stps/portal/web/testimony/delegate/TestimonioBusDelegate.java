/**
 * 
 */
package mx.gob.stps.portal.web.testimony.delegate;

import java.sql.SQLException;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

/**
 * @author concepcion.aguilar
 * Interface mediante la cual se hace la llamada a la capa de negocio del repositor EJB
 */
public interface TestimonioBusDelegate {
	/**
	 * Localiza el servicio remoto que da acceso a la capa de negocio que registra datos en la tabla testimonio 
	 * @param vo objeto con los datos necesarios para generar un registro en la tabla Testimonio
	 * @return id del registro generado
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public long registraTestimonio(TestimonioVO vo) throws BusinessException, ServiceLocatorException;
		
	/**
	 * Localiza el servicio remoto que da acceso a la capa de negocio que recupera datos de la base de datos mediante
	 * un objeto EJB remoto
	 * @param vo objeto con los datos necesarios para realizar una busqueda en la BD y 
	 * regresar el nombre y la empresa
	 * @return objeto con el nombre y la empresa encontrados en la BD
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public TestimonioVO recuperaDatos(TestimonioVO vo) throws SQLException, ServiceLocatorException;
	
	/**
	 * Localiza el servicio remoto que da acceso a la capa de negocio que recupera datos de la base de datos mediante
	 * un objeto EJB remoto
	 * @param vo objetos con los datos necesarios para consultar la BD 
	 * @return el mismo objeto con la descripcion y el propietario del testimonio
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public TestimonioVO recuperaDatosIndex() throws SQLException, ServiceLocatorException;
	
	/**
	 * Localiza el servicio remoto que da acceso a la capa de negocio para crear una sugerencia
	 * @return 1 en caso de exito al guardar sugerencia, -1 en caso contrario
	 * @throws ServiceLocatorException
	 */
	public long saveSuggestion(long idCategoria, String asunto, String mensaje, String email, String nombre, String apellido1, String apellido2, String telefono, int tipoTelefono) throws ServiceLocatorException;
	
	/**
	 * Localiza el servicio remoto que da acceso a la capa de negocio para almacenar la respuesta de un usuario a una encuesta
	 * @return 1 en caso de exito al guardar resultado, -1 en caso contrario
	 * @throws ServiceLocatorException
	 */	
	public long savePollComment(int item1, int item2, int item3, int item4, int item5, int item6, int item7, int item8, int item9, 
			String description1, String description2, String description3, String description4, String description5)throws ServiceLocatorException;
}
