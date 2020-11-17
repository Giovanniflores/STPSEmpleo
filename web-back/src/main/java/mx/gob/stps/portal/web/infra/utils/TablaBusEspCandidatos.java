package mx.gob.stps.portal.web.infra.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import mx.gob.stps.portal.core.candidate.vo.ResultadoBusquedaCandidatosVO;

@SuppressWarnings("rawtypes")
public class TablaBusEspCandidatos  extends TableSortAbstract<ResultadoBusquedaCandidatosVO> implements Comparator{

	@SuppressWarnings("unchecked")
	public TablaBusEspCandidatos(List lista) {
		super(ResultadoBusquedaCandidatosVO.class);
		super.setLista(lista);
	}

	@SuppressWarnings("unchecked")
	public void ordenarTabla(Integer columna, String tipoOrden){
		try{
			numeroColumna = columna;
			tipoOrdenamiento = tipoOrden;
			Collections.sort(getLista(), this);
			if(columna == 6){
				numeroColumna = 9;
				Collections.sort(getLista(), this);
			}
		}catch(NullPointerException ex){/* Si la columna no contine datos para ordenar la ordena por el id de la oferta*/
			numeroColumna = 1;
			tipoOrdenamiento = tipoOrden;
			Collections.sort(getLista(), this);
		}
	}
	
	
	@Override
	public int compare(Object o1, Object o2) {
		ResultadoBusquedaCandidatosVO e1 = (ResultadoBusquedaCandidatosVO) o1;
		ResultadoBusquedaCandidatosVO e2 = (ResultadoBusquedaCandidatosVO) o2;
		
		if(tipoOrdenamiento.equals(TablaOfertasCandidato.ORDENAMIENTO_ASCENDENTE)){
			if(numeroColumna == 1){
				return e1.getNombreCompleto().compareToIgnoreCase(e2.getNombreCompleto());
			}else if(numeroColumna == 2){
				return e1.getEstudios().compareToIgnoreCase(e2.getEstudios());
			}else if(numeroColumna == 3){
				return e1.getEdad().compareToIgnoreCase(e2.getEdad());
			}else if(numeroColumna == 4){
				return e1.getEntidadFederativa().compareToIgnoreCase(e2.getEntidadFederativa());
			}
		}else if(tipoOrdenamiento.equals(TablaOfertasCandidato.ORDENAMIENTO_DESCENDENTE)){
			if(numeroColumna == 1){
				return e2.getNombreCompleto().compareToIgnoreCase(e1.getNombreCompleto());
			}else if(numeroColumna == 2){
				return e2.getEstudios().compareToIgnoreCase(e1.getEstudios());
			}else if(numeroColumna == 3){
				return e2.getEdad().compareToIgnoreCase(e1.getEdad());
			}else if(numeroColumna == 4){
				return e2.getEntidadFederativa().compareToIgnoreCase(e1.getEntidadFederativa());
			}
		}
		return 0;
	}

	@Override
	protected List getListaOrdenada() {
		return getLista();
	}

}
