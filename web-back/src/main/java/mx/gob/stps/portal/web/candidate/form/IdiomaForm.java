/**
 * 
 */
package mx.gob.stps.portal.web.candidate.form;

import java.util.Arrays;

import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Felipe Juàrez Ramìrez
 * @since 22/03/2011
 *
 */
public class IdiomaForm extends ActionForm {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = 7726139252433669956L;
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
	private long idCandidatoIdioma;
	/**
	 * Idioma agregado
	 */
	private long idIdioma;
	private long idCertificacion;
	private long idDominio;
	/**Mensaje*/
	private ResultVO msg;
	/**
	 * Idiomas del candidato
	 */
	private IdiomaVO[] idiomas;
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
	 * @return the idCandidatoIdioma
	 */
	public long getIdCandidatoIdioma() {
		return idCandidatoIdioma;
	}
	/**
	 * @param idCandidatoIdioma the idCandidatoIdioma to set
	 */
	public void setIdCandidatoIdioma(long idCandidatoIdioma) {
		this.idCandidatoIdioma = idCandidatoIdioma;
	}
	/**
	 * @return the idIdioma
	 */
	public long getIdIdioma() {
		return idIdioma;
	}
	/**
	 * @param idIdioma the idIdioma to set
	 */
	public void setIdIdioma(long idIdioma) {
		this.idIdioma = idIdioma;
	}
	/**
	 * @return the idCertificacion
	 */
	public long getIdCertificacion() {
		return idCertificacion;
	}
	/**
	 * @param idCertificacion the idCertificacion to set
	 */
	public void setIdCertificacion(long idCertificacion) {
		this.idCertificacion = idCertificacion;
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
	 * @return the idiomas
	 */
	public IdiomaVO[] getIdiomas() {
		return idiomas;
	}
	/**
	 * @param idiomas the idiomas to set
	 */
	public void setIdiomas(IdiomaVO[] idiomas) {
		this.idiomas = idiomas;
	}
	/**
	 * @return the idioma
	 */
	public IdiomaVO getIdioma(int index) {
		return idiomas[index];
	}
	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(int index, IdiomaVO idioma) {
		this.idiomas[index] = idioma;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("IdiomaForm [idCandidato=");
		builder.append(idCandidato);
		builder.append(", elementos=");
		builder.append(elementos);
		builder.append(", idCandidatoIdioma=");
		builder.append(idCandidatoIdioma);
		builder.append(", idIdioma=");
		builder.append(idIdioma);
		builder.append(", idCertificacion=");
		builder.append(idCertificacion);
		builder.append(", idDominio=");
		builder.append(idDominio);
		builder.append(", idiomas=");
		builder.append(idiomas != null ? Arrays.asList(idiomas).subList(0,
				Math.min(idiomas.length, maxLen)) : null);
		builder.append("]");
		return builder.toString();
	}
	
}
