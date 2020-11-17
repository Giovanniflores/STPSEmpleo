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
public class ExpectativaLaboralVO implements Serializable {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = 8432113331021793357L;
	
	private long idExpectativaLaboral;
	private long idExpectativaLaboral2;
	private long idSectorDeseado;
	private String sectorDeseado;
	private String puestoDeseado;
	private long idAreaLaboralDeseada;
	private String areaLaboralDeseada;
	private long idOcupacionDeseada;
	private long idOcupacionDeseada2;
	private String ocupacionDeseada;
	private double salarioPretendido;
	private long idTipoEmpleoDeseado;
	private String tipoEmpleoDeseado;
	private long idTipoContrato;
	private String tipoContrato;
	private int principal;
	private int idExperiencia;
	private int idExperiencia2;
	private long idSubAreaLaboralDeseada;
	
	/**
	 * @return the idExpectativaLaboral
	 */
	public long getIdExpectativaLaboral() {
		return idExpectativaLaboral;
	}
	/**
	 * @param idExpectativaLaboral the idExpectativaLaboral to set
	 */
	public void setIdExpectativaLaboral(long idExpectativaLaboral) {
		this.idExpectativaLaboral = idExpectativaLaboral;
	}
	/**
	 * @return the idSectorDeseado
	 */
	public long getIdSectorDeseado() {
		return idSectorDeseado;
	}
	/**
	 * @param idSectorDeseado the idSectorDeseado to set
	 */
	public void setIdSectorDeseado(long idSectorDeseado) {
		this.idSectorDeseado = idSectorDeseado;
	}
	/**
	 * @param sectorDeseado the sectorDeseado to set
	 */
	public void setSectorDeseado(String sectorDeseado) {
		this.sectorDeseado = sectorDeseado;
	}
	/**
	 * @return the sectorDeseado
	 */
	public String getSectorDeseado() {
		return sectorDeseado;
	}
	/**
	 * @return the puestoDeseado
	 */
	public String getPuestoDeseado() {
		return puestoDeseado;
	}
	/**
	 * @param puestoDeseado the puestoDeseado to set
	 */
	public void setPuestoDeseado(String puestoDeseado) {
		this.puestoDeseado = puestoDeseado;
	}
	/**
	 * @return the idAreaLaboralDeseada
	 */
	public long getIdAreaLaboralDeseada() {
		return idAreaLaboralDeseada;
	}
	/**
	 * @param idAreaLaboralDeseada the idAreaLaboralDeseada to set
	 */
	public void setIdAreaLaboralDeseada(long idAreaLaboralDeseada) {
		this.idAreaLaboralDeseada = idAreaLaboralDeseada;
	}
	/**
	 * @param areaLaboralDeseada the areaLaboralDeseada to set
	 */
	public void setAreaLaboralDeseada(String areaLaboralDeseada) {
		this.areaLaboralDeseada = areaLaboralDeseada;
	}
	/**
	 * @return the areaLaboralDeseada
	 */
	public String getAreaLaboralDeseada() {
		return areaLaboralDeseada;
	}
	/**
	 * @return the idOcupacionDeseada
	 */
	public long getIdOcupacionDeseada() {
		return idOcupacionDeseada;
	}
	/**
	 * @param idOcupacionDeseada the idOcupacionDeseada to set
	 */
	public void setIdOcupacionDeseada(long idOcupacionDeseada) {
		this.idOcupacionDeseada = idOcupacionDeseada;
	}
	/**
	 * @param ocupacionDeseada the ocupacionDeseada to set
	 */
	public void setOcupacionDeseada(String ocupacionDeseada) {
		this.ocupacionDeseada = ocupacionDeseada;
	}
	/**
	 * @return the ocupacionDeseada
	 */
	public String getOcupacionDeseada() {
		return ocupacionDeseada;
	}
	/**
	 * @return the salarioPretendido
	 */
	public double getSalarioPretendido() {
		return salarioPretendido;
	}
	/**
	 * @param salarioPretendido the salarioPretendido to set
	 */
	public void setSalarioPretendido(double salarioPretendido) {
		this.salarioPretendido = salarioPretendido;
	}
	/**
	 * @return the idTipoEmpleoDeseado
	 */
	public long getIdTipoEmpleoDeseado() {
		return idTipoEmpleoDeseado;
	}
	/**
	 * @param idTipoEmpleoDeseado the idTipoEmpleoDeseado to set
	 */
	public void setIdTipoEmpleoDeseado(long idTipoEmpleoDeseado) {
		this.idTipoEmpleoDeseado = idTipoEmpleoDeseado;
	}
	/**
	 * @param tipoEmpleoDeseado the tipoEmpleoDeseado to set
	 */
	public void setTipoEmpleoDeseado(String tipoEmpleoDeseado) {
		this.tipoEmpleoDeseado = tipoEmpleoDeseado;
	}
	/**
	 * @return the tipoEmpleoDeseado
	 */
	public String getTipoEmpleoDeseado() {
		return tipoEmpleoDeseado;
	}
	/**
	 * @return the idTipoContrato
	 */
	public long getIdTipoContrato() {
		return idTipoContrato;
	}
	/**
	 * @param idTipoContrato the idTipoContrato to set
	 */
	public void setIdTipoContrato(long idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}
	/**
	 * @param tipoContrato the tipoContrato to set
	 */
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	/**
	 * @return the tipoContrato
	 */
	public String getTipoContrato() {
		return tipoContrato;
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
	
		
	public int getIdExperiencia() {
		return idExperiencia;
	}
	public void setIdExperiencia(int idExperiencia) {
		this.idExperiencia = idExperiencia;
	}
	
	
	
	
	public long getIdOcupacionDeseada2() {
		return idOcupacionDeseada2;
	}
	public void setIdOcupacionDeseada2(long idOcupacionDeseada2) {
		this.idOcupacionDeseada2 = idOcupacionDeseada2;
	}
	public int getIdExperiencia2() {
		return idExperiencia2;
	}
	public void setIdExperiencia2(int idExperiencia2) {
		this.idExperiencia2 = idExperiencia2;
	}
	
	
	public long getIdExpectativaLaboral2() {
		return idExpectativaLaboral2;
	}
	public void setIdExpectativaLaboral2(long idExpectativaLaboral2) {
		this.idExpectativaLaboral2 = idExpectativaLaboral2;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExpectativaLaboralVO [idExpectativaLaboral="
				+ idExpectativaLaboral + ", idSectorDeseado=" + idSectorDeseado
				+ ", sectorDeseado=" + sectorDeseado + ", puestoDeseado="
				+ puestoDeseado + ", idAreaLaboralDeseada="
				+ idAreaLaboralDeseada + ", areaLaboralDeseada="
				+ areaLaboralDeseada + ", idOcupacionDeseada="
				+ idOcupacionDeseada + ", ocupacionDeseada=" + ocupacionDeseada
				+ ", salarioPretendido=" + salarioPretendido
				+ ", idTipoEmpleoDeseado=" + idTipoEmpleoDeseado
				+ ", tipoEmpleoDeseado=" + tipoEmpleoDeseado
				+ ", idTipoContrato=" + idTipoContrato + ", tipoContrato="
				+ tipoContrato + ", principal=" + principal + "]";
	}
	public String toBitacora() {
		return "idExpectativaLaboral=" + idExpectativaLaboral
				+ "|idSectorDeseado=" + idSectorDeseado + "|puestoDeseado="
				+ puestoDeseado + "|idAreaLaboralDeseada="
				+ idAreaLaboralDeseada + "|idOcupacionDeseada="
				+ idOcupacionDeseada + "|salarioPretendido="
				+ salarioPretendido + "|idTipoEmpleoDeseado="
				+ idTipoEmpleoDeseado + "|idTipoContrato=" + idTipoContrato
				+ "|principal=" + principal;
	}
	public long getIdSubAreaLaboralDeseada() {
		return idSubAreaLaboralDeseada;
	}
	public void setIdSubAreaLaboralDeseada(long idSubAreaLaboralDeseada) {
		this.idSubAreaLaboralDeseada = idSubAreaLaboralDeseada;
	}
	
}
