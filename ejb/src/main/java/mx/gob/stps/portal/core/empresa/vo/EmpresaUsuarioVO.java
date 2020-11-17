package mx.gob.stps.portal.core.empresa.vo;

import java.io.Serializable;
import java.util.Date;

public class EmpresaUsuarioVO implements Serializable {
	
	private static final long serialVersionUID = -8869574169898494009L;
	
	private long idEmpresa;
	private String RFC;
	private String email;
	private int estatus;
	private String CodigoPostal;
	private Date fechaModificacion;
	
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getRFC() {
		return RFC;
	}
	public void setRFC(String rFC) {
		RFC = rFC;
	}
	public String getCodigoPostal() {
		return CodigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		CodigoPostal = codigoPostal;
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
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
