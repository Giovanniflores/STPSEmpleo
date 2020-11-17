package mx.gob.stps.portal.web.conalep.form;

import org.apache.struts.action.ActionForm;

import mx.gob.stps.portal.persistencia.vo.ConalepCandidatoVO;

public class ConalepCandidatoForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private long idConalepCandidato;
	private long idCandidato;
	private long idAreaNegocio;
	private long idSubAreaNegocio;
	private long idEntidad;
	private long idPlantel;
	private long idPlanEstudios;
	private long generacion;
	private String matricula;

	private boolean direccionGeoReferenciada;

	public ConalepCandidatoForm() {
	}

	public static void copyProperties(ConalepCandidatoVO vo, ConalepCandidatoForm form) {
		form.setIdConalepCandidato(vo.getIdConalepCandidato());
		form.setIdCandidato(vo.getIdCandidato());
		form.setIdAreaNegocio(vo.getIdAreaNegocio());
		form.setIdSubAreaNegocio(vo.getIdSubAreaNegocio());
		form.setIdPlantel(vo.getIdPlantel());
		form.setIdPlanEstudios(vo.getIdPlanEstudios());
		form.setGeneracion(vo.getGeneracion());
		form.setMatricula(vo.getMatricula());
	}

	public void reset() {

		idConalepCandidato = 0L;
		idCandidato = 0L;
		idAreaNegocio = 0L;
		idSubAreaNegocio = 0L;
		idEntidad = 0L;
		idPlantel = 0L;
		idPlanEstudios = 0L;
		generacion = 0L;
		matricula = null;

		direccionGeoReferenciada = false;
	}

	public long getIdConalepCandidato() {
		return idConalepCandidato;
	}

	public void setIdConalepCandidato(long idConalepCandidato) {
		this.idConalepCandidato = idConalepCandidato;
	}

	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public long getIdAreaNegocio() {
		return idAreaNegocio;
	}

	public void setIdAreaNegocio(long idAreaNegocio) {
		this.idAreaNegocio = idAreaNegocio;
	}

	public long getIdSubAreaNegocio() {
		return idSubAreaNegocio;
	}

	public void setIdSubAreaNegocio(long idSubAreaNegocio) {
		this.idSubAreaNegocio = idSubAreaNegocio;
	}

	public long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public long getIdPlantel() {
		return idPlantel;
	}

	public void setIdPlantel(long idPlantel) {
		this.idPlantel = idPlantel;
	}

	public long getIdPlanEstudios() {
		return idPlanEstudios;
	}

	public void setIdPlanEstudios(long idPlanEstudios) {
		this.idPlanEstudios = idPlanEstudios;
	}

	public long getGeneracion() {
		return generacion;
	}

	public void setGeneracion(long generacion) {
		this.generacion = generacion;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public boolean isDireccionGeoReferenciada() {
		return direccionGeoReferenciada;
	}

	public void setDireccionGeoReferenciada(boolean direccionGeoReferenciada) {
		this.direccionGeoReferenciada = direccionGeoReferenciada;
	}
}
