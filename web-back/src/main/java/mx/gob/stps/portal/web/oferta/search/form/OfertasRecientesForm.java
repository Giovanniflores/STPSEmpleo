package mx.gob.stps.portal.web.oferta.search.form;

import java.util.List;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaRecienteVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 22/03/2011
 **/
public class OfertasRecientesForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5120735986627563940L;

	private int numRegistros;
	private String tituloReporte;
	private List<OfertaRecienteVO> ofertas;

	/**
	 * @return the numRegistros
	 */
	public int getNumRegistros() {
		return numRegistros;
	}
	/**
	 * @param numRegistros the numRegistros to set
	 */
	public void setNumRegistros(int numRegistros) {
		this.numRegistros = numRegistros;
	}
	/**
	 * @return the tituloReporte
	 */
	public String getTituloReporte() {
		return tituloReporte;
	}
	/**
	 * @param tituloReporte the tituloReporte to set
	 */
	public void setTituloReporte(String tituloReporte) {
		this.tituloReporte = tituloReporte;
	}
	/**
	 * @return the ofertas
	 */
	public List<OfertaRecienteVO> getOfertas() {
		return ofertas;
	}
	/**
	 * @param ofertas the ofertas to set
	 */
	public void setOfertas(List<OfertaRecienteVO> ofertas) {
		this.ofertas = ofertas;
	}	
	
}
