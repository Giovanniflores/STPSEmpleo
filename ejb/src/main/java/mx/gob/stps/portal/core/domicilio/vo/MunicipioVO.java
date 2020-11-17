package mx.gob.stps.portal.core.domicilio.vo;

import java.io.Serializable;


/**
 * @author Mario Alberto Vázquez Flores
 * @since 09/03/2011
 **/
public class MunicipioVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2297178632654183794L;
	protected long idEntidad;
	protected long idMunicipio;
	protected String municipio;

	/**
	 * @return the idEntidad
	 */
	public long getIdEntidad() {
		return idEntidad;
	}

	/**
	 * @param idEntidad
	 *            the idEntidad to set
	 */
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}

	/**
	 * @return the idMunicipio
	 */
	public long getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio
	 *            the idMunicipio to set
	 */
	public void setIdMunicipio(long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio
	 *            the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String toString() {
		return idEntidad + " " + idMunicipio + " " + municipio;
	}
}
