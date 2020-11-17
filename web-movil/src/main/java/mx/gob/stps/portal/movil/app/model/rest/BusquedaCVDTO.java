package mx.gob.stps.portal.movil.app.model.rest;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;

import mx.gob.stps.portal.core.candidate.vo.InformacionGeneralVO;
import mx.gob.stps.portal.movil.web.oferta.form.PerfilJB;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;

public class BusquedaCVDTO extends BaseRestDTO{
	
	
	
	public BusquedaCVDTO(){
		perfillaboral = new PerfilJB();
	}
	
	private PerfilJB perfillaboral;

	private ConocimientoComputacionVO conocimientoComputacion;
	
	private InformacionGeneralVO info;
	
	private String salario;
	
	public PerfilJB getPerfillaboral() {
		return perfillaboral;
	}

	public void setPerfillaboral(PerfilJB perfillaboral) {
		this.perfillaboral = perfillaboral;
	}

	public ConocimientoComputacionVO getConocimientoComputacion() {
		return conocimientoComputacion;
	}

	public void setConocimientoComputacion(
			ConocimientoComputacionVO conocimientoComputacion) {
		this.conocimientoComputacion = conocimientoComputacion;
	}

	public InformacionGeneralVO getInfo() {
		return info;
	}

	public void setInfo(InformacionGeneralVO info) {
		this.info = info;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	
	
	
}
