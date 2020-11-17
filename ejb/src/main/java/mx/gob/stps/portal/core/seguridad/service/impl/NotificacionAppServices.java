package mx.gob.stps.portal.core.seguridad.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.service.EmpresaAppServiceLocal;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.service.NotificacionService;
import static mx.gob.stps.portal.core.infra.utils.Constantes.ID_USUARIO_ANONIMO;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Password;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.persistencia.facade.CandidatoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.EmpresaFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.UsuarioFacadeLocal;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;
import mx.gob.stps.portal.core.seguridad.service.NotificacionAppServicesRemote;
import mx.gob.stps.portal.core.seguridad.service.SeguridadAppServiceLocal;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;

import org.apache.log4j.Logger;

@Stateless(name = "NotificacionAppServices", mappedName = "NotificacionAppServices")
public class NotificacionAppServices implements NotificacionAppServicesRemote {

	private static Logger logger = Logger.getLogger(NotificacionAppServices.class);
	
	@EJB
	private EmpresaFacadeLocal empresaFacade;
	
	@EJB
	private EmpresaAppServiceLocal empresaAppService;

	@EJB
	private CandidatoFacadeLocal candidatoFacade;
	
	@EJB
	private SeguridadAppServiceLocal seguridadAppService;

	@EJB
	private UsuarioFacadeLocal usuarioFacade;
	
	public void notificaRegistroEmpresa(String username) throws PersistenceException, BusinessException, TechnicalException, MailException, EncodingException {
		
		if (username==null || username.isEmpty()) throw new IllegalArgumentException("Nombre de usuario requerido");
		
		UsuarioVO usuario = usuarioFacade.findByUsuario(username);
		
		//EmpresaVO empresa = empresaFacade.consultaEmpresaPorCorreo(correoElectronico);
		EmpresaVO empresa = empresaFacade.findByIdUsuario(usuario.getIdUsuario());

		if (empresa.getCorreoElectronico()!=null && !empresa.getCorreoElectronico().isEmpty()){
			empresaAppService.notificaEmpresa(empresa, ID_USUARIO_ANONIMO, empresa.getCorreoElectronico());
		}
	}
	
	public void notificaRegistroEmpresa(final List<String> usernames) throws PersistenceException, BusinessException, TechnicalException, MailException, EncodingException {

		if (usernames==null || usernames.isEmpty()) throw new IllegalArgumentException("Lista de nombres de usuario requerida o vacia");

		new Thread(new Runnable() {
			public void run() {
				logger.info("+++++++ ["+ Utils.formatTime(Calendar.getInstance().getTime()) +"] Inicia proceso de notificación del Registro por Empresas +++++++++++");
				logger.info("Empresas a notificar: "+ usernames.size());
				
				int index = 0;
				for (String username : usernames){
					try{
						notificaRegistroEmpresa(username);
						index++;
					}catch(Exception e){
						e.printStackTrace(); logger.error(e);
					}
				}

				logger.info("+++++++ ["+ Utils.formatTime(Calendar.getInstance().getTime()) +"] Finaliza proceso de notificación del Registro por Empresas +++++++++++");
				logger.info("Empresas notificadas: "+ index);
			}
        }).start();
	}
	
	public void notificaRegistroCandidato(String username) throws MailException, EncodingException {
		
		if (username==null || username.isEmpty()) throw new IllegalArgumentException("Nombre de usuario requerido");
		
		UsuarioVO usuario = usuarioFacade.findByUsuario(username);
		
		String passw = Password.getPassword();
		String passwdb = Password.codificaPassword(passw);
		
		//CandidatoVo candidato = candidatoFacade.consultaCandidatoPorCorreo(correoElectronico);
		CandidatoVo candidato = candidatoFacade.consultaCandidato(usuario.getIdUsuario());

		candidatoFacade.inactivarCandidato(candidato.getIdCandidato());

		usuarioFacade.updatePasswordEstatus(candidato.getIdUsuario(), passwdb, ESTATUS.INACTIVO.getIdOpcion());

		if (candidato.getCorreoElectronico()!=null && !candidato.getCorreoElectronico().isEmpty()){
			NotificacionService notificacionService = new NotificacionService();
			notificacionService.notificacionEmailToCandidato(candidato, passw);
		}
	}
	
	public void notificaRegistroCandidato(final List<String> usernames) throws MailException, EncodingException {
		
		if (usernames==null || usernames.isEmpty()) throw new IllegalArgumentException("Lista de nombres de usuario requerida o vacia");
		
		new Thread(new Runnable() {
			public void run() {
				logger.info("+++++++ ["+ Utils.formatTime(Calendar.getInstance().getTime()) +"] Inicia proceso de notificación del Registro por Candidatos +++++++++++");
				logger.info("Candidatos a notificar: "+ usernames.size());
				
				int index = 0;
				for (String username : usernames){
					try{
						notificaRegistroCandidato(username);
						index++;
					}catch(Exception e){
						e.printStackTrace(); logger.error(e);
					}
				}

				logger.info("+++++++ ["+ Utils.formatTime(Calendar.getInstance().getTime()) +"] Finaliza proceso de notificación del Registro por Candidatos +++++++++++");
				logger.info("Candidatos notificados: "+ index);
			}
        }).start();

	}

	public void notificaRecomendacion(String remitente, String destinatario, String correoRemitente, String correoDestinatario, String asunto) throws MailException {
		NotificacionService notificacionService = new NotificacionService();
		notificacionService.notificacionRecomendacion(remitente, destinatario, correoRemitente, correoDestinatario, asunto);
	}
	
	public void notificaRecuperaContrasenaEmpresa(String username) throws LoginRepetidoException, BusinessException, TechnicalException, MailException {
		
		if (username==null || username.isEmpty()) throw new IllegalArgumentException("Nombre de usuario requerido");
		
		UsuarioVO usuario = usuarioFacade.findByUsuario(username);
		
		if (usuario==null) throw new BusinessException("Usuario no localizado :"+ username);
		
		//EmpresaVO empresa = empresaFacade.consultaEmpresaPorCorreo(correoElectronico);
		EmpresaVO empresa = empresaFacade.findByIdUsuario(usuario.getIdUsuario());

		if (empresa!=null && empresa.getCorreoElectronico()!=null && !empresa.getCorreoElectronico().isEmpty()){
			seguridadAppService.recuperaContrasenaEmpresa(username, empresa.getIdPortalEmpleo(), empresa.getCorreoElectronico(), empresa.getCorreoElectronico());
		}
	}

	public void notificaRecuperaContrasenaEmpresa(final List<String> usernames) throws LoginRepetidoException, BusinessException, TechnicalException, MailException {
		
		if (usernames==null || usernames.isEmpty()) throw new IllegalArgumentException("Lista de nombres de usuario requerida o vacia");

		new Thread(new Runnable() {
			public void run() {
				logger.info("+++++++ ["+ Utils.formatTime(Calendar.getInstance().getTime()) +"] Inicia proceso de notificación de la Recuperacion de contraseña de Empresas +++++++++++");
				logger.info("Empresas a notificar: "+ usernames.size());
				
				int index = 0;
				for (String username : usernames){
					try{
						notificaRecuperaContrasenaEmpresa(username);
						index++;
					}catch(Exception e){
						e.printStackTrace(); logger.error(e);
					}
				}

				logger.info("+++++++ ["+ Utils.formatTime(Calendar.getInstance().getTime()) +"] Finaliza proceso de notificación de la Recuperacion de contraseña de Empresas +++++++++++");
				logger.info("Empresas notificadas: "+ index);
			}
        }).start();

	}
	
	public void notificaRecuperaContrasenaCandidato(String username) throws LoginRepetidoException, BusinessException, TechnicalException, MailException, PersistenceException {
		
		if (username==null || username.isEmpty()) throw new IllegalArgumentException("Nombre de usuario requerido");
		
		UsuarioVO usuario = usuarioFacade.findByUsuario(username);

		if (usuario==null) throw new BusinessException("Usuario no localizado :"+ username);
			
		//CandidatoVo candidato = candidatoFacade.consultaCandidatoPorCorreo(correoElectronico);
		CandidatoVo candidato = candidatoFacade.consultaCandidato(usuario.getIdUsuario());

		if (candidato!=null && candidato.getCorreoElectronico()!=null && !candidato.getCorreoElectronico().isEmpty()){
			seguridadAppService.recuperaContrasenaCandidato(username, candidato.getCurp(), candidato.getCorreoElectronico(), candidato.getCorreoElectronico());
		}

	}

	public void notificaRecuperaContrasenaCandidato(final List<String> usernames) throws LoginRepetidoException, BusinessException, TechnicalException, MailException, PersistenceException {
		
		if (usernames==null || usernames.isEmpty()) throw new IllegalArgumentException("Lista de nombres de usuario requerida o vacia");
		
		new Thread(new Runnable() {
			public void run() {
				logger.info("+++++++ ["+ Utils.formatTime(Calendar.getInstance().getTime()) +"] Inicia proceso de notificación de la Recuperacion de contraseña de Candidatos +++++++++++");
				logger.info("Candidatos a notificar: "+ usernames.size());
				
				int index = 0;
				for (String username : usernames){
					try{
						notificaRecuperaContrasenaCandidato(username);
						index++;
					}catch(Exception e){
						e.printStackTrace(); logger.error(e);
					}
				}

				logger.info("+++++++ ["+ Utils.formatTime(Calendar.getInstance().getTime()) +"] Finaliza proceso de notificación de la Recuperacion de contraseña de Candidatos +++++++++++");
				logger.info("Candidatos notificados: "+ index);
			}
        }).start();

	}
	
}
