package mx.gob.stps.portal.movil.app.model.base;

import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.movil.app.service.util.OfertaUtil;

public class PostulatedDTO {


	OfertaEmpleoJB oferta;
	long idCandidato;
	String idPostulated;
	int estatus;
	String fechaPostulated;
	Long compatibilidad;

	public PostulatedDTO(){
		
	}
	
	public PostulatedDTO(String idPostulated, int estatus, String fechaAlta, Long compatibilidad){
		this.idPostulated = idPostulated;
		this.estatus = estatus;
		this.fechaPostulated = fechaAlta;
		this.compatibilidad = compatibilidad;
	}
	
	public PostulatedDTO(String idPostulated, int estatus, String fechaAlta){
		this.idPostulated = idPostulated;
		this.estatus = estatus;
		this.fechaPostulated = fechaAlta;
	}
	
	public PostulatedDTO(long idPostulated, int estatus,String fechaAlta){
		this.idPostulated = String.valueOf(idPostulated);
		this.estatus = estatus;
		this.fechaPostulated = fechaAlta;
	}
	
	public PostulatedDTO(OfertaEmpleoJB miOferta, Long idCandidato) {
		this.idCandidato = idCandidato;
		this.oferta = miOferta;
	}
	
	public PostulatedDTO(OfertaEmpleoJB miOferta, Long idCandidato, Long compatibilidad) {
		this.idCandidato = idCandidato;
		this.oferta = miOferta;
		this.compatibilidad = compatibilidad;
	}

	public String getIdPostulated() {
		return idPostulated;
	}

	public void setIdPostulated(String idPostulated) {
		this.idPostulated = idPostulated;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getFechaPostulated() {
		return fechaPostulated;
	}

	public void setFechaPostulated(String fechaPostulated) {
		this.fechaPostulated = fechaPostulated;
	}

	public OfertaEmpleoJB getOferta() {
		return oferta;
	}

	public void setOferta(OfertaEmpleoJB oferta) {
		this.oferta = oferta;
	}

	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public Long getCompatibilidad() {
		return compatibilidad;
	}

	public void setCompatibilidad(Long compatibilidad) {
		this.compatibilidad = compatibilidad;
	}

	
}
