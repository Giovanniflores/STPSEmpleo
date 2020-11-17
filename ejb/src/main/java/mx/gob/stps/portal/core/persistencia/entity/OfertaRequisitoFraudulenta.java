package mx.gob.stps.portal.core.persistencia.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "OFERTA_REQUISITO_FRAUDULENTA")
public class OfertaRequisitoFraudulenta implements Serializable {
	private static final long serialVersionUID = -5709570661093982216L;

	protected long idOfertaRequisito;

	protected long idOfertaEmpleo;

	protected long idTipoRequisito;

	protected String descripcion;

	protected long idExperiencia;

	protected long idDominio;

	protected int principal;
	
	protected Date fechaAlta;

	/**
	 * Method 'OfertaRequisitoFraudulenta'
	 * 
	 */
	public OfertaRequisitoFraudulenta() {
	}

	/**
	 * Method 'getIdOfertaRequisito'
	 * 
	 * @return int
	 */
	@Id
	@Column(name = "id_oferta_requisito")
	public long getIdOfertaRequisito() {
		return idOfertaRequisito;
	}

	/**
	 * Method 'setIdOfertaRequisito'
	 * 
	 * @param idOfertaRequisito
	 */
	public void setIdOfertaRequisito(long idOfertaRequisito) {
		this.idOfertaRequisito = idOfertaRequisito;
	}

	/**
	 * Method 'getIdOfertaEmpleo'
	 * 
	 * @return int
	 */
	@Column(name = "id_oferta_empleo")
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	/**
	 * Method 'setIdOfertaEmpleo'
	 * 
	 * @param idOfertaEmpleo
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	/**
	 * Method 'getIdTipoRequisito'
	 * 
	 * @return int
	 */
	@Column(name = "id_tipo_requisito")
	public long getIdTipoRequisito() {
		return idTipoRequisito;
	}

	/**
	 * Method 'setIdTipoRequisito'
	 * 
	 * @param idTipoRequisito
	 */
	public void setIdTipoRequisito(long idTipoRequisito) {
		this.idTipoRequisito = idTipoRequisito;
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
	 * Method 'getIdExperiencia'
	 * 
	 * @return int
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
	 * @return int
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

	@Column(name = "principal")
	public int getPrincipal() {
		return principal;
	}

	public void setPrincipal(int principal) {
		this.principal = principal;
	}

}
