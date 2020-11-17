package mx.gob.stps.portal.web.ofertasRSS.form;

import org.apache.struts.action.ActionForm;

public class OfertasRSSForm extends ActionForm{
	private static final long serialVersionUID = -5969841219121254689L;

	private int entidad;
	
	
	public void setEntidad(int entidad) {
		this.entidad = entidad;
	}
	public int getEntidad() {
		return entidad;
	}
}
