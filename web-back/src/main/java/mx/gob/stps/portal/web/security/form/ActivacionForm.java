package mx.gob.stps.portal.web.security.form;

import mx.gob.stps.portal.core.seguridad.vo.ConfirmacionVO;

import org.apache.struts.action.ActionForm;

public class ActivacionForm extends ActionForm {
	private static final long serialVersionUID = 5828250531518217423L;

	private String curp;
	private String usuario;
	
	private int dia;
	private int mes;
	private int anio;
	
	private int idTipoUsuario;
	
	private String pregunta;
	
	private String respuestaUsuario;
	
	private ConfirmacionVO confirmacion;
	
	private int idTipoEmpresa;
	private String razonSocial;
	private String etiquetaEmpresaPublica;
	private String etiquetaEmpresaMoral;
	private String etiquetaEmpresaOng;	
	
	private long idEmpresa;
	private long idCandidato;

	public ConfirmacionVO getConfirmacion() {
		return confirmacion;
	}
	public void setConfirmacion(ConfirmacionVO confirmacion) {
		this.confirmacion = confirmacion;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	public String getEtiquetaEmpresaPublica() {
		return etiquetaEmpresaPublica;
	}
	public void setEtiquetaEmpresaPublica(String etiquetaEmpresaPublica) {
		this.etiquetaEmpresaPublica = etiquetaEmpresaPublica;
	}
	public String getEtiquetaEmpresaMoral() {
		return etiquetaEmpresaMoral;
	}
	public void setEtiquetaEmpresaMoral(String etiquetaEmpresaMoral) {
		this.etiquetaEmpresaMoral = etiquetaEmpresaMoral;
	}
	public String getEtiquetaEmpresaOng() {
		return etiquetaEmpresaOng;
	}
	public void setEtiquetaEmpresaOng(String etiquetaEmpresaOng) {
		this.etiquetaEmpresaOng = etiquetaEmpresaOng;
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
	
	
	
}
