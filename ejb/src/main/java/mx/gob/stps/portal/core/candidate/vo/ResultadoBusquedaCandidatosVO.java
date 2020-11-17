package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

public class ResultadoBusquedaCandidatosVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idCandidato;
	
	private String nombreCompleto;
	
	private String estudios;
	
	private String edad;
	
	private String entidadFederativa;
	
	private String ocupacionDeseada;
	
	private String salarioPretendido;
	
	private String tipoContrato;
	
	private String tipoEmpleo;
	
	private String viajar;
	
	private String radicar;
	
	private String subAreaLaboralExpectativa;
	
	public ResultadoBusquedaCandidatosVO(){}
	
	//Getters & Setters
	public Long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getEstudios() {
		return estudios;
	}

	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getEntidadFederativa() {
		return entidadFederativa;
	}

	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}

	public String getOcupacionDeseada() {
		return ocupacionDeseada;
	}

	public void setOcupacionDeseada(String ocupacionDeseada) {
		this.ocupacionDeseada = ocupacionDeseada;
	}

	public String getSalarioPretendido() {
		return salarioPretendido;
	}

	public void setSalarioPretendido(String salarioPretendido) {
		this.salarioPretendido = salarioPretendido;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getTipoEmpleo() {
		return tipoEmpleo;
	}

	public void setTipoEmpleo(String tipoEmpleo) {
		this.tipoEmpleo = tipoEmpleo;
	}

	public String getViajar() {
		return viajar;
	}

	public void setViajar(String viajar) {
		this.viajar = viajar;
	}

	public String getRadicar() {
		return radicar;
	}

	public void setRadicar(String radicar) {
		this.radicar = radicar;
	}

	public String getSubAreaLaboralExpectativa() {
		return subAreaLaboralExpectativa;
	}

	public void setSubAreaLaboralExpectativa(String subAreaLaboralExpectativa) {
		this.subAreaLaboralExpectativa = subAreaLaboralExpectativa;
	}
}
