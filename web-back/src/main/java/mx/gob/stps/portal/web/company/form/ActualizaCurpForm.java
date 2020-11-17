package mx.gob.stps.portal.web.company.form;

import mx.gob.stps.portal.web.infra.vo.ResultVO;

import org.apache.struts.action.ActionForm;

public class ActualizaCurpForm extends ActionForm {
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String fechaNacimiento;	
	private String curp;	
	private int idEntidadNacimiento;
	private int idGenero;
	private String entidadNacimiento;		
	private boolean curpUnico;
	private short curpValidada;
	private Long IdMotivoNoValidacion;
	private Long idEmpresa;
	private ResultVO msg;
	
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
	
	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public Long getIdMotivoNoValidacion() {
		return IdMotivoNoValidacion;
	}

	public void setIdMotivoNoValidacion(Long idMotivoNoValidacion) {
		this.IdMotivoNoValidacion = idMotivoNoValidacion;
	}
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public ResultVO getMsg() {
		return msg;
	}

	public void setMsg(ResultVO msg) {
		this.msg = msg;
	}

	
	public String toString() {
		return "ActualizaCurp [nombre=" + nombre
				+ ", apellido1=" + apellido1
				+ ", apellido2=" + apellido2
				+ ", fechaNacimiento=" + fechaNacimiento
				+ ", curp=" + curp					
				+ ", curpUnico=" + curpUnico	
				+ ", curpValidada=" + curp	
				+ ", IdMotivoNoValidacion=" + IdMotivoNoValidacion					
				+ "]";
	}	

}
