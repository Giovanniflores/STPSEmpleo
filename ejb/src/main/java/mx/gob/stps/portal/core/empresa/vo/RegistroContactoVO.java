package mx.gob.stps.portal.core.empresa.vo;

import mx.gob.stps.portal.core.domicilio.vo.DomicilioVO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//import mx.gob.stps.portal.core.infra.vo.TelefonoVO;

//TODO ELIMINAR CLASE YA NO SE UTILIZA
public final class RegistroContactoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6558784347371780702L;

	private long idContacto;
	private long idEmpresa;
	private String nombreContacto;
	private String cargo;
	private Date fechaAlta;
	private int estatus;
	private Date fechaModificacion;
	private long idTerceraEmpresa;
	private String correoElectronico;	
	private List<TelefonoVO> telefonos;
	private DomicilioVO domicilio;	
	
	
	private RegistroContactoVO(){}
	
	private RegistroContactoVO(long idContacto, long idEmpresa, String nombreContacto, String cargo,
			Date fechaAlta, int estatus, Date fechaModificacion,long idTerceraEmpresa, 
			String correoElectronico, List<TelefonoVO> lstTelefonos){
		this.idContacto = idContacto;
		this.idEmpresa = idEmpresa;
		this.nombreContacto = nombreContacto;
		this.cargo = cargo;
		this.fechaAlta = fechaAlta;
		this.estatus = estatus;
		this.fechaModificacion = fechaModificacion;
		this.idTerceraEmpresa = idTerceraEmpresa;
		this.correoElectronico = correoElectronico;
		this.telefonos = lstTelefonos;
	}

	public long getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(long idContacto) {
		this.idContacto = idContacto;
	}

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public long getIdTerceraEmpresa() {
		return idTerceraEmpresa;
	}

	public void setIdTerceraEmpresa(long idTerceraEmpresa) {
		this.idTerceraEmpresa = idTerceraEmpresa;
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
	
}
