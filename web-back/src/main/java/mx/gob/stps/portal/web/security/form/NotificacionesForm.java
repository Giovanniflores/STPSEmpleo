package mx.gob.stps.portal.web.security.form;

import org.apache.struts.action.ActionForm;

import org.apache.struts.upload.FormFile;

public class NotificacionesForm extends ActionForm {
	private static final long serialVersionUID = 3311444193009796384L;

	private String usuario;
	private FormFile archivoUsuarios;

	private String fileFlag;

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public FormFile getArchivoUsuarios() {
		return archivoUsuarios;
	}
	public void setArchivoUsuarios(FormFile archivoUsuarios) {
		this.archivoUsuarios = archivoUsuarios;
	}
	public String getFileFlag() {
		return fileFlag;
	}
	public void setFileFlag(String fileFlag) {
		this.fileFlag = fileFlag;
	}
}
