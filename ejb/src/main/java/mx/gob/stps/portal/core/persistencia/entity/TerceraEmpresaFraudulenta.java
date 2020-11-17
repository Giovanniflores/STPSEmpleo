package mx.gob.stps.portal.core.persistencia.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*@Entity
@Table(name = "TERCERA_EMPRESA_FRAUDULENTA")*/
public class TerceraEmpresaFraudulenta implements Serializable {
	private static final long serialVersionUID = -12566976131191539L;

	protected long idTerceraEmpresa;

	protected long idEmpresa;

	protected String rfc;

	protected long idTipoPersona;

	protected String nombre;

	protected String apellido1;

	protected String apellido2;

	protected String razonSocial;

	protected String descripcion;

	protected String contactoEmpresa;

	protected long idTipoEmpresa;

	protected long idActividadEconomica;

	protected long numeroEmpleados;

	protected Date fechaAlta;

	protected int estatus;

	protected Date fechaUltimaActualizacion;

	protected String correoElectronico;
	
	//COMENTAR EN PRODUCCION 
	protected String nombreComercial;

	/**
	 * Method 'TerceraEmpresaFraudulenta'
	 * 
	 */
	private TerceraEmpresaFraudulenta() {
	}

	/**
	 * Method 'getIdTerceraEmpresa'
	 * 
	 * @return long
	 */
	@Id
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
	 * Method 'getRfc'
	 * 
	 * @return String
	 */
	@Column(name = "rfc")
	public String getRfc() {
		return rfc;
	}

	/**
	 * Method 'setRfc'
	 * 
	 * @param rfc
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * Method 'getIdTipoPersona'
	 * 
	 * @return long
	 */
	@Column(name = "id_tipo_persona")
	public long getIdTipoPersona() {
		return idTipoPersona;
	}

	/**
	 * Method 'setIdTipoPersona'
	 * 
	 * @param idTipoPersona
	 */
	public void setIdTipoPersona(long idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}

	/**
	 * Method 'getNombre'
	 * 
	 * @return String
	 */
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	/**
	 * Method 'setNombre'
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Method 'getApellido1'
	 * 
	 * @return String
	 */
	@Column(name = "apellido1")
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * Method 'setApellido1'
	 * 
	 * @param apellido1
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * Method 'getApellido2'
	 * 
	 * @return String
	 */
	@Column(name = "apellido2")
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * Method 'setApellido2'
	 * 
	 * @param apellido2
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * Method 'getRazonSocial'
	 * 
	 * @return String
	 */
	@Column(name = "razon_social")
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * Method 'setRazonSocial'
	 * 
	 * @param razonSocial
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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
	 * Method 'getContactoEmpresa'
	 * 
	 * @return String
	 */
	@Column(name = "contacto_empresa")
	public String getContactoEmpresa() {
		return contactoEmpresa;
	}

	/**
	 * Method 'setContactoEmpresa'
	 * 
	 * @param contactoEmpresa
	 */
	public void setContactoEmpresa(String contactoEmpresa) {
		this.contactoEmpresa = contactoEmpresa;
	}

	/**
	 * Method 'getIdTipoEmpresa'
	 * 
	 * @return long
	 */
	@Column(name = "id_tipo_empresa")
	public long getIdTipoEmpresa() {
		return idTipoEmpresa;
	}

	/**
	 * Method 'setIdTipoEmpresa'
	 * 
	 * @param idTipoEmpresa
	 */
	public void setIdTipoEmpresa(long idTipoEmpresa) {
		this.idTipoEmpresa = idTipoEmpresa;
	}

	/**
	 * Method 'getIdActividadEconomica'
	 * 
	 * @return long
	 */
	@Column(name = "id_actividad_economica")
	public long getIdActividadEconomica() {
		return idActividadEconomica;
	}

	/**
	 * Method 'setIdActividadEconomica'
	 * 
	 * @param idActividadEconomica
	 */
	public void setIdActividadEconomica(long idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}

	/**
	 * Method 'getNumeroEmpleados'
	 * 
	 * @return long
	 */
	@Column(name = "numero_empleados")
	public long getNumeroEmpleados() {
		return numeroEmpleados;
	}

	/**
	 * Method 'setNumeroEmpleados'
	 * 
	 * @param numeroEmpleados
	 */
	public void setNumeroEmpleados(long numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
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
	 * Method 'getFechaUltimaActualizacion'
	 * 
	 * @return Date
	 */
	@Column(name = "fecha_ultima_actualizacion")
	@Temporal(TemporalType.DATE)
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	/**
	 * Method 'setFechaUltimaActualizacion'
	 * 
	 * @param fechaUltimaActualizacion
	 */
	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
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

	/* COMENTAR EN PROD */
	@Column(name = "nombre_comercial")
	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	
}
