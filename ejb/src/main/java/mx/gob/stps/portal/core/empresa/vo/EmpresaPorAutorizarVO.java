package mx.gob.stps.portal.core.empresa.vo;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

public final class EmpresaPorAutorizarVO implements Serializable  {


	private static final long serialVersionUID = 1597076973915603316L;

	private long idEmpresa;

	private String idPortalEmpleo;

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
	
	private List<TelefonoVO> telefonos;
	
	private int aseguraDatos;
	
	private DomicilioVO domicilio;
	
	private String tipoPersona;
	private String tipoEmpresa;
	private String actividadEconomica;
	
	//COMENTAR EN PRODUCCION 
	private String nombreComercial;
	
	


	/**
	 * Constructor por defecto
	 * 
	 * @return EmpresaPorAutorizarVO
	 */		
	public EmpresaPorAutorizarVO(){}
	
	/**
	 * Constructor que acepta todos los valores de una empresa por autorizar
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
	public EmpresaPorAutorizarVO(long idEmpresa, String idPortalEmpleo, String rfc, 
			int idTipoPersona, String nombre, String apellido1, String apellido2,
			Date fechaNacimiento, String razonSocial, Date fechaActa, String descripcion,
			String contactoEmpresa, int idTipoEmpresa, int idActividadEconomica, 
			int numeroEmpleados, int idMedio, int confidencial, String paginaWeb,
			int aceptacionTerminos, Date fechaAlta, int estatus, Date fechaUltimaActualizacion,
			String correoElectronico, List<TelefonoVO> lstTelefonos, 
			int aseguraDatos, DomicilioVO domicilio){
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
		this.telefonos = lstTelefonos;
		this.aseguraDatos = aseguraDatos;
		this.domicilio = domicilio;
	}

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdPortalEmpleo() {
		return idPortalEmpleo;
	}

	public void setIdPortalEmpleo(String idPortalEmpleo) {
		this.idPortalEmpleo = idPortalEmpleo;
	}

	/*
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	*/
	
	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public long getIdTipoPersona() {
		return idTipoPersona;
	}

	public void setIdTipoPersona(long idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Date getFechaActa() {
		return fechaActa;
	}

	public void setFechaActa(Date fechaActa) {
		this.fechaActa = fechaActa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getContactoEmpresa() {
		return contactoEmpresa;
	}

	public void setContactoEmpresa(String contactoEmpresa) {
		this.contactoEmpresa = contactoEmpresa;
	}

	public long getIdTipoEmpresa() {
		return idTipoEmpresa;
	}

	public void setIdTipoEmpresa(long idTipoEmpresa) {
		this.idTipoEmpresa = idTipoEmpresa;
	}

	public long getIdActividadEconomica() {
		return idActividadEconomica;
	}

	public void setIdActividadEconomica(long idActividadEconomica) {
		this.idActividadEconomica = idActividadEconomica;
	}

	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}

	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}

	public long getIdMedio() {
		return idMedio;
	}

	public void setIdMedio(long idMedio) {
		this.idMedio = idMedio;
	}

	public int getConfidencial() {
		return confidencial;
	}

	public void setConfidencial(int confidencial) {
		this.confidencial = confidencial;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public int getAceptacionTerminos() {
		return aceptacionTerminos;
	}

	public void setAceptacionTerminos(int aceptacionTerminos) {
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

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public List<TelefonoVO> getTelefonos(){
		return telefonos;
	}
	
	public void setTelefonos(List<TelefonoVO> lstTelefonos){
		this.telefonos = lstTelefonos;
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
	 * @return the domicilio
	 */
	public DomicilioVO getDomicilio() {
		return domicilio;
	}

	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(DomicilioVO domicilio) {
		this.domicilio = domicilio;
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
	/* COMENTAR EN PRODUCCION */
	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
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

}
