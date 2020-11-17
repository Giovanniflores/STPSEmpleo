package mx.gob.stps.portal.web.jobvacancies.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.List;

public class BolsasTrabajoForm extends ActionForm  {
	private static final long serialVersionUID = 4917562754848356656L;

	private int entidad;
	private String palabra;
	private String fecha;
	private int busquedaen;
	private Long idOcupacion;
	private Long idOferta;
	private boolean fromPortal;

	// --- Hispavista ---
	private String hispavistaState;
	private String hispavistaIdState;
	private String hispavistaIdArea;
	private String hispavistaIdProfession;


	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		entidad = 0;
		palabra = null;
		fecha = null;
		busquedaen = 0;
		idOcupacion = null;

		// --- Hispavista ---
		hispavistaState = null;
		hispavistaIdState = null;
		hispavistaIdArea = null;
		hispavistaIdProfession = null;
//		hispavistaIdProfessions = null;	// fill from REST WS
	}
	
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

	public void setBusquedaen(int busquedaen) {
		this.busquedaen = busquedaen;
	}

	public int getBusquedaen() {
		return busquedaen;
	}

	public Long getIdOcupacion() {
		return idOcupacion;
	}

	public void setIdOcupacion(Long idOcupacion) {
		this.idOcupacion = idOcupacion;
	}

	public Long getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Long idOferta) {
		this.idOferta = idOferta;
	}

	public boolean isFromPortal() {
		return fromPortal;
	}

	public void setFromPortal(boolean fromPortal) {
		this.fromPortal = fromPortal;
	}

	// --- Hispavista ---

	public String getHispavistaState() {
		return hispavistaState;
	}

	public void setHispavistaState(String hispavistaState) {
		this.hispavistaState = hispavistaState;
	}

	public String getHispavistaIdState() {
		return hispavistaIdState;
	}

	public void setHispavistaIdState(String hispavistaIdState) {
		this.hispavistaIdState = hispavistaIdState;
	}

	public String getHispavistaIdArea() {
		return hispavistaIdArea;
	}

	public void setHispavistaIdArea(String hispavistaIdArea) {
		this.hispavistaIdArea = hispavistaIdArea;
	}

	public String getHispavistaIdProfession() {
		return hispavistaIdProfession;
	}

	public void setHispavistaIdProfession(String hispavistaIdProfession) {
		this.hispavistaIdProfession = hispavistaIdProfession;
	}

	@Override
	public String toString() {
		return "BolsasTrabajoForm [entidad=" + entidad + ", palabra=" + palabra
				+ ", fecha=" + fecha + ", busquedaen=" + busquedaen
				+ ", idOcupacion=" + idOcupacion + ", idOferta=" + idOferta
				+ ", fromPortal=" + fromPortal + "]";
	}

}