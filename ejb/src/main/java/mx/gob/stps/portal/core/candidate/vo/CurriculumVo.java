/**
 * 
 */
package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;

/**
 * @author jose.jimenez
 *
 */
public class CurriculumVo implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6547177997200803597L;

	/**
	 * Representa el URL desde el que se puede acceder al Curr&iacute;culum Vitae del candidato
	 */
	private String videoURL;
	
	/**
	 * Contiene la descripci&oacute;n del Curr&iacute;culum Vitae del candidato
	 */
	private String videoDescription;
	
	/**
	 * Indica si se aceptaron los t&eacute;rminos y condiciones para publicar
	 * el Curr&iacute;culum Vitae del candidato.
	 */
	private boolean termsConditionsAccepted;
	
	/**
	 * Indica el estado de publicaci&oacute;n del video curr&iacute;culum
	 */
	private int estatusVideoc;
	
	
	public CurriculumVo() {}

	/**
	 * @return el URL desde el que se puede acceder al Curr&iacute;culum Vitae del candidato
	 */
	public String getVideoURL() {
		return videoURL;
	}

	/**
	 * @param videoURL representa el URL desde el que se puede acceder al 
	 * Curr&iacute;culum Vitae del candidato
	 */
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	/**
	 * @return la descripci&oacute;n del video curr&iacute;culum del candidato
	 */
	public String getVideoDescription() {
		return videoDescription;
	}

	/**
	 * @param videoDescription la descripci&oacute;n del video a asignar
	 */
	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}

	/**
	 * @return la indicaci&oacute;n de si los t&eacute;rminos y condiciones
	 *         fueron aceptados
	 */
	public boolean isTermsConditionsAccepted() {
		return termsConditionsAccepted;
	}

	/**
	 * @param termsConditionsAccepted la indicaci&oacute;n sobre si los
	 *        t&eacute;rminos y condiciones fueron aceptados
	 */
	public void setTermsConditionsAccepted(boolean termsConditionsAccepted) {
		this.termsConditionsAccepted = termsConditionsAccepted;
	}

	/**
	 * @return el valor entero asociado al estado de publicaci&oacute;n del video
	 *  	   curr&iacute;culum del candidato
	 */
	public int getEstatusVideoc() {
		return estatusVideoc;
	}

	/**
	 * @param estatusVideoc el valor entero asociado al estado de publicaci&oacute;n
	 *        del video curr&iacute;culum del candidato
	 */
	public void setEstatusVideoc(int estatusVideoc) {
		this.estatusVideoc = estatusVideoc;
	}

}
