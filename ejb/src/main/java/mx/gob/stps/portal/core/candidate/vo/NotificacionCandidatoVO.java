package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

public class NotificacionCandidatoVO implements Serializable {

	


	/**
	 * 
	 */
	private static final long serialVersionUID = -999532556739358907L;

	

	private long idNotificacionCandidato;

	private String deviceId;
	
	private long idCandidato;
	
	private long estatusNotificacion;

	public long getIdNotificacionCandidato() {
		return idNotificacionCandidato;
	}

	public void setIdNotificacionCandidato(long idNotificacionCandidato) {
		this.idNotificacionCandidato = idNotificacionCandidato;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public long getEstatusNotificacion() {
		return estatusNotificacion;
	}

	public void setEstatusNotificacion(long estatusNotificacion) {
		this.estatusNotificacion = estatusNotificacion;
	}
	
	
	
	
}
