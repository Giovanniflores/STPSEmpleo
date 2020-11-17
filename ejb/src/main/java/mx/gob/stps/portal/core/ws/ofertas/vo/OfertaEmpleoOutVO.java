package mx.gob.stps.portal.core.ws.ofertas.vo;

import java.io.Serializable;

public class OfertaEmpleoOutVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fecha;
	private String puesto;
	private String estado;
	private String empresa;
	private String url;
	private long idOferta;
	private String descripcion;
	private String ciudad;
	private String[] imagen;
	private String idDiscapacidad;
	private String discapacidad;
	private String idEmpresa;
	
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setIdOferta(long idOferta) {
		this.idOferta = idOferta;
	}
	public long getIdOferta() {
		return idOferta;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String[] getImagen() {
		return imagen;
	}
	public void setImagen(String[] imagen) {
		this.imagen = imagen;
	}
	public String getIdDiscapacidad() {
		return idDiscapacidad;
	}
	public void setIdDiscapacidad(String idDiscapacidad) {
		this.idDiscapacidad = idDiscapacidad;
	}
	public String getDiscapacidad() {
		return discapacidad;
	}
	public void setDiscapacidad(String discapacidad) {
		this.discapacidad = discapacidad;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
}
