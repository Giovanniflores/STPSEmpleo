/**
 * 
 */
package mx.gob.stps.portal.web.candidate.form;

import mx.gob.stps.portal.core.candidate.vo.ExpectativaLugarVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Felipe Juárez Ramírez
 * @since 27/03/2011
 *
 */
public class ExpecLugarForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7128863897689246726L;
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
	private long idExpectativaLugar;
	/**
	 * Detalle de expectativa lugar agregado
	 **/
	private long idEntidadDeseada;
	private long idMunicipioDeseado;
	/**Mensaje*/
	private ResultVO msg;
	/**
	 * Expectativas laborales del candidato
	 **/
	private ExpectativaLugarVO[] expecLugares;
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
	 * @return the expecLugares
	 */
	public ExpectativaLugarVO[] getExpecLugares() {
		return expecLugares;
	}
	/**
	 * @param expecLugares the expecLugares to set
	 */
	public void setExpecLugares(ExpectativaLugarVO[] expecLugares) {
		this.expecLugares = expecLugares;
	}
	/**
	 * @return the expecLugare
	 */
	public ExpectativaLugarVO getExpecLugare(int index) {
		return expecLugares[index];
	}
	/**
	 * @param expecLugare the expecLugare to set
	 */
	public void setExpecLugare(int index, ExpectativaLugarVO expecLugar) {
		this.expecLugares[index] = expecLugar;
	}
	
}
