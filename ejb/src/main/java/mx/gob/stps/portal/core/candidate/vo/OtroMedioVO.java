/**
 * 
 */
package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

/**
 * @author Felipe
 *
 */
public class OtroMedioVO implements Serializable {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = -2764399124107224019L;
	
	private long idOtroMedio;
	private long idMedioBusqueda;
	/**
	 * @return the idOtroMedio
	 */
	public long getIdOtroMedio() {
		return idOtroMedio;
	}
	/**
	 * @param idOtroMedio the idOtroMedio to set
	 */
	public void setIdOtroMedio(long idOtroMedio) {
		this.idOtroMedio = idOtroMedio;
	}
	/**
	 * @return the idMedioBusqueda
	 */
	public long getIdMedioBusqueda() {
		return idMedioBusqueda;
	}
	/**
	 * @param idMedioBusqueda the idMedioBusqueda to set
	 */
	public void setIdMedioBusqueda(long idMedioBusqueda) {
		this.idMedioBusqueda = idMedioBusqueda;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OtroMedioVO [idOtroMedio=");
		builder.append(idOtroMedio);
		builder.append(", idMedioBusqueda=");
		builder.append(idMedioBusqueda);
		builder.append("]");
		return builder.toString();
	}
	
}
