package mx.gob.stps.portal.core.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultSearchVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// Mensaje que envia el indexador, una vez que se realiza la busqueda
	private String mensaje;
	// Codigo de error que envia el indexador
	private int error;

	// Cadena original enviada al indexador
	private String cadenaOriginal;
	// Sinonimos y abreviaturas encontrados
	private List<String> sinonimosAbreviaturas;
	// Carreras encontradas
	private List<String> carreras;
	// Ocupaciones encontradas
	private List<String> ocupaciones;
	// Terminos encontrados
	private List<String> terminos;

	// Indica si es una busqueda de candidatos
	private boolean busquedaCandidatos;
	// Indica si es una busqueda de Ofertas
	private boolean busquedaOfertas;

	// Constructor
	public ResultSearchVO() {
		busquedaOfertas = false;
		busquedaCandidatos = false;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public List<String> getSinonimosAbreviaturas() {
		if(sinonimosAbreviaturas == null)
			sinonimosAbreviaturas = new ArrayList<String>();
		return sinonimosAbreviaturas;
	}

	public void setSinonimosAbreviaturas(List<String> sinonimosAbreviaturas) {
		this.sinonimosAbreviaturas = sinonimosAbreviaturas;
	}

	public List<String> getCarreras() {
		if(carreras == null)
			carreras = new ArrayList<String>();
		return carreras;
	}

	public void setCarreras(List<String> carreras) {
		this.carreras = carreras;
	}

	public List<String> getOcupaciones() {
		if(ocupaciones == null)
			ocupaciones = new ArrayList<String>();
		return ocupaciones;
	}

	public void setOcupaciones(List<String> ocupaciones) {
		this.ocupaciones = ocupaciones;
	}

	public List<String> getTerminos() {
		return terminos;
	}

	public void setTerminos(List<String> terminos) {
		this.terminos = terminos;
	}

	public String getCadenaOriginal() {
		if(cadenaOriginal == null)
			cadenaOriginal = "";
		return cadenaOriginal;
	}

	public void setCadenaOriginal(String cadenaOriginal) {
		this.cadenaOriginal = cadenaOriginal;
	}

	public boolean isBusquedaCandidatos() {
		return busquedaCandidatos;
	}

	public void setBusquedaCandidatos(boolean busquedaCandidatos) {
		this.busquedaCandidatos = busquedaCandidatos;
	}

	public boolean isBusquedaOfertas() {
		return busquedaOfertas;
	}

	public void setBusquedaOfertas(boolean busquedaOfertas) {
		this.busquedaOfertas = busquedaOfertas;
	}
	
}
