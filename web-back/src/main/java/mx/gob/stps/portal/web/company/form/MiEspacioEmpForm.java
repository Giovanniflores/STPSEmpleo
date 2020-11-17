package mx.gob.stps.portal.web.company.form;

import java.util.List;

import mx.gob.stps.portal.core.oferta.vo.MiOfertaRecienteVO;
import mx.gob.stps.portal.core.oferta.vo.PostulacionRecienteVO;

import org.apache.struts.action.ActionForm;

public class MiEspacioEmpForm extends ActionForm {
	private static final long serialVersionUID = -3053199453019047262L;

	private long idEmpresa;
	private String nombreEmpresa;
	private String contactoEmpresa;
	private String idPortalEmpleo;
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String fechaNacimiento;	
	private String curp;	
	private int idEntidadNacimiento;
	private String entidadNacimiento;		
	private boolean curpUnico;
	private short curpValidada;
	private Long IdMotivoNoValidacion;	
	
	/** Ofertas Recientes Activas de la empresa */
	private List<MiOfertaRecienteVO> ofertasRecientes;
	
	/** Postulaciones Recientes de Ofertas Activas de la empresa */
	private List<PostulacionRecienteVO> postulacionesRecientes;
	
	
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getContactoEmpresa() {
		return contactoEmpresa;
	}
	public void setContactoEmpresa(String contactoEmpresa) {
		this.contactoEmpresa = contactoEmpresa;
	}
	public String getIdPortalEmpleo() {
		return idPortalEmpleo;
	}
	public void setIdPortalEmpleo(String idPortalEmpleo) {
		this.idPortalEmpleo = idPortalEmpleo;
	}
	/**
	 * @return the ofertasRecientes
	 */
	public List<MiOfertaRecienteVO> getOfertasRecientes() {
		return ofertasRecientes;
	}
	/**
	 * @param ofertasRecientes the ofertasRecientes to set
	 */
	public void setOfertasRecientes(List<MiOfertaRecienteVO> ofertasRecientes) {
		this.ofertasRecientes = ofertasRecientes;
	}
	/**
	 * @return the postulacionesRecientes
	 */
	public List<PostulacionRecienteVO> getPostulacionesRecientes() {
		return postulacionesRecientes;
	}
	/**
	 * @param postulacionesRecientes the postulacionesRecientes to set
	 */
	public void setPostulacionesRecientes(
			List<PostulacionRecienteVO> postulacionesRecientes) {
		this.postulacionesRecientes = postulacionesRecientes;
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
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
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
	public boolean isCurpUnico() {
		return curpUnico;
	}
	public void setCurpUnico(boolean curpUnico) {
		this.curpUnico = curpUnico;
	}
	public short getCurpValidada() {
		return curpValidada;
	}
	public void setCurpValidada(short curpValidada) {
		this.curpValidada = curpValidada;
	}
	public Long getIdMotivoNoValidacion() {
		return IdMotivoNoValidacion;
	}
	public void setIdMotivoNoValidacion(Long idMotivoNoValidacion) {
		IdMotivoNoValidacion = idMotivoNoValidacion;
	}
	
	public String toString() {
		return "MiEspacioEmpForm [nombre=" + nombre
				+ ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2
				+ ", fechaNacimiento=" + fechaNacimiento
				+ ", curp=" + curp					
				+ ", curpUnico=" + curpUnico	
				+ ", curpValidada=" + curp	
				+ ", IdMotivoNoValidacion=" + IdMotivoNoValidacion		
				+ ", idEmpresa=" + idEmpresa		
				+ ", nombreEmpresa=" + IdMotivoNoValidacion		
				+ ", contactoEmpresa=" + contactoEmpresa		
				+ ", idPortalEmpleo=" + idPortalEmpleo						
				+ "]";
	}		
	
}
