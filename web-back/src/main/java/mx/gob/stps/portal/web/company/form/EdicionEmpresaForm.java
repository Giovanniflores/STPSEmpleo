package mx.gob.stps.portal.web.company.form;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mx.gob.stps.portal.core.infra.utils.Constantes.GENERO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EdicionEmpresaForm extends ActionForm{

	private static final long serialVersionUID = -2522337956218865579L;
	
	private long idEmpresa;
	private long idUsuario;
	private long idTipoUsuario;
	private String idPortalEmpleo;
	private String usuario;

	private String correoAux;
	private String correoElectronico;
	private int esCorreo;
	private boolean correoElectronicoUnico;

	private String rfc;
	private long idTipoPersona;
	private String tipoPersona;
	//PERSONA FISICA
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date fechaNacimiento;

	private String curp;
	private int genero;	
	private int generoMasculino = GENERO.MASCULINO.getIdOpcion();
	private int generoFemenino  = GENERO.FEMENINO.getIdOpcion();	
	private int idEntidadNacimiento;
	private String entidadNacimiento;
	private short CurpValidada;
	private Long IdMotivoNoValidacion;
	private String pregunta;
	private String respuestaUsuario;
	private boolean curpUnico;		
	//PERSONA MORAL
	private String razonSocial;
	private Date fechaActa;	
	private String descripcion;	
	private String nombreComercial;
	private String contactoEmpresa;
	private long idTipoEmpresa;
	private String tipoEmpresa;
	private long idTipoActividadEconomica;
	private String actividadEconomica;
	private String sector;
	private String subsector;
	private int numeroEmpleados;
	private long idMedio;
	private String medioEnterado;
	private int confidencial;		
	//DOMICiLIO
	private long idDomicilio;
	private long idTipoPropietario;
	private String calle;
	private String entreCalle;
	private String yCalle;
	private String numeroExterior;
	private String numeroInterior;	
	private int idEntidad;
	private int idMunicipio;
	private int idColonia;
	private String codigoPostal;	
	private String codigoPostalAux;	
	//TELEFONOS
	private List<CatalogoOpcionVO> tipoTelefonos;
	/*Telefono principal*/
	private String accesoCelular;
	private String accesoFijo;	
	private int idTipoTelefonoFijo;
	private int idTipoTelefonoCelular;	
	private int tipoTelefono;
	private String acceso;
	private String clave;
	private String telefono;
	private String extension;
	private long idTelefono;
	private long idPropietario;
	private long idTipoPropietarioTel;
	private long idTipoTelefono;	
	
	private String paginaWeb;
	
	private ResultVO msg;
	private String password;
	
	/**Para Geolocalizacion**/
	private Double latitud;
	private Double longitud;
	private boolean permisoGeolocalizacion;


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

	/**
	 * @return the idTipoUsuario
	 */
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	/**
	 * @param idTipoUsuario the idTipoUsuario to set
	 */
	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	/**
	 * @return the idPortalEmpleo
	 */
	public String getIdPortalEmpleo() {
		return idPortalEmpleo;
	}

	/**
	 * @param idPortalEmpleo the idPortalEmpleo to set
	 */
	public void setIdPortalEmpleo(String idPortalEmpleo) {
		this.idPortalEmpleo = idPortalEmpleo;
	}

	/**
	 * @return the correoAux
	 */
	public String getCorreoAux() {
		return correoAux;
	}

	/**
	 * @param correoAux the correoAux to set
	 */
	public void setCorreoAux(String correoAux) {
		this.correoAux = correoAux;
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
	 * @return the esCorreo
	 */
	public int getEsCorreo() {
		return esCorreo;
	}

	/**
	 * @param esCorreo the esCorreo to set
	 */
	public void setEsCorreo(int esCorreo) {
		this.esCorreo = esCorreo;
	}

	/**
	 * @return the correoElectronicoUnico
	 */
	public boolean isCorreoElectronicoUnico() {
		return correoElectronicoUnico;
	}

	/**
	 * @param correoElectronicoUnico the correoElectronicoUnico to set
	 */
	public void setCorreoElectronicoUnico(boolean correoElectronicoUnico) {
		this.correoElectronicoUnico = correoElectronicoUnico;
	}

	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * @return the idTipoPersona
	 */
	public long getIdTipoPersona() {
		return idTipoPersona;
	}

	/**
	 * @param idTipoPersona the idTipoPersona to set
	 */
	public void setIdTipoPersona(long idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}

	/**
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * @param tipoPersona the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
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
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public int getGeneroMasculino() {
		return generoMasculino;
	}

	public void setGeneroMasculino(int generoMasculino) {
		this.generoMasculino = generoMasculino;
	}

	public int getGeneroFemenino() {
		return generoFemenino;
	}

	public void setGeneroFemenino(int generoFemenino) {
		this.generoFemenino = generoFemenino;
	}

	public int getIdEntidadNacimiento() {
		return idEntidadNacimiento;
	}

	public void setIdEntidadNacimiento(int idEntidadNacimiento) {
		this.idEntidadNacimiento = idEntidadNacimiento;
	}

	public String getEntidadNacimiento() {
		return entidadNacimiento;
	}

	public void setEntidadNacimiento(String entidadNacimiento) {
		this.entidadNacimiento = entidadNacimiento;
	}

	public short getCurpValidada() {
		return CurpValidada;
	}

	public void setCurpValidada(short curpValidada) {
		CurpValidada = curpValidada;
	}

	public Long getIdMotivoNoValidacion() {
		return IdMotivoNoValidacion;
	}

	public void setIdMotivoNoValidacion(Long idMotivoNoValidacion) {
		IdMotivoNoValidacion = idMotivoNoValidacion;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuestaUsuario() {
		return respuestaUsuario;
	}

	public void setRespuestaUsuario(String respuestaUsuario) {
		this.respuestaUsuario = respuestaUsuario;
	}

	public boolean isCurpUnico() {
		return curpUnico;
	}

	public void setCurpUnico(boolean curpUnico) {
		this.curpUnico = curpUnico;
	}

	/**
	 * @return the razonSocial
	 */
	public String getRazonSocial() {
		return razonSocial;
	}

	/**
	 * @param razonSocial the razonSocial to set
	 */
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	/**
	 * @return the fechaActa
	 */
	public Date getFechaActa() {
		return fechaActa;
	}

	/**
	 * @param fechaActa the fechaActa to set
	 */
	public void setFechaActa(Date fechaActa) {
		this.fechaActa = fechaActa;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the nombreComercial
	 */
	public String getNombreComercial() {
		return nombreComercial;
	}

	/**
	 * @param nombreComercial the nombreComercial to set
	 */
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	/**
	 * @return the contactoEmpresa
	 */
	public String getContactoEmpresa() {
		return contactoEmpresa;
	}

	/**
	 * @param contactoEmpresa the contactoEmpresa to set
	 */
	public void setContactoEmpresa(String contactoEmpresa) {
		this.contactoEmpresa = contactoEmpresa;
	}

	/**
	 * @return the idTipoEmpresa
	 */
	public long getIdTipoEmpresa() {
		return idTipoEmpresa;
	}

	/**
	 * @param idTipoEmpresa the idTipoEmpresa to set
	 */
	public void setIdTipoEmpresa(long idTipoEmpresa) {
		this.idTipoEmpresa = idTipoEmpresa;
	}

	/**
	 * @return the tipoEmpresa
	 */
	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	/**
	 * @param tipoEmpresa the tipoEmpresa to set
	 */
	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	/**
	 * @return the idTipoActividadEconomica
	 */
	public long getIdTipoActividadEconomica() {
		return idTipoActividadEconomica;
	}

	/**
	 * @param idTipoActividadEconomica the idTipoActividadEconomica to set
	 */
	public void setIdTipoActividadEconomica(long idTipoActividadEconomica) {
		this.idTipoActividadEconomica = idTipoActividadEconomica;
	}

	/**
	 * @return the actividadEconomica
	 */
	public String getActividadEconomica() {
		return actividadEconomica;
	}

	/**
	 * @param actividadEconomica the actividadEconomica to set
	 */
	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	/**
	 * @return the numeroEmpleados
	 */
	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}

	/**
	 * @param numeroEmpleados the numeroEmpleados to set
	 */
	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}

	/**
	 * @return the idMedio
	 */
	public long getIdMedio() {
		return idMedio;
	}

	/**
	 * @param idMedio the idMedio to set
	 */
	public void setIdMedio(long idMedio) {
		this.idMedio = idMedio;
	}
	
	/**
	 * @return the medioEnterado
	 */
	public String getMedioEnterado() {
		return medioEnterado;
	}

	/**
	 * @param medioEnterado the medioEnterado to set
	 */
	public void setMedioEnterado(String medioEnterado) {
		this.medioEnterado = medioEnterado;
	}

	/**
	 * @return the confidencial
	 */
	public int getConfidencial() {
		return confidencial;
	}

	/**
	 * @param confidencial the confidencial to set
	 */
	public void setConfidencial(int confidencial) {
		this.confidencial = confidencial;
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
	 * @return the idMunicipio
	 */
	public int getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio the idMunicipio to set
	 */
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/**
	 * @return the idColonia
	 */
	public int getIdColonia() {
		return idColonia;
	}

	/**
	 * @param idColonia the idColonia to set
	 */
	public void setIdColonia(int idColonia) {
		this.idColonia = idColonia;
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
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the codigoPostalAux
	 */
	public String getCodigoPostalAux() {
		return codigoPostalAux;
	}

	/**
	 * @param codigoPostalAux the codigoPostalAux to set
	 */
	public void setCodigoPostalAux(String codigoPostalAux) {
		this.codigoPostalAux = codigoPostalAux;
	}

	/**
	 * @return the tipoTelefonos
	 */
	public List<CatalogoOpcionVO> getTipoTelefonos() {
		return tipoTelefonos;
	}

	/**
	 * @param tipoTelefonos the tipoTelefonos to set
	 */
	public void setTipoTelefonos(List<CatalogoOpcionVO> tipoTelefonos) {
		this.tipoTelefonos = tipoTelefonos;
	}

	/**
	 * @return the accesoCelular
	 */
	public String getAccesoCelular() {
		return accesoCelular;
	}

	/**
	 * @param accesoCelular the accesoCelular to set
	 */
	public void setAccesoCelular(String accesoCelular) {
		this.accesoCelular = accesoCelular;
	}

	/**
	 * @return the accesoFijo
	 */
	public String getAccesoFijo() {
		return accesoFijo;
	}

	/**
	 * @param accesoFijo the accesoFijo to set
	 */
	public void setAccesoFijo(String accesoFijo) {
		this.accesoFijo = accesoFijo;
	}

	/**
	 * @return the idTipoTelefonoFijo
	 */
	public int getIdTipoTelefonoFijo() {
		return idTipoTelefonoFijo;
	}

	/**
	 * @param idTipoTelefonoFijo the idTipoTelefonoFijo to set
	 */
	public void setIdTipoTelefonoFijo(int idTipoTelefonoFijo) {
		this.idTipoTelefonoFijo = idTipoTelefonoFijo;
	}

	/**
	 * @return the idTipoTelefonoCelular
	 */
	public int getIdTipoTelefonoCelular() {
		return idTipoTelefonoCelular;
	}

	/**
	 * @param idTipoTelefonoCelular the idTipoTelefonoCelular to set
	 */
	public void setIdTipoTelefonoCelular(int idTipoTelefonoCelular) {
		this.idTipoTelefonoCelular = idTipoTelefonoCelular;
	}

	/**
	 * @return the tipoTelefono
	 */
	public int getTipoTelefono() {
		return tipoTelefono;
	}

	/**
	 * @param tipoTelefono the tipoTelefono to set
	 */
	public void setTipoTelefono(int tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
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
	 * @return the idTelefono
	 */
	public long getIdTelefono() {
		return idTelefono;
	}

	/**
	 * @param idTelefono the idTelefono to set
	 */
	public void setIdTelefono(long idTelefono) {
		this.idTelefono = idTelefono;
	}

	/**
	 * @return the idPropietario
	 */
	public long getIdPropietario() {
		return idPropietario;
	}

	/**
	 * @param idPropietario the idPropietario to set
	 */
	public void setIdPropietario(long idPropietario) {
		this.idPropietario = idPropietario;
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
	 * @return the paginaWeb
	 */
	public String getPaginaWeb() {
		return paginaWeb;
	}

	/**
	 * @param paginaWeb the paginaWeb to set
	 */
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
	
	/**
	 * @return the msg
	 */
	public ResultVO getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(ResultVO msg) {
		this.msg = msg;
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
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		return super.validate(mapping, request);
	}		

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSubsector() {
		return subsector;
	}

	public void setSubsector(String subsector) {
		this.subsector = subsector;
	}
	
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

	public String toString() {
		return "EdicionEmpresaForm [correoElectronico=" + correoElectronico
				+ ", esCorreo=" + esCorreo
				+ ", idEmpresa=" + idEmpresa
				+ ", idUsuario=" + idUsuario
				+ ", idTipoUsuario=" + idTipoUsuario
				+ ", rfc=" + rfc
				+ ", idTipoPersona=" + idTipoPersona
				+ ", tipoPersona=" + tipoPersona
				+ ", nombre=" + nombre
				+ ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2
				+ ", fechaNacimiento=" + fechaNacimiento
				+ ", razonSocial=" + razonSocial
				+ ", fechaActa=" + fechaActa			
				+ ", descripcion=" + descripcion
				+ ", nombreComercial=" + nombreComercial	
				+ ", contactoEmpresa=" + contactoEmpresa
				+ ", idTipoEmpresa=" + idTipoEmpresa
				+ ", tipoEmpresa=" + tipoEmpresa
				+ ", idTipoActividadEconomica=" + idTipoActividadEconomica
				+ ", actividadEconomica=" + actividadEconomica
				+ ", numeroEmpleados=" + numeroEmpleados
				+ ", idMedio=" + idMedio
				+ ", confidencial=" + confidencial				
				+ ", calle=" + calle	
				+ ", entreCalle=" + entreCalle
				+ ", yCalle=" + yCalle
				+ ", numeroExterior=" + numeroExterior
				+ ", numeroInterior=" + numeroInterior
				+ ", idEntidad=" + idEntidad
				+ ", idMunicipio=" + idMunicipio
				+ ", idColonia=" + idColonia		
				+ ", codigoPostal=" + codigoPostal	
				+ ", accesoCelular=" + accesoCelular	
				+ ", accesoFijo=" + accesoFijo
				+ ", idTipoTelefonoFijo=" + idTipoTelefonoFijo
				+ ", idTipoTelefonoCelular=" + idTipoTelefonoCelular
				+ ", tipoTelefono=" + tipoTelefono
				+ ", acceso=" + acceso
				+ ", clave=" + clave
				+ ", telefono=" + telefono		
				+ ", extension=" + extension
				+ ", paginaWeb=" + paginaWeb
				+ ", curp=" + curp	
				+ ", genero=" + genero	
				+ ", idEntidadNacimiento=" + idEntidadNacimiento	
				+ ", entidadNacimiento=" + entidadNacimiento	
				+ ", CurpValidada=" + CurpValidada	
				+ ", IdMotivoNoValidacion=" + IdMotivoNoValidacion	
				+ ", pregunta=" + pregunta	
				+ ", respuestaUsuario=" + respuestaUsuario	
				+ ", curpUnico=" + curpUnico					
				+ ", idPortalEmpleo=" + idPortalEmpleo
				+ ", latitud=" + latitud
				+ ", longitud=" + longitud
				+ ", permisoGeolocalizacion=" + permisoGeolocalizacion
				+ "]";
	}

	public void reset() {
		idEmpresa = 0;		
		idUsuario = 0;	
		idTipoUsuario = 0;
		idPortalEmpleo = null;
		correoAux = null;
		correoElectronico = null;
		esCorreo = 0;	
		correoElectronicoUnico = false; 
		
		rfc = null;
		idTipoPersona = 0;
		tipoPersona = null;
			//PERSONA FISICA
			nombre = null;
			apellido1 = null;
			apellido2 = null;
			fechaNacimiento = null;
			//PERSONA MORAL
			razonSocial = null;
			fechaActa = null;	
		descripcion = null;	
		nombreComercial = null;
		contactoEmpresa = null;
		idTipoEmpresa = 0;
		tipoEmpresa = null;
		idTipoActividadEconomica = 0;
		actividadEconomica = null;
		numeroEmpleados = 0;
		idMedio = 0;
		medioEnterado = null;
		confidencial = 0;	
		//DOMICiLIO
		idDomicilio = 0;
		idTipoPropietario = 0;
		calle = null;
		entreCalle = null;
		yCalle = null;
		numeroExterior = null;
		numeroInterior = null;	
		idEntidad = 0;
		idMunicipio = 0;
		idColonia = 0;
		codigoPostal = null;	
		codigoPostalAux = null;	
		//TELEFONOS
		tipoTelefonos = null;
		accesoCelular = null;
		accesoFijo = null;	
		idTipoTelefonoFijo = 0;
		idTipoTelefonoCelular = 0;	
		tipoTelefono = 0;
		acceso = null;
		clave = null;
		telefono = null;
		extension = null;	
		paginaWeb = null;		
		idTelefono = 0;
		idPropietario = 0;
		idTipoPropietarioTel = 0;
		idTipoTelefono = 0;	
		msg = null;
		password = null;
		
		curp = null;
		genero = 0;
		idEntidadNacimiento = 0;
		entidadNacimiento = null;
		CurpValidada = 0;
		IdMotivoNoValidacion = null;
		pregunta = null;
		respuestaUsuario = null;
		curpUnico = false;
		latitud=null;
		longitud=null;
		permisoGeolocalizacion=false;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	

}
