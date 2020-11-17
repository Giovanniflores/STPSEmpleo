package mx.gob.stps.portal.web.offer.form;

import java.util.ArrayList;
import java.util.List;

import mx.gob.stps.portal.core.oferta.vo.BusquedaOfertasVO;
import mx.gob.stps.portal.web.offer.action.CompararBusquedasAction;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

public class CompararBusquedasForm extends ActionForm{

	private static final long serialVersionUID = 1192113283867065739L;
	private static Logger logger = Logger.getLogger(CompararBusquedasAction.class);
	public final Integer ENTIDAD_DISTRITO_FEDERAL = 9;
	
	private String msg = "";
	
	private BusquedaOfertasVO criteriosBusquedaEspecifica = new BusquedaOfertasVO();	
	private List<Long> indicesOfertasBusquedaEspecifica = new ArrayList<Long>();
	private List<Long> indicesOfertasBusquedaOcupate = new ArrayList<Long>();
	private List<Long> indicesSoloBusquedaEspecifica = new ArrayList<Long>();
	private List<Long> indicesSoloBusquedaOcupate = new ArrayList<Long>();
	
	
	public BusquedaOfertasVO getCriteriosBusquedaEspecifica() {
		return criteriosBusquedaEspecifica;
	}

	public void setCriteriosBusquedaEspecifica(BusquedaOfertasVO criteriosBusquedaEspecifica) {
		this.criteriosBusquedaEspecifica = criteriosBusquedaEspecifica;
	}	
	
    public List<Long> getIndicesOfertasBusquedaEspecifica() {
		return indicesOfertasBusquedaEspecifica;
	}

	public void setIndicesOfertasBusquedaEspecifica(List<Long> indicesOfertasBusquedaEspecifica) {
		this.indicesOfertasBusquedaEspecifica = indicesOfertasBusquedaEspecifica;
	}

	public List<Long> getIndicesOfertasBusquedaOcupate() {
		return indicesOfertasBusquedaOcupate;
	}

	public void setIndicesOfertasBusquedaOcupate(List<Long> indicesOfertasBusquedaOcupate) {
		this.indicesOfertasBusquedaOcupate = indicesOfertasBusquedaOcupate;
	}
	
	public List<Long> getIndicesSoloBusquedaEspecifica() {
		return indicesSoloBusquedaEspecifica;
	}

	public void setIndicesSoloBusquedaEspecifica(List<Long> indicesSoloBusquedaEspecifica) {
		this.indicesSoloBusquedaEspecifica = indicesSoloBusquedaEspecifica;
	}

	public List<Long> getIndicesSoloBusquedaOcupate() {
		return indicesSoloBusquedaOcupate;
	}

	public void setIndicesSoloBusquedaOcupate(List<Long> indicesSoloBusquedaOcupate) {
		this.indicesSoloBusquedaOcupate = indicesSoloBusquedaOcupate;
	}

	

}
