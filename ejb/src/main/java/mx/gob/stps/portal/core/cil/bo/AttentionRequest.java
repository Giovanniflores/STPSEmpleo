package mx.gob.stps.portal.core.cil.bo;

import java.util.Date;
import java.util.ArrayList;
import java.io.Serializable;
import mx.gob.stps.portal.core.cil.vo.BitacoraAtencionVO;

public final class AttentionRequest implements Serializable{
	
	private static final long serialVersionUID = 7721330364207415486L;
	
	private long idCil;
	private long idAtencion;
	private long idCandidato;
	private Date fechaInicio;
	private BitacoraAtencionVO impresion;
	private BitacoraAtencionVO actualizarCV;
	private BitacoraAtencionVO fotocopias;
	private BitacoraAtencionVO llamadas;	
	private BitacoraAtencionVO otrasBolsas;
	private BitacoraAtencionVO actividadPortal;

	
	public AttentionRequest(long idCil, long idCandidato, long idAtencion, Date fechaInicio, BitacoraAtencionVO actualizarCV, BitacoraAtencionVO impresion, BitacoraAtencionVO fotocopias, BitacoraAtencionVO llamadas, BitacoraAtencionVO otrasBolsas, BitacoraAtencionVO actividadPortal) {
		setIdCil(idCil);
		setIdCandidato(idCandidato);
		setIdAtencion(idAtencion);
		setFechaInicio(fechaInicio);
		setImpresion(impresion);
		setActualizarCV(actualizarCV);
		setFotocopias(fotocopias);
		setLlamadas(llamadas);
		setOtrasBolsas(otrasBolsas);
		setActividadPortal(actividadPortal);
	}


	/**
	 * @return the idAtencion
	 */
	public long getIdAtencion() {
		return idAtencion;
	}
	
	/**
	 * @param idAtencion the idAtencion to set
	 */
	public void setIdAtencion(long idAtencion) {
		this.idAtencion = idAtencion;
	}
	
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * @return the impresion
	 */
	public BitacoraAtencionVO getImpresion() {
		return impresion;
	}
	
	/**
	 * @param impresion the impresion to set
	 */
	public void setImpresion(BitacoraAtencionVO impresion) {
		this.impresion = impresion;
	}
	
	/**
	 * @return the actualizarCV
	 */
	public BitacoraAtencionVO getActualizarCV() {
		return actualizarCV;
	}
	
	/**
	 * @param actualizarCV the actualizarCV to set
	 */
	public void setActualizarCV(BitacoraAtencionVO actualizarCV) {
		this.actualizarCV = actualizarCV;
	}
	
	/**
	 * @return the fotocopias
	 */
	public BitacoraAtencionVO getFotocopias() {
		return fotocopias;
	}
	
	/**
	 * @param fotocopias the fotocopias to set
	 */
	public void setFotocopias(BitacoraAtencionVO fotocopias) {
		this.fotocopias = fotocopias;
	}
	
	/**
	 * @return the otrasBolsas
	 */
	public BitacoraAtencionVO getOtrasBolsas() {
		return otrasBolsas;
	}
	
	/**
	 * @param otrasBolsas the otrasBolsas to set
	 */
	public void setOtrasBolsas(BitacoraAtencionVO otrasBolsas) {
		this.otrasBolsas = otrasBolsas;
	}


	/**
	 * @return the idCil
	 */
	public long getIdCil() {
		return idCil;
	}

	/**
	 * @param idCil the idCil to set
	 */
	public void setIdCil(long idCil) {
		this.idCil = idCil;
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
	 * @return the actividadPortal
	 */
	public BitacoraAtencionVO getActividadPortal() {
		return actividadPortal;
	}

	/**
	 * @param actividadPortal the actividadPortal to set
	 */
	public void setActividadPortal(BitacoraAtencionVO actividadPortal) {
		this.actividadPortal = actividadPortal;
	}

	/**
	 * @return the llamadas
	 */
	public BitacoraAtencionVO getLlamadas() {
		return llamadas;
	}

	/**
	 * @param llamadas the llamadas to set
	 */
	public void setLlamadas(BitacoraAtencionVO llamadas) {
		this.llamadas = llamadas;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AttentionRequest [idCil=");
		builder.append(idCil);
		builder.append(", idAtencion=");
		builder.append(idAtencion);
		builder.append(", idCandidato=");
		builder.append(idCandidato);
		builder.append(", fechaInicio=");
		builder.append(fechaInicio);
		builder.append(", impresion=");
		builder.append(impresion);
		builder.append(", actualizarCV=");
		builder.append(actualizarCV);
		builder.append(", fotocopias=");
		builder.append(fotocopias);
		builder.append(", llamadas=");
		builder.append(llamadas);
		builder.append(", otrasBolsas=");
		builder.append(otrasBolsas);
		builder.append(", actividadPortal=");
		builder.append(actividadPortal);
		builder.append("]");
		return builder.toString();
	}	
	
}
