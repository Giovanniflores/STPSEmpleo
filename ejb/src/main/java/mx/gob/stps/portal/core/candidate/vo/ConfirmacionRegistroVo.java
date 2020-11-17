/**
 * 
 */
package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

/**
 * @author jose.hernandez
 *
 */
public class ConfirmacionRegistroVo implements Serializable {
	private static final long serialVersionUID = -6156449320481345431L;
	
	public static final Integer TAMANO_CORRECTO	= 4;
	
	private long idPropietario;
	
	private int idTipoPropietario;
	
	private String usuario;
	private String password;
	
	private String correo;
	
	private String emailEmpresa;
	
	private String emailCandidato;
	
	private String emailMensaje;
	
	private String asunto;

	public long getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(long idPropietario) {
		this.idPropietario = idPropietario;
	}

	public int getIdTipoPropietario() {
		return idTipoPropietario;
	}

	public void setIdTipoPropietario(int idTipoPropietario) {
		this.idTipoPropietario = idTipoPropietario;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEmailEmpresa() {
		return emailEmpresa;
	}

	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}

	public String getEmailCandidato() {
		return emailCandidato;
	}

	public void setEmailCandidato(String emailCandidato) {
		this.emailCandidato = emailCandidato;
	}

	public String getEmailMensaje() {
		return emailMensaje;
	}

	public void setEmailMensaje(String emailMensaje) {
		this.emailMensaje = emailMensaje;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	@Override
	public String toString() {
		return "ConfirmacionRegistroVo [idPropietario=" + idPropietario
				+ ", idTipoPropietario=" + idTipoPropietario + ", password="
				+ password + ", correo=" + correo + ", emailEmpresa="
				+ emailEmpresa + ", emailCandidato=" + emailCandidato
				+ ", emailMensaje=" + emailMensaje + ", asunto=" + asunto + "]";
	}

}
