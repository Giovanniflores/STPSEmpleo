package mx.gob.stps.portal.core.domicilio.vo;

import java.io.Serializable;
import java.util.List;


/**
 * @author Mario Alberto Vázquez Flores
 * @since 09/03/2011
 **/
public final class CodigoPostalVO implements Serializable {

	private static final long serialVersionUID = -678232223759145330L;

	private long idEntidad;
	private long idMunicipio;
	private String entidadDescripcion;
	private String municipioDescripcion;
	private String coloniaDescripcion;

	public String getEntidadDescripcion() {
		return entidadDescripcion;
	}

	public void setEntidadDescripcion(String entidadDescripcion) {
		this.entidadDescripcion = entidadDescripcion;
	}

	public String getMunicipioDescripcion() {
		return municipioDescripcion;
	}

	public void setMunicipioDescripcion(String municipioDescripcion) {
		this.municipioDescripcion = municipioDescripcion;
	}

	public String getColoniaDescripcion() {
		return coloniaDescripcion;
	}

	public void setColoniaDescripcion(String coloniaDescripcion) {
		this.coloniaDescripcion = coloniaDescripcion;
	}

	private List<ColoniaVO> listColonias = null;

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
	 * @return the listColonias
	 */
	public List<ColoniaVO> getListColonias() {
		return listColonias;
	}

	/**
	 * @param listColonias
	 *            the listColonias to set
	 */
	public void setListColonias(List<ColoniaVO> listColonias) {
		this.listColonias = listColonias;
	}

}
