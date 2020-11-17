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

/*@Entity
@Table(name = "CONTACTO")
@NamedQueries({
	@NamedQuery(name = "buscaContacto",
			query = "SELECT c FROM Contacto c WHERE c.idEmpresa=:idEmpresa AND c.estatus=:estatus ORDER BY c.nombreContacto ")
})*/
public class Contacto implements Serializable {
	private static final long serialVersionUID = -8681777140889771108L;

	protected long idContacto;

	protected long idEmpresa;

	protected String nombreContacto;

	protected String cargo;

	protected Date fechaAlta;

	protected int estatus;

	protected Date fechaModificacion;

	protected long idTerceraEmpresa;

	protected String correoElectronico;

	/**
	 * Method 'Contacto'
	 * 
	 */
	private Contacto() {
	}

	/**
	 * Method 'getIdContacto'
	 * 
	 * @return long
	 */
	@Id
	@SequenceGenerator(name = "SEQ_CONTACTO", sequenceName = "SEQ_CONTACTO", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTACTO")
	@Column(name = "id_contacto")
	public long getIdContacto() {
		return idContacto;
	}

	/**
	 * Method 'setIdContacto'
	 * 
	 * @param idContacto
	 */
	public void setIdContacto(long idContacto) {
		this.idContacto = idContacto;
	}

	/**
	 * Method 'getIdEmpresa'
	 * 
	 * @return long
	 */
	@Column(name = "id_empresa")
	public long getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * Method 'setIdEmpresa'
	 * 
	 * @param idEmpresa
	 */
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * Method 'getNombreContacto'
	 * 
	 * @return String
	 */
	@Column(name = "nombre_contacto")
	public String getNombreContacto() {
		return nombreContacto;
	}

	/**
	 * Method 'setNombreContacto'
	 * 
	 * @param nombreContacto
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	/**
	 * Method 'getCargo'
	 * 
	 * @return String
	 */
	@Column(name = "cargo")
	public String getCargo() {
		return cargo;
	}

	/**
	 * Method 'setCargo'
	 * 
	 * @param cargo
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
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
	 * Method 'getEstatus'
	 * 
	 * @return int
	 */
	@Column(name = "estatus")
	public int getEstatus() {
		return estatus;
	}

	/**
	 * Method 'setEstatus'
	 * 
	 * @param estatus
	 */
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	/**
	 * Method 'getFechaModificacion'
	 * 
	 * @return Date
	 */
	@Column(name = "fecha_modificacion")
	@Temporal(TemporalType.DATE)
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * Method 'setFechaModificacion'
	 * 
	 * @param fechaModificacion
	 */
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * Method 'getIdTerceraEmpresa'
	 * 
	 * @return long
	 */
	@Column(name = "id_tercera_empresa")
	public long getIdTerceraEmpresa() {
		return idTerceraEmpresa;
	}

	/**
	 * Method 'setIdTerceraEmpresa'
	 * 
	 * @param idTerceraEmpresa
	 */
	public void setIdTerceraEmpresa(long idTerceraEmpresa) {
		this.idTerceraEmpresa = idTerceraEmpresa;
	}

	/**
	 * Method 'getCorreoElectronico'
	 * 
	 * @return String
	 */
	@Column(name = "correo_electronico")
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * Method 'setCorreoElectronico'
	 * 
	 * @param correoElectronico
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

}
