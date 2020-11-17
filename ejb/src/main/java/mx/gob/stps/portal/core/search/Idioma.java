package mx.gob.stps.portal.core.search;

import java.io.Serializable;

public class Idioma implements Serializable {

	private static final long serialVersionUID = 2102334997555171121L;
	
	private int id = -1;   
	private int dominio_id = -1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDominio_id() {
		return dominio_id;
	}
	public void setDominio_id(int dominio_id) {
		this.dominio_id = dominio_id;
	}
}
