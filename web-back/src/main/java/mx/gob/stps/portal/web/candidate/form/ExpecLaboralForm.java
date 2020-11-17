/**
 * 
 */
package mx.gob.stps.portal.web.candidate.form;

import mx.gob.stps.portal.core.candidate.vo.ExpectativaLaboralVO;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Felipe
 *
 */
public class ExpecLaboralForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8578930911089672122L;
	/**
	 * Id del candidato
	 */
	private long idCandidato;
	/**
	 * numero de detalle puesto
	 */
	private int elementos;
	/**
	 * Consecutivo, se usara solo para borrado
	 */
	private long idExpectativaLaboral;
	/**
	 * Detalle de expectativa laboral agregado
	 **/
	private long idSectorDeseado;
	private String puestoDeseado;
	private long idAreaLaboralDeseada;
	private long idOcupacionDeseada;
	private String salarioPretendido;
	private long idTipoEmpleoDeseado;
	private long idTipoContrato;
	/**Mensaje*/
	private ResultVO msg;
	/**
	 * Expectativas laborales del candidato
	 **/
	private ExpectativaLaboralVO[] expecLaborales;
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
		return Utils.formatMoney(salarioPretendido);
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
	 * @return the expecLaborales
	 */
	public ExpectativaLaboralVO[] getExpecLaborales() {
		return expecLaborales;
	}
	/**
	 * @param expecLaborales the expecLaborales to set
	 */
	public void setExpecLaborales(ExpectativaLaboralVO[] expecLaborales) {
		this.expecLaborales = expecLaborales;
	}
	/**
	 * @return the expecLaborale
	 */
	public ExpectativaLaboralVO getExpecLaborale(int index) {
		return expecLaborales[index];
	}
	/**
	 * @param expecLaborale the expecLaborale to set
	 */
	public void setExpecLaborale(int index, ExpectativaLaboralVO expecLaborale) {
		this.expecLaborales[index] = expecLaborale;
	}
	
}
