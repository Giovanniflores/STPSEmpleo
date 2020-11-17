package mx.gob.stps.portal.core.autorizacion.service;

import javax.ejb.Local;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.search.exception.IndexerException;

@Local
public interface AutorizacionAppServiceLocal {

	/**
	 * Registra una Empresa por Validar
	 * @param idEmpresa identificador de la empresa
	 */
	public void registraEmpresaPorValidar(long idEmpresa);
	
	/**
	 * Registra una Oferta por Validar
	 * @param idOfertaEmpleo identificador de la oferta
	 * @param idEmpresa identificador de la empresa
	 */
	public void registraOfertaPorValidar(long idOfertaEmpleo, long idEmpresa);
	
	/**
	 * Registra un Testimono de Candidato por Validar
	 * @param idTestimonio identificador del Testimonio
	 * @param idCandidato identificador del Candidato
	 */
	public void registraTestimonioCandidatoPorValidar(long idTestimonio, long idCandidato);

	/**
	 * Registra un Testimono de Empresa por Validar
	 * @param idTestimonio identificador del Testimonio
	 * @param idEmpresa identificador de la empresa
	 */
	public void registraTestimonioEmpresaPorValidar(long idTestimonio, long idEmpresa);

	/**
	 * Registra un Video Curriculo por Validar
	 * @param idCandidato identificador del candidato propietario del video
	 */
	public void registraVideoCurrPorValidar(long idCandidato);	

	//XXX public void autorizaEmpresaPorAutorizar(long idEmpresa, long idUsuario, String detalle, long idRegValidar) throws BusinessException, MailException, TechnicalException;

	public void autorizaEmpresa(long idEmpresa, long idUsuario, String detalle, long idRegValidar) throws BusinessException, MailException, TechnicalException;

	//XXX public void autorizaEmpresaModificada(long idEmpresa, long idUsuario, long idRegValidar);

	public void autorizaOfertaEmpleo(long idOfertaEmpleo, long idUsuario, long idRegValidar) throws TechnicalException, IndexerException;

	//public void actualizaEstatusPorRegistroOfertaEmpleo(long idOfertaEmpleo, ESTATUS estatus, long idUsuario);
}