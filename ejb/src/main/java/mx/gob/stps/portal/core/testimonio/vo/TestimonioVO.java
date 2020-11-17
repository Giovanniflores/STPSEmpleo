/**
 * 
 */
package mx.gob.stps.portal.core.testimonio.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author concepcion.aguilar
 *
 */
public class TestimonioVO implements Serializable {
	private static final long serialVersionUID = -6113328105074899431L;

	private long idTestimonio;
	
	private long idPropietario;
	
	private long idTipoPropietario;
	
	private String descripcion;
	
	private Date fechaAlta;
	
	private int estatus;
	
	private String nombre;
	
	private String empresa;

	public long getIdTestimonio() {
		return idTestimonio;
	}

	public void setIdTestimonio(long idTestimonio) {
		this.idTestimonio = idTestimonio;
	}

	public long getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(long idPropietario) {
		this.idPropietario = idPropietario;
	}

	public long getIdTipoPropietario() {
		return idTipoPropietario;
	}

	public void setIdTipoPropietario(long idTipoPropietario) {
		this.idTipoPropietario = idTipoPropietario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	
}
