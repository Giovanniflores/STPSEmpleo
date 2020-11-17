package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que contiene datos que se mostraran en el CV
 * @author amunoz
 * 23/05/2017
 */
public class RedesSocialesVO implements Serializable {
	
	/**
	 * Identificador serial
	 */
	private static final long serialVersionUID = -3677512347903325492L;
	/**
	 * FB del candidato
	 */
	private String facebook;
	/**
	 * Twitter del candidato
	 */
	private String twitter;
	/**
	 * Ln del candidato
	 */
	private String linkedin;
 
	/**
	 * @return the facebook
	 */
	public String getFacebook() {
		return facebook;
	}
	/**
	 * @param facebook the facebook to set
	 */
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	/**
	 * @return the twitter
	 */
	public String getTwitter() {
		return twitter;
	}
	/**
	 * @param twitter the twitter to set
	 */
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	/**
	 * @return the linkedin
	 */
	public String getLinkedin() {
		return linkedin;
	}
	/**
	 * @param linkedin the linkedin to set
	 */
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	
}
