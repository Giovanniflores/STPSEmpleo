package mx.gob.stps.portal.movil.app.empresa.model.base;

import mx.gob.stps.portal.movil.web.empresa.form.OfertasEmpresaForm;

public class OfertasEmpresaDTO {
	
	private OfertasEmpresaForm oferta;
	
	public OfertasEmpresaDTO(OfertasEmpresaForm oferta) {
		this.oferta = oferta;
	}
	
	public OfertasEmpresaDTO(){
		this.oferta = new OfertasEmpresaForm();
	}
	

	public OfertasEmpresaForm getOferta() {
		return oferta;
	}

	public void setOferta(OfertasEmpresaForm oferta) {
		this.oferta = oferta;
	}
	
	
	


}
