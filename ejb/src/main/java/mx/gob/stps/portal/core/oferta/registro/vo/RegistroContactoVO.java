package mx.gob.stps.portal.core.oferta.registro.vo;

import java.io.Serializable;

public class RegistroContactoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7159332390518290718L;
	
	private long idOferta;
	private long nombreEmpresa;
	private long nombreContacto;
	private int telefonoContacto;
	private int correoContacto;
	private int domicilioContacto;
	private String lunes;
	private String martes;
	private String miercoles;
	private String jueves;
	private String viernes;
	private String sabado;
	private String domingo;
	private Long HoraAtencionInicio;
	private Long HoraAtencionFin;
	private Long DuracionAtencion;
	private long idEmpresa;
	protected int estatus;
	
	
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public long getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(long idOferta) {
		this.idOferta = idOferta;
	}
	public long getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(long nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public long getNombreContacto() {
		return nombreContacto;
	}
	public void setNombreContacto(long nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	public int getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(int telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	public int getCorreoContacto() {
		return correoContacto;
	}
	public void setCorreoContacto(int correoContacto) {
		this.correoContacto = correoContacto;
	}
	public int getDomicilioContacto() {
		return domicilioContacto;
	}
	public void setDomicilioContacto(int domicilioContacto) {
		this.domicilioContacto = domicilioContacto;
	}	
	public String getLunes() {
		return lunes;
	}
	public void setLunes(String lunes) {
		this.lunes = lunes;
	}
	public String getMartes() {
		return martes;
	}
	public void setMartes(String martes) {
		this.martes = martes;
	}
	public String getMiercoles() {
		return miercoles;
	}
	public void setMiercoles(String miercoles) {
		this.miercoles = miercoles;
	}
	public String getJueves() {
		return jueves;
	}
	public void setJueves(String jueves) {
		this.jueves = jueves;
	}
	public String getViernes() {
		return viernes;
	}
	public void setViernes(String viernes) {
		this.viernes = viernes;
	}
	public String getSabado() {
		return sabado;
	}
	public void setSabado(String sabado) {
		this.sabado = sabado;
	}
	public String getDomingo() {
		return domingo;
	}
	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}
	public Long getHoraAtencionInicio() {
		return HoraAtencionInicio;
	}
	public void setHoraAtencionInicio(Long horaAtencionInicio) {
		HoraAtencionInicio = horaAtencionInicio;
	}
	public Long getHoraAtencionFin() {
		return HoraAtencionFin;
	}
	public void setHoraAtencionFin(Long horaAtencionFin) {
		HoraAtencionFin = horaAtencionFin;
	}
	public Long getDuracionAtencion() {
		return DuracionAtencion;
	}
	public void setDuracionAtencion(Long duracionAtencion) {
		DuracionAtencion = duracionAtencion;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	
	
		
}
