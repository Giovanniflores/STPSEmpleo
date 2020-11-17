package mx.gob.stps.portal.movil.app.empresa.model.base;

import mx.gob.stps.portal.movil.web.candidato.form.CandidatoForm;

public class BusquedaCandidatoDTO {
	
	private CandidatoForm candidato;
	private int confidencialidadDatos;
	private int compatibilidad;
	private int estatus;
    private String idOferta;	
	

	
	
	public BusquedaCandidatoDTO(CandidatoForm candidato) {
		this.candidato = candidato;
	}
	
	public BusquedaCandidatoDTO(){
		this.candidato = new CandidatoForm();
	}


	public CandidatoForm getCandidato() {
		return candidato;
	}


	public void setCandidato(CandidatoForm candidato) {
		this.candidato = candidato;
	}

	public int getCompatibilidad() {
		return compatibilidad;
	}

	public void setCompatibilidad(int compatibilidad) {
		this.compatibilidad = compatibilidad;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}

	public int getConfidencialidadDatos() {
		return confidencialidadDatos;
	}

	public void setConfidencialidadDatos(int confidencialidadDatos) {
		this.confidencialidadDatos = confidencialidadDatos;
	}



}
