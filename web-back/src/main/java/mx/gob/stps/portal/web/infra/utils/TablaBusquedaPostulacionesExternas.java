package mx.gob.stps.portal.web.infra.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mx.gob.stps.portal.persistencia.entity.PostulacionExterna;
import mx.gob.stps.portal.persistencia.vo.EventoVO;

public class TablaBusquedaPostulacionesExternas extends TableSortAbstract<PostulacionExterna> implements Comparator {

	public TablaBusquedaPostulacionesExternas(List lista) {
		super(PostulacionExterna.class);
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
		PostulacionExterna postulacion1 = (PostulacionExterna) o1;
		PostulacionExterna postulacion2 = (PostulacionExterna) o2;
		int indicator = 0;
		if(tipoOrdenamiento.equals(TablaBusquedaPostulacionesExternas.ORDENAMIENTO_ASCENDENTE)){
			switch(numeroColumna){
				case 1:
					indicator = postulacion1.getTituloOferta().compareToIgnoreCase(postulacion2.getTituloOferta());
					break;
				case 2:
					indicator = postulacion1.getNombreEmpresa().compareToIgnoreCase(postulacion2.getNombreEmpresa());
					break;
				default:
					indicator = 0;
					break;
			}
		}else if(tipoOrdenamiento.equals(TablaBusquedaPostulacionesExternas.ORDENAMIENTO_DESCENDENTE)){
			switch(numeroColumna){
			case 1:
				indicator = postulacion2.getTituloOferta().compareToIgnoreCase(postulacion1.getTituloOferta());
				break;
			case 2:
				indicator = postulacion2.getNombreEmpresa().compareToIgnoreCase(postulacion1.getNombreEmpresa());
				break;
			
			default:
				indicator = 0;
				break;
			}
		}
		
		return indicator;
	}

}
