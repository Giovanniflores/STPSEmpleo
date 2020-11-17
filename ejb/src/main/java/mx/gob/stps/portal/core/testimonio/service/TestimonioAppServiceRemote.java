/**
 * 
 */
package mx.gob.stps.portal.core.testimonio.service;

import java.sql.SQLException;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.testimonio.vo.TestimonioVO;

/**
 * @author concepcion.aguilar
 * Bean Remoto que sirve para conectar a la capa web con la capa de negocio
 */
@Remote
public interface TestimonioAppServiceRemote {
	/**
	 * Realiza las validaciones de los datos de entrada y llama a la clase de la capa de persistencia para 
	 * almacenar los datos de la tabla testimonio
	 * @param vo objeto que contiene los datos necesarios para generar un registro en 
	 * la tabla Testimonio
	 * @return el id de la tabla Testimonio generado por la BD
	 */
	public long registraDatos(TestimonioVO vo) throws PersistenceException;
	
	/**
	 * Realiza las validaciones de los datos de entrada y llama a la clase de la capa de persistencia para 
	 * realizar una consulta para recuperar datos relacionados con el testimonio
	 * @param vo el objeto con los datos necesarios para recuperar el nombre y la empresa
	 * @return el mismo objeto VO ya con los datos de nombre y empresa llenos
	 * @throws Exception
	 */
	public TestimonioVO recuperaDatos(TestimonioVO vo) throws SQLException;
	
	/**
	 * Llama a la clase de la capa de persistencia para realizar una consulta que contiene un
	 * testimonio aleatorio y su propietrio
	 * @return el vo con la descripcion del testimonio y su propietario
	 * @throws SQLException
	 */
	public TestimonioVO recuperaDatosIndex() throws SQLException;

	public TestimonioVO consultaTestimonio(long idTestimonio) throws PersistenceException;
	
	
	/**
	 * Guarda una sugerencia
	 * @return 1 en caso de exito al guardar sugerencia, -1 en caso contrario
	 * @throws PersistenceException
	 */
	public long saveSuggestion(long idCategoria, String asunto, String mensaje, String email, String nombre, String apellido1, String apellido2, String telefono, int tipoTelefono) throws PersistenceException;
	
	/**
	 * Guarda una respuesta a la encuesta
	 * @return 1 en caso de exito al guardar respuesta, -1 en caso contrario
	 * @throws PersistenceException
	 */	
	public long savePollComment(int item1, int item2, int item3, int item4, int item5, int item6, int item7, int item8, int item9, 
			String description1, String description2, String description3, String description4, String description5) throws PersistenceException; 
}
