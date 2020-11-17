package mx.gob.stps.portal.movil.app.service.util;

import java.util.Comparator;

import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorPerfilVO;

public class OfertasRecomendadasComparador implements Comparator<OfertaPorPerfilVO> {

	@Override
	public int compare(OfertaPorPerfilVO o1, OfertaPorPerfilVO o2) {
		
			return Integer.valueOf(o2.getCompatibilidad()).compareTo(Integer.valueOf(o1.getCompatibilidad()));
			

	}
}
