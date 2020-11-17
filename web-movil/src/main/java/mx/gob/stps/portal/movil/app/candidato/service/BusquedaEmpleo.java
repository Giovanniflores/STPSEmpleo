//package mx.gob.stps.portal.movil.app.candidato.service;
//
//import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
//
//import java.math.BigDecimal;
//import java.text.DateFormat;
//import java.text.NumberFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DefaultValue;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//import org.apache.log4j.Logger;
//
//import com.google.gson.Gson;
//
//import javax.ws.rs.core.Context;
//
//import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
//import mx.gob.stps.portal.core.search.ResultInfoBO;
//import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
//import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;
//import mx.gob.stps.portal.movil.app.model.rest.BusquedaOfertaRestDTO;
//import mx.gob.stps.portal.movil.app.model.rest.UsuarioRestDTO;
//import mx.gob.stps.portal.movil.app.service.util.OfertaCandidatoComparadorFecha;
//import mx.gob.stps.portal.movil.app.service.util.OfertaUtil;
//import mx.gob.stps.portal.movil.web.candidato.util.UtilPostulate;
//import mx.gob.stps.portal.movil.web.infra.utils.MessageLoader;
//import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
//import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
//import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;
//
//@Stateless
//@Path("/busquedaEmpleo")
//public class BusquedaEmpleo {
//
//	private Gson gson = new Gson();
//
//	private static Logger logger = Logger.getLogger(BusquedaEmpleo.class);
//
//	@GET
//	// The Java method will produce content identified by the MIME Media
//	// type "text/plain"
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("{idEntidadFederativo}/{palabraClave}/{pagina}/{resultadoPorPagina}")
//	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
//	//public String busquedaOferta(
//	public BusquedaOfertaRestDTO busquedaOferta(
//			@DefaultValue("-1") @PathParam("idEntidadFederativo") final String place,
//			@DefaultValue("0") @PathParam("palabraClave") final String palabraClave,
//			@DefaultValue("0") @PathParam("pagina") final String paginaStr,
//			@DefaultValue("10") @PathParam("resultadoPorPagina") final String resultadoPorPaginaStr,
//			@Context HttpServletRequest request,
//			@Context HttpServletResponse response) {
//
//		// then you can access the request/response/session etc in your methods
//		MessageLoader messages = MessageLoader.getInstance();
//		UsuarioRestDTO usuarioRest = new UsuarioRestDTO();
//		BusquedaOfertaRestDTO busquedaOferta = new BusquedaOfertaRestDTO();
//		Long idCandidato = 0L;
//		//Optener el usuario si esta logeado para calculo de compatibilidad
//		String cookie = request.getHeader(CookieSession.NAME);
//
//		//Validar los valores si son validos antes de seguir
//		if (cookie == null) {
//			cookie = "0";
//		}
//		Integer location = null;
//		String entity = "";
//
//		String query = null;
//		if (!palabraClave.equals("0")) {
//			query = palabraClave;
//		}
//
//		if (!place.equals("-1"))
//			location = Integer.valueOf(place);
//		
//		if (null == query)
//			query = "";
//		
//		busquedaOferta.setResult("OK");
//		List<ResultInfoBO> ofertas = new ArrayList<ResultInfoBO>();
//		List<BusquedaOfertaDTO> ofertasDetaile = new ArrayList<BusquedaOfertaDTO>();
//
//		try {
//			// validar el cookie para obtener el candidato
//			if (!cookie.equals("0")) {
//				MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
//				idCandidato = movilSession.getIdCandidato();
//			}
//			
//			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
//			if (null != location) {
//				ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(location);
//				if (entidad != null) {
//					entity = entidad.getDescripcion();
//				} else {
//					entity = services.getOpcionById(CATALOGO_OPCION_ENTIDAD_FEDERATIVA,location.intValue());
//				}
//			}
//
//			ofertas = services.buscarOfertasEmpleo(query, location);
//
//			// limitar los resultados hasta los de el busqueda
//			// quitarLosNoActivos(ofertas);
//			busquedaOferta.setTamano(ofertas.size());
//			// ofertasDetaile = limitarBusquedaOferta(paginaStr,
//			// resultadoPorPaginaStr, ofertas, idCandidato);
////			for(ResultInfoBO ofer : ofertas){
////				BusquedaOfertaDTO detOfer = detailleOfferta(idCandidato, ofer.getId());
////				ofer.setFecha(mx.gob.stps.portal.utils.Utils.stringDDMMYYToDate(detOfer.getFechaPublicacion()));
////				
////			}
////			
////			Collections.sort(ofertas, new OfertaCandidatoComparadorFecha());
//			busquedaOferta.setIdOfertas(limitarBusquedaIdOferta(paginaStr,resultadoPorPaginaStr, ofertas, idCandidato));
//		} catch (Exception se) {
//			se.printStackTrace();
//			logger.error(se);
//			busquedaOferta.setResult(se.getMessage());
//		}
//
//		busquedaOferta.setOfertas(null);
//
//		// Todo agrega los detalles por los ofertas en el rango de la pagina
//
//		//return gson.toJson(busquedaOferta);
//		return busquedaOferta;
//
//	}
//
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("{idOferta}")
//	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
//	public String busquedaDetailleOferta(
//			@DefaultValue("-1") @PathParam("idOferta") final String idOferta,
//			@Context HttpServletRequest request,
//			@Context HttpServletResponse response) {
//
//		// then you can access the request/response/session etc in your methods
//		BusquedaOfertaRestDTO busquedaOferta = new BusquedaOfertaRestDTO();
//		MessageLoader messages = MessageLoader.getInstance();
//		UsuarioRestDTO usuarioRest = new UsuarioRestDTO();
//		Long idCandidato = 0L;
//		String cookie = request.getHeader(CookieSession.NAME);
//		if (cookie == null) {
//			cookie = "0";
//		}
//		Integer location = null;
//		String entity = "";
//
//		String query = null;
//		if (idOferta.equals("-1")) {
//			busquedaOferta.setResult("Parametros invalido");
//			return gson.toJson(busquedaOferta);
//		}
//
//		logger.info("--> BVS <--- detaille oferta : " + idOferta);
//		logger.info("place " + location);
//		busquedaOferta.setResult("OK");
//		List<ResultInfoBO> ofertas = new ArrayList<ResultInfoBO>();
//		List<BusquedaOfertaDTO> ofertasDetaile = new ArrayList<BusquedaOfertaDTO>();
//
//		try {
//			// validar el cookie para obtener el candidato
//			if (!cookie.equals("0")) {
//				MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
//				idCandidato = movilSession.getIdCandidato();
//			}
//
//			// limitar los resultados hasta los de el busqueda
//			// quitarLosNoActivos(ofertas);
//			busquedaOferta.setTamano(ofertas.size());
//			ofertasDetaile.add(detailleOfferta(idCandidato, Long.valueOf(idOferta)));
//
//		} catch (Exception se) {
//			se.printStackTrace();
//			logger.error(se);
//			busquedaOferta.setResult(se.getMessage());
//		}
//
//		busquedaOferta.setOfertas(ofertasDetaile);
//
//		// Todo agrega los detalles por los ofertas en el rango de la pagina
//
//		return gson.toJson(busquedaOferta);
//
//	}
//
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
//	//public String busquedaDetailleListOferta(String json,
//	public BusquedaOfertaRestDTO busquedaDetailleListOferta(String json,
//			@Context HttpServletRequest request,
//			@Context HttpServletResponse response) {
//
//		// then you can access the request/response/session etc in your methods
//		MessageLoader messages = MessageLoader.getInstance();
//		UsuarioRestDTO usuarioRest = new UsuarioRestDTO();
//		Long idCandidato = 0L;
//		BusquedaOfertaRestDTO ofertas = gson.fromJson(json, BusquedaOfertaRestDTO.class);
//		BusquedaOfertaRestDTO busquedaOferta = new BusquedaOfertaRestDTO();
//		String cookie = request.getHeader(CookieSession.NAME);
//		if (cookie == null) {
//			cookie = "0";
//		}
//		Integer location = null;
//		String entity = "";
//
//		String query = null;
//
//		busquedaOferta.setResult("OK");
//		List<BusquedaOfertaDTO> ofertasDetaile = new ArrayList<BusquedaOfertaDTO>();
//
//		try {
//			// validar el cookie para obtener el candidato
//			if (!cookie.equals("0")) {
//				MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
//				idCandidato = movilSession.getIdCandidato();
//			}
//
//			// limitar los resultados hasta los de el busqueda
//			// quitarLosNoActivos(ofertas);
//			// busquedaOferta.setTamano(ofertas.size());
//			for (String idOferta : ofertas.getIdOfertas()) {
//
//				//logger.info("--> BVS <--- detaille oferta : " + idOferta);
//				//buscar los detaille del oferta referido en el json
//				ofertasDetaile.add(detailleOfferta(idCandidato, Long.valueOf(idOferta)));
//			}
//
//		} catch (Exception se) {
//			se.printStackTrace();
//			logger.error(se);
//			busquedaOferta.setResult(se.getMessage());
//		}
//		busquedaOferta.setTamano(ofertas.getTamano());
//		busquedaOferta.setOfertas(ofertasDetaile);
//
//		// Todo agrega los detalles por los ofertas en el rango de la pagina
//
//		//return gson.toJson(busquedaOferta);
//		return busquedaOferta;
//
//	}
//
//	private void quitarLosNoActivos(List<ResultInfoBO> ofertas) {
//
//		for (int i = ofertas.size() - 1; i >= 0; i--) {
//			System.out.println("test oferta cual oferta = "
//					+ ofertas.get(i).getId());
//			BusquedaOfertaDTO test = OfertaUtil.busquedaDetalle(ofertas.get(i)
//					.getId());
//			if (test.getOferta().getOferta().getEstatus() != 1) {
//				ofertas.remove(i);
//			}
//
//		}
//
//	}
//
//	private List<BusquedaOfertaDTO> limitarBusquedaOferta(String paginaStr,
//			String resultadoPorPaginaStr, List<ResultInfoBO> ofertas,
//			Long idCandidato) {
//		List<BusquedaOfertaDTO> lista = new ArrayList<BusquedaOfertaDTO>();
//		// obtener cuanto resultados debemos regresar
//		int resultados = Integer.valueOf(resultadoPorPaginaStr);
//		// desde cual resultado regresar
//		int offset = Integer.valueOf(paginaStr) * resultados;
//
//		for (int i = 0; i < resultados && (i + offset) < ofertas.size(); i++) {
//			Long idOferta = ofertas.get(i + offset).getId();
//
//			lista.add(detailleOfferta(idCandidato, idOferta));
//		}
//		return lista;
//	}
//
//	private List<String> limitarBusquedaIdOferta(String paginaStr,
//			String resultadoPorPaginaStr, List<ResultInfoBO> ofertas,
//			Long idCandidato) {
//		List<String> lista = new ArrayList<String>();
//		// obtener cuanto resultados debemos regresar
//		int resultados = Integer.valueOf(resultadoPorPaginaStr);
//		// desde cual resultado regresar
//		int offset = Integer.valueOf(paginaStr) * resultados;
//
//		for (int i = 0; i < resultados && (i + offset) < ofertas.size(); i++) {
//			Long idOferta = ofertas.get(i + offset).getId();
//			lista.add(String.valueOf(idOferta));
//
//		}
//		return lista;
//	}
//
//	public BusquedaOfertaDTO detailleOfferta(long idCandidato, long idOferta) {
//
//		BusquedaOfertaDTO oferta = OfertaUtil.busquedaDetalle(idOferta);
//		oferta.getOferta().setTelefonos(new ArrayList<TelefonoVO>());
//
//		if (idCandidato > 0L) {
//			if (oferta.getCompatibilidad()== 0) {
//				int compatibility = (int) OfertaUtil.calculaCompatibilidad(idOferta, idCandidato);
//
//				oferta.setCompatibilidad(compatibility);
//			}
//			oferta.setListPostulated(UtilPostulate.getPostulated(idOferta, idCandidato));
//
//		}
//
//		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
//		DateFormat formatterPub = new SimpleDateFormat(OfertaUtil.FECHAFORMATESCRITO);
//		// Mon May 07 00:00:00 CDT 2012
//		//cambio para el salario
//		String salario = oferta.getOferta().getSalario();
//		
//		NumberFormat nf = NumberFormat.getCurrencyInstance();
//		String salarioFormated = nf.format(new BigDecimal(salario));
//		oferta.getOferta().setSalario(salarioFormated);
//		
//		Date date = new Date();
//		Date datePub=new Date();
//		
//		try {
//			date = (Date) formatter.parse(oferta.getOferta().getFechaAlta());
//			datePub = (Date) formatterPub.parse( oferta.getOferta().getFechaInicio());
//			// oferta.getOferta().setFechaFin(
//			// OfertaUtil.changeDate(oferta.getOferta().getFechaFin(),
//			// "E MMM dd HH:mm:ss z yyyy",
//			// "dd 'de' MMMM 'de' yyyy"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
//		oferta.setFechaAlta(formatter2.format(date));
//		oferta.setFechaPublicacion(formatter2.format(datePub));
//
//		return oferta;
//	}
//
//}

package mx.gob.stps.portal.movil.app.candidato.service;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import javax.ws.rs.core.Context;

import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.search.ResultInfoBO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;
import mx.gob.stps.portal.movil.app.model.rest.BusquedaOfertaRestDTO;
import mx.gob.stps.portal.movil.app.model.rest.UsuarioRestDTO;
import mx.gob.stps.portal.movil.app.service.util.OfertaCandidatoComparadorFecha;
import mx.gob.stps.portal.movil.app.service.util.OfertaUtil;
import mx.gob.stps.portal.movil.web.candidato.util.UtilPostulate;
import mx.gob.stps.portal.movil.web.infra.utils.MessageLoader;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

@Stateless
@Path("/busquedaEmpleo")
public class BusquedaEmpleo {

	private Gson gson = new Gson();

	private static Logger logger = Logger.getLogger(BusquedaEmpleo.class);

	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idEntidadFederativo}/{palabraClave}/{pagina}/{resultadoPorPagina}")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String busquedaOferta(
			@DefaultValue("-1") @PathParam("idEntidadFederativo") final String place,
			@DefaultValue("0") @PathParam("palabraClave") final String palabraClave,
			@DefaultValue("0") @PathParam("pagina") final String paginaStr,
			@DefaultValue("10") @PathParam("resultadoPorPagina") final String resultadoPorPaginaStr,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		MessageLoader messages = MessageLoader.getInstance();
		UsuarioRestDTO usuarioRest = new UsuarioRestDTO();
		BusquedaOfertaRestDTO busquedaOferta = new BusquedaOfertaRestDTO();
		Long idCandidato = 0L;
		//Optener el usuario si esta logeado para calculo de compatibilidad
		String cookie = request.getHeader(CookieSession.NAME);

		//Validar los valores si son validos antes de seguir
		if (cookie == null) {
			cookie = "0";
		}
		Integer location = null;
		String entity = "";

		String query = null;
		if (!palabraClave.equals("0")) {
			query = palabraClave;
		}

		if (!place.equals("-1"))
			location = Integer.valueOf(place);
		
		if (null == query)
			query = "";
		
		busquedaOferta.setResult("OK");
		List<ResultInfoBO> ofertas = new ArrayList<ResultInfoBO>();
		List<BusquedaOfertaDTO> ofertasDetaile = new ArrayList<BusquedaOfertaDTO>();

		try {
			// validar el cookie para obtener el candidato
			if (!cookie.equals("0")) {
				MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
				idCandidato = movilSession.getIdCandidato();
			}
			
			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
			if (null != location) {
				ENTIDADES_FEDERATIVAS entidad = ENTIDADES_FEDERATIVAS.getEntidad(location);
				if (entidad != null) {
					entity = entidad.getDescripcion();
				} else {
					entity = services.getOpcionById(CATALOGO_OPCION_ENTIDAD_FEDERATIVA,location.intValue());
				}
			}

			ofertas = services.buscarOfertasEmpleo(query, location);

			// limitar los resultados hasta los de el busqueda
			// quitarLosNoActivos(ofertas);
			busquedaOferta.setTamano(ofertas.size());
			// ofertasDetaile = limitarBusquedaOferta(paginaStr,
			// resultadoPorPaginaStr, ofertas, idCandidato);
//			for(ResultInfoBO ofer : ofertas){
//				BusquedaOfertaDTO detOfer = detailleOfferta(idCandidato, ofer.getId());
//				ofer.setFecha(mx.gob.stps.portal.utils.Utils.stringDDMMYYToDate(detOfer.getFechaPublicacion()));
//				
//			}
//			
//			Collections.sort(ofertas, new OfertaCandidatoComparadorFecha());
			busquedaOferta.setIdOfertas(limitarBusquedaIdOferta(paginaStr,resultadoPorPaginaStr, ofertas, idCandidato));
		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			busquedaOferta.setResult(se.getMessage());
		}

		busquedaOferta.setOfertas(null);

		// Todo agrega los detalles por los ofertas en el rango de la pagina

		return gson.toJson(busquedaOferta);

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idOferta}")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String busquedaDetailleOferta(
			@DefaultValue("-1") @PathParam("idOferta") final String idOferta,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		BusquedaOfertaRestDTO busquedaOferta = new BusquedaOfertaRestDTO();
		MessageLoader messages = MessageLoader.getInstance();
		UsuarioRestDTO usuarioRest = new UsuarioRestDTO();
		Long idCandidato = 0L;
		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			cookie = "0";
		}
		Integer location = null;
		String entity = "";

		String query = null;
		if (idOferta.equals("-1")) {
			busquedaOferta.setResult("Parametros invalido");
			return gson.toJson(busquedaOferta);
		}

		logger.info("--> BVS <--- detaille oferta : " + idOferta);
		logger.info("place " + location);
		busquedaOferta.setResult("OK");
		List<ResultInfoBO> ofertas = new ArrayList<ResultInfoBO>();
		List<BusquedaOfertaDTO> ofertasDetaile = new ArrayList<BusquedaOfertaDTO>();

		try {
			// validar el cookie para obtener el candidato
			if (!cookie.equals("0")) {
				MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
				idCandidato = movilSession.getIdCandidato();
			}

			// limitar los resultados hasta los de el busqueda
			// quitarLosNoActivos(ofertas);
			busquedaOferta.setTamano(ofertas.size());
			ofertasDetaile.add(detailleOfferta(idCandidato, Long.valueOf(idOferta)));

		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			busquedaOferta.setResult(se.getMessage());
		}

		busquedaOferta.setOfertas(ofertasDetaile);

		// Todo agrega los detalles por los ofertas en el rango de la pagina

		return gson.toJson(busquedaOferta);

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	//public String busquedaDetailleListOferta(String json,
	public String busquedaDetailleListOferta(String json,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		MessageLoader messages = MessageLoader.getInstance();
		UsuarioRestDTO usuarioRest = new UsuarioRestDTO();
		Long idCandidato = 0L;
		BusquedaOfertaRestDTO ofertas = gson.fromJson(json, BusquedaOfertaRestDTO.class);
		BusquedaOfertaRestDTO busquedaOferta = new BusquedaOfertaRestDTO();
		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			cookie = "0";
		}
		Integer location = null;
		String entity = "";

		String query = null;

		busquedaOferta.setResult("OK");
		List<BusquedaOfertaDTO> ofertasDetaile = new ArrayList<BusquedaOfertaDTO>();

		try {
			// validar el cookie para obtener el candidato
			if (!cookie.equals("0")) {
				MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
				idCandidato = movilSession.getIdCandidato();
			}

			// limitar los resultados hasta los de el busqueda
			// quitarLosNoActivos(ofertas);
			// busquedaOferta.setTamano(ofertas.size());
			for (String idOferta : ofertas.getIdOfertas()) {

				//logger.info("--> BVS <--- detaille oferta : " + idOferta);
				//buscar los detaille del oferta referido en el json
				ofertasDetaile.add(detailleOfferta(idCandidato, Long.valueOf(idOferta)));
			}

		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			busquedaOferta.setResult(se.getMessage());
		}
		busquedaOferta.setTamano(ofertas.getTamano());
		busquedaOferta.setOfertas(ofertasDetaile);

		// Todo agrega los detalles por los ofertas en el rango de la pagina

		return gson.toJson(busquedaOferta);

	}

	private void quitarLosNoActivos(List<ResultInfoBO> ofertas) {

		for (int i = ofertas.size() - 1; i >= 0; i--) {
			System.out.println("test oferta cual oferta = "
					+ ofertas.get(i).getId());
			BusquedaOfertaDTO test = OfertaUtil.busquedaDetalle(ofertas.get(i)
					.getId());
			if (test.getOferta().getOferta().getEstatus() != 1) {
				ofertas.remove(i);
			}

		}

	}

	private List<BusquedaOfertaDTO> limitarBusquedaOferta(String paginaStr,
			String resultadoPorPaginaStr, List<ResultInfoBO> ofertas,
			Long idCandidato) {
		List<BusquedaOfertaDTO> lista = new ArrayList<BusquedaOfertaDTO>();
		// obtener cuanto resultados debemos regresar
		int resultados = Integer.valueOf(resultadoPorPaginaStr);
		// desde cual resultado regresar
		int offset = Integer.valueOf(paginaStr) * resultados;

		for (int i = 0; i < resultados && (i + offset) < ofertas.size(); i++) {
			Long idOferta = ofertas.get(i + offset).getId();

			lista.add(detailleOfferta(idCandidato, idOferta));
		}
		return lista;
	}

	private List<String> limitarBusquedaIdOferta(String paginaStr,
			String resultadoPorPaginaStr, List<ResultInfoBO> ofertas,
			Long idCandidato) {
		List<String> lista = new ArrayList<String>();
		// obtener cuanto resultados debemos regresar
		int resultados = Integer.valueOf(resultadoPorPaginaStr);
		// desde cual resultado regresar
		int offset = Integer.valueOf(paginaStr) * resultados;

		for (int i = 0; i < resultados && (i + offset) < ofertas.size(); i++) {
			Long idOferta = ofertas.get(i + offset).getId();
			lista.add(String.valueOf(idOferta));

		}
		return lista;
	}

	public BusquedaOfertaDTO detailleOfferta(long idCandidato, long idOferta) {

		BusquedaOfertaDTO oferta = OfertaUtil.busquedaDetalle(idOferta);
		oferta.getOferta().setTelefonos(new ArrayList<TelefonoVO>());

		if (idCandidato > 0L) {
			if (oferta.getCompatibilidad()== 0) {
				int compatibility = (int) OfertaUtil.calculaCompatibilidad(idOferta, idCandidato);

				oferta.setCompatibilidad(compatibility);
			}
			oferta.setListPostulated(UtilPostulate.getPostulated(idOferta, idCandidato));

		}

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
		DateFormat formatterPub = new SimpleDateFormat(OfertaUtil.FECHAFORMATESCRITO);
		// Mon May 07 00:00:00 CDT 2012
		//cambio para el salario
		String salario = oferta.getOferta().getSalario();
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String salarioFormated = nf.format(new BigDecimal(salario));
		oferta.getOferta().setSalario(salarioFormated);
		
		Date date = new Date();
		Date datePub=new Date();
		
		try {
			date = (Date) formatter.parse(oferta.getOferta().getFechaAlta());
			datePub = (Date) formatterPub.parse( oferta.getOferta().getFechaInicio());
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
		oferta.setFechaPublicacion(formatter2.format(datePub));

		return oferta;
	}

}

