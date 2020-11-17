package mx.gob.stps.portal.movil.app.empresa.model.rest;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpresaAdminDTO;

public class OfertaEmpresaAdminRestDTO extends BaseRestDTO{

	List<OfertaEmpresaAdminDTO> ofertas = new ArrayList();
	int totalRegistros;
	int totalPaginas;
	
	public List<OfertaEmpresaAdminDTO> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertaEmpresaAdminDTO> ofertas) {
		this.ofertas = ofertas;
	}

	public int getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
	
	
	
}
