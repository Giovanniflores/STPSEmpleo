package mx.gob.stps.portal.movil.app.model.base;

import mx.gob.stps.portal.movil.web.oferta.form.OfertaJB;

public class OfertasRecomendadasDTO{
	
	public OfertasRecomendadasDTO(OfertaJB oferta){
		this.oferta = oferta;
	}
	private OfertaJB oferta;
	
	public OfertaJB getOferta() {
		return oferta;
	}

	public void setOferta(OfertaJB oferta) {
		this.oferta = oferta;
	}

	private int compatibilidad;

	public int getCompatibilidad() {
		return compatibilidad;
	}

	public void setCompatibilidad(int compatibilidad) {
		this.compatibilidad = compatibilidad;
	}
	
	private String fechaAlta;

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
		
	
	
}

