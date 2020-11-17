package mx.gob.stps.portal.movil.app.empresa.model.rest;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.movil.app.empresa.model.base.HiredDTO;
import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;

public class HiredRestDTO extends BaseRestDTO{

	List<HiredDTO> ofertasCandidato;
	
	public HiredRestDTO (){
		ofertasCandidato = new ArrayList<HiredDTO>();
	}

	public List<HiredDTO> getOfertasCandidato() {
		return ofertasCandidato;
	}

	public void setOfertasCandidato(List<HiredDTO> ofertasCandidato) {
		this.ofertasCandidato = ofertasCandidato;
	}
	
	
	
}
