package mx.gob.stps.portal.core.infra.vo;

import java.io.Serializable;
import java.util.Date;

public final class ContactoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6558784347371780702L;

	private long idContacto;

	private long idEmpresa;

	private String nombreContacto;

	private String cargo;

	private Date fechaAlta;

	private int estatus;

	private Date fechaModificacion;

	private long idTerceraEmpresa;

	private String correoElectronico;	
	
	private ContactoVO(){}
	
	private ContactoVO(long idContacto, long idEmpresa, String nombreContacto, String cargo,
			Date fechaAlta, int estatus, Date fechaModificacion,long idTerceraEmpresa, String correoElectronico ){
		this.idContacto = idContacto;
		this.idEmpresa = idEmpresa;
		this.nombreContacto = nombreContacto;
		this.cargo = cargo;
		this.fechaAlta = fechaAlta;
		this.estatus = estatus;
		this.fechaModificacion = fechaModificacion;
		this.idTerceraEmpresa = idTerceraEmpresa;
		this.correoElectronico = correoElectronico;
	}

	public long getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(long idContacto) {
		this.idContacto = idContacto;
	}

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public long getIdTerceraEmpresa() {
		return idTerceraEmpresa;
	}

	public void setIdTerceraEmpresa(long idTerceraEmpresa) {
		this.idTerceraEmpresa = idTerceraEmpresa;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
}
