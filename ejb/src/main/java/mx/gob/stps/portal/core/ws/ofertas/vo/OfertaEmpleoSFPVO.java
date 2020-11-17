package mx.gob.stps.portal.core.ws.ofertas.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaUbicacionVO;

public class OfertaEmpleoSFPVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idOfertaEmpleo;

	private long idEmpresa;
	
	private String empresaDescripcion;

	private String tituloOferta;

	private long idAreaLaboral;
	
	private String areaLaboralDescripcion;

	private long idOcupacion;
	
	private String ocupacionDescripcion;

	private String funciones;

	private double salario;

	private long idNivelEstudio;

	private long idSituacionAcademica;
	
	private String situacionAcademicaDescrip;

	private String habilidadGeneral;

	private int experienciaAnios;
	
	private String experienciaAniosDescrip;

	private String observaciones;

	private Date fechaAlta;

	private Date fechaFin;

	private List<OfertaCarreraEspecialidadVO> carreras;
	
	private DomicilioVO domicilio;
		
	private Long idOfertaBolsaSFP;
	
	private String tipoEmpleoDescripcion;
	
	private String horario;
	
	private String escolaridad;
	
	private String dispViajarDescripcion;
	
	private String dispRadicarDescripcion;
	
	private String edadPreferente;
	
	private Long numeroPlazas;
	
	private String carreraDescripcion;
	
	private String nombreEmpresa;
	
	private OfertaUbicacionVO ofertaUbicacion;
	
	public Long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}

	public void setIdOfertaEmpleo(Long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getTituloOferta() {
		return tituloOferta;
	}

	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}

	public long getIdAreaLaboral() {
		return idAreaLaboral;
	}

	public void setIdAreaLaboral(long idAreaLaboral) {
		this.idAreaLaboral = idAreaLaboral;
	}

	public long getIdOcupacion() {
		return idOcupacion;
	}

	public void setIdOcupacion(long idOcupacion) {
		this.idOcupacion = idOcupacion;
	}

	public String getFunciones() {
		return funciones;
	}

	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}


	public long getIdNivelEstudio() {
		return idNivelEstudio;
	}

	public void setIdNivelEstudio(long idNivelEstudio) {
		this.idNivelEstudio = idNivelEstudio;
	}

	public long getIdSituacionAcademica() {
		return idSituacionAcademica;
	}

	public void setIdSituacionAcademica(long idSituacionAcademica) {
		this.idSituacionAcademica = idSituacionAcademica;
	}

	public String getHabilidadGeneral() {
		return habilidadGeneral;
	}

	public void setHabilidadGeneral(String habilidadGeneral) {
		this.habilidadGeneral = habilidadGeneral;
	}

	public int getExperienciaAnios() {
		return experienciaAnios;
	}

	public void setExperienciaAnios(int experienciaAnios) {
		this.experienciaAnios = experienciaAnios;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public DomicilioVO getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioVO domicilio) {
		this.domicilio = domicilio;
	}

	public Long getIdOfertaBolsaSFP() {
		return idOfertaBolsaSFP;
	}

	public void setIdOfertaBolsaSFP(Long idOfertaBolsaSFP) {
		this.idOfertaBolsaSFP = idOfertaBolsaSFP;
	}

	public List<OfertaCarreraEspecialidadVO> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<OfertaCarreraEspecialidadVO> carreras) {
		this.carreras = carreras;
	}

	public void setAreaLaboralDescripcion(String areaLaboralDescripcion) {
		this.areaLaboralDescripcion = areaLaboralDescripcion;
	}

	public String getAreaLaboralDescripcion() {
		return areaLaboralDescripcion;
	}

	public void setOcupacionDescripcion(String ocupacionDescripcion) {
		this.ocupacionDescripcion = ocupacionDescripcion;
	}

	public String getOcupacionDescripcion() {
		return ocupacionDescripcion;
	}

	public void setTipoEmpleoDescripcion(String tipoEmpleoDescripcion) {
		this.tipoEmpleoDescripcion = tipoEmpleoDescripcion;
	}

	public String getTipoEmpleoDescripcion() {
		return tipoEmpleoDescripcion;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getHorario() {
		return horario;
	}

	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}

	public String getEscolaridad() {
		return escolaridad;
	}

	public void setSituacionAcademicaDescrip(String situacionAcademicaDescrip) {
		this.situacionAcademicaDescrip = situacionAcademicaDescrip;
	}

	public String getSituacionAcademicaDescrip() {
		return situacionAcademicaDescrip;
	}

	public void setExperienciaAniosDescrip(String experienciaAniosDescrip) {
		this.experienciaAniosDescrip = experienciaAniosDescrip;
	}

	public String getExperienciaAniosDescrip() {
		return experienciaAniosDescrip;
	}

	public void setDispViajarDescripcion(String dispViajarDescripcion) {
		this.dispViajarDescripcion = dispViajarDescripcion;
	}

	public String getDispViajarDescripcion() {
		return dispViajarDescripcion;
	}

	public void setDispRadicarDescripcion(String dispRadicarDescripcion) {
		this.dispRadicarDescripcion = dispRadicarDescripcion;
	}

	public String getDispRadicarDescripcion() {
		return dispRadicarDescripcion;
	}

	public void setEdadPreferente(String edadPreferente) {
		this.edadPreferente = edadPreferente;
	}

	public String getEdadPreferente() {
		return edadPreferente;
	}

	public void setEmpresaDescripcion(String empresaDescripcion) {
		this.empresaDescripcion = empresaDescripcion;
	}

	public String getEmpresaDescripcion() {
		return empresaDescripcion;
	}

	public void setNumeroPlazas(Long numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}

	public Long getNumeroPlazas() {
		return numeroPlazas;
	}

	public void setCarreraDescripcion(String carreraDescripcion) {
		this.carreraDescripcion = carreraDescripcion;
	}

	public String getCarreraDescripcion() {
		return carreraDescripcion;
	}

	/**
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param nombreEmpresa the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public OfertaUbicacionVO getOfertaUbicacion() {
		return ofertaUbicacion;
	}

	public void setOfertaUbicacion(OfertaUbicacionVO ofertaUbicacion) {
		this.ofertaUbicacion = ofertaUbicacion;
	}

	

}
