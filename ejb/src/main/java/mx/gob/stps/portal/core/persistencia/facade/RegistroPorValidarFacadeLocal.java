package mx.gob.stps.portal.core.persistencia.facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.autorizacion.vo.RegistroPorValidarVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_PROPIETARIO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_REGISTRO;
import mx.gob.stps.portal.core.seguridad.vo.PublicadorVO;

@Local
public interface RegistroPorValidarFacadeLocal {

	public RegistroPorValidarVO find(long idRegValidar) throws PersistenceException;
	
	public long registra(RegistroPorValidarVO vo) throws PersistenceException;
	
	public void actualizaEstatus(long idRegValidar, ESTATUS estatus, long idUsuario) throws PersistenceException;
	
	public void actualizaEstatus(long idRegValidar, ESTATUS estatus, Date fechaModificacion, long idUsuario) throws PersistenceException;
	
	//public void actualizaUsuarioEstatus(long idRegValidar, Long idUsuario, int estatus, Date fechaModificacion) throws PersistenceException;
	
	public void actualizaEstatusPorPropietario(long idPropietario, ESTATUS estatus, Date fechaModificacion,
                                               ESTATUS estatusPend, ESTATUS  estatusAsig, ESTATUS estatusEnEdicion, long idUsuario) throws PersistenceException;
	
	public void actualizaEstatusPorPropietario(long idRegistro, long idPropietario, long idTipoRegistro, long idTipoPropietario, ESTATUS estatus, Date fechaModificacion, long idUsuario, long idMotivoRechazo) throws PersistenceException;


	public void actualizaEstatusPorRegistro(ESTATUS estatus, Date fechaModificacion, 
                                            TIPO_REGISTRO tipoRegistro, long idRegistro,
                                            ESTATUS estatus1, ESTATUS estatus2, long idUsuario) throws PersistenceException;

//	public void actualizaEstatusPorRegistro(ESTATUS estatus, Date fechaModificacion, 
//			                                TIPO_REGISTRO tipoRegistro, long idRegistro,
//			                                ESTATUS estatus1, ESTATUS estatus2, ESTATUS estatus3, 
//			                                long idUsuario) throws PersistenceException;
	
	public void actualizaMotivoRechazo(long idRegValidar, long idMotivoRechazo, String detalleRechazo) throws PersistenceException;

	/**
	 * Consulta los registros por validar de un propietario 
	 * @param tipoRegistro
	 * @param tipoPropietario
	 * @param idPropietario
	 * @param estatus
	 * @return
	 * @throws PersistenceException
	 */
	public List<Long> consultaPorPropietario(TIPO_REGISTRO tipoRegistro,
                                       TIPO_PROPIETARIO tipoPropietario,
                                       long idPropietario,
                                       ESTATUS estatus) throws PersistenceException;

	/**
	 * Elimina el registro por validar de la cola de espera
	 * @param idRegValidar
	 */
	public void elimina(long idRegValidar);

	//public List<Long> consultaPorRegistro(TIPO_REGISTRO tipoRegistro, long idRegistro, ESTATUS estatus1, ESTATUS estatus2) throws PersistenceException;

	public boolean existeRegistroPorValidar(TIPO_REGISTRO tipoRegistro, long idRegistro, ESTATUS pendiente, ESTATUS asignado, ESTATUS revision);
	
	/**
	 * Obtiene los registro por validar para cada publicador
	 */
	public List<PublicadorVO> listAssignedUsers() throws PersistenceException;	
	
	/**
	 * Obtiene los registro por validar pendientes
	 */
	//public int totalRegistrosPorValidar() throws PersistenceException;	
	
	/**
	 * Consulta los registros por validar de acuerdo a ciertos parámetros de búsqueda
	 * @throws PersistenceException Lanzada en caso de error durante la consulta de registros
	 */
	public List<PublicadorVO> productivityPublishersList(String name, String lastname, String rangeInit, String rangeFinal) throws PersistenceException;	

	public void registraOfertaServicioSisne(long idOfertaEmpleo, long idEmpresa);
}
