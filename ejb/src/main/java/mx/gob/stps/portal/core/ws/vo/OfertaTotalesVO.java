package mx.gob.stps.portal.core.ws.vo;

import java.util.List;

public class OfertaTotalesVO {
	private long totalOfertas;
	private double salarioMinimo;
	private double salarioMaximo;
	private double salarioPromedio;
	private List<Long> idsOferta;

	private int idNivelEstudio;
	
	private int experienciaAnios;
	
	public long getTotalOfertas() {
		return totalOfertas;
	}
	public void setTotalOfertas(long totalOfertas) {
		this.totalOfertas = totalOfertas;
	}
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
	public double getSalarioPromedio() {
		return salarioPromedio;
	}
	public void setSalarioPromedio(double salarioPromedio) {
		this.salarioPromedio = salarioPromedio;
	}
	public List<Long> getIdsOferta() {
		return idsOferta;
	}
	public void setIdsOferta(List<Long> idsOferta) {
		this.idsOferta = idsOferta;
	}
	public int getIdNivelEstudio() {
		return idNivelEstudio;
	}
	public void setIdNivelEstudio(int idNivelEstudio) {
		this.idNivelEstudio = idNivelEstudio;
	}
	public int getExperienciaAnios() {
		return experienciaAnios;
	}
	public void setExperienciaAnios(int experienciaAnios) {
		this.experienciaAnios = experienciaAnios;
	}
}
