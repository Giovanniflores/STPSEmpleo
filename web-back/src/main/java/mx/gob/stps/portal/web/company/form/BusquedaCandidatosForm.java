package mx.gob.stps.portal.web.company.form;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.core.infra.utils.Constantes;

import org.apache.struts.action.ActionForm;

public class BusquedaCandidatosForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private static final Integer numeroCampos = 24;

	private Integer idOcupacionDeseadaUno;
	
	private Integer idOcupacionDeseadaDos;
	
	private Integer idOcupacionDeseadaTres;
	
	private String descOcupacionDeseadaUnoForm;
	
	private String descOcupacionDeseadaDosForm;
	
	private String descOcupacionDeseadaTresForm;
	
	private Integer idEntidadSelect;
	
	private String entidadDescripcionForm;
	
	private Integer municipioUno;
	
	private Integer municipioDos;
	
	private Integer municipioTres;
	
	private String descMunicipioUnoForm;
	
	private String descMunicipioDosForm;
	
	private String descMunicipioTresForm;
	
	private Integer genero;
	
	private String edadMinima;
	
	private String edadMaxima;
	
	private String filtroEdad;
	
	private String edadDe;
	
	private String edadA;
	
	private String filtroSueldo;
	
	private Integer idGradoEstudioSelect;
	
	private String descEscolaridadForm;
	
	private Integer carreraUno;
	
	private Integer carreraDos;
	
	private Integer carreraTres;
	
	private String descCarreraUnoForm;
	
	private String descCarreraDosForm;
	
	private String descCarreraTresForm;
	
	private Integer idiomaUno;
	
	private Integer idiomaDos;
	
	private Integer idiomaTres;
	
	private String descIdiomaUnoForm;
	
	private String descIdiomaDosForm;
	
	private String descIdiomaTresForm;
	
	private Integer disponibilidadViajar;
	
	private Integer cambiarResidencia;
	
	private Integer habilidadUno;
	
	private Integer habilidadDos;
	
	private Integer habilidadTres;
	
	private String descHabilidadUnoForm;
	
	private String descHabilidadDosForm;
	
	private String descHabilidadTresForm;
	
	private Integer numeroColumna;
	
	private String tipoOrdenamiento;
	
	private String[] gradosDependientes;
	
	private String[] idiomasDependientes;
	
	private List<Integer> numeroBloques;

	private Integer idSubprograma;
	
	private Integer idAreaLaboral;
	
	private Integer idSubAreaLaboral;
	
	private String areaLaboral;
	
	private String subAreaLaboral;

	private List<String> filtrosBusqueda;
	
	public void reset() {
	
		this.descOcupacionDeseadaUnoForm = null;
		
		this.descOcupacionDeseadaDosForm = null;
		
		this.descOcupacionDeseadaTresForm = null;
		
		this.idOcupacionDeseadaUno = null;
		
		this.idOcupacionDeseadaDos = null;
		
		this.idOcupacionDeseadaTres = null;
		
		this.idEntidadSelect = null;
		
		this.entidadDescripcionForm = null;
		
		this.municipioUno = null;
		
		this.municipioDos = null;
		
		this.municipioTres = null;
		
		this.descMunicipioUnoForm = null;
		
		this.descMunicipioDosForm = null;
		
		this.descMunicipioTresForm = null;
		
		this.genero = null;
		
		this.edadMinima = null;
		
		this.edadMaxima = null;
		
		this.edadDe = null;
		
		this.edadA = null;
		
		this.idGradoEstudioSelect = null;
		
		this.descEscolaridadForm = null;
		
		this.carreraUno = null;
		
		this.carreraDos = null;
		
		this.carreraTres = null;
		
		this.descCarreraUnoForm = null;
		
		this.descCarreraDosForm = null;
		
		this.descCarreraTresForm = null;
		
		this.idiomaUno = null;
		
		this.idiomaDos = null;
		
		this.idiomaTres = null;
		
		this.descIdiomaUnoForm = null;
		
		this.descIdiomaDosForm = null;
		
		this.descIdiomaTresForm = null;
		
		this.disponibilidadViajar = null;
		
		this.cambiarResidencia = null;
		
		this.habilidadUno = null;
		
		this.habilidadDos = null;
		
		this.habilidadTres = null;
		
		this.descHabilidadUnoForm = null;
		
		this.descHabilidadDosForm = null;
		
		this.descHabilidadTresForm = null;
		
		this.numeroColumna = null;
		
		this.tipoOrdenamiento = null;
		
		this.filtrosBusqueda = null;
		
		this.numeroBloques = null;
		
		this.filtroEdad = null;
		
		this.filtroSueldo = null;
		
		this.idSubprograma = null;
	}
	
	public boolean validate() {
		if (null == this.idAreaLaboral || this.idAreaLaboral == 0)
			return false;
		if (null == this.idSubAreaLaboral || this.idSubAreaLaboral == 0)
			return false;
		return true;		
	}

	public boolean validarForma(){
		
		int count = 0;
		
		int empleoCount = 0;
		
		int entidadCount = 0;
		
		boolean valida = true;
		
		if(idOcupacionDeseadaUno == 0 && idOcupacionDeseadaDos == 0 && idOcupacionDeseadaTres == 0)
			count++;
		else if(idOcupacionDeseadaUno != 0 || idOcupacionDeseadaDos != 0 || idOcupacionDeseadaTres != 0)
			empleoCount++;
		
		if(idEntidadSelect == 0)
			count++;
		else if(idEntidadSelect != 0)
			entidadCount++;
		
		if(count == 2){
			valida = false;
		}else if(empleoCount != 0 && entidadCount == 0){
			valida = false;
		}else if(empleoCount == 0 && entidadCount != 0){
			valida = false;
		}
		
		return valida;		
	}
	
	public void obtenerFiltrosBusqueda() {
		
		filtrosBusqueda = new ArrayList<String>();
		if(descOcupacionDeseadaUnoForm != null && !descOcupacionDeseadaUnoForm.trim().isEmpty())
			filtrosBusqueda.add(descOcupacionDeseadaUnoForm);
		if(descOcupacionDeseadaDosForm != null && !descOcupacionDeseadaDosForm.trim().isEmpty())
			filtrosBusqueda.add(descOcupacionDeseadaDosForm);
		if(descOcupacionDeseadaTresForm != null && !descOcupacionDeseadaTresForm.trim().isEmpty())
			filtrosBusqueda.add(descOcupacionDeseadaTresForm);
		if(entidadDescripcionForm != null && !entidadDescripcionForm.trim().isEmpty())
			filtrosBusqueda.add(entidadDescripcionForm);
		if(descMunicipioUnoForm != null &&!descMunicipioUnoForm.trim().isEmpty())
			filtrosBusqueda.add(descMunicipioUnoForm);
		if(descMunicipioDosForm != null &&!descMunicipioDosForm.trim().isEmpty())
			filtrosBusqueda.add(descMunicipioDosForm);
		if(descMunicipioTresForm != null &&!descMunicipioTresForm.trim().isEmpty())
			filtrosBusqueda.add(descMunicipioTresForm);
		if(edadMinima != null && edadMaxima != null &&!edadMinima.trim().isEmpty() && !edadMaxima.trim().isEmpty()){
			filtroEdad = "Edad(" + edadMinima + " - " + edadMaxima + ")";
			filtrosBusqueda.add(filtroEdad);
		}
		if(edadDe != null  && edadA != null && !edadDe.trim().isEmpty() && !edadA.trim().isEmpty()){
			filtroSueldo = "Sueldo($" + edadDe + " - $" + edadA +")";
			filtrosBusqueda.add(filtroSueldo);
		}
		if(descEscolaridadForm != null && !descEscolaridadForm.trim().isEmpty())
			filtrosBusqueda.add(descEscolaridadForm);
		if(descCarreraUnoForm != null && !descCarreraUnoForm.trim().isEmpty())
			filtrosBusqueda.add(descCarreraUnoForm);
		if(descCarreraDosForm != null &&!descCarreraDosForm.trim().isEmpty())
			filtrosBusqueda.add(descCarreraDosForm);
		if(descCarreraTresForm != null &&!descCarreraTresForm.trim().isEmpty())
			filtrosBusqueda.add(descCarreraTresForm);
		if(descIdiomaUnoForm != null &&!descIdiomaUnoForm.trim().isEmpty())
			filtrosBusqueda.add(descIdiomaUnoForm);
		if(descIdiomaDosForm != null &&!descIdiomaDosForm.trim().isEmpty())
			filtrosBusqueda.add(descIdiomaDosForm);
		if(descIdiomaTresForm != null &&!descIdiomaTresForm.trim().isEmpty())
			filtrosBusqueda.add(descIdiomaTresForm);
		if(descHabilidadUnoForm != null &&!descHabilidadUnoForm.trim().isEmpty())
			filtrosBusqueda.add(descHabilidadUnoForm);
		if(descHabilidadDosForm != null &&!descHabilidadDosForm.trim().isEmpty())
			filtrosBusqueda.add(descHabilidadDosForm);
		if(descHabilidadTresForm != null &&!descHabilidadTresForm.trim().isEmpty())
			filtrosBusqueda.add(descHabilidadTresForm);
		if(genero != null){
			if(genero == 1)
				filtrosBusqueda.add("Masculino");
			else if(genero == 2)
				filtrosBusqueda.add("Femenino");
		}
		
		if(disponibilidadViajar != null){
			if(disponibilidadViajar == 1)
				filtrosBusqueda.add("No Disponible para viajar");
			else if(disponibilidadViajar == 2)
				filtrosBusqueda.add("Disponible para viajar");
				
		}
		if (null != cambiarResidencia) {
			if (cambiarResidencia == 1)
				filtrosBusqueda.add("No Disponible a cambiar de residencia");
			else if (cambiarResidencia == 2)
				filtrosBusqueda.add("Disponible a cambiar de residencia");
		}
		if (null != idSubprograma && idSubprograma > 0)
			filtrosBusqueda.add(Constantes.SUBPROGRAMA.findByID(idSubprograma).getOpcion());
		if (null != idAreaLaboral && idAreaLaboral > 0)
			filtrosBusqueda.add(areaLaboral);
		if (null != idSubAreaLaboral && idSubAreaLaboral > 0)
			filtrosBusqueda.add(subAreaLaboral);
	}
	
	//Getters && Setters
	public String[] getGradosDependientes() {
		return gradosDependientes;
	}

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

	public List<String> getFiltrosBusqueda() {
		return filtrosBusqueda;
	}

	public void setFiltrosBusqueda(List<String> filtrosBusqueda) {
		this.filtrosBusqueda = filtrosBusqueda;
	}

	public String getDescOcupacionDeseadaUnoForm() {
		return descOcupacionDeseadaUnoForm;
	}

	public void setDescOcupacionDeseadaUnoForm(String descOcupacionDeseadaUnoForm) {
		this.descOcupacionDeseadaUnoForm = descOcupacionDeseadaUnoForm;
	}

	public String getDescOcupacionDeseadaDosForm() {
		return descOcupacionDeseadaDosForm;
	}

	public void setDescOcupacionDeseadaDosForm(String descOcupacionDeseadaDosForm) {
		this.descOcupacionDeseadaDosForm = descOcupacionDeseadaDosForm;
	}

	public String getDescOcupacionDeseadaTresForm() {
		return descOcupacionDeseadaTresForm;
	}

	public void setDescOcupacionDeseadaTresForm(String descOcupacionDeseadaTresForm) {
		this.descOcupacionDeseadaTresForm = descOcupacionDeseadaTresForm;
	}

	public String getEntidadDescripcionForm() {
		return entidadDescripcionForm;
	}

	public void setEntidadDescripcionForm(String entidadDescripcionForm) {
		this.entidadDescripcionForm = entidadDescripcionForm;
	}

	public String getDescMunicipioUnoForm() {
		return descMunicipioUnoForm;
	}

	public void setDescMunicipioUnoForm(String descMunicipioUnoForm) {
		this.descMunicipioUnoForm = descMunicipioUnoForm;
	}

	public String getDescMunicipioDosForm() {
		return descMunicipioDosForm;
	}

	public void setDescMunicipioDosForm(String descMunicipioDosForm) {
		this.descMunicipioDosForm = descMunicipioDosForm;
	}

	public String getDescMunicipioTresForm() {
		return descMunicipioTresForm;
	}

	public void setDescMunicipioTresForm(String descMunicipioTresForm) {
		this.descMunicipioTresForm = descMunicipioTresForm;
	}

	public String getDescEscolaridadForm() {
		return descEscolaridadForm;
	}

	public void setDescEscolaridadForm(String descEscolaridadForm) {
		this.descEscolaridadForm = descEscolaridadForm;
	}

	public String getDescCarreraUnoForm() {
		return descCarreraUnoForm;
	}

	public void setDescCarreraUnoForm(String descCarreraUnoForm) {
		this.descCarreraUnoForm = descCarreraUnoForm;
	}

	public String getDescCarreraDosForm() {
		return descCarreraDosForm;
	}

	public void setDescCarreraDosForm(String descCarreraDosForm) {
		this.descCarreraDosForm = descCarreraDosForm;
	}

	public String getDescCarreraTresForm() {
		return descCarreraTresForm;
	}

	public void setDescCarreraTresForm(String descCarreraTresForm) {
		this.descCarreraTresForm = descCarreraTresForm;
	}

	public String getDescIdiomaUnoForm() {
		return descIdiomaUnoForm;
	}

	public void setDescIdiomaUnoForm(String descIdiomaUnoForm) {
		this.descIdiomaUnoForm = descIdiomaUnoForm;
	}

	public String getDescIdiomaDosForm() {
		return descIdiomaDosForm;
	}

	public void setDescIdiomaDosForm(String descIdiomaDosForm) {
		this.descIdiomaDosForm = descIdiomaDosForm;
	}

	public String getDescIdiomaTresForm() {
		return descIdiomaTresForm;
	}

	public void setDescIdiomaTresForm(String descIdiomaTresForm) {
		this.descIdiomaTresForm = descIdiomaTresForm;
	}

	public String getDescHabilidadUnoForm() {
		return descHabilidadUnoForm;
	}

	public void setDescHabilidadUnoForm(String descHabilidadUnoForm) {
		this.descHabilidadUnoForm = descHabilidadUnoForm;
	}

	public String getDescHabilidadDosForm() {
		return descHabilidadDosForm;
	}

	public void setDescHabilidadDosForm(String descHabilidadDosForm) {
		this.descHabilidadDosForm = descHabilidadDosForm;
	}

	public String getDescHabilidadTresForm() {
		return descHabilidadTresForm;
	}

	public void setDescHabilidadTresForm(String descHabilidadTresForm) {
		this.descHabilidadTresForm = descHabilidadTresForm;
	}

	public List<Integer> getNumeroBloques() {
		return numeroBloques;
	}

	public void setNumeroBloques(List<Integer> numeroBloques) {
		this.numeroBloques = numeroBloques;
	}

	public String getFiltroEdad() {
		return filtroEdad;
	}

	public void setFiltroEdad(String filtroEdad) {
		this.filtroEdad = filtroEdad;
	}

	public String getFiltroSueldo() {
		return filtroSueldo;
	}

	public void setFiltroSueldo(String filtroSueldo) {
		this.filtroSueldo = filtroSueldo;
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

	@Override
	public String toString() {
		return "BusquedaCandidatosForm [idAreaLaboral=" + idAreaLaboral
				+ ", idSubAreaLaboral=" + idSubAreaLaboral + "]";
	}

	public String getAreaLaboral() {
		return areaLaboral;
	}

	public void setAreaLaboral(String areaLaboral) {
		this.areaLaboral = areaLaboral;
	}

	public String getSubAreaLaboral() {
		return subAreaLaboral;
	}

	public void setSubAreaLaboral(String subAreaLaboral) {
		this.subAreaLaboral = subAreaLaboral;
	}
}
