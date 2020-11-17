package mx.gob.stps.portal.core.persistencia.facade;

import javax.persistence.PersistenceException;

public interface PollCommentFacadeLocal {

	/**
	 * Guarda un registro en la tabla poll_comment
	 * @return 1 en caso de exito al insertar el registro, -1 en caso contrario
	 * @throws PersistenceException
	 */	
	public long create(int item1, int item2, int item3, int item4, int item5, int item6, int item7, int item8, int item9, 
			String description1, String description2, String description3, String description4, String description5);
	
}
