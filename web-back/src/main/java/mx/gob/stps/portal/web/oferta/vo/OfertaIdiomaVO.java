package mx.gob.stps.portal.web.oferta.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaIdiomaVO implements Serializable {
	private static final long serialVersionUID = 2317067942248413442L;

	protected int idOfertaIdioma;

	protected int idOfertaEmpleo;

	protected int idIdioma;

	protected int idCertificacion;

	protected int idDominio;

	protected Date fechaAlta;

	protected int estatus;

	protected Date fechaModificacion;

	public int getIdOfertaIdioma() {
		return idOfertaIdioma;
	}

	
	public void setIdOfertaIdioma(int idOfertaIdioma) {
		this.idOfertaIdioma = idOfertaIdioma;
	}

	
	public int getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	
	public void setIdOfertaEmpleo(int idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	
	public int getIdIdioma() {
		return idIdioma;
	}

	
	public void setIdIdioma(int idIdioma) {
		this.idIdioma = idIdioma;
	}

	
	public int getIdCertificacion() {
		return idCertificacion;
	}

	
	public void setIdCertificacion(int idCertificacion) {
		this.idCertificacion = idCertificacion;
	}

	
	public int getIdDominio() {
		return idDominio;
	}

	
	public void setIdDominio(int idDominio) {
		this.idDominio = idDominio;
	}

	
	public Date getFechaAlta() {
		return fechaAlta;
	}

	
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	
	public int getEstatus() {
		return estatus;
	}

	
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

}
