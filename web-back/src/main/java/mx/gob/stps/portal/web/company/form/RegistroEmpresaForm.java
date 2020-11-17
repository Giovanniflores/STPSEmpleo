package mx.gob.stps.portal.web.company.form;

import java.util.List;

import mx.gob.stps.portal.core.infra.utils.Constantes.GENERO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;

public class RegistroEmpresaForm extends ActionForm{

	private static final long serialVersionUID = 6547473672547298446L;
	private boolean registroDirecto;
	
	private String urlTerminosCondiciones;
	private String urlRecuperaContrasena;
	private long idEmpresa;
	private String idPortalEmpleo;
	
	private String etiquetaEmpresaPublica;
	private String etiquetaEmpresaMoral;
	private String etiquetaEmpresaOng;
	
	private String usuario;
	private String contrasena;
	private String confirmacion;
	private String correoElectronico;
	private int esCorreo;
	private boolean cuentaRegenerada;
	private boolean tieneOfertas;
	
	//CALENDARIO FECHA NACIMIENTO
	private int dia;
	private int mes;
	private int anio;	
	//CALENDARIO FECHA ACTA
	private int diaActa;
	private int mesActa;
	private int anioActa;		
	
	private String rfc;
	private long idTipoPersona;
	private String tipoPersona;
		//PERSONA FISICA
		private String nombre;
		private String apellido1;
		private String apellido2;
		private String fechaNacimiento;
		private int idConocesTuCurp;
		
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
		private String fechaActa;	
		private String razonSocialPublica;
		private String razonSocialOng;
	private String descripcion;	
	private String nombreComercial;
	private String contactoEmpresa;
	private String cargoContacto;
	private long idTipoEmpresa;
	private String tipoEmpresa;
	private long idTipoSociedad;
	private String tipoSociedad;
	private long idTipoActividadEconomica;
	private long idSector;
	private long idSubsector;
	private String actividadEconomica;
	private int numeroEmpleados;
	private long idMedio;
	private int confidencial;	
	//DOMICiLIO
	private String calle;
	private String entreCalle;
	private String yCalle;
	private String numeroExterior;
	private String numeroInterior;	
	private int idEntidad;
	private int idMunicipio;
	private int idColonia;
	private String codigoPostal;	
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
	
	private boolean usuarioUnico;
	private boolean correoElectronicoUnico;	
	private List<TelefonoVO> telefonos;
	private List<TelefonoVO> telefonosAdicionales;
	private int totalTelefonosAdicionales;
	
	/** Para Geolocalizacion**/
	private Double latitud;
	private Double longitud;
	private boolean permisoGeolocalizacion;

	
	public ActionErrors validate() {
		ActionErrors errors = new ActionErrors();
		
		if (usuario==null || usuario.trim().isEmpty())
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "El nombre de usuario es requerido"));

		if (contrasena==null || contrasena.trim().isEmpty())
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "La contraseña es requerida"));
		
		if (confirmacion==null || confirmacion.trim().isEmpty())
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.invalid", "La confirmación de  Contraseña es requerida"));

		return errors;
	}	
	
	/**
	 * @return the urlTerminosCondiciones
	 */
	public String getUrlTerminosCondiciones() {
		return urlTerminosCondiciones;
	}

	/**
	 * @param urlTerminosCondiciones the urlTerminosCondiciones to set
	 */
	public void setUrlTerminosCondiciones(String urlTerminosCondiciones) {
		this.urlTerminosCondiciones = urlTerminosCondiciones;
	}

	/**
	 * @return the urlRecuperaContrasena
	 */
	public String getUrlRecuperaContrasena() {
		return urlRecuperaContrasena;
	}

	/**
	 * @param urlRecuperaContrasena the urlRecuperaContrasena to set
	 */
	public void setUrlRecuperaContrasena(String urlRecuperaContrasena) {
		this.urlRecuperaContrasena = urlRecuperaContrasena;
	}

	/**
	 * @return the cuentaRegenerada
	 */
	public boolean isCuentaRegenerada() {
		return cuentaRegenerada;
	}

	/**
	 * @param cuentaRegenerada the cuentaRegenerada to set
	 */
	public void setCuentaRegenerada(boolean cuentaRegenerada) {
		this.cuentaRegenerada = cuentaRegenerada;
	}

	
	
	/**
	 * @return the tieneOfertas
	 */
	public boolean isTieneOfertas() {
		return tieneOfertas;
	}

	/**
	 * @param tieneOfertas the tieneOfertas to set
	 */
	public void setTieneOfertas(boolean tieneOfertas) {
		this.tieneOfertas = tieneOfertas;
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
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * @return the confirmacion
	 */
	public String getConfirmacion() {
		return confirmacion;
	}

	/**
	 * @param confirmacion the confirmacion to set
	 */
	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
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
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public int getIdConocesTuCurp() {
		return idConocesTuCurp;
	}

	public void setIdConocesTuCurp(int idConocesTuCurp) {
		this.idConocesTuCurp = idConocesTuCurp;
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
	 * @return the diaActa
	 */
	public int getDiaActa() {
		return diaActa;
	}

	/**
	 * @param diaActa the diaActa to set
	 */
	public void setDiaActa(int diaActa) {
		this.diaActa = diaActa;
	}

	/**
	 * @return the mesActa
	 */
	public int getMesActa() {
		return mesActa;
	}

	/**
	 * @param mesActa the mesActa to set
	 */
	public void setMesActa(int mesActa) {
		this.mesActa = mesActa;
	}

	/**
	 * @return the anioActa
	 */
	public int getAnioActa() {
		return anioActa;
	}

	/**
	 * @param anioActa the anioActa to set
	 */
	public void setAnioActa(int anioActa) {
		this.anioActa = anioActa;
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
	public String getFechaActa() {
		return fechaActa;
	}

	/**
	 * @param fechaActa the fechaActa to set
	 */
	public void setFechaActa(String fechaActa) {
		this.fechaActa = fechaActa;
	}

	
	
	public String getRazonSocialPublica() {
		return razonSocialPublica;
	}

	public void setRazonSocialPublica(String razonSocialPublica) {
		this.razonSocialPublica = razonSocialPublica;
	}

	public String getRazonSocialOng() {
		return razonSocialOng;
	}

	public void setRazonSocialOng(String razonSocialOng) {
		this.razonSocialOng = razonSocialOng;
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
	
	public String getCargoContacto() {
		return cargoContacto;
	}

	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
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

	public long getIdTipoSociedad() {
		return idTipoSociedad;
	}

	public void setIdTipoSociedad(long idTipoSociedad) {
		this.idTipoSociedad = idTipoSociedad;
	}

	public String getTipoSociedad() {
		return tipoSociedad;
	}

	public void setTipoSociedad(String tipoSociedad) {
		this.tipoSociedad = tipoSociedad;
	}

	/**
	 * @return the idActividadEconomica
	 */
	public long getIdTipoActividadEconomica() {
		return idTipoActividadEconomica;
	}

	/**
	 * @param idActividadEconomica the idActividadEconomica to set
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
	 * @return the dia
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia(int dia) {
		this.dia = dia;
	}

	/**
	 * @return the mes
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(int mes) {
		this.mes = mes;
	}

	/**
	 * @return the anio
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * @param anio the anio to set
	 */
	public void setAnio(int anio) {
		this.anio = anio;
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
	 * @return the usuarioUnico
	 */
	public boolean isUsuarioUnico() {
		return usuarioUnico;
	}

	/**
	 * @param usuarioUnico the usuarioUnico to set
	 */
	public void setUsuarioUnico(boolean usuarioUnico) {
		this.usuarioUnico = usuarioUnico;
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
	 * @return the telefonos
	 */
	public List<TelefonoVO> getTelefonos() {
		return telefonos;
	}

	/**
	 * @param telefonos the telefonos to set
	 */
	public void setTelefonos(List<TelefonoVO> telefonos) {
		this.telefonos = telefonos;
	}

	/**
	 * @return the telefonosAdicionales
	 */
	public List<TelefonoVO> getTelefonosAdicionales() {
		return telefonosAdicionales;
	}

	/**
	 * @param telefonosAdicionales the telefonosAdicionales to set
	 */
	public void setTelefonosAdicionales(List<TelefonoVO> telefonosAdicionales) {
		this.telefonosAdicionales = telefonosAdicionales;
	}

	/**
	 * @return the totalTelefonosAdicionales
	 */
	public int getTotalTelefonosAdicionales() {
		return totalTelefonosAdicionales;
	}

	/**
	 * @param totalTelefonosAdicionales the totalTelefonosAdicionales to set
	 */
	public void setTotalTelefonosAdicionales(int totalTelefonosAdicionales) {
		this.totalTelefonosAdicionales = totalTelefonosAdicionales;
	}
	
	public boolean isRegistroDirecto() {
		return registroDirecto;
	}
	public void setRegistroDirecto(boolean registroDirecto) {
		this.registroDirecto = registroDirecto;
	}		

	public long getIdSector() {
		return idSector;
	}

	public void setIdSector(long idSector) {
		this.idSector = idSector;
	}

	public long getIdSubsector() {
		return idSubsector;
	}

	public void setIdSubsector(long idSubsector) {
		this.idSubsector = idSubsector;
	}
	
	public String getEtiquetaEmpresaPublica() {
		return etiquetaEmpresaPublica;
	}

	public void setEtiquetaEmpresaPublica(String etiquetaEmpresaPublica) {
		this.etiquetaEmpresaPublica = etiquetaEmpresaPublica;
	}

	public String getEtiquetaEmpresaMoral() {
		return etiquetaEmpresaMoral;
	}

	public void setEtiquetaEmpresaMoral(String etiquetaEmpresaMoral) {
		this.etiquetaEmpresaMoral = etiquetaEmpresaMoral;
	}

	public String getEtiquetaEmpresaOng() {
		return etiquetaEmpresaOng;
	}

	public void setEtiquetaEmpresaOng(String etiquetaEmpresaOng) {
		this.etiquetaEmpresaOng = etiquetaEmpresaOng;
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
		return "RegistroEmpresaForm [usuario=" + usuario
				+ ", contrasena=" + contrasena
				+ ", confirmacion=" + confirmacion
				+ ", correoElectronico=" + correoElectronico
				+ ", esCorreo=" + esCorreo
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
				+ ", dia=" + dia + ", mes=" + mes + ", anio=" + anio
				+ ", diaActa=" + diaActa + ", mesActa=" + mesActa + ", anioActa=" + anioActa
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
		urlRecuperaContrasena = null;
		idEmpresa = 0;		
		idPortalEmpleo = null;
		
		usuario = null;
		contrasena = null;
		confirmacion = null;
		correoElectronico = null;
		esCorreo = 0;
		
		//CALENDARIO FECHA NACIMIENTO
		dia = 0;
		mes = 0;
		anio = 0;		
		//CALENDARIO FECHA ACTA
		diaActa = 0;
		mesActa = 0;
		anioActa = 0;				
		
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
		confidencial = 0;
		
		curp = null;
		genero = 0;
		idEntidadNacimiento = 0;
		entidadNacimiento = null;
		CurpValidada = 0;
		IdMotivoNoValidacion = null;
		pregunta = null;
		respuestaUsuario = null;
		curpUnico = false;		
		//DOMICiLIO
		calle = null;
		entreCalle = null;
		yCalle = null;
		numeroExterior = null;
		numeroInterior = null;	
		idEntidad = 0;
		idMunicipio = 0;
		idColonia = 0;
		codigoPostal = null;	
		//TELEFONOS
		tipoTelefonos = null;
		//telefono principal
		accesoCelular = null;
		accesoFijo = null;	
		idTipoTelefonoFijo = 0;
		idTipoTelefonoCelular = 0;	
		tipoTelefono = 0;
		acceso = null;
		clave = null;
		telefono = null;
		extension = null;	
		idTelefono = 0;
		idPropietario = 0;
		idTipoPropietarioTel = 0;
		idTipoTelefono = 0;	
		paginaWeb = null;
		usuarioUnico = false;
		correoElectronicoUnico =  false;
		telefonos = null;
		telefonosAdicionales = null;
		totalTelefonosAdicionales = 0;
		registroDirecto = false;
		latitud = null;
		longitud = null;
		permisoGeolocalizacion=false;
	}
	
	

}
