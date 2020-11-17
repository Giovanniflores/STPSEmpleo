package mx.gob.stps.portal.web.offer.form;

import java.io.Serializable;

public class BusquedaEspecificaForm implements Serializable{

	private static final long serialVersionUID = -4392208547407277641L;
	
	private Integer entidad;
	
	private Integer area;
	
	private Integer escolaridad;
	
	private Integer salario;
	
	private Integer idMunicipio;
	
	private Integer ocupacion;
	
	private Integer carrera;
	
	private Integer edad;
	
	private Integer region;
	
	private Integer fuente;
	
	/**
	 * Constructor sin argumentos
	 */
	public BusquedaEspecificaForm(){
		
	}

	//Métodos públicos de acceso
	
	public Integer getEntidad() {
		return entidad;
	}

	public void setEntidad(Integer entidad) {
		this.entidad = entidad;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(Integer escolaridad) {
		this.escolaridad = escolaridad;
	}

	public Integer getSalario() {
		return salario;
	}

	public void setSalario(Integer salario) {
		this.salario = salario;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Integer ocupacion) {
		this.ocupacion = ocupacion;
	}

	public Integer getCarrera() {
		return carrera;
	}

	public void setCarrera(Integer carrera) {
		this.carrera = carrera;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public void setFuente(Integer fuente) {
		this.fuente = fuente;
	}

	public Integer getFuente() {
		return fuente;
	}
}
