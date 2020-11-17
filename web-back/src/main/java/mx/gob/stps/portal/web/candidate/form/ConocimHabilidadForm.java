/**
 * 
 */
package mx.gob.stps.portal.web.candidate.form;

import java.util.Arrays;

import mx.gob.stps.portal.core.candidate.vo.ConocimientoHabilidadVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Felipe Juàrez Ramírez
 * @since 23/03/2011
 *
 */
public class ConocimHabilidadForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5842882397136390699L;
	
	/**
	 * Id del candidato
	 */
	private long idCandidato;
	/**
	 * numero de conocimientos o habilidades
	 */
	private int elementos;
	/**
	 * Consecutivo, se usara solo para borrado
	 */
	private long idCandidatoConocimHabilidad;
	/**
	 * Conocimiento o Habilidad agregado
	 **/
	private long idTipoConocimHabilidad;
	private String conocimientoHabilidad;
	private long idExperiencia;
	private long idDominio;
	private String descripcion;
	/**Mensaje*/
	private ResultVO msg;
	/**
	 * Conocimientos o Habilidades del candidato
	 **/
	private ConocimientoHabilidadVO[] conocimientosHabilidades;
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
	 * @return the conocimientosHabilidades
	 */
	public ConocimientoHabilidadVO[] getConocimientosHabilidades() {
		return conocimientosHabilidades;
	}
	/**
	 * @param conocimientosHabilidades the conocimientosHabilidades to set
	 */
	public void setConocimientosHabilidades(
			ConocimientoHabilidadVO[] conocimientosHabilidades) {
		this.conocimientosHabilidades = conocimientosHabilidades;
	}
	/**
	 * @return the conocimientosHabilidade
	 */
	public ConocimientoHabilidadVO getConocimientosHabilidade(int index) {
		return conocimientosHabilidades[index];
	}
	/**
	 * @param conocimientosHabilidades the conocimientosHabilidade to set
	 */
	public void setConocimientosHabilidade(int index,
			ConocimientoHabilidadVO conocimientosHabilidade) {
		this.conocimientosHabilidades[index] = conocimientosHabilidade;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("ConocimHabilidadForm [idCandidato=");
		builder.append(idCandidato);
		builder.append(", elementos=");
		builder.append(elementos);
		builder.append(", idCandidatoConocimHabilidad=");
		builder.append(idCandidatoConocimHabilidad);
		builder.append(", idTipoConocimHabilidad=");
		builder.append(idTipoConocimHabilidad);
		builder.append(", conocimientoHabilidad=");
		builder.append(conocimientoHabilidad);
		builder.append(", idExperiencia=");
		builder.append(idExperiencia);
		builder.append(", idDominio=");
		builder.append(idDominio);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", conocimientosHabilidades=");
		builder.append(conocimientosHabilidades != null ? Arrays.asList(
				conocimientosHabilidades).subList(0,
				Math.min(conocimientosHabilidades.length, maxLen)) : null);
		builder.append("]");
		return builder.toString();
	}
	
}
