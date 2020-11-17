package mx.gob.stps.portal.core.domicilio.vo;

import java.io.Serializable;


/**
 * @author Mario Alberto Vázquez Flores
 * @since 09/03/2011
 **/
public final class ColoniaVO implements Serializable {

	private static final long serialVersionUID = 3735945930503923560L;

	private long idColonia;
	private String descColonia;

	/**
	 * @return the idColonia
	 */
	public long getIdColonia() {
		return idColonia;
	}

	/**
	 * @param idColonia
	 *            the idColonia to set
	 */
	public void setIdColonia(long idColonia) {
		this.idColonia = idColonia;
	}

	/**
	 * @return the descColonia
	 */
	public String getDescColonia() {
		return descColonia;
	}

	/**
	 * @param descColonia
	 *            the descColonia to set
	 */
	public void setDescColonia(String descColonia) {
		this.descColonia = descColonia;
	}

	public String toString() {
		return idColonia + " " + descColonia;
	}

}
