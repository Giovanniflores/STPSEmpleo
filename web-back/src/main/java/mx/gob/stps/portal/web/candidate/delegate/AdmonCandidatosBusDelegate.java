package mx.gob.stps.portal.web.candidate.delegate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.OfertaCandidatoResumenVo;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.vo.DestinatarioVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public interface AdmonCandidatosBusDelegate {
	
	/**
	 * Realiza la carga de los candidatos asociados a la oferta de empleo
	 * @author Ricardo Geiringer
	 * @since 28/03/2011
	 * @param idEmpresa, idOferta
	 * @throws BusinessException, ServiceLocatorException
	 * @return List<CandidatoVo>
	 **/
	public List<CandidatoVo> obtenerCandidatos(long idEmpresa, long idOferta)
	        throws SQLException, ServiceLocatorException;
	
	/**
	 * Realiza la carga de los candidatos postulados a la oferta de empleo
	 * @author Ricardo Geiringer
	 * @since 28/03/2011
	 * @param idEmpresa, idOferta
	 * @throws BusinessException, ServiceLocatorException
	 * @return List<CandidatoVo>
	 **/
	public List<CandidatoVo> obtenerPostulados(long idEmpresa, long idOferta)
	        throws SQLException, ServiceLocatorException;
	
	/**
	 * Contrata el candidato en proceso para la oferta relacionada
	 * @author Ricardo Geiringer
	 * @since 29/03/2011
	 * @param idOferta
	 * @param fechaContrato
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws PersistenceException
	 * @throws SQLException
	 * @throws MailException 
	 */

	void contratarCandidato(long idOfertaCandidato, Date fechaContrato,
			DestinatarioVO destinatarioVO) throws BusinessException,
			ServiceLocatorException, PersistenceException, SQLException, MailException;

	
	/**
	 * No acepta el candidato en proceso para la oferta relacionada
	 * @author Ricardo Geiringer
	 * @since 29/03/2011
	 * @param idOfertaCandidato
	 * @param idMotivo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws PersistenceException
	 * @throws SQLException
	 */

	void rechazarCandidato(long idOfertaCandidato, int idMotivo, String motivoDesc,
			DestinatarioVO destinatarioVO) throws BusinessException,
			ServiceLocatorException, PersistenceException, SQLException,
			MailException;
	
	/**
	 * Coloca el estatus del candidato en proceso para la oferta relacionada
	 * @author Ricardo Geiringer
	 * @since 28/03/2011
	 * @param idOferta
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws SQLException 
	 * @throws PersistenceException 
	 */
	public void procesarCandidato(long idOferta, long idUsuario)
		throws BusinessException, ServiceLocatorException, PersistenceException, SQLException;


	/**
	 * Elimina la asociacion del candidato a la oferta de empleo
	 * @author Ricardo Geiringer
	 * @since 28/03/2011
	 * @param idCandidato, idOferta
	 * @throws BusinessException, ServiceLocatorException
	 * @return List<CandidatoVo>
	 **/
	public void eliminarCandidatos(long idCandidato)
			throws BusinessException, ServiceLocatorException, PersistenceException, SQLException;

	/**
	 * Obtiene el detalle de la oferta de emplo
	 * @author Ricardo Geiringer
	 * @since 30/03/2011
	 * @param idOfertaEmpleo
	 * @throws BusinessException, ServiceLocatorException
	 * @return obtenerDetalleOferta
	 **/
	
	public OfertaPorPerfilVO obtenerDatosOferta(long idOfertaEmpleo) throws BusinessException,
			ServiceLocatorException, PersistenceException, SQLException;

	/**
	 * @author Ricardo Geiringer
	 * @since 05/04/2011
	 * @param idOfertaCandidato
	 * @return OfertaCandidatoResumenVo
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws PersistenceException
	 * @throws SQLException
	 */
	public OfertaCandidatoResumenVo obtenerOfertaCandidatoResumen(long idOfertaCandidato)
			throws BusinessException, ServiceLocatorException, PersistenceException, SQLException;

	/**
	 * Método que obtiene los candidatos con su grado de estudio
	 * @param idEmpresa
	 * @param idOferta
	 * @return List<CandidatoVo>
	 * @throws SQLException
	 * @throws ServiceLocatorException
	 */
	public List<CandidatoVo> obtenerCandidatosOfertasActivas(long idEmpresa, long idOferta) throws SQLException, ServiceLocatorException;

	/**
	 * Crea una vinculacion entre el candidato y la oferta de empleo
	 * @param idOfertaCandidato
	 * @param destinatarioVO
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws PersistenceException
	 * @throws SQLException
	 * @throws MailException
	 */
	void contactarCandidato(long idOfertaCandidato,
			DestinatarioVO destinatarioVO) throws BusinessException,
			ServiceLocatorException, PersistenceException, SQLException,
			MailException;

	/**
	 * Programa una entrevista con el candidato
	 * @param idOfertaCandidato
	 * @param fecha
	 * @param hora
	 * @param destinatarioVO
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws PersistenceException
	 * @throws SQLException
	 * @throws MailException
	 */
	void entrevistarCandidato(long idOfertaCandidato, Date fecha, String hora,
			DestinatarioVO destinatarioVO) throws BusinessException,
			ServiceLocatorException, PersistenceException, SQLException,
			MailException;
	
	public List<CandidatoVo> postulatesByEmpresaList(long idEmpresa) throws SQLException, ServiceLocatorException;
	
	/**
	 * Método que obtiene los candidatos nominales vinculados a una oferta del subprograma PTAT
	 * @author Sergio Téllez
	 * @since 06/06/2016
	 * @param idOferta
	 * @return List<Long>
	 * @throws ServiceLocatorException
	 */
	public List<Long> getNominalList(long idOferta) throws BusinessException, ServiceLocatorException;
}
