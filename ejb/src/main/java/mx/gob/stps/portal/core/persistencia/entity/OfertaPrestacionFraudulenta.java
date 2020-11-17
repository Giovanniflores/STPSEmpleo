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
@Table(name = "OFERTA_PRESTACION_FRAUDULENTA")
public class OfertaPrestacionFraudulenta implements Serializable {
	private static final long serialVersionUID = 2929525061192883650L;

	protected long idOfertaPrestacion;

	protected long idOfertaEmpleo;

	protected long idPrestacion;

	protected Date fechaAlta;

	/**
	 * Method 'OfertaPrestacionFraudulenta'
	 * 
	 */
	public OfertaPrestacionFraudulenta() {
	}

	/**
	 * Method 'getIdOfertaPrestacion'
	 * 
	 * @return long
	 */
	@Id
	@Column(name = "id_oferta_prestacion")
	public long getIdOfertaPrestacion() {
		return idOfertaPrestacion;
	}

	/**
	 * Method 'setIdOfertaPrestacion'
	 * 
	 * @param idOfertaPrestacion
	 */
	public void setIdOfertaPrestacion(long idOfertaPrestacion) {
		this.idOfertaPrestacion = idOfertaPrestacion;
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
	 * Method 'getIdPrestacion'
	 * 
	 * @return long
	 */
	@Column(name = "id_prestacion")
	public long getIdPrestacion() {
		return idPrestacion;
	}

	/**
	 * Method 'setIdPrestacion'
	 * 
	 * @param idPrestacion
	 */
	public void setIdPrestacion(long idPrestacion) {
		this.idPrestacion = idPrestacion;
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

}
