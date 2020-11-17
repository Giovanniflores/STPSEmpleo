package mx.gob.stps.portal.core.empresa.service;

import javax.ejb.Remote;


/**
 * Proporciona el acceso a los servicios para el modulo de Terceras Empresas
 * 
 * @author haydee.vertti
 *
 */
//TODO A ELIMINAR
//@Remote
public interface TerceraEmpresaAppServiceRemote {

	/**
	 * Registra una tercera empresa
	 * @param vo
	 * @param long
	 * @return long
	 */	
	//public long registrarEmpresa(TerceraEmpresaVO vo, long idUsuario);	
	
	/**
	 * BUsca una tercera empresa en base a su identificador
	 * @param vo
	 * @return EmpresaVO
	 */		
	//public TerceraEmpresaVO findEmpresaById(long id);

	/**
	 * Actualiza los datos de una tercera empresa
	 * @param vo
	 * @return long
	 */		
	//public long actualizarEmpresa(TerceraEmpresaVO vo, long idUsuario);
	
	/**
	 * Obtiene todas las terceras empresas para una empresa padre
	 * @param long
	 * @return List<TerceraEmpresaVO>
	 */			
	//public List<TerceraEmpresaVO> findAllByIdEmpresa(long id);
	
	//public int countOffersByIdTerceraEmpresa(long idTerceraEmpresa, int estatus);
	
	//public void actualizaEstatus(long idTerceraEmpresa, int estatus);
	
	//public void actualizaEstatus(long idTerceraEmpresa, int estatus, long idUsuario);
	
}
