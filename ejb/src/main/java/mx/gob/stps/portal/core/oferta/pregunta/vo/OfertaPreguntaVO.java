package mx.gob.stps.portal.core.oferta.pregunta.vo;

import java.io.Serializable;
import java.util.Date;

public final class OfertaPreguntaVO implements Serializable {

	private static final long serialVersionUID = 7786308025933877960L;

	protected long idOfertaEmpleo;

	protected long idCandidato;

	protected String pregunta;

	protected String respuesta;

	protected Date fechaAlta;

	protected Date fechaRespuesta;

	protected long idOfertaPregunta;
	
	public OfertaPreguntaVO() {
	}

	/**
	 * Constructor parameterized
	 */
	public OfertaPreguntaVO(long idOfertaEmpleo, long idCandidato, String pregunta, String respuesta, Date fechaAlta, Date fechaRespuesta) {
		this.fechaAlta = fechaAlta;
		this.fechaRespuesta = fechaRespuesta;
		this.idCandidato = idCandidato;
		this.idOfertaEmpleo = idOfertaEmpleo;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
	}
	
	/**
	 * Method 'getIdOfertaEmpleo'
	 * 
	 * @return long
	 */
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	/**
	 * Method 'setIdOfertaEmpleo'
	 * 
	 * @param idOfertaEmpleo
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	/**
	 * Method 'getIdCandidato'
	 * 
	 * @return long
	 */
	public long getIdCandidato() {
		return idCandidato;
	}

	/**
	 * Method 'setIdCandidato'
	 * 
	 * @param idCandidato
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	/**
	 * Method 'getPregunta'
	 * 
	 * @return String
	 */
	public String getPregunta() {
		return pregunta;
	}

	/**
	 * Method 'setPregunta'
	 * 
	 * @param pregunta
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * Method 'getRespuesta'
	 * 
	 * @return String
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * Method 'setRespuesta'
	 * 
	 * @param respuesta
	 */
	public void setRespuesta(String respuesta) {
		if (null != respuesta)
			this.respuesta = respuesta;
		else
			this.respuesta = "";
	}

	/**
	 * Method 'getFechaAlta'
	 * 
	 * @return Date
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Method 'setFechaAlta'
	 * 
	 * @param fechaAlta
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Method 'getFechaRespuesta'
	 * 
	 * @return Date
	 */
	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}

	/**
	 * Method 'setFechaRespuesta'
	 * 
	 * @param fechaRespuesta
	 */
	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}

	public long getIdOfertaPregunta() {
		return idOfertaPregunta;
	}
	public void setIdOfertaPregunta(long idOfertaPregunta) {
		this.idOfertaPregunta = idOfertaPregunta;
	}
}
