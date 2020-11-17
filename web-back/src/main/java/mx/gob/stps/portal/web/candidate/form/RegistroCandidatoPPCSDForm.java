package mx.gob.stps.portal.web.candidate.form;

import org.apache.struts.action.ActionForm;

public class RegistroCandidatoPPCSDForm extends ActionForm{

	private static final long serialVersionUID = 4025195857932842010L;
	
	private long idCandidato;
	private String curp;
	private boolean editCurp = true;
	private String nss;
	private String aceptacionTerminos;

	private String pregunta;	
	private String respuestaUsuario;

	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getCurp() {
		return curp;
	}
	
	public void setCurp(String curp) {
		this.curp = curp;
	}

	public boolean isEditCurp() {
		return editCurp;
	}

	public void setEditCurp(boolean editCurp) {
		this.editCurp = editCurp;
	}

	public String getNss() {
		return nss;
	}
	
	public void setNss(String nss) {
		this.nss = nss;
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

	public String getAceptacionTerminos() {
		return aceptacionTerminos;
	}

	public void setAceptacionTerminos(String aceptacionTerminos) {
		this.aceptacionTerminos = aceptacionTerminos;
	}

}
