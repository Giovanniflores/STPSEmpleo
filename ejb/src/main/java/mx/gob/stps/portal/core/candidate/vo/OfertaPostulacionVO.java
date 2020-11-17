package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.gob.stps.portal.core.infra.utils.Utils;

public class OfertaPostulacionVO implements Serializable {

	private static final long serialVersionUID = 4359257537850307623L;
	
	protected int idOfertaCandidato;
	
	protected int idEmpresa;
	
	protected int idOfertaEmpleo;	
	
	protected String tituloOferta;
	
	protected int idCandidato;
	
	protected String nombreCompletoCandidato;
	
	protected Date fechaAlta;
	
	protected int antiguedadDias;
	
	protected int estatus;

	public int getIdOfertaCandidato() {
		return idOfertaCandidato;
	}

	public void setIdOfertaCandidato(int idOfertaCandidato) {
		this.idOfertaCandidato = idOfertaCandidato;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public int getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(int idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}

	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public int getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getNombreCompletoCandidato() {
		return nombreCompletoCandidato;
	}

	public void setNombreCompletoCandidato(String nombreCompletoCandidato) {
		this.nombreCompletoCandidato = nombreCompletoCandidato;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getAntiguedadDias() {
		return antiguedadDias;
	}

	public void setAntiguedadDias(int antiguedadDias) {
		this.antiguedadDias = antiguedadDias;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	
	public String getFechaAltaToString(){	
		return Utils.getFechaFormato(this.fechaAlta);
	}
}
