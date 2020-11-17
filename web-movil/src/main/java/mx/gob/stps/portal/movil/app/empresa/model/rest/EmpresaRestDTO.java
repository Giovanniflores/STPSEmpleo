package mx.gob.stps.portal.movil.app.empresa.model.rest;

import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;

public class EmpresaRestDTO extends BaseRestDTO{
	
	private EmpresaVO empresa;
	
	private String cookie;
	private String nombre;
	private String razonSocial;
	

	
	public String getCookie() {
		return cookie;
	}
	
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
