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
@Table(name = "OFERTA_CARRERA_FRAUDULENTA")
public class OfertaCarreraFraudulenta implements Serializable {
	private static final long serialVersionUID = 2813292792804441445L;

	protected long idOfertaCarrera;

	protected long idOfertaEmpleo;

	protected long idCarreraEspecialidad;

	protected int principal;
	
	protected Date fechaAlta;

	/**
	 * Method 'OfertaCarreraFraudulenta'
	 * 
	 */
	public OfertaCarreraFraudulenta() {
	}

	/**
	 * Method 'getIdOfertaCarrera'
	 * 
	 * @return int
	 */
	@Id
	@Column(name = "id_oferta_carrera")
	public long getIdOfertaCarrera() {
		return idOfertaCarrera;
	}

	/**
	 * Method 'setIdOfertaCarrera'
	 * 
	 * @param idOfertaCarrera
	 */
	public void setIdOfertaCarrera(long idOfertaCarrera) {
		this.idOfertaCarrera = idOfertaCarrera;
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
	 * Method 'getIdCarreraEspecialidad'
	 * 
	 * @return int
	 */
	@Column(name = "id_carrera_especialidad")
	public long getIdCarreraEspecialidad() {
		return idCarreraEspecialidad;
	}

	/**
	 * Method 'setIdCarreraEspecialidad'
	 * 
	 * @param idCarreraEspecialidad
	 */
	public void setIdCarreraEspecialidad(long idCarreraEspecialidad) {
		this.idCarreraEspecialidad = idCarreraEspecialidad;
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
