package mx.gob.stps.portal.core.oferta.envioSMS.vo;

import java.util.Date;

public class OfertasSMSVO {
	
	private long idOfertaSms;
	private int idCandidato;
	private long idOfertaEmpleo;
	private int idTelefono;
	private String telefono;
	private String mensaje;
	private Date fechaEnvio;
	private long idEntidad;
	private String entidad;	
	
	public int getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}
	public int getIdTelefono() {
		return idTelefono;
	}
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	public Date getFechaEnvio() {
		return fechaEnvio;
	}
	public void setIdOfertaSms(long idOfertaSms) {
		this.idOfertaSms = idOfertaSms;
	}
	public long getIdOfertaSms() {
		return idOfertaSms;
	}
	public long getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
}
