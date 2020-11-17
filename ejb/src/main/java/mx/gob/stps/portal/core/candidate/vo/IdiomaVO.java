package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Objeto de valor que transporta un idioma
 * @author Felipe Juárez Ramírez
 * @since 03/08/2011 
 **/
public class IdiomaVO implements Serializable {

	/**
	 * @serialField 
	 */
	private static final long serialVersionUID = 7214585615445104264L;
	
	private long idCandidatoIdioma;
	private long idCandidato;
	private long idIdioma;
	private String idioma;
	private long idCertificacion;
	private String certificacion;
	private long idDominio;
	private String dominio;
	private int principal;
	private int idDominioHabla;
	private int idDominioEscrito;
	private int idDominioLectura;
	private int idDominioComprension;
	private int examenPresentado;
	private int examenId;
	private int examenPuntos;
	private Date examenFecha;
	
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
	 * @param idioma the idioma to set
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	/**
	 * @return the idioma
	 */
	public String getIdioma() {
		return idioma;
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
	 * @param certificacion the certificacion to set
	 */
	public void setCertificacion(String certificacion) {
		this.certificacion = certificacion;
	}
	/**
	 * @return the certificacion
	 */
	public String getCertificacion() {
		return certificacion;
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
	
	public int getIdDominioHabla() {
		return idDominioHabla;
	}
	
	public void setIdDominioHabla(int idDominioHabla) {
		this.idDominioHabla = idDominioHabla;
	}
	
	public int getIdDominioLectura() {
		return idDominioLectura;
	}
	
	public void setIdDominioLectura(int idDominioLectura) {
		this.idDominioLectura = idDominioLectura;
	}
	
	public int getIdDominioEscrito() {
		return idDominioEscrito;
	}
	
	public void setIdDominioEscrito(int idDominioEscrito) {
		this.idDominioEscrito = idDominioEscrito;
	}
	
	public int getIdDominioComprension() {
		return idDominioComprension;
	}
	
	public void setIdDominioComprension(int idDominioComprension) {
		this.idDominioComprension = idDominioComprension;
	}
	
	public String toBitacora() {
		return "idCandidatoIdioma=" + idCandidatoIdioma + "|idIdioma="
				+ idIdioma + "|idioma=" + idioma + "|idCertificacion="
				+ idCertificacion + "|certificacion=" + certificacion
				+ "|idDominio=" + idDominio + "|dominio=" + dominio
				+ "|principal=" + principal;
	}
	public long getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
	public int getExamenPresentado() {
		return examenPresentado;
	}
	public void setExamenPresentado(int examenPresentado) {
		this.examenPresentado = examenPresentado;
	}
	
	public int getExamenId() {
		return examenId;
	}
	
	public void setExamenId(int examenId) {
		this.examenId = examenId;
	}
	
	public int getExamenPuntos() {
		return examenPuntos;
	}
	
	public void setExamenPuntos(int examenPuntos) {
		this.examenPuntos = examenPuntos;
	}
	
	public Date getExamenFecha() {
		return examenFecha;
	}
	
	public void setExamenFecha(Date examenFecha) {
		this.examenFecha = examenFecha;
	}
	
	@Override
	public String toString() {
		return "IdiomaVO [idCandidatoIdioma=" + idCandidatoIdioma
				+ ", idCandidato=" + idCandidato + ", idCertificacion="
				+ idCertificacion + ", idDominio=" + idDominio + ", principal="
				+ principal + ", idDominioHabla=" + idDominioHabla
				+ ", idDominioEscrito=" + idDominioEscrito
				+ ", idDominioLectura=" + idDominioLectura
				+ ", examenPresentado=" + examenPresentado + ", examenId="
				+ examenId + ", examenPuntos=" + examenPuntos
				+ ", examenFecha=" + examenFecha + "]";
	}
}
