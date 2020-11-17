/**
 * 
 */
package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Objeto de valor que transporta los datos de experiencia laboral de un candidato.
 * @author Felipe Juárez Ramírez
 * @since 10/03/2011
 */
public class ExperienciaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7549866798198817753L;
	
	private boolean nuncaHeTrabajado;
	
	private long idUsuario;
	private long idCandidato;
	/*PERFIL_LABORAL*/
	private long idExperienciaTotal;
	protected String experiencia;
	private long idSectorMayorExpr;
	private String puestoMayorExpr;
	private long idAreaLaboralMayorExpr;
	private long idOcupacionMayorExpr;
	/*HISTORIA_LABORAL*/
	private HistoriaLaboralVO histLaboral;
	
	private List<HistoriaLaboralVO> histLaborales;
	
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
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
	 * @return the idExperienciaTotal
	 */
	public long getIdExperienciaTotal() {
		return idExperienciaTotal;
	}
	/**
	 * @param idExperienciaTotal the idExperienciaTotal to set
	 */
	public void setIdExperienciaTotal(long idExperienciaTotal) {
		this.idExperienciaTotal = idExperienciaTotal;
	}
	public String getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}
	/**
	 * @return the idSectorMayorExpr
	 */
	public long getIdSectorMayorExpr() {
		return idSectorMayorExpr;
	}
	/**
	 * @param idSectorMayorExpr the idSectorMayorExpr to set
	 */
	public void setIdSectorMayorExpr(long idSectorMayorExpr) {
		this.idSectorMayorExpr = idSectorMayorExpr;
	}
	/**
	 * @return the puestoMayorExpr
	 */
	public String getPuestoMayorExpr() {
		return puestoMayorExpr;
	}
	/**
	 * @param puestoMayorExpr the puestoMayorExpr to set
	 */
	public void setPuestoMayorExpr(String puestoMayorExpr) {
		this.puestoMayorExpr = puestoMayorExpr;
	}
	/**
	 * @return the idAreaLaboralMayorExpr
	 */
	public long getIdAreaLaboralMayorExpr() {
		return idAreaLaboralMayorExpr;
	}
	/**
	 * @param idAreaLaboralMayorExpr the idAreaLaboralMayorExpr to set
	 */
	public void setIdAreaLaboralMayorExpr(long idAreaLaboralMayorExpr) {
		this.idAreaLaboralMayorExpr = idAreaLaboralMayorExpr;
	}
	/**
	 * @return the idOcupacionMayorExpr
	 */
	public long getIdOcupacionMayorExpr() {
		return idOcupacionMayorExpr;
	}
	/**
	 * @param idOcupacionMayorExpr the idOcupacionMayorExpr to set
	 */
	public void setIdOcupacionMayorExpr(long idOcupacionMayorExpr) {
		this.idOcupacionMayorExpr = idOcupacionMayorExpr;
	}
	/**
	 * @return the histLaborales
	 */
	public HistoriaLaboralVO getHistLaboral() {
		return histLaboral;
	}
	/**
	 * @param histLaborales the histLaborales to set
	 */
	public void setHistLaboral(HistoriaLaboralVO histLaboral) {
		this.histLaboral = histLaboral;
	}
	
	
	
	public List<HistoriaLaboralVO> getHistLaborales() {
		return histLaborales;
	}
	public void setHistLaborales(List<HistoriaLaboralVO> histLaborales) {
		this.histLaborales = histLaborales;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "idUsuario=" + idUsuario + "|idCandidato=" + idCandidato
				+ "|idExperienciaTotal=" + idExperienciaTotal
				+ "|idSectorMayorExpr=" + idSectorMayorExpr
				+ "|puestoMayorExpr=" + puestoMayorExpr
				+ "|idAreaLaboralMayorExpr=" + idAreaLaboralMayorExpr
				+ "|idOcupacionMayorExpr=" + idOcupacionMayorExpr
				+ "|histLaboral=" + histLaboral;
	}
	/**
	 * @param nuncaHeTrabajado the nuncaHeTrabajado to set
	 */
	public void setNuncaHeTrabajado(boolean nuncaHeTrabajado) {
		this.nuncaHeTrabajado = nuncaHeTrabajado;
	}
	/**
	 * @return the nuncaHeTrabajado
	 */
	public boolean isNuncaHeTrabajado() {
		return nuncaHeTrabajado;
	}
	public String toBitacora() {
		return "idCandidato=" + idCandidato + "|idExperienciaTotal="
				+ idExperienciaTotal + "|idSectorMayorExpr="
				+ idSectorMayorExpr + "|puestoMayorExpr=" + puestoMayorExpr
				+ "|idAreaLaboralMayorExpr=" + idAreaLaboralMayorExpr
				+ "|idOcupacionMayorExpr=" + idOcupacionMayorExpr
				+ "|histLaboral=" + histLaboral.toBitacora();
	}
	
}
