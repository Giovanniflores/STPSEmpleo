package mx.gob.stps.portal.core.oferta.registro.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class RegistroRequisitosVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8345908322285592063L;
	
	private long idOferta;
	private  ArrayList<RequisitoVO> listaConocimientos;
	private  ArrayList<RequisitoVO> listaHabilidades;
	private  ArrayList<RequisitoVO> listaCompetencias;
	private  ArrayList<RegistroIdiomaVO> listaIdiomas;
	private  ArrayList<String> listaCarrerasSeleccionadas;
	private  ArrayList<String> listaSectoresSeleccionados;
	private  ArrayList<RegistroEntidadesVO> listaEntidadesSeleccionados;
	private String[] listaPrestaciones;
	
	private long estudios;
	private long carrera;
	private long situacionAcademica;
	private String conocimientosGenerales;
	private int aniosExperiencia;
	private String conocimiento1;
	private int aniosExperienciaConoc;
	private int dominioConoc;
	private String habilidad1;
	private int aniosExperienciaHabilidad;
	private int dominioHabilidad;
	private String competencia1;
	private int aniosExperienciaComp;
	private int dominioComp;
	private long idioma1;
	private long CertificacionIdioma;
	private long dominioIdioma;
	
	private int disponibilidadViajar;
	private int disponibilidadRadicar;
	private int edadRequerida;
	private int edadDe;
	private int edadA;
	private int genero;
	private String observaciones ;
	
	
	
	
	
	
	public String[] getListaPrestaciones() {
		return listaPrestaciones;
	}
	public void setListaPrestaciones(String[] listaPrestaciones) {
		this.listaPrestaciones = listaPrestaciones;
	}
	public long getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(long idOferta) {
		this.idOferta = idOferta;
	}
	public ArrayList<RequisitoVO> getListaConocimientos() {
		return listaConocimientos;
	}
	public void setListaConocimientos(ArrayList<RequisitoVO> listaConocimientos) {
		this.listaConocimientos = listaConocimientos;
	}
	public ArrayList<RequisitoVO> getListaHabilidades() {
		return listaHabilidades;
	}
	public void setListaHabilidades(ArrayList<RequisitoVO> listaHabilidades) {
		this.listaHabilidades = listaHabilidades;
	}
	public ArrayList<RequisitoVO> getListaCompetencias() {
		return listaCompetencias;
	}
	public void setListaCompetencias(ArrayList<RequisitoVO> listaCompetencias) {
		this.listaCompetencias = listaCompetencias;
	}
	public ArrayList<RegistroIdiomaVO> getListaIdiomas() {
		return listaIdiomas;
	}
	public void setListaIdiomas(ArrayList<RegistroIdiomaVO> listaIdiomas) {
		this.listaIdiomas = listaIdiomas;
	}
	public ArrayList<String> getListaCarrerasSeleccionadas() {
		return listaCarrerasSeleccionadas;
	}
	public void setListaCarrerasSeleccionadas(
			ArrayList<String> listaCarrerasSeleccionadas) {
		this.listaCarrerasSeleccionadas = listaCarrerasSeleccionadas;
	}
	public ArrayList<String> getListaSectoresSeleccionados() {
		return listaSectoresSeleccionados;
	}
	public void setListaSectoresSeleccionados(
			ArrayList<String> listaSectoresSeleccionados) {
		this.listaSectoresSeleccionados = listaSectoresSeleccionados;
	}
	public ArrayList<RegistroEntidadesVO> getListaEntidadesSeleccionados() {
		return listaEntidadesSeleccionados;
	}
	public void setListaEntidadesSeleccionados(
			ArrayList<RegistroEntidadesVO> listaEntidadesSeleccionados) {
		this.listaEntidadesSeleccionados = listaEntidadesSeleccionados;
	}
	public long getEstudios() {
		return estudios;
	}
	public void setEstudios(long estudios) {
		this.estudios = estudios;
	}
	public long getCarrera() {
		return carrera;
	}
	public void setCarrera(long carrera) {
		this.carrera = carrera;
	}
	public long getSituacionAcademica() {
		return situacionAcademica;
	}
	public void setSituacionAcademica(long situacionAcademica) {
		this.situacionAcademica = situacionAcademica;
	}
	public String getConocimientosGenerales() {
		return conocimientosGenerales;
	}
	public void setConocimientosGenerales(String conocimientosGenerales) {
		this.conocimientosGenerales = conocimientosGenerales;
	}
	public int getAniosExperiencia() {
		return aniosExperiencia;
	}
	public void setAniosExperiencia(int aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
	}
	public String getConocimiento1() {
		return conocimiento1;
	}
	public void setConocimiento1(String conocimiento1) {
		this.conocimiento1 = conocimiento1;
	}
	public int getAniosExperienciaConoc() {
		return aniosExperienciaConoc;
	}
	public void setAniosExperienciaConoc(int aniosExperienciaConoc) {
		this.aniosExperienciaConoc = aniosExperienciaConoc;
	}
	public int getDominioConoc() {
		return dominioConoc;
	}
	public void setDominioConoc(int dominioConoc) {
		this.dominioConoc = dominioConoc;
	}
	public String getHabilidad1() {
		return habilidad1;
	}
	public void setHabilidad1(String habilidad1) {
		this.habilidad1 = habilidad1;
	}
	public int getAniosExperienciaHabilidad() {
		return aniosExperienciaHabilidad;
	}
	public void setAniosExperienciaHabilidad(int aniosExperienciaHabilidad) {
		this.aniosExperienciaHabilidad = aniosExperienciaHabilidad;
	}
	public int getDominioHabilidad() {
		return dominioHabilidad;
	}
	public void setDominioHabilidad(int dominioHabilidad) {
		this.dominioHabilidad = dominioHabilidad;
	}
	public String getCompetencia1() {
		return competencia1;
	}
	public void setCompetencia1(String competencia1) {
		this.competencia1 = competencia1;
	}
	public int getAniosExperienciaComp() {
		return aniosExperienciaComp;
	}
	public void setAniosExperienciaComp(int aniosExperienciaComp) {
		this.aniosExperienciaComp = aniosExperienciaComp;
	}
	public int getDominioComp() {
		return dominioComp;
	}
	public void setDominioComp(int dominioComp) {
		this.dominioComp = dominioComp;
	}
	public long getIdioma1() {
		return idioma1;
	}
	public void setIdioma1(long idioma1) {
		this.idioma1 = idioma1;
	}
	public long getCertificacionIdioma() {
		return CertificacionIdioma;
	}
	public void setCertificacionIdioma(long certificacionIdioma) {
		CertificacionIdioma = certificacionIdioma;
	}
	public long getDominioIdioma() {
		return dominioIdioma;
	}
	public void setDominioIdioma(long dominioIdioma) {
		this.dominioIdioma = dominioIdioma;
	}
	public int getDisponibilidadViajar() {
		return disponibilidadViajar;
	}
	public void setDisponibilidadViajar(int disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}
	public int getDisponibilidadRadicar() {
		return disponibilidadRadicar;
	}
	public void setDisponibilidadRadicar(int disponibilidadRadicar) {
		this.disponibilidadRadicar = disponibilidadRadicar;
	}
	public int getEdadRequerida() {
		return edadRequerida;
	}
	public void setEdadRequerida(int edadRequerida) {
		this.edadRequerida = edadRequerida;
	}
	public int getEdadDe() {
		return edadDe;
	}
	public void setEdadDe(int edadDe) {
		this.edadDe = edadDe;
	}
	public int getEdadA() {
		return edadA;
	}
	public void setEdadA(int edadA) {
		this.edadA = edadA;
	}
	public int getGenero() {
		return genero;
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	


}
