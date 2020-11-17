package mx.gob.stps.portal.movil.app.empresa.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.movil.app.empresa.model.rest.OfertasEmpresaRestDTO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;
import mx.gob.stps.portal.movil.app.service.util.OfertaUtil;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.empresa.vo.OfertasEmpresaVO;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

@Stateless
@Path("/ofertasEmpresa")
public class OfertasEmpresa {

	private Gson gson = new Gson();
	private static Logger logger = Logger.getLogger(OfertasEmpresa.class);
	private OfertasEmpresaRestDTO ofertasEmpresa = new OfertasEmpresaRestDTO();

	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{pagina}/{resultadoPorPagina}")
	public String ofertasEmpresa(@DefaultValue("0") @PathParam("pagina") final String paginaStr,
			@DefaultValue("10") @PathParam("resultadoPorPagina") final String resultadoPorPaginaStr,
			@Context HttpServletRequest request, @Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		Long idEmpresa = 0L;
		ofertasEmpresa.setResult("OK");
		String cookie = request.getHeader(CookieSession.NAME);

		if (cookie == null) {
			ofertasEmpresa.setResult("No se encuentra el Token.");
			return gson.toJson(ofertasEmpresa);
		}

		List<BusquedaOfertaDTO> ofertasDetaile = new ArrayList<BusquedaOfertaDTO>();

		try {
			// validar el cookie para obtener el candidato
			MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
			idEmpresa = movilSession.getIdEmpresa();

			EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();
			List<OfertaEmpleoVO> ofertas = service.obtenerOfertasEmpresa(idEmpresa);

//			List<OfertasEmpresaVO> listaOfertas = generaListaOfertas(ofertas);

			// limitar los resultados hasta los de el busqueda
			quitarLosNoActivos(ofertas);
			ofertasEmpresa.setTamano(ofertas.size());
			
			// Todo agrega los detalles por los ofertas en el rango de la pagina
			ofertasDetaile = limitarBusquedaOferta(paginaStr, resultadoPorPaginaStr, ofertas);


		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			ofertasEmpresa.setResult(se.getMessage());
		}

		ofertasEmpresa.setOfertas(ofertasDetaile);
        return gson.toJson(ofertasEmpresa);

	}

	private List<OfertasEmpresaVO> generaListaOfertas(List<OfertaEmpleoVO> ofertas) {
		List<OfertasEmpresaVO> listaOfertas = new ArrayList<OfertasEmpresaVO>();
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();

		for (OfertaEmpleoVO oferta : ofertas) {
			try {
				oferta.setUbicaciones(service.getUbicacionesOferta(oferta.getIdOfertaEmpleo()));
				OfertasEmpresaVO ofertaVO = new OfertasEmpresaVO();
				OfertaCarreraEspecialidadVO carr = oferta.getCarreraPrincipal();
				ofertaVO.setIdOfertaEmpleo(oferta.getIdOfertaEmpleo());
				ofertaVO.setTituloOferta(oferta.getTituloOferta());
				ofertaVO.setOcupacion(service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_OCUPACION,
						oferta.getIdOcupacion()));
				ofertaVO.setNivelEstudios(service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS,
						oferta.getIdNivelEstudio()));
				ofertaVO.setCarrera(obtieneCarrera(carr));
				ofertaVO.setUbicacion(oferta.getUbicaciones().get(0).getEntidad() + "/"
						+ oferta.getUbicaciones().get(0).getMunicipio());
				ofertaVO.setEstatus(oferta.getEstatus());

				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				ofertaVO.setFechaAlta(formatter.format(oferta.getFechaAlta()));
				ofertaVO.setOfertaEmpleoVO(oferta);

				listaOfertas.add(ofertaVO);
			} catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return listaOfertas;
	}

	private String obtieneCarrera(OfertaCarreraEspecialidadVO carr) {
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();
		String carrera = "";
		logger.info("Carrera " + carr.getId());
		if (carr != null) {
			try {
				carrera = service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_CARRERAS_1, carr.getId());
				if ((carrera == null) || ("".equals(carrera)))
					carrera = service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_CARRERAS_2, carr.getId());
				if ((carrera == null) || ("".equals(carrera)))
					carrera = service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_CARRERAS_3, carr.getId());
				if ((carrera == null) || ("".equals(carrera)))
					carrera = service.getCatalogoOpcionById(Constantes.CATALOGO_OPCION_CARRERAS_4, carr.getId());

			} catch (ServiceLocatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return carrera;
	}

	private void quitarLosNoActivos(List<OfertaEmpleoVO> ofertas) {

		for (int i = ofertas.size() - 1; i >= 0; i--) {
			System.out.println("test oferta cual oferta = " + ofertas.get(i).getIdOfertaEmpleo());
			BusquedaOfertaDTO test = OfertaUtil.busquedaDetalle(ofertas.get(i).getIdOfertaEmpleo());
			if (test.getOferta().getOferta().getEstatus() != 1) {
				ofertas.remove(i);
			}

		}

	}

	private List<BusquedaOfertaDTO> limitarBusquedaOferta(String paginaStr, String resultadoPorPaginaStr,
			List<OfertaEmpleoVO> ofertas) {
		List<BusquedaOfertaDTO> lista = new ArrayList<BusquedaOfertaDTO>();
		// obtener cuanto resultados debemos regresar
		int resultados = Integer.valueOf(resultadoPorPaginaStr);
		// desde cual resultado regresar
		int offset = Integer.valueOf(paginaStr) * resultados;

		for (int i = 0; i < resultados && (i + offset) < ofertas.size(); i++) {
			Long idOferta = ofertas.get(i + offset).getIdOfertaEmpleo();
			BusquedaOfertaDTO oferta = OfertaUtil.busquedaDetalle(idOferta);
			oferta.getOferta().setTelefonos(new ArrayList<TelefonoVO>());

			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);

			Date date = new Date();
			try {
				date = (Date) formatter.parse(oferta.getOferta().getFechaAlta());
				oferta.getOferta().setFechaFin(
						OfertaUtil.changeDate(oferta.getOferta().getFechaFin(), "E MMM dd HH:mm:ss z yyyy",
								"dd 'de' MMMM 'de' yyyy"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
			oferta.setFechaAlta(formatter2.format(date));

			lista.add(oferta);
		}
		return lista;
	}

}


