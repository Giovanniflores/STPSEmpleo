package mx.gob.stps.portal.core.oferta.registro.vo;

import java.io.Serializable;

public class RegistroIdiomaVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2538930243724630434L;
	private long idioma;
	private long certificacion;
	private long dominio;
	private String idiomaDescripcion;
	private String dominioDescripcion;
	private String certificacionDescripcion;
	private int principal;
	
	
	
	public String getCertificacionDescripcion() {
		return certificacionDescripcion;
	}
	public void setCertificacionDescripcion(String certificacionDescripcion) {
		this.certificacionDescripcion = certificacionDescripcion;
	}
	public int getPrincipal() {
		return principal;
	}
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	public long getIdioma() {
		return idioma;
	}
	public void setIdioma(long idioma) {
		this.idioma = idioma;
	}
	public long getCertificacion() {
		return certificacion;
	}
	public void setCertificacion(long certificacion) {
		this.certificacion = certificacion;
	}
	public long getDominio() {
		return dominio;
	}
	public void setDominio(long dominio) {
		this.dominio = dominio;
	}
	public String getIdiomaDescripcion() {
		return idiomaDescripcion;
	}
	public void setIdiomaDescripcion(String idiomaDescripcion) {
		this.idiomaDescripcion = idiomaDescripcion;
	}
	public String getDominioDescripcion() {
		return dominioDescripcion;
	}
	public void setDominioDescripcion(String dominioDescripcion) {
		this.dominioDescripcion = dominioDescripcion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
	
	
	
}
