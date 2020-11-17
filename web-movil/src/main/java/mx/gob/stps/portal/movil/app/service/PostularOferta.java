package mx.gob.stps.portal.movil.app.service;

import java.util.Calendar;
import java.util.List;

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

import mx.gob.stps.portal.core.infra.utils.Constantes.ESTATUS;
import mx.gob.stps.portal.core.infra.utils.Constantes.EVENTO;
import mx.gob.stps.portal.core.infra.utils.Constantes.TIPO_USUARIO;
import mx.gob.stps.portal.core.oferta.detalle.vo.OfertaCandidatoVO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;
import mx.gob.stps.portal.movil.app.model.rest.PostulateRestDTO;
import mx.gob.stps.portal.movil.app.model.rest.PostulatedRestDTO;
import mx.gob.stps.portal.movil.app.service.util.OfertaUtil;
import mx.gob.stps.portal.movil.web.candidato.util.UtilPostulate;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;
import mx.gob.stps.portal.utils.Catalogos.TIPO_PROPIETARIO;
import mx.gob.stps.portal.utils.ConstantesGenerales;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

@Stateless
@Path("/postularOferta")
public class PostularOferta {

	private Gson gson = new Gson();

	private static Logger logger = Logger.getLogger(PostularOferta.class);

	
	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idOferta}")
	public String postulateOfertaCandidato(
			@DefaultValue("-1") @PathParam("idOferta") final String idOferta,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		Long idCandidato = 0L;
		boolean guardado = false;
		Long idUsuario = 0L;
		Long idOfertaEmpleo = Long.valueOf(idOferta);
		String mensaje = "OK";
		Integer location = null;
		PostulateRestDTO datos = new PostulateRestDTO();
		String query = null;
		String cookie = request.getHeader(CookieSession.NAME);
		if(cookie == null){
			datos.setResult("No tiene datos del Candidato. Inicio Session.");
			return gson.toJson(datos);
		}
		if (null == query)
			query = "";
		logger.info(query);
		logger.info("place " + location);
		
		try {
			// validar el cookie para obtener el candidato
			MovilSessionVO movilSession = new MovilSessionVO();
			if (!cookie.equals("0")) {
				movilSession = CookieSession.decodeValue(cookie);
				idCandidato = movilSession.getIdCandidato();
				idUsuario = movilSession.getIdUsuario();
			}
			
			/*Se checa que la oferta siga activa*/
			BusquedaOfertaDTO ofertadetail = OfertaUtil.busquedaDetalle(idOfertaEmpleo);
			if(ofertadetail.getOferta().getOferta().getEstatus() != 1){
				datos.setResult("La oferta ya no se encuentra activa");
				return gson.toJson(datos);				
			}

			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();

			List<OfertaCandidatoVO> ofertasPostuladas = services
					.findByOfferCandidate(idOfertaEmpleo, idCandidato);
			boolean postulado = false;
			ESTATUS estatusPostulado = ESTATUS.POSTULADO; 
			if (ofertasPostuladas == null || ofertasPostuladas.isEmpty()) {
				OfertaCandidatoVO oferta = new OfertaCandidatoVO();
				oferta.setFechaAlta(Calendar.getInstance().getTime());
				oferta.setIdCandidato(idCandidato);
				oferta.setIdOfertaEmpleo(idOfertaEmpleo);
				oferta.setEstatus(ESTATUS.POSTULADO.getIdOpcion());
				oferta.setIdUsuario(movilSession.getIdUsuario());
				//poner officina fija por el portal de empleo porque aqui no tenemos la oficina de la empresa
				oferta.setIdOficina(ConstantesGenerales.PORTAL_ID_OFICINA);
				oferta.setPostulacionCartera(0);
				oferta.setIdFuente(1L);
				
				
				oferta.setIdUsuario(idUsuario);
				//para la bitacora
				if(TIPO_USUARIO.EMPRESA.getIdTipoUsuario() == movilSession.getPerfil()){
					oferta.setTipoPropietario(TIPO_PROPIETARIO.EMPRESA);
				}
				else
				{
					if(TIPO_USUARIO.CANDIDATO.getIdTipoUsuario() == movilSession.getPerfil()){
						oferta.setTipoPropietario(TIPO_PROPIETARIO.CANDIDATO);
						oferta.setEvento(EVENTO.REGISTRO_DE_POSTULACION);
					}
					else
					{
						datos.setResult("Tipo Usuario incorrecto");
						return gson.toJson(datos);
					}
					
				}
				
				
				
				services.postulaOferta(oferta);
				guardado = true;

			} else {
				for (OfertaCandidatoVO oferta : ofertasPostuladas) {
					if(oferta.getEstatus() == ESTATUS.POSTULADO.getIdOpcion() || oferta.getEstatus() == ESTATUS.ELIMINADA_ADMIN.getIdOpcion()){
						postulado= true;
						if(oferta.getEstatus() == ESTATUS.POSTULADO.getIdOpcion()){
							estatusPostulado = ESTATUS.POSTULADO;
						}
						else
						{
							estatusPostulado = ESTATUS.ELIMINADA_ADMIN;
						}
					}
					else
					if (oferta.getEstatus() == ESTATUS.SELECCIONADA.getIdOpcion() || 
							oferta.getEstatus() == ESTATUS.SELECCIONADO.getIdOpcion() ||
									oferta.getEstatus() == ESTATUS.VINCULADO.getIdOpcion()) {
						oferta.setEstatus(ESTATUS.POSTULADO.getIdOpcion());
						services.actualizaPostulacionOferta(oferta);
						guardado = true;
					}
					
					
				}
			}

			if(guardado){
				mensaje = "Ha sido postulado a la oferta.";
				UtilPostulate.increasePostCount(idOfertaEmpleo);
			}
			else
			{
				mensaje = "Error en la postulacion de la oferta.";
			}
			
			
			if(!guardado){
				datos = new PostulateRestDTO();
				
				if(!postulado){
					datos.setResult("No se ha postulado su cv");
					//datos.setError("OK");
				}
				else
				{
					datos.setResult("OK");
					if(estatusPostulado.equals(ESTATUS.POSTULADO)){
						datos.setResult("Ya te postulaste para este oferta.");
					}
					else
					{
						datos.setResult(estatusPostulado.getOpcion());
					}
					datos.setIdPostulated(String.valueOf(UtilPostulate.getIdPostulated(idOfertaEmpleo, idCandidato)));
				}
				return gson.toJson(datos);
			}
			datos = UtilPostulate.avisoCandidatoPostuladoActionSinRequest(
					idOfertaEmpleo, request);
			
			if(datos == null){
				datos = new PostulateRestDTO();
				datos.setResult(mensaje);
			}
			else
			{
				datos.setIdPostulated(String.valueOf(UtilPostulate.getIdPostulated(idOfertaEmpleo, idCandidato)));
			}

		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			datos.setResult(se.getMessage());
			
		}
		
		// Todo agrega los detalles por los ofertas en el rango de la pagina

		return gson.toJson(datos);

	}
	
	
	@POST
	// Actualizar el estatus de oferta_candidato
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String cambioOfertaCandidato(
			String postulatedJson,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		// then you can access the request/response/session etc in your methods
		Long idCandidato = 0L;
		PostulatedRestDTO ofertasCandidato = gson.fromJson(postulatedJson,PostulatedRestDTO.class);
		PostulateRestDTO datos = new PostulateRestDTO();
		

		Integer location = null;
		
		String query = null;
		String cookie = request.getHeader(CookieSession.NAME);
		if(cookie == null){
			datos.setResult("No tiene datos del Candidato. Inicio Session.");
			return gson.toJson(datos);
		}
		if (null == query)
			query = "";
		logger.info(query);
		logger.info("place " + location);
		
		for(int i = 0 ; i<ofertasCandidato.getOfertas().size() ; i++){
		try {
			PostulateRestDTO ofertaCandidato = new PostulateRestDTO(ofertasCandidato.getOfertas().get(i));
			// validar el cookie para obtener el candidato
			if (!cookie.equals("0")) {
				MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
				idCandidato = movilSession.getIdCandidato();
			}

			OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();

			OfertaCandidatoVO ofertaCandidatoVO  = services.findById(Long.valueOf(ofertaCandidato.getIdPostulated()));

			if (ofertaCandidatoVO == null ) {
				datos.setResult("No se han encontrado la relacion entre la oferta y el Candidato");
				return gson.toJson(datos);
			}
			//actualizar el estatus con filter que si es correcto los estatus como se puede actualiar
			/* casos 1) el candidato ya no quere estar postulado por el empleo
					 2) la empresa contacta al candidato
					 3) el candidato se postula despues de la empresa le contacto 
					 */
			boolean isPostulated = OfertaUtil.validateIsPostulated(ofertaCandidatoVO.getEstatus());
			if((isPostulated && ofertaCandidato.getEstatus() == ESTATUS.DESPOSTULADO.getIdOpcion()) ||
					(ofertaCandidatoVO.getEstatus() == ESTATUS.VINCULADO.getIdOpcion() && ofertaCandidato.getEstatus() == ESTATUS.DESVNCULADO.getIdOpcion()) ||
					(ofertaCandidatoVO.getEstatus() == ESTATUS.VINCULADO.getIdOpcion() && ofertaCandidato.getEstatus() == ESTATUS.POSTULADO.getIdOpcion())){
				ofertaCandidatoVO.setEstatus(ofertaCandidato.getEstatus());
				services.actualizaPostulacionOferta(ofertaCandidatoVO);
			}
			else
			{
				datos.setResult("datos para actualizacion del estatus invalidos");
				return gson.toJson(datos);
			}

			
		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
			datos.setResult(se.getMessage());
			return gson.toJson(datos);
		}
		}
		// Todo agrega los detalles por los ofertas en el rango de la pagina

		return gson.toJson(datos);

	}

}
