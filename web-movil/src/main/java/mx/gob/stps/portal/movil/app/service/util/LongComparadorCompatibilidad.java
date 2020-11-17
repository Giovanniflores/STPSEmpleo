package mx.gob.stps.portal.movil.app.service.util;

import java.util.Comparator;

/*
 * Se occupa por cuando viene el valor de la compatibilidad en long y se transforma a Integer para poderlo comparar correctamente
 */
public class LongComparadorCompatibilidad implements Comparator<Long[]>{

	
	@Override
	public int compare(Long[] o1, Long[] o2) {
		return Integer.valueOf(o2[1].toString()).compareTo(Integer.valueOf(o1[1].toString()));
		
	}

}
