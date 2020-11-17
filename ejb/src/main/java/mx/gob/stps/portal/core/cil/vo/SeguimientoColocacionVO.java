package mx.gob.stps.portal.core.cil.vo;

import java.io.Serializable;
import java.util.Date;

public class SeguimientoColocacionVO implements Serializable{

	private static final long serialVersionUID = 4563051216338144871L;
	
	private long idCil;
	private long idSeguimientoColocacion;
	private long idCandidato;
	private long idUsuario;
	private long idTiposeguimiento;
	private Date fechaSeguimiento;
	private int estatus;
	private int idCausa;
	private String otraCausa;
	private Date fechaColocacion;
	private String horaSeguimiento;
	private long idAtencion;
	
	public SeguimientoColocacionVO() {
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
	 * @return the idSeguimientoColocacion
	 */
	public long getIdSeguimientoColocacion() {
		return idSeguimientoColocacion;
	}
	
	/**
	 * @param idSeguimientoColocacion the idSeguimientoColocacion to set
	 */
	public void setIdSeguimientoColocacion(long idSeguimientoColocacion) {
		this.idSeguimientoColocacion = idSeguimientoColocacion;
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
	 * @return the idUsuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * @return the idTiposeguimiento
	 */
	public long getIdTiposeguimiento() {
		return idTiposeguimiento;
	}
	
	/**
	 * @param idTiposeguimiento the idTiposeguimiento to set
	 */
	public void setIdTiposeguimiento(long idTiposeguimiento) {
		this.idTiposeguimiento = idTiposeguimiento;
	}
	
	/**
	 * @return the fechaSeguimiento
	 */
	public Date getFechaSeguimiento() {
		return fechaSeguimiento;
	}
	
	/**
	 * @param fechaSeguimiento the fechaSeguimiento to set
	 */
	public void setFechaSeguimiento(Date fechaSeguimiento) {
		this.fechaSeguimiento = fechaSeguimiento;
	}
	
	/**
	 * @return the estatus
	 */
	public int getEstatus() {
		return estatus;
	}
	
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	
	/**
	 * @return the idCausa
	 */
	public int getIdCausa() {
		return idCausa;
	}
	
	/**
	 * @param idCausa the idCausa to set
	 */
	public void setIdCausa(int idCausa) {
		this.idCausa = idCausa;
	}
	
	/**
	 * @return the otraCausa
	 */
	public String getOtraCausa() {
		return otraCausa;
	}
	
	/**
	 * @param otraCausa the otraCausa to set
	 */
	public void setOtraCausa(String otraCausa) {
		this.otraCausa = otraCausa;
	}
	
	/**
	 * @return the fechaColocacion
	 */
	public Date getFechaColocacion() {
		return fechaColocacion;
	}
	
	/**
	 * @param fechaColocacion the fechaColocacion to set
	 */
	public void setFechaColocacion(Date fechaColocacion) {
		this.fechaColocacion = fechaColocacion;
	}
	
	/**
	 * @return the horaSeguimiento
	 */
	public String getHoraSeguimiento() {
		return horaSeguimiento;
	}
	
	/**
	 * @param horaSeguimiento the horaSeguimiento to set
	 */
	public void setHoraSeguimiento(String horaSeguimiento) {
		this.horaSeguimiento = horaSeguimiento;
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
}
