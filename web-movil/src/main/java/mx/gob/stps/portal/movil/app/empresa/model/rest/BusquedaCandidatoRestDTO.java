package mx.gob.stps.portal.movil.app.empresa.model.rest;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.movil.app.empresa.model.base.BusquedaCandidatoDTO;
import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
//import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;

public class BusquedaCandidatoRestDTO extends BaseRestDTO {
	
	private List<BusquedaCandidatoDTO> candidatos = new ArrayList<BusquedaCandidatoDTO>();
	private BusquedaCandidatoDTO candidato = new BusquedaCandidatoDTO();
	private List<CandidatoVo> candidatosRec = new ArrayList<CandidatoVo>();
	private List<String> idCandidatos = new ArrayList<String>();
	private List<OfertaCandidatoOcupacionDTO> ofertaCandidato = new ArrayList<OfertaCandidatoOcupacionDTO>();
	private List<Long> idCandidates = new ArrayList<Long>();
    private String idOferta;	

 //   private List<OfertaCandidatoOcupacionDTO> ofertaCandidato = new ArrayList<OfertaCandidatoOcupacionDTO>();
    
	private int tamano;
	
	
	public List<BusquedaCandidatoDTO> getCandidatos() {
		return candidatos;
	}
	public void setCandidatos(List<BusquedaCandidatoDTO> candidatos) {
		this.candidatos = candidatos;
	}

	public List<String> getIdCandidatos() {
		return idCandidatos;
	}
	public void setIdCandidatos(List<String> idCandidatos) {
		this.idCandidatos = idCandidatos;
	}
	public String getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}
	public int getTamano() {
		return tamano;
	}
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public List<OfertaCandidatoOcupacionDTO> getOfertaCandidato() {
		return ofertaCandidato;
	}
	
	public void setOfertaCandidato(List<OfertaCandidatoOcupacionDTO> ofertaCandidato) {
		this.ofertaCandidato = ofertaCandidato;
	}
	public List<CandidatoVo> getCandidatosRec() {
		return candidatosRec;
	}
	public void setCandidatosRec(List<CandidatoVo> candidatosRec) {
		this.candidatosRec = candidatosRec;
	}
	public BusquedaCandidatoDTO getCandidato() {
		return candidato;
	}
	public void setCandidato(BusquedaCandidatoDTO candidato) {
		this.candidato = candidato;
	}
	public List<Long> getIdCandidates() {
		return idCandidates;
	}
	public void setIdCandidates(List<Long> idCandidates) {
		this.idCandidates = idCandidates;
	}
	
	

}
