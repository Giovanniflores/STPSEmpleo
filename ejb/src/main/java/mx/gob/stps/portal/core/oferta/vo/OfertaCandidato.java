package mx.gob.stps.portal.core.oferta.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaCandidato implements Serializable {
	//Control de versión
	private static final long serialVersionUID = -9189986260656820770L;
	//Identificador de la tabla
	protected long idOfertaCandidato;
	//Identificador del candidato
	protected long idOfertaEmpleo;
	//Identificador de la oferta
	protected long idCandidato;
	//Fecha de creación
	protected Date fechaAlta;
	//estatus de la oferta con candidato
	protected int estatus;
	//Morivo de la oferta
	protected int idMotivo;
	
	/**
	 * @return the estatus
	 */
	public int getEstatus() {
		return estatus;
	}
	
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the idOfertaCandidato
	 */
	public long getIdOfertaCandidato() {
		return idOfertaCandidato;
	}

	/**
	 * @param idOfertaCandidato the idOfertaCandidato to set
	 */
	public void setIdOfertaCandidato(long idOfertaCandidato) {
		this.idOfertaCandidato = idOfertaCandidato;
	}

	/**
	 * @return the idOfertaEmpleo
	 */
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	/**
	 * @param idOfertaEmpleo the idOfertaEmpleo to set
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
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
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the idMotivo
	 */
	public int getIdMotivo() {
		return idMotivo;
	}

	/**
	 * @param idMotivo the idMotivo to set
	 */
	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
	}
	
}
