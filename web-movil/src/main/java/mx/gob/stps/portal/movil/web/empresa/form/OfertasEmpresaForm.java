package mx.gob.stps.portal.movil.web.empresa.form;

import java.util.List;

import mx.gob.stps.portal.movil.web.empresa.vo.OfertasEmpresaVO;

import org.apache.struts.action.ActionForm;

public class OfertasEmpresaForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9091623435007772041L;
	
	
	private List<OfertasEmpresaVO> listaOfertas;
	private int paginaActual;
	private boolean paginaSiguiente;
	private boolean paginaAnterior;
	private int ofertaDesde;
	private int ofertaHasta;


	public void setListaOfertas(List<OfertasEmpresaVO> listaOfertas) {
		this.listaOfertas = listaOfertas;
	}


	public List<OfertasEmpresaVO> getListaOfertas() {
		return listaOfertas;
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


	public int getOfertaDesde() {
		return ofertaDesde;
	}


	public void setOfertaDesde(int ofertaDesde) {
		this.ofertaDesde = ofertaDesde;
	}


	public int getOfertaHasta() {
		return ofertaHasta;
	}


	public void setOfertaHasta(int ofertaHasta) {
		this.ofertaHasta = ofertaHasta;
	}

	
}
