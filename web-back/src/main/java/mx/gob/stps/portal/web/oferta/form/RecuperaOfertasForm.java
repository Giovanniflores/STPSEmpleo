package mx.gob.stps.portal.web.oferta.form;

import org.apache.struts.action.ActionForm;

public class RecuperaOfertasForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1673061315572242172L;
	private String fechaDe;
	private String fechaA;
	private String folio;
	private String titulo;
	private int ultimaOfertaMostrada;
	private int totalOfertasListadas;

	
	
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

	public String getFechaDe() {
		return fechaDe;
	}

	public void setFechaDe(String fechaDe) {
		this.fechaDe = fechaDe;
	}

	public String getFechaA() {
		return fechaA;
	}

	public void setFechaA(String fechaA) {
		this.fechaA = fechaA;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
