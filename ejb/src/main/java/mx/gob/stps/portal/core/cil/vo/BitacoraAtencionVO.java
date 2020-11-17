package mx.gob.stps.portal.core.cil.vo;

import java.util.Date;
import java.io.Serializable;

public final class BitacoraAtencionVO implements Serializable {

	private static final long serialVersionUID = -8796239521979514232L;
	
	private long idCil;
	private long idCandidato;
	private long idAtencion;
	private long idTipoAtencion;
	private Date fechaInicio;
	private String detalle;
	private long contador;
	private long idBitacoraAtencion;
	
	public BitacoraAtencionVO (long idCil, long idCandidato, long idAtencion, long idTipoAtencion, Date fechaInicio, String detalle, int contador) {
		setContador(contador);
		setDetalle(detalle);
		setFechaInicio(fechaInicio);
		setIdAtencion(idAtencion);
		setIdCandidato(idCandidato);
		setIdCil(idCil);
		setIdTipoAtencion(idTipoAtencion);
	}
	
	/**
	 * @return the idCil
	 */
	public long getIdCil() {
		return idCil;
	}
	
	/**
	 * @param idCil the idCil to set
	 */
	public void setIdCil(long idCil) {
		this.idCil = idCil;
	}
	
	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}
	
	/**
	 * @param idCandidato the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
	
	/**
	 * @return the idAtencion
	 */
	public long getIdAtencion() {
		return idAtencion;
	}
	
	/**
	 * @param idAtencion the idAtencion to set
	 */
	public void setIdAtencion(long idAtencion) {
		this.idAtencion = idAtencion;
	}
	
	/**
	 * @return the idTipoAtencion
	 */
	public long getIdTipoAtencion() {
		return idTipoAtencion;
	}
	
	/**
	 * @param idTipoAtencion the idTipoAtencion to set
	 */
	public void setIdTipoAtencion(long idTipoAtencion) {
		this.idTipoAtencion = idTipoAtencion;
	}
	
	/**
	 * @return the fecha_inicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fecha_inicio the fecha_inicio to set
	 */
	public void setFechaInicio(Date fecha_inicio) {
		this.fechaInicio = fecha_inicio;
	}
	
	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}
	
	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	/**
	 * @return the contador
	 */
	public long getContador() {
		return contador;
	}
	
	/**
	 * @param contador the contador to set
	 */
	public void setContador(long contador) {
		this.contador = contador;
	}
	
	
	
	/**
	 * @return the idBitacoraAtencion
	 */
	public long getIdBitacoraAtencion() {
		return idBitacoraAtencion;
	}

	/**
	 * @param idBitacoraAtencion the idBitacoraAtencion to set
	 */
	public void setIdBitacoraAtencion(long idBitacoraAtencion) {
		this.idBitacoraAtencion = idBitacoraAtencion;
	}

	public String toString() {
		StringBuilder eventLog = new StringBuilder();
		eventLog.append("[ " + getContador() + " " + getDetalle() + " " + getIdAtencion() + " " + getIdCandidato() + " " + getIdCil() + " " + getIdTipoAtencion() + " " + getFechaInicio() + " ]");
		return eventLog.toString();
	}
}