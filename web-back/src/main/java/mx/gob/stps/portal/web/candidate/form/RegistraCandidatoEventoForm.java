package mx.gob.stps.portal.web.candidate.form;

import java.util.List;



import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.EmpresaEventoVO;
import mx.gob.stps.portal.persistencia.vo.EventoVO;

import org.apache.struts.action.ActionForm;

public class RegistraCandidatoEventoForm extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8104345009839050904L;

	private List<CatalogoOpcionVO> locationEntityCatalog;
	
	 private Integer locationEntity ;
	 
	 private List<EventoVO> eventos;
	 
	 private List<EmpresaEventoVO> empresasEvento;
	 
	 private EventoVO evento;
	 
	 private Long idCandidato;
	 
	 private String orderType;
	 
	 private Integer columnNumber;
	 
	 private Integer eventosSize;
	 
	 private Long idEvento;
	 
	 private boolean msjExito;
	 
	 private boolean registrado;
	 
	 
	 public RegistraCandidatoEventoForm(){
		 
	 }
	 
	
	public List<CatalogoOpcionVO> getLocationEntityCatalog() {
		return locationEntityCatalog;
	}

	public void setLocationEntityCatalog(List<CatalogoOpcionVO> locationEntityCatalog) {
		this.locationEntityCatalog = locationEntityCatalog;
	}

	public Integer getLocationEntity() {
		return locationEntity;
	}

	public void setLocationEntity(Integer locationEntity) {
		this.locationEntity = locationEntity;
	}

	public List<EventoVO> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoVO> eventos) {
		this.eventos = eventos;
	}

	public List<EmpresaEventoVO> getEmpresasEvento() {
		return empresasEvento;
	}

	public void setEmpresasEvento(List<EmpresaEventoVO> empresasEvento) {
		this.empresasEvento = empresasEvento;
	}

	public Long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public EventoVO getEvento() {
		return evento;
	}

	public void setEvento(EventoVO evento) {
		this.evento = evento;
	}
	
	public void reset(){
		locationEntityCatalog = null;
		locationEntity = 0;
		eventos = null;
		empresasEvento = null;
		evento = null;
		idCandidato = null;
	}


	public String getOrderType() {
		return orderType;
	}


	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}


	public Integer getColumnNumber() {
		return columnNumber;
	}


	public void setColumnNumber(Integer columnNumber) {
		this.columnNumber = columnNumber;
	}


	public Integer getEventosSize() {
		return eventosSize;
	}


	public void setEventosSize(Integer eventosSize) {
		this.eventosSize = eventosSize;
	}


	public Long getIdEvento() {
		return idEvento;
	}


	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}


	public boolean isMsjExito() {
		return msjExito;
	}


	public void setMsjExito(boolean msjExito) {
		this.msjExito = msjExito;
	}


	public boolean isRegistrado() {
		return registrado;
	}


	public void setRegistrado(boolean registrado) {
		this.registrado = registrado;
	}

}
