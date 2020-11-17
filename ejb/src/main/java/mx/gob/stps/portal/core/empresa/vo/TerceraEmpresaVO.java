package mx.gob.stps.portal.core.empresa.vo;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

//TODO ELIMINAR CLASE YA NO SE UTILIZA
public class TerceraEmpresaVO implements Serializable {
	private static final long serialVersionUID = -4587038360789280583L;
	private long idTerceraEmpresa;
	private long idEmpresa;
	private String rfc;
	private long idTipoPersona;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String razonSocial;
	private String descripcion;
	private String contactoEmpresa;
	private long idTipoEmpresa;
	private long idActividadEconomica;
	private long numeroEmpleados;
	private Date fechaAlta;
	private int estatus;
	private Date fechaUltimaActualizacion;
	private String correoElectronico;	
	private List<TelefonoVO> telefonos;	
	private DomicilioVO domicilio;	
	private String nombreComercial;
	
	/**
	 * Constructor por defecto
	 * 
	 * @return TerceraEmpresaVO
	 */		
	private TerceraEmpresaVO(){}	
	
	/**
	 * Constructor que acepta todos los valores de una empresa
	 * 
	 * @param idTerceraEmpresa
	 */ 		
	private TerceraEmpresaVO(long idTerceraEmpresa, long idEmpresa, String rfc,
			long idTipoPersona, String nombre, String apellido1, String apellido2,
			String razonSocial, String descripcion, String contactoEmpresa, long idTipoEmpresa,
			long idActividadEconomica, int numeroEmpleados, Date fechaAlta, int estatus,
			Date fechaUltimaActualizacion, String correoElectronico, 
			List<TelefonoVO> lstTelefonos, DomicilioVO domicilio){
		this.idTerceraEmpresa =idTerceraEmpresa;
		this.idEmpresa = idEmpresa;
		this.rfc = rfc;
		this.idTipoPersona = idTipoPersona;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.razonSocial = razonSocial;
		this.descripcion = descripcion;
		this.contactoEmpresa = contactoEmpresa;
		this.idTipoEmpresa = idTipoEmpresa;
		this.idActividadEconomica = idActividadEconomica;
		this.numeroEmpleados = numeroEmpleados;
		this.fechaAlta = fechaAlta;
		this.estatus = estatus;
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
		this.correoElectronico = correoElectronico;
		this.telefonos = lstTelefonos;
		this.domicilio = domicilio;
	}

	/**
	 * @return the idTerceraEmpresa
	 */
	public long getIdTerceraEmpresa() {
		return idTerceraEmpresa;
	}

	/**
	 * @param idTerceraEmpresa the idTerceraEmpresa to set
	 */
	public void setIdTerceraEmpresa(long idTerceraEmpresa) {
		this.idTerceraEmpresa = idTerceraEmpresa;
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
	public long getNumeroEmpleados() {
		return numeroEmpleados;
	}

	/**
	 * @param numeroEmpleados the numeroEmpleados to set
	 */
	public void setNumeroEmpleados(long numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
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
	 * @param nombreComercial the nombreComercial to set
	 */
	/* COMENTAR EN PROD */
	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	/**
	 * @return the nombreComercial
	 */
	/* COMENTAR EN PROD */
	public String getNombreComercial() {
		return nombreComercial;
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
		builder.append("TerceraEmpresaVO [idTerceraEmpresa=");
		builder.append(idTerceraEmpresa);
		builder.append(", idEmpresa=");
		builder.append(idEmpresa);
		builder.append(", rfc=");
		builder.append(rfc);
		builder.append(", idTipoPersona=");
		builder.append(idTipoPersona);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido1=");
		builder.append(apellido1);
		builder.append(", apellido2=");
		builder.append(apellido2);
		builder.append(", razonSocial=");
		builder.append(razonSocial);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", contactoEmpresa=");
		builder.append(contactoEmpresa);
		builder.append(", idTipoEmpresa=");
		builder.append(idTipoEmpresa);
		builder.append(", idActividadEconomica=");
		builder.append(idActividadEconomica);
		builder.append(", numeroEmpleados=");
		builder.append(numeroEmpleados);
		builder.append(", fechaAlta=");
		builder.append(fechaAlta);
		builder.append(", estatus=");
		builder.append(estatus);
		builder.append(", fechaUltimaActualizacion=");
		builder.append(fechaUltimaActualizacion);
		builder.append(", telefonos=");
		builder.append(telefonos);
		builder.append(", domicilio=");
		builder.append(domicilio);
		builder.append(", nombreComercial=");
		builder.append(nombreComercial);
		builder.append("]");
		return builder.toString();
	}
	
}
