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
@Table(name = "OFERTA_SECTOR")
@NamedQueries({
	@NamedQuery(name = "buscaSectorOferta",
			query = "SELECT c FROM OfertaSector c WHERE c.idOfertaEmpleo=:idOferta"),
	@NamedQuery(name = "borraSectorOferta",
			query = "DELETE FROM OfertaSector c WHERE c.idOfertaEmpleo=:idOferta")
})



public class OfertaSector implements Serializable {
	private static final long serialVersionUID = 6398863175570202309L;

	protected long idOfertaSector;

	protected long idOfertaEmpleo;

	protected long idSector;

	protected Date fechaAlta;

	/**
	 * Method 'OfertaSector'
	 * 
	 */
	public OfertaSector() {
	}

	/**
	 * Method 'getIdOfertaSector'
	 * 
	 * @return long
	 */
	@Id
	@SequenceGenerator(name = "SEQ_OFER_SECTOR", sequenceName = "SEQ_OFER_SECTOR", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OFER_SECTOR")
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
