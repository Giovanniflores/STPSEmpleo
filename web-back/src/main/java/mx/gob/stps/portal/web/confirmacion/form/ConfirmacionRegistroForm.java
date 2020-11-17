package mx.gob.stps.portal.web.confirmacion.form;

import org.apache.struts.action.ActionForm;

/**
 * @author jose.hernandez
 *
 */
public class ConfirmacionRegistroForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 423463455646546456L;
	
		
	private String argumet;
	private String urlInicioSesion;
	private String urlRegistro;
	private String home;

	/**
	 * @return the home
	 */
	public String getHome() {
		return home;
	}


	/**
	 * @param home the home to set
	 */
	public void setHome(String home) {
		this.home = home;
	}


	/**
	 * @return the urlInicioSesion
	 */
	public String getUrlInicioSesion() {
		return urlInicioSesion;
	}


	/**
	 * @return the urlRegistro
	 */
	public String getUrlRegistro() {
		return urlRegistro;
	}


	/**
	 * @param urlInicioSesion the urlInicioSesion to set
	 */
	public void setUrlInicioSesion(String urlInicioSesion) {
		this.urlInicioSesion = urlInicioSesion;
	}


	/**
	 * @param urlRegistro the urlRegistro to set
	 */
	public void setUrlRegistro(String urlRegistro) {
		this.urlRegistro = urlRegistro;
	}


	/**
	 * @param argumet the argumet to set
	 */
	public void setArgumet(String argumet) {
		this.argumet = argumet;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfirmacionRegistroForm [argumet=" + argumet + "]";
	}


	/**
	 * @return the argumet
	 */
	public String getArgumet() {
		return argumet;
	}
}