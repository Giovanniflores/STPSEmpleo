package mx.gob.stps.portal.movil.app.empresa.model.rest;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;

public class OfertaRestDTO extends BaseRestDTO{
	
	private String idOferta;
	private int estatus;

	public String getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

}
