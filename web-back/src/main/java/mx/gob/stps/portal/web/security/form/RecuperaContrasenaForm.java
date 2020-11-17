package mx.gob.stps.portal.web.security.form;

import org.apache.struts.action.ActionForm;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.seguridad.vo.ConfirmacionVO;

public class RecuperaContrasenaForm extends ActionForm {

	private static final long serialVersionUID = 8713218993819910650L;
	
	private String curp;
	private String usuario;
	private String correocode;
	private long idUsuario;
	private String tipo;
	private int idDomicilio;
	private String rfc;
	private String codigoPostal;
	private boolean urlvalido;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String correoElectronico;
	private int idTipoUsuario;
	private String pregunta;
	private String respuestaUsuario;
	private int idTipoEmpresa;
	private String razonSocial;
	private long idEmpresa;
	private long idCandidato;
	private DomicilioVO domicilio;
	private ConfirmacionVO confirmacion;
	private String telefono;
	
	public boolean isUrlvalido() {
		return urlvalido;
	}
	public void setUrlvalido(boolean urlvalido) {
		this.urlvalido = urlvalido;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public DomicilioVO getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(DomicilioVO domicilio) {
		this.domicilio = domicilio;
	}
	public int getIdDomicilio() {
		return idDomicilio;
	}
	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
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
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuestaUsuario() {
		return respuestaUsuario;
	}
	public void setRespuestaUsuario(String respuestaUsuario) {
		this.respuestaUsuario = respuestaUsuario;
	}
	public int getIdTipoEmpresa() {
		return idTipoEmpresa;
	}
	public void setIdTipoEmpresa(int idTipoEmpresa) {
		this.idTipoEmpresa = idTipoEmpresa;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public long getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
	public ConfirmacionVO getConfirmacion() {
		return confirmacion;
	}
	public void setConfirmacion(ConfirmacionVO confirmacion) {
		this.confirmacion = confirmacion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCorreocode() {
		return correocode;
	}
	public void setCorreocode(String correocode) {
		this.correocode = correocode;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}