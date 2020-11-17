package mx.gob.stps.portal.core.oferta.registro.vo;

import java.io.Serializable;
import java.util.Calendar;


public class EventoVO implements Serializable{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -7297985474585909408L;
	private long id;
	private long usuario;
	private String descripcion;
	private Calendar fecha;
	private String detalle;
	private Long idRegistro;
	private Integer idTipoPropietario;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUsuario() {
		return usuario;
	}
	public void setUsuario(long usuario) {
		this.usuario = usuario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Long getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}
	public Integer getIdTipoPropietario() {
		return idTipoPropietario;
	}
	public void setIdTipoPropietario(Integer idTipoPropietario) {
		this.idTipoPropietario = idTipoPropietario;
	}
}
