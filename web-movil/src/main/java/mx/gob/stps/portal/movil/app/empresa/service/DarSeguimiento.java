package mx.gob.stps.portal.movil.app.empresa.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.movil.app.empresa.model.rest.BusquedaCandidatoRestDTO;
import mx.gob.stps.portal.movil.app.empresa.model.rest.HireRestDTO;
import mx.gob.stps.portal.movil.app.empresa.model.rest.HiredRestDTO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.movil.app.service.util.OfertaCandidatoComparador;
import mx.gob.stps.portal.movil.web.candidato.delegate.CandidatoDelegateImpl;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;

import org.apache.log4j.Logger;

import com.google.gson.Gson;


@Stateless
@Path("/darSeguimiento")
public class DarSeguimiento {

	private Gson gson = new Gson();
	private static Logger logger = Logger.getLogger(BuscarCandidatosEmpresa.class);
	BaseRestDTO datos = new BaseRestDTO();


	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{pagina}/{resultadoPorPagina}")
	public String darSeguimientoCandidatos(
			@DefaultValue("0") @PathParam("pagina") final String paginaStr,
			@DefaultValue("10") @PathParam("resultadoPorPagina") final String resultadoPorPaginaStr,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		BusquedaCandidatoRestDTO darSeguimiento = new BusquedaCandidatoRestDTO();
		String cookie = request.getHeader(CookieSession.NAME);
		darSeguimiento.setResult("OK");
		if (cookie == null) {
			darSeguimiento.setResult("No se encuentra el Token");
			return gson.toJson(darSeguimiento);
		}

		List<OfertaCandidatoOcupacionDTO> ofertasCandidato = new ArrayList<OfertaCandidatoOcupacionDTO>();
		Long idEmpresa = 0L;
		try {
			// validar el cookie para obtener el idEmpresa
			MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
			idEmpresa = movilSession.getIdEmpresa();

			EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl
					.getInstance();
			List<ESTATUS> estatusBusqueda = new ArrayList<ESTATUS>();
			estatusBusqueda.add(ESTATUS.EN_PROCESO);
			List<ESTATUS> estatusOferta = new ArrayList<ESTATUS>();
			estatusOferta.add(ESTATUS.ACTIVO);

			ofertasCandidato = service.obtenerOfertaCandidatoEmpresa(idEmpresa,
					estatusBusqueda,estatusOferta);
			/*****/
			if (ofertasCandidato == null || ofertasCandidato.isEmpty()) {
//				darSeguimiento.setError("No se encontraron ofertas activas para dar seguimiento");
				return gson.toJson(darSeguimiento);
			}

			/* se calcula compatibilidad */
			for (int i = 0; i < ofertasCandidato.size(); i++) {
				long compatibility = calculaCompatibilidad(Long.valueOf(ofertasCandidato.get(i).getIdOfertaEmpleo()),
						Long.valueOf(ofertasCandidato.get(i).getIdCandidato()));
				ofertasCandidato.get(i).setCompatibilidad(String.valueOf(compatibility));

			}

			/* se ordena la lista deacuerdo a la compatibilidad */
			Collections.sort(ofertasCandidato, new OfertaCandidatoComparador());

			darSeguimiento.setTamano(ofertasCandidato.size());
			ofertasCandidato = limitarBusquedaCandidato(paginaStr,
					resultadoPorPaginaStr, ofertasCandidato);

		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			darSeguimiento.setResult(se.getMessage());
			return gson.toJson(darSeguimiento);
		}

		darSeguimiento.setOfertaCandidato(ofertasCandidato);

		return gson.toJson(darSeguimiento);

	}

	private List<OfertaCandidatoOcupacionDTO> limitarBusquedaCandidato(String paginaStr, String resultadoPorPaginaStr,
			List<OfertaCandidatoOcupacionDTO> ofertasCandidato) {

		List<OfertaCandidatoOcupacionDTO> lista = new ArrayList<OfertaCandidatoOcupacionDTO>();

		// obtener cuanto resultados debemos regresar
		int resultados = Integer.valueOf(resultadoPorPaginaStr);
		// desde cual resultado regresar
		int offset = Integer.valueOf(paginaStr) * resultados;

		for (int i = 0; i < resultados && (i + offset) < ofertasCandidato.size(); i++) {
			String fechaAlta = null;
			fechaAlta = ofertasCandidato.get(i + offset).getFechaPostulacion();
			ofertasCandidato.get(i + offset).setFechaAlta(fechaAlta);
			lista.add(ofertasCandidato.get(i + offset));
		}
		return lista;
	}

	private long calculaCompatibilidad(long idOfertaEmpleo, long idCandidato) {
		long compatibility = 0;

		try {
			EmpresaEspacioDelegate services = EmpresaEspacioDelegateImpl.getInstance();
			compatibility = services.match(idOfertaEmpleo, idCandidato);
		} catch (Exception e) {
			logger.error(e);
		}

		return compatibility;
	}

	@POST
	// Actualizar el estatus de oferta_candidato
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String actualizarEstatusOfertaCandidato(String seguimientoJson, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		HiredRestDTO ofCandidato = gson.fromJson(seguimientoJson, HiredRestDTO.class);

		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			datos.setResult("No se encuentra el Token");
			return gson.toJson(datos);
		}

		for (int i = 0; i < ofCandidato.getOfertasCandidato().size(); i++) {
			try {
				HireRestDTO ofertaCandidato = new HireRestDTO(ofCandidato.getOfertasCandidato().get(i));
				Boolean contratacion= false;
		    	int status = ofertaCandidato.getEstatus();
				if (ofertaCandidato.getFechaContratacion()!=null)
					{contratacion = true;}
				
				OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();
				OfertaCandidatoVO ofertaCanVO = services.findById(Long.valueOf(ofertaCandidato.getIdOfertaCandidato()));

				if (ofertaCanVO == null) {
					datos.setResult("No existe relacion entre la oferta y el candidato");
					return gson.toJson(datos);
				}
				//revisar los campos por nueva regla de negocio en base de datos 
				/*1) si se actualiza el campo OFERTA_CANDIDATO.ESTATUS  a alguno de los valores 20, 21, 22, entonces hay que informar los campos:

					ID_OFICINA_SEGUIMIENTO [oficina del PE, me parece que es la idOficina=10000 verificar esto]
					ID_USUARIO_SEGUIMIENTO [idUsuarioEmpresa ]
					FUENTE_SEGUIMIENTO [PE (1)]*/
				

				
				int enproceso = mx.gob.stps.portal.utils.Catalogos.ESTATUS.EN_PROCESO.getIdOpcion();
				int contratado = mx.gob.stps.portal.utils.Catalogos.ESTATUS.CONTRATADO.getIdOpcion();
				int no_aceptado = mx.gob.stps.portal.utils.Catalogos.ESTATUS.NO_ACEPTADO.getIdOpcion();
				if(enproceso == status || contratado == status || no_aceptado == status){
					MovilSessionVO movilSession = new MovilSessionVO();
					// validar el cookie para obtener el idUsuario
					if (!cookie.equals("0")) {
						movilSession = CookieSession.decodeValue(cookie);
						long idUsuario = movilSession.getIdUsuario();
						ofertaCanVO.setIdUsuarioSeguimiento(idUsuario);
					}
					ofertaCanVO.setIdOficinaSeguimiento(10000L);
					
					ofertaCanVO.setIdOficinaSeguimiento(1L);
					
				}

				
				// CANDIDATO CONTRATADO
				if (contratacion && status == ESTATUS.CONTRATADO.getIdOpcion() && ofertaCanVO.getEstatus() == ESTATUS.EN_PROCESO.getIdOpcion()){

					//estatus = contratado
					ofertaCanVO.setEstatus(status);
					
				/** se formatea la fecha de contratacion a persistir*/
					DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//					DateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
					DateFormat formatter2 = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
					Date date = new Date();
					String dateFormat;
					Date fechaContratacion = new Date();

					try {
						date = (Date) formatter.parse(ofertaCandidato.getFechaContratacion());
						dateFormat = formatter2.format(date);
						fechaContratacion = (Date) formatter2.parse(dateFormat);
						
						ofertaCanVO.setFechaInicioContratacion(fechaContratacion);

						} catch (ParseException e) {
							e.printStackTrace();
							logger.error(e);
							datos.setResult(e.getMessage());
							return gson.toJson(datos);
						}
				
					 services.actualizaPostulacionOferta(ofertaCanVO);
					
					/* Se manda la notificacion de Contratacion**/
						enviarCorreo(ofertaCandidato, contratacion);

				//CANDIDATO NO ACEPTADO
				} else if(!contratacion && status == ESTATUS.NO_ACEPTADO.getIdOpcion() && ofertaCandidato.getIdMotivo() != 0
						&& ofertaCanVO.getEstatus() == ESTATUS.EN_PROCESO.getIdOpcion()){
					
					//estatus final = No_aceptado
					ofertaCanVO.setEstatus(status);
					ofertaCanVO.setIdMotivo(ofertaCandidato.getIdMotivo());

					 services.actualizaPostulacionOferta(ofertaCanVO);
					
					/* Se manda la notificacion de No Contratacion**/
					enviarCorreo(ofertaCandidato, contratacion);

				//CANDIDATO ELIMINADO DE LA LISTA CON ESTATUS EN PROCESO/POSTULADOS
				}else if(status == ESTATUS.NO_ACEPTADO.getIdOpcion() && ofertaCandidato.getIdMotivo() == 0
						&& (ofertaCanVO.getEstatus() == ESTATUS.EN_PROCESO.getIdOpcion() ||
								ofertaCanVO.getEstatus() == ESTATUS.POSTULADO.getIdOpcion())){
								
					//estatus final -> No_aceptado
					ofertaCanVO.setEstatus(status);
			    	services.actualizaPostulacionOferta(ofertaCanVO);

			    //CANDIDATO ELIMINADO DE LA LISTA con estatus VINCULADO
				}else if(status == ESTATUS.DESVNCULADO.getIdOpcion()
						&& ofertaCanVO.getEstatus() == ESTATUS.VINCULADO.getIdOpcion()){
						
					//estatus final -> Desvinculado
					ofertaCanVO.setEstatus(status);
					services.actualizaPostulacionOferta(ofertaCanVO);

				//CANDIDATO ELIMINADO DE LA LISTA con estatus SELECCIONADO/SELECCIONADA
				}else if(status == 0
						&& (ofertaCanVO.getEstatus() == ESTATUS.SELECCIONADO.getIdOpcion() ||
							ofertaCanVO.getEstatus() == ESTATUS.SELECCIONADA.getIdOpcion())){
						
					//ternima la asociacion con la oferta
					services.eliminaAsociacionConOferta(ofertaCanVO.getIdOfertaCandidato());
				}
				else{
					datos.setResult("Datos inválidos");
					return gson.toJson(datos);
				}

			} catch (Exception se) {
				se.printStackTrace();
				logger.error(se);
				datos.setResult(se.getMessage());
				return gson.toJson(datos);
			}
		}

		return gson.toJson(datos);

	}

	private void enviarCorreo(HireRestDTO ofertaCandidato, Boolean contratacion) {
		CandidatoDelegateImpl services = CandidatoDelegateImpl.getInstance();
		String email = ofertaCandidato.getCorreoElectronico();
		String nombre = ofertaCandidato.getNombreEmpresa();
		String tituloOferta = ofertaCandidato.getTituloOferta();
		String tipoPersona = ofertaCandidato.getTipoPersona();

		try {
		services.notificarCandidato(email, nombre, tituloOferta, contratacion, tipoPersona);
		} catch (MailException e) {
			e.printStackTrace();
			logger.error(e);			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);			
		}
	}
	

}
