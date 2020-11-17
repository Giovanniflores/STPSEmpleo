package mx.gob.stps.portal.web.admin.offer;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TITULO_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.DESCRIPCION_PAGINA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FACEBOOK_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.TWITTER_IMAGE;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_ESPECIFICA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.stps.portal.core.autorizacion.vo.RegistroPorValidarVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.exception.TechnicalException;
import mx.gob.stps.portal.core.infra.mail.exception.MailException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.oferta.busqueda.vo.OfertaPorCanalVO;
import mx.gob.stps.portal.core.search.exception.IndexerException;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegate;
import mx.gob.stps.portal.web.autorization.delegate.AutorizationBusDelegateImpl;
import mx.gob.stps.portal.web.autorization.form.AutorizationForm;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.offer.delegate.SearchCanalOffersBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.SearchCanalOffersBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdminFilterOfferAction extends PagerAction {

	private static Logger logger = Logger.getLogger(AdminFilterOfferAction.class);
	private static String JSP_PENDIENTES_PUBLICAR = "JSP_PENDIENTES_PUBLICAR";
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("NUM_PAGE_LIST_OFEPUBLISH");
		session.removeAttribute("TOTAL_PAGES__OFEPUBLISH");
		session.removeAttribute("PAGE_JUMP_SIZE_OFEPUBLISH");
		session.removeAttribute("NUM_PAGE_JUMP");//REVISAR ESTE
		session.removeAttribute("NUM_RECORDS_VISIBLE_OFEPUBLISH");//REVISAR ESTE
		session.removeAttribute("NUM_RECORDS_TOTAL_OFEPUBLISH");
		session.removeAttribute("PAGE_LIST_OFEPUBLISH");
		session.removeAttribute(FULL_LIST+"_OFEPUBLISH");
		session.removeAttribute("NUM_REGISTROS");
		request.setAttribute("init", "init");
		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		request.getSession().setAttribute(TITULO_PAGINA, "Administrador");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administrador, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}
	
	public ActionForward filter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute("NUM_PAGE_LIST_OFEPUBLISH");
		session.removeAttribute("TOTAL_PAGES_OFEPUBLISH");
		session.removeAttribute("PAGE_JUMP_SIZE_OFEPUBLISH");
		//session.removeAttribute("NUM_PAGE_JUMP");
		session.removeAttribute("NUM_RECORDS_VISIBLE_OFEPUBLISH");
		session.removeAttribute("NUM_RECORDS_TOTAL_OFEPUBLISH");
		session.removeAttribute("NUM_REGISTROS");	
		long idoffer = Utils.parseLong(request.getParameter("idoffer"));
		int district = Utils.parseInt(request.getParameter("district"));
		String dateInitAdd = parseDate(request.getParameter("dateInitAdd"));
		String dateFinalAdd = parseDate(request.getParameter("dateFinalAdd"));
		String dateInitUpd = parseDate(request.getParameter("dateInitUpd"));
		String dateFinalUpd = parseDate(request.getParameter("dateFinalUpd"));
		String idPortal = (null != request.getParameter("idPortal") ? request.getParameter("idPortal") : "");
		String email = (null != request.getParameter("email") ? request.getParameter("email") : "");
		int status = Utils.parseInt(request.getParameter("status"));
		int deleteRazon = Utils.parseInt(request.getParameter("deleteRazon"));
		
		String idEmpresa = request.getParameter("idEmpresa");
		String contacto = request.getParameter("contacto");
		String telefono = request.getParameter("telefono");
		String idEntidadSelect = request.getParameter("idEntidadSelect");
		String idMunicipio = request.getParameter("idMunicipio");
		String salarioRango = request.getParameter("salarioRango");		
		String salario = request.getParameter("salario");
		String titulo = request.getParameter("titulo");		

		SearchCanalOffersBusDelegate service = SearchCanalOffersBusDelegateImpl.getInstance();
		try {
			List<OfertaPorCanalVO> offersList = service.getOffersPerFilter(idoffer, district, dateInitAdd, dateFinalAdd, dateInitUpd, dateFinalUpd, idPortal, email, status, deleteRazon,
																			idEmpresa, contacto, telefono, idEntidadSelect, idMunicipio, salarioRango, salario, titulo);
			this.PAGE_NUM_ROW = 5;
    		this.PAGE_JUMP_SIZE = 5;

    		request.getSession().setAttribute(FULL_LIST+"_OFEPUBLISH", offersList); // paginacion
    		session.setAttribute("NUM_RECORDS_TOTAL_OFEPUBLISH",offersList.size());
    		session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
    		request.getSession().removeAttribute("total");
			request.getSession().setAttribute("total", offersList.size());
			
			Integer totalPages = offersList.size()/PAGE_NUM_ROW; 
			if (offersList.size()%PAGE_NUM_ROW != 0){
				totalPages ++; 
			}
			session.setAttribute(TOTAL_PAGES+"_OFEPUBLISH", totalPages);
			List rowsPage = getRows(1, offersList, session);    				//Se obtienen los registros a mostrar en la pagina
			session.removeAttribute("NUM_RECORDS_OFEPUBLISH");
			session.setAttribute("NUM_RECORDS_VISIBLE_OFEPUBLISH", rowsPage.size());		//numero de registros mostrados en la pagina actual
			session.setAttribute("PAGE_JUMP_SIZE_OFEPUBLISH", PAGE_JUMP_SIZE);				
			session.setAttribute(PAGE_LIST+"_OFEPUBLISH" , rowsPage);
			session.setAttribute("NUM_PAGE_LIST_OFEPUBLISH", 1);
    		
    		if (offersList != null && !offersList.isEmpty()){
    			
        		AutorizationForm autform = (AutorizationForm)form;
    			List<RegistroPorValidarVO> listaRegistrosPorValidar = new ArrayList<RegistroPorValidarVO>();
    			
    			for (OfertaPorCanalVO oferta :offersList){
    				RegistroPorValidarVO registroPorValidar = new RegistroPorValidarVO();    				
        			
    				registroPorValidar.setIdRegistro(oferta.getIdRegistroPorValidar());
    				registroPorValidar.setIdRegValidar(oferta.getIdRegistroPorValidar());
    				registroPorValidar.setIdTipoRegistro(Constantes.TIPO_REGISTRO.OFERTA.getIdTipoRegistro());
    				registroPorValidar.setIdRegistro(oferta.getIdOfertaEmpleo());
    				
    				listaRegistrosPorValidar.add(registroPorValidar);
    			}
    			autform.setRegistros(listaRegistrosPorValidar);
    		}
    		
		} catch (Exception e) {	e.printStackTrace(); }	
		request.getSession().setAttribute("filterAdminPublisher",false);
		request.getSession().setAttribute("_urlpageinvoke", "filteroffer.do?method=init");
		

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
		request.getSession().setAttribute(TITULO_PAGINA, "Administrador");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administrador, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}
	
	public ActionForward initPendientesPublicar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		session.removeAttribute("NUM_PAGE_LIST_OFEPUBLISH");
		session.removeAttribute("TOTAL_PAGES__OFEPUBLISH");
		session.removeAttribute("PAGE_JUMP_SIZE_OFEPUBLISH");
		session.removeAttribute("NUM_PAGE_JUMP");//REVISAR ESTE
		session.removeAttribute("NUM_RECORDS_VISIBLE_OFEPUBLISH");//REVISAR ESTE
		session.removeAttribute("NUM_RECORDS_TOTAL_OFEPUBLISH");
		session.removeAttribute("PAGE_LIST_OFEPUBLISH");
		session.removeAttribute(FULL_LIST+"_OFEPUBLISH");
		request.setAttribute("init", "initPendientesPublicar");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(JSP_PENDIENTES_PUBLICAR).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Administrador");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administrador, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}
	
	public ActionForward filterAdminPublisher(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute("NUM_PAGE_LIST_OFEPUBLISH");
		session.removeAttribute("TOTAL_PAGES_OFEPUBLISH");
		session.removeAttribute("PAGE_JUMP_SIZE_OFEPUBLISH");
		//session.removeAttribute("NUM_PAGE_JUMP");
		session.removeAttribute("NUM_RECORDS_VISIBLE_OFEPUBLISH");
		session.removeAttribute("NUM_RECORDS_TOTAL_OFEPUBLISH");
		session.removeAttribute("NUM_REGISTROS");
		long idoffer = Utils.parseLong(request.getParameter("idoffer"));
		String idPortal = (null != request.getParameter("idPortal") ? request.getParameter("idPortal") : "");
		String nombreEmpresa = (null != request.getParameter("nombreEmpresa") ? request.getParameter("nombreEmpresa") : "");
		SearchCanalOffersBusDelegate service = SearchCanalOffersBusDelegateImpl.getInstance();
		boolean filterAdminPublisher = true;
		try {
			List<OfertaPorCanalVO> offersList = service.getOffersPerFilterAdminPublisher(idoffer, idPortal, nombreEmpresa);
			
			this.PAGE_NUM_ROW = 5;
    		this.PAGE_JUMP_SIZE = 5;
    		
    		request.getSession().setAttribute(FULL_LIST+"_OFEPUBLISH", offersList); // paginacion
    		session.setAttribute("NUM_RECORDS_TOTAL_OFEPUBLISH",offersList.size());
    		session.setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
    		request.getSession().removeAttribute("total");
			request.getSession().setAttribute("total", offersList.size());
			
			Integer totalPages = offersList.size()/PAGE_NUM_ROW; 
			if (offersList.size()%PAGE_NUM_ROW != 0){
				totalPages ++; 
			}
			session.setAttribute(TOTAL_PAGES+"_OFEPUBLISH", totalPages);
			List rowsPage = getRows(1, offersList, session);    				//Se obtienen los registros a mostrar en la pagina
			session.removeAttribute("NUM_RECORDS_OFEPUBLISH");
			session.setAttribute("NUM_RECORDS_VISIBLE_OFEPUBLISH", rowsPage.size());		//numero de registros mostrados en la pagina actual
			session.setAttribute("PAGE_JUMP_SIZE_OFEPUBLISH", PAGE_JUMP_SIZE);				
			session.setAttribute(PAGE_LIST+"_OFEPUBLISH" , rowsPage);
			session.setAttribute("NUM_PAGE_LIST_OFEPUBLISH", 1);
    		
    		if (offersList != null && !offersList.isEmpty()){
    			
        		AutorizationForm autform = (AutorizationForm)form;
    			List<RegistroPorValidarVO> listaRegistrosPorValidar = new ArrayList<RegistroPorValidarVO>();
    			
    			for (OfertaPorCanalVO oferta :offersList){
    				RegistroPorValidarVO registroPorValidar = new RegistroPorValidarVO();    				
        			
    				registroPorValidar.setIdRegistro(oferta.getIdRegistroPorValidar());
    				registroPorValidar.setIdRegValidar(oferta.getIdRegistroPorValidar());
    				registroPorValidar.setIdTipoRegistro(Constantes.TIPO_REGISTRO.OFERTA.getIdTipoRegistro());
    				registroPorValidar.setIdRegistro(oferta.getIdOfertaEmpleo());
    				listaRegistrosPorValidar.add(registroPorValidar);
    				if(offersList.size()==1){
    					
    					if(oferta.getEstatus()!=Constantes.ESTATUS.PENDIENTE_PUBLICAR.getIdOpcion())filterAdminPublisher=false;
    					
    				}
    			
    			}
    			autform.setRegistros(listaRegistrosPorValidar);
    		}
    		
		} catch (Exception e) {	e.printStackTrace(); }	
		request.getSession().setAttribute("filterAdminPublisher",filterAdminPublisher);
		request.getSession().setAttribute("_urlpageinvoke", "filteroffer.do?method=initPendientesPublicar");
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(JSP_PENDIENTES_PUBLICAR).getPath());

		PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Administrador");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administrador, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}
	
	
	public ActionForward publicaRegistrosAdmin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		AutorizationForm autForm = (AutorizationForm)form;
		UsuarioWebVO usuario = getUsuario(session);
		
		String type = new String();
		String[] idRegistroValidar =autForm.getIdRegistroValidar().split(",");
		try {
		
			for(int x=0;x<idRegistroValidar.length;x++){
				
				if (Long.parseLong(idRegistroValidar[x])<=0){
					registraError(request, "aut.error.requerido", "El identificador del registro por validar");
					request.getSession().setAttribute(BODY_JSP, mapping.getInput());

					PropertiesLoader properties = PropertiesLoader.getInstance();
					request.getSession().setAttribute(TITULO_PAGINA, "Administrador");
					request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administrador, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
					request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
					request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
					request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
					return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
				}
			
				AutorizationBusDelegate services = AutorizationBusDelegateImpl.getInstance();
				services.autorizaRegistro(Long.parseLong(idRegistroValidar[x]), usuario.getIdUsuario());
				
			}
			
			registraMensaje(request, "aut.autorizacion.msg.exito");		
			type = ResultVO.TYPE_SUCCESS;
				
			} catch (PersistenceException e) {
				e.printStackTrace();
				logger.error(e);
				registraError(request, "aut.error.sql.persist");
				type = ResultVO.TYPE_ERROR;
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
				logger.error(e);
				registraError(request, "aut.error.locator");
				type = ResultVO.TYPE_ERROR;			
			} catch(BusinessException e){
				e.printStackTrace();
				logger.error(e);
				registraError(request, "aut.autorizacion.msg.error");
				type = ResultVO.TYPE_ERROR;
			} catch(TechnicalException e){
				e.printStackTrace();
				logger.error(e);
				registraError(request, "aut.autorizacion.msg.error");
				type = ResultVO.TYPE_ERROR;
			} catch(MailException e){
				e.printStackTrace();
				logger.error(e);
				registraError(request, "aut.error.mail");
				type = ResultVO.TYPE_ERROR;
			} catch(IndexerException e){
				e.printStackTrace();
				logger.error(e);
				registraError(request, "aut.autorizacion.msg.indexer.error");
				type = ResultVO.TYPE_ERROR;
			} catch(Exception e){
				e.printStackTrace();
				logger.error(e);
				registraError(request, "aut.autorizacion.msg.error");
				type = ResultVO.TYPE_ERROR;
			}
		return initPendientesPublicar(mapping, autForm, request, response);
	}
	
	
	private String parseDate(String date) {
		if (null != date && !date.isEmpty())
			return date.substring(8,10) + "/" + date.substring(5,7) + "/" + date.substring(0,4);
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected List getRows(int pagenum, List<?> rows) {
		List<OfertaPorCanalVO> offersList = new ArrayList<OfertaPorCanalVO>();
		List<OfertaPorCanalVO> rowsPage = super.getRows(pagenum, rows);
		SearchCanalOffersBusDelegate service = SearchCanalOffersBusDelegateImpl.getInstance();
		try {
			for (OfertaPorCanalVO row : rowsPage) {
				List<BitacoraVO> records = service.getBitacora(row.getIdOfertaEmpleo());
				row.setBitacora(records);
				offersList.add(row);
			}
		}catch (Exception e) { logger.info(e.getMessage()); }
		return offersList;
	}
	
	public ActionForward autorizarRegistro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		AutorizationForm autForm = (AutorizationForm)form;

		if (autForm.getIdRegValidar()<=0){
			registraError(request, "aut.error.requerido", "El identificador del registro por validar");
			request.getSession().setAttribute(BODY_JSP, mapping.getInput());

			PropertiesLoader properties = PropertiesLoader.getInstance();
			request.getSession().setAttribute(TITULO_PAGINA, "Administrador");
			request.getSession().setAttribute(DESCRIPCION_PAGINA, "Administrador, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
			request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
			request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
			request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
			return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		}
		
		int adminPublisher = (request.getParameter("adminPublisher")!=null)?Integer.parseInt(request.getParameter("adminPublisher")):0;
		
		UsuarioWebVO usuario = getUsuario(session);

		String msg = new String();
		String type = new String();
		
		try {
			if(usuario!=null){
				AutorizationBusDelegate services = AutorizationBusDelegateImpl.getInstance();
				services.autorizaRegistro(autForm.getIdRegValidar(), usuario.getIdUsuario());

				registraMensaje(request, "aut.autorizacion.msg.exito");

				type = ResultVO.TYPE_SUCCESS;
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			logger.error(e);
			registraError(request, "aut.error.sql.persist");
			type = ResultVO.TYPE_ERROR;
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
			registraError(request, "aut.error.locator");
			type = ResultVO.TYPE_ERROR;			
		} catch(BusinessException e){
			e.printStackTrace();
			logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
			type = ResultVO.TYPE_ERROR;
		} catch(TechnicalException e){
			e.printStackTrace();
			logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
			type = ResultVO.TYPE_ERROR;
		} catch(MailException e){
			e.printStackTrace();
			logger.error(e);
			registraError(request, "aut.error.mail");
			type = ResultVO.TYPE_ERROR;
		} catch(IndexerException e){
			e.printStackTrace();
			logger.error(e);
			registraError(request, "aut.autorizacion.msg.indexer.error");
			type = ResultVO.TYPE_ERROR;
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
			type = ResultVO.TYPE_ERROR;
		}

		if(adminPublisher==0)return init(mapping, autForm, request, response);
		else return initPendientesPublicar(mapping, autForm, request, response);
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
		} catch (Exception e) {		
			e.printStackTrace();
		}		
		
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);		
		return null;
	}
	
	public ActionForward consultarMunicipio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		String idEntidad = request.getParameter("idEntidad");
		try{
			if (idEntidad != null && !"".equals(idEntidad) && !"0".equals(idEntidad)){
				DomicilioBusDelegate service = DomicilioBusDelegateImpl.getInstance();
				List<CatalogoOpcionVO> municipios = service.consultaMunicipios(Long.valueOf(idEntidad));
				redirectJson(response, municipios);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error en FiltraCandidatoAction.consultarMunicipio");			
			logger.error(e);			
		} catch (PersistenceException e) {
			e.printStackTrace();
			logger.error("Error en FiltraCandidatoAction.consultarMunicipio");			
			logger.error(e);			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error("Error en FiltraCandidatoAction.consultarMunicipio");			
			logger.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en FiltraCandidatoAction.consultarCatalogo");			
			logger.error(e);
		}
		return null;
	}
	
	public ActionForward consultarRangos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	try{
		
		List<CatalogoOpcionVO> rangos = new ArrayList<CatalogoOpcionVO>();

		CatalogoOpcionVO opcion1 = new CatalogoOpcionVO();

		opcion1.setIdCatalogoOpcion(Constantes.RANGO_COMPARA.MAYOR.getIdOpcion());
		opcion1.setOpcion(Constantes.RANGO_COMPARA.MAYOR.getOpcion());
		rangos.add(opcion1);
		
		CatalogoOpcionVO opcion2 = new CatalogoOpcionVO();
		opcion2.setIdCatalogoOpcion(Constantes.RANGO_COMPARA.MENOR.getIdOpcion());
		opcion2.setOpcion(Constantes.RANGO_COMPARA.MENOR.getOpcion());
		rangos.add(opcion2);
		
		CatalogoOpcionVO opcion3 = new CatalogoOpcionVO();
		opcion3.setIdCatalogoOpcion(Constantes.RANGO_COMPARA.IGUAL.getIdOpcion());
		opcion3.setOpcion(Constantes.RANGO_COMPARA.IGUAL.getOpcion());		
		rangos.add(opcion3);		

		redirectJson(response, rangos);
	} catch (Exception e) {
		e.printStackTrace();
		logger.error("Error en FiltraCandidatoAction.consultarCatalogo");			
		logger.error(e);
	}
	return null;
}
	
	
	private void redirectJson(HttpServletResponse response, List<CatalogoOpcionVO> opciones) throws IOException {
		CatalogoVO cat = getCatalogo(opciones);
		String json = toJson(cat);
		redirectJsonResponse(response, json);
	}	
	
}