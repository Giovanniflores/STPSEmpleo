package mx.gob.stps.portal.core.oferta.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * Contiene los datos necesarios para mostrar el apartado &quot;Postulaciones 
 * Recientes&quot; a una empresa.
 * @author jose.jimenez
 *
 */
public class PostulacionRecienteVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3413562588722789240L;

	/** Nombre del candidato postulado */
	private String nombreCandidato;
	
	/** Identificador del candidato postulado */
	private long idCandidato;
	
	/** T&iacute;tulo de la oferta de empleo relacionada */
	private String tituloOferta;
	
	/** Fecha de alta de la postulaci&oacute;n */
	private Date fechaAltaOferta;
	
	/** Identificador de la relaci&oacute;n oferta - candidato */
	private long idOfertaCandidato;
	
	public PostulacionRecienteVO() {}
	
	public PostulacionRecienteVO(long idCandidato, String nombreCandidato,
			String tituloOferta, Date fechaAltaOferta) {
		
		this.idCandidato = idCandidato;
		if (nombreCandidato != null) {
			this.nombreCandidato = nombreCandidato;
		} else {
			this.nombreCandidato = "";
		}
		this.tituloOferta = tituloOferta;
		this.fechaAltaOferta = fechaAltaOferta;
	}

	/**
	 * @return the nombreCandidato
	 */
	public String getNombreCandidato() {
		return nombreCandidato;
	}

	/**
	 * @param nombreCandidato the nombreCandidato to set
	 */
	public void setNombreCandidato(String nombreCandidato) {
		this.nombreCandidato = nombreCandidato;
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
	 * @return the fechaAltaOferta
	 */
	public Date getFechaAltaOferta() {
		return fechaAltaOferta;
	}

	/**
	 * @param fechaAltaOferta the fechaAltaOferta to set
	 */
	public void setFechaAltaOferta(Date fechaAltaOferta) {
		this.fechaAltaOferta = fechaAltaOferta;
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
	
	
}
