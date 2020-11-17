package mx.gob.stps.portal.movil.app.empresa.model.rest;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;

public class VincularCandidatoRestDTO extends BaseRestDTO{
	
	private String idOferta;	
    private String idCandidato;	    
	private int estatus;    
	private String nombreEmpresa;
	private String tituloOferta;
	private String correoElectronico;
	private String tipoPersona;
	

    public String getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}
	public String getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(String idCandidato) {
		this.idCandidato = idCandidato;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombre(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getTituloOferta() {
		return tituloOferta;
	}
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
}
