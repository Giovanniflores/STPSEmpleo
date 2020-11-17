package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

public class CandidatoComputacionVO implements Serializable {
	private static final long serialVersionUID = 2311055365741943470L;

	private long idCandidatoComputacion;
	private long idCandidato;
	private int procesadorTxt;
	private int hojaCalculo;
	private int internet;
	private int redesSociales;
	private String otros;

	public long getIdCandidatoComputacion() {
		return idCandidatoComputacion;
	}
	public void setIdCandidatoComputacion(long idCandidatoComputacion) {
		this.idCandidatoComputacion = idCandidatoComputacion;
	}
	public long getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
	public int getProcesadorTxt() {
		return procesadorTxt;
	}
	public void setProcesadorTxt(int procesadorTxt) {
		this.procesadorTxt = procesadorTxt;
	}
	public int getHojaCalculo() {
		return hojaCalculo;
	}
	public void setHojaCalculo(int hojaCalculo) {
		this.hojaCalculo = hojaCalculo;
	}
	public int getInternet() {
		return internet;
	}
	public void setInternet(int internet) {
		this.internet = internet;
	}
	public int getRedesSociales() {
		return redesSociales;
	}
	public void setRedesSociales(int redesSociales) {
		this.redesSociales = redesSociales;
	}
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
	}
}
