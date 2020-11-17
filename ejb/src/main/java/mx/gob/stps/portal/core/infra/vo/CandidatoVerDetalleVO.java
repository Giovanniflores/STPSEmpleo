package mx.gob.stps.portal.core.infra.vo;

import java.io.Serializable;

public class CandidatoVerDetalleVO implements Serializable {

	// Control de versión
	private static final long serialVersionUID = 1L;
	// Identificador de la oferta
	private long idCandidato;
	// Año en que se agrego el contador
	private int anio;
	// Mes en que se agrego el contador
	private int mes;
	// Contador del candidato
	private int contador;
	//Identificador propio de la tabla
	private long idCandidatoVerDetalle;
	
	/**
	 * @return the anio
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}

	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}

	/**
	 * @param idCandidato the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	/**
	 * @return the mes
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * @return the contador
	 */
	public int getContador() {
		return contador;
	}

	/**
	 * @param contador the contador to set
	 */
	public void setContador(int contador) {
		this.contador = contador;
	}

	/**
	 * @return the idCandidatoVerDetalle
	 */
	public long getIdCandidatoVerDetalle() {
		return idCandidatoVerDetalle;
	}

	/**
	 * @param idCandidatoVerDetalle the idCandidatoVerDetalle to set
	 */
	public void setIdCandidatoVerDetalle(long idCandidatoVerDetalle) {
		this.idCandidatoVerDetalle = idCandidatoVerDetalle;
	}
	
}