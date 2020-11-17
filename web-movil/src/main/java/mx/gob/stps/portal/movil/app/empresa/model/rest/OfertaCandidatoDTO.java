package mx.gob.stps.portal.movil.app.empresa.model.rest;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;

public class OfertaCandidatoDTO extends BaseRestDTO {

	List<OfertaCandidatoOcupacionDTO> ofertaCandidato = new ArrayList<OfertaCandidatoOcupacionDTO>();
	Integer tamano = 0;
	
	
	public List<OfertaCandidatoOcupacionDTO> getOfertaCandidato() {
		return ofertaCandidato;
	}

	public void setOfertaCandidato(List<OfertaCandidatoOcupacionDTO> ofertaCandidato) {
		this.ofertaCandidato = ofertaCandidato;
	}

	public Integer getTamano() {
		return tamano;
	}

	public void setTamano(Integer tamano) {
		this.tamano = tamano;
	}
	
	
	
}
