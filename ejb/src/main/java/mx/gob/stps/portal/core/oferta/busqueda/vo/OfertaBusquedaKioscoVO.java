package mx.gob.stps.portal.core.oferta.busqueda.vo;

import java.io.Serializable;



public class OfertaBusquedaKioscoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4185337585918575868L;
	
	private long idEntidad;
	private long idOfertaEmpleo;
	private String fechaAlta;
	private String ubicacion;
	private String ocupacion;
	private String municipio;
	
	
	public long getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getMunicipio() {
		return municipio;
	}
	
	

}
