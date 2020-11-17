package mx.gob.stps.portal.movil.app.empresa.service;

import java.math.BigDecimal;
import java.sql.SQLException;
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
import javax.persistence.PersistenceException;
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

import mx.gob.stps.portal.core.candidate.vo.CandidatoVo;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.oferta.registro.vo.OfertaCarreraEspecialidadVO;
import mx.gob.stps.portal.core.oferta.vo.OfertaEmpleoVO;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.movil.app.empresa.model.base.BusquedaCandidatoDTO;
import mx.gob.stps.portal.movil.app.empresa.model.rest.BusquedaCandidatoRestDTO;
import mx.gob.stps.portal.movil.app.empresa.model.rest.OfertaCandidatoDTO;
import mx.gob.stps.portal.movil.app.empresa.model.rest.OfertaEmpresaAdminRestDTO;
import mx.gob.stps.portal.movil.app.empresa.model.rest.OfertaRestDTO;
import mx.gob.stps.portal.movil.app.glasses.cookies.CookieSession;
import mx.gob.stps.portal.movil.app.model.base.BaseRestDTO;
import mx.gob.stps.portal.movil.app.model.base.BusquedaOfertaDTO;
import mx.gob.stps.portal.movil.app.model.rest.BusquedaOfertaRestDTO;
import mx.gob.stps.portal.movil.app.model.rest.OfertaMinimoRestDTO;
import mx.gob.stps.portal.movil.app.service.util.CandidatosRelacionadosComparador;
import mx.gob.stps.portal.movil.app.service.util.OfertaCandidatoComparadorFechaPostulacion;
import mx.gob.stps.portal.movil.app.service.util.OfertaUtil;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegate;
import mx.gob.stps.portal.movil.web.empresa.delegate.EmpresaEspacioDelegateImpl;
import mx.gob.stps.portal.movil.web.empresa.vo.OfertasEmpresaVO;
import mx.gob.stps.portal.movil.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.movil.web.infra.utils.ConstantesMovil.TIPOACTION;
import mx.gob.stps.portal.movil.web.oferta.delegate.OfertaDelegateImpl;
import mx.gob.stps.portal.persistencia.vo.OfertaCandidatoOcupacionDTO;
import mx.gob.stps.portal.persistencia.vo.OfertaEmpresaAdminDTO;
import mx.gob.stps.portal.persistencia.vo.TelefonoVO;
import mx.gob.stps.portal.seguridad.vo.MovilSessionVO;
import mx.gob.stps.portal.utils.Catalogos.ESTATUS;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

@Stateless
@Path("/empresaAdministrarOfertas")
public class AdministrarOfertas {

	private Gson gson = new Gson();
	private static Logger logger = Logger.getLogger(AdministrarOfertas.class);
	private BusquedaCandidatoRestDTO busquedaCandidato = new BusquedaCandidatoRestDTO();
	private TIPOACTION tipoAction;
	private EmpresaVO empresaVO;

	@GET
	// The Java method will produce content identified by the MIME Media
	// type "text/plain"
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{tipoConsulta}/{pagina}/{resultadoPorPagina}/{idOferta}")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String buscarCandidatos(
			@DefaultValue("0") @PathParam("tipoConsulta") final String tipoConsulta,
			@DefaultValue("0") @PathParam("pagina") final String paginaStr,
			@DefaultValue("10") @PathParam("resultadoPorPagina") final String resultadoPorPaginaStr,
			@DefaultValue("0") @PathParam("idOferta") final String idOfertaStr,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		//para vaciar el objeto del ultimo llamada
		busquedaCandidato = new BusquedaCandidatoRestDTO();
		// then you can access the request/response/session etc in your methods
		
		
		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			busquedaCandidato.setResult("No se encuentra el Token");
			return gson.toJson(busquedaCandidato);
		}
		List<Long> indices = new ArrayList<Long>();
		Long idEmpresa = 0L;

		String query = null;
		if (tipoConsulta.equals("0")) {
			busquedaCandidato.setResult("Tipo de consulta incorrecto");
			return gson.toJson(busquedaCandidato);
		}

		MovilSessionVO movilSession = CookieSession.decodeValue(cookie);
		idEmpresa = movilSession.getIdEmpresa();

		int tipo = Integer.parseInt(tipoConsulta);
		OfertaEmpresaAdminRestDTO ofertas = new OfertaEmpresaAdminRestDTO();

		List<ESTATUS> candidatoEstatus = new ArrayList<ESTATUS>();
		List<ESTATUS> ofertaEstatus = new ArrayList<ESTATUS>();
		
		switch (tipo) {
		// lista de ofertas por empresa
		case 1:
			tipoAction = TIPOACTION.LISTAOFERTAS;
			List<OfertaEmpresaAdminDTO> lista;
			List<CandidatoVo> candidatos = new ArrayList<CandidatoVo>();
			try {
				lista = getListaOferatasEmprasa(idEmpresa);
				if (lista == null) {
//					ofertas.setError("No se encontraron ofertas activas para su empresa");
					return gson.toJson(ofertas);
				}
			} catch (PersistenceException e) {
				ofertas.setResult(e.getMessage());
				return gson.toJson(ofertas);
			} catch (ServiceLocatorException e) {
				ofertas.setResult(e.getMessage());
				return gson.toJson(ofertas);
			} catch (NumberFormatException e) {
				ofertas.setResult(e.getMessage());
				return gson.toJson(ofertas);
			} catch (Exception e) {
				ofertas.setResult(e.getMessage());
				return gson.toJson(ofertas);
			}

			ofertas.setTotalRegistros(lista.size());
			ofertas.setOfertas(lista);
			limitListaOfertaEmpresaAdmin(ofertas, paginaStr,resultadoPorPaginaStr);
			// cambio por la tardancia del servicio del indexador
	/*		try {
				for(OfertaEmpresaAdminDTO oferta : ofertas.getOfertas()){
					
						candidatos = IndexerServiceLocator.getIndexadorServiceRemote().busquedaCandidatos(Long.valueOf(oferta.getIdOfertaEmpleo()));
					if(candidatos != null && candidatos.size()>0){
						
						oferta.addListEstatusCandidato(ESTATUS.RELACIONADO, candidatos.size());
					}
				}
			} catch (NumberFormatException e) {
					ofertas.setError(e.getMessage());
					return gson.toJson(ofertas);
			} catch (Exception e) {
					ofertas.setError(e.getMessage());
					return gson.toJson(ofertas);
			}
		
*/
			return gson.toJson(ofertas);
			//regresar los candidatos relacionados con la oferta
		case 11:
			
			OfertaMinimoRestDTO oferta = new OfertaMinimoRestDTO();
			List<Long[]> candidats = new ArrayList<Long[]>();
			oferta.setIdOfertaEmpleo(idOfertaStr);
			EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl
					.getInstance();
				
			try {
				candidats = service.busquedaAsistidaIdCandidatos(Long.valueOf(idOfertaStr));
			
				
				if(candidats != null && candidats.size()>0){
					
					oferta.addListEstatusCandidato(ESTATUS.RELACIONADO, candidats.size());
				}
			} catch (NumberFormatException e) {
				

				ofertas.setResult(e.getMessage());
				return gson.toJson(ofertas);
			} catch (Exception e) {
				
				ofertas.setResult(e.getMessage());
				return gson.toJson(ofertas);
			}
			
			oferta.setResult("OK");
			return gson.toJson(oferta);
		case 2:
			tipoAction = TIPOACTION.DETALLEOFERTA;
			BusquedaOfertaRestDTO ofertaDetail = new BusquedaOfertaRestDTO();
			BusquedaOfertaDTO ofertaEmpresa = new BusquedaOfertaDTO();
			List<BusquedaOfertaDTO> list = new ArrayList<BusquedaOfertaDTO>();

			ofertaEmpresa = detailOferta(Long.valueOf(idOfertaStr), idEmpresa);
			list.add(ofertaEmpresa);
			ofertaDetail.setTamano(list.size());
			ofertaDetail.setOfertas(list);
			return gson.toJson(ofertaDetail);

		case 3:
			tipoAction = TIPOACTION.CANDIDATOSPOSTULADO;

			candidatoEstatus.add(ESTATUS.POSTULADO);
			//
			ofertaEstatus.add(ESTATUS.ACTIVO);
			try {
				return gson.toJson(buscarOfertaCandidatoConEstatus(
						candidatoEstatus, idEmpresa, Long.valueOf(idOfertaStr),
						paginaStr, resultadoPorPaginaStr, ofertaEstatus));

			} catch (NumberFormatException e) {
				busquedaCandidato.setResult(e.getMessage());
			} catch (ServiceLocatorException e) {
				busquedaCandidato.setResult(e.getMessage());
			}
			return gson.toJson(busquedaCandidato);				

		// candidatos relacionados
		case 4:
			tipoAction = TIPOACTION.CANDIDATOSRELACIONADOS;
			try {
				OfertaCandidatoDTO candidatosOfertas = buscarCandidatosRelacionados(
						Long.valueOf(idOfertaStr), idEmpresa,
						Integer.valueOf(paginaStr),
						Integer.valueOf(resultadoPorPaginaStr));
				String result = gson.toJson(candidatosOfertas);
				return result;
				
				//this.busquedaCandidato.setOfertaCandidato(candidatosOfertas.getOfertaCandidato());
				//this.busquedaCandidato.setTamano(candidatosOfertas.getTamano());
				
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (PersistenceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TechnicalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServiceLocatorException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//return gson.toJson(this.busquedaCandidato);
			
			
			
		case 5:
			tipoAction = TIPOACTION.CANDIDATOSSELECIONADOSVINCULADOS;

			candidatoEstatus.add(ESTATUS.SELECCIONADA);
			candidatoEstatus.add(ESTATUS.VINCULADO);
			candidatoEstatus.add(ESTATUS.SELECCIONADO);
			//
			ofertaEstatus.add(ESTATUS.ACTIVO);
			
			try {
				return gson.toJson(buscarOfertaCandidatoConEstatus(
						candidatoEstatus, idEmpresa, Long.valueOf(idOfertaStr),
						paginaStr, resultadoPorPaginaStr, ofertaEstatus));

			} catch (NumberFormatException e) {
				busquedaCandidato.setResult(e.getMessage());
			} catch (ServiceLocatorException e) {
				busquedaCandidato.setResult(e.getMessage());
			}
			break;

		default:
			busquedaCandidato.setResult("revisa los instrucciones de la api");

			break;
		}

		return gson.toJson(busquedaCandidato);

	}
	
	
	@POST
	// Desactiva/elimina oferta
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String desactivarOferta(String seguimientoJson, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		OfertaRestDTO oferta = gson.fromJson(seguimientoJson, OfertaRestDTO.class);
		BaseRestDTO datos = new BaseRestDTO();

		String cookie = request.getHeader(CookieSession.NAME);
		if (cookie == null) {
			datos.setResult("No se encuentra el Token");
			return gson.toJson(datos);
		}
		Long idOfertaEmpleo = Long.valueOf(oferta.getIdOferta());
    	int status = oferta.getEstatus();
		Long idUsuario = 0L;    	
		OfertaDelegateImpl services = OfertaDelegateImpl.getInstance();

		try {
			MovilSessionVO movilSession = new MovilSessionVO();

			// validar el cookie para obtener el idUsuario
			if (!cookie.equals("0")) {
				movilSession = CookieSession.decodeValue(cookie);
				idUsuario = movilSession.getIdUsuario();
			}
				
		//SE DESACTIVA LA OFERTA 
		//estatus => Cancelada
		if (status == ESTATUS.CANCELADA.getIdOpcion()){
			services.cancelaOfertaEmpleo(idOfertaEmpleo, idUsuario);
			}
		
		//SE ELIMINA LA OFERTA 
		//estatus => Eliminada por Empresa
		else if (status == ESTATUS.ELIMINADA_EMP.getIdOpcion()){
			services.eliminaOfertaPorEmpresa(idOfertaEmpleo, idUsuario);
	    }
		
	    else{
	    	datos.setResult("Datos inválidos");
			return gson.toJson(datos);
	    	}
		
		} catch (Exception se) {
		se.printStackTrace();
		logger.error(se);
		datos.setResult(se.getMessage());
		}

	return gson.toJson(datos);

	}
	

	private OfertaCandidatoDTO buscarCandidatosRelacionados(
			Long idOfertaEmpleo, Long idEmpresa, int pagina, int porPagina) throws PersistenceException, SQLException, TechnicalException, ServiceLocatorException {
		OfertaCandidatoDTO resultadosBusqueda = new OfertaCandidatoDTO();
		
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl
				.getInstance();
			//List<Long[]> candidatosId = service.busquedaAsistidaIdCandidatos(idOfertaEmpleo);
			List<CandidatoVo> candidatos = new ArrayList<CandidatoVo>();
			try {
				//candidatos = service.busquedaCandidatos(idOfertaEmpleo);
				candidatos = service.busquedaAsistidaCandidatos(idOfertaEmpleo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<CandidatoVo> candidatosPaginador = new ArrayList<CandidatoVo>();
			if (candidatos == null || candidatos.isEmpty()) {
//				resultadosBusqueda.setError("No existen candidatos");
				return resultadosBusqueda;
			}
			if (candidatos != null && candidatos.size() > 0) {
				/* se ordena la lista deacuerdo a la compatibilidad */
				Collections.sort(candidatos, new CandidatosRelacionadosComparador());
				
				if (candidatos.size() < ((pagina + 1) * porPagina)) {
					if((pagina * porPagina)>candidatos.size()){
						candidatosPaginador = new ArrayList<CandidatoVo>();
					}
					else
					{
					candidatosPaginador = candidatos.subList(
							pagina * porPagina, candidatos.size());
					}
				} else {
					candidatosPaginador = candidatos.subList(
							pagina * porPagina, ((pagina + 1) * porPagina));
				}
			}

			
			for (int i = 0; i < candidatosPaginador.size(); i++) {
				
				OfertaCandidatoOcupacionDTO candidatoRest = new OfertaCandidatoOcupacionDTO();
				CandidatoVo candidato = candidatosPaginador.get(i);
				BuscarCandidatosEmpresa buscarCandidatos = new BuscarCandidatosEmpresa();
				BusquedaCandidatoDTO busquedaCandidatoDto = buscarCandidatos.detalleCandidato(idOfertaEmpleo, candidato.getIdCandidato());
				// todo ver como consigier ofertaEmpleo
				//candidatoRest.setIdOfertaEmpleo(busquedaCandidatoDto)
				//candidatoRest.setTituloOferta():
				
				candidatoRest.setIdOfertaEmpleo(String.valueOf(idOfertaEmpleo));
				candidatoRest.setIdCandidato(String.valueOf(candidato
						.getIdCandidato()));
				// todo los candidatos en el servicio de indexador son activos
				candidatoRest.setEstatus(ESTATUS.getDescripcion(1));
				candidatoRest.setCompatibilidad(String.valueOf(candidato
						.getCompatibilidad()));
				candidatoRest.setIdEmpresa(String.valueOf(idEmpresa));
				candidatoRest.setNombre(candidato.getNombre());
				candidatoRest.setApelidoPaterno(candidato.getApellido1());
				candidatoRest.setApelidoMaterno(candidato.getApellido2());
				
				candidatoRest.setEntidad(candidato.getDomicilioVo().getEntidad());
				candidatoRest.setMunicipio(candidato.getDomicilioVo().getMunicipio());
						
				
				candidatoRest.setOcupacionCandidato(candidato.getOcupacion());
				candidatoRest.setEstatusCandidato(Constantes.ESTATUS.getDescripcion((busquedaCandidatoDto.getEstatus())));

				resultadosBusqueda.getOfertaCandidato().add(candidatoRest);
				
			}
			
			resultadosBusqueda.setTamano(candidatos.size());
		

		return resultadosBusqueda;

	}

	private BusquedaCandidatoRestDTO buscarOfertaCandidatoConEstatus(
			List<ESTATUS> candidatoEstatus, Long idEmpresa, Long idOferta,
			String paginaStr, String resultadoPorPaginaStr, List<ESTATUS> ofertaEstatus)
			throws ServiceLocatorException {
		BusquedaCandidatoRestDTO estatusOfertas = new BusquedaCandidatoRestDTO();
		List<OfertaCandidatoOcupacionDTO> ofertasCandidato = new ArrayList<OfertaCandidatoOcupacionDTO>();
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl
				.getInstance();

		ofertasCandidato = service.obtenerOfertaCandidatoEmpresaPorEstatus(
				idEmpresa, candidatoEstatus, idOferta, ofertaEstatus);
		if (ofertasCandidato == null || ofertasCandidato.isEmpty()) {
			//estatusOfertas.setError("No existen candidatos");
			return estatusOfertas;
		}
		/* se calcula compatibilidad */
		for (int i = 0; i < ofertasCandidato.size(); i++) {
          if (ofertasCandidato.get(i).getCompatibilidad()==null){
        	  long compatibility = calculaCompatibilidad(
  					Long.valueOf(ofertasCandidato.get(i).getIdOfertaEmpleo()),
  					Long.valueOf(ofertasCandidato.get(i).getIdCandidato()));
        	  //
        	  ofertasCandidato.get(i).setCompatibilidad(
  					String.valueOf(compatibility));
			}
          if(Integer.valueOf(ofertasCandidato.get(i).getCompatibilidad()) <50){
        	  long compatibility = calculaCompatibilidad(
    					Long.valueOf(ofertasCandidato.get(i).getIdOfertaEmpleo()),
    					Long.valueOf(ofertasCandidato.get(i).getIdCandidato()));
          	  //
          	  ofertasCandidato.get(i).setCompatibilidad(
    					String.valueOf(compatibility));
				}	

		}

		/* se ordena la lista deacuerdo a la compatibilidad */
		Collections.sort(ofertasCandidato,
				new OfertaCandidatoComparadorFechaPostulacion());

		estatusOfertas.setTamano(ofertasCandidato.size());
		ofertasCandidato = limitarBusquedaCandidato(paginaStr,
				resultadoPorPaginaStr, ofertasCandidato);
		estatusOfertas.setOfertaCandidato(ofertasCandidato);
		return estatusOfertas;
	}

	private void limitListaOfertaEmpresaAdmin(
			OfertaEmpresaAdminRestDTO ofertas, String paginaStr,
			String resultadoPorPaginaStr) {

		int pagina = Integer.valueOf(paginaStr);
		int resultadoPorPagina = Integer.valueOf(resultadoPorPaginaStr);
		int totalPaginas = 1;

		if (resultadoPorPagina != 0) {
			totalPaginas = (int) Math
					.round((ofertas.getTotalRegistros() / resultadoPorPagina));
			ofertas.setTotalPaginas(totalPaginas);
		}
		if ((pagina + 1) * resultadoPorPagina < ofertas.getOfertas().size()) {
			ofertas.setOfertas(ofertas.getOfertas().subList(
					pagina * resultadoPorPagina,
					((pagina + 1) * resultadoPorPagina)));
		} else {
			if ((pagina) * resultadoPorPagina >= ofertas.getOfertas().size()) {
				ofertas.setOfertas(new ArrayList<OfertaEmpresaAdminDTO>());
			} else {
				ofertas.setOfertas(ofertas.getOfertas().subList(
						pagina * resultadoPorPagina,
						ofertas.getOfertas().size()));
			}
		}
		/*
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl
				.getInstance();
		try {
			for(OfertaEmpresaAdminDTO oferta : ofertas.getOfertas()){
					List<CandidatoVo> candidatos = service.busquedaAsistidaCandidatos(Long.valueOf(oferta.getIdOfertaEmpleo()));
					oferta.addListEstatusCandidato(ESTATUS.RELACIONADO, candidatos.size());
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TechnicalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

	}

	private List<OfertaEmpresaAdminDTO> getListaOferatasEmprasa(Long idEmpresa)
			throws PersistenceException, ServiceLocatorException {
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl
				.getInstance();
		// Estatus de los candidatos
		List<ESTATUS> estatus = new ArrayList<ESTATUS>();
		estatus.add(ESTATUS.POSTULADO);
		estatus.add(ESTATUS.SELECCIONADO);
		estatus.add(ESTATUS.SELECCIONADA);		
		estatus.add(ESTATUS.VINCULADO);
		// Estatus de la oferta
		List<ESTATUS> ofertaEstatus = new ArrayList<ESTATUS>();
		ofertaEstatus.add(ESTATUS.ACTIVO);

		List<OfertaEmpresaAdminDTO> ofertas = service.obtenerOfertasEmpresa(
				idEmpresa, estatus, ofertaEstatus);

		return ofertas;

	}

	private List<OfertasEmpresaVO> generaListaOfertas(
			List<OfertaEmpleoVO> ofertas) {
		List<OfertasEmpresaVO> listaOfertas = new ArrayList<OfertasEmpresaVO>();
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl
				.getInstance();

		for (OfertaEmpleoVO oferta : ofertas) {
			try {
				oferta.setUbicaciones(service.getUbicacionesOferta(oferta
						.getIdOfertaEmpleo()));
				OfertasEmpresaVO ofertaVO = new OfertasEmpresaVO();
				OfertaCarreraEspecialidadVO carr = oferta.getCarreraPrincipal();
				ofertaVO.setIdOfertaEmpleo(oferta.getIdOfertaEmpleo());
				ofertaVO.setTituloOferta(oferta.getTituloOferta());
				ofertaVO.setOcupacion(service.getCatalogoOpcionById(
						Constantes.CATALOGO_OPCION_OCUPACION,
						oferta.getIdOcupacion()));
				ofertaVO.setNivelEstudios(service.getCatalogoOpcionById(
						Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS,
						oferta.getIdNivelEstudio()));
				ofertaVO.setCarrera(obtieneCarrera(carr));
				ofertaVO.setUbicacion(oferta.getUbicaciones().get(0)
						.getEntidad()
						+ "/" + oferta.getUbicaciones().get(0).getMunicipio());
				ofertaVO.setEstatus(oferta.getEstatus());
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
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl
				.getInstance();
		String carrera = "";
		logger.info("Carrera " + carr.getId());
		if (carr != null) {
			try {
				carrera = service.getCatalogoOpcionById(
						Constantes.CATALOGO_OPCION_CARRERAS_1, carr.getId());
				if ((carrera == null) || ("".equals(carrera)))
					carrera = service
							.getCatalogoOpcionById(
									Constantes.CATALOGO_OPCION_CARRERAS_2,
									carr.getId());
				if ((carrera == null) || ("".equals(carrera)))
					carrera = service
							.getCatalogoOpcionById(
									Constantes.CATALOGO_OPCION_CARRERAS_3,
									carr.getId());
				if ((carrera == null) || ("".equals(carrera)))
					carrera = service
							.getCatalogoOpcionById(
									Constantes.CATALOGO_OPCION_CARRERAS_4,
									carr.getId());

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

	private List<OfertaCandidatoOcupacionDTO> limitarBusquedaCandidato(
			String paginaStr, String resultadoPorPaginaStr,
			List<OfertaCandidatoOcupacionDTO> ofertasCandidato) {

		List<OfertaCandidatoOcupacionDTO> lista = new ArrayList<OfertaCandidatoOcupacionDTO>();

		// obtener cuanto resultados debemos regresar
		int resultados = Integer.valueOf(resultadoPorPaginaStr);
		// desde cual resultado regresar
		int offset = Integer.valueOf(paginaStr) * resultados;

		for (int i = 0; i < resultados
				&& (i + offset) < ofertasCandidato.size(); i++) {
			lista.add(ofertasCandidato.get(i + offset));
		}

		return lista;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
	
	private BusquedaOfertaDTO detailOferta(Long idOfertaEmpleo, Long idEmpresa) {

		BusquedaOfertaDTO oferta = OfertaUtil.busquedaDetalle(idOfertaEmpleo);
		oferta.getOferta().setTelefonos(new ArrayList<TelefonoVO>());

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",
				Locale.US);

		Date date = new Date();
		try {
			date = (Date) formatter.parse(oferta.getOferta().getFechaAlta());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oferta.getOferta().setFechaFin(
				OfertaUtil.changeDate(oferta.getOferta().getFechaFin(),
						"E MMM dd HH:mm:ss z yyyy",
						"dd 'de' MMMM 'de' yyyy"));
		String salarioSinFormato = oferta.getOferta().getSalario();
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		oferta.getOferta().setSalario(nf.format(new BigDecimal(salarioSinFormato)));
		
		DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
		oferta.setFechaAlta(formatter2.format(date));

		empresaVO=buscarDatosEmpresa(idEmpresa);
		oferta.setDomicilio(empresaVO.getDomicilio());

		return oferta;
	}
	
	private EmpresaVO buscarDatosEmpresa(Long idEmpresa){
		empresaVO = new EmpresaVO();
		EmpresaEspacioDelegate service = EmpresaEspacioDelegateImpl.getInstance();
		try {
			empresaVO = service.findEmpresaById(idEmpresa);
		} catch (Exception se) {
			se.printStackTrace();
			logger.error(se);
		}
		return empresaVO;		
	}

}
