package mx.gob.stps.portal.core.candidate.vo;

import java.io.Serializable;
import java.util.List;
 
/**
 * Clase que contiene datos que se mostraran en el CV
 * @author amunoz
 * 23/05/2017
 */
public class DatosCurriculumVO implements Serializable {
	
	/**
	 * Identificador serial
	 */
	private static final long serialVersionUID = -3677512347903325492L;
	/** Título del CV */
	private String tituloCV;
	/** Objetivo profesional del candidato */
	private String objetivoProfesional;
	/** Resumen profesional del candidato */
	private String resumenProfesional;
	/** Redes sociales */
	private RedesSocialesVO redesSociales;
	/** Historia Laboral del candidato */
	private List<HistoriaLaboralVO> historiaLaboral;
	/** Formacion Academica */
	private List<GradoAcademicoVO> gradoAcademico;
	/** Certificaciones */
	private List<String> certificaciones;
	/** Sistemas Especializados */
	private String sistemas;
	/** Computacion */
	private String computacion;
	/** Idiomas **/
	private List<IdiomaVO> idiomas;
	
	
	/**
	 * @return the tituloCV
	 */
	public String getTituloCV() {
		return tituloCV;
	}
	/**
	 * @param tituloCV the tituloCV to set
	 */
	public void setTituloCV(String tituloCV) {
		this.tituloCV = tituloCV;
	}
	/**
	 * @return the resumenProfesional
	 */
	public String getResumenProfesional() {
		return resumenProfesional;
	}
	/**
	 * @param resumenProfesional the resumenProfesional to set
	 */
	public void setResumenProfesional(String resumenProfesional) {
		this.resumenProfesional = resumenProfesional;
	}
	
	/**
	 * @return the objetivoProfesional
	 */
	public String getObjetivoProfesional() {
		return objetivoProfesional;
	}
	/**
	 * @param objetivoProfesional the objetivoProfesional to set
	 */
	public void setObjetivoProfesional(String objetivoProfesional) {
		this.objetivoProfesional = objetivoProfesional;
	}
	/**
	 * @return the historiaLaboral
	 */
	public List<HistoriaLaboralVO> getHistoriaLaboral() {
		return historiaLaboral;
	}
	/**
	 * @param historiaLaboral the historiaLaboral to set
	 */
	public void setHistoriaLaboral(List<HistoriaLaboralVO> historiaLaboral) {
		this.historiaLaboral = historiaLaboral;
	}
	/**
	 * @return the redesSociales
	 */
	public RedesSocialesVO getRedesSociales() {
		return redesSociales;
	}
	/**
	 * @param redesSociales the redesSociales to set
	 */
	public void setRedesSociales(RedesSocialesVO redesSociales) {
		this.redesSociales = redesSociales;
	}
	/**
	 * @return the gradoAcademico
	 */
	public List<GradoAcademicoVO> getGradoAcademico() {
		return gradoAcademico;
	}
	/**
	 * @param gradoAcademico the gradoAcademico to set
	 */
	public void setGradoAcademico(List<GradoAcademicoVO> gradoAcademico) {
		this.gradoAcademico = gradoAcademico;
	}
	/**
	 * @return the certificaciones
	 */
	public List<String> getCertificaciones() {
		return certificaciones;
	}
	/**
	 * @param certificaciones the certificaciones to set
	 */
	public void setCertificaciones(List<String> certificaciones) {
		this.certificaciones = certificaciones;
	}
	/**
	 * @return the sistemas
	 */
	public String getSistemas() {
		return sistemas;
	}
	/**
	 * @param sistemasEsp the sistemas to set
	 */
	public void setSistemas(String sistemas) {
		this.sistemas = sistemas;
	}
	/**
	 * @return the computacion
	 */
	public String getComputacion() {
		return computacion;
	}
	/**
	 * @param computacion the computacion to set
	 */
	public void setComputacion(String computacion) {
		this.computacion = computacion;
	}
	/**
	 * @return the idiomas
	 */
	public List<IdiomaVO> getIdiomas() {
		return idiomas;
	}
	/**
	 * @param idiomas the idiomas to set
	 */
	public void setIdiomas(List<IdiomaVO> idiomas) {
		this.idiomas = idiomas;
	}	
	
	
}
 