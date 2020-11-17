package mx.gob.stps.portal.core.oferta.registro.vo;

import java.io.Serializable;

public class RequisitoVO implements Serializable{

	/**
	 *  
	 */
	private static final long serialVersionUID = 8375943581335093465L;
	
	private long idRegistro;
	private String descripcion;
	private long experiencia;
	private long dominio;
	private String expDescripcion;
	private String domDescripcion;
	private int principal;
	private long tipo;
	
	
	
	
	public long getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(long idRegistro) {
		this.idRegistro = idRegistro;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public long getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(long experiencia) {
		this.experiencia = experiencia;
	}
	public long getDominio() {
		return dominio;
	}
	public void setDominio(long dominio) {
		this.dominio = dominio;
	}
	public String getExpDescripcion() {
		return expDescripcion;
	}
	public void setExpDescripcion(String expDescripcion) {
		this.expDescripcion = expDescripcion;
	}
	public String getDomDescripcion() {
		return domDescripcion;
	}
	public void setDomDescripcion(String domDescripcion) {
		this.domDescripcion = domDescripcion;
	}
	public int getPrincipal() {
		return principal;
	}
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	public long getTipo() {
		return tipo;
	}
	public void setTipo(long tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
}
