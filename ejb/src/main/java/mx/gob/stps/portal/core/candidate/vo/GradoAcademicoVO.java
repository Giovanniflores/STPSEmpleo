package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Objeto de valor que sera usado para transportar un Grado Academico
 * @author Felipe Juárez Ramírez
 * @since 08/03/2011
 *  
 **/
public class GradoAcademicoVO implements Serializable {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = -4531924116422563625L;
	
	private long idCandidatoGradoAcademico;
	private long idNivelEstudio;
	private String nivel;
	private long idSituacionAcademica;
	private String situacion;
	private long idCarreraEspecialidad;
	private String carrera;
	private String escuela;
	private int inicio;
	private int fin;
	private int principal;
	private String gradoAcademico;
	private String especialidad;
	private String situacionAcademicaString;
	private String experienciaString;
	private String areaLaboralString;
	private String tipoEmpleoString;
	private Long salarioPretendido;
	private String porqueBuscaEmpleo;
	private Date fechaInicio;
	private Date FechaFin;
	private String lugar;
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
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	/**
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
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
	 * @param situacion the situacion to set
	 */
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}
	/**
	 * @return the situacion
	 */
	public String getSituacion() {
		return situacion;
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
	 * @param carrera the carrera to set
	 */
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	/**
	 * @return the carrera
	 */
	public String getCarrera() {
		return carrera;
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
	public int getInicio() {
		return inicio;
	}
	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	/**
	 * @return the fin
	 */
	public int getFin() {
		return fin;
	}
	/**
	 * @param fin the fin to set
	 */
	public void setFin(int fin) {
		this.fin = fin;
	}
	
	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	/**
	 * @return the principal
	 */
	public int getPrincipal() {
		return principal;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fin;
		result = prime
				* result
				+ (int) (idCarreraEspecialidad ^ (idCarreraEspecialidad >>> 32));
		result = prime * result
				+ (int) (idNivelEstudio ^ (idNivelEstudio >>> 32));
		result = prime * result
				+ (int) (idSituacionAcademica ^ (idSituacionAcademica >>> 32));
		result = prime * result + inicio;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GradoAcademicoVO other = (GradoAcademicoVO) obj;
		if (idCarreraEspecialidad != other.idCarreraEspecialidad) {
			return false;
		}
		if (idNivelEstudio != other.idNivelEstudio) {
			return false;
		}
		if (idSituacionAcademica != other.idSituacionAcademica) {
			return false;
		}
		return true;
	}
	
	public String toBitacora() {
		return "idCandidatoGradoAcademico=" + idCandidatoGradoAcademico
				+ "|idNivelEstudio=" + idNivelEstudio + "|nivel=" + nivel
				+ "|idSituacionAcademica=" + idSituacionAcademica
				+ "|situacion=" + situacion + "|idCarreraEspecialidad="
				+ idCarreraEspecialidad + "|carrera=" + carrera + "|escuela="
				+ escuela + "|inicio=" + inicio + "|fin=" + fin + "|principal="
				+ principal;
	}
	public String getGradoAcademico() {
		return gradoAcademico;
	}
	public void setGradoAcademico(String gradoAcademico) {
		this.gradoAcademico = gradoAcademico;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getSituacionAcademicaString() {
		return situacionAcademicaString;
	}
	public void setSituacionAcademicaString(String situacionAcademicaString) {
		this.situacionAcademicaString = situacionAcademicaString;
	}
	public String getExperienciaString() {
		return experienciaString;
	}
	public void setExperienciaString(String experienciaString) {
		this.experienciaString = experienciaString;
	}
	public String getAreaLaboralString() {
		return areaLaboralString;
	}
	public void setAreaLaboralString(String areaLaboralString) {
		this.areaLaboralString = areaLaboralString;
	}
	public String getTipoEmpleoString() {
		return tipoEmpleoString;
	}
	public void setTipoEmpleoString(String tipoEmpleoString) {
		this.tipoEmpleoString = tipoEmpleoString;
	}
	public Long getSalarioPretendido() {
		return salarioPretendido;
	}
	public void setSalarioPretendido(Long salarioPretendido) {
		this.salarioPretendido = salarioPretendido;
	}
	public String getPorqueBuscaEmpleo() {
		return porqueBuscaEmpleo;
	}
	public void setPorqueBuscaEmpleo(String porqueBuscaEmpleo) {
		this.porqueBuscaEmpleo = porqueBuscaEmpleo;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return FechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		FechaFin = fechaFin;
	}
	
	@Override
	public String toString() {
		return "GradoAcademicoVO [idCandidatoGradoAcademico="
				+ idCandidatoGradoAcademico + ", idNivelEstudio="
				+ idNivelEstudio + ", idSituacionAcademica="
				+ idSituacionAcademica + ", idCarreraEspecialidad="
				+ idCarreraEspecialidad + ", escuela=" + escuela
				+ ", principal=" + principal + ", gradoAcademico="
				+ gradoAcademico + ", fechaInicio=" + fechaInicio
				+ ", FechaFin=" + FechaFin + "]";
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
}
