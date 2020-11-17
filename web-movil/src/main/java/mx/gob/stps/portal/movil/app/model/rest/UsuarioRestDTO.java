package mx.gob.stps.portal.movil.app.model.rest;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.movil.web.seguridad.vo.UsuarioFirmadoVO;


public class UsuarioRestDTO  extends BaseRestDTO{

	UsuarioVO usuario;
	UsuarioFirmadoVO usuarioVO;
	private EmpresaVO empresa;
	
	private String cookie;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String razonSocial;
	
	
	
	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public UsuarioFirmadoVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioFirmadoVO usuarioVO) {
		this.usuarioVO = usuarioVO;
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

	public EmpresaVO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	
	
	
	
	
}
