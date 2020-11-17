package mx.gob.stps.portal.web.offer.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_PROVINCIAS_CANADA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_BUSQUEDAS;
import static mx.gob.stps.portal.web.infra.utils.Constantes.PAIS_OFERTA;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.candidate.form.RegistroCandidatoForm;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.Constantes.RANGOS_EDADES;
import mx.gob.stps.portal.web.infra.utils.Constantes.RANGOS_SALARIOS;
import mx.gob.stps.portal.web.infra.utils.Constantes.REGION;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegate;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegateImpl;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.offer.form.BusquedaEspecificaForm;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SearchSpecificOffers extends PagerSpecificAction {
	
	private static Logger logger = Logger.getLogger(SearchSpecificOffers.class);

	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String search = null;
		List<Long> indices = null;
		Integer entidad = null;
		HttpSession session = request.getSession();
		session.removeAttribute(FULL_LIST);
		session.removeAttribute("SPECIFIC_SEARCH");
		//Parametro enviados por portal INFONAVIT
		String nss =  request.getParameter("nss");
		String credito = request.getParameter("credito");
		String entidadFederativa = request.getParameter("entidadFederativa");
		
		try{
			UsuarioWebVO usuario = getUsuario(request.getSession());
			if (usuario!=null)
				SecutityDelegateImpl.getInstance().busquedaEspecifica(usuario.getIdUsuario(), usuario.getIdPerfil());
			
			
			 //Captura el NSS, crédito y
			 //busca las ofertas de la entidad enviado portal INFONAVIT
			if((nss != null && !nss.trim().isEmpty()) || (credito != null && !credito.trim().isEmpty()) || (entidadFederativa != null && !entidadFederativa.trim().isEmpty())){
				RegistroCandidatoForm registroForm = (RegistroCandidatoForm)form;
				registroForm.reset();
				
				if(nss != null && !nss.trim().isEmpty()){
					registroForm.setNumeroSeguroSocial(nss);
				}
				if(credito != null && !credito.trim().isEmpty()){
					registroForm.setCreditoInfonavit(Utils.parseLong(credito));
				}
				if(entidadFederativa != null && !entidadFederativa.trim().isEmpty()){
					registroForm.setIdEntidad(Integer.parseInt(entidadFederativa));
					entidad = Utils.parseInt(entidadFederativa);
					
					BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
					indices = services.busquedaEspecifica(entidad, 0, 0, 0, -1, -1, -1, 0, 0, null, 0,1);
					search = services.busquedaEspecificaLbl(entidad, 0, 0, 0, -1, -1, -1, 0, 0, "", 1);
					session.removeAttribute(NUM_PAGE_LIST);
					session.setAttribute("SPECIFIC_SEARCH", search);
					session.setAttribute(FULL_LIST, indices);
					session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
					
					Integer totalPlazas = obtenerTotalNumeroPlazas(indices);					
					session.setAttribute("totalNumeroPlazas", totalPlazas);
					
					request.getSession().setAttribute("_urlpageinvoke", "specificsearch.do?method=resultados");
				}	
			}
		}catch(NumberFormatException numberException){
			logger.error("número incorrecto idEntidad = " + numberException);
			indices = new ArrayList<Long>();
			session.removeAttribute(NUM_PAGE_LIST);
			session.setAttribute("SPECIFIC_SEARCH", entidadFederativa);
			session.setAttribute(FULL_LIST, indices);
			session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
			request.getSession().setAttribute("_urlpageinvoke", "specificsearch.do?method=resultados");
		}catch(Exception e) {	e.printStackTrace(); }
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		request.setAttribute("TITULO_CONSULTA", "Búsqueda específica");
		return mapping.findForward(FORWARD_TEMPLATE_BUSQUEDAS);
	}
	
	public Integer obtenerTotalNumeroPlazas(List<Long> indicesOfertas){
		Integer totalPlazas = 0;
		try {
			OfferBusDelegate offerBusDelegate = OfferBusDelegateImpl.getInstance();			
			totalPlazas = offerBusDelegate.contarNumeroPlazasResultados(indicesOfertas);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return totalPlazas;
	}	
	
	/**
	 * Regresa al busqueda especifica 
	 * pero conservando la lista de resultados.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward resultados(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		request.setAttribute("TITULO_CONSULTA", "Búsqueda específica");
		return mapping.findForward(FORWARD_TEMPLATE_BUSQUEDAS);
	}
	
	public ActionForward filterQ(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String search = null;
		List<Long> indices = null;
		HttpSession session = request.getSession();
		//PropertiesLoader properties = PropertiesLoader.getInstance();
		//String urlOfertas = properties.getProperty("ws.portal.busqueda.especifica.ofertas.url");
		//String qString = request.getQueryString();		
		int entidad = Utils.parseInt(request.getParameter("district"));
		int area = Utils.parseInt(request.getParameter("area"));
		int escolaridad = Utils.parseInt(request.getParameter("escolaridad"));
		int salario = Utils.parseInt(request.getParameter("salario"));
		int idMunicipio = Utils.parseInt(request.getParameter("idMunicipio"));
		String[] strOcupaciones = request.getParameterValues("ocupacion");
		String[] strCarreras = request.getParameterValues("carrera");
		List<Integer> idsOcupaciones = new ArrayList<Integer>();
		List<Integer> idsCarreras = new ArrayList<Integer>();
		int edad = Utils.parseInt(request.getParameter("edad"));
		int region = Utils.parseInt(request.getParameter("region"));
		String descripcionCarreraUOcupacionOLA = request.getParameter("descr");
		BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
		try {
			if(null!=strOcupaciones && strOcupaciones.length>0){
				for(int i=0; i<strOcupaciones.length; i++){
					if(null!=strOcupaciones[i])
						idsOcupaciones.add(Utils.parseInt(strOcupaciones[i].toString()));
				}				
			}
			if(null!=strCarreras && strCarreras.length>0){
				for(int i=0; i<strCarreras.length; i++){
					if(null!=strCarreras[i])
						idsCarreras.add(Utils.parseInt(strCarreras[i].toString()));
				}				
			}			
			indices = services.busquedaEspecificaMultiple(entidad, area, escolaridad, salario, idMunicipio, idsOcupaciones, idsCarreras, edad, region);
			search = services.busquedaEspecificaMultipleLbl(entidad, area, escolaridad, salario, idMunicipio, idsOcupaciones, idsCarreras, edad, region,descripcionCarreraUOcupacionOLA );
			Integer totalPlazas = obtenerTotalNumeroPlazas(indices);					
			session.setAttribute("totalNumeroPlazas", totalPlazas);						
			session.removeAttribute(NUM_PAGE_LIST);
			session.setAttribute("SPECIFIC_SEARCH", search);
			session.setAttribute(FULL_LIST, indices);
			session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
		} catch (Exception e) {	e.printStackTrace(); }
		request.getSession().setAttribute("_urlpageinvoke", "specificsearch.do?method=init");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward("JSPWS").getPath());	
		page(mapping, form, request, response); // Primera paginacion
		request.setAttribute("TITULO_CONSULTA", "Búsqueda específica");
		return mapping.findForward(FORWARD_TEMPLATE_BUSQUEDAS);		
	}
	
	public ActionForward filter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String search = null;
		List<Long> indices = null;
		HttpSession session = request.getSession();
		session.removeAttribute("busquedaEspecificaForm");
		BusquedaEspecificaForm busquedaEspecificaForm = new BusquedaEspecificaForm();

		int entidad = Utils.parseInt(request.getParameter("district"));
		int fuente = Utils.parseInt(request.getParameter("fuente"));
		int area = Utils.parseInt(request.getParameter("area"));
		int escolaridad = Utils.parseInt(request.getParameter("escolaridad"));
		int salario = Utils.parseInt(request.getParameter("salario"));
		int idMunicipio = Utils.parseInt(request.getParameter("idMunicipio"));
		int ocupacion = Utils.parseInt(request.getParameter("ocupacion"));
		int carrera = Utils.parseInt(request.getParameter("carrera"));
		int edad = Utils.parseInt(request.getParameter("edad"));
		int region = Utils.parseInt(request.getParameter("region"));
		
		String descripcionCarreraUOcupacionOLA = "";
		
		busquedaEspecificaForm.setEntidad(entidad);
		busquedaEspecificaForm.setArea(area);
		busquedaEspecificaForm.setEscolaridad(escolaridad);
		busquedaEspecificaForm.setSalario(salario);
		busquedaEspecificaForm.setIdMunicipio(idMunicipio);
		busquedaEspecificaForm.setOcupacion(ocupacion);
		busquedaEspecificaForm.setCarrera(carrera);
		busquedaEspecificaForm.setEdad(edad);
		busquedaEspecificaForm.setRegion(region);
		
		busquedaEspecificaForm.setFuente(fuente);
		
		session.setAttribute("busquedaEspecificaForm", busquedaEspecificaForm);
		
		
		BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
		try {
			indices = services.busquedaEspecifica(entidad, area, escolaridad, salario, idMunicipio, ocupacion, carrera, edad, region, null, 0,fuente);
			search = services.busquedaEspecificaLbl(entidad, area, escolaridad, salario, idMunicipio, ocupacion, carrera, edad, region, descripcionCarreraUOcupacionOLA, fuente);
			Integer totalPlazas = obtenerTotalNumeroPlazas(indices);					
			session.setAttribute("totalNumeroPlazas", totalPlazas);
			
			session.removeAttribute(NUM_PAGE_LIST);
			session.setAttribute("SPECIFIC_SEARCH", search);
			session.setAttribute(FULL_LIST, indices);
			session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);			
		} catch (Exception e) {	e.printStackTrace(); }
		request.getSession().setAttribute("_urlpageinvoke", "specificsearch.do?method=resultados");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		request.setAttribute("TITULO_CONSULTA", "Búsqueda específica");
		return mapping.findForward(FORWARD_TEMPLATE_BUSQUEDAS);
	}
	
	/**
	 * Method 'catalogoTiposServicio' 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */		
	public ActionForward filteringSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		int lengthSelect = 0;
		CatalogoOpcionVO vo = new CatalogoOpcionVO();
		vo.setIdCatalogoOpcion(0);
		vo.setOpcion("");
		int option = null != request.getParameter("filter") ? Utils.parseInt(request.getParameter("filter")) : 0;
		switch (option) {
			case 8 : lengthSelect = 12; break;
			case 20 : lengthSelect = 53; break;
			default : lengthSelect = 0;
		}
		long filterOptions [] = {lengthSelect};
		List<CatalogoOpcionVO> options = null;
		try {
			CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();
			options = catalogoOpcionDelegate.consultarCatalogo(option,filterOptions);
			options.add(0, vo);
			CatalogoVO cat = getCatalogo(options);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (Exception e) { logger.error(e); }
		return null;
	}
	
	public ActionForward occupation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String idarea = null != request.getParameter("idAreaLaboral") ? "" + request.getParameter("idAreaLaboral") + "%" : "";
		try {			
			CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();
			List<CatalogoOpcionVO> options = catalogoOpcionDelegate.consultarCatalogo(Constantes.CATALOGO_OPCION_OCUPACION, idarea, false);
			CatalogoVO cat = getCatalogo(options);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}
	
	public ActionForward career(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		int lengthSelect = 1;
		long option = Constantes.CATALOGO_OPCION_SIN_ESPECIALIDAD;
		int id = null != request.getParameter("idEscolaridad") ? Utils.parseInt(request.getParameter("idEscolaridad")) : 0;
		switch (id) {
			case 1 : option = Constantes.CATALOGO_OPCION_SIN_ESPECIALIDAD; lengthSelect = 1; break;
			case 2 : option = Constantes.CATALOGO_OPCION_SIN_ESPECIALIDAD; lengthSelect = 1; break;
			case 3 : option = Constantes.CATALOGO_OPCION_SIN_ESPECIALIDAD; lengthSelect = 1; break;
			case 4 : option = Constantes.CATALOGO_OPCION_SIN_ESPECIALIDAD; lengthSelect = 1; break;
			case 5 : option = Constantes.CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_BASICO; lengthSelect = 304; break;
			case 6 : option = Constantes.CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_SUPERIOR; lengthSelect = 1044; break;
			case 7 : option = Constantes.CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_SUPERIOR; lengthSelect = 1044; break;
			case 8 : option = Constantes.CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_SUPERIOR; lengthSelect = 1044; break;
			case 9 : option = Constantes.CATALOGO_OPCION_CARRERA_TECNICA_COMERCIAL_SUPERIOR; lengthSelect = 1044; break;
			case 10 : option = Constantes.CATALOGO_OPCION_CARRERA_PROFESIONAL; lengthSelect = 1624; break;
			case 11 : option = Constantes.CATALOGO_OPCION_CARRERA_MAESTRIA_DOCTORADO; lengthSelect = 2009; break;
			case 12 : option = Constantes.CATALOGO_OPCION_CARRERA_MAESTRIA_DOCTORADO; lengthSelect = 2009; break;
			default : option = Constantes.CATALOGO_OPCION_SIN_ESPECIALIDAD; lengthSelect = 1;
		}
		long filterOptions [] = {lengthSelect};
		try {			
			CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();
			List<CatalogoOpcionVO> options = catalogoOpcionDelegate.consultarCatalogo(option,filterOptions);
			CatalogoVO cat = getCatalogo(options);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (Exception e) { logger.error(e); }
		return null;
	}
	
	public ActionForward salary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CatalogoOpcionVO vo = new CatalogoOpcionVO();
			RANGOS_SALARIOS[] salaries = RANGOS_SALARIOS.values();		
			List<CatalogoOpcionVO> options = new ArrayList<CatalogoOpcionVO>();
			options.add(0, vo);
			for (int i=0; i<salaries.length; i++) {
				vo = new CatalogoOpcionVO();
				switch (i + 1) {
					case 1 : vo.setIdCatalogoOpcion(RANGOS_SALARIOS.MINIMUM.getIdOpcion()); vo.setOpcion(RANGOS_SALARIOS.MINIMUM.getOpcion()); break;
					case 2 : vo.setIdCatalogoOpcion(RANGOS_SALARIOS.MINIMUM_MIDDLE.getIdOpcion()); vo.setOpcion(RANGOS_SALARIOS.MINIMUM_MIDDLE.getOpcion()); break;
					case 3 : vo.setIdCatalogoOpcion(RANGOS_SALARIOS.MIDDLE.getIdOpcion()); vo.setOpcion(RANGOS_SALARIOS.MIDDLE.getOpcion()); break;
					case 4 : vo.setIdCatalogoOpcion(RANGOS_SALARIOS.MAXIMUM_MIDDLE.getIdOpcion()); vo.setOpcion(RANGOS_SALARIOS.MAXIMUM_MIDDLE.getOpcion()); break;
					case 5 : vo.setIdCatalogoOpcion(RANGOS_SALARIOS.MAXIMUM.getIdOpcion()); vo.setOpcion(RANGOS_SALARIOS.MAXIMUM.getOpcion()); break;
					case 6 : vo.setIdCatalogoOpcion(RANGOS_SALARIOS.OVER_MAXIMUM.getIdOpcion()); vo.setOpcion(RANGOS_SALARIOS.OVER_MAXIMUM.getOpcion()); break;
					default : vo = new CatalogoOpcionVO();
				}
				options.add(vo);
			}
			CatalogoVO cat = getCatalogo(options);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (Exception e) { logger.error(e); }
		return null;
	}
	
	public ActionForward ages(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CatalogoOpcionVO vo = new CatalogoOpcionVO();
			RANGOS_EDADES[] salaries = RANGOS_EDADES.values();		
			List<CatalogoOpcionVO> options = new ArrayList<CatalogoOpcionVO>();
			options.add(0, vo);
			for (int i=0; i<salaries.length; i++) {
				vo = new CatalogoOpcionVO();
				switch (i + 1) {
					case 1 : vo.setIdCatalogoOpcion(RANGOS_EDADES.YOUNG.getIdOpcion()); vo.setOpcion(RANGOS_EDADES.YOUNG.getOpcion()); break;
					case 2 : vo.setIdCatalogoOpcion(RANGOS_EDADES.YOUNG_ADULT.getIdOpcion()); vo.setOpcion(RANGOS_EDADES.YOUNG_ADULT.getOpcion()); break;
					case 3 : vo.setIdCatalogoOpcion(RANGOS_EDADES.ADULT.getIdOpcion()); vo.setOpcion(RANGOS_EDADES.ADULT.getOpcion()); break;
					case 4 : vo.setIdCatalogoOpcion(RANGOS_EDADES.CONTEMPORARY_ADULT.getIdOpcion()); vo.setOpcion(RANGOS_EDADES.CONTEMPORARY_ADULT.getOpcion()); break;
					case 5 : vo.setIdCatalogoOpcion(RANGOS_EDADES.SENIOR.getIdOpcion()); vo.setOpcion(RANGOS_EDADES.SENIOR.getOpcion()); break;
					default : vo = new CatalogoOpcionVO();
				}
				options.add(vo);
			}
			CatalogoVO cat = getCatalogo(options);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (Exception e) { logger.error(e); }
		return null;
	}

	public ActionForward regions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CatalogoOpcionVO vo = new CatalogoOpcionVO();
			REGION[] salaries = REGION.values();		
			List<CatalogoOpcionVO> options = new ArrayList<CatalogoOpcionVO>();
			options.add(0, vo);
			for (int i=0; i<salaries.length; i++) {
				vo = new CatalogoOpcionVO();
				switch (i + 1) {
					case 1 : vo.setIdCatalogoOpcion(REGION.NOROESTE.getIdOpcion()); vo.setOpcion(REGION.NOROESTE.getOpcion()); break;
					case 2 : vo.setIdCatalogoOpcion(REGION.NORESTE.getIdOpcion()); vo.setOpcion(REGION.NORESTE.getOpcion()); break;
					case 3 : vo.setIdCatalogoOpcion(REGION.CENTRO_OCCIDENTE.getIdOpcion()); vo.setOpcion(REGION.CENTRO_OCCIDENTE.getOpcion()); break;
					case 4 : vo.setIdCatalogoOpcion(REGION.CENTRO.getIdOpcion()); vo.setOpcion(REGION.CENTRO.getOpcion()); break;
					case 5 : vo.setIdCatalogoOpcion(REGION.SUR_SURESTE.getIdOpcion()); vo.setOpcion(REGION.SUR_SURESTE.getOpcion()); break;
					default : vo = new CatalogoOpcionVO();
				}
				options.add(vo);
			}
			CatalogoVO cat = getCatalogo(options);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (Exception e) { logger.error(e); }
		return null;
	}

	public ActionForward entidadesFederativasOpcion(ActionMapping mapping,  ActionForm form, HttpServletRequest request, HttpServletResponse response)  throws IOException {

		long filtroOpciones [] = {33};
		CatalogoOpcionVO vo = new CatalogoOpcionVO();
		vo.setIdCatalogoOpcion(0);
		vo.setOpcion("");

		CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
		List<CatalogoOpcionVO> opciones = null;

		try {
			opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_ENTIDAD_FEDERATIVA,filtroOpciones);
			opciones.add(0,vo);
		} catch (ServiceLocatorException e) {		
			e.printStackTrace();
		}		
		
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);		
		return null;
	}

	public ActionForward obtenerMunicipio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String idEntidad = request.getParameter("idEntidad");
		try {			
			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
			List<CatalogoOpcionVO> opciones = services.consultaMunicipios(Utils.parseLong(idEntidad));
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}
	
	
	public ActionForward fuenteOpcion(ActionMapping mapping,  ActionForm form, HttpServletRequest request, HttpServletResponse response){
		List<CatalogoOpcionVO> opciones = new ArrayList<CatalogoOpcionVO>();
		
		CatalogoOpcionVO vo = new CatalogoOpcionVO();
		vo.setIdCatalogoOpcion(0);
		vo.setOpcion("");
		opciones.add(vo);
		
		vo= new CatalogoOpcionVO();
		vo.setIdCatalogoOpcion(PAIS_OFERTA.MEXICO.getIdOpcion());
		vo.setOpcion(PAIS_OFERTA.MEXICO.getOpcion()); 
		opciones.add(vo);
		
		vo= new CatalogoOpcionVO();
		vo.setIdCatalogoOpcion(PAIS_OFERTA.CANADA.getIdOpcion());
		vo.setOpcion(PAIS_OFERTA.CANADA.getOpcion()); 
		opciones.add(vo);
		
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		try {
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			logger.info(e);
		}		
		return null;
		
	}
	
	public ActionForward obtenerCiudadCanada(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		String idProvincia = request.getParameter("idProvincia");
		try {			
			DomicilioBusDelegate services = DomicilioBusDelegateImpl.getInstance();
			List<CatalogoOpcionVO> opciones = services.consultaCiudadesCanada(Utils.parseLong(idProvincia));
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
		
	}
	
	public ActionForward provinciasCanadaOpcion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
	
		CatalogoOpcionVO vo = new CatalogoOpcionVO();
		vo.setIdCatalogoOpcion(0);
		vo.setOpcion("");

		CatalogoOpcionDelegate catalogoOpcionDelegate = CatalogoOpcionDelegateImpl.getInstance();	
		List<CatalogoOpcionVO> opciones = null;

		try {
			opciones = catalogoOpcionDelegate.consultarCatalogo(CATALOGO_OPCION_PROVINCIAS_CANADA);
			opciones.add(0,vo);
			CatalogoVO cat = getCatalogo(opciones);
			String json = toJson(cat);
			redirectJsonResponse(response, json);	
		} catch (ServiceLocatorException e) {		
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}		
			
		return null;
	
	}
	
		
	
	
	
	public ActionForward orderByColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		BusquedaEspecificaForm forma = (BusquedaEspecificaForm)session.getAttribute("busquedaEspecificaForm");
		String tipoOrdenamiento = request.getParameter("tipoOrden");
		String numeroColumna = request.getParameter("tipoColumna");
		List<Long> indices = null;
		try{
			BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();
		
			indices = services.busquedaEspecifica(forma.getEntidad(), forma.getArea(), forma.getEscolaridad(), forma.getSalario(), 
				forma.getIdMunicipio(), forma.getOcupacion(), forma.getCarrera(), forma.getEdad(), forma.getRegion(), tipoOrdenamiento, Integer.parseInt(numeroColumna), forma.getFuente());
		
			session.removeAttribute(FULL_LIST);
			session.setAttribute(FULL_LIST + "", indices);
			
		}catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (SQLException e) {
			logger.error(e);
		}catch(Exception e){
			logger.error(e);
		}
		
		return page(mapping, form, request, response);
	}
	


}