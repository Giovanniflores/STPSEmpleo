/**
 * 
 */
package mx.gob.stps.portal.web.candidate.form;

import java.util.Arrays;

import mx.gob.stps.portal.core.candidate.vo.GradoAcademicoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Felipe Juàrez Ramìrez
 * @since 18/03/2011
 * 
 */
public class GradoAcademicoForm extends ActionForm {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = 8816695803862920588L;
	/**
	 * Id del candidato
	 */
	private long idCandidato;
	/**
	 * numero de grados academicos
	 */
	private int elementos;
	/**
	 * Consecutivo, se usara solo para borrado
	 */
	private long idCandidatoGradoAcademico;
	/**
	 * Grado academico agregado
	 */
	private long idNivelEstudio;
	private long idSituacionAcademica;
	private long idCarreraEspecialidad;
	private String escuela;
	private int inicio;
	private int fin;
	/*Mensaje*/
	private ResultVO msg;
	/**
	 * Grados academicos del candidato
	 */
	private GradoAcademicoVO[] grados;
	/**
	 * Metodo constructor
	 */
	public GradoAcademicoForm() {
		
	}
	
	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}

	/**
	 * @param idCandidato
	 *            the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	/**
	 * @param elementos the elementos to set
	 */
	public void setElementos(int elementos) {
		this.elementos = elementos;
	}
	/**
	 * @param idCandidatoGradoAcademico the idCandidatoGradoAcademico to set
	 */
	public void setIdCandidatoGradoAcademico(long idCandidatoGradoAcademico) {
		this.idCandidatoGradoAcademico = idCandidatoGradoAcademico;
	}

	/**
	 * @return the idCandidatoGradoAcademico
	 */
	public long getIdCandidatoGradoAcademico() {
		return idCandidatoGradoAcademico;
	}

	/**
	 * @param idNivelEstudio the idNivelEstudio to set
	 */
	public void setIdNivelEstudio(long idNivelEstudio) {
		this.idNivelEstudio = idNivelEstudio;
	}

	/**
	 * @return the idNivelEstudio
	 */
	public long getIdNivelEstudio() {
		return idNivelEstudio;
	}

	/**
	 * @param idSituacionAcademica the idSituacionAcademica to set
	 */
	public void setIdSituacionAcademica(long idSituacionAcademica) {
		this.idSituacionAcademica = idSituacionAcademica;
	}

	/**
	 * @return the idSituacionAcademica
	 */
	public long getIdSituacionAcademica() {
		return idSituacionAcademica;
	}

	/**
	 * @param idCarreraEspecialidad the idCarreraEspecialidad to set
	 */
	public void setIdCarreraEspecialidad(long idCarreraEspecialidad) {
		this.idCarreraEspecialidad = idCarreraEspecialidad;
	}

	/**
	 * @return the idCarreraEspecialidad
	 */
	public long getIdCarreraEspecialidad() {
		return idCarreraEspecialidad;
	}

	/**
	 * @param escuela the escuela to set
	 */
	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}

	/**
	 * @return the escuela
	 */
	public String getEscuela() {
		return escuela;
	}

	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	/**
	 * @return the inicio
	 */
	public int getInicio() {
		return inicio;
	}

	/**
	 * @param fin the fin to set
	 */
	public void setFin(int fin) {
		this.fin = fin;
	}

	/**
	 * @return the fin
	 */
	public int getFin() {
		return fin;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(ResultVO msg) {
		this.msg = msg;
	}

	/**
	 * @return the msg
	 */
	public ResultVO getMsg() {
		return msg;
	}

	/**
	 * @return the grados
	 */
	public GradoAcademicoVO[] getGrados() {
		return grados;
	}
	/**
	 * @return the grados
	 */
	public int getElementos() {
		if (this.grados != null)
			this.elementos = this.grados.length;
		return elementos;
	}
	/**
	 * @param grados
	 *            the grados to set
	 */
	public void setGrado(int index, GradoAcademicoVO grado) {
		this.grados[index] = grado;
	}
	
	/**
	 * @return the grados
	 */
	public GradoAcademicoVO getGrado(int index) {
		return grados[index];
	}

	/**
	 * @param grados
	 *            the grados to set
	 */
	public void setGrados(GradoAcademicoVO[] grados) {
		this.grados = grados;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("GradoAcademicoForm [idCandidato=");
		builder.append(idCandidato);
		builder.append(", elementos=");
		builder.append(elementos);
		builder.append(", idCandidatoGradoAcademico=");
		builder.append(idCandidatoGradoAcademico);
		builder.append(", idNivelEstudio=");
		builder.append(idNivelEstudio);
		builder.append(", idSituacionAcademica=");
		builder.append(idSituacionAcademica);
		builder.append(", idCarreraEspecialidad=");
		builder.append(idCarreraEspecialidad);
		builder.append(", escuela=");
		builder.append(escuela);
		builder.append(", inicio=");
		builder.append(inicio);
		builder.append(", fin=");
		builder.append(fin);
		builder.append(", grados=");
		builder.append(grados != null ? Arrays.asList(grados).subList(0,
				Math.min(grados.length, maxLen)) : null);
		builder.append("]");
		return builder.toString();
	}
	
}
