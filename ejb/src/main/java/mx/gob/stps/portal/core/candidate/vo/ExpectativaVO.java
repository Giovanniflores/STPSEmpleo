/**
 * 
 */
package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

/**
 * @author Felipe Juárez Ramírez
 * @since 10/03/2011
 *
 */
public class ExpectativaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7013383317748277423L;
	/*Id del candidato y de usuario*/
	private long idCandidato;
	private long idUsuario;
	/*PERFIL_LABORAL*/
	private int disponibilidadViajar;
	private int disponibilidadRadicar;
	/*EXPECTATIVA_LABORAL*/
	private ExpectativaLaboralVO expLaboral;
	/*EXPECTATIVA_LUGAR*/
	private ExpectativaLugarVO expLugar;
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
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @return the disponibilidadViajar
	 */
	public int getDisponibilidadViajar() {
		return disponibilidadViajar;
	}
	/**
	 * @param disponibilidadViajar the disponibilidadViajar to set
	 */
	public void setDisponibilidadViajar(int disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}
	/**
	 * @return the disponibilidadRadicar
	 */
	public int getDisponibilidadRadicar() {
		return disponibilidadRadicar;
	}
	/**
	 * @param disponibilidadRadicar the disponibilidadRadicar to set
	 */
	public void setDisponibilidadRadicar(int disponibilidadRadicar) {
		this.disponibilidadRadicar = disponibilidadRadicar;
	}
	/**
	 * @return the expLaboral
	 */
	public ExpectativaLaboralVO getExpLaboral() {
		return expLaboral;
	}
	/**
	 * @param expLaboral the expLaboral to set
	 */
	public void setExpLaboral(ExpectativaLaboralVO expLaboral) {
		this.expLaboral = expLaboral;
	}
	/**
	 * @return the expLugar
	 */
	public ExpectativaLugarVO getExpLugar() {
		return expLugar;
	}
	/**
	 * @param expLugares the expLugares to set
	 */
	public void setExpLugar(ExpectativaLugarVO expLugar) {
		this.expLugar = expLugar;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExpectativaVO [idCandidato=" + idCandidato + ", idUsuario="
				+ idUsuario + ", disponibilidadViajar=" + disponibilidadViajar
				+ ", disponibilidadRadicar=" + disponibilidadRadicar
				+ ", expLaboral=" + expLaboral + ", expLugar=" + expLugar + "]";
	}
	public String toBitacora() {
		return "idCandidato=" + idCandidato + "|disponibilidadViajar="
				+ disponibilidadViajar + "|disponibilidadRadicar="
				+ disponibilidadRadicar + "|expLaboral=" + expLaboral.toBitacora()
				+ "|expLugar=" + expLugar.toBitacora();
	}
	
	
}
