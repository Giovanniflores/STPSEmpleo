package mx.gob.stps.portal.core.persistencia.facade;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.empresa.vo.EmpresaUsuarioVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaRecienteVO;
import mx.gob.stps.portal.ws.renapo.vo.CurpVO;

/**
 * Proporciona acceso a los metodos de persistencia para registros de la entidad "Empresa"
 * 
 * @author haydee.vertti
 *
 */
@Local
public interface EmpresaFacadeLocal {

	/**
	 * Registra una empresa
	 * @param vo EmpresaVO
	 * @return Identificador de la empresa
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */
	public long save(EmpresaVO vo) throws PersistenceException;	

	
	/**
	 * Elimina una empresa
	 * @param vo EmpresaVO
	 * @throws PersistenceException Lanzada en caso de error durante el proceso de persistencia
	 */		
	public void delete(EmpresaVO vo) throws PersistenceException;	
	
	public void delete(long idEmpresa) throws PersistenceException;
	
	/**
	 * Method 'findById'
	 * Regresa un objeto EmpresaVO con los datos correspondientes a
	 * la empresa cuyo identificador se proporciona
	 * 
	 * @param id
	 * @return EmpresaVO
	 */	
	public EmpresaVO findById(long id) throws PersistenceException;
	
	/**
	 * Method 'findByIdUsuario'
	 * Regresa un objeto EmpresaVO con los datos correspondientes a
	 * la empresa cuyo identificador de usuario se proporciona. En caso de no 
	 * encontrar una empresa asociada al {@code idUsuario} proporcionado, devuelve {@code null}.
	 * 
	 * @param idUsuario el identificador del usuario asociado a la empresa
	 * @return EmpresaVO que representa el registro encontrado para el {@code idUsuario}
	 *         proporcionado, o {@code null}, si no se encuentra un registro coincidente.
	 * @throws PersistenceException en caso de que ocurra alg&uacute;n problema en 
	 *         tiempo de ejecuci&oacute;n, o si el proveedor de persistencia encuentra un problema.
	 */		
	public EmpresaVO findByIdUsuario(long idUsuario) throws PersistenceException;
	
	/**
	 * Method 'findByIdEmpresa'
	 * Regresa un objeto EmpresaVO con los datos correspondientes a
	 * la empresa cuyo identificador de empresa se proporciona. En caso de no 
	 * encontrar una empresa asociada al {@code idUsuario} proporcionado, devuelve {@code null}.
	 * 
	 * @param idUsuario el identificador del empresa asociado a la empresa
	 * @return EmpresaVO que representa el registro encontrado para el {@code idEmpresa}
	 *         proporcionado, o {@code null}, si no se encuentra un registro coincidente.
	 * @throws PersistenceException en caso de que ocurra alg&uacute;n problema en 
	 *         tiempo de ejecuci&oacute;n, o si el proveedor de persistencia encuentra un problema.
	 */		
	public EmpresaVO findByIdEmpresa(long idEmpresa) throws PersistenceException;
	
	/**
	 * Method 'findByEmailCP'
	 * Regresa un objeto EmpresaVO con los datos correspondientes a
	 * la empresa cuyo RFC del usuario se proporciona. En caso de no 
	 * encontrar una empresa asociada al {@code idUsuario} proporcionado, devuelve {@code null}.
	 * 
	 * @param RFC el rfc del usuario asociado a la empresa
	 * @return EmpresaVO que representa el registro encontrado para el {@code RFC}
	 *         proporcionado, o {@code null}, si no se encuentra un registro coincidente.
	 * @throws PersistenceException en caso de que ocurra alg&uacute;n problema en 
	 *         tiempo de ejecuci&oacute;n, o si el proveedor de persistencia encuentra un problema.
	 */	
	public EmpresaVO findByEmailCP(String email, String cp) throws PersistenceException;
	
	public List<Long> findByRFC_CP(String RFC, String CodigoPostal) throws PersistenceException;
	
	/**
	 * Method 'update'
	 * 
	 * @param vo
	 */		
	public void update(EmpresaVO vo) throws PersistenceException;
	
	public void updateFull(EmpresaVO vo) throws PersistenceException;
	
	/**
	 * Method 'actualizaEstatus'
	 * Actualiza el estatus a la empresa correspondiente al valor proporcionado.
	 * 
	 * @param idEmpresa
	 * @param estatus
	 */		
	public void actualizaEstatus(long idEmpresa, int estatus) throws PersistenceException;
	
	/**
	 * Method 'actualizaUltimaFecha'
	 * Actualiza la fecha de actualización a la empresa correspondiente al valor proporcionado.
	 * 
	 * @param idEmpresa
	 * @param ultimaFecha
	 */		
	public void actualizaUltimaFecha(long idEmpresa, Date ultimaFecha) throws PersistenceException;		
	
	public void actualizaFechaConfirma(long idEmpresa, Date fechaConfirma) throws PersistenceException;
	
	/** Se validad que la empresa contenga en correo correcto y  el estatus de prevalidada
	 * @return
	 * @throws PersistenceException
	 */
	public Boolean validandoEmpresaAutorizar(long idEmpresa)throws PersistenceException, BusinessException;
	
	/**
	 * Method 'actualizaEstatus'
	 * Actualiza el estatus a la empresa correspondiente al valor proporcionado.
	 * 
	 * @param idEmpresa
	 * @param estatus
	 * @return idUsuario
	 */		
	public long actualizaEmpresaEstatus(long idEmpresa, int estatus) throws PersistenceException;
	
	
	public EmpresaVO findByIdPortalEmpleo(String idPortalEmpleo) throws PersistenceException;
	
	public void actualizaCorreoElectronico(long idEmpresa, String correoElectronico) throws PersistenceException;
	
	public void desactivarEmpresa(long idEmpresa, int idMotivoDesactivacion, String detalleDesactivacion) throws PersistenceException;
	
	public void reactivarEmpresa(long idEmpresa) throws PersistenceException;

	/** Metodo para validar Empresa con estatus de Inactiva
	 * @param confirmacionRegistroVo
	 * @return
	 * @throws PersistenceException
	 * @throws BusinessException
	 */
	public Boolean validandoEmpresaEstatus(long idEmpresa)throws PersistenceException, BusinessException;
	
	public String generaIDPortalEmpleo(EmpresaVO vo, String codigoPostal) throws PersistenceException;
	
	/** Metodo para generar el ID del Portal del Empleo
	 * @param vo
	 * @return String
	 * @throws PersistenceException
	 */	
	public String generaIDPortalEmpleo(EmpresaVO vo) throws PersistenceException;
	
	/** Metodo para actualizar el ID del Portal del Empleo
	 * @param idEmpresa
	 * @param idPortalEmpleo
	 * @return 
	 * @throws PersistenceException
	 */		
	public void actualizaIDPortalEmpleo(long idEmpresa, String idPortalEmpleo) throws PersistenceException;

	public List<EmpresaVO> consultaEmpresas(int idTipoPersona,
			String correoElectronico,
			String idPortalEmpleo,
			String nombre,
			String apellido1,
			String razonSocial,
			String fechaActa,
			String apellido2, Long idEmpresa, String contacto, String telefono, String domicilio, long idEntidad, long idMunicipio,	String usuario			
			);

	public EmpresaVO consultaEmpresaPorCorreo(String correoElectronico) throws PersistenceException;
	
	public List<OfertaRecienteVO> obtenerOfertasRecientes(int numRegistros,long idOrigenCanada);
	
	public List<OfertaPorCanalVO> obtenerOfertasRecientesTodas (int numRegistros, long idFuenteCanada) throws PersistenceException;
	
	public List<OfertaRecienteVO> obtenerOfertasDestacadas(int numRegistros, 
			                                               double salarioMinimoDoctorado,
			                                               double salarioMinimoMaestria, 
			                                               double salarioMinimoLicenciatura) throws SQLException;

	public List<OfertaPorCanalVO> obtenerOfertasDestacadasTodas(int numRegistros, 
                                                                double salarioMinimoDoctorado,
                                                                double salarioMinimoMaestria, 
                                                                double salarioMinimoLicenciatura) throws SQLException;
	
	
	public List<OfertaRecienteVO> obtenerOfertasGendarmeria(int numRegistros) throws PersistenceException;
	public List<OfertaPorCanalVO> obtenerOfertasGendarmeriaTodas(int numRegistros) throws PersistenceException;
	
	public long obtenerIdEmpresa();
	
	Map<String, String> obtenerActividadEconomica(long idActEco);
	
	public Boolean repetidaCurp(String curp);
	
	public Boolean repetidoIdPortalEmpleoCurp(String curp);
	
	public CurpVO consultaDatosPersonalesPorCURP(CurpVO curpVo);
	
	public List<EmpresaUsuarioVO> obtieneEmpresas(String RFC, String CodigoPostal) throws PersistenceException;


	public byte[] consultaLogotipo(int idPropietario);
	
}
