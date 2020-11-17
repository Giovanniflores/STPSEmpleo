package mx.gob.stps.portal.movil.app.empresa.model.rest;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.persistencia.vo.CandidatoVO;

public class CandidatoRestDTO extends BaseRestDTO {

	private CandidatoVO candidato;

	private String cookie;
	private String nombre;
	private String idOfertaCandidato;
	
	
	public CandidatoVO getCandidato() {
		return candidato;
	}
	
	public void setCandidato(CandidatoVO candidato) {
		this.candidato = candidato;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdOfertaCandidato() {
		return idOfertaCandidato;
	}

	public void setIdOfertaCandidato(String idOfertaCandidato) {
		this.idOfertaCandidato = idOfertaCandidato;
	}

}
