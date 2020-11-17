/**
 * 
 */
package mx.gob.stps.portal.web.candidate.vo;

import java.io.Serializable;

import mx.gob.stps.portal.web.infra.vo.ResultVO;

public class EscolaridadVOForm implements Serializable {
	private static final long serialVersionUID = 5513288670522104555L;

	private long idCandidato;
	private int sinEstudios;
	private long idCandidatoGradoAcademico;
	private long idNivelEstudio;
	private long idSituacionAcademica;
	private long idCarreraEspecialidad;
	private String escuela;
	private String inicio;
	private String fin;
	private long idCandidatoIdioma;
	private long idIdioma;
	private long idCertificacion;
	private long idDominio;
	private long idCandidatoConocimiento;
	private long idTipoConocimiento;
	private String conocimiento;
	private long idExperienciaCon;
	private long idDominioCon;
	private String descripcionCon;
	private long idCandidatoHabilidad;
	private long idTipoHabilidad;
	private String habilidad;
	private long idExperienciaHab;
	private long idDominioHab;
	private String descripcionHab;
	private int computacionBasica;
	private long idExperienciaCompu;
	private long idDominioCompu;
	private long idExperienciaOffice;
	private long idDominioOffice;
	private long idExperienciaInternet;
	private long idDominioInternet;
	private int computacionAvanzada;
	private long idCandidatoCompuAvanzada;
	private String softwareHardware;
	private long idExperiencia;
	private long idDominioCmp;
	private String descripcion;
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
	 * @return the sinEstudios
	 */
	public int getSinEstudios() {
		return sinEstudios;
	}
	/**
	 * @param sinEstudios the sinEstudios to set
	 */
	public void setSinEstudios(int sinEstudios) {
		this.sinEstudios = sinEstudios;
	}
	/**
	 * @return the idCandidatoGradoAcademico
	 */
	public long getIdCandidatoGradoAcademico() {
		return idCandidatoGradoAcademico;
	}
	/**
	 * @param idCandidatoGradoAcademico the idCandidatoGradoAcademico to set
	 */
	public void setIdCandidatoGradoAcademico(long idCandidatoGradoAcademico) {
		this.idCandidatoGradoAcademico = idCandidatoGradoAcademico;
	}
	/**
	 * @return the idNivelEstudio
	 */
	public long getIdNivelEstudio() {
		return idNivelEstudio;
	}
	/**
	 * @param idNivelEstudio the idNivelEstudio to set
	 */
	public void setIdNivelEstudio(long idNivelEstudio) {
		this.idNivelEstudio = idNivelEstudio;
	}
	/**
	 * @return the idSituacionAcademica
	 */
	public long getIdSituacionAcademica() {
		return idSituacionAcademica;
	}
	/**
	 * @param idSituacionAcademica the idSituacionAcademica to set
	 */
	public void setIdSituacionAcademica(long idSituacionAcademica) {
		this.idSituacionAcademica = idSituacionAcademica;
	}
	/**
	 * @return the idCarreraEspecialidad
	 */
	public long getIdCarreraEspecialidad() {
		return idCarreraEspecialidad;
	}
	/**
	 * @param idCarreraEspecialidad the idCarreraEspecialidad to set
	 */
	public void setIdCarreraEspecialidad(long idCarreraEspecialidad) {
		this.idCarreraEspecialidad = idCarreraEspecialidad;
	}
	/**
	 * @return the escuela
	 */
	public String getEscuela() {
		return escuela;
	}
	/**
	 * @param escuela the escuela to set
	 */
	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}
	/**
	 * @return the inicio
	 */
	public String getInicio() {
		return inicio;
	}
	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	/**
	 * @return the fin
	 */
	public String getFin() {
		return fin;
	}
	/**
	 * @param fin the fin to set
	 */
	public void setFin(String fin) {
		this.fin = fin;
	}
	/**
	 * @return the idCandidatoIdioma
	 */
	public long getIdCandidatoIdioma() {
		return idCandidatoIdioma;
	}
	/**
	 * @param idCandidatoIdioma the idCandidatoIdioma to set
	 */
	public void setIdCandidatoIdioma(long idCandidatoIdioma) {
		this.idCandidatoIdioma = idCandidatoIdioma;
	}
	/**
	 * @return the idIdioma
	 */
	public long getIdIdioma() {
		return idIdioma;
	}
	/**
	 * @param idIdioma the idIdioma to set
	 */
	public void setIdIdioma(long idIdioma) {
		this.idIdioma = idIdioma;
	}
	/**
	 * @return the idCertificacion
	 */
	public long getIdCertificacion() {
		return idCertificacion;
	}
	/**
	 * @param idCertificacion the idCertificacion to set
	 */
	public void setIdCertificacion(long idCertificacion) {
		this.idCertificacion = idCertificacion;
	}
	/**
	 * @return the idDominio
	 */
	public long getIdDominio() {
		return idDominio;
	}
	/**
	 * @param idDominio the idDominio to set
	 */
	public void setIdDominio(long idDominio) {
		this.idDominio = idDominio;
	}
	/**
	 * @return the idCandidatoConocimiento
	 */
	public long getIdCandidatoConocimiento() {
		return idCandidatoConocimiento;
	}
	/**
	 * @param idCandidatoConocimiento the idCandidatoConocimiento to set
	 */
	public void setIdCandidatoConocimiento(long idCandidatoConocimiento) {
		this.idCandidatoConocimiento = idCandidatoConocimiento;
	}
	/**
	 * @return the idTipoConocimiento
	 */
	public long getIdTipoConocimiento() {
		return idTipoConocimiento;
	}
	/**
	 * @param idTipoConocimiento the idTipoConocimiento to set
	 */
	public void setIdTipoConocimiento(long idTipoConocimiento) {
		this.idTipoConocimiento = idTipoConocimiento;
	}
	/**
	 * @return the conocimiento
	 */
	public String getConocimiento() {
		return conocimiento;
	}
	/**
	 * @param conocimiento the conocimiento to set
	 */
	public void setConocimiento(String conocimiento) {
		this.conocimiento = conocimiento;
	}
	/**
	 * @return the idExperienciaCon
	 */
	public long getIdExperienciaCon() {
		return idExperienciaCon;
	}
	/**
	 * @param idExperienciaCon the idExperienciaCon to set
	 */
	public void setIdExperienciaCon(long idExperienciaCon) {
		this.idExperienciaCon = idExperienciaCon;
	}
	/**
	 * @return the idDominioCon
	 */
	public long getIdDominioCon() {
		return idDominioCon;
	}
	/**
	 * @param idDominioCon the idDominioCon to set
	 */
	public void setIdDominioCon(long idDominioCon) {
		this.idDominioCon = idDominioCon;
	}
	/**
	 * @return the descripcionCon
	 */
	public String getDescripcionCon() {
		return descripcionCon;
	}
	/**
	 * @param descripcionCon the descripcionCon to set
	 */
	public void setDescripcionCon(String descripcionCon) {
		this.descripcionCon = descripcionCon;
	}
	/**
	 * @return the idCandidatoHabilidad
	 */
	public long getIdCandidatoHabilidad() {
		return idCandidatoHabilidad;
	}
	/**
	 * @param idCandidatoHabilidad the idCandidatoHabilidad to set
	 */
	public void setIdCandidatoHabilidad(long idCandidatoHabilidad) {
		this.idCandidatoHabilidad = idCandidatoHabilidad;
	}
	/**
	 * @return the idTipoHabilidad
	 */
	public long getIdTipoHabilidad() {
		return idTipoHabilidad;
	}
	/**
	 * @param idTipoHabilidad the idTipoHabilidad to set
	 */
	public void setIdTipoHabilidad(long idTipoHabilidad) {
		this.idTipoHabilidad = idTipoHabilidad;
	}
	/**
	 * @return the habilidad
	 */
	public String getHabilidad() {
		return habilidad;
	}
	/**
	 * @param habilidad the habilidad to set
	 */
	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}
	/**
	 * @return the idExperienciaHab
	 */
	public long getIdExperienciaHab() {
		return idExperienciaHab;
	}
	/**
	 * @param idExperienciaHab the idExperienciaHab to set
	 */
	public void setIdExperienciaHab(long idExperienciaHab) {
		this.idExperienciaHab = idExperienciaHab;
	}
	/**
	 * @return the idDominioHab
	 */
	public long getIdDominioHab() {
		return idDominioHab;
	}
	/**
	 * @param idDominioHab the idDominioHab to set
	 */
	public void setIdDominioHab(long idDominioHab) {
		this.idDominioHab = idDominioHab;
	}
	/**
	 * @return the descripcionHab
	 */
	public String getDescripcionHab() {
		return descripcionHab;
	}
	/**
	 * @param descripcionHab the descripcionHab to set
	 */
	public void setDescripcionHab(String descripcionHab) {
		this.descripcionHab = descripcionHab;
	}
	/**
	 * @return the computacionBasica
	 */
	public int getComputacionBasica() {
		return computacionBasica;
	}
	/**
	 * @param computacionBasica the computacionBasica to set
	 */
	public void setComputacionBasica(int computacionBasica) {
		this.computacionBasica = computacionBasica;
	}
	/**
	 * @return the idExperienciaCompu
	 */
	public long getIdExperienciaCompu() {
		return idExperienciaCompu;
	}
	/**
	 * @param idExperienciaCompu the idExperienciaCompu to set
	 */
	public void setIdExperienciaCompu(long idExperienciaCompu) {
		this.idExperienciaCompu = idExperienciaCompu;
	}
	/**
	 * @return the idDominioCompu
	 */
	public long getIdDominioCompu() {
		return idDominioCompu;
	}
	/**
	 * @param idDominioCompu the idDominioCompu to set
	 */
	public void setIdDominioCompu(long idDominioCompu) {
		this.idDominioCompu = idDominioCompu;
	}
	/**
	 * @return the idExperienciaOffice
	 */
	public long getIdExperienciaOffice() {
		return idExperienciaOffice;
	}
	/**
	 * @param idExperienciaOffice the idExperienciaOffice to set
	 */
	public void setIdExperienciaOffice(long idExperienciaOffice) {
		this.idExperienciaOffice = idExperienciaOffice;
	}
	/**
	 * @return the idDominioOffice
	 */
	public long getIdDominioOffice() {
		return idDominioOffice;
	}
	/**
	 * @param idDominioOffice the idDominioOffice to set
	 */
	public void setIdDominioOffice(long idDominioOffice) {
		this.idDominioOffice = idDominioOffice;
	}
	/**
	 * @return the idExperienciaInternet
	 */
	public long getIdExperienciaInternet() {
		return idExperienciaInternet;
	}
	/**
	 * @param idExperienciaInternet the idExperienciaInternet to set
	 */
	public void setIdExperienciaInternet(long idExperienciaInternet) {
		this.idExperienciaInternet = idExperienciaInternet;
	}
	/**
	 * @return the idDominioInternet
	 */
	public long getIdDominioInternet() {
		return idDominioInternet;
	}
	/**
	 * @param idDominioInternet the idDominioInternet to set
	 */
	public void setIdDominioInternet(long idDominioInternet) {
		this.idDominioInternet = idDominioInternet;
	}
	/**
	 * @return the computacionAvanzada
	 */
	public int getComputacionAvanzada() {
		return computacionAvanzada;
	}
	/**
	 * @param computacionAvanzada the computacionAvanzada to set
	 */
	public void setComputacionAvanzada(int computacionAvanzada) {
		this.computacionAvanzada = computacionAvanzada;
	}
	/**
	 * @return the idCandidatoCompuAvanzada
	 */
	public long getIdCandidatoCompuAvanzada() {
		return idCandidatoCompuAvanzada;
	}
	/**
	 * @param idCandidatoCompuAvanzada the idCandidatoCompuAvanzada to set
	 */
	public void setIdCandidatoCompuAvanzada(long idCandidatoCompuAvanzada) {
		this.idCandidatoCompuAvanzada = idCandidatoCompuAvanzada;
	}
	/**
	 * @return the softwareHardware
	 */
	public String getSoftwareHardware() {
		return softwareHardware;
	}
	/**
	 * @param softwareHardware the softwareHardware to set
	 */
	public void setSoftwareHardware(String softwareHardware) {
		this.softwareHardware = softwareHardware;
	}
	/**
	 * @return the idExperiencia
	 */
	public long getIdExperiencia() {
		return idExperiencia;
	}
	/**
	 * @param idExperiencia the idExperiencia to set
	 */
	public void setIdExperiencia(long idExperiencia) {
		this.idExperiencia = idExperiencia;
	}
	/**
	 * @return the idDominioCmp
	 */
	public long getIdDominioCmp() {
		return idDominioCmp;
	}
	/**
	 * @param idDominioCmp the idDominioCmp to set
	 */
	public void setIdDominioCmp(long idDominioCmp) {
		this.idDominioCmp = idDominioCmp;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setMsg(ResultVO msg) {
		this.msg = msg;
	}
	public ResultVO getMsg() {
		return msg;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EscolaridadForm [idCandidato=");
		builder.append(idCandidato);
		builder.append(", sinEstudios=");
		builder.append(sinEstudios);
		builder.append(", idCandidatoGradoAcademico=");
		builder.append(idCandidatoGradoAcademico);
		builder.append(", idNivelEstudio=");
		builder.append(idNivelEstudio);
		builder.append(", idSituacionAcademica=");
		builder.append(idSituacionAcademica);
		builder.append(", idCarreraEspecialidad=");
		builder.append(idCarreraEspecialidad);
		builder.append(", escuela=");
		builder.append(escuela);
		builder.append(", inicio=");
		builder.append(inicio);
		builder.append(", fin=");
		builder.append(fin);
		builder.append(", idCandidatoIdioma=");
		builder.append(idCandidatoIdioma);
		builder.append(", idIdioma=");
		builder.append(idIdioma);
		builder.append(", idCertificacion=");
		builder.append(idCertificacion);
		builder.append(", idDominio=");
		builder.append(idDominio);
		builder.append(", idCandidatoConocimiento=");
		builder.append(idCandidatoConocimiento);
		builder.append(", idTipoConocimiento=");
		builder.append(idTipoConocimiento);
		builder.append(", conocimiento=");
		builder.append(conocimiento);
		builder.append(", idExperienciaCon=");
		builder.append(idExperienciaCon);
		builder.append(", idDominioCon=");
		builder.append(idDominioCon);
		builder.append(", descripcionCon=");
		builder.append(descripcionCon);
		builder.append(", idCandidatoHabilidad=");
		builder.append(idCandidatoHabilidad);
		builder.append(", idTipoHabilidad=");
		builder.append(idTipoHabilidad);
		builder.append(", habilidad=");
		builder.append(habilidad);
		builder.append(", idExperienciaHab=");
		builder.append(idExperienciaHab);
		builder.append(", idDominioHab=");
		builder.append(idDominioHab);
		builder.append(", descripcionHab=");
		builder.append(descripcionHab);
		builder.append(", computacionBasica=");
		builder.append(computacionBasica);
		builder.append(", idExperienciaCompu=");
		builder.append(idExperienciaCompu);
		builder.append(", idDominioCompu=");
		builder.append(idDominioCompu);
		builder.append(", idExperienciaOffice=");
		builder.append(idExperienciaOffice);
		builder.append(", idDominioOffice=");
		builder.append(idDominioOffice);
		builder.append(", idExperienciaInternet=");
		builder.append(idExperienciaInternet);
		builder.append(", idDominioInternet=");
		builder.append(idDominioInternet);
		builder.append(", computacionAvanzada=");
		builder.append(computacionAvanzada);
		builder.append(", idCandidatoCompuAvanzada=");
		builder.append(idCandidatoCompuAvanzada);
		builder.append(", softwareHardware=");
		builder.append(softwareHardware);
		builder.append(", idExperiencia=");
		builder.append(idExperiencia);
		builder.append(", idDominioCmp=");
		builder.append(idDominioCmp);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append("]");
		return builder.toString();
	}
	
}
