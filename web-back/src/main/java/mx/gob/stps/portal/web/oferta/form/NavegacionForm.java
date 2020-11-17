package mx.gob.stps.portal.web.oferta.form;

import org.apache.struts.action.ActionForm;

public class NavegacionForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5349730208883114796L;
	
	private int ultimaOfertaMostrada;
	
	private int totalOfertasListadas;
	
	private long folio;
	
	private String titulo;
	
	public int getUltimaOfertaMostrada() {
		return ultimaOfertaMostrada;
	}
	public void setUltimaOfertaMostrada(int ultimaOfertaMostrada) {
		this.ultimaOfertaMostrada = ultimaOfertaMostrada;
	}
	public int getTotalOfertasListadas() {
		return totalOfertasListadas;
	}
	public void setTotalOfertasListadas(int totalOfertasListadas) {
		this.totalOfertasListadas = totalOfertasListadas;
	}
	public long getFolio() {
		return folio;
	}
	public void setFolio(long folio) {
		this.folio = folio;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	
	
}
