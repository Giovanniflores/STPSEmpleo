package mx.gob.stps.portal.core.seguridad.vo;

import java.io.Serializable;

public final class ConfirmacionVO implements Serializable {
	private static final long serialVersionUID = -429458229461991355L;

	private String usuario;
	private String nombre;
	private String correoElectronico;
	private String contrasena;
	private String urlConfirmacion;
	private Integer estatus;
	
	private Long idEmpresa;
	private Long idCandidato;
	
	

	private ConfirmacionVO(String usuario, String nombre, String correoElectronico, String contrasena, String urlConfirmacion){
		this.usuario = usuario;
		this.nombre = nombre;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
		this.urlConfirmacion = urlConfirmacion;
	}

	private ConfirmacionVO(String usuario, String nombre, String correoElectronico, String contrasena, String urlConfirmacion, int estatus){
		this.usuario = usuario;
		this.nombre = nombre;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
		this.urlConfirmacion = urlConfirmacion;
		this.estatus = estatus;
	}

	
	public static ConfirmacionVO getInstance(String usuario, String nombre, String correoElectronico, String contrasena, String urlConfirmacion){
		return new ConfirmacionVO(usuario, nombre, correoElectronico, contrasena, urlConfirmacion);
	}
	
	public static ConfirmacionVO getInstance(String usuario, String nombre, String correoElectronico, String contrasena, String urlConfirmacion, int estatus){
		return new ConfirmacionVO(usuario, nombre, correoElectronico, contrasena, urlConfirmacion, estatus);
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getUrlConfirmacion() {
		return urlConfirmacion;
	}

	public void setUrlConfirmacion(String urlConfirmacion) {
		this.urlConfirmacion = urlConfirmacion;
	}
	
	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(Long idCandidato) {
		this.idCandidato = idCandidato;
	}
	

}
