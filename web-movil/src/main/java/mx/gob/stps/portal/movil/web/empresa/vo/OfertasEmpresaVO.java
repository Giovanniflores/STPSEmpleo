package mx.gob.stps.portal.movil.web.empresa.vo;

import java.io.Serializable;

import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;

public class OfertasEmpresaVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6063809750490275869L;
	private OfertaEmpleoVO ofertaEmpleoVO;
	private long idOfertaEmpleo;
	private String tituloOferta;
	private String ocupacion;
	private String nivelEstudios;
	private String carrera;
	private String ubicacion;
	private String fechaAlta;
	private int estatus;
	
	
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}
	public String getTituloOferta() {
		return tituloOferta;
	}
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getNivelEstudios() {
		return nivelEstudios;
	}
	public void setNivelEstudios(String nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	public int getEstatus() {
		return estatus;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public OfertaEmpleoVO getOfertaEmpleoVO() {
		return ofertaEmpleoVO;
	}
	public void setOfertaEmpleoVO(OfertaEmpleoVO ofertaEmpleoVO) {
		this.ofertaEmpleoVO = ofertaEmpleoVO;
	}
	
	
	
	

}
