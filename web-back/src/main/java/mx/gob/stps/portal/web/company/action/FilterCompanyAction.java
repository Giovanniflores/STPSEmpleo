package mx.gob.stps.portal.web.company.action;

import static mx.gob.stps.portal.core.infra.utils.Constantes.CATALOGO_OPCION_MOTIVO_DESACTIVACION_EMPRESA;
import static mx.gob.stps.portal.web.infra.utils.Constantes.BODY_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_JSP;
import static mx.gob.stps.portal.web.infra.utils.Constantes.FORWARD_TEMPLATE_ESP_ADMIN;
import static mx.gob.stps.portal.web.infra.utils.Constantes.JSP_SUCCESS;
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

import mx.gob.stps.portal.core.empresa.vo.EmpresaPorAutorizarVO;
import mx.gob.stps.portal.core.empresa.vo.EmpresaVO;
import mx.gob.stps.portal.core.infra.exception.BusinessException;
import mx.gob.stps.portal.core.infra.utils.Constantes;
import mx.gob.stps.portal.core.infra.utils.Constantes.ENTIDADES_FEDERATIVAS;
import mx.gob.stps.portal.core.infra.utils.Constantes.PERFIL;
import mx.gob.stps.portal.core.infra.utils.Utils;
import mx.gob.stps.portal.core.infra.vo.BitacoraVO;
import mx.gob.stps.portal.persistencia.vo.CatalogoOpcionVO;
import mx.gob.stps.portal.core.seguridad.vo.UsuarioVO;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegate;
import mx.gob.stps.portal.web.address.delegate.DomicilioBusDelegateImpl;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegate;
import mx.gob.stps.portal.web.company.delegate.RegisterBusDelegateImpl;
import mx.gob.stps.portal.web.company.form.FilterCompanyForm;
import mx.gob.stps.portal.web.company.vo.CompanyVO;
import mx.gob.stps.portal.web.infra.action.PagerAction;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegate;
import mx.gob.stps.portal.web.infra.delegate.CatalogoOpcionDelegateImpl;
import mx.gob.stps.portal.web.infra.exception.ServiceLocatorException;
import mx.gob.stps.portal.web.infra.utils.PropertiesLoader;
import mx.gob.stps.portal.web.infra.vo.CatalogoVO;
import mx.gob.stps.portal.web.infra.vo.ResultVO;
import mx.gob.stps.portal.web.security.delegate.SecutityDelegateImpl;
import mx.gob.stps.portal.web.security.vo.UsuarioWebVO;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FilterCompanyAction extends PagerAction{

	private static Logger logger = Logger.getLogger(FilterCompanyAction.class);
	
	private static final String FORWARD_DETALLE_MOTIVO = "ACTION_DESACTIVACION_MOTIVO";

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {	
			FilterCompanyForm filterForm = (FilterCompanyForm)form;
			filterForm.reset();

			UsuarioWebVO webVo = getUsuario(request.getSession());
			filterForm.setIdUsuario(webVo.getIdUsuario());
			filterForm.setIdTipoUsuario(webVo.getIdTipoUsuario());
			filterForm.setIdPerfil(webVo.getIdPerfil());
			UsuarioVO usuarioVo;

			usuarioVo = SecutityDelegateImpl.getInstance().consultaUsuario(webVo.getIdUsuario());



			List<EmpresaVO> lstEmpresas = new ArrayList<EmpresaVO>();
			request.getSession().setAttribute(FULL_LIST, lstEmpresas); //paginacion
			request.setAttribute("init", "init"); // para identificar desde el formulario cuando se ha invocado el método init

			request.getSession().setAttribute("FILTER_PAGE", "FILTER_COMPANY");
			request.getSession().setAttribute("TITULO", "Administrador");
			request.getSession().setAttribute("datosAdmin", usuarioVo.getNombre()+" "+usuarioVo.getApellido1()+" "+usuarioVo.getApellido2());
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        PropertiesLoader properties = PropertiesLoader.getInstance();

		request.getSession().setAttribute(TITULO_PAGINA, "Empresas");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Empresas, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
	}

	public ActionForward limpiar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		FilterCompanyForm filterForm = (FilterCompanyForm)form;
		filterForm.reset();
		return init(mapping, form, request, response);
	}

	public ActionForward showCompany(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		long idEmpresa = Utils.parseLong(request.getParameter("idEmpresa"));

		UsuarioWebVO usrWebVo = getUsuario(request.getSession());
		usrWebVo.setIdPropietario(idEmpresa);		

		request.getSession().setAttribute("ID_EMPRESA", idEmpresa);	

		try {
			RegisterBusDelegate serviceEmp = RegisterBusDelegateImpl.getInstance();
			EmpresaVO empresaVo = serviceEmp.findEmpresaById(idEmpresa);
			String idPortalEmpleo = empresaVo.getIdPortalEmpleo();

			String nombreContacto = empresaVo.getContactoEmpresa();

			String nombreEmpresa = empresaVo.getNombreEmpresa();

			request.getSession().setAttribute("ID_PORTALEMPLEO", idPortalEmpleo);	
			request.getSession().setAttribute("NOMBRECONTACTOEMP", nombreContacto);	
			request.getSession().setAttribute("NOMBREEMPRESA", nombreEmpresa);	

		} catch (BusinessException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		} catch (SQLException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.sql.persist");
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.locator");
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(JSP_SUCCESS).getPath());
		return mapping.findForward(JSP_SUCCESS);		
	}	

	
	public ActionForward reactivarEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		StringBuffer mensajeDeExito = new StringBuffer();
		ResultVO msg =  null;
		boolean esAdministrador= false;
		HttpSession session = request.getSession();
		FilterCompanyForm filterCompanyForm = (FilterCompanyForm)form;
		long idEmpresaAReactivar = Utils.parseLong(request.getParameter("idEmpresaAReactivar"));
		UsuarioWebVO usuario = getUsuario(session);		
		
		try{
			if(null != usuario){
				if(usuario.getIdPerfil()==PERFIL.ADMINISTRADOR.getIdOpcion())
					esAdministrador=true;
			}			
			
			if(idEmpresaAReactivar<=0){
				registraError(request, "aut.error.requerido", "Identificador de la empresa a reactivar");
				request.getSession().setAttribute(BODY_JSP, mapping.getInput());

		        PropertiesLoader properties = PropertiesLoader.getInstance();
				request.getSession().setAttribute(TITULO_PAGINA, "Empresas");
				request.getSession().setAttribute(DESCRIPCION_PAGINA, "Empresas, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
				request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
				request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
				request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
				return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
				
			} else {
				if(null != usuario){
					RegisterBusDelegate services = RegisterBusDelegateImpl.getInstance();				
					
					EmpresaVO empresaVo = services.findEmpresaById(idEmpresaAReactivar);
					if(null != empresaVo){
						filterCompanyForm.setIdPortalEmpleo(empresaVo.getIdPortalEmpleo());
						mensajeDeExito.append("La empresa " + empresaVo.getNombreEmpresa() + "con Folio " + idEmpresaAReactivar + " se ha reactivado exitosamente.");
						services.reactivarEmpresa(idEmpresaAReactivar,empresaVo.getIdUsuario(), usuario.getIdUsuario());
					}																
				}				
			}
						
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			//TODO: generar propiedades para errores particulares de filterCompany
			registraError(request, "aut.autorizacion.msg.error");			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
			//TODO: generar propiedades para errores particulares de filterCompany
			registraError(request, "aut.error.sql.persist");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
			//TODO: generar propiedades para errores particulares de filterCompany
			registraError(request, "aut.error.locator");
		}		
				
		//request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        //return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		msg = new ResultVO(getMensaje(request, mensajeDeExito.toString()), ResultVO.TYPE_SUCCESS);
		filterCompanyForm.setMsg(msg);
        return buscar(mapping, filterCompanyForm, request, response);			        		
	}
	
	
	public ActionForward desactivarEmpresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		StringBuffer mensajeDeExito = new StringBuffer();
		ResultVO msg =  null;
		boolean esAdministrador= false;
		HttpSession session = request.getSession();
		FilterCompanyForm filterCompanyForm = (FilterCompanyForm)form;
		long idEmpresaADesactivar = Utils.parseLong(request.getParameter("idEmpresaADesactivar"));
		UsuarioWebVO usuario = getUsuario(session);
			
		try {
			if(null != usuario){
				if(usuario.getIdPerfil()==PERFIL.ADMINISTRADOR.getIdOpcion())
					esAdministrador=true;
			}
			
			if(idEmpresaADesactivar<=0){
				registraError(request, "aut.error.requerido", "Identificador de la empresa a desactivar");
				request.getSession().setAttribute(BODY_JSP, mapping.getInput());

		        PropertiesLoader properties = PropertiesLoader.getInstance();
				request.getSession().setAttribute(TITULO_PAGINA, "Empresas");
				request.getSession().setAttribute(DESCRIPCION_PAGINA, "Empresas, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
				request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
				request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
				request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
				return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
				
			} else{
					
				if(null != usuario){
					
					int idMotivoDesactivacion = filterCompanyForm.getIdMotivoDesactivacion();
					String detalleDesactivacion = filterCompanyForm.getMotivoDesactivacion();			
					RegisterBusDelegate services = RegisterBusDelegateImpl.getInstance();				
					
					EmpresaVO empresaVo = services.findEmpresaById(idEmpresaADesactivar);
					if(null != empresaVo){
						filterCompanyForm.setIdPortalEmpleo(empresaVo.getIdPortalEmpleo());
						mensajeDeExito.append("La empresa " + empresaVo.getNombreEmpresa() + "con Folio " + idEmpresaADesactivar + " se ha desactivado exitosamente.");
						services.desactivarEmpresa(idEmpresaADesactivar, empresaVo.getIdUsuario(), usuario.getIdUsuario(), idMotivoDesactivacion, detalleDesactivacion);
					}						
				}
			}							
			
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error(e);
			//TODO: generar propiedades para errores particulares de filterCompany
			registraError(request, "aut.autorizacion.msg.error");			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e);
			//TODO: generar propiedades para errores particulares de filterCompany
			registraError(request, "aut.error.sql.persist");
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			logger.error(e);
			//TODO: generar propiedades para errores particulares de filterCompany
			registraError(request, "aut.error.locator");
		}

		//request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());
        //return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);
		msg = new ResultVO(getMensaje(request, mensajeDeExito.toString()), ResultVO.TYPE_SUCCESS);
		filterCompanyForm.setMsg(msg);
        return buscar(mapping, filterCompanyForm, request, response);			        
	}	
	
	public ActionForward motivosDesactivacionCatalogo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		List<CatalogoOpcionVO> motivos = null;
		
		try{
			FilterCompanyForm filterCompanyForm = (FilterCompanyForm)form;
			
			motivos = CatalogoOpcionDelegateImpl.getInstance().consultarCatalogo(CATALOGO_OPCION_MOTIVO_DESACTIVACION_EMPRESA);							
			
		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			motivos = new ArrayList<CatalogoOpcionVO>();
		}
		
		try {
			CatalogoVO cat = getCatalogo(motivos);
			String json = toJson(cat);
			redirectJsonResponse(response, json);
		} catch (IOException e) {
			e.printStackTrace(); logger.error(e);
		}
		
		return null;
	}

	public ActionForward motivoDesactivacionDetalle(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		FilterCompanyForm filterForm = (FilterCompanyForm)form;
		long idEmpresaADesactivar = Utils.parseLong(request.getParameter("idEmpresaADesactivar"));
		filterForm.setIdEmpresaADesactivar(idEmpresaADesactivar);
		return mapping.findForward(FORWARD_DETALLE_MOTIVO);
	}		
		
	public ActionForward buscar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		FilterCompanyForm filterForm = (FilterCompanyForm)form;

		try{
			session.removeAttribute("NUM_PAGE_LIST");
			session.removeAttribute("TOTAL_PAGES");
			session.removeAttribute("PAGE_JUMP_SIZE");
			session.removeAttribute("NUM_PAGE_JUMP");
			session.removeAttribute("NUM_RECORDS_VISIBLE");
			session.removeAttribute("NUM_RECORDS_TOTAL");

			RegisterBusDelegate services = RegisterBusDelegateImpl.getInstance();
			List<EmpresaVO> lstEmpresas = services.consultaEmpresas(filterForm.getIdTipoPersona(),
					filterForm.getCorreoElectronico(), filterForm.getIdPortalEmpleo(),
					filterForm.getNombre(), filterForm.getApellido1(),
					filterForm.getRazonSocial(), filterForm.getFechaActa(),
					filterForm.getApellido2(), filterForm.getIdEmpresa(), filterForm.getContacto(), filterForm.getTelefono(), 
					filterForm.getDomicilio(), filterForm.getIdEntidad(), filterForm.getIdMunicipio(), filterForm.getUsuario());
			
			filterForm.setLstEmpresas(lstEmpresas);
			this.PAGE_NUM_ROW = 10;
			this.PAGE_JUMP_SIZE = 10;

			request.getSession().setAttribute(FULL_LIST, lstEmpresas); //paginacion

		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.locator");
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}

		request.getSession().setAttribute(BODY_JSP, mapping.findForward(FORWARD_JSP).getPath());

        PropertiesLoader properties = PropertiesLoader.getInstance();
		request.getSession().setAttribute(TITULO_PAGINA, "Empresas");
		request.getSession().setAttribute(DESCRIPCION_PAGINA, "Empresas, Secretaría del Trabajo y Previsión Social, Servicio Nacional de Empleo, Portal del Empleo");
		request.getSession().setAttribute(FACEBOOK_IMAGE, properties.getProperty("app.context.url.ssl")+"/css/images/contenido-compartir.jpg");
		request.getSession().setAttribute(TWITTER_IMAGE, properties.getProperty("app.context.url.ssl") + "/css/images/compartir-contenido-tweetA.jpg");
		request.getSession().setAttribute(URL_ESPECIFICA, properties.getProperty("app.context.url.ssl")+ response.encodeURL(request.getRequestURI().toString()));
		return mapping.findForward(FORWARD_TEMPLATE_ESP_ADMIN);	        				
	}

	public ActionForward recuperaempresa(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		FilterCompanyForm filterForm = (FilterCompanyForm)form;
		long idEmpresa = Utils.parseLong(request.getParameter("idEmpresa"));
		String tipoEmpresa = request.getParameter("TIPO_EMPRESA_ELIM");

		try {
			RegisterBusDelegate services = RegisterBusDelegateImpl.getInstance();

			String nombreEmpresa = null;
			String correoElectronico = null;

			if ("EMPRESA".equals(tipoEmpresa)){
				EmpresaVO empresa = services.recuperaEmpresaEliminada(idEmpresa);
				nombreEmpresa = empresa.getNombreEmpresa();
				correoElectronico = empresa.getCorreoElectronico();
			} else if ("EMPRESA_POR_AUTORIZAR".equals(tipoEmpresa)){
				EmpresaPorAutorizarVO empresa = services.recuperaEmpresaPorAutorizarEliminada(idEmpresa);
				nombreEmpresa = empresa.getNombreEmpresa();
				correoElectronico = empresa.getCorreoElectronico();
			}

			registraMensaje(request, "emp.buscador.recuperar.exito", nombreEmpresa);

			filterForm.setCorreoElectronico(correoElectronico);

		} catch (ServiceLocatorException e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.error.locator");
		} catch (Exception e) {
			e.printStackTrace(); logger.error(e);
			registraError(request, "aut.autorizacion.msg.error");
		}

		return buscar(mapping, form, request, response);
	}	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected List getRows(int pagenum, List<?> rows){
		List<EmpresaVO> rowsPage = super.getRows(pagenum, rows);

		RegisterBusDelegate services = RegisterBusDelegateImpl.getInstance();

		List<CompanyVO> empresas = new ArrayList<CompanyVO>();

		for (EmpresaVO empresarow : rowsPage){
			CompanyVO empresa = CompanyVO.getInstance(empresarow);

			try {
				empresa.setEsEmpresaSNE(empresarow.getEsEmpresaSNE());
				
				List<BitacoraVO> movimientos = services.consultaMovimientos(empresarow.getIdEmpresa());
				empresa.setMovimientos(movimientos);
			} catch (ServiceLocatorException e) {
				e.printStackTrace(); logger.error(e);
			}

			empresas.add(empresa);
		}

		return empresas;
	}

	public ActionForward consultarEntidades(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CatalogoOpcionDelegate service = CatalogoOpcionDelegateImpl.getInstance();			
			List<CatalogoOpcionVO> opciones = service.consultarCatalogo(Constantes.CATALOGO_OPCION_ENTIDAD_FEDERATIVA);
			int i = 0;
			int iRemove = 0;
			for (CatalogoOpcionVO o: opciones){
				if (o.getIdCatalogoOpcion() == Utils.toLong(ENTIDADES_FEDERATIVAS.NACIDO_EN_EL_EXTRANJERO.getIdEntidad())){
					iRemove = i;
				}
				i++;
			}
			opciones.remove(iRemove);
						
			redirectJson(response, opciones);
		} catch (PersistenceException e) {
			logger.error("Error en FiltraCandidatoAction.consultarCatalogo");			
			logger.error(e);
		} catch (ServiceLocatorException e) {
			logger.error("Error en FiltraCandidatoAction.consultarCatalogo");			
			logger.error(e);			
		} catch (IOException e) {
			logger.error("Error en FiltraCandidatoAction.consultarCatalogo");			
			logger.error(e);
		}
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
			logger.error("Error en FiltraCandidatoAction.consultarMunicipio");			
			logger.error(e);			
		} catch (PersistenceException e) {
			logger.error("Error en FiltraCandidatoAction.consultarMunicipio");			
			logger.error(e);			
		} catch (ServiceLocatorException e) {
			logger.error("Error en FiltraCandidatoAction.consultarMunicipio");			
			logger.error(e);
		} catch (IOException e) {
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
