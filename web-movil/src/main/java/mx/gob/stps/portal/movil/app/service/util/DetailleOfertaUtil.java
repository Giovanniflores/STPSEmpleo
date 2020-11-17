package mx.gob.stps.portal.movil.app.service.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;
import mx.gob.stps.portal.movil.web.candidato.util.UtilPostulate;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;

public class DetailleOfertaUtil {

	public static BusquedaOfertaDTO detailleOfferta(final long idCandidato, final Long idOferta, final Long comp) {

		BusquedaOfertaDTO oferta = OfertaUtil.busquedaDetalle(idOferta);
		oferta.getOferta().setTelefonos(new ArrayList<TelefonoVO>());
		if (idCandidato > 0L) {
			if(comp >0){
				oferta.setCompatibilidad(Integer.valueOf(String.valueOf(comp)));
			}
			if (oferta.getCompatibilidad() == 0) {
				int compatibility = (int) OfertaUtil.calculaCompatibilidad(
						idOferta, idCandidato);

				oferta.setCompatibilidad(compatibility);
			}
			oferta.setListPostulated(UtilPostulate.getPostulated(idOferta,
					idCandidato));
			if(oferta.getIdPostulated() == null){
				oferta.setIdPostulated("");
			}

		}

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",
				Locale.US);
		// Mon May 07 00:00:00 CDT 2012

		Date date = new Date();
		try {
			date = (Date) formatter.parse(oferta.getOferta().getFechaAlta());
			// oferta.getOferta().setFechaFin(
			// OfertaUtil.changeDate(oferta.getOferta().getFechaFin(),
			// "E MMM dd HH:mm:ss z yyyy",
			// "dd 'de' MMMM 'de' yyyy"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
		oferta.setFechaAlta(formatter2.format(date));

		return oferta;
	}

}
