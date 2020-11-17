package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

public final class AccesoOLAVO implements Serializable {
	private static final long serialVersionUID = -3110467323597893921L;
	private String urlCarrera;
	private String urlOcupacion;
	private String tituloCarrera;
	private String tituloOcupacion;

	public String getUrlCarrera() {
		return urlCarrera;
	}
	public void setUrlCarrera(String urlCarrera) {
		this.urlCarrera = urlCarrera;
	}
	public String getUrlOcupacion() {
		return urlOcupacion;
	}
	public void setUrlOcupacion(String urlOcupacion) {
		this.urlOcupacion = urlOcupacion;
	}
	public String getTituloCarrera() {
		return tituloCarrera;
	}
	public void setTituloCarrera(String tituloCarrera) {
		this.tituloCarrera = tituloCarrera;
	}
	public String getTituloOcupacion() {
		return tituloOcupacion;
	}
	public void setTituloOcupacion(String tituloOcupacion) {
		this.tituloOcupacion = tituloOcupacion;
	}	
}