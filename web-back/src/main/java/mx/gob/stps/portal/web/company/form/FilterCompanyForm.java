package mx.gob.stps.portal.web.company.form;

import java.util.List;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionForm;

public class FilterCompanyForm extends ActionForm{
	private static final long serialVersionUID = -5744397068535928294L;
	private long idUsuario;
	private long idTipoUsuario;
	private long idPerfil;
	
	//datos de la busqueda
	private int idTipoPersona;
	private String nombre;
	private String apellido1;
	private String razonSocial;
	private String fechaActa;
	private String fechaNacimiento;
	private String fechaAlta;
	private List<EmpresaVO> lstEmpresas;
	private String correoElectronico;
	private String idPortalEmpleo;
	
	private Integer idMotivoDesactivacion;
	private String motivoDesactivacion;
	private Long idEmpresaADesactivar;
	private Long idEmpresaAReactivar;
	private ResultVO msg;
	
	private String apellido2;	
	private Long idEmpresa;
	private String contacto;
	private String telefono;	
	private String domicilio;
	private long idEntidad;
	private long idMunicipio;	
	private String usuario;
	
	public void reset(){
		idTipoPersona = 0;
		nombre = null;
		apellido1 = null;
		razonSocial =  null;
		fechaActa = null;
		lstEmpresas = null;
		correoElectronico = null;
		idPortalEmpleo = null;
		idMotivoDesactivacion = null;
		motivoDesactivacion = null;
		idEmpresaADesactivar = null;
		idEmpresaAReactivar = null;
		apellido2 = null;
		idEmpresa = null;
		contacto = null;
		telefono = null;
		domicilio = null;
        idEntidad = 0;
		idMunicipio = 0;
		usuario = null;		
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
	 * @return the idPerfil
	 */
	public long getIdPerfil() {
		return idPerfil;
	}

	/**
	 * @param idPerfil the idPerfil to set
	 */
	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}

	/**
	 * @return the idTipoPersona
	 */
	public int getIdTipoPersona() {
		return idTipoPersona;
	}

	/**
	 * @param idTipoPersona the idTipoPersona to set
	 */
	public void setIdTipoPersona(int idTipoPersona) {
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
	 * @return the fechaAlta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * @return the lstEmpresas
	 */
	public List<EmpresaVO> getLstEmpresas() {
		return lstEmpresas;
	}

	/**
	 * @param lstEmpresas the lstEmpresas to set
	 */
	public void setLstEmpresas(List<EmpresaVO> lstEmpresas) {
		this.lstEmpresas = lstEmpresas;
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

	public String getIdPortalEmpleo() {
		return idPortalEmpleo;
	}

	public void setIdPortalEmpleo(String idPortalEmpleo) {
		this.idPortalEmpleo = idPortalEmpleo;
	}
	
	public Integer getIdMotivoDesactivacion() {
		return idMotivoDesactivacion;
	}

	public void setIdMotivoDesactivacion(Integer idMotivoDesactivacion) {
		this.idMotivoDesactivacion = idMotivoDesactivacion;
	}

	public String getMotivoDesactivacion() {
		return motivoDesactivacion;
	}

	public void setMotivoDesactivacion(String motivoDesactivacion) {
		this.motivoDesactivacion = motivoDesactivacion;
	}

	public Long getIdEmpresaADesactivar() {
		return idEmpresaADesactivar;
	}

	public void setIdEmpresaADesactivar(Long idEmpresaADesactivar) {
		this.idEmpresaADesactivar = idEmpresaADesactivar;
	}	

	public Long getIdEmpresaAReactivar() {
		return idEmpresaAReactivar;
	}

	public void setIdEmpresaAReactivar(Long idEmpresaAReactivar) {
		this.idEmpresaAReactivar = idEmpresaAReactivar;
	}
	
	public ResultVO getMsg() {
		return msg;
	}

	public void setMsg(ResultVO msg) {
		this.msg = msg;
	}

	
	
	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public long getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "FilterCompanyForm [idUsuario=" + idUsuario + ", idTipoUsuario="
				+ idTipoUsuario + ", idPerfil=" + idPerfil + ", idTipoPersona="
				+ idTipoPersona + ", nombre=" + nombre + ", apellido1="
				+ apellido1 + ", apellido2=" + apellido2 +", razonSocial=" + razonSocial + ", fechaActa="
				+ fechaActa + ", fechaNacimiento=" + fechaNacimiento
				+ ", fechaAlta=" + fechaAlta + ", lstEmpresas=" + lstEmpresas
				+ ", correoElectronico=" + correoElectronico
				+ ", idPortalEmpleo=" + idPortalEmpleo 
				+ ", idEmpresa=" + idEmpresa
				+ ", contacto=" + contacto
				+ ", telefono=" + telefono
				+ ", domicilio=" + domicilio	
				+ ", idEntidad=" + idEntidad
				+ ", idMunicipio=" + idMunicipio	
				+ ", usuario=" + usuario
				+ "]";
	}	
	
}
