/**
 * 
 */
package mx.gob.stps.portal.web.candidate.form;

import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionForm;

/**
 * @author Felipe Juarez Ramirez
 * @since 10/03/2011
 *
 */
public class ExperienciaForm extends PPCStatusForm {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = -6676213119238649714L;

	/**
	 * Id's
	 * */
	private long idCandidato;
	private long idHistorialLaboral;
	/* PERFIL_LABORAL */
	private long idExperienciaTotal;
	private long idSectorMayorExpr;
	private String puestoMayorExpr;
	private long idAreaLaboralMayorExpr;
	private long idOcupacionMayorExpr;
	/* HISTORIA_LABORAL */
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
	private ResultVO msg;
	private Integer sinExperiencia;
	private boolean isNuncaHeTrabajado = true;

	private String laboresInicialAux;
	private String laboresFinalAux;
	/** Expectativa Laboral **/
	private long idExpectativaLaboral;
	private double salarioPretendido;
	// private long idTipoEmpleoDeseado;
	private long idTipoContrato;
	private int disponibilidadViajar;
	private int disponibilidadRadicar;
	private long idOcupacionDeseada;
	private String ocupacionDeseada;
	private int idExperienciaOcupacion;
	
	private String puestoDeseado;
	private String experiencia;

	/** Segundo expectativa Laboral **/

	private long idExpectativaLaboral2;
	private double salarioPretendido2;
	private long idTipoEmpleoDeseado2;
	private long idTipoContrato2;
	private int disponibilidadViajar2;
	private int disponibilidadRadicar2;
	private long idOcupacionDeseada2;
	private String ocupacionDeseada2;
	private int idExperienciaOcupacion2;
	
	
	private String puestoDeseado2;
	private String experiencia2;

	
	
	/**
	 * @return the idCandidato
	 */
	public long getIdCandidato() {
		return idCandidato;
	}

	/**
	 * @param idCandidato
	 *            the idCandidato to set
	 */
	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	/**
	 * @return the idHistorialLaboral
	 */
	public long getIdHistorialLaboral() {
		return idHistorialLaboral;
	}

	/**
	 * @param idHistorialLaboral
	 *            the idHistorialLaboral to set
	 */
	public void setIdHistorialLaboral(long idHistorialLaboral) {
		this.idHistorialLaboral = idHistorialLaboral;
	}

	/**
	 * @return the idExperienciaTotal
	 */
	public long getIdExperienciaTotal() {
		return idExperienciaTotal;
	}

	/**
	 * @param idExperienciaTotal
	 *            the idExperienciaTotal to set
	 */
	public void setIdExperienciaTotal(long idExperienciaTotal) {
		this.idExperienciaTotal = idExperienciaTotal;
	}

	/**
	 * @return the idSectorMayorExpr
	 */
	public long getIdSectorMayorExpr() {
		return idSectorMayorExpr;
	}

	/**
	 * @param idSectorMayorExpr
	 *            the idSectorMayorExpr to set
	 */
	public void setIdSectorMayorExpr(long idSectorMayorExpr) {
		this.idSectorMayorExpr = idSectorMayorExpr;
	}

	/**
	 * @return the puestoMayorExpr
	 */
	public String getPuestoMayorExpr() {
		if (null == this.puestoMayorExpr)
			this.puestoMayorExpr = "";
		return puestoMayorExpr;
	}

	/**
	 * @param puestoMayorExpr
	 *            the puestoMayorExpr to set
	 */
	public void setPuestoMayorExpr(String puestoMayorExpr) {
		this.puestoMayorExpr = puestoMayorExpr;
	}

	/**
	 * @return the idAreaLaboralMayorExpr
	 */
	public long getIdAreaLaboralMayorExpr() {
		return idAreaLaboralMayorExpr;
	}

	/**
	 * @param idAreaLaboralMayorExpr
	 *            the idAreaLaboralMayorExpr to set
	 */
	public void setIdAreaLaboralMayorExpr(long idAreaLaboralMayorExpr) {
		this.idAreaLaboralMayorExpr = idAreaLaboralMayorExpr;
	}

	/**
	 * @return the idOcupacionMayorExpr
	 */
	public long getIdOcupacionMayorExpr() {
		return idOcupacionMayorExpr;
	}

	/**
	 * @param idOcupacionMayorExpr
	 *            the idOcupacionMayorExpr to set
	 */
	public void setIdOcupacionMayorExpr(long idOcupacionMayorExpr) {
		this.idOcupacionMayorExpr = idOcupacionMayorExpr;
	}

	/**
	 * @return the trabajoActual
	 */
	public int getTrabajoActual() {
		return trabajoActual;
	}

	/**
	 * @param trabajoActual
	 *            the trabajoActual to set
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
	 * @param idSector
	 *            the idSector to set
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
	 * @param puesto
	 *            the puesto to set
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
	 * @param idAreaLaboral
	 *            the idAreaLaboral to set
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
	 * @param idOcupacion
	 *            the idOcupacion to set
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
	 * @param empresa
	 *            the empresa to set
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
	 * @param confidencialidadEmpresa
	 *            the confidencialidadEmpresa to set
	 */
	public void setConfidencialidadEmpresa(int confidencialidadEmpresa) {
		this.confidencialidadEmpresa = confidencialidadEmpresa;
	}

	/**
	 * @return the laboresInicial
	 */
	public String getLaboresInicial() {
		if (null == this.laboresInicial)
			this.laboresInicial = "";
		return laboresInicial;
	}

	/**
	 * @param laboresInicial
	 *            the laboresInicial to set
	 */
	public void setLaboresInicial(String laboresInicial) {
		this.laboresInicial = laboresInicial;
	}

	/**
	 * @return the laboresFinal
	 */
	public String getLaboresFinal() {
		if (null == this.laboresFinal)
			this.laboresFinal = "";
		return laboresFinal;
	}

	/**
	 * @param laboresFinal
	 *            the laboresFinal to set
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
	 * @param aniosLaborados
	 *            the aniosLaborados to set
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
	 * @param idJerarquia
	 *            the idJerarquia to set
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
	 * @param personasCargo
	 *            the personasCargo to set
	 */
	public void setPersonasCargo(int personasCargo) {
		this.personasCargo = personasCargo;
	}

	/**
	 * @return the salarioMensual
	 */
	public String getSalarioMensual() {
		return Utils.formatDecimal(this.salarioMensual);

	}

	/**
	 * @param salarioMensual
	 *            the salarioMensual to set
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
	 * @param funcion
	 *            the funcion to set
	 */
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	/**
	 * @return the logro
	 */
	public String getLogro() {
		if (null == this.logro)
			this.logro = "";
		return logro;
	}

	/**
	 * @param logro
	 *            the logro to set
	 */
	public void setLogro(String logro) {
		this.logro = logro;
	}

	public void setMsg(ResultVO msg) {
		this.msg = msg;
	}

	public ResultVO getMsg() {
		return msg;
	}

	/**
	 * @param sinExperiencia
	 *            the sinExperiencia to set
	 */
	public void setSinExperiencia(Integer sinExperiencia) {
		this.sinExperiencia = sinExperiencia;
	}

	/**
	 * @return the sinExperiencia
	 */
	public Integer getSinExperiencia() {
		return sinExperiencia;
	}

	/**
	 * @param isNuncaHeTrabajado
	 *            the isNuncaHeTrabajado to set
	 */
	public void setNuncaHeTrabajado(boolean isNuncaHeTrabajado) {
		this.isNuncaHeTrabajado = isNuncaHeTrabajado;
	}

	/**
	 * @return the isNuncaHeTrabajado
	 */
	public boolean isNuncaHeTrabajado() {
		return isNuncaHeTrabajado;
	}

	public String getLaboresFinalAux() {
		if (null == this.laboresFinalAux)
			this.laboresFinalAux = "";
		return laboresFinalAux;
	}

	public void setLaboresFinalAux(String laboresFinalAux) {
		this.laboresFinalAux = laboresFinalAux;
	}

	/**
	 * @param laboresInicialAux
	 *            the laboresInicialAux to set
	 */
	public void setLaboresInicialAux(String laboresInicialAux) {
		this.laboresInicialAux = laboresInicialAux;
	}

	/**
	 * @return the laboresInicialAux
	 */
	public String getLaboresInicialAux() {
		if (null == this.laboresInicialAux)
			this.laboresInicialAux = "";
		return laboresInicialAux;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "ExperienciaForm [idCandidato=" + idCandidato + ", idHistorialLaboral=" + idHistorialLaboral
				+ ", idExperienciaTotal=" + idExperienciaTotal + ", idSectorMayorExpr=" + idSectorMayorExpr
				+ ", puestoMayorExpr=" + puestoMayorExpr + ", idAreaLaboralMayorExpr=" + idAreaLaboralMayorExpr
				+ ", idOcupacionMayorExpr=" + idOcupacionMayorExpr + ", trabajoActual=" + trabajoActual + ", idSector="
				+ idSector + ", puesto=" + puesto + ", idAreaLaboral=" + idAreaLaboral + ", idOcupacion=" + idOcupacion
				+ ", empresa=" + empresa + ", confidencialidadEmpresa=" + confidencialidadEmpresa + ", laboresInicial="
				+ laboresInicial + ", laboresFinal=" + laboresFinal + ", aniosLaborados=" + aniosLaborados
				+ ", idJerarquia=" + idJerarquia + ", personasCargo=" + personasCargo + ", salarioMensual="
				+ salarioMensual + ", funcion=" + funcion + ", logro=" + logro + ", msg=" + msg + ", sinExperiencia="
				+ sinExperiencia + ", isNuncaHeTrabajado=" + isNuncaHeTrabajado + "]";
	}

	/**
	 * @return the idExpectativaLaboral
	 */
	public long getIdExpectativaLaboral() {
		return idExpectativaLaboral;
	}

	/**
	 * @param idExpectativaLaboral
	 *            the idExpectativaLaboral to set
	 */
	public void setIdExpectativaLaboral(long idExpectativaLaboral) {
		this.idExpectativaLaboral = idExpectativaLaboral;
	}

	/**
	 * @return the salarioPretendido
	 */
	public double getSalarioPretendido() {
		return salarioPretendido;
	}

	/**
	 * @param salarioPretendido
	 *            the salarioPretendido to set
	 */
	public void setSalarioPretendido(double salarioPretendido) {
		this.salarioPretendido = salarioPretendido;
	}

	// /**
	// * @return the idTipoEmpleoDeseado
	// */
	// public long getIdTipoEmpleoDeseado() {
	// return idTipoEmpleoDeseado;
	// }
	// /**
	// * @param idTipoEmpleoDeseado the idTipoEmpleoDeseado to set
	// */
	// public void setIdTipoEmpleoDeseado(long idTipoEmpleoDeseado) {
	// this.idTipoEmpleoDeseado = idTipoEmpleoDeseado;
	// }
	/**
	 * @return the idTipoContrato
	 */
	public long getIdTipoContrato() {
		return idTipoContrato;
	}

	/**
	 * @param idTipoContrato
	 *            the idTipoContrato to set
	 */
	public void setIdTipoContrato(long idTipoContrato) {
		this.idTipoContrato = idTipoContrato;
	}

	/**
	 * @return the disponibilidadViajar
	 */
	public int getDisponibilidadViajar() {
		return disponibilidadViajar;
	}

	/**
	 * @param disponibilidadViajar
	 *            the disponibilidadViajar to set
	 */
	public void setDisponibilidadViajar(int disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}

	/**
	 * @return the disponibilidadRadicar
	 */
	public int getDisponibilidadRadicar() {
		return disponibilidadRadicar;
	}

	/**
	 * @param disponibilidadRadicar
	 *            the disponibilidadRadicar to set
	 */
	public void setDisponibilidadRadicar(int disponibilidadRadicar) {
		this.disponibilidadRadicar = disponibilidadRadicar;
	}

	/**
	 * @return the idOcupacionDeseada
	 */
	public long getIdOcupacionDeseada() {
		return idOcupacionDeseada;
	}

	/**
	 * @param idOcupacionDeseada
	 *            the idOcupacionDeseada to set
	 */
	public void setIdOcupacionDeseada(long idOcupacionDeseada) {
		this.idOcupacionDeseada = idOcupacionDeseada;
	}

	/**
	 * @return the ocupacionDeseada
	 */
	public String getOcupacionDeseada() {
		return ocupacionDeseada;
	}

	/**
	 * @param ocupacionDeseada
	 *            the ocupacionDeseada to set
	 */
	public void setOcupacionDeseada(String ocupacionDeseada) {
		this.ocupacionDeseada = ocupacionDeseada;
	}

	/**
	 * @return the idExperienciaOcupacion
	 */
	public int getIdExperienciaOcupacion() {
		return idExperienciaOcupacion;
	}

	/**
	 * @param idExperienciaOcupacion
	 *            the idExperienciaOcupacion to set
	 */
	public void setIdExperienciaOcupacion(int idExperienciaOcupacion) {
		this.idExperienciaOcupacion = idExperienciaOcupacion;
	}

	/**
	 * @return the puestoDeseado
	 */
	public String getPuestoDeseado() {
		return puestoDeseado;
	}

	/**
	 * @param puestoDeseado
	 *            the puestoDeseado to set
	 */
	public void setPuestoDeseado(String puestoDeseado) {
		this.puestoDeseado = puestoDeseado;
	}

	/**
	 * @return the experiencia
	 */
	public String getExperiencia() {
		return experiencia;
	}

	/**
	 * @param experiencia
	 *            the experiencia to set
	 */
	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public long getIdExpectativaLaboral2() {
		return idExpectativaLaboral2;
	}

	public void setIdExpectativaLaboral2(long idExpectativaLaboral2) {
		this.idExpectativaLaboral2 = idExpectativaLaboral2;
	}

	public double getSalarioPretendido2() {
		return salarioPretendido2;
	}

	public void setSalarioPretendido2(double salarioPretendido2) {
		this.salarioPretendido2 = salarioPretendido2;
	}

	public long getIdTipoEmpleoDeseado2() {
		return idTipoEmpleoDeseado2;
	}

	public void setIdTipoEmpleoDeseado2(long idTipoEmpleoDeseado2) {
		this.idTipoEmpleoDeseado2 = idTipoEmpleoDeseado2;
	}

	public long getIdTipoContrato2() {
		return idTipoContrato2;
	}

	public void setIdTipoContrato2(long idTipoContrato2) {
		this.idTipoContrato2 = idTipoContrato2;
	}

	public int getDisponibilidadViajar2() {
		return disponibilidadViajar2;
	}

	public void setDisponibilidadViajar2(int disponibilidadViajar2) {
		this.disponibilidadViajar2 = disponibilidadViajar2;
	}

	public int getDisponibilidadRadicar2() {
		return disponibilidadRadicar2;
	}

	public void setDisponibilidadRadicar2(int disponibilidadRadicar2) {
		this.disponibilidadRadicar2 = disponibilidadRadicar2;
	}

	public long getIdOcupacionDeseada2() {
		return idOcupacionDeseada2;
	}

	public void setIdOcupacionDeseada2(long idOcupacionDeseada2) {
		this.idOcupacionDeseada2 = idOcupacionDeseada2;
	}

	public String getOcupacionDeseada2() {
		return ocupacionDeseada2;
	}

	public void setOcupacionDeseada2(String ocupacionDeseada2) {
		this.ocupacionDeseada2 = ocupacionDeseada2;
	}

	public int getIdExperienciaOcupacion2() {
		return idExperienciaOcupacion2;
	}

	public void setIdExperienciaOcupacion2(int idExperienciaOcupacion2) {
		this.idExperienciaOcupacion2 = idExperienciaOcupacion2;
	}

	public String getPuestoDeseado2() {
		return puestoDeseado2;
	}

	public void setPuestoDeseado2(String puestoDeseado2) {
		this.puestoDeseado2 = puestoDeseado2;
	}

	public String getExperiencia2() {
		return experiencia2;
	}

	public void setExperiencia2(String experiencia2) {
		this.experiencia2 = experiencia2;
	}

}
