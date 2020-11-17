package mx.gob.stps.portal.core.seguridad.vo;

import java.io.Serializable;

public final class PerfilVO implements Serializable {
	private static final long serialVersionUID = 6414499935329194043L;

	private long idPerfil;

	private String nombre;

	private String descripcion;

	private int estatus;	
	
	public PerfilVO(){}

	public PerfilVO(long idPerfil, String nombre, String descripcion, int estatus){
		this.idPerfil = idPerfil;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
		
	
}
