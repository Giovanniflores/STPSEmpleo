package mx.gob.stps.portal.core.seguridad.service;

import javax.ejb.Local;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.seguridad.exception.LoginRepetidoException;

/**
 * Define los servicios para el modulo de Seguridad
 * 
 * @author oscar.manzo
 *
 */
@Local
public interface SeguridadAppServiceLocal {
	
	public String recuperaContrasenaCandidato(String usuario, String curp, String correoActual, String correoNuevo) throws LoginRepetidoException, BusinessException, TechnicalException, MailException;

	public String recuperaContrasenaEmpresa(String usuario, String idPortalEmpleo, String correoActual, String correoNuevo) throws LoginRepetidoException, BusinessException, TechnicalException, MailException;
}