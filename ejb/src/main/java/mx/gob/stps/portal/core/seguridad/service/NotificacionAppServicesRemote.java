package mx.gob.stps.portal.core.seguridad.service;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.EncodingException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;

@Remote
public interface NotificacionAppServicesRemote {

	public void notificaRegistroEmpresa(String correoElectronico) throws PersistenceException, BusinessException, TechnicalException, MailException, EncodingException;

	public void notificaRegistroEmpresa(List<String> correos) throws PersistenceException, BusinessException, TechnicalException, MailException, EncodingException;

	public void notificaRegistroCandidato(String correoElectronico) throws MailException, EncodingException;

	public void notificaRegistroCandidato(List<String> correos) throws MailException, EncodingException;
	
	public void notificaRecomendacion(String remitente, String destinatario, String correoRemitente, String correoDestinatario, String asunto) throws MailException;

	public void notificaRecuperaContrasenaEmpresa(String correoElectronico) throws LoginRepetidoException, BusinessException, TechnicalException, MailException;

	public void notificaRecuperaContrasenaEmpresa(List<String> correos) throws LoginRepetidoException, BusinessException, TechnicalException, MailException;

	public void notificaRecuperaContrasenaCandidato(String correoElectronico) throws LoginRepetidoException, BusinessException, TechnicalException, MailException, PersistenceException;

	public void notificaRecuperaContrasenaCandidato(List<String> correos) throws LoginRepetidoException, BusinessException, TechnicalException, MailException, PersistenceException;

}
