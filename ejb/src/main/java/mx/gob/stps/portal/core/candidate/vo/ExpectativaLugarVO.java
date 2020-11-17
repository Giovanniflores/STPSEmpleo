/**
 * 
 */
package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

/**
 * @author Felipe Juárez Ramírez
 * @since 10/03/2011
 *
 */
public class ExpectativaLugarVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3955546529265303856L;

	private long idExpectativaLugar;
	private long idEntidadDeseada;
	private String entidadDeseada;
	private long idMunicipioDeseado;
	private String municipioDeseado;
	private int principal;
	/**
	 * @return the idExpectativaLugar
	 */
	public long getIdExpectativaLugar() {
		return idExpectativaLugar;
	}
	/**
	 * @param idExpectativaLugar the idExpectativaLugar to set
	 */
	public void setIdExpectativaLugar(long idExpectativaLugar) {
		this.idExpectativaLugar = idExpectativaLugar;
	}
	/**
	 * @return the idEntidadDeseada
	 */
	public long getIdEntidadDeseada() {
		return idEntidadDeseada;
	}
	/**
	 * @param idEntidadDeseada the idEntidadDeseada to set
	 */
	public void setIdEntidadDeseada(long idEntidadDeseada) {
		this.idEntidadDeseada = idEntidadDeseada;
	}
	/**
	 * @param entidadDeseada the entidadDeseada to set
	 */
	public void setEntidadDeseada(String entidadDeseada) {
		this.entidadDeseada = entidadDeseada;
	}
	/**
	 * @return the entidadDeseada
	 */
	public String getEntidadDeseada() {
		return entidadDeseada;
	}
	/**
	 * @return the idMunicipioDeseado
	 */
	public long getIdMunicipioDeseado() {
		return idMunicipioDeseado;
	}
	/**
	 * @param idMunicipioDeseado the idMunicipioDeseado to set
	 */
	public void setIdMunicipioDeseado(long idMunicipioDeseado) {
		this.idMunicipioDeseado = idMunicipioDeseado;
	}
	/**
	 * @param municipioDeseado the municipioDeseado to set
	 */
	public void setMunicipioDeseado(String municipioDeseado) {
		this.municipioDeseado = municipioDeseado;
	}
	/**
	 * @return the municipioDeseado
	 */
	public String getMunicipioDeseado() {
		return municipioDeseado;
	}
	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	/**
	 * @return the principal
	 */
	public int getPrincipal() {
		return principal;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExpectativaLugarVO [idExpectativaLugar=" + idExpectativaLugar
				+ ", idEntidadDeseada=" + idEntidadDeseada
				+ ", entidadDeseada=" + entidadDeseada
				+ ", idMunicipioDeseado=" + idMunicipioDeseado
				+ ", municipioDeseado=" + municipioDeseado + ", principal="
				+ principal + "]";
	}
	public String toBitacora() {
		return "idExpectativaLugar=" + idExpectativaLugar
				+ "|idEntidadDeseada=" + idEntidadDeseada
				+ "|idMunicipioDeseado=" + idMunicipioDeseado + "|principal="
				+ principal;
	}
		
}
