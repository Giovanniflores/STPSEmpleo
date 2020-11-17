package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.Date;

public class TelefonoVo implements Serializable {
	private static final long serialVersionUID = 1584184569246296205L;

	protected long idTelefono;

	protected long idPropietario;

	protected long idTipoPropietario;

	protected long idTipoTelefono;

	protected String acceso;

	protected String clave;

	protected String telefono;

	protected String extension;

	protected Date fechaAlta;

	protected int estatus;

	protected Date fechaModificacion;
	
	protected String tipoTelefonoString;

	
	public long getIdTelefono() {
		return idTelefono;
	}

	public long getIdPropietario() {
		return idPropietario;
	}

	public long getIdTipoPropietario() {
		return idTipoPropietario;
	}

	public long getIdTipoTelefono() {
		return idTipoTelefono;
	}

	public String getAcceso() {
		return acceso;
	}

	public String getClave() {
		return clave;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getExtension() {
		return extension;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public int getEstatus() {
		return estatus;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setIdTelefono(long idTelefono) {
		this.idTelefono = idTelefono;
	}

	public void setIdPropietario(long idPropietario) {
		this.idPropietario = idPropietario;
	}

	public void setIdTipoPropietario(long idTipoPropietario) {
		this.idTipoPropietario = idTipoPropietario;
	}

	public void setIdTipoTelefono(long idTipoTelefono) {
		this.idTipoTelefono = idTipoTelefono;
	}

	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	

	public String getTipoTelefonoString() {
		return tipoTelefonoString;
	}

	public void setTipoTelefonoString(String tipoTelefonoString) {
		this.tipoTelefonoString = tipoTelefonoString;
	}

	@Override
	public String toString() {
		return "TelefonoVo [idTelefono=" + idTelefono + ", idPropietario="
				+ idPropietario + ", idTipoPropietario=" + idTipoPropietario
				+ ", idTipoTelefono=" + idTipoTelefono + ", acceso=" + acceso
				+ ", clave=" + clave + ", telefono=" + telefono
				+ ", extension=" + extension + ", fechaAlta=" + fechaAlta
				+ ", estatus=" + estatus + ", fechaModificacion="
				+ fechaModificacion + "]";
	}

}
