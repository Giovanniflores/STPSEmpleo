package mx.gob.stps.portal.web.security.form;

import org.apache.struts.action.ActionForm;

public class RecuperarPswForm extends ActionForm {
	private static final long serialVersionUID = 6379234899693778863L;
	private String curp;
	private String idPortalEmpleo;
	private String usuario;
	private String correoActual;
	private String correoNuevo;
	private String correoNuevoConfirma;
	private String tipo;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public String getIdPortalEmpleo() {
		return idPortalEmpleo;
	}
	public void setIdPortalEmpleo(String idPortalEmpleo) {
		this.idPortalEmpleo = idPortalEmpleo;
	}
	public String getCorreoActual() {
		return correoActual;
	}
	public void setCorreoActual(String correoActual) {
		this.correoActual = correoActual;
	}
	public String getCorreoNuevo() {
		return correoNuevo;
	}
	public void setCorreoNuevo(String correoNuevo) {
		this.correoNuevo = correoNuevo;
	}
	public String getCorreoNuevoConfirma() {
		return correoNuevoConfirma;
	}
	public void setCorreoNuevoConfirma(String correoNuevoConfirma) {
		this.correoNuevoConfirma = correoNuevoConfirma;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
}