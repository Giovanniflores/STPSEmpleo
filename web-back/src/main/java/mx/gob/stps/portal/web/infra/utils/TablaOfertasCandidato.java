package mx.gob.stps.portal.web.infra.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;

@SuppressWarnings("rawtypes")
public class TablaOfertasCandidato extends TableSortAbstract<OfertaEmpleoVO> implements Comparator{

	@SuppressWarnings("unchecked")
	public TablaOfertasCandidato(List lista) {
		super(OfertaEmpleoVO.class);
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
		OfertaEmpleoVO e1 = (OfertaEmpleoVO) o1;  
    	OfertaEmpleoVO e2 = (OfertaEmpleoVO) o2;
    	if(tipoOrdenamiento.equals(TablaOfertasCandidato.ORDENAMIENTO_ASCENDENTE)){//Ordena la tabla de forma ascendente
    		if(numeroColumna == 1){
        		long id1 = e1.getIdOfertaEmpleo();
                long id2 = e2.getIdOfertaEmpleo();

                if (id1 > id2) {
                    return 1;
                } else if (id1 < id2) {
                    return -1;
                } else {
                    return 0;
                }

        	}else if(numeroColumna == 2){
        		return e1.getTituloOferta().compareToIgnoreCase(e2.getTituloOferta()); 
        	}else if(numeroColumna == 3){
        		 return e1.getOcupacionDescripcion().compareToIgnoreCase(e2.getOcupacionDescripcion());
        	}else if(numeroColumna == 4){
        		return e1.getNivelEstudiosDescripcion().compareToIgnoreCase(e2.getNivelEstudiosDescripcion());
        	}else if(numeroColumna == 5){
        		return e1.getCarreraDescripcion().compareToIgnoreCase(e2.getCarreraDescripcion());
        	}else if(numeroColumna == 6){
        		//return e1.getMunicipioDescripcion().compareToIgnoreCase(e2.getMunicipioDescripcion());
        		return comparador.compare(e1.getMunicipioDescripcion(),e2.getMunicipioDescripcion());
        	}else if(numeroColumna == 7){
        		return e1.getFechaAlta().compareTo(e2.getFechaAlta());
        	}else if(numeroColumna == 8){
        		int id1 = e1.getEstatus();
                int id2 = e2.getEstatus();

                if (id1 > id2) {
                    return 1;
                } else if (id1 < id2) {
                    return -1;
                } else {
                    return 0;
                }
        	}else if(numeroColumna == 9){
        		//return e1.getEntidadDescripcion().compareToIgnoreCase(e2.getEntidadDescripcion());
        		return comparador.compare(e1.getEntidadDescripcion(), e2.getEntidadDescripcion());
        	} else if(numeroColumna == 10){
        		return e1.getDescripcionesDiscapacidades().compareToIgnoreCase(e2.getDescripcionesDiscapacidades());
        	} else if(numeroColumna == 11){
        		return e1.getTipoOferta().compareTo(e2.getTipoOferta());
        	}
    		
    	}else if(tipoOrdenamiento.equals(TablaOfertasCandidato.ORDENAMIENTO_DESCENDENTE)){//Ordena la tabla de forma descendente
    		if(numeroColumna == 1){
        		long id1 = e1.getIdOfertaEmpleo();
                long id2 = e2.getIdOfertaEmpleo();

                if (id2 > id1) {
                    return 1;
                } else if (id2 < id1) {
                    return -1;
                } else {
                    return 0;
                }

        	}else if(numeroColumna == 2){
        		return e2.getTituloOferta().compareToIgnoreCase(e1.getTituloOferta()); 
        	}else if(numeroColumna == 3){
        		 return e2.getOcupacionDescripcion().compareToIgnoreCase(e1.getOcupacionDescripcion());
        	}else if(numeroColumna == 4){
        		return e2.getNivelEstudiosDescripcion().compareToIgnoreCase(e1.getNivelEstudiosDescripcion());
        	}else if(numeroColumna == 5){
        		return e2.getCarreraDescripcion().compareToIgnoreCase(e1.getCarreraDescripcion());
        	}else if(numeroColumna == 6){
        		//return  e2.getMunicipioDescripcion().compareToIgnoreCase(e1.getMunicipioDescripcion());
        		return comparador.compare(e2.getMunicipioDescripcion(), e1.getMunicipioDescripcion());
        	}else if(numeroColumna == 7){
        		return e2.getFechaAlta().compareTo(e1.getFechaAlta());
        	}else if(numeroColumna == 8){
        		int id1 = e1.getEstatus();
                int id2 = e2.getEstatus();

                if (id2 > id1) {
                    return 1;
                } else if (id2 < id1) {
                    return -1;
                } else {
                    return 0;
                }
        	}else if(numeroColumna == 9){
        		//return e2.getEntidadDescripcion().compareToIgnoreCase(e1.getEntidadDescripcion());
        		return comparador.compare(e2.getEntidadDescripcion(), e1.getEntidadDescripcion());
        	} else if(numeroColumna == 10){
        		return e2.getDescripcionesDiscapacidades().compareToIgnoreCase(e1.getDescripcionesDiscapacidades());
        	} else if(numeroColumna == 11){
        		return e2.getTipoOferta().compareTo(e1.getTipoOferta());
        	}
    	}
    	
    	
    	return 0;
    }

	@Override
	protected List getListaOrdenada() {
		return getLista();
	}
}
