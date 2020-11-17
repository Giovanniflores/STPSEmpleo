package mx.gob.stps.portal.core.oferta.busqueda.vo;

import java.io.Serializable;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 22/03/2011
 **/
public class OfertaRecienteVO implements Serializable {

	private static final long serialVersionUID = 5990800186052325954L;
	
	protected long idOfertaEmpleo;
	protected String tituloOferta;
	protected String ubicacion;
	protected String vigencia;

	/**
	 * @return the idOfertaEmpleo
	 */
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	/**
	 * @param idOfertaEmpleo
	 *            the idOfertaEmpleo to set
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	/**
	 * @return the tituloOferta
	 */
	public String getTituloOferta() {
		return tituloOferta;
	}

	/**
	 * @param tituloOferta
	 *            the tituloOferta to set
	 */
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	/**
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion
	 *            the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return the vigencia
	 */
	public String getVigencia() {
		return vigencia;
	}

	/**
	 * @param vigencia
	 *            the vigencia to set
	 */
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return idOfertaEmpleo + " " + tituloOferta + " " + ubicacion + " "
				+ vigencia;
	}

}
