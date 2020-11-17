package mx.gob.stps.portal.web.test.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.xeustechnologies.googleapi.spelling.SpellResponse;

/**
 * @author Mario Vázquez Flores
 * 
 */
public class SpellForm extends ActionForm {

	private static final long serialVersionUID = -6464528815635237811L;

	private String miTexto;
	private SpellResponse otro;

	/**
	 * @return the miTexto
	 */
	public String getMiTexto() {
		return miTexto;
	}

	/**
	 * @return the otro
	 */
	public SpellResponse getOtro() {
		return otro;
	}

	/**
	 * @param otro the otro to set
	 */
	public void setOtro(SpellResponse otro) {
		this.otro = otro;
	}

	/**
	 * @param miTexto the miTexto to set
	 */
	public void setMiTexto(String miTexto) {
		this.miTexto = miTexto;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.validate(mapping, request);
	}

	

}
