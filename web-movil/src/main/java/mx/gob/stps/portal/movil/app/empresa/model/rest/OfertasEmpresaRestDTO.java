package mx.gob.stps.portal.movil.app.empresa.model.rest;


import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.movil.app.empresa.model.base.OfertasEmpresaDTO;
import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;

public class OfertasEmpresaRestDTO extends BaseRestDTO{
	
	private List<BusquedaOfertaDTO> ofertas = new ArrayList<BusquedaOfertaDTO>();
	private int tamano;
	
	
	
	public int getTamano() {
		return tamano;
	}
	
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public List<BusquedaOfertaDTO> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<BusquedaOfertaDTO> ofertas) {
		this.ofertas = ofertas;
	}
	


}
