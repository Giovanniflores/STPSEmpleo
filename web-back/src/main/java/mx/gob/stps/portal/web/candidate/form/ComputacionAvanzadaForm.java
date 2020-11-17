/**
 * 
 */
package mx.gob.stps.portal.web.candidate.form;

import java.util.Arrays;

import mx.gob.stps.portal.core.candidate.vo.ComputacionAvanzadaVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Felipe Juárez Ramírez
 * @since 24/03/2011
 */
public class ComputacionAvanzadaForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3704618355239280888L;
	/**
	 * Id del candidato
	 */
	private long idCandidato;
	/**
	 * numero de computacion avanzada
	 */
	private int elementos;
	/**
	 * Consecutivo, se usara solo para borrado
	 */
	private long idCandidatoCompuAvanzada;
	/**
	 * Computacion avanzada agregada
	 **/
	private String softwareHardware;
	private long idExperiencia;
	private long idDominio;
	private String descripcion;
	private ResultVO msg;
	/**
	 * Computacion avanzada del candidato
	 **/
	private ComputacionAvanzadaVO[] avanzadas;
	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}
	/**
	 * @param idCandidato the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
	/**
	 * @return the elementos
	 */
	public int getElementos() {
		return elementos;
	}
	/**
	 * @param elementos the elementos to set
	 */
	public void setElementos(int elementos) {
		this.elementos = elementos;
	}
	/**
	 * @return the idCandidatoCompuAvanzada
	 */
	public long getIdCandidatoCompuAvanzada() {
		return idCandidatoCompuAvanzada;
	}
	/**
	 * @param idCandidatoCompuAvanzada the idCandidatoCompuAvanzada to set
	 */
	public void setIdCandidatoCompuAvanzada(long idCandidatoCompuAvanzada) {
		this.idCandidatoCompuAvanzada = idCandidatoCompuAvanzada;
	}
	/**
	 * @return the softwareHardware
	 */
	public String getSoftwareHardware() {
		return softwareHardware;
	}
	/**
	 * @param softwareHardware the softwareHardware to set
	 */
	public void setSoftwareHardware(String softwareHardware) {
		this.softwareHardware = softwareHardware;
	}
	/**
	 * @return the idExperiencia
	 */
	public long getIdExperiencia() {
		return idExperiencia;
	}
	/**
	 * @param idExperiencia the idExperiencia to set
	 */
	public void setIdExperiencia(long idExperiencia) {
		this.idExperiencia = idExperiencia;
	}
	/**
	 * @return the idDominio
	 */
	public long getIdDominio() {
		return idDominio;
	}
	/**
	 * @param idDominio the idDominio to set
	 */
	public void setIdDominio(long idDominio) {
		this.idDominio = idDominio;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * @return the avanzadas
	 */
	public ComputacionAvanzadaVO[] getAvanzadas() {
		return avanzadas;
	}
	/**
	 * @param avanzadas the avanzadas to set
	 */
	public void setAvanzadas(ComputacionAvanzadaVO[] avanzadas) {
		this.avanzadas = avanzadas;
	}
	/**
	 * @return the avanzada
	 */
	public ComputacionAvanzadaVO getAvanzada(int index) {
		return avanzadas[index];
	}
	/**
	 * @param avanzada the avanzada to set
	 */
	public void setAvanzada(int index, ComputacionAvanzadaVO avanzada) {
		this.avanzadas[index] = avanzada;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("ComputacionAvanzadaForm [idCandidato=");
		builder.append(idCandidato);
		builder.append(", elementos=");
		builder.append(elementos);
		builder.append(", idCandidatoCompuAvanzada=");
		builder.append(idCandidatoCompuAvanzada);
		builder.append(", softwareHardware=");
		builder.append(softwareHardware);
		builder.append(", idExperiencia=");
		builder.append(idExperiencia);
		builder.append(", idDominio=");
		builder.append(idDominio);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", avanzadas=");
		builder.append(avanzadas != null ? Arrays.asList(avanzadas).subList(0,
				Math.min(avanzadas.length, maxLen)) : null);
		builder.append("]");
		return builder.toString();
	}
	
}
