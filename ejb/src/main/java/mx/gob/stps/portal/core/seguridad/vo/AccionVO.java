package mx.gob.stps.portal.core.seguridad.vo;

import java.io.Serializable;

public final class AccionVO implements Serializable {
	private static final long serialVersionUID = 350220012256394074L;

	private long idAccion;
	private String vinculo;
	private String descripcion;
	private int autenticado;
	private int asignado;

	public long getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(long idAccion) {
		this.idAccion = idAccion;
	}
	public String getVinculo() {
		return vinculo;
	}
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getAutenticado() {
		return autenticado;
	}
	public void setAutenticado(int autenticado) {
		this.autenticado = autenticado;
	}
	public int getAsignado() {
		return asignado;
	}
	public void setAsignado(int asignado) {
		this.asignado = asignado;
	}
}
