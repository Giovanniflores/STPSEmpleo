package mx.gob.stps.portal.web.candidate.delegate;

import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_CANDIDATOS_REGISTRO;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JNDI_EJB_CATALOGO_OPCION;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.service.CandidatoRegistroAppServiceRemote;
import mx.gob.stps.portal.core.candidate.vo.AccesoOLAVO;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.RegistroCandidatoVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.service.CatalogoOpcionAppServiceRemote;
import mx.gob.stps.portal.core.seguridad.exception.CorreoRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.CurpRepetidoException;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;
import mx.gob.stps.portal.ws.renapo.exception.CurpNotFoundException;
import mx.gob.stps.portal.core.ws.renapo.exception.ConsultaWsPorCurpException;
import mx.gob.stps.portal.persistencia.vo.CandidatoQuebecVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.HistoriaLaboralVO;
import mx.gob.stps.portal.utils.Utils;
import mx.gob.stps.portal.utils.converter.exceptions.NotFoundAnnotationException;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;


/**
 * CandidatosRegistroBusDelegate
 * 
 * @author jose.hernandez
 * 
 */
public class CandidatosRegistroBusDelegateImpl implements CandidatosRegistroBusDelegate {

	public static CandidatosRegistroBusDelegate instace = new CandidatosRegistroBusDelegateImpl();

	public static CandidatosRegistroBusDelegate getInstance() {
		return instace;
	}

	public boolean esCorreoUnico(String correoElectronico) throws ServiceLocatorException{
		return getCandidatoRegistroAppService().esCorreoUnico(correoElectronico);
	}
	
	public boolean esUsuarioUnico(String usuario) throws ServiceLocatorException{
		return getCandidatoRegistroAppService().esUsuarioUnico(usuario);
	}
	
	public boolean esCurpUnico(String curp) throws ServiceLocatorException{
		return getCandidatoRegistroAppService().esCurpUnico(curp);
	}

	public CandidatoVo consultaCandidatoPorCURP(String curp) throws ServiceLocatorException {
		return getCandidatoRegistroAppService().consultaCandidatoPorCURP(curp);
	}
	
	public boolean esNSSUnico(String nss) throws ServiceLocatorException{
		return getCandidatoRegistroAppService().esNSSUnico(nss);
	}
	public String consultaNombreCookie() throws ServiceLocatorException {
		return getCandidatoRegistroAppService().consultaNombreCookie();
	}
	
	public String consultaNombreParametro() throws ServiceLocatorException {
		return getCandidatoRegistroAppService().consultaNombreParametro();
	}
	
	public long registraCandidatoPublicidad(String curp, String cookie, String parametro, int idTipoIngreso){
		long id = 0;
		
		try {
			id = getCandidatoRegistroAppService().registraCandidatoPublicidad(curp, cookie, parametro, idTipoIngreso);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
	
		return id;
	}
	
	public long registraCandidato(RegistroCandidatoVO registroCandidatoVO) throws LoginRepetidoException, CorreoRepetidoException, CurpRepetidoException,
    	                                                                          BusinessException, TechnicalException, PersistenceException,
    	                                                                          ServiceLocatorException {
		return getCandidatoRegistroAppService().registraCandidato(registroCandidatoVO);
	}

	public long actualizaRegistroCandidato(long idCandidato, RegistroCandidatoVO registroCandidatoVO) throws LoginRepetidoException, CorreoRepetidoException, CurpRepetidoException,
	                                                                                                         BusinessException, TechnicalException, PersistenceException, ServiceLocatorException {
		return getCandidatoRegistroAppService().actualizaRegistroCandidato(idCandidato, registroCandidatoVO);
	}
	
	public long borrarRegistrosYRegistrarHistorialLaboral(HistoriaLaboralVO historiaLaboralVO) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException, NotFoundAnnotationException, ServiceLocatorException{
		return getCandidatoRegistroAppService().borrarRegistrosYRegistrarHistorialLaboral(historiaLaboralVO);
	}
	
	public int guardarCandidatoHabilidades(long idCandidato, long[] habilidades) throws ServiceLocatorException {
		return getCandidatoRegistroAppService().guardarCandidatoHabilidades(idCandidato, habilidades);
	}
	
	public void notificaRegistroCandidato(String correoElectronico, String usuario, String contrasena, String nombre, String apellido1, String apellido2, int estatusPPC) throws MailException, ServiceLocatorException {
		getCandidatoRegistroAppService().notificaRegistroCandidato(correoElectronico, usuario, contrasena, nombre, apellido1, apellido2, estatusPPC);
	}
	
	public void notificaRegistroCandidatoPpc(String correoElectronico, String usuario, String contrasena, int estatusPPC, int entidadFederativa,
			int municipio, String nombre, String apellido1, String apellido2, String curp, String numeroSeguridadSocial) throws MailException, ServiceLocatorException {
		getCandidatoRegistroAppService().notificaRegistroCandidatoPpc(correoElectronico, usuario, contrasena, estatusPPC, entidadFederativa,
				municipio, nombre, apellido1, apellido2, curp, numeroSeguridadSocial);
	}	
	
	public void notificaRecuperacionPsw(String correoElectronico, long usuario, String contrasena, String nombre, String apellido1, String apellido2) throws MailException, ServiceLocatorException {
		getCandidatoRegistroAppService().notificaRecuperacionPsw(correoElectronico, usuario, contrasena, nombre, apellido1, apellido2);
	}
	
	@Override
	public void registraCandidatos(CandidatoVo candidatoVo, long idAdministrador) throws /*LoginRepetidoException,*/ BusinessException, ServiceLocatorException,
																						 PersistenceException, TechnicalException, MailException {
		getCandidatoRegistroAppService().registrar(candidatoVo, idAdministrador);
	}

	public List<CatalogoOpcionVO> consultarCatalogo(long idCatalogo) throws ServiceLocatorException {
		CatalogoOpcionAppServiceRemote catalogoOpcionAppServiceRemote = getCatalogoOpcionAppService();
		return catalogoOpcionAppServiceRemote.consultarCatalogo(idCatalogo);
	}

	/**
	 * Valor de EJB CandidatoRegistroAppService
	 * 
	 * @return CandidatoRegistroAppServiceRemote
	 * @throws ServiceLocatorException
	 */
	private CandidatoRegistroAppServiceRemote getCandidatoRegistroAppService() throws ServiceLocatorException {
		CandidatoRegistroAppServiceRemote ejb = (CandidatoRegistroAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_CANDIDATOS_REGISTRO);
		return ejb;
	}

	/**
	 * CatalogoOpcionAppService
	 * 
	 * @return CandidatoRegistroAppServiceRemote
	 * @throws ServiceLocatorException
	 */
	private CatalogoOpcionAppServiceRemote getCatalogoOpcionAppService() throws ServiceLocatorException {
		CatalogoOpcionAppServiceRemote ejb = (CatalogoOpcionAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_CATALOGO_OPCION);
		return ejb;
	}

	@Override
	public CandidatoVo consultaCURPPorDatosPersonales(String nombre, String apellido1, String apellido2, int genero, Date fechaNacimiento, int idEntidadNacimiento) throws CurpNotFoundException, ServiceLocatorException, ConsultaWsPorCurpException {
		CandidatoVo candidatoVo = new CandidatoVo();
		candidatoVo.setNombre(nombre);
		candidatoVo.setApellido1(apellido1);
		candidatoVo.setApellido2(apellido2);
		candidatoVo.setGenero(genero);
		candidatoVo.setFechaNacimiento(fechaNacimiento);		
		candidatoVo.setFechaNacimientoString(Utils.getFechaFormato(fechaNacimiento));		
		candidatoVo.setIdEntidadNacimiento(idEntidadNacimiento);
		return getCandidatoRegistroAppService().consultaCURPPorDatosPersonales(candidatoVo);
	}

	@Override
	public CandidatoVo consultaDatosPersonalesPorCURP(String CURP) throws CurpNotFoundException, ServiceLocatorException, ConsultaWsPorCurpException {		
		return getCandidatoRegistroAppService().consultaDatosPersonalesPorCURP(CURP);

	}

	@Override
	public Boolean activarCandidato(long idCandidato, String correoElectronico)throws BusinessException, ServiceLocatorException {
		return getCandidatoRegistroAppService().activarCandidato(idCandidato, correoElectronico);
	}

	public AccesoOLAVO consultaAccesosOLA(long idPropietario, long idPerfil) throws SQLException, ServiceLocatorException {
		return getCandidatoRegistroAppService().consultaAccesosOLA(idPropietario, idPerfil);
	}

	@Override
	public int persistCandidatoQuebec(long idCandidato) throws SQLException, ServiceLocatorException {
		return getCandidatoRegistroAppService().persistCandidatoQuebec(idCandidato);
	}

	@Override
	public CandidatoQuebecVO findByID(long idCandidato) throws SQLException, ServiceLocatorException {
		return getCandidatoRegistroAppService().findByID(idCandidato);
	}
}
