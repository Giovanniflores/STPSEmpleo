package mx.gob.stps.portal.core.oferta.registro.vo;

import java.io.Serializable;



public class OfertaIdiomaVO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1774843224999184862L;

	private long idRegistro;
	
	private long idOfertaEmpleo;

	private long idIdioma;

	private long idCertificacion;

	private long idDominio;

	private int principal;
	
	
	
	public int getPrincipal() {
		return principal;
	}

	public void setPrincipal(int principal) {
		this.principal = principal;
	}

	public long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public long getIdIdioma() {
		return idIdioma;
	}

	public void setIdIdioma(long idIdioma) {
		this.idIdioma = idIdioma;
	}

	public long getIdCertificacion() {
		return idCertificacion;
	}

	public void setIdCertificacion(long idCertificacion) {
		this.idCertificacion = idCertificacion;
	}

	public long getIdDominio() {
		return idDominio;
	}

	public void setIdDominio(long idDominio) {
		this.idDominio = idDominio;
	}

	public String toString(){
		return "OfertaIdiomaVO [idRegistro=" + idRegistro
				+ ", idOfertaEmpleo=" + idOfertaEmpleo 
				+ ", idIdioma=" + idIdioma
				+ ", idCertificacion=" + idCertificacion 
				+ ", idDominio=" + idDominio
				+ ", principal=" + principal + "]";		
	}
	
	
}
