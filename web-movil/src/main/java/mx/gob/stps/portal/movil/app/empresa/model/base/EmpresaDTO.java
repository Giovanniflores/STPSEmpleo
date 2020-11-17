package mx.gob.stps.portal.movil.app.empresa.model.base;

import mx.gob.stps.portal.movil.web.empresa.form.EmpresaEspacioForm;

public class EmpresaDTO {
	
	private EmpresaEspacioForm empresa;
	
	public EmpresaDTO(EmpresaEspacioForm empresa){
		this.empresa   = empresa;
		
	}

	public EmpresaEspacioForm getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaEspacioForm empresa) {
		this.empresa = empresa;
	}
	
	

}
