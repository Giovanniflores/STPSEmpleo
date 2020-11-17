package mx.gob.stps.portal.movil.app.empresa.model.base;

public class HiredDTO {

	private String idOfertaCandidato;
	private int estatus;
	private int idMotivo;
	private String fechaContratacion;
	private String nombreEmpresa;
	private String tituloOferta;
	private String correoElectronico;
	private String tipoPersona;
	

	public HiredDTO(){
		
	}
	
	public HiredDTO(String idOfertaCandidato, int idMotivo, int estatus, String fechaContratacion, String nombreEmpresa, String tituloOferta, String correoElectronico, String tipoPersona){
		this.idOfertaCandidato = idOfertaCandidato;
		this.idMotivo = idMotivo;
		this.estatus = estatus;
		this.fechaContratacion = fechaContratacion;
		this.nombreEmpresa = nombreEmpresa;
        this.tituloOferta = tituloOferta;	
        this.correoElectronico = correoElectronico; 
        this.tipoPersona = tipoPersona;         
	}
	
	public HiredDTO(long idOfertaCandidato, int idMotivo, int estatus, String fechaContratacion, String nombreEmpresa, String tituloOferta, String correoElectronico, String tipoPersona){
		this.idOfertaCandidato = String.valueOf(idOfertaCandidato);
		this.idMotivo = idMotivo;
		this.estatus = estatus;
		this.fechaContratacion = fechaContratacion;
		this.nombreEmpresa = nombreEmpresa;
        this.tituloOferta = tituloOferta;	
        this.correoElectronico = correoElectronico; 
        this.tipoPersona= tipoPersona;
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

	public String getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFecha(String fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public int getIdMotivo() {
		return idMotivo;
	}

	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}

	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombre(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
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
