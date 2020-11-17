package mx.gob.stps.portal.core.persistencia.facade;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import mx.gob.stps.portal.exception.BusinessException;
import mx.gob.stps.portal.exception.TechnicalException;
import mx.gob.stps.portal.persistencia.entity.CandidatoEvento;
import mx.gob.stps.portal.persistencia.entity.EventoFolio;
import mx.gob.stps.portal.persistencia.vo.ComprobanteRegistroEventoCandidatoVO;
import mx.gob.stps.portal.persistencia.vo.EmpresaEventoVO;
import mx.gob.stps.portal.persistencia.vo.EventoVO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpleoVO;

@Local
public interface EventoFacadeLocal {
	
	public List<EventoVO> obtieneEventosFerias(Integer idEntidad) throws TechnicalException;
	
	public List<EmpresaEventoVO> consultarEmpresasEvento(Long idEvento) throws NoResultException,TechnicalException;
	
	public CandidatoEvento obtieneEventosCandidato(Long idCandidato, Long idEvento)	throws NoResultException, TechnicalException;
	
	public EventoFolio findEventoFolio(long idEvento) throws IllegalArgumentException, NoResultException, TechnicalException;
	
	public Long registrarCandidatoEvento(Long idCandidato,int idEvento, int folioReg, Long idUsuario) throws BusinessException;
	
	public boolean actualizaFolio(EventoFolio eventoFol) throws BusinessException;
	
	public EventoVO findEventoById(Long idEvento) throws IllegalArgumentException, NoResultException, TechnicalException;
	
	public ComprobanteRegistroEventoCandidatoVO consultaCandidatoEventoImprimir(Long idCandidato, Long idEvento)
			throws IllegalArgumentException, NoResultException,
			TechnicalException;
	
	public List<OfertaEmpleoVO> findOfertaEmpleoEvento(String idOfertas,Integer idEvento) throws  NoResultException,PersistenceException;
	
	public EventoVO asignaMunicipioColonia(EventoVO evento) throws NoResultException;
	
	public EventoVO asignaSoloMunicipio(EventoVO evento) throws NoResultException;
	
	public List<EventoVO> eventosRecientes(int mes, int anio, int estatus) throws Exception;

	public List<EventoVO> previousEventList(int month, int year, int status) throws Exception;
}
