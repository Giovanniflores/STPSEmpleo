package mx.gob.stps.portal.movil.app.model.rest;

import java.util.List;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.movil.app.model.base.OfertasRecomendadasDTO;

public class OfertasRecomendadasRestDTO extends BaseRestDTO {

	private List<OfertasRecomendadasDTO> ofertas;

	private int tamano;

	public List<OfertasRecomendadasDTO> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertasRecomendadasDTO> ofertasDetaile) {
		this.ofertas = ofertasDetaile;

	}

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;

	}

}
