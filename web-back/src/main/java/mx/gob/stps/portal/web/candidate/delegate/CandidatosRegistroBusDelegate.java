/**
 * 
 */
package mx.gob.stps.portal.web.candidate.delegate;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.AccesoOLAVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.RegistroCandidatoVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.persistencia.vo.CandidatoQuebecVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.CurpRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;
import mx.gob.stps.portal.ws.renapo.exception.CurpNotFoundException;
import mx.gob.stps.portal.core.ws.renapo.exception.ConsultaWsPorCurpException;
import mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.utils.converter.exceptions.NotFoundAnnotationException;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

/**
 * @author jose.hernandez
 *
 */
public interface CandidatosRegistroBusDelegate {	

	public boolean esCorreoUnico(String correoElectronico) throws ServiceLocatorException;
	
	public boolean esUsuarioUnico(String usuario) throws ServiceLocatorException;
	
	public boolean esCurpUnico(String curp) throws ServiceLocatorException;

	public CandidatoVo consultaCandidatoPorCURP(String curp) throws ServiceLocatorException;

	public boolean esNSSUnico(String nss) throws ServiceLocatorException;
	
	public String consultaNombreCookie() throws ServiceLocatorException;
	
	public String consultaNombreParametro() throws ServiceLocatorException;
	
	public long registraCandidatoPublicidad(String curp, String cookie, String parametro, int idTipoIngreso);
	
	public long registraCandidato(RegistroCandidatoVO registroCandidatoVO) throws LoginRepetidoException, CorreoRepetidoException, CurpRepetidoException,
                                                                                  BusinessException, TechnicalException, PersistenceException,
                                                                                  ServiceLocatorException;

	public long actualizaRegistroCandidato(long idCandidato, RegistroCandidatoVO registroCandidatoVO) throws LoginRepetidoException, CorreoRepetidoException, CurpRepetidoException,
    																					   					 BusinessException, TechnicalException, PersistenceException, ServiceLocatorException,
																				   					 ServiceLocatorException;
	
	public void notificaRecuperacionPsw(String correoElectronico, long usuario, String contrasena, String nombre, String apellido1, String apellido2) throws MailException, ServiceLocatorException;
	
	public int guardarCandidatoHabilidades(long idCandidato, long[] habilidades) throws PersistenceException, ServiceLocatorException;
	
	public void notificaRegistroCandidato(String correoElectronico, String usuario, String contrasena, String nombre, String apellido1, String apellido2, int estatusPPC) throws MailException, ServiceLocatorException;
	
	public void notificaRegistroCandidatoPpc(String correoElectronico, String usuario, String contrasena, int estatusPPC, int entidadFederativa,
			int municipio, String nombre, String apellido1, String apellido2, String curp, String numeroSeguridadSocial) throws MailException, ServiceLocatorException;
	
	/**
	 * @param candidatoVo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws MailException 
	 * @throws TechnicalException 
	 * @throws PersistenceException 
	 */
	public void registraCandidatos(CandidatoVo candidatoVo,long idAdministrador) throws /*LoginRepetidoException, */BusinessException, ServiceLocatorException, PersistenceException, TechnicalException, MailException;
	
	/**
	 * @param idCatalogo
	 * @return
	 * @throws ServiceLocatorException
	 */
	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo) throws ServiceLocatorException;
	
	/**
	 * @param candidatoVo
	 * @return
	 * @throws ServiceLocatorException
	 * @throws BusinessException 
	 */
	public CandidatoVo consultaCURPPorDatosPersonales(String nombre, String apellido1, String apellido2, int genero, Date fechaNacimiento, int idEntidadNacimiento) throws CurpNotFoundException, ServiceLocatorException, ConsultaWsPorCurpException;
	
	/**Consultar por Datos Personales
	 * @param candidatoVo
	 * @return
	 * @throws ServiceLocatorException
	 * @throws BusinessException 
	 */
	public CandidatoVo consultaDatosPersonalesPorCURP(String CURP) throws CurpNotFoundException, ServiceLocatorException, ConsultaWsPorCurpException;
	
	
	/** Activando un Candidato
	 * @param idCandidato
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public Boolean activarCandidato(long idCandidato, String correoElectronico) throws BusinessException, ServiceLocatorException;

	public AccesoOLAVO consultaAccesosOLA(long idPropietario, long idPerfil) throws SQLException, ServiceLocatorException;

	public long borrarRegistrosYRegistrarHistorialLaboral(HistoriaLaboralVO historiaLaboralVO) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NotFoundAnnotationException, ServiceLocatorException;
	
	public int persistCandidatoQuebec(long idCandidato) throws SQLException, ServiceLocatorException;
	
	public CandidatoQuebecVO findByID(long idCandidato) throws SQLException, ServiceLocatorException;
}
