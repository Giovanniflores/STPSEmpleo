package mx.gob.stps.portal.core.kiosco.vo;

import java.io.Serializable;
import java.util.Date;


public class KioscoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2174808586426107126L;

	private long idKiosco;

	private String correoElectronico;

	private long estatus;

	private int extension;

    private Date fechaAlta;

    private Date fechaBaja;

	private int idDomicilio;

	private int idEntidad;

	private int idMotivoEliminacion;

	private int idMunicipio;

	private int idPropiedad;

	private int idPropioLogo;

	private int idTipoImpresion;

	private String lada;

	private String nombre;

	private String password;

	private String responsable;

	private String telefono;

	private String usuario;

	private String version;

	public long getIdKiosco() {
		return idKiosco;
	}

	public void setIdKiosco(long idKiosco) {
		this.idKiosco = idKiosco;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public long getEstatus() {
		return estatus;
	}

	public void setEstatus(long estatus) {
		this.estatus = estatus;
	}

	public int getExtension() {
		return extension;
	}

	public void setExtension(int extension) {
		this.extension = extension;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public int getIdDomicilio() {
		return idDomicilio;
	}

	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public int getIdMotivoEliminacion() {
		return idMotivoEliminacion;
	}

	public void setIdMotivoEliminacion(int idMotivoEliminacion) {
		this.idMotivoEliminacion = idMotivoEliminacion;
	}

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public int getIdPropiedad() {
		return idPropiedad;
	}

	public void setIdPropiedad(int idPropiedad) {
		this.idPropiedad = idPropiedad;
	}

	public int getIdPropioLogo() {
		return idPropioLogo;
	}

	public void setIdPropioLogo(int idPropioLogo) {
		this.idPropioLogo = idPropioLogo;
	}

	public int getIdTipoImpresion() {
		return idTipoImpresion;
	}

	public void setIdTipoImpresion(int idTipoImpresion) {
		this.idTipoImpresion = idTipoImpresion;
	}

	public String getLada() {
		return lada;
	}

	public void setLada(String lada) {
		this.lada = lada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
	

	
	
	
	
	
}
