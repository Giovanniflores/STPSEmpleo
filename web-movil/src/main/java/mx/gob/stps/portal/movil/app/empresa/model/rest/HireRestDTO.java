package mx.gob.stps.portal.movil.app.empresa.model.rest;


import mx.gob.stps.portal.movil.app.empresa.model.base.HiredDTO;
import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;

public class HireRestDTO extends BaseRestDTO{

	private String idOfertaCandidato;
	private int estatus;
	private int idMotivo;
	private String FechaContratacion;
	private String nombreEmpresa;
	private String tituloOferta;
	private String correoElectronico;
	private String tipoPersona;

	
    public HireRestDTO(){
    	
    }
    
    public HireRestDTO(HiredDTO hiredDTO){
    	this.idOfertaCandidato = hiredDTO.getIdOfertaCandidato();
    	this.estatus = hiredDTO.getEstatus();
    	this.idMotivo = hiredDTO.getIdMotivo();
    	this.FechaContratacion = hiredDTO.getFechaContratacion();
    	this.nombreEmpresa = hiredDTO.getNombreEmpresa();
    	this.tituloOferta = hiredDTO.getTituloOferta();
    	this.correoElectronico = hiredDTO.getCorreoElectronico();
    	this.tipoPersona = hiredDTO.getTipoPersona();

    }
	
	public String getIdOfertaCandidato() {
		return idOfertaCandidato;
	}

	public void setIdCandidato(String idOfertaCandidato) {
		this.idOfertaCandidato = idOfertaCandidato;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public int getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
	}

	public String getFechaContratacion() {
		return FechaContratacion;
	}

	public void setFechaContratacion(String fechaContratacion) {
		FechaContratacion = fechaContratacion;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
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
