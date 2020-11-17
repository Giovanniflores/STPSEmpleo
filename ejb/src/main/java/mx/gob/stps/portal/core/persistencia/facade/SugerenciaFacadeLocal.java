package mx.gob.stps.portal.core.persistencia.facade;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

@Local
public interface SugerenciaFacadeLocal {

	/**
	 * Guarda un registro en la tabla sugerencia
	 * @return 1 en caso de exito al insertar el registro, -1 en caso contrario
	 * @throws PersistenceException
	 */
	public long create(long idCategoria, String asunto, String mensaje, String email, String nombre, String apellido1, String apellido2, String telefono, int tipoTelefono) throws PersistenceException;
}