package mx.gob.stps.portal.web.accesoOLA.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_EXPERIENCIA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.core.persistencia.facade.CatalogoOpcionFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OLAFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaEmpleoFacadeLocal;
import mx.gob.stps.portal.core.persistencia.facade.OfertaFacadeLocal;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.core.ws.vo.Oferta;
import mx.gob.stps.portal.core.ws.vo.OfertaTotalesVO;
import mx.gob.stps.portal.core.ws.vo.OfertasPortalEmpleo;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class PortalEmpleoRest
 */
@WebServlet("/PortalEmpleoRest")
public class PortalEmpleoRest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PortalEmpleoRest.class);
	@EJB
	private OfertaEmpleoFacadeLocal ofertaEmpleoFacade;

	@EJB
	private OLAFacadeLocal olaFacade;

	@EJB
	private CatalogoOpcionFacadeLocal catalogoOpcionFacade;

	@EJB
	private OfertaFacadeLocal ofertaFacade;

	private String urlBusquedaEspecifica;
	private int limiteDeOfertasAPresentarEnListado;

	private static final int SIN_ENTIDAD = 0;
	private static final int SIN_AREA = 0;
	private static final int SIN_ESCOLARIDAD = 0;
	private static final int SIN_SALARIO = 0;
	private static final int SIN_MUNICIPIO = 0;
	private static final int SIN_EDAD = 0;
	private static final int SIN_REGION = 0;
	private static final int PRIMERA_PAGINA = 1;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PortalEmpleoRest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String parametro = request.getParameter("param");
			String idEntidad = request.getParameter("idEntidad");
			if (parametro != null && parametro != "") {
				if (parametro.equals("esCarrera"))
					if (idEntidad != null)
						this.consultaOfertasEmpleoPorEntidadCarrera(request,
								response);
					else
						this.consultaOfertasEmpleoPorCarrera(request, response);
				else if (parametro.equals("esOcupacion"))
					if (idEntidad != null)
						this.consultaOfertasEmpleoPorEntidadOcupacion(request,
								response);
					else
						this.consultaOfertasEmpleoPorOcupacion(request,
								response);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void inicializarPropiedades() {
		setUrlBusquedaEspecifica();
		setLimiteListadoOfertasEmpleo();
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws BusinessException
	 * @throws ServiceLocatorException
	 * @throws IOException
	 * @throws JSONException
	 */
	public void consultaOfertasEmpleoPorCarrera(HttpServletRequest request,
			HttpServletResponse response) throws BusinessException,
			ServiceLocatorException, IOException, JSONException {

		response.setContentType("application/json;charset=UTF-8");

		String pCarrera = request.getParameter("idCarrera");
		int idCarrera = 0;
		if (pCarrera != null && pCarrera != "")
			idCarrera = Integer.parseInt(pCarrera);
		if (idCarrera <= 0)
			throw new IllegalArgumentException(
					"Identificador de Carrera requerido");
		// if (carrera == null || carrera.isEmpty())
		String carrera = "";

		inicializarPropiedades();

		List<Integer> idsCarreraPortal = consultaCarrerasEquivalentes(idCarrera);
		if (idsCarreraPortal == null || idsCarreraPortal.isEmpty())
			throw new IllegalArgumentException(
					"Carrera no pertenece al catalogo");

		OfertasPortalEmpleo ofertasPortalEmpleo = null;

		try {
			int idCarreraPortal = idsCarreraPortal.get(0);
			int idEscolaridad = consultaEscolaridad(idCarreraPortal);

			String urlOfertas = getUrlBusquedaEspecifica() + "&escolaridad="
					+ idEscolaridad;
			urlOfertas = agregarCarrerasRelacionadasAlURL(urlOfertas,
					idsCarreraPortal);
			urlOfertas = urlOfertas + "&descr=" + carrera;

			List<Long> idOfertasEncontradas = ofertaFacade
					.busquedaEspecificaMultiple(SIN_ENTIDAD, SIN_AREA,
							idEscolaridad, SIN_SALARIO, SIN_MUNICIPIO, null,
							idsCarreraPortal, SIN_EDAD, SIN_REGION);
			List<Long> idsOfertasEncontradasLimitadas = new ArrayList<Long>();

			int limiteResultados = getLimiteListadoOfertasEmpleo();
			for (int i = 0; i < limiteResultados
					&& i < idOfertasEncontradas.size(); i++) {
				idsOfertasEncontradasLimitadas.add(i,
						idOfertasEncontradas.get(i));
			}
			OfertaTotalesVO estadisticasOfertas = ofertaFacade
					.obtenerEstadisticasDeOfertasEncontradasPorCarrera(
							idsCarreraPortal, idEscolaridad, 0);
			estadisticasOfertas.setIdsOferta(idsOfertasEncontradasLimitadas);
			ofertasPortalEmpleo = consultaTotalesUltimasOfertas(
					estadisticasOfertas, urlOfertas);

			PrintWriter out = response.getWriter();
			out.print(this.getOfertasJson(ofertasPortalEmpleo));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			ofertasPortalEmpleo = new OfertasPortalEmpleo();
		}
	}

	public OfertasPortalEmpleo consultaOfertasEmpleoPorCarrera(int idCarrera,
			String carrera) throws BusinessException, ServiceLocatorException {

		if (idCarrera <= 0)
			throw new IllegalArgumentException(
					"Identificador de Carrera requerido");
		if (carrera == null || carrera.isEmpty())
			carrera = "";

		inicializarPropiedades();

		List<Integer> idsCarreraPortal = consultaCarrerasEquivalentes(idCarrera);
		if (idsCarreraPortal == null || idsCarreraPortal.isEmpty())
			throw new IllegalArgumentException(
					"Carrera no pertenece al catalogo");

		OfertasPortalEmpleo ofertasPortalEmpleo = null;

		try {
			int idCarreraPortal = idsCarreraPortal.get(0);
			int idEscolaridad = consultaEscolaridad(idCarreraPortal);

			String urlOfertas = getUrlBusquedaEspecifica() + "&escolaridad="
					+ idEscolaridad;
			urlOfertas = agregarCarrerasRelacionadasAlURL(urlOfertas,
					idsCarreraPortal);
			urlOfertas = urlOfertas + "&descr=" + carrera;

			List<Long> idOfertasEncontradas = ofertaFacade
					.busquedaEspecificaMultiple(SIN_ENTIDAD, SIN_AREA,
							idEscolaridad, SIN_SALARIO, SIN_MUNICIPIO, null,
							idsCarreraPortal, SIN_EDAD, SIN_REGION);
			List<Long> idsOfertasEncontradasLimitadas = new ArrayList<Long>();

			int limiteResultados = getLimiteListadoOfertasEmpleo();
			for (int i = 0; i < limiteResultados
					&& i < idOfertasEncontradas.size(); i++) {
				idsOfertasEncontradasLimitadas.add(i,
						idOfertasEncontradas.get(i));
			}
			OfertaTotalesVO estadisticasOfertas = ofertaFacade
					.obtenerEstadisticasDeOfertasEncontradasPorCarrera(
							idsCarreraPortal, idEscolaridad, 0);
			estadisticasOfertas.setIdsOferta(idsOfertasEncontradasLimitadas);
			ofertasPortalEmpleo = consultaTotalesUltimasOfertas(
					estadisticasOfertas, urlOfertas);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			ofertasPortalEmpleo = new OfertasPortalEmpleo();
		}
		return ofertasPortalEmpleo;
	}

	public void consultaOfertasEmpleoPorOcupacion(HttpServletRequest request,
			HttpServletResponse response) throws BusinessException,
			ServiceLocatorException, IOException, JSONException {

		response.setContentType("application/json;charset=UTF-8");

		String pOcupacion = request.getParameter("idOcupacion");

		int idOcupacion = 0;
		if (pOcupacion != null && pOcupacion != "")
			idOcupacion = Integer.parseInt(pOcupacion);
		if (idOcupacion <= 0)
			throw new IllegalArgumentException(
					"Identificador de Ocupacion requerido");
		String ocupacion = "";

		List<Integer> idsOcupacionPortal = consultaOcupacionesEquivalentes(idOcupacion);
		if (idsOcupacionPortal == null || idsOcupacionPortal.isEmpty())
			throw new IllegalArgumentException(
					"Ocupacion no pertenece al catalogo");

		inicializarPropiedades();

		OfertasPortalEmpleo ofertasPortalEmpleo = null;

		try {
			int idOcupacionPortal = idsOcupacionPortal.get(0);
			int idAreaPortal = Integer.valueOf(String
					.valueOf(idOcupacionPortal).substring(0, 2));

			String urlOfertas = getUrlBusquedaEspecifica().toString()
					+ "&area=" + idAreaPortal;
			urlOfertas = agregarOcupacionesRelacionadasAlURL(urlOfertas,
					idsOcupacionPortal);
			urlOfertas = urlOfertas + "&descr=" + ocupacion;

			List<Long> idOfertasEncontradas = ofertaFacade
					.busquedaEspecificaMultiple(SIN_ENTIDAD, idAreaPortal,
							SIN_ESCOLARIDAD, SIN_SALARIO, SIN_MUNICIPIO,
							idsOcupacionPortal, null, SIN_EDAD, SIN_REGION);
			List<Long> idsOfertasEncontradasLimitadas = new ArrayList<Long>();
			int limiteResultados = getLimiteListadoOfertasEmpleo();
			for (int i = 0; i < limiteResultados
					&& i < idOfertasEncontradas.size(); i++) {
				idsOfertasEncontradasLimitadas.add(i,
						idOfertasEncontradas.get(i));
			}
			OfertaTotalesVO estadisticasOfertas = ofertaFacade
					.obtenerEstadisticasDeOfertasEncontradasPorOcupacion(
							idsOcupacionPortal, 0);
			estadisticasOfertas.setIdsOferta(idsOfertasEncontradasLimitadas);
			ofertasPortalEmpleo = consultaTotalesUltimasOfertas(
					estadisticasOfertas, urlOfertas);

			PrintWriter out = response.getWriter();
			out.print(this.getOfertasJson(ofertasPortalEmpleo));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			ofertasPortalEmpleo = new OfertasPortalEmpleo();
		}
	}

	public OfertasPortalEmpleo consultaOfertasEmpleoPorEntidad(int idEntidad) {

		if (idEntidad <= 0)
			throw new IllegalArgumentException(
					"Identificador de Entidad requerido");
		ENTIDADES_FEDERATIVAS entidad = matchEntidadPortalPorCarreraOLA(idEntidad);
		if (entidad == null)
			throw new IllegalArgumentException(
					"Entidad no pertenece al catalogo");

		inicializarPropiedades();

		OfertasPortalEmpleo ofertasPortalEmpleo = null;

		try {
			String urlOfertas = getUrlBusquedaEspecifica().toString()
					+ "&district=" + String.valueOf(idEntidad);

			OfertaTotalesVO totales = ofertaEmpleoFacade
					.consultaOfertasEmpleoPorEntidad(entidad.getIdEntidad());
			ofertasPortalEmpleo = consultaTotalesUltimasOfertas(totales,
					urlOfertas);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return ofertasPortalEmpleo;
	}

	public void consultaOfertasEmpleoPorEntidadOcupacion(
			HttpServletRequest request, HttpServletResponse response)
			throws BusinessException, ServiceLocatorException, IOException,
			JSONException {

		PrintWriter out = response.getWriter();
		response.setContentType("application/json;charset=UTF-8");

		String idEntidad = request.getParameter("idEntidad");
		String idOcupacion = request.getParameter("idOcupacion");
		String ocupacion = request.getParameter("ocupacion");
		int idEntfed = 0;
		int idOcup = 0;

		if (idEntidad != null && idEntidad != "")
			idEntfed = Integer.parseInt(idEntidad);
		else
			throw new IllegalArgumentException(
					"Identificador de idEntidad es requerido");
		if (idOcupacion != null && idOcupacion != "")
			idOcup = Integer.parseInt(idOcupacion);
		else
			throw new IllegalArgumentException(
					"Identificador de idOcupacion es requerido");
		if (idEntfed <= 0 && idOcup <= 0)
			throw new IllegalArgumentException(
					"Identificador de idEntidad e idOcupacion debe ser mayor que cero");
		if (ocupacion == null || ocupacion.isEmpty())
			ocupacion = "";

		inicializarPropiedades();
		OfertasPortalEmpleo ofertasPortalEmpleo = null;

		if (idEntfed > 0 && idOcup <= 0) {
			out.print(this
					.getOfertasJson(consultaOfertasEmpleoPorEntidad(idEntfed)));
		} else {
			ENTIDADES_FEDERATIVAS entidad = matchEntidadPortalPorOcupacionOLA(idEntfed);
			if (entidad == null)
				throw new IllegalArgumentException(
						"Entidad no pertenece al catalogo");

			List<Integer> idsOcupacionPortal = consultaOcupacionesEquivalentes(idOcup);
			if (idsOcupacionPortal == null || idsOcupacionPortal.isEmpty())
				throw new IllegalArgumentException("Ocupacion " + idOcupacion
						+ " no pertenece al catalogo");

			try {
				int idOcupacionPortal = idsOcupacionPortal.get(0);
				int idAreaPortal = Integer.valueOf(String.valueOf(
						idOcupacionPortal).substring(0, 2));

				String urlOfertas = getUrlBusquedaEspecifica().toString()
						+ "&area=" + idAreaPortal;
				urlOfertas = agregarOcupacionesRelacionadasAlURL(urlOfertas,
						idsOcupacionPortal);
				urlOfertas = urlOfertas + "&descr=" + ocupacion;
				urlOfertas = urlOfertas + "&district="
						+ String.valueOf(entidad.getIdEntidad());

				List<Long> idOfertasEncontradas = ofertaFacade
						.busquedaEspecificaMultiple(entidad.getIdEntidad(),
								idAreaPortal, SIN_ESCOLARIDAD, SIN_SALARIO,
								SIN_MUNICIPIO, idsOcupacionPortal, null,
								SIN_EDAD, SIN_REGION);
				List<Long> idsOfertasEncontradasLimitadas = new ArrayList<Long>();
				int limiteResultados = getLimiteListadoOfertasEmpleo();
				for (int i = 0; i < limiteResultados
						&& i < idOfertasEncontradas.size(); i++) {
					idsOfertasEncontradasLimitadas.add(i,
							idOfertasEncontradas.get(i));
				}
				OfertaTotalesVO estadisticasOfertas = ofertaFacade
						.obtenerEstadisticasDeOfertasEncontradasPorOcupacion(
								idsOcupacionPortal, entidad.getIdEntidad());
				estadisticasOfertas
						.setIdsOferta(idsOfertasEncontradasLimitadas);
				ofertasPortalEmpleo = consultaTotalesUltimasOfertas(
						estadisticasOfertas, urlOfertas);
				out.print(this.getOfertasJson(ofertasPortalEmpleo));
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			} finally {
				out.flush();
			}
		}
	}

	public void consultaOfertasEmpleoPorEntidadCarrera(
			HttpServletRequest request, HttpServletResponse response)
			throws BusinessException, ServiceLocatorException, IOException,
			JSONException {

		PrintWriter out = response.getWriter();
		response.setContentType("application/json;charset=UTF-8");

		String idEntidad = request.getParameter("idEntidad");
		String idCarrera = request.getParameter("idCarrera");
		String carrera = request.getParameter("carrera");
		int idEntfed = 0;
		int idCarreraProf = 0;

		if (idEntidad != null && idEntidad != "")
			idEntfed = Integer.parseInt(idEntidad);
		else
			throw new IllegalArgumentException(
					"Identificador de idEntidad es requerido");
		if (idCarrera != null && idCarrera != "")
			idCarreraProf = Integer.parseInt(idCarrera);
		else
			throw new IllegalArgumentException(
					"Identificador de idCarrera es requerido");
		if (carrera == null || carrera.isEmpty())
			carrera = "";

		inicializarPropiedades();
		OfertasPortalEmpleo ofertasPortalEmpleo = null;

		if (idEntfed > 0 && idCarreraProf <= 0) {
			out.print(this
					.getOfertasJson(consultaOfertasEmpleoPorEntidad(idEntfed)));
		} else if (idEntfed <= 0 && idCarreraProf > 0) {
			out.print(this.getOfertasJson(consultaOfertasEmpleoPorCarrera(
					idCarreraProf, carrera)));
		} else {
			ENTIDADES_FEDERATIVAS entidad = matchEntidadPortalPorCarreraOLA(idEntfed);
			if (entidad == null)
				throw new IllegalArgumentException(
						"Entidad no pertenece al catalogo");
			if (carrera == null || carrera.isEmpty())
				carrera = "";

			List<Integer> idsCarreraPortal = consultaCarrerasEquivalentes(idCarreraProf);
			if (idsCarreraPortal == null || idsCarreraPortal.isEmpty())
				throw new IllegalArgumentException("Carrera " + idCarreraProf
						+ " no pertenece al catalogo");

			try {
				int idCarreraPortal = idsCarreraPortal.get(0);
				int idEscolaridad = consultaEscolaridad(idCarreraPortal);

				String urlOfertas = getUrlBusquedaEspecifica().toString()
						+ "&district=" + String.valueOf(entidad.getIdEntidad())
						+ "&escolaridad=" + idEscolaridad;
				urlOfertas = agregarCarrerasRelacionadasAlURL(urlOfertas,
						idsCarreraPortal);
				urlOfertas = urlOfertas + "&descr=" + carrera;

				List<Long> idOfertasEncontradas = ofertaFacade
						.busquedaEspecificaMultiple(entidad.getIdEntidad(),
								SIN_AREA, idEscolaridad, SIN_SALARIO,
								SIN_MUNICIPIO, null, idsCarreraPortal,
								SIN_EDAD, SIN_REGION);
				List<Long> idsOfertasEncontradasLimitadas = new ArrayList<Long>();

				int limiteResultados = getLimiteListadoOfertasEmpleo();
				for (int i = 0; i < limiteResultados
						&& i < idOfertasEncontradas.size(); i++) {
					idsOfertasEncontradasLimitadas.add(i,
							idOfertasEncontradas.get(i));
				}
				OfertaTotalesVO estadisticasOfertas;
				estadisticasOfertas = ofertaFacade
						.obtenerEstadisticasDeOfertasEncontradasPorCarrera(
								idsCarreraPortal, idEscolaridad,
								entidad.getIdEntidad());
				estadisticasOfertas
						.setIdsOferta(idsOfertasEncontradasLimitadas);
				ofertasPortalEmpleo = consultaTotalesUltimasOfertas(
						estadisticasOfertas, urlOfertas);

				out.print(this.getOfertasJson(ofertasPortalEmpleo));

			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			} finally {
				out.flush();
			}
		}
	}

	private OfertasPortalEmpleo consultaTotalesUltimasOfertas(
			OfertaTotalesVO totales, String urlOfertas) {

		List<Long> idsOfertasTotalesVO = totales.getIdsOferta();
		List<Oferta> ofertasWs = new ArrayList<Oferta>(); // Ofertas a regresar

		try {
			if (idsOfertasTotalesVO != null && !idsOfertasTotalesVO.isEmpty()) {
				int page = PRIMERA_PAGINA;
				List<OfertaPorCanalVO> ofertasOcupate = IndexerServiceLocator
						.getIndexadorServiceRemote().buscarOfertasOcupateWS(
								page, idsOfertasTotalesVO);
				for (OfertaPorCanalVO oferta : ofertasOcupate) {
					Oferta ofertaWs = crearOferta(oferta);
					ofertasWs.add(ofertaWs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		OfertasPortalEmpleo ofertasPortalEmpleo = setEstadisticasDeOfertasPortalEmpleo(totales);
		ofertasPortalEmpleo = setDescripcionDeExperienciaEnOfertasPortalEmpleo(
				ofertasPortalEmpleo, totales.getExperienciaAnios());
		ofertasPortalEmpleo = setDescripcionDeEscolaridadEnOfertasPortalEmpleo(
				ofertasPortalEmpleo, totales.getIdNivelEstudio());
		ofertasPortalEmpleo.setUrlOfertas(urlOfertas);
		ofertasPortalEmpleo.setOfertas(ofertasWs);

		return ofertasPortalEmpleo;
	}

	private Oferta crearOferta(OfertaPorCanalVO ofertaPorCanalVo) {
		Oferta ofertaWs = new Oferta();
		ofertaWs.setTitulo(ofertaPorCanalVo.getTituloOferta());
		ofertaWs.setUbicacion(ofertaPorCanalVo.getUbicacion());
		ofertaWs.setEmpresa(ofertaPorCanalVo.getEmpresa());
		ofertaWs.setSalario(ofertaPorCanalVo.getSalario());
		ofertaWs.setIdOfertaEmpleo(ofertaPorCanalVo.getIdOfertaEmpleo());
		ofertaWs.setIdEmpresa(ofertaPorCanalVo.getIdEmpresa());
		ofertaWs.setIdMunicipio(ofertaPorCanalVo.getIdMunicipio());

		return ofertaWs;
	}

	private OfertasPortalEmpleo setEstadisticasDeOfertasPortalEmpleo(
			OfertaTotalesVO ofertaTotalesVo) {
		OfertasPortalEmpleo ofertasPortalEmpleo = new OfertasPortalEmpleo();
		ofertasPortalEmpleo.setTotalOfertas(ofertaTotalesVo.getTotalOfertas());
		ofertasPortalEmpleo
				.setSalarioMinimo(ofertaTotalesVo.getSalarioMinimo());
		ofertasPortalEmpleo
				.setSalarioMaximo(ofertaTotalesVo.getSalarioMaximo());
		ofertasPortalEmpleo.setSalarioPromedio(ofertaTotalesVo
				.getSalarioPromedio());
		return ofertasPortalEmpleo;
	}

	private OfertasPortalEmpleo setDescripcionDeExperienciaEnOfertasPortalEmpleo(
			OfertasPortalEmpleo ofertasPortalEmpleo, int idAniosDeExperiencia) {
		CatalogoOpcionVO experiencia = catalogoOpcionFacade.findById(
				CATALOGO_OPCION_EXPERIENCIA, idAniosDeExperiencia);
		if (experiencia != null)
			ofertasPortalEmpleo.setExperiencia(experiencia.getOpcion());
		return ofertasPortalEmpleo;
	}

	private OfertasPortalEmpleo setDescripcionDeEscolaridadEnOfertasPortalEmpleo(
			OfertasPortalEmpleo ofertasPortalEmpleo, int idNivelEstudio) {
		CatalogoOpcionVO nivelEstudio = catalogoOpcionFacade.findById(
				CATALOGO_OPCION_GRADO_ESTUDIOS, idNivelEstudio);
		if (nivelEstudio != null)
			ofertasPortalEmpleo.setEscolaridad(nivelEstudio.getOpcion());
		return ofertasPortalEmpleo;
	}

	private int consultaEscolaridad(int idCarreraEspecPortal) {
		int escolaridad = -1;
		int idCatalogo = -1;
		CatalogoOpcionVO carreravo = catalogoOpcionFacade
				.findById((long) idCarreraEspecPortal);
		if (carreravo != null) {
			idCatalogo = (int) carreravo.getIdCatalogo();
			if (idCatalogo >= 40 && idCatalogo <= 45) {
				switch (idCatalogo) {
				case 45:
					escolaridad = 4;
					break;
				case 40:
					escolaridad = 6;
					break;
				case 42:
					escolaridad = 8;
					break;
				case 43:
					escolaridad = 10;
					break;
				case 44:
					escolaridad = 12;
					break;
				}
			}
		}
		return escolaridad;
	}

	@SuppressWarnings("unused")
	// private OLAServiceRemote getConsultaCarrerasOLA()
	// throws ServiceLocatorException {
	// OLAServiceRemote ejb = (OLAServiceRemote) ServiceLocator
	// .getSessionRemote(JNDI_OLA);
	// return ejb;
	// }
	private List<Integer> consultaCarrerasEquivalentes(int idCarreraOLA)
			throws BusinessException, ServiceLocatorException {
		List<Integer> idsCarrera = olaFacade.consultaCarrerasOLA(idCarreraOLA);
		// List<Integer> idsCarrera =
		// this.getConsultaCarrerasOLA().consultaCarrerasOLA(idCarreraOLA);
		if (idsCarrera == null || idsCarrera.isEmpty())
			throw new IllegalArgumentException(
					"Carrera no pertenece al catalogo");
		return idsCarrera;
	}

	private List<Integer> consultaOcupacionesEquivalentes(int idOcupacionOLA) {
		List<Integer> idsOcupacion = olaFacade
				.consultaOcupacionesOLA(idOcupacionOLA);
		if (idsOcupacion == null || idsOcupacion.isEmpty())
			throw new IllegalArgumentException(
					"Ocupación no pertenece al catalogo");
		return idsOcupacion;
	}

	private String agregarOcupacionesRelacionadasAlURL(String urlOriginal,
			List<Integer> idsOcupacionesPortalRelacionadas) {
		String urlNueva = urlOriginal;
		if (null != idsOcupacionesPortalRelacionadas
				&& !idsOcupacionesPortalRelacionadas.isEmpty()) {
			for (Integer tmpIdOcupacion : idsOcupacionesPortalRelacionadas) {
				if (tmpIdOcupacion > 0)
					urlNueva = urlNueva + "&ocupacion="
							+ String.valueOf(tmpIdOcupacion);
			}
		}
		return urlNueva;
	}

	private String agregarCarrerasRelacionadasAlURL(String urlOriginal,
			List<Integer> idsCarrerasPortalRelacionadas) {
		String urlNueva = urlOriginal;
		if (null != idsCarrerasPortalRelacionadas
				&& !idsCarrerasPortalRelacionadas.isEmpty()) {
			for (Integer tmpIdCarrera : idsCarrerasPortalRelacionadas) {
				if (tmpIdCarrera > 0)
					urlNueva = urlNueva + "&carrera="
							+ String.valueOf(tmpIdCarrera);
			}
		}
		return urlNueva;
	}

	private void setLimiteListadoOfertasEmpleo() {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		int limiteDeOfertasAPresentarEnListadoSegunPropiedad = properties
				.getPropertyInt("ws.portal.consulta.ofertas.limite");
		if (limiteDeOfertasAPresentarEnListadoSegunPropiedad <= 0) {
			this.limiteDeOfertasAPresentarEnListado = limiteDeOfertasAPresentarEnListadoSegunPropiedad;
		} else {
			this.limiteDeOfertasAPresentarEnListado = 5;
		}
	}

	private int getLimiteListadoOfertasEmpleo() {
		logger.info("---limiteListado:"
				+ this.limiteDeOfertasAPresentarEnListado);
		return this.limiteDeOfertasAPresentarEnListado;
	}

	private void setUrlBusquedaEspecifica() {
		PropertiesLoader properties = PropertiesLoader.getInstance();
		urlBusquedaEspecifica = properties
				.getProperty("ws.portal.busqueda.especifica.ofertas.url");
	}

	private String getUrlBusquedaEspecifica() {
		return urlBusquedaEspecifica;
	}

	private ENTIDADES_FEDERATIVAS matchEntidadPortalPorCarreraOLA(int idEntidad) {
		ENTIDADES_FEDERATIVAS entidadPortal = null;

		ENTIDADES_FEDERATIVAS_CARRERAS entidadOla = ENTIDADES_FEDERATIVAS_CARRERAS
				.getEntidad(idEntidad);

		switch (entidadOla) {
		case AGUASCALIENTES:
			entidadPortal = ENTIDADES_FEDERATIVAS.AGUASCALIENTES;
			break;
		case BAJA_CALIFORNIA:
			entidadPortal = ENTIDADES_FEDERATIVAS.BAJA_CALIFORNIA;
			break;
		case BAJA_CALIFORNIA_SUR:
			entidadPortal = ENTIDADES_FEDERATIVAS.BAJA_CALIFORNIA_SUR;
			break;
		case CAMPECHE:
			entidadPortal = ENTIDADES_FEDERATIVAS.CAMPECHE;
			break;
		case COAHUILA:
			entidadPortal = ENTIDADES_FEDERATIVAS.COAHUILA;
			break;
		case COLIMA:
			entidadPortal = ENTIDADES_FEDERATIVAS.COLIMA;
			break;
		case CHIAPAS:
			entidadPortal = ENTIDADES_FEDERATIVAS.CHIAPAS;
			break;
		case CHIHUAHUA:
			entidadPortal = ENTIDADES_FEDERATIVAS.CHIHUAHUA;
			break;
		case DISTRITO_FEDERAL:
			entidadPortal = ENTIDADES_FEDERATIVAS.DISTRITO_FEDERAL;
			break;
		case DURANGO:
			entidadPortal = ENTIDADES_FEDERATIVAS.DURANGO;
			break;
		case ESTADO_DE_MEXICO:
			entidadPortal = ENTIDADES_FEDERATIVAS.MEXICO;
			break;
		case GUANAJUATO:
			entidadPortal = ENTIDADES_FEDERATIVAS.GUANAJUATO;
			break;
		case GUERRERO:
			entidadPortal = ENTIDADES_FEDERATIVAS.GUERRERO;
			break;
		case HIDALGO:
			entidadPortal = ENTIDADES_FEDERATIVAS.HIDALGO;
			break;
		case JALISCO:
			entidadPortal = ENTIDADES_FEDERATIVAS.JALISCO;
			break;
		case MICHOACAN:
			entidadPortal = ENTIDADES_FEDERATIVAS.MICHOACAN;
			break;
		case MORELOS:
			entidadPortal = ENTIDADES_FEDERATIVAS.MORELOS;
			break;
		case NAYARIT:
			entidadPortal = ENTIDADES_FEDERATIVAS.NAYARIT;
			break;
		case NUEVO_LEON:
			entidadPortal = ENTIDADES_FEDERATIVAS.NUEVO_LEON;
			break;
		case OAXACA:
			entidadPortal = ENTIDADES_FEDERATIVAS.OAXACA;
			break;
		case PUEBLA:
			entidadPortal = ENTIDADES_FEDERATIVAS.PUEBLA;
			break;
		case QUERETARO:
			entidadPortal = ENTIDADES_FEDERATIVAS.QUERETARO;
			break;
		case QUINTANA_ROO:
			entidadPortal = ENTIDADES_FEDERATIVAS.QUINTANA_ROO;
			break;
		case SAN_LUIS_POTOSI:
			entidadPortal = ENTIDADES_FEDERATIVAS.SAN_LUIS_POTOSI;
			break;
		case SINALOA:
			entidadPortal = ENTIDADES_FEDERATIVAS.SINALOA;
			break;
		case SONORA:
			entidadPortal = ENTIDADES_FEDERATIVAS.SONORA;
			break;
		case TABASCO:
			entidadPortal = ENTIDADES_FEDERATIVAS.TABASCO;
			break;
		case TAMAULIPAS:
			entidadPortal = ENTIDADES_FEDERATIVAS.TAMAULIPAS;
			break;
		case TLAXCALA:
			entidadPortal = ENTIDADES_FEDERATIVAS.TLAXCALA;
			break;
		case VERACRUZ:
			entidadPortal = ENTIDADES_FEDERATIVAS.VERACRUZ;
			break;
		case YUCATAN:
			entidadPortal = ENTIDADES_FEDERATIVAS.YUCATAN;
			break;
		case ZACATECAS:
			entidadPortal = ENTIDADES_FEDERATIVAS.ZACATECAS;
			break;
		}

		return entidadPortal;
	}

	private ENTIDADES_FEDERATIVAS matchEntidadPortalPorOcupacionOLA(
			int idEntidad) {
		ENTIDADES_FEDERATIVAS entidadPortal = null;

		ENTIDADES_FEDERATIVAS_OCUPACIONES entidadOla = ENTIDADES_FEDERATIVAS_OCUPACIONES
				.getEntidad(idEntidad);

		switch (entidadOla) {
		case AGUASCALIENTES:
			entidadPortal = ENTIDADES_FEDERATIVAS.AGUASCALIENTES;
			break;
		case BAJA_CALIFORNIA:
			entidadPortal = ENTIDADES_FEDERATIVAS.BAJA_CALIFORNIA;
			break;
		case BAJA_CALIFORNIA_SUR:
			entidadPortal = ENTIDADES_FEDERATIVAS.BAJA_CALIFORNIA_SUR;
			break;
		case CAMPECHE:
			entidadPortal = ENTIDADES_FEDERATIVAS.CAMPECHE;
			break;
		case COAHUILA:
			entidadPortal = ENTIDADES_FEDERATIVAS.COAHUILA;
			break;
		case COLIMA:
			entidadPortal = ENTIDADES_FEDERATIVAS.COLIMA;
			break;
		case CHIAPAS:
			entidadPortal = ENTIDADES_FEDERATIVAS.CHIAPAS;
			break;
		case CHIHUAHUA:
			entidadPortal = ENTIDADES_FEDERATIVAS.CHIHUAHUA;
			break;
		case DISTRITO_FEDERAL:
			entidadPortal = ENTIDADES_FEDERATIVAS.DISTRITO_FEDERAL;
			break;
		case DURANGO:
			entidadPortal = ENTIDADES_FEDERATIVAS.DURANGO;
			break;
		case ESTADO_DE_MEXICO:
			entidadPortal = ENTIDADES_FEDERATIVAS.MEXICO;
			break;
		case GUANAJUATO:
			entidadPortal = ENTIDADES_FEDERATIVAS.GUANAJUATO;
			break;
		case GUERRERO:
			entidadPortal = ENTIDADES_FEDERATIVAS.GUERRERO;
			break;
		case HIDALGO:
			entidadPortal = ENTIDADES_FEDERATIVAS.HIDALGO;
			break;
		case JALISCO:
			entidadPortal = ENTIDADES_FEDERATIVAS.JALISCO;
			break;
		case MICHOACAN:
			entidadPortal = ENTIDADES_FEDERATIVAS.MICHOACAN;
			break;
		case MORELOS:
			entidadPortal = ENTIDADES_FEDERATIVAS.MORELOS;
			break;
		case NAYARIT:
			entidadPortal = ENTIDADES_FEDERATIVAS.NAYARIT;
			break;
		case NUEVO_LEON:
			entidadPortal = ENTIDADES_FEDERATIVAS.NUEVO_LEON;
			break;
		case OAXACA:
			entidadPortal = ENTIDADES_FEDERATIVAS.OAXACA;
			break;
		case PUEBLA:
			entidadPortal = ENTIDADES_FEDERATIVAS.PUEBLA;
			break;
		case QUERETARO:
			entidadPortal = ENTIDADES_FEDERATIVAS.QUERETARO;
			break;
		case QUINTANA_ROO:
			entidadPortal = ENTIDADES_FEDERATIVAS.QUINTANA_ROO;
			break;
		case SAN_LUIS_POTOSI:
			entidadPortal = ENTIDADES_FEDERATIVAS.SAN_LUIS_POTOSI;
			break;
		case SINALOA:
			entidadPortal = ENTIDADES_FEDERATIVAS.SINALOA;
			break;
		case SONORA:
			entidadPortal = ENTIDADES_FEDERATIVAS.SONORA;
			break;
		case TABASCO:
			entidadPortal = ENTIDADES_FEDERATIVAS.TABASCO;
			break;
		case TAMAULIPAS:
			entidadPortal = ENTIDADES_FEDERATIVAS.TAMAULIPAS;
			break;
		case TLAXCALA:
			entidadPortal = ENTIDADES_FEDERATIVAS.TLAXCALA;
			break;
		case VERACRUZ:
			entidadPortal = ENTIDADES_FEDERATIVAS.VERACRUZ;
			break;
		case YUCATAN:
			entidadPortal = ENTIDADES_FEDERATIVAS.YUCATAN;
			break;
		case ZACATECAS:
			entidadPortal = ENTIDADES_FEDERATIVAS.ZACATECAS;
			break;
		}

		return entidadPortal;
	}

	public enum ENTIDADES_FEDERATIVAS_CARRERAS {

		AGUASCALIENTES(1, "Aguascalientes"), BAJA_CALIFORNIA(2,
				"Baja California"), BAJA_CALIFORNIA_SUR(3,
				"Baja California Sur"), CAMPECHE(4, "Campeche"), COAHUILA(5,
				"Coahuila"), COLIMA(6, "Colima"), CHIAPAS(7, "Chiapas"), CHIHUAHUA(
				8, "Chihuahua"), DISTRITO_FEDERAL(9, "Distrito Federal"), DURANGO(
				10, "Durango"), ESTADO_DE_MEXICO(11, "Estado de México"), GUANAJUATO(
				12, "Guanajuato"), GUERRERO(13, "Guerrero"), HIDALGO(14,
				"Hidalgo"), JALISCO(15, "Jalisco"), MICHOACAN(16, "Michoacán"), MORELOS(
				17, "Morelos"), NAYARIT(18, "Nayarit"), NUEVO_LEON(19,
				"Nuevo León"), OAXACA(20, "Oaxaca"), PUEBLA(21, "Puebla"), QUERETARO(
				22, "Querétaro"), QUINTANA_ROO(23, "Quintana Roo"), SAN_LUIS_POTOSI(
				24, "San Luis Potosí"), SINALOA(25, "Sinaloa"), SONORA(26,
				"Sonora"), TABASCO(27, "Tabasco"), TAMAULIPAS(28, "Tamaulipas"), TLAXCALA(
				29, "Tlaxcala"), VERACRUZ(30, "Veracruz"), YUCATAN(31,
				"Yucatán"), ZACATECAS(32, "Zacatecas");

		private int idEntidad;
		private String descripcion;

		private ENTIDADES_FEDERATIVAS_CARRERAS(int idEntidad, String descripcion) {
			this.idEntidad = idEntidad;
			this.descripcion = descripcion;
		}

		public int getIdEntidad() {
			return idEntidad;
		}

		@SuppressWarnings("unused")
		public String getDescripcion() {
			return descripcion;
		}

		public static ENTIDADES_FEDERATIVAS_CARRERAS getEntidad(int idEntidad) {
			ENTIDADES_FEDERATIVAS_CARRERAS entidad = null;

			for (ENTIDADES_FEDERATIVAS_CARRERAS entidadfor : ENTIDADES_FEDERATIVAS_CARRERAS
					.values()) {
				if (entidadfor.getIdEntidad() == idEntidad) {
					entidad = entidadfor;
					break;
				}
			}

			return entidad;
		}
	};

	public enum ENTIDADES_FEDERATIVAS_OCUPACIONES {

		AGUASCALIENTES(1, "Aguascalientes"), BAJA_CALIFORNIA(2,
				"Baja California"), BAJA_CALIFORNIA_SUR(3,
				"Baja California Sur"), CAMPECHE(4, "Campeche"), CHIHUAHUA(5,
				"Chihuahua"), CHIAPAS(6, "Chiapas"), COAHUILA(7, "Coahuila"), COLIMA(
				8, "Colima"), DISTRITO_FEDERAL(9, "Distrito Federal"), DURANGO(
				10, "Durango"), GUERRERO(11, "Guerrero"), GUANAJUATO(12,
				"Guanajuato"), HIDALGO(13, "Hidalgo"), JALISCO(14, "Jalisco"), ESTADO_DE_MEXICO(
				15, "Estado de México"), MICHOACAN(16, "Michoacán"), MORELOS(
				17, "Morelos"), NAYARIT(18, "Nayarit"), NUEVO_LEON(19,
				"Nuevo León"), OAXACA(20, "Oaxaca"), PUEBLA(21, "Puebla"), QUERETARO(
				22, "Querétaro"), QUINTANA_ROO(23, "Quintana Roo"), SINALOA(24,
				"Sinaloa"), SAN_LUIS_POTOSI(25, "San Luis Potosí"), SONORA(26,
				"Sonora"), TABASCO(27, "Tabasco"), TAMAULIPAS(28, "Tamaulipas"), TLAXCALA(
				29, "Tlaxcala"), VERACRUZ(30, "Veracruz"), YUCATAN(31,
				"Yucatán"), ZACATECAS(32, "Zacatecas");

		private int idEntidad;
		private String descripcion;

		private ENTIDADES_FEDERATIVAS_OCUPACIONES(int idEntidad,
				String descripcion) {
			this.idEntidad = idEntidad;
			this.descripcion = descripcion;
		}

		public int getIdEntidad() {
			return idEntidad;
		}

		@SuppressWarnings("unused")
		public String getDescripcion() {
			return descripcion;
		}

		public static ENTIDADES_FEDERATIVAS_OCUPACIONES getEntidad(int idEntidad) {
			ENTIDADES_FEDERATIVAS_OCUPACIONES entidad = null;

			for (ENTIDADES_FEDERATIVAS_OCUPACIONES entidadfor : ENTIDADES_FEDERATIVAS_OCUPACIONES
					.values()) {
				if (entidadfor.getIdEntidad() == idEntidad) {
					entidad = entidadfor;
					break;
				}
			}
			return entidad;
		}
	};

	public JSONArray getOfertasJson(OfertasPortalEmpleo ofertasPortalEmpleo) {
		JSONArray listJson = new JSONArray();
		JSONObject obj1 = new JSONObject();
		try {
			obj1.put(
					"escolaridad",
					(ofertasPortalEmpleo.getEscolaridad() != null ? ofertasPortalEmpleo
							.getEscolaridad().toString() : ""));
			obj1.put(
					"experiencia",
					(ofertasPortalEmpleo.getExperiencia() != null ? ofertasPortalEmpleo
							.getExperiencia().toString() : ""));
			listJson.put(obj1);
			for (Oferta list : ofertasPortalEmpleo.getOfertas()) {
				JSONObject obj2 = new JSONObject();
				obj2.put("titulo", (list.getTitulo() != null ? list.getTitulo()
						.toString() : ""));
				obj2.put(
						"idOfertaEmpleo",
						(list.getIdOfertaEmpleo() > 0 ? String.valueOf(list
								.getIdOfertaEmpleo()) : "0"));
				obj2.put(
						"idEmpresa",
						(list.getIdEmpresa() > 0 ? String.valueOf(list
								.getIdEmpresa()) : "0"));
				obj2.put("empresa", (list.getEmpresa() != null ? list
						.getEmpresa().toString() : ""));
				obj2.put(
						"idMunicipio",
						(list.getIdMunicipio() > 0 ? String.valueOf(list
								.getIdMunicipio()) : "0"));
				obj2.put("ubicacion", (list.getUbicacion() != null ? list
						.getUbicacion().toString() : ""));
				obj2.put(
						"salario",
						(list.getSalario() > 0 ? String.valueOf(list
								.getSalario()) : "0"));
				listJson.put(obj2);
			}
			JSONObject obj3 = new JSONObject();
			obj3.put(
					"salarioMaximo",
					(ofertasPortalEmpleo.getSalarioMaximo() > 0 ? String
							.valueOf(ofertasPortalEmpleo.getSalarioMaximo())
							: "0"));
			obj3.put(
					"salarioMinimo",
					(ofertasPortalEmpleo.getSalarioMinimo() > 0 ? String
							.valueOf(ofertasPortalEmpleo.getSalarioMinimo())
							: "0"));
			obj3.put(
					"salarioPromedio",
					(ofertasPortalEmpleo.getSalarioPromedio() > 0 ? String
							.valueOf(ofertasPortalEmpleo.getSalarioPromedio())
							: "0"));
			obj3.put(
					"totalOfertas",
					(ofertasPortalEmpleo.getTotalOfertas() > 0 ? String
							.valueOf(ofertasPortalEmpleo.getTotalOfertas())
							: "0"));
			obj3.put(
					"urlOfertas",
					(ofertasPortalEmpleo.getUrlOfertas() != null ? ofertasPortalEmpleo
							.getUrlOfertas().toString() : ""));
			listJson.put(obj3);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return listJson;
	}

}
