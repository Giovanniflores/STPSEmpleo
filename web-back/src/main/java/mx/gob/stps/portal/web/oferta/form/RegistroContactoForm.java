package mx.gob.stps.portal.web.oferta.form;

import org.apache.struts.action.ActionForm;

public class RegistroContactoForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8440500970246611678L;
	
	private String nombreEmpresa;
	private String nombreContacto;
	private String teleconoContacto;
	private String correoContacto;
	private String domingo;
	private String lunes; 
	private String martes;
	private String miercoles;
	private String jueves;
	private String viernes;
	private String sabado;
	private String horaAtencionInicio;
	private String horaAtencionFin;
	private String duracionAtencion;
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getNombreContacto() {
		return nombreContacto;
	}
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	public String getTeleconoContacto() {
		return teleconoContacto;
	}
	public void setTeleconoContacto(String teleconoContacto) {
		this.teleconoContacto = teleconoContacto;
	}
	public String getCorreoContacto() {
		return correoContacto;
	}
	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}
	public String getDomingo() {
		return domingo;
	}
	public void setDomingo(String domingo) {
		this.domingo = domingo;
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
	public String getHoraAtencionInicio() {
		return horaAtencionInicio;
	}
	public void setHoraAtencionInicio(String horaAtencionInicio) {
		this.horaAtencionInicio = horaAtencionInicio;
	}
	public String getHoraAtencionFin() {
		return horaAtencionFin;
	}
	public void setHoraAtencionFin(String horaAtencionFin) {
		this.horaAtencionFin = horaAtencionFin;
	}
	public String getDuracionAtencion() {
		return duracionAtencion;
	}
	public void setDuracionAtencion(String duracionAtencion) {
		this.duracionAtencion = duracionAtencion;
	}
	
	
	
	
}
