package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

public class BusquedaCandidatosVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idOcupacionDeseadaUno;
	
	private Integer idOcupacionDeseadaDos;
	
	private Integer idOcupacionDeseadaTres;
	
	private Integer idEntidadSelect;
	
	private Integer municipioUno;
	
	private Integer municipioDos;
	
	private Integer municipioTres;
	
	private Integer genero;
	
	private String edadMinima;
	
	private String edadMaxima;
	
	private String edadDe;
	
	private String edadA;
	
	private Integer idGradoEstudioSelect;
	
	private Integer carreraUno;
	
	private Integer carreraDos;
	
	private Integer carreraTres;
	
	private Integer idiomaUno;
	
	private Integer idiomaDos;
	
	private Integer idiomaTres;
	
	private Integer disponibilidadViajar;
	
	private Integer cambiarResidencia;
	
	private Integer habilidadUno;
	
	private Integer habilidadDos;
	
	private Integer habilidadTres;
	
	private Integer numeroColumna;
	
	private String tipoOrdenamiento;
	
	private String[] gradosDependientes;
	
	private String[] idiomasDependientes;
	
	private Integer idSubprograma;
	
	private Integer idAreaLaboral;
	
	private Integer idSubAreaLaboral;
	
	public BusquedaCandidatosVO(){}

	public Integer getIdOcupacionDeseadaUno() {
		return idOcupacionDeseadaUno;
	}

	public void setIdOcupacionDeseadaUno(Integer idOcupacionDeseadaUno) {
		this.idOcupacionDeseadaUno = idOcupacionDeseadaUno;
	}

	public Integer getIdOcupacionDeseadaDos() {
		return idOcupacionDeseadaDos;
	}

	public void setIdOcupacionDeseadaDos(Integer idOcupacionDeseadaDos) {
		this.idOcupacionDeseadaDos = idOcupacionDeseadaDos;
	}

	public Integer getIdOcupacionDeseadaTres() {
		return idOcupacionDeseadaTres;
	}

	public void setIdOcupacionDeseadaTres(Integer idOcupacionDeseadaTres) {
		this.idOcupacionDeseadaTres = idOcupacionDeseadaTres;
	}

	public Integer getIdEntidadSelect() {
		return idEntidadSelect;
	}

	public void setIdEntidadSelect(Integer idEntidadSelect) {
		this.idEntidadSelect = idEntidadSelect;
	}

	public Integer getMunicipioUno() {
		return municipioUno;
	}

	public void setMunicipioUno(Integer municipioUno) {
		this.municipioUno = municipioUno;
	}

	public Integer getMunicipioDos() {
		return municipioDos;
	}

	public void setMunicipioDos(Integer municipioDos) {
		this.municipioDos = municipioDos;
	}

	public Integer getMunicipioTres() {
		return municipioTres;
	}

	public void setMunicipioTres(Integer municipioTres) {
		this.municipioTres = municipioTres;
	}

	public Integer getGenero() {
		return genero;
	}

	public void setGenero(Integer genero) {
		this.genero = genero;
	}

	public String getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(String edadMinima) {
		this.edadMinima = edadMinima;
	}

	public String getEdadMaxima() {
		return edadMaxima;
	}

	public void setEdadMaxima(String edadMaxima) {
		this.edadMaxima = edadMaxima;
	}

	public String getEdadDe() {
		return edadDe;
	}

	public void setEdadDe(String edadDe) {
		this.edadDe = edadDe;
	}

	public String getEdadA() {
		return edadA;
	}

	public void setEdadA(String edadA) {
		this.edadA = edadA;
	}

	public Integer getIdGradoEstudioSelect() {
		return idGradoEstudioSelect;
	}

	public void setIdGradoEstudioSelect(Integer idGradoEstudioSelect) {
		this.idGradoEstudioSelect = idGradoEstudioSelect;
	}

	public Integer getCarreraUno() {
		return carreraUno;
	}

	public void setCarreraUno(Integer carreraUno) {
		this.carreraUno = carreraUno;
	}

	public Integer getCarreraDos() {
		return carreraDos;
	}

	public void setCarreraDos(Integer carreraDos) {
		this.carreraDos = carreraDos;
	}

	public Integer getCarreraTres() {
		return carreraTres;
	}

	public void setCarreraTres(Integer carreraTres) {
		this.carreraTres = carreraTres;
	}

	public Integer getIdiomaUno() {
		return idiomaUno;
	}

	public void setIdiomaUno(Integer idiomaUno) {
		this.idiomaUno = idiomaUno;
	}

	public Integer getIdiomaDos() {
		return idiomaDos;
	}

	public void setIdiomaDos(Integer idiomaDos) {
		this.idiomaDos = idiomaDos;
	}

	public Integer getIdiomaTres() {
		return idiomaTres;
	}

	public void setIdiomaTres(Integer idiomaTres) {
		this.idiomaTres = idiomaTres;
	}

	public Integer getDisponibilidadViajar() {
		return disponibilidadViajar;
	}

	public void setDisponibilidadViajar(Integer disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}

	public Integer getCambiarResidencia() {
		return cambiarResidencia;
	}

	public void setCambiarResidencia(Integer cambiarResidencia) {
		this.cambiarResidencia = cambiarResidencia;
	}

	public Integer getHabilidadUno() {
		return habilidadUno;
	}

	public void setHabilidadUno(Integer habilidadUno) {
		this.habilidadUno = habilidadUno;
	}

	public Integer getHabilidadDos() {
		return habilidadDos;
	}

	public void setHabilidadDos(Integer habilidadDos) {
		this.habilidadDos = habilidadDos;
	}

	public Integer getHabilidadTres() {
		return habilidadTres;
	}

	public void setHabilidadTres(Integer habilidadTres) {
		this.habilidadTres = habilidadTres;
	}

	public String[] getGradosDependientes() {
		return gradosDependientes;
	}

	public void setGradosDependientes(String[] gradosDependientes) {
		this.gradosDependientes = gradosDependientes;
	}

	public String[] getIdiomasDependientes() {
		return idiomasDependientes;
	}

	public void setIdiomasDependientes(String[] idiomasDependientes) {
		this.idiomasDependientes = idiomasDependientes;
	}

	public Integer getNumeroColumna() {
		return numeroColumna;
	}

	public void setNumeroColumna(Integer numeroColumna) {
		this.numeroColumna = numeroColumna;
	}

	public String getTipoOrdenamiento() {
		return tipoOrdenamiento;
	}

	public void setTipoOrdenamiento(String tipoOrdenamiento) {
		this.tipoOrdenamiento = tipoOrdenamiento;
	}

	public Integer getIdSubprograma() {
		return idSubprograma;
	}

	public void setIdSubprograma(Integer idSubprograma) {
		this.idSubprograma = idSubprograma;
	}

	public Integer getIdAreaLaboral() {
		return idAreaLaboral;
	}

	public void setIdAreaLaboral(Integer idAreaLaboral) {
		this.idAreaLaboral = idAreaLaboral;
	}

	public Integer getIdSubAreaLaboral() {
		return idSubAreaLaboral;
	}

	public void setIdSubAreaLaboral(Integer idSubAreaLaboral) {
		this.idSubAreaLaboral = idSubAreaLaboral;
	}
}
