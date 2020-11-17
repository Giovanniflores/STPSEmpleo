package mx.gob.stps.portal.core.oferta.busqueda.vo;

import java.io.Serializable;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 28/03/2011
 **/
public class OfertaPerfilCandidatoVO implements Serializable {

	private static final long serialVersionUID = -6519538229363292905L;

	private long idCandidato;
	private long idUusario;

	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}

	/**
	 * @param idCandidato
	 *            the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	/**
	 * @return the idUusario
	 */
	public long getIdUusario() {
		return idUusario;
	}

	/**
	 * @param idUusario
	 *            the idUusario to set
	 */
	public void setIdUusario(long idUusario) {
		this.idUusario = idUusario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ": " + idCandidato + ", :" + idUusario;
	}

}
