package mx.gob.stps.portal.core.curso.vo;

import java.io.Serializable;


public class CursoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8025886753125607908L;

	private int idCurso;
	
	private String correo;

	private String curso;

	private String domicilio;

	private String fax;

	private int idEntidad;

	private String linkPdf;

	private String plantel;

	private String telefono;
	
	private String entidad;

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public String getLinkPdf() {
		return linkPdf;
	}

	public void setLinkPdf(String linkPdf) {
		this.linkPdf = linkPdf;
	}

	public String getPlantel() {
		return plantel;
	}

	public void setPlantel(String plantel) {
		this.plantel = plantel;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getEntidad() {
		return entidad;
	}
	
	
	
	
}
