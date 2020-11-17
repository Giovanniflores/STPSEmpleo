package mx.gob.stps.portal.core.persistencia.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SEGUIMIENTO_COLOCACION")
@NamedQueries({
	@NamedQuery(name = "existTraceUserEmployment", query = "SELECT c FROM SeguimientoColocacion c WHERE	c.idCil = :idCil AND c.idCandidato = :idCandidato AND c.idAtencion = :idAtencion")
})

public class SeguimientoColocacion implements Serializable {
	
	private static final long serialVersionUID = -2437928180416872377L;
	
	protected long idCil;
	protected long idSeguimientoColocacion;
	protected long idCandidato;
	protected long idUsuario;
	protected long idTiposeguimiento;
	protected Date fechaSeguimiento;
	protected int estatus;
	protected int idCausa;
	protected String otraCausa;
	protected Date fechaColocacion;
	protected String horaSeguimiento;
	protected long idAtencion;
	
	@Id
	@SequenceGenerator(name = "SEQ_SEGUIMIENTO_COLOCACION", sequenceName = "SEQ_SEGUIMIENTO_COLOCACION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SEGUIMIENTO_COLOCACION")
	@Column(name = "id_seguimiento_colocacion")
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
	 * @return the idCil
	 */
	@Column(name = "id_cil")
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
	@Column(name = "id_candidato")
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
	@Column(name = "id_usuario")
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
	@Column(name = "id_tipo_seguimiento")
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
	@Column(name = "fecha_seguimiento")
	@Temporal(TemporalType.DATE)
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
	@Column(name = "estatus")
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
	@Column(name = "id_causa")
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
	@Column(name = "otra_causa")
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
	@Column(name = "fecha_colocacion")
	@Temporal(TemporalType.DATE)
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
	@Column(name = "hora_seguimiento")
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
	@Column(name = "id_atencion")
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