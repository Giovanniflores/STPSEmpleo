package mx.gob.stps.portal.core.empresa.vo;

import java.io.Serializable;
import java.util.Date;


public class HistEmpresaPorAutorizarVO implements Serializable {
	private static final long serialVersionUID = 9208800805261757334L;

	private long idEmpresa;

	private String idPortalEmpleo;
	
	//private long idUsuario;

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
	
	/**
	 * Constructor por defecto
	 * 
	 * @return HistEmpresaPorAutorizarVO
	 */		
	public HistEmpresaPorAutorizarVO(){}	
	
	/**
	 * Constructor que acepta todos los valores de una empresa por autorizar historico
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
	public HistEmpresaPorAutorizarVO(long idEmpresa, String idPortalEmpleo, 
			long idUsuario, String rfc, long idTipoPersona, String nombre, 
			String apellido1, String apellido2, Date fechaNacimiento, String razonSocial,
			Date fechaActa, String descripcion, String contactoEmpresa, long idTipoEmpresa,
			long idActividadEconomica, int numeroEmpleados, long idMedio, int confidencial,
			String paginaWeb, int aceptacionTerminos, Date fechaAlta,int estatus, 
			Date fechaUltimaActualizacion, String correoElectronico, int aseguraDatos){
		this.aceptacionTerminos = aceptacionTerminos;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.aseguraDatos =aseguraDatos;
		this.confidencial = confidencial;
		this.contactoEmpresa = contactoEmpresa;
		this.correoElectronico = correoElectronico;
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.fechaActa = fechaActa;
		this.fechaAlta = fechaAlta;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.idActividadEconomica = idActividadEconomica;
		this.idEmpresa = idEmpresa;
		this.idMedio = idMedio;
		this.idPortalEmpleo = idPortalEmpleo;
		this.idTipoEmpresa = idTipoEmpresa;
		this.idTipoPersona = idTipoPersona;
		//this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.numeroEmpleados = numeroEmpleados;
		this.paginaWeb = paginaWeb;
		this.razonSocial = razonSocial;
		this.rfc = rfc;
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

	/*public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}*/

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
	
	
	
}
