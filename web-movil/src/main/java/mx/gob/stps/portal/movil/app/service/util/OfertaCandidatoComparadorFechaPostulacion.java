package mx.gob.stps.portal.movil.app.service.util;

import java.util.Comparator;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;

public class OfertaCandidatoComparadorFechaPostulacion implements Comparator<OfertaCandidatoOcupacionDTO> {

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * Ordenar ofertaCandidato por compatibilidad y fecha de postulacion, si no tiene compatibildad se ordena por el nombre
	  */
	@Override
	public int compare(OfertaCandidatoOcupacionDTO o1, OfertaCandidatoOcupacionDTO o2) {
		if (o1.getCompatibilidad() != null && o2.getCompatibilidad() != null
				&& Integer.valueOf(o1.getCompatibilidad()).compareTo(Integer.valueOf(o2.getCompatibilidad())) != 0) {

			int compared =  Integer.valueOf(o2.getCompatibilidad()).compareTo(Integer.valueOf(o1.getCompatibilidad()));
			if(compared == 0 && o2.getFechaPostulacion() != null && o1.getFechaPostulacion()!= null){
				return o1.getFechaPostulacion().compareTo(o2.getFechaPostulacion());
			}
			else
			{
				return compared;
			}

		} else if (o1.getNombre() != null && o2.getNombre() != null) {

			return o1.getNombre().compareTo(o2.getNombre());
		}
		return 1;

	}
}
