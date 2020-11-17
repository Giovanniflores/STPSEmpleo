package mx.gob.stps.portal.web.candidate.form;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author jose.hernandez
 *
 */
public class CandidatosRegistroForm extends ActionForm {
	
	/**
	 * logger
	 */
	private static Logger logger = Logger.getLogger(CandidatosRegistroForm.class);
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5445486906907552317L;
	
	private String usuario;
	private String password;
	private String password_confirma;
	
	private String correoElectronico;
	private String correoElectronicoConfirma;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private long colonia;
	private long municipio_delegacion;
	private String ciudad;
	private long entidadFederativa;
	private String entidadFederativaDescripcion;
	private int portalEnteraste;
	private String buscarTrabajo;	
	private String horarioContacta;
	private String porqueBuscasTrabajo;
	private String tipoTelefono;
	private int discapacidad;
	private String curp;
	
	private int genero; 
	private String fechaNacimiento[];
	private int edad;
	private int idEntidadNacimiento;
	private int idEstadoCivil;
	private int idTipoDiscapacidad;
	private int idMedioPortal;
	private boolean confidencialidadDatos;
	protected boolean veracidadDatos;
	protected boolean aceptacionTerminos;
	protected Date fechaAlta;
	protected int estatus;
	protected Date fechaUltimaActualizacion;	
	protected int estiloCv;
	
	private int idCandidato;
	private int idUsuario;
	private String codigoPostal;
	private int situacionLegal;
	private int diaNacimiento;
	private int mesNacimiento;
	private int anoNacimiento;
	private String ladaTel;
	private String claveTel;
	private String telefono;
	private String extensionTel;
	
	private int idTipoTelefono;	
	private int entidadFederativaNacimiento;
	private int idEntidadFederativa;
	
	private String mensajes;
	
	private String nss;
	
	private Long creditoInfonavit;
	
	private List<CatalogoOpcionVO> listaMedioEntero;

	
	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	
	public String getLadaTel() {
		return ladaTel;
	}

	public void setLadaTel(String ladaTel) {
		this.ladaTel = ladaTel;
	}

	public String getClaveTel() {
		return claveTel;
	}

	public void setClaveTel(String claveTel) {
		this.claveTel = claveTel;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getExtensionTel() {
		return extensionTel;
	}

	public void setExtensionTel(String extensionTel) {
		this.extensionTel = extensionTel;
	}

	public int getDiaNacimiento() {
		return diaNacimiento;
	}

	public void setDiaNacimiento(int diaNacimiento) {
		this.diaNacimiento = diaNacimiento;
	}

	public int getMesNacimiento() {
		return mesNacimiento;
	}

	public void setMesNacimiento(int mesNacimiento) {
		this.mesNacimiento = mesNacimiento;
	}

	public int getAnoNacimiento() {
		return anoNacimiento;
	}

	public void setAnoNacimiento(int anoNacimiento) {
		this.anoNacimiento = anoNacimiento;
	}

	
	public int getSituacionLegal() {
		return situacionLegal;
	}

	public void setSituacionLegal(int situacionLegal) {
		this.situacionLegal = situacionLegal;
	}



	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	
	
	public int getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setColonia(long colonia) {
		this.colonia = colonia;
	}

	public long getColonia() {
		return colonia;
	}

	public void setMunicipio_delegacion(long municipio_delegacion) {
		this.municipio_delegacion = municipio_delegacion;
	}

	public long getMunicipio_delegacion() {
		return municipio_delegacion;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setEntidadFederativa(long entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}

	public long getEntidadFederativa() {
		return entidadFederativa;
	}

	public void setEntidadFederativaDescripcion(
			String entidadFederativaDescripcion) {
		this.entidadFederativaDescripcion = entidadFederativaDescripcion;
	}

	public String getEntidadFederativaDescripcion() {
		return entidadFederativaDescripcion;
	}

	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

	public String getTipoTelefono() {
		return tipoTelefono;
	}

	public void setDiscapacidad(int discapacidad) {
		this.discapacidad = discapacidad;
	}

	public int getDiscapacidad() {
		return discapacidad;
	}

	
	public void setPortalEnteraste(int portalEnteraste) {
		this.portalEnteraste = portalEnteraste;
	}

	public int getPortalEnteraste() {
		return portalEnteraste;
	}

	public void setBuscarTrabajo(String buscarTrabajo) {
		this.buscarTrabajo = buscarTrabajo;
	}

	public String getBuscarTrabajo() {
		return buscarTrabajo;
	}

	public void setHorarioContacta(String horarioContacta) {
		this.horarioContacta = horarioContacta;
	}

	public String getHorarioContacta() {
		return horarioContacta;
	}

	public void setPorqueBuscasTrabajo(String porqueBuscasTrabajo) {
		this.porqueBuscasTrabajo = porqueBuscasTrabajo;
	}

	public String getPorqueBuscasTrabajo() {
		return porqueBuscasTrabajo;
	}


	public void setIdEntidadNacimiento(int idEntidadNacimiento) {
		this.idEntidadNacimiento = idEntidadNacimiento;
	}

	public int getIdEntidadNacimiento() {
		return idEntidadNacimiento;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword_confirma() {
		return password_confirma;
	}

	public void setPassword_confirma(String password_confirma) {
		this.password_confirma = password_confirma;
	}

	public void setFechaNacimiento(String fechaNacimiento[]) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String[] getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getEdad() {
		return edad;
	}

	public void setIdEstadoCivil(int idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public int getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdTipoDiscapacidad(int idTipoDiscapacidad) {
		this.idTipoDiscapacidad = idTipoDiscapacidad;
	}

	public int getIdTipoDiscapacidad() {
		return idTipoDiscapacidad;
	}

	public int getIdMedioPortal() {
		return idMedioPortal;
	}

	public void setIdMedioPortal(int idMedioPortal) {
		this.idMedioPortal = idMedioPortal;
	}

	public boolean getConfidencialidadDatos() {
		return confidencialidadDatos;
	}

	public void setConfidencialidadDatos(boolean confidencialidadDatos) {
		this.confidencialidadDatos = confidencialidadDatos;
	}

	public boolean getVeracidadDatos() {
		return veracidadDatos;
	}

	public void setVeracidadDatos(boolean veracidadDatos) {
		this.veracidadDatos = veracidadDatos;
	}

	public boolean getAceptacionTerminos() {
		return aceptacionTerminos;
	}

	public void setAceptacionTerminos(boolean aceptacionTerminos) {
		this.aceptacionTerminos = aceptacionTerminos;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public int getEstiloCv() {
		return estiloCv;
	}

	public void setEstiloCv(int estiloCv) {
		this.estiloCv = estiloCv;
	}

	/**
	 * @param idTipoTelefono the idTipoTelefono to set
	 */
	public void setIdTipoTelefono(int idTipoTelefono) {
		this.idTipoTelefono = idTipoTelefono;
	}

	/**
	 * @return the idTipoTelefono
	 */
	public int getIdTipoTelefono() {
		return idTipoTelefono;
	}

	/**
	 * @param entidadFederativaNacimiento the entidadFederativaNacimiento to set
	 */
	public void setEntidadFederativaNacimiento(int entidadFederativaNacimiento) {
		this.entidadFederativaNacimiento = entidadFederativaNacimiento;
	}

	/**
	 * @return the entidadFederativaNacimiento
	 */
	public int getEntidadFederativaNacimiento() {
		return entidadFederativaNacimiento;
	}

	/**
	 * @param idEntidadFederativa the idEntidadFederativa to set
	 */
	public void setIdEntidadFederativa(int idEntidadFederativa) {
		this.idEntidadFederativa = idEntidadFederativa;
	}

	/**
	 * @return the idEntidadFederativa
	 */
	public int getIdEntidadFederativa() {
		return idEntidadFederativa;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

        return errors;
	}

	/**
	 * @param correoElectronicoConfirma the correoElectronicoConfirma to set
	 */
	public void setCorreoElectronicoConfirma(String correoElectronicoConfirma) {
		this.correoElectronicoConfirma = correoElectronicoConfirma;
	}

	/**
	 * @return the correoElectronicoConfirma
	 */
	public String getCorreoElectronicoConfirma() {
		return correoElectronicoConfirma;
	}

	/**
	 * @param listaMedioEntero the listaMedioEntero to set
	 */
	public void setListaMedioEntero(List<CatalogoOpcionVO> listaMedioEntero) {
		this.listaMedioEntero = listaMedioEntero;
	}

	/**
	 * @return the listaMedioEntero
	 */
	public List<CatalogoOpcionVO> getListaMedioEntero() {
		return listaMedioEntero;
	}

	/**
	 * Limpiando el formulario
	 */
	public void reset() {
		
		//logger.debug("Limpiando forma registro candidato");
		
		curp							=     "";
		
		idEntidadNacimiento    			=     0;
		correoElectronico    			=     "";
		nombre    						=     "";
		apellido1    					=     "";
		apellido2    					=     "";
		nss 							= 	  "";
		creditoInfonavit 				= 	  null;
	}

	 
	@Override
	public String toString() {
		return "CandidatosRegistroForm [usuario=" + usuario + ", password="
				+ password + ", password_confirma=" + password_confirma
				+ ", correoElectronico=" + correoElectronico
				+ ", correoElectronicoConfirma=" + correoElectronicoConfirma
				+ ", nombre=" + nombre + ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2 + ", colonia=" + colonia
				+ ", municipio_delegacion=" + municipio_delegacion
				+ ", ciudad=" + ciudad + ", entidadFederativa="
				+ entidadFederativa + ", entidadFederativaDescripcion="
				+ entidadFederativaDescripcion + ", portalEnteraste="
				+ portalEnteraste + ", buscarTrabajo=" + buscarTrabajo
				+ ", horarioContacta=" + horarioContacta
				+ ", porqueBuscasTrabajo=" + porqueBuscasTrabajo
				+ ", tipoTelefono=" + tipoTelefono + ", discapacidad="
				+ discapacidad + ", curp=" + curp + ", genero=" + genero
				+ ", fechaNacimiento=" + Arrays.toString(fechaNacimiento) + ", edad=" + edad
				+ ", idEntidadNacimiento=" + idEntidadNacimiento
				+ ", idEstadoCivil=" + idEstadoCivil + ", idTipoDiscapacidad="
				+ idTipoDiscapacidad + ", idMedioPortal=" + idMedioPortal
				+ ", confidencialidadDatos=" + confidencialidadDatos
				+ ", veracidadDatos=" + veracidadDatos
				+ ", aceptacionTerminos=" + aceptacionTerminos + ", fechaAlta="
				+ fechaAlta + ", estatus=" + estatus
				+ ", fechaUltimaActualizacion=" + fechaUltimaActualizacion
				+ ", estiloCv=" + estiloCv + ", idCandidato=" + idCandidato
				+ ", idUsuario=" + idUsuario + ", codigoPostal=" + codigoPostal
				+ ", situacionLegal=" + situacionLegal + ", diaNacimiento="
				+ diaNacimiento + ", mesNacimiento=" + mesNacimiento
				+ ", anoNacimiento=" + anoNacimiento + ", ladaTel=" + ladaTel
				+ ", claveTel=" + claveTel + ", telefono=" + telefono
				+ ", extensionTel=" + extensionTel + ", idTipoTelefono="
				+ idTipoTelefono + ", entidadFederativaNacimiento="
				+ entidadFederativaNacimiento + ", idEntidadFederativa="
				+ idEntidadFederativa + ", listaMedioEntero="
				+ listaMedioEntero + "]";
	}

	/**
	 * @param mensajes the mensajes to set
	 */
	public void setMensajes(String mensajes) {
		this.mensajes = mensajes;
	}

	/**
	 * @return the mensajes
	 */
	public String getMensajes() {
		return mensajes;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public Long getCreditoInfonavit() {
		return creditoInfonavit;
	}

	public void setCreditoInfonavit(Long creditoInfonavit) {
		this.creditoInfonavit = creditoInfonavit;
	}



	

}
