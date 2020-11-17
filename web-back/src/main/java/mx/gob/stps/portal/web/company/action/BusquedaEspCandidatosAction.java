package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_REDIRECT_SWB;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_GRADO_ESTUDIOS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_IDIOMAS;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_OCUPACION;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_AREA_LABORAL;
import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_CANDIDATO_HABILIDAD;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_BUSQUEDAS_EMPRESA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.URL_REDIRECT_SWB;
import static mx.gob.stps.portal.web.infra.utils.Constantes.USUARIO_APP;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import mx.gob.stps.portal.core.candidate.vo.ResultadoBusquedaCandidatosVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.persistencia.vo.CatAreaVO;
import mx.gob.stps.portal.persistencia.vo.CatSubareaVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegate;
import mx.gob.stps.portal.web.candidate.delegate.CandidatoBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.BusquedaCandidatosForm;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.utils.TablaBusEspCandidatos;
import mx.gob.stps.portal.web.infra.utils.Utils;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.offer.delegate.RecentOfferSummaryBusDelegate;
import mx.gob.stps.portal.web.offer.delegate.RecentOfferSummaryBusDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

public class BusquedaEspCandidatosAction extends PagerAction{
	
	private static Logger logger = Logger.getLogger(BusquedaEspCandidatosAction.class);
	public static final String EXISTEN_OFERTAS_ACTIVAS = "existenOfertasActivas";
	public static final String SI = "Si";
	public static final String NO = "No";
	
	@Override
	public ActionForward init(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {

		PropertiesLoader properties = PropertiesLoader.getInstance();

		String template = "";
		String forward = "";

		HttpSession session = request.getSession();
		BusquedaCandidatosForm candidatosForm = (BusquedaCandidatosForm) form;
		candidatosForm.reset();

		//Obtiene las ofertas y postulaciones recientes
		RecentOfferSummaryBusDelegate summaryServices = RecentOfferSummaryBusDelegateImpl.getInstance();

		UsuarioWebVO usuarioWeb = (UsuarioWebVO)session.getAttribute(USUARIO_APP);

		long idEmpresa = usuarioWeb.getIdPropietario();

		//Verifica que exista al menos una oferta con estatus de activa
		Long numeroOfertasActivas = summaryServices.getCountOfertasActivas(idEmpresa);					
		if(numeroOfertasActivas >= 1){
			request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, SI);
			forward = FORWARD_JSP;
			template = FORWARD_TEMPLATE_BUSQUEDAS_EMPRESA;
			request.getSession().setAttribute(BODY_JSP, mapping.findForward(forward).getPath());
		}else{
			request.getSession().setAttribute(EXISTEN_OFERTAS_ACTIVAS, NO);
			forward = properties.getProperty("app.swb.redirect.miespacio.empresa");
			template = FORWARD_REDIRECT_SWB;
			request.setAttribute(URL_REDIRECT_SWB, forward);
		}
		estableceDatosPersonalesCatalogos(candidatosForm);
		request.getSession().removeAttribute("tablaPager");
		request.getSession().removeAttribute("NUM_PAGE_LIST"); 
		request.getSession().removeAttribute(FULL_LIST);
		request.getSession().removeAttribute("NUM_REGISTROS");

		return mapping.findForward(template);
	}

	public ActionForward frmOnBoard(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		if(null!=request.getSession().getAttribute("_FORM_BUSQ_CAND")){
			BusquedaCandidatosForm candidatosForm = (BusquedaCandidatosForm) request.getSession().getAttribute("_FORM_BUSQ_CAND");
			//FIXME request.getSession().removeAttribute("_FORM_BUSQ_CAND");
			return buscarCandidatos(mapping, candidatosForm, request, response);
		}
		
		return null;
	}
		
	public ActionForward buscarCandidatos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		List<ResultadoBusquedaCandidatosVO> resultado = null;
		String forward = "";
		try {
			BusquedaCandidatosForm candidatosForm = (BusquedaCandidatosForm) form;
			
			candidatosForm.obtenerFiltrosBusqueda();
	
			if(!candidatosForm.validarForma()){
				request.removeAttribute("formaNoValida");
				request.setAttribute("formaNoValida", true);
				forward = FORWARD_JSP;
			}else{
				CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
				resultado = service.busquedaEspecificaCandidatos(candidatosForm);
				
				
				this.PAGE_NUM_ROW = 5;
			    this.PAGE_JUMP_SIZE = 5;
	
			    request.getSession().removeAttribute("tablaPager");
			    request.getSession().removeAttribute("NUM_PAGE_LIST");
			    
			    request.getSession().removeAttribute(FULL_LIST);
			    request.getSession().setAttribute(FULL_LIST, resultado);
	
			    request.getSession().removeAttribute("NUM_REGISTROS");			
				request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
				
				request.getSession().removeAttribute("total");			
				request.getSession().setAttribute("total", resultado.size());
				
				forward = "JSP_RESULTADOS";
				
				//FIXME
				request.getSession().setAttribute("_FORM_BUSQ_CAND", candidatosForm);
				request.getSession().setAttribute("_urlpageinvokeCand", "busquedaEspecificaCandidatos.do?method=frmOnBoard"); 
			}
		
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(forward).getPath());
		return mapping.findForward(FORWARD_TEMPLATE_BUSQUEDAS_EMPRESA);
	}
	
	public ActionForward specificSearch(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		String forward = "";
		List<ResultadoBusquedaCandidatosVO> result = null;
		try {
			BusquedaCandidatosForm candidatosForm = (BusquedaCandidatosForm) form;
			candidatosForm.obtenerFiltrosBusqueda();
			if (!candidatosForm.validate()) {
				request.removeAttribute("formaNoValida");
				request.setAttribute("formaNoValida", true);
				forward = FORWARD_JSP;
			}else {
				CandidatoBusDelegate service = CandidatoBusDelegateImpl.getInstance();
				result = service.busquedaEspecificaCandidatos(candidatosForm);
				this.PAGE_NUM_ROW = 5;
			    this.PAGE_JUMP_SIZE = 5;
	
			    request.getSession().removeAttribute("tablaPager");
			    request.getSession().removeAttribute("NUM_PAGE_LIST");
			    
			    request.getSession().removeAttribute(FULL_LIST);
			    request.getSession().setAttribute(FULL_LIST, result);
	
			    request.getSession().removeAttribute("NUM_REGISTROS");			
				request.getSession().setAttribute("NUM_REGISTROS", PAGE_NUM_ROW);
				
				request.getSession().removeAttribute("total");			
				request.getSession().setAttribute("total", result.size());
				
				forward = "JSP_RESULTADOS";
				
				//FIXME
				request.getSession().setAttribute("_FORM_BUSQ_CAND", candidatosForm);
				request.getSession().setAttribute("_urlpageinvokeCand", "busquedaEspecificaCandidatos.do?method=frmOnBoard"); 
			}
		
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(forward).getPath());
		return mapping.findForward(FORWARD_TEMPLATE_BUSQUEDAS_EMPRESA);
	}

	public ActionForward ocupaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			//Checa el tipo de codificación del request AJAX de Dojo.
			@SuppressWarnings("unused")
			String encoding = request.getCharacterEncoding();
			String catalogo = request.getParameter("search"); 
			CatalogoOpcionDelegate services = CatalogoOpcionDelegateImpl.getInstance();		

			redirectJsonResponse(response, services.obtenerCatalogoDinamico(CATALOGO_OPCION_OCUPACION, catalogo));
			
		} catch(Exception e){logger.error(e);}

		return null;
	}
	
	public ActionForward areaLaboraList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CatalogoVO cat = new CatalogoVO("value", "label");
		try {
			@SuppressWarnings("unused")
			String encoding = request.getCharacterEncoding();
			CatalogoOpcionDelegate services = CatalogoOpcionDelegateImpl.getInstance();
			List<CatAreaVO> areaList = services.areaList();
			for (CatAreaVO op : areaList) {
				cat.addItem(op.getDescripcion(), String.valueOf(op.getIdArea()), String.valueOf(op.getIdArea()));
			}
			String json = Utils.toJson(cat);
			redirectJsonResponse(response, json);
		} catch(Exception e){logger.error(e);}
		return null;
	}
	
	public ActionForward subareaLaboraList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CatalogoVO cat = new CatalogoVO("value", "label");
		String idArea = request.getParameter("idAreaLaboral"); 
		try {
			CatalogoOpcionDelegate services = CatalogoOpcionDelegateImpl.getInstance();
			List<CatSubareaVO> subareaList = services.subareaListByIdArea(Utils.parseLong(idArea));
			for (CatSubareaVO op : subareaList) {
				cat.addItem(op.getDescripcion().trim(), String.valueOf(op.getIdSubarea()), String.valueOf(op.getIdSubarea()));
			}
			String json = Utils.toJson(cat);
			redirectJsonResponse(response, json);
		} catch(Exception e){logger.error(e);}
		return null;
	}
	
	private void estableceDatosPersonalesCatalogos(BusquedaCandidatosForm busquedaCandidatosForm) {
		try{
			List<CatalogoOpcionVO> grados = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_GRADO_ESTUDIOS, true);
	
			String[] gradosDependientes = new String[grados.size() + 1];
			gradosDependientes[0] = "0";
			
			for (CatalogoOpcionVO opcion : grados) {
				gradosDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
			}	
		
			busquedaCandidatosForm.setGradosDependientes(gradosDependientes);
			
			List<CatalogoOpcionVO> idiomas = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_IDIOMAS);
			
			// Carga en sesión los catalogos asociados a cada opcion
			String[] idiomasDependientes = new String[idiomas.size() + 1];
			idiomasDependientes[0] = "0";

			for (CatalogoOpcionVO opcion : idiomas) {
				if(opcion.getIdCatalogoOpcion() == 10)
					idiomasDependientes[9] = String.valueOf(opcion.getIdCatalagoAsociado());
				else
					idiomasDependientes[(int) opcion.getIdCatalogoOpcion()] = String.valueOf(opcion.getIdCatalagoAsociado());
			}
			
			busquedaCandidatosForm.setIdiomasDependientes(idiomasDependientes);
			
		} catch(Exception e){
			e.printStackTrace(); logger.error(e);
		}
	}
	
	public ActionForward carreras(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			long idCatDep = Utils.parseLong(request.getParameter("idCatDep"));
			//List<CatalogoOpcionVO> opciones = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(idCatDep);
			//redirectJsonCatalogo(opciones, response);
			String catalogojson = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogoJSON(idCatDep);
			redirectJsonResponse(response, catalogojson);
		} catch (Exception e) {logger.error(e);}

		return null;
	}
	
	public ActionForward habilidades(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		try {
			//long idCatDep = Utils.parseLong(request.getParameter("idCatDep"));
			String catalogojson = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogoJSON(CATALOGO_OPCION_CANDIDATO_HABILIDAD);
			//redirectJsonCatalogo(opciones, response);
			//String catalogojson = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogoJSON(idCatDep);
			redirectJsonResponse(response, catalogojson);
		} catch (Exception e) {logger.error(e);}

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward orderByColumn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		BusquedaCandidatosForm forma = (BusquedaCandidatosForm)session.getAttribute("busquedaCandidatosForm");;
		String tipoOrdenamiento = request.getParameter("tipoOrden");
		String numeroColumna = request.getParameter("tipoColumna");
		List<ResultadoBusquedaCandidatosVO> resultado = null;
		try{
			
			forma.setTipoOrdenamiento(tipoOrdenamiento);
			forma.setNumeroColumna(Integer.parseInt(numeroColumna));
				
			resultado = (List<ResultadoBusquedaCandidatosVO>) session.getAttribute(FULL_LIST);

			
			TablaBusEspCandidatos tabla = new TablaBusEspCandidatos(resultado);
			tabla.ordenarTabla(Integer.parseInt(numeroColumna), tipoOrdenamiento);
			resultado = tabla.getLista();
		
			session.removeAttribute(FULL_LIST);
			session.setAttribute(FULL_LIST, resultado);
			

		}catch(Exception e){
			logger.error(e);
		}
		
		return page(mapping, form, request, response);
	}
}
