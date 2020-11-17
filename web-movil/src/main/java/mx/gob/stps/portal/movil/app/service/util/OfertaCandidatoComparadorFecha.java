package mx.gob.stps.portal.movil.app.service.util;

import java.util.Comparator;

import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;

public class OfertaCandidatoComparadorFecha implements Comparator<ResultInfoBO> {

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * ordernar los ofertasde candidtos por la fecha 
	 */
	@Override
	public int compare(ResultInfoBO o1, ResultInfoBO o2) {
		
		if (o1.getFecha() != null && o2.getFecha() != null
				&& o2.getFecha().compareTo(o1.getFecha()) != 0) {

			return o2.getFecha().compareTo(o1.getFecha());
			
			/* si la compatibilidad es la misma, se ordena por el nombre* */
		}
		return 1;

	}
}
