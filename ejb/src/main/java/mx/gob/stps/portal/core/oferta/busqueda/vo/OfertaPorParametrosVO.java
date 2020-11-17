package mx.gob.stps.portal.core.oferta.busqueda.vo;

import java.io.Serializable;
import java.text.NumberFormat;

import mx.gob.stps.portal.core.search.vo.MatchModVO;

/**
 * @author Jorge Montoya Marin
 * @since 22/12/2015
 **/
public class OfertaPorParametrosVO implements Serializable {

	private static final long serialVersionUID = -7604673662470520177L;
	private long idCandidato;
	private long idEmpresa;
	private long idOfertaEmpleo;
	private String tituloOferta;
	private String ubicacion;
	private String empresa;
	private double salario;
	private String sSalario;
	private String gradoEstudio;
	private String carrera;
	private String funciones;
	private String edad;
	private String idiomas;
	private String idiomasCert;
	private String habilidades;
	private String habilidadGeneral;
	private String horario;
	private int numeroPlazas;
	private String medioContacto;
	private String ocupacion;
	private int fuente;
	private int estatus;
	private MatchModVO compatibilidad;
	private String contactoTel;
	private String contactoCorreo;
	private String contactoDomicilio;
	/**
	 * @return the contactoTel
	 */
	public String getContactoTel() {
		return contactoTel;
	}
	/**
	 * @param contactoTel the contactoTel to set
	 */
	public void setContactoTel(String contactoTel) {
		this.contactoTel = contactoTel;
	}
	/**
	 * @return the contactoCorreo
	 */
	public String getContactoCorreo() {
		return contactoCorreo;
	}
	/**
	 * @param contactoCorreo the contactoCorreo to set
	 */
	public void setContactoCorreo(String contactoCorreo) {
		this.contactoCorreo = contactoCorreo;
	}
	/**
	 * @return the contactoDomicilio
	 */
	public String getContactoDomicilio() {
		return contactoDomicilio;
	}
	/**
	 * @param contactoDomicilio the contactoDomicilio to set
	 */
	public void setContactoDomicilio(String contactoDomicilio) {
		this.contactoDomicilio = contactoDomicilio;
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
	 * @return the telefonoOferta
	 */
	public String getTelefonoOferta() {
		return telefonoOferta;
	}
	/**
	 * @param telefonoOferta the telefonoOferta to set
	 */
	public void setTelefonoOferta(String telefonoOferta) {
		this.telefonoOferta = telefonoOferta;
	}
	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}
	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	private String correoElectronicoContacto;
	private String telefonoOferta;
	private String domicilio;
	
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
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}
	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
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
	 * @return the sSalario
	 */
	public String getsSalario() {
		return sSalario;
	}
	/**
	 * @param sSalario the sSalario to set
	 */
	public void setsSalario(String sSalario) {
		this.sSalario = sSalario;
	}
	/**
	 * @return the gradoEstudio
	 */
	public String getGradoEstudio() {
		return gradoEstudio;
	}
	/**
	 * @param gradoEstudio the gradoEstudio to set
	 */
	public void setGradoEstudio(String gradoEstudio) {
		this.gradoEstudio = gradoEstudio;
	}
	/**
	 * @return the carrera
	 */
	public String getCarrera() {
		return carrera;
	}
	/**
	 * @param carrera the carrera to set
	 */
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	/**
	 * @return the funciones
	 */
	public String getFunciones() {
		return funciones;
	}
	/**
	 * @param funciones the funciones to set
	 */
	public void setFunciones(String funciones) {
		this.funciones = funciones;
	}
	/**
	 * @return the edad
	 */
	public String getEdad() {
		return edad;
	}
	/**
	 * @param edad the edad to set
	 */
	public void setEdad(String edad) {
		this.edad = edad;
	}
	/**
	 * @return the idiomas
	 */
	public String getIdiomas() {
		return idiomas;
	}
	/**
	 * @param idiomas the idiomas to set
	 */
	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}
	/**
	 * @return the idiomasCert
	 */
	public String getIdiomasCert() {
		return idiomasCert;
	}
	/**
	 * @param idiomasCert the idiomasCert to set
	 */
	public void setIdiomasCert(String idiomasCert) {
		this.idiomasCert = idiomasCert;
	}
	/**
	 * @return the habilidades
	 */
	public String getHabilidades() {
		return habilidades;
	}
	/**
	 * @param habilidades the habilidades to set
	 */
	public void setHabilidades(String habilidades) {
		this.habilidades = habilidades;
	}
	/**
	 * @return the horario
	 */
	public String getHorario() {
		return horario;
	}
	/**
	 * @param horario the horario to set
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}
	/**
	 * @return the numeroPlazas
	 */
	public int getNumeroPlazas() {
		return numeroPlazas;
	}
	/**
	 * @param numeroPlazas the numeroPlazas to set
	 */
	public void setNumeroPlazas(int numeroPlazas) {
		this.numeroPlazas = numeroPlazas;
	}
	/**
	 * @return the medioContacto
	 */
	public String getMedioContacto() {
		return medioContacto;
	}
	/**
	 * @param medioContacto the medioContacto to set
	 */
	public void setMedioContacto(String medioContacto) {
		this.medioContacto = medioContacto;
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
	 * @return the fuente
	 */
	public int getFuente() {
		return fuente;
	}
	/**
	 * @param fuente the fuente to set
	 */
	public void setFuente(int fuente) {
		this.fuente = fuente;
	}
	/**
	 * @return the estatus
	 */
	public int getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the compatibilidad
	 */
	public MatchModVO getCompatibilidad() {
		return compatibilidad;
	}
	/**
	 * @param compatibilidad the compatibilidad to set
	 */
	public void setCompatibilidad(MatchModVO compatibilidad) {
		this.compatibilidad = compatibilidad;
	}
	/**
	 * @return the habilidadGeneral
	 */
	public String getHabilidadGeneral() {
		return habilidadGeneral;
	}
	/**
	 * @param habilidadGeneral the habilidadGeneral to set
	 */
	public void setHabilidadGeneral(String habilidadGeneral) {
		this.habilidadGeneral = habilidadGeneral;
	}

	
}
