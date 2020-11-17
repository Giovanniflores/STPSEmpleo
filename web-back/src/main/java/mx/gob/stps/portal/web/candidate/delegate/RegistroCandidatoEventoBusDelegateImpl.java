package mx.gob.stps.portal.web.candidate.delegate;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import mx.gob.stps.portal.core.event.service.EventoAppServiceRemote;
import mx.gob.stps.portal.persistencia.entity.CandidatoEvento;
import mx.gob.stps.portal.persistencia.entity.EventoFolio;
import mx.gob.stps.portal.persistencia.vo.EmpresaEventoVO;
import mx.gob.stps.portal.persistencia.vo.EventoVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.seguridad.vo.UsuarioFirmadoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.service.ServiceLocator;
import mx.gob.stps.portal.web.infra.utils.Constantes;


public class RegistroCandidatoEventoBusDelegateImpl implements RegistroCandidatoEventoBusDelegate {
	
	private static RegistroCandidatoEventoBusDelegate instancia = new RegistroCandidatoEventoBusDelegateImpl();
	
	private RegistroCandidatoEventoBusDelegateImpl(){
		
	}

	public static RegistroCandidatoEventoBusDelegate getInstance(){
		return instancia;
	}
	
	public EventoAppServiceRemote getEventoAppService() throws ServiceLocatorException{
		EventoAppServiceRemote ejb = (EventoAppServiceRemote) ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_EVENTO);
		return ejb;
	}
	
	public List<EventoVO> initEventosFerias(Integer idEntidad) throws ServiceLocatorException {
		return getEventoAppService().obtieneEventosFerias(idEntidad);
	}
	
	public List<EmpresaEventoVO> initEmpresasEvento(Long idEvento) throws ServiceLocatorException {
		return getEventoAppService().consultarEmpresasEvento(idEvento);
	}
	
	public CandidatoEvento initEventosCandidato(Long idCandidato,
			Long idEvento) throws ServiceLocatorException {
		return getEventoAppService().obtieneEventosCandidato(idCandidato, idEvento);
	}
	
	public EventoFolio findEventoFolio(long idEvento) throws ServiceLocatorException {
		return getEventoAppService().findEventoFolio(idEvento);
	}
	
	public Long registrarCandidatoEvento(Long idCandidato, int idEvento,
			int folioReg, Long idUsuario) throws ServiceLocatorException {
		return getEventoAppService().registrarCandidatoEvento(idCandidato, idEvento, folioReg, idUsuario);
	}
	
	public boolean updateFolio(EventoFolio eventoFol) throws ServiceLocatorException {
		return getEventoAppService().actualizaFolio(eventoFol);
	}
	
	public EventoVO buscaEventoPorId(Long idEvento) throws ServiceLocatorException{
		return getEventoAppService().findEventoById(idEvento);
	}

	@Override
	public void registraBitacora(Long idUsuario, long idRegistro,
			int folioReg, Long idCandidato, int idEvento)
			throws ServiceLocatorException {
		 getEventoAppService().registraBitacora(idUsuario, idRegistro, folioReg, idCandidato, idEvento);
		
	}

	@Override
	public ServletOutputStream imprimeComprobantePDF(
			ServletOutputStream servletOutputStream,
			List<OfertaEmpleoVO> ofertasCompat, String rutaHeader,
			String rutaFooter, Long idCandidato, Long idEvento) throws ServiceLocatorException   {
		return getEventoAppService().imprimeComprobantePDF(servletOutputStream, ofertasCompat, rutaHeader, rutaFooter, idCandidato, idEvento);
	}

	@Override
	public List<OfertaEmpleoVO> consultaOfertasCompatiblesEvento(
			Integer idEvento, Long idCandidato, Integer estatusPPC) throws ServiceLocatorException {
		return getEventoAppService().consultaOfertasCompatiblesEvento(idEvento, idCandidato, estatusPPC);
	}
	
	public EventoAppServiceRemote getEventoAppServiceRemote() throws ServiceLocatorException {
		EventoAppServiceRemote ejb = (EventoAppServiceRemote)ServiceLocator.getSessionRemote(Constantes.JNDI_EJB_EVENTO);
		return ejb;
	} 

	@Override
	public List<EventoVO> eventosRecientes(int mes, int anio, int estatus) throws Exception {
		return getEventoAppServiceRemote().eventosRecientes( mes, anio, estatus);
	}
	
	@Override
	public List<EventoVO> previousEventList(int month, int year, int status) throws Exception {
		return getEventoAppServiceRemote().previousEventList( month, year, status);
	}
}
