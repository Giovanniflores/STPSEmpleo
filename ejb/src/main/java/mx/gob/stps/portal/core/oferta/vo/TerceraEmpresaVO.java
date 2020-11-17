package mx.gob.stps.portal.core.oferta.vo;

import java.io.Serializable;

//TODO ELIMINAR CLASE YA NO SE UTILIZA
public class TerceraEmpresaVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2640369826619410996L;

	protected long idTerceraEmpresa;

	protected long idEmpresa;

	
	protected String razonSocial;

	protected String descripcion;

	protected int estatus;
	
	protected String nombre;
	
	protected long tipo;
	
	private TerceraEmpresaVO(){}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getTipo() {
		return tipo;
	}

	public void setTipo(long tipo) {
		this.tipo = tipo;
	}

	public long getIdTerceraEmpresa() {
		return idTerceraEmpresa;
	}

	public void setIdTerceraEmpresa(long idTerceraEmpresa) {
		this.idTerceraEmpresa = idTerceraEmpresa;
	}

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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
