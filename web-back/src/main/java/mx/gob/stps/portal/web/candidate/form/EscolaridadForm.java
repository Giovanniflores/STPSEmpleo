/**
 * 
 */
package mx.gob.stps.portal.web.candidate.form;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.core.candidate.vo.IdiomaVO;
import mx.gob.stps.portal.core.infra.utils.Constantes.APOYO_PROSPERA;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.persistencia.vo.EstandarConocerVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

/**
 * Objeto ActionForm que transporta los datos de escolaridad, idiomas, 
 * computación basica y avanzada, así como conocimientos y habilidades 
 * de un candidato
 * @author Felipe Juárez Ramírez
 * @since 08/03/2011
 */
public class EscolaridadForm extends PPCStatusForm {

	/**
	 * @serialField 
	 */
	private static final long serialVersionUID = 7832884540002038962L;
	/**
	 * Identificador del candidato
	 **/
	private long idCandidato;
	/**
	 * Indica si tiene estudios
	 */
	private int sinEstudios;
	/**
	 * Grado Academico (Principal)
	 */
	private long idCandidatoGradoAcademico;
	private long idNivelEstudio;
	private long idSituacionAcademica;
	private long idCarreraEspecialidad;
	private String escuela;
	private String inicio;
	private String fin;
	/**
	 * Idioma (Principal)
	 */
	private long idCandidatoIdioma;
	private long idIdioma;
	private long idCertificacion;
	private long idDominio;
	/**
	 * Idiomas (Adicionales)
	 */
	private List<IdiomaVO> idiomas;
	private List<IdiomaVO> idiomasAdicionales;
	private String[] idiomasDependientes;
	private List<CatalogoOpcionVO> idiomasMultiRegistro1;
	private List<CatalogoOpcionVO> idiomasMultiRegistro2;
	private List<CatalogoOpcionVO> idiomasMultiRegistro3;
	private List<CatalogoOpcionVO> catalogoIdiomas;
	private List<CatalogoOpcionVO> catalogoDominios;
	private int totalIdiomasAdicionales;	
	
	



	/**
	 * Conocimiento (Principal)
	 */
	private long idCandidatoConocimiento;
	private long idTipoConocimiento;
	private String conocimiento;
	private long idExperienciaCon;
	private long idDominioCon;
	private String descripcionCon;
	/**
	 * Habilidad (Principal)
	 */
	private long idCandidatoHabilidad;
	private long idTipoHabilidad;
	private String habilidad;
	private long idExperienciaHab;
	private long idDominioHab;
	private String descripcionHab;
	/**
	 * Certificaciones
	 **/	
	private long tieneCertificaciones;
	private long mostrarCertificacionesEnPerfil;
	private long mostrarCertificacionesEnCV;
	private List<EstandarConocerVO> listaCertificaciones; 
	
	/**
	 * Computación Básica
	 **/
	private int computacionBasica;
	private long idExperienciaCompu;
	private long idDominioCompu;
	private long idExperienciaOffice;
	private long idDominioOffice;
	private long idExperienciaInternet;
	private long idDominioInternet;
	/**
	 * Bandera que indica si se tiene conocimientos en computación avanzada.
	 **/
	private int computacionAvanzada;
	/**
	 * Computación Avanzada (Principal)
	 **/
	private long idCandidatoCompuAvanzada;
	private String softwareHardware;
	private long idExperiencia;
	private long idDominioCmp;
	private String descripcion;
	
	
	private boolean nuncaTrabajo;
	/**
	 * Conocimientos en computación
	 **/
	private long conocimientoCompNinguno;
	private int conocimientoCompProcesadorTxt;
	private int conocimientoCompHojaCal;
	private int conocimientoCompInternet;
	private int conocimientoCompRedes;
	private long idConocimientoComputacion;
	private String conocimientoCompOtros;

	//PROSPERA
	private int apoyoProsperaSi = APOYO_PROSPERA.SI.getIdOpcion();
	private int apoyoProsperaNo = APOYO_PROSPERA.NO.getIdOpcion();
	// ---
	private int apoyoProspera = apoyoProsperaNo;
	private String folioProspera;
	private String folioIntegranteProspera;
	
	/**
	 * Mensaje 
	 **/
	private ResultVO msg;
	
	private long[] idHabilidad;
	
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
	 * @return the sinEstudios
	 */
	public int getSinEstudios() {
		return sinEstudios;
	}

	/**
	 * @param sinEstudios
	 *            the sinEstudios to set
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
	 * @param idCandidatoGradoAcademico
	 *            the idCandidatoGradoAcademico to set
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
	 * @param idNivelEstudio
	 *            the idNivelEstudio to set
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
	 * @param idSituacionAcademica
	 *            the idSituacionAcademica to set
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
	 * @param idCarreraEspecialidad
	 *            the idCarreraEspecialidad to set
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
	 * @param escuela
	 *            the escuela to set
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
	 * @param inicio
	 *            the inicio to set
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
	 * @param fin
	 *            the fin to set
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
	 * @param idCandidatoIdioma
	 *            the idCandidatoIdioma to set
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
	 * @param idIdioma
	 *            the idIdioma to set
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
	 * @param idCertificacion
	 *            the idCertificacion to set
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
	 * @param idDominio
	 *            the idDominio to set
	 */
	public void setIdDominio(long idDominio) {
		this.idDominio = idDominio;
	}

	/**
	 * @return the idiomas
	 */
	public List<IdiomaVO> getIdiomas() {
		return idiomas;
	}

	/**
	 * @param idiomas
	 *            the idiomas to set
	 */
	public void setIdiomas(List<IdiomaVO> idiomas) {
		this.idiomas = idiomas;
	}

	/**
	 * @return the idiomasAdicionales
	 */
	public List<IdiomaVO> getIdiomasAdicionales() {
		if (null == idiomasAdicionales)
			idiomasAdicionales = new ArrayList<IdiomaVO>();
		return idiomasAdicionales;
	}

	/**
	 * @param idiomasAdicionales
	 *            the idiomasAdicionales to set
	 */
	public void setIdiomasAdicionales(List<IdiomaVO> idiomasAdicionales) {
		this.idiomasAdicionales = idiomasAdicionales;
	}

	/**
	 * @return the idiomasDependientes
	 */
	public String[] getIdiomasDependientes() {
		return idiomasDependientes;
	}

	/**
	 * @param idiomasDependientes
	 *            the idiomasDependientes to set
	 */
	public void setIdiomasDependientes(String[] idiomasDependientes) {
		this.idiomasDependientes = idiomasDependientes;
	}

	/**
	 * @return the idiomasMultiRegistro1
	 */
	public List<CatalogoOpcionVO> getIdiomasMultiRegistro1() {
		return idiomasMultiRegistro1;
	}

	/**
	 * @param idiomasMultiRegistro1
	 *            the idiomasMultiRegistro1 to set
	 */
	public void setIdiomasMultiRegistro1(
			List<CatalogoOpcionVO> idiomasMultiRegistro1) {
		this.idiomasMultiRegistro1 = idiomasMultiRegistro1;
	}

	/**
	 * @return the idiomasMultiRegistro2
	 */
	public List<CatalogoOpcionVO> getIdiomasMultiRegistro2() {
		return idiomasMultiRegistro2;
	}

	/**
	 * @param idiomasMultiRegistro2
	 *            the idiomasMultiRegistro2 to set
	 */
	public void setIdiomasMultiRegistro2(
			List<CatalogoOpcionVO> idiomasMultiRegistro2) {
		this.idiomasMultiRegistro2 = idiomasMultiRegistro2;
	}

	/**
	 * @return the idiomasMultiRegistro3
	 */
	public List<CatalogoOpcionVO> getIdiomasMultiRegistro3() {
		return idiomasMultiRegistro3;
	}

	/**
	 * @param idiomasMultiRegistro3
	 *            the idiomasMultiRegistro3 to set
	 */
	public void setIdiomasMultiRegistro3(
			List<CatalogoOpcionVO> idiomasMultiRegistro3) {
		this.idiomasMultiRegistro3 = idiomasMultiRegistro3;
	}

	/**
	 * @return the totalIdiomasAdicionales
	 */
	public int getTotalIdiomasAdicionales() {
		return totalIdiomasAdicionales;
	}

	/**
	 * @param totalIdiomasAdicionales
	 *            the totalIdiomasAdicionales to set
	 */
	public void setTotalIdiomasAdicionales(int totalIdiomasAdicionales) {
		this.totalIdiomasAdicionales = totalIdiomasAdicionales;
	}
	
	public List<CatalogoOpcionVO> getCatalogoIdiomas() {
		return catalogoIdiomas;
	}

	public void setCatalogoIdiomas(List<CatalogoOpcionVO> catalogoIdiomas) {
		this.catalogoIdiomas = catalogoIdiomas;
	}	
	
	public List<CatalogoOpcionVO> getCatalogoDominios() {
		return catalogoDominios;
	}

	public void setCatalogoDominios(List<CatalogoOpcionVO> catalogoDominios) {
		this.catalogoDominios = catalogoDominios;
	}	

	/**
	 * @return the idCandidatoConocimiento
	 */
	public long getIdCandidatoConocimiento() {
		return idCandidatoConocimiento;
	}

	/**
	 * @param idCandidatoConocimiento
	 *            the idCandidatoConocimiento to set
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
	 * @param idTipoConocimiento
	 *            the idTipoConocimiento to set
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
	 * @param conocimiento
	 *            the conocimiento to set
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
	 * @param idExperienciaCon
	 *            the idExperienciaCon to set
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
	 * @param idDominioCon
	 *            the idDominioCon to set
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
	 * @param descripcionCon
	 *            the descripcionCon to set
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
	 * @param idCandidatoHabilidad
	 *            the idCandidatoHabilidad to set
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
	 * @param idTipoHabilidad
	 *            the idTipoHabilidad to set
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
	 * @param habilidad
	 *            the habilidad to set
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
	 * @param idExperienciaHab
	 *            the idExperienciaHab to set
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
	 * @param idDominioHab
	 *            the idDominioHab to set
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
	 * @param descripcionHab
	 *            the descripcionHab to set
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
	 * @param computacionBasica
	 *            the computacionBasica to set
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
	 * @param idExperienciaCompu
	 *            the idExperienciaCompu to set
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
	 * @param idDominioCompu
	 *            the idDominioCompu to set
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
	 * @param idExperienciaOffice
	 *            the idExperienciaOffice to set
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
	 * @param idDominioOffice
	 *            the idDominioOffice to set
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
	 * @param idExperienciaInternet
	 *            the idExperienciaInternet to set
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
	 * @param idDominioInternet
	 *            the idDominioInternet to set
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
	 * @param computacionAvanzada
	 *            the computacionAvanzada to set
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
	 * @param idCandidatoCompuAvanzada
	 *            the idCandidatoCompuAvanzada to set
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
	 * @param softwareHardware
	 *            the softwareHardware to set
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
	 * @param idExperiencia
	 *            the idExperiencia to set
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
	 * @param idDominioCmp
	 *            the idDominioCmp to set
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
	 * @param descripcion
	 *            the descripcion to set
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
	@Override
	public String toString() {
		return "EscolaridadForm [idCandidato=" + idCandidato + ", sinEstudios="
				+ sinEstudios + ", idCandidatoGradoAcademico="
				+ idCandidatoGradoAcademico + ", idNivelEstudio="
				+ idNivelEstudio + ", idSituacionAcademica="
				+ idSituacionAcademica + ", idCarreraEspecialidad="
				+ idCarreraEspecialidad + ", escuela=" + escuela + ", inicio="
				+ inicio + ", fin=" + fin + ", idCandidatoIdioma="
				+ idCandidatoIdioma + ", idIdioma=" + idIdioma
				+ ", idCertificacion=" + idCertificacion + ", idDominio="
				+ idDominio + ", idCandidatoConocimiento="
				+ idCandidatoConocimiento + ", idTipoConocimiento="
				+ idTipoConocimiento + ", conocimiento=" + conocimiento
				+ ", idExperienciaCon=" + idExperienciaCon + ", idDominioCon="
				+ idDominioCon + ", descripcionCon=" + descripcionCon
				+ ", idCandidatoHabilidad=" + idCandidatoHabilidad
				+ ", idTipoHabilidad=" + idTipoHabilidad + ", habilidad="
				+ habilidad + ", idExperienciaHab=" + idExperienciaHab
				+ ", idDominioHab=" + idDominioHab + ", descripcionHab="
				+ descripcionHab + ", computacionBasica=" + computacionBasica
				+ ", idExperienciaCompu=" + idExperienciaCompu
				+ ", idDominioCompu=" + idDominioCompu
				+ ", idExperienciaOffice=" + idExperienciaOffice
				+ ", idDominioOffice=" + idDominioOffice
				+ ", idExperienciaInternet=" + idExperienciaInternet
				+ ", idDominioInternet=" + idDominioInternet
				+ ", computacionAvanzada=" + computacionAvanzada
				+ ", idCandidatoCompuAvanzada=" + idCandidatoCompuAvanzada
				+ ", softwareHardware=" + softwareHardware + ", idExperiencia="
				+ idExperiencia + ", idDominioCmp=" + idDominioCmp
				+ ", descripcion=" + descripcion + ", msg=" + msg 
				+ ", tieneCertificaciones=" + tieneCertificaciones
				+ ", mostrarCertificacionesEnPerfil=" + mostrarCertificacionesEnPerfil
				+ ", mostrarCertificacionesEnCV=" + mostrarCertificacionesEnCV
				+"]";
	}

	public void setNuncaTrabajo(boolean nuncaTrabajo) {
		this.nuncaTrabajo = nuncaTrabajo;
	}

	public boolean isNuncaTrabajo() {
		return nuncaTrabajo;
	}

	public void reset() {
		this.idCandidato = 0;
		this.sinEstudios = 0;
		this.idCandidatoGradoAcademico = 0;
		this.idNivelEstudio = 0;
		this.idSituacionAcademica = 0;
		this.idCarreraEspecialidad = 0;
		this.escuela = null;
		this.inicio = null;
		this.fin = null;
		this.idCandidatoIdioma = 0;
		this.idIdioma = 0;
		this.idCertificacion = 0;
		this.idDominio = 0;
		this.idCandidatoConocimiento = 0;
		this.idTipoConocimiento = 0;
		this.conocimiento = null;
		this.idExperienciaCon = 0;
		this.idDominioCon = 0;
		this.descripcionCon = null;
		this.idCandidatoHabilidad = 0;
		this.idTipoHabilidad = 0;
		this.habilidad = null;
		this.idExperienciaHab = 0;
		this.idDominioHab = 0;
		this.descripcionHab = null;
		this.computacionBasica = 0;
		this.idExperienciaCompu = 0;
		this.idDominioCompu = 0;
		this.idExperienciaOffice = 0;
		this.idDominioOffice = 0;
		this.idExperienciaInternet = 0;
		this.idDominioInternet = 0;
		this.computacionAvanzada = 0;
		this.idCandidatoCompuAvanzada = 0;
		this.softwareHardware = null;
		this.idExperiencia = 0;
		this.idDominioCmp = 0;
		this.descripcion = null;
		this.msg = null;
		this.tieneCertificaciones = 0;
		this.mostrarCertificacionesEnPerfil = 0;
		this.mostrarCertificacionesEnCV = 0;
		this.listaCertificaciones = null;
		this.idHabilidad = null;

		apoyoProspera = APOYO_PROSPERA.NO.getIdOpcion();
		folioProspera = null;
		folioIntegranteProspera = null;
	}

	public long getTieneCertificaciones() {
		return tieneCertificaciones;
	}

	public void setTieneCertificaciones(long tieneCertificaciones) {
		this.tieneCertificaciones = tieneCertificaciones;
	}

	public long getMostrarCertificacionesEnPerfil() {
		return mostrarCertificacionesEnPerfil;
	}

	public void setMostrarCertificacionesEnPerfil(
			long mostrarCertificacionesEnPerfil) {
		this.mostrarCertificacionesEnPerfil = mostrarCertificacionesEnPerfil;
	}

	public long getMostrarCertificacionesEnCV() {
		return mostrarCertificacionesEnCV;
	}

	public void setMostrarCertificacionesEnCV(long mostrarCertificacionesEnCV) {
		this.mostrarCertificacionesEnCV = mostrarCertificacionesEnCV;
	}

	public List<EstandarConocerVO> getListaCertificaciones() {
		return listaCertificaciones;
	}
	public void setListaCertificaciones(List<EstandarConocerVO> listaCertificaciones) {
		this.listaCertificaciones = listaCertificaciones;
	}

	/**
	 * @return the conocimientoCompNinguno
	 */
	public long getConocimientoCompNinguno() {
		return conocimientoCompNinguno;
	}

	/**
	 * @param conocimientoCompNinguno
	 *            the conocimientoCompNinguno to set
	 */
	public void setConocimientoCompNinguno(long conocimientoCompNinguno) {
		this.conocimientoCompNinguno = conocimientoCompNinguno;
	}

	/**
	 * @return the conocimientoCompProcesadorTxt
	 */
	public int getConocimientoCompProcesadorTxt() {
		return conocimientoCompProcesadorTxt;
	}

	/**
	 * @param conocimientoCompProcesadorTxt
	 *            the conocimientoCompProcesadorTxt to set
	 */
	public void setConocimientoCompProcesadorTxt(
			int conocimientoCompProcesadorTxt) {
		this.conocimientoCompProcesadorTxt = conocimientoCompProcesadorTxt;
	}

	/**
	 * @return the conocimientoCompHojaCal
	 */
	public int getConocimientoCompHojaCal() {
		return conocimientoCompHojaCal;
	}

	/**
	 * @param conocimientoCompHojaCal
	 *            the conocimientoCompHojaCal to set
	 */
	public void setConocimientoCompHojaCal(int conocimientoCompHojaCal) {
		this.conocimientoCompHojaCal = conocimientoCompHojaCal;
	}

	/**
	 * @return the conocimientoCompInternet
	 */
	public int getConocimientoCompInternet() {
		return conocimientoCompInternet;
	}

	/**
	 * @param conocimientoCompInternet
	 *            the conocimientoCompInternet to set
	 */
	public void setConocimientoCompInternet(int conocimientoCompInternet) {
		this.conocimientoCompInternet = conocimientoCompInternet;
	}

	/**
	 * @return the conocimientoCompRedes
	 */
	public int getConocimientoCompRedes() {
		return conocimientoCompRedes;
	}

	/**
	 * @param conocimientoCompRedes
	 *            the conocimientoCompRedes to set
	 */
	public void setConocimientoCompRedes(int conocimientoCompRedes) {
		this.conocimientoCompRedes = conocimientoCompRedes;
	}

	/**
	 * @return the idConocimientoComputacion
	 */
	public long getIdConocimientoComputacion() {
		return idConocimientoComputacion;
	}

	/**
	 * @param idConocimientoComputacion
	 *            the idConocimientoComputacion to set
	 */
	public void setIdConocimientoComputacion(long idConocimientoComputacion) {
		this.idConocimientoComputacion = idConocimientoComputacion;
	}

	/**
	 * @return the conocimientoCompOtros
	 */
	public String getConocimientoCompOtros() {
		return conocimientoCompOtros;
	}

	/**
	 * @param conocimientoCompOtros
	 *            the conocimientoCompOtros to set
	 */
	public void setConocimientoCompOtros(String conocimientoCompOtros) {
		this.conocimientoCompOtros = conocimientoCompOtros;
	}

	public int getApoyoProsperaSi() {
		return apoyoProsperaSi;
	}

	public int getApoyoProsperaNo() {
		return apoyoProsperaNo;
	}

	public int getApoyoProspera() {
		return apoyoProspera;
	}

	public void setApoyoProspera(int apoyoProspera) {
		if(apoyoProspera == 0){
			this.apoyoProspera = APOYO_PROSPERA.NO.getIdOpcion();
		} else {
			this.apoyoProspera = apoyoProspera;
		}
	}

	public String getFolioProspera() {
		return folioProspera;
	}

	public void setFolioProspera(String folioProspera) {
		this.folioProspera = folioProspera;
	}

	public String getFolioIntegranteProspera() {
		return folioIntegranteProspera;
	}

	public void setFolioIntegranteProspera(String folioIntegranteProspera) {
		this.folioIntegranteProspera = folioIntegranteProspera;
	}

	public long[] getIdHabilidad() {
		return idHabilidad;
	}

	public void setIdHabilidad(long[] idHabilidad) {
		this.idHabilidad = idHabilidad;
	}
}
