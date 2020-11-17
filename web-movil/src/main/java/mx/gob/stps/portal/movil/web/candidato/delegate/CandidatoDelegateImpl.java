package mx.gob.stps.portal.movil.web.candidato.delegate;

import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_CANDIDATO;
import static mx.gob.stps.portal.movil.web.infra.utils.ConstantesJNDI.JNDI_EJB_CANDIDATOS_REGISTRO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceRemote;
import mx.gob.stps.portal.core.candidate.service.CandidatoRegistroAppServiceRemote;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.core.candidate.vo.PerfilVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.oferta.detalle.bo.PerfilBO;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.movil.web.oferta.form.PerfilJB;
import mx.gob.stps.portal.persistencia.vo.CandidatoVO;

import org.apache.commons.beanutils.BeanUtils;

public final class CandidatoDelegateImpl {

	
	private static CandidatoDelegateImpl instance = new CandidatoDelegateImpl();
	
	private CandidatoDelegateImpl(){}	

	public static CandidatoDelegateImpl getInstance(){
		return instance;
	}
	
	public CandidatoAppServiceRemote getCandidatoAppService() throws ServiceLocatorException {
		CandidatoAppServiceRemote ejb = (CandidatoAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_CANDIDATO);
		return ejb;
	}
	
	private CandidatoRegistroAppServiceRemote getCandidatoRegistroAppService() throws ServiceLocatorException {
		CandidatoRegistroAppServiceRemote ejb = (CandidatoRegistroAppServiceRemote) ServiceLocator.getSessionRemote(JNDI_EJB_CANDIDATOS_REGISTRO);
		return ejb;
	}

	public CandidatoVo buscarDatosHeaderTemplateCandidato(long idCandidato) throws SQLException, ServiceLocatorException {
		return getCandidatoAppService().buscarDatosHeaderTemplateCandidato(idCandidato);
	}
	
	public PerfilJB loadPerfilUsuario(long idUsuario) throws BusinessException, ServiceLocatorException {//try full_load
		PerfilJB perfil = new PerfilJB();
		PerfilBO perfilBO = getCandidatoAppService().loadPerfilUsuario(idUsuario);
		try {
			if (null != perfilBO) {
				BeanUtils.copyProperties(perfil, perfilBO);

				perfil.setEstiloCV(perfilBO.getEstiloCV());
				perfil.setFechaNacimiento(perfilBO.getFechaNacimiento());
				perfil.setCalle(perfilBO.getCalle());
				perfil.setNumeroExterior(perfilBO.getNumeroExterior());
				perfil.setNumeroInterior(perfilBO.getNumeroInterior());
				int intIni = Utils.parseInt(String.valueOf(perfilBO.getHoraContactoIni()));
				int intFin = Utils.parseInt(String.valueOf(perfilBO.getHoraContactoFin()));
				perfil.setHorarioLlamar(intIni, intFin);				
				
				/*List<IdiomaVO> lstIdiomas = perfil.getIdiomas();
				ListIterator<IdiomaVO>itIdiomas = lstIdiomas.listIterator();				
				while(itIdiomas.hasNext()){
					IdiomaVO idioma = (IdiomaVO)itIdiomas.next();
					logger.info("-----" + idioma.getIdioma() + " principal:" + idioma.getPrincipal());
				}*/
				
			}
		} catch (IllegalAccessException iae) { iae.printStackTrace();
		} catch (InvocationTargetException ite) { ite.printStackTrace(); }
		
		return perfil;
	}

	public PerfilJB loadPerfil(long idCandidato) throws BusinessException, ServiceLocatorException {//try full_load
		PerfilJB perfil = new PerfilJB();
		PerfilBO perfilBO = getCandidatoAppService().loadPerfil(idCandidato);
		try {
			if (null != perfilBO) {
				BeanUtils.copyProperties(perfil, perfilBO);

				perfil.setEstiloCV(perfilBO.getEstiloCV());
				perfil.setFechaNacimiento(perfilBO.getFechaNacimiento());
				perfil.setCalle(perfilBO.getCalle());
				perfil.setNumeroExterior(perfilBO.getNumeroExterior());
				perfil.setNumeroInterior(perfilBO.getNumeroInterior());
				int intIni = Utils.parseInt(String.valueOf(perfilBO.getHoraContactoIni()));
				int intFin = Utils.parseInt(String.valueOf(perfilBO.getHoraContactoFin()));
				perfil.setHorarioLlamar(intIni, intFin);				
				
				/*List<IdiomaVO> lstIdiomas = perfil.getIdiomas();
				ListIterator<IdiomaVO>itIdiomas = lstIdiomas.listIterator();				
				while(itIdiomas.hasNext()){
					IdiomaVO idioma = (IdiomaVO)itIdiomas.next();
					logger.info("-----" + idioma.getIdioma() + " principal:" + idioma.getPrincipal());
				}*/
				
			}
		} catch (IllegalAccessException iae) { iae.printStackTrace();
		} catch (InvocationTargetException ite) { ite.printStackTrace(); }
		
		return perfil;
	}

	public ConocimientoComputacionVO findConocimientosComputacion(
			long idCandidato) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppService().findConocimientosComputacion(idCandidato); 
		}
	
	public void notificaRegistroCandidato(String correoElectronico, String usuario, String contrasena) throws MailException, ServiceLocatorException {
		//TODO poner apelido materno y paterno
		String nombre = "";
		String apellido1 = "";
		String apellido2 = "";
		this.getCandidatoRegistroAppService().notificaRegistroCandidato(correoElectronico, usuario, contrasena,nombre,apellido1,apellido2,0);
	}
	
	public void notificacionRecuperacionPswMovilCandidato(String correoElectronico, long idCandidato, String contrasena) throws MailException, ServiceLocatorException {
		//TODO poner apelido materno y paterno
		String nombre = "";
		String apellido1 = "";
		String apellido2 = "";
		//TODO: verificar la firma de llamada de este metodo
		//this.getCandidatoRegistroAppService().notificaRecuperacionPsw(correoElectronico, idCandidato, contrasena, nombre, apellido1, apellido2);
	}
	
	public void notificarCandidato(String correoElectronico, String nombre, String tituloOferta, Boolean contratacion, String tipoPersona) throws MailException, ServiceLocatorException {
		this.getCandidatoRegistroAppService().notificarCandidato(correoElectronico, nombre, tituloOferta, contratacion, tipoPersona);
	}
	
	public void notificarCandidatoVinculado(String correoElectronico, String nombre, String tituloOferta, String tipoPersona) throws MailException, ServiceLocatorException {
		this.getCandidatoRegistroAppService().notificarCandidatoVinculado(correoElectronico, nombre, tituloOferta, tipoPersona);
	}
	
	public CandidatoVo findCandidatoVO(long idCandidato) throws ServiceLocatorException{
		return this.getCandidatoAppService().findById(idCandidato);
	}
	
	public void actualizarRegistroCandidato(CandidatoVo candidatoVo) throws ServiceLocatorException{
		this.getCandidatoAppService().actualizarRegistroCandidato(candidatoVo);
	}


	
	public PerfilVO telefonoCandidato(long idCandidato) throws ServiceLocatorException, SQLException {
		return this.getCandidatoAppService().buscarPerfilCandidato(idCandidato);
	}
	
	public InformacionGeneralVO telefonoCandidato(Long idCandidato) throws SQLException, ServiceLocatorException {
		return this.getCandidatoAppService().buscarInformacionGeneral(idCandidato);
		
	}
	
	
}