package mx.gob.stps.portal.movil.app.service.util;

import java.util.Comparator;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;

public class OfertaCandidatoComparador implements Comparator<OfertaCandidatoOcupacionDTO> {

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * se compara OfertaCandidatoOcupacionDTO por la regla de negocio
	 */
	@Override
	public int compare(OfertaCandidatoOcupacionDTO o1, OfertaCandidatoOcupacionDTO o2) {
		
		if (o1.getCompatibilidad() != null && o2.getCompatibilidad() != null
				&& Integer.valueOf(o1.getCompatibilidad()).compareTo(Integer.valueOf(o2.getCompatibilidad())) != 0) {

			return Integer.valueOf(o2.getCompatibilidad()).compareTo(Integer.valueOf(o1.getCompatibilidad()));
			
		/* si la compatibilidad es la misma, se ordena por el nombre* */
		} else if (o1.getNombre() != null && o2.getNombre() != null) {

			return o1.getNombre().compareTo(o2.getNombre());
		}
		return 1;

	}
}
