package mx.gob.stps.portal.web.candidate.form;

import org.apache.struts.action.ActionForm;

public class CalculadoraForm extends ActionForm{
	private static final long serialVersionUID = 6934364801260971561L;
	private double total;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}