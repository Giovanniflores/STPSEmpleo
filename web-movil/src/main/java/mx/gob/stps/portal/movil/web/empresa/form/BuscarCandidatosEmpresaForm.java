package mx.gob.stps.portal.movil.web.empresa.form;

import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.candidate.vo.ConocimientoComputacionVO;
import mx.gob.stps.portal.movil.web.oferta.form.PerfilJB;

import org.apache.struts.action.ActionForm;

public class BuscarCandidatosEmpresaForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4609213985946221430L;
	private PerfilJB perfilLaboral;
	private String buscarCandidatos;
	private long idOfertaEmpleo;
	private String tituloOferta;
	
	private int paginaActual;
	private boolean paginaSiguiente;
	private boolean paginaAnterior;
	
	private List<CandidatoVo> listaCandidatosOferta;
	private int ofertaDesde;
	private int ofertaHasta;
	private ConocimientoComputacionVO conocimientoComputacion;

	public void setBuscarCandidatos(String buscarCandidatos) {
		this.buscarCandidatos = buscarCandidatos;
	}

	public String getBuscarCandidatos() {
		return buscarCandidatos;
	}

	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(int paginaActual) {
		this.paginaActual = paginaActual;
	}

	public boolean isPaginaSiguiente() {
		return paginaSiguiente;
	}

	public void setPaginaSiguiente(boolean paginaSiguiente) {
		this.paginaSiguiente = paginaSiguiente;
	}

	public boolean isPaginaAnterior() {
		return paginaAnterior;
	}

	public void setPaginaAnterior(boolean paginaAnterior) {
		this.paginaAnterior = paginaAnterior;
	}

	public void setListaCandidatosOferta(List<CandidatoVo> listaCandidatosOferta) {
		this.listaCandidatosOferta = listaCandidatosOferta;
	}

	public List<CandidatoVo> getListaCandidatosOferta() {
		return listaCandidatosOferta;
	}

	public void setOfertaDesde(int ofertaDesde) {
		this.ofertaDesde = ofertaDesde;
	}

	public int getOfertaDesde() {
		return ofertaDesde;
	}

	public void setOfertaHasta(int ofertaHasta) {
		this.ofertaHasta = ofertaHasta;
	}

	public int getOfertaHasta() {
		return ofertaHasta;
	}

	public void setPerfilLaboral(PerfilJB perfilLaboral) {
		this.perfilLaboral = perfilLaboral;
	}

	public PerfilJB getPerfilLaboral() {
		return perfilLaboral;
	}

	public void setConocimientoComputacion(ConocimientoComputacionVO conocimientoComputacion) {
		this.conocimientoComputacion = conocimientoComputacion;
	}

	public ConocimientoComputacionVO getConocimientoComputacion() {
		return conocimientoComputacion;
	}

}
