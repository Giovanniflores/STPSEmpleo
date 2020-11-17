package mx.gob.stps.portal.core.empresa.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.GENERO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public class RegistroEmpresaVO implements Serializable{

	private static final long serialVersionUID = 7282902705215572376L;

	private String usuario;
	private String contrasena;
	private String confirmacion;
	private String correoElectronico;
	private int esCorreo;
	private boolean cuentaRegenerada;
	
	private long idEmpresa;
	private String idPortalEmpleo;
	private Date fechaAlta;
	private int estatus;
	private Date fechaUltimaActualizacion;
	
	private String rfc;
	private long idTipoPersona;
	private String tipoPersona;
		//PERSONA FISICA
		private String nombre;
		private String apellido1;
		private String apellido2;
		private Date fechaNacimiento;
		private String fechaNacimientoString;
		private String idEntidadNacimientoString;
		private String generoString;
		
		private String curp;
		private int genero;	
		private int generoMasculino = GENERO.MASCULINO.getIdOpcion();
		private int generoFemenino  = GENERO.FEMENINO.getIdOpcion();	
		private int idEntidadNacimiento;
		private String entidadNacimiento;			
		//PERSONA MORAL
		private String razonSocial;
		private Date fechaActa;	
	private String descripcion;	
	private String nombreComercial;
	private String contactoEmpresa;
	private String cargoContacto;
	private long idTipoEmpresa;
	private String tipoEmpresa;
	private long idTipoSociedad;
	private long idActividadEconomica;
	private String actividadEconomica;
	private int numeroEmpleados;
	private long idMedio;
	private int confidencial;	
	private String paginaWeb;
	private int aceptacionTerminos;
	private int aseguraDatos;	
	//DOMICILIO
	private String calle;
	private String entreCalle;
	private String yCalle;
	private String numeroExterior;
	private String numeroInterior;	
	private int idEntidad;
	private int idMunicipio;
	private int idColonia;
	private String codigoPostal;
	//TELEFONO
	private int tipoTelefono;
	private String acceso;
	private String clave;
	private String telefono;
	private String extension;
	private List<TelefonoVO> listaTelefonos;
	
	/**Para Geolocalizacion**/
	private Double latitud;
	private Double longitud;

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
	 * @return the fechaUltimaActualizacion
	 */
	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}
	/**
	 * @param fechaUltimaActualizacion the fechaUltimaActualizacion to set
	 */
	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
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
	
	public String getFechaNacimientoString() {
		return fechaNacimientoString;
	}
	public void setFechaNacimientoString(String fechaNacimientoString) {
		this.fechaNacimientoString = fechaNacimientoString;
	}
	public String getIdEntidadNacimientoString() {
		return idEntidadNacimientoString;
	}
	public void setIdEntidadNacimientoString(String idEntidadNacimientoString) {
		this.idEntidadNacimientoString = idEntidadNacimientoString;
	}
	public String getGeneroString() {
		return generoString;
	}
	public void setGeneroString(String generoString) {
		this.generoString = generoString;
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
	/**
	 * @return the idActividadEconomica
	 */
	public long getIdActividadEconomica() {
		return idActividadEconomica;
	}
	/**
	 * @param idActividadEconomica the idActividadEconomica to set
	 */
	public void setIdActividadEconomica(long idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
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
	 * @return the aceptacionTerminos
	 */
	public int getAceptacionTerminos() {
		return aceptacionTerminos;
	}
	/**
	 * @param aceptacionTerminos the aceptacionTerminos to set
	 */
	public void setAceptacionTerminos(int aceptacionTerminos) {
		this.aceptacionTerminos = aceptacionTerminos;
	}
	/**
	 * @return the aseguraDatos
	 */
	public int getAseguraDatos() {
		return aseguraDatos;
	}
	/**
	 * @param aseguraDatos the aseguraDatos to set
	 */
	public void setAseguraDatos(int aseguraDatos) {
		this.aseguraDatos = aseguraDatos;
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
	 * @return the listaTelefonos
	 */
	public List<TelefonoVO> getListaTelefonos() {
		return listaTelefonos;
	}
	/**
	 * @param listaTelefonos the listaTelefonos to set
	 */
	public void setListaTelefonos(List<TelefonoVO> listaTelefonos) {
		this.listaTelefonos = listaTelefonos;
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
	public String getNombreEmpresa(){
		String strName = null;
		if(Constantes.TIPO_PERSONA.PERSONA_FISICA.getIdTipoPersona() == idTipoPersona){
			strName = nombre + (apellido1!=null?(" " + apellido1) : "")  + (apellido2!=null?(" " + apellido2) : "");
		} else if(Constantes.TIPO_PERSONA.PERSONA_MORAL.getIdTipoPersona() == idTipoPersona){
			strName = razonSocial;			
		}
		return strName;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("RegistroEmpresaVO [usuario=");
		builder.append(usuario);
		builder.append(", contrasena=");
		builder.append(contrasena);
		builder.append(", confirmacion=");
		builder.append(confirmacion);
		builder.append(", correoElectronico=");
		builder.append(correoElectronico);
		builder.append(", esCorreo=");
		builder.append(esCorreo);
		builder.append(", idEmpresa=");
		builder.append(idEmpresa);
		builder.append(", idPortalEmpleo=");
		builder.append(idPortalEmpleo);
		builder.append(", fechaAlta=");
		builder.append(fechaAlta);
		builder.append(", estatus=");
		builder.append(estatus);
		builder.append(", fechaUltimaActualizacion=");
		builder.append(fechaUltimaActualizacion);
		builder.append(", rfc=");
		builder.append(rfc);
		builder.append(", idTipoPersona=");
		builder.append(idTipoPersona);
		builder.append(", tipoPersona=");
		builder.append(tipoPersona);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido1=");
		builder.append(apellido1);
		builder.append(", apellido2=");
		builder.append(apellido2);
		builder.append(", fechaNacimiento=");
		builder.append(fechaNacimiento);
		builder.append(", razonSocial=");
		builder.append(razonSocial);
		builder.append(", fechaActa=");
		builder.append(fechaActa);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", nombreComercial=");
		builder.append(nombreComercial);
		builder.append(", contactoEmpresa=");
		builder.append(contactoEmpresa);
		builder.append(", cargoContacto=");
		builder.append(cargoContacto);		
		builder.append(", idTipoEmpresa=");
		builder.append(idTipoEmpresa);
		builder.append(", tipoEmpresa=");
		builder.append(tipoEmpresa);
		builder.append(", idTipoSociedad=");
		builder.append(idTipoSociedad);		
		builder.append(", idActividadEconomica=");
		builder.append(idActividadEconomica);
		builder.append(", actividadEconomica=");
		builder.append(actividadEconomica);
		builder.append(", numeroEmpleados=");
		builder.append(numeroEmpleados);
		builder.append(", idMedio=");
		builder.append(idMedio);
		builder.append(", confidencial=");
		builder.append(confidencial);
		builder.append(", paginaWeb=");
		builder.append(paginaWeb);
		builder.append(", aceptacionTerminos=");
		builder.append(aceptacionTerminos);
		builder.append(", aseguraDatos=");
		builder.append(aseguraDatos);
		builder.append(", calle=");
		builder.append(calle);
		builder.append(", entreCalle=");
		builder.append(entreCalle);
		builder.append(", yCalle=");
		builder.append(yCalle);
		builder.append(", numeroExterior=");
		builder.append(numeroExterior);
		builder.append(", numeroInterior=");
		builder.append(numeroInterior);
		builder.append(", idEntidad=");
		builder.append(idEntidad);
		builder.append(", idMunicipio=");
		builder.append(idMunicipio);
		builder.append(", idColonia=");
		builder.append(idColonia);
		builder.append(", codigoPostal=");
		builder.append(codigoPostal);
		builder.append(", tipoTelefono=");
		builder.append(tipoTelefono);
		builder.append(", acceso=");
		builder.append(acceso);
		builder.append(", clave=");
		builder.append(clave);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append(", extension=");
		builder.append(extension);
		builder.append(", latitud=");
		builder.append(latitud);
		builder.append(", longitud=");
		builder.append(longitud);
		builder.append("]");
		return builder.toString();		
	}
}
