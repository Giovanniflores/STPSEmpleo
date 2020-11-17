package mx.gob.stps.portal.movil.app.model.rest;

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.movil.app.model.base.PostulatedDTO;

public class PostulateRestDTO extends BaseRestDTO {
	String nombreContacto;
	String correoElectronico;
	String domicilio;
	String idPostulated;
	int estatus;

	public PostulateRestDTO(PostulatedDTO postulatedDTO) {
		this.estatus = postulatedDTO.getEstatus();
		this.idPostulated = postulatedDTO.getIdPostulated();
	}

	public PostulateRestDTO() {
		
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getIdPostulated() {
		return idPostulated;
	}

	public void setIdPostulated(String idPostulated) {
		this.idPostulated = idPostulated;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	
	

}
