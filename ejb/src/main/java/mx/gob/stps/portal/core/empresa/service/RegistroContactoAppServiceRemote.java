package mx.gob.stps.portal.core.empresa.service;

import java.util.List;

import javax.ejb.Remote;

//TODO ELIMINAR CLASE YA NO SE UTILIZA
//@Remote
public interface RegistroContactoAppServiceRemote {


	/**
	 * Registra un contacto
	 * @param vo
	 * @return long
	 */	
	//public long registrarContacto(RegistroContactoVO vo, long idUsuario);

	/**
	 * BUsca un contacto en base a su identificador
	 * @param vo
	 * @return RegistroContactoVO
	 */		
	//public RegistroContactoVO findContactoById(long id);	
	

	//public List<RegistroContactoVO> findAllContactsByIdEmpresa(long idEmpresa);		
	
	/**
	 * Actualiza los datos de un contacto
	 * @param vo
	 * @return long
	 */		
	//public long actualizarContacto(RegistroContactoVO vo, long idUsuario);	
	
	/**
	 * Obtiene todos los contactos de una empresa
	 * @param long
	 * @return List<RegistroContactoVO>
	 */			
	//public List<RegistroContactoVO> findAllByIdEmpresa(long id);

	/**
	 * Actualiza los contactos
	 * @param long
	 * @return List<RegistroContactoVO>
	 */
	
	//public void actualizaEstatus(long idContacto, int estatus);
	
	//public void actualizaEstatus(long idContacto, int estatus, long idUsuario);
	
	//public int countOffersByIdContact(long idContacto, int estatus);


}
