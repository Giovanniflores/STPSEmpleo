package mx.gob.stps.portal.core.oferta.detalle.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaIdiomaVO implements Serializable{

	private static final long serialVersionUID = 3478230415330961206L;

	private long idOfertaIdioma;

	private long idOfertaEmpleo;

	private long idIdioma;

	private long idCertificacion;
	private String certificacion;
	
	private long idDominio;

	private Date fechaAlta;
	
	private int principal;

	/**
	 * Method 'OfertaIdiomaVO'
	 * 
	 */
	public OfertaIdiomaVO() {
	}

	/**
	 * Method 'getIdOfertaIdioma'
	 * 
	 * @return long
	 */
	public long getIdOfertaIdioma() {
		return idOfertaIdioma;
	}

	/**
	 * Method 'setIdOfertaIdioma'
	 * 
	 * @param idOfertaIdioma
	 */
	public void setIdOfertaIdioma(long idOfertaIdioma) {
		this.idOfertaIdioma = idOfertaIdioma;
	}

	/**
	 * Method 'getIdOfertaEmpleo'
	 * 
	 * @return long
	 */
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	/**
	 * Method 'setIdOfertaEmpleo'
	 * 
	 * @param idOfertaEmpleo
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	/**
	 * Method 'getIdIdioma'
	 * 
	 * @return long
	 */
	public long getIdIdioma() {
		return idIdioma;
	}

	/**
	 * Method 'setIdIdioma'
	 * 
	 * @param idIdioma
	 */
	public void setIdIdioma(long idIdioma) {
		this.idIdioma = idIdioma;
	}

	/**
	 * Method 'getIdCertificacion'
	 * 
	 * @return long
	 */
	public long getIdCertificacion() {
		return idCertificacion;
	}

	/**
	 * Method 'setIdCertificacion'
	 * 
	 * @param idCertificacion
	 */
	public void setIdCertificacion(long idCertificacion) {
		this.idCertificacion = idCertificacion;
	}

	/**
	 * Method 'getIdDominio'
	 * 
	 * @return long
	 */
	public long getIdDominio() {
		return idDominio;
	}

	/**
	 * Method 'setIdDominio'
	 * 
	 * @param idDominio
	 */
	public void setIdDominio(long idDominio) {
		this.idDominio = idDominio;
	}

	/**
	 * Method 'getFechaAlta'
	 * 
	 * @return Date
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Method 'setFechaAlta'
	 * 
	 * @param fechaAlta
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getCertificacion() {
		return certificacion;
	}

	public void setCertificacion(String certificacion) {
		this.certificacion = certificacion;
	}

	public int getPrincipal() {
		return principal;
	}

	public void setPrincipal(int principal) {
		this.principal = principal;
	}

}
