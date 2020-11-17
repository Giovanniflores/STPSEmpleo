package mx.gob.stps.portal.movil.app.service.util;

import java.util.Comparator;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;

public class CandidatosRelacionadosComparador implements Comparator<CandidatoVo> {

	@Override
	public int compare(CandidatoVo o1, CandidatoVo o2) {
		/*se verifica si tiene compatiblidad si tienen entonces se ordenan por compatibilidad*/
		if (String.valueOf(o1.getCompatibilidad()) != null && String.valueOf(o2.getCompatibilidad()) != null
				&& Integer.valueOf(o1.getCompatibilidad()).compareTo(Integer.valueOf(o2.getCompatibilidad())) != 0) {

			return Integer.valueOf(o2.getCompatibilidad()).compareTo(Integer.valueOf(o1.getCompatibilidad()));
			
			/* si la compatibilidad es la misma, se ordena por el nombre* */
		} else if (o1.getNombre() != null && o2.getNombre() != null) {

			return o1.getNombre().compareTo(o2.getNombre());
		}
		return 1;

	}
}
