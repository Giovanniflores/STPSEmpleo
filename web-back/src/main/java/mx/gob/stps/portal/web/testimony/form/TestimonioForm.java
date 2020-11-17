/**
 * 
 */
package mx.gob.stps.portal.web.testimony.form;

import org.apache.struts.action.ActionForm;

/**
 * @author concepcion.aguilar
 * Clase que mapea las propiedades del jsp para enviarlos a la clase 
 * action en un objeto form
 */
public class TestimonioForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6611299177819996291L;
	
	private String testimonio;
	
	private String nombre;
	
	private String empresa;

	public String getTestimonio() {
		return testimonio;
	}

	public void setTestimonio(String testimonio) {
		this.testimonio = testimonio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

}
