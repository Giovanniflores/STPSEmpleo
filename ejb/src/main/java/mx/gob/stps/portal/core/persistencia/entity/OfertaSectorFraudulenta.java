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
@Table(name = "OFERTA_SECTOR_FRAUDULENTA")
public class OfertaSectorFraudulenta implements Serializable {
	private static final long serialVersionUID = 8483237559838592019L;

	protected long idOfertaSector;

	protected long idOfertaEmpleo;

	protected long idSector;

	protected Date fechaAlta;

	/**
	 * Method 'OfertaSectorFraudulenta'
	 * 
	 */
	public OfertaSectorFraudulenta() {
	}

	/**
	 * Method 'getIdOfertaSector'
	 * 
	 * @return long
	 */
	@Id
	@Column(name = "id_oferta_sector")
	public long getIdOfertaSector() {
		return idOfertaSector;
	}

	/**
	 * Method 'setIdOfertaSector'
	 * 
	 * @param idOfertaSector
	 */
	public void setIdOfertaSector(long idOfertaSector) {
		this.idOfertaSector = idOfertaSector;
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
	 * Method 'getIdSector'
	 * 
	 * @return long
	 */
	@Column(name = "id_sector")
	public long getIdSector() {
		return idSector;
	}

	/**
	 * Method 'setIdSector'
	 * 
	 * @param idSector
	 */
	public void setIdSector(long idSector) {
		this.idSector = idSector;
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
