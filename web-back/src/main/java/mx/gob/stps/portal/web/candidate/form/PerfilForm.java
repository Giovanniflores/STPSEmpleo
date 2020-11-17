package mx.gob.stps.portal.web.candidate.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.stps.portal.core.candidate.vo.OtroMedioVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class PerfilForm extends PPCStatusForm  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -990593591827698921L;
	/*Id's candidato*/
	private long idCandidato;
	private long idUsuario;
	private long idTipoUsuario;
	private String usuario;
	private int estatusCandidato;
	/*Datos personales*/
	private String curp;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int idGenero;
	private String genero;
	private String fechaNacimiento;
	private int edad;
	private long idEntidadNacimiento;
	private String entidadNacimiento;
	private long idEstadoCivil;
	private long idTipoDiscapacidad;
	/*Domicilio*/
	private long idDomicilio;
	private long idTipoPropietario;
	private long idEntidad;
	private long idMunicipio;
	private long idColonia;
	private String calle;
	private String numeroInterior;
	private String numeroExterior;
	private String entreCalle;
	private String yCalle;
	private String codigoPostal;
	/*Datos de contacto*/
	private int confidencialidad;
	private int contactoCorreo;
	private int contactoTelefono;
	private int horaContactoIni;
	private int horaContactoFin;
	/*Telefono principal*/
	private long idTelefono;
	private long idPropietario;
	private long idTipoPropietarioTel;
	private long idTipoTelefono;
	private String acceso;
	private String clave;
	private String telefono;
	private String extension;
	/*Telefono cambio*/
	private long idTelefono1;
	private long idPropietario1;
	private long idTipoPropietarioTel1;
	private long idTipoTelefono1;
	private String acceso1;
	private String clave1;
	private String telefono1;
	private String extension1;
	
	private long idTelefono2;
	private long idPropietario2;
	private long idTipoPropietarioTel2;
	private long idTipoTelefono2;
	private String acceso2;
	private String clave2;
	private String telefono2;
	private String extension2;
	
	private long idTelefono3;
	private long idPropietario3;
	private long idTipoPropietarioTel3;
	private long idTipoTelefono3;
	private String acceso3;
	private String clave3;
	private String telefono3;
	private String extension3;
	/*Correo electronico*/
	private String correoAux;
	private String correoElectronico;
	/*Situacion Laboral*/
	private int idRecibeOferta;
	private int idTrabaja;
	private int idRazonBusqueda;
	private String descripcionOtroMotivoBusq;
	private String inicioBusqueda;//Fecha de inicio de busqueda de empleo
	private int idMedioBusqueda;
	private int idMedioPortal;
	//Consulta otros medios del candidato
	private List<OtroMedioVO> otrosMediosVO;
	//Guardar otros medios
	private long[] idOtrosMedios;
	/*Lista de catalogos*/
	private List<CatalogoOpcionVO> tipoTelefonos;
	private List<CatalogoOpcionVO> otrosMedios;
	/*mensaje*/
	private ResultVO msg;
	private String password;
	private int idMotivoDesactivacion;
	private String detalleDesactivacion;
	/*Para la integracion con google maps*/
	private Double latitud;
	private Double longitud;
	private boolean permisoGeolocalizacion;
	
	/*
	 * Metodo constructor 
	 **/
	public PerfilForm() {
		idOtrosMedios = new long[0];
		tipoTelefonos = new ArrayList<CatalogoOpcionVO>();
		otrosMedios = new ArrayList<CatalogoOpcionVO>();
	}
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
	 * @return the idUsuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}
	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}
	
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the estatusCandidato
	 */
	public int getEstatusCandidato() {
		return estatusCandidato;
	}
	/**
	 * @param estatusCandidato the estatusCandidato to set
	 */
	public void setEstatusCandidato(int estatusCandidato) {
		this.estatusCandidato = estatusCandidato;
	}
	/**
	 * @return the curp
	 */
	public String getCurp() {
		return curp;
	}
	/**
	 * @param curp the curp to set
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}
	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}
	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
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
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}
	/**
	 * @param edad the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	/**
	 * @return the idEntidadNacimiento
	 */
	public long getIdEntidadNacimiento() {
		return idEntidadNacimiento;
	}
	/**
	 * @param idEntidadNacimiento the idEntidadNacimiento to set
	 */
	public void setIdEntidadNacimiento(long idEntidadNacimiento) {
		this.idEntidadNacimiento = idEntidadNacimiento;
	}
	/**
	 * @return the entidadNacimiento
	 */
	public String getEntidadNacimiento() {
		return entidadNacimiento;
	}
	/**
	 * @param entidadNacimiento the entidadNacimiento to set
	 */
	public void setEntidadNacimiento(String entidadNacimiento) {
		this.entidadNacimiento = entidadNacimiento;
	}
	/**
	 * @return the idEstadoCivil
	 */
	public long getIdEstadoCivil() {
		return idEstadoCivil;
	}
	/**
	 * @param idEstadoCivil the idEstadoCivil to set
	 */
	public void setIdEstadoCivil(long idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}
	/**
	 * @return the idTipoDiscapacidad
	 */
	public long getIdTipoDiscapacidad() {
		return idTipoDiscapacidad;
	}
	/**
	 * @param idTipoDiscapacidad the idTipoDiscapacidad to set
	 */
	public void setIdTipoDiscapacidad(long idTipoDiscapacidad) {
		this.idTipoDiscapacidad = idTipoDiscapacidad;
	}
	/**
	 * @return the idDomicilio
	 */
	public long getIdDomicilio() {
		return idDomicilio;
	}
	/**
	 * @param idDomicilio the idDomicilio to set
	 */
	public void setIdDomicilio(long idDomicilio) {
		this.idDomicilio = idDomicilio;
	}
	/**
	 * @return the idTipoPropietario
	 */
	public long getIdTipoPropietario() {
		return idTipoPropietario;
	}
	/**
	 * @param idTipoPropietario the idTipoPropietario to set
	 */
	public void setIdTipoPropietario(long idTipoPropietario) {
		this.idTipoPropietario = idTipoPropietario;
	}
	/**
	 * @return the idEntidad
	 */
	public long getIdEntidad() {
		return idEntidad;
	}
	/**
	 * @param idEntidad the idEntidad to set
	 */
	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
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
	 * @return the idColonia
	 */
	public long getIdColonia() {
		return idColonia;
	}
	/**
	 * @param idColonia the idColonia to set
	 */
	public void setIdColonia(long idColonia) {
		this.idColonia = idColonia;
	}
	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}
	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	/**
	 * @return the numeroInterior
	 */
	public String getNumeroInterior() {
		return numeroInterior;
	}
	/**
	 * @param numeroInterior the numeroInterior to set
	 */
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	/**
	 * @return the numeroExterior
	 */
	public String getNumeroExterior() {
		return numeroExterior;
	}
	/**
	 * @param numeroExterior the numeroExterior to set
	 */
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	/**
	 * @return the entreCalle
	 */
	public String getEntreCalle() {
		return entreCalle;
	}
	/**
	 * @param entreCalle the entreCalle to set
	 */
	public void setEntreCalle(String entreCalle) {
		this.entreCalle = entreCalle;
	}
	/**
	 * @return the yCalle
	 */
	public String getyCalle() {
		return yCalle;
	}
	/**
	 * @param yCalle the yCalle to set
	 */
	public void setyCalle(String yCalle) {
		this.yCalle = yCalle;
	}
	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}
	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setcodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public void setConfidencialidad(int confidencialidad) {
		this.confidencialidad = confidencialidad;
	}
	public int getConfidencialidad() {
		return confidencialidad;
	}
	/**
	 * @return the contactoCorreo
	 */
	public int getContactoCorreo() {
		return contactoCorreo;
	}
	/**
	 * @param contactoCorreo the contactoCorreo to set
	 */
	public void setContactoCorreo(int contactoCorreo) {
		this.contactoCorreo = contactoCorreo;
	}
	/**
	 * @return the contactoTelefono
	 */
	public int getContactoTelefono() {
		return contactoTelefono;
	}
	/**
	 * @param contactoTelefono the contactoTelefono to set
	 */
	public void setContactoTelefono(int contactoTelefono) {
		this.contactoTelefono = contactoTelefono;
	}
	/**
	 * @return the horaContactoIni
	 */
	public int getHoraContactoIni() {
		return horaContactoIni;
	}
	/**
	 * @param horaContactoIni the horaContactoIni to set
	 */
	public void setHoraContactoIni(int horaContactoIni) {
		this.horaContactoIni = horaContactoIni;
	}
	/**
	 * @return the horaContactoFin
	 */
	public int getHoraContactoFin() {
		return horaContactoFin;
	}
	/**
	 * @param horaContactoFin the horaContactoFin to set
	 */
	public void setHoraContactoFin(int horaContactoFin) {
		this.horaContactoFin = horaContactoFin;
	}
	/**
	 * @param idTelefono the idTelefono to set
	 */
	public void setIdTelefono(long idTelefono) {
		this.idTelefono = idTelefono;
	}
	/**
	 * @return the idTelefono
	 */
	public long getIdTelefono() {
		return idTelefono;
	}
	/**
	 * @param idPropietario the idPropietario to set
	 */
	public void setIdPropietario(long idPropietario) {
		this.idPropietario = idPropietario;
	}
	/**
	 * @return the idPropietario
	 */
	public long getIdPropietario() {
		return idPropietario;
	}
	/**
	 * @return the idTipoPropietarioTel
	 */
	public long getIdTipoPropietarioTel() {
		return idTipoPropietarioTel;
	}
	/**
	 * @param idTipoPropietarioTel the idTipoPropietarioTel to set
	 */
	public void setIdTipoPropietarioTel(long idTipoPropietarioTel) {
		this.idTipoPropietarioTel = idTipoPropietarioTel;
	}
	/**
	 * @return the idTipoTelefono
	 */
	public long getIdTipoTelefono() {
		return idTipoTelefono;
	}
	/**
	 * @param idTipoTelefono the idTipoTelefono to set
	 */
	public void setIdTipoTelefono(long idTipoTelefono) {
		this.idTipoTelefono = idTipoTelefono;
	}
	/**
	 * @return the acceso
	 */
	public String getAcceso() {
		return acceso;
	}
	/**
	 * @param acceso the acceso to set
	 */
	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}
	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}
	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}
	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	/**
	 * @param correoAux the correoAux to set
	 */
	public void setCorreoAux(String correoAux) {
		this.correoAux = correoAux;
	}
	/**
	 * @return the correoAux
	 */
	public String getCorreoAux() {
		return correoAux;
	}
	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	/**
	 * @return the idRecibeOferta
	 */
	public int getIdRecibeOferta() {
		return idRecibeOferta;
	}
	/**
	 * @param idRecibeOferta the idRecibeOferta to set
	 */
	public void setIdRecibeOferta(int idRecibeOferta) {
		this.idRecibeOferta = idRecibeOferta;
	}
	/**
	 * @return the idTrabaja
	 */
	public int getIdTrabaja() {
		return idTrabaja;
	}
	/**
	 * @param idTrabaja the idTrabaja to set
	 */
	public void setIdTrabaja(int idTrabaja) {
		this.idTrabaja = idTrabaja;
	}
	/**
	 * @return the idRazonBusqueda
	 */
	public int getIdRazonBusqueda() {
		return idRazonBusqueda;
	}
	/**
	 * @param idRazonBusqueda the idRazonBusqueda to set
	 */
	public void setIdRazonBusqueda(int idRazonBusqueda) {
		this.idRazonBusqueda = idRazonBusqueda;
	}

	public String getDescripcionOtroMotivoBusq() {
		return descripcionOtroMotivoBusq;
	}

	public void setDescripcionOtroMotivoBusq(String descripcionOtroMotivoBusq) {
		this.descripcionOtroMotivoBusq = descripcionOtroMotivoBusq;
	}

	/**
	 * @return the inicioBusqueda
	 */
	public String getInicioBusqueda() {
		return inicioBusqueda;
	}
	/**
	 * @param inicioBusqueda the inicioBusqueda to set
	 */
	public void setInicioBusqueda(String inicioBusqueda) {
		this.inicioBusqueda = inicioBusqueda;
	}
	/**
	 * @return the idMedioBusqueda
	 */
	public int getIdMedioBusqueda() {
		return idMedioBusqueda;
	}
	/**
	 * @param idMedioBusqueda the idMedioBusqueda to set
	 */
	public void setIdMedioBusqueda(int idMedioBusqueda) {
		this.idMedioBusqueda = idMedioBusqueda;
	}
	/**
	 * @param otrosMediosVO the otrosMediosVO to set
	 */
	public void setOtrosMediosVO(List<OtroMedioVO> otrosMediosVO) {
		this.otrosMediosVO = otrosMediosVO;
	}
	/**
	 * @return the otrosMediosVO
	 */
	public List<OtroMedioVO> getOtrosMediosVO() {
		return otrosMediosVO;
	}
	/**
	 * @param idOtrosMedio the idOtrosMedio to set
	 */
	public void setIdOtrosMedio(int index, long idOtrosMedio) {
		this.idOtrosMedios[index] = idOtrosMedio;
	}
	/**
	 * @return the idOtrosMedio
	 */
	public long getIdOtrosMedio(int index) {
		return idOtrosMedios[index];
	}
	/**
	 * @param idOtrosMedios the idOtrosMedios to set
	 */
	public void setIdOtrosMedios(long[] idOtrosMedios) {
		this.idOtrosMedios = idOtrosMedios;
	}
	/**
	 * @return the idOtrosMedios
	 */
	public long[] getIdOtrosMedios() {
		return idOtrosMedios;
	}
	/**
	 * @param tipoTelefonos the tipoTelefonos to set
	 */
	public void setTipoTelefonos(List<CatalogoOpcionVO> tipoTelefonos) {
		this.tipoTelefonos = tipoTelefonos;
	}
	/**
	 * @return the tipoTelefonos
	 */
	public List<CatalogoOpcionVO> getTipoTelefonos() {
		return tipoTelefonos;
	}
	/**
	 * @param otrosMedios the otrosMedios to set
	 */
	public void setOtrosMedios(List<CatalogoOpcionVO> otrosMedios) {
		this.otrosMedios = otrosMedios;
	}
	/**
	 * @return the otrosMedios
	 */
	public List<CatalogoOpcionVO> getOtrosMedios() {
		return otrosMedios;
	}
	public void setMsg(ResultVO msg) {
		this.msg = msg;
	}
	public ResultVO getMsg() {
		return msg;
	}
	/**
	 * @return the idMedioPortal
	 */
	public int getIdMedioPortal() {
		return idMedioPortal;
	}
	/**
	 * @param idMedioPortal the idMedioPortal to set
	 */
	public void setIdMedioPortal(int idMedioPortal) {
		this.idMedioPortal = idMedioPortal;
	}
	
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getIdMotivoDesactivacion() {
		return idMotivoDesactivacion;
	}
	public void setIdMotivoDesactivacion(int idMotivoDesactivacion) {
		this.idMotivoDesactivacion = idMotivoDesactivacion;
	}
	public String getDetalleDesactivacion() {
		return detalleDesactivacion;
	}
	public void setDetalleDesactivacion(String detalleDesactivacion) {
		this.detalleDesactivacion = detalleDesactivacion;
	}	
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	/*public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = (String) converter.convert(Date.class, fechaNacimiento);
	}*/
	/**
	 * @param inicioBusqueda the inicioBusqueda to set
	 */
	/*public void setInicioBusqueda(Date inicioBusqueda) {
		this.inicioBusqueda = (String) converter.convert(Date.class, inicioBusqueda);
	}*/
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.reset(mapping, request);
	}
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return super.validate(mapping, request);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PerfilForm [idCandidato=");
		builder.append(idCandidato);
		builder.append(", idUsuario=");
		builder.append(idUsuario);
		builder.append(", curp=");
		builder.append(curp);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido1=");
		builder.append(apellido1);
		builder.append(", apellido2=");
		builder.append(apellido2);
		builder.append(", idGenero=");
		builder.append(idGenero);
		builder.append(", genero=");
		builder.append(genero);
		builder.append(", fechaNacimiento=");
		builder.append(fechaNacimiento);
		builder.append(", edad=");
		builder.append(edad);
		builder.append(", idEntidadNacimiento=");
		builder.append(idEntidadNacimiento);
		builder.append(", entidadNacimiento=");
		builder.append(entidadNacimiento);
		builder.append(", idEstadoCivil=");
		builder.append(idEstadoCivil);
		builder.append(", idTipoDiscapacidad=");
		builder.append(idTipoDiscapacidad);
		builder.append(", idDomicilio=");
		builder.append(idDomicilio);
		builder.append(", idTipoPropietario=");
		builder.append(idTipoPropietario);
		builder.append(", idEntidad=");
		builder.append(idEntidad);
		builder.append(", idMunicipio=");
		builder.append(idMunicipio);
		builder.append(", idColonia=");
		builder.append(idColonia);
		builder.append(", calle=");
		builder.append(calle);
		builder.append(", numeroInterior=");
		builder.append(numeroInterior);
		builder.append(", numeroExterior=");
		builder.append(numeroExterior);
		builder.append(", entreCalle=");
		builder.append(entreCalle);
		builder.append(", yCalle=");
		builder.append(yCalle);
		builder.append(", codigoPostal=");
		builder.append(codigoPostal);
		builder.append(", contactoCorreo=");
		builder.append(contactoCorreo);
		builder.append(", contactoTelefono=");
		builder.append(contactoTelefono);
		builder.append(", horaContactoIni=");
		builder.append(horaContactoIni);
		builder.append(", horaContactoFin=");
		builder.append(horaContactoFin);
		builder.append(", correoElectronico=");
		builder.append(correoElectronico);
		builder.append(", idRecibeOferta=");
		builder.append(idRecibeOferta);
		builder.append(", idTrabaja=");
		builder.append(idTrabaja);
		builder.append(", idRazonBusqueda=");
		builder.append(idRazonBusqueda);
		builder.append(", inicioBusqueda=");
		builder.append(inicioBusqueda);
		builder.append(", idMedioBusqueda=");
		builder.append(idMedioBusqueda);
		builder.append("]");
		return builder.toString();
	}
	public long getIdTelefono1() {
		return idTelefono1;
	}
	public void setIdTelefono1(long idTelefono1) {
		this.idTelefono1 = idTelefono1;
	}
	public long getIdPropietario1() {
		return idPropietario1;
	}
	public void setIdPropietario1(long idPropietario1) {
		this.idPropietario1 = idPropietario1;
	}
	public long getIdTipoPropietarioTel1() {
		return idTipoPropietarioTel1;
	}
	public void setIdTipoPropietarioTel1(long idTipoPropietarioTel1) {
		this.idTipoPropietarioTel1 = idTipoPropietarioTel1;
	}
	public long getIdTipoTelefono1() {
		return idTipoTelefono1;
	}
	public void setIdTipoTelefono1(long idTipoTelefono1) {
		this.idTipoTelefono1 = idTipoTelefono1;
	}
	public String getAcceso1() {
		return acceso1;
	}
	public void setAcceso1(String acceso1) {
		this.acceso1 = acceso1;
	}
	public String getClave1() {
		return clave1;
	}
	public void setClave1(String clave1) {
		this.clave1 = clave1;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getExtension1() {
		return extension1;
	}
	public void setExtension1(String extension1) {
		this.extension1 = extension1;
	}
	public long getIdTelefono2() {
		return idTelefono2;
	}
	public void setIdTelefono2(long idTelefono2) {
		this.idTelefono2 = idTelefono2;
	}
	public long getIdPropietario2() {
		return idPropietario2;
	}
	public void setIdPropietario2(long idPropietario2) {
		this.idPropietario2 = idPropietario2;
	}
	public long getIdTipoPropietarioTel2() {
		return idTipoPropietarioTel2;
	}
	public void setIdTipoPropietarioTel2(long idTipoPropietarioTel2) {
		this.idTipoPropietarioTel2 = idTipoPropietarioTel2;
	}
	public long getIdTipoTelefono2() {
		return idTipoTelefono2;
	}
	public void setIdTipoTelefono2(long idTipoTelefono2) {
		this.idTipoTelefono2 = idTipoTelefono2;
	}
	public String getAcceso2() {
		return acceso2;
	}
	public void setAcceso2(String acceso2) {
		this.acceso2 = acceso2;
	}
	public String getClave2() {
		return clave2;
	}
	public void setClave2(String clave2) {
		this.clave2 = clave2;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getExtension2() {
		return extension2;
	}
	public void setExtension2(String extension2) {
		this.extension2 = extension2;
	}
	public long getIdTelefono3() {
		return idTelefono3;
	}
	public void setIdTelefono3(long idTelefono3) {
		this.idTelefono3 = idTelefono3;
	}
	public long getIdPropietario3() {
		return idPropietario3;
	}
	public void setIdPropietario3(long idPropietario3) {
		this.idPropietario3 = idPropietario3;
	}
	public long getIdTipoPropietarioTel3() {
		return idTipoPropietarioTel3;
	}
	public void setIdTipoPropietarioTel3(long idTipoPropietarioTel3) {
		this.idTipoPropietarioTel3 = idTipoPropietarioTel3;
	}
	public long getIdTipoTelefono3() {
		return idTipoTelefono3;
	}
	public void setIdTipoTelefono3(long idTipoTelefono3) {
		this.idTipoTelefono3 = idTipoTelefono3;
	}
	public String getAcceso3() {
		return acceso3;
	}
	public void setAcceso3(String acceso3) {
		this.acceso3 = acceso3;
	}
	public String getClave3() {
		return clave3;
	}
	public void setClave3(String clave3) {
		this.clave3 = clave3;
	}
	public String getTelefono3() {
		return telefono3;
	}
	public void setTelefono3(String telefono3) {
		this.telefono3 = telefono3;
	}
	public String getExtension3() {
		return extension3;
	}
	public void setExtension3(String extension3) {
		this.extension3 = extension3;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	/*Para la integracion con google maps*/
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public boolean isPermisoGeolocalizacion() {
		return permisoGeolocalizacion;
	}
	public void setPermisoGeolocalizacion(boolean permisoGeolocalizacion) {
		this.permisoGeolocalizacion = permisoGeolocalizacion;
	}
}
