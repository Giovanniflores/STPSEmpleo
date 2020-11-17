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
@Table(name = "OFERTA_IDIOMA_FRAUDULENTA")
public class OfertaIdiomaFraudulenta implements Serializable {
	private static final long serialVersionUID = 7755305297323465279L;

	protected long idOfertaIdioma;

	protected long idOfertaEmpleo;

	protected long idIdioma;

	protected long idCertificacion;

	protected long idDominio;

	protected Date fechaAlta;

	protected int principal;
	
	/**
	 * Method 'OfertaIdiomaFraudulenta'
	 * 
	 */
	public OfertaIdiomaFraudulenta() {
	}

	/**
	 * Method 'getIdOfertaIdioma'
	 * 
	 * @return long
	 */
	@Id
	@Column(name = "id_oferta_idioma")
	public long getIdOfertaIdioma() {
		return idOfertaIdioma;
	}

	/**
	 * Method 'setIdOfertaIdioma'
	 * 
	 * @param idOfertaIdioma
	 */
	public void setIdOfertaIdioma(long idOfertaIdioma) {
		this.idOfertaIdioma = idOfertaIdioma;
	}

	/**
	 * Method 'getIdOfertaEmpleo'
	 * 
	 * @return long
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
	 * Method 'getIdIdioma'
	 * 
	 * @return long
	 */
	@Column(name = "id_idioma")
	public long getIdIdioma() {
		return idIdioma;
	}

	/**
	 * Method 'setIdIdioma'
	 * 
	 * @param idIdioma
	 */
	public void setIdIdioma(long idIdioma) {
		this.idIdioma = idIdioma;
	}

	/**
	 * Method 'getIdCertificacion'
	 * 
	 * @return long
	 */
	@Column(name = "id_certificacion")
	public long getIdCertificacion() {
		return idCertificacion;
	}

	/**
	 * Method 'setIdCertificacion'
	 * 
	 * @param idCertificacion
	 */
	public void setIdCertificacion(long idCertificacion) {
		this.idCertificacion = idCertificacion;
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

	@Column(name = "PRINCIPAL")
	public int getPrincipal() {
		return principal;
	}

	public void setPrincipal(int principal) {
		this.principal = principal;
	}

	
}
