/**
 * 
 */
package mx.gob.stps.portal.web.candidate.form;

import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Felipe Juárez Ramírez
 * @since 25/03/2011
 *
 */
public class HistoriaLaboralForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7903855662317877407L;
	
	/**
	 * Id del candidato
	 */
	private long idCandidato;
	/**
	 * numero de detalle puesto
	 */
	private int elementos;
	/**
	 * Consecutivo, se usara solo para borrado
	 */
	private long idHistorialLaboral;
	/**
	 * Detalle de puesto agregado
	 **/
	private int trabajoActual;
	private long idSector;
	private String puesto;
	private long idAreaLaboral;
	private long idOcupacion;
	private String empresa;
	private int confidencialidadEmpresa;
	private String laboresInicial;
	private String laboresFinal;
	private int aniosLaborados;
	private long idJerarquia;
	private int personasCargo;
	private String salarioMensual;
	private String funcion;
	private String logro;
	/**Mensaje*/
	private ResultVO msg;
	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}
	/**
	 * @param idCandidato the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}
	/**
	 * @return the elementos
	 */
	public int getElementos() {
		return elementos;
	}
	/**
	 * @param elementos the elementos to set
	 */
	public void setElementos(int elementos) {
		this.elementos = elementos;
	}
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
	public String getLaboresInicial() {
		return laboresInicial;
	}
	/**
	 * @param laboresInicial the laboresInicial to set
	 */
	public void setLaboresInicial(String laboresInicial) {
		this.laboresInicial = laboresInicial;
	}
	/**
	 * @return the laboresFinal
	 */
	public String getLaboresFinal() {
		return laboresFinal;
	}
	/**
	 * @param laboresFinal the laboresFinal to set
	 */
	public void setLaboresFinal(String laboresFinal) {
		this.laboresFinal = laboresFinal;
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
	 * @return the salarioMensual
	 */
	public String getSalarioMensual() {
		return salarioMensual;
	}
	/**
	 * @param salarioMensual the salarioMensual to set
	 */
	public void setSalarioMensual(String salarioMensual) {
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
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(ResultVO msg) {
		this.msg = msg;
	}
	/**
	 * @return the msg
	 */
	public ResultVO getMsg() {
		return msg;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DetallePuestoForm [idCandidato=");
		builder.append(idCandidato);
		builder.append(", elementos=");
		builder.append(elementos);
		builder.append(", idHistorialLaboral=");
		builder.append(idHistorialLaboral);
		builder.append(", trabajoActual=");
		builder.append(trabajoActual);
		builder.append(", idSector=");
		builder.append(idSector);
		builder.append(", puesto=");
		builder.append(puesto);
		builder.append(", idAreaLaboral=");
		builder.append(idAreaLaboral);
		builder.append(", idOcupacion=");
		builder.append(idOcupacion);
		builder.append(", empresa=");
		builder.append(empresa);
		builder.append(", confidencialidadEmpresa=");
		builder.append(confidencialidadEmpresa);
		builder.append(", laboresInicial=");
		builder.append(laboresInicial);
		builder.append(", laboresFinal=");
		builder.append(laboresFinal);
		builder.append(", aniosLaborados=");
		builder.append(aniosLaborados);
		builder.append(", idJerarquia=");
		builder.append(idJerarquia);
		builder.append(", personasCargo=");
		builder.append(personasCargo);
		builder.append(", salarioMensual=");
		builder.append(salarioMensual);
		builder.append(", funcion=");
		builder.append(funcion);
		builder.append(", logro=");
		builder.append(logro);
		builder.append("]");
		return builder.toString();
	}
	
}
