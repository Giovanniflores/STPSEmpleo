package mx.gob.stps.portal.core.persistencia.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * This class represents the primary key of the OfertaPregunta table.
 */
@Embeddable
public class OfertaPreguntaPK implements Serializable{

	private static final long serialVersionUID = -319376531890167655L;

	protected long idOfertaEmpleo;
	protected long idCandidato;
	
	public OfertaPreguntaPK() {
		
	}
	
	public OfertaPreguntaPK(final long idOfertaEmpleo, final long idCandidato) {
		this.idCandidato = idCandidato;
		this.idOfertaEmpleo = idOfertaEmpleo;
	}
	
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}
	
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}
	
	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
	
	/**
	 * Method 'equals'
	 * 
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other) {
		if (_other == null) {
			return false;
		}
		if (_other == this) {
			return true;
		}
		if (!(_other instanceof OfertaPreguntaPK)) {
			return false;
		}
		final OfertaPreguntaPK _cast = (OfertaPreguntaPK) _other;
		if (idOfertaEmpleo != _cast.idOfertaEmpleo) {
			return false;
		}
		if (idCandidato != _cast.idCandidato) {
			return false;
		}
		return true;
	}
	
	/**
	 * Method 'hashCode'
	 * 
	 * @return int
	 */
	public int hashCode() {
		long _hashCode = 0;
		_hashCode = 29 * _hashCode + idOfertaEmpleo;
		_hashCode = 29 * _hashCode + idCandidato;
		return Long.valueOf(_hashCode).intValue();
	}
	
	/**
	 * Method 'toString'
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append("mx.gob.stps.portal.core.persistencia.entity.OfertaPreguntaPK: ");
		ret.append("idOtroMedio=" + idOfertaEmpleo);
		ret.append(", idCandidato=" + idCandidato);
		return ret.toString();
	}
}
