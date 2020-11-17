package mx.gob.stps.portal.core.oferta.detalle.vo;

import java.io.Serializable;
import java.util.Date;

public class OfertaCandidatoADesactivarVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7028947060420057762L;
	protected long idOfertaCandidato;
	protected long idCandidato;
	protected long idOfertaEmpleo;
	protected int estatus;
	protected String tituloOferta;
	protected String empresaNombre;
	protected String correoElectronicoContacto;
	
	public OfertaCandidatoADesactivarVO(){		
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
	 * @return the tituloOferta
	 */
	public String getTituloOferta() {
		return tituloOferta;
	}

	/**
	 * @param tituloOferta the tituloOferta to set
	 */
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	/**
	 * @return the empresaNombre
	 */
	public String getEmpresaNombre() {
		return empresaNombre;
	}

	/**
	 * @param empresaNombre the empresaNombre to set
	 */
	public void setEmpresaNombre(String empresaNombre) {
		this.empresaNombre = empresaNombre;
	}

	/**
	 * @return the correoElectronicoContacto
	 */
	public String getCorreoElectronicoContacto() {
		return correoElectronicoContacto;
	}

	/**
	 * @param correoElectronicoContacto the correoElectronicoContacto to set
	 */
	public void setCorreoElectronicoContacto(String correoElectronicoContacto) {
		this.correoElectronicoContacto = correoElectronicoContacto;
	}
	
	
	
}
