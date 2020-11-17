package mx.gob.stps.portal.core.empresa.vo;

import java.io.Serializable;
import java.util.Date;

public class EmpresaPortalSisneVO implements Serializable {

	long idEmpresa;
	long idEmpresaSisne;
	Date fechaAlta;
	
	public long getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public long getIdEmpresaSisne() {
		return idEmpresaSisne;
	}
	
	public void setIdEmpresaSisne(long idEmpresaSisne) {
		this.idEmpresaSisne = idEmpresaSisne;
	}
	
	public Date getFechaAlta() {
		return fechaAlta;
	}
	
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}
