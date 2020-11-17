package mx.gob.stps.portal.web.autorization.vo;

import java.io.Serializable;
import java.util.List;

import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

public final class OfertaDetalleVO implements Serializable {
	private static final long serialVersionUID = 5698695454131496051L;
	private long idEmpresa;
	private long idOfertaEmpleo;
	private String tituloOferta;
	private String empresaNombre;
	private String tipoContrato;
	private String horarioLaboral;
	private String gradoEstudios;
	private String especialidades;
	private String situacionAcademica;
	private String habilidadGeneral;
	private List<CatalogoOpcionVO> habilidades;
	private String experienciaAnios;
	private String requisitos;
	private String idiomasCert;
	private int edadRequisito;
	private String edadMinima;
	private String edadMaxima;
	private String disponibilidadViajar;
	private String disponibilidadRadicar;
	private String observaciones;
	private String areaLaboral;
	private String ocupacion;
	private String sector;
	private String funciones;
	private String horario;
	private String salario;
	private String empresaOfrece;
	private String numeroPlazas;
	private String prestaciones;
	private String descripcionesDiscapacidades;
	
	private int compatibility;
	private boolean postulated;
	private String ubicacion;
	
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}
	public String getTituloOferta() {
		return tituloOferta;
	}
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}
	public String getEmpresaNombre() {
		return empresaNombre;
	}
	public void setEmpresaNombre(String empresaNombre) {
		this.empresaNombre = empresaNombre;
	}
	public String getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	public String getHorarioLaboral() {
		return horarioLaboral;
	}
	public void setHorarioLaboral(String horarioLaboral) {
		this.horarioLaboral = horarioLaboral;
	}
	public String getGradoEstudios() {
		return gradoEstudios;
	}
	public void setGradoEstudios(String gradoEstudios) {
		this.gradoEstudios = gradoEstudios;
	}
	public String getEspecialidades() {
		return especialidades;
	}
	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}
	public String getSituacionAcademica() {
		return situacionAcademica;
	}
	public void setSituacionAcademica(String situacionAcademica) {
		this.situacionAcademica = situacionAcademica;
	}
	public String getHabilidadGeneral() {
		return habilidadGeneral;
	}
	public void setHabilidadGeneral(String habilidadGeneral) {
		this.habilidadGeneral = habilidadGeneral;
	}
	public String getExperienciaAnios() {
		return experienciaAnios;
	}
	public void setExperienciaAnios(String experienciaAnios) {
		this.experienciaAnios = experienciaAnios;
	}
	public String getRequisitos() {
		return requisitos;
	}
	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}
	public String getIdiomasCert() {
		return idiomasCert;
	}
	public void setIdiomasCert(String idiomasCert) {
		this.idiomasCert = idiomasCert;
	}
	public int getEdadRequisito() {
		return edadRequisito;
	}
	public void setEdadRequisito(int edadRequisito) {
		this.edadRequisito = edadRequisito;
	}
	public String getEdadMinima() {
		return edadMinima;
	}
	public void setEdadMinima(String edadMinima) {
		this.edadMinima = edadMinima;
	}
	public String getEdadMaxima() {
		return edadMaxima;
	}
	public void setEdadMaxima(String edadMaxima) {
		this.edadMaxima = edadMaxima;
	}
	public String getDisponibilidadViajar() {
		return disponibilidadViajar;
	}
	public void setDisponibilidadViajar(String disponibilidadViajar) {
		this.disponibilidadViajar = disponibilidadViajar;
	}
	public String getDisponibilidadRadicar() {
		return disponibilidadRadicar;
	}
	public void setDisponibilidadRadicar(String disponibilidadRadicar) {
		this.disponibilidadRadicar = disponibilidadRadicar;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getAreaLaboral() {
		return areaLaboral;
	}
	public void setAreaLaboral(String areaLaboral) {
		this.areaLaboral = areaLaboral;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getFunciones() {
		return funciones;
	}
	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}
	public String getEmpresaOfrece() {
		return empresaOfrece;
	}
	public void setEmpresaOfrece(String empresaOfrece) {
		this.empresaOfrece = empresaOfrece;
	}
	public String getNumeroPlazas() {
		return numeroPlazas;
	}
	public void setNumeroPlazas(String numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}
	public String getPrestaciones() {
		return prestaciones;
	}
	public void setPrestaciones(String prestaciones) {
		this.prestaciones = prestaciones;
	}		
	public int getCompatibility() {
		return compatibility;
	}
	public void setCompatibility(int compatibility) {
		this.compatibility = compatibility;
	}
	public boolean isPostulated() {
		return postulated;
	}
	public void setPostulated(boolean postulated) {
		this.postulated = postulated;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public List<CatalogoOpcionVO> getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(List<CatalogoOpcionVO> habilidades) {
		this.habilidades = habilidades;
	}
	public String getDescripcionesDiscapacidades() {
		return descripcionesDiscapacidades;
	}
	public void setDescripcionesDiscapacidades(String descripcionesDiscapacidades) {
		this.descripcionesDiscapacidades = descripcionesDiscapacidades;
	}	
	
}
