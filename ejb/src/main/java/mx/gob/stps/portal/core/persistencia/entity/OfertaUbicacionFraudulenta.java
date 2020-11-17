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
@Table(name = "OFERTA_UBICACION_FRAUDULENTA")
public class OfertaUbicacionFraudulenta implements Serializable {
	private static final long serialVersionUID = 5491229138263232445L;

	protected long idOfertaUbicacion;

	protected long idOfertaEmpleo;

	protected long idEntidad;

	protected long idMunicipio;

	protected Date fechaAlta;

	/**
	 * Method 'OfertaUbicacionFraudulenta'
	 * 
	 */
	public OfertaUbicacionFraudulenta() {
	}

	/**
	 * Method 'getIdOfertaUbicacion'
	 * 
	 * @return long
	 */
	@Id
	@Column(name = "id_oferta_ubicacion")
	public long getIdOfertaUbicacion() {
		return idOfertaUbicacion;
	}

	/**
	 * Method 'setIdOfertaUbicacion'
	 * 
	 * @param idOfertaUbicacion
	 */
	public void setIdOfertaUbicacion(long idOfertaUbicacion) {
		this.idOfertaUbicacion = idOfertaUbicacion;
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
	 * Method 'getIdEntidad'
	 * 
	 * @return long
	 */
	@Column(name = "id_entidad")
	public long getIdEntidad() {
		return idEntidad;
	}

	/**
	 * Method 'setIdEntidad'
	 * 
	 * @param idEntidad
	 */
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}

	/**
	 * Method 'getIdMunicipio'
	 * 
	 * @return long
	 */
	@Column(name = "id_municipio")
	public long getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * Method 'setIdMunicipio'
	 * 
	 * @param idMunicipio
	 */
	public void setIdMunicipio(long idMunicipio) {
		this.idMunicipio = idMunicipio;
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
