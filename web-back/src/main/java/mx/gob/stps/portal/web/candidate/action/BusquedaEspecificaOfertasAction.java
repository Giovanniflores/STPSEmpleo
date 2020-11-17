package mx.gob.stps.portal.web.candidate.action;

import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.SUBPROGRAMA;
import mx.gob.stps.portal.core.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.persistencia.entity.CodigoPostal;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.vo.BusquedaOfertasVO;
import mx.gob.stps.portal.core.oferta.vo.ResultadoBusquedaOfertasVO;
import mx.gob.stps.portal.core.search.service.IndexerServiceLocator;
import mx.gob.stps.portal.web.candidate.form.BusquedaEspecificaOfertasForm;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.TablaBusquedaEspecificaOfertas;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegate;
import mx.gob.stps.portal.web.jobvacancies.delegate.BolsasTrabajoBusDelegateImpl;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegate;
import mx.gob.stps.portal.web.oferta.delegate.OfertaBusDelegateImpl;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.OfferBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static mx.gob.stps.portal.web.infra.utils.Constantes.*;

public class BusquedaEspecificaOfertasAction extends PagerAction {

    private enum ActionForwardMapping {
        
        SEARCH("SEARCH"),
        SEARCH_RESULT("SEARCH_RESULT");

        private final String forwardName;

        private ActionForwardMapping(String forwardName) {
            this.forwardName = forwardName;
        }
    }

	

    private static Logger logger = Logger.getLogger(BusquedaEspecificaOfertasAction.class);
    
    @Override
	public ActionForward init(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

    public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
    	// --- BEGIN: Check invocation from OLA ---
    	String[] methods = request.getParameterValues("method");
    	Boolean invokedFromOLA = Boolean.FALSE;
    	for (String s : methods) {
    		if (s.equals("filterQ")) {
    			invokedFromOLA = Boolean.TRUE;
    		}
    	}
    	if (invokedFromOLA) {
    		// Delegate job
    		try {
				return filterQ(mapping, form, request, response);
			} catch (Exception e) {
				logger.debug("Scope: filterQ");
				registraError(request, "errors.app", e.getMessage());
				request.getSession().setAttribute(BODY_JSP, mapping.findForward(ActionForwardMapping.SEARCH.toString()).getPath());
				return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
			}
    	}
    	// ---  END : Check invocation from OLA ---
    	
    	BusquedaEspecificaOfertasForm busquedaEspecificaOfertasForm = (BusquedaEspecificaOfertasForm) form;
        busquedaEspecificaOfertasForm.reset();

        CatalogoOpcionDelegate catService = CatalogoOpcionDelegateImpl.getInstance();

        // NOTE: JobsCatalogue are loaded dinamically via AJAX

        List<CatalogoOpcionVO> locationEntityCatalog = null;
		try {
			locationEntityCatalog = catService.consultarCatalogo(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
		} catch (ServiceLocatorException e) {
			logger.debug("Scope: locationEntityCatalog");
			registraError(request, "errors.app", e.getMessage());
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(ActionForwardMapping.SEARCH.toString()).getPath());
			return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		}
        CatalogoOpcionVO locationEntityTmp = null;
        for (CatalogoOpcionVO locationEntity : locationEntityCatalog) {
        	if (locationEntity.getIdCatalogoOpcion() == Constantes.ENTIDADES_FEDERATIVAS.NACIDO_EN_EL_EXTRANJERO.getIdEntidad()) { // EXCLUDE 'Nacido en el extranjero'
        		locationEntityTmp = locationEntity;
        		break;
        	}
        }
        if (locationEntityTmp != null) {
        	locationEntityCatalog.remove(locationEntityTmp);
        }
        busquedaEspecificaOfertasForm.setLocationStateCatalog(locationEntityCatalog);
        
        // NOTE: There's no methods for finding all locationStates
        List<CatalogoOpcionVO> locationRegionCatalog = new ArrayList<CatalogoOpcionVO>();
        for (Constantes.REGION region : Constantes.REGION.values()) {
        	CatalogoOpcionVO catalogoOpcionVO = new CatalogoOpcionVO();
        	catalogoOpcionVO.setIdCatalogoOpcion(region.getIdOpcion());
        	catalogoOpcionVO.setOpcion(region.getOpcion());
        	locationRegionCatalog.add(catalogoOpcionVO);
        }
        busquedaEspecificaOfertasForm.setLocationRegionCatalog(locationRegionCatalog);
        
        List<CatalogoOpcionVO> educationGradeCatalog = null;
		try {
			educationGradeCatalog = catService.consultarCatalogo(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS);
		} catch (ServiceLocatorException e) {
			logger.debug("Scope: educationGradeCatalog");
			registraError(request, "errors.app", e.getMessage());
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(ActionForwardMapping.SEARCH.toString()).getPath());
			return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		}
        busquedaEspecificaOfertasForm.setEducationGradeCatalog(educationGradeCatalog);
        
        // NOTE: CareerCatalogue are loaded dinamically via AJAX
        
//        List<CatalogoOpcionVO> employmentCatalog = catService.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_EMPLEO);
//        busquedaEspecificaOfertasForm.setEmploymentCatalog(employmentCatalog);
//        List<CatalogoOpcionVO> contractCatalog = catService.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_CONTRATO);
//        busquedaEspecificaOfertasForm.setContractCatalog(contractCatalog);

//        List<CatalogoOpcionVO> enterpriseActivityCatalog = catService.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA);
//        busquedaEspecificaOfertasForm.setEnterpriseActivityCatalog(enterpriseActivityCatalog);
        
        UsuarioWebVO usuario = getUsuario(request.getSession());
        if (usuario != null && usuario.getCandidato()) {
        	busquedaEspecificaOfertasForm.setCandidateLogged(true);
        }

        //return mapping.findForward(ActionForwardMapping.SEARCH.toString());
        request.getSession().setAttribute(BODY_JSP, mapping.findForward(ActionForwardMapping.SEARCH.toString()).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();
        String propertyHost = properties.getProperty("app.context.url.ssl");
        if(propertyHost == null){
        	if(request.getContextPath().equals("qa.empleo.gob.mx")){
        		propertyHost = "https://qa.empleo.gob.mx";
        	}else if(request.getContextPath().equals("www.empleo.gob.mx")){
        		propertyHost = "https://www.empleo.gob.mx";
        	}else{
        		propertyHost = "https://qa.empleo.gob.mx";
        	}
        }else{
        	propertyHost = properties.getProperty("app.context.url.ssl");
        }
		request.getSession().setAttribute(TITULO_PAGINA, "B&uacute;squeda Espec&iacute;fica");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "B&uacute;squeda Espec&iacute;fica, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo.");
		request.getSession().setAttribute(FACEBOOK_IMAGE, propertyHost+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, propertyHost+ "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, propertyHost+ response.encodeURL(request.getRequestURI().toString()));
        return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
    }

    public ActionForward buscarOcupacionesViaAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	try{
            //Checa el tipo de codificacion del request AJAX de Dojo.
            @SuppressWarnings("unused")
            String encoding = request.getCharacterEncoding();
            String catalogo = request.getParameter("search");
            CatalogoOpcionDelegate services = CatalogoOpcionDelegateImpl.getInstance();

            redirectJsonResponse(response, services.obtenerCatalogoDinamico(Constantes.CATALOGO_OPCION_OCUPACION, catalogo));

        } catch(Exception e){
            logger.error(e);
        }

        return null;
    }
    
//    public ActionForward buscarMunicipiosViaAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//    	try {
//    		//Checa el tipo de codificacion del request AJAX de Dojo.
//            @SuppressWarnings("unused")
//            String encoding = request.getCharacterEncoding();
//            String catalogo = request.getParameter("search");
//            CatalogoOpcionDelegate services = CatalogoOpcionDelegateImpl.getInstance();
//            
//            // TODO: Search a Catalog according to it's parent, i.e., LocationEntity has many LocationMunicipalites
//            redirectJsonResponse(response, services.obtenerCatalogoDinamico(Constantes.CATALOGO_OPCION_MUNICIPIO, catalogo));
//            
//            // -----
//              
//    	} catch(Exception e){
//            logger.error(e);
//        }
//
//        return null;
//    }
    
    public ActionForward getSubprogram(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	List<CatalogoOpcionVO> subprogramsList = new ArrayList<CatalogoOpcionVO>();
    	try {
			SUBPROGRAMA [] subprogramas = SUBPROGRAMA.values();
			for (int i=0; i<subprogramas.length; i++) {
				CatalogoOpcionVO cat = new CatalogoOpcionVO();
				cat.setIdCatalogoOpcion(subprogramas[i].getIdOpcion());
				cat.setOpcion(subprogramas[i].getOpcion());
				subprogramsList.add(cat);
			}
			unshiftOptionZeroToListCatalogoOpcionVO(subprogramsList);
			redirectJsonResponse(response, toJson(Utils.getCatalogo(subprogramsList)));
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
    }
    
    public ActionForward obtainEducationGradeViaAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	CatalogoOpcionDelegate catService = CatalogoOpcionDelegateImpl.getInstance();
    	try {
    		List<CatalogoOpcionVO> educationGradeCatalog = catService.consultarCatalogo(Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS);
    		unshiftOptionZeroToListCatalogoOpcionVO(educationGradeCatalog);
    		CatalogoVO cat = Utils.getCatalogo(educationGradeCatalog);
    		String contractCatalogJson = toJson(cat);
			redirectJsonResponse(response, contractCatalogJson);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
    	
		return null;
    }
    
    public ActionForward obtainEmploymentTypeViaAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	CatalogoOpcionDelegate catService = CatalogoOpcionDelegateImpl.getInstance();
    	try {
    		List<CatalogoOpcionVO> employmentCatalog = catService.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_EMPLEO);
    		unshiftOptionZeroToListCatalogoOpcionVO(employmentCatalog);
    		CatalogoVO cat = Utils.getCatalogo(employmentCatalog);
    		String contractCatalogJson = toJson(cat);
			redirectJsonResponse(response, contractCatalogJson);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
    	
    	return null;
    }
    
    public ActionForward obtainContractTypeViaAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	CatalogoOpcionDelegate catService = CatalogoOpcionDelegateImpl.getInstance();
    	try {
    		List<CatalogoOpcionVO> contractTypeCatalog = catService.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_CONTRATO);
    		unshiftOptionZeroToListCatalogoOpcionVO(contractTypeCatalog);
    		CatalogoVO cat = Utils.getCatalogo(contractTypeCatalog);
    		String contractCatalogJson = toJson(cat);
			redirectJsonResponse(response, contractCatalogJson);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
    	
    	return null;
    }
    
    public ActionForward obtainEnterpriseActivityViaAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	CatalogoOpcionDelegate catService = CatalogoOpcionDelegateImpl.getInstance();
    	try {
    		List<CatalogoOpcionVO> enterpriseActivityCatalog = catService.consultarCatalogo(Constantes.CATALOGO_OPCION_TIPO_ACTIVIDAD_ECONOMICA);
    		unshiftOptionZeroToListCatalogoOpcionVO(enterpriseActivityCatalog);
    		CatalogoVO cat = Utils.getCatalogo(enterpriseActivityCatalog);
    		String contractCatalogJson = toJson(cat);
			redirectJsonResponse(response, contractCatalogJson);
		} catch (ServiceLocatorException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
    	
    	return null;
    }
    
    public ActionForward encontrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
       
    	BusquedaEspecificaOfertasForm busquedaEspecificaOfertasForm = (BusquedaEspecificaOfertasForm) form;
    	String forward;

        // NOTE: It's extremely important to invoke this method before calling the service
        busquedaEspecificaOfertasForm.assignTxtValues();
        
        UsuarioWebVO usuario = getUsuario(request.getSession());
        if (null != usuario && usuario.getCandidato()) {
        	busquedaEspecificaOfertasForm.setCandidateLogged(true);
        	logger.debug("CANDIDATE IS LOGGED");
        }
        try {
			if (busquedaEspecificaOfertasForm.hasSelectedFilters()) {
				String filtroGeo="false";
				List<String> codigoPostal = new ArrayList<String>();
				// Delegate the search to the business tier
				OfertaBusDelegate service = OfertaBusDelegateImpl.getInstance(); 
				BusquedaOfertasVO busquedaOfertasVO = busquedaEspecificaOfertasForm.buildBusquedaOfertasVO();
				List<ResultadoBusquedaOfertasVO> jobOffers = service.buscarOfertasEspecificas(busquedaOfertasVO);
				if (busquedaEspecificaOfertasForm.hasSelectedFiltersGeolocalizacion())
					filtroGeo="true";
				request.getSession().setAttribute(FILTRO_GEO, filtroGeo);
				for (ResultadoBusquedaOfertasVO job:jobOffers) {
					codigoPostal.add(job.getCodigoPostal());
				}
				request.getSession().setAttribute(CODIGOS, codigoPostal);
				
				String selectedFilters = busquedaEspecificaOfertasForm.concatenateSelectedFilters(); 
		        busquedaEspecificaOfertasForm.reset(); // Ensure the form is clean in case the page (busquedaEspecificaOfertas.do?method=buscar) is accessed via history.go(-1) 
		        busquedaEspecificaOfertasForm.setSelectedFilters(selectedFilters);
		        busquedaEspecificaOfertasForm.setJobOffersSize(jobOffers.size());
				
				// --- BEGIN: Pagination code ---
				this.PAGE_NUM_ROW = 5;
			    this.PAGE_JUMP_SIZE = 10;
	
			    request.getSession().removeAttribute("tablaPager");
			    request.getSession().removeAttribute("NUM_PAGE_LIST");
			    
			    //request.getSession().removeAttribute(FULL_LIST);
			    request.getSession().setAttribute(FULL_LIST, jobOffers);
	
			    request.getSession().removeAttribute("NUM_REGISTROS");			
				request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
				
				request.getSession().removeAttribute("total");			
				request.getSession().setAttribute("total", jobOffers.size());
				
				List<Long> indicesOfertas = obtenerIndicesOfertas(jobOffers);
				Integer totalPlazas = obtenerTotalNumeroPlazas(indicesOfertas);					
				request.getSession().setAttribute("totalNumeroPlazas", totalPlazas);
				
				// --- END: Pagination code ---
				
				//forward = "JSP_RESULTADOS";
			}else{
				busquedaEspecificaOfertasForm.reset();
				request.removeAttribute("formaNoValida");
				request.setAttribute("formaNoValida", true);
				forward = FORWARD_JSP;
			}
		
		} catch (ServiceLocatorException e) {
			logger.debug("Scope: ServiceLocatorException");
			logger.error(e);
			registraError(request, "errors.app", e.getMessage());
		} catch (BusinessException e) {
			logger.debug("Scope: BusinessException");
			logger.error(e);
			registraError(request, "errors.app", e.getMessage());

//			String selectedFilters = busquedaEspecificaOfertasForm.concatenateSelectedFilters(); 
//	        busquedaEspecificaOfertasForm.reset(); // Ensure the form is clean in case the page (busquedaEspecificaOfertas.do?method=buscar) is accessed via history.go(-1) 
//	        busquedaEspecificaOfertasForm.setSelectedFilters(selectedFilters);
		} catch (SQLException e) {
			logger.debug("Scope: SQLException");
			logger.error(e);
			registraError(request, "errors.app", e.getMessage());
		} catch (Exception e) {
			logger.debug("Scope: Exception");
			logger.error(e);
			registraError(request, "errors.app", e.getMessage());
		}
        
        // Remove _urlpageinvoke from Session or Request in order to have javascript:history.go(-1)
        request.getSession().removeAttribute("_urlpageinvoke");
	    request.getSession().setAttribute(BODY_JSP, mapping.findForward(ActionForwardMapping.SEARCH_RESULT.toString()).getPath());
        return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
    }
    
	@Override
	protected List getRows(int pagenum, List<?> rows, HttpSession session) {

		List rowsPage = super.getRows(pagenum, rows, session);
		BolsasTrabajoBusDelegate services = BolsasTrabajoBusDelegateImpl.getInstance();

		try {
			UsuarioWebVO usuario = getUsuario(session);
			if (usuario != null && usuario.getCandidato()) {
				try {
					OfferBusDelegate offerServices = OfferBusDelegateImpl.getInstance();
					long idCandidato = usuario.getIdPropietario();
					for (int i = 0; i < rowsPage.size(); i++) {
						ResultadoBusquedaOfertasVO oferta = (ResultadoBusquedaOfertasVO) rowsPage.get(i);
//						int compatibility = offerServices.match(oferta.getJobId(), idCandidato);
						int compatibility = IndexerServiceLocator.getIndexerServiceRemote().match(oferta.getJobId(), idCandidato);	
						
						oferta.setCompatibility(compatibility);
//						if (PAGE_NUM_ROW == i) {
//							break; // Solo se consulta la compatibilidad para
//								   // los registros a mostrarse
//						}		
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			rowsPage = new ArrayList();
		}

		return rowsPage;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward orderByColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		BusquedaEspecificaOfertasForm myForm = (BusquedaEspecificaOfertasForm) form;
		String orderType = request.getParameter("orderType");
		String columnNumber = request.getParameter("columnNumber");
		List<ResultadoBusquedaOfertasVO> resultado = null;
		
		try{	
			myForm.setOrderType(orderType);
			myForm.setColumnNumber(Integer.parseInt(columnNumber));
				
			resultado = (List<ResultadoBusquedaOfertasVO>) session.getAttribute(FULL_LIST);
			
			TablaBusquedaEspecificaOfertas tabla = new TablaBusquedaEspecificaOfertas(resultado);
			tabla.ordenarTabla(Integer.parseInt(columnNumber), orderType);
			resultado = tabla.getLista();
		
			session.removeAttribute(FULL_LIST);
			session.setAttribute(FULL_LIST, resultado);
		}catch(Exception e){
			logger.error(e);
		}
		
		return page(mapping, form, request, response);
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
	
	public List<Long> obtenerIndicesOfertas(List<ResultadoBusquedaOfertasVO> jobOffers){
		List<Long> indicesOfertas = new ArrayList<Long>();
		Iterator<ResultadoBusquedaOfertasVO> itOffers = jobOffers.iterator();
		while(itOffers.hasNext()){
			ResultadoBusquedaOfertasVO offer = itOffers.next();
			indicesOfertas.add(offer.getJobId());
		}
		return indicesOfertas;
	}

	private void unshiftOptionZeroToListCatalogoOpcionVO(List<CatalogoOpcionVO> catList) {
		CatalogoOpcionVO catVO = new CatalogoOpcionVO();
		catVO.setIdCatalogoOpcion(0L);
		catVO.setOpcion("Seleccione una opción");
		catList.add(0, catVO);
	}
	
	/**
	 * This method is intended to be used from ObservatorioLaboral
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward filterQ(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Build an actionForm object using the stringQuery parameters
		// http://qa.empleo.gob.mx/swb/empleo/busquedaEspecifica?method=filterQ&ola=true&district=9&escolaridad=10&carrera=4144024&carrera=4144023&carrera=4144022&carrera=4144021&carrera=4144020&carrera=4144019&carrera=4144018&carrera=4144017&carrera=4144016&carrera=4144015&carrera=4144014&carrera=4144013&carrera=4144012&carrera=4144011&carrera=4144010&carrera=4144009&carrera=4144008&carrera=4144007&carrera=4144006&carrera=4144005&carrera=4144004&carrera=4144003&carrera=4144002&carrera=4144001&descr=desconocido
        // http://empleo.gob.mx/busquedaEspecificaOfertas.do?method=filterQ&area=31&ocupacion=311101&ocupacion=311102&ocupacion=311103&ocupacion=311104&descr=Secretarias&district=9

        // NOTE: 'area' parameter is not included within the filters

		Integer locationEntityId = 0;
		Integer educationGradeId = 0;
		List<Integer> educationCareerIds = new ArrayList<Integer>();
		List<Integer> jobIds = new ArrayList<Integer>();

		// There's no risk of SQL injection since all sensitive data must be numeric, no way SQL statement will be parsed to numeric
		try {
			if (request.getParameter("district") != null && !request.getParameter("district").equals("")) {
				locationEntityId = Integer.parseInt(request.getParameter("district"));
			}
			if (request.getParameter("escolaridad") != null && !request.getParameter("escolaridad").equals("")) {
				educationGradeId = Integer.parseInt(request.getParameter("escolaridad"));
			}
            if (request.getParameterValues("carrera") != null && request.getParameterValues("carrera").length != 0) {
                for (String i : request.getParameterValues("carrera")) {
                    educationCareerIds.add(Integer.parseInt(i));
                }
            }
            if (request.getParameterValues("ocupacion") != null && request.getParameterValues("ocupacion").length != 0) {
                for (String i : request.getParameterValues("ocupacion")) {
                    jobIds.add(Integer.parseInt(i));
                }
            }
		} catch (NumberFormatException e) {
			logger.error(e);
			//registraError(request, "");
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(ActionForwardMapping.SEARCH.toString()).getPath());
	        return mapping.findForward(FORWARD_TEMPLATE_RESPONSIVE);
		}
		String description = request.getParameter("descr");
		
		BusquedaEspecificaOfertasForm busquedaEspecificaOfertasForm = (BusquedaEspecificaOfertasForm) form;
        busquedaEspecificaOfertasForm.reset();
		
        busquedaEspecificaOfertasForm.setInvokedFromObservatorioLaboral(true);
        busquedaEspecificaOfertasForm.setLocationEntityId(locationEntityId);
        busquedaEspecificaOfertasForm.setEducationGradeId(educationGradeId);
        busquedaEspecificaOfertasForm.setEducationCareerIds(educationCareerIds);
        busquedaEspecificaOfertasForm.setJobIds(jobIds);
        busquedaEspecificaOfertasForm.setDescription(description);

        // Invoke/delegate "encontrar" method
		return encontrar(mapping, form, request, response);
	}
}
