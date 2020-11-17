package mx.gob.stps.portal.web.infra.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;


@SuppressWarnings("rawtypes")
public class TablaOfertasOcupate extends TableSortAbstract<OfertaPorCanalVO> implements Comparator{
	
	@SuppressWarnings("unchecked")
	public TablaOfertasOcupate(List lista){
		super(OfertaPorCanalVO.class);
		super.setLista(lista);
	}

	@SuppressWarnings("unchecked")
	public void ordenarTabla(Integer columna, String tipoOrden){
		try{
			numeroColumna = columna;
			tipoOrdenamiento = tipoOrden;
			Collections.sort(getLista(), this);
			if(columna == 2){
				numeroColumna = 6;
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
		OfertaPorCanalVO e1 = (OfertaPorCanalVO) o1;
		OfertaPorCanalVO e2 = (OfertaPorCanalVO) o2;
		if(tipoOrdenamiento.equals(TablaOfertasCandidato.ORDENAMIENTO_ASCENDENTE)){//Ordena la tabla de forma ascendente
			if(numeroColumna == 1)
				return e1.getTituloOferta().compareToIgnoreCase(e2.getTituloOferta());
			else if(numeroColumna == 2)
				return comparador.compare(e1.getMunicipio(), e2.getMunicipio());
			else if(numeroColumna == 3)
				return e1.getEmpresa().compareToIgnoreCase(e2.getEmpresa());
        	else if(numeroColumna == 4){
	        	double id1 = e1.getSalario();
	        	double id2 = e2.getSalario();
	        	   if (id1 > id2) {
	                   return 1;
	               } else if (id1 < id2) {
	                   return -1;
	               } else {
	                   return 0;
	               }
        	}else if(numeroColumna == 5){
        		return e1.getFechaInicio().compareTo(e2.getFechaInicio());
        	}else if(numeroColumna == 6){
        		return comparador.compare(e1.getEntidad(), e2.getEntidad());
        	}
    	}else if(tipoOrdenamiento.equals(TablaOfertasCandidato.ORDENAMIENTO_DESCENDENTE)){//Ordena la tabla de forma descendente
    		if(numeroColumna == 1)
				return e2.getTituloOferta().compareToIgnoreCase(e1.getTituloOferta());
			else if(numeroColumna == 2)
				return comparador.compare(e2.getMunicipio(), e1.getMunicipio());
			else if(numeroColumna == 3)
				return e2.getEmpresa().compareToIgnoreCase(e1.getEmpresa());
        	else if(numeroColumna == 4){
	        	double id1 = e1.getSalario();
	        	double id2 = e2.getSalario();
	        	   if (id2 > id1) {
	                   return 1;
	               } else if (id2 < id1) {
	                   return -1;
	               } else {
	                   return 0;
	               }
        	}else if(numeroColumna == 5){
        		return e2.getFechaInicio().compareTo(e1.getFechaInicio());
        	}else if(numeroColumna == 6){
        		return comparador.compare(e2.getEntidad(), e1.getEntidad());
        	}
        	
    	}
		return 0;
	}
	
	@Override
	protected List getListaOrdenada() {
		return getLista();
	}


}
