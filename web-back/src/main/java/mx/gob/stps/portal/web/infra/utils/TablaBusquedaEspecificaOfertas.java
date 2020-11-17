package mx.gob.stps.portal.web.infra.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mx.gob.stps.portal.core.oferta.vo.ResultadoBusquedaOfertasVO;

@SuppressWarnings("rawtypes")
public class TablaBusquedaEspecificaOfertas extends TableSortAbstract<ResultadoBusquedaOfertasVO> implements Comparator {

	@SuppressWarnings("unchecked")
	public TablaBusquedaEspecificaOfertas(List lista) {
		super(ResultadoBusquedaOfertasVO.class);
		super.setLista(lista);
	}

	@SuppressWarnings("unchecked")
	public void ordenarTabla(Integer columna, String tipoOrden){
		try {
			numeroColumna = columna;
			tipoOrdenamiento = tipoOrden;
			Collections.sort(getLista(), this);
			if (columna == 6) {
				numeroColumna = 9;
				Collections.sort(getLista(), this);
			}
		} catch(NullPointerException ex) {/* Si la columna no contine datos para ordenar la ordena por el id de la oferta*/
			numeroColumna = 1;
			tipoOrdenamiento = tipoOrden;
			Collections.sort(getLista(), this);
		}
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		ResultadoBusquedaOfertasVO e1 = (ResultadoBusquedaOfertasVO) o1;
		ResultadoBusquedaOfertasVO e2 = (ResultadoBusquedaOfertasVO) o2;
		
		if (tipoOrdenamiento.equals(TablaBusquedaEspecificaOfertas.ORDENAMIENTO_ASCENDENTE)) {
			if (numeroColumna == 1) { // JobOfferName
				return e1.getJobOfferName().compareToIgnoreCase(e2.getJobOfferName());
			} else if(numeroColumna == 2) { // Location
				return e1.getLocationEntity().compareToIgnoreCase(e2.getLocationEntity());
			} else if(numeroColumna == 3) { // EnterpriseName (RFC)
				return e1.getEnterpriseName().compareToIgnoreCase(e2.getEnterpriseName());
			} else if(numeroColumna == 4) { // NetSalary
				return e1.getNetSalary().compareTo(e2.getNetSalary());
			} else if(numeroColumna == 5) { // PublicationDate
				return e1.getPublicationDate().compareTo(e2.getPublicationDate());
			}
		} else if (tipoOrdenamiento.equals(TablaBusquedaEspecificaOfertas.ORDENAMIENTO_DESCENDENTE)) {			
			if (numeroColumna == 1) { // JobOfferName
				return e2.getJobOfferName().compareToIgnoreCase(e1.getJobOfferName());
			} else if(numeroColumna == 2) { // Location
				return e2.getLocationEntity().compareToIgnoreCase(e1.getLocationEntity());
			} else if(numeroColumna == 3) { // EnterpriseName (RFC)
				return e2.getEnterpriseName().compareToIgnoreCase(e1.getEnterpriseName());
			} else if(numeroColumna == 4) { // NetSalary
				return e2.getNetSalary().compareTo(e1.getNetSalary());
			} else if(numeroColumna == 5) { // PublicationDate
				return e2.getPublicationDate().compareTo(e1.getPublicationDate());
			}
		}
		return 0;
	}

	@Override
	protected List getListaOrdenada() {
		return getLista();
	}
}
