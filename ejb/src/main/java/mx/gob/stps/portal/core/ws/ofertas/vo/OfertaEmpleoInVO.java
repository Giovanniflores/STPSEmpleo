package mx.gob.stps.portal.core.ws.ofertas.vo;

import java.io.Serializable;

public class OfertaEmpleoInVO implements Serializable {
	private static final long serialVersionUID = -6708377872401023071L;

	private int entidad;
	
	private String palabra;
	
	private String fecha;
	
	private int busquedaen;
	
	public int getEntidad() {
		return entidad;
	}

	public void setEntidad(int entidad) {
		this.entidad = entidad;
	}

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getBusquedaen() {
		return busquedaen;
	}

	public void setBusquedaen(int busquedaen) {
		this.busquedaen = busquedaen;
	}
}
