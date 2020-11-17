package mx.gob.stps.portal.movil.app.model.rest;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;

public class ConfidencialidadDatosRestDTO extends BaseRestDTO{
	private boolean datosprivado;

	public boolean isDatosprivado() {
		return datosprivado;
	}

	public void setDatosprivado(boolean datosprivado) {
		this.datosprivado = datosprivado;
	}
	
	
}
