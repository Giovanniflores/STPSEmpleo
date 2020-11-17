package mx.gob.stps.portal.movil.app.model.base;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.movil.web.oferta.form.OfertaJB;

public class BusquedaOfertaDTO {

	public BusquedaOfertaDTO(OfertaJB oferta) {
		this.oferta = oferta;
	}
	public BusquedaOfertaDTO(){
		this.oferta = new OfertaJB();
	}

	private OfertaJB oferta;
	private DomicilioVO domicilio;

	public DomicilioVO getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(DomicilioVO domicilio) {
		this.domicilio = domicilio;
	}
	public OfertaJB getOferta() {
		return oferta;
	}

	public void setOferta(OfertaJB oferta) {
		this.oferta = oferta;
	}

	private int compatibilidad;

	private String idEstatusPostulated;

	private String fechaAlta;
	
	private String idPostulated = "0";
	
	private String postulated;
	
	private Boolean enTiempo;
	
	private List<PostulatedDTO> listPostulated = new ArrayList<PostulatedDTO>();

	private String fechaModificacion;
	private String idOferta;
	
	private String fechaPublicacion;
	
	
	public int getCompatibilidad() {
		return compatibilidad;
	}

	
	
	public void setCompatibilidad(int compatibilidad) {
		this.compatibilidad = compatibilidad;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		// Just facking
		this.fechaAlta = fechaAlta;
	}

	
	public String getIdPostulated() {
		return idPostulated;
	}

	public void setIdPostulated(String idPostulated) {
		this.idPostulated = idPostulated;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getIdEstatusPostulated() {
		return idEstatusPostulated;
	}

	public void setIdEstatusPostulated(String idEstatusPostulated) {
		this.idEstatusPostulated = idEstatusPostulated;
	}

	public List<PostulatedDTO> getListPostulated() {
		return listPostulated;
	}

	public void setListPostulated(List<PostulatedDTO> listPostulated) {
		this.listPostulated = listPostulated;
	}

	public String getPostulated() {
		return postulated;
	}

	public void setPostulated(String postulated) {
		this.postulated = postulated;
	}

	public Boolean getEnTiempo() {
		return enTiempo;
	}

	public void setEnTiempo(Boolean enTiempo) {
		this.enTiempo = enTiempo;
	}

	public String getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}


	
	

}
