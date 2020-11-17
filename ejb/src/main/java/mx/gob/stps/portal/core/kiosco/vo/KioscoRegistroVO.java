package mx.gob.stps.portal.core.kiosco.vo;

import java.io.Serializable;
import java.util.Date;


public class KioscoRegistroVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 760593730082233331L;
	
	private long idKioscoRegistro;
	private long empleadoActualmente;
	private Date fechaRegistro;
	private long genero;
	private long idExperienciaTotal;
	private long idKiosco;
	private long idNivelEstudios;
	private long idRangoEdad;
	private String ipOrigen;
	private long registradoPortal;
	
	
	public long getIdKioscoRegistro() {
		return idKioscoRegistro;
	}
	public void setIdKioscoRegistro(long idKioscoRegistro) {
		this.idKioscoRegistro = idKioscoRegistro;
	}
	public long getEmpleadoActualmente() {
		return empleadoActualmente;
	}
	public void setEmpleadoActualmente(long empleadoActualmente) {
		this.empleadoActualmente = empleadoActualmente;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public long getGenero() {
		return genero;
	}
	public void setGenero(long genero) {
		this.genero = genero;
	}
	public long getIdExperienciaTotal() {
		return idExperienciaTotal;
	}
	public void setIdExperienciaTotal(long idExperienciaTotal) {
		this.idExperienciaTotal = idExperienciaTotal;
	}
	public long getIdKiosco() {
		return idKiosco;
	}
	public void setIdKiosco(long idKiosco) {
		this.idKiosco = idKiosco;
	}
	public long getIdNivelEstudios() {
		return idNivelEstudios;
	}
	public void setIdNivelEstudios(long idNivelEstudios) {
		this.idNivelEstudios = idNivelEstudios;
	}
	public long getIdRangoEdad() {
		return idRangoEdad;
	}
	public void setIdRangoEdad(long idRangoEdad) {
		this.idRangoEdad = idRangoEdad;
	}
	public String getIpOrigen() {
		return ipOrigen;
	}
	public void setIpOrigen(String ipOrigen) {
		this.ipOrigen = ipOrigen;
	}
	public long getRegistradoPortal() {
		return registradoPortal;
	}
	public void setRegistradoPortal(long registradoPortal) {
		this.registradoPortal = registradoPortal;
	}
	
	
	

}
