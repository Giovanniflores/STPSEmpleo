package mx.gob.stps.portal.movil.app.empresa.model.rest;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.movil.web.empresa.vo.OfertasEmpresaVO;

public class OfertaEmpleoRestDTO extends  BaseRestDTO{
	List<OfertasEmpresaVO> ofertas = new ArrayList<OfertasEmpresaVO>();

	public List<OfertasEmpresaVO> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertasEmpresaVO> ofertas) {
		this.ofertas = ofertas;
	}
	
}
