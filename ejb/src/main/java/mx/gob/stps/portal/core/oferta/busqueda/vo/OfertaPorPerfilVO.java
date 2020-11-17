package mx.gob.stps.portal.core.oferta.busqueda.vo;

import java.io.Serializable;
import java.text.NumberFormat;
import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;

/**
 * @author Mario Alberto Vázquez Flores
 * @since 18/03/2011
 * @modified Sergio Téllez
 *  
 **/
public class OfertaPorPerfilVO implements Serializable {

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
	private String contactoTel;
	private String contactoCorreo;
	private String contactoDomicilio;
	private String correoElectronicoContacto;
	private String telefonoOferta;
	private String domicilio;

	private int compatibilidad;
	private int fuente;
	private int estatus;
	private String subAreaLaboralDescripcion;
	
	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

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

	public int getCompatibilidad() {
		return compatibilidad;
	}

	public void setCompatibilidad(int compatibilidad) {
		this.compatibilidad = compatibilidad;
	}
	
	public long getIdEmpresa() {
		return idEmpresa;
	}

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
	 * @param idOfertaEmpleo
	 *            the idOfertaEmpleo to set
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
	 * @param tituloOferta
	 *            the tituloOferta to set
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
	 * @param ubicacion
	 *            the ubicacion to set
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
	 * @param empresa
	 *            the empresa to set
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
	 * @param salario
	 *            the salario to set
	 */
	public void setSalario(double salario) {
		NumberFormat fSalario = NumberFormat.getInstance();		
        setsSalario("$ " + fSalario.format(salario));
		this.salario = salario;		
	}

	/**
	 * @return the sSalario
	 */
	public String getsSalario() {
		return sSalario;
	}

	/**
	 * @param sSalario
	 *            the sSalario to set
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
	 * @param gradoEstudio
	 *            the gradoEstudio to set
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
	 * @param carrera
	 *            the carrera to set
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
	 * @param funciones
	 *            the funciones to set
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
	 * @param edad
	 *            the edad to set
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
	 * @param idiomas
	 *            the idiomas to set
	 */
	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}
	
	
	public String getIdiomasCert() {
		return idiomasCert;
	}

	public void setIdiomasCert(String idiomasCert) {
		this.idiomasCert = idiomasCert;
	}

	
	public String getHabilidades() {
		return habilidades;
	}

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
	 * @param horario
	 *            the horario to set
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
	 * @param numeroPlazas
	 *            the numeroPlazas to set
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
	 * @param medioContacto
	 *            the medioContacto to set
	 */
	public void setMedioContacto(String medioContacto) {
		this.medioContacto = medioContacto;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	@Override
	public String toString() {
		return idCandidato + " " + idOfertaEmpleo + " " + tituloOferta + " "
				+ ubicacion + " " + empresa + " " + getsSalario() + " " + getCompatibilidad();
	}
	
	/**
	 * @return the fuenteId
	 */
	public int getFuente() {
		return fuente;
	}

	/**
	 * @param fuenteId the fuenteId to set
	 */
	public void setFuente(int fuente) {
		this.fuente = fuente;
	}
	
	//Inicio Cambio Movil
	
	private String fechaInicio;
	
	
	String fechaModificacion;
	int differenciaFecha;

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public int getDifferenciaFecha() {
		return differenciaFecha;
	}

	public void setDifferenciaFecha(int differenciaFecha) {
		this.differenciaFecha = differenciaFecha;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getHabilidadGeneral() {
		return habilidadGeneral;
	}

	public void setHabilidadGeneral(String habilidadGeneral) {
		this.habilidadGeneral = habilidadGeneral;
	}

	public String getContactoTel() {
		return contactoTel;
	}

	public void setContactoTel(String contactoTel) {
		this.contactoTel = contactoTel;
	}

	public String getContactoCorreo() {
		return contactoCorreo;
	}

	public void setContactoCorreo(String contactoCorreo) {
		this.contactoCorreo = contactoCorreo;
	}

	public String getCorreoElectronicoContacto() {
		return correoElectronicoContacto;
	}

	public void setCorreoElectronicoContacto(String correoElectronicoContacto) {
		this.correoElectronicoContacto = correoElectronicoContacto;
	}

	public String getTelefonoOferta() {
		return telefonoOferta;
	}

	public void setTelefonoOferta(String telefonoOferta) {
		this.telefonoOferta = telefonoOferta;
	}

	public String getContactoDomicilio() {
		return contactoDomicilio;
	}

	public void setContactoDomicilio(String contactoDomicilio) {
		this.contactoDomicilio = contactoDomicilio;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getSubAreaLaboralDescripcion() {
		return subAreaLaboralDescripcion;
	}

	public void setSubAreaLaboralDescripcion(String subAreaLaboralDescripcion) {
		this.subAreaLaboralDescripcion = subAreaLaboralDescripcion;
	}
	
	//Fin Cambio Movil
}
