package mx.gob.stps.portal.core.infra.vo;

import java.io.Serializable;
import java.util.Date;

public final class CandidatosVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6558784347371780702L;

	private long idCandidato;

	private long idUsuario;

	private String curp;

	private String nombre;

	private String apellido1;

	private String apellido2;

	private int genero;

	private Date fechaNacimiento;

	private int edad;

	private long idEntidadNacimiento;

	private long idEstadoCivil;

	private long idTipoDiscapacidad;

	private long idMedioPortal;

	private int confidencialidadDatos;

	private int veracidadDatos;

	private int aceptacionTerminos;

	private Date fechaAlta;

	private int estatus;

	private Date fechaUltimaActualizacion;

	private String correoElectronico;	
	
	public CandidatosVO(){}
	
	public CandidatosVO(long idCandidato, long idUsuario, String curp,
			String nombre, String apellido1, String apellido2, int genero, Date fechaNacimiento, int edad,
			long idEntidadNacimiento, long idEstadoCivil, long idTipoDiscapacidad, long idMedioPortal, int confidencialidadDatos,
			int veracidadDatos, int aceptacionTerminos, Date fechaAlta, int estatus,
			Date fechaUltimaActualizacion,String correoElectronico){
		this.idCandidato = idCandidato;
		this.idUsuario = idUsuario;
		this.curp = curp;
		this.nombre = nombre;
		this.apellido1 =apellido1;
		this.apellido2 = apellido2;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.idEntidadNacimiento = idEntidadNacimiento;
		this.idEstadoCivil = idEstadoCivil;
		this.idTipoDiscapacidad = idTipoDiscapacidad;
		this.idMedioPortal = idMedioPortal;
		this.confidencialidadDatos = confidencialidadDatos;
		this.veracidadDatos = veracidadDatos;
		this.aceptacionTerminos = aceptacionTerminos;
		this.fechaAlta = fechaAlta;
		this.estatus = estatus;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.correoElectronico = correoElectronico;
	}

	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}	

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public long getIdEntidadNacimiento() {
		return idEntidadNacimiento;
	}

	public void setIdEntidadNacimiento(long idEntidadNacimiento) {
		this.idEntidadNacimiento = idEntidadNacimiento;
	}	
	
	public long getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(long idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}	
	
	public long getIdTipoDiscapacidad() {
		return idTipoDiscapacidad;
	}

	public void setIdTipoDiscapacidad(long idTipoDiscapacidad) {
		this.idTipoDiscapacidad = idTipoDiscapacidad;
	}	
	
	public long getIdMedioPortal() {
		return idMedioPortal;
	}

	public void setIdMedioPortal(long idMedioPortal) {
		this.idMedioPortal = idMedioPortal;
	}	

	public int getConfidencialidadDatos() {
		return confidencialidadDatos;
	}

	public void setConfidencialidadDatos(int confidencialidadDatos) {
		this.confidencialidadDatos = confidencialidadDatos;
	}
	
	public int getVeracidadDatos() {
		return veracidadDatos;
	}

	public void setVeracidadDatos(int veracidadDatos) {
		this.veracidadDatos = veracidadDatos;
	}
	
	public int getAceptacionTerminos() {
		return aceptacionTerminos;
	}

	public void setAceptacionTerminos(int aceptacionTerminos) {
		this.aceptacionTerminos = aceptacionTerminos;
	}	
	
	
	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaAlta) {
		this.fechaUltimaActualizacion = fechaAlta;
	}
	
	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
}
