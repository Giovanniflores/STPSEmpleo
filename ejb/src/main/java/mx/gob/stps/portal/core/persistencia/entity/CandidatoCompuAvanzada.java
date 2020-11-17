package mx.gob.stps.portal.core.persistencia.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CANDIDATO_COMPU_AVANZADA")
public class CandidatoCompuAvanzada implements Serializable {
	private static final long serialVersionUID = -4971833857369281231L;

	protected long idCandidatoCompuAvanzada;

	protected long idCandidato;

	protected String softwareHardware;

	protected long idExperiencia;

	protected long idDominio;

	protected String descripcion;

	protected Date fechaAlta;
	
	protected int principal;

	/**
	 * Method 'CandidatoCompuAvanzada'
	 * 
	 */
	public CandidatoCompuAvanzada() {
	}

	/**
	 * Method 'getIdCandidatoCompuAvanzada'
	 * 
	 * @return long
	 */
	@Id
	@SequenceGenerator(name = "SEQ_CAND_COMPU_AV", sequenceName = "SEQ_CAND_COMPU_AV", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAND_COMPU_AV")
	@Column(name = "id_candidato_compu_avanzada")
	public long getIdCandidatoCompuAvanzada() {
		return idCandidatoCompuAvanzada;
	}

	/**
	 * Method 'setIdCandidatoCompuAvanzada'
	 * 
	 * @param idCandidatoCompuAvanzada
	 */
	public void setIdCandidatoCompuAvanzada(long idCandidatoCompuAvanzada) {
		this.idCandidatoCompuAvanzada = idCandidatoCompuAvanzada;
	}

	/**
	 * Method 'getIdCandidato'
	 * 
	 * @return long
	 */
	@Column(name = "id_candidato")
	public long getIdCandidato() {
		return idCandidato;
	}

	/**
	 * Method 'setIdCandidato'
	 * 
	 * @param idCandidato
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	/**
	 * Method 'getSoftwareHardware'
	 * 
	 * @return String
	 */
	@Column(name = "software_hardware")
	public String getSoftwareHardware() {
		return softwareHardware;
	}

	/**
	 * Method 'setSoftwareHardware'
	 * 
	 * @param softwareHardware
	 */
	public void setSoftwareHardware(String softwareHardware) {
		this.softwareHardware = softwareHardware;
	}

	/**
	 * Method 'getIdExperiencia'
	 * 
	 * @return long
	 */
	@Column(name = "id_experiencia")
	public long getIdExperiencia() {
		return idExperiencia;
	}

	/**
	 * Method 'setIdExperiencia'
	 * 
	 * @param idExperiencia
	 */
	public void setIdExperiencia(long idExperiencia) {
		this.idExperiencia = idExperiencia;
	}

	/**
	 * Method 'getIdDominio'
	 * 
	 * @return long
	 */
	@Column(name = "id_dominio")
	public long getIdDominio() {
		return idDominio;
	}

	/**
	 * Method 'setIdDominio'
	 * 
	 * @param idDominio
	 */
	public void setIdDominio(long idDominio) {
		this.idDominio = idDominio;
	}

	/**
	 * Method 'getDescripcion'
	 * 
	 * @return String
	 */
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Method 'setDescripcion'
	 * 
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Method 'getFechaAlta'
	 * 
	 * @return Date
	 */
	@Column(name = "fecha_alta")
	@Temporal(TemporalType.DATE)
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Method 'setFechaAlta'
	 * 
	 * @param fechaAlta
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	/**
	 * @return the principal
	 */
	@Column(name = "principal")
	public int getPrincipal() {
		return principal;
	}
	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	
}
