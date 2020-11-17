package mx.gob.stps.portal.web.ofertasSFP.form;

import java.util.List;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.ws.ofertas.vo.OfertaEmpleoOutVO;

import org.apache.struts.action.ActionForm;

public class OfertasSFPForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int entidad;
	private Long idOcupacion;
	private List<OfertaPorCanalVO> registros;
	private Long idOferta;
	private String tablaPager;
	
	public int getEntidad() {
		return entidad;
	}
	public void setEntidad(int entidad) {
		this.entidad = entidad;
	}
	public Long getIdOcupacion() {
		return idOcupacion;
	}
	public void setIdOcupacion(Long idOcupacion) {
		this.idOcupacion = idOcupacion;
	}
	public void setRegistros(List<OfertaPorCanalVO> registros) {
		this.registros = registros;
	}
	public List<OfertaPorCanalVO> getRegistros() {
		return registros;
	}
	public void setIdOferta(Long idOferta) {
		this.idOferta = idOferta;
	}
	public Long getIdOferta() {
		return idOferta;
	}
	public void setTablaPager(String tablaPager) {
		this.tablaPager = tablaPager;
	}
	public String getTablaPager() {
		return tablaPager;
	}
	

}
