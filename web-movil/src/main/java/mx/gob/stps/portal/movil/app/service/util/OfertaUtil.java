package mx.gob.stps.portal.movil.app.service.util;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaEmpleoJB;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.movil.web.oferta.form.OfertaJB;
import mx.gob.stps.portal.movil.web.seguridad.delegate.SeguridadMovilDelegateImpl;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

public final class OfertaUtil {

	private static Logger logger = Logger.getLogger(OfertaUtil.class);

	private static SeguridadMovilDelegateImpl services = SeguridadMovilDelegateImpl.getInstance();

	public static String FECHAFORMATESCRITO = "dd 'de' MMMM 'de' yyyy";

	public static long calculaCompatibilidad(long idOfertaEmpleo, long idCandidato) {
		long compatibility = 0;

		try {
			EmpresaEspacioDelegate services = EmpresaEspacioDelegateImpl.getInstance();
			compatibility = services.match(idOfertaEmpleo, idCandidato);
		} catch (Exception e) {
			logger.error(e);
		}

		return compatibility;
	}

	public static String calculaCompatibilidad(String idOfertaEmpleo, long idCandidato) {
		long compatibility = 0;

		try {
			EmpresaEspacioDelegate services = EmpresaEspacioDelegateImpl.getInstance();
			compatibility = services.match(Long.valueOf(idOfertaEmpleo), idCandidato);
		} catch (Exception e) {
			logger.error(e);
		}

		return String.valueOf(compatibility);
	}
	public static BusquedaOfertaDTO busquedaDetalle(long idOferta) {

		BusquedaOfertaDTO ofertaJB = new BusquedaOfertaDTO(getoffer(idOferta));
		/** Se consulta el detalle de la oferta de empleo **/

		return ofertaJB;
	}
	
	
	public static BusquedaOfertaDTO busquedaDetalleActiva(long idOferta) {

		BusquedaOfertaDTO ofertaJB = new BusquedaOfertaDTO(getofferActiva(idOferta));
		/** Se consulta el detalle de la oferta de empleo **/

		return ofertaJB;
	}
	// Solo regresa los detailles de los ofertas activas y si no regresa null
	private static OfertaJB getofferActiva(long offerID) {
		OfertaJB offer = new OfertaJB();

		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			OfertaEmpleoJB jb = services.buscarOfertaEmpleo(offerID);
			//OfertaBusDelegate ofertaServices = OfertaBusDelegateImpl.getInstance();
			
			OfertaEmpleoVO oferta = services.consultaOfertaEmpleoActiva(offerID);
			if(oferta != null){
				BeanUtils.copyProperties(offer, jb);
				jb.getOferta().setDomicilio(oferta.getDomicilio());
			}
			else
				return null;

		} catch (BusinessException be) {
			logger.error(be);
		} catch (ServiceLocatorException le) {
			logger.error(le);
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}

		return offer;
	}

	
	private static OfertaJB getoffer(long offerID) {
		OfertaJB offer = new OfertaJB();

		try {
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			OfertaEmpleoJB jb = services.buscarOfertaEmpleo(offerID);
			//OfertaBusDelegate ofertaServices = OfertaBusDelegateImpl.getInstance();
			
			OfertaEmpleoVO oferta = services.consultaOfertaEmpleo(offerID);
			BeanUtils.copyProperties(offer, jb);
			jb.getOferta().setDomicilio(oferta.getDomicilio());

		} catch (BusinessException be) {
			logger.error(be);
		} catch (ServiceLocatorException le) {
			logger.error(le);
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}

		return offer;
	}

	public static List<ESTATUS> getEstatusPostulados() {
		List<ESTATUS> listEstatus = new ArrayList<ESTATUS>();
		listEstatus.add(ESTATUS.POSTULADO);
		listEstatus.add(ESTATUS.CANCELADA);
		listEstatus.add(ESTATUS.PENDIENTE_PUBLICAR);
		listEstatus.add(ESTATUS.ELIMINADA_ADMIN);
		listEstatus.add(ESTATUS.ELIMINADA_EMP);
		listEstatus.add(ESTATUS.ELIMINADA_VIG);
		return listEstatus;
	}

	public static List<ESTATUS> getEstatusEmpresasMeBuscan() {
		List<ESTATUS> listEstatus = new ArrayList<ESTATUS>();
		listEstatus.add(ESTATUS.VINCULADO);
		listEstatus.add(ESTATUS.EN_PROCESO);
		return listEstatus;
	}

	public static boolean validateIsPostulated(int est) {

		for (ESTATUS estatus : getEstatusPostulados()) {
			if (estatus.getIdOpcion() == est) {
				return true;
			}
		}
		return false;
	}

	public static String datoToString(Date fecha) {
		if (fecha == null) {
			return "";
		}
		DateFormat formatter2 = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "MX"));

		return formatter2.format(fecha);
	}

	public static String changeDate(String original, String fechaFormat1, String fechaFormat2) {
		if (original != null && !original.isEmpty()) {
			if(original.contains(" de ")){
				return original;
			}
			DateFormat formatter = new SimpleDateFormat(fechaFormat1, Locale.US);

			Date date = new Date();

			try {
				date = (Date) formatter.parse(original);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return original;
			}
			DateFormat formatter2 = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "MX"));
			return formatter2.format(date);
		} else {
			return "";
		}

	}

	public static boolean isAuthenticatedCookie(MovilSessionVO movilSession) throws BusinessException,
			ServiceLocatorException {
		MovilSessionVO movilSessionDb = services.existeMovilSessionVO(movilSession);
		if (!movilSessionDb.getToken().equals(movilSession.getToken())) {
			BusinessException business = new BusinessException("Usuario No Authenticado");

			throw business;
		}
		return true;
	}

	public static boolean comparaFecha(String fecha) {
		
		if (fecha != null && !fecha.isEmpty()) {

			DateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
			try {
				Date currentDate = new Date();

				Date date = new Date();
				if (fecha != null) {
					date = (Date) formatter.parse(fecha);
				}

				Calendar cal = Calendar.getInstance();
				cal.setTime(currentDate);

				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				cal.set(Calendar.AM_PM, 0);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(date);

				int diffDays = 0;
				while (cal.before(cal2)) {
					cal.add(Calendar.DAY_OF_MONTH, 1);
					diffDays++;
				}

				while (cal.after(cal2)) {
					cal.add(Calendar.DAY_OF_MONTH, -1);
					diffDays++;
				}

				if (diffDays > 7) {
					return false;
				} else {
					return true;
				}
			} catch (ParseException ex) {

			}
		}
		return false;

	}

}
