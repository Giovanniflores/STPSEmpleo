package mx.gob.stps.portal.web.security.form;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

public class RecomendarForm extends ActionForm{
	/**
	 * logger
	 */
	private static Logger logger = Logger.getLogger(RecomendarForm.class);
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String txtFromName;
	private String txtFromEmail;
	private String txtToName;
	private String txtToEmail;
	private String tarMsg;
	
	private String pregunta;
	private String respuestaUsuario;
	
	/* Remitente */
	public String getTxtFromName() {
		return txtFromName;
	}
	public void setTxtFromName(String txtFromName) {
		this.txtFromName = txtFromName;
	}
	/* Correo Remitente */
	public String getTxtFromEmail() {
		return txtFromEmail;
	}
	public void setTxtFromEmail(String txtFromEmail) {
		this.txtFromEmail = txtFromEmail;
	}
	/* Destinatario */	
	public String getTxtToName() {
		return txtToName;
	}
	public void setTxtToName(String txtToName) {
		this.txtToName = txtToName;
	}
	/* Correo Destinatario */	
	public String getTxtToEmail() {
		return txtToEmail;
	}
	public void setTxtToEmail(String txtToEmail) {
		this.txtToEmail = txtToEmail;
	}
	/* Mensaje Adicional */
	public String getTarMsg() {
		return tarMsg;
	}
	public void setTarMsg(String tarMsg) {
		this.tarMsg = tarMsg;
	}
	
	/* Capcha */
	
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
	
}
