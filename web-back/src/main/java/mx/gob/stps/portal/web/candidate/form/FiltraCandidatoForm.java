package mx.gob.stps.portal.web.candidate.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.web.infra.utils.Utils;

import org.apache.struts.action.ActionForm;


public class FiltraCandidatoForm extends ActionForm{
	private static final long serialVersionUID = -6129545422505578673L;
	private long idUsuario;
	private long idTipoUsuario;
	private long idPerfil;
	//datos de la busqueda
	private String nombre;
	private String apellido1;
	private String apellido2;	
	private String fechaNacimiento;
	private String fechaAlta;
	private String correoElectronico;
	private String curp;
	private String telefono;
	private long idEntidad;
	private long idMunicipio;
	private String domicilio;
	private String usuario;	
	
	private List<CandidatoVo> lstCandidatos;
	
	public void reset(){
		nombre = null;
		apellido1 = null;
		apellido2 = null;		
		fechaNacimiento = null;
		lstCandidatos = null;	
		correoElectronico = null;
		curp = null;
		telefono = null;
		idEntidad = 0;
		idMunicipio = 0;
		domicilio = null;
		usuario = null;		
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
	 * @return the idTipoUsuario
	 */
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	/**
	 * @param idTipoUsuario the idTipoUsuario to set
	 */
	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	/**
	 * @return the idPerfil
	 */
	public long getIdPerfil() {
		return idPerfil;
	}

	/**
	 * @param idPerfil the idPerfil to set
	 */
	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;			
	}



	/**
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	
	/**
	 * @return the lstCandidatos
	 */
	public List<CandidatoVo> getLstCandidatos() {
		return lstCandidatos;
	}

	/**
	 * @param lstCandidatos the lstCandidatos to set
	 */
	public void setLstCandidatos(List<CandidatoVo> lstCandidatos) {
		this.lstCandidatos = lstCandidatos;
	}

	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * 
	 * @return the curp
	 */
	public String getCurp() {
		return curp;
	}

	/**
	 * 
	 * @param curp
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public long getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
	
}
