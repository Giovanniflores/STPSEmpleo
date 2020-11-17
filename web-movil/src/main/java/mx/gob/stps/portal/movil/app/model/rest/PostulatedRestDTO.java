package mx.gob.stps.portal.movil.app.model.rest;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.movil.app.model.base.PostulatedDTO;

public class PostulatedRestDTO extends BaseRestDTO{

	List<PostulatedDTO> ofertas;
	
	public  PostulatedRestDTO (){
		ofertas = new ArrayList<PostulatedDTO>();
	}

	public List<PostulatedDTO> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<PostulatedDTO> ofertas) {
		this.ofertas = ofertas;
	}
	
	
	
}
