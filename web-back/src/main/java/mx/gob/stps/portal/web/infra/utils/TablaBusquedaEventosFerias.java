package mx.gob.stps.portal.web.infra.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;

import mx.gob.stps.portal.persistencia.vo.EventoVO;

public class TablaBusquedaEventosFerias extends TableSortAbstract<EventoVO> implements Comparator {

	public TablaBusquedaEventosFerias(List lista) {
		super(EventoVO.class);
		super.setLista(lista);
	}

	@SuppressWarnings("unchecked")
	public void ordenarTabla(Integer columna, String tipoOrden){
		try {
			numeroColumna = columna;
			tipoOrdenamiento = tipoOrden;
			Collections.sort(getLista(), this);
			
		} catch(NullPointerException ex) {/* Si la columna no contine datos para ordenar la ordena por el nombre del evento*/
			numeroColumna = 1;
			tipoOrdenamiento = tipoOrden;
			Collections.sort(getLista(), this);
		}
	}
	
	@Override
	protected List getListaOrdenada() {
		return getLista();
	}

	@Override
	public int compare(Object o1, Object o2) {
		EventoVO evento1 = (EventoVO) o1;
		EventoVO evento2 = (EventoVO) o2;
		int indicator = 0;
		if(tipoOrdenamiento.equals(TablaBusquedaEventosFerias.ORDENAMIENTO_ASCENDENTE)){
			switch(numeroColumna){
				case 1:
					indicator = evento1.getEvento().compareToIgnoreCase(evento2.getEvento());
					break;
				case 2:
					indicator = evento1.getEntidad().compareToIgnoreCase(evento2.getEntidad());
					if(indicator == 0)
						indicator = evento1.getMunicipio().compareToIgnoreCase(evento2.getMunicipio());
					break;
				case 3:
					indicator = evento1.getSede().compareToIgnoreCase(evento2.getSede());
					break;
				case 4:
					indicator = evento1.getAmbiente().compareToIgnoreCase(evento2.getAmbiente());
					break;
				case 5:
					indicator = evento1.getFechaInicio().compareTo(evento2.getFechaInicio());
					break;
				default:
					indicator = 0;
					break;
			}
		}else if(tipoOrdenamiento.equals(TablaBusquedaEventosFerias.ORDENAMIENTO_DESCENDENTE)){
			switch(numeroColumna){
			case 1:
				indicator = evento2.getEvento().compareToIgnoreCase(evento1.getEvento());
				break;
			case 2:
				indicator = evento2.getEntidad().compareToIgnoreCase(evento1.getEntidad());
				if(indicator == 0)
					indicator = evento2.getMunicipio().compareToIgnoreCase(evento1.getMunicipio());
				break;
			case 3:
				indicator = evento2.getSede().compareToIgnoreCase(evento1.getSede());
				break;
			case 4:
				indicator = evento2.getAmbiente().compareToIgnoreCase(evento1.getAmbiente());
				break;
			case 5:
				indicator = evento2.getFechaInicio().compareTo(evento1.getFechaInicio());
				break;
			default:
				indicator = 0;
				break;
			}
		}
		
		return indicator;
	}

}
