package mx.gob.stps.portal.core.search;

import java.io.Serializable;

public class InformacionAcademica implements Serializable {

	
	private static final long serialVersionUID = 6488067013324422371L;
	
	private int grado_estudios=-1;
	private int carrera=-1;
	private int status=-1;
	
	public int getGrado_estudios() {
		return grado_estudios;
	}
	public void setGrado_estudios(int grado_estudios) {
		this.grado_estudios = grado_estudios;
	}
	public int getCarrera() {
		return carrera;
	}
	public void setCarrera(int carrera) {
		this.carrera = carrera;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
