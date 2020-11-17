package mx.gob.stps.portal.core.oferta.vo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CurrentOfferAreaOcupacionVO implements Serializable {

	// nombre del área laboral
	String area;
	// número de ofertas para el área laboral
	int areaTotalPlazas;
	// número de ocupaciones que existen agrupadas bajo el área laboral
	int areaNumOcupaciones;
	// array de ocupaciones (pertenecientes al área laboral) y número de ofertas de cada ocupación
	List<Object[]> listaOcupaciones;
	
	public CurrentOfferAreaOcupacionVO() {
		this.area = "";
		this.areaTotalPlazas = 0;
		this.areaNumOcupaciones = 0;
		this.listaOcupaciones = new ArrayList<Object[]>();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getAreaTotalPlazas() {
		return areaTotalPlazas;
	}

	public void setAreaTotalPlazas(int areaTotalPlazas) {
		this.areaTotalPlazas = areaTotalPlazas;
	}

	public int getAreaNumOcupaciones() {
		return this.areaNumOcupaciones;
	}

	public void setAreaNumOcupaciones(int areaNumOcupaciones) {
		this.areaNumOcupaciones = areaNumOcupaciones;
	}

	public List<Object[]> getListaOcupaciones() {
		return listaOcupaciones;
	}

	public void setListaOcupaciones(List<Object[]> listaOcupaciones) {
		this.listaOcupaciones = listaOcupaciones;
	}
	
	public void clearCurrentOfferAreaOcupacion() {
		this.area = "";
		this.areaTotalPlazas = 0;
		this.areaNumOcupaciones = 0;
		this.listaOcupaciones.clear();		
	}
	
	public void addListaOcupaciones(Object[] registroOcupaciones){
		this.listaOcupaciones.add(registroOcupaciones);
	}

	public void addOcupacion(long idOcupacion, String opcupacion){
		this.listaOcupaciones.add(new Object[]{idOcupacion, opcupacion});
	}
}
