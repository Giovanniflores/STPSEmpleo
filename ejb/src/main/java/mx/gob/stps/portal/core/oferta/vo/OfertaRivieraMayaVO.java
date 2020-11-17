package mx.gob.stps.portal.core.oferta.vo;

import java.io.Serializable;

public class OfertaRivieraMayaVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String prestaciones;
	private String vigencia;
	private String gradoescolaridad;
	private String ubicacion;
	private String conocimientos;
	private String experiencia;
	private String estatus;
	private String descripcion;
	private Long idEempresa;
	private Long idOferta;
	private String sueldo;
	private String nombrepuesto;
	private String nombreContacto;
	private String nombre;
	private String razonSocial;
	private String correoContacto;
	
	public OfertaRivieraMayaVO(){}

	public String getPrestaciones() {
		return prestaciones;
	}

	public void setPrestaciones(String prestaciones) {
		this.prestaciones = prestaciones;
	}

	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	public String getGradoescolaridad() {
		return gradoescolaridad;
	}

	public void setGradoescolaridad(String gradoescolaridad) {
		this.gradoescolaridad = gradoescolaridad;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getConocimientos() {
		return conocimientos;
	}

	public void setConocimientos(String conocimientos) {
		this.conocimientos = conocimientos;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Long getIdEempresa() {
		return idEempresa;
	}

	public void setIdEempresa(Long idEempresa) {
		this.idEempresa = idEempresa;
	}

	public Long getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Long idOferta) {
		this.idOferta = idOferta;
	}

	public String getSueldo() {
		return sueldo;
	}

	public void setSueldo(String sueldo) {
		this.sueldo = sueldo;
	}

	public String getNombrepuesto() {
		return nombrepuesto;
	}

	public void setNombrepuesto(String nombrepuesto) {
		this.nombrepuesto = nombrepuesto;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}
	
	
}
