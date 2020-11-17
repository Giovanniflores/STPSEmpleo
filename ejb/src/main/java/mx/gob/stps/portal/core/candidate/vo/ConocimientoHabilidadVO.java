/**
 * 
 */
package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

import mx.gob.stps.portal.utils.Catalogos.EXPERIENCIA;

/**
 * Objeto de valor que transporta un conocimiento o una habilidad.
 * @author Felipe Juárez Ramírez
 * @since 08/03/2011
 */
public class ConocimientoHabilidadVO implements Serializable {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = 8156401154232880423L;
	
	private long idCandidato;
	private long idCandidatoConocimHabilidad;
	private long idTipoConocimHabilidad;
	private String conocimientoHabilidad;
	private long idExperiencia;
	private String experiencia;
	private long idDominio;
	private String dominio;
	private String descripcion;
	private int principal;
	private int row;
	/**
	 * @return the idCandidatoConocimHabilidad
	 */
	public long getIdCandidatoConocimHabilidad() {
		return idCandidatoConocimHabilidad;
	}
	/**
	 * @param idCandidatoConocimHabilidad the idCandidatoConocimHabilidad to set
	 */
	public void setIdCandidatoConocimHabilidad(long idCandidatoConocimHabilidad) {
		this.idCandidatoConocimHabilidad = idCandidatoConocimHabilidad;
	}
	/**
	 * @return the idTipoConocimHabilidad
	 */
	public long getIdTipoConocimHabilidad() {
		return idTipoConocimHabilidad;
	}
	/**
	 * @param idTipoConocimHabilidad the idTipoConocimHabilidad to set
	 */
	public void setIdTipoConocimHabilidad(long idTipoConocimHabilidad) {
		this.idTipoConocimHabilidad = idTipoConocimHabilidad;
	}
	/**
	 * @return the conocimientoHabilidad
	 */
	public String getConocimientoHabilidad() {
		return conocimientoHabilidad;
	}
	/**
	 * @param conocimientoHabilidad the conocimientoHabilidad to set
	 */
	public void setConocimientoHabilidad(String conocimientoHabilidad) {
		this.conocimientoHabilidad = conocimientoHabilidad;
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
		this.experiencia = EXPERIENCIA.getDescripcion((int)this.idExperiencia);
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
	 * @return the principal
	 */
	public int getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConocimientoHabilidadVO [idCandidatoConocimHabilidad="
				+ idCandidatoConocimHabilidad + ", idTipoConocimHabilidad="
				+ idTipoConocimHabilidad + ", conocimientoHabilidad="
				+ conocimientoHabilidad + ", idExperiencia=" + idExperiencia
				+ ", experiencia=" + experiencia + ", idDominio=" + idDominio
				+ ", dominio=" + dominio + ", descripcion=" + descripcion
				+ ", principal=" + principal + "]";
	}
	public String toBitacora() {
		return "idCandidatoConocimHabilidad=" + idCandidatoConocimHabilidad
				+ "|idTipoConocimHabilidad=" + idTipoConocimHabilidad
				+ "|conocimientoHabilidad=" + conocimientoHabilidad
				+ "|idExperiencia=" + idExperiencia + "|experiencia="
				+ experiencia + "|idDominio=" + idDominio + "|dominio="
				+ dominio + "|descripcion=" + descripcion + "|principal="
				+ principal;
	}
	public long getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}		
		
}
