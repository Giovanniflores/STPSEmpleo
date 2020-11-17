package mx.gob.stps.portal.core.autorizacion.vo;

import java.io.Serializable;
import java.util.Date;

public class RegistroPorValidarVO implements Serializable {
	private static final long serialVersionUID = 6408681001467417726L;
	protected long idRegValidar;
	protected long idRegistro;
	protected long idTipoRegistro;
	protected long idPropietario;
	protected long idTipoPropietario;
	protected long idUsuario;
	protected Date fechaAlta;
	protected Date fechaModificacion;

	private Long idMotivoRechazo;
	private String detalleRechazo;
	
	protected int estatus;
	
	private int estatusReg;
	private String estatusRegDesc;
	
	private String registro;
	private String propietario;

	protected String tipoRegistro;
	protected String tipoPropietario;
	
	protected long idSubTipoRegistro;
	protected String subTipoRegistro;
	
	public long getIdRegValidar() {
		return idRegValidar;
	}
	public void setIdRegValidar(long idRegValidar) {
		this.idRegValidar = idRegValidar;
	}
	public long getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(long idRegistro) {
		this.idRegistro = idRegistro;
	}
	public long getIdTipoRegistro() {
		return idTipoRegistro;
	}
	public void setIdTipoRegistro(long idTipoRegistro) {
		this.idTipoRegistro = idTipoRegistro;
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
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
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
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public String getTipoPropietario() {
		return tipoPropietario;
	}
	public void setTipoPropietario(String tipoPropietario) {
		this.tipoPropietario = tipoPropietario;
	}
	public int getEstatusReg() {
		return estatusReg;
	}
	public void setEstatusReg(int estatusReg) {
		this.estatusReg = estatusReg;
	}
	public String getEstatusRegDesc() {
		return estatusRegDesc;
	}
	public void setEstatusRegDesc(String estatusRegDesc) {
		this.estatusRegDesc = estatusRegDesc;
	}
	public long getIdSubTipoRegistro() {
		return idSubTipoRegistro;
	}
	public void setIdSubTipoRegistro(long idSubTipoRegistro) {
		this.idSubTipoRegistro = idSubTipoRegistro;
	}
	public String getSubTipoRegistro() {
		return subTipoRegistro;
	}
	public void setSubTipoRegistro(String subTipoRegistro) {
		this.subTipoRegistro = subTipoRegistro;
	}
	public Long getIdMotivoRechazo() {
		return idMotivoRechazo;
	}
	public void setIdMotivoRechazo(Long idMotivoRechazo) {
		this.idMotivoRechazo = idMotivoRechazo;
	}
	public String getDetalleRechazo() {
		return detalleRechazo;
	}
	public void setDetalleRechazo(String detalleRechazo) {
		this.detalleRechazo = detalleRechazo;
	}
}