package mx.gob.stps.portal.movil.app.service.util;

import java.util.Comparator;

import mx.gob.stps.portal.movil.app.model.base.PostulatedDTO;

public class OfertasPostuladoComparador implements Comparator<PostulatedDTO> {

	@Override
	public int compare(PostulatedDTO o1, PostulatedDTO o2) {
		
			return Integer.valueOf(o2.getCompatibilidad().compareTo(o1.getCompatibilidad()));
			

	}
}
