package mx.gob.stps.portal.core.oferta.reporte.vo;

import java.io.Serializable;
import java.util.Date;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public  final class ReporteOfertasEmpresaVO implements Serializable {	
	
	private static final long serialVersionUID = -2477994707333519424L;
	private long idEmpresa;
	private long idOfertaEmpleo;
	private String tituloOferta;
	private String empresaNombre;
	private long numeroPlazas;
	private double salario;
	private int idVigenciaOferta;	
	private String nombreContacto;
	private String correoElectronicoContacto;
	private Date fechaAlta;
	private Date fechaInicio;
	private Date fechaFin;	
	private long idGradoEstudios;
	private String gradoEstudios;
	private int experienciaAnios;
	private String descripcionExperienciaAnios;
	private long idOcupacion;
	private String ocupacion;
	private int idEstatus;
	private String estatus;
	private long idCarreraEspecialidad;
	private String carreraEspecialidad;
	private int idGenero;
	private String genero;
	private int idEntidad;
	private String entidad;
	private long idMunicipio;
	private String municipio;	
	private String telefonoPrincipal;
	private long idArea;
	private long idSubArea;
	private String subAreaLaboralDescripcion;
	
	/* 
TEL.ID_TELEFONO, TEL.ID_TIPO_TELEFONO, TEL.ACCESO, TEL.CLAVE, TEL.TELEFONO, TEL.EXTENSION
	 * */
	
	/**
	 * @return the idEmpresa
	 */
	public long getIdEmpresa() {
		return idEmpresa;
	}
	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	/**
	 * @return the idOfertaEmpleo
	 */
	public long getIdOfertaEmpleo() {
		return idOfertaEmpleo;
	}
	/**
	 * @param idOfertaEmpleo the idOfertaEmpleo to set
	 */
	public void setIdOfertaEmpleo(long idOfertaEmpleo) {
		this.idOfertaEmpleo = idOfertaEmpleo;
	}
	/**
	 * @return the tituloOferta
	 */
	public String getTituloOferta() {
		return tituloOferta;
	}
	/**
	 * @param tituloOferta the tituloOferta to set
	 */
	public void setTituloOferta(String tituloOferta) {
		this.tituloOferta = tituloOferta;
	}
	/**
	 * @return the empresaNombre
	 */
	public String getEmpresaNombre() {
		return empresaNombre;
	}
	/**
	 * @param empresaNombre the empresaNombre to set
	 */
	public void setEmpresaNombre(String empresaNombre) {
		this.empresaNombre = empresaNombre;
	}
	/**
	 * @return the numeroPlazas
	 */
	public long getNumeroPlazas() {
		return numeroPlazas;
	}
	/**
	 * @param numeroPlazas the numeroPlazas to set
	 */
	public void setNumeroPlazas(long numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}
	/**
	 * @return the salario
	 */
	public double getSalario() {
		return salario;
	}
	/**
	 * @param salario the salario to set
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}
	/**
	 * @return the idVigenciaOferta
	 */
	public int getIdVigenciaOferta() {
		return idVigenciaOferta;
	}
	/**
	 * @param idVigenciaOferta the idVigenciaOferta to set
	 */
	public void setIdVigenciaOferta(int idVigenciaOferta) {
		this.idVigenciaOferta = idVigenciaOferta;
	}
	/**
	 * @return the nombreContacto
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}
	/**
	 * @param nombreContacto the nombreContacto to set
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	/**
	 * @return the correoElectronicoContacto
	 */
	public String getCorreoElectronicoContacto() {
		return correoElectronicoContacto;
	}
	/**
	 * @param correoElectronicoContacto the correoElectronicoContacto to set
	 */
	public void setCorreoElectronicoContacto(String correoElectronicoContacto) {
		this.correoElectronicoContacto = correoElectronicoContacto;
	}
	/**
	 * @return the fechaAlta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}
	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	/**
	 * @return the idGradoEstudios
	 */
	public long getIdGradoEstudios() {
		return idGradoEstudios;
	}
	/**
	 * @param idGradoEstudios the idGradoEstudios to set
	 */
	public void setIdGradoEstudios(long idGradoEstudios) {
		this.idGradoEstudios = idGradoEstudios;
	}
	/**
	 * @return the gradoEstudios
	 */
	public String getGradoEstudios() {
		return gradoEstudios;
	}
	/**
	 * @param gradoEstudios the gradoEstudios to set
	 */
	public void setGradoEstudios(String gradoEstudios) {
		this.gradoEstudios = gradoEstudios;
	}
	/**
	 * @return the experienciaAnios
	 */
	public int getExperienciaAnios() {
		return experienciaAnios;
	}
	/**
	 * @param experienciaAnios the experienciaAnios to set
	 */
	public void setExperienciaAnios(int experienciaAnios) {
		this.experienciaAnios = experienciaAnios;
	}
	
	
	/**
	 * @return the descripcionExperienciaAnios
	 */
	public String getDescripcionExperienciaAnios() {
		return descripcionExperienciaAnios;
	}
	/**
	 * @param descripcionExperienciaAnios the descripcionExperienciaAnios to set
	 */
	public void setDescripcionExperienciaAnios(String descripcionExperienciaAnios) {
		this.descripcionExperienciaAnios = descripcionExperienciaAnios;
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
	 * @return the ocupacion
	 */
	public String getOcupacion() {
		return ocupacion;
	}
	/**
	 * @param ocupacion the ocupacion to set
	 */
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
	/**
	 * @return the idEstatus
	 */
	public int getIdEstatus() {
		return idEstatus;
	}
	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
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
	 * @return the carreraEspecialidad
	 */
	public String getCarreraEspecialidad() {
		return carreraEspecialidad;
	}
	/**
	 * @param carreraEspecialidad the carreraEspecialidad to set
	 */
	public void setCarreraEspecialidad(String carreraEspecialidad) {
		this.carreraEspecialidad = carreraEspecialidad;
	}
	/**
	 * @return the idGenero
	 */
	public int getIdGenero() {
		return idGenero;
	}
	/**
	 * @param idGenero the idGenero to set
	 */
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}
	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}
	/**
	 * @return the idEntidad
	 */
	public int getIdEntidad() {
		return idEntidad;
	}
	/**
	 * @param idEntidad the idEntidad to set
	 */
	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}
	/**
	 * @return the entidad
	 */
	public String getEntidad() {
		return entidad;
	}
	/**
	 * @param entidad the entidad to set
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	/**
	 * @return the idMunicipio
	 */
	public long getIdMunicipio() {
		return idMunicipio;
	}
	/**
	 * @param idMunicipio the idMunicipio to set
	 */
	public void setIdMunicipio(long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}
	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	/**
	 * @return the telefonoPrincipal
	 */
	public String getTelefonoPrincipal() {
		return telefonoPrincipal;
	}
	/**
	 * @param telefonoPrincipal the telefonoPrincipal to set
	 */
	public void setTelefonoPrincipal(String telefonoPrincipal) {
		this.telefonoPrincipal = telefonoPrincipal;
	}
	public String getSubAreaLaboralDescripcion() {
		return subAreaLaboralDescripcion;
	}
	public void setSubAreaLaboralDescripcion(String subAreaLaboralDescripcion) {
		this.subAreaLaboralDescripcion = subAreaLaboralDescripcion;
	}
	public long getIdArea() {
		return idArea;
	}
	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}
	public long getIdSubArea() {
		return idSubArea;
	}
	public void setIdSubArea(long idSubArea) {
		this.idSubArea = idSubArea;
	}
	
}
