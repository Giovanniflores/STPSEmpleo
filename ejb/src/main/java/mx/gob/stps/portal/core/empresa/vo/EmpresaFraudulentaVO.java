package mx.gob.stps.portal.core.empresa.vo;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public class EmpresaFraudulentaVO implements Serializable  {
	private static final long serialVersionUID = 2094203442473520062L;

	private long idEmpresa;

	private String idPortalEmpleo;
	
	private long idUsuario;

	private String rfc;

	private long idTipoPersona;

	private String nombre;

	private String apellido1;

	private String apellido2;

	private Date fechaNacimiento;

	private String razonSocial;

	private Date fechaActa;

	private String descripcion;

	private String contactoEmpresa;

	private long idTipoEmpresa;

	private long idActividadEconomica;

	private int numeroEmpleados;

	private long idMedio;

	private int confidencial;

	private String paginaWeb;

	private int aceptacionTerminos;

	private Date fechaAlta;

	private int estatus;

	private Date fechaUltimaActualizacion;

	private String correoElectronico;
	
	private int aseguraDatos;
	
	private List<DomicilioVO> domicilios;
	private List<TelefonoVO> telefonos;
	
	private DomicilioVO domicilio;
	private TelefonoVO telefono;
	
	private String coincidencias; // Coincidencias de Empresa con la Empresas fraudulentas
	
	private String nombreComercial;
	
	private String nombreUsuarioDetectorFraude;
	
	private Date fechaDeteccionFraude;
	
	private String tipoEmpresa;

	private String actividadEconomica;	
	
	/**
	 * Constructor por defecto
	 * 
	 * @return EmpresaFraudulentaVO
	 */		
	public EmpresaFraudulentaVO(){}	
	
	/**
	 * Constructor que acepta todos los valores de una empresa fraudulenta
	 * 
	 * @param idEmpresa
	 * @param idPortalEmpleo
	 * @param rfc
	 * @param idTipoPersona
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param fechaNacimiento
	 * @param razonSocial
	 * @param fechaActa
	 * @param descripcion
	 * @param contactoEmpresa
	 * @param idTipoEmpresa
	 * @param idActividadEconomica
	 * @param numeroEmpleados
	 * @param idMedio
	 * @param confidencial
	 * @param paginaWeb
	 * @param aceptacionTerminos
	 * @param fechaAlta
	 * @param estatus
	 * @param fechaUltimaActualizacion
	 * @param correoElectronico
	 * @param aseguraDatos
	 * @return EmpresaPorAutorizarVO
	 */		
	public EmpresaFraudulentaVO(long idEmpresa, String idPortalEmpleo, String rfc, 
			int idTipoPersona, String nombre, String apellido1, String apellido2,
			Date fechaNacimiento, String razonSocial, Date fechaActa, String descripcion,
			String contactoEmpresa, int idTipoEmpresa, int idActividadEconomica, 
			int numeroEmpleados, int idMedio, int confidencial, String paginaWeb,
			int aceptacionTerminos, Date fechaAlta, int estatus, Date fechaUltimaActualizacion,
			String correoElectronico, List<TelefonoVO> lstTelefonos, int aseguraDatos){
		this.idEmpresa = idEmpresa;
		this.idPortalEmpleo = idPortalEmpleo;
		//this.idUsuario = idUsuario;
		this.rfc = rfc;
		this.idTipoPersona = idTipoPersona;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaNacimiento = fechaNacimiento;
		this.razonSocial = razonSocial;
		this.fechaActa = fechaActa;
		this.descripcion = descripcion;
		this.contactoEmpresa = contactoEmpresa;
		this.idTipoEmpresa = idTipoEmpresa;
		this.idActividadEconomica = idActividadEconomica;
		this.numeroEmpleados = numeroEmpleados;
		this.idMedio = idMedio;
		this.confidencial = confidencial;
		this.paginaWeb = paginaWeb;
		this.aceptacionTerminos = aceptacionTerminos;
		this.fechaAlta = fechaAlta;
		this.estatus = estatus;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.correoElectronico = correoElectronico;		
		this.aseguraDatos = aseguraDatos;
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

	public List<DomicilioVO> getDomicilios() {
		return domicilios;
	}

	public void addDomicilio(DomicilioVO domicilio) {
		if (domicilios==null) domicilios = new ArrayList<DomicilioVO>();
		domicilios.add(domicilio);
	}

	public void addDomicilios(List<DomicilioVO> domicilios) {
		if (this.domicilios==null) this.domicilios = new ArrayList<DomicilioVO>();
		this.domicilios.addAll(domicilios);
	}

	public List<TelefonoVO> getTelefonos() {
		return telefonos;
	}

	public void addTelefono(TelefonoVO telefono) {
		if (telefonos==null) telefonos = new ArrayList<TelefonoVO>();
		telefonos.add(telefono);
	}

	public void addTelefonos(List<TelefonoVO> telefonos) {
		if (this.telefonos==null) this.telefonos = new ArrayList<TelefonoVO>();
		this.telefonos.addAll(telefonos);
	}

	public void clearTelefonos(){
		this.telefonos = new ArrayList<TelefonoVO>();
	}
	
	public String getEmpresa(){
		String empresa = null;
		
		if (nombre!=null && !nombre.isEmpty()){
			empresa = nombre +" "+ apellido1 +" "+ apellido2;
		}else if (razonSocial!=null && !razonSocial.isEmpty()) {
			empresa = razonSocial;
		}else{
			empresa = "";
		}
		
		return empresa;
	}

	public DomicilioVO getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioVO domicilio) {
		this.domicilio = domicilio;
	}

	public TelefonoVO getTelefono() {
		return telefono;
	}

	public void setTelefono(TelefonoVO telefono) {
		this.telefono = telefono;
	}

	public String getCoincidencias() {
		return coincidencias;
	}

	public void setCoincidencias(String coincidencias) {
		this.coincidencias = coincidencias;
	}

	/**
	 * @param nombreComercial the nombreComercial to set
	 */
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}
	
	/**
	 * @return the nombreComercial
	 */
	public String getNombreComercial() {
		return nombreComercial;
	}

	public String getNombreUsuarioDetectorFraude() {
		return nombreUsuarioDetectorFraude;
	}

	public void setNombreUsuarioDetectorFraude(String nombreUsuarioDetectorFraude) {
		this.nombreUsuarioDetectorFraude = nombreUsuarioDetectorFraude;
	}

	public Date getFechaDeteccionFraude() {
		return fechaDeteccionFraude;
	}

	public void setFechaDeteccionFraude(Date fechaDeteccionFraude) {
		this.fechaDeteccionFraude = fechaDeteccionFraude;
	}

	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public String getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}


	
	
}
