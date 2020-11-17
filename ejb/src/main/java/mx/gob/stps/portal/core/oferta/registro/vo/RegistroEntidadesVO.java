package mx.gob.stps.portal.core.oferta.registro.vo;

import java.io.Serializable;

public class RegistroEntidadesVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7320445764566629943L;
	private long idRegistro;
	private long entidad;
	private long municipio;
	private String entidadDescripcion;
	private String municipioDescripcion;
	
	
	
	public long getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(long idRegistro) {
		this.idRegistro = idRegistro;
	}
	public String getEntidadDescripcion() {
		return entidadDescripcion;
	}
	public void setEntidadDescripcion(String entidadDescripcion) {
		this.entidadDescripcion = entidadDescripcion;
	}
	public String getMunicipioDescripcion() {
		return municipioDescripcion;
	}
	public void setMunicipioDescripcion(String municipioDescripcion) {
		this.municipioDescripcion = municipioDescripcion;
	}
	public long getEntidad() {
		return entidad;
	}
	public void setEntidad(long entidad) {
		this.entidad = entidad;
	}
	public long getMunicipio() {
		return municipio;
	}
	public void setMunicipio(long municipio) {
		this.municipio = municipio;
	}
	

}
