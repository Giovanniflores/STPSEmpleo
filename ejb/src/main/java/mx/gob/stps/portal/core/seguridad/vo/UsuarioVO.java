package mx.gob.stps.portal.core.seguridad.vo;

import java.io.Serializable;
import java.util.Date;

public class UsuarioVO implements Serializable {
	private static final long serialVersionUID = 8403826559337849504L;
	private long idUsuario;
	private String correoElectronico;
	private long idTipoUsuario;
	private String contrasena;
	private long idRegistro;
	private int estatus;
	private Date fechaModificacion;
	private long idEntidad;
	private long idPerfil;
	private Date fechaAlta;
	private String nombre;
	private String apellido1;
	private String apellido2;
	protected int sesionActiva;
	protected String usuario;
	
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public long getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(long idRegistro) {
		this.idRegistro = idRegistro;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public long getIdEntidad() {
		return idEntidad;
	}
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}
	public long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
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
	public int getSesionActiva() {
		return sesionActiva;
	}
	public void setSesionActiva(int sesionActiva) {
		this.sesionActiva = sesionActiva;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
