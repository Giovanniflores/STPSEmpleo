/**
 * 
 */
package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

/**
 * Objeto de valor que transporta una computacion avanzada
 * @author Felipe Juárez Ramírez
 * @since 08/03/2011
 *
 */
public class ComputacionAvanzadaVO implements Serializable {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = 780891525427452078L;
	
	private long idCandidatoCompuAvanzada;
	private String softwareHardware;
	private long idExperiencia;
	private String experiencia;
	private long idDominio;
	private String dominio;
	private String descripcion;
	private int principal;
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
	 * @param experiencia the experiencia to set
	 */
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}
	/**
	 * @return the experiencia
	 */
	public String getExperiencia() {
		return experiencia;
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
	 * @param dominio the dominio to set
	 */
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	/**
	 * @return the dominio
	 */
	public String getDominio() {
		return dominio;
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
	 * @param principal the principal to set
	 */
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	/**
	 * @return the principal
	 */
	public int getPrincipal() {
		return principal;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComputacionAvanzadaVO [idCandidatoCompuAvanzada="
				+ idCandidatoCompuAvanzada + ", softwareHardware="
				+ softwareHardware + ", idExperiencia=" + idExperiencia
				+ ", experiencia=" + experiencia + ", idDominio=" + idDominio
				+ ", dominio=" + dominio + ", descripcion=" + descripcion
				+ ", principal=" + principal + "]";
	}
	
	public String toBitacora() {
		return "idCandidatoCompuAvanzada=" + idCandidatoCompuAvanzada
				+ "|softwareHardware=" + softwareHardware + "|idExperiencia="
				+ idExperiencia + "|experiencia=" + experiencia + "|idDominio="
				+ idDominio + "|dominio=" + dominio + "|descripcion="
				+ descripcion + "|principal=" + principal;
	}
		
}
