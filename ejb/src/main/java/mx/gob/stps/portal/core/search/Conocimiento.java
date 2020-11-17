package mx.gob.stps.portal.core.search;

import java.io.Serializable;

public class Conocimiento implements Serializable {

	private static final long serialVersionUID = -5957916466956485742L;
	
	private String name;
	private int experiencia=-1;
	
	public Conocimiento(String name, int experiencia) {
		this.name = name;
		this.experiencia = experiencia;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getExperiencia() {
		return experiencia;
	}
	
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	
	public String toString() {
		return "[" + getName() + " " + getExperiencia() + " ]";
	}
}