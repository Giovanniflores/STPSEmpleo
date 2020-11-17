package mx.gob.stps.portal.core.candidate.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.AccesoOLAVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.RegistroCandidatoVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.CurpRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;
import mx.gob.stps.portal.core.ws.renapo.exception.ConsultaWsPorCurpException;
import mx.gob.stps.portal.persistencia.vo.CandidatoGeolocalizacionVO;
import mx.gob.stps.portal.persistencia.vo.CandidatoQuebecVO;
import mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.utils.converter.exceptions.NotFoundAnnotationException;

/**
 * @author jose.hernandez
 * 
 */
@Remote
public interface CandidatoRegistroAppServiceRemote {

	public boolean esCorreoUnico(String correoElectronico);

	public boolean esUsuarioUnico(String usuario);

	public boolean esCurpUnico(String curp);

	public CandidatoVo consultaCandidatoPorCURP(String curp);
	
	public boolean esNSSUnico(String nss);

	public String consultaNombreCookie();

	public String consultaNombreParametro();

	public long registraCandidatoPublicidad(String curp, String cookie,
			String parametro, int idTipoIngreso);

	public void notificaRecuperacionPsw(String correoElectronico, long idUsuario, String contrasena, String nombre, String apellido1, String apellido2) throws MailException;
	
	public long registraCandidato(RegistroCandidatoVO registroCandidatoVO)
			throws LoginRepetidoException, CorreoRepetidoException,
			CurpRepetidoException, BusinessException, TechnicalException,
			PersistenceException;

	public long actualizaRegistroCandidato(long idCandidato,
			RegistroCandidatoVO registro) throws LoginRepetidoException,
			CorreoRepetidoException, CurpRepetidoException, BusinessException,
			TechnicalException, PersistenceException;

	public long borrarRegistrosYRegistrarHistorialLaboral(HistoriaLaboralVO historiaLaboralVO) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NotFoundAnnotationException;
	
	public long registrarHistorialLaboral(HistoriaLaboralVO historiaLaboralVO) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NotFoundAnnotationException;
	
	public void notificarCandidato(String correoElectronico, String nombre,
			String tituloOferta, Boolean contratacion, String tipoPersona) throws MailException;

	public void notificarCandidatoVinculado(String correoElectronico,
			String nombre, String tituloOferta, String tipoPersona) throws MailException;
	
	public void notificaRegistroCandidato(String correoElectronico,
			String usuario, String contrasena, String nombre,
			String apellido1, String apellido2, int estatusPPC) throws MailException; 

	
	public void notificaRegistroCandidatoPpc(String correoElectronico, String usuario, String contrasena, int estatusPPC, 
			int entidadFederativa, int municipio, String nombre, String apellido1, String apellido2, String curp, 
			String numeroSeguridadSocial) throws MailException;

	
	/**
	 * Insertando un Candidato
	 * 
	 * @param candidatoVo
	 * @throws BusinessException
	 * @throws MailException
	 * @throws TechnicalException
	 * @throws PersistenceException
	 */
	public void registrar(CandidatoVo candidatoVo, long idAdministrador)
			throws BusinessException, PersistenceException, TechnicalException,
			MailException;

	/**
	 * @param candidatoVo
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public CandidatoVo consultaCURPPorDatosPersonales(CandidatoVo candidatoVo)
			throws ConsultaWsPorCurpException;

	/**
	 * Consultar por Datos Personales
	 * 
	 * @param candidatoVo
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public CandidatoVo consultaDatosPersonalesPorCURP(String CURP)
			throws ConsultaWsPorCurpException;

	/**
	 * Activar candidato
	 * 
	 * @param idCandidato
	 * @return
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 */
	public Boolean activarCandidato(long idCandidato, String correoElectronico)
			throws BusinessException;

	public AccesoOLAVO consultaAccesosOLA(long idPropietario, long idPerfil) throws SQLException;
	
	public int guardarCandidatoHabilidades(long idCandidato, long[] habilidades);
	
	public int persistCandidatoQuebec(long idCandidato) throws SQLException;
	
	public CandidatoQuebecVO findByID(long idCandidato) throws SQLException;

	public void guardarCandidatoGeolocalizacion(long idCandidato, RegistroCandidatoVO registroCandidatoVO, Date fechaCreacion) throws SQLException;
}
