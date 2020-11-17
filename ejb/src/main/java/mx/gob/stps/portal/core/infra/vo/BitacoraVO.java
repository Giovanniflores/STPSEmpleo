package mx.gob.stps.portal.core.infra.vo;

import java.io.Serializable;
import java.util.Calendar;


public final class BitacoraVO implements Serializable {
	private static final long serialVersionUID = -1931279612227556535L;

	private long idBitacora;

	private long idEvento;

	private long idUsuario;

	private String descripcion;

	private Calendar fechaEvento;

	private String detalle;	

	private long idRegistro;

	private long idTipoPropietario;

	// Datos del usuario que realizo el movimiento
	private String correoElectronico;
	private String nombre;
	private String apellido1;
	private String apellido2;

	public BitacoraVO(){}
	
	public BitacoraVO(long idBitacora, long idEvento, long idUsuario, 
			String descripcion, Calendar fechaEvento, String detalle){
		this.idBitacora = idBitacora;
		this.idEvento = idEvento;
		this.idUsuario = idUsuario;
		this.descripcion = descripcion;
		this.fechaEvento = fechaEvento;
		this.detalle = detalle;
	}

	/**
	 * @return the idBitacora
	 */
	public long getIdBitacora() {
		return idBitacora;
	}

	/**
	 * @param idBitacora the idBitacora to set
	 */
	public void setIdBitacora(long idBitacora) {
		this.idBitacora = idBitacora;
	}

	/**
	 * @return the idEvento
	 */
	public long getIdEvento() {
		return idEvento;
	}

	/**
	 * @param idEvento the idEvento to set
	 */
	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	/**
	 * @return the idUsuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the fechaEvento
	 */
	public Calendar getFechaEvento() {
		return fechaEvento;
	}

	/**
	 * @param fechaEvento the fechaEvento to set
	 */
	public void setFechaEvento(Calendar fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public long getIdTipoPropietario() {
		return idTipoPropietario;
	}

	public void setIdTipoPropietario(long idTipoPropietario) {
		this.idTipoPropietario = idTipoPropietario;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

}
