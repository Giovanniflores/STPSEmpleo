/**
 * 
 */
package mx.gob.stps.portal.web.candidate.form;

import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Felipe Ju�rez Ram�rez
 * @since 10/03/2011
 *
 */
public class ExpectativaForm extends ActionForm {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = -5171078591655608710L;
	
	//Cambio para el ppc
	boolean ppc;
	
	/*Id's */
	private long idCandidato;
	private long idExpectativaLaboral;
	private long idExpectativaLugar;
	/**
	 * EXPECTATIVA_LABORAL PRINCIPAL
	 **/
	private long idSectorDeseado;
	private String puestoDeseado;
	private long idAreaLaboralDeseada;
	private long idOcupacionDeseada;
	private String salarioPretendido;
	private long idTipoEmpleoDeseado;
	private long idTipoContrato;
	/**
	 * EXPECTATIVA_LUGAR PRINCIPAL
	 **/
	private long idEntidadDeseada;
	private long idMunicipioDeseado;
	/*PERFIL_LABORAL*/
	private int disponibilidadViajar;
	private int disponibilidadRadicar;
	/*Mensaje*/
	private ResultVO msg;
	/**
	 * @return the idCandidato
	 **/
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
	 * @return the idExpectativaLaboral
	 */
	public long getIdExpectativaLaboral() {
		return idExpectativaLaboral;
	}
	/**
	 * @param idExpectativaLaboral the idExpectativaLaboral to set
	 */
	public void setIdExpectativaLaboral(long idExpectativaLaboral) {
		this.idExpectativaLaboral = idExpectativaLaboral;
	}
	/**
	 * @return the idExpectativaLugar
	 */
	public long getIdExpectativaLugar() {
		return idExpectativaLugar;
	}
	/**
	 * @param idExpectativaLugar the idExpectativaLugar to set
	 */
	public void setIdExpectativaLugar(long idExpectativaLugar) {
		this.idExpectativaLugar = idExpectativaLugar;
	}
	/**
	 * @return the idSectorDeseado
	 */
	public long getIdSectorDeseado() {
		return idSectorDeseado;
	}
	/**
	 * @param idSectorDeseado the idSectorDeseado to set
	 */
	public void setIdSectorDeseado(long idSectorDeseado) {
		this.idSectorDeseado = idSectorDeseado;
	}
	/**
	 * @return the puestoDeseado
	 */
	public String getPuestoDeseado() {
		return puestoDeseado;
	}
	/**
	 * @param puestoDeseado the puestoDeseado to set
	 */
	public void setPuestoDeseado(String puestoDeseado) {
		this.puestoDeseado = puestoDeseado;
	}
	/**
	 * @return the idAreaLaboralDeseada
	 */
	public long getIdAreaLaboralDeseada() {
		return idAreaLaboralDeseada;
	}
	/**
	 * @param idAreaLaboralDeseada the idAreaLaboralDeseada to set
	 */
	public void setIdAreaLaboralDeseada(long idAreaLaboralDeseada) {
		this.idAreaLaboralDeseada = idAreaLaboralDeseada;
	}
	/**
	 * @return the idOcupacionDeseada
	 */
	public long getIdOcupacionDeseada() {
		return idOcupacionDeseada;
	}
	/**
	 * @param idOcupacionDeseada the idOcupacionDeseada to set
	 */
	public void setIdOcupacionDeseada(long idOcupacionDeseada) {
		this.idOcupacionDeseada = idOcupacionDeseada;
	}
	/**
	 * @return the salarioPretendido
	 */
	public String getSalarioPretendido() {		
		return Utils.formatDecimal(salarioPretendido);
	}
	/**
	 * @param salarioPretendido the salarioPretendido to set
	 */
	public void setSalarioPretendido(String salarioPretendido) {
		this.salarioPretendido = salarioPretendido;
	}
	/**
	 * @return the idTipoEmpleoDeseado
	 */
	public long getIdTipoEmpleoDeseado() {
		return idTipoEmpleoDeseado;
	}
	/**
	 * @param idTipoEmpleoDeseado the idTipoEmpleoDeseado to set
	 */
	public void setIdTipoEmpleoDeseado(long idTipoEmpleoDeseado) {
		this.idTipoEmpleoDeseado = idTipoEmpleoDeseado;
	}
	/**
	 * @return the idTipoContrato
	 */
	public long getIdTipoContrato() {
		return idTipoContrato;
	}
	/**
	 * @param idTipoContrato the idTipoContrato to set
	 */
	public void setIdTipoContrato(long idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}
	/**
	 * @return the idEntidadDeseada
	 */
	public long getIdEntidadDeseada() {
		return idEntidadDeseada;
	}
	/**
	 * @param idEntidadDeseada the idEntidadDeseada to set
	 */
	public void setIdEntidadDeseada(long idEntidadDeseada) {
		this.idEntidadDeseada = idEntidadDeseada;
	}
	/**
	 * @return the idMunicipioDeseado
	 */
	public long getIdMunicipioDeseado() {
		return idMunicipioDeseado;
	}
	/**
	 * @param idMunicipioDeseado the idMunicipioDeseado to set
	 */
	public void setIdMunicipioDeseado(long idMunicipioDeseado) {
		this.idMunicipioDeseado = idMunicipioDeseado;
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
	public void setMsg(ResultVO msg) {
		this.msg = msg;
	}
	public ResultVO getMsg() {
		return msg;
	}
	
	
	public boolean isPpc() {
		return ppc;
	}
	public void setPpc(boolean ppc) {
		this.ppc = ppc;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExpectativaForm [idCandidato=");
		builder.append(idCandidato);
		builder.append(", idExpectativaLaboral=");
		builder.append(idExpectativaLaboral);
		builder.append(", idExpectativaLugar=");
		builder.append(idExpectativaLugar);
		builder.append(", idSectorDeseado=");
		builder.append(idSectorDeseado);
		builder.append(", puestoDeseado=");
		builder.append(puestoDeseado);
		builder.append(", idAreaLaboralDeseada=");
		builder.append(idAreaLaboralDeseada);
		builder.append(", idOcupacionDeseada=");
		builder.append(idOcupacionDeseada);
		builder.append(", salarioPretendido=");
		builder.append(salarioPretendido);
		builder.append(", idTipoEmpleoDeseado=");
		builder.append(idTipoEmpleoDeseado);
		builder.append(", idTipoContrato=");
		builder.append(idTipoContrato);
		builder.append(", idEntidadDeseada=");
		builder.append(idEntidadDeseada);
		builder.append(", idMunicipioDeseado=");
		builder.append(idMunicipioDeseado);
		builder.append(", disponibilidadViajar=");
		builder.append(disponibilidadViajar);
		builder.append(", disponibilidadRadicar=");
		builder.append(disponibilidadRadicar);
		builder.append("]");
		return builder.toString();
	}
	
}
