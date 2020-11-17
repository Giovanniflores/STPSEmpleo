package mx.gob.stps.portal.core.ws.vo;

import java.io.Serializable;
import java.util.List;

public class OfertasPortalEmpleo implements Serializable {

	private static final long serialVersionUID = 8380542302495608405L;

	private long totalOfertas;
	
	private double salarioMinimo;
	private double salarioMaximo;
	
	private double salarioPromedio;
	
	private String escolaridad;

	private String experiencia;
	
	private String urlOfertas;

	private List<Oferta> ofertas;

	public double getSalarioMinimo() {
		return salarioMinimo;
	}

	public void setSalarioMinimo(double salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}

	public double getSalarioMaximo() {
		return salarioMaximo;
	}

	public void setSalarioMaximo(double salarioMaximo) {
		this.salarioMaximo = salarioMaximo;
	}

	public long getTotalOfertas() {
		return totalOfertas;
	}

	public void setTotalOfertas(long totalOfertas) {
		this.totalOfertas = totalOfertas;
	}

	public double getSalarioPromedio() {
		return salarioPromedio;
	}

	public void setSalarioPromedio(double salarioPromedio) {
		this.salarioPromedio = salarioPromedio;
	}

	public String getUrlOfertas() {
		return urlOfertas;
	}

	public void setUrlOfertas(String urlOfertas) {
		this.urlOfertas = urlOfertas;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public String getEscolaridad() {
		return escolaridad;
	}

	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

}
