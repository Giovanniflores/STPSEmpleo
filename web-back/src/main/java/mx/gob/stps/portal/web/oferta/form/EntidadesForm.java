package mx.gob.stps.portal.web.oferta.form;

import org.apache.struts.action.ActionForm;

public class EntidadesForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5349730208883114796L;
	private String entidad;
	private String municipio;
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	
	
	
	
}
