package mx.gob.stps.portal.web.cil.form;

import org.apache.struts.action.ActionForm;

public class EventUserForm extends ActionForm {
	
	private String accesoscv;
	private String impresioncv;
	private String llamadas;
	private String fotocopias;
	private String portal;
	private String bolsas;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9149280213405949919L;

	/**
	 * @return the accesoscv
	 */
	public String getAccesoscv() {
		return accesoscv;
	}

	/**
	 * @param accesoscv the accesoscv to set
	 */
	public void setAccesoscv(String accesoscv) {
		this.accesoscv = accesoscv;
	}

	/**
	 * @return the impresioncv
	 */
	public String getImpresioncv() {
		return impresioncv;
	}

	/**
	 * @param impresioncv the impresioncv to set
	 */
	public void setImpresioncv(String impresioncv) {
		this.impresioncv = impresioncv;
	}

	/**
	 * @return the llamadas
	 */
	public String getLlamadas() {
		return llamadas;
	}

	/**
	 * @param llamadas the llamadas to set
	 */
	public void setLlamadas(String llamadas) {
		this.llamadas = llamadas;
	}

	/**
	 * @return the fotocopias
	 */
	public String getFotocopias() {
		return fotocopias;
	}

	/**
	 * @param fotocopias the fotocopias to set
	 */
	public void setFotocopias(String fotocopias) {
		this.fotocopias = fotocopias;
	}

	/**
	 * @return the portal
	 */
	public String getPortal() {
		return portal;
	}

	/**
	 * @param portal the portal to set
	 */
	public void setPortal(String portal) {
		this.portal = portal;
	}

	/**
	 * @return the bolsas
	 */
	public String getBolsas() {
		return bolsas;
	}

	/**
	 * @param bolsas the bolsas to set
	 */
	public void setBolsas(String bolsas) {
		this.bolsas = bolsas;
	}

}