package mx.gob.stps.portal.core.event.service;

import java.util.List;

import javax.ejb.Remote;
import javax.servlet.ServletOutputStream;

import mx.gob.stps.portal.persistencia.entity.CandidatoEvento;
import mx.gob.stps.portal.persistencia.entity.EventoFolio;
import mx.gob.stps.portal.persistencia.vo.EmpresaEventoVO;
import mx.gob.stps.portal.persistencia.vo.EventoVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO;


@Remote
public interface EventoAppServiceRemote {

	public List<EventoVO> obtieneEventosFerias(Integer idEntidad);
	
	public List<EmpresaEventoVO> consultarEmpresasEvento(Long idEvento) ;
	
	public CandidatoEvento obtieneEventosCandidato(Long idCandidato, Long idEvento);
	
	public EventoFolio findEventoFolio(long idEvento);
	
	public Long registrarCandidatoEvento(Long idCandidato, int idEvento,int folioReg,Long idUsuario);
	
	public boolean actualizaFolio(EventoFolio eventoFol);
	
	public EventoVO findEventoById(Long idEvento);
	
	public void registraBitacora(Long idUsuario,long idRegistro,int folioReg, Long idCandidato, int idEvento);
	
	public ServletOutputStream imprimeComprobantePDF(
			ServletOutputStream servletOutputStream,
			List<OfertaEmpleoVO> ofertasCompat, String rutaHeader,
			String rutaFooter,Long idCandidato,Long idEvento);
	
	public List<OfertaEmpleoVO> consultaOfertasCompatiblesEvento(Integer idEvento, Long idCandidato, Integer estatusPPC);
	
	public List<EventoVO> eventosRecientes(int mes, int anio, int estatus) throws Exception;
	
	public List<EventoVO> previousEventList(int month, int year, int status) throws Exception;	
}
