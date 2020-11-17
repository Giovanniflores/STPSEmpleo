package mx.gob.stps.portal.core.persistencia.facade;

import java.io.Serializable;

public class QuejasGabssaLocal implements Serializable{
	
	private String err;
	private int contacto_portal_id;
	
	private static final long serialVersionUID = -6558784347371780702L;

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public int getContacto_portal_id() {
		return contacto_portal_id;
	}

	public void setContacto_portal_id(int contacto_portal_id) {
		this.contacto_portal_id = contacto_portal_id;
	}
	
}
