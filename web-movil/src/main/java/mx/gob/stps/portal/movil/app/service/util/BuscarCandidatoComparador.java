package mx.gob.stps.portal.movil.app.service.util;
/*
 *  La funcion de este clase es para ordenar CandidatoVo como se especifican en la regla de negocio
 *  en los busquedas de candidato
 */
import java.util.Comparator;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;

public class BuscarCandidatoComparador implements Comparator<CandidatoVo> {

	@Override
	public int compare(CandidatoVo o1, CandidatoVo o2) {
		// Verificar que estan ordenado por el nombre
		if (o1.getNombre() != null && o2.getNombre() != null && o1.getNombre().compareTo(o2.getNombre()) != 0
				) {

			return o1.getNombre().compareTo(o2.getNombre());
			
		/* si el nombre es el mismo se ordena por el primero apellido* */
		} else if (o1.getApellido1() != null && o2.getApellido1() != null && o1.getApellido1().compareTo(o2.getApellido1()) != 0) {

			return o1.getApellido1().compareTo(o2.getApellido1());
		}
		/* si el nombre y apellido paterno son igual entonces se ordena por el appelido materna */
		else if(o1.getApellido2() != null && o2.getApellido2() != null){
			return o1.getApellido2().compareTo(o2.getApellido2());
		}
		return 1;

	}
}
