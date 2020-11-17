package mx.gob.stps.portal.movil.app.service.util;

import java.util.Comparator;

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;

public class CandidatoComparador implements Comparator<CandidatoVo> {

	@Override
	public int compare(CandidatoVo o1, CandidatoVo o2) {
		
		if (o1.getApellido1() != null && o2.getApellido1() != null
				&& o1.getApellido2() != null && o2.getApellido2() != null && o1.getNombre() != null && o2.getNombre() != null) {
			/*se debe comparar todo los nombres juntso apellido paterno, apellido materna y nombre */
			StringBuilder nombre1 = new StringBuilder().append(o1.getApellido1()).append(" ").append(o1.getApellido2()).append(" ").append(o1.getNombre());
			StringBuilder nombre2 = new StringBuilder().append(o2.getApellido1()).append(" ").append(o2.getApellido2()).append(" ").append(o2.getNombre());
			return nombre1.toString().compareTo(nombre2.toString());
			
			/* si la compatibilidad es la misma, se ordena por el nombre* */
		} 
		return 1;

	}
}
