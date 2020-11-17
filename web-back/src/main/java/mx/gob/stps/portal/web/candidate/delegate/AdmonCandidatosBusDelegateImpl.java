/**
 * 
 */
package mx.gob.stps.portal.web.candidate.delegate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import mx.gob.stps.portal.core.candidate.service.CandidatoAppServiceRemote;
import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.OfertaCandidatoResumenVo;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.mail.vo.DestinatarioVO;
import mx.gob.stps.portal.core.oferta.busqueda.service.OfertasPorPerfilAppServiceRemote;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;

/**
 * @author Felipe Juárez Ramírez
 * @since 28 de Febrero de 2011
 * @category BusinessDelegate
 */
public final class AdmonCandidatosBusDelegateImpl implements AdmonCandidatosBusDelegate {

	private static AdmonCandidatosBusDelegate instance = new AdmonCandidatosBusDelegateImpl();

	private AdmonCandidatosBusDelegateImpl() {
	}

	@Override
	public List<CandidatoVo> obtenerCandidatos(long idEmpresa, long idOferta) throws SQLException, ServiceLocatorException{
		return getCandidatoAppServiceRemote().obtenerCandidatos(idEmpresa, idOferta);
	}
	@Override
	public List<CandidatoVo> obtenerPostulados(long idEmpresa, long idOferta) throws SQLException, ServiceLocatorException{
		return getCandidatoAppServiceRemote().obtenerPostulados(idEmpresa, idOferta);
	}

	@Override
	public void eliminarCandidatos(long idOfertaCandidato) throws BusinessException, ServiceLocatorException, PersistenceException, SQLException {
		getCandidatoAppServiceRemote().eliminarCandidatosOferta(idOfertaCandidato);
	}
	
	@Override
	public OfertaPorPerfilVO obtenerDatosOferta(long idOfertaEmpleo) throws BusinessException, ServiceLocatorException, PersistenceException, SQLException {
		return getOfertasPorPerfilAppServiceRemote().obtenerOfertaPorId(idOfertaEmpleo);
	}

	public static AdmonCandidatosBusDelegate getInstance() {
		return instance;
	}

	/**
	 * Obtiene la referencia a los servicios de candidato
	 * 
	 * @return instancia de servcios
	 * @throws ServiceLocatorException
	 *             Lanzada en caso de error en la conexion con los servicios
	 */
	public CandidatoAppServiceRemote getCandidatoAppServiceRemote()throws ServiceLocatorException {
		CandidatoAppServiceRemote ejb = (CandidatoAppServiceRemote) ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_CANDIDATO);
		return ejb;
	}

	/**
	 * Obtiene la referencia a los servicios de candidato
	 * 
	 * @return instancia de servcios
	 * @throws ServiceLocatorException
	 *             Lanzada en caso de error en la conexion con los servicios
	 */
	public OfertasPorPerfilAppServiceRemote getOfertasPorPerfilAppServiceRemote()throws ServiceLocatorException {
		OfertasPorPerfilAppServiceRemote ejb = (OfertasPorPerfilAppServiceRemote) ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_OFERTAS_XPERFIL);
		return ejb;
	}

	@Override
	public void contratarCandidato(long idOfertaCandidato, Date fechaContrato, DestinatarioVO destinatarioVO) throws BusinessException, ServiceLocatorException, PersistenceException, SQLException, MailException {
		getCandidatoAppServiceRemote().aprobarOfertaCandidato(idOfertaCandidato, fechaContrato, destinatarioVO);
	}

	@Override
	public void rechazarCandidato(long idOfertaCandidato, int idMotivo, String motivoDesc, DestinatarioVO destinatarioVO) throws BusinessException, ServiceLocatorException, PersistenceException, SQLException, MailException {
		getCandidatoAppServiceRemote().rechazarOfertaCandidatoEnProceso(idOfertaCandidato, idMotivo, motivoDesc, destinatarioVO);
	}

	@Override
	public void procesarCandidato(long idOfertaCandidato, long idUsuario) throws BusinessException, ServiceLocatorException, PersistenceException, SQLException {
		getCandidatoAppServiceRemote().procesarOfertaCandidatoEnProceso(idOfertaCandidato, idUsuario);
	}

	@Override
	public OfertaCandidatoResumenVo obtenerOfertaCandidatoResumen(long idOfertaCandidato) throws BusinessException, ServiceLocatorException, PersistenceException, SQLException{
		return getCandidatoAppServiceRemote().obtenerOfertaCandidatoResumen(idOfertaCandidato);
	}

	@Override
	public void contactarCandidato(long idOfertaCandidato, DestinatarioVO destinatarioVO) throws BusinessException, ServiceLocatorException, PersistenceException, SQLException, MailException {
		getCandidatoAppServiceRemote().vincularOfertaCandidato(idOfertaCandidato, destinatarioVO);
		
	}

	@Override
	public void entrevistarCandidato(long idOfertaCandidato,Date fecha, String hora, DestinatarioVO destinatarioVO) throws BusinessException, ServiceLocatorException, PersistenceException, SQLException, MailException {
		getCandidatoAppServiceRemote().programarEntrevistaOfertaCandidato(idOfertaCandidato, fecha, hora, destinatarioVO);
	}
	
	/**
	 * Método que obtiene los candidatos con su grado de estudio
	 */
	public List<CandidatoVo> obtenerCandidatosOfertasActivas(long idEmpresa, long idOferta) throws SQLException, ServiceLocatorException{
		return (List<CandidatoVo>)getCandidatoAppServiceRemote().obtenerCandidatosOfertasActivas(idEmpresa, idOferta);
	}

	@Override
	public List<CandidatoVo> postulatesByEmpresaList(long idEmpresa) throws SQLException, ServiceLocatorException {
		return getCandidatoAppServiceRemote().postulatesByEmpresaList(idEmpresa);
	}

	@Override
	public List<Long> getNominalList(long idOferta) throws BusinessException, ServiceLocatorException {
		return getCandidatoAppServiceRemote().getNominalList(idOferta);
	}
}