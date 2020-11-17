package mx.gob.stps.portal.movil.app.model.rest;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;


public class BusquedaOfertaRestDTO extends BaseRestDTO {

	
	private List<BusquedaOfertaDTO> ofertas = new ArrayList<BusquedaOfertaDTO>();
	private List<String> idOfertas = new ArrayList<String>();
	private int tamano;

	public List<BusquedaOfertaDTO> getOfertas() {
		return ofertas;
	}
	
	

	public void setOfertas(List<BusquedaOfertaDTO> ofertasDetaile) {
		this.ofertas = ofertasDetaile;
		
	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
		
	}

	public List<String> getIdOfertas() {
		return idOfertas;
	}

	public void setIdOfertas(List<String> idOfertas) {
		this.idOfertas = idOfertas;
	}
	
	
	
	
}
