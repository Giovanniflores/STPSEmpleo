package mx.gob.stps.portal.web.candidate.delegate;

import java.util.List;

import javax.servlet.ServletOutputStream;

import mx.gob.stps.portal.core.event.service.EventoAppServiceRemote;
import mx.gob.stps.portal.persistencia.entity.CandidatoEvento;
import mx.gob.stps.portal.persistencia.entity.EventoFolio;
import mx.gob.stps.portal.persistencia.vo.EmpresaEventoVO;
import mx.gob.stps.portal.persistencia.vo.EventoVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;

public interface RegistroCandidatoEventoBusDelegate {
	
	public EventoAppServiceRemote getEventoAppService() throws ServiceLocatorException;
	
	public List<EventoVO> initEventosFerias(Integer idEntidad) throws ServiceLocatorException;
	
	public List<EmpresaEventoVO> initEmpresasEvento(Long idEvento) throws ServiceLocatorException;
	
	public CandidatoEvento initEventosCandidato(Long idCandidato,Long idEvento) throws ServiceLocatorException;

	public EventoFolio findEventoFolio(long idEvento) throws ServiceLocatorException;
	
	public Long registrarCandidatoEvento(Long idCandidato, int idEvento,int folioReg, Long idUsuario) throws ServiceLocatorException;
	
	public boolean updateFolio(EventoFolio eventoFol) throws ServiceLocatorException;
	
	public EventoVO buscaEventoPorId(Long idEvento) throws ServiceLocatorException;
	
	public void registraBitacora(Long idUsuario, long idRegistro,int folioReg, Long idCandidato, int idEvento) throws ServiceLocatorException;
	
	public ServletOutputStream imprimeComprobantePDF(
			ServletOutputStream servletOutputStream,
			List<OfertaEmpleoVO> ofertasCompat, String rutaHeader,
			String rutaFooter, Long idCandidato, Long idEvento) throws ServiceLocatorException;
	
	public List<OfertaEmpleoVO> consultaOfertasCompatiblesEvento(Integer idEvento, Long idCandidato, Integer estatusPPC) throws ServiceLocatorException;
	
	public List<EventoVO> eventosRecientes(int mes, int anio, int estatus) throws Exception;

	public List<EventoVO> previousEventList(int month, int year, int status) throws Exception;
}
