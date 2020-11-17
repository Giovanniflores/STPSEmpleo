package mx.gob.stps.portal.web.oferta.vo;

import java.io.Serializable;

public class ElementoListaVO implements Serializable {
	private static final long serialVersionUID = 3344560855355969115L;
	private String id;
	private String descripcion;
	private String seleccionada;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getSeleccionada() {
		return seleccionada;
	}
	public void setSeleccionada(String seleccionada) {
		this.seleccionada = seleccionada;
	}
	
	
	
}
