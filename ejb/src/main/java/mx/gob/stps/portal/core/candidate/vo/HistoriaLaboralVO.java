/**
 * 
 */
package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Felipe Juárez Ramírez
 * @since 15/03/2011
 *
 */
public class HistoriaLaboralVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3966715574735649602L;
	private static String formatPattern = "dd/MM/yyyy";
	
	private long idCandidato;
	private long idHistorialLaboral;
	private int trabajoActual;
	private long idSector;
	private String sector;
	private String puesto;
	private long idAreaLaboral;
	private String areaLaboral;
	private long idOcupacion;
	private String ocupacion;
	private String empresa;
	private int confidencialidadEmpresa;
	private Date laboresInicial;
	private String fechaIni;
	private Date laboresFinal;
	private String fechaFin;
	private int aniosLaborados;
	private String anios;
	private long idJerarquia;
	private String jerarquia;
	private int personasCargo;
	private String personas;
	private double salarioMensual;
	private String funcion;
	private String logro;
	protected int principal;
	
	private String refNombre;
	
	private String refApellido1;
	
	private String refApellido2;
	
	private String refPuesto;
	
	private String herramientas;
	
	/**
	 * @return the idHistorialLaboral
	 */
	public long getIdHistorialLaboral() {
		return idHistorialLaboral;
	}
	/**
	 * @param idHistorialLaboral the idHistorialLaboral to set
	 */
	public void setIdHistorialLaboral(long idHistorialLaboral) {
		this.idHistorialLaboral = idHistorialLaboral;
	}
	/**
	 * @return the trabajoActual
	 */
	public int getTrabajoActual() {
		return trabajoActual;
	}
	/**
	 * @param trabajoActual the trabajoActual to set
	 */
	public void setTrabajoActual(int trabajoActual) {
		this.trabajoActual = trabajoActual;
	}
	/**
	 * @return the idSector
	 */
	public long getIdSector() {
		return idSector;
	}
	/**
	 * @param idSector the idSector to set
	 */
	public void setIdSector(long idSector) {
		this.idSector = idSector;
	}
	/**
	 * @param sector the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}
	/**
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}
	/**
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}
	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	/**
	 * @return the idAreaLaboral
	 */
	public long getIdAreaLaboral() {
		return idAreaLaboral;
	}
	/**
	 * @param idAreaLaboral the idAreaLaboral to set
	 */
	public void setIdAreaLaboral(long idAreaLaboral) {
		this.idAreaLaboral = idAreaLaboral;
	}
	/**
	 * @param areaLaboral the areaLaboral to set
	 */
	public void setAreaLaboral(String areaLaboral) {
		this.areaLaboral = areaLaboral;
	}
	/**
	 * @return the areaLaboral
	 */
	public String getAreaLaboral() {
		return areaLaboral;
	}
	/**
	 * @return the idOcupacion
	 */
	public long getIdOcupacion() {
		return idOcupacion;
	}
	/**
	 * @param idOcupacion the idOcupacion to set
	 */
	public void setIdOcupacion(long idOcupacion) {
		this.idOcupacion = idOcupacion;
	}
	/**
	 * @param ocupacion the ocupacion to set
	 */
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	/**
	 * @return the ocupacion
	 */
	public String getOcupacion() {
		return ocupacion;
	}
	/**
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}
	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	/**
	 * @return the confidencialidadEmpresa
	 */
	public int getConfidencialidadEmpresa() {
		return confidencialidadEmpresa;
	}
	/**
	 * @param confidencialidadEmpresa the confidencialidadEmpresa to set
	 */
	public void setConfidencialidadEmpresa(int confidencialidadEmpresa) {
		this.confidencialidadEmpresa = confidencialidadEmpresa;
	}
	/**
	 * @return the laboresInicial
	 */
	public Date getLaboresInicial() {
		return laboresInicial;
	}
	/**
	 * @param laboresInicial the laboresInicial to set
	 */
	public void setLaboresInicial(Date laboresInicial) {
		this.laboresInicial = laboresInicial;
		SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
		if (null != laboresInicial)
			this.setFechaIni(formatter.format(laboresInicial));
	}
	/**
	 * @param fechaIni the fechaIni to set
	 */
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}
	/**
	 * @return the fechaIni
	 */
	public String getFechaIni() {
		return fechaIni;
	}
	/**
	 * @return the laboresFinal
	 */
	public Date getLaboresFinal() {
		return laboresFinal;
	}
	/**
	 * @param laboresFinal the laboresFinal to set
	 */
	public void setLaboresFinal(Date laboresFinal) {
		this.laboresFinal = laboresFinal;
		SimpleDateFormat formatter =
            new SimpleDateFormat(formatPattern);
		if (null != laboresFinal)
		this.setFechaFin(formatter.format(laboresFinal));
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @return the aniosLaborados
	 */
	public int getAniosLaborados() {
		return aniosLaborados;
	}
	/**
	 * @param aniosLaborados the aniosLaborados to set
	 */
	public void setAniosLaborados(int aniosLaborados) {
		this.aniosLaborados = aniosLaborados;
	}
	/**
	 * @param anios the anios to set
	 */
	public void setAnios(String anios) {
		this.anios = anios;
	}
	/**
	 * @return the anios
	 */
	public String getAnios() {
		return anios;
	}
	/**
	 * @return the idJerarquia
	 */
	public long getIdJerarquia() {
		return idJerarquia;
	}
	/**
	 * @param idJerarquia the idJerarquia to set
	 */
	public void setIdJerarquia(long idJerarquia) {
		this.idJerarquia = idJerarquia;
	}
	/**
	 * @param jerarquia the jerarquia to set
	 */
	public void setJerarquia(String jerarquia) {
		this.jerarquia = jerarquia;
	}
	/**
	 * @return the jerarquia
	 */
	public String getJerarquia() {
		return jerarquia;
	}
	/**
	 * @return the personasCargo
	 */
	public int getPersonasCargo() {
		return personasCargo;
	}
	/**
	 * @param personasCargo the personasCargo to set
	 */
	public void setPersonasCargo(int personasCargo) {
		this.personasCargo = personasCargo;
	}
	/**
	 * @param personas the personas to set
	 */
	public void setPersonas(String personas) {
		this.personas = personas;
	}
	/**
	 * @return the personas
	 */
	public String getPersonas() {
		return personas;
	}
	/**
	 * @return the salarioMensual
	 */
	public double getSalarioMensual() {
		return salarioMensual;
	}
	/**
	 * @param salarioMensual the salarioMensual to set
	 */
	public void setSalarioMensual(double salarioMensual) {
		this.salarioMensual = salarioMensual;
	}
	/**
	 * @return the funcion
	 */
	public String getFuncion() {
		return funcion;
	}
	/**
	 * @param funcion the funcion to set
	 */
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	/**
	 * @return the logro
	 */
	public String getLogro() {
		return logro;
	}
	/**
	 * @param logro the logro to set
	 */
	public void setLogro(String logro) {
		this.logro = logro;
	}
	public int getPrincipal() {
		return principal;
	}
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	
	public String getHerramientas() {
		return herramientas;
	}
	public void setHerramientas(String herramientas) {
		this.herramientas = herramientas;
	}
	public String getRefNombre() {
		return refNombre;
	}
	public void setRefNombre(String refNombre) {
		this.refNombre = refNombre;
	}
	public String getRefApellido1() {
		return refApellido1;
	}
	public void setRefApellido1(String refApellido1) {
		this.refApellido1 = refApellido1;
	}
	public String getRefApellido2() {
		return refApellido2;
	}
	public void setRefApellido2(String refApellido2) {
		this.refApellido2 = refApellido2;
	}
	public String getRefPuesto() {
		return refPuesto;
	}
	public void setRefPuesto(String refPuesto) {
		this.refPuesto = refPuesto;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public String toBitacora() {
		return "idHistorialLaboral=" + idHistorialLaboral + "|trabajoActual="
				+ trabajoActual + "|idSector=" + idSector + "|puesto=" + puesto
				+ "|idAreaLaboral=" + idAreaLaboral + "|idOcupacion="
				+ idOcupacion + "|empresa=" + empresa
				+ "|confidencialidadEmpresa=" + confidencialidadEmpresa
				+ "|laboresInicial=" + laboresInicial + "|laboresFinal="
				+ laboresFinal + "|aniosLaborados=" + aniosLaborados
				+ "|idJerarquia=" + idJerarquia + "|personasCargo="
				+ personasCargo + "|salarioMensual=" + salarioMensual
				+ "|funcion=" + funcion + "|logro=" + logro + "|principal="
				+ principal;
	}
	
	@Override
	public String toString() {
		return "HistoriaLaboralVO [idHistorialLaboral=" + idHistorialLaboral
				+ ", trabajoActual=" + trabajoActual + ", idSector=" + idSector
				+ ", sector=" + sector + ", puesto=" + puesto
				+ ", idAreaLaboral=" + idAreaLaboral + ", areaLaboral="
				+ areaLaboral + ", idOcupacion=" + idOcupacion + ", ocupacion="
				+ ocupacion + ", empresa=" + empresa
				+ ", confidencialidadEmpresa=" + confidencialidadEmpresa
				+ ", laboresInicial=" + laboresInicial + ", fechaIni="
				+ fechaIni + ", laboresFinal=" + laboresFinal + ", fechaFin="
				+ fechaFin + ", aniosLaborados=" + aniosLaborados + ", anios="
				+ anios + ", idJerarquia=" + idJerarquia + ", jerarquia="
				+ jerarquia + ", personasCargo=" + personasCargo
				+ ", personas=" + personas + ", salarioMensual="
				+ salarioMensual + ", funcion=" + funcion + ", logro=" + logro
				+ ", principal=" + principal + ", refNombre=" + refNombre
				+ ", refApellido1=" + refApellido1 + ", refApellido2="
				+ refApellido2 + ", refPuesto=" + refPuesto + ", herramientas="
				+ herramientas + "]";
	}
	public long getIdCandidato() {
		return idCandidato;
	}
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
}
